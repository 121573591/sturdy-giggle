package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

public class DemuxInputStream extends InputStream {
  private final InheritableThreadLocal<InputStream> inputStreamLocal = new InheritableThreadLocal<>();
  
  public InputStream bindStream(InputStream input) {
    InputStream oldValue = this.inputStreamLocal.get();
    this.inputStreamLocal.set(input);
    return oldValue;
  }
  
  public void close() throws IOException {
    IOUtils.close(this.inputStreamLocal.get());
  }
  
  public int read() throws IOException {
    InputStream inputStream = this.inputStreamLocal.get();
    if (null != inputStream)
      return inputStream.read(); 
    return -1;
  }
}
