package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\\\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\013\n\002\b\003\n\002\030\002\n\000\n\002\020\002\n\002\b\006\n\002\020\b\n\000\n\002\030\002\n\002\b\016\n\002\030\002\n\000\n\002\020\022\n\002\b\003\n\002\020 \n\002\030\002\n\002\b\021\n\002\020\t\n\002\b\b\n\002\030\002\n\002\b\t\030\000 L2\0020\001:\001LB\027\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007J\025\020\013\032\0020\n2\006\020\t\032\0020\b¢\006\004\b\013\020\fJ\017\020\r\032\0020\nH\026¢\006\004\b\r\020\016J\r\020\017\032\0020\n¢\006\004\b\017\020\016J/\020\026\032\0020\n2\006\020\020\032\0020\0042\006\020\022\032\0020\0212\b\020\024\032\004\030\0010\0232\006\020\025\032\0020\021¢\006\004\b\026\020\027J/\020\032\032\0020\n2\006\020\022\032\0020\0212\006\020\030\032\0020\0212\b\020\031\032\004\030\0010\0232\006\020\025\032\0020\021¢\006\004\b\032\020\033J\r\020\034\032\0020\n¢\006\004\b\034\020\016J-\020\037\032\0020\n2\006\020\022\032\0020\0212\006\020\035\032\0020\0212\006\020\036\032\0020\0212\006\020\030\032\0020\021¢\006\004\b\037\020 J%\020&\032\0020\n2\006\020!\032\0020\0212\006\020#\032\0020\"2\006\020%\032\0020$¢\006\004\b&\020'J+\020+\032\0020\n2\006\020\020\032\0020\0042\006\020\022\032\0020\0212\f\020*\032\b\022\004\022\0020)0(¢\006\004\b+\020,J\r\020-\032\0020\021¢\006\004\b-\020.J%\0202\032\0020\n2\006\020/\032\0020\0042\006\0200\032\0020\0212\006\0201\032\0020\021¢\006\004\b2\0203J+\0206\032\0020\n2\006\020\022\032\0020\0212\006\0204\032\0020\0212\f\0205\032\b\022\004\022\0020)0(¢\006\004\b6\0207J\035\0208\032\0020\n2\006\020\022\032\0020\0212\006\020#\032\0020\"¢\006\004\b8\0209J\025\020:\032\0020\n2\006\020:\032\0020\b¢\006\004\b:\020\fJ\035\020=\032\0020\n2\006\020\022\032\0020\0212\006\020<\032\0020;¢\006\004\b=\020>J\037\020?\032\0020\n2\006\020\022\032\0020\0212\006\020\025\032\0020;H\002¢\006\004\b?\020>R\024\020\005\032\0020\0048\002X\004¢\006\006\n\004\b\005\020@R\026\020A\032\0020\0048\002@\002X\016¢\006\006\n\004\bA\020@R\024\020B\032\0020\0238\002X\004¢\006\006\n\004\bB\020CR\027\020E\032\0020D8\006¢\006\f\n\004\bE\020F\032\004\bG\020HR\026\020I\032\0020\0218\002@\002X\016¢\006\006\n\004\bI\020JR\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020K¨\006M"}, d2 = {"Lokhttp3/internal/http2/Http2Writer;", "Ljava/io/Closeable;", "Lokio/BufferedSink;", "sink", "", "client", "<init>", "(Lokio/BufferedSink;Z)V", "Lokhttp3/internal/http2/Settings;", "peerSettings", "", "applyAndAckSettings", "(Lokhttp3/internal/http2/Settings;)V", "close", "()V", "connectionPreface", "outFinished", "", "streamId", "Lokio/Buffer;", "source", "byteCount", "data", "(ZILokio/Buffer;I)V", "flags", "buffer", "dataFrame", "(IILokio/Buffer;I)V", "flush", "length", "type", "frameHeader", "(IIII)V", "lastGoodStreamId", "Lokhttp3/internal/http2/ErrorCode;", "errorCode", "", "debugData", "goAway", "(ILokhttp3/internal/http2/ErrorCode;[B)V", "", "Lokhttp3/internal/http2/Header;", "headerBlock", "headers", "(ZILjava/util/List;)V", "maxDataLength", "()I", "ack", "payload1", "payload2", "ping", "(ZII)V", "promisedStreamId", "requestHeaders", "pushPromise", "(IILjava/util/List;)V", "rstStream", "(ILokhttp3/internal/http2/ErrorCode;)V", "settings", "", "windowSizeIncrement", "windowUpdate", "(IJ)V", "writeContinuationFrames", "Z", "closed", "hpackBuffer", "Lokio/Buffer;", "Lokhttp3/internal/http2/Hpack$Writer;", "hpackWriter", "Lokhttp3/internal/http2/Hpack$Writer;", "getHpackWriter", "()Lokhttp3/internal/http2/Hpack$Writer;", "maxFrameSize", "I", "Lokio/BufferedSink;", "Companion", "okhttp"})
@SourceDebugExtension({"SMAP\nHttp2Writer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http2Writer.kt\nokhttp3/internal/http2/Http2Writer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,317:1\n1#2:318\n*E\n"})
public final class Http2Writer implements Closeable {
  public Http2Writer(@NotNull BufferedSink sink, boolean client) {
    this.sink = sink;
    this.client = client;
    this.hpackBuffer = new Buffer();
    this.maxFrameSize = 16384;
    this.hpackWriter = new Hpack.Writer(0, false, this.hpackBuffer, 3, null);
  }
  
  @NotNull
  public final Hpack.Writer getHpackWriter() {
    return this.hpackWriter;
  }
  
  public final synchronized void connectionPreface() throws IOException {
    if (this.closed)
      throw new IOException("closed"); 
    if (!this.client)
      return; 
    if (logger.isLoggable(Level.FINE))
      logger.fine(Util.format(">> CONNECTION " + Http2.CONNECTION_PREFACE.hex(), new Object[0])); 
    this.sink.write(Http2.CONNECTION_PREFACE);
    this.sink.flush();
  }
  
  public final synchronized void applyAndAckSettings(@NotNull Settings peerSettings) throws IOException {
    Intrinsics.checkNotNullParameter(peerSettings, "peerSettings");
    if (this.closed)
      throw new IOException("closed"); 
    this.maxFrameSize = peerSettings.getMaxFrameSize(this.maxFrameSize);
    if (peerSettings.getHeaderTableSize() != -1)
      this.hpackWriter.resizeHeaderTable(peerSettings.getHeaderTableSize()); 
    frameHeader(
        0, 
        0, 
        4, 
        1);
    this.sink.flush();
  }
  
  public final synchronized void pushPromise(int streamId, int promisedStreamId, @NotNull List<Header> requestHeaders) throws IOException {
    Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
    if (this.closed)
      throw new IOException("closed"); 
    this.hpackWriter.writeHeaders(requestHeaders);
    long byteCount = this.hpackBuffer.size();
    int length = (int)Math.min(this.maxFrameSize - 4L, byteCount);
    frameHeader(
        streamId, 
        length + 4, 
        5, 
        (byteCount == length) ? 4 : 0);
    this.sink.writeInt(promisedStreamId & Integer.MAX_VALUE);
    this.sink.write(this.hpackBuffer, length);
    if (byteCount > length)
      writeContinuationFrames(streamId, byteCount - length); 
  }
  
  public final synchronized void flush() throws IOException {
    if (this.closed)
      throw new IOException("closed"); 
    this.sink.flush();
  }
  
  public final synchronized void rstStream(int streamId, @NotNull ErrorCode errorCode) throws IOException {
    Intrinsics.checkNotNullParameter(errorCode, "errorCode");
    if (this.closed)
      throw new IOException("closed"); 
    if (!((errorCode.getHttpCode() != -1) ? 1 : 0)) {
      String str = "Failed requirement.";
      throw new IllegalArgumentException(str.toString());
    } 
    frameHeader(
        streamId, 
        4, 
        3, 
        0);
    this.sink.writeInt(errorCode.getHttpCode());
    this.sink.flush();
  }
  
  public final int maxDataLength() {
    return this.maxFrameSize;
  }
  
  public final synchronized void data(boolean outFinished, int streamId, @Nullable Buffer source, int byteCount) throws IOException {
    if (this.closed)
      throw new IOException("closed"); 
    int flags = 0;
    if (outFinished)
      flags |= 0x1; 
    dataFrame(streamId, flags, source, byteCount);
  }
  
  public final void dataFrame(int streamId, int flags, @Nullable Buffer buffer, int byteCount) throws IOException {
    frameHeader(
        streamId, 
        byteCount, 
        0, 
        flags);
    if (byteCount > 0) {
      Intrinsics.checkNotNull(buffer);
      this.sink.write(buffer, byteCount);
    } 
  }
  
  public final synchronized void settings(@NotNull Settings settings) throws IOException {
    Intrinsics.checkNotNullParameter(settings, "settings");
    if (this.closed)
      throw new IOException("closed"); 
    frameHeader(
        0, 
        settings.size() * 6, 
        4, 
        0);
    for (int i = 0; i < 10; i++) {
      if (settings.isSet(i)) {
        switch (i) {
          case 4:
          
          case 7:
          
          default:
            break;
        } 
        int id = 
          
          i;
        this.sink.writeShort(id);
        this.sink.writeInt(settings.get(i));
      } 
    } 
    this.sink.flush();
  }
  
  public final synchronized void ping(boolean ack, int payload1, int payload2) throws IOException {
    if (this.closed)
      throw new IOException("closed"); 
    frameHeader(
        0, 
        8, 
        6, 
        ack ? 1 : 0);
    this.sink.writeInt(payload1);
    this.sink.writeInt(payload2);
    this.sink.flush();
  }
  
  public final synchronized void goAway(int lastGoodStreamId, @NotNull ErrorCode errorCode, @NotNull byte[] debugData) throws IOException {
    Intrinsics.checkNotNullParameter(errorCode, "errorCode");
    Intrinsics.checkNotNullParameter(debugData, "debugData");
    if (this.closed)
      throw new IOException("closed"); 
    if (!((errorCode.getHttpCode() != -1) ? 1 : 0)) {
      int $i$a$-require-Http2Writer$goAway$1 = 0;
      String str = "errorCode.httpCode == -1";
      throw new IllegalArgumentException(str.toString());
    } 
    frameHeader(0, 8 + debugData.length, 7, 0);
    this.sink.writeInt(lastGoodStreamId);
    this.sink.writeInt(errorCode.getHttpCode());
    if (!((debugData.length == 0) ? 1 : 0))
      this.sink.write(debugData); 
    this.sink.flush();
  }
  
  public final synchronized void windowUpdate(int streamId, long windowSizeIncrement) throws IOException {
    if (this.closed)
      throw new IOException("closed"); 
    if (!((windowSizeIncrement != 0L && windowSizeIncrement <= 2147483647L) ? 1 : 0)) {
      int $i$a$-require-Http2Writer$windowUpdate$1 = 0;
      String str = "windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: " + windowSizeIncrement;
      throw new IllegalArgumentException(str.toString());
    } 
    frameHeader(streamId, 4, 8, 0);
    this.sink.writeInt((int)windowSizeIncrement);
    this.sink.flush();
  }
  
  public final void frameHeader(int streamId, int length, int type, int flags) throws IOException {
    if (logger.isLoggable(Level.FINE))
      logger.fine(Http2.INSTANCE.frameLog(false, streamId, length, type, flags)); 
    if (!((length <= this.maxFrameSize) ? 1 : 0)) {
      int $i$a$-require-Http2Writer$frameHeader$1 = 0;
      String str = "FRAME_SIZE_ERROR length > " + this.maxFrameSize + ": " + length;
      throw new IllegalArgumentException(str.toString());
    } 
    if (!(((streamId & Integer.MIN_VALUE) == 0) ? 1 : 0)) {
      int $i$a$-require-Http2Writer$frameHeader$2 = 0;
      String str = "reserved bit set: " + streamId;
      throw new IllegalArgumentException(str.toString());
    } 
    Util.writeMedium(this.sink, length);
    this.sink.writeByte(type & 0xFF);
    this.sink.writeByte(flags & 0xFF);
    this.sink.writeInt(streamId & Integer.MAX_VALUE);
  }
  
  public synchronized void close() throws IOException {
    this.closed = true;
    this.sink.close();
  }
  
  private final void writeContinuationFrames(int streamId, long byteCount) throws IOException {
    long l = byteCount;
    while (l > 0L) {
      long length = Math.min(this.maxFrameSize, l);
      l -= length;
      frameHeader(streamId, (int)length, 9, (l == 0L) ? 4 : 0);
      this.sink.write(this.hpackBuffer, length);
    } 
  }
  
  public final synchronized void headers(boolean outFinished, int streamId, @NotNull List<Header> headerBlock) throws IOException {
    Intrinsics.checkNotNullParameter(headerBlock, "headerBlock");
    if (this.closed)
      throw new IOException("closed"); 
    this.hpackWriter.writeHeaders(headerBlock);
    long byteCount = this.hpackBuffer.size();
    long length = Math.min(this.maxFrameSize, byteCount);
    int flags = (byteCount == length) ? 4 : 0;
    if (outFinished)
      flags |= 0x1; 
    frameHeader(streamId, (int)length, 1, flags);
    this.sink.write(this.hpackBuffer, length);
    if (byteCount > length)
      writeContinuationFrames(streamId, byteCount - length); 
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\004\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\034\020\006\032\n \005*\004\030\0010\0040\0048\002X\004¢\006\006\n\004\b\006\020\007¨\006\b"}, d2 = {"Lokhttp3/internal/http2/Http2Writer$Companion;", "", "<init>", "()V", "Ljava/util/logging/Logger;", "kotlin.jvm.PlatformType", "logger", "Ljava/util/logging/Logger;", "okhttp"})
  public static final class Companion {
    private Companion() {}
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final BufferedSink sink;
  
  private final boolean client;
  
  @NotNull
  private final Buffer hpackBuffer;
  
  private int maxFrameSize;
  
  private boolean closed;
  
  @NotNull
  private final Hpack.Writer hpackWriter;
  
  private static final Logger logger = Logger.getLogger(Http2.class.getName());
}
