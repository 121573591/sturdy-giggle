package org.apache.commons.io.output;

import java.io.IOException;

public class NullAppendable implements Appendable {
  public static final NullAppendable INSTANCE = new NullAppendable();
  
  public Appendable append(char c) throws IOException {
    return this;
  }
  
  public Appendable append(CharSequence csq) throws IOException {
    return this;
  }
  
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    return this;
  }
}
