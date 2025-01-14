package cn.hutool.core.lang.reflect;

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

public class MethodHandleUtil {
  public static MethodHandles.Lookup lookup(Class<?> callerClass) {
    return LookupFactory.lookup(callerClass);
  }
  
  public static MethodHandle findMethod(Class<?> callerClass, String name, MethodType type) {
    if (StrUtil.isBlank(name))
      return findConstructor(callerClass, type); 
    MethodHandle handle = null;
    MethodHandles.Lookup lookup = lookup(callerClass);
    try {
      handle = lookup.findVirtual(callerClass, name, type);
    } catch (IllegalAccessException|NoSuchMethodException illegalAccessException) {}
    if (null == handle)
      try {
        handle = lookup.findStatic(callerClass, name, type);
      } catch (IllegalAccessException|NoSuchMethodException illegalAccessException) {} 
    if (null == handle)
      try {
        handle = lookup.findSpecial(callerClass, name, type, callerClass);
      } catch (NoSuchMethodException noSuchMethodException) {
      
      } catch (IllegalAccessException e) {
        throw new UtilException(e);
      }  
    return handle;
  }
  
  public static MethodHandle findConstructor(Class<?> callerClass, Class<?>... args) {
    return findConstructor(callerClass, MethodType.methodType(void.class, args));
  }
  
  public static MethodHandle findConstructor(Class<?> callerClass, MethodType type) {
    MethodHandles.Lookup lookup = lookup(callerClass);
    try {
      return lookup.findConstructor(callerClass, type);
    } catch (NoSuchMethodException e) {
      return null;
    } catch (IllegalAccessException e) {
      throw new UtilException(e);
    } 
  }
  
  public static <T> T invokeSpecial(Object obj, String methodName, Object... args) {
    Assert.notNull(obj, "Object to get method must be not null!", new Object[0]);
    Assert.notBlank(methodName, "Method name must be not blank!", new Object[0]);
    Method method = ReflectUtil.getMethodOfObj(obj, methodName, args);
    if (null == method)
      throw new UtilException("No such method: [{}] from [{}]", new Object[] { methodName, obj.getClass() }); 
    return invokeSpecial(obj, method, args);
  }
  
  public static <T> T invoke(Object obj, Method method, Object... args) {
    return invoke(false, obj, method, args);
  }
  
  public static <T> T invokeSpecial(Object obj, Method method, Object... args) {
    return invoke(true, obj, method, args);
  }
  
  public static <T> T invoke(boolean isSpecial, Object obj, Method method, Object... args) {
    Assert.notNull(method, "Method must be not null!", new Object[0]);
    Class<?> declaringClass = method.getDeclaringClass();
    MethodHandles.Lookup lookup = lookup(declaringClass);
    try {
      MethodHandle handle = isSpecial ? lookup.unreflectSpecial(method, declaringClass) : lookup.unreflect(method);
      if (null != obj)
        handle = handle.bindTo(obj); 
      return (T)handle.invokeWithArguments(args);
    } catch (Throwable e) {
      throw new UtilException(e);
    } 
  }
}
