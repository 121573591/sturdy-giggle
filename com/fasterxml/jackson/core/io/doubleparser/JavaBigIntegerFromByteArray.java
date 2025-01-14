package com.fasterxml.jackson.core.io.doubleparser;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Map;

class JavaBigIntegerFromByteArray extends AbstractBigIntegerParser {
  public BigInteger parseBigIntegerString(byte[] str, int offset, int length, int radix) throws NumberFormatException {
    try {
      int endIndex = AbstractNumberParser.checkBounds(str.length, offset, length);
      int index = offset;
      byte ch = str[index];
      boolean isNegative = (ch == 45);
      if (isNegative || ch == 43) {
        ch = charAt(str, ++index, endIndex);
        if (ch == 0)
          throw new NumberFormatException("illegal syntax"); 
      } 
      switch (radix) {
        case 10:
          return parseDecDigits(str, index, endIndex, isNegative);
        case 16:
          return parseHexDigits(str, index, endIndex, isNegative);
      } 
      return new BigInteger(new String(str, offset, length, StandardCharsets.ISO_8859_1), radix);
    } catch (ArithmeticException e) {
      NumberFormatException nfe = new NumberFormatException("value exceeds limits");
      nfe.initCause(e);
      throw nfe;
    } 
  }
  
  private BigInteger parseDecDigits(byte[] str, int from, int to, boolean isNegative) {
    int i, numDigits = to - from;
    if (hasManyDigits(numDigits))
      return parseManyDecDigits(str, from, to, isNegative); 
    int preroll = from + (numDigits & 0x7);
    long significand = FastDoubleSwar.tryToParseUpTo7Digits(str, from, preroll);
    boolean success = (significand >= 0L);
    for (from = preroll; from < to; from += 8) {
      int addend = FastDoubleSwar.tryToParseEightDigitsUtf8(str, from);
      i = success & ((addend >= 0) ? 1 : 0);
      significand = significand * 100000000L + addend;
    } 
    if (i == 0)
      throw new NumberFormatException("illegal syntax"); 
    return BigInteger.valueOf(isNegative ? -significand : significand);
  }
  
  private BigInteger parseHexDigits(byte[] str, int from, int to, boolean isNegative) {
    int i;
    from = skipZeroes(str, from, to);
    int numDigits = to - from;
    if (numDigits <= 0)
      return BigInteger.ZERO; 
    checkHexBigIntegerBounds(numDigits);
    byte[] bytes = new byte[(numDigits + 1 >> 1) + 1];
    int index = 1;
    boolean illegalDigits = false;
    if ((numDigits & 0x1) != 0) {
      byte chLow = str[from++];
      int valueLow = lookupHex(chLow);
      bytes[index++] = (byte)valueLow;
      illegalDigits = (valueLow < 0);
    } 
    int prerollLimit = from + (to - from & 0x7);
    for (; from < prerollLimit; from += 2) {
      byte chHigh = str[from];
      byte chLow = str[from + 1];
      int valueHigh = lookupHex(chHigh);
      int valueLow = lookupHex(chLow);
      bytes[index++] = (byte)(valueHigh << 4 | valueLow);
      i = illegalDigits | ((valueHigh < 0 || valueLow < 0) ? 1 : 0);
    } 
    for (; from < to; from += 8, index += 4) {
      long value = FastDoubleSwar.tryToParseEightHexDigits(str, from);
      FastDoubleSwar.writeIntBE(bytes, index, (int)value);
      i |= (value < 0L) ? 1 : 0;
    } 
    if (i != 0)
      throw new NumberFormatException("illegal syntax"); 
    BigInteger result = new BigInteger(bytes);
    return isNegative ? result.negate() : result;
  }
  
  private BigInteger parseManyDecDigits(byte[] str, int from, int to, boolean isNegative) {
    from = skipZeroes(str, from, to);
    int numDigits = to - from;
    checkDecBigIntegerBounds(numDigits);
    Map<Integer, BigInteger> powersOfTen = FastIntegerMath.fillPowersOf10Floor16(from, to);
    BigInteger result = ParseDigitsTaskByteArray.parseDigitsRecursive(str, from, to, powersOfTen, 400);
    return isNegative ? result.negate() : result;
  }
  
  private int skipZeroes(byte[] str, int from, int to) {
    while (from < to - 8 && FastDoubleSwar.isEightZeroes(str, from))
      from += 8; 
    while (from < to && str[from] == 48)
      from++; 
    return from;
  }
}
