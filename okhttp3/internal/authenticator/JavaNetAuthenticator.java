package okhttp3.internal.authenticator;

import java.io.IOException;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Authenticator;
import okhttp3.Challenge;
import okhttp3.Credentials;
import okhttp3.Dns;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0008\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\030\0002\0020\001B\021\022\b\b\002\020\003\032\0020\002¢\006\004\b\004\020\005J#\020\013\032\004\030\0010\n2\b\020\007\032\004\030\0010\0062\006\020\t\032\0020\bH\026¢\006\004\b\013\020\fJ#\020\022\032\0020\021*\0020\r2\006\020\017\032\0020\0162\006\020\020\032\0020\002H\002¢\006\004\b\022\020\023R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\024¨\006\025"}, d2 = {"Lokhttp3/internal/authenticator/JavaNetAuthenticator;", "Lokhttp3/Authenticator;", "Lokhttp3/Dns;", "defaultDns", "<init>", "(Lokhttp3/Dns;)V", "Lokhttp3/Route;", "route", "Lokhttp3/Response;", "response", "Lokhttp3/Request;", "authenticate", "(Lokhttp3/Route;Lokhttp3/Response;)Lokhttp3/Request;", "Ljava/net/Proxy;", "Lokhttp3/HttpUrl;", "url", "dns", "Ljava/net/InetAddress;", "connectToInetAddress", "(Ljava/net/Proxy;Lokhttp3/HttpUrl;Lokhttp3/Dns;)Ljava/net/InetAddress;", "Lokhttp3/Dns;", "okhttp"})
public final class JavaNetAuthenticator implements Authenticator {
  @NotNull
  private final Dns defaultDns;
  
  public JavaNetAuthenticator(@NotNull Dns defaultDns) {
    this.defaultDns = defaultDns;
  }
  
  @Nullable
  public Request authenticate(@Nullable Route route, @NotNull Response response) throws IOException {
    Intrinsics.checkNotNullParameter(response, "response");
    List challenges = response.challenges();
    Request request = response.request();
    HttpUrl url = request.url();
    boolean proxyAuthorization = (response.code() == 407);
    if (route == null || route.proxy() == null)
      route.proxy(); 
    Proxy proxy = Proxy.NO_PROXY;
    for (Challenge challenge : challenges) {
      if (!StringsKt.equals("Basic", challenge.scheme(), true))
        continue; 
      if (route == null || route.address() == null || route.address().dns() == null)
        route.address().dns(); 
      Dns dns = this.defaultDns;
      Intrinsics.checkNotNull(proxy.address(), "null cannot be cast to non-null type java.net.InetSocketAddress");
      InetSocketAddress proxyAddress = (InetSocketAddress)proxy.address();
      Intrinsics.checkNotNullExpressionValue(proxy, "proxy");
      Intrinsics.checkNotNullExpressionValue(proxy, "proxy");
      PasswordAuthentication auth = proxyAuthorization ? Authenticator.requestPasswordAuthentication(proxyAddress.getHostName(), connectToInetAddress(proxy, url, dns), proxyAddress.getPort(), url.scheme(), challenge.realm(), challenge.scheme(), url.url(), Authenticator.RequestorType.PROXY) : Authenticator.requestPasswordAuthentication(url.host(), connectToInetAddress(proxy, url, dns), 
          url.port(), 
          url.scheme(), 
          challenge.realm(), 
          challenge.scheme(), 
          url.url(), 
          Authenticator.RequestorType.SERVER);
      if (auth != null) {
        String credentialHeader = proxyAuthorization ? "Proxy-Authorization" : "Authorization";
        Intrinsics.checkNotNullExpressionValue(auth.getUserName(), "auth.userName");
        Intrinsics.checkNotNullExpressionValue(auth.getPassword(), "auth.password");
        char[] arrayOfChar = auth.getPassword();
        String credential = Credentials.basic(auth.getUserName(), new String(arrayOfChar), challenge.charset());
        return request.newBuilder()
          .header(credentialHeader, credential)
          .build();
      } 
    } 
    return null;
  }
  
  private final InetAddress connectToInetAddress(Proxy $this$connectToInetAddress, HttpUrl url, Dns dns) throws IOException {
    $this$connectToInetAddress.type();
    Intrinsics.checkNotNull($this$connectToInetAddress.address(), "null cannot be cast to non-null type java.net.InetSocketAddress");
    Intrinsics.checkNotNullExpressionValue(((InetSocketAddress)$this$connectToInetAddress.address()).getAddress(), "address() as InetSocketAddress).address");
    return ((($this$connectToInetAddress.type() == null) ? true : WhenMappings.$EnumSwitchMapping$0[$this$connectToInetAddress.type().ordinal()]) == true) ? (InetAddress)CollectionsKt.first(dns.lookup(url.host())) : ((InetSocketAddress)$this$connectToInetAddress.address()).getAddress();
  }
  
  public JavaNetAuthenticator() {
    this(null, 1, null);
  }
}
