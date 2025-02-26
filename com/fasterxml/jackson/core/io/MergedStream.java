package com.fasterxml.jackson.core.io;

import java.io.IOException;
import java.io.InputStream;

public final class MergedStream extends InputStream {
  private final IOContext _ctxt;
  
  private final InputStream _in;
  
  private byte[] _b;
  
  private int _ptr;
  
  private final int _end;
  
  public MergedStream(IOContext ctxt, InputStream in, byte[] buf, int start, int end) {
    this._ctxt = ctxt;
    this._in = in;
    this._b = buf;
    this._ptr = start;
    this._end = end;
  }
  
  public int available() throws IOException {
    if (this._b != null)
      return this._end - this._ptr; 
    return this._in.available();
  }
  
  public void close() throws IOException {
    _free();
    this._in.close();
  }
  
  public synchronized void mark(int readlimit) {
    if (this._b == null)
      this._in.mark(readlimit); 
  }
  
  public boolean markSupported() {
    return (this._b == null && this._in.markSupported());
  }
  
  public int read() throws IOException {
    if (this._b != null) {
      int c = this._b[this._ptr++] & 0xFF;
      if (this._ptr >= this._end)
        _free(); 
      return c;
    } 
    return this._in.read();
  }
  
  public int read(byte[] b) throws IOException {
    return read(b, 0, b.length);
  }
  
  public int read(byte[] b, int off, int len) throws IOException {
    if (this._b != null) {
      int avail = this._end - this._ptr;
      if (len > avail)
        len = avail; 
      System.arraycopy(this._b, this._ptr, b, off, len);
      this._ptr += len;
      if (this._ptr >= this._end)
        _free(); 
      return len;
    } 
    return this._in.read(b, off, len);
  }
  
  public synchronized void reset() throws IOException {
    if (this._b == null)
      this._in.reset(); 
  }
  
  public long skip(long n) throws IOException {
    long count = 0L;
    if (this._b != null) {
      int amount = this._end - this._ptr;
      if (amount > n) {
        this._ptr += (int)n;
        return n;
      } 
      _free();
      count += amount;
      n -= amount;
    } 
    if (n > 0L)
      count += this._in.skip(n); 
    return count;
  }
  
  private void _free() {
    byte[] buf = this._b;
    if (buf != null) {
      this._b = null;
      if (this._ctxt != null)
        this._ctxt.releaseReadIOBuffer(buf); 
    } 
  }
}
