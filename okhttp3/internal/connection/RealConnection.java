package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownServiceException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.ConnectionSpec;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http1.Http1ExchangeCodec;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2ExchangeCodec;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.http2.Settings;
import okhttp3.internal.http2.StreamResetException;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000ð\001\n\002\030\002\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\005\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\n\n\002\030\002\n\000\n\002\020 \n\002\b\007\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\002\b\007\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\006\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\006\n\002\020!\n\002\030\002\n\002\b\n\n\002\020\t\n\002\b\026\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\030\000  \0012\0020\0012\0020\002:\002 \001B\027\022\006\020\004\032\0020\003\022\006\020\006\032\0020\005¢\006\004\b\007\020\bJ\r\020\n\032\0020\t¢\006\004\b\n\020\013J\037\020\021\032\0020\0202\006\020\r\032\0020\f2\006\020\017\032\0020\016H\002¢\006\004\b\021\020\022JE\020\035\032\0020\t2\006\020\024\032\0020\0232\006\020\025\032\0020\0232\006\020\026\032\0020\0232\006\020\027\032\0020\0232\006\020\030\032\0020\0202\006\020\032\032\0020\0312\006\020\034\032\0020\033¢\006\004\b\035\020\036J'\020&\032\0020\t2\006\020 \032\0020\0372\006\020!\032\0020\0052\006\020#\032\0020\"H\000¢\006\004\b$\020%J/\020'\032\0020\t2\006\020\024\032\0020\0232\006\020\025\032\0020\0232\006\020\032\032\0020\0312\006\020\034\032\0020\033H\002¢\006\004\b'\020(J\027\020+\032\0020\t2\006\020*\032\0020)H\002¢\006\004\b+\020,J7\020-\032\0020\t2\006\020\024\032\0020\0232\006\020\025\032\0020\0232\006\020\026\032\0020\0232\006\020\032\032\0020\0312\006\020\034\032\0020\033H\002¢\006\004\b-\020.J1\0201\032\004\030\0010/2\006\020\025\032\0020\0232\006\020\026\032\0020\0232\006\0200\032\0020/2\006\020\r\032\0020\fH\002¢\006\004\b1\0202J\017\0203\032\0020/H\002¢\006\004\b3\0204J/\0205\032\0020\t2\006\020*\032\0020)2\006\020\027\032\0020\0232\006\020\032\032\0020\0312\006\020\034\032\0020\033H\002¢\006\004\b5\0206J\021\020\017\032\004\030\0010\016H\026¢\006\004\b\017\0207J\017\0209\032\0020\tH\000¢\006\004\b8\020\013J'\020@\032\0020\0202\006\020;\032\0020:2\016\020=\032\n\022\004\022\0020\005\030\0010<H\000¢\006\004\b>\020?J\025\020B\032\0020\0202\006\020A\032\0020\020¢\006\004\bB\020CJ\037\020I\032\0020F2\006\020 \032\0020\0372\006\020E\032\0020DH\000¢\006\004\bG\020HJ\027\020O\032\0020L2\006\020K\032\0020JH\000¢\006\004\bM\020NJ\017\020Q\032\0020\tH\000¢\006\004\bP\020\013J\017\020S\032\0020\tH\000¢\006\004\bR\020\013J\037\020X\032\0020\t2\006\020U\032\0020T2\006\020W\032\0020VH\026¢\006\004\bX\020YJ\027\020\\\032\0020\t2\006\020[\032\0020ZH\026¢\006\004\b\\\020]J\017\020_\032\0020^H\026¢\006\004\b_\020`J\017\020\006\032\0020\005H\026¢\006\004\b\006\020aJ\035\020c\032\0020\0202\f\020b\032\b\022\004\022\0020\0050<H\002¢\006\004\bc\020dJ\017\020f\032\0020eH\026¢\006\004\bf\020gJ\027\020h\032\0020\t2\006\020\027\032\0020\023H\002¢\006\004\bh\020iJ\027\020j\032\0020\0202\006\020\r\032\0020\fH\002¢\006\004\bj\020kJ\017\020m\032\0020lH\026¢\006\004\bm\020nJ!\020s\032\0020\t2\006\020\032\032\0020o2\b\020p\032\004\030\0010\"H\000¢\006\004\bq\020rR\026\020t\032\0020\0238\002@\002X\016¢\006\006\n\004\bt\020uR#\020x\032\016\022\n\022\b\022\004\022\0020o0w0v8\006¢\006\f\n\004\bx\020y\032\004\bz\020{R\027\020\004\032\0020\0038\006¢\006\f\n\004\b\004\020|\032\004\b}\020~R\030\020\017\032\004\030\0010\0168\002@\002X\016¢\006\006\n\004\b\017\020R\033\020\001\032\004\030\0010T8\002@\002X\016¢\006\b\n\006\b\001\020\001R*\020\001\032\0030\0018\000@\000X\016¢\006\030\n\006\b\001\020\001\032\006\b\001\020\001\"\006\b\001\020\001R\027\020\001\032\0020\0208@X\004¢\006\b\032\006\b\001\020\001R\027\020Q\032\0020\0208\002@\002X\016¢\006\007\n\005\bQ\020\001R'\020S\032\0020\0208\006@\006X\016¢\006\027\n\005\bS\020\001\032\006\b\001\020\001\"\006\b\001\020\001R\031\020_\032\004\030\0010^8\002@\002X\016¢\006\007\n\005\b_\020\001R\033\020\001\032\004\030\0010e8\002@\002X\016¢\006\b\n\006\b\001\020\001R\030\020\001\032\0020\0238\002@\002X\016¢\006\007\n\005\b\001\020uR\025\020\006\032\0020\0058\002X\004¢\006\007\n\005\b\006\020\001R'\020\001\032\0020\0238\000@\000X\016¢\006\026\n\005\b\001\020u\032\006\b\001\020\001\"\005\b\001\020iR\034\020\001\032\005\030\0010\0018\002@\002X\016¢\006\b\n\006\b\001\020\001R\031\020f\032\004\030\0010e8\002@\002X\016¢\006\007\n\005\bf\020\001R\034\020\001\032\005\030\0010\0018\002@\002X\016¢\006\b\n\006\b\001\020\001R\030\020\001\032\0020\0238\002@\002X\016¢\006\007\n\005\b\001\020u¨\006¡\001"}, d2 = {"Lokhttp3/internal/connection/RealConnection;", "Lokhttp3/internal/http2/Http2Connection$Listener;", "Lokhttp3/Connection;", "Lokhttp3/internal/connection/RealConnectionPool;", "connectionPool", "Lokhttp3/Route;", "route", "<init>", "(Lokhttp3/internal/connection/RealConnectionPool;Lokhttp3/Route;)V", "", "cancel", "()V", "Lokhttp3/HttpUrl;", "url", "Lokhttp3/Handshake;", "handshake", "", "certificateSupportHost", "(Lokhttp3/HttpUrl;Lokhttp3/Handshake;)Z", "", "connectTimeout", "readTimeout", "writeTimeout", "pingIntervalMillis", "connectionRetryEnabled", "Lokhttp3/Call;", "call", "Lokhttp3/EventListener;", "eventListener", "connect", "(IIIIZLokhttp3/Call;Lokhttp3/EventListener;)V", "Lokhttp3/OkHttpClient;", "client", "failedRoute", "Ljava/io/IOException;", "failure", "connectFailed$okhttp", "(Lokhttp3/OkHttpClient;Lokhttp3/Route;Ljava/io/IOException;)V", "connectFailed", "connectSocket", "(IILokhttp3/Call;Lokhttp3/EventListener;)V", "Lokhttp3/internal/connection/ConnectionSpecSelector;", "connectionSpecSelector", "connectTls", "(Lokhttp3/internal/connection/ConnectionSpecSelector;)V", "connectTunnel", "(IIILokhttp3/Call;Lokhttp3/EventListener;)V", "Lokhttp3/Request;", "tunnelRequest", "createTunnel", "(IILokhttp3/Request;Lokhttp3/HttpUrl;)Lokhttp3/Request;", "createTunnelRequest", "()Lokhttp3/Request;", "establishProtocol", "(Lokhttp3/internal/connection/ConnectionSpecSelector;ILokhttp3/Call;Lokhttp3/EventListener;)V", "()Lokhttp3/Handshake;", "incrementSuccessCount$okhttp", "incrementSuccessCount", "Lokhttp3/Address;", "address", "", "routes", "isEligible$okhttp", "(Lokhttp3/Address;Ljava/util/List;)Z", "isEligible", "doExtensiveChecks", "isHealthy", "(Z)Z", "Lokhttp3/internal/http/RealInterceptorChain;", "chain", "Lokhttp3/internal/http/ExchangeCodec;", "newCodec$okhttp", "(Lokhttp3/OkHttpClient;Lokhttp3/internal/http/RealInterceptorChain;)Lokhttp3/internal/http/ExchangeCodec;", "newCodec", "Lokhttp3/internal/connection/Exchange;", "exchange", "Lokhttp3/internal/ws/RealWebSocket$Streams;", "newWebSocketStreams$okhttp", "(Lokhttp3/internal/connection/Exchange;)Lokhttp3/internal/ws/RealWebSocket$Streams;", "newWebSocketStreams", "noCoalescedConnections$okhttp", "noCoalescedConnections", "noNewExchanges$okhttp", "noNewExchanges", "Lokhttp3/internal/http2/Http2Connection;", "connection", "Lokhttp3/internal/http2/Settings;", "settings", "onSettings", "(Lokhttp3/internal/http2/Http2Connection;Lokhttp3/internal/http2/Settings;)V", "Lokhttp3/internal/http2/Http2Stream;", "stream", "onStream", "(Lokhttp3/internal/http2/Http2Stream;)V", "Lokhttp3/Protocol;", "protocol", "()Lokhttp3/Protocol;", "()Lokhttp3/Route;", "candidates", "routeMatchesAny", "(Ljava/util/List;)Z", "Ljava/net/Socket;", "socket", "()Ljava/net/Socket;", "startHttp2", "(I)V", "supportsUrl", "(Lokhttp3/HttpUrl;)Z", "", "toString", "()Ljava/lang/String;", "Lokhttp3/internal/connection/RealCall;", "e", "trackFailure$okhttp", "(Lokhttp3/internal/connection/RealCall;Ljava/io/IOException;)V", "trackFailure", "allocationLimit", "I", "", "Ljava/lang/ref/Reference;", "calls", "Ljava/util/List;", "getCalls", "()Ljava/util/List;", "Lokhttp3/internal/connection/RealConnectionPool;", "getConnectionPool", "()Lokhttp3/internal/connection/RealConnectionPool;", "Lokhttp3/Handshake;", "http2Connection", "Lokhttp3/internal/http2/Http2Connection;", "", "idleAtNs", "J", "getIdleAtNs$okhttp", "()J", "setIdleAtNs$okhttp", "(J)V", "isMultiplexed$okhttp", "()Z", "isMultiplexed", "Z", "getNoNewExchanges", "setNoNewExchanges", "(Z)V", "Lokhttp3/Protocol;", "rawSocket", "Ljava/net/Socket;", "refusedStreamCount", "Lokhttp3/Route;", "routeFailureCount", "getRouteFailureCount$okhttp", "()I", "setRouteFailureCount$okhttp", "Lokio/BufferedSink;", "sink", "Lokio/BufferedSink;", "Lokio/BufferedSource;", "source", "Lokio/BufferedSource;", "successCount", "Companion", "okhttp"})
@SourceDebugExtension({"SMAP\nRealConnection.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealConnection.kt\nokhttp3/internal/connection/RealConnection\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Util.kt\nokhttp3/internal/Util\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,765:1\n1#2:766\n608#3,4:767\n608#3,4:774\n615#3,4:778\n1747#4,3:771\n*S KotlinDebug\n*F\n+ 1 RealConnection.kt\nokhttp3/internal/connection/RealConnection\n*L\n529#1:767,4\n582#1:774,4\n648#1:778,4\n574#1:771,3\n*E\n"})
public final class RealConnection extends Http2Connection.Listener implements Connection {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final RealConnectionPool connectionPool;
  
  @NotNull
  private final Route route;
  
  @Nullable
  private Socket rawSocket;
  
  @Nullable
  private Socket socket;
  
  @Nullable
  private Handshake handshake;
  
  @Nullable
  private Protocol protocol;
  
  @Nullable
  private Http2Connection http2Connection;
  
  @Nullable
  private BufferedSource source;
  
  @Nullable
  private BufferedSink sink;
  
  private boolean noNewExchanges;
  
  private boolean noCoalescedConnections;
  
  private int routeFailureCount;
  
  private int successCount;
  
  private int refusedStreamCount;
  
  private int allocationLimit;
  
  @NotNull
  private final List<Reference<RealCall>> calls;
  
  private long idleAtNs;
  
  @NotNull
  private static final String NPE_THROW_WITH_NULL = "throw with null exception";
  
  private static final int MAX_TUNNEL_ATTEMPTS = 21;
  
  public static final long IDLE_CONNECTION_HEALTHY_NS = 10000000000L;
  
  @NotNull
  public final RealConnectionPool getConnectionPool() {
    return this.connectionPool;
  }
  
  public RealConnection(@NotNull RealConnectionPool connectionPool, @NotNull Route route) {
    this.connectionPool = connectionPool;
    this.route = route;
    this.allocationLimit = 1;
    this.calls = new ArrayList<>();
    this.idleAtNs = Long.MAX_VALUE;
  }
  
  public final boolean getNoNewExchanges() {
    return this.noNewExchanges;
  }
  
  public final void setNoNewExchanges(boolean <set-?>) {
    this.noNewExchanges = <set-?>;
  }
  
  public final int getRouteFailureCount$okhttp() {
    return this.routeFailureCount;
  }
  
  public final void setRouteFailureCount$okhttp(int <set-?>) {
    this.routeFailureCount = <set-?>;
  }
  
  @NotNull
  public final List<Reference<RealCall>> getCalls() {
    return this.calls;
  }
  
  public final long getIdleAtNs$okhttp() {
    return this.idleAtNs;
  }
  
  public final void setIdleAtNs$okhttp(long <set-?>) {
    this.idleAtNs = <set-?>;
  }
  
  public final boolean isMultiplexed$okhttp() {
    return (this.http2Connection != null);
  }
  
  public final synchronized void noNewExchanges$okhttp() {
    this.noNewExchanges = true;
  }
  
  public final synchronized void noCoalescedConnections$okhttp() {
    this.noCoalescedConnections = true;
  }
  
  public final synchronized void incrementSuccessCount$okhttp() {
    int i = this.successCount;
    this.successCount = i + 1;
  }
  
  public final void connect(int connectTimeout, int readTimeout, int writeTimeout, int pingIntervalMillis, boolean connectionRetryEnabled, @NotNull Call call, @NotNull EventListener eventListener) {
    Intrinsics.checkNotNullParameter(call, "call");
    Intrinsics.checkNotNullParameter(eventListener, "eventListener");
    if (!((this.protocol == null) ? 1 : 0)) {
      int $i$a$-check-RealConnection$connect$1 = 0;
      String str = "already connected";
      throw new IllegalStateException(str.toString());
    } 
    RouteException routeException = null;
    List<ConnectionSpec> connectionSpecs = this.route.address().connectionSpecs();
    ConnectionSpecSelector connectionSpecSelector = new ConnectionSpecSelector(connectionSpecs);
    if (this.route.address().sslSocketFactory() == null) {
      if (!connectionSpecs.contains(ConnectionSpec.CLEARTEXT))
        throw new RouteException(new UnknownServiceException("CLEARTEXT communication not enabled for client")); 
      String host = this.route.address().url().host();
      if (!Platform.Companion.get().isCleartextTrafficPermitted(host))
        throw new RouteException(new UnknownServiceException("CLEARTEXT communication to " + host + " not permitted by network security policy")); 
    } else if (this.route.address().protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
      throw new RouteException(new UnknownServiceException("H2_PRIOR_KNOWLEDGE cannot be used with HTTPS"));
    } 
    while (true) {
      try {
        if (this.route.requiresTunnel()) {
          connectTunnel(connectTimeout, readTimeout, writeTimeout, call, eventListener);
          if (this.rawSocket == null)
            break; 
        } else {
          connectSocket(connectTimeout, readTimeout, call, eventListener);
        } 
        establishProtocol(connectionSpecSelector, pingIntervalMillis, call, eventListener);
        eventListener.connectEnd(call, this.route.socketAddress(), this.route.proxy(), this.protocol);
        break;
      } catch (IOException e) {
        if (this.socket != null) {
          Util.closeQuietly(this.socket);
        } else {
        
        } 
        if (this.rawSocket != null) {
          Util.closeQuietly(this.rawSocket);
        } else {
        
        } 
        this.socket = null;
        this.rawSocket = null;
        this.source = null;
        this.sink = null;
        this.handshake = null;
        this.protocol = null;
        this.http2Connection = null;
        this.allocationLimit = 1;
        eventListener.connectFailed(call, this.route.socketAddress(), this.route.proxy(), null, e);
        if (routeException == null) {
          routeException = new RouteException(e);
        } else {
          routeException.addConnectException(e);
        } 
        if (!connectionRetryEnabled || !connectionSpecSelector.connectionFailed(e))
          throw routeException; 
      } 
    } 
    if (this.route.requiresTunnel() && this.rawSocket == null)
      throw new RouteException(new ProtocolException("Too many tunnel connections attempted: 21")); 
    this.idleAtNs = System.nanoTime();
  }
  
  private final void connectTunnel(int connectTimeout, int readTimeout, int writeTimeout, Call call, EventListener eventListener) throws IOException {
    Request tunnelRequest = createTunnelRequest();
    HttpUrl url = tunnelRequest.url();
    for (int i = 0; i < 21; i++) {
      connectSocket(connectTimeout, readTimeout, call, eventListener);
      if (createTunnel(readTimeout, writeTimeout, tunnelRequest, url) == null) {
        createTunnel(readTimeout, writeTimeout, tunnelRequest, url);
        break;
      } 
      if (this.rawSocket != null) {
        Util.closeQuietly(this.rawSocket);
      } else {
      
      } 
      this.rawSocket = null;
      this.sink = null;
      this.source = null;
      eventListener.connectEnd(call, this.route.socketAddress(), this.route.proxy(), null);
    } 
  }
  
  private final void connectSocket(int connectTimeout, int readTimeout, Call call, EventListener eventListener) throws IOException {
    Proxy proxy = this.route.proxy();
    Address address = this.route.address();
    proxy.type();
    switch ((proxy.type() == null) ? -1 : WhenMappings.$EnumSwitchMapping$0[proxy.type().ordinal()]) {
      case true:
      case true:
        Intrinsics.checkNotNull(address.socketFactory().createSocket());
      default:
        break;
    } 
    Socket rawSocket = new Socket(proxy);
    this.rawSocket = rawSocket;
    eventListener.connectStart(call, this.route.socketAddress(), proxy);
    rawSocket.setSoTimeout(readTimeout);
    try {
      Platform.Companion.get().connectSocket(rawSocket, this.route.socketAddress(), connectTimeout);
    } catch (ConnectException e) {
      ConnectException connectException1 = new ConnectException("Failed to connect to " + this.route.socketAddress()), $this$connectSocket_u24lambda_u241 = connectException1;
      int $i$a$-apply-RealConnection$connectSocket$1 = 0;
      $this$connectSocket_u24lambda_u241.initCause(e);
      throw (Throwable)connectException1;
    } 
    try {
      this.source = Okio.buffer(Okio.source(rawSocket));
      this.sink = Okio.buffer(Okio.sink(rawSocket));
    } catch (NullPointerException npe) {
      if (Intrinsics.areEqual(npe.getMessage(), "throw with null exception"))
        throw new IOException((Throwable)npe); 
    } 
  }
  
  private final void establishProtocol(ConnectionSpecSelector connectionSpecSelector, int pingIntervalMillis, Call call, EventListener eventListener) throws IOException {
    if (this.route.address().sslSocketFactory() == null) {
      if (this.route.address().protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
        this.socket = this.rawSocket;
        this.protocol = Protocol.H2_PRIOR_KNOWLEDGE;
        startHttp2(pingIntervalMillis);
        return;
      } 
      this.socket = this.rawSocket;
      this.protocol = Protocol.HTTP_1_1;
      return;
    } 
    eventListener.secureConnectStart(call);
    connectTls(connectionSpecSelector);
    eventListener.secureConnectEnd(call, this.handshake);
    if (this.protocol == Protocol.HTTP_2)
      startHttp2(pingIntervalMillis); 
  }
  
  private final void startHttp2(int pingIntervalMillis) throws IOException {
    Intrinsics.checkNotNull(this.socket);
    Socket socket = this.socket;
    Intrinsics.checkNotNull(this.source);
    BufferedSource source = this.source;
    Intrinsics.checkNotNull(this.sink);
    BufferedSink sink = this.sink;
    socket.setSoTimeout(0);
    Http2Connection http2Connection = (new Http2Connection.Builder(true, TaskRunner.INSTANCE)).socket(socket, this.route.address().url().host(), source, sink).listener(this).pingIntervalMillis(pingIntervalMillis).build();
    this.http2Connection = http2Connection;
    this.allocationLimit = Http2Connection.Companion.getDEFAULT_SETTINGS().getMaxConcurrentStreams();
    Http2Connection.start$default(http2Connection, false, null, 3, null);
  }
  
  private final void connectTls(ConnectionSpecSelector connectionSpecSelector) throws IOException {
    Address address = this.route.address();
    SSLSocketFactory sslSocketFactory = address.sslSocketFactory();
    boolean success = false;
    SSLSocket sslSocket = null;
    try {
      Intrinsics.checkNotNull(sslSocketFactory);
      Intrinsics.checkNotNull(sslSocketFactory.createSocket(this.rawSocket, address.url().host(), address.url().port(), true), "null cannot be cast to non-null type javax.net.ssl.SSLSocket");
      sslSocket = (SSLSocket)sslSocketFactory.createSocket(this.rawSocket, address.url().host(), address.url().port(), true);
      ConnectionSpec connectionSpec = connectionSpecSelector.configureSecureSocket(sslSocket);
      if (connectionSpec.supportsTlsExtensions())
        Platform.Companion.get().configureTlsExtensions(sslSocket, address.url().host(), address.protocols()); 
      sslSocket.startHandshake();
      SSLSession sslSocketSession = sslSocket.getSession();
      Intrinsics.checkNotNullExpressionValue(sslSocketSession, "sslSocketSession");
      Handshake unverifiedHandshake = Handshake.Companion.get(sslSocketSession);
      Intrinsics.checkNotNull(address.hostnameVerifier());
      if (!address.hostnameVerifier().verify(address.url().host(), sslSocketSession)) {
        List peerCertificates = unverifiedHandshake.peerCertificates();
        if (!peerCertificates.isEmpty()) {
          Intrinsics.checkNotNull(peerCertificates.get(0), "null cannot be cast to non-null type java.security.cert.X509Certificate");
          X509Certificate cert = (X509Certificate)peerCertificates.get(0);
          throw new SSLPeerUnverifiedException(StringsKt.trimMargin$default("\n              |Hostname " + address.url().host() + " not verified:\n              |    certificate: " + CertificatePinner.Companion.pin(cert) + "\n              |    DN: " + cert.getSubjectDN().getName() + "\n              |    subjectAltNames: " + OkHostnameVerifier.INSTANCE.allSubjectAltNames(cert) + "\n              ", null, 1, null));
        } 
        throw new SSLPeerUnverifiedException("Hostname " + address.url().host() + " not verified (no certificates)");
      } 
      Intrinsics.checkNotNull(address.certificatePinner());
      CertificatePinner certificatePinner = address.certificatePinner();
      this.handshake = new Handshake(unverifiedHandshake.tlsVersion(), unverifiedHandshake.cipherSuite(), unverifiedHandshake.localCertificates(), new RealConnection$connectTls$1(certificatePinner, unverifiedHandshake, address));
      certificatePinner.check$okhttp(address.url().host(), new RealConnection$connectTls$2());
      String maybeProtocol = connectionSpec.supportsTlsExtensions() ? Platform.Companion.get().getSelectedProtocol(sslSocket) : null;
      this.socket = sslSocket;
      this.source = Okio.buffer(Okio.source(sslSocket));
      this.sink = Okio.buffer(Okio.sink(sslSocket));
      this.protocol = (maybeProtocol != null) ? Protocol.Companion.get(maybeProtocol) : Protocol.HTTP_1_1;
    } finally {
      if (sslSocket != null)
        Platform.Companion.get().afterHandshake(sslSocket); 
      if (sslSocket != null) {
        Util.closeQuietly(sslSocket);
      } else {
      
      } 
    } 
  }
  
  @Metadata(mv = {1, 8, 0}, k = 3, xi = 48, d1 = {"\000\f\n\002\020 \n\002\030\002\n\002\b\003\020\004\032\b\022\004\022\0020\0010\000H\n¢\006\004\b\002\020\003"}, d2 = {"", "Ljava/security/cert/Certificate;", "invoke", "()Ljava/util/List;", "<anonymous>"})
  static final class RealConnection$connectTls$1 extends Lambda implements Function0<List<? extends Certificate>> {
    @NotNull
    public final List<Certificate> invoke() {
      Intrinsics.checkNotNull(this.$certificatePinner.getCertificateChainCleaner$okhttp());
      return this.$certificatePinner.getCertificateChainCleaner$okhttp().clean(this.$unverifiedHandshake.peerCertificates(), this.$address.url().host());
    }
    
    RealConnection$connectTls$1(CertificatePinner $certificatePinner, Handshake $unverifiedHandshake, Address $address) {
      super(0);
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 3, xi = 48, d1 = {"\000\f\n\002\020 \n\002\030\002\n\002\b\003\020\004\032\b\022\004\022\0020\0010\000H\n¢\006\004\b\002\020\003"}, d2 = {"", "Ljava/security/cert/X509Certificate;", "invoke", "()Ljava/util/List;", "<anonymous>"})
  @SourceDebugExtension({"SMAP\nRealConnection.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealConnection.kt\nokhttp3/internal/connection/RealConnection$connectTls$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,765:1\n1549#2:766\n1620#2,3:767\n*S KotlinDebug\n*F\n+ 1 RealConnection.kt\nokhttp3/internal/connection/RealConnection$connectTls$2\n*L\n411#1:766\n411#1:767,3\n*E\n"})
  static final class RealConnection$connectTls$2 extends Lambda implements Function0<List<? extends X509Certificate>> {
    @NotNull
    public final List<X509Certificate> invoke() {
      Intrinsics.checkNotNull(RealConnection.this.handshake);
      Iterable $this$map$iv = RealConnection.this.handshake.peerCertificates();
      int $i$f$map = 0;
      Iterable iterable1 = $this$map$iv;
      Collection<X509Certificate> destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
      int $i$f$mapTo = 0;
      for (Object item$iv$iv : iterable1) {
        Certificate certificate = (Certificate)item$iv$iv;
        Collection<X509Certificate> collection = destination$iv$iv;
        int $i$a$-map-RealConnection$connectTls$2$1 = 0;
        Intrinsics.checkNotNull(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
        collection.add((X509Certificate)certificate);
      } 
      return (List<X509Certificate>)destination$iv$iv;
    }
    
    RealConnection$connectTls$2() {
      super(0);
    }
  }
  
  private final Request createTunnel(int readTimeout, int writeTimeout, Request tunnelRequest, HttpUrl url) throws IOException {
    Response response;
    Request nextRequest = tunnelRequest;
    String requestLine = "CONNECT " + Util.toHostHeader(url, true) + " HTTP/1.1";
    while (true) {
      Intrinsics.checkNotNull(this.source);
      BufferedSource source = this.source;
      Intrinsics.checkNotNull(this.sink);
      BufferedSink sink = this.sink;
      Http1ExchangeCodec tunnelCodec = new Http1ExchangeCodec(null, this, source, sink);
      source.timeout().timeout(readTimeout, TimeUnit.MILLISECONDS);
      sink.timeout().timeout(writeTimeout, TimeUnit.MILLISECONDS);
      tunnelCodec.writeRequest(nextRequest.headers(), requestLine);
      tunnelCodec.finishRequest();
      Intrinsics.checkNotNull(tunnelCodec.readResponseHeaders(false));
      response = tunnelCodec.readResponseHeaders(false).request(nextRequest).build();
      tunnelCodec.skipConnectBody(response);
      switch (response.code()) {
        case 200:
          if (!source.getBuffer().exhausted() || !sink.getBuffer().exhausted())
            throw new IOException("TLS tunnel buffered too many bytes!"); 
          return null;
        case 407:
          if (this.route.address().proxyAuthenticator().authenticate(this.route, response) == null) {
            this.route.address().proxyAuthenticator().authenticate(this.route, response);
            throw new IOException("Failed to authenticate with proxy");
          } 
          if (StringsKt.equals("close", Response.header$default(response, "Connection", null, 2, null), true))
            return nextRequest; 
          continue;
      } 
      break;
    } 
    throw new IOException("Unexpected response code for CONNECT: " + response.code());
  }
  
  private final Request createTunnelRequest() throws IOException {
    Request proxyConnectRequest = (new Request.Builder()).url(this.route.address().url()).method("CONNECT", null).header("Host", Util.toHostHeader(this.route.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header("User-Agent", "okhttp/4.12.0").build();
    Response fakeAuthChallengeResponse = (new Response.Builder()).request(proxyConnectRequest).protocol(Protocol.HTTP_1_1).code(407).message("Preemptive Authenticate").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1L).receivedResponseAtMillis(-1L).header("Proxy-Authenticate", "OkHttp-Preemptive").build();
    Request authenticatedRequest = this.route.address().proxyAuthenticator().authenticate(this.route, fakeAuthChallengeResponse);
    if (authenticatedRequest == null);
    return proxyConnectRequest;
  }
  
  public final boolean isEligible$okhttp(@NotNull Address address, @Nullable List<Route> routes) {
    Intrinsics.checkNotNullParameter(address, "address");
    Object $this$assertThreadHoldsLock$iv = this;
    int $i$f$assertThreadHoldsLock = 0;
    if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv); 
    if (this.calls.size() >= this.allocationLimit || this.noNewExchanges)
      return false; 
    if (!this.route.address().equalsNonHost$okhttp(address))
      return false; 
    if (Intrinsics.areEqual(address.url().host(), route().address().url().host()))
      return true; 
    if (this.http2Connection == null)
      return false; 
    if (routes == null || !routeMatchesAny(routes))
      return false; 
    if (address.hostnameVerifier() != OkHostnameVerifier.INSTANCE)
      return false; 
    if (!supportsUrl(address.url()))
      return false; 
    try {
      Intrinsics.checkNotNull(address.certificatePinner());
      Intrinsics.checkNotNull(handshake());
      address.certificatePinner().check(address.url().host(), handshake().peerCertificates());
    } catch (SSLPeerUnverifiedException _) {
      return false;
    } 
    return true;
  }
  
  private final boolean routeMatchesAny(List candidates) {
    // Byte code:
    //   0: aload_1
    //   1: checkcast java/lang/Iterable
    //   4: astore_2
    //   5: iconst_0
    //   6: istore_3
    //   7: aload_2
    //   8: instanceof java/util/Collection
    //   11: ifeq -> 30
    //   14: aload_2
    //   15: checkcast java/util/Collection
    //   18: invokeinterface isEmpty : ()Z
    //   23: ifeq -> 30
    //   26: iconst_0
    //   27: goto -> 129
    //   30: aload_2
    //   31: invokeinterface iterator : ()Ljava/util/Iterator;
    //   36: astore #4
    //   38: aload #4
    //   40: invokeinterface hasNext : ()Z
    //   45: ifeq -> 128
    //   48: aload #4
    //   50: invokeinterface next : ()Ljava/lang/Object;
    //   55: astore #5
    //   57: aload #5
    //   59: checkcast okhttp3/Route
    //   62: astore #6
    //   64: iconst_0
    //   65: istore #7
    //   67: aload #6
    //   69: invokevirtual proxy : ()Ljava/net/Proxy;
    //   72: invokevirtual type : ()Ljava/net/Proxy$Type;
    //   75: getstatic java/net/Proxy$Type.DIRECT : Ljava/net/Proxy$Type;
    //   78: if_acmpne -> 119
    //   81: aload_0
    //   82: getfield route : Lokhttp3/Route;
    //   85: invokevirtual proxy : ()Ljava/net/Proxy;
    //   88: invokevirtual type : ()Ljava/net/Proxy$Type;
    //   91: getstatic java/net/Proxy$Type.DIRECT : Ljava/net/Proxy$Type;
    //   94: if_acmpne -> 119
    //   97: aload_0
    //   98: getfield route : Lokhttp3/Route;
    //   101: invokevirtual socketAddress : ()Ljava/net/InetSocketAddress;
    //   104: aload #6
    //   106: invokevirtual socketAddress : ()Ljava/net/InetSocketAddress;
    //   109: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   112: ifeq -> 119
    //   115: iconst_1
    //   116: goto -> 120
    //   119: iconst_0
    //   120: nop
    //   121: ifeq -> 38
    //   124: iconst_1
    //   125: goto -> 129
    //   128: iconst_0
    //   129: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #574	-> 0
    //   #771	-> 7
    //   #772	-> 30
    //   #575	-> 67
    //   #576	-> 81
    //   #577	-> 97
    //   #575	-> 120
    //   #772	-> 121
    //   #773	-> 128
    //   #574	-> 129
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   67	54	7	$i$a$-any-RealConnection$routeMatchesAny$1	I
    //   64	57	6	it	Lokhttp3/Route;
    //   57	71	5	element$iv	Ljava/lang/Object;
    //   7	122	3	$i$f$any	I
    //   5	124	2	$this$any$iv	Ljava/lang/Iterable;
    //   0	130	0	this	Lokhttp3/internal/connection/RealConnection;
    //   0	130	1	candidates	Ljava/util/List;
  }
  
  private final boolean supportsUrl(HttpUrl url) {
    Object $this$assertThreadHoldsLock$iv = this;
    int $i$f$assertThreadHoldsLock = 0;
    if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv); 
    HttpUrl routeUrl = this.route.address().url();
    if (url.port() != routeUrl.port())
      return false; 
    if (Intrinsics.areEqual(url.host(), routeUrl.host()))
      return true; 
    Intrinsics.checkNotNull(this.handshake);
    return (!this.noCoalescedConnections && this.handshake != null && certificateSupportHost(url, this.handshake));
  }
  
  private final boolean certificateSupportHost(HttpUrl url, Handshake handshake) {
    List peerCertificates = handshake.peerCertificates();
    if (!peerCertificates.isEmpty()) {
      Intrinsics.checkNotNull(peerCertificates.get(0), "null cannot be cast to non-null type java.security.cert.X509Certificate");
      if (OkHostnameVerifier.INSTANCE.verify(url.host(), (X509Certificate)peerCertificates.get(0)));
    } 
    return false;
  }
  
  @NotNull
  public final ExchangeCodec newCodec$okhttp(@NotNull OkHttpClient client, @NotNull RealInterceptorChain chain) throws SocketException {
    Intrinsics.checkNotNullParameter(client, "client");
    Intrinsics.checkNotNullParameter(chain, "chain");
    Intrinsics.checkNotNull(this.socket);
    Socket socket = this.socket;
    Intrinsics.checkNotNull(this.source);
    BufferedSource source = this.source;
    Intrinsics.checkNotNull(this.sink);
    BufferedSink sink = this.sink;
    Http2Connection http2Connection = this.http2Connection;
    socket.setSoTimeout(chain.readTimeoutMillis());
    source.timeout().timeout(chain.getReadTimeoutMillis$okhttp(), TimeUnit.MILLISECONDS);
    sink.timeout().timeout(chain.getWriteTimeoutMillis$okhttp(), TimeUnit.MILLISECONDS);
    return (http2Connection != null) ? (ExchangeCodec)new Http2ExchangeCodec(client, this, chain, http2Connection) : (ExchangeCodec)new Http1ExchangeCodec(client, this, source, sink);
  }
  
  @NotNull
  public final RealWebSocket.Streams newWebSocketStreams$okhttp(@NotNull Exchange exchange) throws SocketException {
    Intrinsics.checkNotNullParameter(exchange, "exchange");
    Intrinsics.checkNotNull(this.socket);
    Socket socket = this.socket;
    Intrinsics.checkNotNull(this.source);
    BufferedSource source = this.source;
    Intrinsics.checkNotNull(this.sink);
    BufferedSink sink = this.sink;
    socket.setSoTimeout(0);
    noNewExchanges$okhttp();
    return new RealConnection$newWebSocketStreams$1(source, sink, exchange);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\021\n\000\n\002\030\002\n\002\020\002\n\002\b\003*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\026¢\006\004\b\003\020\004¨\006\005"}, d2 = {"okhttp3/internal/connection/RealConnection.newWebSocketStreams.1", "Lokhttp3/internal/ws/RealWebSocket$Streams;", "", "close", "()V", "okhttp"})
  public static final class RealConnection$newWebSocketStreams$1 extends RealWebSocket.Streams {
    RealConnection$newWebSocketStreams$1(BufferedSource $source, BufferedSink $sink, Exchange $exchange) {
      super(true, $source, $sink);
    }
    
    public void close() {
      this.$exchange.bodyComplete(-1L, true, true, null);
    }
  }
  
  @NotNull
  public Route route() {
    return this.route;
  }
  
  public final void cancel() {
    if (this.rawSocket != null) {
      Util.closeQuietly(this.rawSocket);
    } else {
    
    } 
  }
  
  @NotNull
  public Socket socket() {
    Intrinsics.checkNotNull(this.socket);
    return this.socket;
  }
  
  public final boolean isHealthy(boolean doExtensiveChecks) {
    Object $this$assertThreadDoesntHoldLock$iv = this;
    int $i$f$assertThreadDoesntHoldLock = 0;
    if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); 
    long nowNs = System.nanoTime();
    Intrinsics.checkNotNull(this.rawSocket);
    Socket rawSocket = this.rawSocket;
    Intrinsics.checkNotNull(this.socket);
    Socket socket = this.socket;
    Intrinsics.checkNotNull(this.source);
    BufferedSource source = this.source;
    if (rawSocket.isClosed() || socket.isClosed() || socket.isInputShutdown() || socket.isOutputShutdown())
      return false; 
    Http2Connection http2Connection = this.http2Connection;
    if (http2Connection != null)
      return http2Connection.isHealthy(nowNs); 
    synchronized (this) {
      int $i$a$-synchronized-RealConnection$isHealthy$idleDurationNs$1 = 0;
      long l = nowNs - this.idleAtNs;
    } 
    long idleDurationNs = l;
    if (idleDurationNs >= 10000000000L && doExtensiveChecks)
      return Util.isHealthy(socket, source); 
    return true;
  }
  
  public void onStream(@NotNull Http2Stream stream) throws IOException {
    Intrinsics.checkNotNullParameter(stream, "stream");
    stream.close(ErrorCode.REFUSED_STREAM, null);
  }
  
  public synchronized void onSettings(@NotNull Http2Connection connection, @NotNull Settings settings) {
    Intrinsics.checkNotNullParameter(connection, "connection");
    Intrinsics.checkNotNullParameter(settings, "settings");
    this.allocationLimit = settings.getMaxConcurrentStreams();
  }
  
  @Nullable
  public Handshake handshake() {
    return this.handshake;
  }
  
  public final void connectFailed$okhttp(@NotNull OkHttpClient client, @NotNull Route failedRoute, @NotNull IOException failure) {
    Intrinsics.checkNotNullParameter(client, "client");
    Intrinsics.checkNotNullParameter(failedRoute, "failedRoute");
    Intrinsics.checkNotNullParameter(failure, "failure");
    if (failedRoute.proxy().type() != Proxy.Type.DIRECT) {
      Address address = failedRoute.address();
      address.proxySelector().connectFailed(address.url().uri(), failedRoute.proxy().address(), failure);
    } 
    client.getRouteDatabase().failed(failedRoute);
  }
  
  public final synchronized void trackFailure$okhttp(@NotNull RealCall call, @Nullable IOException e) {
    Intrinsics.checkNotNullParameter(call, "call");
    if (e instanceof StreamResetException) {
      if (((StreamResetException)e).errorCode == ErrorCode.REFUSED_STREAM) {
        int i = this.refusedStreamCount;
        this.refusedStreamCount = i + 1;
        if (this.refusedStreamCount > 1) {
          this.noNewExchanges = true;
          i = this.routeFailureCount;
          this.routeFailureCount = i + 1;
        } 
      } else if (((StreamResetException)e).errorCode != ErrorCode.CANCEL || !call.isCanceled()) {
        this.noNewExchanges = true;
        int i = this.routeFailureCount;
        this.routeFailureCount = i + 1;
      } 
    } else if (!isMultiplexed$okhttp() || e instanceof okhttp3.internal.http2.ConnectionShutdownException) {
      this.noNewExchanges = true;
      if (this.successCount == 0) {
        if (e != null)
          connectFailed$okhttp(call.getClient(), this.route, e); 
        int i = this.routeFailureCount;
        this.routeFailureCount = i + 1;
      } 
    } 
  }
  
  @NotNull
  public Protocol protocol() {
    Intrinsics.checkNotNull(this.protocol);
    return this.protocol;
  }
  
  @NotNull
  public String toString() {
    if (this.handshake == null || this.handshake.cipherSuite() == null)
      this.handshake.cipherSuite(); 
    return this.handshake.cipherSuite().append("none").append(" protocol=").append(this.protocol).append('}').toString();
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000<\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\004\n\002\020\b\n\002\b\002\n\002\020\016\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J-\020\r\032\0020\f2\006\020\005\032\0020\0042\006\020\007\032\0020\0062\006\020\t\032\0020\b2\006\020\013\032\0020\n¢\006\004\b\r\020\016R\024\020\017\032\0020\n8\000XT¢\006\006\n\004\b\017\020\020R\024\020\022\032\0020\0218\002XT¢\006\006\n\004\b\022\020\023R\024\020\025\032\0020\0248\002XT¢\006\006\n\004\b\025\020\026¨\006\027"}, d2 = {"Lokhttp3/internal/connection/RealConnection$Companion;", "", "<init>", "()V", "Lokhttp3/internal/connection/RealConnectionPool;", "connectionPool", "Lokhttp3/Route;", "route", "Ljava/net/Socket;", "socket", "", "idleAtNs", "Lokhttp3/internal/connection/RealConnection;", "newTestConnection", "(Lokhttp3/internal/connection/RealConnectionPool;Lokhttp3/Route;Ljava/net/Socket;J)Lokhttp3/internal/connection/RealConnection;", "IDLE_CONNECTION_HEALTHY_NS", "J", "", "MAX_TUNNEL_ATTEMPTS", "I", "", "NPE_THROW_WITH_NULL", "Ljava/lang/String;", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @NotNull
    public final RealConnection newTestConnection(@NotNull RealConnectionPool connectionPool, @NotNull Route route, @NotNull Socket socket, long idleAtNs) {
      Intrinsics.checkNotNullParameter(connectionPool, "connectionPool");
      Intrinsics.checkNotNullParameter(route, "route");
      Intrinsics.checkNotNullParameter(socket, "socket");
      RealConnection result = new RealConnection(connectionPool, route);
      result.socket = socket;
      result.setIdleAtNs$okhttp(idleAtNs);
      return result;
    }
  }
}
