package okhttp3.internal.platform.android;

import android.annotation.SuppressLint;
import android.net.ssl.SSLSockets;
import android.os.Build;
import java.io.IOException;
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
import okhttp3.internal.SuppressSignatureCheck;
import okhttp3.internal.platform.Platform;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\000\n\002\020\002\n\002\b\004\n\002\020\013\n\002\b\006\b\007\030\000 \0252\0020\001:\001\025B\007¢\006\004\b\002\020\003J/\020\f\032\0020\0132\006\020\005\032\0020\0042\b\020\007\032\004\030\0010\0062\f\020\n\032\b\022\004\022\0020\t0\bH\027¢\006\004\b\f\020\rJ\031\020\016\032\004\030\0010\0062\006\020\005\032\0020\004H\027¢\006\004\b\016\020\017J\017\020\021\032\0020\020H\026¢\006\004\b\021\020\022J\027\020\023\032\0020\0202\006\020\005\032\0020\004H\026¢\006\004\b\023\020\024¨\006\026"}, d2 = {"Lokhttp3/internal/platform/android/Android10SocketAdapter;", "Lokhttp3/internal/platform/android/SocketAdapter;", "<init>", "()V", "Ljavax/net/ssl/SSLSocket;", "sslSocket", "", "hostname", "", "Lokhttp3/Protocol;", "protocols", "", "configureTlsExtensions", "(Ljavax/net/ssl/SSLSocket;Ljava/lang/String;Ljava/util/List;)V", "getSelectedProtocol", "(Ljavax/net/ssl/SSLSocket;)Ljava/lang/String;", "", "isSupported", "()Z", "matchesSocket", "(Ljavax/net/ssl/SSLSocket;)Z", "Companion", "okhttp"})
@SuppressLint({"NewApi"})
@SuppressSignatureCheck
@SourceDebugExtension({"SMAP\nAndroid10SocketAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Android10SocketAdapter.kt\nokhttp3/internal/platform/android/Android10SocketAdapter\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,77:1\n37#2,2:78\n*S KotlinDebug\n*F\n+ 1 Android10SocketAdapter.kt\nokhttp3/internal/platform/android/Android10SocketAdapter\n*L\n60#1:78,2\n*E\n"})
public final class Android10SocketAdapter implements SocketAdapter {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @Nullable
  public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
    return SocketAdapter.DefaultImpls.trustManager(this, sslSocketFactory);
  }
  
  public boolean matchesSocketFactory(@NotNull SSLSocketFactory sslSocketFactory) {
    return SocketAdapter.DefaultImpls.matchesSocketFactory(this, sslSocketFactory);
  }
  
  public boolean matchesSocket(@NotNull SSLSocket sslSocket) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    return SSLSockets.isSupportedSocket(sslSocket);
  }
  
  public boolean isSupported() {
    return Companion.isSupported();
  }
  
  @SuppressLint({"NewApi"})
  @Nullable
  public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    String protocol = sslSocket.getApplicationProtocol();
    return ((protocol == null) ? true : Intrinsics.areEqual(protocol, "")) ? null : 
      protocol;
  }
  
  @SuppressLint({"NewApi"})
  public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List protocols) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    Intrinsics.checkNotNullParameter(protocols, "protocols");
    try {
      SSLSockets.setUseSessionTickets(sslSocket, true);
      SSLParameters sslParameters = sslSocket.getSSLParameters();
      Collection $this$toTypedArray$iv = Platform.Companion.alpnProtocolNames(protocols);
      int $i$f$toTypedArray = 0;
      Collection thisCollection$iv = $this$toTypedArray$iv;
      sslParameters.setApplicationProtocols((String[])thisCollection$iv.toArray((Object[])new String[0]));
      sslSocket.setSSLParameters(sslParameters);
    } catch (IllegalArgumentException iae) {
      throw new IOException("Android internal error", (Throwable)iae);
    } 
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\017\020\005\032\004\030\0010\004¢\006\004\b\005\020\006J\r\020\b\032\0020\007¢\006\004\b\b\020\t¨\006\n"}, d2 = {"Lokhttp3/internal/platform/android/Android10SocketAdapter$Companion;", "", "<init>", "()V", "Lokhttp3/internal/platform/android/SocketAdapter;", "buildIfSupported", "()Lokhttp3/internal/platform/android/SocketAdapter;", "", "isSupported", "()Z", "okhttp"})
  @SuppressSignatureCheck
  public static final class Companion {
    private Companion() {}
    
    @Nullable
    public final SocketAdapter buildIfSupported() {
      return isSupported() ? new Android10SocketAdapter() : null;
    }
    
    public final boolean isSupported() {
      return (Platform.Companion.isAndroid() && Build.VERSION.SDK_INT >= 29);
    }
  }
}
