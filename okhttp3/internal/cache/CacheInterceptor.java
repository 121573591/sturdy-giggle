package okhttp3.internal.cache;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000&\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\b\030\000 \0232\0020\001:\001\023B\021\022\b\020\003\032\004\030\0010\002¢\006\004\b\004\020\005J!\020\n\032\0020\b2\b\020\007\032\004\030\0010\0062\006\020\t\032\0020\bH\002¢\006\004\b\n\020\013J\027\020\016\032\0020\b2\006\020\r\032\0020\fH\026¢\006\004\b\016\020\017R\034\020\003\032\004\030\0010\0028\000X\004¢\006\f\n\004\b\003\020\020\032\004\b\021\020\022¨\006\024"}, d2 = {"Lokhttp3/internal/cache/CacheInterceptor;", "Lokhttp3/Interceptor;", "Lokhttp3/Cache;", "cache", "<init>", "(Lokhttp3/Cache;)V", "Lokhttp3/internal/cache/CacheRequest;", "cacheRequest", "Lokhttp3/Response;", "response", "cacheWritingResponse", "(Lokhttp3/internal/cache/CacheRequest;Lokhttp3/Response;)Lokhttp3/Response;", "Lokhttp3/Interceptor$Chain;", "chain", "intercept", "(Lokhttp3/Interceptor$Chain;)Lokhttp3/Response;", "Lokhttp3/Cache;", "getCache$okhttp", "()Lokhttp3/Cache;", "Companion", "okhttp"})
public final class CacheInterceptor implements Interceptor {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @Nullable
  private final Cache cache;
  
  public CacheInterceptor(@Nullable Cache cache) {
    this.cache = cache;
  }
  
  @Nullable
  public final Cache getCache$okhttp() {
    return this.cache;
  }
  
  @NotNull
  public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
    Intrinsics.checkNotNullParameter(chain, "chain");
    Call call = chain.call();
    Response cacheCandidate = (this.cache != null) ? this.cache.get$okhttp(chain.request()) : null;
    long now = System.currentTimeMillis();
    CacheStrategy strategy = (new CacheStrategy.Factory(now, chain.request(), cacheCandidate)).compute();
    Request networkRequest = strategy.getNetworkRequest();
    Response cacheResponse = strategy.getCacheResponse();
    if (this.cache != null) {
      this.cache.trackResponse$okhttp(strategy);
    } else {
    
    } 
    if (((call instanceof okhttp3.internal.connection.RealCall) ? call : null) == null || ((call instanceof okhttp3.internal.connection.RealCall) ? call : null).getEventListener$okhttp() == null)
      ((call instanceof okhttp3.internal.connection.RealCall) ? call : null).getEventListener$okhttp(); 
    EventListener listener = EventListener.NONE;
    if (cacheCandidate != null && cacheResponse == null)
      if (cacheCandidate.body() != null) {
        Util.closeQuietly((Closeable)cacheCandidate.body());
      } else {
        cacheCandidate.body();
      }  
    if (networkRequest == null && cacheResponse == null) {
      Response response1 = (new Response.Builder()).request(chain.request()).protocol(Protocol.HTTP_1_1).code(504).message("Unsatisfiable Request (only-if-cached)").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1L).receivedResponseAtMillis(System.currentTimeMillis()).build(), it = response1;
      int $i$a$-also-CacheInterceptor$intercept$1 = 0;
      listener.satisfactionFailure(call, it);
      return response1;
    } 
    if (networkRequest == null) {
      Intrinsics.checkNotNull(cacheResponse);
      Response response1 = cacheResponse.newBuilder().cacheResponse(Companion.stripBody(cacheResponse)).build(), it = response1;
      int $i$a$-also-CacheInterceptor$intercept$2 = 0;
      listener.cacheHit(call, it);
      return response1;
    } 
    if (cacheResponse != null) {
      listener.cacheConditionalHit(call, cacheResponse);
    } else if (this.cache != null) {
      listener.cacheMiss(call);
    } 
    Response networkResponse = null;
    try {
      networkResponse = chain.proceed(networkRequest);
    } finally {
      if (cacheCandidate != null)
        if (cacheCandidate.body() != null) {
          Util.closeQuietly((Closeable)cacheCandidate.body());
        } else {
          cacheCandidate.body();
        }  
    } 
    if (cacheResponse != null) {
      if ((networkResponse != null) ? ((networkResponse.code() == 304)) : false) {
        Response response1 = cacheResponse.newBuilder()
          .headers(Companion.combine(cacheResponse.headers(), networkResponse.headers()))
          .sentRequestAtMillis(networkResponse.sentRequestAtMillis())
          .receivedResponseAtMillis(networkResponse.receivedResponseAtMillis())
          .cacheResponse(Companion.stripBody(cacheResponse))
          .networkResponse(Companion.stripBody(networkResponse))
          .build();
        Intrinsics.checkNotNull(networkResponse.body());
        networkResponse.body().close();
        Intrinsics.checkNotNull(this.cache);
        this.cache.trackConditionalCacheHit$okhttp();
        this.cache.update$okhttp(cacheResponse, response1);
        Response response2 = response1, it = response2;
        int $i$a$-also-CacheInterceptor$intercept$3 = 0;
        listener.cacheHit(call, it);
        return response2;
      } 
      if (cacheResponse.body() != null) {
        Util.closeQuietly((Closeable)cacheResponse.body());
      } else {
        cacheResponse.body();
      } 
    } 
    Intrinsics.checkNotNull(networkResponse);
    Response response = networkResponse.newBuilder()
      .cacheResponse(Companion.stripBody(cacheResponse))
      .networkResponse(Companion.stripBody(networkResponse))
      .build();
    if (this.cache != null) {
      if (HttpHeaders.promisesBody(response) && CacheStrategy.Companion.isCacheable(response, networkRequest)) {
        CacheRequest cacheRequest = this.cache.put$okhttp(response);
        Response response1 = cacheWritingResponse(cacheRequest, response), it = response1;
        int $i$a$-also-CacheInterceptor$intercept$4 = 0;
        if (cacheResponse != null)
          listener.cacheMiss(call); 
        return response1;
      } 
      if (HttpMethod.INSTANCE.invalidatesCache(networkRequest.method()))
        try {
          this.cache.remove$okhttp(networkRequest);
        } catch (IOException iOException) {} 
    } 
    return response;
  }
  
  private final Response cacheWritingResponse(CacheRequest cacheRequest, Response response) throws IOException {
    if (cacheRequest == null)
      return response; 
    Sink cacheBodyUnbuffered = cacheRequest.body();
    Intrinsics.checkNotNull(response.body());
    BufferedSource source = response.body().source();
    BufferedSink cacheBody = Okio.buffer(cacheBodyUnbuffered);
    CacheInterceptor$cacheWritingResponse$cacheWritingSource$1 cacheWritingSource = new CacheInterceptor$cacheWritingResponse$cacheWritingSource$1(source, cacheRequest, cacheBody);
    String contentType = Response.header$default(response, "Content-Type", null, 2, null);
    long contentLength = response.body().contentLength();
    return response.newBuilder()
      .body((ResponseBody)new RealResponseBody(contentType, contentLength, Okio.buffer(cacheWritingSource)))
      .build();
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000/\n\000\n\002\030\002\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\003*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\026¢\006\004\b\003\020\004J\037\020\t\032\0020\0072\006\020\006\032\0020\0052\006\020\b\032\0020\007H\026¢\006\004\b\t\020\nJ\017\020\f\032\0020\013H\026¢\006\004\b\f\020\rR\026\020\017\032\0020\0168\002@\002X\016¢\006\006\n\004\b\017\020\020¨\006\021"}, d2 = {"okhttp3/internal/cache/CacheInterceptor.cacheWritingResponse.cacheWritingSource.1", "Lokio/Source;", "", "close", "()V", "Lokio/Buffer;", "sink", "", "byteCount", "read", "(Lokio/Buffer;J)J", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "", "cacheRequestClosed", "Z", "okhttp"})
  public static final class CacheInterceptor$cacheWritingResponse$cacheWritingSource$1 implements Source {
    private boolean cacheRequestClosed;
    
    CacheInterceptor$cacheWritingResponse$cacheWritingSource$1(BufferedSource $source, CacheRequest $cacheRequest, BufferedSink $cacheBody) {}
    
    public long read(@NotNull Buffer sink, long byteCount) throws IOException {
      Intrinsics.checkNotNullParameter(sink, "sink");
      long bytesRead = 0L;
      try {
        bytesRead = this.$source.read(sink, byteCount);
      } catch (IOException e) {
        if (!this.cacheRequestClosed) {
          this.cacheRequestClosed = true;
          this.$cacheRequest.abort();
        } 
        throw e;
      } 
      if (bytesRead == -1L) {
        if (!this.cacheRequestClosed) {
          this.cacheRequestClosed = true;
          this.$cacheBody.close();
        } 
        return -1L;
      } 
      sink.copyTo(this.$cacheBody.getBuffer(), sink.size() - bytesRead, bytesRead);
      this.$cacheBody.emitCompleteSegments();
      return bytesRead;
    }
    
    @NotNull
    public Timeout timeout() {
      return this.$source.timeout();
    }
    
    public void close() throws IOException {
      if (!this.cacheRequestClosed && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
        this.cacheRequestClosed = true;
        this.$cacheRequest.abort();
      } 
      this.$source.close();
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000*\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\004\n\002\020\016\n\000\n\002\020\013\n\002\b\003\n\002\030\002\n\002\b\004\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\037\020\007\032\0020\0042\006\020\005\032\0020\0042\006\020\006\032\0020\004H\002¢\006\004\b\007\020\bJ\027\020\f\032\0020\0132\006\020\n\032\0020\tH\002¢\006\004\b\f\020\rJ\027\020\016\032\0020\0132\006\020\n\032\0020\tH\002¢\006\004\b\016\020\rJ\033\020\021\032\004\030\0010\0172\b\020\020\032\004\030\0010\017H\002¢\006\004\b\021\020\022¨\006\023"}, d2 = {"Lokhttp3/internal/cache/CacheInterceptor$Companion;", "", "<init>", "()V", "Lokhttp3/Headers;", "cachedHeaders", "networkHeaders", "combine", "(Lokhttp3/Headers;Lokhttp3/Headers;)Lokhttp3/Headers;", "", "fieldName", "", "isContentSpecificHeader", "(Ljava/lang/String;)Z", "isEndToEnd", "Lokhttp3/Response;", "response", "stripBody", "(Lokhttp3/Response;)Lokhttp3/Response;", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    private final Response stripBody(Response response) {
      return (((response != null) ? response.body() : null) != null) ? 
        response.newBuilder().body(null).build() : 
        
        response;
    }
    
    private final Headers combine(Headers cachedHeaders, Headers networkHeaders) {
      Headers.Builder result = new Headers.Builder();
      int index, i;
      for (index = 0, i = cachedHeaders.size(); index < i; index++) {
        String fieldName = cachedHeaders.name(index);
        String value = cachedHeaders.value(index);
        if (!StringsKt.equals("Warning", fieldName, true) || !StringsKt.startsWith$default(value, "1", false, 2, null))
          if (isContentSpecificHeader(fieldName) || 
            !isEndToEnd(fieldName) || 
            networkHeaders.get(fieldName) == null)
            result.addLenient$okhttp(fieldName, value);  
      } 
      for (index = 0, i = networkHeaders.size(); index < i; index++) {
        String fieldName = networkHeaders.name(index);
        if (!isContentSpecificHeader(fieldName) && isEndToEnd(fieldName))
          result.addLenient$okhttp(fieldName, networkHeaders.value(index)); 
      } 
      return result.build();
    }
    
    private final boolean isEndToEnd(String fieldName) {
      return (!StringsKt.equals("Connection", fieldName, true) && 
        !StringsKt.equals("Keep-Alive", fieldName, true) && 
        !StringsKt.equals("Proxy-Authenticate", fieldName, true) && 
        !StringsKt.equals("Proxy-Authorization", fieldName, true) && 
        !StringsKt.equals("TE", fieldName, true) && 
        !StringsKt.equals("Trailers", fieldName, true) && 
        !StringsKt.equals("Transfer-Encoding", fieldName, true) && 
        !StringsKt.equals("Upgrade", fieldName, true));
    }
    
    private final boolean isContentSpecificHeader(String fieldName) {
      return (StringsKt.equals("Content-Length", fieldName, true) || 
        StringsKt.equals("Content-Encoding", fieldName, true) || 
        StringsKt.equals("Content-Type", fieldName, true));
    }
  }
}
