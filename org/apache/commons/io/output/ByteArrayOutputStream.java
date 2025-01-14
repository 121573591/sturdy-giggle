package org.apache.commons.io.output;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ByteArrayOutputStream extends AbstractByteArrayOutputStream {
  public ByteArrayOutputStream() {
    this(1024);
  }
  
  public ByteArrayOutputStream(int size) {
    if (size < 0)
      throw new IllegalArgumentException("Negative initial size: " + size); 
    synchronized (this) {
      needNewBuffer(size);
    } 
  }
  
  public void write(byte[] b, int off, int len) {
    if (off < 0 || off > b.length || len < 0 || off + len > b.length || off + len < 0)
      throw new IndexOutOfBoundsException(); 
    if (len == 0)
      return; 
    synchronized (this) {
      writeImpl(b, off, len);
    } 
  }
  
  public synchronized void write(int b) {
    writeImpl(b);
  }
  
  public synchronized int write(InputStream in) throws IOException {
    return writeImpl(in);
  }
  
  public synchronized int size() {
    return this.count;
  }
  
  public synchronized void reset() {
    resetImpl();
  }
  
  public synchronized void writeTo(OutputStream out) throws IOException {
    writeToImpl(out);
  }
  
  public static InputStream toBufferedInputStream(InputStream input) throws IOException {
    return toBufferedInputStream(input, 1024);
  }
  
  public static InputStream toBufferedInputStream(InputStream input, int size) throws IOException {
    try (ByteArrayOutputStream output = new ByteArrayOutputStream(size)) {
      output.write(input);
      return output.toInputStream();
    } 
  }
  
  public synchronized InputStream toInputStream() {
    return toInputStream(java.io.ByteArrayInputStream::new);
  }
  
  public synchronized byte[] toByteArray() {
    return toByteArrayImpl();
  }
}
