package okhttp3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000z\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\002\n\002\b\007\n\002\030\002\n\002\b\005\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\007\n\002\030\002\n\002\b\004\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\006\n\002\020\t\n\002\b\005\n\002\030\002\n\002\b\n\n\002\030\002\n\002\b\007\b&\030\000 N2\0020\001:\002NOB\007¢\006\004\b\002\020\003J\037\020\t\032\0020\b2\006\020\005\032\0020\0042\006\020\007\032\0020\006H\026¢\006\004\b\t\020\nJ\037\020\f\032\0020\b2\006\020\005\032\0020\0042\006\020\013\032\0020\006H\026¢\006\004\b\f\020\nJ\027\020\r\032\0020\b2\006\020\005\032\0020\004H\026¢\006\004\b\r\020\016J\027\020\017\032\0020\b2\006\020\005\032\0020\004H\026¢\006\004\b\017\020\016J\037\020\022\032\0020\b2\006\020\005\032\0020\0042\006\020\021\032\0020\020H\026¢\006\004\b\022\020\023J\027\020\024\032\0020\b2\006\020\005\032\0020\004H\026¢\006\004\b\024\020\016J\027\020\025\032\0020\b2\006\020\005\032\0020\004H\026¢\006\004\b\025\020\016J1\020\034\032\0020\b2\006\020\005\032\0020\0042\006\020\027\032\0020\0262\006\020\031\032\0020\0302\b\020\033\032\004\030\0010\032H\026¢\006\004\b\034\020\035J9\020\036\032\0020\b2\006\020\005\032\0020\0042\006\020\027\032\0020\0262\006\020\031\032\0020\0302\b\020\033\032\004\030\0010\0322\006\020\021\032\0020\020H\026¢\006\004\b\036\020\037J'\020 \032\0020\b2\006\020\005\032\0020\0042\006\020\027\032\0020\0262\006\020\031\032\0020\030H\026¢\006\004\b \020!J\037\020$\032\0020\b2\006\020\005\032\0020\0042\006\020#\032\0020\"H\026¢\006\004\b$\020%J\037\020&\032\0020\b2\006\020\005\032\0020\0042\006\020#\032\0020\"H\026¢\006\004\b&\020%J2\020-\032\0020\b2\006\020\005\032\0020\0042\006\020(\032\0020'2\021\020,\032\r\022\t\022\0070*¢\006\002\b+0)H\026¢\006\004\b-\020.J\037\020/\032\0020\b2\006\020\005\032\0020\0042\006\020(\032\0020'H\026¢\006\004\b/\0200J2\0204\032\0020\b2\006\020\005\032\0020\0042\006\0202\032\002012\021\0203\032\r\022\t\022\0070\030¢\006\002\b+0)H\026¢\006\004\b4\0205J\037\0206\032\0020\b2\006\020\005\032\0020\0042\006\0202\032\00201H\026¢\006\004\b6\0207J\037\020:\032\0020\b2\006\020\005\032\0020\0042\006\0209\032\00208H\026¢\006\004\b:\020;J\027\020<\032\0020\b2\006\020\005\032\0020\004H\026¢\006\004\b<\020\016J\037\020=\032\0020\b2\006\020\005\032\0020\0042\006\020\021\032\0020\020H\026¢\006\004\b=\020\023J\037\020@\032\0020\b2\006\020\005\032\0020\0042\006\020?\032\0020>H\026¢\006\004\b@\020AJ\027\020B\032\0020\b2\006\020\005\032\0020\004H\026¢\006\004\bB\020\016J\037\020C\032\0020\b2\006\020\005\032\0020\0042\006\0209\032\00208H\026¢\006\004\bC\020;J\027\020D\032\0020\b2\006\020\005\032\0020\004H\026¢\006\004\bD\020\016J\037\020E\032\0020\b2\006\020\005\032\0020\0042\006\020\021\032\0020\020H\026¢\006\004\bE\020\023J\037\020F\032\0020\b2\006\020\005\032\0020\0042\006\020\013\032\0020\006H\026¢\006\004\bF\020\nJ\027\020G\032\0020\b2\006\020\005\032\0020\004H\026¢\006\004\bG\020\016J\037\020H\032\0020\b2\006\020\005\032\0020\0042\006\020\013\032\0020\006H\026¢\006\004\bH\020\nJ!\020K\032\0020\b2\006\020\005\032\0020\0042\b\020J\032\004\030\0010IH\026¢\006\004\bK\020LJ\027\020M\032\0020\b2\006\020\005\032\0020\004H\026¢\006\004\bM\020\016¨\006P"}, d2 = {"Lokhttp3/EventListener;", "", "<init>", "()V", "Lokhttp3/Call;", "call", "Lokhttp3/Response;", "cachedResponse", "", "cacheConditionalHit", "(Lokhttp3/Call;Lokhttp3/Response;)V", "response", "cacheHit", "cacheMiss", "(Lokhttp3/Call;)V", "callEnd", "Ljava/io/IOException;", "ioe", "callFailed", "(Lokhttp3/Call;Ljava/io/IOException;)V", "callStart", "canceled", "Ljava/net/InetSocketAddress;", "inetSocketAddress", "Ljava/net/Proxy;", "proxy", "Lokhttp3/Protocol;", "protocol", "connectEnd", "(Lokhttp3/Call;Ljava/net/InetSocketAddress;Ljava/net/Proxy;Lokhttp3/Protocol;)V", "connectFailed", "(Lokhttp3/Call;Ljava/net/InetSocketAddress;Ljava/net/Proxy;Lokhttp3/Protocol;Ljava/io/IOException;)V", "connectStart", "(Lokhttp3/Call;Ljava/net/InetSocketAddress;Ljava/net/Proxy;)V", "Lokhttp3/Connection;", "connection", "connectionAcquired", "(Lokhttp3/Call;Lokhttp3/Connection;)V", "connectionReleased", "", "domainName", "", "Ljava/net/InetAddress;", "Lkotlin/jvm/JvmSuppressWildcards;", "inetAddressList", "dnsEnd", "(Lokhttp3/Call;Ljava/lang/String;Ljava/util/List;)V", "dnsStart", "(Lokhttp3/Call;Ljava/lang/String;)V", "Lokhttp3/HttpUrl;", "url", "proxies", "proxySelectEnd", "(Lokhttp3/Call;Lokhttp3/HttpUrl;Ljava/util/List;)V", "proxySelectStart", "(Lokhttp3/Call;Lokhttp3/HttpUrl;)V", "", "byteCount", "requestBodyEnd", "(Lokhttp3/Call;J)V", "requestBodyStart", "requestFailed", "Lokhttp3/Request;", "request", "requestHeadersEnd", "(Lokhttp3/Call;Lokhttp3/Request;)V", "requestHeadersStart", "responseBodyEnd", "responseBodyStart", "responseFailed", "responseHeadersEnd", "responseHeadersStart", "satisfactionFailure", "Lokhttp3/Handshake;", "handshake", "secureConnectEnd", "(Lokhttp3/Call;Lokhttp3/Handshake;)V", "secureConnectStart", "Companion", "Factory", "okhttp"})
public abstract class EventListener {
  public void callStart(@NotNull Call call) {
    Intrinsics.checkNotNullParameter(call, "call");
  }
  
  public void proxySelectStart(@NotNull Call call, @NotNull HttpUrl url) {
    Intrinsics.checkNotNullParameter(call, "call");
    Intrinsics.checkNotNullParameter(url, "url");
  }
  
  public void proxySelectEnd(@NotNull Call call, @NotNull HttpUrl url, @NotNull List proxies) {
    Intrinsics.checkNotNullParameter(call, "call");
    Intrinsics.checkNotNullParameter(url, "url");
    Intrinsics.checkNotNullParameter(proxies, "proxies");
  }
  
  public void dnsStart(@NotNull Call call, @NotNull String domainName) {
    Intrinsics.checkNotNullParameter(call, "call");
    Intrinsics.checkNotNullParameter(domainName, "domainName");
  }
  
  public void dnsEnd(@NotNull Call call, @NotNull String domainName, @NotNull List inetAddressList) {
    Intrinsics.checkNotNullParameter(call, "call");
    Intrinsics.checkNotNullParameter(domainName, "domainName");
    Intrinsics.checkNotNullParameter(inetAddressList, "inetAddressList");
  }
  
  public void connectStart(@NotNull Call call, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy) {
    Intrinsics.checkNotNullParameter(call, "call");
    Intrinsics.checkNotNullParameter(inetSocketAddress, "inetSocketAddress");
    Intrinsics.checkNotNullParameter(proxy, "proxy");
  }
  
  public void secureConnectStart(@NotNull Call call) {
    Intrinsics.checkNotNullParameter(call, "call");
  }
  
  public void secureConnectEnd(@NotNull Call call, @Nullable Handshake handshake) {
    Intrinsics.checkNotNullParameter(call, "call");
  }
  
  public void connectEnd(@NotNull Call call, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy, @Nullable Protocol protocol) {
    Intrinsics.checkNotNullParameter(call, "call");
    Intrinsics.checkNotNullParameter(inetSocketAddress, "inetSocketAddress");
    Intrinsics.checkNotNullParameter(proxy, "proxy");
  }
  
  public void connectFailed(@NotNull Call call, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy, @Nullable Protocol protocol, @NotNull IOException ioe) {
    Intrinsics.checkNotNullParameter(call, "call");
    Intrinsics.checkNotNullParameter(inetSocketAddress, "inetSocketAddress");
    Intrinsics.checkNotNullParameter(proxy, "proxy");
    Intrinsics.checkNotNullParameter(ioe, "ioe");
  }
  
  public void connectionAcquired(@NotNull Call call, @NotNull Connection connection) {
    Intrinsics.checkNotNullParameter(call, "call");
    Intrinsics.checkNotNullParameter(connection, "connection");
  }
  
  public void connectionReleased(@NotNull Call call, @NotNull Connection connection) {
    Intrinsics.checkNotNullParameter(call, "call");
    Intrinsics.checkNotNullParameter(connection, "connection");
  }
  
  public void requestHeadersStart(@NotNull Call call) {
    Intrinsics.checkNotNullParameter(call, "call");
  }
  
  public void requestHeadersEnd(@NotNull Call call, @NotNull Request request) {
    Intrinsics.checkNotNullParameter(call, "call");
    Intrinsics.checkNotNullParameter(request, "request");
  }
  
  public void requestBodyStart(@NotNull Call call) {
    Intrinsics.checkNotNullParameter(call, "call");
  }
  
  public void requestBodyEnd(@NotNull Call call, long byteCount) {
    Intrinsics.checkNotNullParameter(call, "call");
  }
  
  public void requestFailed(@NotNull Call call, @NotNull IOException ioe) {
    Intrinsics.checkNotNullParameter(call, "call");
    Intrinsics.checkNotNullParameter(ioe, "ioe");
  }
  
  public void responseHeadersStart(@NotNull Call call) {
    Intrinsics.checkNotNullParameter(call, "call");
  }
  
  public void responseHeadersEnd(@NotNull Call call, @NotNull Response response) {
    Intrinsics.checkNotNullParameter(call, "call");
    Intrinsics.checkNotNullParameter(response, "response");
  }
  
  public void responseBodyStart(@NotNull Call call) {
    Intrinsics.checkNotNullParameter(call, "call");
  }
  
  public void responseBodyEnd(@NotNull Call call, long byteCount) {
    Intrinsics.checkNotNullParameter(call, "call");
  }
  
  public void responseFailed(@NotNull Call call, @NotNull IOException ioe) {
    Intrinsics.checkNotNullParameter(call, "call");
    Intrinsics.checkNotNullParameter(ioe, "ioe");
  }
  
  public void callEnd(@NotNull Call call) {
    Intrinsics.checkNotNullParameter(call, "call");
  }
  
  public void callFailed(@NotNull Call call, @NotNull IOException ioe) {
    Intrinsics.checkNotNullParameter(call, "call");
    Intrinsics.checkNotNullParameter(ioe, "ioe");
  }
  
  public void canceled(@NotNull Call call) {
    Intrinsics.checkNotNullParameter(call, "call");
  }
  
  public void satisfactionFailure(@NotNull Call call, @NotNull Response response) {
    Intrinsics.checkNotNullParameter(call, "call");
    Intrinsics.checkNotNullParameter(response, "response");
  }
  
  public void cacheHit(@NotNull Call call, @NotNull Response response) {
    Intrinsics.checkNotNullParameter(call, "call");
    Intrinsics.checkNotNullParameter(response, "response");
  }
  
  public void cacheMiss(@NotNull Call call) {
    Intrinsics.checkNotNullParameter(call, "call");
  }
  
  public void cacheConditionalHit(@NotNull Call call, @NotNull Response cachedResponse) {
    Intrinsics.checkNotNullParameter(call, "call");
    Intrinsics.checkNotNullParameter(cachedResponse, "cachedResponse");
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\026\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\bæ\001\030\0002\0020\001J\027\020\005\032\0020\0042\006\020\003\032\0020\002H&¢\006\004\b\005\020\006¨\006\007"}, d2 = {"Lokhttp3/EventListener$Factory;", "", "Lokhttp3/Call;", "call", "Lokhttp3/EventListener;", "create", "(Lokhttp3/Call;)Lokhttp3/EventListener;", "okhttp"})
  public static interface Factory {
    @NotNull
    EventListener create(@NotNull Call param1Call);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\024\020\005\032\0020\0048\006X\004¢\006\006\n\004\b\005\020\006¨\006\007"}, d2 = {"Lokhttp3/EventListener$Companion;", "", "<init>", "()V", "Lokhttp3/EventListener;", "NONE", "Lokhttp3/EventListener;", "okhttp"})
  public static final class Companion {
    private Companion() {}
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @JvmField
  @NotNull
  public static final EventListener NONE = new EventListener$Companion$NONE$1();
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\013\n\000\n\002\030\002\n\000*\001\000\b\n\030\0002\0020\001¨\006\002"}, d2 = {"okhttp3/EventListener.Companion.NONE.1", "Lokhttp3/EventListener;", "okhttp"})
  public static final class EventListener$Companion$NONE$1 extends EventListener {}
}
