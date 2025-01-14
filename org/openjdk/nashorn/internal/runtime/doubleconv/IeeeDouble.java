package org.openjdk.nashorn.internal.runtime.doubleconv;

class IeeeDouble {
  static final long kSignMask = -9223372036854775808L;
  
  static final long kExponentMask = 9218868437227405312L;
  
  static final long kSignificandMask = 4503599627370495L;
  
  static final long kHiddenBit = 4503599627370496L;
  
  static final int kPhysicalSignificandSize = 52;
  
  static final int kSignificandSize = 53;
  
  private static final int kExponentBias = 1075;
  
  private static final int kDenormalExponent = -1074;
  
  private static final int kMaxExponent = 972;
  
  private static final long kInfinity = 9218868437227405312L;
  
  private static final long kNaN = 9221120237041090560L;
  
  static long doubleToLong(double d) {
    return Double.doubleToRawLongBits(d);
  }
  
  static double longToDouble(long d64) {
    return Double.longBitsToDouble(d64);
  }
  
  static DiyFp asDiyFp(long d64) {
    assert !isSpecial(d64);
    return new DiyFp(significand(d64), exponent(d64));
  }
  
  static DiyFp asNormalizedDiyFp(long d64) {
    assert value(d64) > 0.0D;
    long f = significand(d64);
    int e = exponent(d64);
    while ((f & 0x10000000000000L) == 0L) {
      f <<= 1L;
      e--;
    } 
    f <<= 11L;
    e -= 11;
    return new DiyFp(f, e);
  }
  
  static double nextDouble(long d64) {
    if (d64 == 9218868437227405312L)
      return longToDouble(9218868437227405312L); 
    if (sign(d64) < 0 && significand(d64) == 0L)
      return 0.0D; 
    if (sign(d64) < 0)
      return longToDouble(d64 - 1L); 
    return longToDouble(d64 + 1L);
  }
  
  static double previousDouble(long d64) {
    if (d64 == -4503599627370496L)
      return -Infinity(); 
    if (sign(d64) < 0)
      return longToDouble(d64 + 1L); 
    if (significand(d64) == 0L)
      return -0.0D; 
    return longToDouble(d64 - 1L);
  }
  
  static int exponent(long d64) {
    if (isDenormal(d64))
      return -1074; 
    int biased_e = (int)((d64 & 0x7FF0000000000000L) >>> 52L);
    return biased_e - 1075;
  }
  
  static long significand(long d64) {
    long significand = d64 & 0xFFFFFFFFFFFFFL;
    if (!isDenormal(d64))
      return significand + 4503599627370496L; 
    return significand;
  }
  
  static boolean isDenormal(long d64) {
    return ((d64 & 0x7FF0000000000000L) == 0L);
  }
  
  static boolean isSpecial(long d64) {
    return ((d64 & 0x7FF0000000000000L) == 9218868437227405312L);
  }
  
  static boolean isNaN(long d64) {
    return ((d64 & 0x7FF0000000000000L) == 9218868437227405312L && (d64 & 0xFFFFFFFFFFFFFL) != 0L);
  }
  
  static boolean isInfinite(long d64) {
    return ((d64 & 0x7FF0000000000000L) == 9218868437227405312L && (d64 & 0xFFFFFFFFFFFFFL) == 0L);
  }
  
  static int sign(long d64) {
    return ((d64 & Long.MIN_VALUE) == 0L) ? 1 : -1;
  }
  
  static void normalizedBoundaries(long d64, DiyFp m_minus, DiyFp m_plus) {
    assert value(d64) > 0.0D;
    DiyFp v = asDiyFp(d64);
    m_plus.setF((v.f() << 1L) + 1L);
    m_plus.setE(v.e() - 1);
    m_plus.normalize();
    if (lowerBoundaryIsCloser(d64)) {
      m_minus.setF((v.f() << 2L) - 1L);
      m_minus.setE(v.e() - 2);
    } else {
      m_minus.setF((v.f() << 1L) - 1L);
      m_minus.setE(v.e() - 1);
    } 
    m_minus.setF(m_minus.f() << m_minus.e() - m_plus.e());
    m_minus.setE(m_plus.e());
  }
  
  static boolean lowerBoundaryIsCloser(long d64) {
    boolean physical_significand_is_zero = ((d64 & 0xFFFFFFFFFFFFFL) == 0L);
    return (physical_significand_is_zero && exponent(d64) != -1074);
  }
  
  static double value(long d64) {
    return longToDouble(d64);
  }
  
  static int significandSizeForOrderOfMagnitude(int order) {
    if (order >= -1021)
      return 53; 
    if (order <= -1074)
      return 0; 
    return order - -1074;
  }
  
  static double Infinity() {
    return longToDouble(9218868437227405312L);
  }
  
  static double NaN() {
    return longToDouble(9221120237041090560L);
  }
}
