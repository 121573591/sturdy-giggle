package okio;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000*\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\022\n\000\n\002\020\b\n\002\b\002\n\002\020\013\n\002\b\003\n\002\020\002\n\002\b\026\b\000\030\000 \"2\0020\001:\001\"B\t\b\026¢\006\004\b\002\020\003B1\b\026\022\006\020\005\032\0020\004\022\006\020\007\032\0020\006\022\006\020\b\032\0020\006\022\006\020\n\032\0020\t\022\006\020\013\032\0020\t¢\006\004\b\002\020\fJ\r\020\016\032\0020\r¢\006\004\b\016\020\003J\017\020\017\032\004\030\0010\000¢\006\004\b\017\020\020J\025\020\022\032\0020\0002\006\020\021\032\0020\000¢\006\004\b\022\020\023J\r\020\024\032\0020\000¢\006\004\b\024\020\020J\025\020\026\032\0020\0002\006\020\025\032\0020\006¢\006\004\b\026\020\027J\r\020\030\032\0020\000¢\006\004\b\030\020\020J\035\020\032\032\0020\r2\006\020\031\032\0020\0002\006\020\025\032\0020\006¢\006\004\b\032\020\033R\024\020\005\032\0020\0048\006X\004¢\006\006\n\004\b\005\020\034R\026\020\b\032\0020\0068\006@\006X\016¢\006\006\n\004\b\b\020\035R\030\020\036\032\004\030\0010\0008\006@\006X\016¢\006\006\n\004\b\036\020\037R\026\020\013\032\0020\t8\006@\006X\016¢\006\006\n\004\b\013\020 R\026\020\007\032\0020\0068\006@\006X\016¢\006\006\n\004\b\007\020\035R\030\020!\032\004\030\0010\0008\006@\006X\016¢\006\006\n\004\b!\020\037R\026\020\n\032\0020\t8\006@\006X\016¢\006\006\n\004\b\n\020 ¨\006#"}, d2 = {"Lokio/Segment;", "", "<init>", "()V", "", "data", "", "pos", "limit", "", "shared", "owner", "([BIIZZ)V", "", "compact", "pop", "()Lokio/Segment;", "segment", "push", "(Lokio/Segment;)Lokio/Segment;", "sharedCopy", "byteCount", "split", "(I)Lokio/Segment;", "unsharedCopy", "sink", "writeTo", "(Lokio/Segment;I)V", "[B", "I", "next", "Lokio/Segment;", "Z", "prev", "Companion", "okio"})
@SourceDebugExtension({"SMAP\nSegment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Segment.kt\nokio/Segment\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,187:1\n1#2:188\n*E\n"})
public final class Segment {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @JvmField
  @NotNull
  public final byte[] data;
  
  @JvmField
  public int pos;
  
  @JvmField
  public int limit;
  
  @JvmField
  public boolean shared;
  
  @JvmField
  public boolean owner;
  
  @JvmField
  @Nullable
  public Segment next;
  
  @JvmField
  @Nullable
  public Segment prev;
  
  public static final int SIZE = 8192;
  
  public static final int SHARE_MINIMUM = 1024;
  
  public Segment() {
    this.data = new byte[8192];
    this.owner = true;
    this.shared = false;
  }
  
  public Segment(@NotNull byte[] data, int pos, int limit, boolean shared, boolean owner) {
    this.data = data;
    this.pos = pos;
    this.limit = limit;
    this.shared = shared;
    this.owner = owner;
  }
  
  @NotNull
  public final Segment sharedCopy() {
    this.shared = true;
    return new Segment(this.data, this.pos, this.limit, true, false);
  }
  
  @NotNull
  public final Segment unsharedCopy() {
    Intrinsics.checkNotNullExpressionValue(Arrays.copyOf(this.data, this.data.length), "copyOf(this, size)");
    return new Segment(Arrays.copyOf(this.data, this.data.length), this.pos, this.limit, false, true);
  }
  
  @Nullable
  public final Segment pop() {
    Segment result = (this.next != this) ? this.next : null;
    Intrinsics.checkNotNull(this.prev);
    this.prev.next = this.next;
    Intrinsics.checkNotNull(this.next);
    this.next.prev = this.prev;
    this.next = null;
    this.prev = null;
    return result;
  }
  
  @NotNull
  public final Segment push(@NotNull Segment segment) {
    Intrinsics.checkNotNullParameter(segment, "segment");
    segment.prev = this;
    segment.next = this.next;
    Intrinsics.checkNotNull(this.next);
    this.next.prev = segment;
    this.next = segment;
    return segment;
  }
  
  @NotNull
  public final Segment split(int byteCount) {
    if (!((byteCount > 0 && byteCount <= this.limit - this.pos) ? 1 : 0)) {
      int $i$a$-require-Segment$split$1 = 0;
      String str = "byteCount out of range";
      throw new IllegalArgumentException(str.toString());
    } 
    Segment prefix = null;
    if (byteCount >= 1024) {
      prefix = sharedCopy();
    } else {
      prefix = SegmentPool.take();
      ArraysKt.copyInto$default(this.data, prefix.data, 0, this.pos, this.pos + byteCount, 2, null);
    } 
    prefix.limit = prefix.pos + byteCount;
    this.pos += byteCount;
    Intrinsics.checkNotNull(this.prev);
    this.prev.push(prefix);
    return prefix;
  }
  
  public final void compact() {
    if (!((this.prev != this) ? 1 : 0)) {
      int $i$a$-check-Segment$compact$1 = 0;
      String str = "cannot compact";
      throw new IllegalStateException(str.toString());
    } 
    Intrinsics.checkNotNull(this.prev);
    if (!this.prev.owner)
      return; 
    int byteCount = this.limit - this.pos;
    Intrinsics.checkNotNull(this.prev);
    Intrinsics.checkNotNull(this.prev);
    Intrinsics.checkNotNull(this.prev);
    int availableByteCount = 8192 - this.prev.limit + (this.prev.shared ? 0 : this.prev.pos);
    if (byteCount > availableByteCount)
      return; 
    Intrinsics.checkNotNull(this.prev);
    writeTo(this.prev, byteCount);
    pop();
    SegmentPool.recycle(this);
  }
  
  public final void writeTo(@NotNull Segment sink, int byteCount) {
    Intrinsics.checkNotNullParameter(sink, "sink");
    if (!sink.owner) {
      int $i$a$-check-Segment$writeTo$1 = 0;
      String str = "only owner can write";
      throw new IllegalStateException(str.toString());
    } 
    if (sink.limit + byteCount > 8192) {
      if (sink.shared)
        throw new IllegalArgumentException(); 
      if (sink.limit + byteCount - sink.pos > 8192)
        throw new IllegalArgumentException(); 
      ArraysKt.copyInto$default(sink.data, sink.data, 0, sink.pos, sink.limit, 2, null);
      sink.limit -= sink.pos;
      sink.pos = 0;
    } 
    ArraysKt.copyInto(this.data, sink.data, sink.limit, this.pos, this.pos + byteCount);
    sink.limit += byteCount;
    this.pos += byteCount;
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\002\b\004\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\024\020\005\032\0020\0048\006XT¢\006\006\n\004\b\005\020\006R\024\020\007\032\0020\0048\006XT¢\006\006\n\004\b\007\020\006¨\006\b"}, d2 = {"Lokio/Segment$Companion;", "", "<init>", "()V", "", "SHARE_MINIMUM", "I", "SIZE", "okio"})
  public static final class Companion {
    private Companion() {}
  }
}
