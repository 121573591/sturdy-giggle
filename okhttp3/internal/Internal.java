package okhttp3.internal;

import javax.net.ssl.SSLSocket;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Cache;
import okhttp3.ConnectionSpec;
import okhttp3.Cookie;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 2, xi = 48, d1 = {"\000R\n\002\030\002\n\000\n\002\020\016\n\002\b\006\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\000\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\020\t\n\000\n\002\030\002\n\002\b\005\032\035\020\004\032\0020\0002\006\020\001\032\0020\0002\006\020\003\032\0020\002¢\006\004\b\004\020\005\032%\020\004\032\0020\0002\006\020\001\032\0020\0002\006\020\006\032\0020\0022\006\020\007\032\0020\002¢\006\004\b\004\020\b\032%\020\020\032\0020\0172\006\020\n\032\0020\t2\006\020\f\032\0020\0132\006\020\016\032\0020\r¢\006\004\b\020\020\021\032\037\020\027\032\004\030\0010\0262\006\020\023\032\0020\0222\006\020\025\032\0020\024¢\006\004\b\027\020\030\032\035\020\034\032\0020\0022\006\020\032\032\0020\0312\006\020\033\032\0020\r¢\006\004\b\034\020\035\032'\020#\032\004\030\0010\0312\006\020\037\032\0020\0362\006\020!\032\0020 2\006\020\"\032\0020\002¢\006\004\b#\020$¨\006%"}, d2 = {"Lokhttp3/Headers$Builder;", "builder", "", "line", "addHeaderLenient", "(Lokhttp3/Headers$Builder;Ljava/lang/String;)Lokhttp3/Headers$Builder;", "name", "value", "(Lokhttp3/Headers$Builder;Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Headers$Builder;", "Lokhttp3/ConnectionSpec;", "connectionSpec", "Ljavax/net/ssl/SSLSocket;", "sslSocket", "", "isFallback", "", "applyConnectionSpec", "(Lokhttp3/ConnectionSpec;Ljavax/net/ssl/SSLSocket;Z)V", "Lokhttp3/Cache;", "cache", "Lokhttp3/Request;", "request", "Lokhttp3/Response;", "cacheGet", "(Lokhttp3/Cache;Lokhttp3/Request;)Lokhttp3/Response;", "Lokhttp3/Cookie;", "cookie", "forObsoleteRfc2965", "cookieToString", "(Lokhttp3/Cookie;Z)Ljava/lang/String;", "", "currentTimeMillis", "Lokhttp3/HttpUrl;", "url", "setCookie", "parseCookie", "(JLokhttp3/HttpUrl;Ljava/lang/String;)Lokhttp3/Cookie;", "okhttp"})
@JvmName(name = "Internal")
public final class Internal {
  @Nullable
  public static final Cookie parseCookie(long currentTimeMillis, @NotNull HttpUrl url, @NotNull String setCookie) {
    Intrinsics.checkNotNullParameter(url, "url");
    Intrinsics.checkNotNullParameter(setCookie, "setCookie");
    return Cookie.Companion.parse$okhttp(currentTimeMillis, url, setCookie);
  }
  
  @NotNull
  public static final String cookieToString(@NotNull Cookie cookie, boolean forObsoleteRfc2965) {
    Intrinsics.checkNotNullParameter(cookie, "cookie");
    return cookie.toString$okhttp(forObsoleteRfc2965);
  }
  
  @NotNull
  public static final Headers.Builder addHeaderLenient(@NotNull Headers.Builder builder, @NotNull String line) {
    Intrinsics.checkNotNullParameter(builder, "builder");
    Intrinsics.checkNotNullParameter(line, "line");
    return builder.addLenient$okhttp(line);
  }
  
  @NotNull
  public static final Headers.Builder addHeaderLenient(@NotNull Headers.Builder builder, @NotNull String name, @NotNull String value) {
    Intrinsics.checkNotNullParameter(builder, "builder");
    Intrinsics.checkNotNullParameter(name, "name");
    Intrinsics.checkNotNullParameter(value, "value");
    return builder.addLenient$okhttp(name, value);
  }
  
  @Nullable
  public static final Response cacheGet(@NotNull Cache cache, @NotNull Request request) {
    Intrinsics.checkNotNullParameter(cache, "cache");
    Intrinsics.checkNotNullParameter(request, "request");
    return cache.get$okhttp(request);
  }
  
  public static final void applyConnectionSpec(@NotNull ConnectionSpec connectionSpec, @NotNull SSLSocket sslSocket, boolean isFallback) {
    Intrinsics.checkNotNullParameter(connectionSpec, "connectionSpec");
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    connectionSpec.apply$okhttp(sslSocket, isFallback);
  }
}
