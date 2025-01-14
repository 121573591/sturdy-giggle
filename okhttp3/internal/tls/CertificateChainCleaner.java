package okhttp3.internal.tls;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.platform.Platform;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\036\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020 \n\002\030\002\n\000\n\002\020\016\n\002\b\005\b&\030\000 \0132\0020\001:\001\013B\007¢\006\004\b\002\020\003J+\020\t\032\b\022\004\022\0020\0050\0042\f\020\006\032\b\022\004\022\0020\0050\0042\006\020\b\032\0020\007H&¢\006\004\b\t\020\n¨\006\f"}, d2 = {"Lokhttp3/internal/tls/CertificateChainCleaner;", "", "<init>", "()V", "", "Ljava/security/cert/Certificate;", "chain", "", "hostname", "clean", "(Ljava/util/List;Ljava/lang/String;)Ljava/util/List;", "Companion", "okhttp"})
public abstract class CertificateChainCleaner {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  public abstract List<Certificate> clean(@NotNull List<? extends Certificate> paramList, @NotNull String paramString) throws SSLPeerUnverifiedException;
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000&\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\021\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\025\020\007\032\0020\0062\006\020\005\032\0020\004¢\006\004\b\007\020\bJ!\020\007\032\0020\0062\022\020\013\032\n\022\006\b\001\022\0020\n0\t\"\0020\n¢\006\004\b\007\020\f¨\006\r"}, d2 = {"Lokhttp3/internal/tls/CertificateChainCleaner$Companion;", "", "<init>", "()V", "Ljavax/net/ssl/X509TrustManager;", "trustManager", "Lokhttp3/internal/tls/CertificateChainCleaner;", "get", "(Ljavax/net/ssl/X509TrustManager;)Lokhttp3/internal/tls/CertificateChainCleaner;", "", "Ljava/security/cert/X509Certificate;", "caCerts", "([Ljava/security/cert/X509Certificate;)Lokhttp3/internal/tls/CertificateChainCleaner;", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @NotNull
    public final CertificateChainCleaner get(@NotNull X509TrustManager trustManager) {
      Intrinsics.checkNotNullParameter(trustManager, "trustManager");
      return Platform.Companion.get().buildCertificateChainCleaner(trustManager);
    }
    
    @NotNull
    public final CertificateChainCleaner get(@NotNull X509Certificate... caCerts) {
      Intrinsics.checkNotNullParameter(caCerts, "caCerts");
      return new BasicCertificateChainCleaner(new BasicTrustRootIndex(Arrays.<X509Certificate>copyOf(caCerts, caCerts.length)));
    }
  }
}
