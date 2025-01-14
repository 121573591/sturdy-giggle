package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000J\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\013\n\002\b\003\n\002\020\002\n\002\b\003\n\002\030\002\n\002\b\005\n\002\020\b\n\002\b\007\n\002\020 \n\002\030\002\n\002\b\013\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\007\030\000 .2\0020\001:\003./0B\027\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007J\017\020\t\032\0020\bH\026¢\006\004\b\t\020\nJ\035\020\016\032\0020\0042\006\020\013\032\0020\0042\006\020\r\032\0020\f¢\006\004\b\016\020\017J\025\020\020\032\0020\b2\006\020\r\032\0020\f¢\006\004\b\020\020\021J/\020\026\032\0020\b2\006\020\r\032\0020\f2\006\020\023\032\0020\0222\006\020\024\032\0020\0222\006\020\025\032\0020\022H\002¢\006\004\b\026\020\027J/\020\030\032\0020\b2\006\020\r\032\0020\f2\006\020\023\032\0020\0222\006\020\024\032\0020\0222\006\020\025\032\0020\022H\002¢\006\004\b\030\020\027J5\020\034\032\b\022\004\022\0020\0330\0322\006\020\023\032\0020\0222\006\020\031\032\0020\0222\006\020\024\032\0020\0222\006\020\025\032\0020\022H\002¢\006\004\b\034\020\035J/\020\036\032\0020\b2\006\020\r\032\0020\f2\006\020\023\032\0020\0222\006\020\024\032\0020\0222\006\020\025\032\0020\022H\002¢\006\004\b\036\020\027J/\020\037\032\0020\b2\006\020\r\032\0020\f2\006\020\023\032\0020\0222\006\020\024\032\0020\0222\006\020\025\032\0020\022H\002¢\006\004\b\037\020\027J\037\020 \032\0020\b2\006\020\r\032\0020\f2\006\020\025\032\0020\022H\002¢\006\004\b \020!J/\020 \032\0020\b2\006\020\r\032\0020\f2\006\020\023\032\0020\0222\006\020\024\032\0020\0222\006\020\025\032\0020\022H\002¢\006\004\b \020\027J/\020\"\032\0020\b2\006\020\r\032\0020\f2\006\020\023\032\0020\0222\006\020\024\032\0020\0222\006\020\025\032\0020\022H\002¢\006\004\b\"\020\027J/\020#\032\0020\b2\006\020\r\032\0020\f2\006\020\023\032\0020\0222\006\020\024\032\0020\0222\006\020\025\032\0020\022H\002¢\006\004\b#\020\027J/\020$\032\0020\b2\006\020\r\032\0020\f2\006\020\023\032\0020\0222\006\020\024\032\0020\0222\006\020\025\032\0020\022H\002¢\006\004\b$\020\027J/\020%\032\0020\b2\006\020\r\032\0020\f2\006\020\023\032\0020\0222\006\020\024\032\0020\0222\006\020\025\032\0020\022H\002¢\006\004\b%\020\027R\024\020\005\032\0020\0048\002X\004¢\006\006\n\004\b\005\020&R\024\020(\032\0020'8\002X\004¢\006\006\n\004\b(\020)R\024\020+\032\0020*8\002X\004¢\006\006\n\004\b+\020,R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020-¨\0061"}, d2 = {"Lokhttp3/internal/http2/Http2Reader;", "Ljava/io/Closeable;", "Lokio/BufferedSource;", "source", "", "client", "<init>", "(Lokio/BufferedSource;Z)V", "", "close", "()V", "requireSettings", "Lokhttp3/internal/http2/Http2Reader$Handler;", "handler", "nextFrame", "(ZLokhttp3/internal/http2/Http2Reader$Handler;)Z", "readConnectionPreface", "(Lokhttp3/internal/http2/Http2Reader$Handler;)V", "", "length", "flags", "streamId", "readData", "(Lokhttp3/internal/http2/Http2Reader$Handler;III)V", "readGoAway", "padding", "", "Lokhttp3/internal/http2/Header;", "readHeaderBlock", "(IIII)Ljava/util/List;", "readHeaders", "readPing", "readPriority", "(Lokhttp3/internal/http2/Http2Reader$Handler;I)V", "readPushPromise", "readRstStream", "readSettings", "readWindowUpdate", "Z", "Lokhttp3/internal/http2/Http2Reader$ContinuationSource;", "continuation", "Lokhttp3/internal/http2/Http2Reader$ContinuationSource;", "Lokhttp3/internal/http2/Hpack$Reader;", "hpackReader", "Lokhttp3/internal/http2/Hpack$Reader;", "Lokio/BufferedSource;", "Companion", "ContinuationSource", "Handler", "okhttp"})
public final class Http2Reader implements Closeable {
  public Http2Reader(@NotNull BufferedSource source, boolean client) {
    this.source = source;
    this.client = client;
    this.continuation = new ContinuationSource(this.source);
    this.hpackReader = new Hpack.Reader(
        this.continuation, 
        4096, 0, 4, null);
  }
  
  public final void readConnectionPreface(@NotNull Handler handler) throws IOException {
    Intrinsics.checkNotNullParameter(handler, "handler");
    if (this.client) {
      if (!nextFrame(true, handler))
        throw new IOException("Required SETTINGS preface not received"); 
    } else {
      ByteString connectionPreface = this.source.readByteString(Http2.CONNECTION_PREFACE.size());
      if (logger.isLoggable(Level.FINE))
        logger.fine(Util.format("<< CONNECTION " + connectionPreface.hex(), new Object[0])); 
      if (!Intrinsics.areEqual(Http2.CONNECTION_PREFACE, connectionPreface))
        throw new IOException("Expected a connection header but was " + connectionPreface.utf8()); 
    } 
  }
  
  public final boolean nextFrame(boolean requireSettings, @NotNull Handler handler) throws IOException {
    Intrinsics.checkNotNullParameter(handler, "handler");
    try {
      this.source.require(9L);
    } catch (EOFException e) {
      return false;
    } 
    int length = Util.readMedium(this.source);
    if (length > 16384)
      throw new IOException("FRAME_SIZE_ERROR: " + length); 
    int type = Util.and(this.source.readByte(), 255);
    int flags = Util.and(this.source.readByte(), 255);
    int streamId = this.source.readInt() & Integer.MAX_VALUE;
    if (logger.isLoggable(Level.FINE))
      logger.fine(Http2.INSTANCE.frameLog(true, streamId, length, type, flags)); 
    if (requireSettings && type != 4)
      throw new IOException("Expected a SETTINGS frame but was " + Http2.INSTANCE.formattedType$okhttp(type)); 
    switch (type) {
      case 0:
        readData(handler, length, flags, streamId);
        return true;
      case 1:
        readHeaders(handler, length, flags, streamId);
        return true;
      case 2:
        readPriority(handler, length, flags, streamId);
        return true;
      case 3:
        readRstStream(handler, length, flags, streamId);
        return true;
      case 4:
        readSettings(handler, length, flags, streamId);
        return true;
      case 5:
        readPushPromise(handler, length, flags, streamId);
        return true;
      case 6:
        readPing(handler, length, flags, streamId);
        return true;
      case 7:
        readGoAway(handler, length, flags, streamId);
        return true;
      case 8:
        readWindowUpdate(handler, length, flags, streamId);
        return true;
    } 
    this.source.skip(length);
    return true;
  }
  
  private final void readHeaders(Handler handler, int length, int flags, int streamId) throws IOException {
    if (streamId == 0)
      throw new IOException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0"); 
    boolean endStream = ((flags & 0x1) != 0);
    int padding = ((flags & 0x8) != 0) ? Util.and(this.source.readByte(), 255) : 0;
    int headerBlockLength = length;
    if ((flags & 0x20) != 0) {
      readPriority(handler, streamId);
      headerBlockLength -= 5;
    } 
    headerBlockLength = Companion.lengthWithoutPadding(headerBlockLength, flags, padding);
    List<Header> headerBlock = readHeaderBlock(headerBlockLength, padding, flags, streamId);
    handler.headers(endStream, streamId, -1, headerBlock);
  }
  
  private final List<Header> readHeaderBlock(int length, int padding, int flags, int streamId) throws IOException {
    this.continuation.setLeft(length);
    this.continuation.setLength(this.continuation.getLeft());
    this.continuation.setPadding(padding);
    this.continuation.setFlags(flags);
    this.continuation.setStreamId(streamId);
    this.hpackReader.readHeaders();
    return this.hpackReader.getAndResetHeaderList();
  }
  
  private final void readData(Handler handler, int length, int flags, int streamId) throws IOException {
    if (streamId == 0)
      throw new IOException("PROTOCOL_ERROR: TYPE_DATA streamId == 0"); 
    boolean inFinished = ((flags & 0x1) != 0);
    boolean gzipped = ((flags & 0x20) != 0);
    if (gzipped)
      throw new IOException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA"); 
    int padding = ((flags & 0x8) != 0) ? Util.and(this.source.readByte(), 255) : 0;
    int dataLength = Companion.lengthWithoutPadding(length, flags, padding);
    handler.data(inFinished, streamId, this.source, dataLength);
    this.source.skip(padding);
  }
  
  private final void readPriority(Handler handler, int length, int flags, int streamId) throws IOException {
    if (length != 5)
      throw new IOException("TYPE_PRIORITY length: " + length + " != 5"); 
    if (streamId == 0)
      throw new IOException("TYPE_PRIORITY streamId == 0"); 
    readPriority(handler, streamId);
  }
  
  private final void readPriority(Handler handler, int streamId) throws IOException {
    int w1 = this.source.readInt();
    boolean exclusive = ((w1 & Integer.MIN_VALUE) != 0);
    int streamDependency = w1 & Integer.MAX_VALUE;
    int weight = Util.and(this.source.readByte(), 255) + 1;
    handler.priority(streamId, streamDependency, weight, exclusive);
  }
  
  private final void readRstStream(Handler handler, int length, int flags, int streamId) throws IOException {
    ErrorCode errorCode;
    if (length != 4)
      throw new IOException("TYPE_RST_STREAM length: " + length + " != 4"); 
    if (streamId == 0)
      throw new IOException("TYPE_RST_STREAM streamId == 0"); 
    int errorCodeInt = this.source.readInt();
    if (ErrorCode.Companion.fromHttp2(errorCodeInt) == null) {
      ErrorCode.Companion.fromHttp2(errorCodeInt);
      throw new IOException(
          "TYPE_RST_STREAM unexpected error code: " + errorCodeInt);
    } 
    handler.rstStream(streamId, errorCode);
  }
  
  private final void readSettings(Handler handler, int length, int flags, int streamId) throws IOException {
    if (streamId != 0)
      throw new IOException("TYPE_SETTINGS streamId != 0"); 
    if ((flags & 0x1) != 0) {
      if (length != 0)
        throw new IOException("FRAME_SIZE_ERROR ack frame should be empty!"); 
      handler.ackSettings();
      return;
    } 
    if (length % 6 != 0)
      throw new IOException("TYPE_SETTINGS length % 6 != 0: " + length); 
    Settings settings = new Settings();
    IntProgression intProgression = RangesKt.step((IntProgression)RangesKt.until(0, length), 6);
    int i = intProgression.getFirst(), j = intProgression.getLast(), k = intProgression.getStep();
    if ((k > 0 && i <= j) || (k < 0 && j <= i))
      while (true) {
        int id = Util.and(this.source.readShort(), 65535);
        int value = this.source.readInt();
        switch (id) {
          case 2:
            if (value != 0 && value != 1)
              throw new IOException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1"); 
            break;
          case 3:
            id = 4;
            break;
          case 4:
            id = 7;
            if (value < 0)
              throw new IOException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1"); 
            break;
          case 5:
            if (value < 16384 || value > 16777215)
              throw new IOException("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: " + value); 
            break;
        } 
        settings.set(id, value);
        if (i != j) {
          i += k;
          continue;
        } 
        break;
      }  
    handler.settings(false, settings);
  }
  
  private final void readPushPromise(Handler handler, int length, int flags, int streamId) throws IOException {
    if (streamId == 0)
      throw new IOException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0"); 
    int padding = ((flags & 0x8) != 0) ? Util.and(this.source.readByte(), 255) : 0;
    int promisedStreamId = this.source.readInt() & Integer.MAX_VALUE;
    int headerBlockLength = Companion.lengthWithoutPadding(length - 4, flags, padding);
    List<Header> headerBlock = readHeaderBlock(headerBlockLength, padding, flags, streamId);
    handler.pushPromise(streamId, promisedStreamId, headerBlock);
  }
  
  private final void readPing(Handler handler, int length, int flags, int streamId) throws IOException {
    if (length != 8)
      throw new IOException("TYPE_PING length != 8: " + length); 
    if (streamId != 0)
      throw new IOException("TYPE_PING streamId != 0"); 
    int payload1 = this.source.readInt();
    int payload2 = this.source.readInt();
    boolean ack = ((flags & 0x1) != 0);
    handler.ping(ack, payload1, payload2);
  }
  
  private final void readGoAway(Handler handler, int length, int flags, int streamId) throws IOException {
    ErrorCode errorCode;
    if (length < 8)
      throw new IOException("TYPE_GOAWAY length < 8: " + length); 
    if (streamId != 0)
      throw new IOException("TYPE_GOAWAY streamId != 0"); 
    int lastStreamId = this.source.readInt();
    int errorCodeInt = this.source.readInt();
    int opaqueDataLength = length - 8;
    if (ErrorCode.Companion.fromHttp2(errorCodeInt) == null) {
      ErrorCode.Companion.fromHttp2(errorCodeInt);
      throw new IOException(
          "TYPE_GOAWAY unexpected error code: " + errorCodeInt);
    } 
    ByteString debugData = ByteString.EMPTY;
    if (opaqueDataLength > 0)
      debugData = this.source.readByteString(opaqueDataLength); 
    handler.goAway(lastStreamId, errorCode, debugData);
  }
  
  private final void readWindowUpdate(Handler handler, int length, int flags, int streamId) throws IOException {
    if (length != 4)
      throw new IOException("TYPE_WINDOW_UPDATE length !=4: " + length); 
    long increment = Util.and(this.source.readInt(), 2147483647L);
    if (increment == 0L)
      throw new IOException("windowSizeIncrement was 0"); 
    handler.windowUpdate(streamId, increment);
  }
  
  public void close() throws IOException {
    this.source.close();
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000V\n\002\030\002\n\002\020\000\n\002\020\002\n\002\b\002\n\002\020\b\n\000\n\002\020\016\n\000\n\002\030\002\n\002\b\003\n\002\020\t\n\002\b\003\n\002\020\013\n\000\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\020 \n\002\030\002\n\002\b\024\n\002\030\002\n\002\b\006\bf\030\0002\0020\001J\017\020\003\032\0020\002H&¢\006\004\b\003\020\004J?\020\017\032\0020\0022\006\020\006\032\0020\0052\006\020\b\032\0020\0072\006\020\n\032\0020\t2\006\020\013\032\0020\0072\006\020\f\032\0020\0052\006\020\016\032\0020\rH&¢\006\004\b\017\020\020J/\020\026\032\0020\0022\006\020\022\032\0020\0212\006\020\006\032\0020\0052\006\020\024\032\0020\0232\006\020\025\032\0020\005H&¢\006\004\b\026\020\027J'\020\034\032\0020\0022\006\020\030\032\0020\0052\006\020\032\032\0020\0312\006\020\033\032\0020\tH&¢\006\004\b\034\020\035J5\020\"\032\0020\0022\006\020\022\032\0020\0212\006\020\006\032\0020\0052\006\020\036\032\0020\0052\f\020!\032\b\022\004\022\0020 0\037H&¢\006\004\b\"\020#J'\020'\032\0020\0022\006\020$\032\0020\0212\006\020%\032\0020\0052\006\020&\032\0020\005H&¢\006\004\b'\020(J/\020,\032\0020\0022\006\020\006\032\0020\0052\006\020)\032\0020\0052\006\020*\032\0020\0052\006\020+\032\0020\021H&¢\006\004\b,\020-J-\0200\032\0020\0022\006\020\006\032\0020\0052\006\020.\032\0020\0052\f\020/\032\b\022\004\022\0020 0\037H&¢\006\004\b0\0201J\037\0202\032\0020\0022\006\020\006\032\0020\0052\006\020\032\032\0020\031H&¢\006\004\b2\0203J\037\0206\032\0020\0022\006\0204\032\0020\0212\006\0206\032\00205H&¢\006\004\b6\0207J\037\0209\032\0020\0022\006\020\006\032\0020\0052\006\0208\032\0020\rH&¢\006\004\b9\020:¨\006;"}, d2 = {"Lokhttp3/internal/http2/Http2Reader$Handler;", "", "", "ackSettings", "()V", "", "streamId", "", "origin", "Lokio/ByteString;", "protocol", "host", "port", "", "maxAge", "alternateService", "(ILjava/lang/String;Lokio/ByteString;Ljava/lang/String;IJ)V", "", "inFinished", "Lokio/BufferedSource;", "source", "length", "data", "(ZILokio/BufferedSource;I)V", "lastGoodStreamId", "Lokhttp3/internal/http2/ErrorCode;", "errorCode", "debugData", "goAway", "(ILokhttp3/internal/http2/ErrorCode;Lokio/ByteString;)V", "associatedStreamId", "", "Lokhttp3/internal/http2/Header;", "headerBlock", "headers", "(ZIILjava/util/List;)V", "ack", "payload1", "payload2", "ping", "(ZII)V", "streamDependency", "weight", "exclusive", "priority", "(IIIZ)V", "promisedStreamId", "requestHeaders", "pushPromise", "(IILjava/util/List;)V", "rstStream", "(ILokhttp3/internal/http2/ErrorCode;)V", "clearPrevious", "Lokhttp3/internal/http2/Settings;", "settings", "(ZLokhttp3/internal/http2/Settings;)V", "windowSizeIncrement", "windowUpdate", "(IJ)V", "okhttp"})
  public static interface Handler {
    void data(boolean param1Boolean, int param1Int1, @NotNull BufferedSource param1BufferedSource, int param1Int2) throws IOException;
    
    void headers(boolean param1Boolean, int param1Int1, int param1Int2, @NotNull List<Header> param1List);
    
    void rstStream(int param1Int, @NotNull ErrorCode param1ErrorCode);
    
    void settings(boolean param1Boolean, @NotNull Settings param1Settings);
    
    void ackSettings();
    
    void ping(boolean param1Boolean, int param1Int1, int param1Int2);
    
    void goAway(int param1Int, @NotNull ErrorCode param1ErrorCode, @NotNull ByteString param1ByteString);
    
    void windowUpdate(int param1Int, long param1Long);
    
    void priority(int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean);
    
    void pushPromise(int param1Int1, int param1Int2, @NotNull List<Header> param1List) throws IOException;
    
    void alternateService(int param1Int1, @NotNull String param1String1, @NotNull ByteString param1ByteString, @NotNull String param1String2, int param1Int2, long param1Long);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0006\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\004\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\024\b\000\030\0002\0020\001B\017\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\017\020\007\032\0020\006H\026¢\006\004\b\007\020\bJ\037\020\r\032\0020\0132\006\020\n\032\0020\t2\006\020\f\032\0020\013H\026¢\006\004\b\r\020\016J\017\020\017\032\0020\006H\002¢\006\004\b\017\020\bJ\017\020\021\032\0020\020H\026¢\006\004\b\021\020\022R\"\020\024\032\0020\0238\006@\006X\016¢\006\022\n\004\b\024\020\025\032\004\b\026\020\027\"\004\b\030\020\031R\"\020\032\032\0020\0238\006@\006X\016¢\006\022\n\004\b\032\020\025\032\004\b\033\020\027\"\004\b\034\020\031R\"\020\035\032\0020\0238\006@\006X\016¢\006\022\n\004\b\035\020\025\032\004\b\036\020\027\"\004\b\037\020\031R\"\020 \032\0020\0238\006@\006X\016¢\006\022\n\004\b \020\025\032\004\b!\020\027\"\004\b\"\020\031R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020#R\"\020$\032\0020\0238\006@\006X\016¢\006\022\n\004\b$\020\025\032\004\b%\020\027\"\004\b&\020\031¨\006'"}, d2 = {"Lokhttp3/internal/http2/Http2Reader$ContinuationSource;", "Lokio/Source;", "Lokio/BufferedSource;", "source", "<init>", "(Lokio/BufferedSource;)V", "", "close", "()V", "Lokio/Buffer;", "sink", "", "byteCount", "read", "(Lokio/Buffer;J)J", "readContinuationHeader", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "", "flags", "I", "getFlags", "()I", "setFlags", "(I)V", "left", "getLeft", "setLeft", "length", "getLength", "setLength", "padding", "getPadding", "setPadding", "Lokio/BufferedSource;", "streamId", "getStreamId", "setStreamId", "okhttp"})
  public static final class ContinuationSource implements Source {
    @NotNull
    private final BufferedSource source;
    
    private int length;
    
    private int flags;
    
    private int streamId;
    
    private int left;
    
    private int padding;
    
    public ContinuationSource(@NotNull BufferedSource source) {
      this.source = source;
    }
    
    public final int getLength() {
      return this.length;
    }
    
    public final void setLength(int <set-?>) {
      this.length = <set-?>;
    }
    
    public final int getFlags() {
      return this.flags;
    }
    
    public final void setFlags(int <set-?>) {
      this.flags = <set-?>;
    }
    
    public final int getStreamId() {
      return this.streamId;
    }
    
    public final void setStreamId(int <set-?>) {
      this.streamId = <set-?>;
    }
    
    public final int getLeft() {
      return this.left;
    }
    
    public final void setLeft(int <set-?>) {
      this.left = <set-?>;
    }
    
    public final int getPadding() {
      return this.padding;
    }
    
    public final void setPadding(int <set-?>) {
      this.padding = <set-?>;
    }
    
    public long read(@NotNull Buffer sink, long byteCount) throws IOException {
      Intrinsics.checkNotNullParameter(sink, "sink");
      while (this.left == 0) {
        this.source.skip(this.padding);
        this.padding = 0;
        if ((this.flags & 0x4) != 0)
          return -1L; 
        readContinuationHeader();
      } 
      long read = this.source.read(sink, Math.min(byteCount, this.left));
      if (read == -1L)
        return -1L; 
      this.left -= (int)read;
      return read;
    }
    
    @NotNull
    public Timeout timeout() {
      return this.source.timeout();
    }
    
    public void close() throws IOException {}
    
    private final void readContinuationHeader() throws IOException {
      int previousStreamId = this.streamId;
      this.left = Util.readMedium(this.source);
      this.length = this.left;
      int type = Util.and(this.source.readByte(), 255);
      this.flags = Util.and(this.source.readByte(), 255);
      if (Http2Reader.Companion.getLogger().isLoggable(Level.FINE))
        Http2Reader.Companion.getLogger().fine(Http2.INSTANCE.frameLog(true, this.streamId, this.length, type, this.flags)); 
      this.streamId = this.source.readInt() & Integer.MAX_VALUE;
      if (type != 9)
        throw new IOException(type + " != TYPE_CONTINUATION"); 
      if (this.streamId != previousStreamId)
        throw new IOException("TYPE_CONTINUATION streamId changed"); 
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\002\b\005\n\002\030\002\n\002\b\005\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J%\020\b\032\0020\0042\006\020\005\032\0020\0042\006\020\006\032\0020\0042\006\020\007\032\0020\004¢\006\004\b\b\020\tR\027\020\013\032\0020\n8\006¢\006\f\n\004\b\013\020\f\032\004\b\r\020\016¨\006\017"}, d2 = {"Lokhttp3/internal/http2/Http2Reader$Companion;", "", "<init>", "()V", "", "length", "flags", "padding", "lengthWithoutPadding", "(III)I", "Ljava/util/logging/Logger;", "logger", "Ljava/util/logging/Logger;", "getLogger", "()Ljava/util/logging/Logger;", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @NotNull
    public final Logger getLogger() {
      return Http2Reader.logger;
    }
    
    public final int lengthWithoutPadding(int length, int flags, int padding) throws IOException {
      int result = length;
      if ((flags & 0x8) != 0)
        result--; 
      if (padding > result)
        throw new IOException("PROTOCOL_ERROR padding " + padding + " > remaining length " + result); 
      result -= padding;
      return result;
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final BufferedSource source;
  
  private final boolean client;
  
  @NotNull
  private final ContinuationSource continuation;
  
  @NotNull
  private final Hpack.Reader hpackReader;
  
  @NotNull
  private static final Logger logger = Logger.getLogger(Http2.class.getName());
  
  static {
    Intrinsics.checkNotNullExpressionValue(Logger.getLogger(Http2.class.getName()), "getLogger(Http2::class.java.name)");
  }
}
