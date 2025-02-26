package com.fasterxml.jackson.databind.util;

public class NativeImageUtil {
  private static final boolean RUNNING_IN_SVM = (System.getProperty("org.graalvm.nativeimage.imagecode") != null);
  
  public static boolean isInNativeImageAndIsAtRuntime() {
    return (RUNNING_IN_SVM && "runtime".equals(System.getProperty("org.graalvm.nativeimage.imagecode")));
  }
  
  public static boolean isInNativeImage() {
    return RUNNING_IN_SVM;
  }
  
  public static boolean isUnsupportedFeatureError(Throwable e) {
    if (!isInNativeImageAndIsAtRuntime())
      return false; 
    if (e instanceof java.lang.reflect.InvocationTargetException)
      e = e.getCause(); 
    return e.getClass().getName().equals("com.oracle.svm.core.jdk.UnsupportedFeatureError");
  }
  
  public static boolean needsReflectionConfiguration(Class<?> cl) {
    if (!isInNativeImageAndIsAtRuntime())
      return false; 
    return (((cl.getDeclaredFields()).length == 0 || ClassUtil.isRecordType(cl)) && (cl
      .getDeclaredMethods()).length == 0 && (cl
      .getDeclaredConstructors()).length == 0);
  }
}
