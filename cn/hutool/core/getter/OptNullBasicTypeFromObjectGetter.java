package cn.hutool.core.getter;

import cn.hutool.core.convert.Convert;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public interface OptNullBasicTypeFromObjectGetter<K> extends OptNullBasicTypeGetter<K> {
  default String getStr(K key, String defaultValue) {
    Object obj = getObj(key);
    if (null == obj)
      return defaultValue; 
    return Convert.toStr(obj, defaultValue);
  }
  
  default Integer getInt(K key, Integer defaultValue) {
    Object obj = getObj(key);
    if (null == obj)
      return defaultValue; 
    return Convert.toInt(obj, defaultValue);
  }
  
  default Short getShort(K key, Short defaultValue) {
    Object obj = getObj(key);
    if (null == obj)
      return defaultValue; 
    return Convert.toShort(obj, defaultValue);
  }
  
  default Boolean getBool(K key, Boolean defaultValue) {
    Object obj = getObj(key);
    if (null == obj)
      return defaultValue; 
    return Convert.toBool(obj, defaultValue);
  }
  
  default Long getLong(K key, Long defaultValue) {
    Object obj = getObj(key);
    if (null == obj)
      return defaultValue; 
    return Convert.toLong(obj, defaultValue);
  }
  
  default Character getChar(K key, Character defaultValue) {
    Object obj = getObj(key);
    if (null == obj)
      return defaultValue; 
    return Convert.toChar(obj, defaultValue);
  }
  
  default Float getFloat(K key, Float defaultValue) {
    Object obj = getObj(key);
    if (null == obj)
      return defaultValue; 
    return Convert.toFloat(obj, defaultValue);
  }
  
  default Double getDouble(K key, Double defaultValue) {
    Object obj = getObj(key);
    if (null == obj)
      return defaultValue; 
    return Convert.toDouble(obj, defaultValue);
  }
  
  default Byte getByte(K key, Byte defaultValue) {
    Object obj = getObj(key);
    if (null == obj)
      return defaultValue; 
    return Convert.toByte(obj, defaultValue);
  }
  
  default BigDecimal getBigDecimal(K key, BigDecimal defaultValue) {
    Object obj = getObj(key);
    if (null == obj)
      return defaultValue; 
    return Convert.toBigDecimal(obj, defaultValue);
  }
  
  default BigInteger getBigInteger(K key, BigInteger defaultValue) {
    Object obj = getObj(key);
    if (null == obj)
      return defaultValue; 
    return Convert.toBigInteger(obj, defaultValue);
  }
  
  default <E extends Enum<E>> E getEnum(Class<E> clazz, K key, E defaultValue) {
    Object obj = getObj(key);
    if (null == obj)
      return defaultValue; 
    return (E)Convert.toEnum(clazz, obj, (Enum)defaultValue);
  }
  
  default Date getDate(K key, Date defaultValue) {
    Object obj = getObj(key);
    if (null == obj)
      return defaultValue; 
    return Convert.toDate(obj, defaultValue);
  }
}
