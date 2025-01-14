package okio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000\\\n\002\030\002\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\005\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\022\n\002\b\003\n\002\020\b\n\002\b\003\n\002\030\002\n\002\b\003\n\002\030\002\n\002\020\t\n\002\b\022\n\002\020\016\n\000\n\002\030\002\n\002\b\f\n\002\030\002\n\000\bv\030\0002\0020\0012\0020\002J\017\020\004\032\0020\003H'¢\006\004\b\004\020\005J\017\020\006\032\0020\000H&¢\006\004\b\006\020\007J\017\020\b\032\0020\000H&¢\006\004\b\b\020\007J\017\020\n\032\0020\tH&¢\006\004\b\n\020\013J\017\020\r\032\0020\fH&¢\006\004\b\r\020\016J\027\020\021\032\0020\0002\006\020\020\032\0020\017H&¢\006\004\b\021\020\022J'\020\021\032\0020\0002\006\020\020\032\0020\0172\006\020\024\032\0020\0232\006\020\025\032\0020\023H&¢\006\004\b\021\020\026J\027\020\021\032\0020\0002\006\020\030\032\0020\027H&¢\006\004\b\021\020\031J'\020\021\032\0020\0002\006\020\030\032\0020\0272\006\020\024\032\0020\0232\006\020\025\032\0020\023H&¢\006\004\b\021\020\032J\037\020\021\032\0020\0002\006\020\020\032\0020\0332\006\020\025\032\0020\034H&¢\006\004\b\021\020\035J\027\020\036\032\0020\0342\006\020\020\032\0020\033H&¢\006\004\b\036\020\037J\027\020!\032\0020\0002\006\020 \032\0020\023H&¢\006\004\b!\020\"J\027\020$\032\0020\0002\006\020#\032\0020\034H&¢\006\004\b$\020%J\027\020&\032\0020\0002\006\020#\032\0020\034H&¢\006\004\b&\020%J\027\020(\032\0020\0002\006\020'\032\0020\023H&¢\006\004\b(\020\"J\027\020)\032\0020\0002\006\020'\032\0020\023H&¢\006\004\b)\020\"J\027\020*\032\0020\0002\006\020#\032\0020\034H&¢\006\004\b*\020%J\027\020+\032\0020\0002\006\020#\032\0020\034H&¢\006\004\b+\020%J\027\020-\032\0020\0002\006\020,\032\0020\023H&¢\006\004\b-\020\"J\027\020.\032\0020\0002\006\020,\032\0020\023H&¢\006\004\b.\020\"J\037\0203\032\0020\0002\006\0200\032\0020/2\006\0202\032\00201H&¢\006\004\b3\0204J/\0203\032\0020\0002\006\0200\032\0020/2\006\0205\032\0020\0232\006\0206\032\0020\0232\006\0202\032\00201H&¢\006\004\b3\0207J\027\0208\032\0020\0002\006\0200\032\0020/H&¢\006\004\b8\0209J'\0208\032\0020\0002\006\0200\032\0020/2\006\0205\032\0020\0232\006\0206\032\0020\023H&¢\006\004\b8\020:J\027\020<\032\0020\0002\006\020;\032\0020\023H&¢\006\004\b<\020\"R\024\020\004\032\0020\0038&X¦\004¢\006\006\032\004\b=\020\005\001\002\003>ø\001\000\002\006\n\004\b!0\001¨\006?À\006\001"}, d2 = {"Lokio/BufferedSink;", "Lokio/Sink;", "Ljava/nio/channels/WritableByteChannel;", "Lokio/Buffer;", "buffer", "()Lokio/Buffer;", "emit", "()Lokio/BufferedSink;", "emitCompleteSegments", "", "flush", "()V", "Ljava/io/OutputStream;", "outputStream", "()Ljava/io/OutputStream;", "", "source", "write", "([B)Lokio/BufferedSink;", "", "offset", "byteCount", "([BII)Lokio/BufferedSink;", "Lokio/ByteString;", "byteString", "(Lokio/ByteString;)Lokio/BufferedSink;", "(Lokio/ByteString;II)Lokio/BufferedSink;", "Lokio/Source;", "", "(Lokio/Source;J)Lokio/BufferedSink;", "writeAll", "(Lokio/Source;)J", "b", "writeByte", "(I)Lokio/BufferedSink;", "v", "writeDecimalLong", "(J)Lokio/BufferedSink;", "writeHexadecimalUnsignedLong", "i", "writeInt", "writeIntLe", "writeLong", "writeLongLe", "s", "writeShort", "writeShortLe", "", "string", "Ljava/nio/charset/Charset;", "charset", "writeString", "(Ljava/lang/String;Ljava/nio/charset/Charset;)Lokio/BufferedSink;", "beginIndex", "endIndex", "(Ljava/lang/String;IILjava/nio/charset/Charset;)Lokio/BufferedSink;", "writeUtf8", "(Ljava/lang/String;)Lokio/BufferedSink;", "(Ljava/lang/String;II)Lokio/BufferedSink;", "codePoint", "writeUtf8CodePoint", "getBuffer", "Lokio/RealBufferedSink;", "okio"})
public interface BufferedSink extends Sink, WritableByteChannel {
  @Deprecated(message = "moved to val: use getBuffer() instead", replaceWith = @ReplaceWith(expression = "buffer", imports = {}), level = DeprecationLevel.WARNING)
  @NotNull
  Buffer buffer();
  
  @NotNull
  Buffer getBuffer();
  
  @NotNull
  BufferedSink write(@NotNull ByteString paramByteString) throws IOException;
  
  @NotNull
  BufferedSink write(@NotNull ByteString paramByteString, int paramInt1, int paramInt2) throws IOException;
  
  @NotNull
  BufferedSink write(@NotNull byte[] paramArrayOfbyte) throws IOException;
  
  @NotNull
  BufferedSink write(@NotNull byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  long writeAll(@NotNull Source paramSource) throws IOException;
  
  @NotNull
  BufferedSink write(@NotNull Source paramSource, long paramLong) throws IOException;
  
  @NotNull
  BufferedSink writeUtf8(@NotNull String paramString) throws IOException;
  
  @NotNull
  BufferedSink writeUtf8(@NotNull String paramString, int paramInt1, int paramInt2) throws IOException;
  
  @NotNull
  BufferedSink writeUtf8CodePoint(int paramInt) throws IOException;
  
  @NotNull
  BufferedSink writeString(@NotNull String paramString, @NotNull Charset paramCharset) throws IOException;
  
  @NotNull
  BufferedSink writeString(@NotNull String paramString, int paramInt1, int paramInt2, @NotNull Charset paramCharset) throws IOException;
  
  @NotNull
  BufferedSink writeByte(int paramInt) throws IOException;
  
  @NotNull
  BufferedSink writeShort(int paramInt) throws IOException;
  
  @NotNull
  BufferedSink writeShortLe(int paramInt) throws IOException;
  
  @NotNull
  BufferedSink writeInt(int paramInt) throws IOException;
  
  @NotNull
  BufferedSink writeIntLe(int paramInt) throws IOException;
  
  @NotNull
  BufferedSink writeLong(long paramLong) throws IOException;
  
  @NotNull
  BufferedSink writeLongLe(long paramLong) throws IOException;
  
  @NotNull
  BufferedSink writeDecimalLong(long paramLong) throws IOException;
  
  @NotNull
  BufferedSink writeHexadecimalUnsignedLong(long paramLong) throws IOException;
  
  void flush() throws IOException;
  
  @NotNull
  BufferedSink emit() throws IOException;
  
  @NotNull
  BufferedSink emitCompleteSegments() throws IOException;
  
  @NotNull
  OutputStream outputStream();
}
