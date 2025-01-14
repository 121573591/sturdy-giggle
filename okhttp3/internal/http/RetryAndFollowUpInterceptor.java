package okhttp3.internal.http;

import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RouteException;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000R\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\020\016\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\020\013\n\002\b\003\n\002\030\002\n\002\b\006\n\002\020\b\n\002\b\006\030\000 '2\0020\001:\001'B\017\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J!\020\013\032\004\030\0010\n2\006\020\007\032\0020\0062\006\020\t\032\0020\bH\002¢\006\004\b\013\020\fJ#\020\017\032\004\030\0010\n2\006\020\007\032\0020\0062\b\020\016\032\004\030\0010\rH\002¢\006\004\b\017\020\020J\027\020\023\032\0020\0062\006\020\022\032\0020\021H\026¢\006\004\b\023\020\024J\037\020\031\032\0020\0272\006\020\026\032\0020\0252\006\020\030\032\0020\027H\002¢\006\004\b\031\020\032J/\020\036\032\0020\0272\006\020\026\032\0020\0252\006\020\034\032\0020\0332\006\020\035\032\0020\n2\006\020\030\032\0020\027H\002¢\006\004\b\036\020\037J\037\020 \032\0020\0272\006\020\026\032\0020\0252\006\020\035\032\0020\nH\002¢\006\004\b \020!J\037\020$\032\0020\"2\006\020\007\032\0020\0062\006\020#\032\0020\"H\002¢\006\004\b$\020%R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020&¨\006("}, d2 = {"Lokhttp3/internal/http/RetryAndFollowUpInterceptor;", "Lokhttp3/Interceptor;", "Lokhttp3/OkHttpClient;", "client", "<init>", "(Lokhttp3/OkHttpClient;)V", "Lokhttp3/Response;", "userResponse", "", "method", "Lokhttp3/Request;", "buildRedirectRequest", "(Lokhttp3/Response;Ljava/lang/String;)Lokhttp3/Request;", "Lokhttp3/internal/connection/Exchange;", "exchange", "followUpRequest", "(Lokhttp3/Response;Lokhttp3/internal/connection/Exchange;)Lokhttp3/Request;", "Lokhttp3/Interceptor$Chain;", "chain", "intercept", "(Lokhttp3/Interceptor$Chain;)Lokhttp3/Response;", "Ljava/io/IOException;", "e", "", "requestSendStarted", "isRecoverable", "(Ljava/io/IOException;Z)Z", "Lokhttp3/internal/connection/RealCall;", "call", "userRequest", "recover", "(Ljava/io/IOException;Lokhttp3/internal/connection/RealCall;Lokhttp3/Request;Z)Z", "requestIsOneShot", "(Ljava/io/IOException;Lokhttp3/Request;)Z", "", "defaultDelay", "retryAfter", "(Lokhttp3/Response;I)I", "Lokhttp3/OkHttpClient;", "Companion", "okhttp"})
public final class RetryAndFollowUpInterceptor implements Interceptor {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final OkHttpClient client;
  
  private static final int MAX_FOLLOW_UPS = 20;
  
  public RetryAndFollowUpInterceptor(@NotNull OkHttpClient client) {
    this.client = client;
  }
  
  @NotNull
  public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
    Intrinsics.checkNotNullParameter(chain, "chain");
    RealInterceptorChain realChain = (RealInterceptorChain)chain;
    Request request = ((RealInterceptorChain)chain).getRequest$okhttp();
    RealCall call = realChain.getCall$okhttp();
    int followUpCount = 0;
    Response priorResponse = null;
    boolean newExchangeFinder = true;
    List recoveredFailures = CollectionsKt.emptyList();
    while (true) {
      call.enterNetworkInterceptorExchange(request, newExchangeFinder);
      Response response = null;
      boolean closeActiveExchange = true;
      try {
        if (call.isCanceled())
          throw new IOException("Canceled"); 
        try {
          response = realChain.proceed(request);
          newExchangeFinder = true;
        } catch (RouteException e) {
          if (!recover(e.getLastConnectException(), call, request, false))
            throw Util.withSuppressed(e.getFirstConnectException(), recoveredFailures); 
          recoveredFailures = CollectionsKt.plus(recoveredFailures, e.getFirstConnectException());
          newExchangeFinder = false;
          call.exitNetworkInterceptorExchange$okhttp(closeActiveExchange);
          continue;
        } catch (IOException iOException) {
          RouteException e;
          if (!recover((IOException)e, call, request, !(e instanceof okhttp3.internal.http2.ConnectionShutdownException)))
            throw Util.withSuppressed((Exception)e, recoveredFailures); 
          recoveredFailures = CollectionsKt.plus(recoveredFailures, e);
          newExchangeFinder = false;
          call.exitNetworkInterceptorExchange$okhttp(closeActiveExchange);
          continue;
        } 
        if (priorResponse != null)
          response = response.newBuilder().priorResponse(priorResponse.newBuilder().body(null).build()).build(); 
        Exchange exchange = call.getInterceptorScopedExchange$okhttp();
        Request followUp = followUpRequest(response, exchange);
        if (followUp == null) {
          if (exchange != null && exchange.isDuplex$okhttp())
            call.timeoutEarlyExit(); 
          closeActiveExchange = false;
          return response;
        } 
        RequestBody followUpBody = followUp.body();
        if (followUpBody != null && followUpBody.isOneShot()) {
          closeActiveExchange = false;
          return response;
        } 
        if (response.body() != null) {
          Util.closeQuietly((Closeable)response.body());
        } else {
          response.body();
        } 
        if (++followUpCount > 20)
          throw new ProtocolException("Too many follow-up requests: " + followUpCount); 
        request = followUp;
        priorResponse = response;
      } finally {
        call.exitNetworkInterceptorExchange$okhttp(closeActiveExchange);
      } 
    } 
  }
  
  private final boolean recover(IOException e, RealCall call, Request userRequest, boolean requestSendStarted) {
    if (!this.client.retryOnConnectionFailure())
      return false; 
    if (requestSendStarted && requestIsOneShot(e, userRequest))
      return false; 
    if (!isRecoverable(e, requestSendStarted))
      return false; 
    if (!call.retryAfterFailure())
      return false; 
    return true;
  }
  
  private final boolean requestIsOneShot(IOException e, Request userRequest) {
    RequestBody requestBody = userRequest.body();
    return ((requestBody != null && requestBody.isOneShot()) || 
      e instanceof java.io.FileNotFoundException);
  }
  
  private final boolean isRecoverable(IOException e, boolean requestSendStarted) {
    if (e instanceof ProtocolException)
      return false; 
    if (e instanceof java.io.InterruptedIOException)
      return (e instanceof java.net.SocketTimeoutException && !requestSendStarted); 
    if (e instanceof javax.net.ssl.SSLHandshakeException)
      if (e.getCause() instanceof java.security.cert.CertificateException)
        return false;  
    if (e instanceof javax.net.ssl.SSLPeerUnverifiedException)
      return false; 
    return true;
  }
  
  private final Request followUpRequest(Response userResponse, Exchange exchange) throws IOException {
    Proxy selectedProxy;
    RequestBody requestBody1;
    Response priorResponse;
    RequestBody requestBody;
    Response response1;
    exchange.getConnection$okhttp();
    Route route = (exchange != null && exchange.getConnection$okhttp() != null) ? exchange.getConnection$okhttp().route() : null;
    int responseCode = userResponse.code();
    String method = userResponse.request().method();
    switch (responseCode) {
      case 407:
        Intrinsics.checkNotNull(route);
        selectedProxy = route.proxy();
        if (selectedProxy.type() != Proxy.Type.HTTP)
          throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy"); 
        return this.client.proxyAuthenticator().authenticate(route, userResponse);
      case 401:
        return this.client.authenticator().authenticate(route, userResponse);
      case 300:
      case 301:
      case 302:
      case 303:
      case 307:
      case 308:
        return buildRedirectRequest(userResponse, method);
      case 408:
        if (!this.client.retryOnConnectionFailure())
          return null; 
        requestBody1 = userResponse.request().body();
        if (requestBody1 != null && requestBody1.isOneShot())
          return null; 
        response1 = userResponse.priorResponse();
        if (response1 != null && response1.code() == 408)
          return null; 
        if (retryAfter(userResponse, 0) > 0)
          return null; 
        return userResponse.request();
      case 503:
        priorResponse = userResponse.priorResponse();
        if (priorResponse != null && priorResponse.code() == 503)
          return null; 
        if (retryAfter(userResponse, 2147483647) == 0)
          return userResponse.request(); 
        return null;
      case 421:
        requestBody = userResponse.request().body();
        if (requestBody != null && requestBody.isOneShot())
          return null; 
        if (exchange == null || !exchange.isCoalescedConnection$okhttp())
          return null; 
        exchange.getConnection$okhttp().noCoalescedConnections$okhttp();
        return userResponse.request();
    } 
    return null;
  }
  
  private final Request buildRedirectRequest(Response userResponse, String method) {
    String location;
    HttpUrl url;
    if (!this.client.followRedirects())
      return null; 
    if (Response.header$default(userResponse, "Location", null, 2, null) == null) {
      Response.header$default(userResponse, "Location", null, 2, null);
      return null;
    } 
    if (userResponse.request().url().resolve(location) == null) {
      userResponse.request().url().resolve(location);
      return null;
    } 
    boolean sameScheme = Intrinsics.areEqual(url.scheme(), userResponse.request().url().scheme());
    if (!sameScheme && !this.client.followSslRedirects())
      return null; 
    Request.Builder requestBuilder = userResponse.request().newBuilder();
    if (HttpMethod.permitsRequestBody(method)) {
      int responseCode = userResponse.code();
      boolean maintainBody = (HttpMethod.INSTANCE.redirectsWithBody(method) || 
        responseCode == 308 || 
        responseCode == 307);
      if (HttpMethod.INSTANCE.redirectsToGet(method) && responseCode != 308 && responseCode != 307) {
        requestBuilder.method("GET", null);
      } else {
        RequestBody requestBody = maintainBody ? userResponse.request().body() : null;
        requestBuilder.method(method, requestBody);
      } 
      if (!maintainBody) {
        requestBuilder.removeHeader("Transfer-Encoding");
        requestBuilder.removeHeader("Content-Length");
        requestBuilder.removeHeader("Content-Type");
      } 
    } 
    if (!Util.canReuseConnectionFor(userResponse.request().url(), url))
      requestBuilder.removeHeader("Authorization"); 
    return requestBuilder.url(url).build();
  }
  
  private final int retryAfter(Response userResponse, int defaultDelay) {
    String header;
    if (Response.header$default(userResponse, "Retry-After", null, 2, null) == null) {
      Response.header$default(userResponse, "Retry-After", null, 2, null);
      return defaultDelay;
    } 
    CharSequence charSequence = header;
    if ((new Regex("\\d+")).matches(charSequence)) {
      Intrinsics.checkNotNullExpressionValue(Integer.valueOf(header), "valueOf(header)");
      return Integer.valueOf(header).intValue();
    } 
    return Integer.MAX_VALUE;
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\024\020\005\032\0020\0048\002XT¢\006\006\n\004\b\005\020\006¨\006\007"}, d2 = {"Lokhttp3/internal/http/RetryAndFollowUpInterceptor$Companion;", "", "<init>", "()V", "", "MAX_FOLLOW_UPS", "I", "okhttp"})
  public static final class Companion {
    private Companion() {}
  }
}
