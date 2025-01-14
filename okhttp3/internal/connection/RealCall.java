package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.internal.http.BridgeInterceptor;
import okhttp3.internal.http.CallServerInterceptor;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import okhttp3.internal.platform.Platform;
import okio.AsyncTimeout;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000¥\001\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\003\n\002\030\002\n\000\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\t\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\007\n\002\030\002\n\002\b\b\n\002\030\002\n\000\n\002\030\002\n\002\b\016\n\002\020\016\n\002\b\003\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\006\n\002\020\000\n\002\b\013\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\013\n\002\b\005*\001x\030\0002\0020\001:\002z{B\037\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\006\020\007\032\0020\006¢\006\004\b\b\020\tJ\025\020\r\032\0020\f2\006\020\013\032\0020\n¢\006\004\b\r\020\016J#\020\022\032\0028\000\"\n\b\000\020\020*\004\030\0010\0172\006\020\021\032\0028\000H\002¢\006\004\b\022\020\023J\017\020\024\032\0020\fH\002¢\006\004\b\024\020\025J\017\020\026\032\0020\fH\026¢\006\004\b\026\020\025J\017\020\027\032\0020\000H\026¢\006\004\b\027\020\030J\027\020\034\032\0020\0332\006\020\032\032\0020\031H\002¢\006\004\b\034\020\035J\027\020 \032\0020\f2\006\020\037\032\0020\036H\026¢\006\004\b \020!J\035\020$\032\0020\f2\006\020\"\032\0020\0042\006\020#\032\0020\006¢\006\004\b$\020%J\017\020'\032\0020&H\026¢\006\004\b'\020(J\027\020,\032\0020\f2\006\020)\032\0020\006H\000¢\006\004\b*\020+J\017\020.\032\0020&H\000¢\006\004\b-\020(J\027\0204\032\002012\006\0200\032\0020/H\000¢\006\004\b2\0203J\017\0205\032\0020\006H\026¢\006\004\b5\0206J\017\0207\032\0020\006H\026¢\006\004\b7\0206J;\020=\032\0028\000\"\n\b\000\020\020*\004\030\0010\0172\006\0208\032\002012\006\0209\032\0020\0062\006\020:\032\0020\0062\006\020\021\032\0028\000H\000¢\006\004\b;\020<J\033\020?\032\004\030\0010\0172\b\020\021\032\004\030\0010\017H\000¢\006\004\b>\020\023J\017\020C\032\0020@H\000¢\006\004\bA\020BJ\021\020G\032\004\030\0010DH\000¢\006\004\bE\020FJ\017\020\"\032\0020\004H\026¢\006\004\b\"\020HJ\r\020I\032\0020\006¢\006\004\bI\0206J\017\020K\032\0020JH\026¢\006\004\bK\020LJ\r\020M\032\0020\f¢\006\004\bM\020\025J#\020O\032\0028\000\"\n\b\000\020\020*\004\030\0010\0172\006\020N\032\0028\000H\002¢\006\004\bO\020\023J\017\020P\032\0020@H\002¢\006\004\bP\020BR\030\020R\032\004\030\0010Q8\002@\002X\016¢\006\006\n\004\bR\020SR\026\020T\032\0020\0068\002@\002X\016¢\006\006\n\004\bT\020UR\027\020\003\032\0020\0028\006¢\006\f\n\004\b\003\020V\032\004\bW\020XR(\020\013\032\004\030\0010\n2\b\020Y\032\004\030\0010\n8\006@BX\016¢\006\f\n\004\b\013\020Z\032\004\b[\020\\R\024\020^\032\0020]8\002X\004¢\006\006\n\004\b^\020_R$\020`\032\004\030\0010\n8\006@\006X\016¢\006\022\n\004\b`\020Z\032\004\ba\020\\\"\004\bb\020\016R\032\020d\032\0020c8\000X\004¢\006\f\n\004\bd\020e\032\004\bf\020gR\030\0208\032\004\030\001018\002@\002X\016¢\006\006\n\004\b8\020hR\030\020j\032\004\030\0010i8\002@\002X\016¢\006\006\n\004\bj\020kR\024\020m\032\0020l8\002X\004¢\006\006\n\004\bm\020nR\026\020o\032\0020\0068\002@\002X\016¢\006\006\n\004\bo\020UR\027\020\007\032\0020\0068\006¢\006\f\n\004\b\007\020U\032\004\bp\0206R(\020q\032\004\030\001012\b\020Y\032\004\030\001018\000@BX\016¢\006\f\n\004\bq\020h\032\004\br\020sR\027\020\005\032\0020\0048\006¢\006\f\n\004\b\005\020t\032\004\bu\020HR\026\020v\032\0020\0068\002@\002X\016¢\006\006\n\004\bv\020UR\026\020w\032\0020\0068\002@\002X\016¢\006\006\n\004\bw\020UR\024\020K\032\0020x8\002X\004¢\006\006\n\004\bK\020yR\026\020M\032\0020\0068\002@\002X\016¢\006\006\n\004\bM\020U¨\006|"}, d2 = {"Lokhttp3/internal/connection/RealCall;", "Lokhttp3/Call;", "Lokhttp3/OkHttpClient;", "client", "Lokhttp3/Request;", "originalRequest", "", "forWebSocket", "<init>", "(Lokhttp3/OkHttpClient;Lokhttp3/Request;Z)V", "Lokhttp3/internal/connection/RealConnection;", "connection", "", "acquireConnectionNoEvents", "(Lokhttp3/internal/connection/RealConnection;)V", "Ljava/io/IOException;", "E", "e", "callDone", "(Ljava/io/IOException;)Ljava/io/IOException;", "callStart", "()V", "cancel", "clone", "()Lokhttp3/internal/connection/RealCall;", "Lokhttp3/HttpUrl;", "url", "Lokhttp3/Address;", "createAddress", "(Lokhttp3/HttpUrl;)Lokhttp3/Address;", "Lokhttp3/Callback;", "responseCallback", "enqueue", "(Lokhttp3/Callback;)V", "request", "newExchangeFinder", "enterNetworkInterceptorExchange", "(Lokhttp3/Request;Z)V", "Lokhttp3/Response;", "execute", "()Lokhttp3/Response;", "closeExchange", "exitNetworkInterceptorExchange$okhttp", "(Z)V", "exitNetworkInterceptorExchange", "getResponseWithInterceptorChain$okhttp", "getResponseWithInterceptorChain", "Lokhttp3/internal/http/RealInterceptorChain;", "chain", "Lokhttp3/internal/connection/Exchange;", "initExchange$okhttp", "(Lokhttp3/internal/http/RealInterceptorChain;)Lokhttp3/internal/connection/Exchange;", "initExchange", "isCanceled", "()Z", "isExecuted", "exchange", "requestDone", "responseDone", "messageDone$okhttp", "(Lokhttp3/internal/connection/Exchange;ZZLjava/io/IOException;)Ljava/io/IOException;", "messageDone", "noMoreExchanges$okhttp", "noMoreExchanges", "", "redactedUrl$okhttp", "()Ljava/lang/String;", "redactedUrl", "Ljava/net/Socket;", "releaseConnectionNoEvents$okhttp", "()Ljava/net/Socket;", "releaseConnectionNoEvents", "()Lokhttp3/Request;", "retryAfterFailure", "Lokio/AsyncTimeout;", "timeout", "()Lokio/AsyncTimeout;", "timeoutEarlyExit", "cause", "timeoutExit", "toLoggableString", "", "callStackTrace", "Ljava/lang/Object;", "canceled", "Z", "Lokhttp3/OkHttpClient;", "getClient", "()Lokhttp3/OkHttpClient;", "<set-?>", "Lokhttp3/internal/connection/RealConnection;", "getConnection", "()Lokhttp3/internal/connection/RealConnection;", "Lokhttp3/internal/connection/RealConnectionPool;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "connectionToCancel", "getConnectionToCancel", "setConnectionToCancel", "Lokhttp3/EventListener;", "eventListener", "Lokhttp3/EventListener;", "getEventListener$okhttp", "()Lokhttp3/EventListener;", "Lokhttp3/internal/connection/Exchange;", "Lokhttp3/internal/connection/ExchangeFinder;", "exchangeFinder", "Lokhttp3/internal/connection/ExchangeFinder;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "executed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "expectMoreExchanges", "getForWebSocket", "interceptorScopedExchange", "getInterceptorScopedExchange$okhttp", "()Lokhttp3/internal/connection/Exchange;", "Lokhttp3/Request;", "getOriginalRequest", "requestBodyOpen", "responseBodyOpen", "okhttp3/internal/connection/RealCall.timeout.1", "Lokhttp3/internal/connection/RealCall$timeout$1;", "AsyncCall", "CallReference", "okhttp"})
@SourceDebugExtension({"SMAP\nRealCall.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealCall.kt\nokhttp3/internal/connection/RealCall\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Util.kt\nokhttp3/internal/Util\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,551:1\n1#2:552\n608#3,4:553\n615#3,4:557\n615#3,4:561\n608#3,4:565\n350#4,7:569\n*S KotlinDebug\n*F\n+ 1 RealCall.kt\nokhttp3/internal/connection/RealCall\n*L\n269#1:553,4\n344#1:557,4\n348#1:561,4\n375#1:565,4\n378#1:569,7\n*E\n"})
public final class RealCall implements Call {
  @NotNull
  private final OkHttpClient client;
  
  @NotNull
  private final Request originalRequest;
  
  private final boolean forWebSocket;
  
  @NotNull
  private final RealConnectionPool connectionPool;
  
  @NotNull
  private final EventListener eventListener;
  
  @NotNull
  private final RealCall$timeout$1 timeout;
  
  @NotNull
  private final AtomicBoolean executed;
  
  @Nullable
  private Object callStackTrace;
  
  @Nullable
  private ExchangeFinder exchangeFinder;
  
  @Nullable
  private RealConnection connection;
  
  private boolean timeoutEarlyExit;
  
  @Nullable
  private Exchange interceptorScopedExchange;
  
  private boolean requestBodyOpen;
  
  private boolean responseBodyOpen;
  
  private boolean expectMoreExchanges;
  
  private volatile boolean canceled;
  
  @Nullable
  private volatile Exchange exchange;
  
  @Nullable
  private volatile RealConnection connectionToCancel;
  
  public RealCall(@NotNull OkHttpClient client, @NotNull Request originalRequest, boolean forWebSocket) {
    this.client = client;
    this.originalRequest = originalRequest;
    this.forWebSocket = forWebSocket;
    this.connectionPool = this.client.connectionPool().getDelegate$okhttp();
    this.eventListener = this.client.eventListenerFactory().create(this);
    RealCall$timeout$1 realCall$timeout$11 = new RealCall$timeout$1();
    RealCall$timeout$1 realCall$timeout$12 = realCall$timeout$11;
    RealCall realCall = this;
    int $i$a$-apply-RealCall$timeout$2 = 0;
    realCall$timeout$12.timeout(this.client.callTimeoutMillis(), TimeUnit.MILLISECONDS);
    realCall.timeout = realCall$timeout$11;
    this.executed = new AtomicBoolean();
    this.expectMoreExchanges = true;
  }
  
  @NotNull
  public final OkHttpClient getClient() {
    return this.client;
  }
  
  @NotNull
  public final Request getOriginalRequest() {
    return this.originalRequest;
  }
  
  public final boolean getForWebSocket() {
    return this.forWebSocket;
  }
  
  @NotNull
  public final EventListener getEventListener$okhttp() {
    return this.eventListener;
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\021\n\000\n\002\030\002\n\002\020\002\n\002\b\003*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\024¢\006\004\b\003\020\004¨\006\005"}, d2 = {"okhttp3/internal/connection/RealCall.timeout.1", "Lokio/AsyncTimeout;", "", "timedOut", "()V", "okhttp"})
  public static final class RealCall$timeout$1 extends AsyncTimeout {
    protected void timedOut() {
      RealCall.this.cancel();
    }
  }
  
  @Nullable
  public final RealConnection getConnection() {
    return this.connection;
  }
  
  @Nullable
  public final Exchange getInterceptorScopedExchange$okhttp() {
    return this.interceptorScopedExchange;
  }
  
  @Nullable
  public final RealConnection getConnectionToCancel() {
    return this.connectionToCancel;
  }
  
  public final void setConnectionToCancel(@Nullable RealConnection <set-?>) {
    this.connectionToCancel = <set-?>;
  }
  
  @NotNull
  public AsyncTimeout timeout() {
    return this.timeout;
  }
  
  @NotNull
  public RealCall clone() {
    return new RealCall(this.client, this.originalRequest, this.forWebSocket);
  }
  
  @NotNull
  public Request request() {
    return this.originalRequest;
  }
  
  public void cancel() {
    if (this.canceled)
      return; 
    this.canceled = true;
    if (this.exchange != null) {
      this.exchange.cancel();
    } else {
    
    } 
    if (this.connectionToCancel != null) {
      this.connectionToCancel.cancel();
    } else {
    
    } 
    this.eventListener.canceled(this);
  }
  
  public boolean isCanceled() {
    return this.canceled;
  }
  
  @NotNull
  public Response execute() {
    if (!this.executed.compareAndSet(false, true)) {
      int $i$a$-check-RealCall$execute$1 = 0;
      String str = "Already Executed";
      throw new IllegalStateException(str.toString());
    } 
    this.timeout.enter();
    callStart();
    try {
      this.client.dispatcher().executed$okhttp(this);
      return getResponseWithInterceptorChain$okhttp();
    } finally {
      this.client.dispatcher().finished$okhttp(this);
    } 
  }
  
  public void enqueue(@NotNull Callback responseCallback) {
    Intrinsics.checkNotNullParameter(responseCallback, "responseCallback");
    if (!this.executed.compareAndSet(false, true)) {
      int $i$a$-check-RealCall$enqueue$1 = 0;
      String str = "Already Executed";
      throw new IllegalStateException(str.toString());
    } 
    callStart();
    this.client.dispatcher().enqueue$okhttp(new AsyncCall(responseCallback));
  }
  
  public boolean isExecuted() {
    return this.executed.get();
  }
  
  private final void callStart() {
    this.callStackTrace = Platform.Companion.get().getStackTraceForCloseable("response.body().close()");
    this.eventListener.callStart(this);
  }
  
  @NotNull
  public final Response getResponseWithInterceptorChain$okhttp() throws IOException {
    List<RetryAndFollowUpInterceptor> interceptors = new ArrayList();
    CollectionsKt.addAll(interceptors, this.client.interceptors());
    interceptors.add(new RetryAndFollowUpInterceptor(this.client));
    interceptors.add(new BridgeInterceptor(this.client.cookieJar()));
    interceptors.add(new CacheInterceptor(this.client.cache()));
    interceptors.add(ConnectInterceptor.INSTANCE);
    if (!this.forWebSocket)
      CollectionsKt.addAll(interceptors, this.client.networkInterceptors()); 
    interceptors.add(new CallServerInterceptor(this.forWebSocket));
    RealInterceptorChain chain = new RealInterceptorChain(this, interceptors, 0, null, this.originalRequest, this.client.connectTimeoutMillis(), this.client.readTimeoutMillis(), this.client.writeTimeoutMillis());
    boolean calledNoMoreExchanges = false;
  }
  
  public final void enterNetworkInterceptorExchange(@NotNull Request request, boolean newExchangeFinder) {
    Intrinsics.checkNotNullParameter(request, "request");
    if (!((this.interceptorScopedExchange == null) ? 1 : 0)) {
      String str = "Check failed.";
      throw new IllegalStateException(str.toString());
    } 
    synchronized (this) {
      int $i$a$-synchronized-RealCall$enterNetworkInterceptorExchange$1 = 0;
      if (!(!this.responseBodyOpen ? 1 : 0)) {
        int $i$a$-check-RealCall$enterNetworkInterceptorExchange$1$1 = 0;
        String str = "cannot make a new request because the previous response is still open: please call response.close()";
        throw new IllegalStateException(str.toString());
      } 
      if (!(!this.requestBodyOpen ? 1 : 0)) {
        String str = "Check failed.";
        throw new IllegalStateException(str.toString());
      } 
      Unit unit = Unit.INSTANCE;
    } 
    if (newExchangeFinder)
      this.exchangeFinder = new ExchangeFinder(this.connectionPool, createAddress(request.url()), this, this.eventListener); 
  }
  
  @NotNull
  public final Exchange initExchange$okhttp(@NotNull RealInterceptorChain chain) {
    Intrinsics.checkNotNullParameter(chain, "chain");
    synchronized (this) {
      int $i$a$-synchronized-RealCall$initExchange$1 = 0;
      if (!this.expectMoreExchanges) {
        int $i$a$-check-RealCall$initExchange$1$1 = 0;
        String str = "released";
        throw new IllegalStateException(str.toString());
      } 
      if (!(!this.responseBodyOpen ? 1 : 0)) {
        String str = "Check failed.";
        throw new IllegalStateException(str.toString());
      } 
      if (!(!this.requestBodyOpen ? 1 : 0)) {
        String str = "Check failed.";
        throw new IllegalStateException(str.toString());
      } 
      Unit unit = Unit.INSTANCE;
    } 
    Intrinsics.checkNotNull(this.exchangeFinder);
    ExchangeFinder exchangeFinder = this.exchangeFinder;
    ExchangeCodec codec = exchangeFinder.find(this.client, chain);
    Exchange result = new Exchange(this, this.eventListener, exchangeFinder, codec);
    this.interceptorScopedExchange = result;
    this.exchange = result;
    synchronized (this) {
      int $i$a$-synchronized-RealCall$initExchange$2 = 0;
      this.requestBodyOpen = true;
      this.responseBodyOpen = true;
      Unit unit = Unit.INSTANCE;
    } 
    if (this.canceled)
      throw new IOException("Canceled"); 
    return result;
  }
  
  public final void acquireConnectionNoEvents(@NotNull RealConnection connection) {
    Intrinsics.checkNotNullParameter(connection, "connection");
    Object $this$assertThreadHoldsLock$iv = connection;
    int $i$f$assertThreadHoldsLock = 0;
    if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv); 
    if (!((this.connection == null) ? 1 : 0)) {
      String str = "Check failed.";
      throw new IllegalStateException(str.toString());
    } 
    this.connection = connection;
    connection.getCalls().add(new CallReference(this, this.callStackTrace));
  }
  
  public final <E extends IOException> E messageDone$okhttp(@NotNull Exchange exchange, boolean requestDone, boolean responseDone, IOException e) {
    Intrinsics.checkNotNullParameter(exchange, "exchange");
    if (!Intrinsics.areEqual(exchange, this.exchange))
      return (E)e; 
    boolean bothStreamsDone = false;
    boolean callDone = false;
    synchronized (this) {
      int $i$a$-synchronized-RealCall$messageDone$1 = 0;
      if ((requestDone && this.requestBodyOpen) || (responseDone && this.responseBodyOpen)) {
        if (requestDone)
          this.requestBodyOpen = false; 
        if (responseDone)
          this.responseBodyOpen = false; 
        bothStreamsDone = (!this.requestBodyOpen && !this.responseBodyOpen);
        callDone = (!this.requestBodyOpen && !this.responseBodyOpen && !this.expectMoreExchanges);
      } 
      Unit unit = Unit.INSTANCE;
    } 
    if (bothStreamsDone) {
      this.exchange = null;
      if (this.connection != null) {
        this.connection.incrementSuccessCount$okhttp();
      } else {
      
      } 
    } 
    if (callDone)
      return callDone((E)e); 
    return (E)e;
  }
  
  @Nullable
  public final IOException noMoreExchanges$okhttp(@Nullable IOException e) {
    boolean callDone = false;
    synchronized (this) {
      int $i$a$-synchronized-RealCall$noMoreExchanges$1 = 0;
      if (this.expectMoreExchanges) {
        this.expectMoreExchanges = false;
        callDone = (!this.requestBodyOpen && !this.responseBodyOpen);
      } 
      Unit unit = Unit.INSTANCE;
    } 
    if (callDone)
      return callDone(e); 
    return e;
  }
  
  private final <E extends IOException> E callDone(IOException e) {
    Object $this$assertThreadDoesntHoldLock$iv = this;
    int $i$f$assertThreadDoesntHoldLock = 0;
    if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); 
    RealConnection connection = this.connection;
    if (connection != null) {
      Object object = connection;
      int i = 0;
      if (Util.assertionsEnabled && Thread.holdsLock(object))
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + object); 
      synchronized (connection) {
        int $i$a$-synchronized-RealCall$callDone$socket$1 = 0;
        Socket socket1 = releaseConnectionNoEvents$okhttp();
      } 
      Socket socket = socket1;
      if (this.connection == null) {
        if (socket != null) {
          Util.closeQuietly(socket);
        } else {
        
        } 
        this.eventListener.connectionReleased(this, connection);
      } else if (!((socket == null) ? 1 : 0)) {
        String str = "Check failed.";
        throw new IllegalStateException(str.toString());
      } 
    } 
    IOException result = timeoutExit(e);
    if (e != null) {
      Intrinsics.checkNotNull(result);
      this.eventListener.callFailed(this, result);
    } else {
      this.eventListener.callEnd(this);
    } 
    return (E)result;
  }
  
  @Nullable
  public final Socket releaseConnectionNoEvents$okhttp() {
    // Byte code:
    //   0: aload_0
    //   1: getfield connection : Lokhttp3/internal/connection/RealConnection;
    //   4: dup
    //   5: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   8: astore_1
    //   9: aload_1
    //   10: astore_2
    //   11: iconst_0
    //   12: istore_3
    //   13: getstatic okhttp3/internal/Util.assertionsEnabled : Z
    //   16: ifeq -> 69
    //   19: aload_2
    //   20: invokestatic holdsLock : (Ljava/lang/Object;)Z
    //   23: ifne -> 69
    //   26: new java/lang/AssertionError
    //   29: dup
    //   30: new java/lang/StringBuilder
    //   33: dup
    //   34: invokespecial <init> : ()V
    //   37: ldc_w 'Thread '
    //   40: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: invokestatic currentThread : ()Ljava/lang/Thread;
    //   46: invokevirtual getName : ()Ljava/lang/String;
    //   49: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: ldc_w ' MUST hold lock on '
    //   55: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: aload_2
    //   59: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   62: invokevirtual toString : ()Ljava/lang/String;
    //   65: invokespecial <init> : (Ljava/lang/Object;)V
    //   68: athrow
    //   69: nop
    //   70: aload_1
    //   71: invokevirtual getCalls : ()Ljava/util/List;
    //   74: astore_2
    //   75: aload_2
    //   76: astore #4
    //   78: iconst_0
    //   79: istore #5
    //   81: iconst_0
    //   82: istore #6
    //   84: aload #4
    //   86: invokeinterface iterator : ()Ljava/util/Iterator;
    //   91: astore #7
    //   93: aload #7
    //   95: invokeinterface hasNext : ()Z
    //   100: ifeq -> 145
    //   103: aload #7
    //   105: invokeinterface next : ()Ljava/lang/Object;
    //   110: astore #8
    //   112: aload #8
    //   114: checkcast java/lang/ref/Reference
    //   117: astore #9
    //   119: iconst_0
    //   120: istore #10
    //   122: aload #9
    //   124: invokevirtual get : ()Ljava/lang/Object;
    //   127: aload_0
    //   128: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   131: ifeq -> 139
    //   134: iload #6
    //   136: goto -> 146
    //   139: iinc #6, 1
    //   142: goto -> 93
    //   145: iconst_m1
    //   146: istore_3
    //   147: iload_3
    //   148: iconst_m1
    //   149: if_icmpeq -> 156
    //   152: iconst_1
    //   153: goto -> 157
    //   156: iconst_0
    //   157: ifne -> 178
    //   160: ldc_w 'Check failed.'
    //   163: astore #5
    //   165: new java/lang/IllegalStateException
    //   168: dup
    //   169: aload #5
    //   171: invokevirtual toString : ()Ljava/lang/String;
    //   174: invokespecial <init> : (Ljava/lang/String;)V
    //   177: athrow
    //   178: aload_2
    //   179: iload_3
    //   180: invokeinterface remove : (I)Ljava/lang/Object;
    //   185: pop
    //   186: aload_0
    //   187: aconst_null
    //   188: putfield connection : Lokhttp3/internal/connection/RealConnection;
    //   191: aload_2
    //   192: invokeinterface isEmpty : ()Z
    //   197: ifeq -> 223
    //   200: aload_1
    //   201: invokestatic nanoTime : ()J
    //   204: invokevirtual setIdleAtNs$okhttp : (J)V
    //   207: aload_0
    //   208: getfield connectionPool : Lokhttp3/internal/connection/RealConnectionPool;
    //   211: aload_1
    //   212: invokevirtual connectionBecameIdle : (Lokhttp3/internal/connection/RealConnection;)Z
    //   215: ifeq -> 223
    //   218: aload_1
    //   219: invokevirtual socket : ()Ljava/net/Socket;
    //   222: areturn
    //   223: aconst_null
    //   224: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #374	-> 0
    //   #375	-> 9
    //   #565	-> 13
    //   #566	-> 26
    //   #568	-> 69
    //   #377	-> 70
    //   #378	-> 75
    //   #569	-> 81
    //   #570	-> 84
    //   #571	-> 112
    //   #378	-> 122
    //   #571	-> 131
    //   #572	-> 134
    //   #573	-> 139
    //   #575	-> 145
    //   #378	-> 146
    //   #379	-> 147
    //   #381	-> 178
    //   #382	-> 186
    //   #384	-> 191
    //   #385	-> 200
    //   #386	-> 207
    //   #387	-> 218
    //   #391	-> 223
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   13	57	3	$i$f$assertThreadHoldsLock	I
    //   11	59	2	$this$assertThreadHoldsLock$iv	Ljava/lang/Object;
    //   122	9	10	$i$a$-indexOfFirst-RealCall$releaseConnectionNoEvents$index$1	I
    //   119	12	9	it	Ljava/lang/ref/Reference;
    //   112	30	8	item$iv	Ljava/lang/Object;
    //   81	65	5	$i$f$indexOfFirst	I
    //   84	62	6	index$iv	I
    //   78	68	4	$this$indexOfFirst$iv	Ljava/util/List;
    //   9	216	1	connection	Lokhttp3/internal/connection/RealConnection;
    //   75	150	2	calls	Ljava/util/List;
    //   147	78	3	index	I
    //   0	225	0	this	Lokhttp3/internal/connection/RealCall;
  }
  
  private final <E extends IOException> E timeoutExit(IOException cause) {
    if (this.timeoutEarlyExit)
      return (E)cause; 
    if (!this.timeout.exit())
      return (E)cause; 
    InterruptedIOException e = new InterruptedIOException("timeout");
    if (cause != null)
      e.initCause(cause); 
    return (E)e;
  }
  
  public final void timeoutEarlyExit() {
    if (!(!this.timeoutEarlyExit ? 1 : 0)) {
      String str = "Check failed.";
      throw new IllegalStateException(str.toString());
    } 
    this.timeoutEarlyExit = true;
    this.timeout.exit();
  }
  
  public final void exitNetworkInterceptorExchange$okhttp(boolean closeExchange) {
    synchronized (this) {
      int $i$a$-synchronized-RealCall$exitNetworkInterceptorExchange$1 = 0;
      if (!this.expectMoreExchanges) {
        int $i$a$-check-RealCall$exitNetworkInterceptorExchange$1$1 = 0;
        String str = "released";
        throw new IllegalStateException(str.toString());
      } 
      Unit unit = Unit.INSTANCE;
    } 
    if (closeExchange)
      if (this.exchange != null) {
        this.exchange.detachWithViolence();
      } else {
      
      }  
    this.interceptorScopedExchange = null;
  }
  
  private final Address createAddress(HttpUrl url) {
    SSLSocketFactory sslSocketFactory = null;
    HostnameVerifier hostnameVerifier = null;
    CertificatePinner certificatePinner = null;
    if (url.isHttps()) {
      sslSocketFactory = this.client.sslSocketFactory();
      hostnameVerifier = this.client.hostnameVerifier();
      certificatePinner = this.client.certificatePinner();
    } 
    return new Address(url.host(), url.port(), this.client.dns(), this.client.socketFactory(), sslSocketFactory, hostnameVerifier, certificatePinner, this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector());
  }
  
  public final boolean retryAfterFailure() {
    Intrinsics.checkNotNull(this.exchangeFinder);
    return this.exchangeFinder.retryAfterFailure();
  }
  
  private final String toLoggableString() {
    return (isCanceled() ? "canceled " : "") + (this.forWebSocket ? "web socket" : "call") + " to " + redactedUrl$okhttp();
  }
  
  @NotNull
  public final String redactedUrl$okhttp() {
    return this.originalRequest.url().redact();
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000>\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\b\n\002\030\002\n\002\b\005\n\002\020\016\n\002\b\003\n\002\030\002\n\002\b\005\b\004\030\0002\0020\001B\017\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\025\020\t\032\0020\b2\006\020\007\032\0020\006¢\006\004\b\t\020\nJ\031\020\r\032\0020\b2\n\020\f\032\0060\000R\0020\013¢\006\004\b\r\020\016J\017\020\017\032\0020\bH\026¢\006\004\b\017\020\020R\021\020\023\032\0020\0138F¢\006\006\032\004\b\021\020\022R$\020\026\032\0020\0242\006\020\025\032\0020\0248\006@BX\016¢\006\f\n\004\b\026\020\027\032\004\b\030\020\031R\021\020\035\032\0020\0328F¢\006\006\032\004\b\033\020\034R\021\020!\032\0020\0368F¢\006\006\032\004\b\037\020 R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\"¨\006#"}, d2 = {"Lokhttp3/internal/connection/RealCall$AsyncCall;", "Ljava/lang/Runnable;", "Lokhttp3/Callback;", "responseCallback", "<init>", "(Lokhttp3/internal/connection/RealCall;Lokhttp3/Callback;)V", "Ljava/util/concurrent/ExecutorService;", "executorService", "", "executeOn", "(Ljava/util/concurrent/ExecutorService;)V", "Lokhttp3/internal/connection/RealCall;", "other", "reuseCallsPerHostFrom", "(Lokhttp3/internal/connection/RealCall$AsyncCall;)V", "run", "()V", "getCall", "()Lokhttp3/internal/connection/RealCall;", "call", "Ljava/util/concurrent/atomic/AtomicInteger;", "<set-?>", "callsPerHost", "Ljava/util/concurrent/atomic/AtomicInteger;", "getCallsPerHost", "()Ljava/util/concurrent/atomic/AtomicInteger;", "", "getHost", "()Ljava/lang/String;", "host", "Lokhttp3/Request;", "getRequest", "()Lokhttp3/Request;", "request", "Lokhttp3/Callback;", "okhttp"})
  @SourceDebugExtension({"SMAP\nRealCall.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealCall.kt\nokhttp3/internal/connection/RealCall$AsyncCall\n+ 2 Util.kt\nokhttp3/internal/Util\n*L\n1#1,551:1\n615#2,4:552\n409#2,9:556\n*S KotlinDebug\n*F\n+ 1 RealCall.kt\nokhttp3/internal/connection/RealCall$AsyncCall\n*L\n494#1:552,4\n513#1:556,9\n*E\n"})
  public final class AsyncCall implements Runnable {
    @NotNull
    private final Callback responseCallback;
    
    @NotNull
    private volatile AtomicInteger callsPerHost;
    
    public AsyncCall(Callback responseCallback) {
      this.responseCallback = responseCallback;
      this.callsPerHost = new AtomicInteger(0);
    }
    
    @NotNull
    public final AtomicInteger getCallsPerHost() {
      return this.callsPerHost;
    }
    
    public final void reuseCallsPerHostFrom(@NotNull AsyncCall other) {
      Intrinsics.checkNotNullParameter(other, "other");
      this.callsPerHost = other.callsPerHost;
    }
    
    @NotNull
    public final String getHost() {
      return RealCall.this.getOriginalRequest().url().host();
    }
    
    @NotNull
    public final Request getRequest() {
      return RealCall.this.getOriginalRequest();
    }
    
    @NotNull
    public final RealCall getCall() {
      return RealCall.this;
    }
    
    public final void executeOn(@NotNull ExecutorService executorService) {
      Intrinsics.checkNotNullParameter(executorService, "executorService");
      Object $this$assertThreadDoesntHoldLock$iv = RealCall.this.getClient().dispatcher();
      int $i$f$assertThreadDoesntHoldLock = 0;
      if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); 
      boolean success = false;
      try {
        executorService.execute(this);
        success = true;
      } catch (RejectedExecutionException e) {
        InterruptedIOException ioException = new InterruptedIOException("executor rejected");
        ioException.initCause(e);
        RealCall.this.noMoreExchanges$okhttp(ioException);
        this.responseCallback.onFailure(RealCall.this, ioException);
      } finally {
        RealCall.this.getClient().dispatcher().finished$okhttp(this);
      } 
    }
    
    public void run() {
      String str1 = "OkHttp " + RealCall.this.redactedUrl$okhttp();
      RealCall realCall = RealCall.this;
      int $i$f$threadName = 0;
      Thread currentThread$iv = Thread.currentThread();
      String oldName$iv = currentThread$iv.getName();
      currentThread$iv.setName(str1);
      try {
        int $i$a$-threadName-RealCall$AsyncCall$run$1 = 0;
        boolean signalledCallback = false;
        realCall.timeout.enter();
        try {
          Response response = realCall.getResponseWithInterceptorChain$okhttp();
          signalledCallback = true;
          this.responseCallback.onResponse(realCall, response);
        } catch (IOException e) {
          if (signalledCallback) {
            Platform.Companion.get().log("Callback failure for " + realCall.toLoggableString(), 4, e);
          } else {
            this.responseCallback.onFailure(realCall, e);
          } 
        } catch (Throwable t) {
          realCall.cancel();
          if (!signalledCallback) {
            IOException canceledException = new IOException("canceled due to " + t);
            ExceptionsKt.addSuppressed(canceledException, t);
            this.responseCallback.onFailure(realCall, canceledException);
          } 
          throw t;
        } finally {
          realCall.getClient().dispatcher().finished$okhttp(this);
        } 
      } finally {
        currentThread$iv.setName(oldName$iv);
      } 
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\026\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\000\n\002\b\007\b\000\030\0002\b\022\004\022\0020\0020\001B\031\022\006\020\003\032\0020\002\022\b\020\005\032\004\030\0010\004¢\006\004\b\006\020\007R\031\020\005\032\004\030\0010\0048\006¢\006\f\n\004\b\005\020\b\032\004\b\t\020\n¨\006\013"}, d2 = {"Lokhttp3/internal/connection/RealCall$CallReference;", "Ljava/lang/ref/WeakReference;", "Lokhttp3/internal/connection/RealCall;", "referent", "", "callStackTrace", "<init>", "(Lokhttp3/internal/connection/RealCall;Ljava/lang/Object;)V", "Ljava/lang/Object;", "getCallStackTrace", "()Ljava/lang/Object;", "okhttp"})
  public static final class CallReference extends WeakReference<RealCall> {
    @Nullable
    private final Object callStackTrace;
    
    @Nullable
    public final Object getCallStackTrace() {
      return this.callStackTrace;
    }
    
    public CallReference(@NotNull RealCall referent, @Nullable Object callStackTrace) {
      super(referent);
      this.callStackTrace = callStackTrace;
    }
  }
}
