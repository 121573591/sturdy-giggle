package org.apache.commons.io.input.buffer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import org.apache.commons.io.IOUtils;

public class CircularBufferInputStream extends InputStream {
  protected final InputStream in;
  
  protected final CircularByteBuffer buffer;
  
  protected final int bufferSize;
  
  private boolean eof;
  
  public CircularBufferInputStream(InputStream inputStream, int bufferSize) {
    if (bufferSize <= 0)
      throw new IllegalArgumentException("Invalid bufferSize: " + bufferSize); 
    this.in = Objects.<InputStream>requireNonNull(inputStream, "inputStream");
    this.buffer = new CircularByteBuffer(bufferSize);
    this.bufferSize = bufferSize;
    this.eof = false;
  }
  
  public CircularBufferInputStream(InputStream inputStream) {
    this(inputStream, 8192);
  }
  
  protected void fillBuffer() throws IOException {
    if (this.eof)
      return; 
    int space = this.buffer.getSpace();
    byte[] buf = IOUtils.byteArray(space);
    while (space > 0) {
      int res = this.in.read(buf, 0, space);
      if (res == -1) {
        this.eof = true;
        return;
      } 
      if (res > 0) {
        this.buffer.add(buf, 0, res);
        space -= res;
      } 
    } 
  }
  
  protected boolean haveBytes(int count) throws IOException {
    if (this.buffer.getCurrentNumberOfBytes() < count)
      fillBuffer(); 
    return this.buffer.hasBytes();
  }
  
  public int read() throws IOException {
    if (!haveBytes(1))
      return -1; 
    return this.buffer.read() & 0xFF;
  }
  
  public int read(byte[] buffer) throws IOException {
    return read(buffer, 0, buffer.length);
  }
  
  public int read(byte[] targetBuffer, int offset, int length) throws IOException {
    Objects.requireNonNull(targetBuffer, "targetBuffer");
    if (offset < 0)
      throw new IllegalArgumentException("Offset must not be negative"); 
    if (length < 0)
      throw new IllegalArgumentException("Length must not be negative"); 
    if (!haveBytes(length))
      return -1; 
    int result = Math.min(length, this.buffer.getCurrentNumberOfBytes());
    for (int i = 0; i < result; i++)
      targetBuffer[offset + i] = this.buffer.read(); 
    return result;
  }
  
  public void close() throws IOException {
    this.in.close();
    this.eof = true;
    this.buffer.clear();
  }
}
