package okhttp3.internal.platform.android;

import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000>\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\000\n\002\020\002\n\002\b\004\n\002\020\013\n\002\b\004\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\bf\030\0002\0020\001J/\020\n\032\0020\t2\006\020\003\032\0020\0022\b\020\005\032\004\030\0010\0042\f\020\b\032\b\022\004\022\0020\0070\006H&¢\006\004\b\n\020\013J\031\020\f\032\004\030\0010\0042\006\020\003\032\0020\002H&¢\006\004\b\f\020\rJ\017\020\017\032\0020\016H&¢\006\004\b\017\020\020J\027\020\021\032\0020\0162\006\020\003\032\0020\002H&¢\006\004\b\021\020\022J\027\020\025\032\0020\0162\006\020\024\032\0020\023H\026¢\006\004\b\025\020\026J\031\020\030\032\004\030\0010\0272\006\020\024\032\0020\023H\026¢\006\004\b\030\020\031¨\006\032"}, d2 = {"Lokhttp3/internal/platform/android/SocketAdapter;", "", "Ljavax/net/ssl/SSLSocket;", "sslSocket", "", "hostname", "", "Lokhttp3/Protocol;", "protocols", "", "configureTlsExtensions", "(Ljavax/net/ssl/SSLSocket;Ljava/lang/String;Ljava/util/List;)V", "getSelectedProtocol", "(Ljavax/net/ssl/SSLSocket;)Ljava/lang/String;", "", "isSupported", "()Z", "matchesSocket", "(Ljavax/net/ssl/SSLSocket;)Z", "Ljavax/net/ssl/SSLSocketFactory;", "sslSocketFactory", "matchesSocketFactory", "(Ljavax/net/ssl/SSLSocketFactory;)Z", "Ljavax/net/ssl/X509TrustManager;", "trustManager", "(Ljavax/net/ssl/SSLSocketFactory;)Ljavax/net/ssl/X509TrustManager;", "okhttp"})
public interface SocketAdapter {
  boolean isSupported();
  
  @Nullable
  X509TrustManager trustManager(@NotNull SSLSocketFactory paramSSLSocketFactory);
  
  boolean matchesSocket(@NotNull SSLSocket paramSSLSocket);
  
  boolean matchesSocketFactory(@NotNull SSLSocketFactory paramSSLSocketFactory);
  
  void configureTlsExtensions(@NotNull SSLSocket paramSSLSocket, @Nullable String paramString, @NotNull List<? extends Protocol> paramList);
  
  @Nullable
  String getSelectedProtocol(@NotNull SSLSocket paramSSLSocket);
  
  @Metadata(mv = {1, 8, 0}, k = 3, xi = 48)
  public static final class DefaultImpls {
    @Nullable
    public static X509TrustManager trustManager(@NotNull SocketAdapter $this, @NotNull SSLSocketFactory sslSocketFactory) {
      Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
      return null;
    }
    
    public static boolean matchesSocketFactory(@NotNull SocketAdapter $this, @NotNull SSLSocketFactory sslSocketFactory) {
      Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
      return false;
    }
  }
}
