package okio;

import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\0004\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\004\n\002\020\016\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\006\b\002\030\0002\0020\001B\027\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007J\017\020\t\032\0020\bH\026¢\006\004\b\t\020\nJ\017\020\013\032\0020\bH\026¢\006\004\b\013\020\nJ\017\020\005\032\0020\004H\026¢\006\004\b\005\020\fJ\017\020\016\032\0020\rH\026¢\006\004\b\016\020\017J\037\020\024\032\0020\b2\006\020\021\032\0020\0202\006\020\023\032\0020\022H\026¢\006\004\b\024\020\025R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\026R\024\020\005\032\0020\0048\002X\004¢\006\006\n\004\b\005\020\027¨\006\030"}, d2 = {"Lokio/OutputStreamSink;", "Lokio/Sink;", "Ljava/io/OutputStream;", "out", "Lokio/Timeout;", "timeout", "<init>", "(Ljava/io/OutputStream;Lokio/Timeout;)V", "", "close", "()V", "flush", "()Lokio/Timeout;", "", "toString", "()Ljava/lang/String;", "Lokio/Buffer;", "source", "", "byteCount", "write", "(Lokio/Buffer;J)V", "Ljava/io/OutputStream;", "Lokio/Timeout;", "okio"})
@SourceDebugExtension({"SMAP\nJvmOkio.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JvmOkio.kt\nokio/OutputStreamSink\n+ 2 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,239:1\n86#2:240\n*S KotlinDebug\n*F\n+ 1 JvmOkio.kt\nokio/OutputStreamSink\n*L\n55#1:240\n*E\n"})
final class OutputStreamSink implements Sink {
  @NotNull
  private final OutputStream out;
  
  @NotNull
  private final Timeout timeout;
  
  public OutputStreamSink(@NotNull OutputStream out, @NotNull Timeout timeout) {
    this.out = out;
    this.timeout = timeout;
  }
  
  public void write(@NotNull Buffer source, long byteCount) {
    Intrinsics.checkNotNullParameter(source, "source");
    -SegmentedByteString.checkOffsetAndCount(source.size(), 0L, byteCount);
    long remaining = byteCount;
    while (remaining > 0L) {
      this.timeout.throwIfReached();
      Intrinsics.checkNotNull(source.head);
      Segment head = source.head;
      int b$iv = head.limit - head.pos, $i$f$minOf = 0, toCopy = (int)
        
        Math.min(remaining, b$iv);
      this.out.write(head.data, head.pos, toCopy);
      head.pos += toCopy;
      remaining -= toCopy;
      source.setSize$okio(source.size() - toCopy);
      if (head.pos == head.limit) {
        source.head = head.pop();
        SegmentPool.recycle(head);
      } 
    } 
  }
  
  public void flush() {
    this.out.flush();
  }
  
  public void close() {
    this.out.close();
  }
  
  @NotNull
  public Timeout timeout() {
    return this.timeout;
  }
  
  @NotNull
  public String toString() {
    return "sink(" + this.out + ')';
  }
}
