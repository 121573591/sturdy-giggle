package org.apache.commons.io.output;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class UnsynchronizedByteArrayOutputStream extends AbstractByteArrayOutputStream {
  public UnsynchronizedByteArrayOutputStream() {
    this(1024);
  }
  
  public UnsynchronizedByteArrayOutputStream(int size) {
    if (size < 0)
      throw new IllegalArgumentException("Negative initial size: " + size); 
    needNewBuffer(size);
  }
  
  public void write(byte[] b, int off, int len) {
    if (off < 0 || off > b.length || len < 0 || off + len > b.length || off + len < 0)
      throw new IndexOutOfBoundsException(String.format("offset=%,d, length=%,d", new Object[] { Integer.valueOf(off), Integer.valueOf(len) })); 
    if (len == 0)
      return; 
    writeImpl(b, off, len);
  }
  
  public void write(int b) {
    writeImpl(b);
  }
  
  public int write(InputStream in) throws IOException {
    return writeImpl(in);
  }
  
  public int size() {
    return this.count;
  }
  
  public void reset() {
    resetImpl();
  }
  
  public void writeTo(OutputStream out) throws IOException {
    writeToImpl(out);
  }
  
  public static InputStream toBufferedInputStream(InputStream input) throws IOException {
    return toBufferedInputStream(input, 1024);
  }
  
  public static InputStream toBufferedInputStream(InputStream input, int size) throws IOException {
    try (UnsynchronizedByteArrayOutputStream output = new UnsynchronizedByteArrayOutputStream(size)) {
      output.write(input);
      return output.toInputStream();
    } 
  }
  
  public InputStream toInputStream() {
    return toInputStream(org.apache.commons.io.input.UnsynchronizedByteArrayInputStream::new);
  }
  
  public byte[] toByteArray() {
    return toByteArrayImpl();
  }
}
