package com.fasterxml.jackson.core.io.schubfach;

public final class DoubleToDecimal {
  static final int P = 53;
  
  private static final int W = 11;
  
  static final int Q_MIN = -1074;
  
  static final int Q_MAX = 971;
  
  static final int E_MIN = -323;
  
  static final int E_MAX = 309;
  
  static final long C_TINY = 3L;
  
  static final int K_MIN = -324;
  
  static final int K_MAX = 292;
  
  static final int H = 17;
  
  private static final long C_MIN = 4503599627370496L;
  
  private static final int BQ_MASK = 2047;
  
  private static final long T_MASK = 4503599627370495L;
  
  private static final long MASK_63 = 9223372036854775807L;
  
  private static final int MASK_28 = 268435455;
  
  private static final int NON_SPECIAL = 0;
  
  private static final int PLUS_ZERO = 1;
  
  private static final int MINUS_ZERO = 2;
  
  private static final int PLUS_INF = 3;
  
  private static final int MINUS_INF = 4;
  
  private static final int NAN = 5;
  
  public final int MAX_CHARS = 24;
  
  private final byte[] bytes = new byte[24];
  
  private int index;
  
  public static String toString(double v) {
    return (new DoubleToDecimal()).toDecimalString(v);
  }
  
  private String toDecimalString(double v) {
    switch (toDecimal(v)) {
      case 0:
        return charsToString();
      case 1:
        return "0.0";
      case 2:
        return "-0.0";
      case 3:
        return "Infinity";
      case 4:
        return "-Infinity";
    } 
    return "NaN";
  }
  
  private int toDecimal(double v) {
    long bits = Double.doubleToRawLongBits(v);
    long t = bits & 0xFFFFFFFFFFFFFL;
    int bq = (int)(bits >>> 52L) & 0x7FF;
    if (bq < 2047) {
      this.index = -1;
      if (bits < 0L)
        append(45); 
      if (bq != 0) {
        int mq = 1075 - bq;
        long c = 0x10000000000000L | t;
        if ((((0 < mq) ? 1 : 0) & ((mq < 53) ? 1 : 0)) != 0) {
          long f = c >> mq;
          if (f << mq == c)
            return toChars(f, 0); 
        } 
        return toDecimal(-mq, c, 0);
      } 
      if (t != 0L)
        return (t < 3L) ? 
          toDecimal(-1074, 10L * t, -1) : 
          toDecimal(-1074, t, 0); 
      return (bits == 0L) ? 1 : 2;
    } 
    if (t != 0L)
      return 5; 
    return (bits > 0L) ? 3 : 4;
  }
  
  private int toDecimal(int q, long c, int dk) {
    long cbl;
    int k, out = (int)c & 0x1;
    long cb = c << 2L;
    long cbr = cb + 2L;
    if ((((c != 4503599627370496L) ? 1 : 0) | ((q == -1074) ? 1 : 0)) != 0) {
      cbl = cb - 2L;
      k = MathUtils.flog10pow2(q);
    } else {
      cbl = cb - 1L;
      k = MathUtils.flog10threeQuartersPow2(q);
    } 
    int h = q + MathUtils.flog2pow10(-k) + 2;
    long g1 = MathUtils.g1(k);
    long g0 = MathUtils.g0(k);
    long vb = rop(g1, g0, cb << h);
    long vbl = rop(g1, g0, cbl << h);
    long vbr = rop(g1, g0, cbr << h);
    long s = vb >> 2L;
    if (s >= 100L) {
      long sp10 = 10L * MathUtils.multiplyHigh(s, 1844674407370955168L);
      long tp10 = sp10 + 10L;
      boolean upin = (vbl + out <= sp10 << 2L);
      boolean wpin = ((tp10 << 2L) + out <= vbr);
      if (upin != wpin)
        return toChars(upin ? sp10 : tp10, k); 
    } 
    long t = s + 1L;
    boolean uin = (vbl + out <= s << 2L);
    boolean win = ((t << 2L) + out <= vbr);
    if (uin != win)
      return toChars(uin ? s : t, k + dk); 
    long cmp = vb - (s + t << 1L);
    return toChars((cmp < 0L || (cmp == 0L && (s & 0x1L) == 0L)) ? s : t, k + dk);
  }
  
  private static long rop(long g1, long g0, long cp) {
    long x1 = MathUtils.multiplyHigh(g0, cp);
    long y0 = g1 * cp;
    long y1 = MathUtils.multiplyHigh(g1, cp);
    long z = (y0 >>> 1L) + x1;
    long vbp = y1 + (z >>> 63L);
    return vbp | (z & Long.MAX_VALUE) + Long.MAX_VALUE >>> 63L;
  }
  
  private int toChars(long f, int e) {
    int len = MathUtils.flog10pow2(64 - Long.numberOfLeadingZeros(f));
    if (f >= MathUtils.pow10(len))
      len++; 
    f *= MathUtils.pow10(17 - len);
    e += len;
    long hm = MathUtils.multiplyHigh(f, 193428131138340668L) >>> 20L;
    int l = (int)(f - 100000000L * hm);
    int h = (int)(hm * 1441151881L >>> 57L);
    int m = (int)(hm - (100000000 * h));
    if (0 < e && e <= 7)
      return toChars1(h, m, l, e); 
    if (-3 < e && e <= 0)
      return toChars2(h, m, l, e); 
    return toChars3(h, m, l, e);
  }
  
  private int toChars1(int h, int m, int l, int e) {
    appendDigit(h);
    int y = y(m);
    int i = 1;
    for (; i < e; i++) {
      int t = 10 * y;
      appendDigit(t >>> 28);
      y = t & 0xFFFFFFF;
    } 
    append(46);
    for (; i <= 8; i++) {
      int t = 10 * y;
      appendDigit(t >>> 28);
      y = t & 0xFFFFFFF;
    } 
    lowDigits(l);
    return 0;
  }
  
  private int toChars2(int h, int m, int l, int e) {
    appendDigit(0);
    append(46);
    for (; e < 0; e++)
      appendDigit(0); 
    appendDigit(h);
    append8Digits(m);
    lowDigits(l);
    return 0;
  }
  
  private int toChars3(int h, int m, int l, int e) {
    appendDigit(h);
    append(46);
    append8Digits(m);
    lowDigits(l);
    exponent(e - 1);
    return 0;
  }
  
  private void lowDigits(int l) {
    if (l != 0)
      append8Digits(l); 
    removeTrailingZeroes();
  }
  
  private void append8Digits(int m) {
    int y = y(m);
    for (int i = 0; i < 8; i++) {
      int t = 10 * y;
      appendDigit(t >>> 28);
      y = t & 0xFFFFFFF;
    } 
  }
  
  private void removeTrailingZeroes() {
    while (this.bytes[this.index] == 48)
      this.index--; 
    if (this.bytes[this.index] == 46)
      this.index++; 
  }
  
  private int y(int a) {
    return (int)(MathUtils.multiplyHigh((a + 1) << 28L, 193428131138340668L) >>> 20L) - 1;
  }
  
  private void exponent(int e) {
    append(69);
    if (e < 0) {
      append(45);
      e = -e;
    } 
    if (e < 10) {
      appendDigit(e);
      return;
    } 
    if (e >= 100) {
      int i = e * 1311 >>> 17;
      appendDigit(i);
      e -= 100 * i;
    } 
    int d = e * 103 >>> 10;
    appendDigit(d);
    appendDigit(e - 10 * d);
  }
  
  private void append(int c) {
    this.bytes[++this.index] = (byte)c;
  }
  
  private void appendDigit(int d) {
    this.bytes[++this.index] = (byte)(48 + d);
  }
  
  private String charsToString() {
    return new String(this.bytes, 0, 0, this.index + 1);
  }
}
