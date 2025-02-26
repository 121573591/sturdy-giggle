package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.InvalidMarkException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

final class NioByteString extends ByteString.LeafByteString {
  private final ByteBuffer buffer;
  
  NioByteString(ByteBuffer buffer) {
    Internal.checkNotNull(buffer, "buffer");
    this.buffer = buffer.slice().order(ByteOrder.nativeOrder());
  }
  
  private Object writeReplace() {
    return ByteString.copyFrom(this.buffer.slice());
  }
  
  private void readObject(ObjectInputStream in) throws IOException {
    throw new InvalidObjectException("NioByteString instances are not to be serialized directly");
  }
  
  public byte byteAt(int index) {
    try {
      return this.buffer.get(index);
    } catch (ArrayIndexOutOfBoundsException e) {
      throw e;
    } catch (IndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException(e.getMessage());
    } 
  }
  
  public byte internalByteAt(int index) {
    return byteAt(index);
  }
  
  public int size() {
    return this.buffer.remaining();
  }
  
  public ByteString substring(int beginIndex, int endIndex) {
    try {
      ByteBuffer slice = slice(beginIndex, endIndex);
      return new NioByteString(slice);
    } catch (ArrayIndexOutOfBoundsException e) {
      throw e;
    } catch (IndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException(e.getMessage());
    } 
  }
  
  protected void copyToInternal(byte[] target, int sourceOffset, int targetOffset, int numberToCopy) {
    ByteBuffer slice = this.buffer.slice();
    Java8Compatibility.position(slice, sourceOffset);
    slice.get(target, targetOffset, numberToCopy);
  }
  
  public void copyTo(ByteBuffer target) {
    target.put(this.buffer.slice());
  }
  
  public void writeTo(OutputStream out) throws IOException {
    out.write(toByteArray());
  }
  
  boolean equalsRange(ByteString other, int offset, int length) {
    return substring(0, length).equals(other.substring(offset, offset + length));
  }
  
  void writeToInternal(OutputStream out, int sourceOffset, int numberToWrite) throws IOException {
    if (this.buffer.hasArray()) {
      int bufferOffset = this.buffer.arrayOffset() + this.buffer.position() + sourceOffset;
      out.write(this.buffer.array(), bufferOffset, numberToWrite);
      return;
    } 
    ByteBufferWriter.write(slice(sourceOffset, sourceOffset + numberToWrite), out);
  }
  
  void writeTo(ByteOutput output) throws IOException {
    output.writeLazy(this.buffer.slice());
  }
  
  public ByteBuffer asReadOnlyByteBuffer() {
    return this.buffer.asReadOnlyBuffer();
  }
  
  public List<ByteBuffer> asReadOnlyByteBufferList() {
    return Collections.singletonList(asReadOnlyByteBuffer());
  }
  
  protected String toStringInternal(Charset charset) {
    byte[] bytes;
    int offset;
    int length;
    if (this.buffer.hasArray()) {
      bytes = this.buffer.array();
      offset = this.buffer.arrayOffset() + this.buffer.position();
      length = this.buffer.remaining();
    } else {
      bytes = toByteArray();
      offset = 0;
      length = bytes.length;
    } 
    return new String(bytes, offset, length, charset);
  }
  
  public boolean isValidUtf8() {
    return Utf8.isValidUtf8(this.buffer);
  }
  
  protected int partialIsValidUtf8(int state, int offset, int length) {
    return Utf8.partialIsValidUtf8(state, this.buffer, offset, offset + length);
  }
  
  public boolean equals(Object other) {
    if (other == this)
      return true; 
    if (!(other instanceof ByteString))
      return false; 
    ByteString otherString = (ByteString)other;
    if (size() != otherString.size())
      return false; 
    if (size() == 0)
      return true; 
    if (other instanceof NioByteString)
      return this.buffer.equals(((NioByteString)other).buffer); 
    if (other instanceof RopeByteString)
      return other.equals(this); 
    return this.buffer.equals(otherString.asReadOnlyByteBuffer());
  }
  
  protected int partialHash(int h, int offset, int length) {
    for (int i = offset; i < offset + length; i++)
      h = h * 31 + this.buffer.get(i); 
    return h;
  }
  
  public InputStream newInput() {
    return new InputStream() {
        private final ByteBuffer buf = NioByteString.this.buffer.slice();
        
        public void mark(int readlimit) {
          Java8Compatibility.mark(this.buf);
        }
        
        public boolean markSupported() {
          return true;
        }
        
        public void reset() throws IOException {
          try {
            Java8Compatibility.reset(this.buf);
          } catch (InvalidMarkException e) {
            throw new IOException(e);
          } 
        }
        
        public int available() throws IOException {
          return this.buf.remaining();
        }
        
        public int read() throws IOException {
          if (!this.buf.hasRemaining())
            return -1; 
          return this.buf.get() & 0xFF;
        }
        
        public int read(byte[] bytes, int off, int len) throws IOException {
          if (!this.buf.hasRemaining())
            return -1; 
          len = Math.min(len, this.buf.remaining());
          this.buf.get(bytes, off, len);
          return len;
        }
      };
  }
  
  public CodedInputStream newCodedInput() {
    return CodedInputStream.newInstance(this.buffer, true);
  }
  
  private ByteBuffer slice(int beginIndex, int endIndex) {
    if (beginIndex < this.buffer.position() || endIndex > this.buffer.limit() || beginIndex > endIndex)
      throw new IllegalArgumentException(
          String.format("Invalid indices [%d, %d]", new Object[] { Integer.valueOf(beginIndex), Integer.valueOf(endIndex) })); 
    ByteBuffer slice = this.buffer.slice();
    Java8Compatibility.position(slice, beginIndex - this.buffer.position());
    Java8Compatibility.limit(slice, endIndex - this.buffer.position());
    return slice;
  }
}
