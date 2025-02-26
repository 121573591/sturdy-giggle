package cn.hutool.core.io;

public class FastByteBuffer {
  private byte[][] buffers = new byte[16][];
  
  private int buffersCount;
  
  private int currentBufferIndex = -1;
  
  private byte[] currentBuffer;
  
  private int offset;
  
  private int size;
  
  private final int minChunkLen;
  
  public FastByteBuffer() {
    this(1024);
  }
  
  public FastByteBuffer(int size) {
    if (size <= 0)
      size = 1024; 
    this.minChunkLen = Math.abs(size);
  }
  
  private void needNewBuffer(int newSize) {
    int delta = newSize - this.size;
    int newBufferSize = Math.max(this.minChunkLen, delta);
    this.currentBufferIndex++;
    this.currentBuffer = new byte[newBufferSize];
    this.offset = 0;
    if (this.currentBufferIndex >= this.buffers.length) {
      int newLen = this.buffers.length << 1;
      byte[][] newBuffers = new byte[newLen][];
      System.arraycopy(this.buffers, 0, newBuffers, 0, this.buffers.length);
      this.buffers = newBuffers;
    } 
    this.buffers[this.currentBufferIndex] = this.currentBuffer;
    this.buffersCount++;
  }
  
  public FastByteBuffer append(byte[] array, int off, int len) {
    int end = off + len;
    if (off < 0 || len < 0 || end > array.length)
      throw new IndexOutOfBoundsException(); 
    if (len == 0)
      return this; 
    int newSize = this.size + len;
    int remaining = len;
    if (this.currentBuffer != null) {
      int part = Math.min(remaining, this.currentBuffer.length - this.offset);
      System.arraycopy(array, end - remaining, this.currentBuffer, this.offset, part);
      remaining -= part;
      this.offset += part;
      this.size += part;
    } 
    if (remaining > 0) {
      needNewBuffer(newSize);
      int part = Math.min(remaining, this.currentBuffer.length - this.offset);
      System.arraycopy(array, end - remaining, this.currentBuffer, this.offset, part);
      this.offset += part;
      this.size += part;
    } 
    return this;
  }
  
  public FastByteBuffer append(byte[] array) {
    return append(array, 0, array.length);
  }
  
  public FastByteBuffer append(byte element) {
    if (this.currentBuffer == null || this.offset == this.currentBuffer.length)
      needNewBuffer(this.size + 1); 
    this.currentBuffer[this.offset] = element;
    this.offset++;
    this.size++;
    return this;
  }
  
  public FastByteBuffer append(FastByteBuffer buff) {
    if (buff.size == 0)
      return this; 
    for (int i = 0; i < buff.currentBufferIndex; i++)
      append(buff.buffers[i]); 
    append(buff.currentBuffer, 0, buff.offset);
    return this;
  }
  
  public int size() {
    return this.size;
  }
  
  public boolean isEmpty() {
    return (this.size == 0);
  }
  
  public int index() {
    return this.currentBufferIndex;
  }
  
  public int offset() {
    return this.offset;
  }
  
  public byte[] array(int index) {
    return this.buffers[index];
  }
  
  public void reset() {
    this.size = 0;
    this.offset = 0;
    this.currentBufferIndex = -1;
    this.currentBuffer = null;
    this.buffersCount = 0;
  }
  
  public byte[] toArray() {
    int pos = 0;
    byte[] array = new byte[this.size];
    if (this.currentBufferIndex == -1)
      return array; 
    for (int i = 0; i < this.currentBufferIndex; i++) {
      int len = (this.buffers[i]).length;
      System.arraycopy(this.buffers[i], 0, array, pos, len);
      pos += len;
    } 
    System.arraycopy(this.buffers[this.currentBufferIndex], 0, array, pos, this.offset);
    return array;
  }
  
  public byte[] toArray(int start, int len) {
    int remaining = len;
    int pos = 0;
    byte[] array = new byte[len];
    if (len == 0)
      return array; 
    int i = 0;
    while (start >= (this.buffers[i]).length) {
      start -= (this.buffers[i]).length;
      i++;
    } 
    while (i < this.buffersCount) {
      byte[] buf = this.buffers[i];
      int c = Math.min(buf.length - start, remaining);
      System.arraycopy(buf, start, array, pos, c);
      pos += c;
      remaining -= c;
      if (remaining == 0)
        break; 
      start = 0;
      i++;
    } 
    return array;
  }
  
  public byte get(int index) {
    if (index >= this.size || index < 0)
      throw new IndexOutOfBoundsException(); 
    int ndx = 0;
    while (true) {
      byte[] b = this.buffers[ndx];
      if (index < b.length)
        return b[index]; 
      ndx++;
      index -= b.length;
    } 
  }
}
