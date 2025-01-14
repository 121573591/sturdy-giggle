package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import java.util.Objects;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000f\n\002\030\002\n\002\020\000\n\002\020\016\n\000\n\002\020\b\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\n\n\002\020\013\n\002\b\027\n\002\030\002\n\002\b\016\030\0002\0020\001B{\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\006\020\007\032\0020\006\022\006\020\t\032\0020\b\022\b\020\013\032\004\030\0010\n\022\b\020\r\032\004\030\0010\f\022\b\020\017\032\004\030\0010\016\022\006\020\021\032\0020\020\022\b\020\023\032\004\030\0010\022\022\f\020\026\032\b\022\004\022\0020\0250\024\022\f\020\030\032\b\022\004\022\0020\0270\024\022\006\020\032\032\0020\031¢\006\004\b\033\020\034J\021\020\017\032\004\030\0010\016H\007¢\006\004\b\035\020\036J\025\020\030\032\b\022\004\022\0020\0270\024H\007¢\006\004\b\037\020 J\017\020\007\032\0020\006H\007¢\006\004\b!\020\"J\032\020%\032\0020$2\b\020#\032\004\030\0010\001H\002¢\006\004\b%\020&J\027\020*\032\0020$2\006\020'\032\0020\000H\000¢\006\004\b(\020)J\017\020+\032\0020\004H\026¢\006\004\b+\020,J\021\020\r\032\004\030\0010\fH\007¢\006\004\b-\020.J\025\020\026\032\b\022\004\022\0020\0250\024H\007¢\006\004\b/\020 J\021\020\023\032\004\030\0010\022H\007¢\006\004\b0\0201J\017\020\021\032\0020\020H\007¢\006\004\b2\0203J\017\020\032\032\0020\031H\007¢\006\004\b4\0205J\017\020\t\032\0020\bH\007¢\006\004\b6\0207J\021\020\013\032\004\030\0010\nH\007¢\006\004\b8\0209J\017\020:\032\0020\002H\026¢\006\004\b:\020;J\017\020?\032\0020<H\007¢\006\004\b=\020>R\031\020\017\032\004\030\0010\0168\007¢\006\f\n\004\b\017\020@\032\004\b\017\020\036R\035\020\030\032\b\022\004\022\0020\0270\0248G¢\006\f\n\004\b\030\020A\032\004\b\030\020 R\027\020\007\032\0020\0068\007¢\006\f\n\004\b\007\020B\032\004\b\007\020\"R\031\020\r\032\004\030\0010\f8\007¢\006\f\n\004\b\r\020C\032\004\b\r\020.R\035\020\026\032\b\022\004\022\0020\0250\0248G¢\006\f\n\004\b\026\020A\032\004\b\026\020 R\031\020\023\032\004\030\0010\0228\007¢\006\f\n\004\b\023\020D\032\004\b\023\0201R\027\020\021\032\0020\0208\007¢\006\f\n\004\b\021\020E\032\004\b\021\0203R\027\020\032\032\0020\0318\007¢\006\f\n\004\b\032\020F\032\004\b\032\0205R\027\020\t\032\0020\b8\007¢\006\f\n\004\b\t\020G\032\004\b\t\0207R\031\020\013\032\004\030\0010\n8\007¢\006\f\n\004\b\013\020H\032\004\b\013\0209R\027\020?\032\0020<8G¢\006\f\n\004\b?\020I\032\004\b?\020>¨\006J"}, d2 = {"Lokhttp3/Address;", "", "", "uriHost", "", "uriPort", "Lokhttp3/Dns;", "dns", "Ljavax/net/SocketFactory;", "socketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "sslSocketFactory", "Ljavax/net/ssl/HostnameVerifier;", "hostnameVerifier", "Lokhttp3/CertificatePinner;", "certificatePinner", "Lokhttp3/Authenticator;", "proxyAuthenticator", "Ljava/net/Proxy;", "proxy", "", "Lokhttp3/Protocol;", "protocols", "Lokhttp3/ConnectionSpec;", "connectionSpecs", "Ljava/net/ProxySelector;", "proxySelector", "<init>", "(Ljava/lang/String;ILokhttp3/Dns;Ljavax/net/SocketFactory;Ljavax/net/ssl/SSLSocketFactory;Ljavax/net/ssl/HostnameVerifier;Lokhttp3/CertificatePinner;Lokhttp3/Authenticator;Ljava/net/Proxy;Ljava/util/List;Ljava/util/List;Ljava/net/ProxySelector;)V", "-deprecated_certificatePinner", "()Lokhttp3/CertificatePinner;", "-deprecated_connectionSpecs", "()Ljava/util/List;", "-deprecated_dns", "()Lokhttp3/Dns;", "other", "", "equals", "(Ljava/lang/Object;)Z", "that", "equalsNonHost$okhttp", "(Lokhttp3/Address;)Z", "equalsNonHost", "hashCode", "()I", "-deprecated_hostnameVerifier", "()Ljavax/net/ssl/HostnameVerifier;", "-deprecated_protocols", "-deprecated_proxy", "()Ljava/net/Proxy;", "-deprecated_proxyAuthenticator", "()Lokhttp3/Authenticator;", "-deprecated_proxySelector", "()Ljava/net/ProxySelector;", "-deprecated_socketFactory", "()Ljavax/net/SocketFactory;", "-deprecated_sslSocketFactory", "()Ljavax/net/ssl/SSLSocketFactory;", "toString", "()Ljava/lang/String;", "Lokhttp3/HttpUrl;", "-deprecated_url", "()Lokhttp3/HttpUrl;", "url", "Lokhttp3/CertificatePinner;", "Ljava/util/List;", "Lokhttp3/Dns;", "Ljavax/net/ssl/HostnameVerifier;", "Ljava/net/Proxy;", "Lokhttp3/Authenticator;", "Ljava/net/ProxySelector;", "Ljavax/net/SocketFactory;", "Ljavax/net/ssl/SSLSocketFactory;", "Lokhttp3/HttpUrl;", "okhttp"})
public final class Address {
  @NotNull
  private final Dns dns;
  
  @NotNull
  private final SocketFactory socketFactory;
  
  @Nullable
  private final SSLSocketFactory sslSocketFactory;
  
  @Nullable
  private final HostnameVerifier hostnameVerifier;
  
  @Nullable
  private final CertificatePinner certificatePinner;
  
  @NotNull
  private final Authenticator proxyAuthenticator;
  
  @Nullable
  private final Proxy proxy;
  
  @NotNull
  private final ProxySelector proxySelector;
  
  @NotNull
  private final HttpUrl url;
  
  @NotNull
  private final List<Protocol> protocols;
  
  @NotNull
  private final List<ConnectionSpec> connectionSpecs;
  
  public Address(@NotNull String uriHost, int uriPort, @NotNull Dns dns, @NotNull SocketFactory socketFactory, @Nullable SSLSocketFactory sslSocketFactory, @Nullable HostnameVerifier hostnameVerifier, @Nullable CertificatePinner certificatePinner, @NotNull Authenticator proxyAuthenticator, @Nullable Proxy proxy, @NotNull List protocols, @NotNull List connectionSpecs, @NotNull ProxySelector proxySelector) {
    this.dns = dns;
    this.socketFactory = socketFactory;
    this.sslSocketFactory = sslSocketFactory;
    this.hostnameVerifier = hostnameVerifier;
    this.certificatePinner = certificatePinner;
    this.proxyAuthenticator = proxyAuthenticator;
    this.proxy = proxy;
    this.proxySelector = proxySelector;
    this.url = (new HttpUrl.Builder()).scheme((this.sslSocketFactory != null) ? "https" : "http").host(uriHost).port(uriPort).build();
    this.protocols = Util.toImmutableList(protocols);
    this.connectionSpecs = Util.toImmutableList(connectionSpecs);
  }
  
  @JvmName(name = "dns")
  @NotNull
  public final Dns dns() {
    return this.dns;
  }
  
  @JvmName(name = "socketFactory")
  @NotNull
  public final SocketFactory socketFactory() {
    return this.socketFactory;
  }
  
  @JvmName(name = "sslSocketFactory")
  @Nullable
  public final SSLSocketFactory sslSocketFactory() {
    return this.sslSocketFactory;
  }
  
  @JvmName(name = "hostnameVerifier")
  @Nullable
  public final HostnameVerifier hostnameVerifier() {
    return this.hostnameVerifier;
  }
  
  @JvmName(name = "certificatePinner")
  @Nullable
  public final CertificatePinner certificatePinner() {
    return this.certificatePinner;
  }
  
  @JvmName(name = "proxyAuthenticator")
  @NotNull
  public final Authenticator proxyAuthenticator() {
    return this.proxyAuthenticator;
  }
  
  @JvmName(name = "proxy")
  @Nullable
  public final Proxy proxy() {
    return this.proxy;
  }
  
  @JvmName(name = "proxySelector")
  @NotNull
  public final ProxySelector proxySelector() {
    return this.proxySelector;
  }
  
  @JvmName(name = "url")
  @NotNull
  public final HttpUrl url() {
    return this.url;
  }
  
  @JvmName(name = "protocols")
  @NotNull
  public final List<Protocol> protocols() {
    return this.protocols;
  }
  
  @JvmName(name = "connectionSpecs")
  @NotNull
  public final List<ConnectionSpec> connectionSpecs() {
    return this.connectionSpecs;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "url", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_url")
  @NotNull
  public final HttpUrl -deprecated_url() {
    return this.url;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "dns", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_dns")
  @NotNull
  public final Dns -deprecated_dns() {
    return this.dns;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "socketFactory", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_socketFactory")
  @NotNull
  public final SocketFactory -deprecated_socketFactory() {
    return this.socketFactory;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "proxyAuthenticator", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_proxyAuthenticator")
  @NotNull
  public final Authenticator -deprecated_proxyAuthenticator() {
    return this.proxyAuthenticator;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "protocols", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_protocols")
  @NotNull
  public final List<Protocol> -deprecated_protocols() {
    return this.protocols;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "connectionSpecs", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_connectionSpecs")
  @NotNull
  public final List<ConnectionSpec> -deprecated_connectionSpecs() {
    return this.connectionSpecs;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "proxySelector", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_proxySelector")
  @NotNull
  public final ProxySelector -deprecated_proxySelector() {
    return this.proxySelector;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "proxy", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_proxy")
  @Nullable
  public final Proxy -deprecated_proxy() {
    return this.proxy;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "sslSocketFactory", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_sslSocketFactory")
  @Nullable
  public final SSLSocketFactory -deprecated_sslSocketFactory() {
    return this.sslSocketFactory;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "hostnameVerifier", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_hostnameVerifier")
  @Nullable
  public final HostnameVerifier -deprecated_hostnameVerifier() {
    return this.hostnameVerifier;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "certificatePinner", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_certificatePinner")
  @Nullable
  public final CertificatePinner -deprecated_certificatePinner() {
    return this.certificatePinner;
  }
  
  public boolean equals(@Nullable Object other) {
    return (other instanceof Address && 
      Intrinsics.areEqual(this.url, ((Address)other).url) && 
      equalsNonHost$okhttp((Address)other));
  }
  
  public int hashCode() {
    int result = 17;
    result = 31 * result + this.url.hashCode();
    result = 31 * result + this.dns.hashCode();
    result = 31 * result + this.proxyAuthenticator.hashCode();
    result = 31 * result + this.protocols.hashCode();
    result = 31 * result + this.connectionSpecs.hashCode();
    result = 31 * result + this.proxySelector.hashCode();
    result = 31 * result + Objects.hashCode(this.proxy);
    result = 31 * result + Objects.hashCode(this.sslSocketFactory);
    result = 31 * result + Objects.hashCode(this.hostnameVerifier);
    result = 31 * result + Objects.hashCode(this.certificatePinner);
    return result;
  }
  
  public final boolean equalsNonHost$okhttp(@NotNull Address that) {
    Intrinsics.checkNotNullParameter(that, "that");
    return (Intrinsics.areEqual(this.dns, that.dns) && 
      Intrinsics.areEqual(this.proxyAuthenticator, that.proxyAuthenticator) && 
      Intrinsics.areEqual(this.protocols, that.protocols) && 
      Intrinsics.areEqual(this.connectionSpecs, that.connectionSpecs) && 
      Intrinsics.areEqual(this.proxySelector, that.proxySelector) && 
      Intrinsics.areEqual(this.proxy, that.proxy) && 
      Intrinsics.areEqual(this.sslSocketFactory, that.sslSocketFactory) && 
      Intrinsics.areEqual(this.hostnameVerifier, that.hostnameVerifier) && 
      Intrinsics.areEqual(this.certificatePinner, that.certificatePinner) && 
      this.url.port() == that.url.port());
  }
  
  @NotNull
  public String toString() {
    return "Address{" + 
      this.url.host() + ':' + this.url.port() + ", " + (
      (this.proxy != null) ? ("proxy=" + this.proxy) : ("proxySelector=" + this.proxySelector)) + '}';
  }
}
