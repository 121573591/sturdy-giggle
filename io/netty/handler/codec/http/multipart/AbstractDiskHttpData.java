package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.util.ReferenceCounted;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public abstract class AbstractDiskHttpData extends AbstractHttpData {
  private static final InternalLogger logger = InternalLoggerFactory.getInstance(AbstractDiskHttpData.class);
  
  private File file;
  
  private boolean isRenamed;
  
  private FileChannel fileChannel;
  
  protected AbstractDiskHttpData(String name, Charset charset, long size) {
    super(name, charset, size);
  }
  
  private File tempFile() throws IOException {
    String newpostfix;
    File tmpFile;
    String diskFilename = getDiskFilename();
    if (diskFilename != null) {
      newpostfix = '_' + Integer.toString(diskFilename.hashCode());
    } else {
      newpostfix = getPostfix();
    } 
    if (getBaseDirectory() == null) {
      tmpFile = PlatformDependent.createTempFile(getPrefix(), newpostfix, null);
    } else {
      tmpFile = PlatformDependent.createTempFile(getPrefix(), newpostfix, new File(
            getBaseDirectory()));
    } 
    if (deleteOnExit())
      DeleteFileOnExitHook.add(tmpFile.getPath()); 
    return tmpFile;
  }
  
  public void setContent(ByteBuf buffer) throws IOException {
    ObjectUtil.checkNotNull(buffer, "buffer");
    try {
      this.size = buffer.readableBytes();
      checkSize(this.size);
      if (this.definedSize > 0L && this.definedSize < this.size)
        throw new IOException("Out of size: " + this.size + " > " + this.definedSize); 
      if (this.file == null)
        this.file = tempFile(); 
      if (buffer.readableBytes() == 0) {
        if (!this.file.createNewFile()) {
          if (this.file.length() == 0L)
            return; 
          if (!this.file.delete() || !this.file.createNewFile())
            throw new IOException("file exists already: " + this.file); 
        } 
        return;
      } 
      RandomAccessFile accessFile = new RandomAccessFile(this.file, "rw");
      try {
        accessFile.setLength(0L);
        FileChannel localfileChannel = accessFile.getChannel();
        ByteBuffer byteBuffer = buffer.nioBuffer();
        int written = 0;
        while (written < this.size)
          written += localfileChannel.write(byteBuffer); 
        buffer.readerIndex(buffer.readerIndex() + written);
        localfileChannel.force(false);
      } finally {
        accessFile.close();
      } 
      setCompleted();
    } finally {
      buffer.release();
    } 
  }
  
  public void addContent(ByteBuf buffer, boolean last) throws IOException {
    if (buffer != null)
      try {
        int localsize = buffer.readableBytes();
        checkSize(this.size + localsize);
        if (this.definedSize > 0L && this.definedSize < this.size + localsize)
          throw new IOException("Out of size: " + (this.size + localsize) + " > " + this.definedSize); 
        if (this.file == null)
          this.file = tempFile(); 
        if (this.fileChannel == null) {
          RandomAccessFile accessFile = new RandomAccessFile(this.file, "rw");
          this.fileChannel = accessFile.getChannel();
        } 
        int remaining = localsize;
        long position = this.fileChannel.position();
        int index = buffer.readerIndex();
        while (remaining > 0) {
          int written = buffer.getBytes(index, this.fileChannel, position, remaining);
          if (written < 0)
            break; 
          remaining -= written;
          position += written;
          index += written;
        } 
        this.fileChannel.position(position);
        buffer.readerIndex(index);
        this.size += (localsize - remaining);
      } finally {
        buffer.release();
      }  
    if (last) {
      if (this.file == null)
        this.file = tempFile(); 
      if (this.fileChannel == null) {
        RandomAccessFile accessFile = new RandomAccessFile(this.file, "rw");
        this.fileChannel = accessFile.getChannel();
      } 
      try {
        this.fileChannel.force(false);
      } finally {
        this.fileChannel.close();
      } 
      this.fileChannel = null;
      setCompleted();
    } else {
      ObjectUtil.checkNotNull(buffer, "buffer");
    } 
  }
  
  public void setContent(File file) throws IOException {
    long size = file.length();
    checkSize(size);
    this.size = size;
    if (this.file != null)
      delete(); 
    this.file = file;
    this.isRenamed = true;
    setCompleted();
  }
  
  public void setContent(InputStream inputStream) throws IOException {
    ObjectUtil.checkNotNull(inputStream, "inputStream");
    if (this.file != null)
      delete(); 
    this.file = tempFile();
    RandomAccessFile accessFile = new RandomAccessFile(this.file, "rw");
    int written = 0;
    try {
      accessFile.setLength(0L);
      FileChannel localfileChannel = accessFile.getChannel();
      byte[] bytes = new byte[16384];
      ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
      int read = inputStream.read(bytes);
      while (read > 0) {
        byteBuffer.position(read).flip();
        written += localfileChannel.write(byteBuffer);
        checkSize(written);
        byteBuffer.clear();
        read = inputStream.read(bytes);
      } 
      localfileChannel.force(false);
    } finally {
      accessFile.close();
    } 
    this.size = written;
    if (this.definedSize > 0L && this.definedSize < this.size) {
      if (!this.file.delete())
        logger.warn("Failed to delete: {}", this.file); 
      this.file = null;
      throw new IOException("Out of size: " + this.size + " > " + this.definedSize);
    } 
    this.isRenamed = true;
    setCompleted();
  }
  
  public void delete() {
    if (this.fileChannel != null) {
      try {
        this.fileChannel.force(false);
      } catch (IOException e) {
        logger.warn("Failed to force.", e);
      } finally {
        try {
          this.fileChannel.close();
        } catch (IOException e) {
          logger.warn("Failed to close a file.", e);
        } 
      } 
      this.fileChannel = null;
    } 
    if (!this.isRenamed) {
      String filePath = null;
      if (this.file != null && this.file.exists()) {
        filePath = this.file.getPath();
        if (!this.file.delete()) {
          filePath = null;
          logger.warn("Failed to delete: {}", this.file);
        } 
      } 
      if (deleteOnExit() && filePath != null)
        DeleteFileOnExitHook.remove(filePath); 
      this.file = null;
    } 
  }
  
  public byte[] get() throws IOException {
    if (this.file == null)
      return EmptyArrays.EMPTY_BYTES; 
    return readFrom(this.file);
  }
  
  public ByteBuf getByteBuf() throws IOException {
    if (this.file == null)
      return Unpooled.EMPTY_BUFFER; 
    byte[] array = readFrom(this.file);
    return Unpooled.wrappedBuffer(array);
  }
  
  public ByteBuf getChunk(int length) throws IOException {
    if (this.file == null || length == 0)
      return Unpooled.EMPTY_BUFFER; 
    if (this.fileChannel == null) {
      RandomAccessFile accessFile = new RandomAccessFile(this.file, "r");
      this.fileChannel = accessFile.getChannel();
    } 
    int read = 0;
    ByteBuffer byteBuffer = ByteBuffer.allocate(length);
    try {
      while (read < length) {
        int readnow = this.fileChannel.read(byteBuffer);
        if (readnow == -1) {
          this.fileChannel.close();
          this.fileChannel = null;
          break;
        } 
        read += readnow;
      } 
    } catch (IOException e) {
      this.fileChannel.close();
      this.fileChannel = null;
      throw e;
    } 
    if (read == 0)
      return Unpooled.EMPTY_BUFFER; 
    byteBuffer.flip();
    ByteBuf buffer = Unpooled.wrappedBuffer(byteBuffer);
    buffer.readerIndex(0);
    buffer.writerIndex(read);
    return buffer;
  }
  
  public String getString() throws IOException {
    return getString(HttpConstants.DEFAULT_CHARSET);
  }
  
  public String getString(Charset encoding) throws IOException {
    if (this.file == null)
      return ""; 
    if (encoding == null) {
      byte[] arrayOfByte = readFrom(this.file);
      return new String(arrayOfByte, HttpConstants.DEFAULT_CHARSET.name());
    } 
    byte[] array = readFrom(this.file);
    return new String(array, encoding.name());
  }
  
  public boolean isInMemory() {
    return false;
  }
  
  public boolean renameTo(File dest) throws IOException {
    ObjectUtil.checkNotNull(dest, "dest");
    if (this.file == null)
      throw new IOException("No file defined so cannot be renamed"); 
    if (!this.file.renameTo(dest)) {
      IOException exception = null;
      RandomAccessFile inputAccessFile = null;
      RandomAccessFile outputAccessFile = null;
      long chunkSize = 8196L;
      long position = 0L;
      try {
        inputAccessFile = new RandomAccessFile(this.file, "r");
        outputAccessFile = new RandomAccessFile(dest, "rw");
        FileChannel in = inputAccessFile.getChannel();
        FileChannel out = outputAccessFile.getChannel();
        while (position < this.size) {
          if (chunkSize < this.size - position)
            chunkSize = this.size - position; 
          position += in.transferTo(position, chunkSize, out);
        } 
      } catch (IOException e) {
        exception = e;
      } finally {
        if (inputAccessFile != null)
          try {
            inputAccessFile.close();
          } catch (IOException e) {
            if (exception == null) {
              exception = e;
            } else {
              logger.warn("Multiple exceptions detected, the following will be suppressed {}", e);
            } 
          }  
        if (outputAccessFile != null)
          try {
            outputAccessFile.close();
          } catch (IOException e) {
            if (exception == null) {
              exception = e;
            } else {
              logger.warn("Multiple exceptions detected, the following will be suppressed {}", e);
            } 
          }  
      } 
      if (exception != null)
        throw exception; 
      if (position == this.size) {
        if (!this.file.delete())
          logger.warn("Failed to delete: {}", this.file); 
        this.file = dest;
        this.isRenamed = true;
        return true;
      } 
      if (!dest.delete())
        logger.warn("Failed to delete: {}", dest); 
      return false;
    } 
    this.file = dest;
    this.isRenamed = true;
    return true;
  }
  
  private static byte[] readFrom(File src) throws IOException {
    long srcsize = src.length();
    if (srcsize > 2147483647L)
      throw new IllegalArgumentException("File too big to be loaded in memory"); 
    RandomAccessFile accessFile = new RandomAccessFile(src, "r");
    byte[] array = new byte[(int)srcsize];
    try {
      FileChannel fileChannel = accessFile.getChannel();
      ByteBuffer byteBuffer = ByteBuffer.wrap(array);
      int read = 0;
      while (read < srcsize)
        read += fileChannel.read(byteBuffer); 
    } finally {
      accessFile.close();
    } 
    return array;
  }
  
  public File getFile() throws IOException {
    return this.file;
  }
  
  public HttpData touch() {
    return this;
  }
  
  public HttpData touch(Object hint) {
    return this;
  }
  
  protected abstract String getDiskFilename();
  
  protected abstract String getPrefix();
  
  protected abstract String getBaseDirectory();
  
  protected abstract String getPostfix();
  
  protected abstract boolean deleteOnExit();
}
