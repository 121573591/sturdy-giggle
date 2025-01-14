package cn.hutool.core.convert;

import cn.hutool.core.map.SafeConcurrentHashMap;
import java.util.Map;

public enum BasicType {
  BYTE, SHORT, INT, INTEGER, LONG, DOUBLE, FLOAT, BOOLEAN, CHAR, CHARACTER, STRING;
  
  public static final Map<Class<?>, Class<?>> PRIMITIVE_WRAPPER_MAP;
  
  public static final Map<Class<?>, Class<?>> WRAPPER_PRIMITIVE_MAP;
  
  static {
    WRAPPER_PRIMITIVE_MAP = (Map<Class<?>, Class<?>>)new SafeConcurrentHashMap(8);
    PRIMITIVE_WRAPPER_MAP = (Map<Class<?>, Class<?>>)new SafeConcurrentHashMap(8);
    WRAPPER_PRIMITIVE_MAP.put(Boolean.class, boolean.class);
    WRAPPER_PRIMITIVE_MAP.put(Byte.class, byte.class);
    WRAPPER_PRIMITIVE_MAP.put(Character.class, char.class);
    WRAPPER_PRIMITIVE_MAP.put(Double.class, double.class);
    WRAPPER_PRIMITIVE_MAP.put(Float.class, float.class);
    WRAPPER_PRIMITIVE_MAP.put(Integer.class, int.class);
    WRAPPER_PRIMITIVE_MAP.put(Long.class, long.class);
    WRAPPER_PRIMITIVE_MAP.put(Short.class, short.class);
    for (Map.Entry<Class<?>, Class<?>> entry : WRAPPER_PRIMITIVE_MAP.entrySet())
      PRIMITIVE_WRAPPER_MAP.put(entry.getValue(), entry.getKey()); 
  }
  
  public static Class<?> wrap(Class<?> clazz) {
    if (null == clazz || false == clazz.isPrimitive())
      return clazz; 
    Class<?> result = PRIMITIVE_WRAPPER_MAP.get(clazz);
    return (null == result) ? clazz : result;
  }
  
  public static Class<?> unWrap(Class<?> clazz) {
    if (null == clazz || clazz.isPrimitive())
      return clazz; 
    Class<?> result = WRAPPER_PRIMITIVE_MAP.get(clazz);
    return (null == result) ? clazz : result;
  }
}
