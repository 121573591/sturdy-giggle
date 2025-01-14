package okhttp3.internal.connection;

import java.io.IOException;
import java.net.UnknownServiceException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ConnectionSpec;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0002\n\002\030\002\n\002\020\000\n\002\020 \n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\020\013\n\002\b\b\n\002\020\b\n\002\b\003\b\000\030\0002\0020\001B\025\022\f\020\004\032\b\022\004\022\0020\0030\002¢\006\004\b\005\020\006J\025\020\t\032\0020\0032\006\020\b\032\0020\007¢\006\004\b\t\020\nJ\025\020\016\032\0020\r2\006\020\f\032\0020\013¢\006\004\b\016\020\017J\027\020\021\032\0020\r2\006\020\020\032\0020\007H\002¢\006\004\b\021\020\022R\032\020\004\032\b\022\004\022\0020\0030\0028\002X\004¢\006\006\n\004\b\004\020\023R\026\020\024\032\0020\r8\002@\002X\016¢\006\006\n\004\b\024\020\025R\026\020\021\032\0020\r8\002@\002X\016¢\006\006\n\004\b\021\020\025R\026\020\027\032\0020\0268\002@\002X\016¢\006\006\n\004\b\027\020\030¨\006\031"}, d2 = {"Lokhttp3/internal/connection/ConnectionSpecSelector;", "", "", "Lokhttp3/ConnectionSpec;", "connectionSpecs", "<init>", "(Ljava/util/List;)V", "Ljavax/net/ssl/SSLSocket;", "sslSocket", "configureSecureSocket", "(Ljavax/net/ssl/SSLSocket;)Lokhttp3/ConnectionSpec;", "Ljava/io/IOException;", "e", "", "connectionFailed", "(Ljava/io/IOException;)Z", "socket", "isFallbackPossible", "(Ljavax/net/ssl/SSLSocket;)Z", "Ljava/util/List;", "isFallback", "Z", "", "nextModeIndex", "I", "okhttp"})
public final class ConnectionSpecSelector {
  @NotNull
  private final List<ConnectionSpec> connectionSpecs;
  
  private int nextModeIndex;
  
  private boolean isFallbackPossible;
  
  private boolean isFallback;
  
  public ConnectionSpecSelector(@NotNull List<ConnectionSpec> connectionSpecs) {
    this.connectionSpecs = connectionSpecs;
  }
  
  @NotNull
  public final ConnectionSpec configureSecureSocket(@NotNull SSLSocket sslSocket) throws IOException {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    ConnectionSpec tlsConfiguration = null;
    for (int i = this.nextModeIndex, j = this.connectionSpecs.size(); i < j; i++) {
      ConnectionSpec connectionSpec = this.connectionSpecs.get(i);
      if (connectionSpec.isCompatible(sslSocket)) {
        tlsConfiguration = connectionSpec;
        this.nextModeIndex = i + 1;
        break;
      } 
    } 
    if (tlsConfiguration == null) {
      Intrinsics.checkNotNull(sslSocket.getEnabledProtocols());
      Intrinsics.checkNotNullExpressionValue(Arrays.toString((Object[])sslSocket.getEnabledProtocols()), "toString(this)");
      throw new UnknownServiceException("Unable to find acceptable protocols. isFallback=" + this.isFallback + ", modes=" + this.connectionSpecs + ", supported protocols=" + Arrays.toString((Object[])sslSocket.getEnabledProtocols()));
    } 
    this.isFallbackPossible = isFallbackPossible(sslSocket);
    tlsConfiguration.apply$okhttp(sslSocket, this.isFallback);
    return tlsConfiguration;
  }
  
  public final boolean connectionFailed(@NotNull IOException e) {
    Intrinsics.checkNotNullParameter(e, "e");
    this.isFallback = true;
    return 
      !this.isFallbackPossible ? false : (
      
      (e instanceof java.net.ProtocolException) ? false : (
      
      (e instanceof java.io.InterruptedIOException) ? false : (
      
      (e instanceof javax.net.ssl.SSLHandshakeException && e.getCause() instanceof java.security.cert.CertificateException) ? false : (
      
      (e instanceof javax.net.ssl.SSLPeerUnverifiedException) ? false : (
      
      (e instanceof javax.net.ssl.SSLException))))));
  }
  
  private final boolean isFallbackPossible(SSLSocket socket) {
    for (int i = this.nextModeIndex, j = this.connectionSpecs.size(); i < j; i++) {
      if (((ConnectionSpec)this.connectionSpecs.get(i)).isCompatible(socket))
        return true; 
    } 
    return false;
  }
}
