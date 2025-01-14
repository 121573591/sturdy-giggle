package okhttp3.internal.tls;

import java.security.cert.X509Certificate;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\020\n\002\030\002\n\002\020\000\n\002\030\002\n\002\b\004\bf\030\0002\0020\001J\031\020\004\032\004\030\0010\0022\006\020\003\032\0020\002H&¢\006\004\b\004\020\005¨\006\006"}, d2 = {"Lokhttp3/internal/tls/TrustRootIndex;", "", "Ljava/security/cert/X509Certificate;", "cert", "findByIssuerAndSignature", "(Ljava/security/cert/X509Certificate;)Ljava/security/cert/X509Certificate;", "okhttp"})
public interface TrustRootIndex {
  @Nullable
  X509Certificate findByIssuerAndSignature(@NotNull X509Certificate paramX509Certificate);
}
