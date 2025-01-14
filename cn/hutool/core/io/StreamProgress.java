package cn.hutool.core.io;

public interface StreamProgress {
  void start();
  
  void progress(long paramLong1, long paramLong2);
  
  void finish();
}
