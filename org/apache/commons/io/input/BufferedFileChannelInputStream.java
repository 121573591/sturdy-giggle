package org.apache.commons.io.input;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public final class BufferedFileChannelInputStream extends InputStream {
  private final ByteBuffer byteBuffer;
  
  private final FileChannel fileChannel;
  
  private static final Class<?> DIRECT_BUFFER_CLASS = getDirectBufferClass();
  
  private static Class<?> getDirectBufferClass() {
    Class<?> res = null;
    try {
      res = Class.forName("sun.nio.ch.DirectBuffer");
    } catch (IllegalAccessError|ClassNotFoundException illegalAccessError) {}
    return res;
  }
  
  private static boolean isDirectBuffer(Object object) {
    return (DIRECT_BUFFER_CLASS != null && DIRECT_BUFFER_CLASS.isInstance(object));
  }
  
  public BufferedFileChannelInputStream(File file) throws IOException {
    this(file, 8192);
  }
  
  public BufferedFileChannelInputStream(File file, int bufferSizeInBytes) throws IOException {
    this(file.toPath(), bufferSizeInBytes);
  }
  
  public BufferedFileChannelInputStream(Path path) throws IOException {
    this(path, 8192);
  }
  
  public BufferedFileChannelInputStream(Path path, int bufferSizeInBytes) throws IOException {
    Objects.requireNonNull(path, "path");
    this.fileChannel = FileChannel.open(path, new OpenOption[] { StandardOpenOption.READ });
    this.byteBuffer = ByteBuffer.allocateDirect(bufferSizeInBytes);
    this.byteBuffer.flip();
  }
  
  public synchronized int available() throws IOException {
    return this.byteBuffer.remaining();
  }
  
  private void clean(ByteBuffer buffer) {
    if (isDirectBuffer(buffer))
      cleanDirectBuffer(buffer); 
  }
  
  private void cleanDirectBuffer(ByteBuffer buffer) {
    String specVer = System.getProperty("java.specification.version");
    if ("1.8".equals(specVer)) {
      try {
        Class<?> clsCleaner = Class.forName("sun.misc.Cleaner");
        Method cleanerMethod = DIRECT_BUFFER_CLASS.getMethod("cleaner", new Class[0]);
        Object cleaner = cleanerMethod.invoke(buffer, new Object[0]);
        if (cleaner != null) {
          Method cleanMethod = clsCleaner.getMethod("clean", new Class[0]);
          cleanMethod.invoke(cleaner, new Object[0]);
        } 
      } catch (ReflectiveOperationException e) {
        throw new IllegalStateException(e);
      } 
    } else {
      try {
        Class<?> clsUnsafe = Class.forName("sun.misc.Unsafe");
        Method cleanerMethod = clsUnsafe.getMethod("invokeCleaner", new Class[] { ByteBuffer.class });
        Field unsafeField = clsUnsafe.getDeclaredField("theUnsafe");
        unsafeField.setAccessible(true);
        cleanerMethod.invoke(unsafeField.get(null), new Object[] { buffer });
      } catch (ReflectiveOperationException e) {
        throw new IllegalStateException(e);
      } 
    } 
  }
  
  public synchronized void close() throws IOException {
    try {
      this.fileChannel.close();
    } finally {
      clean(this.byteBuffer);
    } 
  }
  
  public synchronized int read() throws IOException {
    if (!refill())
      return -1; 
    return this.byteBuffer.get() & 0xFF;
  }
  
  public synchronized int read(byte[] b, int offset, int len) throws IOException {
    if (offset < 0 || len < 0 || offset + len < 0 || offset + len > b.length)
      throw new IndexOutOfBoundsException(); 
    if (!refill())
      return -1; 
    len = Math.min(len, this.byteBuffer.remaining());
    this.byteBuffer.get(b, offset, len);
    return len;
  }
  
  private boolean refill() throws IOException {
    if (!this.byteBuffer.hasRemaining()) {
      this.byteBuffer.clear();
      int nRead = 0;
      while (nRead == 0)
        nRead = this.fileChannel.read(this.byteBuffer); 
      this.byteBuffer.flip();
      return (nRead >= 0);
    } 
    return true;
  }
  
  public synchronized long skip(long n) throws IOException {
    if (n <= 0L)
      return 0L; 
    if (this.byteBuffer.remaining() >= n) {
      this.byteBuffer.position(this.byteBuffer.position() + (int)n);
      return n;
    } 
    long skippedFromBuffer = this.byteBuffer.remaining();
    long toSkipFromFileChannel = n - skippedFromBuffer;
    this.byteBuffer.position(0);
    this.byteBuffer.flip();
    return skippedFromBuffer + skipFromFileChannel(toSkipFromFileChannel);
  }
  
  private long skipFromFileChannel(long n) throws IOException {
    long currentFilePosition = this.fileChannel.position();
    long size = this.fileChannel.size();
    if (n > size - currentFilePosition) {
      this.fileChannel.position(size);
      return size - currentFilePosition;
    } 
    this.fileChannel.position(currentFilePosition + n);
    return n;
  }
}
