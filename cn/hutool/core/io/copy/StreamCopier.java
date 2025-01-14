package cn.hutool.core.io.copy;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.StreamProgress;
import cn.hutool.core.lang.Assert;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamCopier extends IoCopier<InputStream, OutputStream> {
  public StreamCopier() {
    this(8192);
  }
  
  public StreamCopier(int bufferSize) {
    this(bufferSize, -1L);
  }
  
  public StreamCopier(int bufferSize, long count) {
    this(bufferSize, count, (StreamProgress)null);
  }
  
  public StreamCopier(int bufferSize, long count, StreamProgress progress) {
    super(bufferSize, count, progress);
  }
  
  public long copy(InputStream source, OutputStream target) {
    long size;
    Assert.notNull(source, "InputStream is null !", new Object[0]);
    Assert.notNull(target, "OutputStream is null !", new Object[0]);
    StreamProgress progress = this.progress;
    if (null != progress)
      progress.start(); 
    try {
      size = doCopy(source, target, new byte[bufferSize(this.count)], progress);
      target.flush();
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    if (null != progress)
      progress.finish(); 
    return size;
  }
  
  private long doCopy(InputStream source, OutputStream target, byte[] buffer, StreamProgress progress) throws IOException {
    long numToRead = (this.count > 0L) ? this.count : Long.MAX_VALUE;
    long total = 0L;
    while (numToRead > 0L) {
      int read = source.read(buffer, 0, bufferSize(numToRead));
      if (read < 0)
        break; 
      target.write(buffer, 0, read);
      if (this.flushEveryBuffer)
        target.flush(); 
      numToRead -= read;
      total += read;
      if (null != progress)
        progress.progress(this.count, total); 
    } 
    return total;
  }
}
