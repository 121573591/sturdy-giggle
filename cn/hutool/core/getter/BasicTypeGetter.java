package cn.hutool.core.getter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public interface BasicTypeGetter<K> {
  Object getObj(K paramK);
  
  String getStr(K paramK);
  
  Integer getInt(K paramK);
  
  Short getShort(K paramK);
  
  Boolean getBool(K paramK);
  
  Long getLong(K paramK);
  
  Character getChar(K paramK);
  
  Float getFloat(K paramK);
  
  Double getDouble(K paramK);
  
  Byte getByte(K paramK);
  
  BigDecimal getBigDecimal(K paramK);
  
  BigInteger getBigInteger(K paramK);
  
  <E extends Enum<E>> E getEnum(Class<E> paramClass, K paramK);
  
  Date getDate(K paramK);
}
