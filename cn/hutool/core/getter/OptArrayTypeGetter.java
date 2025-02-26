package cn.hutool.core.getter;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface OptArrayTypeGetter {
  Object[] getObjs(String paramString, Object[] paramArrayOfObject);
  
  String[] getStrs(String paramString, String[] paramArrayOfString);
  
  Integer[] getInts(String paramString, Integer[] paramArrayOfInteger);
  
  Short[] getShorts(String paramString, Short[] paramArrayOfShort);
  
  Boolean[] getBools(String paramString, Boolean[] paramArrayOfBoolean);
  
  Long[] getLongs(String paramString, Long[] paramArrayOfLong);
  
  Character[] getChars(String paramString, Character[] paramArrayOfCharacter);
  
  Double[] getDoubles(String paramString, Double[] paramArrayOfDouble);
  
  Byte[] getBytes(String paramString, Byte[] paramArrayOfByte);
  
  BigInteger[] getBigIntegers(String paramString, BigInteger[] paramArrayOfBigInteger);
  
  BigDecimal[] getBigDecimals(String paramString, BigDecimal[] paramArrayOfBigDecimal);
}
