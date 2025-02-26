package com.fasterxml.jackson.core.io.doubleparser;

import java.nio.charset.StandardCharsets;

final class JavaDoubleBitsFromByteArray extends AbstractJavaFloatingPointBitsFromByteArray {
  long nan() {
    return Double.doubleToRawLongBits(Double.NaN);
  }
  
  long negativeInfinity() {
    return Double.doubleToRawLongBits(Double.NEGATIVE_INFINITY);
  }
  
  long positiveInfinity() {
    return Double.doubleToRawLongBits(Double.POSITIVE_INFINITY);
  }
  
  long valueOfFloatLiteral(byte[] str, int startIndex, int endIndex, boolean isNegative, long significand, int exponent, boolean isSignificandTruncated, int exponentOfTruncatedSignificand) {
    double d = FastDoubleMath.tryDecFloatToDoubleTruncated(isNegative, significand, exponent, isSignificandTruncated, exponentOfTruncatedSignificand);
    return Double.doubleToRawLongBits(Double.isNaN(d) ? 
        
        Double.parseDouble(new String(str, startIndex, endIndex - startIndex, StandardCharsets.ISO_8859_1)) : 
        
        d);
  }
  
  long valueOfHexLiteral(byte[] str, int startIndex, int endIndex, boolean isNegative, long significand, int exponent, boolean isSignificandTruncated, int exponentOfTruncatedSignificand) {
    double d = FastDoubleMath.tryHexFloatToDoubleTruncated(isNegative, significand, exponent, isSignificandTruncated, exponentOfTruncatedSignificand);
    return Double.doubleToRawLongBits(Double.isNaN(d) ? Double.parseDouble(new String(str, startIndex, endIndex - startIndex, StandardCharsets.ISO_8859_1)) : d);
  }
}
