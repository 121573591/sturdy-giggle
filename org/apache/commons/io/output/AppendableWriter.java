package org.apache.commons.io.output;

import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

public class AppendableWriter<T extends Appendable> extends Writer {
  private final T appendable;
  
  public AppendableWriter(T appendable) {
    this.appendable = appendable;
  }
  
  public Writer append(char c) throws IOException {
    this.appendable.append(c);
    return this;
  }
  
  public Writer append(CharSequence csq) throws IOException {
    this.appendable.append(csq);
    return this;
  }
  
  public Writer append(CharSequence csq, int start, int end) throws IOException {
    this.appendable.append(csq, start, end);
    return this;
  }
  
  public void close() throws IOException {}
  
  public void flush() throws IOException {}
  
  public T getAppendable() {
    return this.appendable;
  }
  
  public void write(char[] cbuf, int off, int len) throws IOException {
    Objects.requireNonNull(cbuf, "Character array is missing");
    if (len < 0 || off + len > cbuf.length)
      throw new IndexOutOfBoundsException("Array Size=" + cbuf.length + ", offset=" + off + ", length=" + len); 
    for (int i = 0; i < len; i++)
      this.appendable.append(cbuf[off + i]); 
  }
  
  public void write(int c) throws IOException {
    this.appendable.append((char)c);
  }
  
  public void write(String str, int off, int len) throws IOException {
    Objects.requireNonNull(str, "String is missing");
    this.appendable.append(str, off, off + len);
  }
}
