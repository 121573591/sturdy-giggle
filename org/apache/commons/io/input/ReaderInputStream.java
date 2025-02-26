package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.Objects;

public class ReaderInputStream extends InputStream {
  private static final int DEFAULT_BUFFER_SIZE = 1024;
  
  private final Reader reader;
  
  private final CharsetEncoder encoder;
  
  private final CharBuffer encoderIn;
  
  private final ByteBuffer encoderOut;
  
  private CoderResult lastCoderResult;
  
  private boolean endOfInput;
  
  public ReaderInputStream(Reader reader, CharsetEncoder encoder) {
    this(reader, encoder, 1024);
  }
  
  public ReaderInputStream(Reader reader, CharsetEncoder encoder, int bufferSize) {
    this.reader = reader;
    this.encoder = encoder;
    this.encoderIn = CharBuffer.allocate(bufferSize);
    this.encoderIn.flip();
    this.encoderOut = ByteBuffer.allocate(128);
    this.encoderOut.flip();
  }
  
  public ReaderInputStream(Reader reader, Charset charset, int bufferSize) {
    this(reader, charset
        .newEncoder()
        .onMalformedInput(CodingErrorAction.REPLACE)
        .onUnmappableCharacter(CodingErrorAction.REPLACE), bufferSize);
  }
  
  public ReaderInputStream(Reader reader, Charset charset) {
    this(reader, charset, 1024);
  }
  
  public ReaderInputStream(Reader reader, String charsetName, int bufferSize) {
    this(reader, Charset.forName(charsetName), bufferSize);
  }
  
  public ReaderInputStream(Reader reader, String charsetName) {
    this(reader, charsetName, 1024);
  }
  
  @Deprecated
  public ReaderInputStream(Reader reader) {
    this(reader, Charset.defaultCharset());
  }
  
  private void fillBuffer() throws IOException {
    if (!this.endOfInput && (this.lastCoderResult == null || this.lastCoderResult.isUnderflow())) {
      this.encoderIn.compact();
      int position = this.encoderIn.position();
      int c = this.reader.read(this.encoderIn.array(), position, this.encoderIn.remaining());
      if (c == -1) {
        this.endOfInput = true;
      } else {
        this.encoderIn.position(position + c);
      } 
      this.encoderIn.flip();
    } 
    this.encoderOut.compact();
    this.lastCoderResult = this.encoder.encode(this.encoderIn, this.encoderOut, this.endOfInput);
    this.encoderOut.flip();
  }
  
  public int read(byte[] array, int off, int len) throws IOException {
    Objects.requireNonNull(array, "array");
    if (len < 0 || off < 0 || off + len > array.length)
      throw new IndexOutOfBoundsException("Array Size=" + array.length + ", offset=" + off + ", length=" + len); 
    int read = 0;
    if (len == 0)
      return 0; 
    while (len > 0) {
      if (this.encoderOut.hasRemaining()) {
        int c = Math.min(this.encoderOut.remaining(), len);
        this.encoderOut.get(array, off, c);
        off += c;
        len -= c;
        read += c;
        continue;
      } 
      fillBuffer();
      if (this.endOfInput && !this.encoderOut.hasRemaining())
        break; 
    } 
    return (read == 0 && this.endOfInput) ? -1 : read;
  }
  
  public int read(byte[] b) throws IOException {
    return read(b, 0, b.length);
  }
  
  public int read() throws IOException {
    while (true) {
      if (this.encoderOut.hasRemaining())
        return this.encoderOut.get() & 0xFF; 
      fillBuffer();
      if (this.endOfInput && !this.encoderOut.hasRemaining())
        return -1; 
    } 
  }
  
  public void close() throws IOException {
    this.reader.close();
  }
}
