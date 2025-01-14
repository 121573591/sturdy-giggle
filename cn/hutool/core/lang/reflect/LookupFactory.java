package cn.hutool.core.lang.reflect;

import cn.hutool.core.exceptions.UtilException;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class LookupFactory {
  private static final int ALLOWED_MODES = 15;
  
  private static Constructor<MethodHandles.Lookup> java8LookupConstructor;
  
  private static Method privateLookupInMethod;
  
  static {
    try {
      privateLookupInMethod = MethodHandles.class.getMethod("privateLookupIn", new Class[] { Class.class, MethodHandles.Lookup.class });
    } catch (NoSuchMethodException noSuchMethodException) {}
    if (privateLookupInMethod == null)
      try {
        java8LookupConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(new Class[] { Class.class, int.class });
        java8LookupConstructor.setAccessible(true);
      } catch (NoSuchMethodException e) {
        throw new IllegalStateException("There is neither 'privateLookupIn(Class, Lookup)' nor 'Lookup(Class, int)' method in java.lang.invoke.MethodHandles.", e);
      }  
  }
  
  public static MethodHandles.Lookup lookup(Class<?> callerClass) {
    if (privateLookupInMethod != null)
      try {
        return (MethodHandles.Lookup)privateLookupInMethod.invoke(MethodHandles.class, new Object[] { callerClass, MethodHandles.lookup() });
      } catch (IllegalAccessException|java.lang.reflect.InvocationTargetException e) {
        throw new UtilException(e);
      }  
    try {
      return java8LookupConstructor.newInstance(new Object[] { callerClass, Integer.valueOf(15) });
    } catch (Exception e) {
      throw new IllegalStateException("no 'Lookup(Class, int)' method in java.lang.invoke.MethodHandles.", e);
    } 
  }
}
