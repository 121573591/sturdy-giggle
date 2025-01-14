package okhttp3.internal.cache;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.http.DatesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\026\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\030\002\n\002\b\f\030\000 \0162\0020\001:\002\016\017B\035\b\000\022\b\020\003\032\004\030\0010\002\022\b\020\005\032\004\030\0010\004¢\006\004\b\006\020\007R\031\020\005\032\004\030\0010\0048\006¢\006\f\n\004\b\005\020\b\032\004\b\t\020\nR\031\020\003\032\004\030\0010\0028\006¢\006\f\n\004\b\003\020\013\032\004\b\f\020\r¨\006\020"}, d2 = {"Lokhttp3/internal/cache/CacheStrategy;", "", "Lokhttp3/Request;", "networkRequest", "Lokhttp3/Response;", "cacheResponse", "<init>", "(Lokhttp3/Request;Lokhttp3/Response;)V", "Lokhttp3/Response;", "getCacheResponse", "()Lokhttp3/Response;", "Lokhttp3/Request;", "getNetworkRequest", "()Lokhttp3/Request;", "Companion", "Factory", "okhttp"})
public final class CacheStrategy {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @Nullable
  private final Request networkRequest;
  
  @Nullable
  private final Response cacheResponse;
  
  public CacheStrategy(@Nullable Request networkRequest, @Nullable Response cacheResponse) {
    this.networkRequest = networkRequest;
    this.cacheResponse = cacheResponse;
  }
  
  @Nullable
  public final Request getNetworkRequest() {
    return this.networkRequest;
  }
  
  @Nullable
  public final Response getCacheResponse() {
    return this.cacheResponse;
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000D\n\002\030\002\n\002\020\000\n\002\020\t\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\004\n\002\020\013\n\002\b\004\n\002\020\b\n\002\b\003\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\r\030\0002\0020\001B!\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\b\020\007\032\004\030\0010\006¢\006\004\b\b\020\tJ\017\020\n\032\0020\002H\002¢\006\004\b\n\020\013J\r\020\r\032\0020\f¢\006\004\b\r\020\016J\017\020\017\032\0020\fH\002¢\006\004\b\017\020\016J\017\020\020\032\0020\002H\002¢\006\004\b\020\020\013J\027\020\022\032\0020\0212\006\020\005\032\0020\004H\002¢\006\004\b\022\020\023J\017\020\024\032\0020\021H\002¢\006\004\b\024\020\025R\026\020\027\032\0020\0268\002@\002X\016¢\006\006\n\004\b\027\020\030R\026\020\007\032\004\030\0010\0068\002X\004¢\006\006\n\004\b\007\020\031R\030\020\033\032\004\030\0010\0328\002@\002X\016¢\006\006\n\004\b\033\020\034R\030\020\036\032\004\030\0010\0358\002@\002X\016¢\006\006\n\004\b\036\020\037R\030\020 \032\004\030\0010\0358\002@\002X\016¢\006\006\n\004\b \020\037R\030\020!\032\004\030\0010\0328\002@\002X\016¢\006\006\n\004\b!\020\034R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\"R\026\020#\032\0020\0028\002@\002X\016¢\006\006\n\004\b#\020\"R\032\020\005\032\0020\0048\000X\004¢\006\f\n\004\b\005\020$\032\004\b%\020&R\026\020'\032\0020\0028\002@\002X\016¢\006\006\n\004\b'\020\"R\030\020(\032\004\030\0010\0358\002@\002X\016¢\006\006\n\004\b(\020\037R\030\020)\032\004\030\0010\0328\002@\002X\016¢\006\006\n\004\b)\020\034¨\006*"}, d2 = {"Lokhttp3/internal/cache/CacheStrategy$Factory;", "", "", "nowMillis", "Lokhttp3/Request;", "request", "Lokhttp3/Response;", "cacheResponse", "<init>", "(JLokhttp3/Request;Lokhttp3/Response;)V", "cacheResponseAge", "()J", "Lokhttp3/internal/cache/CacheStrategy;", "compute", "()Lokhttp3/internal/cache/CacheStrategy;", "computeCandidate", "computeFreshnessLifetime", "", "hasConditions", "(Lokhttp3/Request;)Z", "isFreshnessLifetimeHeuristic", "()Z", "", "ageSeconds", "I", "Lokhttp3/Response;", "", "etag", "Ljava/lang/String;", "Ljava/util/Date;", "expires", "Ljava/util/Date;", "lastModified", "lastModifiedString", "J", "receivedResponseMillis", "Lokhttp3/Request;", "getRequest$okhttp", "()Lokhttp3/Request;", "sentRequestMillis", "servedDate", "servedDateString", "okhttp"})
  public static final class Factory {
    private final long nowMillis;
    
    @NotNull
    private final Request request;
    
    @Nullable
    private final Response cacheResponse;
    
    @Nullable
    private Date servedDate;
    
    @Nullable
    private String servedDateString;
    
    @Nullable
    private Date lastModified;
    
    @Nullable
    private String lastModifiedString;
    
    @Nullable
    private Date expires;
    
    private long sentRequestMillis;
    
    private long receivedResponseMillis;
    
    @Nullable
    private String etag;
    
    private int ageSeconds;
    
    public Factory(long nowMillis, @NotNull Request request, @Nullable Response cacheResponse) {
      this.nowMillis = nowMillis;
      this.request = request;
      this.cacheResponse = cacheResponse;
      this.ageSeconds = -1;
      if (this.cacheResponse != null) {
        this.sentRequestMillis = this.cacheResponse.sentRequestAtMillis();
        this.receivedResponseMillis = this.cacheResponse.receivedResponseAtMillis();
        Headers headers = this.cacheResponse.headers();
        for (int i = 0, j = headers.size(); i < j; i++) {
          String fieldName = headers.name(i);
          String value = headers.value(i);
          if (StringsKt.equals(fieldName, "Date", true)) {
            this.servedDate = DatesKt.toHttpDateOrNull(value);
            this.servedDateString = value;
          } else if (StringsKt.equals(fieldName, "Expires", true)) {
            this.expires = DatesKt.toHttpDateOrNull(value);
          } else if (StringsKt.equals(fieldName, "Last-Modified", true)) {
            this.lastModified = DatesKt.toHttpDateOrNull(value);
            this.lastModifiedString = value;
          } else if (StringsKt.equals(fieldName, "ETag", true)) {
            this.etag = value;
          } else if (StringsKt.equals(fieldName, "Age", true)) {
            this.ageSeconds = Util.toNonNegativeInt(value, -1);
          } 
        } 
      } 
    }
    
    @NotNull
    public final Request getRequest$okhttp() {
      return this.request;
    }
    
    private final boolean isFreshnessLifetimeHeuristic() {
      Intrinsics.checkNotNull(this.cacheResponse);
      return (this.cacheResponse.cacheControl().maxAgeSeconds() == -1 && this.expires == null);
    }
    
    @NotNull
    public final CacheStrategy compute() {
      CacheStrategy candidate = computeCandidate();
      if (candidate.getNetworkRequest() != null && this.request.cacheControl().onlyIfCached())
        return new CacheStrategy(null, null); 
      return candidate;
    }
    
    private final CacheStrategy computeCandidate() {
      if (this.cacheResponse == null)
        return new CacheStrategy(this.request, null); 
      if (this.request.isHttps() && this.cacheResponse.handshake() == null)
        return new CacheStrategy(this.request, null); 
      if (!CacheStrategy.Companion.isCacheable(this.cacheResponse, this.request))
        return new CacheStrategy(this.request, null); 
      CacheControl requestCaching = this.request.cacheControl();
      if (requestCaching.noCache() || hasConditions(this.request))
        return new CacheStrategy(this.request, null); 
      CacheControl responseCaching = this.cacheResponse.cacheControl();
      long ageMillis = cacheResponseAge();
      long freshMillis = computeFreshnessLifetime();
      if (requestCaching.maxAgeSeconds() != -1)
        freshMillis = Math.min(freshMillis, TimeUnit.SECONDS.toMillis(requestCaching.maxAgeSeconds())); 
      long minFreshMillis = 0L;
      if (requestCaching.minFreshSeconds() != -1)
        minFreshMillis = TimeUnit.SECONDS.toMillis(requestCaching.minFreshSeconds()); 
      long maxStaleMillis = 0L;
      if (!responseCaching.mustRevalidate() && requestCaching.maxStaleSeconds() != -1)
        maxStaleMillis = TimeUnit.SECONDS.toMillis(requestCaching.maxStaleSeconds()); 
      if (!responseCaching.noCache() && ageMillis + minFreshMillis < freshMillis + maxStaleMillis) {
        Response.Builder builder = this.cacheResponse.newBuilder();
        if (ageMillis + minFreshMillis >= freshMillis)
          builder.addHeader("Warning", "110 HttpURLConnection \"Response is stale\""); 
        long oneDayMillis = 86400000L;
        if (ageMillis > oneDayMillis && isFreshnessLifetimeHeuristic())
          builder.addHeader("Warning", "113 HttpURLConnection \"Heuristic expiration\""); 
        return new CacheStrategy(null, builder.build());
      } 
      String conditionName = null, conditionValue = null;
      if (this.etag != null) {
        conditionName = "If-None-Match";
        conditionValue = this.etag;
      } else if (this.lastModified != null) {
        conditionName = "If-Modified-Since";
        conditionValue = this.lastModifiedString;
      } else if (this.servedDate != null) {
        conditionName = "If-Modified-Since";
        conditionValue = this.servedDateString;
      } else {
        return new CacheStrategy(this.request, null);
      } 
      Headers.Builder conditionalRequestHeaders = this.request.headers().newBuilder();
      Intrinsics.checkNotNull(conditionValue);
      conditionalRequestHeaders.addLenient$okhttp(conditionName, conditionValue);
      Request conditionalRequest = this.request.newBuilder()
        .headers(conditionalRequestHeaders.build())
        .build();
      return new CacheStrategy(conditionalRequest, this.cacheResponse);
    }
    
    private final long computeFreshnessLifetime() {
      Intrinsics.checkNotNull(this.cacheResponse);
      CacheControl responseCaching = this.cacheResponse.cacheControl();
      if (responseCaching.maxAgeSeconds() != -1)
        return TimeUnit.SECONDS.toMillis(responseCaching.maxAgeSeconds()); 
      Date expires = this.expires;
      if (expires != null) {
        long servedMillis = (this.servedDate != null) ? this.servedDate.getTime() : this.receivedResponseMillis;
        long delta = expires.getTime() - servedMillis;
        return (delta > 0L) ? delta : 0L;
      } 
      if (this.lastModified != null && this.cacheResponse.request().url().query() == null) {
        long servedMillis = (this.servedDate != null) ? this.servedDate.getTime() : this.sentRequestMillis;
        Intrinsics.checkNotNull(this.lastModified);
        long delta = servedMillis - this.lastModified.getTime();
        return (delta > 0L) ? (delta / 10L) : 0L;
      } 
      return 0L;
    }
    
    private final long cacheResponseAge() {
      Date servedDate = this.servedDate;
      long apparentReceivedAge = (servedDate != null) ? 
        Math.max(0L, this.receivedResponseMillis - servedDate.getTime()) : 
        
        0L;
      long receivedAge = (this.ageSeconds != -1) ? 
        Math.max(apparentReceivedAge, TimeUnit.SECONDS.toMillis(this.ageSeconds)) : 
        
        apparentReceivedAge;
      long responseDuration = this.receivedResponseMillis - this.sentRequestMillis;
      long residentDuration = this.nowMillis - this.receivedResponseMillis;
      return receivedAge + responseDuration + residentDuration;
    }
    
    private final boolean hasConditions(Request request) {
      return (request.header("If-Modified-Since") != null || request.header("If-None-Match") != null);
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000 \n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\035\020\t\032\0020\b2\006\020\005\032\0020\0042\006\020\007\032\0020\006¢\006\004\b\t\020\n¨\006\013"}, d2 = {"Lokhttp3/internal/cache/CacheStrategy$Companion;", "", "<init>", "()V", "Lokhttp3/Response;", "response", "Lokhttp3/Request;", "request", "", "isCacheable", "(Lokhttp3/Response;Lokhttp3/Request;)Z", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    public final boolean isCacheable(@NotNull Response response, @NotNull Request request) {
      Intrinsics.checkNotNullParameter(response, "response");
      Intrinsics.checkNotNullParameter(request, "request");
      switch (response.code()) {
        case 200:
        case 203:
        case 204:
        case 300:
        case 301:
        case 308:
        case 404:
        case 405:
        case 410:
        case 414:
        case 501:
          break;
        case 302:
        case 307:
          if (Response.header$default(response, "Expires", null, 2, null) == null && 
            response.cacheControl().maxAgeSeconds() == -1 && 
            !response.cacheControl().isPublic() && 
            !response.cacheControl().isPrivate())
            return false; 
          break;
        default:
          return false;
      } 
      return (!response.cacheControl().noStore() && !request.cacheControl().noStore());
    }
  }
}
