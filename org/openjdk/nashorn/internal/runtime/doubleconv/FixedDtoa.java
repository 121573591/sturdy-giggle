package org.openjdk.nashorn.internal.runtime.doubleconv;

class FixedDtoa {
  static final int kDoubleSignificandSize = 53;
  
  static class UInt128 {
    private static final long kMask32 = 4294967295L;
    
    private long high_bits_;
    
    private long low_bits_;
    
    UInt128(long high_bits, long low_bits) {
      this.high_bits_ = high_bits;
      this.low_bits_ = low_bits;
    }
    
    void multiply(int multiplicand) {
      long accumulator = (this.low_bits_ & 0xFFFFFFFFL) * multiplicand;
      long part = accumulator & 0xFFFFFFFFL;
      accumulator >>>= 32L;
      accumulator += (this.low_bits_ >>> 32L) * multiplicand;
      this.low_bits_ = (accumulator << 32L) + part;
      accumulator >>>= 32L;
      accumulator += (this.high_bits_ & 0xFFFFFFFFL) * multiplicand;
      part = accumulator & 0xFFFFFFFFL;
      accumulator >>>= 32L;
      accumulator += (this.high_bits_ >>> 32L) * multiplicand;
      this.high_bits_ = (accumulator << 32L) + part;
      assert accumulator >>> 32L == 0L;
    }
    
    void shift(int shift_amount) {
      assert -64 <= shift_amount && shift_amount <= 64;
      if (shift_amount == 0)
        return; 
      if (shift_amount == -64) {
        this.high_bits_ = this.low_bits_;
        this.low_bits_ = 0L;
      } else if (shift_amount == 64) {
        this.low_bits_ = this.high_bits_;
        this.high_bits_ = 0L;
      } else if (shift_amount <= 0) {
        this.high_bits_ <<= -shift_amount;
        this.high_bits_ += this.low_bits_ >>> 64 + shift_amount;
        this.low_bits_ <<= -shift_amount;
      } else {
        this.low_bits_ >>>= shift_amount;
        this.low_bits_ += this.high_bits_ << 64 - shift_amount;
        this.high_bits_ >>>= shift_amount;
      } 
    }
    
    int divModPowerOf2(int power) {
      if (power >= 64) {
        int i = (int)(this.high_bits_ >>> power - 64);
        this.high_bits_ -= i << power - 64;
        return i;
      } 
      long part_low = this.low_bits_ >>> power;
      long part_high = this.high_bits_ << 64 - power;
      int result = (int)(part_low + part_high);
      this.high_bits_ = 0L;
      this.low_bits_ -= part_low << power;
      return result;
    }
    
    boolean isZero() {
      return (this.high_bits_ == 0L && this.low_bits_ == 0L);
    }
    
    int bitAt(int position) {
      if (position >= 64)
        return (int)(this.high_bits_ >>> position - 64) & 0x1; 
      return (int)(this.low_bits_ >>> position) & 0x1;
    }
  }
  
  static void fillDigits32FixedLength(int number, int requested_length, DtoaBuffer buffer) {
    for (int i = requested_length - 1; i >= 0; i--) {
      buffer.chars[buffer.length + i] = (char)(48 + Integer.remainderUnsigned(number, 10));
      number = Integer.divideUnsigned(number, 10);
    } 
    buffer.length += requested_length;
  }
  
  static void fillDigits32(int number, DtoaBuffer buffer) {
    int number_length = 0;
    while (number != 0) {
      int digit = Integer.remainderUnsigned(number, 10);
      number = Integer.divideUnsigned(number, 10);
      buffer.chars[buffer.length + number_length] = (char)(48 + digit);
      number_length++;
    } 
    int i = buffer.length;
    int j = buffer.length + number_length - 1;
    while (i < j) {
      char tmp = buffer.chars[i];
      buffer.chars[i] = buffer.chars[j];
      buffer.chars[j] = tmp;
      i++;
      j--;
    } 
    buffer.length += number_length;
  }
  
  static void fillDigits64FixedLength(long number, DtoaBuffer buffer) {
    int kTen7 = 10000000;
    int part2 = (int)Long.remainderUnsigned(number, 10000000L);
    number = Long.divideUnsigned(number, 10000000L);
    int part1 = (int)Long.remainderUnsigned(number, 10000000L);
    int part0 = (int)Long.divideUnsigned(number, 10000000L);
    fillDigits32FixedLength(part0, 3, buffer);
    fillDigits32FixedLength(part1, 7, buffer);
    fillDigits32FixedLength(part2, 7, buffer);
  }
  
  static void FillDigits64(long number, DtoaBuffer buffer) {
    int kTen7 = 10000000;
    int part2 = (int)Long.remainderUnsigned(number, 10000000L);
    number = Long.divideUnsigned(number, 10000000L);
    int part1 = (int)Long.remainderUnsigned(number, 10000000L);
    int part0 = (int)Long.divideUnsigned(number, 10000000L);
    if (part0 != 0) {
      fillDigits32(part0, buffer);
      fillDigits32FixedLength(part1, 7, buffer);
      fillDigits32FixedLength(part2, 7, buffer);
    } else if (part1 != 0) {
      fillDigits32(part1, buffer);
      fillDigits32FixedLength(part2, 7, buffer);
    } else {
      fillDigits32(part2, buffer);
    } 
  }
  
  static void roundUp(DtoaBuffer buffer) {
    if (buffer.length == 0) {
      buffer.chars[0] = '1';
      buffer.decimalPoint = 1;
      buffer.length = 1;
      return;
    } 
    buffer.chars[buffer.length - 1] = (char)(buffer.chars[buffer.length - 1] + 1);
    for (int i = buffer.length - 1; i > 0; i--) {
      if (buffer.chars[i] != ':')
        return; 
      buffer.chars[i] = '0';
      buffer.chars[i - 1] = (char)(buffer.chars[i - 1] + 1);
    } 
    if (buffer.chars[0] == ':') {
      buffer.chars[0] = '1';
      buffer.decimalPoint++;
    } 
  }
  
  static void fillFractionals(long fractionals, int exponent, int fractional_count, DtoaBuffer buffer) {
    assert -128 <= exponent && exponent <= 0;
    if (-exponent <= 64) {
      assert fractionals >>> 56L == 0L;
      int point = -exponent;
      for (int i = 0; i < fractional_count && 
        fractionals != 0L; i++) {
        fractionals *= 5L;
        point--;
        int digit = (int)(fractionals >>> point);
        assert digit <= 9;
        buffer.chars[buffer.length] = (char)(48 + digit);
        buffer.length++;
        fractionals -= digit << point;
      } 
      assert fractionals == 0L || point - 1 >= 0;
      if (fractionals != 0L && (fractionals >>> point - 1 & 0x1L) == 1L)
        roundUp(buffer); 
    } else {
      assert 64 < -exponent && -exponent <= 128;
      UInt128 fractionals128 = new UInt128(fractionals, 0L);
      fractionals128.shift(-exponent - 64);
      int point = 128;
      for (int i = 0; i < fractional_count && 
        !fractionals128.isZero(); i++) {
        fractionals128.multiply(5);
        point--;
        int digit = fractionals128.divModPowerOf2(point);
        assert digit <= 9;
        buffer.chars[buffer.length] = (char)(48 + digit);
        buffer.length++;
      } 
      if (fractionals128.bitAt(point - 1) == 1)
        roundUp(buffer); 
    } 
  }
  
  static void trimZeros(DtoaBuffer buffer) {
    while (buffer.length > 0 && buffer.chars[buffer.length - 1] == '0')
      buffer.length--; 
    int first_non_zero = 0;
    while (first_non_zero < buffer.length && buffer.chars[first_non_zero] == '0')
      first_non_zero++; 
    if (first_non_zero != 0) {
      for (int i = first_non_zero; i < buffer.length; i++)
        buffer.chars[i - first_non_zero] = buffer.chars[i]; 
      buffer.length -= first_non_zero;
      buffer.decimalPoint -= first_non_zero;
    } 
  }
  
  static boolean fastFixedDtoa(double v, int fractional_count, DtoaBuffer buffer) {
    long kMaxUInt32 = 4294967295L;
    long l = IeeeDouble.doubleToLong(v);
    long significand = IeeeDouble.significand(l);
    int exponent = IeeeDouble.exponent(l);
    if (exponent > 20)
      return false; 
    if (fractional_count > 20)
      return false; 
    if (exponent + 53 > 64) {
      int quotient;
      long remainder, kFive17 = 762939453125L;
      long divisor = 762939453125L;
      int divisor_power = 17;
      long dividend = significand;
      if (exponent > 17) {
        dividend <<= exponent - 17;
        quotient = (int)Long.divideUnsigned(dividend, divisor);
        remainder = Long.remainderUnsigned(dividend, divisor) << 17L;
      } else {
        divisor <<= 17 - exponent;
        quotient = (int)Long.divideUnsigned(dividend, divisor);
        remainder = Long.remainderUnsigned(dividend, divisor) << exponent;
      } 
      fillDigits32(quotient, buffer);
      fillDigits64FixedLength(remainder, buffer);
      buffer.decimalPoint = buffer.length;
    } else if (exponent >= 0) {
      significand <<= exponent;
      FillDigits64(significand, buffer);
      buffer.decimalPoint = buffer.length;
    } else if (exponent > -53) {
      long integrals = significand >>> -exponent;
      long fractionals = significand - (integrals << -exponent);
      if (Long.compareUnsigned(integrals, 4294967295L) > 0) {
        FillDigits64(integrals, buffer);
      } else {
        fillDigits32((int)integrals, buffer);
      } 
      buffer.decimalPoint = buffer.length;
      fillFractionals(fractionals, exponent, fractional_count, buffer);
    } else if (exponent < -128) {
      assert fractional_count <= 20;
      buffer.reset();
      buffer.decimalPoint = -fractional_count;
    } else {
      buffer.decimalPoint = 0;
      fillFractionals(significand, exponent, fractional_count, buffer);
    } 
    trimZeros(buffer);
    if (buffer.length == 0)
      buffer.decimalPoint = -fractional_count; 
    return true;
  }
}
