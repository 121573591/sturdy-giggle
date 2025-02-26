package com.fasterxml.jackson.core.io.doubleparser;

abstract class AbstractJavaFloatingPointBitsFromCharSequence extends AbstractFloatValueParser {
  private static int skipWhitespace(CharSequence str, int index, int endIndex) {
    while (index < endIndex && str.charAt(index) <= ' ')
      index++; 
    return index;
  }
  
  abstract long nan();
  
  abstract long negativeInfinity();
  
  private long parseDecFloatLiteral(CharSequence str, int index, int startIndex, int endIndex, boolean isNegative, boolean hasLeadingZero) {
    int i, digitCount, exponent;
    boolean isSignificandTruncated;
    int exponentOfTruncatedSignificand;
    long significand = 0L;
    int significandStartIndex = index;
    int virtualIndexOfPoint = -1;
    boolean illegal = false;
    char ch = Character.MIN_VALUE;
    for (; index < endIndex; index++) {
      ch = str.charAt(index);
      if (FastDoubleSwar.isDigit(ch)) {
        significand = 10L * significand + ch - 48L;
      } else if (ch == '.') {
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
      exponent = 0;
    } else {
      digitCount = significandEndIndex - significandStartIndex - 1;
      exponent = virtualIndexOfPoint - significandEndIndex + 1;
    } 
    int expNumber = 0;
    if ((ch | 0x20) == 101) {
      ch = charAt(str, ++index, endIndex);
      boolean isExponentNegative = (ch == '-');
      if (isExponentNegative || ch == '+')
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
        ch = str.charAt(index);
        if (ch == '.') {
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
  
  public final long parseFloatingPointLiteral(CharSequence str, int offset, int length) {
    int endIndex = checkBounds(str.length(), offset, length);
    int index = skipWhitespace(str, offset, endIndex);
    if (index == endIndex)
      throw new NumberFormatException("illegal syntax"); 
    char ch = str.charAt(index);
    boolean isNegative = (ch == '-');
    if (isNegative || ch == '+') {
      ch = charAt(str, ++index, endIndex);
      if (ch == '\000')
        throw new NumberFormatException("illegal syntax"); 
    } 
    if (ch >= 'I')
      return parseNaNOrInfinity(str, index, endIndex, isNegative); 
    boolean hasLeadingZero = (ch == '0');
    if (hasLeadingZero) {
      ch = charAt(str, ++index, endIndex);
      if ((ch | 0x20) == 120)
        return parseHexFloatLiteral(str, index + 1, offset, endIndex, isNegative); 
    } 
    return parseDecFloatLiteral(str, index, offset, endIndex, isNegative, hasLeadingZero);
  }
  
  private long parseHexFloatLiteral(CharSequence str, int index, int startIndex, int endIndex, boolean isNegative) {
    int digitCount, i;
    boolean isSignificandTruncated;
    long significand = 0L;
    int exponent = 0;
    int significandStartIndex = index;
    int virtualIndexOfPoint = -1;
    boolean illegal = false;
    char ch = Character.MIN_VALUE;
    for (; index < endIndex; index++) {
      ch = str.charAt(index);
      int hexValue = lookupHex(ch);
      if (hexValue >= 0) {
        significand = significand << 4L | hexValue;
      } else if (hexValue == -4) {
        i = illegal | ((virtualIndexOfPoint >= 0) ? 1 : 0);
        virtualIndexOfPoint = index;
        while (index < endIndex - 8) {
          long parsed = FastDoubleSwar.tryToParseEightHexDigits(str, index + 1);
          if (parsed >= 0L) {
            significand = (significand << 32L) + parsed;
            index += 8;
          } 
        } 
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
      boolean isExponentNegative = (ch == '-');
      if (isExponentNegative || ch == '+')
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
        ch = str.charAt(index);
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
  
  private long parseNaNOrInfinity(CharSequence str, int index, int endIndex, boolean isNegative) {
    if (str.charAt(index) == 'N') {
      if (index + 2 < endIndex && str
        
        .charAt(index + 1) == 'a' && str
        .charAt(index + 2) == 'N') {
        index = skipWhitespace(str, index + 3, endIndex);
        if (index == endIndex)
          return nan(); 
      } 
    } else if (index + 7 < endIndex && str
      .charAt(index) == 'I' && str
      .charAt(index + 1) == 'n' && str
      .charAt(index + 2) == 'f' && str
      .charAt(index + 3) == 'i' && str
      .charAt(index + 4) == 'n' && str
      .charAt(index + 5) == 'i' && str
      .charAt(index + 6) == 't' && str
      .charAt(index + 7) == 'y') {
      index = skipWhitespace(str, index + 8, endIndex);
      if (index == endIndex)
        return isNegative ? negativeInfinity() : positiveInfinity(); 
    } 
    throw new NumberFormatException("illegal syntax");
  }
  
  abstract long positiveInfinity();
  
  abstract long valueOfFloatLiteral(CharSequence paramCharSequence, int paramInt1, int paramInt2, boolean paramBoolean1, long paramLong, int paramInt3, boolean paramBoolean2, int paramInt4);
  
  abstract long valueOfHexLiteral(CharSequence paramCharSequence, int paramInt1, int paramInt2, boolean paramBoolean1, long paramLong, int paramInt3, boolean paramBoolean2, int paramInt4);
}
