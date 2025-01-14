package okhttp3.internal.platform;

import java.security.KeyStore;
import java.security.Provider;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Protocol;
import org.conscrypt.Conscrypt;
import org.conscrypt.ConscryptHostnameVerifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000L\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\030\002\n\000\n\002\020\002\n\002\b\004\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\005\030\000  2\0020\001:\002 !B\t\b\002¢\006\004\b\002\020\003J4\020\r\032\0020\f2\006\020\005\032\0020\0042\b\020\007\032\004\030\0010\0062\021\020\013\032\r\022\t\022\0070\t¢\006\002\b\n0\bH\026¢\006\004\b\r\020\016J\031\020\017\032\004\030\0010\0062\006\020\005\032\0020\004H\026¢\006\004\b\017\020\020J\017\020\022\032\0020\021H\026¢\006\004\b\022\020\023J\027\020\027\032\0020\0262\006\020\025\032\0020\024H\026¢\006\004\b\027\020\030J\017\020\031\032\0020\024H\026¢\006\004\b\031\020\032J\031\020\025\032\004\030\0010\0242\006\020\033\032\0020\026H\026¢\006\004\b\025\020\034R\024\020\036\032\0020\0358\002X\004¢\006\006\n\004\b\036\020\037¨\006\""}, d2 = {"Lokhttp3/internal/platform/ConscryptPlatform;", "Lokhttp3/internal/platform/Platform;", "<init>", "()V", "Ljavax/net/ssl/SSLSocket;", "sslSocket", "", "hostname", "", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "protocols", "", "configureTlsExtensions", "(Ljavax/net/ssl/SSLSocket;Ljava/lang/String;Ljava/util/List;)V", "getSelectedProtocol", "(Ljavax/net/ssl/SSLSocket;)Ljava/lang/String;", "Ljavax/net/ssl/SSLContext;", "newSSLContext", "()Ljavax/net/ssl/SSLContext;", "Ljavax/net/ssl/X509TrustManager;", "trustManager", "Ljavax/net/ssl/SSLSocketFactory;", "newSslSocketFactory", "(Ljavax/net/ssl/X509TrustManager;)Ljavax/net/ssl/SSLSocketFactory;", "platformTrustManager", "()Ljavax/net/ssl/X509TrustManager;", "sslSocketFactory", "(Ljavax/net/ssl/SSLSocketFactory;)Ljavax/net/ssl/X509TrustManager;", "Ljava/security/Provider;", "provider", "Ljava/security/Provider;", "Companion", "DisabledHostnameVerifier", "okhttp"})
@SourceDebugExtension({"SMAP\nConscryptPlatform.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConscryptPlatform.kt\nokhttp3/internal/platform/ConscryptPlatform\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,141:1\n37#2,2:142\n*S KotlinDebug\n*F\n+ 1 ConscryptPlatform.kt\nokhttp3/internal/platform/ConscryptPlatform\n*L\n89#1:142,2\n*E\n"})
public final class ConscryptPlatform extends Platform {
  static {
    boolean bool;
  }
  
  private ConscryptPlatform() {
    Intrinsics.checkNotNullExpressionValue(Conscrypt.newProvider(), "newProvider()");
    this.provider = Conscrypt.newProvider();
  }
  
  @NotNull
  public SSLContext newSSLContext() {
    Intrinsics.checkNotNullExpressionValue(SSLContext.getInstance("TLS", this.provider), "getInstance(\"TLS\", provider)");
    return SSLContext.getInstance("TLS", this.provider);
  }
  
  @NotNull
  public X509TrustManager platformTrustManager() {
    TrustManagerFactory trustManagerFactory1 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()), $this$platformTrustManager_u24lambda_u240 = trustManagerFactory1;
    int $i$a$-apply-ConscryptPlatform$platformTrustManager$trustManagers$1 = 0;
    $this$platformTrustManager_u24lambda_u240.init((KeyStore)null);
    Intrinsics.checkNotNull(trustManagerFactory1.getTrustManagers());
    TrustManager[] trustManagers = trustManagerFactory1.getTrustManagers();
    if (!((trustManagers.length == 1 && trustManagers[0] instanceof X509TrustManager) ? 1 : 0)) {
      int $i$a$-check-ConscryptPlatform$platformTrustManager$1 = 0;
      Intrinsics.checkNotNullExpressionValue(Arrays.toString((Object[])trustManagers), "toString(this)");
      String str = "Unexpected default trust managers: " + Arrays.toString((Object[])trustManagers);
      throw new IllegalStateException(str.toString());
    } 
    Intrinsics.checkNotNull(trustManagers[0], "null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
    X509TrustManager x509TrustManager = (X509TrustManager)trustManagers[0];
    Conscrypt.setHostnameVerifier(x509TrustManager, DisabledHostnameVerifier.INSTANCE);
    return x509TrustManager;
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000*\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\021\n\002\030\002\n\000\n\002\020\016\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\004\bÀ\002\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J5\020\f\032\0020\0132\020\020\006\032\f\022\006\b\001\022\0020\005\030\0010\0042\b\020\b\032\004\030\0010\0072\b\020\n\032\004\030\0010\tH\026¢\006\004\b\f\020\rJ!\020\f\032\0020\0132\b\020\b\032\004\030\0010\0072\b\020\n\032\004\030\0010\t¢\006\004\b\f\020\016¨\006\017"}, d2 = {"Lokhttp3/internal/platform/ConscryptPlatform$DisabledHostnameVerifier;", "Lorg/conscrypt/ConscryptHostnameVerifier;", "<init>", "()V", "", "Ljava/security/cert/X509Certificate;", "certs", "", "hostname", "Ljavax/net/ssl/SSLSession;", "session", "", "verify", "([Ljava/security/cert/X509Certificate;Ljava/lang/String;Ljavax/net/ssl/SSLSession;)Z", "(Ljava/lang/String;Ljavax/net/ssl/SSLSession;)Z", "okhttp"})
  public static final class DisabledHostnameVerifier implements ConscryptHostnameVerifier {
    @NotNull
    public static final DisabledHostnameVerifier INSTANCE = new DisabledHostnameVerifier();
    
    public final boolean verify(@Nullable String hostname, @Nullable SSLSession session) {
      return true;
    }
    
    public boolean verify(@Nullable X509Certificate[] certs, @Nullable String hostname, @Nullable SSLSession session) {
      return true;
    }
  }
  
  @Nullable
  public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
    Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
    return null;
  }
  
  public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List<? extends Protocol> protocols) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    Intrinsics.checkNotNullParameter(protocols, "protocols");
    if (Conscrypt.isConscrypt(sslSocket)) {
      Conscrypt.setUseSessionTickets(sslSocket, true);
      List<String> names = Platform.Companion.alpnProtocolNames(protocols);
      Collection<String> $this$toTypedArray$iv = names;
      int $i$f$toTypedArray = 0;
      Collection<String> thisCollection$iv = $this$toTypedArray$iv;
      Conscrypt.setApplicationProtocols(sslSocket, thisCollection$iv.<String>toArray(new String[0]));
    } else {
      super.configureTlsExtensions(sslSocket, hostname, (List)protocols);
    } 
  }
  
  @Nullable
  public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    return Conscrypt.isConscrypt(sslSocket) ? Conscrypt.getApplicationProtocol(sslSocket) : super.getSelectedProtocol(sslSocket);
  }
  
  @NotNull
  public SSLSocketFactory newSslSocketFactory(@NotNull X509TrustManager trustManager) {
    Intrinsics.checkNotNullParameter(trustManager, "trustManager");
    SSLContext sSLContext1 = newSSLContext(), $this$newSslSocketFactory_u24lambda_u242 = sSLContext1;
    int $i$a$-apply-ConscryptPlatform$newSslSocketFactory$1 = 0;
    TrustManager[] arrayOfTrustManager = new TrustManager[1];
    arrayOfTrustManager[0] = trustManager;
    $this$newSslSocketFactory_u24lambda_u242.init(null, arrayOfTrustManager, null);
    Intrinsics.checkNotNullExpressionValue(sSLContext1.getSocketFactory(), "newSSLContext().apply {\n…null)\n    }.socketFactory");
    return sSLContext1.getSocketFactory();
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000$\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\002\b\003\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\006\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J)\020\t\032\0020\b2\006\020\005\032\0020\0042\b\b\002\020\006\032\0020\0042\b\b\002\020\007\032\0020\004¢\006\004\b\t\020\nJ\017\020\f\032\004\030\0010\013¢\006\004\b\f\020\rR\027\020\016\032\0020\b8\006¢\006\f\n\004\b\016\020\017\032\004\b\016\020\020¨\006\021"}, d2 = {"Lokhttp3/internal/platform/ConscryptPlatform$Companion;", "", "<init>", "()V", "", "major", "minor", "patch", "", "atLeastVersion", "(III)Z", "Lokhttp3/internal/platform/ConscryptPlatform;", "buildIfSupported", "()Lokhttp3/internal/platform/ConscryptPlatform;", "isSupported", "Z", "()Z", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    public final boolean isSupported() {
      return ConscryptPlatform.isSupported;
    }
    
    @Nullable
    public final ConscryptPlatform buildIfSupported() {
      return isSupported() ? new ConscryptPlatform(null) : null;
    }
    
    public final boolean atLeastVersion(int major, int minor, int patch) {
      Conscrypt.Version conscryptVersion = Conscrypt.version();
      if (conscryptVersion.major() != major)
        return (conscryptVersion.major() > major); 
      if (conscryptVersion.minor() != minor)
        return (conscryptVersion.minor() > minor); 
      return (conscryptVersion.patch() >= patch);
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final Provider provider;
  
  private static final boolean isSupported;
  
  static {
    try {
      Class.forName("org.conscrypt.Conscrypt$Version", false, Companion.getClass().getClassLoader());
      bool = (Conscrypt.isAvailable() && Companion.atLeastVersion(2, 1, 0)) ? true : false;
    } catch (NoClassDefFoundError e) {
      bool = false;
    } catch (ClassNotFoundException e) {
      bool = false;
    } 
    isSupported = bool;
  }
}
