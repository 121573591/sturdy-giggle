package cn.hutool.core.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LimitedInputStream extends FilterInputStream {
  private final long maxSize;
  
  private long currentPos;
  
  public LimitedInputStream(InputStream in, long maxSize) {
    super(in);
    this.maxSize = maxSize;
  }
  
  public int read() throws IOException {
    int data = super.read();
    if (data != -1) {
      this.currentPos++;
      checkPos();
    } 
    return data;
  }
  
  public int read(byte[] b, int off, int len) throws IOException {
    int count = super.read(b, off, len);
    if (count > 0) {
      this.currentPos += count;
      checkPos();
    } 
    return count;
  }
  
  public long skip(long n) throws IOException {
    long skipped = super.skip(n);
    if (skipped != 0L) {
      this.currentPos += skipped;
      checkPos();
    } 
    return skipped;
  }
  
  private void checkPos() {
    if (this.currentPos > this.maxSize)
      throw new IllegalStateException("Read limit exceeded"); 
  }
}
