package org.openjdk.nashorn.internal.runtime.arrays;

import org.openjdk.nashorn.internal.runtime.JSType;

public final class ArrayIndex {
  private static final int INVALID_ARRAY_INDEX = -1;
  
  private static final long MAX_ARRAY_INDEX = 4294967294L;
  
  private static long fromString(String key) {
    long value = 0L;
    int length = key.length();
    if (length == 0 || (length > 1 && key.charAt(0) == '0'))
      return -1L; 
    for (int i = 0; i < length; i++) {
      char digit = key.charAt(i);
      if (digit < '0' || digit > '9')
        return -1L; 
      value = value * 10L + digit - 48L;
      if (value > 4294967294L)
        return -1L; 
    } 
    return value;
  }
  
  public static int getArrayIndex(Object key) {
    if (key instanceof Integer)
      return getArrayIndex(((Integer)key).intValue()); 
    if (key instanceof Double)
      return getArrayIndex(((Double)key).doubleValue()); 
    if (key instanceof String)
      return (int)fromString((String)key); 
    if (key instanceof Long)
      return getArrayIndex(((Long)key).longValue()); 
    if (key instanceof org.openjdk.nashorn.internal.runtime.ConsString)
      return (int)fromString(key.toString()); 
    assert !(key instanceof org.openjdk.nashorn.internal.runtime.ScriptObject);
    return -1;
  }
  
  public static int getArrayIndex(int key) {
    return (key >= 0) ? key : -1;
  }
  
  public static int getArrayIndex(long key) {
    if (key >= 0L && key <= 4294967294L)
      return (int)key; 
    return -1;
  }
  
  public static int getArrayIndex(double key) {
    if (JSType.isRepresentableAsInt(key))
      return getArrayIndex((int)key); 
    if (JSType.isRepresentableAsLong(key))
      return getArrayIndex((long)key); 
    return -1;
  }
  
  public static int getArrayIndex(String key) {
    return (int)fromString(key);
  }
  
  public static boolean isValidArrayIndex(int index) {
    return (index != -1);
  }
  
  public static long toLongIndex(int index) {
    return JSType.toUint32(index);
  }
  
  public static String toKey(int index) {
    return Long.toString(JSType.toUint32(index));
  }
}
