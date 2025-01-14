package org.openjdk.nashorn.internal.runtime.doubleconv;

class FastDtoa {
  static final int kFastDtoaMaximalLength = 17;
  
  static final int kMinimalTargetExponent = -60;
  
  static final int kMaximalTargetExponent = -32;
  
  static boolean roundWeed(DtoaBuffer buffer, long distance_too_high_w, long unsafe_interval, long rest, long ten_kappa, long unit) {
    long small_distance = distance_too_high_w - unit;
    long big_distance = distance_too_high_w + unit;
    assert Long.compareUnsigned(rest, unsafe_interval) <= 0;
    while (Long.compareUnsigned(rest, small_distance) < 0 && 
      Long.compareUnsigned(unsafe_interval - rest, ten_kappa) >= 0 && (
      Long.compareUnsigned(rest + ten_kappa, small_distance) < 0 || 
      Long.compareUnsigned(small_distance - rest, rest + ten_kappa - small_distance) >= 0)) {
      buffer.chars[buffer.length - 1] = (char)(buffer.chars[buffer.length - 1] - 1);
      rest += ten_kappa;
    } 
    if (Long.compareUnsigned(rest, big_distance) < 0 && 
      Long.compareUnsigned(unsafe_interval - rest, ten_kappa) >= 0 && (
      Long.compareUnsigned(rest + ten_kappa, big_distance) < 0 || 
      Long.compareUnsigned(big_distance - rest, rest + ten_kappa - big_distance) > 0))
      return false; 
    return (Long.compareUnsigned(2L * unit, rest) <= 0 && Long.compareUnsigned(rest, unsafe_interval - 4L * unit) <= 0);
  }
  
  static int roundWeedCounted(char[] buffer, int length, long rest, long ten_kappa, long unit) {
    assert Long.compareUnsigned(rest, ten_kappa) < 0;
    if (Long.compareUnsigned(unit, ten_kappa) >= 0)
      return 0; 
    if (Long.compareUnsigned(ten_kappa - unit, unit) <= 0)
      return 0; 
    if (Long.compareUnsigned(ten_kappa - rest, rest) > 0 && Long.compareUnsigned(ten_kappa - 2L * rest, 2L * unit) >= 0)
      return 1; 
    if (Long.compareUnsigned(rest, unit) > 0 && Long.compareUnsigned(ten_kappa - rest - unit, rest - unit) <= 0) {
      buffer[length - 1] = (char)(buffer[length - 1] + 1);
      for (int i = length - 1; i > 0 && 
        buffer[i] == ':'; i--) {
        buffer[i] = '0';
        buffer[i - 1] = (char)(buffer[i - 1] + 1);
      } 
      if (buffer[0] == ':') {
        buffer[0] = '1';
        return 2;
      } 
      return 1;
    } 
    return 0;
  }
  
  static final int[] kSmallPowersOfTen = new int[] { 
      0, 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 
      1000000000 };
  
  static long biggestPowerTen(int number, int number_bits) {
    assert (number & 0xFFFFFFFFL) < 1L << number_bits + 1;
    int exponent_plus_one_guess = (number_bits + 1) * 1233 >>> 12;
    exponent_plus_one_guess++;
    if (number < kSmallPowersOfTen[exponent_plus_one_guess])
      exponent_plus_one_guess--; 
    int power = kSmallPowersOfTen[exponent_plus_one_guess];
    int exponent_plus_one = exponent_plus_one_guess;
    return power << 32L | exponent_plus_one;
  }
  
  static boolean digitGen(DiyFp low, DiyFp w, DiyFp high, DtoaBuffer buffer, int mk) {
    assert low.e() == w.e() && w.e() == high.e();
    assert Long.compareUnsigned(low.f() + 1L, high.f() - 1L) <= 0;
    assert -60 <= w.e() && w.e() <= -32;
    long unit = 1L;
    DiyFp too_low = new DiyFp(low.f() - unit, low.e());
    DiyFp too_high = new DiyFp(high.f() + unit, high.e());
    DiyFp unsafe_interval = DiyFp.minus(too_high, too_low);
    DiyFp one = new DiyFp(1L << -w.e(), w.e());
    int integrals = (int)(too_high.f() >>> -one.e());
    long fractionals = too_high.f() & one.f() - 1L;
    long result = biggestPowerTen(integrals, 64 - -one.e());
    int divisor = (int)(result >>> 32L);
    int divisor_exponent_plus_one = (int)result;
    int kappa = divisor_exponent_plus_one;
    while (kappa > 0) {
      int digit = integrals / divisor;
      assert digit <= 9;
      buffer.append((char)(48 + digit));
      integrals %= divisor;
      kappa--;
      long rest = (integrals << -one.e()) + fractionals;
      if (Long.compareUnsigned(rest, unsafe_interval.f()) < 0) {
        buffer.decimalPoint = buffer.length - mk + kappa;
        return roundWeed(buffer, DiyFp.minus(too_high, w).f(), unsafe_interval
            .f(), rest, divisor << 
            -one.e(), unit);
      } 
      divisor /= 10;
    } 
    assert one.e() >= -60;
    assert fractionals < one.f();
    assert Long.compareUnsigned(Long.divideUnsigned(-1L, 10L), one.f()) >= 0;
    while (true) {
      fractionals *= 10L;
      unit *= 10L;
      unsafe_interval.setF(unsafe_interval.f() * 10L);
      int digit = (int)(fractionals >>> -one.e());
      assert digit <= 9;
      buffer.append((char)(48 + digit));
      fractionals &= one.f() - 1L;
      kappa--;
      if (Long.compareUnsigned(fractionals, unsafe_interval.f()) < 0) {
        buffer.decimalPoint = buffer.length - mk + kappa;
        return roundWeed(buffer, DiyFp.minus(too_high, w).f() * unit, unsafe_interval
            .f(), fractionals, one.f(), unit);
      } 
    } 
  }
  
  static boolean digitGenCounted(DiyFp w, int requested_digits, DtoaBuffer buffer, int mk) {
    assert -60 <= w.e() && w.e() <= -32;
    long w_error = 1L;
    DiyFp one = new DiyFp(1L << -w.e(), w.e());
    int integrals = (int)(w.f() >>> -one.e());
    long fractionals = w.f() & one.f() - 1L;
    long biggestPower = biggestPowerTen(integrals, 64 - -one.e());
    int divisor = (int)(biggestPower >>> 32L);
    int divisor_exponent_plus_one = (int)biggestPower;
    int kappa = divisor_exponent_plus_one;
    while (kappa > 0) {
      int digit = integrals / divisor;
      assert digit <= 9;
      buffer.append((char)(48 + digit));
      requested_digits--;
      integrals %= divisor;
      kappa--;
      if (requested_digits == 0)
        break; 
      divisor /= 10;
    } 
    if (requested_digits == 0) {
      long rest = (integrals << -one.e()) + fractionals;
      int i = roundWeedCounted(buffer.chars, buffer.length, rest, divisor << 
          -one.e(), w_error);
      buffer.decimalPoint = buffer.length - mk + kappa + ((i == 2) ? 1 : 0);
      return (i > 0);
    } 
    assert one.e() >= -60;
    assert fractionals < one.f();
    assert Long.compareUnsigned(Long.divideUnsigned(-1L, 10L), one.f()) >= 0;
    while (requested_digits > 0 && fractionals > w_error) {
      fractionals *= 10L;
      w_error *= 10L;
      int digit = (int)(fractionals >>> -one.e());
      assert digit <= 9;
      buffer.append((char)(48 + digit));
      requested_digits--;
      fractionals &= one.f() - 1L;
      kappa--;
    } 
    if (requested_digits != 0)
      return false; 
    int result = roundWeedCounted(buffer.chars, buffer.length, fractionals, one.f(), w_error);
    buffer.decimalPoint = buffer.length - mk + kappa + ((result == 2) ? 1 : 0);
    return (result > 0);
  }
  
  static boolean grisu3(double v, DtoaBuffer buffer) {
    long d64 = IeeeDouble.doubleToLong(v);
    DiyFp w = IeeeDouble.asNormalizedDiyFp(d64);
    DiyFp boundary_minus = new DiyFp(), boundary_plus = new DiyFp();
    IeeeDouble.normalizedBoundaries(d64, boundary_minus, boundary_plus);
    assert boundary_plus.e() == w.e();
    DiyFp ten_mk = new DiyFp();
    int ten_mk_minimal_binary_exponent = -60 - w.e() + 64;
    int ten_mk_maximal_binary_exponent = -32 - w.e() + 64;
    int mk = CachedPowers.getCachedPowerForBinaryExponentRange(ten_mk_minimal_binary_exponent, ten_mk_maximal_binary_exponent, ten_mk);
    assert -60 <= w.e() + ten_mk.e() + 64 && -32 >= w
      
      .e() + ten_mk.e() + 64;
    DiyFp scaled_w = DiyFp.times(w, ten_mk);
    assert scaled_w.e() == boundary_plus
      .e() + ten_mk.e() + 64;
    DiyFp scaled_boundary_minus = DiyFp.times(boundary_minus, ten_mk);
    DiyFp scaled_boundary_plus = DiyFp.times(boundary_plus, ten_mk);
    boolean result = digitGen(scaled_boundary_minus, scaled_w, scaled_boundary_plus, buffer, mk);
    return result;
  }
  
  static boolean grisu3Counted(double v, int requested_digits, DtoaBuffer buffer) {
    long d64 = IeeeDouble.doubleToLong(v);
    DiyFp w = IeeeDouble.asNormalizedDiyFp(d64);
    DiyFp ten_mk = new DiyFp();
    int ten_mk_minimal_binary_exponent = -60 - w.e() + 64;
    int ten_mk_maximal_binary_exponent = -32 - w.e() + 64;
    int mk = CachedPowers.getCachedPowerForBinaryExponentRange(ten_mk_minimal_binary_exponent, ten_mk_maximal_binary_exponent, ten_mk);
    assert -60 <= w.e() + ten_mk.e() + 64 && -32 >= w
      
      .e() + ten_mk.e() + 64;
    DiyFp scaled_w = DiyFp.times(w, ten_mk);
    boolean result = digitGenCounted(scaled_w, requested_digits, buffer, mk);
    return result;
  }
}
