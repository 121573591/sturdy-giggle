package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000T\n\002\030\002\n\002\030\002\n\002\020\013\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\005\n\002\020\002\n\002\b\n\n\002\030\002\n\002\b\003\n\002\020\t\n\002\b\004\n\002\030\002\n\002\b\002\n\002\020\022\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\b\030\0002\0020\001:\0011B/\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\006\020\007\032\0020\006\022\006\020\b\032\0020\002\022\006\020\t\032\0020\002¢\006\004\b\n\020\013J\017\020\r\032\0020\fH\026¢\006\004\b\r\020\016J\r\020\017\032\0020\f¢\006\004\b\017\020\016J\017\020\020\032\0020\fH\002¢\006\004\b\020\020\016J\017\020\021\032\0020\fH\002¢\006\004\b\021\020\016J\017\020\022\032\0020\fH\002¢\006\004\b\022\020\016J\017\020\023\032\0020\fH\002¢\006\004\b\023\020\016J\017\020\024\032\0020\fH\002¢\006\004\b\024\020\016R\026\020\025\032\0020\0028\002@\002X\016¢\006\006\n\004\b\025\020\026R\024\020\030\032\0020\0278\002X\004¢\006\006\n\004\b\030\020\031R\024\020\007\032\0020\0068\002X\004¢\006\006\n\004\b\007\020\032R\026\020\034\032\0020\0338\002@\002X\016¢\006\006\n\004\b\034\020\035R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\026R\026\020\036\032\0020\0028\002@\002X\016¢\006\006\n\004\b\036\020\026R\026\020\037\032\0020\0028\002@\002X\016¢\006\006\n\004\b\037\020\026R\026\020!\032\004\030\0010 8\002X\004¢\006\006\n\004\b!\020\"R\026\020$\032\004\030\0010#8\002X\004¢\006\006\n\004\b$\020%R\024\020&\032\0020\0278\002X\004¢\006\006\n\004\b&\020\031R\030\020(\032\004\030\0010'8\002@\002X\016¢\006\006\n\004\b(\020)R\024\020\t\032\0020\0028\002X\004¢\006\006\n\004\b\t\020\026R\026\020+\032\0020*8\002@\002X\016¢\006\006\n\004\b+\020,R\024\020\b\032\0020\0028\002X\004¢\006\006\n\004\b\b\020\026R\026\020-\032\0020\0028\002@\002X\016¢\006\006\n\004\b-\020\026R\027\020\005\032\0020\0048\006¢\006\f\n\004\b\005\020.\032\004\b/\0200¨\0062"}, d2 = {"Lokhttp3/internal/ws/WebSocketReader;", "Ljava/io/Closeable;", "", "isClient", "Lokio/BufferedSource;", "source", "Lokhttp3/internal/ws/WebSocketReader$FrameCallback;", "frameCallback", "perMessageDeflate", "noContextTakeover", "<init>", "(ZLokio/BufferedSource;Lokhttp3/internal/ws/WebSocketReader$FrameCallback;ZZ)V", "", "close", "()V", "processNextFrame", "readControlFrame", "readHeader", "readMessage", "readMessageFrame", "readUntilNonControlFrame", "closed", "Z", "Lokio/Buffer;", "controlFrameBuffer", "Lokio/Buffer;", "Lokhttp3/internal/ws/WebSocketReader$FrameCallback;", "", "frameLength", "J", "isControlFrame", "isFinalFrame", "Lokio/Buffer$UnsafeCursor;", "maskCursor", "Lokio/Buffer$UnsafeCursor;", "", "maskKey", "[B", "messageFrameBuffer", "Lokhttp3/internal/ws/MessageInflater;", "messageInflater", "Lokhttp3/internal/ws/MessageInflater;", "", "opcode", "I", "readingCompressedMessage", "Lokio/BufferedSource;", "getSource", "()Lokio/BufferedSource;", "FrameCallback", "okhttp"})
@SourceDebugExtension({"SMAP\nWebSocketReader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebSocketReader.kt\nokhttp3/internal/ws/WebSocketReader\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,298:1\n1#2:299\n*E\n"})
public final class WebSocketReader implements Closeable {
  private final boolean isClient;
  
  @NotNull
  private final BufferedSource source;
  
  @NotNull
  private final FrameCallback frameCallback;
  
  private final boolean perMessageDeflate;
  
  private final boolean noContextTakeover;
  
  private boolean closed;
  
  private int opcode;
  
  private long frameLength;
  
  private boolean isFinalFrame;
  
  private boolean isControlFrame;
  
  private boolean readingCompressedMessage;
  
  @NotNull
  private final Buffer controlFrameBuffer;
  
  @NotNull
  private final Buffer messageFrameBuffer;
  
  @Nullable
  private MessageInflater messageInflater;
  
  @Nullable
  private final byte[] maskKey;
  
  @Nullable
  private final Buffer.UnsafeCursor maskCursor;
  
  public WebSocketReader(boolean isClient, @NotNull BufferedSource source, @NotNull FrameCallback frameCallback, boolean perMessageDeflate, boolean noContextTakeover) {
    this.isClient = isClient;
    this.source = source;
    this.frameCallback = frameCallback;
    this.perMessageDeflate = perMessageDeflate;
    this.noContextTakeover = noContextTakeover;
    this.controlFrameBuffer = new Buffer();
    this.messageFrameBuffer = new Buffer();
    this.maskKey = this.isClient ? null : new byte[4];
    this.maskCursor = this.isClient ? null : new Buffer.UnsafeCursor();
  }
  
  @NotNull
  public final BufferedSource getSource() {
    return this.source;
  }
  
  public final void processNextFrame() throws IOException {
    readHeader();
    if (this.isControlFrame) {
      readControlFrame();
    } else {
      readMessageFrame();
    } 
  }
  
  private final void readHeader() throws IOException, ProtocolException {
    if (this.closed)
      throw new IOException("closed"); 
    int b0 = 0;
    long timeoutBefore = this.source.timeout().timeoutNanos();
    this.source.timeout().clearTimeout();
    try {
      b0 = Util.and(this.source.readByte(), 255);
    } finally {
      this.source.timeout().timeout(timeoutBefore, TimeUnit.NANOSECONDS);
    } 
    this.opcode = b0 & 0xF;
    this.isFinalFrame = ((b0 & 0x80) != 0);
    this.isControlFrame = ((b0 & 0x8) != 0);
    if (this.isControlFrame && !this.isFinalFrame)
      throw new ProtocolException("Control frames must be final."); 
    boolean reservedFlag1 = ((b0 & 0x40) != 0);
    switch (this.opcode) {
      case 1:
      case 2:
        if (!this.perMessageDeflate)
          throw new ProtocolException("Unexpected rsv1 flag"); 
        this.readingCompressedMessage = reservedFlag1;
        break;
      default:
        if (reservedFlag1)
          throw new ProtocolException("Unexpected rsv1 flag"); 
        break;
    } 
    boolean reservedFlag2 = ((b0 & 0x20) != 0);
    if (reservedFlag2)
      throw new ProtocolException("Unexpected rsv2 flag"); 
    boolean reservedFlag3 = ((b0 & 0x10) != 0);
    if (reservedFlag3)
      throw new ProtocolException("Unexpected rsv3 flag"); 
    int b1 = Util.and(this.source.readByte(), 255);
    boolean isMasked = ((b1 & 0x80) != 0);
    if (isMasked == this.isClient)
      throw new ProtocolException(this.isClient ? 
          "Server-sent frames must not be masked." : 
          
          "Client-sent frames must be masked."); 
    this.frameLength = (b1 & 0x7F);
    if (this.frameLength == 126L) {
      this.frameLength = Util.and(this.source.readShort(), 65535);
    } else if (this.frameLength == 127L) {
      this.frameLength = this.source.readLong();
      if (this.frameLength < 0L)
        throw new ProtocolException(
            "Frame length 0x" + Util.toHexString(this.frameLength) + " > 0x7FFFFFFFFFFFFFFF"); 
    } 
    if (this.isControlFrame && this.frameLength > 125L)
      throw new ProtocolException("Control frame must be less than 125B."); 
    if (isMasked) {
      Intrinsics.checkNotNull(this.maskKey);
      this.source.readFully(this.maskKey);
    } 
  }
  
  private final void readControlFrame() throws IOException {
    int code;
    String reason;
    long bufferSize;
    if (this.frameLength > 0L) {
      this.source.readFully(this.controlFrameBuffer, this.frameLength);
      if (!this.isClient) {
        Intrinsics.checkNotNull(this.maskCursor);
        this.controlFrameBuffer.readAndWriteUnsafe(this.maskCursor);
        this.maskCursor.seek(0L);
        Intrinsics.checkNotNull(this.maskKey);
        WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
        this.maskCursor.close();
      } 
    } 
    switch (this.opcode) {
      case 9:
        this.frameCallback.onReadPing(this.controlFrameBuffer.readByteString());
        return;
      case 10:
        this.frameCallback.onReadPong(this.controlFrameBuffer.readByteString());
        return;
      case 8:
        code = 1005;
        reason = "";
        bufferSize = this.controlFrameBuffer.size();
        if (bufferSize == 1L)
          throw new ProtocolException("Malformed close payload length of 1."); 
        if (bufferSize != 0L) {
          code = this.controlFrameBuffer.readShort();
          reason = this.controlFrameBuffer.readUtf8();
          String codeExceptionMessage = WebSocketProtocol.INSTANCE.closeCodeExceptionMessage(code);
          if (codeExceptionMessage != null)
            throw new ProtocolException(codeExceptionMessage); 
        } 
        this.frameCallback.onReadClose(code, reason);
        this.closed = true;
        return;
    } 
    throw new ProtocolException("Unknown control opcode: " + Util.toHexString(this.opcode));
  }
  
  private final void readMessageFrame() throws IOException {
    int opcode = this.opcode;
    if (opcode != 1 && opcode != 2)
      throw new ProtocolException("Unknown opcode: " + Util.toHexString(opcode)); 
    readMessage();
    if (this.readingCompressedMessage) {
      if (this.messageInflater == null) {
        MessageInflater messageInflater1 = new MessageInflater(this.noContextTakeover), it = messageInflater1;
        int $i$a$-also-WebSocketReader$readMessageFrame$messageInflater$1 = 0;
        this.messageInflater = it;
      } 
      MessageInflater messageInflater = messageInflater1;
      messageInflater.inflate(this.messageFrameBuffer);
    } 
    if (opcode == 1) {
      this.frameCallback.onReadMessage(this.messageFrameBuffer.readUtf8());
    } else {
      this.frameCallback.onReadMessage(this.messageFrameBuffer.readByteString());
    } 
  }
  
  private final void readUntilNonControlFrame() throws IOException {
    while (!this.closed) {
      readHeader();
      if (!this.isControlFrame)
        break; 
      readControlFrame();
    } 
  }
  
  private final void readMessage() throws IOException {
    while (true) {
      if (this.closed)
        throw new IOException("closed"); 
      if (this.frameLength > 0L) {
        this.source.readFully(this.messageFrameBuffer, this.frameLength);
        if (!this.isClient) {
          Intrinsics.checkNotNull(this.maskCursor);
          this.messageFrameBuffer.readAndWriteUnsafe(this.maskCursor);
          this.maskCursor.seek(this.messageFrameBuffer.size() - this.frameLength);
          Intrinsics.checkNotNull(this.maskKey);
          WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
          this.maskCursor.close();
        } 
      } 
      if (!this.isFinalFrame) {
        readUntilNonControlFrame();
        if (this.opcode != 0)
          throw new ProtocolException("Expected continuation opcode. Got: " + Util.toHexString(this.opcode)); 
        continue;
      } 
      break;
    } 
  }
  
  public void close() throws IOException {
    if (this.messageInflater != null) {
      this.messageInflater.close();
    } else {
    
    } 
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000$\n\002\030\002\n\002\020\000\n\002\020\b\n\000\n\002\020\016\n\000\n\002\020\002\n\002\b\005\n\002\030\002\n\002\b\006\bf\030\0002\0020\001J\037\020\007\032\0020\0062\006\020\003\032\0020\0022\006\020\005\032\0020\004H&¢\006\004\b\007\020\bJ\027\020\n\032\0020\0062\006\020\t\032\0020\004H&¢\006\004\b\n\020\013J\027\020\n\032\0020\0062\006\020\r\032\0020\fH&¢\006\004\b\n\020\016J\027\020\020\032\0020\0062\006\020\017\032\0020\fH&¢\006\004\b\020\020\016J\027\020\021\032\0020\0062\006\020\017\032\0020\fH&¢\006\004\b\021\020\016¨\006\022"}, d2 = {"Lokhttp3/internal/ws/WebSocketReader$FrameCallback;", "", "", "code", "", "reason", "", "onReadClose", "(ILjava/lang/String;)V", "text", "onReadMessage", "(Ljava/lang/String;)V", "Lokio/ByteString;", "bytes", "(Lokio/ByteString;)V", "payload", "onReadPing", "onReadPong", "okhttp"})
  public static interface FrameCallback {
    void onReadMessage(@NotNull String param1String) throws IOException;
    
    void onReadMessage(@NotNull ByteString param1ByteString) throws IOException;
    
    void onReadPing(@NotNull ByteString param1ByteString);
    
    void onReadPong(@NotNull ByteString param1ByteString);
    
    void onReadClose(int param1Int, @NotNull String param1String);
  }
}
