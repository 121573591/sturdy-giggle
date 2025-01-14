package com.fasterxml.jackson.core.io.doubleparser;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

class FastIntegerMath {
  public static final BigInteger FIVE = BigInteger.valueOf(5L);
  
  static final BigInteger TEN_POW_16 = BigInteger.valueOf(10000000000000000L);
  
  static final BigInteger FIVE_POW_16 = BigInteger.valueOf(152587890625L);
  
  private static final BigInteger[] SMALL_POWERS_OF_TEN = new BigInteger[] { 
      BigInteger.ONE, BigInteger.TEN, 
      
      BigInteger.valueOf(100L), 
      BigInteger.valueOf(1000L), 
      BigInteger.valueOf(10000L), 
      BigInteger.valueOf(100000L), 
      BigInteger.valueOf(1000000L), 
      BigInteger.valueOf(10000000L), 
      BigInteger.valueOf(100000000L), 
      BigInteger.valueOf(1000000000L), 
      BigInteger.valueOf(10000000000L), 
      BigInteger.valueOf(100000000000L), 
      BigInteger.valueOf(1000000000000L), 
      BigInteger.valueOf(10000000000000L), 
      BigInteger.valueOf(100000000000000L), 
      BigInteger.valueOf(1000000000000000L) };
  
  static BigInteger computePowerOfTen(NavigableMap<Integer, BigInteger> powersOfTen, int n) {
    if (n < SMALL_POWERS_OF_TEN.length)
      return SMALL_POWERS_OF_TEN[n]; 
    if (powersOfTen != null) {
      Map.Entry<Integer, BigInteger> floorEntry = powersOfTen.floorEntry(Integer.valueOf(n));
      Integer floorN = floorEntry.getKey();
      if (floorN.intValue() == n)
        return floorEntry.getValue(); 
      return FftMultiplier.multiply(floorEntry.getValue(), computePowerOfTen(powersOfTen, n - floorN.intValue()));
    } 
    return FIVE.pow(n).shiftLeft(n);
  }
  
  static BigInteger computeTenRaisedByNFloor16Recursive(NavigableMap<Integer, BigInteger> powersOfTen, int n) {
    n &= 0xFFFFFFF0;
    Map.Entry<Integer, BigInteger> floorEntry = powersOfTen.floorEntry(Integer.valueOf(n));
    int floorPower = ((Integer)floorEntry.getKey()).intValue();
    BigInteger floorValue = floorEntry.getValue();
    if (floorPower == n)
      return floorValue; 
    int diff = n - floorPower;
    BigInteger diffValue = powersOfTen.get(Integer.valueOf(diff));
    if (diffValue == null) {
      diffValue = computeTenRaisedByNFloor16Recursive(powersOfTen, diff);
      powersOfTen.put(Integer.valueOf(diff), diffValue);
    } 
    return FftMultiplier.multiply(floorValue, diffValue);
  }
  
  static NavigableMap<Integer, BigInteger> createPowersOfTenFloor16Map() {
    NavigableMap<Integer, BigInteger> powersOfTen = new TreeMap<>();
    powersOfTen.put(Integer.valueOf(0), BigInteger.ONE);
    powersOfTen.put(Integer.valueOf(16), TEN_POW_16);
    return powersOfTen;
  }
  
  public static long estimateNumBits(long numDecimalDigits) {
    return (numDecimalDigits * 3402L >>> 10L) + 1L;
  }
  
  static NavigableMap<Integer, BigInteger> fillPowersOf10Floor16(int from, int to) {
    NavigableMap<Integer, BigInteger> powers = new TreeMap<>();
    powers.put(Integer.valueOf(0), BigInteger.valueOf(5L));
    powers.put(Integer.valueOf(16), FIVE_POW_16);
    fillPowersOfNFloor16Recursive(powers, from, to);
    for (Iterator<Map.Entry<Integer, BigInteger>> iterator = powers.entrySet().iterator(); iterator.hasNext(); ) {
      Map.Entry<Integer, BigInteger> e = iterator.next();
      e.setValue(((BigInteger)e.getValue()).shiftLeft(((Integer)e.getKey()).intValue()));
    } 
    return powers;
  }
  
  static void fillPowersOfNFloor16Recursive(NavigableMap<Integer, BigInteger> powersOfTen, int from, int to) {
    int numDigits = to - from;
    if (numDigits <= 18)
      return; 
    int mid = splitFloor16(from, to);
    int n = to - mid;
    if (!powersOfTen.containsKey(Integer.valueOf(n))) {
      fillPowersOfNFloor16Recursive(powersOfTen, from, mid);
      fillPowersOfNFloor16Recursive(powersOfTen, mid, to);
      powersOfTen.put(Integer.valueOf(n), computeTenRaisedByNFloor16Recursive(powersOfTen, n));
    } 
  }
  
  static long unsignedMultiplyHigh(long x, long y) {
    long x0 = x & 0xFFFFFFFFL, x1 = x >>> 32L;
    long y0 = y & 0xFFFFFFFFL, y1 = y >>> 32L;
    long p11 = x1 * y1, p01 = x0 * y1;
    long p10 = x1 * y0, p00 = x0 * y0;
    long middle = p10 + (p00 >>> 32L) + (p01 & 0xFFFFFFFFL);
    return p11 + (middle >>> 32L) + (p01 >>> 32L);
  }
  
  static int splitFloor16(int from, int to) {
    int mid = from + to >>> 1;
    mid = to - (to - mid + 15 >> 4 << 4);
    return mid;
  }
}
