package org.apache.commons.io.input;

import java.io.InputStream;

public class CloseShieldInputStream extends ProxyInputStream {
  public static CloseShieldInputStream wrap(InputStream inputStream) {
    return new CloseShieldInputStream(inputStream);
  }
  
  @Deprecated
  public CloseShieldInputStream(InputStream inputStream) {
    super(inputStream);
  }
  
  public void close() {
    this.in = ClosedInputStream.CLOSED_INPUT_STREAM;
  }
}
