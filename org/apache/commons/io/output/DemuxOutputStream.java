package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;

public class DemuxOutputStream extends OutputStream {
  private final InheritableThreadLocal<OutputStream> outputStreamThreadLocal = new InheritableThreadLocal<>();
  
  public OutputStream bindStream(OutputStream output) {
    OutputStream stream = this.outputStreamThreadLocal.get();
    this.outputStreamThreadLocal.set(output);
    return stream;
  }
  
  public void close() throws IOException {
    IOUtils.close(this.outputStreamThreadLocal.get());
  }
  
  public void flush() throws IOException {
    OutputStream output = this.outputStreamThreadLocal.get();
    if (null != output)
      output.flush(); 
  }
  
  public void write(int ch) throws IOException {
    OutputStream output = this.outputStreamThreadLocal.get();
    if (null != output)
      output.write(ch); 
  }
}
