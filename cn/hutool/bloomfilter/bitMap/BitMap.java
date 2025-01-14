package cn.hutool.bloomfilter.bitMap;

public interface BitMap {
  public static final int MACHINE32 = 32;
  
  public static final int MACHINE64 = 64;
  
  void add(long paramLong);
  
  boolean contains(long paramLong);
  
  void remove(long paramLong);
}
