package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000º\001\n\002\030\002\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\005\n\002\030\002\n\000\n\002\020\002\n\002\b\004\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\020\b\n\000\n\002\020\016\n\000\n\002\020\013\n\002\b\004\n\002\030\002\n\002\b\003\n\002\030\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\t\n\002\030\002\n\002\b \n\002\030\002\n\002\b\n\n\002\030\002\n\002\020\000\n\002\b\006\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\b\030\000 \0012\0020\0012\0020\002:\n\001\001\001\001\001BA\022\006\020\004\032\0020\003\022\006\020\006\032\0020\005\022\006\020\b\032\0020\007\022\006\020\n\032\0020\t\022\006\020\f\032\0020\013\022\b\020\016\032\004\030\0010\r\022\006\020\017\032\0020\013¢\006\004\b\020\020\021J\035\020\026\032\0020\0252\006\020\022\032\0020\0132\006\020\024\032\0020\023¢\006\004\b\026\020\027J\017\020\030\032\0020\025H\026¢\006\004\b\030\020\031J!\020 \032\0020\0252\006\020\033\032\0020\0322\b\020\035\032\004\030\0010\034H\000¢\006\004\b\036\020\037J!\020&\032\0020%2\006\020\"\032\0020!2\b\020$\032\004\030\0010#H\026¢\006\004\b&\020'J'\020&\032\0020%2\006\020\"\032\0020!2\b\020$\032\004\030\0010#2\006\020(\032\0020\013¢\006\004\b&\020)J\025\020,\032\0020\0252\006\020+\032\0020*¢\006\004\b,\020-J#\0201\032\0020\0252\n\0200\032\0060.j\002`/2\b\020\033\032\004\030\0010\032¢\006\004\b1\0202J\035\0206\032\0020\0252\006\0203\032\0020#2\006\0205\032\00204¢\006\004\b6\0207J\r\0208\032\0020\025¢\006\004\b8\020\031J\037\0209\032\0020\0252\006\020\"\032\0020!2\006\020$\032\0020#H\026¢\006\004\b9\020:J\027\020<\032\0020\0252\006\020;\032\0020#H\026¢\006\004\b<\020=J\027\020<\032\0020\0252\006\020?\032\0020>H\026¢\006\004\b<\020@J\027\020B\032\0020\0252\006\020A\032\0020>H\026¢\006\004\bB\020@J\027\020C\032\0020\0252\006\020A\032\0020>H\026¢\006\004\bC\020@J\025\020D\032\0020%2\006\020A\032\0020>¢\006\004\bD\020EJ\r\020F\032\0020%¢\006\004\bF\020GJ\017\020H\032\0020\013H\026¢\006\004\bH\020IJ\r\020J\032\0020!¢\006\004\bJ\020KJ\r\020L\032\0020!¢\006\004\bL\020KJ\017\020M\032\0020\005H\026¢\006\004\bM\020NJ\017\020O\032\0020\025H\002¢\006\004\bO\020\031J\027\020P\032\0020%2\006\020;\032\0020#H\026¢\006\004\bP\020QJ\027\020P\032\0020%2\006\020?\032\0020>H\026¢\006\004\bP\020EJ\037\020P\032\0020%2\006\020R\032\0020>2\006\020S\032\0020!H\002¢\006\004\bP\020TJ\r\020U\032\0020!¢\006\004\bU\020KJ\r\020V\032\0020\025¢\006\004\bV\020\031J\017\020X\032\0020%H\000¢\006\004\bW\020GJ\017\020Z\032\0020\025H\000¢\006\004\bY\020\031J\023\020[\032\0020%*\0020\rH\002¢\006\004\b[\020\\R\026\020]\032\0020%8\002@\002X\016¢\006\006\n\004\b]\020^R\030\020`\032\004\030\0010_8\002@\002X\016¢\006\006\n\004\b`\020aR\026\020b\032\0020%8\002@\002X\016¢\006\006\n\004\bb\020^R\030\020\016\032\004\030\0010\r8\002@\002X\016¢\006\006\n\004\b\016\020cR\026\020d\032\0020%8\002@\002X\016¢\006\006\n\004\bd\020^R\024\020e\032\0020#8\002X\004¢\006\006\n\004\be\020fR\032\020\b\032\0020\0078\000X\004¢\006\f\n\004\b\b\020g\032\004\bh\020iR\032\020l\032\b\022\004\022\0020k0j8\002X\004¢\006\006\n\004\bl\020mR\026\020\017\032\0020\0138\002@\002X\016¢\006\006\n\004\b\017\020nR\030\0203\032\004\030\0010#8\002@\002X\016¢\006\006\n\004\b3\020fR\024\020\006\032\0020\0058\002X\004¢\006\006\n\004\b\006\020oR\024\020\f\032\0020\0138\002X\004¢\006\006\n\004\b\f\020nR\032\020p\032\b\022\004\022\0020>0j8\002X\004¢\006\006\n\004\bp\020mR\026\020H\032\0020\0138\002@\002X\016¢\006\006\n\004\bH\020nR\024\020\n\032\0020\t8\002X\004¢\006\006\n\004\b\n\020qR\030\020s\032\004\030\0010r8\002@\002X\016¢\006\006\n\004\bs\020tR\026\020u\032\0020!8\002@\002X\016¢\006\006\n\004\bu\020vR\030\020w\032\004\030\0010#8\002@\002X\016¢\006\006\n\004\bw\020fR\026\020J\032\0020!8\002@\002X\016¢\006\006\n\004\bJ\020vR\026\020L\032\0020!8\002@\002X\016¢\006\006\n\004\bL\020vR\026\020U\032\0020!8\002@\002X\016¢\006\006\n\004\bU\020vR\030\0205\032\004\030\001048\002@\002X\016¢\006\006\n\004\b5\020xR\026\020z\032\0020y8\002@\002X\016¢\006\006\n\004\bz\020{R\030\020}\032\004\030\0010|8\002@\002X\016¢\006\006\n\004\b}\020~R\033\020\001\032\004\030\00108\002@\002X\016¢\006\b\n\006\b\001\020\001¨\006\001"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket;", "Lokhttp3/WebSocket;", "Lokhttp3/internal/ws/WebSocketReader$FrameCallback;", "Lokhttp3/internal/concurrent/TaskRunner;", "taskRunner", "Lokhttp3/Request;", "originalRequest", "Lokhttp3/WebSocketListener;", "listener", "Ljava/util/Random;", "random", "", "pingIntervalMillis", "Lokhttp3/internal/ws/WebSocketExtensions;", "extensions", "minimumDeflateSize", "<init>", "(Lokhttp3/internal/concurrent/TaskRunner;Lokhttp3/Request;Lokhttp3/WebSocketListener;Ljava/util/Random;JLokhttp3/internal/ws/WebSocketExtensions;J)V", "timeout", "Ljava/util/concurrent/TimeUnit;", "timeUnit", "", "awaitTermination", "(JLjava/util/concurrent/TimeUnit;)V", "cancel", "()V", "Lokhttp3/Response;", "response", "Lokhttp3/internal/connection/Exchange;", "exchange", "checkUpgradeSuccess$okhttp", "(Lokhttp3/Response;Lokhttp3/internal/connection/Exchange;)V", "checkUpgradeSuccess", "", "code", "", "reason", "", "close", "(ILjava/lang/String;)Z", "cancelAfterCloseMillis", "(ILjava/lang/String;J)Z", "Lokhttp3/OkHttpClient;", "client", "connect", "(Lokhttp3/OkHttpClient;)V", "Ljava/lang/Exception;", "Lkotlin/Exception;", "e", "failWebSocket", "(Ljava/lang/Exception;Lokhttp3/Response;)V", "name", "Lokhttp3/internal/ws/RealWebSocket$Streams;", "streams", "initReaderAndWriter", "(Ljava/lang/String;Lokhttp3/internal/ws/RealWebSocket$Streams;)V", "loopReader", "onReadClose", "(ILjava/lang/String;)V", "text", "onReadMessage", "(Ljava/lang/String;)V", "Lokio/ByteString;", "bytes", "(Lokio/ByteString;)V", "payload", "onReadPing", "onReadPong", "pong", "(Lokio/ByteString;)Z", "processNextFrame", "()Z", "queueSize", "()J", "receivedPingCount", "()I", "receivedPongCount", "request", "()Lokhttp3/Request;", "runWriter", "send", "(Ljava/lang/String;)Z", "data", "formatOpcode", "(Lokio/ByteString;I)Z", "sentPingCount", "tearDown", "writeOneFrame$okhttp", "writeOneFrame", "writePingFrame$okhttp", "writePingFrame", "isValid", "(Lokhttp3/internal/ws/WebSocketExtensions;)Z", "awaitingPong", "Z", "Lokhttp3/Call;", "call", "Lokhttp3/Call;", "enqueuedClose", "Lokhttp3/internal/ws/WebSocketExtensions;", "failed", "key", "Ljava/lang/String;", "Lokhttp3/WebSocketListener;", "getListener$okhttp", "()Lokhttp3/WebSocketListener;", "Ljava/util/ArrayDeque;", "", "messageAndCloseQueue", "Ljava/util/ArrayDeque;", "J", "Lokhttp3/Request;", "pongQueue", "Ljava/util/Random;", "Lokhttp3/internal/ws/WebSocketReader;", "reader", "Lokhttp3/internal/ws/WebSocketReader;", "receivedCloseCode", "I", "receivedCloseReason", "Lokhttp3/internal/ws/RealWebSocket$Streams;", "Lokhttp3/internal/concurrent/TaskQueue;", "taskQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "Lokhttp3/internal/ws/WebSocketWriter;", "writer", "Lokhttp3/internal/ws/WebSocketWriter;", "Lokhttp3/internal/concurrent/Task;", "writerTask", "Lokhttp3/internal/concurrent/Task;", "Companion", "Close", "Message", "Streams", "WriterTask", "okhttp"})
@SourceDebugExtension({"SMAP\nRealWebSocket.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealWebSocket.kt\nokhttp3/internal/ws/RealWebSocket\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 TaskQueue.kt\nokhttp3/internal/concurrent/TaskQueue\n+ 4 Util.kt\nokhttp3/internal/Util\n*L\n1#1,654:1\n1#2:655\n84#3,4:656\n90#3,13:664\n608#4,4:660\n*S KotlinDebug\n*F\n+ 1 RealWebSocket.kt\nokhttp3/internal/ws/RealWebSocket\n*L\n269#1:656,4\n512#1:664,13\n457#1:660,4\n*E\n"})
public final class RealWebSocket implements WebSocket, WebSocketReader.FrameCallback {
  public RealWebSocket(@NotNull TaskRunner taskRunner, @NotNull Request originalRequest, @NotNull WebSocketListener listener, @NotNull Random random, long pingIntervalMillis, @Nullable WebSocketExtensions extensions, long minimumDeflateSize) {
    this.originalRequest = originalRequest;
    this.listener = listener;
    this.random = random;
    this.pingIntervalMillis = pingIntervalMillis;
    this.extensions = extensions;
    this.minimumDeflateSize = minimumDeflateSize;
    this.taskQueue = taskRunner.newQueue();
    this.pongQueue = new ArrayDeque<>();
    this.messageAndCloseQueue = new ArrayDeque();
    this.receivedCloseCode = -1;
    if (!Intrinsics.areEqual("GET", this.originalRequest.method())) {
      int $i$a$-require-RealWebSocket$1 = 0;
      String str = 
        "Request must be GET: " + this.originalRequest.method();
      throw new IllegalArgumentException(str.toString());
    } 
    byte[] arrayOfByte1 = new byte[16], arrayOfByte2 = arrayOfByte1;
    ByteString.Companion companion = ByteString.Companion;
    RealWebSocket realWebSocket = this;
    int $i$a$-apply-RealWebSocket$2 = 0;
    this.random.nextBytes(arrayOfByte2);
    Unit unit = Unit.INSTANCE;
    realWebSocket.key = ByteString.Companion.of$default(companion, arrayOfByte1, 0, 0, 3, null).base64();
  }
  
  @NotNull
  public final WebSocketListener getListener$okhttp() {
    return this.listener;
  }
  
  @NotNull
  public Request request() {
    return this.originalRequest;
  }
  
  public synchronized long queueSize() {
    return this.queueSize;
  }
  
  public void cancel() {
    Intrinsics.checkNotNull(this.call);
    this.call.cancel();
  }
  
  public final void connect(@NotNull OkHttpClient client) {
    Intrinsics.checkNotNullParameter(client, "client");
    if (this.originalRequest.header("Sec-WebSocket-Extensions") != null) {
      failWebSocket(new ProtocolException("Request header not permitted: 'Sec-WebSocket-Extensions'"), null);
      return;
    } 
    OkHttpClient webSocketClient = client.newBuilder().eventListener(EventListener.NONE).protocols(ONLY_HTTP1).build();
    Request request = this.originalRequest.newBuilder().header("Upgrade", "websocket").header("Connection", "Upgrade").header("Sec-WebSocket-Key", this.key).header("Sec-WebSocket-Version", "13").header("Sec-WebSocket-Extensions", "permessage-deflate").build();
    this.call = (Call)new RealCall(webSocketClient, request, true);
    Intrinsics.checkNotNull(this.call);
    this.call.enqueue(new RealWebSocket$connect$1(request));
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000%\n\000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\004*\001\000\b\n\030\0002\0020\001J\037\020\007\032\0020\0062\006\020\003\032\0020\0022\006\020\005\032\0020\004H\026¢\006\004\b\007\020\bJ\037\020\013\032\0020\0062\006\020\003\032\0020\0022\006\020\n\032\0020\tH\026¢\006\004\b\013\020\f¨\006\r"}, d2 = {"okhttp3/internal/ws/RealWebSocket.connect.1", "Lokhttp3/Callback;", "Lokhttp3/Call;", "call", "Ljava/io/IOException;", "e", "", "onFailure", "(Lokhttp3/Call;Ljava/io/IOException;)V", "Lokhttp3/Response;", "response", "onResponse", "(Lokhttp3/Call;Lokhttp3/Response;)V", "okhttp"})
  public static final class RealWebSocket$connect$1 implements Callback {
    RealWebSocket$connect$1(Request $request) {}
    
    public void onResponse(@NotNull Call call, @NotNull Response response) {
      Intrinsics.checkNotNullParameter(call, "call");
      Intrinsics.checkNotNullParameter(response, "response");
      Exchange exchange = response.exchange();
      RealWebSocket.Streams streams = null;
      try {
        RealWebSocket.this.checkUpgradeSuccess$okhttp(response, exchange);
        Intrinsics.checkNotNull(exchange);
        streams = exchange.newWebSocketStreams();
      } catch (IOException e) {
        RealWebSocket.this.failWebSocket(e, response);
        Util.closeQuietly((Closeable)response);
        if (exchange != null) {
          exchange.webSocketUpgradeFailed();
        } else {
        
        } 
        return;
      } 
      WebSocketExtensions extensions = WebSocketExtensions.Companion.parse(response.headers());
      RealWebSocket.this.extensions = extensions;
      if (!RealWebSocket.this.isValid(extensions)) {
        RealWebSocket realWebSocket1 = RealWebSocket.this, realWebSocket2 = RealWebSocket.this;
        synchronized (realWebSocket1) {
          int $i$a$-synchronized-RealWebSocket$connect$1$onResponse$1 = 0;
          realWebSocket2.messageAndCloseQueue.clear();
          boolean bool = realWebSocket2.close(1010, "unexpected Sec-WebSocket-Extensions in response header");
        } 
      } 
      try {
        String name = Util.okHttpName + " WebSocket " + this.$request.url().redact();
        RealWebSocket.this.initReaderAndWriter(name, streams);
        RealWebSocket.this.getListener$okhttp().onOpen(RealWebSocket.this, response);
        RealWebSocket.this.loopReader();
      } catch (Exception e) {
        RealWebSocket.this.failWebSocket(e, null);
      } 
    }
    
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
      Intrinsics.checkNotNullParameter(call, "call");
      Intrinsics.checkNotNullParameter(e, "e");
      RealWebSocket.this.failWebSocket(e, null);
    }
  }
  
  private final boolean isValid(WebSocketExtensions $this$isValid) {
    if ($this$isValid.unknownValues)
      return false; 
    if ($this$isValid.clientMaxWindowBits != null)
      return false; 
    if ($this$isValid.serverMaxWindowBits != null && !(new IntRange(8, 15)).contains($this$isValid.serverMaxWindowBits.intValue()))
      return false; 
    return true;
  }
  
  public final void checkUpgradeSuccess$okhttp(@NotNull Response response, @Nullable Exchange exchange) throws IOException {
    Intrinsics.checkNotNullParameter(response, "response");
    if (response.code() != 101)
      throw new ProtocolException("Expected HTTP 101 response but was '" + response.code() + ' ' + response.message() + '\''); 
    String headerConnection = Response.header$default(response, "Connection", null, 2, null);
    if (!StringsKt.equals("Upgrade", headerConnection, true))
      throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + headerConnection + '\''); 
    String headerUpgrade = Response.header$default(response, "Upgrade", null, 2, null);
    if (!StringsKt.equals("websocket", headerUpgrade, true))
      throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + headerUpgrade + '\''); 
    String headerAccept = Response.header$default(response, "Sec-WebSocket-Accept", null, 2, null);
    String acceptExpected = ByteString.Companion.encodeUtf8(this.key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").sha1().base64();
    if (!Intrinsics.areEqual(acceptExpected, headerAccept))
      throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + acceptExpected + "' but was '" + headerAccept + '\''); 
    if (exchange == null)
      throw new ProtocolException("Web Socket exchange missing: bad interceptor?"); 
  }
  
  public final void initReaderAndWriter(@NotNull String name, @NotNull Streams streams) throws IOException {
    Intrinsics.checkNotNullParameter(name, "name");
    Intrinsics.checkNotNullParameter(streams, "streams");
    Intrinsics.checkNotNull(this.extensions);
    WebSocketExtensions extensions = this.extensions;
    synchronized (this) {
      int $i$a$-synchronized-RealWebSocket$initReaderAndWriter$1 = 0;
      this.name = name;
      this.streams = streams;
      this.writer = new WebSocketWriter(streams.getClient(), streams.getSink(), this.random, extensions.perMessageDeflate, extensions.noContextTakeover(streams.getClient()), this.minimumDeflateSize);
      this.writerTask = new WriterTask();
      if (this.pingIntervalMillis != 0L) {
        long pingIntervalNanos = TimeUnit.MILLISECONDS.toNanos(this.pingIntervalMillis);
        TaskQueue taskQueue = this.taskQueue;
        String name$iv = name + " ping";
        int $i$f$schedule = 0;
        taskQueue.schedule(new RealWebSocket$initReaderAndWriter$lambda$3$$inlined$schedule$1(name$iv, this, pingIntervalNanos), 
            
            pingIntervalNanos);
      } 
      if (!this.messageAndCloseQueue.isEmpty())
        runWriter(); 
      Unit unit = Unit.INSTANCE;
    } 
    this.reader = new WebSocketReader(streams.getClient(), streams.getSource(), this, extensions.perMessageDeflate, extensions.noContextTakeover(!streams.getClient()));
  }
  
  public final void loopReader() throws IOException {
    while (this.receivedCloseCode == -1) {
      Intrinsics.checkNotNull(this.reader);
      this.reader.processNextFrame();
    } 
  }
  
  public final boolean processNextFrame() throws IOException {
    boolean bool;
    try {
      Intrinsics.checkNotNull(this.reader);
      this.reader.processNextFrame();
      bool = (this.receivedCloseCode == -1) ? true : false;
    } catch (Exception e) {
      failWebSocket(e, null);
      bool = false;
    } 
    return bool;
  }
  
  public final void awaitTermination(long timeout, @NotNull TimeUnit timeUnit) throws InterruptedException {
    Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
    this.taskQueue.idleLatch().await(timeout, timeUnit);
  }
  
  public final void tearDown() throws InterruptedException {
    this.taskQueue.shutdown();
    this.taskQueue.idleLatch().await(10L, TimeUnit.SECONDS);
  }
  
  public final synchronized int sentPingCount() {
    return this.sentPingCount;
  }
  
  public final synchronized int receivedPingCount() {
    return this.receivedPingCount;
  }
  
  public final synchronized int receivedPongCount() {
    return this.receivedPongCount;
  }
  
  public void onReadMessage(@NotNull String text) throws IOException {
    Intrinsics.checkNotNullParameter(text, "text");
    this.listener.onMessage(this, text);
  }
  
  public void onReadMessage(@NotNull ByteString bytes) throws IOException {
    Intrinsics.checkNotNullParameter(bytes, "bytes");
    this.listener.onMessage(this, bytes);
  }
  
  public synchronized void onReadPing(@NotNull ByteString payload) {
    Intrinsics.checkNotNullParameter(payload, "payload");
    if (this.failed || (this.enqueuedClose && this.messageAndCloseQueue.isEmpty()))
      return; 
    this.pongQueue.add(payload);
    runWriter();
    int i = this.receivedPingCount;
    this.receivedPingCount = i + 1;
  }
  
  public synchronized void onReadPong(@NotNull ByteString payload) {
    Intrinsics.checkNotNullParameter(payload, "payload");
    int i = this.receivedPongCount;
    this.receivedPongCount = i + 1;
    this.awaitingPong = false;
  }
  
  public void onReadClose(int code, @NotNull String reason) {
    Intrinsics.checkNotNullParameter(reason, "reason");
    if (!((code != -1) ? 1 : 0)) {
      String str = "Failed requirement.";
      throw new IllegalArgumentException(str.toString());
    } 
    Object toClose = null;
    Object readerToClose = null;
    Object writerToClose = null;
    synchronized (this) {
      int $i$a$-synchronized-RealWebSocket$onReadClose$1 = 0;
      if (!((this.receivedCloseCode == -1) ? 1 : 0)) {
        int $i$a$-check-RealWebSocket$onReadClose$1$1 = 0;
        String str = "already closed";
        throw new IllegalStateException(str.toString());
      } 
      this.receivedCloseCode = code;
      this.receivedCloseReason = reason;
      if (this.enqueuedClose && this.messageAndCloseQueue.isEmpty()) {
        toClose = this.streams;
        this.streams = null;
        readerToClose = this.reader;
        this.reader = null;
        writerToClose = this.writer;
        this.writer = null;
        this.taskQueue.shutdown();
      } 
      Unit unit = Unit.INSTANCE;
    } 
    try {
      this.listener.onClosing(this, code, reason);
      if (toClose != null)
        this.listener.onClosed(this, code, reason); 
    } finally {
      if ((Streams)toClose != null) {
        Util.closeQuietly((Streams)toClose);
      } else {
        (Streams)toClose;
      } 
      if ((WebSocketReader)readerToClose != null) {
        Util.closeQuietly((WebSocketReader)readerToClose);
      } else {
        (WebSocketReader)readerToClose;
      } 
      if ((WebSocketWriter)writerToClose != null) {
        Util.closeQuietly((WebSocketWriter)writerToClose);
      } else {
        (WebSocketWriter)writerToClose;
      } 
    } 
  }
  
  public boolean send(@NotNull String text) {
    Intrinsics.checkNotNullParameter(text, "text");
    return send(ByteString.Companion.encodeUtf8(text), 1);
  }
  
  public boolean send(@NotNull ByteString bytes) {
    Intrinsics.checkNotNullParameter(bytes, "bytes");
    return send(bytes, 2);
  }
  
  private final synchronized boolean send(ByteString data, int formatOpcode) {
    if (this.failed || this.enqueuedClose)
      return false; 
    if (this.queueSize + data.size() > 16777216L) {
      close(1001, null);
      return false;
    } 
    this.queueSize += data.size();
    this.messageAndCloseQueue.add(new Message(formatOpcode, data));
    runWriter();
    return true;
  }
  
  public final synchronized boolean pong(@NotNull ByteString payload) {
    Intrinsics.checkNotNullParameter(payload, "payload");
    if (this.failed || (this.enqueuedClose && this.messageAndCloseQueue.isEmpty()))
      return false; 
    this.pongQueue.add(payload);
    runWriter();
    return true;
  }
  
  public boolean close(int code, @Nullable String reason) {
    return close(code, reason, 60000L);
  }
  
  public final synchronized boolean close(int code, @Nullable String reason, long cancelAfterCloseMillis) {
    WebSocketProtocol.INSTANCE.validateCloseCode(code);
    ByteString reasonBytes = null;
    if (reason != null) {
      reasonBytes = ByteString.Companion.encodeUtf8(reason);
      if (!((reasonBytes.size() <= 123L) ? 1 : 0)) {
        int $i$a$-require-RealWebSocket$close$1 = 0;
        String str = "reason.size() > 123: " + reason;
        throw new IllegalArgumentException(str.toString());
      } 
    } 
    if (this.failed || this.enqueuedClose)
      return false; 
    this.enqueuedClose = true;
    this.messageAndCloseQueue.add(new Close(code, reasonBytes, cancelAfterCloseMillis));
    runWriter();
    return true;
  }
  
  private final void runWriter() {
    Object $this$assertThreadHoldsLock$iv = this;
    int $i$f$assertThreadHoldsLock = 0;
    if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv); 
    Task writerTask = this.writerTask;
    if (writerTask != null)
      TaskQueue.schedule$default(this.taskQueue, writerTask, 0L, 2, null); 
  }
  
  public final boolean writeOneFrame$okhttp() throws IOException {
    Object writer = null;
    Object pong = null;
    Object messageOrClose = null;
    int receivedCloseCode = 0;
    receivedCloseCode = -1;
    Object receivedCloseReason = null;
    Object streamsToClose = null;
    Object readerToClose = null;
    Object writerToClose = null;
    synchronized (this) {
      int $i$a$-synchronized-RealWebSocket$writeOneFrame$1 = 0;
      if (this.failed)
        return false; 
      writer = this.writer;
      pong = this.pongQueue.poll();
      if (pong == null) {
        messageOrClose = this.messageAndCloseQueue.poll();
        if (messageOrClose instanceof Close) {
          receivedCloseCode = this.receivedCloseCode;
          receivedCloseReason = this.receivedCloseReason;
          if (receivedCloseCode != -1) {
            streamsToClose = this.streams;
            this.streams = null;
            readerToClose = this.reader;
            this.reader = null;
            writerToClose = this.writer;
            this.writer = null;
            this.taskQueue.shutdown();
          } else {
            long cancelAfterCloseMillis = ((Close)messageOrClose).getCancelAfterCloseMillis();
            TaskQueue taskQueue = this.taskQueue;
            String str = this.name + " cancel";
            long delayNanos$iv = TimeUnit.MILLISECONDS.toNanos(cancelAfterCloseMillis);
            boolean cancelable$iv = true;
            int $i$f$execute = 0;
            taskQueue.schedule(new RealWebSocket$writeOneFrame$lambda$8$$inlined$execute$default$1(str, cancelable$iv, this), 
                
                delayNanos$iv);
          } 
        } else if (messageOrClose == null) {
          return false;
        } 
      } 
      Unit unit = Unit.INSTANCE;
    } 
    try {
      if (pong != null) {
        Intrinsics.checkNotNull(writer);
        writer.writePong((ByteString)pong);
      } else if (messageOrClose instanceof Message) {
        Message message = (Message)messageOrClose;
        Intrinsics.checkNotNull(writer);
        writer.writeMessageFrame(message.getFormatOpcode(), message.getData());
        synchronized (this) {
          int $i$a$-synchronized-RealWebSocket$writeOneFrame$2 = 0;
          this.queueSize -= message.getData().size();
          Unit unit = Unit.INSTANCE;
        } 
      } else if (messageOrClose instanceof Close) {
        Close close = (Close)messageOrClose;
        Intrinsics.checkNotNull(writer);
        writer.writeClose(close.getCode(), close.getReason());
        if (streamsToClose != null) {
          Intrinsics.checkNotNull(receivedCloseReason);
          this.listener.onClosed(this, receivedCloseCode, (String)receivedCloseReason);
        } 
      } else {
        throw new AssertionError();
      } 
      return true;
    } finally {
      if ((Streams)streamsToClose != null) {
        Util.closeQuietly((Streams)streamsToClose);
      } else {
        (Streams)streamsToClose;
      } 
      if ((WebSocketReader)readerToClose != null) {
        Util.closeQuietly((WebSocketReader)readerToClose);
      } else {
        (WebSocketReader)readerToClose;
      } 
      if ((WebSocketWriter)writerToClose != null) {
        Util.closeQuietly((WebSocketWriter)writerToClose);
      } else {
        (WebSocketWriter)writerToClose;
      } 
    } 
  }
  
  public final void writePingFrame$okhttp() {
    Object writer = null;
    int failedPing = 0;
    synchronized (this) {
      int $i$a$-synchronized-RealWebSocket$writePingFrame$1 = 0;
      if (this.failed)
        return; 
      if (this.writer == null)
        return; 
      failedPing = this.awaitingPong ? this.sentPingCount : -1;
      int i = this.sentPingCount;
      this.sentPingCount = i + 1;
      this.awaitingPong = true;
      Unit unit = Unit.INSTANCE;
    } 
    if (failedPing != -1) {
      failWebSocket(new SocketTimeoutException("sent ping but didn't receive pong within " + this.pingIntervalMillis + "ms (after " + (failedPing - 1) + " successful ping/pongs)"), null);
      return;
    } 
    try {
      writer.writePing(ByteString.EMPTY);
    } catch (IOException e) {
      failWebSocket(e, null);
    } 
  }
  
  public final void failWebSocket(@NotNull Exception e, @Nullable Response response) {
    Intrinsics.checkNotNullParameter(e, "e");
    Object streamsToClose = null;
    Object readerToClose = null;
    Object writerToClose = null;
    synchronized (this) {
      int $i$a$-synchronized-RealWebSocket$failWebSocket$1 = 0;
      if (this.failed)
        return; 
      this.failed = true;
      streamsToClose = this.streams;
      this.streams = null;
      readerToClose = this.reader;
      this.reader = null;
      writerToClose = this.writer;
      this.writer = null;
      this.taskQueue.shutdown();
      Unit unit = Unit.INSTANCE;
    } 
    try {
      this.listener.onFailure(this, e, response);
    } finally {
      if ((Streams)streamsToClose != null) {
        Util.closeQuietly((Streams)streamsToClose);
      } else {
        (Streams)streamsToClose;
      } 
      if ((WebSocketReader)readerToClose != null) {
        Util.closeQuietly((WebSocketReader)readerToClose);
      } else {
        (WebSocketReader)readerToClose;
      } 
      if ((WebSocketWriter)writerToClose != null) {
        Util.closeQuietly((WebSocketWriter)writerToClose);
      } else {
        (WebSocketWriter)writerToClose;
      } 
    } 
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\026\n\002\030\002\n\002\020\000\n\002\020\b\n\000\n\002\030\002\n\002\b\n\b\000\030\0002\0020\001B\027\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007R\027\020\005\032\0020\0048\006¢\006\f\n\004\b\005\020\b\032\004\b\t\020\nR\027\020\003\032\0020\0028\006¢\006\f\n\004\b\003\020\013\032\004\b\f\020\r¨\006\016"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Message;", "", "", "formatOpcode", "Lokio/ByteString;", "data", "<init>", "(ILokio/ByteString;)V", "Lokio/ByteString;", "getData", "()Lokio/ByteString;", "I", "getFormatOpcode", "()I", "okhttp"})
  public static final class Message {
    private final int formatOpcode;
    
    @NotNull
    private final ByteString data;
    
    public Message(int formatOpcode, @NotNull ByteString data) {
      this.formatOpcode = formatOpcode;
      this.data = data;
    }
    
    public final int getFormatOpcode() {
      return this.formatOpcode;
    }
    
    @NotNull
    public final ByteString getData() {
      return this.data;
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\002\020\b\n\000\n\002\030\002\n\000\n\002\020\t\n\002\b\r\b\000\030\0002\0020\001B!\022\006\020\003\032\0020\002\022\b\020\005\032\004\030\0010\004\022\006\020\007\032\0020\006¢\006\004\b\b\020\tR\027\020\007\032\0020\0068\006¢\006\f\n\004\b\007\020\n\032\004\b\013\020\fR\027\020\003\032\0020\0028\006¢\006\f\n\004\b\003\020\r\032\004\b\016\020\017R\031\020\005\032\004\030\0010\0048\006¢\006\f\n\004\b\005\020\020\032\004\b\021\020\022¨\006\023"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Close;", "", "", "code", "Lokio/ByteString;", "reason", "", "cancelAfterCloseMillis", "<init>", "(ILokio/ByteString;J)V", "J", "getCancelAfterCloseMillis", "()J", "I", "getCode", "()I", "Lokio/ByteString;", "getReason", "()Lokio/ByteString;", "okhttp"})
  public static final class Close {
    private final int code;
    
    @Nullable
    private final ByteString reason;
    
    private final long cancelAfterCloseMillis;
    
    public Close(int code, @Nullable ByteString reason, long cancelAfterCloseMillis) {
      this.code = code;
      this.reason = reason;
      this.cancelAfterCloseMillis = cancelAfterCloseMillis;
    }
    
    public final int getCode() {
      return this.code;
    }
    
    @Nullable
    public final ByteString getReason() {
      return this.reason;
    }
    
    public final long getCancelAfterCloseMillis() {
      return this.cancelAfterCloseMillis;
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\030\002\n\002\020\013\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\r\b&\030\0002\0020\001B\037\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\006\020\007\032\0020\006¢\006\004\b\b\020\tR\027\020\003\032\0020\0028\006¢\006\f\n\004\b\003\020\n\032\004\b\013\020\fR\027\020\007\032\0020\0068\006¢\006\f\n\004\b\007\020\r\032\004\b\016\020\017R\027\020\005\032\0020\0048\006¢\006\f\n\004\b\005\020\020\032\004\b\021\020\022¨\006\023"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Streams;", "Ljava/io/Closeable;", "", "client", "Lokio/BufferedSource;", "source", "Lokio/BufferedSink;", "sink", "<init>", "(ZLokio/BufferedSource;Lokio/BufferedSink;)V", "Z", "getClient", "()Z", "Lokio/BufferedSink;", "getSink", "()Lokio/BufferedSink;", "Lokio/BufferedSource;", "getSource", "()Lokio/BufferedSource;", "okhttp"})
  public static abstract class Streams implements Closeable {
    private final boolean client;
    
    @NotNull
    private final BufferedSource source;
    
    @NotNull
    private final BufferedSink sink;
    
    public Streams(boolean client, @NotNull BufferedSource source, @NotNull BufferedSink sink) {
      this.client = client;
      this.source = source;
      this.sink = sink;
    }
    
    public final boolean getClient() {
      return this.client;
    }
    
    @NotNull
    public final BufferedSource getSource() {
      return this.source;
    }
    
    @NotNull
    public final BufferedSink getSink() {
      return this.sink;
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\t\n\002\b\003\b\004\030\0002\0020\001B\007¢\006\004\b\002\020\003J\017\020\005\032\0020\004H\026¢\006\004\b\005\020\006¨\006\007"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$WriterTask;", "Lokhttp3/internal/concurrent/Task;", "<init>", "(Lokhttp3/internal/ws/RealWebSocket;)V", "", "runOnce", "()J", "okhttp"})
  private final class WriterTask extends Task {
    public WriterTask() {
      super(RealWebSocket.this.name + " writer", false, 2, null);
    }
    
    public long runOnce() {
      try {
        if (RealWebSocket.this.writeOneFrame$okhttp())
          return 0L; 
      } catch (IOException e) {
        RealWebSocket.this.failWebSocket(e, null);
      } 
      return -1L;
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000 \n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\t\n\002\b\004\n\002\020 \n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\024\020\005\032\0020\0048\002XT¢\006\006\n\004\b\005\020\006R\024\020\007\032\0020\0048\006XT¢\006\006\n\004\b\007\020\006R\024\020\b\032\0020\0048\002XT¢\006\006\n\004\b\b\020\006R\032\020\013\032\b\022\004\022\0020\n0\t8\002X\004¢\006\006\n\004\b\013\020\f¨\006\r"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Companion;", "", "<init>", "()V", "", "CANCEL_AFTER_CLOSE_MILLIS", "J", "DEFAULT_MINIMUM_DEFLATE_SIZE", "MAX_QUEUE_SIZE", "", "Lokhttp3/Protocol;", "ONLY_HTTP1", "Ljava/util/List;", "okhttp"})
  public static final class Companion {
    private Companion() {}
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final Request originalRequest;
  
  @NotNull
  private final WebSocketListener listener;
  
  @NotNull
  private final Random random;
  
  private final long pingIntervalMillis;
  
  @Nullable
  private WebSocketExtensions extensions;
  
  private long minimumDeflateSize;
  
  @NotNull
  private final String key;
  
  @Nullable
  private Call call;
  
  @Nullable
  private Task writerTask;
  
  @Nullable
  private WebSocketReader reader;
  
  @Nullable
  private WebSocketWriter writer;
  
  @NotNull
  private TaskQueue taskQueue;
  
  @Nullable
  private String name;
  
  @Nullable
  private Streams streams;
  
  @NotNull
  private final ArrayDeque<ByteString> pongQueue;
  
  @NotNull
  private final ArrayDeque<Object> messageAndCloseQueue;
  
  private long queueSize;
  
  private boolean enqueuedClose;
  
  private int receivedCloseCode;
  
  @Nullable
  private String receivedCloseReason;
  
  private boolean failed;
  
  private int sentPingCount;
  
  private int receivedPingCount;
  
  private int receivedPongCount;
  
  private boolean awaitingPong;
  
  @NotNull
  private static final List<Protocol> ONLY_HTTP1 = CollectionsKt.listOf(Protocol.HTTP_1_1);
  
  private static final long MAX_QUEUE_SIZE = 16777216L;
  
  private static final long CANCEL_AFTER_CLOSE_MILLIS = 60000L;
  
  public static final long DEFAULT_MINIMUM_DEFLATE_SIZE = 1024L;
}
