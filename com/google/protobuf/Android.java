package com.google.protobuf;

final class Android {
  private static boolean ASSUME_ANDROID;
  
  private static final Class<?> MEMORY_CLASS = getClassForName("libcore.io.Memory");
  
  private static final boolean IS_ROBOLECTRIC = (!ASSUME_ANDROID && 
    getClassForName("org.robolectric.Robolectric") != null);
  
  static boolean isOnAndroidDevice() {
    return (ASSUME_ANDROID || (MEMORY_CLASS != null && !IS_ROBOLECTRIC));
  }
  
  static Class<?> getMemoryClass() {
    return MEMORY_CLASS;
  }
  
  private static <T> Class<T> getClassForName(String name) {
    try {
      return (Class)Class.forName(name);
    } catch (Throwable e) {
      return null;
    } 
  }
}
