package org.openjdk.nashorn.internal.runtime.doubleconv;

class DiyFp {
  private long f_;
  
  private int e_;
  
  static final int kSignificandSize = 64;
  
  static final long kUint64MSB = -9223372036854775808L;
  
  DiyFp() {
    this.f_ = 0L;
    this.e_ = 0;
  }
  
  DiyFp(long f, int e) {
    this.f_ = f;
    this.e_ = e;
  }
  
  void subtract(DiyFp other) {
    assert this.e_ == other.e_;
    assert Long.compareUnsigned(this.f_, other.f_) >= 0;
    this.f_ -= other.f_;
  }
  
  static DiyFp minus(DiyFp a, DiyFp b) {
    DiyFp result = new DiyFp(a.f_, a.e_);
    result.subtract(b);
    return result;
  }
  
  final void multiply(DiyFp other) {
    long kM32 = 4294967295L;
    long a = this.f_ >>> 32L;
    long b = this.f_ & 0xFFFFFFFFL;
    long c = other.f_ >>> 32L;
    long d = other.f_ & 0xFFFFFFFFL;
    long ac = a * c;
    long bc = b * c;
    long ad = a * d;
    long bd = b * d;
    long tmp = (bd >>> 32L) + (ad & 0xFFFFFFFFL) + (bc & 0xFFFFFFFFL);
    tmp += 2147483648L;
    long result_f = ac + (ad >>> 32L) + (bc >>> 32L) + (tmp >>> 32L);
    this.e_ += other.e_ + 64;
    this.f_ = result_f;
  }
  
  static DiyFp times(DiyFp a, DiyFp b) {
    DiyFp result = new DiyFp(a.f_, a.e_);
    result.multiply(b);
    return result;
  }
  
  void normalize() {
    assert this.f_ != 0L;
    long significand = this.f_;
    int exponent = this.e_;
    long k10MSBits = -18014398509481984L;
    while ((significand & 0xFFC0000000000000L) == 0L) {
      significand <<= 10L;
      exponent -= 10;
    } 
    while ((significand & Long.MIN_VALUE) == 0L) {
      significand <<= 1L;
      exponent--;
    } 
    this.f_ = significand;
    this.e_ = exponent;
  }
  
  static DiyFp normalize(DiyFp a) {
    DiyFp result = new DiyFp(a.f_, a.e_);
    result.normalize();
    return result;
  }
  
  long f() {
    return this.f_;
  }
  
  int e() {
    return this.e_;
  }
  
  void setF(long new_value) {
    this.f_ = new_value;
  }
  
  void setE(int new_value) {
    this.e_ = new_value;
  }
  
  public String toString() {
    return "DiyFp[f=" + this.f_ + ", e=" + this.e_ + "]";
  }
}
