package com.fasterxml.jackson.core.io.doubleparser;

import java.math.BigInteger;
import java.util.Map;

class ParseDigitsTaskCharArray {
  static BigInteger parseDigitsIterative(char[] str, int from, int to) {
    int i;
    assert str != null : "str==null";
    int numDigits = to - from;
    BigSignificand bigSignificand = new BigSignificand(FastIntegerMath.estimateNumBits(numDigits));
    int preroll = from + (numDigits & 0x7);
    int value = FastDoubleSwar.tryToParseUpTo7Digits(str, from, preroll);
    boolean success = (value >= 0);
    bigSignificand.add(value);
    for (from = preroll; from < to; from += 8) {
      int addend = FastDoubleSwar.tryToParseEightDigits(str, from);
      i = success & ((addend >= 0) ? 1 : 0);
      bigSignificand.fma(100000000, addend);
    } 
    if (i == 0)
      throw new NumberFormatException("illegal syntax"); 
    return bigSignificand.toBigInteger();
  }
  
  static BigInteger parseDigitsRecursive(char[] str, int from, int to, Map<Integer, BigInteger> powersOfTen, int recursionThreshold) {
    assert str != null : "str==null";
    assert powersOfTen != null : "powersOfTen==null";
    int numDigits = to - from;
    if (numDigits <= recursionThreshold)
      return parseDigitsIterative(str, from, to); 
    int mid = FastIntegerMath.splitFloor16(from, to);
    BigInteger high = parseDigitsRecursive(str, from, mid, powersOfTen, recursionThreshold);
    BigInteger low = parseDigitsRecursive(str, mid, to, powersOfTen, recursionThreshold);
    high = FftMultiplier.multiply(high, powersOfTen.get(Integer.valueOf(to - mid)));
    return low.add(high);
  }
}
