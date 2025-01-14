package okio;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000F\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\030\002\n\002\b\004\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\002\n\002\030\002\n\002\b\006\b\000\030\0002\0020\001B\017\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\017\020\007\032\0020\006H\026¢\006\004\b\007\020\bJ\037\020\r\032\0020\0132\006\020\n\032\0020\t2\006\020\f\032\0020\013H\026¢\006\004\b\r\020\016J\017\020\020\032\0020\017H\026¢\006\004\b\020\020\021R\024\020\022\032\0020\t8\002X\004¢\006\006\n\004\b\022\020\023R\026\020\025\032\0020\0248\002@\002X\016¢\006\006\n\004\b\025\020\026R\026\020\030\032\0020\0278\002@\002X\016¢\006\006\n\004\b\030\020\031R\030\020\033\032\004\030\0010\0328\002@\002X\016¢\006\006\n\004\b\033\020\034R\026\020\035\032\0020\0138\002@\002X\016¢\006\006\n\004\b\035\020\036R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\037¨\006 "}, d2 = {"Lokio/PeekSource;", "Lokio/Source;", "Lokio/BufferedSource;", "upstream", "<init>", "(Lokio/BufferedSource;)V", "", "close", "()V", "Lokio/Buffer;", "sink", "", "byteCount", "read", "(Lokio/Buffer;J)J", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "buffer", "Lokio/Buffer;", "", "closed", "Z", "", "expectedPos", "I", "Lokio/Segment;", "expectedSegment", "Lokio/Segment;", "pos", "J", "Lokio/BufferedSource;", "okio"})
@SourceDebugExtension({"SMAP\nPeekSource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PeekSource.kt\nokio/PeekSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,74:1\n1#2:75\n*E\n"})
public final class PeekSource implements Source {
  @NotNull
  private final BufferedSource upstream;
  
  @NotNull
  private final Buffer buffer;
  
  @Nullable
  private Segment expectedSegment;
  
  private int expectedPos;
  
  private boolean closed;
  
  private long pos;
  
  public PeekSource(@NotNull BufferedSource upstream) {
    this.upstream = upstream;
    this.buffer = this.upstream.getBuffer();
    this.expectedSegment = this.buffer.head;
    this.expectedPos = (this.buffer.head != null) ? this.buffer.head.pos : -1;
  }
  
  public long read(@NotNull Buffer sink, long byteCount) {
    Intrinsics.checkNotNullParameter(sink, "sink");
    if (!((byteCount >= 0L) ? 1 : 0)) {
      int $i$a$-require-PeekSource$read$1 = 0;
      String str = "byteCount < 0: " + byteCount;
      throw new IllegalArgumentException(str.toString());
    } 
    if (!(!this.closed ? 1 : 0)) {
      int $i$a$-check-PeekSource$read$2 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    Intrinsics.checkNotNull(this.buffer.head);
    if (!((this.expectedSegment == null || (this.expectedSegment == this.buffer.head && this.expectedPos == this.buffer.head.pos)) ? 1 : 0)) {
      int $i$a$-check-PeekSource$read$3 = 0;
      String str = "Peek source is invalid because upstream source was used";
      throw new IllegalStateException(str.toString());
    } 
    if (byteCount == 0L)
      return 0L; 
    if (!this.upstream.request(this.pos + 1L))
      return -1L; 
    if (this.expectedSegment == null && this.buffer.head != null) {
      this.expectedSegment = this.buffer.head;
      Intrinsics.checkNotNull(this.buffer.head);
      this.expectedPos = this.buffer.head.pos;
    } 
    long toCopy = Math.min(byteCount, this.buffer.size() - this.pos);
    this.buffer.copyTo(sink, this.pos, toCopy);
    this.pos += toCopy;
    return toCopy;
  }
  
  @NotNull
  public Timeout timeout() {
    return this.upstream.timeout();
  }
  
  public void close() {
    this.closed = true;
  }
}
