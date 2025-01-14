package org.openjdk.nashorn.internal.runtime.doubleconv;

public final class DoubleConversion {
  private static final int BUFFER_LENGTH = 30;
  
  public static String toShortestString(double value) {
    DtoaBuffer buffer = new DtoaBuffer(17);
    double absValue = Math.abs(value);
    if (value < 0.0D)
      buffer.isNegative = true; 
    if (!fastDtoaShortest(absValue, buffer)) {
      buffer.reset();
      bignumDtoa(absValue, DtoaMode.SHORTEST, 0, buffer);
    } 
    return buffer.format(DtoaMode.SHORTEST, 0);
  }
  
  public static String toFixed(double value, int requestedDigits) {
    DtoaBuffer buffer = new DtoaBuffer(30);
    double absValue = Math.abs(value);
    if (value < 0.0D)
      buffer.isNegative = true; 
    if (value == 0.0D) {
      buffer.append('0');
      buffer.decimalPoint = 1;
    } else if (!fixedDtoa(absValue, requestedDigits, buffer)) {
      buffer.reset();
      bignumDtoa(absValue, DtoaMode.FIXED, requestedDigits, buffer);
    } 
    return buffer.format(DtoaMode.FIXED, requestedDigits);
  }
  
  public static String toPrecision(double value, int precision) {
    DtoaBuffer buffer = new DtoaBuffer(precision);
    double absValue = Math.abs(value);
    if (value < 0.0D)
      buffer.isNegative = true; 
    if (value == 0.0D) {
      for (int i = 0; i < precision; i++)
        buffer.append('0'); 
      buffer.decimalPoint = 1;
    } else if (!fastDtoaCounted(absValue, precision, buffer)) {
      buffer.reset();
      bignumDtoa(absValue, DtoaMode.PRECISION, precision, buffer);
    } 
    return buffer.format(DtoaMode.PRECISION, 0);
  }
  
  public static void bignumDtoa(double v, DtoaMode mode, int digits, DtoaBuffer buffer) {
    assert v > 0.0D;
    assert !Double.isNaN(v);
    assert !Double.isInfinite(v);
    BignumDtoa.bignumDtoa(v, mode, digits, buffer);
  }
  
  public static boolean fastDtoaShortest(double v, DtoaBuffer buffer) {
    assert v > 0.0D;
    assert !Double.isNaN(v);
    assert !Double.isInfinite(v);
    return FastDtoa.grisu3(v, buffer);
  }
  
  public static boolean fastDtoaCounted(double v, int precision, DtoaBuffer buffer) {
    assert v > 0.0D;
    assert !Double.isNaN(v);
    assert !Double.isInfinite(v);
    return FastDtoa.grisu3Counted(v, precision, buffer);
  }
  
  public static boolean fixedDtoa(double v, int digits, DtoaBuffer buffer) {
    assert v > 0.0D;
    assert !Double.isNaN(v);
    assert !Double.isInfinite(v);
    return FixedDtoa.fastFixedDtoa(v, digits, buffer);
  }
}
