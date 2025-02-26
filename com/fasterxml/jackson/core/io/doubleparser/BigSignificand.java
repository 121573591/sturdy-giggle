package com.fasterxml.jackson.core.io.doubleparser;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

class BigSignificand {
  private static final long LONG_MASK = 4294967295L;
  
  private final int numInts;
  
  private final int[] x;
  
  private int firstNonZeroInt;
  
  public BigSignificand(long numBits) {
    if (numBits <= 0L || numBits >= 2147483647L)
      throw new IllegalArgumentException("numBits=" + numBits); 
    int numLongs = (int)(numBits + 63L >>> 6L) + 1;
    this.numInts = numLongs << 1;
    this.x = new int[this.numInts];
    this.firstNonZeroInt = this.numInts;
  }
  
  public void add(int value) {
    if (value == 0)
      return; 
    long carry = value & 0xFFFFFFFFL;
    int i = this.numInts - 1;
    for (; carry != 0L; i--) {
      long sum = (x(i) & 0xFFFFFFFFL) + carry;
      x(i, (int)sum);
      carry = sum >>> 32L;
    } 
    this.firstNonZeroInt = Math.min(this.firstNonZeroInt, i + 1);
  }
  
  public void fma(int factor, int addend) {
    long factorL = factor & 0xFFFFFFFFL;
    long carry = addend;
    int i = this.numInts - 1;
    for (; i >= this.firstNonZeroInt; i--) {
      long product = factorL * (x(i) & 0xFFFFFFFFL) + carry;
      x(i, (int)product);
      carry = product >>> 32L;
    } 
    if (carry != 0L) {
      x(i, (int)carry);
      this.firstNonZeroInt = i;
    } 
  }
  
  public BigInteger toBigInteger() {
    byte[] bytes = new byte[this.x.length << 2];
    IntBuffer buf = ByteBuffer.wrap(bytes).asIntBuffer();
    for (int i = 0; i < this.x.length; i++)
      buf.put(i, this.x[i]); 
    return new BigInteger(bytes);
  }
  
  private void x(int i, int value) {
    this.x[i] = value;
  }
  
  private int x(int i) {
    return this.x[i];
  }
}
