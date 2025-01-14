package cn.hutool.core.lang;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.EnumerationIter;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassScanner implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private final String packageName;
  
  private final String packageNameWithDot;
  
  private final String packageDirName;
  
  private final String packagePath;
  
  private final Filter<Class<?>> classFilter;
  
  private final Charset charset;
  
  private ClassLoader classLoader;
  
  private boolean initialize;
  
  private final Set<Class<?>> classes = new HashSet<>();
  
  private boolean ignoreLoadError = false;
  
  private final Set<String> classesOfLoadError = new HashSet<>();
  
  public static Set<Class<?>> scanAllPackageByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
    return scanAllPackage(packageName, clazz -> clazz.isAnnotationPresent(annotationClass));
  }
  
  public static Set<Class<?>> scanPackageByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
    return scanPackage(packageName, clazz -> clazz.isAnnotationPresent(annotationClass));
  }
  
  public static Set<Class<?>> scanAllPackageBySuper(String packageName, Class<?> superClass) {
    return scanAllPackage(packageName, clazz -> (superClass.isAssignableFrom(clazz) && !superClass.equals(clazz)));
  }
  
  public static Set<Class<?>> scanPackageBySuper(String packageName, Class<?> superClass) {
    return scanPackage(packageName, clazz -> (superClass.isAssignableFrom(clazz) && !superClass.equals(clazz)));
  }
  
  public static Set<Class<?>> scanAllPackage() {
    return scanAllPackage("", null);
  }
  
  public static Set<Class<?>> scanPackage() {
    return scanPackage("", null);
  }
  
  public static Set<Class<?>> scanPackage(String packageName) {
    return scanPackage(packageName, null);
  }
  
  public static Set<Class<?>> scanAllPackage(String packageName, Filter<Class<?>> classFilter) {
    return (new ClassScanner(packageName, classFilter)).scan(true);
  }
  
  public static Set<Class<?>> scanPackage(String packageName, Filter<Class<?>> classFilter) {
    return (new ClassScanner(packageName, classFilter)).scan();
  }
  
  public ClassScanner() {
    this(null);
  }
  
  public ClassScanner(String packageName) {
    this(packageName, null);
  }
  
  public ClassScanner(String packageName, Filter<Class<?>> classFilter) {
    this(packageName, classFilter, CharsetUtil.CHARSET_UTF_8);
  }
  
  public ClassScanner(String packageName, Filter<Class<?>> classFilter, Charset charset) {
    packageName = StrUtil.nullToEmpty(packageName);
    this.packageName = packageName;
    this.packageNameWithDot = StrUtil.addSuffixIfNot(packageName, ".");
    this.packageDirName = packageName.replace('.', File.separatorChar);
    this.packagePath = packageName.replace('.', '/');
    this.classFilter = classFilter;
    this.charset = charset;
  }
  
  public ClassScanner setIgnoreLoadError(boolean ignoreLoadError) {
    this.ignoreLoadError = ignoreLoadError;
    return this;
  }
  
  public Set<Class<?>> scan() {
    return scan(false);
  }
  
  public Set<Class<?>> scan(boolean forceScanJavaClassPaths) {
    this.classes.clear();
    this.classesOfLoadError.clear();
    for (URL url : ResourceUtil.getResourceIter(this.packagePath, this.classLoader)) {
      switch (url.getProtocol()) {
        case "file":
          scanFile(new File(URLUtil.decode(url.getFile(), this.charset.name())), null);
        case "jar":
          scanJar(URLUtil.getJarFile(url));
      } 
    } 
    if (forceScanJavaClassPaths || CollUtil.isEmpty(this.classes))
      scanJavaClassPaths(); 
    return Collections.unmodifiableSet(this.classes);
  }
  
  public void setInitialize(boolean initialize) {
    this.initialize = initialize;
  }
  
  public void setClassLoader(ClassLoader classLoader) {
    this.classLoader = classLoader;
  }
  
  public Set<String> getClassesOfLoadError() {
    return Collections.unmodifiableSet(this.classesOfLoadError);
  }
  
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
  
  private void scanJavaClassPaths() {
    String[] javaClassPaths = ClassUtil.getJavaClassPaths();
    for (String classPath : javaClassPaths) {
      classPath = URLUtil.decode(classPath, CharsetUtil.systemCharsetName());
      scanFile(new File(classPath), null);
    } 
  }
  
  private void scanFile(File file, String rootDir) {
    if (file.isFile()) {
      String fileName = file.getAbsolutePath();
      if (fileName.endsWith(".class")) {
        String className = fileName.substring(rootDir.length(), fileName.length() - 6).replace(File.separatorChar, '.');
        addIfAccept(className);
      } else if (fileName.endsWith(".jar")) {
        try {
          scanJar(new JarFile(file));
        } catch (IOException e) {
          throw new IORuntimeException(e);
        } 
      } 
    } else if (file.isDirectory()) {
      File[] files = file.listFiles();
      if (null != files)
        for (File subFile : files)
          scanFile(subFile, (null == rootDir) ? subPathBeforePackage(file) : rootDir);  
    } 
  }
  
  private void scanJar(JarFile jar) {
    try {
      for (JarEntry entry : new EnumerationIter(jar.entries())) {
        String name = StrUtil.removePrefix(entry.getName(), "/");
        if ((StrUtil.isEmpty(this.packagePath) || name.startsWith(this.packagePath)) && 
          name.endsWith(".class") && false == entry.isDirectory()) {
          String className = name.substring(0, name.length() - 6).replace('/', '.');
          addIfAccept(loadClass(className));
        } 
      } 
    } finally {
      IoUtil.close(jar);
    } 
  }
  
  protected Class<?> loadClass(String className) {
    ClassLoader loader = this.classLoader;
    if (null == loader) {
      loader = ClassLoaderUtil.getClassLoader();
      this.classLoader = loader;
    } 
    Class<?> clazz = null;
    try {
      clazz = Class.forName(className, this.initialize, loader);
    } catch (NoClassDefFoundError|ClassNotFoundException e) {
      this.classesOfLoadError.add(className);
    } catch (UnsupportedClassVersionError e) {
      this.classesOfLoadError.add(className);
    } catch (Throwable e) {
      if (false == this.ignoreLoadError)
        throw ExceptionUtil.wrapRuntime(e); 
      this.classesOfLoadError.add(className);
    } 
    return clazz;
  }
  
  private void addIfAccept(String className) {
    if (StrUtil.isBlank(className))
      return; 
    int classLen = className.length();
    int packageLen = this.packageName.length();
    if (classLen == packageLen) {
      if (className.equals(this.packageName))
        addIfAccept(loadClass(className)); 
    } else if (classLen > packageLen) {
      if (".".equals(this.packageNameWithDot) || className.startsWith(this.packageNameWithDot))
        addIfAccept(loadClass(className)); 
    } 
  }
  
  private void addIfAccept(Class<?> clazz) {
    if (null != clazz) {
      Filter<Class<?>> classFilter = this.classFilter;
      if (classFilter == null || classFilter.accept(clazz))
        this.classes.add(clazz); 
    } 
  }
  
  private String subPathBeforePackage(File file) {
    String filePath = file.getAbsolutePath();
    if (StrUtil.isNotEmpty(this.packageDirName))
      filePath = StrUtil.subBefore(filePath, this.packageDirName, true); 
    return StrUtil.addSuffixIfNot(filePath, File.separator);
  }
}
