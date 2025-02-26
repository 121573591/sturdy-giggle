package com.fasterxml.jackson.core.io.doubleparser;

final class JavaDoubleBitsFromCharSequence extends AbstractJavaFloatingPointBitsFromCharSequence {
  long nan() {
    return Double.doubleToRawLongBits(Double.NaN);
  }
  
  long negativeInfinity() {
    return Double.doubleToRawLongBits(Double.NEGATIVE_INFINITY);
  }
  
  long positiveInfinity() {
    return Double.doubleToRawLongBits(Double.POSITIVE_INFINITY);
  }
  
  long valueOfFloatLiteral(CharSequence str, int startIndex, int endIndex, boolean isNegative, long significand, int exponent, boolean isSignificandTruncated, int exponentOfTruncatedSignificand) {
    double d = FastDoubleMath.tryDecFloatToDoubleTruncated(isNegative, significand, exponent, isSignificandTruncated, exponentOfTruncatedSignificand);
    return Double.doubleToRawLongBits(Double.isNaN(d) ? 
        Double.parseDouble(str.subSequence(startIndex, endIndex).toString()) : 
        d);
  }
  
  long valueOfHexLiteral(CharSequence str, int startIndex, int endIndex, boolean isNegative, long significand, int exponent, boolean isSignificandTruncated, int exponentOfTruncatedSignificand) {
    double d = FastDoubleMath.tryHexFloatToDoubleTruncated(isNegative, significand, exponent, isSignificandTruncated, exponentOfTruncatedSignificand);
    return Double.doubleToRawLongBits(Double.isNaN(d) ? 
        Double.parseDouble(str.subSequence(startIndex, endIndex).toString()) : 
        d);
  }
}
