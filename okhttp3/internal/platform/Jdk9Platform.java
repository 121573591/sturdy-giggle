package okhttp3.internal.platform;

import java.util.Collection;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.Protocol;
import okhttp3.internal.SuppressSignatureCheck;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000<\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\030\002\n\000\n\002\020\002\n\002\b\004\n\002\030\002\n\000\n\002\030\002\n\002\b\004\b\026\030\000 \0262\0020\001:\001\026B\007¢\006\004\b\002\020\003J4\020\r\032\0020\f2\006\020\005\032\0020\0042\b\020\007\032\004\030\0010\0062\021\020\013\032\r\022\t\022\0070\t¢\006\002\b\n0\bH\027¢\006\004\b\r\020\016J\031\020\017\032\004\030\0010\0062\006\020\005\032\0020\004H\027¢\006\004\b\017\020\020J\031\020\024\032\004\030\0010\0232\006\020\022\032\0020\021H\026¢\006\004\b\024\020\025¨\006\027"}, d2 = {"Lokhttp3/internal/platform/Jdk9Platform;", "Lokhttp3/internal/platform/Platform;", "<init>", "()V", "Ljavax/net/ssl/SSLSocket;", "sslSocket", "", "hostname", "", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "protocols", "", "configureTlsExtensions", "(Ljavax/net/ssl/SSLSocket;Ljava/lang/String;Ljava/util/List;)V", "getSelectedProtocol", "(Ljavax/net/ssl/SSLSocket;)Ljava/lang/String;", "Ljavax/net/ssl/SSLSocketFactory;", "sslSocketFactory", "Ljavax/net/ssl/X509TrustManager;", "trustManager", "(Ljavax/net/ssl/SSLSocketFactory;)Ljavax/net/ssl/X509TrustManager;", "Companion", "okhttp"})
@SourceDebugExtension({"SMAP\nJdk9Platform.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Jdk9Platform.kt\nokhttp3/internal/platform/Jdk9Platform\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,89:1\n37#2,2:90\n*S KotlinDebug\n*F\n+ 1 Jdk9Platform.kt\nokhttp3/internal/platform/Jdk9Platform\n*L\n36#1:90,2\n*E\n"})
public class Jdk9Platform extends Platform {
  static {
    boolean bool;
  }
  
  @SuppressSignatureCheck
  public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List<? extends Protocol> protocols) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    Intrinsics.checkNotNullParameter(protocols, "protocols");
    SSLParameters sslParameters = sslSocket.getSSLParameters();
    List<String> names = Platform.Companion.alpnProtocolNames(protocols);
    Collection<String> $this$toTypedArray$iv = names;
    int $i$f$toTypedArray = 0;
    Collection<String> thisCollection$iv = $this$toTypedArray$iv;
    sslParameters.setApplicationProtocols(thisCollection$iv.<String>toArray(new String[0]));
    sslSocket.setSSLParameters(sslParameters);
  }
  
  @SuppressSignatureCheck
  @Nullable
  public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    try {
      String protocol = sslSocket.getApplicationProtocol();
      return ((protocol == null) ? true : Intrinsics.areEqual(protocol, "")) ? null : protocol;
    } catch (UnsupportedOperationException e) {
      return null;
    } 
  }
  
  @Nullable
  public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
    Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
    throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on JDK 9+");
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\004\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\017\020\005\032\004\030\0010\004¢\006\004\b\005\020\006R\027\020\b\032\0020\0078\006¢\006\f\n\004\b\b\020\t\032\004\b\b\020\n¨\006\013"}, d2 = {"Lokhttp3/internal/platform/Jdk9Platform$Companion;", "", "<init>", "()V", "Lokhttp3/internal/platform/Jdk9Platform;", "buildIfSupported", "()Lokhttp3/internal/platform/Jdk9Platform;", "", "isAvailable", "Z", "()Z", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    public final boolean isAvailable() {
      return Jdk9Platform.isAvailable;
    }
    
    @Nullable
    public final Jdk9Platform buildIfSupported() {
      return isAvailable() ? new Jdk9Platform() : null;
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  private static final boolean isAvailable;
  
  static {
    String jdkVersion = System.getProperty("java.specification.version");
    Integer majorVersion = (jdkVersion != null) ? StringsKt.toIntOrNull(jdkVersion) : null;
    try {
      SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]);
      bool = true;
    } catch (NoSuchMethodException nsme) {
      bool = false;
    } 
    isAvailable = (majorVersion != null) ? ((majorVersion.intValue() >= 9)) : bool;
  }
}
