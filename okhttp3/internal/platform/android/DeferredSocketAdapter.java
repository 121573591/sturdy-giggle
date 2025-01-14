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

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0006\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\000\n\002\020\002\n\002\b\006\n\002\020\013\n\002\b\t\030\0002\0020\001:\001\034B\017\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J/\020\016\032\0020\r2\006\020\007\032\0020\0062\b\020\t\032\004\030\0010\b2\f\020\f\032\b\022\004\022\0020\0130\nH\026¢\006\004\b\016\020\017J\031\020\020\032\004\030\0010\0012\006\020\007\032\0020\006H\002¢\006\004\b\020\020\021J\031\020\022\032\004\030\0010\b2\006\020\007\032\0020\006H\026¢\006\004\b\022\020\023J\017\020\025\032\0020\024H\026¢\006\004\b\025\020\026J\027\020\027\032\0020\0242\006\020\007\032\0020\006H\026¢\006\004\b\027\020\030R\030\020\031\032\004\030\0010\0018\002@\002X\016¢\006\006\n\004\b\031\020\032R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\033¨\006\035"}, d2 = {"Lokhttp3/internal/platform/android/DeferredSocketAdapter;", "Lokhttp3/internal/platform/android/SocketAdapter;", "Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "socketAdapterFactory", "<init>", "(Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;)V", "Ljavax/net/ssl/SSLSocket;", "sslSocket", "", "hostname", "", "Lokhttp3/Protocol;", "protocols", "", "configureTlsExtensions", "(Ljavax/net/ssl/SSLSocket;Ljava/lang/String;Ljava/util/List;)V", "getDelegate", "(Ljavax/net/ssl/SSLSocket;)Lokhttp3/internal/platform/android/SocketAdapter;", "getSelectedProtocol", "(Ljavax/net/ssl/SSLSocket;)Ljava/lang/String;", "", "isSupported", "()Z", "matchesSocket", "(Ljavax/net/ssl/SSLSocket;)Z", "delegate", "Lokhttp3/internal/platform/android/SocketAdapter;", "Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "Factory", "okhttp"})
public final class DeferredSocketAdapter implements SocketAdapter {
  @NotNull
  private final Factory socketAdapterFactory;
  
  @Nullable
  private SocketAdapter delegate;
  
  public DeferredSocketAdapter(@NotNull Factory socketAdapterFactory) {
    this.socketAdapterFactory = socketAdapterFactory;
  }
  
  @Nullable
  public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
    return SocketAdapter.DefaultImpls.trustManager(this, sslSocketFactory);
  }
  
  public boolean matchesSocketFactory(@NotNull SSLSocketFactory sslSocketFactory) {
    return SocketAdapter.DefaultImpls.matchesSocketFactory(this, sslSocketFactory);
  }
  
  public boolean isSupported() {
    return true;
  }
  
  public boolean matchesSocket(@NotNull SSLSocket sslSocket) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    return this.socketAdapterFactory.matchesSocket(sslSocket);
  }
  
  public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List<? extends Protocol> protocols) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    Intrinsics.checkNotNullParameter(protocols, "protocols");
    if (getDelegate(sslSocket) != null) {
      getDelegate(sslSocket).configureTlsExtensions(sslSocket, hostname, protocols);
    } else {
      getDelegate(sslSocket);
    } 
  }
  
  @Nullable
  public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    getDelegate(sslSocket);
    return (getDelegate(sslSocket) != null) ? getDelegate(sslSocket).getSelectedProtocol(sslSocket) : null;
  }
  
  private final synchronized SocketAdapter getDelegate(SSLSocket sslSocket) {
    if (this.delegate == null && this.socketAdapterFactory.matchesSocket(sslSocket))
      this.delegate = this.socketAdapterFactory.create(sslSocket); 
    return this.delegate;
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\036\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\003\bf\030\0002\0020\001J\027\020\005\032\0020\0042\006\020\003\032\0020\002H&¢\006\004\b\005\020\006J\027\020\b\032\0020\0072\006\020\003\032\0020\002H&¢\006\004\b\b\020\t¨\006\n"}, d2 = {"Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "", "Ljavax/net/ssl/SSLSocket;", "sslSocket", "Lokhttp3/internal/platform/android/SocketAdapter;", "create", "(Ljavax/net/ssl/SSLSocket;)Lokhttp3/internal/platform/android/SocketAdapter;", "", "matchesSocket", "(Ljavax/net/ssl/SSLSocket;)Z", "okhttp"})
  public static interface Factory {
    boolean matchesSocket(@NotNull SSLSocket param1SSLSocket);
    
    @NotNull
    SocketAdapter create(@NotNull SSLSocket param1SSLSocket);
  }
}
