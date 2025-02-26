package cn.hutool.core.getter;

import cn.hutool.core.convert.Convert;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public interface OptNullBasicTypeFromStringGetter<K> extends OptNullBasicTypeGetter<K> {
  default Object getObj(K key, Object defaultValue) {
    return getStr(key, (null == defaultValue) ? null : defaultValue.toString());
  }
  
  default Integer getInt(K key, Integer defaultValue) {
    return Convert.toInt(getStr(key), defaultValue);
  }
  
  default Short getShort(K key, Short defaultValue) {
    return Convert.toShort(getStr(key), defaultValue);
  }
  
  default Boolean getBool(K key, Boolean defaultValue) {
    return Convert.toBool(getStr(key), defaultValue);
  }
  
  default Long getLong(K key, Long defaultValue) {
    return Convert.toLong(getStr(key), defaultValue);
  }
  
  default Character getChar(K key, Character defaultValue) {
    return Convert.toChar(getStr(key), defaultValue);
  }
  
  default Float getFloat(K key, Float defaultValue) {
    return Convert.toFloat(getStr(key), defaultValue);
  }
  
  default Double getDouble(K key, Double defaultValue) {
    return Convert.toDouble(getStr(key), defaultValue);
  }
  
  default Byte getByte(K key, Byte defaultValue) {
    return Convert.toByte(getStr(key), defaultValue);
  }
  
  default BigDecimal getBigDecimal(K key, BigDecimal defaultValue) {
    return Convert.toBigDecimal(getStr(key), defaultValue);
  }
  
  default BigInteger getBigInteger(K key, BigInteger defaultValue) {
    return Convert.toBigInteger(getStr(key), defaultValue);
  }
  
  default <E extends Enum<E>> E getEnum(Class<E> clazz, K key, E defaultValue) {
    return (E)Convert.toEnum(clazz, getStr(key), (Enum)defaultValue);
  }
  
  default Date getDate(K key, Date defaultValue) {
    return Convert.toDate(getStr(key), defaultValue);
  }
}
