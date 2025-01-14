package cn.hutool.core.getter;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ArrayTypeGetter {
  String[] getObjs(String paramString);
  
  String[] getStrs(String paramString);
  
  Integer[] getInts(String paramString);
  
  Short[] getShorts(String paramString);
  
  Boolean[] getBools(String paramString);
  
  Long[] getLongs(String paramString);
  
  Character[] getChars(String paramString);
  
  Double[] getDoubles(String paramString);
  
  Byte[] getBytes(String paramString);
  
  BigInteger[] getBigIntegers(String paramString);
  
  BigDecimal[] getBigDecimals(String paramString);
}
