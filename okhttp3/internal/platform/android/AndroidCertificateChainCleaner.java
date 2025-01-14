package okhttp3.internal.platform.android;

import android.net.http.X509TrustManagerExtensions;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.SuppressSignatureCheck;
import okhttp3.internal.tls.CertificateChainCleaner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000>\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020 \n\002\030\002\n\000\n\002\020\016\n\002\b\003\n\002\020\000\n\000\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\006\b\001\030\000 \0312\0020\001:\001\031B\027\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007J+\020\r\032\b\022\004\022\0020\t0\b2\f\020\n\032\b\022\004\022\0020\t0\b2\006\020\f\032\0020\013H\027¢\006\004\b\r\020\016J\032\020\022\032\0020\0212\b\020\020\032\004\030\0010\017H\002¢\006\004\b\022\020\023J\017\020\025\032\0020\024H\026¢\006\004\b\025\020\026R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\027R\024\020\005\032\0020\0048\002X\004¢\006\006\n\004\b\005\020\030¨\006\032"}, d2 = {"Lokhttp3/internal/platform/android/AndroidCertificateChainCleaner;", "Lokhttp3/internal/tls/CertificateChainCleaner;", "Ljavax/net/ssl/X509TrustManager;", "trustManager", "Landroid/net/http/X509TrustManagerExtensions;", "x509TrustManagerExtensions", "<init>", "(Ljavax/net/ssl/X509TrustManager;Landroid/net/http/X509TrustManagerExtensions;)V", "", "Ljava/security/cert/Certificate;", "chain", "", "hostname", "clean", "(Ljava/util/List;Ljava/lang/String;)Ljava/util/List;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Ljavax/net/ssl/X509TrustManager;", "Landroid/net/http/X509TrustManagerExtensions;", "Companion", "okhttp"})
@SuppressSignatureCheck
@SourceDebugExtension({"SMAP\nAndroidCertificateChainCleaner.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AndroidCertificateChainCleaner.kt\nokhttp3/internal/platform/android/AndroidCertificateChainCleaner\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,74:1\n37#2,2:75\n1#3:77\n*S KotlinDebug\n*F\n+ 1 AndroidCertificateChainCleaner.kt\nokhttp3/internal/platform/android/AndroidCertificateChainCleaner\n*L\n43#1:75,2\n*E\n"})
public final class AndroidCertificateChainCleaner extends CertificateChainCleaner {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final X509TrustManager trustManager;
  
  @NotNull
  private final X509TrustManagerExtensions x509TrustManagerExtensions;
  
  public AndroidCertificateChainCleaner(@NotNull X509TrustManager trustManager, @NotNull X509TrustManagerExtensions x509TrustManagerExtensions) {
    this.trustManager = trustManager;
    this.x509TrustManagerExtensions = x509TrustManagerExtensions;
  }
  
  @SuppressSignatureCheck
  @NotNull
  public List<Certificate> clean(@NotNull List chain, @NotNull String hostname) throws SSLPeerUnverifiedException {
    Intrinsics.checkNotNullParameter(chain, "chain");
    Intrinsics.checkNotNullParameter(hostname, "hostname");
    Collection $this$toTypedArray$iv = chain;
    int $i$f$toTypedArray = 0;
    Collection thisCollection$iv = $this$toTypedArray$iv;
    X509Certificate[] certificates = (X509Certificate[])thisCollection$iv.toArray((Object[])new X509Certificate[0]);
    try {
      Intrinsics.checkNotNullExpressionValue(this.x509TrustManagerExtensions.checkServerTrusted(certificates, "RSA", hostname), "x509TrustManagerExtensio…ficates, \"RSA\", hostname)");
      return this.x509TrustManagerExtensions.checkServerTrusted(certificates, "RSA", hostname);
    } catch (CertificateException ce) {
      SSLPeerUnverifiedException sSLPeerUnverifiedException1 = new SSLPeerUnverifiedException(ce.getMessage()), $this$clean_u24lambda_u240 = sSLPeerUnverifiedException1;
      int $i$a$-apply-AndroidCertificateChainCleaner$clean$1 = 0;
      $this$clean_u24lambda_u240.initCause(ce);
      throw sSLPeerUnverifiedException1;
    } 
  }
  
  public boolean equals(@Nullable Object other) {
    return (other instanceof AndroidCertificateChainCleaner && ((AndroidCertificateChainCleaner)other).trustManager == this.trustManager);
  }
  
  public int hashCode() {
    return System.identityHashCode(this.trustManager);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\031\020\007\032\004\030\0010\0062\006\020\005\032\0020\004H\007¢\006\004\b\007\020\b¨\006\t"}, d2 = {"Lokhttp3/internal/platform/android/AndroidCertificateChainCleaner$Companion;", "", "<init>", "()V", "Ljavax/net/ssl/X509TrustManager;", "trustManager", "Lokhttp3/internal/platform/android/AndroidCertificateChainCleaner;", "buildIfSupported", "(Ljavax/net/ssl/X509TrustManager;)Lokhttp3/internal/platform/android/AndroidCertificateChainCleaner;", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @SuppressSignatureCheck
    @Nullable
    public final AndroidCertificateChainCleaner buildIfSupported(@NotNull X509TrustManager trustManager) {
      X509TrustManagerExtensions x509TrustManagerExtensions1;
      Intrinsics.checkNotNullParameter(trustManager, "trustManager");
      try {
        x509TrustManagerExtensions1 = new X509TrustManagerExtensions(trustManager);
      } catch (IllegalArgumentException iae) {
        x509TrustManagerExtensions1 = null;
      } 
      X509TrustManagerExtensions extensions = x509TrustManagerExtensions1;
      return (extensions != null) ? new AndroidCertificateChainCleaner(trustManager, extensions) : null;
    }
  }
}
