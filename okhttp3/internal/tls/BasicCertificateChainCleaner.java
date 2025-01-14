package okhttp3.internal.tls;

import java.security.GeneralSecurityException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000@\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020 \n\002\030\002\n\000\n\002\020\016\n\002\b\003\n\002\020\000\n\000\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\002\n\002\030\002\n\002\b\007\030\000 \0332\0020\001:\001\033B\017\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J+\020\013\032\b\022\004\022\0020\0070\0062\f\020\b\032\b\022\004\022\0020\0070\0062\006\020\n\032\0020\tH\026¢\006\004\b\013\020\fJ\032\020\020\032\0020\0172\b\020\016\032\004\030\0010\rH\002¢\006\004\b\020\020\021J\017\020\023\032\0020\022H\026¢\006\004\b\023\020\024J\037\020\030\032\0020\0172\006\020\026\032\0020\0252\006\020\027\032\0020\025H\002¢\006\004\b\030\020\031R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\032¨\006\034"}, d2 = {"Lokhttp3/internal/tls/BasicCertificateChainCleaner;", "Lokhttp3/internal/tls/CertificateChainCleaner;", "Lokhttp3/internal/tls/TrustRootIndex;", "trustRootIndex", "<init>", "(Lokhttp3/internal/tls/TrustRootIndex;)V", "", "Ljava/security/cert/Certificate;", "chain", "", "hostname", "clean", "(Ljava/util/List;Ljava/lang/String;)Ljava/util/List;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Ljava/security/cert/X509Certificate;", "toVerify", "signingCert", "verifySignature", "(Ljava/security/cert/X509Certificate;Ljava/security/cert/X509Certificate;)Z", "Lokhttp3/internal/tls/TrustRootIndex;", "Companion", "okhttp"})
public final class BasicCertificateChainCleaner extends CertificateChainCleaner {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final TrustRootIndex trustRootIndex;
  
  private static final int MAX_SIGNERS = 9;
  
  public BasicCertificateChainCleaner(@NotNull TrustRootIndex trustRootIndex) {
    this.trustRootIndex = trustRootIndex;
  }
  
  @NotNull
  public List<Certificate> clean(@NotNull List<?> chain, @NotNull String hostname) throws SSLPeerUnverifiedException {
    Intrinsics.checkNotNullParameter(chain, "chain");
    Intrinsics.checkNotNullParameter(hostname, "hostname");
    Deque queue = new ArrayDeque(chain);
    List<Object> result = new ArrayList();
    Intrinsics.checkNotNullExpressionValue(queue.removeFirst(), "queue.removeFirst()");
    result.add(queue.removeFirst());
    boolean foundTrustedCertificate = false;
    int c;
    label27: for (c = 0; c < 9; c++) {
      Intrinsics.checkNotNull(result.get(result.size() - 1), "null cannot be cast to non-null type java.security.cert.X509Certificate");
      X509Certificate toVerify = (X509Certificate)result.get(result.size() - 1);
      X509Certificate trustedCert = this.trustRootIndex.findByIssuerAndSignature(toVerify);
      if (trustedCert != null) {
        if (result.size() > 1 || !Intrinsics.areEqual(toVerify, trustedCert))
          result.add(trustedCert); 
        if (verifySignature(trustedCert, trustedCert))
          return (List)result; 
        foundTrustedCertificate = true;
      } else {
        Intrinsics.checkNotNullExpressionValue(queue.iterator(), "queue.iterator()");
        Iterator i = queue.iterator();
        while (i.hasNext()) {
          Intrinsics.checkNotNull(i.next(), "null cannot be cast to non-null type java.security.cert.X509Certificate");
          X509Certificate signingCert = (X509Certificate)i.next();
          if (verifySignature(toVerify, signingCert)) {
            i.remove();
            result.add(signingCert);
            continue label27;
          } 
        } 
        if (foundTrustedCertificate)
          return (List)result; 
        throw new SSLPeerUnverifiedException(
            "Failed to find a trusted cert that signed " + toVerify);
      } 
    } 
    throw new SSLPeerUnverifiedException("Certificate chain too long: " + result);
  }
  
  private final boolean verifySignature(X509Certificate toVerify, X509Certificate signingCert) {
    boolean bool;
    if (!Intrinsics.areEqual(toVerify.getIssuerDN(), signingCert.getSubjectDN()))
      return false; 
    try {
      toVerify.verify(signingCert.getPublicKey());
      bool = true;
    } catch (GeneralSecurityException verifyFailed) {
      bool = false;
    } 
    return bool;
  }
  
  public int hashCode() {
    return this.trustRootIndex.hashCode();
  }
  
  public boolean equals(@Nullable Object other) {
    return (other == this) ? true : (
      
      (other instanceof BasicCertificateChainCleaner && Intrinsics.areEqual(((BasicCertificateChainCleaner)other).trustRootIndex, this.trustRootIndex)));
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\024\020\005\032\0020\0048\002XT¢\006\006\n\004\b\005\020\006¨\006\007"}, d2 = {"Lokhttp3/internal/tls/BasicCertificateChainCleaner$Companion;", "", "<init>", "()V", "", "MAX_SIGNERS", "I", "okhttp"})
  public static final class Companion {
    private Companion() {}
  }
}
