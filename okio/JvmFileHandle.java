package okio;

import java.io.RandomAccessFile;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\0002\n\002\030\002\n\002\030\002\n\002\020\013\n\000\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\003\n\002\020\t\n\000\n\002\020\022\n\000\n\002\020\b\n\002\b\r\b\000\030\0002\0020\001B\027\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007J\017\020\t\032\0020\bH\024¢\006\004\b\t\020\nJ\017\020\013\032\0020\bH\024¢\006\004\b\013\020\nJ/\020\023\032\0020\0202\006\020\r\032\0020\f2\006\020\017\032\0020\0162\006\020\021\032\0020\0202\006\020\022\032\0020\020H\024¢\006\004\b\023\020\024J\027\020\026\032\0020\b2\006\020\025\032\0020\fH\024¢\006\004\b\026\020\027J\017\020\030\032\0020\fH\024¢\006\004\b\030\020\031J/\020\032\032\0020\b2\006\020\r\032\0020\f2\006\020\017\032\0020\0162\006\020\021\032\0020\0202\006\020\022\032\0020\020H\024¢\006\004\b\032\020\033R\024\020\005\032\0020\0048\002X\004¢\006\006\n\004\b\005\020\034¨\006\035"}, d2 = {"Lokio/JvmFileHandle;", "Lokio/FileHandle;", "", "readWrite", "Ljava/io/RandomAccessFile;", "randomAccessFile", "<init>", "(ZLjava/io/RandomAccessFile;)V", "", "protectedClose", "()V", "protectedFlush", "", "fileOffset", "", "array", "", "arrayOffset", "byteCount", "protectedRead", "(J[BII)I", "size", "protectedResize", "(J)V", "protectedSize", "()J", "protectedWrite", "(J[BII)V", "Ljava/io/RandomAccessFile;", "okio"})
public final class JvmFileHandle extends FileHandle {
  @NotNull
  private final RandomAccessFile randomAccessFile;
  
  public JvmFileHandle(boolean readWrite, @NotNull RandomAccessFile randomAccessFile) {
    super(readWrite);
    this.randomAccessFile = randomAccessFile;
  }
  
  protected synchronized void protectedResize(long size) {
    long currentSize = size();
    long delta = size - currentSize;
    if (delta > 0L) {
      protectedWrite(currentSize, new byte[(int)delta], 0, (int)delta);
    } else {
      this.randomAccessFile.setLength(size);
    } 
  }
  
  protected synchronized long protectedSize() {
    return this.randomAccessFile.length();
  }
  
  protected synchronized int protectedRead(long fileOffset, @NotNull byte[] array, int arrayOffset, int byteCount) {
    Intrinsics.checkNotNullParameter(array, "array");
    this.randomAccessFile.seek(fileOffset);
    int bytesRead = 0;
    while (bytesRead < byteCount) {
      int readResult = this.randomAccessFile.read(array, arrayOffset, byteCount - bytesRead);
      if (readResult == -1) {
        if (bytesRead == 0)
          return -1; 
        break;
      } 
      bytesRead += readResult;
    } 
    return bytesRead;
  }
  
  protected synchronized void protectedWrite(long fileOffset, @NotNull byte[] array, int arrayOffset, int byteCount) {
    Intrinsics.checkNotNullParameter(array, "array");
    this.randomAccessFile.seek(fileOffset);
    this.randomAccessFile.write(array, arrayOffset, byteCount);
  }
  
  protected synchronized void protectedFlush() {
    this.randomAccessFile.getFD().sync();
  }
  
  protected synchronized void protectedClose() {
    this.randomAccessFile.close();
  }
}
