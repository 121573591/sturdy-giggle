package okhttp3.internal.http;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okio.GzipSource;
import okio.Okio;
import okio.Source;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0000\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020 \n\002\030\002\n\000\n\002\020\016\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\004\030\0002\0020\001B\017\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\035\020\n\032\0020\t2\f\020\b\032\b\022\004\022\0020\0070\006H\002¢\006\004\b\n\020\013J\027\020\017\032\0020\0162\006\020\r\032\0020\fH\026¢\006\004\b\017\020\020R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\021¨\006\022"}, d2 = {"Lokhttp3/internal/http/BridgeInterceptor;", "Lokhttp3/Interceptor;", "Lokhttp3/CookieJar;", "cookieJar", "<init>", "(Lokhttp3/CookieJar;)V", "", "Lokhttp3/Cookie;", "cookies", "", "cookieHeader", "(Ljava/util/List;)Ljava/lang/String;", "Lokhttp3/Interceptor$Chain;", "chain", "Lokhttp3/Response;", "intercept", "(Lokhttp3/Interceptor$Chain;)Lokhttp3/Response;", "Lokhttp3/CookieJar;", "okhttp"})
@SourceDebugExtension({"SMAP\nBridgeInterceptor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BridgeInterceptor.kt\nokhttp3/internal/http/BridgeInterceptor\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,117:1\n1864#2,3:118\n*S KotlinDebug\n*F\n+ 1 BridgeInterceptor.kt\nokhttp3/internal/http/BridgeInterceptor\n*L\n111#1:118,3\n*E\n"})
public final class BridgeInterceptor implements Interceptor {
  @NotNull
  private final CookieJar cookieJar;
  
  public BridgeInterceptor(@NotNull CookieJar cookieJar) {
    this.cookieJar = cookieJar;
  }
  
  @NotNull
  public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
    Intrinsics.checkNotNullParameter(chain, "chain");
    Request userRequest = chain.request();
    Request.Builder requestBuilder = userRequest.newBuilder();
    RequestBody body = userRequest.body();
    if (body != null) {
      MediaType contentType = body.contentType();
      if (contentType != null)
        requestBuilder.header("Content-Type", contentType.toString()); 
      long contentLength = body.contentLength();
      if (contentLength != -1L) {
        requestBuilder.header("Content-Length", String.valueOf(contentLength));
        requestBuilder.removeHeader("Transfer-Encoding");
      } else {
        requestBuilder.header("Transfer-Encoding", "chunked");
        requestBuilder.removeHeader("Content-Length");
      } 
    } 
    if (userRequest.header("Host") == null)
      requestBuilder.header("Host", Util.toHostHeader$default(userRequest.url(), false, 1, null)); 
    if (userRequest.header("Connection") == null)
      requestBuilder.header("Connection", "Keep-Alive"); 
    boolean transparentGzip = false;
    if (userRequest.header("Accept-Encoding") == null && userRequest.header("Range") == null) {
      transparentGzip = true;
      requestBuilder.header("Accept-Encoding", "gzip");
    } 
    List<Cookie> cookies = this.cookieJar.loadForRequest(userRequest.url());
    if (!cookies.isEmpty())
      requestBuilder.header("Cookie", cookieHeader(cookies)); 
    if (userRequest.header("User-Agent") == null)
      requestBuilder.header("User-Agent", "okhttp/4.12.0"); 
    Response networkResponse = chain.proceed(requestBuilder.build());
    HttpHeaders.receiveHeaders(this.cookieJar, userRequest.url(), networkResponse.headers());
    Response.Builder responseBuilder = networkResponse.newBuilder()
      .request(userRequest);
    if (transparentGzip && 
      StringsKt.equals("gzip", Response.header$default(networkResponse, "Content-Encoding", null, 2, null), true) && 
      HttpHeaders.promisesBody(networkResponse)) {
      ResponseBody responseBody = networkResponse.body();
      if (responseBody != null) {
        GzipSource gzipSource = new GzipSource((Source)responseBody.source());
        Headers strippedHeaders = networkResponse.headers().newBuilder()
          .removeAll("Content-Encoding")
          .removeAll("Content-Length")
          .build();
        responseBuilder.headers(strippedHeaders);
        String contentType = Response.header$default(networkResponse, "Content-Type", null, 2, null);
        responseBuilder.body(new RealResponseBody(contentType, -1L, Okio.buffer((Source)gzipSource)));
      } 
    } 
    return responseBuilder.build();
  }
  
  private final String cookieHeader(List cookies) {
    StringBuilder stringBuilder1 = new StringBuilder(), $this$cookieHeader_u24lambda_u241 = stringBuilder1;
    int $i$a$-buildString-BridgeInterceptor$cookieHeader$1 = 0;
    Iterable $this$forEachIndexed$iv = cookies;
    int $i$f$forEachIndexed = 0;
    int index$iv = 0;
    Iterator iterator = $this$forEachIndexed$iv.iterator();
    if (iterator.hasNext()) {
      Object item$iv = iterator.next();
      int i = index$iv++;
      if (i < 0)
        CollectionsKt.throwIndexOverflow(); 
      Cookie cookie = (Cookie)item$iv;
      int index = i, $i$a$-forEachIndexed-BridgeInterceptor$cookieHeader$1$1 = 0;
      if (index > 0)
        $this$cookieHeader_u24lambda_u241.append("; "); 
    } 
    Intrinsics.checkNotNullExpressionValue(stringBuilder1.toString(), "StringBuilder().apply(builderAction).toString()");
    return stringBuilder1.toString();
  }
}
