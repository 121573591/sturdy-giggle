package okio;

import java.io.IOException;
import java.util.zip.Deflater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000F\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\020\002\n\002\b\002\n\002\020\013\n\002\b\006\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\b\030\0002\0020\001B\031\b\026\022\006\020\002\032\0020\001\022\006\020\004\032\0020\003¢\006\004\b\005\020\006B\031\b\000\022\006\020\002\032\0020\007\022\006\020\004\032\0020\003¢\006\004\b\005\020\bJ\017\020\n\032\0020\tH\026¢\006\004\b\n\020\013J\027\020\016\032\0020\t2\006\020\r\032\0020\fH\002¢\006\004\b\016\020\017J\017\020\021\032\0020\tH\000¢\006\004\b\020\020\013J\017\020\022\032\0020\tH\026¢\006\004\b\022\020\013J\017\020\024\032\0020\023H\026¢\006\004\b\024\020\025J\017\020\027\032\0020\026H\026¢\006\004\b\027\020\030J\037\020\035\032\0020\t2\006\020\032\032\0020\0312\006\020\034\032\0020\033H\026¢\006\004\b\035\020\036R\026\020\037\032\0020\f8\002@\002X\016¢\006\006\n\004\b\037\020 R\024\020\004\032\0020\0038\002X\004¢\006\006\n\004\b\004\020!R\024\020\002\032\0020\0078\002X\004¢\006\006\n\004\b\002\020\"¨\006#"}, d2 = {"Lokio/DeflaterSink;", "Lokio/Sink;", "sink", "Ljava/util/zip/Deflater;", "deflater", "<init>", "(Lokio/Sink;Ljava/util/zip/Deflater;)V", "Lokio/BufferedSink;", "(Lokio/BufferedSink;Ljava/util/zip/Deflater;)V", "", "close", "()V", "", "syncFlush", "deflate", "(Z)V", "finishDeflate$okio", "finishDeflate", "flush", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "", "toString", "()Ljava/lang/String;", "Lokio/Buffer;", "source", "", "byteCount", "write", "(Lokio/Buffer;J)V", "closed", "Z", "Ljava/util/zip/Deflater;", "Lokio/BufferedSink;", "okio"})
@SourceDebugExtension({"SMAP\nDeflaterSink.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DeflaterSink.kt\nokio/DeflaterSink\n+ 2 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,163:1\n86#2:164\n*S KotlinDebug\n*F\n+ 1 DeflaterSink.kt\nokio/DeflaterSink\n*L\n58#1:164\n*E\n"})
public final class DeflaterSink implements Sink {
  @NotNull
  private final BufferedSink sink;
  
  @NotNull
  private final Deflater deflater;
  
  private boolean closed;
  
  public DeflaterSink(@NotNull BufferedSink sink, @NotNull Deflater deflater) {
    this.sink = sink;
    this.deflater = deflater;
  }
  
  public DeflaterSink(@NotNull Sink sink, @NotNull Deflater deflater) {
    this(Okio.buffer(sink), deflater);
  }
  
  public void write(@NotNull Buffer source, long byteCount) throws IOException {
    Intrinsics.checkNotNullParameter(source, "source");
    -SegmentedByteString.checkOffsetAndCount(source.size(), 0L, byteCount);
    long remaining = byteCount;
    while (remaining > 0L) {
      Intrinsics.checkNotNull(source.head);
      Segment head = source.head;
      int b$iv = head.limit - head.pos, $i$f$minOf = 0, toDeflate = (int)
        
        Math.min(remaining, b$iv);
      this.deflater.setInput(head.data, head.pos, toDeflate);
      deflate(false);
      source.setSize$okio(source.size() - toDeflate);
      head.pos += toDeflate;
      if (head.pos == head.limit) {
        source.head = head.pop();
        SegmentPool.recycle(head);
      } 
      remaining -= toDeflate;
    } 
  }
  
  private final void deflate(boolean syncFlush) {
    Buffer buffer = this.sink.getBuffer();
    while (true) {
      int i;
      Segment s = buffer.writableSegment$okio(1);
      try {
        i = syncFlush ? this.deflater.deflate(s.data, s.limit, 8192 - s.limit, 2) : this.deflater.deflate(s.data, s.limit, 8192 - s.limit);
      } catch (NullPointerException npe) {
        throw new IOException("Deflater already closed", (Throwable)npe);
      } 
      int deflated = i;
      if (deflated > 0) {
        s.limit += deflated;
        buffer.setSize$okio(buffer.size() + deflated);
        this.sink.emitCompleteSegments();
        continue;
      } 
      if (this.deflater.needsInput()) {
        if (s.pos == s.limit) {
          buffer.head = s.pop();
          SegmentPool.recycle(s);
        } 
        return;
      } 
    } 
  }
  
  public void flush() throws IOException {
    deflate(true);
    this.sink.flush();
  }
  
  public final void finishDeflate$okio() {
    this.deflater.finish();
    deflate(false);
  }
  
  public void close() throws IOException {
    if (this.closed)
      return; 
    Throwable thrown = null;
    try {
      finishDeflate$okio();
    } catch (Throwable e) {
      thrown = e;
    } 
    try {
      this.deflater.end();
    } catch (Throwable e) {
      if (thrown == null)
        thrown = e; 
    } 
    try {
      this.sink.close();
    } catch (Throwable e) {
      if (thrown == null)
        thrown = e; 
    } 
    this.closed = true;
    if (thrown != null)
      throw thrown; 
  }
  
  @NotNull
  public Timeout timeout() {
    return this.sink.timeout();
  }
  
  @NotNull
  public String toString() {
    return "DeflaterSink(" + this.sink + ')';
  }
}
