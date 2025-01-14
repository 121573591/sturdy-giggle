package okio;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000R\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\006\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\030\0002\0020\001B\017\022\006\020\002\032\0020\001¢\006\004\b\003\020\004J\017\020\006\032\0020\005H\026¢\006\004\b\006\020\007J\017\020\013\032\0020\bH\007¢\006\004\b\t\020\nJ\017\020\f\032\0020\005H\026¢\006\004\b\f\020\007J\017\020\016\032\0020\rH\026¢\006\004\b\016\020\017J\037\020\024\032\0020\0052\006\020\021\032\0020\0202\006\020\023\032\0020\022H\002¢\006\004\b\024\020\025J\037\020\027\032\0020\0052\006\020\026\032\0020\0202\006\020\023\032\0020\022H\026¢\006\004\b\027\020\025J\017\020\030\032\0020\005H\002¢\006\004\b\030\020\007R\026\020\032\032\0020\0318\002@\002X\016¢\006\006\n\004\b\032\020\033R\024\020\035\032\0020\0348\002X\004¢\006\006\n\004\b\035\020\036R\027\020\013\032\0020\b8G¢\006\f\n\004\b\013\020\037\032\004\b\013\020\nR\024\020!\032\0020 8\002X\004¢\006\006\n\004\b!\020\"R\024\020\002\032\0020#8\002X\004¢\006\006\n\004\b\002\020$¨\006%"}, d2 = {"Lokio/GzipSink;", "Lokio/Sink;", "sink", "<init>", "(Lokio/Sink;)V", "", "close", "()V", "Ljava/util/zip/Deflater;", "-deprecated_deflater", "()Ljava/util/zip/Deflater;", "deflater", "flush", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "Lokio/Buffer;", "buffer", "", "byteCount", "updateCrc", "(Lokio/Buffer;J)V", "source", "write", "writeFooter", "", "closed", "Z", "Ljava/util/zip/CRC32;", "crc", "Ljava/util/zip/CRC32;", "Ljava/util/zip/Deflater;", "Lokio/DeflaterSink;", "deflaterSink", "Lokio/DeflaterSink;", "Lokio/RealBufferedSink;", "Lokio/RealBufferedSink;", "okio"})
@SourceDebugExtension({"SMAP\nGzipSink.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GzipSink.kt\nokio/GzipSink\n+ 2 RealBufferedSink.kt\nokio/RealBufferedSink\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,153:1\n51#2:154\n1#3:155\n86#4:156\n*S KotlinDebug\n*F\n+ 1 GzipSink.kt\nokio/GzipSink\n*L\n63#1:154\n131#1:156\n*E\n"})
public final class GzipSink implements Sink {
  @NotNull
  private final RealBufferedSink sink;
  
  @NotNull
  private final Deflater deflater;
  
  @NotNull
  private final DeflaterSink deflaterSink;
  
  private boolean closed;
  
  @NotNull
  private final CRC32 crc;
  
  public GzipSink(@NotNull Sink sink) {
    this.sink = new RealBufferedSink(sink);
    this.deflater = new Deflater(-1, true);
    this.deflaterSink = new DeflaterSink(this.sink, this.deflater);
    this.crc = new CRC32();
    RealBufferedSink this_$iv = this.sink;
    int $i$f$getBuffer = 0;
    Buffer buffer1 = this_$iv.bufferField;
    Buffer $this$_init__u24lambda_u240 = buffer1;
    int $i$a$-apply-GzipSink$1 = 0;
    $this$_init__u24lambda_u240.writeShort(8075);
    $this$_init__u24lambda_u240.writeByte(8);
    $this$_init__u24lambda_u240.writeByte(0);
    $this$_init__u24lambda_u240.writeInt(0);
    $this$_init__u24lambda_u240.writeByte(0);
    $this$_init__u24lambda_u240.writeByte(0);
  }
  
  @JvmName(name = "deflater")
  @NotNull
  public final Deflater deflater() {
    return this.deflater;
  }
  
  public void write(@NotNull Buffer source, long byteCount) throws IOException {
    Intrinsics.checkNotNullParameter(source, "source");
    if (!((byteCount >= 0L) ? 1 : 0)) {
      int $i$a$-require-GzipSink$write$1 = 0;
      String str = "byteCount < 0: " + byteCount;
      throw new IllegalArgumentException(str.toString());
    } 
    if (byteCount == 0L)
      return; 
    updateCrc(source, byteCount);
    this.deflaterSink.write(source, byteCount);
  }
  
  public void flush() throws IOException {
    this.deflaterSink.flush();
  }
  
  @NotNull
  public Timeout timeout() {
    return this.sink.timeout();
  }
  
  public void close() throws IOException {
    if (this.closed)
      return; 
    Throwable thrown = null;
    try {
      this.deflaterSink.finishDeflate$okio();
      writeFooter();
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
  
  private final void writeFooter() {
    this.sink.writeIntLe((int)this.crc.getValue());
    this.sink.writeIntLe((int)this.deflater.getBytesRead());
  }
  
  private final void updateCrc(Buffer buffer, long byteCount) {
    Intrinsics.checkNotNull(buffer.head);
    Segment head = buffer.head;
    long remaining = byteCount;
    while (remaining > 0L) {
      int b$iv = head.limit - head.pos, $i$f$minOf = 0, segmentLength = (int)Math.min(remaining, b$iv);
      this.crc.update(head.data, head.pos, segmentLength);
      remaining -= segmentLength;
      Intrinsics.checkNotNull(head.next);
      head = head.next;
    } 
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "deflater", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_deflater")
  @NotNull
  public final Deflater -deprecated_deflater() {
    return this.deflater;
  }
}
