package org.openjdk.nashorn.internal.runtime.doubleconv;

import java.util.Arrays;

class Bignum {
  static final int kMaxSignificantBits = 3584;
  
  static final int kChunkSize = 32;
  
  static final int kDoubleChunkSize = 64;
  
  static final int kBigitSize = 28;
  
  static final int kBigitMask = 268435455;
  
  static final int kBigitCapacity = 128;
  
  private int used_digits_;
  
  private int exponent_;
  
  private final int[] bigits_ = new int[128];
  
  void times10() {
    multiplyByUInt32(10);
  }
  
  static boolean equal(Bignum a, Bignum b) {
    return (compare(a, b) == 0);
  }
  
  static boolean lessEqual(Bignum a, Bignum b) {
    return (compare(a, b) <= 0);
  }
  
  static boolean less(Bignum a, Bignum b) {
    return (compare(a, b) < 0);
  }
  
  static boolean plusEqual(Bignum a, Bignum b, Bignum c) {
    return (plusCompare(a, b, c) == 0);
  }
  
  static boolean plusLessEqual(Bignum a, Bignum b, Bignum c) {
    return (plusCompare(a, b, c) <= 0);
  }
  
  static boolean plusLess(Bignum a, Bignum b, Bignum c) {
    return (plusCompare(a, b, c) < 0);
  }
  
  private void ensureCapacity(int size) {
    if (size > 128)
      throw new RuntimeException(); 
  }
  
  int bigitLength() {
    return this.used_digits_ + this.exponent_;
  }
  
  void assignUInt16(char value) {
    zero();
    if (value == '\000')
      return; 
    ensureCapacity(1);
    this.bigits_[0] = value;
    this.used_digits_ = 1;
  }
  
  void assignUInt64(long value) {
    int kUInt64Size = 64;
    zero();
    if (value == 0L)
      return; 
    int needed_bigits = 3;
    ensureCapacity(3);
    for (int i = 0; i < 3; i++) {
      this.bigits_[i] = (int)(value & 0xFFFFFFFL);
      value >>>= 28L;
    } 
    this.used_digits_ = 3;
    clamp();
  }
  
  void assignBignum(Bignum other) {
    this.exponent_ = other.exponent_;
    int i;
    for (i = 0; i < other.used_digits_; i++)
      this.bigits_[i] = other.bigits_[i]; 
    for (i = other.used_digits_; i < this.used_digits_; i++)
      this.bigits_[i] = 0; 
    this.used_digits_ = other.used_digits_;
  }
  
  static long readUInt64(String str, int from, int digits_to_read) {
    long result = 0L;
    for (int i = from; i < from + digits_to_read; i++) {
      int digit = str.charAt(i) - 48;
      assert 0 <= digit && digit <= 9;
      result = result * 10L + digit;
    } 
    return result;
  }
  
  void assignDecimalString(String str) {
    int kMaxUint64DecimalDigits = 19;
    zero();
    int length = str.length();
    int pos = 0;
    while (length >= 19) {
      long l = readUInt64(str, pos, 19);
      pos += 19;
      length -= 19;
      multiplyByPowerOfTen(19);
      addUInt64(l);
    } 
    long digits = readUInt64(str, pos, length);
    multiplyByPowerOfTen(length);
    addUInt64(digits);
    clamp();
  }
  
  static int hexCharValue(char c) {
    if ('0' <= c && c <= '9')
      return c - 48; 
    if ('a' <= c && c <= 'f')
      return 10 + c - 97; 
    assert 'A' <= c && c <= 'F';
    return 10 + c - 65;
  }
  
  void assignHexString(String str) {
    zero();
    int length = str.length();
    int needed_bigits = length * 4 / 28 + 1;
    ensureCapacity(needed_bigits);
    int string_index = length - 1;
    for (int i = 0; i < needed_bigits - 1; i++) {
      int current_bigit = 0;
      for (int k = 0; k < 7; k++)
        current_bigit += hexCharValue(str.charAt(string_index--)) << k * 4; 
      this.bigits_[i] = current_bigit;
    } 
    this.used_digits_ = needed_bigits - 1;
    int most_significant_bigit = 0;
    for (int j = 0; j <= string_index; j++) {
      most_significant_bigit <<= 4;
      most_significant_bigit += hexCharValue(str.charAt(j));
    } 
    if (most_significant_bigit != 0) {
      this.bigits_[this.used_digits_] = most_significant_bigit;
      this.used_digits_++;
    } 
    clamp();
  }
  
  void addUInt64(long operand) {
    if (operand == 0L)
      return; 
    Bignum other = new Bignum();
    other.assignUInt64(operand);
    addBignum(other);
  }
  
  void addBignum(Bignum other) {
    assert isClamped();
    assert other.isClamped();
    align(other);
    ensureCapacity(1 + Math.max(bigitLength(), other.bigitLength()) - this.exponent_);
    int carry = 0;
    int bigit_pos = other.exponent_ - this.exponent_;
    assert bigit_pos >= 0;
    for (int i = 0; i < other.used_digits_; i++) {
      int sum = this.bigits_[bigit_pos] + other.bigits_[i] + carry;
      this.bigits_[bigit_pos] = sum & 0xFFFFFFF;
      carry = sum >>> 28;
      bigit_pos++;
    } 
    while (carry != 0) {
      int sum = this.bigits_[bigit_pos] + carry;
      this.bigits_[bigit_pos] = sum & 0xFFFFFFF;
      carry = sum >>> 28;
      bigit_pos++;
    } 
    this.used_digits_ = Math.max(bigit_pos, this.used_digits_);
    assert isClamped();
  }
  
  void subtractBignum(Bignum other) {
    assert isClamped();
    assert other.isClamped();
    assert lessEqual(other, this);
    align(other);
    int offset = other.exponent_ - this.exponent_;
    int borrow = 0;
    int i;
    for (i = 0; i < other.used_digits_; i++) {
      assert borrow == 0 || borrow == 1;
      int difference = this.bigits_[i + offset] - other.bigits_[i] - borrow;
      this.bigits_[i + offset] = difference & 0xFFFFFFF;
      borrow = difference >>> 31;
    } 
    while (borrow != 0) {
      int difference = this.bigits_[i + offset] - borrow;
      this.bigits_[i + offset] = difference & 0xFFFFFFF;
      borrow = difference >>> 31;
      i++;
    } 
    clamp();
  }
  
  void shiftLeft(int shift_amount) {
    if (this.used_digits_ == 0)
      return; 
    this.exponent_ += shift_amount / 28;
    int local_shift = shift_amount % 28;
    ensureCapacity(this.used_digits_ + 1);
    bigitsShiftLeft(local_shift);
  }
  
  void multiplyByUInt32(int factor) {
    if (factor == 1)
      return; 
    if (factor == 0) {
      zero();
      return;
    } 
    if (this.used_digits_ == 0)
      return; 
    long carry = 0L;
    for (int i = 0; i < this.used_digits_; i++) {
      long product = (factor & 0xFFFFFFFFL) * this.bigits_[i] + carry;
      this.bigits_[i] = (int)(product & 0xFFFFFFFL);
      carry = product >>> 28L;
    } 
    while (carry != 0L) {
      ensureCapacity(this.used_digits_ + 1);
      this.bigits_[this.used_digits_] = (int)(carry & 0xFFFFFFFL);
      this.used_digits_++;
      carry >>>= 28L;
    } 
  }
  
  void multiplyByUInt64(long factor) {
    if (factor == 1L)
      return; 
    if (factor == 0L) {
      zero();
      return;
    } 
    long carry = 0L;
    long low = factor & 0xFFFFFFFFL;
    long high = factor >>> 32L;
    for (int i = 0; i < this.used_digits_; i++) {
      long product_low = low * this.bigits_[i];
      long product_high = high * this.bigits_[i];
      long tmp = (carry & 0xFFFFFFFL) + product_low;
      this.bigits_[i] = (int)(tmp & 0xFFFFFFFL);
      carry = (carry >>> 28L) + (tmp >>> 28L) + (product_high << 4L);
    } 
    while (carry != 0L) {
      ensureCapacity(this.used_digits_ + 1);
      this.bigits_[this.used_digits_] = (int)(carry & 0xFFFFFFFL);
      this.used_digits_++;
      carry >>>= 28L;
    } 
  }
  
  void multiplyByPowerOfTen(int exponent) {
    long kFive27 = 7450580596923828125L;
    int kFive1 = 5;
    int kFive2 = 25;
    int kFive3 = 125;
    int kFive4 = 625;
    int kFive5 = 3125;
    int kFive6 = 15625;
    int kFive7 = 78125;
    int kFive8 = 390625;
    int kFive9 = 1953125;
    int kFive10 = 9765625;
    int kFive11 = 48828125;
    int kFive12 = 244140625;
    int kFive13 = 1220703125;
    int[] kFive1_to_12 = { 
        5, 25, 125, 625, 3125, 15625, 78125, 390625, 1953125, 9765625, 
        48828125, 244140625 };
    assert exponent >= 0;
    if (exponent == 0)
      return; 
    if (this.used_digits_ == 0)
      return; 
    int remaining_exponent = exponent;
    while (remaining_exponent >= 27) {
      multiplyByUInt64(7450580596923828125L);
      remaining_exponent -= 27;
    } 
    while (remaining_exponent >= 13) {
      multiplyByUInt32(1220703125);
      remaining_exponent -= 13;
    } 
    if (remaining_exponent > 0)
      multiplyByUInt32(kFive1_to_12[remaining_exponent - 1]); 
    shiftLeft(exponent);
  }
  
  void square() {
    assert isClamped();
    int product_length = 2 * this.used_digits_;
    ensureCapacity(product_length);
    if (256L <= this.used_digits_)
      throw new RuntimeException("unimplemented"); 
    long accumulator = 0L;
    int copy_offset = this.used_digits_;
    int i;
    for (i = 0; i < this.used_digits_; i++)
      this.bigits_[copy_offset + i] = this.bigits_[i]; 
    for (i = 0; i < this.used_digits_; i++) {
      int bigit_index1 = i;
      int bigit_index2 = 0;
      while (bigit_index1 >= 0) {
        int int1 = this.bigits_[copy_offset + bigit_index1];
        int int2 = this.bigits_[copy_offset + bigit_index2];
        accumulator += int1 * int2;
        bigit_index1--;
        bigit_index2++;
      } 
      this.bigits_[i] = (int)(accumulator & 0xFFFFFFFL);
      accumulator >>>= 28L;
    } 
    for (i = this.used_digits_; i < product_length; i++) {
      int bigit_index1 = this.used_digits_ - 1;
      int bigit_index2 = i - bigit_index1;
      while (bigit_index2 < this.used_digits_) {
        int int1 = this.bigits_[copy_offset + bigit_index1];
        int int2 = this.bigits_[copy_offset + bigit_index2];
        accumulator += int1 * int2;
        bigit_index1--;
        bigit_index2++;
      } 
      this.bigits_[i] = (int)(accumulator & 0xFFFFFFFL);
      accumulator >>>= 28L;
    } 
    assert accumulator == 0L;
    this.used_digits_ = product_length;
    this.exponent_ *= 2;
    clamp();
  }
  
  void assignPowerUInt16(int base, int power_exponent) {
    assert base != 0;
    assert power_exponent >= 0;
    if (power_exponent == 0) {
      assignUInt16('\001');
      return;
    } 
    zero();
    int shifts = 0;
    while ((base & 0x1) == 0) {
      base >>>= 1;
      shifts++;
    } 
    int bit_size = 0;
    int tmp_base = base;
    while (tmp_base != 0) {
      tmp_base >>>= 1;
      bit_size++;
    } 
    int final_size = bit_size * power_exponent;
    ensureCapacity(final_size / 28 + 2);
    int mask = 1;
    for (; power_exponent >= mask; mask <<= 1);
    mask >>>= 2;
    long this_value = base;
    boolean delayed_multiplication = false;
    long max_32bits = 4294967295L;
    while (mask != 0 && this_value <= 4294967295L) {
      this_value *= this_value;
      if ((power_exponent & mask) != 0) {
        assert bit_size > 0;
        long base_bits_mask = (1L << 64 - bit_size) - 1L ^ 0xFFFFFFFFFFFFFFFFL;
        boolean high_bits_zero = ((this_value & base_bits_mask) == 0L);
        if (high_bits_zero) {
          this_value *= base;
        } else {
          delayed_multiplication = true;
        } 
      } 
      mask >>>= 1;
    } 
    assignUInt64(this_value);
    if (delayed_multiplication)
      multiplyByUInt32(base); 
    while (mask != 0) {
      square();
      if ((power_exponent & mask) != 0)
        multiplyByUInt32(base); 
      mask >>>= 1;
    } 
    shiftLeft(shifts * power_exponent);
  }
  
  char divideModuloIntBignum(Bignum other) {
    assert isClamped();
    assert other.isClamped();
    assert other.used_digits_ > 0;
    if (bigitLength() < other.bigitLength())
      return Character.MIN_VALUE; 
    align(other);
    char result = Character.MIN_VALUE;
    while (bigitLength() > other.bigitLength()) {
      assert other.bigits_[other.used_digits_ - 1] >= 16777216;
      assert this.bigits_[this.used_digits_ - 1] < 65536;
      result = (char)(result + this.bigits_[this.used_digits_ - 1]);
      subtractTimes(other, this.bigits_[this.used_digits_ - 1]);
    } 
    assert bigitLength() == other.bigitLength();
    int this_bigit = this.bigits_[this.used_digits_ - 1];
    int other_bigit = other.bigits_[other.used_digits_ - 1];
    if (other.used_digits_ == 1) {
      int quotient = Integer.divideUnsigned(this_bigit, other_bigit);
      this.bigits_[this.used_digits_ - 1] = this_bigit - other_bigit * quotient;
      assert Integer.compareUnsigned(quotient, 65536) < 0;
      result = (char)(result + quotient);
      clamp();
      return result;
    } 
    int division_estimate = Integer.divideUnsigned(this_bigit, other_bigit + 1);
    assert Integer.compareUnsigned(division_estimate, 65536) < 0;
    result = (char)(result + division_estimate);
    subtractTimes(other, division_estimate);
    if (other_bigit * (division_estimate + 1) > this_bigit)
      return result; 
    while (lessEqual(other, this)) {
      subtractBignum(other);
      result = (char)(result + 1);
    } 
    return result;
  }
  
  static int sizeInHexChars(int number) {
    assert number > 0;
    int result = 0;
    while (number != 0) {
      number >>>= 4;
      result++;
    } 
    return result;
  }
  
  static char hexCharOfValue(int value) {
    assert 0 <= value && value <= 16;
    if (value < 10)
      return (char)(value + 48); 
    return (char)(value - 10 + 65);
  }
  
  String toHexString() {
    assert isClamped();
    int kHexCharsPerBigit = 7;
    if (this.used_digits_ == 0)
      return "0"; 
    int needed_chars = (bigitLength() - 1) * 7 + sizeInHexChars(this.bigits_[this.used_digits_ - 1]);
    StringBuilder buffer = new StringBuilder(needed_chars);
    buffer.setLength(needed_chars);
    int string_index = needed_chars - 1;
    int i;
    for (i = 0; i < this.exponent_; i++) {
      for (int j = 0; j < 7; j++)
        buffer.setCharAt(string_index--, '0'); 
    } 
    for (i = 0; i < this.used_digits_ - 1; i++) {
      int current_bigit = this.bigits_[i];
      for (int j = 0; j < 7; j++) {
        buffer.setCharAt(string_index--, hexCharOfValue(current_bigit & 0xF));
        current_bigit >>>= 4;
      } 
    } 
    int most_significant_bigit = this.bigits_[this.used_digits_ - 1];
    while (most_significant_bigit != 0) {
      buffer.setCharAt(string_index--, hexCharOfValue(most_significant_bigit & 0xF));
      most_significant_bigit >>>= 4;
    } 
    return buffer.toString();
  }
  
  int bigitOrZero(int index) {
    if (index >= bigitLength())
      return 0; 
    if (index < this.exponent_)
      return 0; 
    return this.bigits_[index - this.exponent_];
  }
  
  static int compare(Bignum a, Bignum b) {
    assert a.isClamped();
    assert b.isClamped();
    int bigit_length_a = a.bigitLength();
    int bigit_length_b = b.bigitLength();
    if (bigit_length_a < bigit_length_b)
      return -1; 
    if (bigit_length_a > bigit_length_b)
      return 1; 
    for (int i = bigit_length_a - 1; i >= Math.min(a.exponent_, b.exponent_); i--) {
      int bigit_a = a.bigitOrZero(i);
      int bigit_b = b.bigitOrZero(i);
      if (bigit_a < bigit_b)
        return -1; 
      if (bigit_a > bigit_b)
        return 1; 
    } 
    return 0;
  }
  
  static int plusCompare(Bignum a, Bignum b, Bignum c) {
    assert a.isClamped();
    assert b.isClamped();
    assert c.isClamped();
    if (a.bigitLength() < b.bigitLength())
      return plusCompare(b, a, c); 
    if (a.bigitLength() + 1 < c.bigitLength())
      return -1; 
    if (a.bigitLength() > c.bigitLength())
      return 1; 
    if (a.exponent_ >= b.bigitLength() && a.bigitLength() < c.bigitLength())
      return -1; 
    int borrow = 0;
    int min_exponent = Math.min(Math.min(a.exponent_, b.exponent_), c.exponent_);
    for (int i = c.bigitLength() - 1; i >= min_exponent; i--) {
      int int_a = a.bigitOrZero(i);
      int int_b = b.bigitOrZero(i);
      int int_c = c.bigitOrZero(i);
      int sum = int_a + int_b;
      if (sum > int_c + borrow)
        return 1; 
      borrow = int_c + borrow - sum;
      if (borrow > 1)
        return -1; 
      borrow <<= 28;
    } 
    if (borrow == 0)
      return 0; 
    return -1;
  }
  
  void clamp() {
    while (this.used_digits_ > 0 && this.bigits_[this.used_digits_ - 1] == 0)
      this.used_digits_--; 
    if (this.used_digits_ == 0)
      this.exponent_ = 0; 
  }
  
  boolean isClamped() {
    return (this.used_digits_ == 0 || this.bigits_[this.used_digits_ - 1] != 0);
  }
  
  void zero() {
    for (int i = 0; i < this.used_digits_; i++)
      this.bigits_[i] = 0; 
    this.used_digits_ = 0;
    this.exponent_ = 0;
  }
  
  void align(Bignum other) {
    if (this.exponent_ > other.exponent_) {
      int zero_digits = this.exponent_ - other.exponent_;
      ensureCapacity(this.used_digits_ + zero_digits);
      int i;
      for (i = this.used_digits_ - 1; i >= 0; i--)
        this.bigits_[i + zero_digits] = this.bigits_[i]; 
      for (i = 0; i < zero_digits; i++)
        this.bigits_[i] = 0; 
      this.used_digits_ += zero_digits;
      this.exponent_ -= zero_digits;
      assert this.used_digits_ >= 0;
      assert this.exponent_ >= 0;
    } 
  }
  
  void bigitsShiftLeft(int shift_amount) {
    assert shift_amount < 28;
    assert shift_amount >= 0;
    int carry = 0;
    for (int i = 0; i < this.used_digits_; i++) {
      int new_carry = this.bigits_[i] >>> 28 - shift_amount;
      this.bigits_[i] = (this.bigits_[i] << shift_amount) + carry & 0xFFFFFFF;
      carry = new_carry;
    } 
    if (carry != 0) {
      this.bigits_[this.used_digits_] = carry;
      this.used_digits_++;
    } 
  }
  
  void subtractTimes(Bignum other, int factor) {
    assert this.exponent_ <= other.exponent_;
    if (factor < 3) {
      for (int j = 0; j < factor; j++)
        subtractBignum(other); 
      return;
    } 
    int borrow = 0;
    int exponent_diff = other.exponent_ - this.exponent_;
    int i;
    for (i = 0; i < other.used_digits_; i++) {
      long product = factor * other.bigits_[i];
      long remove = borrow + product;
      int difference = this.bigits_[i + exponent_diff] - (int)(remove & 0xFFFFFFFL);
      this.bigits_[i + exponent_diff] = difference & 0xFFFFFFF;
      borrow = (int)((difference >>> 31) + (remove >>> 28L));
    } 
    for (i = other.used_digits_ + exponent_diff; i < this.used_digits_; i++) {
      if (borrow == 0)
        return; 
      int difference = this.bigits_[i] - borrow;
      this.bigits_[i] = difference & 0xFFFFFFF;
      borrow = difference >>> 31;
    } 
    clamp();
  }
  
  public String toString() {
    return "Bignum" + Arrays.toString(this.bigits_);
  }
}
