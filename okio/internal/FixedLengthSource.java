package okio.internal;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ForwardingSource;
import okio.Source;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000,\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\t\n\000\n\002\020\013\n\002\b\003\n\002\030\002\n\002\b\005\n\002\020\002\n\002\b\006\b\000\030\0002\0020\001B\037\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\006\020\007\032\0020\006¢\006\004\b\b\020\tJ\037\020\r\032\0020\0042\006\020\013\032\0020\n2\006\020\f\032\0020\004H\026¢\006\004\b\r\020\016J\033\020\021\032\0020\020*\0020\n2\006\020\017\032\0020\004H\002¢\006\004\b\021\020\022R\026\020\023\032\0020\0048\002@\002X\016¢\006\006\n\004\b\023\020\024R\024\020\005\032\0020\0048\002X\004¢\006\006\n\004\b\005\020\024R\024\020\007\032\0020\0068\002X\004¢\006\006\n\004\b\007\020\025¨\006\026"}, d2 = {"Lokio/internal/FixedLengthSource;", "Lokio/ForwardingSource;", "Lokio/Source;", "delegate", "", "size", "", "truncate", "<init>", "(Lokio/Source;JZ)V", "Lokio/Buffer;", "sink", "byteCount", "read", "(Lokio/Buffer;J)J", "newSize", "", "truncateToSize", "(Lokio/Buffer;J)V", "bytesReceived", "J", "Z", "okio"})
public final class FixedLengthSource extends ForwardingSource {
  private final long size;
  
  private final boolean truncate;
  
  private long bytesReceived;
  
  public FixedLengthSource(@NotNull Source delegate, long size, boolean truncate) {
    super(delegate);
    this.size = size;
    this.truncate = truncate;
  }
  
  public long read(@NotNull Buffer sink, long byteCount) {
    Intrinsics.checkNotNullParameter(sink, "sink");
    long remaining = this.size - this.bytesReceived;
    if (remaining == 0L)
      return -1L; 
    long toRead = (this.bytesReceived > this.size) ? 0L : (this.truncate ? Math.min(byteCount, remaining) : 
      
      byteCount);
    long result = super.read(sink, toRead);
    if (result != -1L)
      this.bytesReceived += result; 
    if ((this.bytesReceived < this.size && result == -1L) || this.bytesReceived > this.size) {
      if (result > 0L && this.bytesReceived > this.size)
        truncateToSize(sink, sink.size() - this.bytesReceived - this.size); 
      throw new IOException("expected " + this.size + " bytes but got " + this.bytesReceived);
    } 
    return result;
  }
  
  private final void truncateToSize(Buffer $this$truncateToSize, long newSize) {
    Buffer scratch = new Buffer();
    scratch.writeAll((Source)$this$truncateToSize);
    $this$truncateToSize.write(scratch, newSize);
    scratch.clear();
  }
}
