package okhttp3;

import java.io.IOException;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000F\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\000\n\002\030\002\n\002\b\006\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\004\n\002\030\002\n\002\b\t\n\002\020\016\n\002\b\f\030\000 -2\0020\001:\001-B;\b\000\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\f\020\b\032\b\022\004\022\0020\0070\006\022\022\020\n\032\016\022\n\022\b\022\004\022\0020\0070\0060\t¢\006\004\b\013\020\fJ\017\020\005\032\0020\004H\007¢\006\004\b\r\020\016J\032\020\021\032\0020\0202\b\020\017\032\004\030\0010\001H\002¢\006\004\b\021\020\022J\017\020\024\032\0020\023H\026¢\006\004\b\024\020\025J\025\020\b\032\b\022\004\022\0020\0070\006H\007¢\006\004\b\026\020\027J\021\020\033\032\004\030\0010\030H\007¢\006\004\b\031\020\032J\025\020\035\032\b\022\004\022\0020\0070\006H\007¢\006\004\b\034\020\027J\021\020\037\032\004\030\0010\030H\007¢\006\004\b\036\020\032J\017\020\003\032\0020\002H\007¢\006\004\b \020!J\017\020#\032\0020\"H\026¢\006\004\b#\020$R\027\020\005\032\0020\0048\007¢\006\f\n\004\b\005\020%\032\004\b\005\020\016R\035\020\b\032\b\022\004\022\0020\0070\0068\007¢\006\f\n\004\b\b\020&\032\004\b\b\020\027R\023\020\033\032\004\030\0010\0308G¢\006\006\032\004\b\033\020\032R!\020\035\032\b\022\004\022\0020\0070\0068GX\002¢\006\f\n\004\b'\020(\032\004\b\035\020\027R\023\020\037\032\004\030\0010\0308G¢\006\006\032\004\b\037\020\032R\027\020\003\032\0020\0028\007¢\006\f\n\004\b\003\020)\032\004\b\003\020!R\030\020,\032\0020\"*\0020\0078BX\004¢\006\006\032\004\b*\020+¨\006."}, d2 = {"Lokhttp3/Handshake;", "", "Lokhttp3/TlsVersion;", "tlsVersion", "Lokhttp3/CipherSuite;", "cipherSuite", "", "Ljava/security/cert/Certificate;", "localCertificates", "Lkotlin/Function0;", "peerCertificatesFn", "<init>", "(Lokhttp3/TlsVersion;Lokhttp3/CipherSuite;Ljava/util/List;Lkotlin/jvm/functions/Function0;)V", "-deprecated_cipherSuite", "()Lokhttp3/CipherSuite;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "-deprecated_localCertificates", "()Ljava/util/List;", "Ljava/security/Principal;", "-deprecated_localPrincipal", "()Ljava/security/Principal;", "localPrincipal", "-deprecated_peerCertificates", "peerCertificates", "-deprecated_peerPrincipal", "peerPrincipal", "-deprecated_tlsVersion", "()Lokhttp3/TlsVersion;", "", "toString", "()Ljava/lang/String;", "Lokhttp3/CipherSuite;", "Ljava/util/List;", "peerCertificates$delegate", "Lkotlin/Lazy;", "Lokhttp3/TlsVersion;", "getName", "(Ljava/security/cert/Certificate;)Ljava/lang/String;", "name", "Companion", "okhttp"})
@SourceDebugExtension({"SMAP\nHandshake.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Handshake.kt\nokhttp3/Handshake\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,200:1\n1549#2:201\n1620#2,3:202\n1549#2:205\n1620#2,3:206\n*S KotlinDebug\n*F\n+ 1 Handshake.kt\nokhttp3/Handshake\n*L\n129#1:201\n129#1:202,3\n134#1:205\n134#1:206,3\n*E\n"})
public final class Handshake {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final TlsVersion tlsVersion;
  
  @NotNull
  private final CipherSuite cipherSuite;
  
  @NotNull
  private final List<Certificate> localCertificates;
  
  @NotNull
  private final Lazy peerCertificates$delegate;
  
  public Handshake(@NotNull TlsVersion tlsVersion, @NotNull CipherSuite cipherSuite, @NotNull List<Certificate> localCertificates, @NotNull Function0<? extends List<? extends Certificate>> peerCertificatesFn) {
    this.tlsVersion = tlsVersion;
    this.cipherSuite = cipherSuite;
    this.localCertificates = localCertificates;
    this.peerCertificates$delegate = LazyKt.lazy(new Handshake$peerCertificates$2(peerCertificatesFn));
  }
  
  @JvmName(name = "tlsVersion")
  @NotNull
  public final TlsVersion tlsVersion() {
    return this.tlsVersion;
  }
  
  @JvmName(name = "cipherSuite")
  @NotNull
  public final CipherSuite cipherSuite() {
    return this.cipherSuite;
  }
  
  @JvmName(name = "localCertificates")
  @NotNull
  public final List<Certificate> localCertificates() {
    return this.localCertificates;
  }
  
  @JvmName(name = "peerCertificates")
  @NotNull
  public final List<Certificate> peerCertificates() {
    Lazy lazy = this.peerCertificates$delegate;
    return (List<Certificate>)lazy.getValue();
  }
  
  @Metadata(mv = {1, 8, 0}, k = 3, xi = 48, d1 = {"\000\f\n\002\020 \n\002\030\002\n\002\b\003\020\004\032\b\022\004\022\0020\0010\000H\n¢\006\004\b\002\020\003"}, d2 = {"", "Ljava/security/cert/Certificate;", "invoke", "()Ljava/util/List;", "<anonymous>"})
  static final class Handshake$peerCertificates$2 extends Lambda implements Function0<List<? extends Certificate>> {
    @NotNull
    public final List<Certificate> invoke() {
      List<Certificate> list;
      try {
        list = (List)this.$peerCertificatesFn.invoke();
      } catch (SSLPeerUnverifiedException spue) {
        list = CollectionsKt.emptyList();
      } 
      return list;
    }
    
    Handshake$peerCertificates$2(Function0<List<Certificate>> $peerCertificatesFn) {
      super(0);
    }
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "tlsVersion", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_tlsVersion")
  @NotNull
  public final TlsVersion -deprecated_tlsVersion() {
    return this.tlsVersion;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "cipherSuite", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_cipherSuite")
  @NotNull
  public final CipherSuite -deprecated_cipherSuite() {
    return this.cipherSuite;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "peerCertificates", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_peerCertificates")
  @NotNull
  public final List<Certificate> -deprecated_peerCertificates() {
    return peerCertificates();
  }
  
  @JvmName(name = "peerPrincipal")
  @Nullable
  public final Principal peerPrincipal() {
    Object object = CollectionsKt.firstOrNull(peerCertificates());
    (object instanceof X509Certificate) ? object : null;
    return (((object instanceof X509Certificate) ? object : null) != null) ? ((object instanceof X509Certificate) ? object : null).getSubjectX500Principal() : null;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "peerPrincipal", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_peerPrincipal")
  @Nullable
  public final Principal -deprecated_peerPrincipal() {
    return peerPrincipal();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "localCertificates", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_localCertificates")
  @NotNull
  public final List<Certificate> -deprecated_localCertificates() {
    return this.localCertificates;
  }
  
  @JvmName(name = "localPrincipal")
  @Nullable
  public final Principal localPrincipal() {
    Object object = CollectionsKt.firstOrNull(this.localCertificates);
    (object instanceof X509Certificate) ? object : null;
    return (((object instanceof X509Certificate) ? object : null) != null) ? ((object instanceof X509Certificate) ? object : null).getSubjectX500Principal() : null;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "localPrincipal", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_localPrincipal")
  @Nullable
  public final Principal -deprecated_localPrincipal() {
    return localPrincipal();
  }
  
  public boolean equals(@Nullable Object other) {
    return (other instanceof Handshake && 
      ((Handshake)other).tlsVersion == this.tlsVersion && 
      Intrinsics.areEqual(((Handshake)other).cipherSuite, this.cipherSuite) && 
      Intrinsics.areEqual(((Handshake)other).peerCertificates(), peerCertificates()) && 
      Intrinsics.areEqual(((Handshake)other).localCertificates, this.localCertificates));
  }
  
  public int hashCode() {
    int result = 17;
    result = 31 * result + this.tlsVersion.hashCode();
    result = 31 * result + this.cipherSuite.hashCode();
    result = 31 * result + peerCertificates().hashCode();
    result = 31 * result + this.localCertificates.hashCode();
    return result;
  }
  
  @NotNull
  public String toString() {
    Iterable<Certificate> $this$map$iv = peerCertificates();
    int $i$f$map = 0;
    Iterable<Certificate> iterable1 = $this$map$iv;
    Collection<String> collection1 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
    int $i$f$mapTo = 0;
    for (Certificate item$iv$iv : iterable1) {
      Certificate certificate1 = item$iv$iv;
      Collection<String> collection = collection1;
      int $i$a$-map-Handshake$toString$peerCertificatesString$1 = 0;
      collection.add(getName(certificate1));
    } 
    String peerCertificatesString = ((List)collection1).toString();
    $this$map$iv = this.localCertificates;
    StringBuilder stringBuilder = (new StringBuilder()).append("Handshake{tlsVersion=").append(this.tlsVersion).append(" cipherSuite=").append(this.cipherSuite).append(" peerCertificates=").append(peerCertificatesString).append(" localCertificates=");
    $i$f$map = 0;
    Iterable<Certificate> $this$mapTo$iv$iv = $this$map$iv;
    Collection<String> destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
    $i$f$mapTo = 0;
    for (Certificate item$iv$iv : $this$mapTo$iv$iv) {
      Certificate it = item$iv$iv;
      Collection<String> collection = destination$iv$iv;
      int $i$a$-map-Handshake$toString$1 = 0;
      collection.add(getName(it));
    } 
    return stringBuilder.append(destination$iv$iv).append('}').toString();
  }
  
  private final String getName(Certificate $this$name) {
    Intrinsics.checkNotNullExpressionValue($this$name.getType(), "type");
    return ($this$name instanceof X509Certificate) ? ((X509Certificate)$this$name).getSubjectDN().toString() : $this$name.getType();
  }
  
  @JvmStatic
  @JvmName(name = "get")
  @NotNull
  public static final Handshake get(@NotNull SSLSession $this$get) throws IOException {
    return Companion.get($this$get);
  }
  
  @JvmStatic
  @NotNull
  public static final Handshake get(@NotNull TlsVersion tlsVersion, @NotNull CipherSuite cipherSuite, @NotNull List<? extends Certificate> peerCertificates, @NotNull List<? extends Certificate> localCertificates) {
    return Companion.get(tlsVersion, cipherSuite, peerCertificates, localCertificates);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000:\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\002\b\004\n\002\020\021\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\027\020\t\032\0020\0062\006\020\005\032\0020\004H\007¢\006\004\b\007\020\bJ;\020\t\032\0020\0062\006\020\013\032\0020\n2\006\020\r\032\0020\f2\f\020\020\032\b\022\004\022\0020\0170\0162\f\020\021\032\b\022\004\022\0020\0170\016H\007¢\006\004\b\t\020\022J\023\020\023\032\0020\006*\0020\004H\007¢\006\004\b\t\020\bJ#\020\025\032\b\022\004\022\0020\0170\016*\f\022\006\b\001\022\0020\017\030\0010\024H\002¢\006\004\b\025\020\026¨\006\027"}, d2 = {"Lokhttp3/Handshake$Companion;", "", "<init>", "()V", "Ljavax/net/ssl/SSLSession;", "sslSession", "Lokhttp3/Handshake;", "-deprecated_get", "(Ljavax/net/ssl/SSLSession;)Lokhttp3/Handshake;", "get", "Lokhttp3/TlsVersion;", "tlsVersion", "Lokhttp3/CipherSuite;", "cipherSuite", "", "Ljava/security/cert/Certificate;", "peerCertificates", "localCertificates", "(Lokhttp3/TlsVersion;Lokhttp3/CipherSuite;Ljava/util/List;Ljava/util/List;)Lokhttp3/Handshake;", "handshake", "", "toImmutableList", "([Ljava/security/cert/Certificate;)Ljava/util/List;", "okhttp"})
  @SourceDebugExtension({"SMAP\nHandshake.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Handshake.kt\nokhttp3/Handshake$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,200:1\n1#2:201\n*E\n"})
  public static final class Companion {
    private Companion() {}
    
    @JvmStatic
    @JvmName(name = "get")
    @NotNull
    public final Handshake get(@NotNull SSLSession $this$handshake) throws IOException {
      String cipherSuiteString, tlsVersionString;
      List<? extends Certificate> list1;
      Intrinsics.checkNotNullParameter($this$handshake, "<this>");
      if ($this$handshake.getCipherSuite() == null) {
        $this$handshake.getCipherSuite();
        int $i$a$-checkNotNull-Handshake$Companion$handshake$cipherSuiteString$1 = 0;
        String str = "cipherSuite == null";
        throw new IllegalStateException(str.toString());
      } 
      String str1 = cipherSuiteString;
      if (Intrinsics.areEqual(str1, "TLS_NULL_WITH_NULL_NULL") ? true : Intrinsics.areEqual(str1, "SSL_NULL_WITH_NULL_NULL"))
        throw new IOException("cipherSuite == " + cipherSuiteString); 
      CipherSuite cipherSuite = CipherSuite.Companion.forJavaName(cipherSuiteString);
      if ($this$handshake.getProtocol() == null) {
        $this$handshake.getProtocol();
        int $i$a$-checkNotNull-Handshake$Companion$handshake$tlsVersionString$1 = 0;
        String str = "tlsVersion == null";
        throw new IllegalStateException(str.toString());
      } 
      if (Intrinsics.areEqual("NONE", tlsVersionString))
        throw new IOException("tlsVersion == NONE"); 
      TlsVersion tlsVersion = TlsVersion.Companion.forJavaName(tlsVersionString);
      try {
        list1 = toImmutableList($this$handshake.getPeerCertificates());
      } catch (SSLPeerUnverifiedException _) {
        list1 = CollectionsKt.emptyList();
      } 
      List<? extends Certificate> peerCertificatesCopy = list1;
      return new Handshake(tlsVersion, cipherSuite, toImmutableList($this$handshake.getLocalCertificates()), new Handshake$Companion$handshake$1(peerCertificatesCopy));
    }
    
    @Metadata(mv = {1, 8, 0}, k = 3, xi = 48, d1 = {"\000\f\n\002\020 \n\002\030\002\n\002\b\003\020\004\032\b\022\004\022\0020\0010\000H\n¢\006\004\b\002\020\003"}, d2 = {"", "Ljava/security/cert/Certificate;", "invoke", "()Ljava/util/List;", "<anonymous>"})
    static final class Handshake$Companion$handshake$1 extends Lambda implements Function0<List<? extends Certificate>> {
      Handshake$Companion$handshake$1(List<Certificate> $peerCertificatesCopy) {
        super(0);
      }
      
      @NotNull
      public final List<Certificate> invoke() {
        return this.$peerCertificatesCopy;
      }
    }
    
    private final List<Certificate> toImmutableList(Certificate[] $this$toImmutableList) {
      return ($this$toImmutableList != null) ? Util.immutableListOf(Arrays.copyOf((Object[])$this$toImmutableList, $this$toImmutableList.length)) : CollectionsKt.emptyList();
    }
    
    @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "sslSession.handshake()", imports = {}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_get")
    @NotNull
    public final Handshake -deprecated_get(@NotNull SSLSession sslSession) throws IOException {
      Intrinsics.checkNotNullParameter(sslSession, "sslSession");
      return get(sslSession);
    }
    
    @JvmStatic
    @NotNull
    public final Handshake get(@NotNull TlsVersion tlsVersion, @NotNull CipherSuite cipherSuite, @NotNull List peerCertificates, @NotNull List localCertificates) {
      Intrinsics.checkNotNullParameter(tlsVersion, "tlsVersion");
      Intrinsics.checkNotNullParameter(cipherSuite, "cipherSuite");
      Intrinsics.checkNotNullParameter(peerCertificates, "peerCertificates");
      Intrinsics.checkNotNullParameter(localCertificates, "localCertificates");
      List<? extends Certificate> peerCertificatesCopy = Util.toImmutableList(peerCertificates);
      return new Handshake(tlsVersion, cipherSuite, Util.toImmutableList(localCertificates), new Handshake$Companion$get$1(peerCertificatesCopy));
    }
    
    @Metadata(mv = {1, 8, 0}, k = 3, xi = 48, d1 = {"\000\f\n\002\020 \n\002\030\002\n\002\b\003\020\004\032\b\022\004\022\0020\0010\000H\n¢\006\004\b\002\020\003"}, d2 = {"", "Ljava/security/cert/Certificate;", "invoke", "()Ljava/util/List;", "<anonymous>"})
    static final class Handshake$Companion$get$1 extends Lambda implements Function0<List<? extends Certificate>> {
      @NotNull
      public final List<Certificate> invoke() {
        return this.$peerCertificatesCopy;
      }
      
      Handshake$Companion$get$1(List<Certificate> $peerCertificatesCopy) {
        super(0);
      }
    }
  }
}
