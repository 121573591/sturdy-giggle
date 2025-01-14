package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000t\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\006\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\002\n\002\030\002\n\000\n\002\020\b\n\002\b\002\n\002\020\022\n\002\b\004\n\002\020\t\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\023\n\002\030\002\n\002\b\023\b\000\030\0002\0020\001B\017\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\017\020\007\032\0020\006H\026¢\006\004\b\007\020\bJ\017\020\n\032\0020\tH\026¢\006\004\b\n\020\013J\017\020\f\032\0020\001H\026¢\006\004\b\f\020\rJ\017\020\016\032\0020\001H\026¢\006\004\b\016\020\rJ\017\020\017\032\0020\tH\026¢\006\004\b\017\020\013J\017\020\021\032\0020\020H\026¢\006\004\b\021\020\022J\017\020\024\032\0020\023H\026¢\006\004\b\024\020\025J\017\020\027\032\0020\026H\026¢\006\004\b\027\020\030J\017\020\032\032\0020\031H\026¢\006\004\b\032\020\033J\027\020\037\032\0020\0362\006\020\035\032\0020\034H\026¢\006\004\b\037\020 J\027\020\037\032\0020\0012\006\020\035\032\0020!H\026¢\006\004\b\037\020\"J'\020\037\032\0020\0012\006\020\035\032\0020!2\006\020#\032\0020\0362\006\020$\032\0020\036H\026¢\006\004\b\037\020%J\037\020\037\032\0020\t2\006\020\035\032\0020\0062\006\020$\032\0020&H\026¢\006\004\b\037\020'J\027\020\037\032\0020\0012\006\020)\032\0020(H\026¢\006\004\b\037\020*J'\020\037\032\0020\0012\006\020)\032\0020(2\006\020#\032\0020\0362\006\020$\032\0020\036H\026¢\006\004\b\037\020+J\037\020\037\032\0020\0012\006\020\035\032\0020,2\006\020$\032\0020&H\026¢\006\004\b\037\020-J\027\020.\032\0020&2\006\020\035\032\0020,H\026¢\006\004\b.\020/J\027\0201\032\0020\0012\006\0200\032\0020\036H\026¢\006\004\b1\0202J\027\0204\032\0020\0012\006\0203\032\0020&H\026¢\006\004\b4\0205J\027\0206\032\0020\0012\006\0203\032\0020&H\026¢\006\004\b6\0205J\027\0208\032\0020\0012\006\0207\032\0020\036H\026¢\006\004\b8\0202J\027\0209\032\0020\0012\006\0207\032\0020\036H\026¢\006\004\b9\0202J\027\020:\032\0020\0012\006\0203\032\0020&H\026¢\006\004\b:\0205J\027\020;\032\0020\0012\006\0203\032\0020&H\026¢\006\004\b;\0205J\027\020=\032\0020\0012\006\020<\032\0020\036H\026¢\006\004\b=\0202J\027\020>\032\0020\0012\006\020<\032\0020\036H\026¢\006\004\b>\0202J\037\020B\032\0020\0012\006\020?\032\0020\0312\006\020A\032\0020@H\026¢\006\004\bB\020CJ/\020B\032\0020\0012\006\020?\032\0020\0312\006\020D\032\0020\0362\006\020E\032\0020\0362\006\020A\032\0020@H\026¢\006\004\bB\020FJ\027\020G\032\0020\0012\006\020?\032\0020\031H\026¢\006\004\bG\020HJ'\020G\032\0020\0012\006\020?\032\0020\0312\006\020D\032\0020\0362\006\020E\032\0020\036H\026¢\006\004\bG\020IJ\027\020K\032\0020\0012\006\020J\032\0020\036H\026¢\006\004\bK\0202R\033\020\007\032\0020\0068Ö\002X\004¢\006\f\022\004\bM\020\013\032\004\bL\020\bR\024\020N\032\0020\0068\006X\004¢\006\006\n\004\bN\020OR\026\020P\032\0020\0208\006@\006X\016¢\006\006\n\004\bP\020QR\024\020\003\032\0020\0028\006X\004¢\006\006\n\004\b\003\020R¨\006S"}, d2 = {"Lokio/RealBufferedSink;", "Lokio/BufferedSink;", "Lokio/Sink;", "sink", "<init>", "(Lokio/Sink;)V", "Lokio/Buffer;", "buffer", "()Lokio/Buffer;", "", "close", "()V", "emit", "()Lokio/BufferedSink;", "emitCompleteSegments", "flush", "", "isOpen", "()Z", "Ljava/io/OutputStream;", "outputStream", "()Ljava/io/OutputStream;", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "", "toString", "()Ljava/lang/String;", "Ljava/nio/ByteBuffer;", "source", "", "write", "(Ljava/nio/ByteBuffer;)I", "", "([B)Lokio/BufferedSink;", "offset", "byteCount", "([BII)Lokio/BufferedSink;", "", "(Lokio/Buffer;J)V", "Lokio/ByteString;", "byteString", "(Lokio/ByteString;)Lokio/BufferedSink;", "(Lokio/ByteString;II)Lokio/BufferedSink;", "Lokio/Source;", "(Lokio/Source;J)Lokio/BufferedSink;", "writeAll", "(Lokio/Source;)J", "b", "writeByte", "(I)Lokio/BufferedSink;", "v", "writeDecimalLong", "(J)Lokio/BufferedSink;", "writeHexadecimalUnsignedLong", "i", "writeInt", "writeIntLe", "writeLong", "writeLongLe", "s", "writeShort", "writeShortLe", "string", "Ljava/nio/charset/Charset;", "charset", "writeString", "(Ljava/lang/String;Ljava/nio/charset/Charset;)Lokio/BufferedSink;", "beginIndex", "endIndex", "(Ljava/lang/String;IILjava/nio/charset/Charset;)Lokio/BufferedSink;", "writeUtf8", "(Ljava/lang/String;)Lokio/BufferedSink;", "(Ljava/lang/String;II)Lokio/BufferedSink;", "codePoint", "writeUtf8CodePoint", "getBuffer", "getBuffer$annotations", "bufferField", "Lokio/Buffer;", "closed", "Z", "Lokio/Sink;", "okio"})
@SourceDebugExtension({"SMAP\nRealBufferedSink.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealBufferedSink.kt\nokio/RealBufferedSink\n+ 2 RealBufferedSink.kt\nokio/internal/-RealBufferedSink\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,142:1\n51#1:146\n51#1:152\n51#1:157\n51#1:162\n51#1:167\n51#1:172\n51#1:175\n51#1:176\n51#1:180\n51#1:185\n51#1:188\n51#1:192\n51#1:201\n51#1:210\n51#1:215\n51#1:220\n51#1:225\n51#1:230\n51#1:235\n51#1:240\n51#1:245\n51#1:250\n51#1:255\n51#1:261\n51#1:267\n51#1:279\n34#2:143\n35#2:145\n36#2,2:147\n40#2:149\n41#2:151\n42#2:153\n50#2:154\n51#2:156\n52#2:158\n56#2:159\n57#2:161\n58#2:163\n66#2:164\n67#2:166\n68#2:168\n72#2:169\n73#2:171\n74#2:173\n78#2:177\n79#2:179\n80#2:181\n88#2:182\n89#2:184\n90#2:186\n94#2,3:189\n97#2,5:193\n105#2,3:198\n108#2,5:202\n116#2:207\n117#2:209\n118#2:211\n122#2:212\n123#2:214\n124#2:216\n128#2:217\n129#2:219\n130#2:221\n134#2:222\n135#2:224\n136#2:226\n140#2:227\n141#2:229\n142#2:231\n146#2:232\n147#2:234\n148#2:236\n152#2:237\n153#2:239\n154#2:241\n158#2:242\n159#2:244\n160#2:246\n164#2:247\n165#2:249\n166#2:251\n170#2:252\n171#2:254\n172#2,2:256\n177#2:258\n178#2:260\n179#2,2:262\n184#2:264\n185#2:266\n186#2,4:268\n192#2,7:272\n199#2,16:280\n216#2:296\n218#2:297\n1#3:144\n1#3:150\n1#3:155\n1#3:160\n1#3:165\n1#3:170\n1#3:174\n1#3:178\n1#3:183\n1#3:187\n1#3:208\n1#3:213\n1#3:218\n1#3:223\n1#3:228\n1#3:233\n1#3:238\n1#3:243\n1#3:248\n1#3:253\n1#3:259\n1#3:265\n*S KotlinDebug\n*F\n+ 1 RealBufferedSink.kt\nokio/RealBufferedSink\n*L\n55#1:146\n56#1:152\n58#1:157\n59#1:162\n61#1:167\n63#1:172\n67#1:175\n78#1:176\n82#1:180\n84#1:185\n88#1:188\n93#1:192\n94#1:201\n95#1:210\n96#1:215\n97#1:220\n98#1:225\n99#1:230\n100#1:235\n101#1:240\n102#1:245\n103#1:250\n104#1:255\n105#1:261\n134#1:267\n138#1:279\n55#1:143\n55#1:145\n55#1:147,2\n56#1:149\n56#1:151\n56#1:153\n58#1:154\n58#1:156\n58#1:158\n59#1:159\n59#1:161\n59#1:163\n61#1:164\n61#1:166\n61#1:168\n63#1:169\n63#1:171\n63#1:173\n82#1:177\n82#1:179\n82#1:181\n84#1:182\n84#1:184\n84#1:186\n93#1:189,3\n93#1:193,5\n94#1:198,3\n94#1:202,5\n95#1:207\n95#1:209\n95#1:211\n96#1:212\n96#1:214\n96#1:216\n97#1:217\n97#1:219\n97#1:221\n98#1:222\n98#1:224\n98#1:226\n99#1:227\n99#1:229\n99#1:231\n100#1:232\n100#1:234\n100#1:236\n101#1:237\n101#1:239\n101#1:241\n102#1:242\n102#1:244\n102#1:246\n103#1:247\n103#1:249\n103#1:251\n104#1:252\n104#1:254\n104#1:256,2\n105#1:258\n105#1:260\n105#1:262,2\n134#1:264\n134#1:266\n134#1:268,4\n138#1:272,7\n138#1:280,16\n139#1:296\n140#1:297\n55#1:144\n56#1:150\n58#1:155\n59#1:160\n61#1:165\n63#1:170\n82#1:178\n84#1:183\n95#1:208\n96#1:213\n97#1:218\n98#1:223\n99#1:228\n100#1:233\n101#1:238\n102#1:243\n103#1:248\n104#1:253\n105#1:259\n134#1:265\n*E\n"})
public final class RealBufferedSink implements BufferedSink {
  @JvmField
  @NotNull
  public final Sink sink;
  
  @JvmField
  @NotNull
  public final Buffer bufferField;
  
  @JvmField
  public boolean closed;
  
  public RealBufferedSink(@NotNull Sink sink) {
    this.sink = sink;
    this.bufferField = new Buffer();
  }
  
  @NotNull
  public Buffer getBuffer() {
    int $i$f$getBuffer = 0;
    return this.bufferField;
  }
  
  @NotNull
  public Buffer buffer() {
    return this.bufferField;
  }
  
  public void write(@NotNull Buffer source, long byteCount) {
    Intrinsics.checkNotNullParameter(source, "source");
    RealBufferedSink $this$commonWrite$iv = this;
    int $i$f$commonWrite = 0;
    if (!(!$this$commonWrite$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWrite$1$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv$iv = $this$commonWrite$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.write(source, byteCount);
    $this$commonWrite$iv.emitCompleteSegments();
  }
  
  @NotNull
  public BufferedSink write(@NotNull ByteString byteString) {
    Intrinsics.checkNotNullParameter(byteString, "byteString");
    RealBufferedSink $this$commonWrite$iv = this;
    int $i$f$commonWrite = 0;
    if (!(!$this$commonWrite$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWrite$2$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv$iv = $this$commonWrite$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.write(byteString);
    return $this$commonWrite$iv.emitCompleteSegments();
  }
  
  @NotNull
  public BufferedSink write(@NotNull ByteString byteString, int offset, int byteCount) {
    Intrinsics.checkNotNullParameter(byteString, "byteString");
    RealBufferedSink $this$commonWrite$iv = this;
    int $i$f$commonWrite = 0;
    if (!(!$this$commonWrite$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWrite$3$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv$iv = $this$commonWrite$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.write(byteString, offset, byteCount);
    return $this$commonWrite$iv.emitCompleteSegments();
  }
  
  @NotNull
  public BufferedSink writeUtf8(@NotNull String string) {
    Intrinsics.checkNotNullParameter(string, "string");
    RealBufferedSink $this$commonWriteUtf8$iv = this;
    int $i$f$commonWriteUtf8 = 0;
    if (!(!$this$commonWriteUtf8$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteUtf8$1$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv$iv = $this$commonWriteUtf8$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.writeUtf8(string);
    return $this$commonWriteUtf8$iv.emitCompleteSegments();
  }
  
  @NotNull
  public BufferedSink writeUtf8(@NotNull String string, int beginIndex, int endIndex) {
    Intrinsics.checkNotNullParameter(string, "string");
    RealBufferedSink $this$commonWriteUtf8$iv = this;
    int $i$f$commonWriteUtf8 = 0;
    if (!(!$this$commonWriteUtf8$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteUtf8$2$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv$iv = $this$commonWriteUtf8$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.writeUtf8(string, beginIndex, endIndex);
    return $this$commonWriteUtf8$iv.emitCompleteSegments();
  }
  
  @NotNull
  public BufferedSink writeUtf8CodePoint(int codePoint) {
    RealBufferedSink $this$commonWriteUtf8CodePoint$iv = this;
    int $i$f$commonWriteUtf8CodePoint = 0;
    if (!(!$this$commonWriteUtf8CodePoint$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteUtf8CodePoint$1$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv$iv = $this$commonWriteUtf8CodePoint$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.writeUtf8CodePoint(codePoint);
    return $this$commonWriteUtf8CodePoint$iv.emitCompleteSegments();
  }
  
  @NotNull
  public BufferedSink writeString(@NotNull String string, @NotNull Charset charset) {
    Intrinsics.checkNotNullParameter(string, "string");
    Intrinsics.checkNotNullParameter(charset, "charset");
    if (!(!this.closed ? 1 : 0)) {
      int $i$a$-check-RealBufferedSink$writeString$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = this;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.writeString(string, charset);
    return emitCompleteSegments();
  }
  
  @NotNull
  public BufferedSink writeString(@NotNull String string, int beginIndex, int endIndex, @NotNull Charset charset) {
    Intrinsics.checkNotNullParameter(string, "string");
    Intrinsics.checkNotNullParameter(charset, "charset");
    if (!(!this.closed ? 1 : 0)) {
      int $i$a$-check-RealBufferedSink$writeString$2 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = this;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.writeString(string, beginIndex, endIndex, charset);
    return emitCompleteSegments();
  }
  
  @NotNull
  public BufferedSink write(@NotNull byte[] source) {
    Intrinsics.checkNotNullParameter(source, "source");
    RealBufferedSink $this$commonWrite$iv = this;
    int $i$f$commonWrite = 0;
    if (!(!$this$commonWrite$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWrite$4$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv$iv = $this$commonWrite$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.write(source);
    return $this$commonWrite$iv.emitCompleteSegments();
  }
  
  @NotNull
  public BufferedSink write(@NotNull byte[] source, int offset, int byteCount) {
    Intrinsics.checkNotNullParameter(source, "source");
    RealBufferedSink $this$commonWrite$iv = this;
    int $i$f$commonWrite = 0;
    if (!(!$this$commonWrite$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWrite$5$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv$iv = $this$commonWrite$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.write(source, offset, byteCount);
    return $this$commonWrite$iv.emitCompleteSegments();
  }
  
  public int write(@NotNull ByteBuffer source) {
    Intrinsics.checkNotNullParameter(source, "source");
    if (!(!this.closed ? 1 : 0)) {
      int $i$a$-check-RealBufferedSink$write$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv = this;
    int $i$f$getBuffer = 0, result = this_$iv.bufferField.write(source);
    emitCompleteSegments();
    return result;
  }
  
  public long writeAll(@NotNull Source source) {
    Intrinsics.checkNotNullParameter(source, "source");
    RealBufferedSink $this$commonWriteAll$iv = this;
    int $i$f$commonWriteAll = 0;
    long totalBytesRead$iv = 0L;
    while (true) {
      RealBufferedSink this_$iv$iv = $this$commonWriteAll$iv;
      int $i$f$getBuffer = 0;
      long readCount$iv = source.read(
          this_$iv$iv.bufferField, 8192L);
      if (readCount$iv != -1L) {
        totalBytesRead$iv += readCount$iv;
        $this$commonWriteAll$iv.emitCompleteSegments();
        continue;
      } 
      break;
    } 
    return totalBytesRead$iv;
  }
  
  @NotNull
  public BufferedSink write(@NotNull Source source, long byteCount) {
    Intrinsics.checkNotNullParameter(source, "source");
    RealBufferedSink $this$commonWrite$iv = this;
    int $i$f$commonWrite = 0;
    long byteCount$iv = byteCount;
    while (byteCount$iv > 0L) {
      RealBufferedSink this_$iv$iv = $this$commonWrite$iv;
      int $i$f$getBuffer = 0;
      long read$iv = source.read(
          this_$iv$iv.bufferField, byteCount$iv);
      if (read$iv == -1L)
        throw new EOFException(); 
      byteCount$iv -= read$iv;
      $this$commonWrite$iv.emitCompleteSegments();
    } 
    return $this$commonWrite$iv;
  }
  
  @NotNull
  public BufferedSink writeByte(int b) {
    RealBufferedSink $this$commonWriteByte$iv = this;
    int $i$f$commonWriteByte = 0;
    if (!(!$this$commonWriteByte$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteByte$1$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv$iv = $this$commonWriteByte$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.writeByte(b);
    return $this$commonWriteByte$iv.emitCompleteSegments();
  }
  
  @NotNull
  public BufferedSink writeShort(int s) {
    RealBufferedSink $this$commonWriteShort$iv = this;
    int $i$f$commonWriteShort = 0;
    if (!(!$this$commonWriteShort$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteShort$1$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv$iv = $this$commonWriteShort$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.writeShort(s);
    return $this$commonWriteShort$iv.emitCompleteSegments();
  }
  
  @NotNull
  public BufferedSink writeShortLe(int s) {
    RealBufferedSink $this$commonWriteShortLe$iv = this;
    int $i$f$commonWriteShortLe = 0;
    if (!(!$this$commonWriteShortLe$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteShortLe$1$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv$iv = $this$commonWriteShortLe$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.writeShortLe(s);
    return $this$commonWriteShortLe$iv.emitCompleteSegments();
  }
  
  @NotNull
  public BufferedSink writeInt(int i) {
    RealBufferedSink $this$commonWriteInt$iv = this;
    int $i$f$commonWriteInt = 0;
    if (!(!$this$commonWriteInt$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteInt$1$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv$iv = $this$commonWriteInt$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.writeInt(i);
    return $this$commonWriteInt$iv.emitCompleteSegments();
  }
  
  @NotNull
  public BufferedSink writeIntLe(int i) {
    RealBufferedSink $this$commonWriteIntLe$iv = this;
    int $i$f$commonWriteIntLe = 0;
    if (!(!$this$commonWriteIntLe$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteIntLe$1$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv$iv = $this$commonWriteIntLe$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.writeIntLe(i);
    return $this$commonWriteIntLe$iv.emitCompleteSegments();
  }
  
  @NotNull
  public BufferedSink writeLong(long v) {
    RealBufferedSink $this$commonWriteLong$iv = this;
    int $i$f$commonWriteLong = 0;
    if (!(!$this$commonWriteLong$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteLong$1$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv$iv = $this$commonWriteLong$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.writeLong(v);
    return $this$commonWriteLong$iv.emitCompleteSegments();
  }
  
  @NotNull
  public BufferedSink writeLongLe(long v) {
    RealBufferedSink $this$commonWriteLongLe$iv = this;
    int $i$f$commonWriteLongLe = 0;
    if (!(!$this$commonWriteLongLe$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteLongLe$1$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv$iv = $this$commonWriteLongLe$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.writeLongLe(v);
    return $this$commonWriteLongLe$iv.emitCompleteSegments();
  }
  
  @NotNull
  public BufferedSink writeDecimalLong(long v) {
    RealBufferedSink $this$commonWriteDecimalLong$iv = this;
    int $i$f$commonWriteDecimalLong = 0;
    if (!(!$this$commonWriteDecimalLong$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteDecimalLong$1$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv$iv = $this$commonWriteDecimalLong$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.writeDecimalLong(v);
    return $this$commonWriteDecimalLong$iv.emitCompleteSegments();
  }
  
  @NotNull
  public BufferedSink writeHexadecimalUnsignedLong(long v) {
    RealBufferedSink $this$commonWriteHexadecimalUnsignedLong$iv = this;
    int $i$f$commonWriteHexadecimalUnsignedLong = 0;
    if (!(!$this$commonWriteHexadecimalUnsignedLong$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonWriteHexadecimalUnsignedLong$1$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv$iv = $this$commonWriteHexadecimalUnsignedLong$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.writeHexadecimalUnsignedLong(v);
    return $this$commonWriteHexadecimalUnsignedLong$iv.emitCompleteSegments();
  }
  
  @NotNull
  public BufferedSink emitCompleteSegments() {
    // Byte code:
    //   0: aload_0
    //   1: astore_1
    //   2: iconst_0
    //   3: istore_2
    //   4: aload_1
    //   5: getfield closed : Z
    //   8: ifne -> 15
    //   11: iconst_1
    //   12: goto -> 16
    //   15: iconst_0
    //   16: ifne -> 36
    //   19: iconst_0
    //   20: istore_3
    //   21: ldc 'closed'
    //   23: astore_3
    //   24: new java/lang/IllegalStateException
    //   27: dup
    //   28: aload_3
    //   29: invokevirtual toString : ()Ljava/lang/String;
    //   32: invokespecial <init> : (Ljava/lang/String;)V
    //   35: athrow
    //   36: aload_1
    //   37: astore #4
    //   39: iconst_0
    //   40: istore #5
    //   42: aload #4
    //   44: getfield bufferField : Lokio/Buffer;
    //   47: invokevirtual completeSegmentByteCount : ()J
    //   50: lstore #6
    //   52: lload #6
    //   54: lconst_0
    //   55: lcmp
    //   56: ifle -> 81
    //   59: aload_1
    //   60: getfield sink : Lokio/Sink;
    //   63: aload_1
    //   64: astore #4
    //   66: iconst_0
    //   67: istore #5
    //   69: aload #4
    //   71: getfield bufferField : Lokio/Buffer;
    //   74: lload #6
    //   76: invokeinterface write : (Lokio/Buffer;J)V
    //   81: aload_1
    //   82: checkcast okio/BufferedSink
    //   85: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #104	-> 0
    //   #252	-> 4
    //   #253	-> 19
    //   #252	-> 21
    //   #252	-> 23
    //   #254	-> 36
    //   #255	-> 42
    //   #254	-> 47
    //   #256	-> 52
    //   #255	-> 69
    //   #256	-> 74
    //   #257	-> 81
    //   #104	-> 85
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   21	2	3	$i$a$-check--RealBufferedSink$commonEmitCompleteSegments$1$iv	I
    //   42	5	5	$i$f$getBuffer	I
    //   39	8	4	this_$iv$iv	Lokio/RealBufferedSink;
    //   69	5	5	$i$f$getBuffer	I
    //   66	8	4	this_$iv$iv	Lokio/RealBufferedSink;
    //   4	81	2	$i$f$commonEmitCompleteSegments	I
    //   52	33	6	byteCount$iv	J
    //   2	83	1	$this$commonEmitCompleteSegments$iv	Lokio/RealBufferedSink;
    //   0	86	0	this	Lokio/RealBufferedSink;
  }
  
  @NotNull
  public BufferedSink emit() {
    // Byte code:
    //   0: aload_0
    //   1: astore_1
    //   2: iconst_0
    //   3: istore_2
    //   4: aload_1
    //   5: getfield closed : Z
    //   8: ifne -> 15
    //   11: iconst_1
    //   12: goto -> 16
    //   15: iconst_0
    //   16: ifne -> 36
    //   19: iconst_0
    //   20: istore_3
    //   21: ldc 'closed'
    //   23: astore_3
    //   24: new java/lang/IllegalStateException
    //   27: dup
    //   28: aload_3
    //   29: invokevirtual toString : ()Ljava/lang/String;
    //   32: invokespecial <init> : (Ljava/lang/String;)V
    //   35: athrow
    //   36: aload_1
    //   37: astore #4
    //   39: iconst_0
    //   40: istore #5
    //   42: aload #4
    //   44: getfield bufferField : Lokio/Buffer;
    //   47: invokevirtual size : ()J
    //   50: lstore #6
    //   52: lload #6
    //   54: lconst_0
    //   55: lcmp
    //   56: ifle -> 81
    //   59: aload_1
    //   60: getfield sink : Lokio/Sink;
    //   63: aload_1
    //   64: astore #4
    //   66: iconst_0
    //   67: istore #5
    //   69: aload #4
    //   71: getfield bufferField : Lokio/Buffer;
    //   74: lload #6
    //   76: invokeinterface write : (Lokio/Buffer;J)V
    //   81: aload_1
    //   82: checkcast okio/BufferedSink
    //   85: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #105	-> 0
    //   #258	-> 4
    //   #259	-> 19
    //   #258	-> 21
    //   #258	-> 23
    //   #260	-> 36
    //   #261	-> 42
    //   #260	-> 47
    //   #262	-> 52
    //   #261	-> 69
    //   #262	-> 74
    //   #263	-> 81
    //   #105	-> 85
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   21	2	3	$i$a$-check--RealBufferedSink$commonEmit$1$iv	I
    //   42	5	5	$i$f$getBuffer	I
    //   39	8	4	this_$iv$iv	Lokio/RealBufferedSink;
    //   69	5	5	$i$f$getBuffer	I
    //   66	8	4	this_$iv$iv	Lokio/RealBufferedSink;
    //   4	81	2	$i$f$commonEmit	I
    //   52	33	6	byteCount$iv	J
    //   2	83	1	$this$commonEmit$iv	Lokio/RealBufferedSink;
    //   0	86	0	this	Lokio/RealBufferedSink;
  }
  
  @NotNull
  public OutputStream outputStream() {
    return new RealBufferedSink$outputStream$1();
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000'\n\000\n\002\030\002\n\002\020\002\n\002\b\003\n\002\020\016\n\002\b\002\n\002\020\022\n\000\n\002\020\b\n\002\b\007*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\026¢\006\004\b\003\020\004J\017\020\005\032\0020\002H\026¢\006\004\b\005\020\004J\017\020\007\032\0020\006H\026¢\006\004\b\007\020\bJ'\020\016\032\0020\0022\006\020\n\032\0020\t2\006\020\f\032\0020\0132\006\020\r\032\0020\013H\026¢\006\004\b\016\020\017J\027\020\016\032\0020\0022\006\020\020\032\0020\013H\026¢\006\004\b\016\020\021¨\006\022"}, d2 = {"okio/RealBufferedSink.outputStream.1", "Ljava/io/OutputStream;", "", "close", "()V", "flush", "", "toString", "()Ljava/lang/String;", "", "data", "", "offset", "byteCount", "write", "([BII)V", "b", "(I)V", "okio"})
  @SourceDebugExtension({"SMAP\nRealBufferedSink.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealBufferedSink.kt\nokio/RealBufferedSink$outputStream$1\n+ 2 RealBufferedSink.kt\nokio/RealBufferedSink\n*L\n1#1,142:1\n51#2:143\n51#2:144\n*S KotlinDebug\n*F\n+ 1 RealBufferedSink.kt\nokio/RealBufferedSink$outputStream$1\n*L\n111#1:143\n117#1:144\n*E\n"})
  public static final class RealBufferedSink$outputStream$1 extends OutputStream {
    public void write(int b) {
      if (RealBufferedSink.this.closed)
        throw new IOException("closed"); 
      RealBufferedSink this_$iv = RealBufferedSink.this;
      int $i$f$getBuffer = 0;
      this_$iv.bufferField.writeByte((byte)b);
      RealBufferedSink.this.emitCompleteSegments();
    }
    
    public void write(@NotNull byte[] data, int offset, int byteCount) {
      Intrinsics.checkNotNullParameter(data, "data");
      if (RealBufferedSink.this.closed)
        throw new IOException("closed"); 
      RealBufferedSink this_$iv = RealBufferedSink.this;
      int $i$f$getBuffer = 0;
      this_$iv.bufferField.write(data, offset, byteCount);
      RealBufferedSink.this.emitCompleteSegments();
    }
    
    public void flush() {
      if (!RealBufferedSink.this.closed)
        RealBufferedSink.this.flush(); 
    }
    
    public void close() {
      RealBufferedSink.this.close();
    }
    
    @NotNull
    public String toString() {
      return RealBufferedSink.this + ".outputStream()";
    }
  }
  
  public void flush() {
    RealBufferedSink $this$commonFlush$iv = this;
    int $i$f$commonFlush = 0;
    if (!(!$this$commonFlush$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSink$commonFlush$1$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSink this_$iv$iv = $this$commonFlush$iv;
    int $i$f$getBuffer = 0;
    if (this_$iv$iv.bufferField.size() > 0L) {
      this_$iv$iv = $this$commonFlush$iv;
      $i$f$getBuffer = 0;
      this_$iv$iv = $this$commonFlush$iv;
      $i$f$getBuffer = 0;
      $this$commonFlush$iv.sink.write(this_$iv$iv.bufferField, this_$iv$iv.bufferField.size());
    } 
    $this$commonFlush$iv.sink.flush();
  }
  
  public boolean isOpen() {
    return !this.closed;
  }
  
  public void close() {
    RealBufferedSink $this$commonClose$iv = this;
    int $i$f$commonClose = 0;
    if (!$this$commonClose$iv.closed) {
      Throwable thrown$iv = null;
      try {
        RealBufferedSink this_$iv$iv = $this$commonClose$iv;
        int $i$f$getBuffer = 0;
        if (this_$iv$iv.bufferField.size() > 0L) {
          this_$iv$iv = $this$commonClose$iv;
          $i$f$getBuffer = 0;
          this_$iv$iv = $this$commonClose$iv;
          $i$f$getBuffer = 0;
          $this$commonClose$iv.sink.write(this_$iv$iv.bufferField, this_$iv$iv.bufferField.size());
        } 
      } catch (Throwable e$iv) {
        thrown$iv = e$iv;
      } 
      try {
        $this$commonClose$iv.sink.close();
      } catch (Throwable e$iv) {
        if (thrown$iv == null)
          thrown$iv = e$iv; 
      } 
      $this$commonClose$iv.closed = true;
      if (thrown$iv != null)
        throw thrown$iv; 
    } 
  }
  
  @NotNull
  public Timeout timeout() {
    RealBufferedSink $this$commonTimeout$iv = this;
    int $i$f$commonTimeout = 0;
    return $this$commonTimeout$iv.sink.timeout();
  }
  
  @NotNull
  public String toString() {
    RealBufferedSink $this$commonToString$iv = this;
    int $i$f$commonToString = 0;
    return "buffer(" + $this$commonToString$iv.sink + ')';
  }
}
