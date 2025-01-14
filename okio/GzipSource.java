package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000`\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020\016\n\000\n\002\020\b\n\002\b\002\n\002\020\002\n\002\b\006\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\005\n\002\b\002\n\002\030\002\n\002\b\002\030\0002\0020\001B\017\022\006\020\002\032\0020\001¢\006\004\b\003\020\004J'\020\013\032\0020\n2\006\020\006\032\0020\0052\006\020\b\032\0020\0072\006\020\t\032\0020\007H\002¢\006\004\b\013\020\fJ\017\020\r\032\0020\nH\026¢\006\004\b\r\020\016J\017\020\017\032\0020\nH\002¢\006\004\b\017\020\016J\017\020\020\032\0020\nH\002¢\006\004\b\020\020\016J\037\020\025\032\0020\0232\006\020\022\032\0020\0212\006\020\024\032\0020\023H\026¢\006\004\b\025\020\026J\017\020\030\032\0020\027H\026¢\006\004\b\030\020\031J'\020\034\032\0020\n2\006\020\032\032\0020\0212\006\020\033\032\0020\0232\006\020\024\032\0020\023H\002¢\006\004\b\034\020\035R\024\020\037\032\0020\0368\002X\004¢\006\006\n\004\b\037\020 R\024\020\"\032\0020!8\002X\004¢\006\006\n\004\b\"\020#R\024\020%\032\0020$8\002X\004¢\006\006\n\004\b%\020&R\026\020(\032\0020'8\002@\002X\016¢\006\006\n\004\b(\020)R\024\020\002\032\0020*8\002X\004¢\006\006\n\004\b\002\020+¨\006,"}, d2 = {"Lokio/GzipSource;", "Lokio/Source;", "source", "<init>", "(Lokio/Source;)V", "", "name", "", "expected", "actual", "", "checkEqual", "(Ljava/lang/String;II)V", "close", "()V", "consumeHeader", "consumeTrailer", "Lokio/Buffer;", "sink", "", "byteCount", "read", "(Lokio/Buffer;J)J", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "buffer", "offset", "updateCrc", "(Lokio/Buffer;JJ)V", "Ljava/util/zip/CRC32;", "crc", "Ljava/util/zip/CRC32;", "Ljava/util/zip/Inflater;", "inflater", "Ljava/util/zip/Inflater;", "Lokio/InflaterSource;", "inflaterSource", "Lokio/InflaterSource;", "", "section", "B", "Lokio/RealBufferedSource;", "Lokio/RealBufferedSource;", "okio"})
@SourceDebugExtension({"SMAP\nGzipSource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GzipSource.kt\nokio/GzipSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 RealBufferedSource.kt\nokio/RealBufferedSource\n+ 4 GzipSource.kt\nokio/-GzipSourceExtensions\n+ 5 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,220:1\n1#2:221\n62#3:222\n62#3:224\n62#3:226\n62#3:227\n62#3:228\n62#3:230\n62#3:232\n202#4:223\n202#4:225\n202#4:229\n202#4:231\n89#5:233\n*S KotlinDebug\n*F\n+ 1 GzipSource.kt\nokio/GzipSource\n*L\n105#1:222\n107#1:224\n119#1:226\n120#1:227\n122#1:228\n133#1:230\n144#1:232\n106#1:223\n117#1:225\n130#1:229\n141#1:231\n187#1:233\n*E\n"})
public final class GzipSource implements Source {
  private byte section;
  
  @NotNull
  private final RealBufferedSource source;
  
  @NotNull
  private final Inflater inflater;
  
  @NotNull
  private final InflaterSource inflaterSource;
  
  @NotNull
  private final CRC32 crc;
  
  public GzipSource(@NotNull Source source) {
    this.source = new RealBufferedSource(source);
    this.inflater = new Inflater(true);
    this.inflaterSource = new InflaterSource(this.source, this.inflater);
    this.crc = new CRC32();
  }
  
  public long read(@NotNull Buffer sink, long byteCount) throws IOException {
    Intrinsics.checkNotNullParameter(sink, "sink");
    if (!((byteCount >= 0L) ? 1 : 0)) {
      int $i$a$-require-GzipSource$read$1 = 0;
      String str = "byteCount < 0: " + byteCount;
      throw new IllegalArgumentException(str.toString());
    } 
    if (byteCount == 0L)
      return 0L; 
    if (this.section == 0) {
      consumeHeader();
      this.section = 1;
    } 
    if (this.section == 1) {
      long offset = sink.size();
      long result = this.inflaterSource.read(sink, byteCount);
      if (result != -1L) {
        updateCrc(sink, offset, result);
        return result;
      } 
      this.section = 2;
    } 
    if (this.section == 2) {
      consumeTrailer();
      this.section = 3;
      if (!this.source.exhausted())
        throw new IOException("gzip finished without exhausting source"); 
    } 
    return -1L;
  }
  
  private final void consumeHeader() throws IOException {
    this.source.require(10L);
    RealBufferedSource this_$iv = this.source;
    int $i$f$getBuffer = 0, flags = this_$iv.bufferField.getByte(3L);
    $i$f$getBuffer = flags;
    int bit$iv = 1, $i$f$getBit = 0;
    boolean fhcrc = (($i$f$getBuffer >> bit$iv & 0x1) == 1);
    if (fhcrc) {
      RealBufferedSource realBufferedSource = this.source;
      int k = 0;
      updateCrc(realBufferedSource.bufferField, 0L, 10L);
    } 
    short id1id2 = this.source.readShort();
    checkEqual("ID1ID2", 8075, id1id2);
    this.source.skip(8L);
    bit$iv = flags;
    int i = 2, j = 0;
    if (((bit$iv >> i & 0x1) == 1)) {
      this.source.require(2L);
      if (fhcrc) {
        RealBufferedSource realBufferedSource1 = this.source;
        int m = 0;
        updateCrc(realBufferedSource1.bufferField, 0L, 2L);
      } 
      RealBufferedSource realBufferedSource = this.source;
      int k = 0;
      long xlen = (realBufferedSource.bufferField.readShortLe() & 0xFFFF);
      this.source.require(xlen);
      if (fhcrc) {
        realBufferedSource = this.source;
        k = 0;
        updateCrc(realBufferedSource.bufferField, 0L, xlen);
      } 
      this.source.skip(xlen);
    } 
    int $this$getBit$iv = flags;
    i = 3;
    j = 0;
    if ((($this$getBit$iv >> i & 0x1) == 1)) {
      long index = this.source.indexOf((byte)0);
      if (index == -1L)
        throw new EOFException(); 
      if (fhcrc) {
        RealBufferedSource realBufferedSource = this.source;
        int k = 0;
        updateCrc(realBufferedSource.bufferField, 0L, index + 1L);
      } 
      this.source.skip(index + 1L);
    } 
    $this$getBit$iv = flags;
    i = 4;
    j = 0;
    if ((($this$getBit$iv >> i & 0x1) == 1)) {
      long index = this.source.indexOf((byte)0);
      if (index == -1L)
        throw new EOFException(); 
      if (fhcrc) {
        RealBufferedSource realBufferedSource = this.source;
        int k = 0;
        updateCrc(realBufferedSource.bufferField, 0L, index + 1L);
      } 
      this.source.skip(index + 1L);
    } 
    if (fhcrc) {
      checkEqual("FHCRC", this.source.readShortLe(), (short)(int)this.crc.getValue());
      this.crc.reset();
    } 
  }
  
  private final void consumeTrailer() throws IOException {
    checkEqual("CRC", this.source.readIntLe(), (int)this.crc.getValue());
    checkEqual("ISIZE", this.source.readIntLe(), (int)this.inflater.getBytesWritten());
  }
  
  @NotNull
  public Timeout timeout() {
    return this.source.timeout();
  }
  
  public void close() throws IOException {
    this.inflaterSource.close();
  }
  
  private final void updateCrc(Buffer buffer, long offset, long byteCount) {
    long l1 = offset;
    long l2 = byteCount;
    Intrinsics.checkNotNull(buffer.head);
    Segment s = buffer.head;
    while (l1 >= (s.limit - s.pos)) {
      l1 -= (s.limit - s.pos);
      Intrinsics.checkNotNull(s.next);
      s = s.next;
    } 
    while (l2 > 0L) {
      int pos = (int)(s.pos + l1);
      int a$iv = s.limit - pos, $i$f$minOf = 0, toUpdate = (int)Math.min(a$iv, l2);
      this.crc.update(s.data, pos, toUpdate);
      l2 -= toUpdate;
      l1 = 0L;
      Intrinsics.checkNotNull(s.next);
      s = s.next;
    } 
  }
  
  private final void checkEqual(String name, int expected, int actual) {
    if (actual != expected) {
      String str = "%s: actual 0x%08x != expected 0x%08x";
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = name;
      arrayOfObject[1] = Integer.valueOf(actual);
      arrayOfObject[2] = Integer.valueOf(expected);
      arrayOfObject = arrayOfObject;
      Intrinsics.checkNotNullExpressionValue(String.format(str, Arrays.copyOf(arrayOfObject, arrayOfObject.length)), "format(this, *args)");
      throw new IOException(String.format(str, Arrays.copyOf(arrayOfObject, arrayOfObject.length)));
    } 
  }
}
