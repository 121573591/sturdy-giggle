package okhttp3.internal.platform;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.internal.Util;
import okhttp3.internal.platform.android.AndroidLog;
import okhttp3.internal.tls.BasicCertificateChainCleaner;
import okhttp3.internal.tls.BasicTrustRootIndex;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000z\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\b\n\002\b\n\n\002\020\013\n\002\b\004\n\002\020\003\n\002\b\006\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\t\b\026\030\000 ?2\0020\001:\001?B\007¢\006\004\b\002\020\003J\027\020\007\032\0020\0062\006\020\005\032\0020\004H\026¢\006\004\b\007\020\bJ\027\020\f\032\0020\0132\006\020\n\032\0020\tH\026¢\006\004\b\f\020\rJ\027\020\017\032\0020\0162\006\020\n\032\0020\tH\026¢\006\004\b\017\020\020J4\020\027\032\0020\0062\006\020\005\032\0020\0042\b\020\022\032\004\030\0010\0212\021\020\026\032\r\022\t\022\0070\024¢\006\002\b\0250\023H\026¢\006\004\b\027\020\030J'\020\037\032\0020\0062\006\020\032\032\0020\0312\006\020\034\032\0020\0332\006\020\036\032\0020\035H\026¢\006\004\b\037\020 J\r\020!\032\0020\021¢\006\004\b!\020\"J\031\020#\032\004\030\0010\0212\006\020\005\032\0020\004H\026¢\006\004\b#\020$J\031\020&\032\004\030\0010\0012\006\020%\032\0020\021H\026¢\006\004\b&\020'J\027\020)\032\0020(2\006\020\022\032\0020\021H\026¢\006\004\b)\020*J-\020/\032\0020\0062\006\020+\032\0020\0212\b\b\002\020,\032\0020\0352\n\b\002\020.\032\004\030\0010-H\026¢\006\004\b/\0200J!\0202\032\0020\0062\006\020+\032\0020\0212\b\0201\032\004\030\0010\001H\026¢\006\004\b2\0203J\017\0205\032\00204H\026¢\006\004\b5\0206J\027\0208\032\002072\006\020\n\032\0020\tH\026¢\006\004\b8\0209J\017\020:\032\0020\tH\026¢\006\004\b:\020;J\017\020<\032\0020\021H\026¢\006\004\b<\020\"J\031\020\n\032\004\030\0010\t2\006\020=\032\00207H\026¢\006\004\b\n\020>¨\006@"}, d2 = {"Lokhttp3/internal/platform/Platform;", "", "<init>", "()V", "Ljavax/net/ssl/SSLSocket;", "sslSocket", "", "afterHandshake", "(Ljavax/net/ssl/SSLSocket;)V", "Ljavax/net/ssl/X509TrustManager;", "trustManager", "Lokhttp3/internal/tls/CertificateChainCleaner;", "buildCertificateChainCleaner", "(Ljavax/net/ssl/X509TrustManager;)Lokhttp3/internal/tls/CertificateChainCleaner;", "Lokhttp3/internal/tls/TrustRootIndex;", "buildTrustRootIndex", "(Ljavax/net/ssl/X509TrustManager;)Lokhttp3/internal/tls/TrustRootIndex;", "", "hostname", "", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "protocols", "configureTlsExtensions", "(Ljavax/net/ssl/SSLSocket;Ljava/lang/String;Ljava/util/List;)V", "Ljava/net/Socket;", "socket", "Ljava/net/InetSocketAddress;", "address", "", "connectTimeout", "connectSocket", "(Ljava/net/Socket;Ljava/net/InetSocketAddress;I)V", "getPrefix", "()Ljava/lang/String;", "getSelectedProtocol", "(Ljavax/net/ssl/SSLSocket;)Ljava/lang/String;", "closer", "getStackTraceForCloseable", "(Ljava/lang/String;)Ljava/lang/Object;", "", "isCleartextTrafficPermitted", "(Ljava/lang/String;)Z", "message", "level", "", "t", "log", "(Ljava/lang/String;ILjava/lang/Throwable;)V", "stackTrace", "logCloseableLeak", "(Ljava/lang/String;Ljava/lang/Object;)V", "Ljavax/net/ssl/SSLContext;", "newSSLContext", "()Ljavax/net/ssl/SSLContext;", "Ljavax/net/ssl/SSLSocketFactory;", "newSslSocketFactory", "(Ljavax/net/ssl/X509TrustManager;)Ljavax/net/ssl/SSLSocketFactory;", "platformTrustManager", "()Ljavax/net/ssl/X509TrustManager;", "toString", "sslSocketFactory", "(Ljavax/net/ssl/SSLSocketFactory;)Ljavax/net/ssl/X509TrustManager;", "Companion", "okhttp"})
public class Platform {
  @NotNull
  public final String getPrefix() {
    return "OkHttp";
  }
  
  @NotNull
  public SSLContext newSSLContext() {
    Intrinsics.checkNotNullExpressionValue(SSLContext.getInstance("TLS"), "getInstance(\"TLS\")");
    return SSLContext.getInstance("TLS");
  }
  
  @NotNull
  public X509TrustManager platformTrustManager() {
    TrustManagerFactory factory = TrustManagerFactory.getInstance(
        TrustManagerFactory.getDefaultAlgorithm());
    factory.init((KeyStore)null);
    Intrinsics.checkNotNull(factory.getTrustManagers());
    TrustManager[] trustManagers = factory.getTrustManagers();
    if (!((trustManagers.length == 1 && trustManagers[0] instanceof X509TrustManager) ? 1 : 0)) {
      int $i$a$-check-Platform$platformTrustManager$1 = 0;
      Intrinsics.checkNotNullExpressionValue(Arrays.toString((Object[])trustManagers), "toString(this)");
      String str = "Unexpected default trust managers: " + Arrays.toString((Object[])trustManagers);
      throw new IllegalStateException(str.toString());
    } 
    Intrinsics.checkNotNull(trustManagers[0], "null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
    return (X509TrustManager)trustManagers[0];
  }
  
  @Nullable
  public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
    X509TrustManager x509TrustManager;
    Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
    try {
      Object context;
      Class<?> sslContextClass = Class.forName("sun.security.ssl.SSLContextImpl");
      Intrinsics.checkNotNullExpressionValue(sslContextClass, "sslContextClass");
      if (Util.readFieldOrNull(sslSocketFactory, sslContextClass, "context") == null) {
        Util.readFieldOrNull(sslSocketFactory, sslContextClass, "context");
        return null;
      } 
      x509TrustManager = (X509TrustManager)Util.readFieldOrNull(context, X509TrustManager.class, "trustManager");
    } catch (ClassNotFoundException e) {
      x509TrustManager = null;
    } catch (RuntimeException e) {
      if (!Intrinsics.areEqual(e.getClass().getName(), "java.lang.reflect.InaccessibleObjectException"))
        throw e; 
      x509TrustManager = null;
    } 
    return x509TrustManager;
  }
  
  public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List protocols) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    Intrinsics.checkNotNullParameter(protocols, "protocols");
  }
  
  public void afterHandshake(@NotNull SSLSocket sslSocket) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
  }
  
  @Nullable
  public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    return null;
  }
  
  public void connectSocket(@NotNull Socket socket, @NotNull InetSocketAddress address, int connectTimeout) throws IOException {
    Intrinsics.checkNotNullParameter(socket, "socket");
    Intrinsics.checkNotNullParameter(address, "address");
    socket.connect(address, connectTimeout);
  }
  
  public void log(@NotNull String message, int level, @Nullable Throwable t) {
    Intrinsics.checkNotNullParameter(message, "message");
    Level logLevel = (level == 5) ? Level.WARNING : Level.INFO;
    logger.log(logLevel, message, t);
  }
  
  public boolean isCleartextTrafficPermitted(@NotNull String hostname) {
    Intrinsics.checkNotNullParameter(hostname, "hostname");
    return true;
  }
  
  @Nullable
  public Object getStackTraceForCloseable(@NotNull String closer) {
    Intrinsics.checkNotNullParameter(closer, "closer");
    return 
      logger.isLoggable(Level.FINE) ? new Throwable(closer) : 
      null;
  }
  
  public void logCloseableLeak(@NotNull String message, @Nullable Object stackTrace) {
    Intrinsics.checkNotNullParameter(message, "message");
    String logMessage = message;
    if (stackTrace == null)
      logMessage = logMessage + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);"; 
    log(logMessage, 5, (Throwable)stackTrace);
  }
  
  @NotNull
  public CertificateChainCleaner buildCertificateChainCleaner(@NotNull X509TrustManager trustManager) {
    Intrinsics.checkNotNullParameter(trustManager, "trustManager");
    return (CertificateChainCleaner)new BasicCertificateChainCleaner(buildTrustRootIndex(trustManager));
  }
  
  @NotNull
  public TrustRootIndex buildTrustRootIndex(@NotNull X509TrustManager trustManager) {
    Intrinsics.checkNotNullParameter(trustManager, "trustManager");
    Intrinsics.checkNotNullExpressionValue(trustManager.getAcceptedIssuers(), "trustManager.acceptedIssuers");
    X509Certificate[] arrayOfX509Certificate = trustManager.getAcceptedIssuers();
    return (TrustRootIndex)new BasicTrustRootIndex(Arrays.<X509Certificate>copyOf(arrayOfX509Certificate, arrayOfX509Certificate.length));
  }
  
  @NotNull
  public SSLSocketFactory newSslSocketFactory(@NotNull X509TrustManager trustManager) {
    Intrinsics.checkNotNullParameter(trustManager, "trustManager");
    try {
      SSLContext sSLContext1 = newSSLContext(), $this$newSslSocketFactory_u24lambda_u241 = sSLContext1;
      int $i$a$-apply-Platform$newSslSocketFactory$1 = 0;
      TrustManager[] arrayOfTrustManager = new TrustManager[1];
      arrayOfTrustManager[0] = trustManager;
      $this$newSslSocketFactory_u24lambda_u241.init(null, arrayOfTrustManager, null);
      Intrinsics.checkNotNullExpressionValue(sSLContext1.getSocketFactory(), "newSSLContext().apply {\n…ll)\n      }.socketFactory");
      return sSLContext1.getSocketFactory();
    } catch (GeneralSecurityException e) {
      throw new AssertionError("No System TLS: " + e, (Throwable)e);
    } 
  }
  
  @NotNull
  public String toString() {
    Intrinsics.checkNotNullExpressionValue(getClass().getSimpleName(), "javaClass.simpleName");
    return getClass().getSimpleName();
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000N\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020 \n\002\030\002\n\000\n\002\020\016\n\002\b\002\n\002\020\022\n\002\b\002\n\002\030\002\n\002\b\006\n\002\020\002\n\002\b\002\n\002\020\b\n\002\b\003\n\002\020\013\n\002\b\005\n\002\030\002\n\002\b\005\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J!\020\b\032\b\022\004\022\0020\0070\0042\f\020\006\032\b\022\004\022\0020\0050\004¢\006\004\b\b\020\tJ\033\020\013\032\0020\n2\f\020\006\032\b\022\004\022\0020\0050\004¢\006\004\b\013\020\fJ\017\020\016\032\0020\rH\002¢\006\004\b\016\020\017J\017\020\020\032\0020\rH\002¢\006\004\b\020\020\017J\017\020\021\032\0020\rH\002¢\006\004\b\021\020\017J\017\020\022\032\0020\rH\007¢\006\004\b\022\020\017J\027\020\025\032\0020\0242\b\b\002\020\023\032\0020\r¢\006\004\b\025\020\026R\024\020\030\032\0020\0278\006XT¢\006\006\n\004\b\030\020\031R\024\020\032\032\0020\0278\006XT¢\006\006\n\004\b\032\020\031R\021\020\034\032\0020\0338F¢\006\006\032\004\b\034\020\035R\024\020\036\032\0020\0338BX\004¢\006\006\032\004\b\036\020\035R\024\020\037\032\0020\0338BX\004¢\006\006\032\004\b\037\020\035R\024\020 \032\0020\0338BX\004¢\006\006\032\004\b \020\035R\034\020#\032\n \"*\004\030\0010!0!8\002X\004¢\006\006\n\004\b#\020$R\026\020\023\032\0020\r8\002@\002X\016¢\006\006\n\004\b\023\020%¨\006&"}, d2 = {"Lokhttp3/internal/platform/Platform$Companion;", "", "<init>", "()V", "", "Lokhttp3/Protocol;", "protocols", "", "alpnProtocolNames", "(Ljava/util/List;)Ljava/util/List;", "", "concatLengthPrefixed", "(Ljava/util/List;)[B", "Lokhttp3/internal/platform/Platform;", "findAndroidPlatform", "()Lokhttp3/internal/platform/Platform;", "findJvmPlatform", "findPlatform", "get", "platform", "", "resetForTests", "(Lokhttp3/internal/platform/Platform;)V", "", "INFO", "I", "WARN", "", "isAndroid", "()Z", "isBouncyCastlePreferred", "isConscryptPreferred", "isOpenJSSEPreferred", "Ljava/util/logging/Logger;", "kotlin.jvm.PlatformType", "logger", "Ljava/util/logging/Logger;", "Lokhttp3/internal/platform/Platform;", "okhttp"})
  @SourceDebugExtension({"SMAP\nPlatform.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Platform.kt\nokhttp3/internal/platform/Platform$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,286:1\n766#2:287\n857#2,2:288\n1549#2:290\n1620#2,3:291\n*S KotlinDebug\n*F\n+ 1 Platform.kt\nokhttp3/internal/platform/Platform$Companion\n*L\n193#1:287\n193#1:288,2\n193#1:290\n193#1:291,3\n*E\n"})
  public static final class Companion {
    private Companion() {}
    
    @JvmStatic
    @NotNull
    public final Platform get() {
      return Platform.platform;
    }
    
    public final void resetForTests(@NotNull Platform platform) {
      Intrinsics.checkNotNullParameter(platform, "platform");
      Platform.platform = platform;
    }
    
    @NotNull
    public final List<String> alpnProtocolNames(@NotNull List protocols) {
      Intrinsics.checkNotNullParameter(protocols, "protocols");
      Iterable $this$filter$iv = protocols;
      int $i$f$filter = 0;
      Iterable iterable1 = $this$filter$iv;
      Collection<Object> collection = new ArrayList();
      int $i$f$filterTo = 0;
      for (Object element$iv$iv : iterable1) {
        Protocol it = (Protocol)element$iv$iv;
        int $i$a$-filter-Platform$Companion$alpnProtocolNames$1 = 0;
        if ((it != Protocol.HTTP_1_0))
          collection.add(element$iv$iv); 
      } 
      $this$filter$iv = collection;
      int $i$f$map = 0;
      Iterable $this$filterTo$iv$iv = $this$filter$iv;
      Collection<String> destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$filter$iv, 10));
      int $i$f$mapTo = 0;
      for (Object item$iv$iv : $this$filterTo$iv$iv) {
        Protocol protocol = (Protocol)item$iv$iv;
        Collection<String> collection1 = destination$iv$iv;
        int $i$a$-map-Platform$Companion$alpnProtocolNames$2 = 0;
        collection1.add(protocol.toString());
      } 
      return (List<String>)destination$iv$iv;
    }
    
    public final boolean isAndroid() {
      return Intrinsics.areEqual("Dalvik", System.getProperty("java.vm.name"));
    }
    
    private final boolean isConscryptPreferred() {
      String preferredProvider = Security.getProviders()[0].getName();
      return Intrinsics.areEqual("Conscrypt", preferredProvider);
    }
    
    private final boolean isOpenJSSEPreferred() {
      String preferredProvider = Security.getProviders()[0].getName();
      return Intrinsics.areEqual("OpenJSSE", preferredProvider);
    }
    
    private final boolean isBouncyCastlePreferred() {
      String preferredProvider = Security.getProviders()[0].getName();
      return Intrinsics.areEqual("BC", preferredProvider);
    }
    
    private final Platform findPlatform() {
      return isAndroid() ? findAndroidPlatform() : findJvmPlatform();
    }
    
    private final Platform findAndroidPlatform() {
      AndroidLog.INSTANCE.enable();
      if (Android10Platform.Companion.buildIfSupported() == null) {
        Android10Platform.Companion.buildIfSupported();
        Intrinsics.checkNotNull(AndroidPlatform.Companion.buildIfSupported());
      } 
      return AndroidPlatform.Companion.buildIfSupported();
    }
    
    private final Platform findJvmPlatform() {
      if (isConscryptPreferred()) {
        ConscryptPlatform conscrypt = ConscryptPlatform.Companion.buildIfSupported();
        if (conscrypt != null)
          return conscrypt; 
      } 
      if (isBouncyCastlePreferred()) {
        BouncyCastlePlatform bc = BouncyCastlePlatform.Companion.buildIfSupported();
        if (bc != null)
          return bc; 
      } 
      if (isOpenJSSEPreferred()) {
        OpenJSSEPlatform openJSSE = OpenJSSEPlatform.Companion.buildIfSupported();
        if (openJSSE != null)
          return openJSSE; 
      } 
      Jdk9Platform jdk9 = Jdk9Platform.Companion.buildIfSupported();
      if (jdk9 != null)
        return jdk9; 
      Platform jdkWithJettyBoot = Jdk8WithJettyBootPlatform.Companion.buildIfSupported();
      if (jdkWithJettyBoot != null)
        return jdkWithJettyBoot; 
      return new Platform();
    }
    
    @NotNull
    public final byte[] concatLengthPrefixed(@NotNull List<? extends Protocol> protocols) {
      Intrinsics.checkNotNullParameter(protocols, "protocols");
      Buffer result = new Buffer();
      for (String protocol : alpnProtocolNames(protocols)) {
        result.writeByte(protocol.length());
        result.writeUtf8(protocol);
      } 
      return result.readByteArray();
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private static volatile Platform platform = Companion.findPlatform();
  
  public static final int INFO = 4;
  
  public static final int WARN = 5;
  
  private static final Logger logger = Logger.getLogger(OkHttpClient.class.getName());
  
  @JvmStatic
  @NotNull
  public static final Platform get() {
    return Companion.get();
  }
}
