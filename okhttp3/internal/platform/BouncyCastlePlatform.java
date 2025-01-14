package okhttp3.internal.platform;

import java.security.KeyStore;
import java.security.Provider;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.net.ssl.SSLContext;
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
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.BCSSLSocket;
import org.bouncycastle.jsse.provider.BouncyCastleJsseProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000N\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\030\002\n\000\n\002\020\002\n\002\b\004\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\004\030\000 \0362\0020\001:\001\036B\t\b\002¢\006\004\b\002\020\003J4\020\r\032\0020\f2\006\020\005\032\0020\0042\b\020\007\032\004\030\0010\0062\021\020\013\032\r\022\t\022\0070\t¢\006\002\b\n0\bH\026¢\006\004\b\r\020\016J\031\020\017\032\004\030\0010\0062\006\020\005\032\0020\004H\026¢\006\004\b\017\020\020J\017\020\022\032\0020\021H\026¢\006\004\b\022\020\023J\017\020\025\032\0020\024H\026¢\006\004\b\025\020\026J\031\020\031\032\004\030\0010\0242\006\020\030\032\0020\027H\026¢\006\004\b\031\020\032R\024\020\034\032\0020\0338\002X\004¢\006\006\n\004\b\034\020\035¨\006\037"}, d2 = {"Lokhttp3/internal/platform/BouncyCastlePlatform;", "Lokhttp3/internal/platform/Platform;", "<init>", "()V", "Ljavax/net/ssl/SSLSocket;", "sslSocket", "", "hostname", "", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "protocols", "", "configureTlsExtensions", "(Ljavax/net/ssl/SSLSocket;Ljava/lang/String;Ljava/util/List;)V", "getSelectedProtocol", "(Ljavax/net/ssl/SSLSocket;)Ljava/lang/String;", "Ljavax/net/ssl/SSLContext;", "newSSLContext", "()Ljavax/net/ssl/SSLContext;", "Ljavax/net/ssl/X509TrustManager;", "platformTrustManager", "()Ljavax/net/ssl/X509TrustManager;", "Ljavax/net/ssl/SSLSocketFactory;", "sslSocketFactory", "trustManager", "(Ljavax/net/ssl/SSLSocketFactory;)Ljavax/net/ssl/X509TrustManager;", "Ljava/security/Provider;", "provider", "Ljava/security/Provider;", "Companion", "okhttp"})
@SourceDebugExtension({"SMAP\nBouncyCastlePlatform.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BouncyCastlePlatform.kt\nokhttp3/internal/platform/BouncyCastlePlatform\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,97:1\n37#2,2:98\n*S KotlinDebug\n*F\n+ 1 BouncyCastlePlatform.kt\nokhttp3/internal/platform/BouncyCastlePlatform\n*L\n65#1:98,2\n*E\n"})
public final class BouncyCastlePlatform extends Platform {
  private static final boolean isSupported;
  
  static {
    boolean bool;
  }
  
  @NotNull
  private final Provider provider = (Provider)new BouncyCastleJsseProvider();
  
  @NotNull
  public SSLContext newSSLContext() {
    Intrinsics.checkNotNullExpressionValue(SSLContext.getInstance("TLS", this.provider), "getInstance(\"TLS\", provider)");
    return SSLContext.getInstance("TLS", this.provider);
  }
  
  @NotNull
  public X509TrustManager platformTrustManager() {
    TrustManagerFactory factory = TrustManagerFactory.getInstance(
        "PKIX", "BCJSSE");
    factory.init((KeyStore)null);
    Intrinsics.checkNotNull(factory.getTrustManagers());
    TrustManager[] trustManagers = factory.getTrustManagers();
    if (!((trustManagers.length == 1 && trustManagers[0] instanceof X509TrustManager) ? 1 : 0)) {
      int $i$a$-check-BouncyCastlePlatform$platformTrustManager$1 = 0;
      Intrinsics.checkNotNullExpressionValue(Arrays.toString((Object[])trustManagers), "toString(this)");
      String str = "Unexpected default trust managers: " + Arrays.toString((Object[])trustManagers);
      throw new IllegalStateException(str.toString());
    } 
    Intrinsics.checkNotNull(trustManagers[0], "null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
    return (X509TrustManager)trustManagers[0];
  }
  
  @Nullable
  public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
    Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
    throw new UnsupportedOperationException(
        "clientBuilder.sslSocketFactory(SSLSocketFactory) not supported with BouncyCastle");
  }
  
  public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List<? extends Protocol> protocols) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    Intrinsics.checkNotNullParameter(protocols, "protocols");
    if (sslSocket instanceof BCSSLSocket) {
      BCSSLParameters sslParameters = ((BCSSLSocket)sslSocket).getParameters();
      List<String> names = Platform.Companion.alpnProtocolNames(protocols);
      Collection<String> $this$toTypedArray$iv = names;
      int $i$f$toTypedArray = 0;
      Collection<String> thisCollection$iv = $this$toTypedArray$iv;
      sslParameters.setApplicationProtocols(thisCollection$iv.<String>toArray(new String[0]));
      ((BCSSLSocket)sslSocket).setParameters(sslParameters);
    } else {
      super.configureTlsExtensions(sslSocket, hostname, (List)protocols);
    } 
  }
  
  @Nullable
  public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    String protocol = ((BCSSLSocket)sslSocket).getApplicationProtocol();
    return (sslSocket instanceof BCSSLSocket) ? (((protocol == null) ? true : Intrinsics.areEqual(protocol, "")) ? null : protocol) : super.getSelectedProtocol(sslSocket);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\004\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\017\020\005\032\004\030\0010\004¢\006\004\b\005\020\006R\027\020\b\032\0020\0078\006¢\006\f\n\004\b\b\020\t\032\004\b\b\020\n¨\006\013"}, d2 = {"Lokhttp3/internal/platform/BouncyCastlePlatform$Companion;", "", "<init>", "()V", "Lokhttp3/internal/platform/BouncyCastlePlatform;", "buildIfSupported", "()Lokhttp3/internal/platform/BouncyCastlePlatform;", "", "isSupported", "Z", "()Z", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    public final boolean isSupported() {
      return BouncyCastlePlatform.isSupported;
    }
    
    @Nullable
    public final BouncyCastlePlatform buildIfSupported() {
      return isSupported() ? new BouncyCastlePlatform(null) : null;
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  static {
    try {
      Class.forName("org.bouncycastle.jsse.provider.BouncyCastleJsseProvider", false, Companion.getClass().getClassLoader());
      bool = true;
    } catch (ClassNotFoundException _) {
      bool = false;
    } 
    isSupported = bool;
  }
  
  private BouncyCastlePlatform() {}
}
