package cn.hutool.bloomfilter.bitMap;

import java.io.Serializable;

public class LongMap implements BitMap, Serializable {
  private static final long serialVersionUID = 1L;
  
  private final long[] longs;
  
  public LongMap() {
    this.longs = new long[93750000];
  }
  
  public LongMap(int size) {
    this.longs = new long[size];
  }
  
  public void add(long i) {
    int r = (int)(i / 64L);
    long c = i & 0x3FL;
    this.longs[r] = this.longs[r] | 1L << (int)c;
  }
  
  public boolean contains(long i) {
    int r = (int)(i / 64L);
    long c = i & 0x3FL;
    return ((this.longs[r] >>> (int)c & 0x1L) == 1L);
  }
  
  public void remove(long i) {
    int r = (int)(i / 64L);
    long c = i & 0x3FL;
    this.longs[r] = this.longs[r] & (1L << (int)c ^ 0xFFFFFFFFFFFFFFFFL);
  }
}
