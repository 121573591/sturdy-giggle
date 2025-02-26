package com.fasterxml.jackson.core.io.doubleparser;

final class JavaFloatBitsFromCharArray extends AbstractJavaFloatingPointBitsFromCharArray {
  long nan() {
    return Float.floatToRawIntBits(Float.NaN);
  }
  
  long negativeInfinity() {
    return Float.floatToRawIntBits(Float.NEGATIVE_INFINITY);
  }
  
  long positiveInfinity() {
    return Float.floatToRawIntBits(Float.POSITIVE_INFINITY);
  }
  
  long valueOfFloatLiteral(char[] str, int startIndex, int endIndex, boolean isNegative, long significand, int exponent, boolean isSignificandTruncated, int exponentOfTruncatedSignificand) {
    float result = FastFloatMath.tryDecFloatToFloatTruncated(isNegative, significand, exponent, isSignificandTruncated, exponentOfTruncatedSignificand);
    return Float.isNaN(result) ? Float.floatToRawIntBits(Float.parseFloat(new String(str, startIndex, endIndex - startIndex))) : Float.floatToRawIntBits(result);
  }
  
  long valueOfHexLiteral(char[] str, int startIndex, int endIndex, boolean isNegative, long significand, int exponent, boolean isSignificandTruncated, int exponentOfTruncatedSignificand) {
    float d = FastFloatMath.tryHexFloatToFloatTruncated(isNegative, significand, exponent, isSignificandTruncated, exponentOfTruncatedSignificand);
    return Float.floatToRawIntBits(Float.isNaN(d) ? Float.parseFloat(new String(str, startIndex, endIndex - startIndex)) : d);
  }
}
