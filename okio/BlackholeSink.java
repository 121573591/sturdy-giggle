package okio;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000*\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\004\b\002\030\0002\0020\001B\007¢\006\004\b\002\020\003J\017\020\005\032\0020\004H\026¢\006\004\b\005\020\003J\017\020\006\032\0020\004H\026¢\006\004\b\006\020\003J\017\020\b\032\0020\007H\026¢\006\004\b\b\020\tJ\037\020\016\032\0020\0042\006\020\013\032\0020\n2\006\020\r\032\0020\fH\026¢\006\004\b\016\020\017¨\006\020"}, d2 = {"Lokio/BlackholeSink;", "Lokio/Sink;", "<init>", "()V", "", "close", "flush", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "Lokio/Buffer;", "source", "", "byteCount", "write", "(Lokio/Buffer;J)V", "okio"})
final class BlackholeSink implements Sink {
  public void write(@NotNull Buffer source, long byteCount) {
    Intrinsics.checkNotNullParameter(source, "source");
    source.skip(byteCount);
  }
  
  public void flush() {}
  
  @NotNull
  public Timeout timeout() {
    return Timeout.NONE;
  }
  
  public void close() {}
}
