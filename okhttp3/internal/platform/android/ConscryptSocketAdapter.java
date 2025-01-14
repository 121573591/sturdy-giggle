package okhttp3.internal.platform.android;

import java.util.Collection;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.platform.ConscryptPlatform;
import okhttp3.internal.platform.Platform;
import org.conscrypt.Conscrypt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\000\n\002\020\002\n\002\b\004\n\002\020\013\n\002\b\006\030\000 \0252\0020\001:\001\025B\007¢\006\004\b\002\020\003J/\020\f\032\0020\0132\006\020\005\032\0020\0042\b\020\007\032\004\030\0010\0062\f\020\n\032\b\022\004\022\0020\t0\bH\026¢\006\004\b\f\020\rJ\031\020\016\032\004\030\0010\0062\006\020\005\032\0020\004H\026¢\006\004\b\016\020\017J\017\020\021\032\0020\020H\026¢\006\004\b\021\020\022J\027\020\023\032\0020\0202\006\020\005\032\0020\004H\026¢\006\004\b\023\020\024¨\006\026"}, d2 = {"Lokhttp3/internal/platform/android/ConscryptSocketAdapter;", "Lokhttp3/internal/platform/android/SocketAdapter;", "<init>", "()V", "Ljavax/net/ssl/SSLSocket;", "sslSocket", "", "hostname", "", "Lokhttp3/Protocol;", "protocols", "", "configureTlsExtensions", "(Ljavax/net/ssl/SSLSocket;Ljava/lang/String;Ljava/util/List;)V", "getSelectedProtocol", "(Ljavax/net/ssl/SSLSocket;)Ljava/lang/String;", "", "isSupported", "()Z", "matchesSocket", "(Ljavax/net/ssl/SSLSocket;)Z", "Companion", "okhttp"})
@SourceDebugExtension({"SMAP\nConscryptSocketAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConscryptSocketAdapter.kt\nokhttp3/internal/platform/android/ConscryptSocketAdapter\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,64:1\n37#2,2:65\n*S KotlinDebug\n*F\n+ 1 ConscryptSocketAdapter.kt\nokhttp3/internal/platform/android/ConscryptSocketAdapter\n*L\n51#1:65,2\n*E\n"})
public final class ConscryptSocketAdapter implements SocketAdapter {
  @Nullable
  public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
    return SocketAdapter.DefaultImpls.trustManager(this, sslSocketFactory);
  }
  
  public boolean matchesSocketFactory(@NotNull SSLSocketFactory sslSocketFactory) {
    return SocketAdapter.DefaultImpls.matchesSocketFactory(this, sslSocketFactory);
  }
  
  public boolean matchesSocket(@NotNull SSLSocket sslSocket) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    return Conscrypt.isConscrypt(sslSocket);
  }
  
  public boolean isSupported() {
    return ConscryptPlatform.Companion.isSupported();
  }
  
  @Nullable
  public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    return 
      matchesSocket(sslSocket) ? Conscrypt.getApplicationProtocol(sslSocket) : 
      null;
  }
  
  public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List protocols) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    Intrinsics.checkNotNullParameter(protocols, "protocols");
    if (matchesSocket(sslSocket)) {
      Conscrypt.setUseSessionTickets(sslSocket, true);
      List names = Platform.Companion.alpnProtocolNames(protocols);
      Collection $this$toTypedArray$iv = names;
      int $i$f$toTypedArray = 0;
      Collection thisCollection$iv = $this$toTypedArray$iv;
      Conscrypt.setApplicationProtocols(sslSocket, (String[])thisCollection$iv.toArray((Object[])new String[0]));
    } 
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\005\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\027\020\005\032\0020\0048\006¢\006\f\n\004\b\005\020\006\032\004\b\007\020\b¨\006\t"}, d2 = {"Lokhttp3/internal/platform/android/ConscryptSocketAdapter$Companion;", "", "<init>", "()V", "Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "factory", "Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "getFactory", "()Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @NotNull
    public final DeferredSocketAdapter.Factory getFactory() {
      return ConscryptSocketAdapter.factory;
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private static final DeferredSocketAdapter.Factory factory = new ConscryptSocketAdapter$Companion$factory$1();
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\037\n\000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\003*\001\000\b\n\030\0002\0020\001J\027\020\005\032\0020\0042\006\020\003\032\0020\002H\026¢\006\004\b\005\020\006J\027\020\b\032\0020\0072\006\020\003\032\0020\002H\026¢\006\004\b\b\020\t¨\006\n"}, d2 = {"okhttp3/internal/platform/android/ConscryptSocketAdapter.Companion.factory.1", "Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "Ljavax/net/ssl/SSLSocket;", "sslSocket", "Lokhttp3/internal/platform/android/SocketAdapter;", "create", "(Ljavax/net/ssl/SSLSocket;)Lokhttp3/internal/platform/android/SocketAdapter;", "", "matchesSocket", "(Ljavax/net/ssl/SSLSocket;)Z", "okhttp"})
  public static final class ConscryptSocketAdapter$Companion$factory$1 implements DeferredSocketAdapter.Factory {
    public boolean matchesSocket(@NotNull SSLSocket sslSocket) {
      Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
      return (ConscryptPlatform.Companion.isSupported() && Conscrypt.isConscrypt(sslSocket));
    }
    
    @NotNull
    public SocketAdapter create(@NotNull SSLSocket sslSocket) {
      Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
      return new ConscryptSocketAdapter();
    }
  }
}
