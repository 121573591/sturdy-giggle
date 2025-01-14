package cn.hutool.core.getter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public interface OptNullBasicTypeGetter<K> extends BasicTypeGetter<K>, OptBasicTypeGetter<K> {
  default Object getObj(K key) {
    return getObj(key, null);
  }
  
  default String getStr(K key) {
    return getStr(key, null);
  }
  
  default Integer getInt(K key) {
    return getInt(key, null);
  }
  
  default Short getShort(K key) {
    return getShort(key, null);
  }
  
  default Boolean getBool(K key) {
    return getBool(key, null);
  }
  
  default Long getLong(K key) {
    return getLong(key, null);
  }
  
  default Character getChar(K key) {
    return getChar(key, null);
  }
  
  default Float getFloat(K key) {
    return getFloat(key, null);
  }
  
  default Double getDouble(K key) {
    return getDouble(key, null);
  }
  
  default Byte getByte(K key) {
    return getByte(key, null);
  }
  
  default BigDecimal getBigDecimal(K key) {
    return getBigDecimal(key, null);
  }
  
  default BigInteger getBigInteger(K key) {
    return getBigInteger(key, null);
  }
  
  default <E extends Enum<E>> E getEnum(Class<E> clazz, K key) {
    return (E)getEnum(clazz, key, null);
  }
  
  default Date getDate(K key) {
    return getDate(key, null);
  }
}
