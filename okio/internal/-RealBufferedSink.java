package okio.internal;

import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.RealBufferedSink;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\000N\n\002\030\002\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\002\n\002\020\022\n\002\b\003\n\002\020\b\n\002\b\003\n\002\030\002\n\002\020\t\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\033\032\024\020\002\032\0020\001*\0020\000H\b¢\006\004\b\002\020\003\032\024\020\005\032\0020\004*\0020\000H\b¢\006\004\b\005\020\006\032\024\020\007\032\0020\004*\0020\000H\b¢\006\004\b\007\020\006\032\024\020\b\032\0020\001*\0020\000H\b¢\006\004\b\b\020\003\032\024\020\n\032\0020\t*\0020\000H\b¢\006\004\b\n\020\013\032\024\020\r\032\0020\f*\0020\000H\b¢\006\004\b\r\020\016\032\034\020\021\032\0020\004*\0020\0002\006\020\020\032\0020\017H\b¢\006\004\b\021\020\022\032,\020\021\032\0020\004*\0020\0002\006\020\020\032\0020\0172\006\020\024\032\0020\0232\006\020\025\032\0020\023H\b¢\006\004\b\021\020\026\032$\020\021\032\0020\001*\0020\0002\006\020\020\032\0020\0272\006\020\025\032\0020\030H\b¢\006\004\b\021\020\031\032\034\020\021\032\0020\004*\0020\0002\006\020\033\032\0020\032H\b¢\006\004\b\021\020\034\032,\020\021\032\0020\004*\0020\0002\006\020\033\032\0020\0322\006\020\024\032\0020\0232\006\020\025\032\0020\023H\b¢\006\004\b\021\020\035\032$\020\021\032\0020\004*\0020\0002\006\020\020\032\0020\0362\006\020\025\032\0020\030H\b¢\006\004\b\021\020\037\032\034\020 \032\0020\030*\0020\0002\006\020\020\032\0020\036H\b¢\006\004\b \020!\032\034\020#\032\0020\004*\0020\0002\006\020\"\032\0020\023H\b¢\006\004\b#\020$\032\034\020&\032\0020\004*\0020\0002\006\020%\032\0020\030H\b¢\006\004\b&\020'\032\034\020(\032\0020\004*\0020\0002\006\020%\032\0020\030H\b¢\006\004\b(\020'\032\034\020*\032\0020\004*\0020\0002\006\020)\032\0020\023H\b¢\006\004\b*\020$\032\034\020+\032\0020\004*\0020\0002\006\020)\032\0020\023H\b¢\006\004\b+\020$\032\034\020,\032\0020\004*\0020\0002\006\020%\032\0020\030H\b¢\006\004\b,\020'\032\034\020-\032\0020\004*\0020\0002\006\020%\032\0020\030H\b¢\006\004\b-\020'\032\034\020/\032\0020\004*\0020\0002\006\020.\032\0020\023H\b¢\006\004\b/\020$\032\034\0200\032\0020\004*\0020\0002\006\020.\032\0020\023H\b¢\006\004\b0\020$\032\034\0202\032\0020\004*\0020\0002\006\0201\032\0020\fH\b¢\006\004\b2\0203\032,\0202\032\0020\004*\0020\0002\006\0201\032\0020\f2\006\0204\032\0020\0232\006\0205\032\0020\023H\b¢\006\004\b2\0206\032\034\0208\032\0020\004*\0020\0002\006\0207\032\0020\023H\b¢\006\004\b8\020$¨\0069"}, d2 = {"Lokio/RealBufferedSink;", "", "commonClose", "(Lokio/RealBufferedSink;)V", "Lokio/BufferedSink;", "commonEmit", "(Lokio/RealBufferedSink;)Lokio/BufferedSink;", "commonEmitCompleteSegments", "commonFlush", "Lokio/Timeout;", "commonTimeout", "(Lokio/RealBufferedSink;)Lokio/Timeout;", "", "commonToString", "(Lokio/RealBufferedSink;)Ljava/lang/String;", "", "source", "commonWrite", "(Lokio/RealBufferedSink;[B)Lokio/BufferedSink;", "", "offset", "byteCount", "(Lokio/RealBufferedSink;[BII)Lokio/BufferedSink;", "Lokio/Buffer;", "", "(Lokio/RealBufferedSink;Lokio/Buffer;J)V", "Lokio/ByteString;", "byteString", "(Lokio/RealBufferedSink;Lokio/ByteString;)Lokio/BufferedSink;", "(Lokio/RealBufferedSink;Lokio/ByteString;II)Lokio/BufferedSink;", "Lokio/Source;", "(Lokio/RealBufferedSink;Lokio/Source;J)Lokio/BufferedSink;", "commonWriteAll", "(Lokio/RealBufferedSink;Lokio/Source;)J", "b", "commonWriteByte", "(Lokio/RealBufferedSink;I)Lokio/BufferedSink;", "v", "commonWriteDecimalLong", "(Lokio/RealBufferedSink;J)Lokio/BufferedSink;", "commonWriteHexadecimalUnsignedLong", "i", "commonWriteInt", "commonWriteIntLe", "commonWriteLong", "commonWriteLongLe", "s", "commonWriteShort", "commonWriteShortLe", "string", "commonWriteUtf8", "(Lokio/RealBufferedSink;Ljava/lang/String;)Lokio/BufferedSink;", "beginIndex", "endIndex", "(Lokio/RealBufferedSink;Ljava/lang/String;II)Lokio/BufferedSink;", "codePoint", "commonWriteUtf8CodePoint", "okio"})
@JvmName(name = "-RealBufferedSink")
@SourceDebugExtension({"SMAP\nRealBufferedSink.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealBufferedSink.kt\nokio/internal/-RealBufferedSink\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 RealBufferedSink.kt\nokio/RealBufferedSink\n*L\n1#1,219:1\n1#2:220\n51#3:221\n51#3:222\n51#3:223\n51#3:224\n51#3:225\n51#3:226\n51#3:227\n51#3:228\n51#3:229\n51#3:230\n51#3:231\n51#3:232\n51#3:233\n51#3:234\n51#3:235\n51#3:236\n51#3:237\n51#3:238\n51#3:239\n51#3:240\n51#3:241\n51#3:242\n51#3:243\n51#3:244\n51#3:245\n51#3:246\n51#3:247\n*S KotlinDebug\n*F\n+ 1 RealBufferedSink.kt\nokio/internal/-RealBufferedSink\n*L\n35#1:221\n41#1:222\n51#1:223\n57#1:224\n67#1:225\n73#1:226\n79#1:227\n89#1:228\n96#1:229\n107#1:230\n117#1:231\n123#1:232\n129#1:233\n135#1:234\n141#1:235\n147#1:236\n153#1:237\n159#1:238\n165#1:239\n171#1:240\n172#1:241\n178#1:242\n179#1:243\n185#1:244\n186#1:245\n198#1:246\n199#1:247\n*E\n"})
public final class -RealBufferedSink {
  public static final void commonWrite(@NotNull RealBufferedSink $this$commonWrite, @NotNull Buffer source, long byteCount) {
    Intrinsics.checkNotNullParameter($this$commonWrite, "<this>");
    Intrinsics.checkNotNullParameter(source, "source");
    int $i$f$commonWrite = 0;
    if (!(!$this$commonWrite.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWrite$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = $this$commonWrite;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.write(source, byteCount);
    $this$commonWrite.emitCompleteSegments();
  }
  
  @NotNull
  public static final BufferedSink commonWrite(@NotNull RealBufferedSink $this$commonWrite, @NotNull ByteString byteString) {
    Intrinsics.checkNotNullParameter($this$commonWrite, "<this>");
    Intrinsics.checkNotNullParameter(byteString, "byteString");
    int $i$f$commonWrite = 0;
    if (!(!$this$commonWrite.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWrite$2 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = $this$commonWrite;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.write(byteString);
    return $this$commonWrite.emitCompleteSegments();
  }
  
  @NotNull
  public static final BufferedSink commonWrite(@NotNull RealBufferedSink $this$commonWrite, @NotNull ByteString byteString, int offset, int byteCount) {
    Intrinsics.checkNotNullParameter($this$commonWrite, "<this>");
    Intrinsics.checkNotNullParameter(byteString, "byteString");
    int $i$f$commonWrite = 0;
    if (!(!$this$commonWrite.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWrite$3 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = $this$commonWrite;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.write(byteString, offset, byteCount);
    return $this$commonWrite.emitCompleteSegments();
  }
  
  @NotNull
  public static final BufferedSink commonWriteUtf8(@NotNull RealBufferedSink $this$commonWriteUtf8, @NotNull String string) {
    Intrinsics.checkNotNullParameter($this$commonWriteUtf8, "<this>");
    Intrinsics.checkNotNullParameter(string, "string");
    int $i$f$commonWriteUtf8 = 0;
    if (!(!$this$commonWriteUtf8.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteUtf8$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = $this$commonWriteUtf8;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.writeUtf8(string);
    return $this$commonWriteUtf8.emitCompleteSegments();
  }
  
  @NotNull
  public static final BufferedSink commonWriteUtf8(@NotNull RealBufferedSink $this$commonWriteUtf8, @NotNull String string, int beginIndex, int endIndex) {
    Intrinsics.checkNotNullParameter($this$commonWriteUtf8, "<this>");
    Intrinsics.checkNotNullParameter(string, "string");
    int $i$f$commonWriteUtf8 = 0;
    if (!(!$this$commonWriteUtf8.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteUtf8$2 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = $this$commonWriteUtf8;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.writeUtf8(string, beginIndex, endIndex);
    return $this$commonWriteUtf8.emitCompleteSegments();
  }
  
  @NotNull
  public static final BufferedSink commonWriteUtf8CodePoint(@NotNull RealBufferedSink $this$commonWriteUtf8CodePoint, int codePoint) {
    Intrinsics.checkNotNullParameter($this$commonWriteUtf8CodePoint, "<this>");
    int $i$f$commonWriteUtf8CodePoint = 0;
    if (!(!$this$commonWriteUtf8CodePoint.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteUtf8CodePoint$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = $this$commonWriteUtf8CodePoint;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.writeUtf8CodePoint(codePoint);
    return $this$commonWriteUtf8CodePoint.emitCompleteSegments();
  }
  
  @NotNull
  public static final BufferedSink commonWrite(@NotNull RealBufferedSink $this$commonWrite, @NotNull byte[] source) {
    Intrinsics.checkNotNullParameter($this$commonWrite, "<this>");
    Intrinsics.checkNotNullParameter(source, "source");
    int $i$f$commonWrite = 0;
    if (!(!$this$commonWrite.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWrite$4 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = $this$commonWrite;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.write(source);
    return $this$commonWrite.emitCompleteSegments();
  }
  
  @NotNull
  public static final BufferedSink commonWrite(@NotNull RealBufferedSink $this$commonWrite, @NotNull byte[] source, int offset, int byteCount) {
    Intrinsics.checkNotNullParameter($this$commonWrite, "<this>");
    Intrinsics.checkNotNullParameter(source, "source");
    int $i$f$commonWrite = 0;
    if (!(!$this$commonWrite.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWrite$5 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = $this$commonWrite;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.write(source, offset, byteCount);
    return $this$commonWrite.emitCompleteSegments();
  }
  
  public static final long commonWriteAll(@NotNull RealBufferedSink $this$commonWriteAll, @NotNull Source source) {
    Intrinsics.checkNotNullParameter($this$commonWriteAll, "<this>");
    Intrinsics.checkNotNullParameter(source, "source");
    int $i$f$commonWriteAll = 0;
    long totalBytesRead = 0L;
    while (true) {
      RealBufferedSink this_$iv = $this$commonWriteAll;
      int $i$f$getBuffer = 0;
      long readCount = source.read(this_$iv.bufferField, 8192L);
      if (readCount != -1L) {
        totalBytesRead += readCount;
        $this$commonWriteAll.emitCompleteSegments();
        continue;
      } 
      break;
    } 
    return totalBytesRead;
  }
  
  @NotNull
  public static final BufferedSink commonWrite(@NotNull RealBufferedSink $this$commonWrite, @NotNull Source source, long byteCount) {
    Intrinsics.checkNotNullParameter($this$commonWrite, "<this>");
    Intrinsics.checkNotNullParameter(source, "source");
    int $i$f$commonWrite = 0;
    long l = byteCount;
    while (l > 0L) {
      RealBufferedSink this_$iv = $this$commonWrite;
      int $i$f$getBuffer = 0;
      long read = source.read(this_$iv.bufferField, l);
      if (read == -1L)
        throw new EOFException(); 
      l -= read;
      $this$commonWrite.emitCompleteSegments();
    } 
    return (BufferedSink)$this$commonWrite;
  }
  
  @NotNull
  public static final BufferedSink commonWriteByte(@NotNull RealBufferedSink $this$commonWriteByte, int b) {
    Intrinsics.checkNotNullParameter($this$commonWriteByte, "<this>");
    int $i$f$commonWriteByte = 0;
    if (!(!$this$commonWriteByte.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteByte$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = $this$commonWriteByte;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.writeByte(b);
    return $this$commonWriteByte.emitCompleteSegments();
  }
  
  @NotNull
  public static final BufferedSink commonWriteShort(@NotNull RealBufferedSink $this$commonWriteShort, int s) {
    Intrinsics.checkNotNullParameter($this$commonWriteShort, "<this>");
    int $i$f$commonWriteShort = 0;
    if (!(!$this$commonWriteShort.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteShort$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = $this$commonWriteShort;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.writeShort(s);
    return $this$commonWriteShort.emitCompleteSegments();
  }
  
  @NotNull
  public static final BufferedSink commonWriteShortLe(@NotNull RealBufferedSink $this$commonWriteShortLe, int s) {
    Intrinsics.checkNotNullParameter($this$commonWriteShortLe, "<this>");
    int $i$f$commonWriteShortLe = 0;
    if (!(!$this$commonWriteShortLe.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteShortLe$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = $this$commonWriteShortLe;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.writeShortLe(s);
    return $this$commonWriteShortLe.emitCompleteSegments();
  }
  
  @NotNull
  public static final BufferedSink commonWriteInt(@NotNull RealBufferedSink $this$commonWriteInt, int i) {
    Intrinsics.checkNotNullParameter($this$commonWriteInt, "<this>");
    int $i$f$commonWriteInt = 0;
    if (!(!$this$commonWriteInt.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteInt$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = $this$commonWriteInt;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.writeInt(i);
    return $this$commonWriteInt.emitCompleteSegments();
  }
  
  @NotNull
  public static final BufferedSink commonWriteIntLe(@NotNull RealBufferedSink $this$commonWriteIntLe, int i) {
    Intrinsics.checkNotNullParameter($this$commonWriteIntLe, "<this>");
    int $i$f$commonWriteIntLe = 0;
    if (!(!$this$commonWriteIntLe.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteIntLe$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = $this$commonWriteIntLe;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.writeIntLe(i);
    return $this$commonWriteIntLe.emitCompleteSegments();
  }
  
  @NotNull
  public static final BufferedSink commonWriteLong(@NotNull RealBufferedSink $this$commonWriteLong, long v) {
    Intrinsics.checkNotNullParameter($this$commonWriteLong, "<this>");
    int $i$f$commonWriteLong = 0;
    if (!(!$this$commonWriteLong.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteLong$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = $this$commonWriteLong;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.writeLong(v);
    return $this$commonWriteLong.emitCompleteSegments();
  }
  
  @NotNull
  public static final BufferedSink commonWriteLongLe(@NotNull RealBufferedSink $this$commonWriteLongLe, long v) {
    Intrinsics.checkNotNullParameter($this$commonWriteLongLe, "<this>");
    int $i$f$commonWriteLongLe = 0;
    if (!(!$this$commonWriteLongLe.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteLongLe$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = $this$commonWriteLongLe;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.writeLongLe(v);
    return $this$commonWriteLongLe.emitCompleteSegments();
  }
  
  @NotNull
  public static final BufferedSink commonWriteDecimalLong(@NotNull RealBufferedSink $this$commonWriteDecimalLong, long v) {
    Intrinsics.checkNotNullParameter($this$commonWriteDecimalLong, "<this>");
    int $i$f$commonWriteDecimalLong = 0;
    if (!(!$this$commonWriteDecimalLong.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteDecimalLong$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = $this$commonWriteDecimalLong;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.writeDecimalLong(v);
    return $this$commonWriteDecimalLong.emitCompleteSegments();
  }
  
  @NotNull
  public static final BufferedSink commonWriteHexadecimalUnsignedLong(@NotNull RealBufferedSink $this$commonWriteHexadecimalUnsignedLong, long v) {
    Intrinsics.checkNotNullParameter($this$commonWriteHexadecimalUnsignedLong, "<this>");
    int $i$f$commonWriteHexadecimalUnsignedLong = 0;
    if (!(!$this$commonWriteHexadecimalUnsignedLong.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteHexadecimalUnsignedLong$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = $this$commonWriteHexadecimalUnsignedLong;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.writeHexadecimalUnsignedLong(v);
    return $this$commonWriteHexadecimalUnsignedLong.emitCompleteSegments();
  }
  
  @NotNull
  public static final BufferedSink commonEmitCompleteSegments(@NotNull RealBufferedSink $this$commonEmitCompleteSegments) {
    Intrinsics.checkNotNullParameter($this$commonEmitCompleteSegments, "<this>");
    int $i$f$commonEmitCompleteSegments = 0;
    if (!(!$this$commonEmitCompleteSegments.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonEmitCompleteSegments$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = $this$commonEmitCompleteSegments;
    int $i$f$getBuffer = 0;
    long byteCount = this_$iv.bufferField.completeSegmentByteCount();
    if (byteCount > 0L) {
      this_$iv = $this$commonEmitCompleteSegments;
      $i$f$getBuffer = 0;
      $this$commonEmitCompleteSegments.sink.write(this_$iv.bufferField, byteCount);
    } 
    return (BufferedSink)$this$commonEmitCompleteSegments;
  }
  
  @NotNull
  public static final BufferedSink commonEmit(@NotNull RealBufferedSink $this$commonEmit) {
    Intrinsics.checkNotNullParameter($this$commonEmit, "<this>");
    int $i$f$commonEmit = 0;
    if (!(!$this$commonEmit.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonEmit$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = $this$commonEmit;
    int $i$f$getBuffer = 0;
    long byteCount = this_$iv.bufferField.size();
    if (byteCount > 0L) {
      this_$iv = $this$commonEmit;
      $i$f$getBuffer = 0;
      $this$commonEmit.sink.write(this_$iv.bufferField, byteCount);
    } 
    return (BufferedSink)$this$commonEmit;
  }
  
  public static final void commonFlush(@NotNull RealBufferedSink $this$commonFlush) {
    // Byte code:
    //   0: aload_0
    //   1: ldc '<this>'
    //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   6: iconst_0
    //   7: istore_1
    //   8: aload_0
    //   9: getfield closed : Z
    //   12: ifne -> 19
    //   15: iconst_1
    //   16: goto -> 20
    //   19: iconst_0
    //   20: ifne -> 40
    //   23: iconst_0
    //   24: istore_3
    //   25: ldc 'closed'
    //   27: astore_3
    //   28: new java/lang/IllegalStateException
    //   31: dup
    //   32: aload_3
    //   33: invokevirtual toString : ()Ljava/lang/String;
    //   36: invokespecial <init> : (Ljava/lang/String;)V
    //   39: athrow
    //   40: aload_0
    //   41: astore_2
    //   42: iconst_0
    //   43: istore_3
    //   44: aload_2
    //   45: getfield bufferField : Lokio/Buffer;
    //   48: invokevirtual size : ()J
    //   51: lconst_0
    //   52: lcmp
    //   53: ifle -> 84
    //   56: aload_0
    //   57: getfield sink : Lokio/Sink;
    //   60: aload_0
    //   61: astore_2
    //   62: iconst_0
    //   63: istore_3
    //   64: aload_2
    //   65: getfield bufferField : Lokio/Buffer;
    //   68: aload_0
    //   69: astore_2
    //   70: iconst_0
    //   71: istore_3
    //   72: aload_2
    //   73: getfield bufferField : Lokio/Buffer;
    //   76: invokevirtual size : ()J
    //   79: invokeinterface write : (Lokio/Buffer;J)V
    //   84: aload_0
    //   85: getfield sink : Lokio/Sink;
    //   88: invokeinterface flush : ()V
    //   93: return
    // Line number table:
    //   Java source line number -> byte code offset
    //   #184	-> 8
    //   #220	-> 23
    //   #184	-> 25
    //   #184	-> 27
    //   #185	-> 40
    //   #244	-> 44
    //   #185	-> 48
    //   #186	-> 56
    //   #245	-> 64
    //   #186	-> 68
    //   #245	-> 72
    //   #186	-> 76
    //   #188	-> 84
    //   #189	-> 93
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   25	2	3	$i$a$-check--RealBufferedSink$commonFlush$1	I
    //   44	4	3	$i$f$getBuffer	I
    //   42	6	2	this_$iv	Lokio/RealBufferedSink;
    //   64	4	3	$i$f$getBuffer	I
    //   62	6	2	this_$iv	Lokio/RealBufferedSink;
    //   72	4	3	$i$f$getBuffer	I
    //   70	6	2	this_$iv	Lokio/RealBufferedSink;
    //   8	86	1	$i$f$commonFlush	I
    //   0	94	0	$this$commonFlush	Lokio/RealBufferedSink;
  }
  
  public static final void commonClose(@NotNull RealBufferedSink $this$commonClose) {
    // Byte code:
    //   0: aload_0
    //   1: ldc '<this>'
    //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   6: iconst_0
    //   7: istore_1
    //   8: aload_0
    //   9: getfield closed : Z
    //   12: ifeq -> 16
    //   15: return
    //   16: aconst_null
    //   17: astore_2
    //   18: nop
    //   19: aload_0
    //   20: astore_3
    //   21: iconst_0
    //   22: istore #4
    //   24: aload_3
    //   25: getfield bufferField : Lokio/Buffer;
    //   28: invokevirtual size : ()J
    //   31: lconst_0
    //   32: lcmp
    //   33: ifle -> 72
    //   36: aload_0
    //   37: getfield sink : Lokio/Sink;
    //   40: aload_0
    //   41: astore_3
    //   42: iconst_0
    //   43: istore #4
    //   45: aload_3
    //   46: getfield bufferField : Lokio/Buffer;
    //   49: aload_0
    //   50: astore_3
    //   51: iconst_0
    //   52: istore #4
    //   54: aload_3
    //   55: getfield bufferField : Lokio/Buffer;
    //   58: invokevirtual size : ()J
    //   61: invokeinterface write : (Lokio/Buffer;J)V
    //   66: goto -> 72
    //   69: astore_3
    //   70: aload_3
    //   71: astore_2
    //   72: nop
    //   73: aload_0
    //   74: getfield sink : Lokio/Sink;
    //   77: invokeinterface close : ()V
    //   82: goto -> 92
    //   85: astore_3
    //   86: aload_2
    //   87: ifnonnull -> 92
    //   90: aload_3
    //   91: astore_2
    //   92: aload_0
    //   93: iconst_1
    //   94: putfield closed : Z
    //   97: aload_2
    //   98: dup
    //   99: ifnull -> 103
    //   102: athrow
    //   103: pop
    //   104: return
    // Line number table:
    //   Java source line number -> byte code offset
    //   #192	-> 8
    //   #196	-> 16
    //   #197	-> 18
    //   #198	-> 19
    //   #246	-> 24
    //   #198	-> 28
    //   #199	-> 36
    //   #247	-> 45
    //   #199	-> 49
    //   #247	-> 54
    //   #199	-> 58
    //   #201	-> 69
    //   #202	-> 70
    //   #205	-> 72
    //   #206	-> 73
    //   #207	-> 85
    //   #208	-> 86
    //   #211	-> 92
    //   #213	-> 97
    //   #214	-> 103
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   24	4	4	$i$f$getBuffer	I
    //   21	7	3	this_$iv	Lokio/RealBufferedSink;
    //   45	4	4	$i$f$getBuffer	I
    //   42	7	3	this_$iv	Lokio/RealBufferedSink;
    //   54	4	4	$i$f$getBuffer	I
    //   51	7	3	this_$iv	Lokio/RealBufferedSink;
    //   70	2	3	e	Ljava/lang/Throwable;
    //   86	6	3	e	Ljava/lang/Throwable;
    //   8	97	1	$i$f$commonClose	I
    //   18	87	2	thrown	Ljava/lang/Throwable;
    //   0	105	0	$this$commonClose	Lokio/RealBufferedSink;
    // Exception table:
    //   from	to	target	type
    //   18	66	69	java/lang/Throwable
    //   72	82	85	java/lang/Throwable
  }
  
  @NotNull
  public static final Timeout commonTimeout(@NotNull RealBufferedSink $this$commonTimeout) {
    Intrinsics.checkNotNullParameter($this$commonTimeout, "<this>");
    int $i$f$commonTimeout = 0;
    return $this$commonTimeout.sink.timeout();
  }
  
  @NotNull
  public static final String commonToString(@NotNull RealBufferedSink $this$commonToString) {
    Intrinsics.checkNotNullParameter($this$commonToString, "<this>");
    int $i$f$commonToString = 0;
    return "buffer(" + $this$commonToString.sink + ')';
  }
}
