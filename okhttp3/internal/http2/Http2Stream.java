package okhttp3.internal.http2;

import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Headers;
import okhttp3.internal.Util;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\001\n\002\030\002\n\002\020\000\n\002\020\b\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\t\n\000\n\002\020\002\n\002\b\007\n\002\030\002\n\000\n\002\030\002\n\002\b\013\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\013\n\002\020 \n\002\030\002\n\002\b\023\n\002\030\002\n\002\b\021\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\017\030\000 u2\0020\001:\004uvwxB3\b\000\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\006\020\007\032\0020\006\022\006\020\b\032\0020\006\022\b\020\n\032\004\030\0010\t¢\006\004\b\013\020\fJ\025\020\020\032\0020\0172\006\020\016\032\0020\r¢\006\004\b\020\020\021J\017\020\024\032\0020\017H\000¢\006\004\b\022\020\023J\017\020\026\032\0020\017H\000¢\006\004\b\025\020\023J\037\020\033\032\0020\0172\006\020\030\032\0020\0272\b\020\032\032\004\030\0010\031¢\006\004\b\033\020\034J!\020\036\032\0020\0062\006\020\035\032\0020\0272\b\020\032\032\004\030\0010\031H\002¢\006\004\b\036\020\037J\025\020 \032\0020\0172\006\020\035\032\0020\027¢\006\004\b \020!J\025\020#\032\0020\0172\006\020\"\032\0020\t¢\006\004\b#\020$J\r\020&\032\0020%¢\006\004\b&\020'J\r\020)\032\0020(¢\006\004\b)\020*J\r\020,\032\0020+¢\006\004\b,\020-J\035\0201\032\0020\0172\006\020/\032\0020.2\006\0200\032\0020\002¢\006\004\b1\0202J\035\0203\032\0020\0172\006\020\n\032\0020\t2\006\020\b\032\0020\006¢\006\004\b3\0204J\025\0205\032\0020\0172\006\020\035\032\0020\027¢\006\004\b5\020!J\r\0206\032\0020\t¢\006\004\b6\0207J\r\020\"\032\0020\t¢\006\004\b\"\0207J\017\0209\032\0020\017H\000¢\006\004\b8\020\023J+\020>\032\0020\0172\f\020<\032\b\022\004\022\0020;0:2\006\020\007\032\0020\0062\006\020=\032\0020\006¢\006\004\b>\020?J\r\020@\032\0020+¢\006\004\b@\020-R\027\020\005\032\0020\0048\006¢\006\f\n\004\b\005\020A\032\004\bB\020CR$\020\035\032\004\030\0010\0278@@\000X\016¢\006\022\n\004\b\035\020D\032\004\bE\020F\"\004\bG\020!R$\020\032\032\004\030\0010\0318\000@\000X\016¢\006\022\n\004\b\032\020H\032\004\bI\020J\"\004\bK\020LR\026\020M\032\0020\0068\002@\002X\016¢\006\006\n\004\bM\020NR\032\020P\032\b\022\004\022\0020\t0O8\002X\004¢\006\006\n\004\bP\020QR\027\020\003\032\0020\0028\006¢\006\f\n\004\b\003\020R\032\004\bS\020TR\021\020U\032\0020\0068F¢\006\006\032\004\bU\020VR\021\020W\032\0020\0068F¢\006\006\032\004\bW\020VR*\020Y\032\0020\r2\006\020X\032\0020\r8\006@@X\016¢\006\022\n\004\bY\020Z\032\004\b[\020\\\"\004\b]\020\021R*\020^\032\0020\r2\006\020X\032\0020\r8\006@@X\016¢\006\022\n\004\b^\020Z\032\004\b_\020\\\"\004\b`\020\021R\036\020,\032\0060aR\0020\0008\000X\004¢\006\f\n\004\b,\020b\032\004\bc\020dR\036\020f\032\0060eR\0020\0008\000X\004¢\006\f\n\004\bf\020g\032\004\bh\020iR\036\020/\032\0060jR\0020\0008\000X\004¢\006\f\n\004\b/\020k\032\004\bl\020mR*\020n\032\0020\r2\006\020X\032\0020\r8\006@@X\016¢\006\022\n\004\bn\020Z\032\004\bo\020\\\"\004\bp\020\021R*\020q\032\0020\r2\006\020X\032\0020\r8\006@@X\016¢\006\022\n\004\bq\020Z\032\004\br\020\\\"\004\bs\020\021R\036\020@\032\0060aR\0020\0008\000X\004¢\006\f\n\004\b@\020b\032\004\bt\020d¨\006y"}, d2 = {"Lokhttp3/internal/http2/Http2Stream;", "", "", "id", "Lokhttp3/internal/http2/Http2Connection;", "connection", "", "outFinished", "inFinished", "Lokhttp3/Headers;", "headers", "<init>", "(ILokhttp3/internal/http2/Http2Connection;ZZLokhttp3/Headers;)V", "", "delta", "", "addBytesToWriteWindow", "(J)V", "cancelStreamIfNecessary$okhttp", "()V", "cancelStreamIfNecessary", "checkOutNotClosed$okhttp", "checkOutNotClosed", "Lokhttp3/internal/http2/ErrorCode;", "rstStatusCode", "Ljava/io/IOException;", "errorException", "close", "(Lokhttp3/internal/http2/ErrorCode;Ljava/io/IOException;)V", "errorCode", "closeInternal", "(Lokhttp3/internal/http2/ErrorCode;Ljava/io/IOException;)Z", "closeLater", "(Lokhttp3/internal/http2/ErrorCode;)V", "trailers", "enqueueTrailers", "(Lokhttp3/Headers;)V", "Lokio/Sink;", "getSink", "()Lokio/Sink;", "Lokio/Source;", "getSource", "()Lokio/Source;", "Lokio/Timeout;", "readTimeout", "()Lokio/Timeout;", "Lokio/BufferedSource;", "source", "length", "receiveData", "(Lokio/BufferedSource;I)V", "receiveHeaders", "(Lokhttp3/Headers;Z)V", "receiveRstStream", "takeHeaders", "()Lokhttp3/Headers;", "waitForIo$okhttp", "waitForIo", "", "Lokhttp3/internal/http2/Header;", "responseHeaders", "flushHeaders", "writeHeaders", "(Ljava/util/List;ZZ)V", "writeTimeout", "Lokhttp3/internal/http2/Http2Connection;", "getConnection", "()Lokhttp3/internal/http2/Http2Connection;", "Lokhttp3/internal/http2/ErrorCode;", "getErrorCode$okhttp", "()Lokhttp3/internal/http2/ErrorCode;", "setErrorCode$okhttp", "Ljava/io/IOException;", "getErrorException$okhttp", "()Ljava/io/IOException;", "setErrorException$okhttp", "(Ljava/io/IOException;)V", "hasResponseHeaders", "Z", "Ljava/util/ArrayDeque;", "headersQueue", "Ljava/util/ArrayDeque;", "I", "getId", "()I", "isLocallyInitiated", "()Z", "isOpen", "<set-?>", "readBytesAcknowledged", "J", "getReadBytesAcknowledged", "()J", "setReadBytesAcknowledged$okhttp", "readBytesTotal", "getReadBytesTotal", "setReadBytesTotal$okhttp", "Lokhttp3/internal/http2/Http2Stream$StreamTimeout;", "Lokhttp3/internal/http2/Http2Stream$StreamTimeout;", "getReadTimeout$okhttp", "()Lokhttp3/internal/http2/Http2Stream$StreamTimeout;", "Lokhttp3/internal/http2/Http2Stream$FramingSink;", "sink", "Lokhttp3/internal/http2/Http2Stream$FramingSink;", "getSink$okhttp", "()Lokhttp3/internal/http2/Http2Stream$FramingSink;", "Lokhttp3/internal/http2/Http2Stream$FramingSource;", "Lokhttp3/internal/http2/Http2Stream$FramingSource;", "getSource$okhttp", "()Lokhttp3/internal/http2/Http2Stream$FramingSource;", "writeBytesMaximum", "getWriteBytesMaximum", "setWriteBytesMaximum$okhttp", "writeBytesTotal", "getWriteBytesTotal", "setWriteBytesTotal$okhttp", "getWriteTimeout$okhttp", "Companion", "FramingSink", "FramingSource", "StreamTimeout", "okhttp"})
@SourceDebugExtension({"SMAP\nHttp2Stream.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http2Stream.kt\nokhttp3/internal/http2/Http2Stream\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Util.kt\nokhttp3/internal/Util\n*L\n1#1,688:1\n1#2:689\n615#3,4:690\n615#3,4:694\n563#3:698\n615#3,4:699\n615#3,4:703\n563#3:707\n563#3:708\n615#3,4:709\n563#3:713\n557#3:714\n*S KotlinDebug\n*F\n+ 1 Http2Stream.kt\nokhttp3/internal/http2/Http2Stream\n*L\n176#1:690,4\n255#1:694,4\n263#1:698\n274#1:699,4\n281#1:703,4\n295#1:707\n305#1:708\n491#1:709,4\n637#1:713\n657#1:714\n*E\n"})
public final class Http2Stream {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  private final int id;
  
  @NotNull
  private final Http2Connection connection;
  
  private long readBytesTotal;
  
  private long readBytesAcknowledged;
  
  private long writeBytesTotal;
  
  private long writeBytesMaximum;
  
  @NotNull
  private final ArrayDeque<Headers> headersQueue;
  
  private boolean hasResponseHeaders;
  
  @NotNull
  private final FramingSource source;
  
  @NotNull
  private final FramingSink sink;
  
  @NotNull
  private final StreamTimeout readTimeout;
  
  @NotNull
  private final StreamTimeout writeTimeout;
  
  @Nullable
  private ErrorCode errorCode;
  
  @Nullable
  private IOException errorException;
  
  public static final long EMIT_BUFFER_SIZE = 16384L;
  
  public Http2Stream(int id, @NotNull Http2Connection connection, boolean outFinished, boolean inFinished, @Nullable Headers headers) {
    this.id = id;
    this.connection = connection;
    this.writeBytesMaximum = this.connection.getPeerSettings().getInitialWindowSize();
    this.headersQueue = new ArrayDeque<>();
    this.source = new FramingSource(
        this.connection.getOkHttpSettings().getInitialWindowSize(), 
        inFinished);
    this.sink = new FramingSink(
        outFinished);
    this.readTimeout = new StreamTimeout();
    this.writeTimeout = new StreamTimeout();
    if (headers != null) {
      if (!(!isLocallyInitiated() ? 1 : 0)) {
        int $i$a$-check-Http2Stream$1 = 0;
        String str = "locally-initiated streams shouldn't have headers yet";
        throw new IllegalStateException(str.toString());
      } 
      this.headersQueue.add(headers);
    } else if (!isLocallyInitiated()) {
      int $i$a$-check-Http2Stream$2 = 0;
      String str = "remotely-initiated streams should have headers";
      throw new IllegalStateException(str.toString());
    } 
  }
  
  public final int getId() {
    return this.id;
  }
  
  @NotNull
  public final Http2Connection getConnection() {
    return this.connection;
  }
  
  public final long getReadBytesTotal() {
    return this.readBytesTotal;
  }
  
  public final void setReadBytesTotal$okhttp(long <set-?>) {
    this.readBytesTotal = <set-?>;
  }
  
  public final long getReadBytesAcknowledged() {
    return this.readBytesAcknowledged;
  }
  
  public final void setReadBytesAcknowledged$okhttp(long <set-?>) {
    this.readBytesAcknowledged = <set-?>;
  }
  
  public final long getWriteBytesTotal() {
    return this.writeBytesTotal;
  }
  
  public final void setWriteBytesTotal$okhttp(long <set-?>) {
    this.writeBytesTotal = <set-?>;
  }
  
  public final long getWriteBytesMaximum() {
    return this.writeBytesMaximum;
  }
  
  public final void setWriteBytesMaximum$okhttp(long <set-?>) {
    this.writeBytesMaximum = <set-?>;
  }
  
  @NotNull
  public final FramingSource getSource$okhttp() {
    return this.source;
  }
  
  @NotNull
  public final FramingSink getSink$okhttp() {
    return this.sink;
  }
  
  @NotNull
  public final StreamTimeout getReadTimeout$okhttp() {
    return this.readTimeout;
  }
  
  @NotNull
  public final StreamTimeout getWriteTimeout$okhttp() {
    return this.writeTimeout;
  }
  
  @Nullable
  public final synchronized ErrorCode getErrorCode$okhttp() {
    return this.errorCode;
  }
  
  public final void setErrorCode$okhttp(@Nullable ErrorCode <set-?>) {
    this.errorCode = <set-?>;
  }
  
  @Nullable
  public final IOException getErrorException$okhttp() {
    return this.errorException;
  }
  
  public final void setErrorException$okhttp(@Nullable IOException <set-?>) {
    this.errorException = <set-?>;
  }
  
  public final synchronized boolean isOpen() {
    if (this.errorCode != null)
      return false; 
    if ((this.source.getFinished$okhttp() || this.source.getClosed$okhttp()) && (this.sink.getFinished() || this.sink.getClosed()) && this.hasResponseHeaders)
      return false; 
    return true;
  }
  
  public final boolean isLocallyInitiated() {
    boolean streamIsClient = ((this.id & 0x1) == 1);
    return (this.connection.getClient$okhttp() == streamIsClient);
  }
  
  @NotNull
  public final synchronized Headers takeHeaders() throws IOException {
    this.readTimeout.enter();
    try {
      while (this.headersQueue.isEmpty() && this.errorCode == null)
        waitForIo$okhttp(); 
    } finally {
      this.readTimeout.exitAndThrowIfTimedOut();
    } 
    if (!this.headersQueue.isEmpty()) {
      Intrinsics.checkNotNullExpressionValue(this.headersQueue.removeFirst(), "headersQueue.removeFirst()");
      return this.headersQueue.removeFirst();
    } 
    Intrinsics.checkNotNull(this.errorCode);
    throw (this.errorException != null) ? this.errorException : new StreamResetException(this.errorCode);
  }
  
  @NotNull
  public final synchronized Headers trailers() throws IOException {
    if (this.source.getFinished$okhttp() && this.source.getReceiveBuffer().exhausted() && this.source.getReadBuffer().exhausted()) {
      if (this.source.getTrailers() == null)
        this.source.getTrailers(); 
      return Util.EMPTY_HEADERS;
    } 
    if (this.errorCode != null) {
      Intrinsics.checkNotNull(this.errorCode);
      throw (this.errorException != null) ? this.errorException : new StreamResetException(this.errorCode);
    } 
    throw new IllegalStateException("too early; can't read the trailers yet");
  }
  
  public final void writeHeaders(@NotNull List<Header> responseHeaders, boolean outFinished, boolean flushHeaders) throws IOException {
    Intrinsics.checkNotNullParameter(responseHeaders, "responseHeaders");
    Object $this$assertThreadDoesntHoldLock$iv = this;
    int $i$f$assertThreadDoesntHoldLock = 0;
    if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); 
    boolean bool = false;
    bool = flushHeaders;
    synchronized (this) {
      int $i$a$-synchronized-Http2Stream$writeHeaders$1 = 0;
      this.hasResponseHeaders = true;
      if (outFinished)
        this.sink.setFinished(true); 
      Unit unit = Unit.INSTANCE;
    } 
    if (!bool)
      synchronized (this.connection) {
        int $i$a$-synchronized-Http2Stream$writeHeaders$2 = 0;
        bool = (this.connection.getWriteBytesTotal() >= this.connection.getWriteBytesMaximum());
        Unit unit = Unit.INSTANCE;
      }  
    this.connection.writeHeaders$okhttp(this.id, outFinished, responseHeaders);
    if (bool)
      this.connection.flush(); 
  }
  
  public final void enqueueTrailers(@NotNull Headers trailers) {
    Intrinsics.checkNotNullParameter(trailers, "trailers");
    synchronized (this) {
      int $i$a$-synchronized-Http2Stream$enqueueTrailers$1 = 0;
      if (!(!this.sink.getFinished() ? 1 : 0)) {
        int $i$a$-check-Http2Stream$enqueueTrailers$1$1 = 0;
        String str = "already finished";
        throw new IllegalStateException(str.toString());
      } 
      if (!((trailers.size() != 0) ? 1 : 0)) {
        int $i$a$-require-Http2Stream$enqueueTrailers$1$2 = 0;
        String str = "trailers.size() == 0";
        throw new IllegalArgumentException(str.toString());
      } 
      this.sink.setTrailers(trailers);
      Unit unit = Unit.INSTANCE;
    } 
  }
  
  @NotNull
  public final Timeout readTimeout() {
    return (Timeout)this.readTimeout;
  }
  
  @NotNull
  public final Timeout writeTimeout() {
    return (Timeout)this.writeTimeout;
  }
  
  @NotNull
  public final Source getSource() {
    return this.source;
  }
  
  @NotNull
  public final Sink getSink() {
    synchronized (this) {
      int $i$a$-synchronized-Http2Stream$getSink$1 = 0;
      if (!((this.hasResponseHeaders || isLocallyInitiated()) ? 1 : 0)) {
        int $i$a$-check-Http2Stream$getSink$1$1 = 0;
        String str = "reply before requesting the sink";
        throw new IllegalStateException(str.toString());
      } 
      Unit unit = Unit.INSTANCE;
    } 
    return this.sink;
  }
  
  public final void close(@NotNull ErrorCode rstStatusCode, @Nullable IOException errorException) throws IOException {
    Intrinsics.checkNotNullParameter(rstStatusCode, "rstStatusCode");
    if (!closeInternal(rstStatusCode, errorException))
      return; 
    this.connection.writeSynReset$okhttp(this.id, rstStatusCode);
  }
  
  public final void closeLater(@NotNull ErrorCode errorCode) {
    Intrinsics.checkNotNullParameter(errorCode, "errorCode");
    if (!closeInternal(errorCode, null))
      return; 
    this.connection.writeSynResetLater$okhttp(this.id, errorCode);
  }
  
  private final boolean closeInternal(ErrorCode errorCode, IOException errorException) {
    $this$assertThreadDoesntHoldLock$iv = this;
    int $i$f$assertThreadDoesntHoldLock = 0;
    if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); 
    synchronized (this) {
      int $i$a$-synchronized-Http2Stream$closeInternal$1 = 0;
      if (this.errorCode != null)
        return false; 
      this.errorCode = errorCode;
      this.errorException = errorException;
      Object $this$notifyAll$iv = this;
      int $i$f$notifyAll = 0;
      Intrinsics.checkNotNull($this$notifyAll$iv, "null cannot be cast to non-null type java.lang.Object");
      $this$notifyAll$iv.notifyAll();
      if (this.source.getFinished$okhttp() && this.sink.getFinished())
        return false; 
      Unit unit = Unit.INSTANCE;
    } 
    this.connection.removeStream$okhttp(this.id);
    return true;
  }
  
  public final void receiveData(@NotNull BufferedSource source, int length) throws IOException {
    Intrinsics.checkNotNullParameter(source, "source");
    Object $this$assertThreadDoesntHoldLock$iv = this;
    int $i$f$assertThreadDoesntHoldLock = 0;
    if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); 
    this.source.receive$okhttp(source, length);
  }
  
  public final void receiveHeaders(@NotNull Headers headers, boolean inFinished) {
    Intrinsics.checkNotNullParameter(headers, "headers");
    Object $this$assertThreadDoesntHoldLock$iv = this;
    int $i$f$assertThreadDoesntHoldLock = 0;
    if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); 
    boolean open = false;
    synchronized (this) {
      int $i$a$-synchronized-Http2Stream$receiveHeaders$1 = 0;
      if (!this.hasResponseHeaders || !inFinished) {
        this.hasResponseHeaders = true;
        this.headersQueue.add(headers);
      } else {
        this.source.setTrailers(headers);
      } 
      if (inFinished)
        this.source.setFinished$okhttp(true); 
      open = isOpen();
      Object $this$notifyAll$iv = this;
      int $i$f$notifyAll = 0;
      Intrinsics.checkNotNull($this$notifyAll$iv, "null cannot be cast to non-null type java.lang.Object");
      $this$notifyAll$iv.notifyAll();
      Unit unit = Unit.INSTANCE;
    } 
    if (!open)
      this.connection.removeStream$okhttp(this.id); 
  }
  
  public final synchronized void receiveRstStream(@NotNull ErrorCode errorCode) {
    Intrinsics.checkNotNullParameter(errorCode, "errorCode");
    if (this.errorCode == null) {
      this.errorCode = errorCode;
      Object $this$notifyAll$iv = this;
      int $i$f$notifyAll = 0;
      Intrinsics.checkNotNull($this$notifyAll$iv, "null cannot be cast to non-null type java.lang.Object");
      $this$notifyAll$iv.notifyAll();
    } 
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000>\n\002\030\002\n\002\030\002\n\002\020\t\n\000\n\002\020\013\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\023\n\002\030\002\n\002\b\007\b\004\030\0002\0020\001B\031\b\000\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007J\017\020\t\032\0020\bH\026¢\006\004\b\t\020\nJ\037\020\016\032\0020\0022\006\020\f\032\0020\0132\006\020\r\032\0020\002H\026¢\006\004\b\016\020\017J\037\020\024\032\0020\b2\006\020\021\032\0020\0202\006\020\r\032\0020\002H\000¢\006\004\b\022\020\023J\017\020\026\032\0020\025H\026¢\006\004\b\026\020\027J\027\020\030\032\0020\b2\006\020\016\032\0020\002H\002¢\006\004\b\030\020\031R\"\020\032\032\0020\0048\000@\000X\016¢\006\022\n\004\b\032\020\033\032\004\b\034\020\035\"\004\b\036\020\037R\"\020\005\032\0020\0048\000@\000X\016¢\006\022\n\004\b\005\020\033\032\004\b \020\035\"\004\b!\020\037R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\"R\027\020#\032\0020\0138\006¢\006\f\n\004\b#\020$\032\004\b%\020&R\027\020'\032\0020\0138\006¢\006\f\n\004\b'\020$\032\004\b(\020&R$\020*\032\004\030\0010)8\006@\006X\016¢\006\022\n\004\b*\020+\032\004\b,\020-\"\004\b.\020/¨\0060"}, d2 = {"Lokhttp3/internal/http2/Http2Stream$FramingSource;", "Lokio/Source;", "", "maxByteCount", "", "finished", "<init>", "(Lokhttp3/internal/http2/Http2Stream;JZ)V", "", "close", "()V", "Lokio/Buffer;", "sink", "byteCount", "read", "(Lokio/Buffer;J)J", "Lokio/BufferedSource;", "source", "receive$okhttp", "(Lokio/BufferedSource;J)V", "receive", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "updateConnectionFlowControl", "(J)V", "closed", "Z", "getClosed$okhttp", "()Z", "setClosed$okhttp", "(Z)V", "getFinished$okhttp", "setFinished$okhttp", "J", "readBuffer", "Lokio/Buffer;", "getReadBuffer", "()Lokio/Buffer;", "receiveBuffer", "getReceiveBuffer", "Lokhttp3/Headers;", "trailers", "Lokhttp3/Headers;", "getTrailers", "()Lokhttp3/Headers;", "setTrailers", "(Lokhttp3/Headers;)V", "okhttp"})
  @SourceDebugExtension({"SMAP\nHttp2Stream.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http2Stream.kt\nokhttp3/internal/http2/Http2Stream$FramingSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Util.kt\nokhttp3/internal/Util\n*L\n1#1,688:1\n1#2:689\n615#3,4:690\n615#3,4:694\n563#3:698\n563#3:699\n*S KotlinDebug\n*F\n+ 1 Http2Stream.kt\nokhttp3/internal/http2/Http2Stream$FramingSource\n*L\n407#1:690,4\n418#1:694,4\n458#1:698\n480#1:699\n*E\n"})
  public final class FramingSource implements Source {
    private final long maxByteCount;
    
    private boolean finished;
    
    @NotNull
    private final Buffer receiveBuffer;
    
    @NotNull
    private final Buffer readBuffer;
    
    @Nullable
    private Headers trailers;
    
    private boolean closed;
    
    public FramingSource(long maxByteCount, boolean finished) {
      this.maxByteCount = maxByteCount;
      this.finished = finished;
      this.receiveBuffer = new Buffer();
      this.readBuffer = new Buffer();
    }
    
    public final boolean getFinished$okhttp() {
      return this.finished;
    }
    
    public final void setFinished$okhttp(boolean <set-?>) {
      this.finished = <set-?>;
    }
    
    @NotNull
    public final Buffer getReceiveBuffer() {
      return this.receiveBuffer;
    }
    
    @NotNull
    public final Buffer getReadBuffer() {
      return this.readBuffer;
    }
    
    @Nullable
    public final Headers getTrailers() {
      return this.trailers;
    }
    
    public final void setTrailers(@Nullable Headers <set-?>) {
      this.trailers = <set-?>;
    }
    
    public final boolean getClosed$okhttp() {
      return this.closed;
    }
    
    public final void setClosed$okhttp(boolean <set-?>) {
      this.closed = <set-?>;
    }
    
    public long read(@NotNull Buffer sink, long byteCount) throws IOException {
      Object errorExceptionToDeliver;
      long readBytesDelivered;
      Intrinsics.checkNotNullParameter(sink, "sink");
      if (!((byteCount >= 0L) ? 1 : 0)) {
        int $i$a$-require-Http2Stream$FramingSource$read$1 = 0;
        String str = "byteCount < 0: " + byteCount;
        throw new IllegalArgumentException(str.toString());
      } 
      while (true) {
        boolean tryAgain = false;
        readBytesDelivered = 0L;
        readBytesDelivered = -1L;
        errorExceptionToDeliver = null;
        Http2Stream http2Stream1 = Http2Stream.this, http2Stream2 = Http2Stream.this;
        synchronized (http2Stream1) {
          int $i$a$-synchronized-Http2Stream$FramingSource$read$2 = 0;
          http2Stream2.getReadTimeout$okhttp().enter();
          try {
            if (http2Stream2.getErrorCode$okhttp() != null && !this.finished) {
              if (http2Stream2.getErrorException$okhttp() == null) {
                http2Stream2.getErrorException$okhttp();
                Intrinsics.checkNotNull(http2Stream2.getErrorCode$okhttp());
              } 
              errorExceptionToDeliver = new StreamResetException(http2Stream2.getErrorCode$okhttp());
            } 
            if (this.closed)
              throw new IOException("stream closed"); 
            if (this.readBuffer.size() > 0L) {
              readBytesDelivered = this.readBuffer.read(sink, Math.min(byteCount, this.readBuffer.size()));
              http2Stream2.setReadBytesTotal$okhttp(http2Stream2.getReadBytesTotal() + readBytesDelivered);
              long unacknowledgedBytesRead = http2Stream2.getReadBytesTotal() - http2Stream2.getReadBytesAcknowledged();
              if (errorExceptionToDeliver == null && unacknowledgedBytesRead >= (http2Stream2.getConnection().getOkHttpSettings().getInitialWindowSize() / 2)) {
                http2Stream2.getConnection().writeWindowUpdateLater$okhttp(http2Stream2.getId(), unacknowledgedBytesRead);
                http2Stream2.setReadBytesAcknowledged$okhttp(http2Stream2.getReadBytesTotal());
              } 
            } else if (!this.finished && errorExceptionToDeliver == null) {
              http2Stream2.waitForIo$okhttp();
              tryAgain = true;
            } 
          } finally {
            http2Stream2.getReadTimeout$okhttp().exitAndThrowIfTimedOut();
          } 
          Unit unit = Unit.INSTANCE;
        } 
        if (tryAgain)
          continue; 
        break;
      } 
      if (readBytesDelivered != -1L)
        return readBytesDelivered; 
      if (errorExceptionToDeliver != null)
        throw (Throwable)errorExceptionToDeliver; 
      return -1L;
    }
    
    private final void updateConnectionFlowControl(long read) {
      Object $this$assertThreadDoesntHoldLock$iv = Http2Stream.this;
      int $i$f$assertThreadDoesntHoldLock = 0;
      if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); 
      Http2Stream.this.getConnection().updateConnectionFlowControl$okhttp(read);
    }
    
    public final void receive$okhttp(@NotNull BufferedSource source, long byteCount) throws IOException {
      Intrinsics.checkNotNullParameter(source, "source");
      Object $this$assertThreadDoesntHoldLock$iv = Http2Stream.this;
      int $i$f$assertThreadDoesntHoldLock = 0;
      if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); 
      long remainingByteCount = 0L;
      remainingByteCount = byteCount;
      while (remainingByteCount > 0L) {
        boolean finished = false;
        boolean flowControlError = false;
        synchronized (Http2Stream.this) {
          int $i$a$-synchronized-Http2Stream$FramingSource$receive$1 = 0;
          finished = this.finished;
          flowControlError = (remainingByteCount + this.readBuffer.size() > this.maxByteCount);
          Unit unit = Unit.INSTANCE;
        } 
        if (flowControlError) {
          source.skip(remainingByteCount);
          Http2Stream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
          return;
        } 
        if (finished) {
          source.skip(remainingByteCount);
          return;
        } 
        long read = source.read(this.receiveBuffer, remainingByteCount);
        if (read == -1L)
          throw new EOFException(); 
        remainingByteCount -= read;
        Http2Stream http2Stream1 = Http2Stream.this, http2Stream2 = Http2Stream.this;
        synchronized (http2Stream1) {
          int $i$a$-synchronized-Http2Stream$FramingSource$receive$2 = 0;
          if (this.closed) {
            this.receiveBuffer.clear();
          } else {
            boolean wasEmpty = (this.readBuffer.size() == 0L);
            this.readBuffer.writeAll((Source)this.receiveBuffer);
            if (wasEmpty) {
              Object $this$notifyAll$iv = http2Stream2;
              int $i$f$notifyAll = 0;
              Intrinsics.checkNotNull($this$notifyAll$iv, "null cannot be cast to non-null type java.lang.Object");
              $this$notifyAll$iv.notifyAll();
            } 
          } 
          Unit unit = Unit.INSTANCE;
        } 
      } 
      updateConnectionFlowControl(byteCount);
    }
    
    @NotNull
    public Timeout timeout() {
      return (Timeout)Http2Stream.this.getReadTimeout$okhttp();
    }
    
    public void close() throws IOException {
      long bytesDiscarded = 0L;
      Http2Stream http2Stream1 = Http2Stream.this, http2Stream2 = Http2Stream.this;
      synchronized (http2Stream1) {
        int $i$a$-synchronized-Http2Stream$FramingSource$close$1 = 0;
        this.closed = true;
        bytesDiscarded = this.readBuffer.size();
        this.readBuffer.clear();
        Object $this$notifyAll$iv = http2Stream2;
        int $i$f$notifyAll = 0;
        Intrinsics.checkNotNull($this$notifyAll$iv, "null cannot be cast to non-null type java.lang.Object");
        $this$notifyAll$iv.notifyAll();
        Unit unit = Unit.INSTANCE;
      } 
      if (bytesDiscarded > 0L)
        updateConnectionFlowControl(bytesDiscarded); 
      Http2Stream.this.cancelStreamIfNecessary$okhttp();
    }
  }
  
  public final void cancelStreamIfNecessary$okhttp() throws IOException {
    Object $this$assertThreadDoesntHoldLock$iv = this;
    int $i$f$assertThreadDoesntHoldLock = 0;
    if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); 
    boolean open = false;
    boolean cancel = false;
    synchronized (this) {
      int $i$a$-synchronized-Http2Stream$cancelStreamIfNecessary$1 = 0;
      cancel = (!this.source.getFinished$okhttp() && this.source.getClosed$okhttp() && (this.sink.getFinished() || this.sink.getClosed()));
      open = isOpen();
      Unit unit = Unit.INSTANCE;
    } 
    if (cancel) {
      close(ErrorCode.CANCEL, null);
    } else if (!open) {
      this.connection.removeStream$okhttp(this.id);
    } 
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0006\n\002\030\002\n\002\030\002\n\002\020\013\n\002\b\003\n\002\020\002\n\002\b\006\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\f\n\002\030\002\n\002\b\007\b\004\030\0002\0020\001B\021\022\b\b\002\020\003\032\0020\002¢\006\004\b\004\020\005J\017\020\007\032\0020\006H\026¢\006\004\b\007\020\bJ\027\020\n\032\0020\0062\006\020\t\032\0020\002H\002¢\006\004\b\n\020\013J\017\020\f\032\0020\006H\026¢\006\004\b\f\020\bJ\017\020\016\032\0020\rH\026¢\006\004\b\016\020\017J\037\020\024\032\0020\0062\006\020\021\032\0020\0202\006\020\023\032\0020\022H\026¢\006\004\b\024\020\025R\"\020\026\032\0020\0028\006@\006X\016¢\006\022\n\004\b\026\020\027\032\004\b\030\020\031\"\004\b\032\020\013R\"\020\003\032\0020\0028\006@\006X\016¢\006\022\n\004\b\003\020\027\032\004\b\033\020\031\"\004\b\034\020\013R\024\020\035\032\0020\0208\002X\004¢\006\006\n\004\b\035\020\036R$\020 \032\004\030\0010\0378\006@\006X\016¢\006\022\n\004\b \020!\032\004\b\"\020#\"\004\b$\020%¨\006&"}, d2 = {"Lokhttp3/internal/http2/Http2Stream$FramingSink;", "Lokio/Sink;", "", "finished", "<init>", "(Lokhttp3/internal/http2/Http2Stream;Z)V", "", "close", "()V", "outFinishedOnLastFrame", "emitFrame", "(Z)V", "flush", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "Lokio/Buffer;", "source", "", "byteCount", "write", "(Lokio/Buffer;J)V", "closed", "Z", "getClosed", "()Z", "setClosed", "getFinished", "setFinished", "sendBuffer", "Lokio/Buffer;", "Lokhttp3/Headers;", "trailers", "Lokhttp3/Headers;", "getTrailers", "()Lokhttp3/Headers;", "setTrailers", "(Lokhttp3/Headers;)V", "okhttp"})
  @SourceDebugExtension({"SMAP\nHttp2Stream.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http2Stream.kt\nokhttp3/internal/http2/Http2Stream$FramingSink\n+ 2 Util.kt\nokhttp3/internal/Util\n*L\n1#1,688:1\n615#2,4:689\n615#2,4:693\n615#2,4:697\n*S KotlinDebug\n*F\n+ 1 Http2Stream.kt\nokhttp3/internal/http2/Http2Stream$FramingSink\n*L\n528#1:689,4\n573#1:693,4\n589#1:697,4\n*E\n"})
  public final class FramingSink implements Sink {
    private boolean finished;
    
    @NotNull
    private final Buffer sendBuffer;
    
    @Nullable
    private Headers trailers;
    
    private boolean closed;
    
    public FramingSink(boolean finished) {
      this.finished = finished;
      this.sendBuffer = new Buffer();
    }
    
    public final boolean getFinished() {
      return this.finished;
    }
    
    public final void setFinished(boolean <set-?>) {
      this.finished = <set-?>;
    }
    
    @Nullable
    public final Headers getTrailers() {
      return this.trailers;
    }
    
    public final void setTrailers(@Nullable Headers <set-?>) {
      this.trailers = <set-?>;
    }
    
    public final boolean getClosed() {
      return this.closed;
    }
    
    public final void setClosed(boolean <set-?>) {
      this.closed = <set-?>;
    }
    
    public void write(@NotNull Buffer source, long byteCount) throws IOException {
      Intrinsics.checkNotNullParameter(source, "source");
      Object $this$assertThreadDoesntHoldLock$iv = Http2Stream.this;
      int $i$f$assertThreadDoesntHoldLock = 0;
      if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); 
      this.sendBuffer.write(source, byteCount);
      while (this.sendBuffer.size() >= 16384L)
        emitFrame(false); 
    }
    
    private final void emitFrame(boolean outFinishedOnLastFrame) throws IOException {
      long toWrite = 0L;
      boolean outFinished = false;
      null = Http2Stream.this;
      Http2Stream http2Stream = Http2Stream.this;
      synchronized (null) {
        int $i$a$-synchronized-Http2Stream$FramingSink$emitFrame$1 = 0;
        http2Stream.getWriteTimeout$okhttp().enter();
        try {
          while (http2Stream.getWriteBytesTotal() >= http2Stream.getWriteBytesMaximum() && !this.finished && !this.closed && http2Stream.getErrorCode$okhttp() == null)
            http2Stream.waitForIo$okhttp(); 
        } finally {
          http2Stream.getWriteTimeout$okhttp().exitAndThrowIfTimedOut();
        } 
        http2Stream.checkOutNotClosed$okhttp();
        toWrite = Math.min(http2Stream.getWriteBytesMaximum() - http2Stream.getWriteBytesTotal(), this.sendBuffer.size());
        http2Stream.setWriteBytesTotal$okhttp(http2Stream.getWriteBytesTotal() + toWrite);
        outFinished = (outFinishedOnLastFrame && toWrite == this.sendBuffer.size());
        Unit unit = Unit.INSTANCE;
      } 
      Http2Stream.this.getWriteTimeout$okhttp().enter();
      try {
        Http2Stream.this.getConnection().writeData(Http2Stream.this.getId(), outFinished, this.sendBuffer, toWrite);
      } finally {
        Http2Stream.this.getWriteTimeout$okhttp().exitAndThrowIfTimedOut();
      } 
    }
    
    public void flush() throws IOException {
      Object $this$assertThreadDoesntHoldLock$iv = Http2Stream.this;
      int $i$f$assertThreadDoesntHoldLock = 0;
      if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); 
      $this$assertThreadDoesntHoldLock$iv = Http2Stream.this;
      Http2Stream http2Stream = Http2Stream.this;
      synchronized ($this$assertThreadDoesntHoldLock$iv) {
        int $i$a$-synchronized-Http2Stream$FramingSink$flush$1 = 0;
        http2Stream.checkOutNotClosed$okhttp();
        Unit unit = Unit.INSTANCE;
      } 
      while (this.sendBuffer.size() > 0L) {
        emitFrame(false);
        Http2Stream.this.getConnection().flush();
      } 
    }
    
    @NotNull
    public Timeout timeout() {
      return (Timeout)Http2Stream.this.getWriteTimeout$okhttp();
    }
    
    public void close() throws IOException {
      Object $this$assertThreadDoesntHoldLock$iv = Http2Stream.this;
      int $i$f$assertThreadDoesntHoldLock = 0;
      if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); 
      boolean outFinished = false;
      Http2Stream http2Stream = Http2Stream.this;
      null = Http2Stream.this;
      synchronized (http2Stream) {
        int $i$a$-synchronized-Http2Stream$FramingSink$close$1 = 0;
        if (this.closed)
          return; 
        outFinished = (null.getErrorCode$okhttp() == null);
        Unit unit = Unit.INSTANCE;
      } 
      if (!(Http2Stream.this.getSink$okhttp()).finished) {
        boolean hasData = (this.sendBuffer.size() > 0L);
        boolean hasTrailers = (this.trailers != null);
        if (hasTrailers) {
          while (this.sendBuffer.size() > 0L)
            emitFrame(false); 
          Intrinsics.checkNotNull(this.trailers);
          Http2Stream.this.getConnection().writeHeaders$okhttp(Http2Stream.this.getId(), outFinished, Util.toHeaderList(this.trailers));
        } else if (hasData) {
          while (this.sendBuffer.size() > 0L)
            emitFrame(true); 
        } else if (outFinished) {
          Http2Stream.this.getConnection().writeData(Http2Stream.this.getId(), true, null, 0L);
        } 
      } 
      synchronized (Http2Stream.this) {
        int $i$a$-synchronized-Http2Stream$FramingSink$close$2 = 0;
        this.closed = true;
        Unit unit = Unit.INSTANCE;
      } 
      Http2Stream.this.getConnection().flush();
      Http2Stream.this.cancelStreamIfNecessary$okhttp();
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\t\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\024\020\005\032\0020\0048\000XT¢\006\006\n\004\b\005\020\006¨\006\007"}, d2 = {"Lokhttp3/internal/http2/Http2Stream$Companion;", "", "<init>", "()V", "", "EMIT_BUFFER_SIZE", "J", "okhttp"})
  public static final class Companion {
    private Companion() {}
  }
  
  public final void addBytesToWriteWindow(long delta) {
    this.writeBytesMaximum += delta;
    if (delta > 0L) {
      Object $this$notifyAll$iv = this;
      int $i$f$notifyAll = 0;
      Intrinsics.checkNotNull($this$notifyAll$iv, "null cannot be cast to non-null type java.lang.Object");
      $this$notifyAll$iv.notifyAll();
    } 
  }
  
  public final void checkOutNotClosed$okhttp() throws IOException {
    if (this.sink.getClosed())
      throw new IOException("stream closed"); 
    if (this.sink.getFinished())
      throw new IOException("stream finished"); 
    if (this.errorCode != null) {
      Intrinsics.checkNotNull(this.errorCode);
      throw (this.errorException != null) ? this.errorException : new StreamResetException(this.errorCode);
    } 
  }
  
  public final void waitForIo$okhttp() throws InterruptedIOException {
    try {
      Object $this$wait$iv = this;
      int $i$f$wait = 0;
      Intrinsics.checkNotNull($this$wait$iv, "null cannot be cast to non-null type java.lang.Object");
      $this$wait$iv.wait();
    } catch (InterruptedException _) {
      Thread.currentThread().interrupt();
      throw new InterruptedIOException();
    } 
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\005\b\004\030\0002\0020\001B\007¢\006\004\b\002\020\003J\r\020\005\032\0020\004¢\006\004\b\005\020\006J\031\020\t\032\0020\0072\b\020\b\032\004\030\0010\007H\024¢\006\004\b\t\020\nJ\017\020\013\032\0020\004H\024¢\006\004\b\013\020\006¨\006\f"}, d2 = {"Lokhttp3/internal/http2/Http2Stream$StreamTimeout;", "Lokio/AsyncTimeout;", "<init>", "(Lokhttp3/internal/http2/Http2Stream;)V", "", "exitAndThrowIfTimedOut", "()V", "Ljava/io/IOException;", "cause", "newTimeoutException", "(Ljava/io/IOException;)Ljava/io/IOException;", "timedOut", "okhttp"})
  public final class StreamTimeout extends AsyncTimeout {
    protected void timedOut() {
      Http2Stream.this.closeLater(ErrorCode.CANCEL);
      Http2Stream.this.getConnection().sendDegradedPingLater$okhttp();
    }
    
    @NotNull
    protected IOException newTimeoutException(@Nullable IOException cause) {
      SocketTimeoutException socketTimeoutException1 = new SocketTimeoutException("timeout"), $this$newTimeoutException_u24lambda_u240 = socketTimeoutException1;
      int $i$a$-apply-Http2Stream$StreamTimeout$newTimeoutException$1 = 0;
      if (cause != null)
        $this$newTimeoutException_u24lambda_u240.initCause(cause); 
      return socketTimeoutException1;
    }
    
    public final void exitAndThrowIfTimedOut() throws IOException {
      if (exit())
        throw newTimeoutException(null); 
    }
  }
}
