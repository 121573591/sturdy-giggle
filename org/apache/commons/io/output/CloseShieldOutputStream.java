package org.apache.commons.io.output;

import java.io.OutputStream;

public class CloseShieldOutputStream extends ProxyOutputStream {
  public static CloseShieldOutputStream wrap(OutputStream outputStream) {
    return new CloseShieldOutputStream(outputStream);
  }
  
  @Deprecated
  public CloseShieldOutputStream(OutputStream outputStream) {
    super(outputStream);
  }
  
  public void close() {
    this.out = ClosedOutputStream.CLOSED_OUTPUT_STREAM;
  }
}
