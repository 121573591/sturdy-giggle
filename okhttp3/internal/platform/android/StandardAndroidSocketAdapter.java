package okhttp3.internal.platform.android;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000*\n\002\030\002\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\005\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\005\030\000 \0222\0020\001:\001\022B3\022\016\020\004\032\n\022\006\b\000\022\0020\0030\002\022\016\020\006\032\n\022\006\b\000\022\0020\0050\002\022\n\020\007\032\006\022\002\b\0030\002¢\006\004\b\b\020\tJ\027\020\f\032\0020\0132\006\020\n\032\0020\005H\026¢\006\004\b\f\020\rJ\031\020\017\032\004\030\0010\0162\006\020\n\032\0020\005H\026¢\006\004\b\017\020\020R\030\020\007\032\006\022\002\b\0030\0028\002X\004¢\006\006\n\004\b\007\020\021R\034\020\006\032\n\022\006\b\000\022\0020\0050\0028\002X\004¢\006\006\n\004\b\006\020\021¨\006\023"}, d2 = {"Lokhttp3/internal/platform/android/StandardAndroidSocketAdapter;", "Lokhttp3/internal/platform/android/AndroidSocketAdapter;", "Ljava/lang/Class;", "Ljavax/net/ssl/SSLSocket;", "sslSocketClass", "Ljavax/net/ssl/SSLSocketFactory;", "sslSocketFactoryClass", "paramClass", "<init>", "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;)V", "sslSocketFactory", "", "matchesSocketFactory", "(Ljavax/net/ssl/SSLSocketFactory;)Z", "Ljavax/net/ssl/X509TrustManager;", "trustManager", "(Ljavax/net/ssl/SSLSocketFactory;)Ljavax/net/ssl/X509TrustManager;", "Ljava/lang/Class;", "Companion", "okhttp"})
public final class StandardAndroidSocketAdapter extends AndroidSocketAdapter {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final Class<? super SSLSocketFactory> sslSocketFactoryClass;
  
  @NotNull
  private final Class<?> paramClass;
  
  public StandardAndroidSocketAdapter(@NotNull Class<? super SSLSocket> sslSocketClass, @NotNull Class<? super SSLSocketFactory> sslSocketFactoryClass, @NotNull Class<?> paramClass) {
    super(sslSocketClass);
    this.sslSocketFactoryClass = sslSocketFactoryClass;
    this.paramClass = paramClass;
  }
  
  public boolean matchesSocketFactory(@NotNull SSLSocketFactory sslSocketFactory) {
    Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
    return this.sslSocketFactoryClass.isInstance(sslSocketFactory);
  }
  
  @Nullable
  public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
    Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
    Object context = Util.readFieldOrNull(sslSocketFactory, this.paramClass, 
        "sslParameters");
    Intrinsics.checkNotNull(context);
    X509TrustManager x509TrustManager = (X509TrustManager)Util.readFieldOrNull(context, X509TrustManager.class, "x509TrustManager");
    if (x509TrustManager == null);
    return (X509TrustManager)Util.readFieldOrNull(context, X509TrustManager.class, 
        
        "trustManager");
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\031\020\007\032\004\030\0010\0062\b\b\002\020\005\032\0020\004¢\006\004\b\007\020\b¨\006\t"}, d2 = {"Lokhttp3/internal/platform/android/StandardAndroidSocketAdapter$Companion;", "", "<init>", "()V", "", "packageName", "Lokhttp3/internal/platform/android/SocketAdapter;", "buildIfSupported", "(Ljava/lang/String;)Lokhttp3/internal/platform/android/SocketAdapter;", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @Nullable
    public final SocketAdapter buildIfSupported(@NotNull String packageName) {
      SocketAdapter socketAdapter;
      Intrinsics.checkNotNullParameter(packageName, "packageName");
      try {
        Intrinsics.checkNotNull(Class.forName(packageName + ".OpenSSLSocketImpl"), "null cannot be cast to non-null type java.lang.Class<in javax.net.ssl.SSLSocket>");
        Class<?> sslSocketClass = Class.forName(packageName + ".OpenSSLSocketImpl");
        Intrinsics.checkNotNull(Class.forName(packageName + ".OpenSSLSocketFactoryImpl"), "null cannot be cast to non-null type java.lang.Class<in javax.net.ssl.SSLSocketFactory>");
        Class<?> sslSocketFactoryClass = Class.forName(packageName + ".OpenSSLSocketFactoryImpl");
        Class<?> paramsClass = Class.forName(packageName + ".SSLParametersImpl");
        Intrinsics.checkNotNullExpressionValue(paramsClass, "paramsClass");
        socketAdapter = new StandardAndroidSocketAdapter((Class)sslSocketClass, (Class)sslSocketFactoryClass, paramsClass);
      } catch (Exception e) {
        Platform.Companion.get().log("unable to load android socket classes", 5, e);
        socketAdapter = null;
      } 
      return socketAdapter;
    }
  }
}
