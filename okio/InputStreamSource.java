package okio;

import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\0004\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\004\n\002\020\016\n\002\b\005\b\022\030\0002\0020\001B\027\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007J\017\020\t\032\0020\bH\026¢\006\004\b\t\020\nJ\037\020\017\032\0020\r2\006\020\f\032\0020\0132\006\020\016\032\0020\rH\026¢\006\004\b\017\020\020J\017\020\005\032\0020\004H\026¢\006\004\b\005\020\021J\017\020\023\032\0020\022H\026¢\006\004\b\023\020\024R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\025R\024\020\005\032\0020\0048\002X\004¢\006\006\n\004\b\005\020\026¨\006\027"}, d2 = {"Lokio/InputStreamSource;", "Lokio/Source;", "Ljava/io/InputStream;", "input", "Lokio/Timeout;", "timeout", "<init>", "(Ljava/io/InputStream;Lokio/Timeout;)V", "", "close", "()V", "Lokio/Buffer;", "sink", "", "byteCount", "read", "(Lokio/Buffer;J)J", "()Lokio/Timeout;", "", "toString", "()Ljava/lang/String;", "Ljava/io/InputStream;", "Lokio/Timeout;", "okio"})
@SourceDebugExtension({"SMAP\nJvmOkio.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JvmOkio.kt\nokio/InputStreamSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,239:1\n1#2:240\n86#3:241\n*S KotlinDebug\n*F\n+ 1 JvmOkio.kt\nokio/InputStreamSource\n*L\n92#1:241\n*E\n"})
class InputStreamSource implements Source {
  @NotNull
  private final InputStream input;
  
  @NotNull
  private final Timeout timeout;
  
  public InputStreamSource(@NotNull InputStream input, @NotNull Timeout timeout) {
    this.input = input;
    this.timeout = timeout;
  }
  
  public long read(@NotNull Buffer sink, long byteCount) {
    Intrinsics.checkNotNullParameter(sink, "sink");
    if (byteCount == 0L)
      return 0L; 
    if (!((byteCount >= 0L) ? 1 : 0)) {
      int $i$a$-require-InputStreamSource$read$1 = 0;
      String str = "byteCount < 0: " + byteCount;
      throw new IllegalArgumentException(str.toString());
    } 
    try {
      this.timeout.throwIfReached();
      Segment tail = sink.writableSegment$okio(1);
      int b$iv = 8192 - tail.limit, $i$f$minOf = 0, maxToCopy = (int)Math.min(byteCount, b$iv);
      int bytesRead = this.input.read(tail.data, tail.limit, maxToCopy);
      if (bytesRead == -1) {
        if (tail.pos == tail.limit) {
          sink.head = tail.pop();
          SegmentPool.recycle(tail);
        } 
        return -1L;
      } 
      tail.limit += bytesRead;
      sink.setSize$okio(sink.size() + bytesRead);
      return bytesRead;
    } catch (AssertionError e) {
      if (Okio.isAndroidGetsocknameError(e))
        throw new IOException(e); 
      throw e;
    } 
  }
  
  public void close() {
    this.input.close();
  }
  
  @NotNull
  public Timeout timeout() {
    return this.timeout;
  }
  
  @NotNull
  public String toString() {
    return "source(" + this.input + ')';
  }
}
