package cn.hutool.core.util;

import cn.hutool.core.convert.BasicType;
import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.JarClassLoader;
import cn.hutool.core.map.SafeConcurrentHashMap;
import java.io.File;
import java.lang.reflect.Array;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassLoaderUtil {
  private static final String ARRAY_SUFFIX = "[]";
  
  private static final String INTERNAL_ARRAY_PREFIX = "[";
  
  private static final String NON_PRIMITIVE_ARRAY_PREFIX = "[L";
  
  private static final char PACKAGE_SEPARATOR = '.';
  
  private static final char INNER_CLASS_SEPARATOR = '$';
  
  private static final Map<String, Class<?>> PRIMITIVE_TYPE_NAME_MAP = (Map<String, Class<?>>)new SafeConcurrentHashMap(32);
  
  static {
    List<Class<?>> primitiveTypes = new ArrayList<>(32);
    primitiveTypes.addAll(BasicType.PRIMITIVE_WRAPPER_MAP.keySet());
    primitiveTypes.add(boolean[].class);
    primitiveTypes.add(byte[].class);
    primitiveTypes.add(char[].class);
    primitiveTypes.add(double[].class);
    primitiveTypes.add(float[].class);
    primitiveTypes.add(int[].class);
    primitiveTypes.add(long[].class);
    primitiveTypes.add(short[].class);
    primitiveTypes.add(void.class);
    for (Class<?> primitiveType : primitiveTypes)
      PRIMITIVE_TYPE_NAME_MAP.put(primitiveType.getName(), primitiveType); 
  }
  
  public static ClassLoader getContextClassLoader() {
    if (System.getSecurityManager() == null)
      return Thread.currentThread().getContextClassLoader(); 
    return AccessController.<ClassLoader>doPrivileged(() -> Thread.currentThread().getContextClassLoader());
  }
  
  public static ClassLoader getSystemClassLoader() {
    if (System.getSecurityManager() == null)
      return ClassLoader.getSystemClassLoader(); 
    return AccessController.<ClassLoader>doPrivileged(ClassLoader::getSystemClassLoader);
  }
  
  public static ClassLoader getClassLoader() {
    ClassLoader classLoader = getContextClassLoader();
    if (classLoader == null) {
      classLoader = ClassLoaderUtil.class.getClassLoader();
      if (null == classLoader)
        classLoader = getSystemClassLoader(); 
    } 
    return classLoader;
  }
  
  public static Class<?> loadClass(String name) throws UtilException {
    return loadClass(name, true);
  }
  
  public static Class<?> loadClass(String name, boolean isInitialized) throws UtilException {
    return loadClass(name, null, isInitialized);
  }
  
  public static Class<?> loadClass(String name, ClassLoader classLoader, boolean isInitialized) throws UtilException {
    Assert.notNull(name, "Name must not be null", new Object[0]);
    name = name.replace('/', '.');
    if (null == classLoader)
      classLoader = getClassLoader(); 
    Class<?> clazz = loadPrimitiveClass(name);
    if (clazz == null)
      clazz = doLoadClass(name, classLoader, isInitialized); 
    return clazz;
  }
  
  public static Class<?> loadPrimitiveClass(String name) {
    Class<?> result = null;
    if (StrUtil.isNotBlank(name)) {
      name = name.trim();
      if (name.length() <= 8)
        result = PRIMITIVE_TYPE_NAME_MAP.get(name); 
    } 
    return result;
  }
  
  public static JarClassLoader getJarClassLoader(File jarOrDir) {
    return JarClassLoader.load(jarOrDir);
  }
  
  public static Class<?> loadClass(File jarOrDir, String name) {
    try {
      return getJarClassLoader(jarOrDir).loadClass(name);
    } catch (ClassNotFoundException e) {
      throw new UtilException(e);
    } 
  }
  
  public static boolean isPresent(String className) {
    return isPresent(className, null);
  }
  
  public static boolean isPresent(String className, ClassLoader classLoader) {
    try {
      loadClass(className, classLoader, false);
      return true;
    } catch (Throwable ex) {
      return false;
    } 
  }
  
  private static Class<?> doLoadClass(String name, ClassLoader classLoader, boolean isInitialized) {
    Class<?> clazz;
    if (name.endsWith("[]")) {
      String elementClassName = name.substring(0, name.length() - "[]".length());
      Class<?> elementClass = loadClass(elementClassName, classLoader, isInitialized);
      clazz = Array.newInstance(elementClass, 0).getClass();
    } else if (name.startsWith("[L") && name.endsWith(";")) {
      String elementName = name.substring("[L".length(), name.length() - 1);
      Class<?> elementClass = loadClass(elementName, classLoader, isInitialized);
      clazz = Array.newInstance(elementClass, 0).getClass();
    } else if (name.startsWith("[")) {
      String elementName = name.substring("[".length());
      Class<?> elementClass = loadClass(elementName, classLoader, isInitialized);
      clazz = Array.newInstance(elementClass, 0).getClass();
    } else {
      if (null == classLoader)
        classLoader = getClassLoader(); 
      try {
        clazz = Class.forName(name, isInitialized, classLoader);
      } catch (ClassNotFoundException ex) {
        clazz = tryLoadInnerClass(name, classLoader, isInitialized);
        if (null == clazz)
          throw new UtilException(ex); 
      } 
    } 
    return clazz;
  }
  
  private static Class<?> tryLoadInnerClass(String name, ClassLoader classLoader, boolean isInitialized) {
    int lastDotIndex = name.lastIndexOf('.');
    if (lastDotIndex > 0) {
      String innerClassName = name.substring(0, lastDotIndex) + '$' + name.substring(lastDotIndex + 1);
      try {
        return Class.forName(innerClassName, isInitialized, classLoader);
      } catch (ClassNotFoundException classNotFoundException) {}
    } 
    return null;
  }
}
