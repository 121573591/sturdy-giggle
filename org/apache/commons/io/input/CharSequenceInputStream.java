package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.Objects;

public class CharSequenceInputStream extends InputStream {
  private static final int BUFFER_SIZE = 2048;
  
  private static final int NO_MARK = -1;
  
  private final CharsetEncoder encoder;
  
  private final CharBuffer cbuf;
  
  private final ByteBuffer bbuf;
  
  private int mark_cbuf;
  
  private int mark_bbuf;
  
  public CharSequenceInputStream(CharSequence cs, Charset charset, int bufferSize) {
    this
      
      .encoder = charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
    float maxBytesPerChar = this.encoder.maxBytesPerChar();
    if (bufferSize < maxBytesPerChar)
      throw new IllegalArgumentException("Buffer size " + bufferSize + " is less than maxBytesPerChar " + maxBytesPerChar); 
    this.bbuf = ByteBuffer.allocate(bufferSize);
    this.bbuf.flip();
    this.cbuf = CharBuffer.wrap(cs);
    this.mark_cbuf = -1;
    this.mark_bbuf = -1;
  }
  
  public CharSequenceInputStream(CharSequence cs, String charset, int bufferSize) {
    this(cs, Charset.forName(charset), bufferSize);
  }
  
  public CharSequenceInputStream(CharSequence cs, Charset charset) {
    this(cs, charset, 2048);
  }
  
  public CharSequenceInputStream(CharSequence cs, String charset) {
    this(cs, charset, 2048);
  }
  
  private void fillBuffer() throws CharacterCodingException {
    this.bbuf.compact();
    CoderResult result = this.encoder.encode(this.cbuf, this.bbuf, true);
    if (result.isError())
      result.throwException(); 
    this.bbuf.flip();
  }
  
  public int read(byte[] array, int off, int len) throws IOException {
    Objects.requireNonNull(array, "array");
    if (len < 0 || off + len > array.length)
      throw new IndexOutOfBoundsException("Array Size=" + array.length + ", offset=" + off + ", length=" + len); 
    if (len == 0)
      return 0; 
    if (!this.bbuf.hasRemaining() && !this.cbuf.hasRemaining())
      return -1; 
    int bytesRead = 0;
    while (len > 0) {
      if (this.bbuf.hasRemaining()) {
        int chunk = Math.min(this.bbuf.remaining(), len);
        this.bbuf.get(array, off, chunk);
        off += chunk;
        len -= chunk;
        bytesRead += chunk;
        continue;
      } 
      fillBuffer();
      if (!this.bbuf.hasRemaining() && !this.cbuf.hasRemaining())
        break; 
    } 
    return (bytesRead == 0 && !this.cbuf.hasRemaining()) ? -1 : bytesRead;
  }
  
  public int read() throws IOException {
    while (true) {
      if (this.bbuf.hasRemaining())
        return this.bbuf.get() & 0xFF; 
      fillBuffer();
      if (!this.bbuf.hasRemaining() && !this.cbuf.hasRemaining())
        return -1; 
    } 
  }
  
  public int read(byte[] b) throws IOException {
    return read(b, 0, b.length);
  }
  
  public long skip(long n) throws IOException {
    long skipped = 0L;
    while (n > 0L && available() > 0) {
      read();
      n--;
      skipped++;
    } 
    return skipped;
  }
  
  public int available() throws IOException {
    return this.bbuf.remaining() + this.cbuf.remaining();
  }
  
  public void close() throws IOException {}
  
  public synchronized void mark(int readlimit) {
    this.mark_cbuf = this.cbuf.position();
    this.mark_bbuf = this.bbuf.position();
    this.cbuf.mark();
    this.bbuf.mark();
  }
  
  public synchronized void reset() throws IOException {
    if (this.mark_cbuf != -1) {
      if (this.cbuf.position() != 0) {
        this.encoder.reset();
        this.cbuf.rewind();
        this.bbuf.rewind();
        this.bbuf.limit(0);
        while (this.cbuf.position() < this.mark_cbuf) {
          this.bbuf.rewind();
          this.bbuf.limit(0);
          fillBuffer();
        } 
      } 
      if (this.cbuf.position() != this.mark_cbuf)
        throw new IllegalStateException("Unexpected CharBuffer position: actual=" + this.cbuf.position() + " expected=" + this.mark_cbuf); 
      this.bbuf.position(this.mark_bbuf);
      this.mark_cbuf = -1;
      this.mark_bbuf = -1;
    } 
  }
  
  public boolean markSupported() {
    return true;
  }
}
