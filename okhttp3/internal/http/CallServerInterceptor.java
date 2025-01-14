package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okio.BufferedSink;
import okio.Okio;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000&\n\002\030\002\n\002\030\002\n\002\020\013\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\005\030\0002\0020\001B\017\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\027\020\t\032\0020\b2\006\020\007\032\0020\006H\026¢\006\004\b\t\020\nJ\027\020\r\032\0020\0022\006\020\f\032\0020\013H\002¢\006\004\b\r\020\016R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\017¨\006\020"}, d2 = {"Lokhttp3/internal/http/CallServerInterceptor;", "Lokhttp3/Interceptor;", "", "forWebSocket", "<init>", "(Z)V", "Lokhttp3/Interceptor$Chain;", "chain", "Lokhttp3/Response;", "intercept", "(Lokhttp3/Interceptor$Chain;)Lokhttp3/Response;", "", "code", "shouldIgnoreAndWaitForRealResponse", "(I)Z", "Z", "okhttp"})
public final class CallServerInterceptor implements Interceptor {
  private final boolean forWebSocket;
  
  public CallServerInterceptor(boolean forWebSocket) {
    this.forWebSocket = forWebSocket;
  }
  
  @NotNull
  public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
    Intrinsics.checkNotNullParameter(chain, "chain");
    RealInterceptorChain realChain = (RealInterceptorChain)chain;
    Intrinsics.checkNotNull(realChain.getExchange$okhttp());
    Exchange exchange = realChain.getExchange$okhttp();
    Request request = realChain.getRequest$okhttp();
    RequestBody requestBody = request.body();
    long sentRequestMillis = System.currentTimeMillis();
    boolean invokeStartEvent = true;
    Response.Builder responseBuilder = null;
    IOException sendRequestException = null;
    try {
      exchange.writeRequestHeaders(request);
      if (HttpMethod.permitsRequestBody(request.method()) && requestBody != null) {
        if (StringsKt.equals("100-continue", request.header("Expect"), true)) {
          exchange.flushRequest();
          responseBuilder = exchange.readResponseHeaders(true);
          exchange.responseHeadersStart();
          invokeStartEvent = false;
        } 
        if (responseBuilder == null) {
          if (requestBody.isDuplex()) {
            exchange.flushRequest();
            BufferedSink bufferedRequestBody = Okio.buffer(exchange.createRequestBody(request, true));
            requestBody.writeTo(bufferedRequestBody);
          } else {
            BufferedSink bufferedRequestBody = Okio.buffer(exchange.createRequestBody(request, false));
            requestBody.writeTo(bufferedRequestBody);
            bufferedRequestBody.close();
          } 
        } else {
          exchange.noRequestBody();
          if (!exchange.getConnection$okhttp().isMultiplexed$okhttp())
            exchange.noNewExchangesOnConnection(); 
        } 
      } else {
        exchange.noRequestBody();
      } 
      if (requestBody == null || !requestBody.isDuplex())
        exchange.finishRequest(); 
    } catch (IOException e) {
      if (e instanceof okhttp3.internal.http2.ConnectionShutdownException)
        throw e; 
      if (!exchange.getHasFailure$okhttp())
        throw e; 
      sendRequestException = e;
    } 
    try {
      if (responseBuilder == null) {
        Intrinsics.checkNotNull(exchange.readResponseHeaders(false));
        responseBuilder = exchange.readResponseHeaders(false);
        if (invokeStartEvent) {
          exchange.responseHeadersStart();
          invokeStartEvent = false;
        } 
      } 
      Response response = responseBuilder
        .request(request)
        .handshake(exchange.getConnection$okhttp().handshake())
        .sentRequestAtMillis(sentRequestMillis)
        .receivedResponseAtMillis(System.currentTimeMillis())
        .build();
      int code = response.code();
      if (shouldIgnoreAndWaitForRealResponse(code)) {
        Intrinsics.checkNotNull(exchange.readResponseHeaders(false));
        responseBuilder = exchange.readResponseHeaders(false);
        if (invokeStartEvent)
          exchange.responseHeadersStart(); 
        response = responseBuilder
          .request(request)
          .handshake(exchange.getConnection$okhttp().handshake())
          .sentRequestAtMillis(sentRequestMillis)
          .receivedResponseAtMillis(System.currentTimeMillis())
          .build();
        code = response.code();
      } 
      exchange.responseHeadersEnd(response);
      response = (this.forWebSocket && code == 101) ? 
        
        response.newBuilder()
        .body(Util.EMPTY_RESPONSE)
        .build() : 
        
        response.newBuilder()
        .body(exchange.openResponseBody(response))
        .build();
      if (StringsKt.equals("close", response.request().header("Connection"), true) || 
        StringsKt.equals("close", Response.header$default(response, "Connection", null, 2, null), true))
        exchange.noNewExchangesOnConnection(); 
      if (code == 204 || code == 205) {
        response.body();
        if (((response.body() != null) ? response.body().contentLength() : -1L) > 0L) {
          response.body();
          throw new ProtocolException("HTTP " + code + " had non-zero Content-Length: " + ((response.body() != null) ? Long.valueOf(response.body().contentLength()) : null));
        } 
      } 
      return response;
    } catch (IOException e) {
      if (sendRequestException != null) {
        ExceptionsKt.addSuppressed(sendRequestException, e);
        throw sendRequestException;
      } 
      throw e;
    } 
  }
  
  private final boolean shouldIgnoreAndWaitForRealResponse(int code) {
    return 
      
      (code == 100) ? true : (
      
      ((102 <= code) ? ((code < 200)) : false));
  }
}
