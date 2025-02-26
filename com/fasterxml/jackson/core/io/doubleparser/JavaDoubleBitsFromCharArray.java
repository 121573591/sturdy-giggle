package com.fasterxml.jackson.core.io.doubleparser;

final class JavaDoubleBitsFromCharArray extends AbstractJavaFloatingPointBitsFromCharArray {
  long nan() {
    return Double.doubleToRawLongBits(Double.NaN);
  }
  
  long negativeInfinity() {
    return Double.doubleToRawLongBits(Double.NEGATIVE_INFINITY);
  }
  
  long positiveInfinity() {
    return Double.doubleToRawLongBits(Double.POSITIVE_INFINITY);
  }
  
  long valueOfFloatLiteral(char[] str, int startIndex, int endIndex, boolean isNegative, long significand, int exponent, boolean isSignificandTruncated, int exponentOfTruncatedSignificand) {
    double d = FastDoubleMath.tryDecFloatToDoubleTruncated(isNegative, significand, exponent, isSignificandTruncated, exponentOfTruncatedSignificand);
    return Double.doubleToRawLongBits(Double.isNaN(d) ? Double.parseDouble(new String(str, startIndex, endIndex - startIndex)) : d);
  }
  
  long valueOfHexLiteral(char[] str, int startIndex, int endIndex, boolean isNegative, long significand, int exponent, boolean isSignificandTruncated, int exponentOfTruncatedSignificand) {
    double d = FastDoubleMath.tryHexFloatToDoubleTruncated(isNegative, significand, exponent, isSignificandTruncated, exponentOfTruncatedSignificand);
    return Double.doubleToRawLongBits(Double.isNaN(d) ? Double.parseDouble(new String(str, startIndex, endIndex - startIndex)) : d);
  }
}
