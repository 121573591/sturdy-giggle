package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000F\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\004\n\002\020\013\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\007\030\0002\0020\001B\031\b\026\022\006\020\002\032\0020\001\022\006\020\004\032\0020\003¢\006\004\b\005\020\006B\031\b\000\022\006\020\002\032\0020\007\022\006\020\004\032\0020\003¢\006\004\b\005\020\bJ\017\020\n\032\0020\tH\026¢\006\004\b\n\020\013J\037\020\020\032\0020\0162\006\020\r\032\0020\f2\006\020\017\032\0020\016H\026¢\006\004\b\020\020\021J\035\020\022\032\0020\0162\006\020\r\032\0020\f2\006\020\017\032\0020\016¢\006\004\b\022\020\021J\r\020\024\032\0020\023¢\006\004\b\024\020\025J\017\020\026\032\0020\tH\002¢\006\004\b\026\020\013J\017\020\030\032\0020\027H\026¢\006\004\b\030\020\031R\026\020\033\032\0020\0328\002@\002X\016¢\006\006\n\004\b\033\020\034R\026\020\035\032\0020\0238\002@\002X\016¢\006\006\n\004\b\035\020\036R\024\020\004\032\0020\0038\002X\004¢\006\006\n\004\b\004\020\037R\024\020\002\032\0020\0078\002X\004¢\006\006\n\004\b\002\020 ¨\006!"}, d2 = {"Lokio/InflaterSource;", "Lokio/Source;", "source", "Ljava/util/zip/Inflater;", "inflater", "<init>", "(Lokio/Source;Ljava/util/zip/Inflater;)V", "Lokio/BufferedSource;", "(Lokio/BufferedSource;Ljava/util/zip/Inflater;)V", "", "close", "()V", "Lokio/Buffer;", "sink", "", "byteCount", "read", "(Lokio/Buffer;J)J", "readOrInflate", "", "refill", "()Z", "releaseBytesAfterInflate", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "", "bufferBytesHeldByInflater", "I", "closed", "Z", "Ljava/util/zip/Inflater;", "Lokio/BufferedSource;", "okio"})
@SourceDebugExtension({"SMAP\nInflaterSource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InflaterSource.kt\nokio/InflaterSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,147:1\n1#2:148\n86#3:149\n*S KotlinDebug\n*F\n+ 1 InflaterSource.kt\nokio/InflaterSource\n*L\n73#1:149\n*E\n"})
public final class InflaterSource implements Source {
  @NotNull
  private final BufferedSource source;
  
  @NotNull
  private final Inflater inflater;
  
  private int bufferBytesHeldByInflater;
  
  private boolean closed;
  
  public InflaterSource(@NotNull BufferedSource source, @NotNull Inflater inflater) {
    this.source = source;
    this.inflater = inflater;
  }
  
  public InflaterSource(@NotNull Source source, @NotNull Inflater inflater) {
    this(Okio.buffer(source), inflater);
  }
  
  public long read(@NotNull Buffer sink, long byteCount) throws IOException {
    Intrinsics.checkNotNullParameter(sink, "sink");
    while (true) {
      long bytesInflated = readOrInflate(sink, byteCount);
      if (bytesInflated > 0L)
        return bytesInflated; 
      if (this.inflater.finished() || this.inflater.needsDictionary())
        return -1L; 
      if (this.source.exhausted())
        throw new EOFException("source exhausted prematurely"); 
    } 
  }
  
  public final long readOrInflate(@NotNull Buffer sink, long byteCount) throws IOException {
    Intrinsics.checkNotNullParameter(sink, "sink");
    if (!((byteCount >= 0L) ? 1 : 0)) {
      int $i$a$-require-InflaterSource$readOrInflate$1 = 0;
      String str = "byteCount < 0: " + byteCount;
      throw new IllegalArgumentException(str.toString());
    } 
    if (!(!this.closed ? 1 : 0)) {
      int $i$a$-check-InflaterSource$readOrInflate$2 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    if (byteCount == 0L)
      return 0L; 
    try {
      Segment tail = sink.writableSegment$okio(1);
      int b$iv = 8192 - tail.limit, $i$f$minOf = 0, toRead = (int)Math.min(byteCount, b$iv);
      refill();
      int bytesInflated = this.inflater.inflate(tail.data, tail.limit, toRead);
      releaseBytesAfterInflate();
      if (bytesInflated > 0) {
        tail.limit += bytesInflated;
        sink.setSize$okio(sink.size() + bytesInflated);
        return bytesInflated;
      } 
      if (tail.pos == tail.limit) {
        sink.head = tail.pop();
        SegmentPool.recycle(tail);
      } 
      return 0L;
    } catch (DataFormatException e) {
      throw new IOException((Throwable)e);
    } 
  }
  
  public final boolean refill() throws IOException {
    if (!this.inflater.needsInput())
      return false; 
    if (this.source.exhausted())
      return true; 
    Intrinsics.checkNotNull((this.source.getBuffer()).head);
    Segment head = (this.source.getBuffer()).head;
    this.bufferBytesHeldByInflater = head.limit - head.pos;
    this.inflater.setInput(head.data, head.pos, this.bufferBytesHeldByInflater);
    return false;
  }
  
  private final void releaseBytesAfterInflate() {
    if (this.bufferBytesHeldByInflater == 0)
      return; 
    int toRelease = this.bufferBytesHeldByInflater - this.inflater.getRemaining();
    this.bufferBytesHeldByInflater -= toRelease;
    this.source.skip(toRelease);
  }
  
  @NotNull
  public Timeout timeout() {
    return this.source.timeout();
  }
  
  public void close() throws IOException {
    if (this.closed)
      return; 
    this.inflater.end();
    this.closed = true;
    this.source.close();
  }
}
