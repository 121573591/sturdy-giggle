package okhttp3.internal.http;

import java.net.Proxy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0000\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\016\n\002\b\002\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\004\bÆ\002\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\035\020\t\032\0020\b2\006\020\005\032\0020\0042\006\020\007\032\0020\006¢\006\004\b\t\020\nJ\037\020\f\032\0020\0132\006\020\005\032\0020\0042\006\020\007\032\0020\006H\002¢\006\004\b\f\020\rJ\025\020\020\032\0020\b2\006\020\017\032\0020\016¢\006\004\b\020\020\021¨\006\022"}, d2 = {"Lokhttp3/internal/http/RequestLine;", "", "<init>", "()V", "Lokhttp3/Request;", "request", "Ljava/net/Proxy$Type;", "proxyType", "", "get", "(Lokhttp3/Request;Ljava/net/Proxy$Type;)Ljava/lang/String;", "", "includeAuthorityInRequestLine", "(Lokhttp3/Request;Ljava/net/Proxy$Type;)Z", "Lokhttp3/HttpUrl;", "url", "requestPath", "(Lokhttp3/HttpUrl;)Ljava/lang/String;", "okhttp"})
public final class RequestLine {
  @NotNull
  public static final RequestLine INSTANCE = new RequestLine();
  
  @NotNull
  public final String get(@NotNull Request request, @NotNull Proxy.Type proxyType) {
    Intrinsics.checkNotNullParameter(request, "request");
    Intrinsics.checkNotNullParameter(proxyType, "proxyType");
    StringBuilder stringBuilder1 = new StringBuilder(), $this$get_u24lambda_u240 = stringBuilder1;
    int $i$a$-buildString-RequestLine$get$1 = 0;
    $this$get_u24lambda_u240.append(request.method());
    $this$get_u24lambda_u240.append(' ');
    if (INSTANCE.includeAuthorityInRequestLine(request, proxyType)) {
      $this$get_u24lambda_u240.append(request.url());
    } else {
      $this$get_u24lambda_u240.append(INSTANCE.requestPath(request.url()));
    } 
    $this$get_u24lambda_u240.append(" HTTP/1.1");
    Intrinsics.checkNotNullExpressionValue(stringBuilder1.toString(), "StringBuilder().apply(builderAction).toString()");
    return stringBuilder1.toString();
  }
  
  private final boolean includeAuthorityInRequestLine(Request request, Proxy.Type proxyType) {
    return (!request.isHttps() && proxyType == Proxy.Type.HTTP);
  }
  
  @NotNull
  public final String requestPath(@NotNull HttpUrl url) {
    Intrinsics.checkNotNullParameter(url, "url");
    String path = url.encodedPath();
    String query = url.encodedQuery();
    return (query != null) ? (path + '?' + query) : path;
  }
}
