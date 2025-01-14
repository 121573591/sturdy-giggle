package okhttp3.internal.connection;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000z\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\004\n\002\020\013\n\000\n\002\030\002\n\002\b\007\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\020\002\n\002\b\016\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\030\0002\0020\001B'\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\006\020\007\032\0020\006\022\006\020\t\032\0020\b¢\006\004\b\n\020\013J\035\020\021\032\0020\0202\006\020\r\032\0020\f2\006\020\017\032\0020\016¢\006\004\b\021\020\022J7\020\033\032\0020\0322\006\020\024\032\0020\0232\006\020\025\032\0020\0232\006\020\026\032\0020\0232\006\020\027\032\0020\0232\006\020\031\032\0020\030H\002¢\006\004\b\033\020\034J?\020\036\032\0020\0322\006\020\024\032\0020\0232\006\020\025\032\0020\0232\006\020\026\032\0020\0232\006\020\027\032\0020\0232\006\020\031\032\0020\0302\006\020\035\032\0020\030H\002¢\006\004\b\036\020\037J\r\020 \032\0020\030¢\006\004\b \020!J\021\020#\032\004\030\0010\"H\002¢\006\004\b#\020$J\025\020'\032\0020\0302\006\020&\032\0020%¢\006\004\b'\020(J\025\020,\032\0020+2\006\020*\032\0020)¢\006\004\b,\020-R\032\020\005\032\0020\0048\000X\004¢\006\f\n\004\b\005\020.\032\004\b/\0200R\024\020\007\032\0020\0068\002X\004¢\006\006\n\004\b\007\0201R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\0202R\026\0203\032\0020\0238\002@\002X\016¢\006\006\n\004\b3\0204R\024\020\t\032\0020\b8\002X\004¢\006\006\n\004\b\t\0205R\030\0206\032\004\030\0010\"8\002@\002X\016¢\006\006\n\004\b6\0207R\026\0208\032\0020\0238\002@\002X\016¢\006\006\n\004\b8\0204R\026\0209\032\0020\0238\002@\002X\016¢\006\006\n\004\b9\0204R\030\020;\032\004\030\0010:8\002@\002X\016¢\006\006\n\004\b;\020<R\030\020>\032\004\030\0010=8\002@\002X\016¢\006\006\n\004\b>\020?¨\006@"}, d2 = {"Lokhttp3/internal/connection/ExchangeFinder;", "", "Lokhttp3/internal/connection/RealConnectionPool;", "connectionPool", "Lokhttp3/Address;", "address", "Lokhttp3/internal/connection/RealCall;", "call", "Lokhttp3/EventListener;", "eventListener", "<init>", "(Lokhttp3/internal/connection/RealConnectionPool;Lokhttp3/Address;Lokhttp3/internal/connection/RealCall;Lokhttp3/EventListener;)V", "Lokhttp3/OkHttpClient;", "client", "Lokhttp3/internal/http/RealInterceptorChain;", "chain", "Lokhttp3/internal/http/ExchangeCodec;", "find", "(Lokhttp3/OkHttpClient;Lokhttp3/internal/http/RealInterceptorChain;)Lokhttp3/internal/http/ExchangeCodec;", "", "connectTimeout", "readTimeout", "writeTimeout", "pingIntervalMillis", "", "connectionRetryEnabled", "Lokhttp3/internal/connection/RealConnection;", "findConnection", "(IIIIZ)Lokhttp3/internal/connection/RealConnection;", "doExtensiveHealthChecks", "findHealthyConnection", "(IIIIZZ)Lokhttp3/internal/connection/RealConnection;", "retryAfterFailure", "()Z", "Lokhttp3/Route;", "retryRoute", "()Lokhttp3/Route;", "Lokhttp3/HttpUrl;", "url", "sameHostAndPort", "(Lokhttp3/HttpUrl;)Z", "Ljava/io/IOException;", "e", "", "trackFailure", "(Ljava/io/IOException;)V", "Lokhttp3/Address;", "getAddress$okhttp", "()Lokhttp3/Address;", "Lokhttp3/internal/connection/RealCall;", "Lokhttp3/internal/connection/RealConnectionPool;", "connectionShutdownCount", "I", "Lokhttp3/EventListener;", "nextRouteToTry", "Lokhttp3/Route;", "otherFailureCount", "refusedStreamCount", "Lokhttp3/internal/connection/RouteSelector$Selection;", "routeSelection", "Lokhttp3/internal/connection/RouteSelector$Selection;", "Lokhttp3/internal/connection/RouteSelector;", "routeSelector", "Lokhttp3/internal/connection/RouteSelector;", "okhttp"})
public final class ExchangeFinder {
  @NotNull
  private final RealConnectionPool connectionPool;
  
  @NotNull
  private final Address address;
  
  @NotNull
  private final RealCall call;
  
  @NotNull
  private final EventListener eventListener;
  
  @Nullable
  private RouteSelector.Selection routeSelection;
  
  @Nullable
  private RouteSelector routeSelector;
  
  private int refusedStreamCount;
  
  private int connectionShutdownCount;
  
  private int otherFailureCount;
  
  @Nullable
  private Route nextRouteToTry;
  
  public ExchangeFinder(@NotNull RealConnectionPool connectionPool, @NotNull Address address, @NotNull RealCall call, @NotNull EventListener eventListener) {
    this.connectionPool = connectionPool;
    this.address = address;
    this.call = call;
    this.eventListener = eventListener;
  }
  
  @NotNull
  public final Address getAddress$okhttp() {
    return this.address;
  }
  
  @NotNull
  public final ExchangeCodec find(@NotNull OkHttpClient client, @NotNull RealInterceptorChain chain) {
    Intrinsics.checkNotNullParameter(client, "client");
    Intrinsics.checkNotNullParameter(chain, "chain");
    try {
      RealConnection resultConnection = findHealthyConnection(
          chain.getConnectTimeoutMillis$okhttp(), 
          chain.getReadTimeoutMillis$okhttp(), 
          chain.getWriteTimeoutMillis$okhttp(), 
          client.pingIntervalMillis(), 
          client.retryOnConnectionFailure(), 
          !Intrinsics.areEqual(chain.getRequest$okhttp().method(), "GET"));
      return resultConnection.newCodec$okhttp(client, chain);
    } catch (RouteException e) {
      trackFailure(e.getLastConnectException());
      throw e;
    } catch (IOException e) {
      trackFailure(e);
      throw new RouteException(e);
    } 
  }
  
  private final RealConnection findHealthyConnection(int connectTimeout, int readTimeout, int writeTimeout, int pingIntervalMillis, boolean connectionRetryEnabled, boolean doExtensiveHealthChecks) throws IOException {
    while (true) {
      RealConnection candidate = findConnection(
          connectTimeout, 
          readTimeout, 
          writeTimeout, 
          pingIntervalMillis, 
          connectionRetryEnabled);
      if (candidate.isHealthy(doExtensiveHealthChecks))
        return candidate; 
      candidate.noNewExchanges$okhttp();
      if (this.nextRouteToTry == null) {
        boolean routesLeft = (this.routeSelection != null) ? this.routeSelection.hasNext() : true;
        if (!routesLeft) {
          boolean routesSelectionLeft = (this.routeSelector != null) ? this.routeSelector.hasNext() : true;
          if (!routesSelectionLeft)
            break; 
        } 
      } 
    } 
    throw new IOException("exhausted all routes");
  }
  
  private final RealConnection findConnection(int connectTimeout, int readTimeout, int writeTimeout, int pingIntervalMillis, boolean connectionRetryEnabled) throws IOException {
    if (this.call.isCanceled())
      throw new IOException("Canceled"); 
    RealConnection callConnection = this.call.getConnection();
    if (callConnection != null) {
      Object toClose = null;
      synchronized (callConnection) {
        int $i$a$-synchronized-ExchangeFinder$findConnection$1 = 0;
        if (callConnection.getNoNewExchanges() || !sameHostAndPort(callConnection.route().address().url()))
          toClose = this.call.releaseConnectionNoEvents$okhttp(); 
        Unit unit = Unit.INSTANCE;
      } 
      if (this.call.getConnection() != null) {
        if (!((toClose == null) ? 1 : 0)) {
          String str = "Check failed.";
          throw new IllegalStateException(str.toString());
        } 
        return callConnection;
      } 
      if (toClose != null) {
        Util.closeQuietly((Socket)toClose);
      } else {
      
      } 
      this.eventListener.connectionReleased(this.call, callConnection);
    } 
    this.refusedStreamCount = 0;
    this.connectionShutdownCount = 0;
    this.otherFailureCount = 0;
    if (this.connectionPool.callAcquirePooledConnection(this.address, this.call, null, false)) {
      Intrinsics.checkNotNull(this.call.getConnection());
      RealConnection result = this.call.getConnection();
      this.eventListener.connectionAcquired(this.call, result);
      return result;
    } 
    List<Route> routes = null;
    Route route = null;
    if (this.nextRouteToTry != null) {
      routes = null;
      Intrinsics.checkNotNull(this.nextRouteToTry);
      route = this.nextRouteToTry;
      this.nextRouteToTry = null;
    } else {
      Intrinsics.checkNotNull(this.routeSelection);
      if (this.routeSelection != null && this.routeSelection.hasNext()) {
        routes = null;
        Intrinsics.checkNotNull(this.routeSelection);
        route = this.routeSelection.next();
      } else {
        RouteSelector localRouteSelector = this.routeSelector;
        if (localRouteSelector == null) {
          localRouteSelector = new RouteSelector(this.address, this.call.getClient().getRouteDatabase(), this.call, this.eventListener);
          this.routeSelector = localRouteSelector;
        } 
        RouteSelector.Selection localRouteSelection = localRouteSelector.next();
        this.routeSelection = localRouteSelection;
        routes = localRouteSelection.getRoutes();
        if (this.call.isCanceled())
          throw new IOException("Canceled"); 
        if (this.connectionPool.callAcquirePooledConnection(this.address, this.call, routes, false)) {
          Intrinsics.checkNotNull(this.call.getConnection());
          RealConnection result = this.call.getConnection();
          this.eventListener.connectionAcquired(this.call, result);
          return result;
        } 
        route = localRouteSelection.next();
      } 
    } 
    RealConnection newConnection = new RealConnection(this.connectionPool, route);
    this.call.setConnectionToCancel(newConnection);
    try {
      newConnection.connect(
          connectTimeout, 
          readTimeout, 
          writeTimeout, 
          pingIntervalMillis, 
          connectionRetryEnabled, 
          this.call, 
          this.eventListener);
    } finally {
      this.call.setConnectionToCancel(null);
    } 
    this.call.getClient().getRouteDatabase().connected(newConnection.route());
    if (this.connectionPool.callAcquirePooledConnection(this.address, this.call, routes, true)) {
      Intrinsics.checkNotNull(this.call.getConnection());
      RealConnection result = this.call.getConnection();
      this.nextRouteToTry = route;
      Util.closeQuietly(newConnection.socket());
      this.eventListener.connectionAcquired(this.call, result);
      return result;
    } 
    synchronized (newConnection) {
      int $i$a$-synchronized-ExchangeFinder$findConnection$2 = 0;
      this.connectionPool.put(newConnection);
      this.call.acquireConnectionNoEvents(newConnection);
      Unit unit = Unit.INSTANCE;
    } 
    this.eventListener.connectionAcquired(this.call, newConnection);
    return newConnection;
  }
  
  public final void trackFailure(@NotNull IOException e) {
    Intrinsics.checkNotNullParameter(e, "e");
    this.nextRouteToTry = null;
    if (e instanceof StreamResetException && ((StreamResetException)e).errorCode == ErrorCode.REFUSED_STREAM) {
      int i = this.refusedStreamCount;
      this.refusedStreamCount = i + 1;
    } else if (e instanceof okhttp3.internal.http2.ConnectionShutdownException) {
      int i = this.connectionShutdownCount;
      this.connectionShutdownCount = i + 1;
    } else {
      int i = this.otherFailureCount;
      this.otherFailureCount = i + 1;
    } 
  }
  
  public final boolean retryAfterFailure() {
    RouteSelector localRouteSelector;
    if (this.refusedStreamCount == 0 && this.connectionShutdownCount == 0 && this.otherFailureCount == 0)
      return false; 
    if (this.nextRouteToTry != null)
      return true; 
    Route retryRoute = retryRoute();
    if (retryRoute != null) {
      this.nextRouteToTry = retryRoute;
      return true;
    } 
    if ((this.routeSelection != null) ? ((this.routeSelection.hasNext() == true)) : false)
      return true; 
    if (this.routeSelector == null)
      return true; 
    return localRouteSelector.hasNext();
  }
  
  private final Route retryRoute() {
    RealConnection connection;
    if (this.refusedStreamCount > 1 || this.connectionShutdownCount > 1 || this.otherFailureCount > 0)
      return null; 
    if (this.call.getConnection() == null) {
      this.call.getConnection();
      return null;
    } 
    synchronized (connection) {
      int $i$a$-synchronized-ExchangeFinder$retryRoute$1 = 0;
      if (connection.getRouteFailureCount$okhttp() != 0)
        return null; 
      if (!Util.canReuseConnectionFor(connection.route().address().url(), this.address.url()))
        return null; 
      return connection.route();
    } 
  }
  
  public final boolean sameHostAndPort(@NotNull HttpUrl url) {
    Intrinsics.checkNotNullParameter(url, "url");
    HttpUrl routeUrl = this.address.url();
    return (url.port() == routeUrl.port() && Intrinsics.areEqual(url.host(), routeUrl.host()));
  }
}
