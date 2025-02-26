package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.util.ReferenceCounted;
import io.netty.util.internal.ObjectUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public abstract class AbstractMemoryHttpData extends AbstractHttpData {
  private ByteBuf byteBuf;
  
  private int chunkPosition;
  
  protected AbstractMemoryHttpData(String name, Charset charset, long size) {
    super(name, charset, size);
    this.byteBuf = Unpooled.EMPTY_BUFFER;
  }
  
  public void setContent(ByteBuf buffer) throws IOException {
    ObjectUtil.checkNotNull(buffer, "buffer");
    long localsize = buffer.readableBytes();
    try {
      checkSize(localsize);
    } catch (IOException e) {
      buffer.release();
      throw e;
    } 
    if (this.definedSize > 0L && this.definedSize < localsize) {
      buffer.release();
      throw new IOException("Out of size: " + localsize + " > " + this.definedSize);
    } 
    if (this.byteBuf != null)
      this.byteBuf.release(); 
    this.byteBuf = buffer;
    this.size = localsize;
    setCompleted();
  }
  
  public void setContent(InputStream inputStream) throws IOException {
    ObjectUtil.checkNotNull(inputStream, "inputStream");
    byte[] bytes = new byte[16384];
    ByteBuf buffer = Unpooled.buffer();
    int written = 0;
    try {
      int read = inputStream.read(bytes);
      while (read > 0) {
        buffer.writeBytes(bytes, 0, read);
        written += read;
        checkSize(written);
        read = inputStream.read(bytes);
      } 
    } catch (IOException e) {
      buffer.release();
      throw e;
    } 
    this.size = written;
    if (this.definedSize > 0L && this.definedSize < this.size) {
      buffer.release();
      throw new IOException("Out of size: " + this.size + " > " + this.definedSize);
    } 
    if (this.byteBuf != null)
      this.byteBuf.release(); 
    this.byteBuf = buffer;
    setCompleted();
  }
  
  public void addContent(ByteBuf buffer, boolean last) throws IOException {
    if (buffer != null) {
      long localsize = buffer.readableBytes();
      try {
        checkSize(this.size + localsize);
      } catch (IOException e) {
        buffer.release();
        throw e;
      } 
      if (this.definedSize > 0L && this.definedSize < this.size + localsize) {
        buffer.release();
        throw new IOException("Out of size: " + (this.size + localsize) + " > " + this.definedSize);
      } 
      this.size += localsize;
      if (this.byteBuf == null) {
        this.byteBuf = buffer;
      } else if (localsize == 0L) {
        buffer.release();
      } else if (this.byteBuf.readableBytes() == 0) {
        this.byteBuf.release();
        this.byteBuf = buffer;
      } else if (this.byteBuf instanceof CompositeByteBuf) {
        CompositeByteBuf cbb = (CompositeByteBuf)this.byteBuf;
        cbb.addComponent(true, buffer);
      } else {
        CompositeByteBuf cbb = Unpooled.compositeBuffer(2147483647);
        cbb.addComponents(true, new ByteBuf[] { this.byteBuf, buffer });
        this.byteBuf = (ByteBuf)cbb;
      } 
    } 
    if (last) {
      setCompleted();
    } else {
      ObjectUtil.checkNotNull(buffer, "buffer");
    } 
  }
  
  public void setContent(File file) throws IOException {
    ByteBuffer byteBuffer;
    ObjectUtil.checkNotNull(file, "file");
    long newsize = file.length();
    if (newsize > 2147483647L)
      throw new IllegalArgumentException("File too big to be loaded in memory"); 
    checkSize(newsize);
    RandomAccessFile accessFile = new RandomAccessFile(file, "r");
    try {
      FileChannel fileChannel = accessFile.getChannel();
      try {
        byte[] array = new byte[(int)newsize];
        byteBuffer = ByteBuffer.wrap(array);
        int read = 0;
        while (read < newsize)
          read += fileChannel.read(byteBuffer); 
      } finally {
        fileChannel.close();
      } 
    } finally {
      accessFile.close();
    } 
    byteBuffer.flip();
    if (this.byteBuf != null)
      this.byteBuf.release(); 
    this.byteBuf = Unpooled.wrappedBuffer(2147483647, new ByteBuffer[] { byteBuffer });
    this.size = newsize;
    setCompleted();
  }
  
  public void delete() {
    if (this.byteBuf != null) {
      this.byteBuf.release();
      this.byteBuf = null;
    } 
  }
  
  public byte[] get() {
    if (this.byteBuf == null)
      return Unpooled.EMPTY_BUFFER.array(); 
    byte[] array = new byte[this.byteBuf.readableBytes()];
    this.byteBuf.getBytes(this.byteBuf.readerIndex(), array);
    return array;
  }
  
  public String getString() {
    return getString(HttpConstants.DEFAULT_CHARSET);
  }
  
  public String getString(Charset encoding) {
    if (this.byteBuf == null)
      return ""; 
    if (encoding == null)
      encoding = HttpConstants.DEFAULT_CHARSET; 
    return this.byteBuf.toString(encoding);
  }
  
  public ByteBuf getByteBuf() {
    return this.byteBuf;
  }
  
  public ByteBuf getChunk(int length) throws IOException {
    if (this.byteBuf == null || length == 0 || this.byteBuf.readableBytes() == 0) {
      this.chunkPosition = 0;
      return Unpooled.EMPTY_BUFFER;
    } 
    int sizeLeft = this.byteBuf.readableBytes() - this.chunkPosition;
    if (sizeLeft == 0) {
      this.chunkPosition = 0;
      return Unpooled.EMPTY_BUFFER;
    } 
    int sliceLength = length;
    if (sizeLeft < length)
      sliceLength = sizeLeft; 
    ByteBuf chunk = this.byteBuf.retainedSlice(this.chunkPosition, sliceLength);
    this.chunkPosition += sliceLength;
    return chunk;
  }
  
  public boolean isInMemory() {
    return true;
  }
  
  public boolean renameTo(File dest) throws IOException {
    ObjectUtil.checkNotNull(dest, "dest");
    if (this.byteBuf == null) {
      if (!dest.createNewFile())
        throw new IOException("file exists already: " + dest); 
      return true;
    } 
    int length = this.byteBuf.readableBytes();
    long written = 0L;
    RandomAccessFile accessFile = new RandomAccessFile(dest, "rw");
    try {
      FileChannel fileChannel = accessFile.getChannel();
      try {
        if (this.byteBuf.nioBufferCount() == 1) {
          ByteBuffer byteBuffer = this.byteBuf.nioBuffer();
          while (written < length)
            written += fileChannel.write(byteBuffer); 
        } else {
          ByteBuffer[] byteBuffers = this.byteBuf.nioBuffers();
          while (written < length)
            written += fileChannel.write(byteBuffers); 
        } 
        fileChannel.force(false);
      } finally {
        fileChannel.close();
      } 
    } finally {
      accessFile.close();
    } 
    return (written == length);
  }
  
  public File getFile() throws IOException {
    throw new IOException("Not represented by a file");
  }
  
  public HttpData touch() {
    return touch((Object)null);
  }
  
  public HttpData touch(Object hint) {
    if (this.byteBuf != null)
      this.byteBuf.touch(hint); 
    return this;
  }
}
