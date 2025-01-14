package org.openjdk.nashorn.internal.runtime;

import java.util.Arrays;

public final class BitVector implements Cloneable {
  private static final int BITSPERSLOT = 64;
  
  private static final int SLOTSQUANTA = 4;
  
  private static final int BITSHIFT = 6;
  
  private static final int BITMASK = 63;
  
  private long[] bits;
  
  public BitVector() {
    this.bits = new long[4];
  }
  
  public BitVector(long length) {
    int need = (int)growthNeeded(length);
    this.bits = new long[need];
  }
  
  public BitVector(long[] bits) {
    this.bits = (long[])bits.clone();
  }
  
  public void copy(BitVector other) {
    this.bits = (long[])other.bits.clone();
  }
  
  private static long slotsNeeded(long length) {
    return length + 63L >> 6L;
  }
  
  private static long growthNeeded(long length) {
    return (slotsNeeded(length) + 4L - 1L) / 4L * 4L;
  }
  
  private long slot(int index) {
    return (0 <= index && index < this.bits.length) ? this.bits[index] : 0L;
  }
  
  public void resize(long length) {
    int need = (int)growthNeeded(length);
    if (this.bits.length != need)
      this.bits = Arrays.copyOf(this.bits, need); 
    int shift = (int)(length & 0x3FL);
    int slot = (int)(length >> 6L);
    if (shift != 0) {
      this.bits[slot] = this.bits[slot] & (1L << shift) - 1L;
      slot++;
    } 
    for (; slot < this.bits.length; slot++)
      this.bits[slot] = 0L; 
  }
  
  public void set(long bit) {
    this.bits[(int)(bit >> 6L)] = this.bits[(int)(bit >> 6L)] | 1L << (int)(bit & 0x3FL);
  }
  
  public void clear(long bit) {
    this.bits[(int)(bit >> 6L)] = this.bits[(int)(bit >> 6L)] & (1L << (int)(bit & 0x3FL) ^ 0xFFFFFFFFFFFFFFFFL);
  }
  
  public void toggle(long bit) {
    this.bits[(int)(bit >> 6L)] = this.bits[(int)(bit >> 6L)] ^ 1L << (int)(bit & 0x3FL);
  }
  
  public void setTo(long length) {
    if (0L < length) {
      int lastWord = (int)(length >> 6L);
      long lastBits = (1L << (int)(length & 0x3FL)) - 1L;
      Arrays.fill(this.bits, 0, lastWord, -1L);
      if (lastBits != 0L)
        this.bits[lastWord] = this.bits[lastWord] | lastBits; 
    } 
  }
  
  public void clearAll() {
    Arrays.fill(this.bits, 0L);
  }
  
  public boolean isSet(long bit) {
    return ((this.bits[(int)(bit >> 6L)] & 1L << (int)(bit & 0x3FL)) != 0L);
  }
  
  public boolean isClear(long bit) {
    return ((this.bits[(int)(bit >> 6L)] & 1L << (int)(bit & 0x3FL)) == 0L);
  }
  
  public void shiftLeft(long shift, long length) {
    if (shift != 0L) {
      int leftShift = (int)(shift & 0x3FL);
      int rightShift = 64 - leftShift;
      int slotShift = (int)(shift >> 6L);
      int slotCount = this.bits.length - slotShift;
      if (leftShift == 0) {
        for (int slot = 0, from = slotShift; slot < slotCount; slot++, from++)
          this.bits[slot] = slot(from); 
      } else {
        for (int slot = 0, from = slotShift; slot < slotCount; slot++)
          this.bits[slot] = slot(from) >>> leftShift | slot(++from) << rightShift; 
      } 
    } 
    resize(length);
  }
  
  public void shiftRight(long shift, long length) {
    resize(length);
    if (shift != 0L) {
      int rightShift = (int)(shift & 0x3FL);
      int leftShift = 64 - rightShift;
      int slotShift = (int)(shift >> 6L);
      if (leftShift == 0) {
        for (int slot = this.bits.length, from = slot - slotShift; slot >= slotShift; ) {
          slot--;
          from--;
          this.bits[slot] = slot(from);
        } 
      } else {
        for (int slot = this.bits.length, from = slot - slotShift; slot > 0; ) {
          slot--;
          from--;
          this.bits[slot] = slot(from - 1) >>> leftShift | slot(from) << rightShift;
        } 
      } 
    } 
    resize(length);
  }
  
  public void setRange(long fromIndex, long toIndex) {
    if (fromIndex < toIndex) {
      int firstWord = (int)(fromIndex >> 6L);
      int lastWord = (int)(toIndex - 1L >> 6L);
      long firstBits = -1L << (int)fromIndex;
      long lastBits = -1L >>> (int)-toIndex;
      if (firstWord == lastWord) {
        this.bits[firstWord] = this.bits[firstWord] | firstBits & lastBits;
      } else {
        this.bits[firstWord] = this.bits[firstWord] | firstBits;
        Arrays.fill(this.bits, firstWord + 1, lastWord, -1L);
        this.bits[lastWord] = this.bits[lastWord] | lastBits;
      } 
    } 
  }
}
