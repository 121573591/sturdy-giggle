package okhttp3.internal.cache2;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000&\n\002\030\002\n\002\020\000\n\002\030\002\n\002\b\003\n\002\020\t\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\006\b\000\030\0002\0020\001B\017\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J%\020\f\032\0020\0132\006\020\007\032\0020\0062\006\020\t\032\0020\b2\006\020\n\032\0020\006¢\006\004\b\f\020\rJ%\020\017\032\0020\0132\006\020\007\032\0020\0062\006\020\016\032\0020\b2\006\020\n\032\0020\006¢\006\004\b\017\020\rR\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\020¨\006\021"}, d2 = {"Lokhttp3/internal/cache2/FileOperator;", "", "Ljava/nio/channels/FileChannel;", "fileChannel", "<init>", "(Ljava/nio/channels/FileChannel;)V", "", "pos", "Lokio/Buffer;", "sink", "byteCount", "", "read", "(JLokio/Buffer;J)V", "source", "write", "Ljava/nio/channels/FileChannel;", "okhttp"})
public final class FileOperator {
  @NotNull
  private final FileChannel fileChannel;
  
  public FileOperator(@NotNull FileChannel fileChannel) {
    this.fileChannel = fileChannel;
  }
  
  public final void write(long pos, @NotNull Buffer source, long byteCount) throws IOException {
    Intrinsics.checkNotNullParameter(source, "source");
    if (byteCount < 0L || byteCount > source.size())
      throw new IndexOutOfBoundsException(); 
    long mutablePos = pos;
    long mutableByteCount = byteCount;
    while (mutableByteCount > 0L) {
      long bytesWritten = this.fileChannel.transferFrom((ReadableByteChannel)source, mutablePos, mutableByteCount);
      mutablePos += bytesWritten;
      mutableByteCount -= bytesWritten;
    } 
  }
  
  public final void read(long pos, @NotNull Buffer sink, long byteCount) {
    Intrinsics.checkNotNullParameter(sink, "sink");
    if (byteCount < 0L)
      throw new IndexOutOfBoundsException(); 
    long mutablePos = pos;
    long mutableByteCount = byteCount;
    while (mutableByteCount > 0L) {
      long bytesRead = this.fileChannel.transferTo(mutablePos, mutableByteCount, (WritableByteChannel)sink);
      mutablePos += bytesRead;
      mutableByteCount -= bytesRead;
    } 
  }
}
