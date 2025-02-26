package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;

public class MarkShieldInputStream extends ProxyInputStream {
  public MarkShieldInputStream(InputStream in) {
    super(in);
  }
  
  public void mark(int readlimit) {}
  
  public boolean markSupported() {
    return false;
  }
  
  public void reset() throws IOException {
    throw UnsupportedOperationExceptions.reset();
  }
}
