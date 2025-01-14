package okhttp3.internal.connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Route;
import okhttp3.internal.Util;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000j\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\013\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\006\n\002\020 \n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\002\n\002\020!\n\002\030\002\n\002\b\006\030\000 -2\0020\001:\002-.B'\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\006\020\007\032\0020\006\022\006\020\t\032\0020\b¢\006\004\b\n\020\013J\020\020\r\032\0020\fH\002¢\006\004\b\r\020\016J\017\020\017\032\0020\fH\002¢\006\004\b\017\020\016J\020\020\021\032\0020\020H\002¢\006\004\b\021\020\022J\017\020\024\032\0020\023H\002¢\006\004\b\024\020\025J\027\020\030\032\0020\0272\006\020\026\032\0020\023H\002¢\006\004\b\030\020\031J!\020\034\032\0020\0272\006\020\033\032\0020\0322\b\020\026\032\004\030\0010\023H\002¢\006\004\b\034\020\035R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\036R\024\020\007\032\0020\0068\002X\004¢\006\006\n\004\b\007\020\037R\024\020\t\032\0020\b8\002X\004¢\006\006\n\004\b\t\020 R\034\020#\032\b\022\004\022\0020\"0!8\002@\002X\016¢\006\006\n\004\b#\020$R\026\020&\032\0020%8\002@\002X\016¢\006\006\n\004\b&\020'R\032\020*\032\b\022\004\022\0020)0(8\002X\004¢\006\006\n\004\b*\020$R\034\020+\032\b\022\004\022\0020\0230!8\002@\002X\016¢\006\006\n\004\b+\020$R\024\020\005\032\0020\0048\002X\004¢\006\006\n\004\b\005\020,¨\006/"}, d2 = {"Lokhttp3/internal/connection/RouteSelector;", "", "Lokhttp3/Address;", "address", "Lokhttp3/internal/connection/RouteDatabase;", "routeDatabase", "Lokhttp3/Call;", "call", "Lokhttp3/EventListener;", "eventListener", "<init>", "(Lokhttp3/Address;Lokhttp3/internal/connection/RouteDatabase;Lokhttp3/Call;Lokhttp3/EventListener;)V", "", "hasNext", "()Z", "hasNextProxy", "Lokhttp3/internal/connection/RouteSelector$Selection;", "next", "()Lokhttp3/internal/connection/RouteSelector$Selection;", "Ljava/net/Proxy;", "nextProxy", "()Ljava/net/Proxy;", "proxy", "", "resetNextInetSocketAddress", "(Ljava/net/Proxy;)V", "Lokhttp3/HttpUrl;", "url", "resetNextProxy", "(Lokhttp3/HttpUrl;Ljava/net/Proxy;)V", "Lokhttp3/Address;", "Lokhttp3/Call;", "Lokhttp3/EventListener;", "", "Ljava/net/InetSocketAddress;", "inetSocketAddresses", "Ljava/util/List;", "", "nextProxyIndex", "I", "", "Lokhttp3/Route;", "postponedRoutes", "proxies", "Lokhttp3/internal/connection/RouteDatabase;", "Companion", "Selection", "okhttp"})
public final class RouteSelector {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final Address address;
  
  @NotNull
  private final RouteDatabase routeDatabase;
  
  @NotNull
  private final Call call;
  
  @NotNull
  private final EventListener eventListener;
  
  @NotNull
  private List<? extends Proxy> proxies;
  
  private int nextProxyIndex;
  
  @NotNull
  private List<? extends InetSocketAddress> inetSocketAddresses;
  
  @NotNull
  private final List<Route> postponedRoutes;
  
  public RouteSelector(@NotNull Address address, @NotNull RouteDatabase routeDatabase, @NotNull Call call, @NotNull EventListener eventListener) {
    this.address = address;
    this.routeDatabase = routeDatabase;
    this.call = call;
    this.eventListener = eventListener;
    this.proxies = CollectionsKt.emptyList();
    this.inetSocketAddresses = CollectionsKt.emptyList();
    this.postponedRoutes = new ArrayList<>();
    resetNextProxy(this.address.url(), this.address.proxy());
  }
  
  public final boolean hasNext() {
    return (hasNextProxy() || (!this.postponedRoutes.isEmpty()));
  }
  
  @NotNull
  public final Selection next() throws IOException {
    if (!hasNext())
      throw new NoSuchElementException(); 
    List<Route> routes = new ArrayList();
    while (hasNextProxy()) {
      Proxy proxy = nextProxy();
      for (InetSocketAddress inetSocketAddress : this.inetSocketAddresses) {
        Route route = new Route(this.address, proxy, inetSocketAddress);
        if (this.routeDatabase.shouldPostpone(route)) {
          this.postponedRoutes.add(route);
          continue;
        } 
        routes.add(route);
      } 
      if (!routes.isEmpty())
        break; 
    } 
    if (routes.isEmpty()) {
      CollectionsKt.addAll(routes, this.postponedRoutes);
      this.postponedRoutes.clear();
    } 
    return new Selection(routes);
  }
  
  private static final List<Proxy> resetNextProxy$selectProxies(Proxy $proxy, HttpUrl $url, RouteSelector this$0) {
    if ($proxy != null)
      return CollectionsKt.listOf($proxy); 
    URI uri = $url.uri();
    if (uri.getHost() == null) {
      Proxy[] arrayOfProxy = new Proxy[1];
      arrayOfProxy[0] = Proxy.NO_PROXY;
      return Util.immutableListOf((Object[])arrayOfProxy);
    } 
    List<Proxy> proxiesOrNull = this$0.address.proxySelector().select(uri);
    List<Proxy> list1 = proxiesOrNull;
    if ((list1 == null || list1.isEmpty())) {
      Proxy[] arrayOfProxy = new Proxy[1];
      arrayOfProxy[0] = Proxy.NO_PROXY;
      return Util.immutableListOf((Object[])arrayOfProxy);
    } 
    Intrinsics.checkNotNullExpressionValue(proxiesOrNull, "proxiesOrNull");
    return Util.toImmutableList(proxiesOrNull);
  }
  
  private final void resetNextProxy(HttpUrl url, Proxy proxy) {
    this.eventListener.proxySelectStart(this.call, url);
    this.proxies = resetNextProxy$selectProxies(proxy, url, this);
    this.nextProxyIndex = 0;
    this.eventListener.proxySelectEnd(this.call, url, this.proxies);
  }
  
  private final boolean hasNextProxy() {
    return (this.nextProxyIndex < this.proxies.size());
  }
  
  private final Proxy nextProxy() throws IOException {
    if (!hasNextProxy())
      throw new SocketException(
          "No route to " + this.address.url().host() + "; exhausted proxy configurations: " + this.proxies); 
    int i = this.nextProxyIndex;
    this.nextProxyIndex = i + 1;
    Proxy result = this.proxies.get(i);
    resetNextInetSocketAddress(result);
    return result;
  }
  
  private final void resetNextInetSocketAddress(Proxy proxy) throws IOException {
    List<? extends InetSocketAddress> mutableInetSocketAddresses = new ArrayList();
    this.inetSocketAddresses = mutableInetSocketAddresses;
    String socketHost = null;
    int socketPort = 0;
    if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
      socketHost = this.address.url().host();
      socketPort = this.address.url().port();
    } else {
      SocketAddress proxyAddress = proxy.address();
      if (!(proxyAddress instanceof InetSocketAddress)) {
        int $i$a$-require-RouteSelector$resetNextInetSocketAddress$1 = 0;
        String str = 
          "Proxy.address() is not an InetSocketAddress: " + proxyAddress.getClass();
        throw new IllegalArgumentException(str.toString());
      } 
      Intrinsics.checkNotNullExpressionValue(proxyAddress, "proxyAddress");
      socketHost = Companion.getSocketHost((InetSocketAddress)proxyAddress);
      socketPort = ((InetSocketAddress)proxyAddress).getPort();
    } 
    if (!((1 <= socketPort) ? ((socketPort < 65536) ? 1 : 0) : 0))
      throw new SocketException("No route to " + socketHost + ':' + socketPort + "; port is out of range"); 
    if (proxy.type() == Proxy.Type.SOCKS) {
      mutableInetSocketAddresses.add(InetSocketAddress.createUnresolved(socketHost, socketPort));
    } else {
      this.eventListener.dnsStart(this.call, socketHost);
      List result = this.address.dns().lookup(socketHost);
      if (result.isEmpty())
        throw new UnknownHostException(this.address.dns() + " returned no addresses for " + socketHost); 
      this.eventListener.dnsEnd(this.call, socketHost, result);
      List addresses = Util.canParseAsIpAddress(socketHost) ? CollectionsKt.listOf(InetAddress.getByName(socketHost)) : result;
      for (InetAddress inetAddress : addresses)
        mutableInetSocketAddresses.add(new InetSocketAddress(inetAddress, socketPort)); 
    } 
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000$\n\002\030\002\n\002\020\000\n\002\020 \n\002\030\002\n\002\b\003\n\002\020\013\n\002\b\004\n\002\020\b\n\002\b\006\030\0002\0020\001B\025\022\f\020\004\032\b\022\004\022\0020\0030\002¢\006\004\b\005\020\006J\020\020\b\032\0020\007H\002¢\006\004\b\b\020\tJ\020\020\n\032\0020\003H\002¢\006\004\b\n\020\013R\026\020\r\032\0020\f8\002@\002X\016¢\006\006\n\004\b\r\020\016R\035\020\004\032\b\022\004\022\0020\0030\0028\006¢\006\f\n\004\b\004\020\017\032\004\b\020\020\021¨\006\022"}, d2 = {"Lokhttp3/internal/connection/RouteSelector$Selection;", "", "", "Lokhttp3/Route;", "routes", "<init>", "(Ljava/util/List;)V", "", "hasNext", "()Z", "next", "()Lokhttp3/Route;", "", "nextRouteIndex", "I", "Ljava/util/List;", "getRoutes", "()Ljava/util/List;", "okhttp"})
  public static final class Selection {
    @NotNull
    private final List<Route> routes;
    
    private int nextRouteIndex;
    
    public Selection(@NotNull List<Route> routes) {
      this.routes = routes;
    }
    
    @NotNull
    public final List<Route> getRoutes() {
      return this.routes;
    }
    
    public final boolean hasNext() {
      return (this.nextRouteIndex < this.routes.size());
    }
    
    @NotNull
    public final Route next() {
      if (!hasNext())
        throw new NoSuchElementException(); 
      int i = this.nextRouteIndex;
      this.nextRouteIndex = i + 1;
      return this.routes.get(i);
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\030\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\020\016\n\002\b\004\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\025\020\b\032\0020\005*\0020\0048F¢\006\006\032\004\b\006\020\007¨\006\t"}, d2 = {"Lokhttp3/internal/connection/RouteSelector$Companion;", "", "<init>", "()V", "Ljava/net/InetSocketAddress;", "", "getSocketHost", "(Ljava/net/InetSocketAddress;)Ljava/lang/String;", "socketHost", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @NotNull
    public final String getSocketHost(@NotNull InetSocketAddress $this$socketHost) {
      InetAddress address;
      Intrinsics.checkNotNullParameter($this$socketHost, "<this>");
      if ($this$socketHost.getAddress() == null) {
        $this$socketHost.getAddress();
        Intrinsics.checkNotNullExpressionValue($this$socketHost.getHostName(), "hostName");
        return $this$socketHost.getHostName();
      } 
      Intrinsics.checkNotNullExpressionValue(address.getHostAddress(), "address.hostAddress");
      return address.getHostAddress();
    }
  }
}
