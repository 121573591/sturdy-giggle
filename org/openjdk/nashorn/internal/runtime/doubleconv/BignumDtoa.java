package org.openjdk.nashorn.internal.runtime.doubleconv;

class BignumDtoa {
  private static int normalizedExponent(long significand, int exponent) {
    assert significand != 0L;
    while ((significand & 0x10000000000000L) == 0L) {
      significand <<= 1L;
      exponent--;
    } 
    return exponent;
  }
  
  static void bignumDtoa(double v, DtoaMode mode, int requested_digits, DtoaBuffer buffer) {
    assert v > 0.0D;
    assert !IeeeDouble.isSpecial(IeeeDouble.doubleToLong(v));
    long l = IeeeDouble.doubleToLong(v);
    long significand = IeeeDouble.significand(l);
    int exponent = IeeeDouble.exponent(l);
    boolean lower_boundary_is_closer = IeeeDouble.lowerBoundaryIsCloser(l);
    boolean need_boundary_deltas = (mode == DtoaMode.SHORTEST);
    boolean is_even = ((significand & 0x1L) == 0L);
    assert significand != 0L;
    int normalizedExponent = normalizedExponent(significand, exponent);
    int estimated_power = estimatePower(normalizedExponent);
    if (mode == DtoaMode.FIXED && -estimated_power - 1 > requested_digits) {
      buffer.reset();
      buffer.decimalPoint = -requested_digits;
      return;
    } 
    Bignum numerator = new Bignum();
    Bignum denominator = new Bignum();
    Bignum delta_minus = new Bignum();
    Bignum delta_plus = new Bignum();
    initialScaledStartValues(significand, exponent, lower_boundary_is_closer, estimated_power, need_boundary_deltas, numerator, denominator, delta_minus, delta_plus);
    buffer.decimalPoint = fixupMultiply10(estimated_power, is_even, numerator, denominator, delta_minus, delta_plus);
    switch (mode) {
      case SHORTEST:
        generateShortestDigits(numerator, denominator, delta_minus, delta_plus, is_even, buffer);
        return;
      case FIXED:
        bignumToFixed(requested_digits, numerator, denominator, buffer);
        return;
      case PRECISION:
        generateCountedDigits(requested_digits, numerator, denominator, buffer);
        return;
    } 
    throw new RuntimeException();
  }
  
  static void generateShortestDigits(Bignum numerator, Bignum denominator, Bignum delta_minus, Bignum delta_plus, boolean is_even, DtoaBuffer buffer) {
    boolean in_delta_room_minus, in_delta_room_plus;
    if (Bignum.equal(delta_minus, delta_plus))
      delta_plus = delta_minus; 
    while (true) {
      char digit = numerator.divideModuloIntBignum(denominator);
      assert digit <= '\t';
      buffer.append((char)(digit + 48));
      if (is_even) {
        in_delta_room_minus = Bignum.lessEqual(numerator, delta_minus);
      } else {
        in_delta_room_minus = Bignum.less(numerator, delta_minus);
      } 
      if (is_even) {
        in_delta_room_plus = (Bignum.plusCompare(numerator, delta_plus, denominator) >= 0);
      } else {
        in_delta_room_plus = (Bignum.plusCompare(numerator, delta_plus, denominator) > 0);
      } 
      if (!in_delta_room_minus && !in_delta_room_plus) {
        numerator.times10();
        delta_minus.times10();
        if (delta_minus != delta_plus)
          delta_plus.times10(); 
        continue;
      } 
      break;
    } 
    if (in_delta_room_minus && in_delta_room_plus) {
      int compare = Bignum.plusCompare(numerator, numerator, denominator);
      if (compare >= 0)
        if (compare > 0) {
          assert buffer.chars[buffer.length - 1] != '9';
          buffer.chars[buffer.length - 1] = (char)(buffer.chars[buffer.length - 1] + 1);
        } else if ((buffer.chars[buffer.length - 1] - 48) % 2 != 0) {
          assert buffer.chars[buffer.length - 1] != '9';
          buffer.chars[buffer.length - 1] = (char)(buffer.chars[buffer.length - 1] + 1);
        }  
      return;
    } 
    if (in_delta_room_minus)
      return; 
    assert buffer.chars[buffer.length - 1] != '9';
    buffer.chars[buffer.length - 1] = (char)(buffer.chars[buffer.length - 1] + 1);
  }
  
  static void generateCountedDigits(int count, Bignum numerator, Bignum denominator, DtoaBuffer buffer) {
    assert count >= 0;
    for (int i = 0; i < count - 1; i++) {
      char c = numerator.divideModuloIntBignum(denominator);
      assert c <= '\t';
      buffer.chars[i] = (char)(c + 48);
      numerator.times10();
    } 
    char digit = numerator.divideModuloIntBignum(denominator);
    if (Bignum.plusCompare(numerator, numerator, denominator) >= 0)
      digit = (char)(digit + 1); 
    assert digit <= '\n';
    buffer.chars[count - 1] = (char)(digit + 48);
    for (int j = count - 1; j > 0 && 
      buffer.chars[j] == ':'; j--) {
      buffer.chars[j] = '0';
      buffer.chars[j - 1] = (char)(buffer.chars[j - 1] + 1);
    } 
    if (buffer.chars[0] == ':') {
      buffer.chars[0] = '1';
      buffer.decimalPoint++;
    } 
    buffer.length = count;
  }
  
  static void bignumToFixed(int requested_digits, Bignum numerator, Bignum denominator, DtoaBuffer buffer) {
    if (-buffer.decimalPoint > requested_digits) {
      buffer.decimalPoint = -requested_digits;
      buffer.length = 0;
    } else if (-buffer.decimalPoint == requested_digits) {
      assert buffer.decimalPoint == -requested_digits;
      denominator.times10();
      if (Bignum.plusCompare(numerator, numerator, denominator) >= 0) {
        buffer.chars[0] = '1';
        buffer.length = 1;
        buffer.decimalPoint++;
      } else {
        buffer.length = 0;
      } 
    } else {
      int needed_digits = buffer.decimalPoint + requested_digits;
      generateCountedDigits(needed_digits, numerator, denominator, buffer);
    } 
  }
  
  static int estimatePower(int exponent) {
    double k1Log10 = 0.30102999566398114D;
    int kSignificandSize = 53;
    double estimate = Math.ceil((exponent + 53 - 1) * 0.30102999566398114D - 1.0E-10D);
    return (int)estimate;
  }
  
  static void initialScaledStartValuesPositiveExponent(long significand, int exponent, int estimated_power, boolean need_boundary_deltas, Bignum numerator, Bignum denominator, Bignum delta_minus, Bignum delta_plus) {
    assert estimated_power >= 0;
    numerator.assignUInt64(significand);
    numerator.shiftLeft(exponent);
    denominator.assignPowerUInt16(10, estimated_power);
    if (need_boundary_deltas) {
      denominator.shiftLeft(1);
      numerator.shiftLeft(1);
      delta_plus.assignUInt16('\001');
      delta_plus.shiftLeft(exponent);
      delta_minus.assignUInt16('\001');
      delta_minus.shiftLeft(exponent);
    } 
  }
  
  static void initialScaledStartValuesNegativeExponentPositivePower(long significand, int exponent, int estimated_power, boolean need_boundary_deltas, Bignum numerator, Bignum denominator, Bignum delta_minus, Bignum delta_plus) {
    numerator.assignUInt64(significand);
    denominator.assignPowerUInt16(10, estimated_power);
    denominator.shiftLeft(-exponent);
    if (need_boundary_deltas) {
      denominator.shiftLeft(1);
      numerator.shiftLeft(1);
      delta_plus.assignUInt16('\001');
      delta_minus.assignUInt16('\001');
    } 
  }
  
  static void initialScaledStartValuesNegativeExponentNegativePower(long significand, int exponent, int estimated_power, boolean need_boundary_deltas, Bignum numerator, Bignum denominator, Bignum delta_minus, Bignum delta_plus) {
    Bignum power_ten = numerator;
    power_ten.assignPowerUInt16(10, -estimated_power);
    if (need_boundary_deltas) {
      delta_plus.assignBignum(power_ten);
      delta_minus.assignBignum(power_ten);
    } 
    assert numerator == power_ten;
    numerator.multiplyByUInt64(significand);
    denominator.assignUInt16('\001');
    denominator.shiftLeft(-exponent);
    if (need_boundary_deltas) {
      numerator.shiftLeft(1);
      denominator.shiftLeft(1);
    } 
  }
  
  static void initialScaledStartValues(long significand, int exponent, boolean lower_boundary_is_closer, int estimated_power, boolean need_boundary_deltas, Bignum numerator, Bignum denominator, Bignum delta_minus, Bignum delta_plus) {
    if (exponent >= 0) {
      initialScaledStartValuesPositiveExponent(significand, exponent, estimated_power, need_boundary_deltas, numerator, denominator, delta_minus, delta_plus);
    } else if (estimated_power >= 0) {
      initialScaledStartValuesNegativeExponentPositivePower(significand, exponent, estimated_power, need_boundary_deltas, numerator, denominator, delta_minus, delta_plus);
    } else {
      initialScaledStartValuesNegativeExponentNegativePower(significand, exponent, estimated_power, need_boundary_deltas, numerator, denominator, delta_minus, delta_plus);
    } 
    if (need_boundary_deltas && lower_boundary_is_closer) {
      denominator.shiftLeft(1);
      numerator.shiftLeft(1);
      delta_plus.shiftLeft(1);
    } 
  }
  
  static int fixupMultiply10(int estimated_power, boolean is_even, Bignum numerator, Bignum denominator, Bignum delta_minus, Bignum delta_plus) {
    boolean in_range;
    int decimal_point;
    if (is_even) {
      in_range = (Bignum.plusCompare(numerator, delta_plus, denominator) >= 0);
    } else {
      in_range = (Bignum.plusCompare(numerator, delta_plus, denominator) > 0);
    } 
    if (in_range) {
      decimal_point = estimated_power + 1;
    } else {
      decimal_point = estimated_power;
      numerator.times10();
      if (Bignum.equal(delta_minus, delta_plus)) {
        delta_minus.times10();
        delta_plus.assignBignum(delta_minus);
      } else {
        delta_minus.times10();
        delta_plus.times10();
      } 
    } 
    return decimal_point;
  }
}
