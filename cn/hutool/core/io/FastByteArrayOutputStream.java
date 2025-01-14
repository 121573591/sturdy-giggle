package cn.hutool.core.io;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjectUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class FastByteArrayOutputStream extends OutputStream {
  private final FastByteBuffer buffer;
  
  public FastByteArrayOutputStream() {
    this(1024);
  }
  
  public FastByteArrayOutputStream(int size) {
    this.buffer = new FastByteBuffer(size);
  }
  
  public void write(byte[] b, int off, int len) {
    this.buffer.append(b, off, len);
  }
  
  public void write(int b) {
    this.buffer.append((byte)b);
  }
  
  public int size() {
    return this.buffer.size();
  }
  
  public void close() {}
  
  public void reset() {
    this.buffer.reset();
  }
  
  public void writeTo(OutputStream out) throws IORuntimeException {
    int index = this.buffer.index();
    if (index < 0)
      return; 
    try {
      for (int i = 0; i < index; i++) {
        byte[] buf = this.buffer.array(i);
        out.write(buf);
      } 
      out.write(this.buffer.array(index), 0, this.buffer.offset());
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public byte[] toByteArray() {
    return this.buffer.toArray();
  }
  
  public String toString() {
    return toString(CharsetUtil.defaultCharset());
  }
  
  public String toString(String charsetName) {
    return toString(CharsetUtil.charset(charsetName));
  }
  
  public String toString(Charset charset) {
    return new String(toByteArray(), 
        (Charset)ObjectUtil.defaultIfNull(charset, CharsetUtil.defaultCharset()));
  }
}
