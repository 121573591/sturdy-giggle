package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Headers;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000¸\001\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\007\n\002\020\b\n\000\n\002\030\002\n\002\b\002\n\002\020\t\n\000\n\002\020\013\n\002\b\003\n\002\020 \n\002\030\002\n\002\b\b\n\002\030\002\n\002\b\030\n\002\030\002\n\002\b\007\n\002\030\002\n\002\b\b\n\002\030\002\n\002\b\034\n\002\020\016\n\002\b\004\n\002\020#\n\002\b\r\n\002\030\002\n\002\b\016\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\b\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\004\n\002\020%\n\002\b\t\n\002\030\002\n\002\b\n\030\000 º\0012\0020\001:\b»\001º\001¼\001½\001B\021\b\000\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\r\020\007\032\0020\006¢\006\004\b\007\020\bJ\017\020\t\032\0020\006H\026¢\006\004\b\t\020\bJ)\020\t\032\0020\0062\006\020\013\032\0020\n2\006\020\f\032\0020\n2\b\020\016\032\004\030\0010\rH\000¢\006\004\b\017\020\020J\031\020\022\032\0020\0062\b\020\021\032\004\030\0010\rH\002¢\006\004\b\022\020\023J\r\020\024\032\0020\006¢\006\004\b\024\020\bJ\027\020\030\032\004\030\0010\0272\006\020\026\032\0020\025¢\006\004\b\030\020\031J\025\020\035\032\0020\0342\006\020\033\032\0020\032¢\006\004\b\035\020\036J-\020$\032\0020\0272\006\020\037\032\0020\0252\f\020\"\032\b\022\004\022\0020!0 2\006\020#\032\0020\034H\002¢\006\004\b$\020%J#\020$\032\0020\0272\f\020\"\032\b\022\004\022\0020!0 2\006\020#\032\0020\034¢\006\004\b$\020&J\r\020'\032\0020\025¢\006\004\b'\020(J/\0200\032\0020\0062\006\020)\032\0020\0252\006\020+\032\0020*2\006\020,\032\0020\0252\006\020-\032\0020\034H\000¢\006\004\b.\020/J-\0203\032\0020\0062\006\020)\032\0020\0252\f\020\"\032\b\022\004\022\0020!0 2\006\020-\032\0020\034H\000¢\006\004\b1\0202J%\0206\032\0020\0062\006\020)\032\0020\0252\f\020\"\032\b\022\004\022\0020!0 H\000¢\006\004\b4\0205J\037\020:\032\0020\0062\006\020)\032\0020\0252\006\0207\032\0020\nH\000¢\006\004\b8\0209J+\020;\032\0020\0272\006\020\037\032\0020\0252\f\020\"\032\b\022\004\022\0020!0 2\006\020#\032\0020\034¢\006\004\b;\020%J\027\020>\032\0020\0342\006\020)\032\0020\025H\000¢\006\004\b<\020=J\031\020@\032\004\030\0010\0272\006\020)\032\0020\025H\000¢\006\004\b?\020\031J\017\020B\032\0020\006H\000¢\006\004\bA\020\bJ\025\020E\032\0020\0062\006\020D\032\0020C¢\006\004\bE\020FJ\025\020H\032\0020\0062\006\020G\032\0020\n¢\006\004\bH\020IJ#\020M\032\0020\0062\b\b\002\020J\032\0020\0342\b\b\002\020L\032\0020KH\007¢\006\004\bM\020NJ\027\020R\032\0020\0062\006\020O\032\0020\032H\000¢\006\004\bP\020QJ/\020V\032\0020\0062\006\020)\032\0020\0252\006\020S\032\0020\0342\b\020U\032\004\030\0010T2\006\020,\032\0020\032¢\006\004\bV\020WJ-\020[\032\0020\0062\006\020)\032\0020\0252\006\020S\032\0020\0342\f\020X\032\b\022\004\022\0020!0 H\000¢\006\004\bY\020ZJ\r\020\\\032\0020\006¢\006\004\b\\\020\bJ%\020\\\032\0020\0062\006\020]\032\0020\0342\006\020^\032\0020\0252\006\020_\032\0020\025¢\006\004\b\\\020`J\r\020a\032\0020\006¢\006\004\ba\020\bJ\037\020c\032\0020\0062\006\020)\032\0020\0252\006\020G\032\0020\nH\000¢\006\004\bb\0209J\037\020e\032\0020\0062\006\020)\032\0020\0252\006\0207\032\0020\nH\000¢\006\004\bd\0209J\037\020i\032\0020\0062\006\020)\032\0020\0252\006\020f\032\0020\032H\000¢\006\004\bg\020hR\026\020j\032\0020\0328\002@\002X\016¢\006\006\n\004\bj\020kR\026\020l\032\0020\0328\002@\002X\016¢\006\006\n\004\bl\020kR\032\020m\032\0020\0348\000X\004¢\006\f\n\004\bm\020n\032\004\bo\020pR\032\020r\032\0020q8\000X\004¢\006\f\n\004\br\020s\032\004\bt\020uR\032\020w\032\b\022\004\022\0020\0250v8\002X\004¢\006\006\n\004\bw\020xR\026\020y\032\0020\0328\002@\002X\016¢\006\006\n\004\by\020kR\026\020z\032\0020\0328\002@\002X\016¢\006\006\n\004\bz\020kR\026\020{\032\0020\0328\002@\002X\016¢\006\006\n\004\b{\020kR\026\020|\032\0020\0328\002@\002X\016¢\006\006\n\004\b|\020kR\026\020}\032\0020\0328\002@\002X\016¢\006\006\n\004\b}\020kR\026\020~\032\0020\0348\002@\002X\016¢\006\006\n\004\b~\020nR&\020\032\0020\0258\000@\000X\016¢\006\026\n\005\b\020\001\032\005\b\001\020(\"\006\b\001\020\001R \020\001\032\0030\0018\000X\004¢\006\020\n\006\b\001\020\001\032\006\b\001\020\001R(\020\001\032\0020\0258\000@\000X\016¢\006\027\n\006\b\001\020\001\032\005\b\001\020(\"\006\b\001\020\001R\034\020\001\032\0020C8\006¢\006\020\n\006\b\001\020\001\032\006\b\001\020\001R(\020\001\032\0020C8\006@\006X\016¢\006\027\n\006\b\001\020\001\032\006\b\001\020\001\"\005\b\001\020FR\030\020\001\032\0030\0018\002X\004¢\006\b\n\006\b\001\020\001R\030\020\001\032\0030\0018\002X\004¢\006\b\n\006\b\001\020\001R)\020\001\032\0020\0322\007\020\001\032\0020\0328\006@BX\016¢\006\017\n\005\b\001\020k\032\006\b\001\020\001R)\020\001\032\0020\0322\007\020\001\032\0020\0328\006@BX\016¢\006\017\n\005\b\001\020k\032\006\b\001\020\001R!\020 \001\032\0070\001R\0020\0008\006¢\006\020\n\006\b \001\020¡\001\032\006\b¢\001\020£\001R\030\020¤\001\032\0030\0018\002X\004¢\006\b\n\006\b¤\001\020\001R \020¦\001\032\0030¥\0018\000X\004¢\006\020\n\006\b¦\001\020§\001\032\006\b¨\001\020©\001R,\020«\001\032\017\022\004\022\0020\025\022\004\022\0020\0270ª\0018\000X\004¢\006\020\n\006\b«\001\020¬\001\032\006\b­\001\020®\001R\025\020L\032\0020K8\002X\004¢\006\007\n\005\bL\020¯\001R)\020°\001\032\0020\0322\007\020\001\032\0020\0328\006@BX\016¢\006\017\n\005\b°\001\020k\032\006\b±\001\020\001R)\020²\001\032\0020\0322\007\020\001\032\0020\0328\006@BX\016¢\006\017\n\005\b²\001\020k\032\006\b³\001\020\001R\035\020µ\001\032\0030´\0018\006¢\006\020\n\006\bµ\001\020¶\001\032\006\b·\001\020¸\001R\030\020¹\001\032\0030\0018\002X\004¢\006\b\n\006\b¹\001\020\001¨\006¾\001"}, d2 = {"Lokhttp3/internal/http2/Http2Connection;", "Ljava/io/Closeable;", "Lokhttp3/internal/http2/Http2Connection$Builder;", "builder", "<init>", "(Lokhttp3/internal/http2/Http2Connection$Builder;)V", "", "awaitPong", "()V", "close", "Lokhttp3/internal/http2/ErrorCode;", "connectionCode", "streamCode", "Ljava/io/IOException;", "cause", "close$okhttp", "(Lokhttp3/internal/http2/ErrorCode;Lokhttp3/internal/http2/ErrorCode;Ljava/io/IOException;)V", "e", "failConnection", "(Ljava/io/IOException;)V", "flush", "", "id", "Lokhttp3/internal/http2/Http2Stream;", "getStream", "(I)Lokhttp3/internal/http2/Http2Stream;", "", "nowNs", "", "isHealthy", "(J)Z", "associatedStreamId", "", "Lokhttp3/internal/http2/Header;", "requestHeaders", "out", "newStream", "(ILjava/util/List;Z)Lokhttp3/internal/http2/Http2Stream;", "(Ljava/util/List;Z)Lokhttp3/internal/http2/Http2Stream;", "openStreamCount", "()I", "streamId", "Lokio/BufferedSource;", "source", "byteCount", "inFinished", "pushDataLater$okhttp", "(ILokio/BufferedSource;IZ)V", "pushDataLater", "pushHeadersLater$okhttp", "(ILjava/util/List;Z)V", "pushHeadersLater", "pushRequestLater$okhttp", "(ILjava/util/List;)V", "pushRequestLater", "errorCode", "pushResetLater$okhttp", "(ILokhttp3/internal/http2/ErrorCode;)V", "pushResetLater", "pushStream", "pushedStream$okhttp", "(I)Z", "pushedStream", "removeStream$okhttp", "removeStream", "sendDegradedPingLater$okhttp", "sendDegradedPingLater", "Lokhttp3/internal/http2/Settings;", "settings", "setSettings", "(Lokhttp3/internal/http2/Settings;)V", "statusCode", "shutdown", "(Lokhttp3/internal/http2/ErrorCode;)V", "sendConnectionPreface", "Lokhttp3/internal/concurrent/TaskRunner;", "taskRunner", "start", "(ZLokhttp3/internal/concurrent/TaskRunner;)V", "read", "updateConnectionFlowControl$okhttp", "(J)V", "updateConnectionFlowControl", "outFinished", "Lokio/Buffer;", "buffer", "writeData", "(IZLokio/Buffer;J)V", "alternating", "writeHeaders$okhttp", "(IZLjava/util/List;)V", "writeHeaders", "writePing", "reply", "payload1", "payload2", "(ZII)V", "writePingAndAwaitPong", "writeSynReset$okhttp", "writeSynReset", "writeSynResetLater$okhttp", "writeSynResetLater", "unacknowledgedBytesRead", "writeWindowUpdateLater$okhttp", "(IJ)V", "writeWindowUpdateLater", "awaitPingsSent", "J", "awaitPongsReceived", "client", "Z", "getClient$okhttp", "()Z", "", "connectionName", "Ljava/lang/String;", "getConnectionName$okhttp", "()Ljava/lang/String;", "", "currentPushRequests", "Ljava/util/Set;", "degradedPingsSent", "degradedPongDeadlineNs", "degradedPongsReceived", "intervalPingsSent", "intervalPongsReceived", "isShutdown", "lastGoodStreamId", "I", "getLastGoodStreamId$okhttp", "setLastGoodStreamId$okhttp", "(I)V", "Lokhttp3/internal/http2/Http2Connection$Listener;", "listener", "Lokhttp3/internal/http2/Http2Connection$Listener;", "getListener$okhttp", "()Lokhttp3/internal/http2/Http2Connection$Listener;", "nextStreamId", "getNextStreamId$okhttp", "setNextStreamId$okhttp", "okHttpSettings", "Lokhttp3/internal/http2/Settings;", "getOkHttpSettings", "()Lokhttp3/internal/http2/Settings;", "peerSettings", "getPeerSettings", "setPeerSettings", "Lokhttp3/internal/http2/PushObserver;", "pushObserver", "Lokhttp3/internal/http2/PushObserver;", "Lokhttp3/internal/concurrent/TaskQueue;", "pushQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "<set-?>", "readBytesAcknowledged", "getReadBytesAcknowledged", "()J", "readBytesTotal", "getReadBytesTotal", "Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "readerRunnable", "Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "getReaderRunnable", "()Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "settingsListenerQueue", "Ljava/net/Socket;", "socket", "Ljava/net/Socket;", "getSocket$okhttp", "()Ljava/net/Socket;", "", "streams", "Ljava/util/Map;", "getStreams$okhttp", "()Ljava/util/Map;", "Lokhttp3/internal/concurrent/TaskRunner;", "writeBytesMaximum", "getWriteBytesMaximum", "writeBytesTotal", "getWriteBytesTotal", "Lokhttp3/internal/http2/Http2Writer;", "writer", "Lokhttp3/internal/http2/Http2Writer;", "getWriter", "()Lokhttp3/internal/http2/Http2Writer;", "writerQueue", "Companion", "Builder", "Listener", "ReaderRunnable", "okhttp"})
@SourceDebugExtension({"SMAP\nHttp2Connection.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http2Connection.kt\nokhttp3/internal/http2/Http2Connection\n+ 2 TaskQueue.kt\nokhttp3/internal/concurrent/TaskQueue\n+ 3 Util.kt\nokhttp3/internal/Util\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 6 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,1006:1\n84#2,4:1007\n90#2,13:1014\n90#2,13:1027\n90#2,13:1069\n90#2,13:1082\n90#2,13:1095\n90#2,13:1108\n90#2,13:1121\n90#2,13:1134\n563#3:1011\n557#3:1013\n557#3:1040\n615#3,4:1041\n402#3,5:1045\n402#3,5:1053\n402#3,5:1059\n402#3,5:1064\n1#4:1012\n37#5,2:1050\n13309#6:1052\n13310#6:1058\n*S KotlinDebug\n*F\n+ 1 Http2Connection.kt\nokhttp3/internal/http2/Http2Connection\n*L\n152#1:1007,4\n340#1:1014,13\n361#1:1027,13\n506#1:1069,13\n554#1:1082,13\n893#1:1095,13\n911#1:1108,13\n938#1:1121,13\n952#1:1134,13\n183#1:1011\n319#1:1013\n402#1:1040\n446#1:1041,4\n448#1:1045,5\n461#1:1053,5\n467#1:1059,5\n472#1:1064,5\n455#1:1050,2\n460#1:1052\n460#1:1058\n*E\n"})
public final class Http2Connection implements Closeable {
  public Http2Connection(@NotNull Builder builder) {
    this.client = builder.getClient$okhttp();
    this.listener = builder.getListener$okhttp();
    this.streams = new LinkedHashMap<>();
    this.connectionName = builder.getConnectionName$okhttp();
    this.nextStreamId = builder.getClient$okhttp() ? 3 : 2;
    this.taskRunner = builder.getTaskRunner$okhttp();
    this.writerQueue = this.taskRunner.newQueue();
    this.pushQueue = this.taskRunner.newQueue();
    this.settingsListenerQueue = this.taskRunner.newQueue();
    this.pushObserver = builder.getPushObserver$okhttp();
    Settings settings1 = new Settings(), settings2 = settings1;
    Http2Connection http2Connection = this;
    int $i$a$-apply-Http2Connection$okHttpSettings$1 = 0;
    if (builder.getClient$okhttp())
      settings2.set(7, 16777216); 
    http2Connection.okHttpSettings = settings1;
    this.peerSettings = DEFAULT_SETTINGS;
    this.writeBytesMaximum = this.peerSettings.getInitialWindowSize();
    this.socket = builder.getSocket$okhttp();
    this.writer = new Http2Writer(builder.getSink$okhttp(), this.client);
    this.readerRunnable = new ReaderRunnable(new Http2Reader(builder.getSource$okhttp(), this.client));
    this.currentPushRequests = new LinkedHashSet<>();
    if (builder.getPingIntervalMillis$okhttp() != 0) {
      long pingIntervalNanos = TimeUnit.MILLISECONDS.toNanos(builder.getPingIntervalMillis$okhttp());
      TaskQueue taskQueue = this.writerQueue;
      String name$iv = this.connectionName + " ping";
      int $i$f$schedule = 0;
      taskQueue.schedule(new Http2Connection$special$$inlined$schedule$1(name$iv, this, pingIntervalNanos), 
          
          pingIntervalNanos);
    } 
  }
  
  public final boolean getClient$okhttp() {
    return this.client;
  }
  
  @NotNull
  public final Listener getListener$okhttp() {
    return this.listener;
  }
  
  @NotNull
  public final Map<Integer, Http2Stream> getStreams$okhttp() {
    return this.streams;
  }
  
  @NotNull
  public final String getConnectionName$okhttp() {
    return this.connectionName;
  }
  
  public final int getLastGoodStreamId$okhttp() {
    return this.lastGoodStreamId;
  }
  
  public final void setLastGoodStreamId$okhttp(int <set-?>) {
    this.lastGoodStreamId = <set-?>;
  }
  
  public final int getNextStreamId$okhttp() {
    return this.nextStreamId;
  }
  
  public final void setNextStreamId$okhttp(int <set-?>) {
    this.nextStreamId = <set-?>;
  }
  
  @NotNull
  public final Settings getOkHttpSettings() {
    return this.okHttpSettings;
  }
  
  @NotNull
  public final Settings getPeerSettings() {
    return this.peerSettings;
  }
  
  public final void setPeerSettings(@NotNull Settings <set-?>) {
    Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
    this.peerSettings = <set-?>;
  }
  
  public final long getReadBytesTotal() {
    return this.readBytesTotal;
  }
  
  public final long getReadBytesAcknowledged() {
    return this.readBytesAcknowledged;
  }
  
  public final long getWriteBytesTotal() {
    return this.writeBytesTotal;
  }
  
  public final long getWriteBytesMaximum() {
    return this.writeBytesMaximum;
  }
  
  @NotNull
  public final Socket getSocket$okhttp() {
    return this.socket;
  }
  
  @NotNull
  public final Http2Writer getWriter() {
    return this.writer;
  }
  
  @NotNull
  public final ReaderRunnable getReaderRunnable() {
    return this.readerRunnable;
  }
  
  public final synchronized int openStreamCount() {
    return this.streams.size();
  }
  
  @Nullable
  public final synchronized Http2Stream getStream(int id) {
    return this.streams.get(Integer.valueOf(id));
  }
  
  @Nullable
  public final synchronized Http2Stream removeStream$okhttp(int streamId) {
    Http2Stream stream = this.streams.remove(Integer.valueOf(streamId));
    Object $this$notifyAll$iv = this;
    int $i$f$notifyAll = 0;
    Intrinsics.checkNotNull($this$notifyAll$iv, "null cannot be cast to non-null type java.lang.Object");
    $this$notifyAll$iv.notifyAll();
    return stream;
  }
  
  public final synchronized void updateConnectionFlowControl$okhttp(long read) {
    this.readBytesTotal += read;
    long readBytesToAcknowledge = this.readBytesTotal - this.readBytesAcknowledged;
    if (readBytesToAcknowledge >= (this.okHttpSettings.getInitialWindowSize() / 2)) {
      writeWindowUpdateLater$okhttp(0, readBytesToAcknowledge);
      this.readBytesAcknowledged += readBytesToAcknowledge;
    } 
  }
  
  @NotNull
  public final Http2Stream pushStream(int associatedStreamId, @NotNull List<Header> requestHeaders, boolean out) throws IOException {
    Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
    if (!(!this.client ? 1 : 0)) {
      int $i$a$-check-Http2Connection$pushStream$1 = 0;
      String str = "Client cannot push requests.";
      throw new IllegalStateException(str.toString());
    } 
    return newStream(associatedStreamId, requestHeaders, out);
  }
  
  @NotNull
  public final Http2Stream newStream(@NotNull List<Header> requestHeaders, boolean out) throws IOException {
    Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
    return newStream(0, requestHeaders, out);
  }
  
  private final Http2Stream newStream(int associatedStreamId, List<Header> requestHeaders, boolean out) throws IOException {
    boolean outFinished = !out;
    boolean inFinished = false;
    boolean flushHeaders = false;
    Object stream = null;
    int streamId = 0;
    synchronized (this.writer) {
      int $i$a$-synchronized-Http2Connection$newStream$1 = 0;
      synchronized (this) {
        int $i$a$-synchronized-Http2Connection$newStream$1$1 = 0;
        if (this.nextStreamId > 1073741823)
          shutdown(ErrorCode.REFUSED_STREAM); 
        if (this.isShutdown)
          throw new ConnectionShutdownException(); 
        streamId = this.nextStreamId;
        this.nextStreamId += 2;
        stream = new Http2Stream(streamId, this, outFinished, inFinished, null);
        flushHeaders = (!out || this.writeBytesTotal >= this.writeBytesMaximum || stream.getWriteBytesTotal() >= stream.getWriteBytesMaximum());
        if (stream.isOpen())
          this.streams.put(Integer.valueOf(streamId), stream); 
        Unit unit1 = Unit.INSTANCE;
      } 
      if (associatedStreamId == 0) {
        this.writer.headers(outFinished, streamId, requestHeaders);
      } else {
        if (!(!this.client ? 1 : 0)) {
          int $i$a$-require-Http2Connection$newStream$1$2 = 0;
          String str = "client streams shouldn't have associated stream IDs";
          throw new IllegalArgumentException(str.toString());
        } 
        this.writer.pushPromise(associatedStreamId, streamId, requestHeaders);
      } 
      Unit unit = Unit.INSTANCE;
    } 
    if (flushHeaders)
      this.writer.flush(); 
    return (Http2Stream)stream;
  }
  
  public final void writeHeaders$okhttp(int streamId, boolean outFinished, @NotNull List<Header> alternating) throws IOException {
    Intrinsics.checkNotNullParameter(alternating, "alternating");
    this.writer.headers(outFinished, streamId, alternating);
  }
  
  public final void writeData(int streamId, boolean outFinished, @Nullable Buffer buffer, long byteCount) throws IOException {
    if (byteCount == 0L) {
      this.writer.data(outFinished, streamId, buffer, 0);
      return;
    } 
    long l = 0L;
    l = byteCount;
    while (l > 0L) {
      int toWrite = 0;
      synchronized (this) {
        int $i$a$-synchronized-Http2Connection$writeData$1 = 0;
        try {
          while (this.writeBytesTotal >= this.writeBytesMaximum) {
            if (!this.streams.containsKey(Integer.valueOf(streamId)))
              throw new IOException("stream closed"); 
            Object $this$wait$iv = this;
            int $i$f$wait = 0;
            Intrinsics.checkNotNull($this$wait$iv, "null cannot be cast to non-null type java.lang.Object");
            $this$wait$iv.wait();
          } 
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          throw new InterruptedIOException();
        } 
        toWrite = (int)Math.min(l, this.writeBytesMaximum - this.writeBytesTotal);
        toWrite = Math.min(toWrite, this.writer.maxDataLength());
        this.writeBytesTotal += toWrite;
        Unit unit = Unit.INSTANCE;
      } 
      l -= toWrite;
      this.writer.data((outFinished && l == 0L), streamId, buffer, toWrite);
    } 
  }
  
  public final void writeSynResetLater$okhttp(int streamId, @NotNull ErrorCode errorCode) {
    Intrinsics.checkNotNullParameter(errorCode, "errorCode");
    TaskQueue taskQueue = this.writerQueue;
    String name$iv = this.connectionName + '[' + streamId + "] writeSynReset";
    long delayNanos$iv = 0L;
    boolean cancelable$iv = true;
    int $i$f$execute = 0;
    taskQueue.schedule(new Http2Connection$writeSynResetLater$$inlined$execute$default$1(name$iv, cancelable$iv, this, streamId, errorCode), 
        
        delayNanos$iv);
  }
  
  public final void writeSynReset$okhttp(int streamId, @NotNull ErrorCode statusCode) throws IOException {
    Intrinsics.checkNotNullParameter(statusCode, "statusCode");
    this.writer.rstStream(streamId, statusCode);
  }
  
  public final void writeWindowUpdateLater$okhttp(int streamId, long unacknowledgedBytesRead) {
    TaskQueue taskQueue = this.writerQueue;
    String name$iv = this.connectionName + '[' + streamId + "] windowUpdate";
    long delayNanos$iv = 0L;
    boolean cancelable$iv = true;
    int $i$f$execute = 0;
    taskQueue.schedule(new Http2Connection$writeWindowUpdateLater$$inlined$execute$default$1(name$iv, cancelable$iv, this, streamId, unacknowledgedBytesRead), 
        
        delayNanos$iv);
  }
  
  public final void writePing(boolean reply, int payload1, int payload2) {
    try {
      this.writer.ping(reply, payload1, payload2);
    } catch (IOException e) {
      failConnection(e);
    } 
  }
  
  public final void writePingAndAwaitPong() throws InterruptedException {
    writePing();
    awaitPong();
  }
  
  public final void writePing() throws InterruptedException {
    synchronized (this) {
      int $i$a$-synchronized-Http2Connection$writePing$1 = 0;
      long l2 = this.awaitPingsSent;
      this.awaitPingsSent = l2 + 1L;
      long l1 = l2;
    } 
    writePing(false, 3, 1330343787);
  }
  
  public final synchronized void awaitPong() throws InterruptedException {
    while (this.awaitPongsReceived < this.awaitPingsSent) {
      Object $this$wait$iv = this;
      int $i$f$wait = 0;
      Intrinsics.checkNotNull($this$wait$iv, "null cannot be cast to non-null type java.lang.Object");
      $this$wait$iv.wait();
    } 
  }
  
  public final void flush() throws IOException {
    this.writer.flush();
  }
  
  public final void shutdown(@NotNull ErrorCode statusCode) throws IOException {
    Intrinsics.checkNotNullParameter(statusCode, "statusCode");
    synchronized (this.writer) {
      int $i$a$-synchronized-Http2Connection$shutdown$1 = 0;
      Ref.IntRef lastGoodStreamId = new Ref.IntRef();
      synchronized (this) {
        int $i$a$-synchronized-Http2Connection$shutdown$1$1 = 0;
        if (this.isShutdown)
          return; 
        this.isShutdown = true;
        lastGoodStreamId.element = this.lastGoodStreamId;
        Unit unit1 = Unit.INSTANCE;
      } 
      this.writer.goAway(lastGoodStreamId.element, statusCode, Util.EMPTY_BYTE_ARRAY);
      Unit unit = Unit.INSTANCE;
    } 
  }
  
  public void close() {
    close$okhttp(ErrorCode.NO_ERROR, ErrorCode.CANCEL, null);
  }
  
  public final void close$okhttp(@NotNull ErrorCode connectionCode, @NotNull ErrorCode streamCode, @Nullable IOException cause) {
    Http2Stream[] arrayOfHttp2Stream;
    byte b;
    int i;
    Intrinsics.checkNotNullParameter(connectionCode, "connectionCode");
    Intrinsics.checkNotNullParameter(streamCode, "streamCode");
    Object $this$assertThreadDoesntHoldLock$iv = this;
    int $i$f$assertThreadDoesntHoldLock = 0;
    if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); 
    int $i$f$ignoreIoExceptions = 0;
    try {
      int $i$a$-ignoreIoExceptions-Http2Connection$close$1 = 0;
      shutdown(connectionCode);
    } catch (IOException iOException) {}
    Object streamsToClose = null;
    synchronized (this) {
      int $i$a$-synchronized-Http2Connection$close$2 = 0;
      if (!this.streams.isEmpty()) {
        Collection<Http2Stream> $this$toTypedArray$iv = this.streams.values();
        b = 0;
        Collection<Http2Stream> thisCollection$iv = $this$toTypedArray$iv;
        streamsToClose = thisCollection$iv.toArray(new Http2Stream[0]);
        this.streams.clear();
      } 
      Unit unit = Unit.INSTANCE;
    } 
    if ((Http2Stream[])streamsToClose != null) {
      arrayOfHttp2Stream = (Http2Stream[])streamsToClose;
      int $i$f$forEach = 0;
      b = 0;
      i = arrayOfHttp2Stream.length;
    } else {
      (Http2Stream[])streamsToClose;
      int j = 0;
    } 
    if (b < i) {
      Object element$iv = arrayOfHttp2Stream[b], object1 = element$iv;
      int $i$a$-forEach-Http2Connection$close$3 = 0;
      int j = 0;
    } 
  }
  
  private final void failConnection(IOException e) {
    close$okhttp(ErrorCode.PROTOCOL_ERROR, ErrorCode.PROTOCOL_ERROR, e);
  }
  
  @JvmOverloads
  public final void start(boolean sendConnectionPreface, @NotNull TaskRunner taskRunner) throws IOException {
    Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
    if (sendConnectionPreface) {
      this.writer.connectionPreface();
      this.writer.settings(this.okHttpSettings);
      int windowSize = this.okHttpSettings.getInitialWindowSize();
      if (windowSize != 65535)
        this.writer.windowUpdate(0, (windowSize - 65535)); 
    } 
    TaskQueue taskQueue = taskRunner.newQueue();
    String str = this.connectionName;
    Function0 block$iv = this.readerRunnable;
    long delayNanos$iv = 0L;
    boolean cancelable$iv = true;
    int $i$f$execute = 0;
    taskQueue.schedule((Task)new Object(str, cancelable$iv, block$iv), 
        
        delayNanos$iv);
  }
  
  public final void setSettings(@NotNull Settings settings) throws IOException {
    Intrinsics.checkNotNullParameter(settings, "settings");
    synchronized (this.writer) {
      int $i$a$-synchronized-Http2Connection$setSettings$1 = 0;
      synchronized (this) {
        int $i$a$-synchronized-Http2Connection$setSettings$1$1 = 0;
        if (this.isShutdown)
          throw new ConnectionShutdownException(); 
        this.okHttpSettings.merge(settings);
        Unit unit1 = Unit.INSTANCE;
      } 
      this.writer.settings(settings);
      Unit unit = Unit.INSTANCE;
    } 
  }
  
  public final synchronized boolean isHealthy(long nowNs) {
    if (this.isShutdown)
      return false; 
    if (this.degradedPongsReceived < this.degradedPingsSent && nowNs >= this.degradedPongDeadlineNs)
      return false; 
    return true;
  }
  
  public final void sendDegradedPingLater$okhttp() {
    synchronized (this) {
      int $i$a$-synchronized-Http2Connection$sendDegradedPingLater$1 = 0;
      if (this.degradedPongsReceived < this.degradedPingsSent)
        return; 
      long l = this.degradedPingsSent;
      this.degradedPingsSent = l + 1L;
      this.degradedPongDeadlineNs = System.nanoTime() + 1000000000L;
      Unit unit = Unit.INSTANCE;
    } 
    TaskQueue taskQueue = this.writerQueue;
    String name$iv = this.connectionName + " ping";
    long delayNanos$iv = 0L;
    boolean cancelable$iv = true;
    int $i$f$execute = 0;
    taskQueue.schedule(new Http2Connection$sendDegradedPingLater$$inlined$execute$default$1(name$iv, cancelable$iv, this), 
        
        delayNanos$iv);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000P\n\002\030\002\n\002\020\000\n\002\020\013\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\016\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b/\030\0002\0020\001B\027\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007J\r\020\t\032\0020\b¢\006\004\b\t\020\nJ\025\020\f\032\0020\0002\006\020\f\032\0020\013¢\006\004\b\f\020\rJ\025\020\017\032\0020\0002\006\020\017\032\0020\016¢\006\004\b\017\020\020J\025\020\022\032\0020\0002\006\020\022\032\0020\021¢\006\004\b\022\020\023J5\020\025\032\0020\0002\006\020\025\032\0020\0242\b\b\002\020\027\032\0020\0262\b\b\002\020\031\032\0020\0302\b\b\002\020\033\032\0020\032H\007¢\006\004\b\025\020\034R\"\020\003\032\0020\0028\000@\000X\016¢\006\022\n\004\b\003\020\035\032\004\b\036\020\037\"\004\b \020!R\"\020\"\032\0020\0268\000@\000X.¢\006\022\n\004\b\"\020#\032\004\b$\020%\"\004\b&\020'R\"\020\f\032\0020\0138\000@\000X\016¢\006\022\n\004\b\f\020(\032\004\b)\020*\"\004\b+\020,R\"\020\017\032\0020\0168\000@\000X\016¢\006\022\n\004\b\017\020-\032\004\b.\020/\"\004\b0\0201R\"\020\022\032\0020\0218\000@\000X\016¢\006\022\n\004\b\022\0202\032\004\b3\0204\"\004\b5\0206R\"\020\033\032\0020\0328\000@\000X.¢\006\022\n\004\b\033\0207\032\004\b8\0209\"\004\b:\020;R\"\020\025\032\0020\0248\000@\000X.¢\006\022\n\004\b\025\020<\032\004\b=\020>\"\004\b?\020@R\"\020\031\032\0020\0308\000@\000X.¢\006\022\n\004\b\031\020A\032\004\bB\020C\"\004\bD\020ER\032\020\005\032\0020\0048\000X\004¢\006\f\n\004\b\005\020F\032\004\bG\020H¨\006I"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$Builder;", "", "", "client", "Lokhttp3/internal/concurrent/TaskRunner;", "taskRunner", "<init>", "(ZLokhttp3/internal/concurrent/TaskRunner;)V", "Lokhttp3/internal/http2/Http2Connection;", "build", "()Lokhttp3/internal/http2/Http2Connection;", "Lokhttp3/internal/http2/Http2Connection$Listener;", "listener", "(Lokhttp3/internal/http2/Http2Connection$Listener;)Lokhttp3/internal/http2/Http2Connection$Builder;", "", "pingIntervalMillis", "(I)Lokhttp3/internal/http2/Http2Connection$Builder;", "Lokhttp3/internal/http2/PushObserver;", "pushObserver", "(Lokhttp3/internal/http2/PushObserver;)Lokhttp3/internal/http2/Http2Connection$Builder;", "Ljava/net/Socket;", "socket", "", "peerName", "Lokio/BufferedSource;", "source", "Lokio/BufferedSink;", "sink", "(Ljava/net/Socket;Ljava/lang/String;Lokio/BufferedSource;Lokio/BufferedSink;)Lokhttp3/internal/http2/Http2Connection$Builder;", "Z", "getClient$okhttp", "()Z", "setClient$okhttp", "(Z)V", "connectionName", "Ljava/lang/String;", "getConnectionName$okhttp", "()Ljava/lang/String;", "setConnectionName$okhttp", "(Ljava/lang/String;)V", "Lokhttp3/internal/http2/Http2Connection$Listener;", "getListener$okhttp", "()Lokhttp3/internal/http2/Http2Connection$Listener;", "setListener$okhttp", "(Lokhttp3/internal/http2/Http2Connection$Listener;)V", "I", "getPingIntervalMillis$okhttp", "()I", "setPingIntervalMillis$okhttp", "(I)V", "Lokhttp3/internal/http2/PushObserver;", "getPushObserver$okhttp", "()Lokhttp3/internal/http2/PushObserver;", "setPushObserver$okhttp", "(Lokhttp3/internal/http2/PushObserver;)V", "Lokio/BufferedSink;", "getSink$okhttp", "()Lokio/BufferedSink;", "setSink$okhttp", "(Lokio/BufferedSink;)V", "Ljava/net/Socket;", "getSocket$okhttp", "()Ljava/net/Socket;", "setSocket$okhttp", "(Ljava/net/Socket;)V", "Lokio/BufferedSource;", "getSource$okhttp", "()Lokio/BufferedSource;", "setSource$okhttp", "(Lokio/BufferedSource;)V", "Lokhttp3/internal/concurrent/TaskRunner;", "getTaskRunner$okhttp", "()Lokhttp3/internal/concurrent/TaskRunner;", "okhttp"})
  public static final class Builder {
    private boolean client;
    
    @NotNull
    private final TaskRunner taskRunner;
    
    public Socket socket;
    
    public String connectionName;
    
    public BufferedSource source;
    
    public BufferedSink sink;
    
    @NotNull
    private Http2Connection.Listener listener;
    
    @NotNull
    private PushObserver pushObserver;
    
    private int pingIntervalMillis;
    
    public Builder(boolean client, @NotNull TaskRunner taskRunner) {
      this.client = client;
      this.taskRunner = taskRunner;
      this.listener = Http2Connection.Listener.REFUSE_INCOMING_STREAMS;
      this.pushObserver = PushObserver.CANCEL;
    }
    
    public final boolean getClient$okhttp() {
      return this.client;
    }
    
    public final void setClient$okhttp(boolean <set-?>) {
      this.client = <set-?>;
    }
    
    @NotNull
    public final TaskRunner getTaskRunner$okhttp() {
      return this.taskRunner;
    }
    
    @NotNull
    public final Socket getSocket$okhttp() {
      if (this.socket != null)
        return this.socket; 
      Intrinsics.throwUninitializedPropertyAccessException("socket");
      return null;
    }
    
    public final void setSocket$okhttp(@NotNull Socket <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.socket = <set-?>;
    }
    
    @NotNull
    public final String getConnectionName$okhttp() {
      if (this.connectionName != null)
        return this.connectionName; 
      Intrinsics.throwUninitializedPropertyAccessException("connectionName");
      return null;
    }
    
    public final void setConnectionName$okhttp(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.connectionName = <set-?>;
    }
    
    @NotNull
    public final BufferedSource getSource$okhttp() {
      if (this.source != null)
        return this.source; 
      Intrinsics.throwUninitializedPropertyAccessException("source");
      return null;
    }
    
    public final void setSource$okhttp(@NotNull BufferedSource <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.source = <set-?>;
    }
    
    @NotNull
    public final BufferedSink getSink$okhttp() {
      if (this.sink != null)
        return this.sink; 
      Intrinsics.throwUninitializedPropertyAccessException("sink");
      return null;
    }
    
    public final void setSink$okhttp(@NotNull BufferedSink <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.sink = <set-?>;
    }
    
    @NotNull
    public final Http2Connection.Listener getListener$okhttp() {
      return this.listener;
    }
    
    public final void setListener$okhttp(@NotNull Http2Connection.Listener <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.listener = <set-?>;
    }
    
    @NotNull
    public final PushObserver getPushObserver$okhttp() {
      return this.pushObserver;
    }
    
    public final void setPushObserver$okhttp(@NotNull PushObserver <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.pushObserver = <set-?>;
    }
    
    public final int getPingIntervalMillis$okhttp() {
      return this.pingIntervalMillis;
    }
    
    public final void setPingIntervalMillis$okhttp(int <set-?>) {
      this.pingIntervalMillis = <set-?>;
    }
    
    @JvmOverloads
    @NotNull
    public final Builder socket(@NotNull Socket socket, @NotNull String peerName, @NotNull BufferedSource source, @NotNull BufferedSink sink) throws IOException {
      Intrinsics.checkNotNullParameter(socket, "socket");
      Intrinsics.checkNotNullParameter(peerName, "peerName");
      Intrinsics.checkNotNullParameter(source, "source");
      Intrinsics.checkNotNullParameter(sink, "sink");
      Builder builder1 = this, $this$socket_u24lambda_u240 = builder1;
      int $i$a$-apply-Http2Connection$Builder$socket$1 = 0;
      $this$socket_u24lambda_u240.setSocket$okhttp(socket);
      $this$socket_u24lambda_u240.setConnectionName$okhttp($this$socket_u24lambda_u240.client ? (Util.okHttpName + ' ' + peerName) : ("MockWebServer " + peerName));
      $this$socket_u24lambda_u240.setSource$okhttp(source);
      $this$socket_u24lambda_u240.setSink$okhttp(sink);
      return builder1;
    }
    
    @NotNull
    public final Builder listener(@NotNull Http2Connection.Listener listener) {
      Intrinsics.checkNotNullParameter(listener, "listener");
      Builder builder1 = this, $this$listener_u24lambda_u241 = builder1;
      int $i$a$-apply-Http2Connection$Builder$listener$1 = 0;
      $this$listener_u24lambda_u241.listener = listener;
      return builder1;
    }
    
    @NotNull
    public final Builder pushObserver(@NotNull PushObserver pushObserver) {
      Intrinsics.checkNotNullParameter(pushObserver, "pushObserver");
      Builder builder1 = this, $this$pushObserver_u24lambda_u242 = builder1;
      int $i$a$-apply-Http2Connection$Builder$pushObserver$1 = 0;
      $this$pushObserver_u24lambda_u242.pushObserver = pushObserver;
      return builder1;
    }
    
    @NotNull
    public final Builder pingIntervalMillis(int pingIntervalMillis) {
      Builder builder1 = this, $this$pingIntervalMillis_u24lambda_u243 = builder1;
      int $i$a$-apply-Http2Connection$Builder$pingIntervalMillis$1 = 0;
      $this$pingIntervalMillis_u24lambda_u243.pingIntervalMillis = pingIntervalMillis;
      return builder1;
    }
    
    @NotNull
    public final Http2Connection build() {
      return new Http2Connection(this);
    }
    
    @JvmOverloads
    @NotNull
    public final Builder socket(@NotNull Socket socket, @NotNull String peerName, @NotNull BufferedSource source) throws IOException {
      Intrinsics.checkNotNullParameter(socket, "socket");
      Intrinsics.checkNotNullParameter(peerName, "peerName");
      Intrinsics.checkNotNullParameter(source, "source");
      return socket$default(this, socket, peerName, source, null, 8, null);
    }
    
    @JvmOverloads
    @NotNull
    public final Builder socket(@NotNull Socket socket, @NotNull String peerName) throws IOException {
      Intrinsics.checkNotNullParameter(socket, "socket");
      Intrinsics.checkNotNullParameter(peerName, "peerName");
      return socket$default(this, socket, peerName, null, null, 12, null);
    }
    
    @JvmOverloads
    @NotNull
    public final Builder socket(@NotNull Socket socket) throws IOException {
      Intrinsics.checkNotNullParameter(socket, "socket");
      return socket$default(this, socket, null, null, null, 14, null);
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000^\n\002\030\002\n\002\030\002\n\002\030\002\n\002\020\002\n\002\030\002\n\002\b\005\n\002\020\b\n\000\n\002\020\016\n\000\n\002\030\002\n\002\b\003\n\002\020\t\n\002\b\003\n\002\020\013\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\020 \n\002\030\002\n\002\b\033\b\004\030\0002\0020\0012\b\022\004\022\0020\0030\002B\021\b\000\022\006\020\005\032\0020\004¢\006\004\b\006\020\007J\017\020\b\032\0020\003H\026¢\006\004\b\b\020\tJ?\020\024\032\0020\0032\006\020\013\032\0020\n2\006\020\r\032\0020\f2\006\020\017\032\0020\0162\006\020\020\032\0020\f2\006\020\021\032\0020\n2\006\020\023\032\0020\022H\026¢\006\004\b\024\020\025J\035\020\032\032\0020\0032\006\020\027\032\0020\0262\006\020\031\032\0020\030¢\006\004\b\032\020\033J/\020 \032\0020\0032\006\020\034\032\0020\0262\006\020\013\032\0020\n2\006\020\036\032\0020\0352\006\020\037\032\0020\nH\026¢\006\004\b \020!J'\020&\032\0020\0032\006\020\"\032\0020\n2\006\020$\032\0020#2\006\020%\032\0020\016H\026¢\006\004\b&\020'J5\020,\032\0020\0032\006\020\034\032\0020\0262\006\020\013\032\0020\n2\006\020(\032\0020\n2\f\020+\032\b\022\004\022\0020*0)H\026¢\006\004\b,\020-J\020\020.\032\0020\003H\002¢\006\004\b.\020\tJ'\0202\032\0020\0032\006\020/\032\0020\0262\006\0200\032\0020\n2\006\0201\032\0020\nH\026¢\006\004\b2\0203J/\0207\032\0020\0032\006\020\013\032\0020\n2\006\0204\032\0020\n2\006\0205\032\0020\n2\006\0206\032\0020\026H\026¢\006\004\b7\0208J-\020;\032\0020\0032\006\020\013\032\0020\n2\006\0209\032\0020\n2\f\020:\032\b\022\004\022\0020*0)H\026¢\006\004\b;\020<J\037\020=\032\0020\0032\006\020\013\032\0020\n2\006\020$\032\0020#H\026¢\006\004\b=\020>J\037\020\031\032\0020\0032\006\020\027\032\0020\0262\006\020\031\032\0020\030H\026¢\006\004\b\031\020\033J\037\020@\032\0020\0032\006\020\013\032\0020\n2\006\020?\032\0020\022H\026¢\006\004\b@\020AR\032\020\005\032\0020\0048\000X\004¢\006\f\n\004\b\005\020B\032\004\bC\020D¨\006E"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "Lokhttp3/internal/http2/Http2Reader$Handler;", "Lkotlin/Function0;", "", "Lokhttp3/internal/http2/Http2Reader;", "reader", "<init>", "(Lokhttp3/internal/http2/Http2Connection;Lokhttp3/internal/http2/Http2Reader;)V", "ackSettings", "()V", "", "streamId", "", "origin", "Lokio/ByteString;", "protocol", "host", "port", "", "maxAge", "alternateService", "(ILjava/lang/String;Lokio/ByteString;Ljava/lang/String;IJ)V", "", "clearPrevious", "Lokhttp3/internal/http2/Settings;", "settings", "applyAndAckSettings", "(ZLokhttp3/internal/http2/Settings;)V", "inFinished", "Lokio/BufferedSource;", "source", "length", "data", "(ZILokio/BufferedSource;I)V", "lastGoodStreamId", "Lokhttp3/internal/http2/ErrorCode;", "errorCode", "debugData", "goAway", "(ILokhttp3/internal/http2/ErrorCode;Lokio/ByteString;)V", "associatedStreamId", "", "Lokhttp3/internal/http2/Header;", "headerBlock", "headers", "(ZIILjava/util/List;)V", "invoke", "ack", "payload1", "payload2", "ping", "(ZII)V", "streamDependency", "weight", "exclusive", "priority", "(IIIZ)V", "promisedStreamId", "requestHeaders", "pushPromise", "(IILjava/util/List;)V", "rstStream", "(ILokhttp3/internal/http2/ErrorCode;)V", "windowSizeIncrement", "windowUpdate", "(IJ)V", "Lokhttp3/internal/http2/Http2Reader;", "getReader$okhttp", "()Lokhttp3/internal/http2/Http2Reader;", "okhttp"})
  @SourceDebugExtension({"SMAP\nHttp2Connection.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http2Connection.kt\nokhttp3/internal/http2/Http2Connection$ReaderRunnable\n+ 2 TaskQueue.kt\nokhttp3/internal/concurrent/TaskQueue\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 Util.kt\nokhttp3/internal/Util\n*L\n1#1,1006:1\n90#2,13:1007\n90#2,13:1020\n90#2,13:1035\n90#2,13:1049\n37#3,2:1033\n37#3,2:1062\n563#4:1048\n563#4:1064\n*S KotlinDebug\n*F\n+ 1 Http2Connection.kt\nokhttp3/internal/http2/Http2Connection$ReaderRunnable\n*L\n687#1:1007,13\n715#1:1020,13\n758#1:1035,13\n806#1:1049,13\n753#1:1033,2\n824#1:1062,2\n797#1:1048\n841#1:1064\n*E\n"})
  public final class ReaderRunnable implements Http2Reader.Handler, Function0<Unit> {
    @NotNull
    private final Http2Reader reader;
    
    public ReaderRunnable(Http2Reader reader) {
      this.reader = reader;
    }
    
    @NotNull
    public final Http2Reader getReader$okhttp() {
      return this.reader;
    }
    
    public void invoke() {
      ErrorCode connectionErrorCode = ErrorCode.INTERNAL_ERROR;
      ErrorCode streamErrorCode = ErrorCode.INTERNAL_ERROR;
      IOException errorException = null;
      try {
        this.reader.readConnectionPreface(this);
        do {
        
        } while (this.reader.nextFrame(false, this));
        connectionErrorCode = ErrorCode.NO_ERROR;
        streamErrorCode = ErrorCode.CANCEL;
      } catch (IOException e) {
        errorException = e;
        connectionErrorCode = ErrorCode.PROTOCOL_ERROR;
        streamErrorCode = ErrorCode.PROTOCOL_ERROR;
      } finally {
        Http2Connection.this.close$okhttp(connectionErrorCode, streamErrorCode, errorException);
        Util.closeQuietly(this.reader);
      } 
    }
    
    public void data(boolean inFinished, int streamId, @NotNull BufferedSource source, int length) throws IOException {
      Intrinsics.checkNotNullParameter(source, "source");
      if (Http2Connection.this.pushedStream$okhttp(streamId)) {
        Http2Connection.this.pushDataLater$okhttp(streamId, source, length, inFinished);
        return;
      } 
      Http2Stream dataStream = Http2Connection.this.getStream(streamId);
      if (dataStream == null) {
        Http2Connection.this.writeSynResetLater$okhttp(streamId, ErrorCode.PROTOCOL_ERROR);
        Http2Connection.this.updateConnectionFlowControl$okhttp(length);
        source.skip(length);
        return;
      } 
      dataStream.receiveData(source, length);
      if (inFinished)
        dataStream.receiveHeaders(Util.EMPTY_HEADERS, true); 
    }
    
    public void headers(boolean inFinished, int streamId, int associatedStreamId, @NotNull List<Header> headerBlock) {
      Intrinsics.checkNotNullParameter(headerBlock, "headerBlock");
      if (Http2Connection.this.pushedStream$okhttp(streamId)) {
        Http2Connection.this.pushHeadersLater$okhttp(streamId, headerBlock, inFinished);
        return;
      } 
      Object stream = null;
      Http2Connection http2Connection1 = Http2Connection.this, http2Connection2 = Http2Connection.this;
      synchronized (http2Connection1) {
        int $i$a$-synchronized-Http2Connection$ReaderRunnable$headers$1 = 0;
        stream = http2Connection2.getStream(streamId);
        if (stream == null) {
          if (http2Connection2.isShutdown)
            return; 
          if (streamId <= http2Connection2.getLastGoodStreamId$okhttp())
            return; 
          if (streamId % 2 == http2Connection2.getNextStreamId$okhttp() % 2)
            return; 
          Headers headers = Util.toHeaders(headerBlock);
          Http2Stream newStream = new Http2Stream(streamId, http2Connection2, false, inFinished, headers);
          http2Connection2.setLastGoodStreamId$okhttp(streamId);
          Integer integer = Integer.valueOf(streamId);
          http2Connection2.getStreams$okhttp().put(integer, newStream);
          TaskQueue taskQueue = http2Connection2.taskRunner.newQueue();
          String name$iv = http2Connection2.getConnectionName$okhttp() + '[' + streamId + "] onStream";
          long delayNanos$iv = 0L;
          boolean cancelable$iv = true;
          int $i$f$execute = 0;
          taskQueue.schedule(new Http2Connection$ReaderRunnable$headers$lambda$2$$inlined$execute$default$1(name$iv, cancelable$iv, http2Connection2, newStream), delayNanos$iv);
          return;
        } 
        Unit unit = Unit.INSTANCE;
      } 
      stream.receiveHeaders(Util.toHeaders(headerBlock), inFinished);
    }
    
    public void rstStream(int streamId, @NotNull ErrorCode errorCode) {
      Intrinsics.checkNotNullParameter(errorCode, "errorCode");
      if (Http2Connection.this.pushedStream$okhttp(streamId)) {
        Http2Connection.this.pushResetLater$okhttp(streamId, errorCode);
        return;
      } 
      Http2Stream rstStream = Http2Connection.this.removeStream$okhttp(streamId);
      if (rstStream != null) {
        rstStream.receiveRstStream(errorCode);
      } else {
      
      } 
    }
    
    public void settings(boolean clearPrevious, @NotNull Settings settings) {
      Intrinsics.checkNotNullParameter(settings, "settings");
      TaskQueue taskQueue = Http2Connection.this.writerQueue;
      String name$iv = Http2Connection.this.getConnectionName$okhttp() + " applyAndAckSettings";
      long delayNanos$iv = 0L;
      boolean cancelable$iv = true;
      int $i$f$execute = 0;
      taskQueue.schedule(new Http2Connection$ReaderRunnable$settings$$inlined$execute$default$1(name$iv, cancelable$iv, this, clearPrevious, settings), delayNanos$iv);
    }
    
    public final void applyAndAckSettings(boolean clearPrevious, @NotNull Settings settings) {
      Intrinsics.checkNotNullParameter(settings, "settings");
      long delta = 0L;
      Object streamsToNotify = null;
      Ref.ObjectRef newPeerSettings = new Ref.ObjectRef();
      Http2Writer http2Writer = Http2Connection.this.getWriter();
      Http2Connection http2Connection = Http2Connection.this;
      synchronized (http2Writer) {
        int $i$a$-synchronized-Http2Connection$ReaderRunnable$applyAndAckSettings$1 = 0;
        synchronized (http2Connection) {
          int $i$a$-synchronized-Http2Connection$ReaderRunnable$applyAndAckSettings$1$1 = 0;
          Settings previousPeerSettings = http2Connection.getPeerSettings();
          Settings settings1 = new Settings(), settings2 = settings1;
          Ref.ObjectRef objectRef = newPeerSettings;
          int $i$a$-apply-Http2Connection$ReaderRunnable$applyAndAckSettings$1$1$1 = 0;
          settings2.merge(previousPeerSettings);
          settings2.merge(settings);
          objectRef.element = clearPrevious ? settings : settings1;
          long peerInitialWindowSize = ((Settings)newPeerSettings.element).getInitialWindowSize();
          delta = peerInitialWindowSize - previousPeerSettings.getInitialWindowSize();
          Collection<Http2Stream> $this$toTypedArray$iv = http2Connection.getStreams$okhttp().values();
          int $i$f$toTypedArray = 0;
          Collection<Http2Stream> thisCollection$iv = $this$toTypedArray$iv;
          streamsToNotify = (delta == 0L || http2Connection.getStreams$okhttp().isEmpty()) ? null : thisCollection$iv.<Http2Stream>toArray(new Http2Stream[0]);
          http2Connection.setPeerSettings((Settings)newPeerSettings.element);
          TaskQueue taskQueue = http2Connection.settingsListenerQueue;
          String name$iv = http2Connection.getConnectionName$okhttp() + " onSettings";
          long delayNanos$iv = 0L;
          boolean cancelable$iv = true;
          int $i$f$execute = 0;
          taskQueue.schedule(new Http2Connection$ReaderRunnable$applyAndAckSettings$lambda$7$lambda$6$$inlined$execute$default$1(name$iv, cancelable$iv, http2Connection, newPeerSettings), delayNanos$iv);
          Unit unit1 = Unit.INSTANCE;
        } 
        try {
          http2Connection.getWriter().applyAndAckSettings((Settings)newPeerSettings.element);
        } catch (IOException e) {
          http2Connection.failConnection(e);
        } 
        Unit unit = Unit.INSTANCE;
      } 
      if (streamsToNotify != null) {
        Object object;
        byte b;
        int i;
        for (object = streamsToNotify, b = 0, i = object.length; b < i; ) {
          Object object1 = object[b];
          synchronized (object1) {
            int $i$a$-synchronized-Http2Connection$ReaderRunnable$applyAndAckSettings$2 = 0;
            object1.addBytesToWriteWindow(delta);
            Unit unit = Unit.INSTANCE;
          } 
          b++;
        } 
      } 
    }
    
    public void ackSettings() {}
    
    public void ping(boolean ack, int payload1, int payload2) {
      if (ack) {
        Http2Connection http2Connection1 = Http2Connection.this, http2Connection2 = Http2Connection.this;
        synchronized (http2Connection1) {
          long l;
          Object $this$notifyAll$iv;
          int $i$f$notifyAll, $i$a$-synchronized-Http2Connection$ReaderRunnable$ping$1 = 0;
          switch (payload1) {
            case 1:
              l = http2Connection2.intervalPongsReceived;
              http2Connection2.intervalPongsReceived = l + 1L;
            case 2:
              l = http2Connection2.degradedPongsReceived;
              http2Connection2.degradedPongsReceived = l + 1L;
            case 3:
              l = http2Connection2.awaitPongsReceived;
              http2Connection2.awaitPongsReceived = l + 1L;
              $this$notifyAll$iv = http2Connection2;
              $i$f$notifyAll = 0;
              Intrinsics.checkNotNull($this$notifyAll$iv, "null cannot be cast to non-null type java.lang.Object");
              $this$notifyAll$iv.notifyAll();
            default:
              break;
          } 
          Unit unit = Unit.INSTANCE;
        } 
      } else {
        TaskQueue taskQueue = Http2Connection.this.writerQueue;
        String str = Http2Connection.this.getConnectionName$okhttp() + " ping";
        Http2Connection http2Connection = Http2Connection.this;
        long delayNanos$iv = 0L;
        boolean cancelable$iv = true;
        int $i$f$execute = 0;
        taskQueue.schedule(new Http2Connection$ReaderRunnable$ping$$inlined$execute$default$1(str, cancelable$iv, http2Connection, payload1, payload2), delayNanos$iv);
      } 
    }
    
    public void goAway(int lastGoodStreamId, @NotNull ErrorCode errorCode, @NotNull ByteString debugData) {
      Intrinsics.checkNotNullParameter(errorCode, "errorCode");
      Intrinsics.checkNotNullParameter(debugData, "debugData");
      if (debugData.size() > 0);
      Object streamsCopy = null;
      Http2Connection http2Connection1 = Http2Connection.this, http2Connection2 = Http2Connection.this;
      synchronized (http2Connection1) {
        int $i$a$-synchronized-Http2Connection$ReaderRunnable$goAway$1 = 0;
        Collection<Http2Stream> $this$toTypedArray$iv = http2Connection2.getStreams$okhttp().values();
        int $i$f$toTypedArray = 0;
        Collection<Http2Stream> thisCollection$iv = $this$toTypedArray$iv;
        streamsCopy = thisCollection$iv.toArray(new Http2Stream[0]);
        http2Connection2.isShutdown = true;
        Unit unit = Unit.INSTANCE;
      } 
      Http2Stream[] arrayOfHttp2Stream;
      byte b;
      int i;
      for (arrayOfHttp2Stream = (Http2Stream[])streamsCopy, b = 0, i = arrayOfHttp2Stream.length; b < i; ) {
        Http2Stream http2Stream = arrayOfHttp2Stream[b];
        if (http2Stream.getId() > lastGoodStreamId && http2Stream.isLocallyInitiated()) {
          http2Stream.receiveRstStream(ErrorCode.REFUSED_STREAM);
          Http2Connection.this.removeStream$okhttp(http2Stream.getId());
        } 
        b++;
      } 
    }
    
    public void windowUpdate(int streamId, long windowSizeIncrement) {
      if (streamId == 0) {
        Http2Connection http2Connection1 = Http2Connection.this, http2Connection2 = Http2Connection.this;
        synchronized (http2Connection1) {
          int $i$a$-synchronized-Http2Connection$ReaderRunnable$windowUpdate$1 = 0;
          http2Connection2.writeBytesMaximum = http2Connection2.getWriteBytesMaximum() + windowSizeIncrement;
          Object $this$notifyAll$iv = http2Connection2;
          int $i$f$notifyAll = 0;
          Intrinsics.checkNotNull($this$notifyAll$iv, "null cannot be cast to non-null type java.lang.Object");
          $this$notifyAll$iv.notifyAll();
          Unit unit = Unit.INSTANCE;
        } 
      } else {
        Http2Stream stream = Http2Connection.this.getStream(streamId);
        if (stream != null)
          synchronized (stream) {
            int $i$a$-synchronized-Http2Connection$ReaderRunnable$windowUpdate$2 = 0;
            stream.addBytesToWriteWindow(windowSizeIncrement);
            Unit unit = Unit.INSTANCE;
          }  
      } 
    }
    
    public void priority(int streamId, int streamDependency, int weight, boolean exclusive) {}
    
    public void pushPromise(int streamId, int promisedStreamId, @NotNull List<Header> requestHeaders) {
      Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
      Http2Connection.this.pushRequestLater$okhttp(promisedStreamId, requestHeaders);
    }
    
    public void alternateService(int streamId, @NotNull String origin, @NotNull ByteString protocol, @NotNull String host, int port, long maxAge) {
      Intrinsics.checkNotNullParameter(origin, "origin");
      Intrinsics.checkNotNullParameter(protocol, "protocol");
      Intrinsics.checkNotNullParameter(host, "host");
    }
  }
  
  public final boolean pushedStream$okhttp(int streamId) {
    return (streamId != 0 && (streamId & 0x1) == 0);
  }
  
  public final void pushRequestLater$okhttp(int streamId, @NotNull List requestHeaders) {
    Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
    synchronized (this) {
      int $i$a$-synchronized-Http2Connection$pushRequestLater$1 = 0;
      if (this.currentPushRequests.contains(Integer.valueOf(streamId))) {
        writeSynResetLater$okhttp(streamId, ErrorCode.PROTOCOL_ERROR);
        return;
      } 
      boolean bool = this.currentPushRequests.add(Integer.valueOf(streamId));
    } 
    TaskQueue taskQueue = this.pushQueue;
    String name$iv = this.connectionName + '[' + streamId + "] onRequest";
    long delayNanos$iv = 0L;
    boolean cancelable$iv = true;
    int $i$f$execute = 0;
    taskQueue.schedule(new Http2Connection$pushRequestLater$$inlined$execute$default$1(name$iv, cancelable$iv, this, streamId, requestHeaders), 
        
        delayNanos$iv);
  }
  
  public final void pushHeadersLater$okhttp(int streamId, @NotNull List requestHeaders, boolean inFinished) {
    Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
    TaskQueue taskQueue = this.pushQueue;
    String name$iv = this.connectionName + '[' + streamId + "] onHeaders";
    long delayNanos$iv = 0L;
    boolean cancelable$iv = true;
    int $i$f$execute = 0;
    taskQueue.schedule(new Http2Connection$pushHeadersLater$$inlined$execute$default$1(name$iv, cancelable$iv, this, streamId, requestHeaders, inFinished), 
        
        delayNanos$iv);
  }
  
  public final void pushDataLater$okhttp(int streamId, @NotNull BufferedSource source, int byteCount, boolean inFinished) throws IOException {
    Intrinsics.checkNotNullParameter(source, "source");
    Buffer buffer = new Buffer();
    source.require(byteCount);
    source.read(buffer, byteCount);
    TaskQueue taskQueue = this.pushQueue;
    String name$iv = this.connectionName + '[' + streamId + "] onData";
    long delayNanos$iv = 0L;
    boolean cancelable$iv = true;
    int $i$f$execute = 0;
    taskQueue.schedule(new Http2Connection$pushDataLater$$inlined$execute$default$1(name$iv, cancelable$iv, this, streamId, buffer, byteCount, inFinished), 
        
        delayNanos$iv);
  }
  
  public final void pushResetLater$okhttp(int streamId, @NotNull ErrorCode errorCode) {
    Intrinsics.checkNotNullParameter(errorCode, "errorCode");
    TaskQueue taskQueue = this.pushQueue;
    String name$iv = this.connectionName + '[' + streamId + "] onReset";
    long delayNanos$iv = 0L;
    boolean cancelable$iv = true;
    int $i$f$execute = 0;
    taskQueue.schedule(new Http2Connection$pushResetLater$$inlined$execute$default$1(name$iv, cancelable$iv, this, streamId, errorCode), 
        
        delayNanos$iv);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000(\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\005\b&\030\000 \0172\0020\001:\001\017B\007¢\006\004\b\002\020\003J\037\020\t\032\0020\b2\006\020\005\032\0020\0042\006\020\007\032\0020\006H\026¢\006\004\b\t\020\nJ\027\020\r\032\0020\b2\006\020\f\032\0020\013H&¢\006\004\b\r\020\016¨\006\020"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$Listener;", "", "<init>", "()V", "Lokhttp3/internal/http2/Http2Connection;", "connection", "Lokhttp3/internal/http2/Settings;", "settings", "", "onSettings", "(Lokhttp3/internal/http2/Http2Connection;Lokhttp3/internal/http2/Settings;)V", "Lokhttp3/internal/http2/Http2Stream;", "stream", "onStream", "(Lokhttp3/internal/http2/Http2Stream;)V", "Companion", "okhttp"})
  public static abstract class Listener {
    public abstract void onStream(@NotNull Http2Stream param1Http2Stream) throws IOException;
    
    public void onSettings(@NotNull Http2Connection connection, @NotNull Settings settings) {
      Intrinsics.checkNotNullParameter(connection, "connection");
      Intrinsics.checkNotNullParameter(settings, "settings");
    }
    
    @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\024\020\005\032\0020\0048\006X\004¢\006\006\n\004\b\005\020\006¨\006\007"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$Listener$Companion;", "", "<init>", "()V", "Lokhttp3/internal/http2/Http2Connection$Listener;", "REFUSE_INCOMING_STREAMS", "Lokhttp3/internal/http2/Http2Connection$Listener;", "okhttp"})
    public static final class Companion {
      private Companion() {}
    }
    
    @NotNull
    public static final Companion Companion = new Companion(null);
    
    @JvmField
    @NotNull
    public static final Listener REFUSE_INCOMING_STREAMS = new Http2Connection$Listener$Companion$REFUSE_INCOMING_STREAMS$1();
    
    @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\027\n\000\n\002\030\002\n\002\030\002\n\000\n\002\020\002\n\002\b\003*\001\000\b\n\030\0002\0020\001J\027\020\005\032\0020\0042\006\020\003\032\0020\002H\026¢\006\004\b\005\020\006¨\006\007"}, d2 = {"okhttp3/internal/http2/Http2Connection.Listener.Companion.REFUSE_INCOMING_STREAMS.1", "Lokhttp3/internal/http2/Http2Connection$Listener;", "Lokhttp3/internal/http2/Http2Stream;", "stream", "", "onStream", "(Lokhttp3/internal/http2/Http2Stream;)V", "okhttp"})
    public static final class Http2Connection$Listener$Companion$REFUSE_INCOMING_STREAMS$1 extends Listener {
      public void onStream(@NotNull Http2Stream stream) throws IOException {
        Intrinsics.checkNotNullParameter(stream, "stream");
        stream.close(ErrorCode.REFUSED_STREAM, null);
      }
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\002\b\002\n\002\030\002\n\002\b\t\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\024\020\005\032\0020\0048\006XT¢\006\006\n\004\b\005\020\006R\027\020\b\032\0020\0078\006¢\006\f\n\004\b\b\020\t\032\004\b\n\020\013R\024\020\f\032\0020\0048\006XT¢\006\006\n\004\b\f\020\006R\024\020\r\032\0020\0048\006XT¢\006\006\n\004\b\r\020\006R\024\020\016\032\0020\0048\006XT¢\006\006\n\004\b\016\020\006R\024\020\017\032\0020\0048\006XT¢\006\006\n\004\b\017\020\006¨\006\020"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$Companion;", "", "<init>", "()V", "", "AWAIT_PING", "I", "Lokhttp3/internal/http2/Settings;", "DEFAULT_SETTINGS", "Lokhttp3/internal/http2/Settings;", "getDEFAULT_SETTINGS", "()Lokhttp3/internal/http2/Settings;", "DEGRADED_PING", "DEGRADED_PONG_TIMEOUT_NS", "INTERVAL_PING", "OKHTTP_CLIENT_WINDOW_SIZE", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @NotNull
    public final Settings getDEFAULT_SETTINGS() {
      return Http2Connection.DEFAULT_SETTINGS;
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  private final boolean client;
  
  @NotNull
  private final Listener listener;
  
  @NotNull
  private final Map<Integer, Http2Stream> streams;
  
  @NotNull
  private final String connectionName;
  
  private int lastGoodStreamId;
  
  private int nextStreamId;
  
  private boolean isShutdown;
  
  @NotNull
  private final TaskRunner taskRunner;
  
  @NotNull
  private final TaskQueue writerQueue;
  
  @NotNull
  private final TaskQueue pushQueue;
  
  @NotNull
  private final TaskQueue settingsListenerQueue;
  
  @NotNull
  private final PushObserver pushObserver;
  
  private long intervalPingsSent;
  
  private long intervalPongsReceived;
  
  private long degradedPingsSent;
  
  private long degradedPongsReceived;
  
  private long awaitPingsSent;
  
  private long awaitPongsReceived;
  
  private long degradedPongDeadlineNs;
  
  @NotNull
  private final Settings okHttpSettings;
  
  @NotNull
  private Settings peerSettings;
  
  private long readBytesTotal;
  
  private long readBytesAcknowledged;
  
  private long writeBytesTotal;
  
  private long writeBytesMaximum;
  
  @NotNull
  private final Socket socket;
  
  @NotNull
  private final Http2Writer writer;
  
  @NotNull
  private final ReaderRunnable readerRunnable;
  
  @NotNull
  private final Set<Integer> currentPushRequests;
  
  public static final int OKHTTP_CLIENT_WINDOW_SIZE = 16777216;
  
  @NotNull
  private static final Settings DEFAULT_SETTINGS;
  
  public static final int INTERVAL_PING = 1;
  
  public static final int DEGRADED_PING = 2;
  
  public static final int AWAIT_PING = 3;
  
  public static final int DEGRADED_PONG_TIMEOUT_NS = 1000000000;
  
  static {
    Settings settings1 = new Settings(), $this$DEFAULT_SETTINGS_u24lambda_u2435 = settings1;
    int $i$a$-apply-Http2Connection$Companion$DEFAULT_SETTINGS$1 = 0;
    $this$DEFAULT_SETTINGS_u24lambda_u2435.set(7, 65535);
    $this$DEFAULT_SETTINGS_u24lambda_u2435.set(5, 16384);
    DEFAULT_SETTINGS = settings1;
  }
  
  @JvmOverloads
  public final void start(boolean sendConnectionPreface) throws IOException {
    start$default(this, sendConnectionPreface, null, 2, null);
  }
  
  @JvmOverloads
  public final void start() throws IOException {
    start$default(this, false, null, 3, null);
  }
}
