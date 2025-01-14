package okio;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000~\n\002\030\002\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\002\n\002\020\005\n\000\n\002\020\t\n\002\b\006\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\007\n\002\020\b\n\002\b\003\n\002\020\022\n\002\b\004\n\002\030\002\n\002\b\f\n\002\020\002\n\002\b\t\n\002\020\n\n\002\b\003\n\002\030\002\n\000\n\002\020\016\n\002\b\016\n\002\030\002\n\002\b\005\n\002\030\002\n\000\bv\030\0002\0020\0012\0020\002J\017\020\004\032\0020\003H'¢\006\004\b\004\020\005J\017\020\007\032\0020\006H&¢\006\004\b\007\020\bJ\027\020\f\032\0020\0132\006\020\n\032\0020\tH&¢\006\004\b\f\020\rJ\037\020\f\032\0020\0132\006\020\n\032\0020\t2\006\020\016\032\0020\013H&¢\006\004\b\f\020\017J'\020\f\032\0020\0132\006\020\n\032\0020\t2\006\020\016\032\0020\0132\006\020\020\032\0020\013H&¢\006\004\b\f\020\021J\027\020\f\032\0020\0132\006\020\023\032\0020\022H&¢\006\004\b\f\020\024J\037\020\f\032\0020\0132\006\020\023\032\0020\0222\006\020\016\032\0020\013H&¢\006\004\b\f\020\025J\027\020\027\032\0020\0132\006\020\026\032\0020\022H&¢\006\004\b\027\020\024J\037\020\027\032\0020\0132\006\020\026\032\0020\0222\006\020\016\032\0020\013H&¢\006\004\b\027\020\025J\017\020\031\032\0020\030H&¢\006\004\b\031\020\032J\017\020\033\032\0020\000H&¢\006\004\b\033\020\034J\037\020\036\032\0020\0062\006\020\035\032\0020\0132\006\020\023\032\0020\022H&¢\006\004\b\036\020\037J/\020\036\032\0020\0062\006\020\035\032\0020\0132\006\020\023\032\0020\0222\006\020!\032\0020 2\006\020\"\032\0020 H&¢\006\004\b\036\020#J\027\020&\032\0020 2\006\020%\032\0020$H&¢\006\004\b&\020'J'\020&\032\0020 2\006\020%\032\0020$2\006\020\035\032\0020 2\006\020\"\032\0020 H&¢\006\004\b&\020(J\027\020*\032\0020\0132\006\020%\032\0020)H&¢\006\004\b*\020+J\017\020,\032\0020\tH&¢\006\004\b,\020-J\017\020.\032\0020$H&¢\006\004\b.\020/J\027\020.\032\0020$2\006\020\"\032\0020\013H&¢\006\004\b.\0200J\017\0201\032\0020\022H&¢\006\004\b1\0202J\027\0201\032\0020\0222\006\020\"\032\0020\013H&¢\006\004\b1\0203J\017\0204\032\0020\013H&¢\006\004\b4\0205J\027\0207\032\002062\006\020%\032\0020$H&¢\006\004\b7\0208J\037\0207\032\002062\006\020%\032\0020\0032\006\020\"\032\0020\013H&¢\006\004\b7\0209J\017\020:\032\0020\013H&¢\006\004\b:\0205J\017\020;\032\0020 H&¢\006\004\b;\020<J\017\020=\032\0020 H&¢\006\004\b=\020<J\017\020>\032\0020\013H&¢\006\004\b>\0205J\017\020?\032\0020\013H&¢\006\004\b?\0205J\017\020A\032\0020@H&¢\006\004\bA\020BJ\017\020C\032\0020@H&¢\006\004\bC\020BJ\027\020G\032\0020F2\006\020E\032\0020DH&¢\006\004\bG\020HJ\037\020G\032\0020F2\006\020\"\032\0020\0132\006\020E\032\0020DH&¢\006\004\bG\020IJ\017\020J\032\0020FH&¢\006\004\bJ\020KJ\027\020J\032\0020F2\006\020\"\032\0020\013H&¢\006\004\bJ\020LJ\017\020M\032\0020 H&¢\006\004\bM\020<J\021\020N\032\004\030\0010FH&¢\006\004\bN\020KJ\017\020O\032\0020FH&¢\006\004\bO\020KJ\027\020O\032\0020F2\006\020P\032\0020\013H&¢\006\004\bO\020LJ\027\020Q\032\0020\0062\006\020\"\032\0020\013H&¢\006\004\bQ\020RJ\027\020S\032\002062\006\020\"\032\0020\013H&¢\006\004\bS\020TJ\027\020W\032\0020 2\006\020V\032\0020UH&¢\006\004\bW\020XJ\027\020Y\032\002062\006\020\"\032\0020\013H&¢\006\004\bY\020TR\024\020\004\032\0020\0038&X¦\004¢\006\006\032\004\bZ\020\005\001\002\003[ø\001\000\002\006\n\004\b!0\001¨\006\\À\006\001"}, d2 = {"Lokio/BufferedSource;", "Lokio/Source;", "Ljava/nio/channels/ReadableByteChannel;", "Lokio/Buffer;", "buffer", "()Lokio/Buffer;", "", "exhausted", "()Z", "", "b", "", "indexOf", "(B)J", "fromIndex", "(BJ)J", "toIndex", "(BJJ)J", "Lokio/ByteString;", "bytes", "(Lokio/ByteString;)J", "(Lokio/ByteString;J)J", "targetBytes", "indexOfElement", "Ljava/io/InputStream;", "inputStream", "()Ljava/io/InputStream;", "peek", "()Lokio/BufferedSource;", "offset", "rangeEquals", "(JLokio/ByteString;)Z", "", "bytesOffset", "byteCount", "(JLokio/ByteString;II)Z", "", "sink", "read", "([B)I", "([BII)I", "Lokio/Sink;", "readAll", "(Lokio/Sink;)J", "readByte", "()B", "readByteArray", "()[B", "(J)[B", "readByteString", "()Lokio/ByteString;", "(J)Lokio/ByteString;", "readDecimalLong", "()J", "", "readFully", "([B)V", "(Lokio/Buffer;J)V", "readHexadecimalUnsignedLong", "readInt", "()I", "readIntLe", "readLong", "readLongLe", "", "readShort", "()S", "readShortLe", "Ljava/nio/charset/Charset;", "charset", "", "readString", "(Ljava/nio/charset/Charset;)Ljava/lang/String;", "(JLjava/nio/charset/Charset;)Ljava/lang/String;", "readUtf8", "()Ljava/lang/String;", "(J)Ljava/lang/String;", "readUtf8CodePoint", "readUtf8Line", "readUtf8LineStrict", "limit", "request", "(J)Z", "require", "(J)V", "Lokio/Options;", "options", "select", "(Lokio/Options;)I", "skip", "getBuffer", "Lokio/RealBufferedSource;", "okio"})
public interface BufferedSource extends Source, ReadableByteChannel {
  @Deprecated(message = "moved to val: use getBuffer() instead", replaceWith = @ReplaceWith(expression = "buffer", imports = {}), level = DeprecationLevel.WARNING)
  @NotNull
  Buffer buffer();
  
  @NotNull
  Buffer getBuffer();
  
  boolean exhausted() throws IOException;
  
  void require(long paramLong) throws IOException;
  
  boolean request(long paramLong) throws IOException;
  
  byte readByte() throws IOException;
  
  short readShort() throws IOException;
  
  short readShortLe() throws IOException;
  
  int readInt() throws IOException;
  
  int readIntLe() throws IOException;
  
  long readLong() throws IOException;
  
  long readLongLe() throws IOException;
  
  long readDecimalLong() throws IOException;
  
  long readHexadecimalUnsignedLong() throws IOException;
  
  void skip(long paramLong) throws IOException;
  
  @NotNull
  ByteString readByteString() throws IOException;
  
  @NotNull
  ByteString readByteString(long paramLong) throws IOException;
  
  int select(@NotNull Options paramOptions) throws IOException;
  
  @NotNull
  byte[] readByteArray() throws IOException;
  
  @NotNull
  byte[] readByteArray(long paramLong) throws IOException;
  
  int read(@NotNull byte[] paramArrayOfbyte) throws IOException;
  
  void readFully(@NotNull byte[] paramArrayOfbyte) throws IOException;
  
  int read(@NotNull byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  void readFully(@NotNull Buffer paramBuffer, long paramLong) throws IOException;
  
  long readAll(@NotNull Sink paramSink) throws IOException;
  
  @NotNull
  String readUtf8() throws IOException;
  
  @NotNull
  String readUtf8(long paramLong) throws IOException;
  
  @Nullable
  String readUtf8Line() throws IOException;
  
  @NotNull
  String readUtf8LineStrict() throws IOException;
  
  @NotNull
  String readUtf8LineStrict(long paramLong) throws IOException;
  
  int readUtf8CodePoint() throws IOException;
  
  @NotNull
  String readString(@NotNull Charset paramCharset) throws IOException;
  
  @NotNull
  String readString(long paramLong, @NotNull Charset paramCharset) throws IOException;
  
  long indexOf(byte paramByte) throws IOException;
  
  long indexOf(byte paramByte, long paramLong) throws IOException;
  
  long indexOf(byte paramByte, long paramLong1, long paramLong2) throws IOException;
  
  long indexOf(@NotNull ByteString paramByteString) throws IOException;
  
  long indexOf(@NotNull ByteString paramByteString, long paramLong) throws IOException;
  
  long indexOfElement(@NotNull ByteString paramByteString) throws IOException;
  
  long indexOfElement(@NotNull ByteString paramByteString, long paramLong) throws IOException;
  
  boolean rangeEquals(long paramLong, @NotNull ByteString paramByteString) throws IOException;
  
  boolean rangeEquals(long paramLong, @NotNull ByteString paramByteString, int paramInt1, int paramInt2) throws IOException;
  
  @NotNull
  BufferedSource peek();
  
  @NotNull
  InputStream inputStream();
}
