package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000Z\n\002\030\002\n\002\030\002\n\002\020\013\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\t\n\002\b\003\n\002\020\002\n\002\b\002\n\002\020\b\n\000\n\002\030\002\n\002\b\r\n\002\030\002\n\002\b\002\n\002\020\022\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\f\030\0002\0020\001B7\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\006\020\007\032\0020\006\022\006\020\b\032\0020\002\022\006\020\t\032\0020\002\022\006\020\013\032\0020\n¢\006\004\b\f\020\rJ\017\020\017\032\0020\016H\026¢\006\004\b\017\020\020J\037\020\025\032\0020\0162\006\020\022\032\0020\0212\b\020\024\032\004\030\0010\023¢\006\004\b\025\020\026J\037\020\031\032\0020\0162\006\020\027\032\0020\0212\006\020\030\032\0020\023H\002¢\006\004\b\031\020\026J\035\020\034\032\0020\0162\006\020\032\032\0020\0212\006\020\033\032\0020\023¢\006\004\b\034\020\026J\025\020\035\032\0020\0162\006\020\030\032\0020\023¢\006\004\b\035\020\036J\025\020\037\032\0020\0162\006\020\030\032\0020\023¢\006\004\b\037\020\036R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020 R\026\020\"\032\004\030\0010!8\002X\004¢\006\006\n\004\b\"\020#R\026\020%\032\004\030\0010$8\002X\004¢\006\006\n\004\b%\020&R\024\020(\032\0020'8\002X\004¢\006\006\n\004\b(\020)R\030\020+\032\004\030\0010*8\002@\002X\016¢\006\006\n\004\b+\020,R\024\020\013\032\0020\n8\002X\004¢\006\006\n\004\b\013\020-R\024\020\t\032\0020\0028\002X\004¢\006\006\n\004\b\t\020 R\024\020\b\032\0020\0028\002X\004¢\006\006\n\004\b\b\020 R\027\020\007\032\0020\0068\006¢\006\f\n\004\b\007\020.\032\004\b/\0200R\027\020\005\032\0020\0048\006¢\006\f\n\004\b\005\0201\032\004\b2\0203R\024\0204\032\0020'8\002X\004¢\006\006\n\004\b4\020)R\026\0205\032\0020\0028\002@\002X\016¢\006\006\n\004\b5\020 ¨\0066"}, d2 = {"Lokhttp3/internal/ws/WebSocketWriter;", "Ljava/io/Closeable;", "", "isClient", "Lokio/BufferedSink;", "sink", "Ljava/util/Random;", "random", "perMessageDeflate", "noContextTakeover", "", "minimumDeflateSize", "<init>", "(ZLokio/BufferedSink;Ljava/util/Random;ZZJ)V", "", "close", "()V", "", "code", "Lokio/ByteString;", "reason", "writeClose", "(ILokio/ByteString;)V", "opcode", "payload", "writeControlFrame", "formatOpcode", "data", "writeMessageFrame", "writePing", "(Lokio/ByteString;)V", "writePong", "Z", "Lokio/Buffer$UnsafeCursor;", "maskCursor", "Lokio/Buffer$UnsafeCursor;", "", "maskKey", "[B", "Lokio/Buffer;", "messageBuffer", "Lokio/Buffer;", "Lokhttp3/internal/ws/MessageDeflater;", "messageDeflater", "Lokhttp3/internal/ws/MessageDeflater;", "J", "Ljava/util/Random;", "getRandom", "()Ljava/util/Random;", "Lokio/BufferedSink;", "getSink", "()Lokio/BufferedSink;", "sinkBuffer", "writerClosed", "okhttp"})
@SourceDebugExtension({"SMAP\nWebSocketWriter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebSocketWriter.kt\nokhttp3/internal/ws/WebSocketWriter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,202:1\n1#2:203\n*E\n"})
public final class WebSocketWriter implements Closeable {
  private final boolean isClient;
  
  @NotNull
  private final BufferedSink sink;
  
  @NotNull
  private final Random random;
  
  private final boolean perMessageDeflate;
  
  private final boolean noContextTakeover;
  
  private final long minimumDeflateSize;
  
  @NotNull
  private final Buffer messageBuffer;
  
  @NotNull
  private final Buffer sinkBuffer;
  
  private boolean writerClosed;
  
  @Nullable
  private MessageDeflater messageDeflater;
  
  @Nullable
  private final byte[] maskKey;
  
  @Nullable
  private final Buffer.UnsafeCursor maskCursor;
  
  public WebSocketWriter(boolean isClient, @NotNull BufferedSink sink, @NotNull Random random, boolean perMessageDeflate, boolean noContextTakeover, long minimumDeflateSize) {
    this.isClient = isClient;
    this.sink = sink;
    this.random = random;
    this.perMessageDeflate = perMessageDeflate;
    this.noContextTakeover = noContextTakeover;
    this.minimumDeflateSize = minimumDeflateSize;
    this.messageBuffer = new Buffer();
    this.sinkBuffer = this.sink.getBuffer();
    this.maskKey = this.isClient ? new byte[4] : null;
    this.maskCursor = this.isClient ? new Buffer.UnsafeCursor() : null;
  }
  
  @NotNull
  public final BufferedSink getSink() {
    return this.sink;
  }
  
  @NotNull
  public final Random getRandom() {
    return this.random;
  }
  
  public final void writePing(@NotNull ByteString payload) throws IOException {
    Intrinsics.checkNotNullParameter(payload, "payload");
    writeControlFrame(9, payload);
  }
  
  public final void writePong(@NotNull ByteString payload) throws IOException {
    Intrinsics.checkNotNullParameter(payload, "payload");
    writeControlFrame(10, payload);
  }
  
  public final void writeClose(int code, @Nullable ByteString reason) throws IOException {
    ByteString payload = ByteString.EMPTY;
    if (code != 0 || reason != null) {
      if (code != 0)
        WebSocketProtocol.INSTANCE.validateCloseCode(code); 
      Buffer $this$writeClose_u24lambda_u240 = new Buffer();
      int $i$a$-run-WebSocketWriter$writeClose$1 = 0;
      $this$writeClose_u24lambda_u240.writeShort(code);
      if (reason != null)
        $this$writeClose_u24lambda_u240.write(reason); 
      payload = $this$writeClose_u24lambda_u240.readByteString();
    } 
    try {
      writeControlFrame(8, payload);
    } finally {
      this.writerClosed = true;
    } 
  }
  
  private final void writeControlFrame(int opcode, ByteString payload) throws IOException {
    if (this.writerClosed)
      throw new IOException("closed"); 
    int length = payload.size();
    if (!((length <= 125L) ? 1 : 0)) {
      int $i$a$-require-WebSocketWriter$writeControlFrame$1 = 0;
      String str = 
        "Payload size must be less than or equal to 125";
      throw new IllegalArgumentException(str.toString());
    } 
    int b0 = 0x80 | opcode;
    this.sinkBuffer.writeByte(b0);
    int b1 = length;
    if (this.isClient) {
      b1 |= 0x80;
      this.sinkBuffer.writeByte(b1);
      Intrinsics.checkNotNull(this.maskKey);
      this.random.nextBytes(this.maskKey);
      this.sinkBuffer.write(this.maskKey);
      if (length > 0) {
        long payloadStart = this.sinkBuffer.size();
        this.sinkBuffer.write(payload);
        Intrinsics.checkNotNull(this.maskCursor);
        this.sinkBuffer.readAndWriteUnsafe(this.maskCursor);
        this.maskCursor.seek(payloadStart);
        WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
        this.maskCursor.close();
      } 
    } else {
      this.sinkBuffer.writeByte(b1);
      this.sinkBuffer.write(payload);
    } 
    this.sink.flush();
  }
  
  public final void writeMessageFrame(int formatOpcode, @NotNull ByteString data) throws IOException {
    Intrinsics.checkNotNullParameter(data, "data");
    if (this.writerClosed)
      throw new IOException("closed"); 
    this.messageBuffer.write(data);
    int b0 = formatOpcode | 0x80;
    if (this.perMessageDeflate && data.size() >= this.minimumDeflateSize) {
      if (this.messageDeflater == null) {
        MessageDeflater messageDeflater1 = new MessageDeflater(this.noContextTakeover), it = messageDeflater1;
        int $i$a$-also-WebSocketWriter$writeMessageFrame$messageDeflater$1 = 0;
        this.messageDeflater = it;
      } 
      MessageDeflater messageDeflater = messageDeflater1;
      messageDeflater.deflate(this.messageBuffer);
      b0 |= 0x40;
    } 
    long dataSize = this.messageBuffer.size();
    this.sinkBuffer.writeByte(b0);
    int b1 = 0;
    if (this.isClient)
      b1 |= 0x80; 
    if (dataSize <= 125L) {
      b1 |= (int)dataSize;
      this.sinkBuffer.writeByte(b1);
    } else if (dataSize <= 65535L) {
      b1 |= 0x7E;
      this.sinkBuffer.writeByte(b1);
      this.sinkBuffer.writeShort((int)dataSize);
    } else {
      b1 |= 0x7F;
      this.sinkBuffer.writeByte(b1);
      this.sinkBuffer.writeLong(dataSize);
    } 
    if (this.isClient) {
      Intrinsics.checkNotNull(this.maskKey);
      this.random.nextBytes(this.maskKey);
      this.sinkBuffer.write(this.maskKey);
      if (dataSize > 0L) {
        Intrinsics.checkNotNull(this.maskCursor);
        this.messageBuffer.readAndWriteUnsafe(this.maskCursor);
        this.maskCursor.seek(0L);
        WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
        this.maskCursor.close();
      } 
    } 
    this.sinkBuffer.write(this.messageBuffer, dataSize);
    this.sink.emit();
  }
  
  public void close() {
    if (this.messageDeflater != null) {
      this.messageDeflater.close();
    } else {
    
    } 
  }
}
