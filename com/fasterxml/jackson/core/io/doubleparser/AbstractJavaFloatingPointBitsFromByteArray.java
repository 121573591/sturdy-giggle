package com.fasterxml.jackson.core.io.doubleparser;

abstract class AbstractJavaFloatingPointBitsFromByteArray extends AbstractFloatValueParser {
  private static int skipWhitespace(byte[] str, int index, int endIndex) {
    while (index < endIndex && (str[index] & 0xFF) <= 32)
      index++; 
    return index;
  }
  
  abstract long nan();
  
  abstract long negativeInfinity();
  
  private long parseDecFloatLiteral(byte[] str, int index, int startIndex, int endIndex, boolean isNegative, boolean hasLeadingZero) {
    int i, digitCount, exponent;
    boolean isSignificandTruncated;
    int exponentOfTruncatedSignificand;
    long significand = 0L;
    int significandStartIndex = index;
    int virtualIndexOfPoint = -1;
    boolean illegal = false;
    byte ch = 0;
    for (; index < endIndex; index++) {
      ch = str[index];
      if (FastDoubleSwar.isDigit(ch)) {
        significand = 10L * significand + ch - 48L;
      } else if (ch == 46) {
        i = illegal | ((virtualIndexOfPoint >= 0) ? 1 : 0);
        virtualIndexOfPoint = index;
        for (; index < endIndex - 4; index += 4) {
          int digits = FastDoubleSwar.tryToParseFourDigits(str, index + 1);
          if (digits < 0)
            break; 
          significand = 10000L * significand + digits;
        } 
      } else {
        break;
      } 
    } 
    int significandEndIndex = index;
    if (virtualIndexOfPoint < 0) {
      digitCount = index - significandStartIndex;
      virtualIndexOfPoint = index;
      exponent = 0;
    } else {
      digitCount = index - significandStartIndex - 1;
      exponent = virtualIndexOfPoint - index + 1;
    } 
    int expNumber = 0;
    if ((ch | 0x20) == 101) {
      ch = charAt(str, ++index, endIndex);
      boolean isExponentNegative = (ch == 45);
      if (isExponentNegative || ch == 43)
        ch = charAt(str, ++index, endIndex); 
      i |= !FastDoubleSwar.isDigit(ch) ? 1 : 0;
      while (true) {
        if (expNumber < 1024)
          expNumber = 10 * expNumber + ch - 48; 
        ch = charAt(str, ++index, endIndex);
        if (!FastDoubleSwar.isDigit(ch)) {
          if (isExponentNegative)
            expNumber = -expNumber; 
          exponent += expNumber;
          break;
        } 
      } 
    } 
    if ((ch | 0x22) == 102)
      index++; 
    index = skipWhitespace(str, index, endIndex);
    if (i != 0 || index < endIndex || (!hasLeadingZero && digitCount == 0))
      throw new NumberFormatException("illegal syntax"); 
    int skipCountInTruncatedDigits = 0;
    if (digitCount > 19) {
      significand = 0L;
      for (index = significandStartIndex; index < significandEndIndex; index++) {
        ch = str[index];
        if (ch == 46) {
          skipCountInTruncatedDigits++;
        } else if (Long.compareUnsigned(significand, 1000000000000000000L) < 0) {
          significand = 10L * significand + ch - 48L;
        } else {
          break;
        } 
      } 
      isSignificandTruncated = (index < significandEndIndex);
      exponentOfTruncatedSignificand = virtualIndexOfPoint - index + skipCountInTruncatedDigits + expNumber;
    } else {
      isSignificandTruncated = false;
      exponentOfTruncatedSignificand = 0;
    } 
    return valueOfFloatLiteral(str, startIndex, endIndex, isNegative, significand, exponent, isSignificandTruncated, exponentOfTruncatedSignificand);
  }
  
  public long parseFloatingPointLiteral(byte[] str, int offset, int length) {
    int endIndex = checkBounds(str.length, offset, length);
    int index = skipWhitespace(str, offset, endIndex);
    if (index == endIndex)
      throw new NumberFormatException("illegal syntax"); 
    byte ch = str[index];
    boolean isNegative = (ch == 45);
    if (isNegative || ch == 43) {
      ch = charAt(str, ++index, endIndex);
      if (ch == 0)
        throw new NumberFormatException("illegal syntax"); 
    } 
    if (ch >= 73)
      return parseNaNOrInfinity(str, index, endIndex, isNegative); 
    boolean hasLeadingZero = (ch == 48);
    if (hasLeadingZero) {
      ch = charAt(str, ++index, endIndex);
      if ((ch | 0x20) == 120)
        return parseHexFloatingPointLiteral(str, index + 1, offset, endIndex, isNegative); 
    } 
    return parseDecFloatLiteral(str, index, offset, endIndex, isNegative, hasLeadingZero);
  }
  
  private long parseHexFloatingPointLiteral(byte[] str, int index, int startIndex, int endIndex, boolean isNegative) {
    int digitCount, i;
    boolean isSignificandTruncated;
    long significand = 0L;
    int exponent = 0;
    int significandStartIndex = index;
    int virtualIndexOfPoint = -1;
    boolean illegal = false;
    byte ch = 0;
    for (; index < endIndex; index++) {
      ch = str[index];
      int hexValue = lookupHex(ch);
      if (hexValue >= 0) {
        significand = significand << 4L | hexValue;
      } else if (hexValue == -4) {
        i = illegal | ((virtualIndexOfPoint >= 0) ? 1 : 0);
        virtualIndexOfPoint = index;
      } else {
        break;
      } 
    } 
    int significandEndIndex = index;
    if (virtualIndexOfPoint < 0) {
      digitCount = significandEndIndex - significandStartIndex;
      virtualIndexOfPoint = significandEndIndex;
    } else {
      digitCount = significandEndIndex - significandStartIndex - 1;
      exponent = Math.min(virtualIndexOfPoint - index + 1, 1024) * 4;
    } 
    int expNumber = 0;
    boolean hasExponent = ((ch | 0x20) == 112);
    if (hasExponent) {
      ch = charAt(str, ++index, endIndex);
      boolean isExponentNegative = (ch == 45);
      if (isExponentNegative || ch == 43)
        ch = charAt(str, ++index, endIndex); 
      i |= !FastDoubleSwar.isDigit(ch) ? 1 : 0;
      while (true) {
        if (expNumber < 1024)
          expNumber = 10 * expNumber + ch - 48; 
        ch = charAt(str, ++index, endIndex);
        if (!FastDoubleSwar.isDigit(ch)) {
          if (isExponentNegative)
            expNumber = -expNumber; 
          exponent += expNumber;
          break;
        } 
      } 
    } 
    if ((ch | 0x22) == 102)
      index++; 
    index = skipWhitespace(str, index, endIndex);
    if (i != 0 || index < endIndex || digitCount == 0 || !hasExponent)
      throw new NumberFormatException("illegal syntax"); 
    int skipCountInTruncatedDigits = 0;
    if (digitCount > 16) {
      significand = 0L;
      for (index = significandStartIndex; index < significandEndIndex; index++) {
        ch = str[index];
        int hexValue = lookupHex(ch);
        if (hexValue >= 0) {
          if (Long.compareUnsigned(significand, 1000000000000000000L) < 0) {
            significand = significand << 4L | hexValue;
          } else {
            break;
          } 
        } else {
          skipCountInTruncatedDigits++;
        } 
      } 
      isSignificandTruncated = (index < significandEndIndex);
    } else {
      isSignificandTruncated = false;
    } 
    return valueOfHexLiteral(str, startIndex, endIndex, isNegative, significand, exponent, isSignificandTruncated, (virtualIndexOfPoint - index + skipCountInTruncatedDigits) * 4 + expNumber);
  }
  
  private long parseNaNOrInfinity(byte[] str, int index, int endIndex, boolean isNegative) {
    if (str[index] == 78) {
      if (index + 2 < endIndex && str[index + 1] == 97 && str[index + 2] == 78) {
        index = skipWhitespace(str, index + 3, endIndex);
        if (index == endIndex)
          return nan(); 
      } 
    } else if (index + 7 < endIndex && 
      FastDoubleSwar.readLongLE(str, index) == 8751735898823355977L) {
      index = skipWhitespace(str, index + 8, endIndex);
      if (index == endIndex)
        return isNegative ? negativeInfinity() : positiveInfinity(); 
    } 
    throw new NumberFormatException("illegal syntax");
  }
  
  abstract long positiveInfinity();
  
  abstract long valueOfFloatLiteral(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean1, long paramLong, int paramInt3, boolean paramBoolean2, int paramInt4);
  
  abstract long valueOfHexLiteral(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean1, long paramLong, int paramInt3, boolean paramBoolean2, int paramInt4);
}
