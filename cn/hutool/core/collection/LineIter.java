package cn.hutool.core.collection;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Assert;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.nio.charset.Charset;

public class LineIter extends ComputeIter<String> implements IterableIter<String>, Closeable, Serializable {
  private static final long serialVersionUID = 1L;
  
  private final BufferedReader bufferedReader;
  
  public LineIter(InputStream in, Charset charset) throws IllegalArgumentException {
    this(IoUtil.getReader(in, charset));
  }
  
  public LineIter(Reader reader) throws IllegalArgumentException {
    Assert.notNull(reader, "Reader must not be null", new Object[0]);
    this.bufferedReader = IoUtil.getReader(reader);
  }
  
  protected String computeNext() {
    try {
      while (true) {
        String line = this.bufferedReader.readLine();
        if (line == null)
          return null; 
        if (isValidLine(line))
          return line; 
      } 
    } catch (IOException ioe) {
      close();
      throw new IORuntimeException(ioe);
    } 
  }
  
  public void close() {
    finish();
    IoUtil.close(this.bufferedReader);
  }
  
  protected boolean isValidLine(String line) {
    return true;
  }
}
