package okhttp3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLSocket;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000H\n\002\030\002\n\002\020\000\n\002\020\013\n\002\b\002\n\002\020\021\n\002\020\016\n\002\b\004\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\003\n\002\020 \n\002\030\002\n\002\b\006\n\002\020\b\n\002\b\t\n\002\030\002\n\002\b\t\030\000 +2\0020\001:\002,+B9\b\000\022\006\020\003\032\0020\002\022\006\020\004\032\0020\002\022\016\020\007\032\n\022\004\022\0020\006\030\0010\005\022\016\020\b\032\n\022\004\022\0020\006\030\0010\005¢\006\004\b\t\020\nJ\037\020\021\032\0020\0162\006\020\f\032\0020\0132\006\020\r\032\0020\002H\000¢\006\004\b\017\020\020J\027\020\026\032\n\022\004\022\0020\023\030\0010\022H\007¢\006\004\b\024\020\025J\032\020\030\032\0020\0022\b\020\027\032\004\030\0010\001H\002¢\006\004\b\030\020\031J\017\020\033\032\0020\032H\026¢\006\004\b\033\020\034J\025\020\036\032\0020\0022\006\020\035\032\0020\013¢\006\004\b\036\020\037J\037\020 \032\0020\0002\006\020\f\032\0020\0132\006\020\r\032\0020\002H\002¢\006\004\b \020!J\017\020\004\032\0020\002H\007¢\006\004\b\"\020#J\027\020&\032\n\022\004\022\0020$\030\0010\022H\007¢\006\004\b%\020\025J\017\020'\032\0020\006H\026¢\006\004\b'\020(R\031\020\026\032\n\022\004\022\0020\023\030\0010\0228G¢\006\006\032\004\b\026\020\025R\034\020\007\032\n\022\004\022\0020\006\030\0010\0058\002X\004¢\006\006\n\004\b\007\020)R\027\020\003\032\0020\0028\007¢\006\f\n\004\b\003\020*\032\004\b\003\020#R\027\020\004\032\0020\0028\007¢\006\f\n\004\b\004\020*\032\004\b\004\020#R\031\020&\032\n\022\004\022\0020$\030\0010\0228G¢\006\006\032\004\b&\020\025R\034\020\b\032\n\022\004\022\0020\006\030\0010\0058\002X\004¢\006\006\n\004\b\b\020)¨\006-"}, d2 = {"Lokhttp3/ConnectionSpec;", "", "", "isTls", "supportsTlsExtensions", "", "", "cipherSuitesAsString", "tlsVersionsAsString", "<init>", "(ZZ[Ljava/lang/String;[Ljava/lang/String;)V", "Ljavax/net/ssl/SSLSocket;", "sslSocket", "isFallback", "", "apply$okhttp", "(Ljavax/net/ssl/SSLSocket;Z)V", "apply", "", "Lokhttp3/CipherSuite;", "-deprecated_cipherSuites", "()Ljava/util/List;", "cipherSuites", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "socket", "isCompatible", "(Ljavax/net/ssl/SSLSocket;)Z", "supportedSpec", "(Ljavax/net/ssl/SSLSocket;Z)Lokhttp3/ConnectionSpec;", "-deprecated_supportsTlsExtensions", "()Z", "Lokhttp3/TlsVersion;", "-deprecated_tlsVersions", "tlsVersions", "toString", "()Ljava/lang/String;", "[Ljava/lang/String;", "Z", "Companion", "Builder", "okhttp"})
@SourceDebugExtension({"SMAP\nConnectionSpec.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConnectionSpec.kt\nokhttp3/ConnectionSpec\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,350:1\n11065#2:351\n11400#2,3:352\n11065#2:355\n11400#2,3:356\n*S KotlinDebug\n*F\n+ 1 ConnectionSpec.kt\nokhttp3/ConnectionSpec\n*L\n59#1:351\n59#1:352,3\n75#1:355\n75#1:356,3\n*E\n"})
public final class ConnectionSpec {
  public ConnectionSpec(boolean isTls, boolean supportsTlsExtensions, @Nullable String[] cipherSuitesAsString, @Nullable String[] tlsVersionsAsString) {
    this.isTls = isTls;
    this.supportsTlsExtensions = supportsTlsExtensions;
    this.cipherSuitesAsString = cipherSuitesAsString;
    this.tlsVersionsAsString = tlsVersionsAsString;
  }
  
  @JvmName(name = "isTls")
  public final boolean isTls() {
    return this.isTls;
  }
  
  @JvmName(name = "supportsTlsExtensions")
  public final boolean supportsTlsExtensions() {
    return this.supportsTlsExtensions;
  }
  
  @JvmName(name = "cipherSuites")
  @Nullable
  public final List<CipherSuite> cipherSuites() {
    String[] arrayOfString1 = this.cipherSuitesAsString;
    int $i$f$map = 0;
    String[] arrayOfString2 = arrayOfString1;
    Collection<CipherSuite> destination$iv$iv = new ArrayList(arrayOfString1.length);
    int $i$f$mapTo = 0;
    byte b;
    int i;
    for (b = 0, i = arrayOfString2.length; b < i; ) {
      Object item$iv$iv = arrayOfString2[b];
      Object object1 = item$iv$iv;
      Collection<CipherSuite> collection = destination$iv$iv;
      int $i$a$-map-ConnectionSpec$cipherSuites$1 = 0;
      collection.add(CipherSuite.Companion.forJavaName((String)object1));
    } 
    return (this.cipherSuitesAsString != null) ? CollectionsKt.toList(destination$iv$iv) : null;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "cipherSuites", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_cipherSuites")
  @Nullable
  public final List<CipherSuite> -deprecated_cipherSuites() {
    return cipherSuites();
  }
  
  @JvmName(name = "tlsVersions")
  @Nullable
  public final List<TlsVersion> tlsVersions() {
    String[] arrayOfString1 = this.tlsVersionsAsString;
    int $i$f$map = 0;
    String[] arrayOfString2 = arrayOfString1;
    Collection<TlsVersion> destination$iv$iv = new ArrayList(arrayOfString1.length);
    int $i$f$mapTo = 0;
    byte b;
    int i;
    for (b = 0, i = arrayOfString2.length; b < i; ) {
      Object item$iv$iv = arrayOfString2[b];
      Object object1 = item$iv$iv;
      Collection<TlsVersion> collection = destination$iv$iv;
      int $i$a$-map-ConnectionSpec$tlsVersions$1 = 0;
      collection.add(TlsVersion.Companion.forJavaName((String)object1));
    } 
    return (this.tlsVersionsAsString != null) ? CollectionsKt.toList(destination$iv$iv) : null;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "tlsVersions", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_tlsVersions")
  @Nullable
  public final List<TlsVersion> -deprecated_tlsVersions() {
    return tlsVersions();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "supportsTlsExtensions", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_supportsTlsExtensions")
  public final boolean -deprecated_supportsTlsExtensions() {
    return this.supportsTlsExtensions;
  }
  
  public final void apply$okhttp(@NotNull SSLSocket sslSocket, boolean isFallback) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    ConnectionSpec specToApply = supportedSpec(sslSocket, isFallback);
    if (specToApply.tlsVersions() != null)
      sslSocket.setEnabledProtocols(specToApply.tlsVersionsAsString); 
    if (specToApply.cipherSuites() != null)
      sslSocket.setEnabledCipherSuites(specToApply.cipherSuitesAsString); 
  }
  
  private final ConnectionSpec supportedSpec(SSLSocket sslSocket, boolean isFallback) {
    Intrinsics.checkNotNullExpressionValue(sslSocket.getEnabledCipherSuites(), "sslSocket.enabledCipherSuites");
    String[] cipherSuitesIntersection = (this.cipherSuitesAsString != null) ? Util.intersect(sslSocket.getEnabledCipherSuites(), this.cipherSuitesAsString, CipherSuite.Companion.getORDER_BY_NAME$okhttp()) : sslSocket.getEnabledCipherSuites();
    Intrinsics.checkNotNullExpressionValue(sslSocket.getEnabledProtocols(), "sslSocket.enabledProtocols");
    String[] tlsVersionsIntersection = (this.tlsVersionsAsString != null) ? Util.intersect(sslSocket.getEnabledProtocols(), this.tlsVersionsAsString, ComparisonsKt.naturalOrder()) : sslSocket.getEnabledProtocols();
    String[] supportedCipherSuites = sslSocket.getSupportedCipherSuites();
    Intrinsics.checkNotNullExpressionValue(supportedCipherSuites, "supportedCipherSuites");
    int indexOfFallbackScsv = Util.indexOf(supportedCipherSuites, "TLS_FALLBACK_SCSV", CipherSuite.Companion.getORDER_BY_NAME$okhttp());
    if (isFallback && indexOfFallbackScsv != -1) {
      Intrinsics.checkNotNullExpressionValue(cipherSuitesIntersection, "cipherSuitesIntersection");
      Intrinsics.checkNotNullExpressionValue(supportedCipherSuites[indexOfFallbackScsv], "supportedCipherSuites[indexOfFallbackScsv]");
      cipherSuitesIntersection = Util.concat(cipherSuitesIntersection, supportedCipherSuites[indexOfFallbackScsv]);
    } 
    Intrinsics.checkNotNullExpressionValue(cipherSuitesIntersection, "cipherSuitesIntersection");
    String[] arrayOfString1 = cipherSuitesIntersection;
    Intrinsics.checkNotNullExpressionValue(tlsVersionsIntersection, "tlsVersionsIntersection");
    arrayOfString1 = tlsVersionsIntersection;
    return (new Builder(this)).cipherSuites(Arrays.<String>copyOf(arrayOfString1, arrayOfString1.length)).tlsVersions(Arrays.<String>copyOf(arrayOfString1, arrayOfString1.length)).build();
  }
  
  public final boolean isCompatible(@NotNull SSLSocket socket) {
    Intrinsics.checkNotNullParameter(socket, "socket");
    if (!this.isTls)
      return false; 
    if (this.tlsVersionsAsString != null && !Util.hasIntersection(this.tlsVersionsAsString, socket.getEnabledProtocols(), ComparisonsKt.naturalOrder()))
      return false; 
    if (this.cipherSuitesAsString != null && !Util.hasIntersection(this.cipherSuitesAsString, socket.getEnabledCipherSuites(), CipherSuite.Companion.getORDER_BY_NAME$okhttp()))
      return false; 
    return true;
  }
  
  public boolean equals(@Nullable Object other) {
    if (!(other instanceof ConnectionSpec))
      return false; 
    if (other == this)
      return true; 
    if (this.isTls != ((ConnectionSpec)other).isTls)
      return false; 
    if (this.isTls) {
      if (!Arrays.equals((Object[])this.cipherSuitesAsString, (Object[])((ConnectionSpec)other).cipherSuitesAsString))
        return false; 
      if (!Arrays.equals((Object[])this.tlsVersionsAsString, (Object[])((ConnectionSpec)other).tlsVersionsAsString))
        return false; 
      if (this.supportsTlsExtensions != ((ConnectionSpec)other).supportsTlsExtensions)
        return false; 
    } 
    return true;
  }
  
  public int hashCode() {
    int result = 17;
    if (this.isTls) {
      result = 31 * result + ((this.cipherSuitesAsString != null) ? Arrays.hashCode((Object[])this.cipherSuitesAsString) : 0);
      result = 31 * result + ((this.tlsVersionsAsString != null) ? Arrays.hashCode((Object[])this.tlsVersionsAsString) : 0);
      result = 31 * result + (this.supportsTlsExtensions ? 0 : 1);
    } 
    return result;
  }
  
  @NotNull
  public String toString() {
    if (!this.isTls)
      return "ConnectionSpec()"; 
    return "ConnectionSpec(cipherSuites=" + Objects.toString(cipherSuites(), "[all enabled]") + ", tlsVersions=" + Objects.toString(tlsVersions(), "[all enabled]") + ", supportsTlsExtensions=" + this.supportsTlsExtensions + ')';
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0004\n\002\030\002\n\002\020\000\n\002\020\013\n\002\b\003\n\002\030\002\n\002\b\007\n\002\020\021\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\017\030\0002\0020\001B\021\b\020\022\006\020\003\032\0020\002¢\006\004\b\004\020\005B\021\b\026\022\006\020\007\032\0020\006¢\006\004\b\004\020\bJ\r\020\t\032\0020\000¢\006\004\b\t\020\nJ\r\020\013\032\0020\000¢\006\004\b\013\020\nJ\r\020\f\032\0020\006¢\006\004\b\f\020\rJ!\020\020\032\0020\0002\022\020\020\032\n\022\006\b\001\022\0020\0170\016\"\0020\017¢\006\004\b\020\020\021J!\020\020\032\0020\0002\022\020\020\032\n\022\006\b\001\022\0020\0220\016\"\0020\022¢\006\004\b\020\020\023J\027\020\024\032\0020\0002\006\020\024\032\0020\002H\007¢\006\004\b\024\020\025J!\020\026\032\0020\0002\022\020\026\032\n\022\006\b\001\022\0020\0170\016\"\0020\017¢\006\004\b\026\020\021J!\020\026\032\0020\0002\022\020\026\032\n\022\006\b\001\022\0020\0270\016\"\0020\027¢\006\004\b\026\020\030R*\020\020\032\n\022\004\022\0020\017\030\0010\0168\000@\000X\016¢\006\022\n\004\b\020\020\031\032\004\b\032\020\033\"\004\b\034\020\035R\"\020\024\032\0020\0028\000@\000X\016¢\006\022\n\004\b\024\020\036\032\004\b\037\020 \"\004\b!\020\005R\"\020\003\032\0020\0028\000@\000X\016¢\006\022\n\004\b\003\020\036\032\004\b\"\020 \"\004\b#\020\005R*\020\026\032\n\022\004\022\0020\017\030\0010\0168\000@\000X\016¢\006\022\n\004\b\026\020\031\032\004\b$\020\033\"\004\b%\020\035¨\006&"}, d2 = {"Lokhttp3/ConnectionSpec$Builder;", "", "", "tls", "<init>", "(Z)V", "Lokhttp3/ConnectionSpec;", "connectionSpec", "(Lokhttp3/ConnectionSpec;)V", "allEnabledCipherSuites", "()Lokhttp3/ConnectionSpec$Builder;", "allEnabledTlsVersions", "build", "()Lokhttp3/ConnectionSpec;", "", "", "cipherSuites", "([Ljava/lang/String;)Lokhttp3/ConnectionSpec$Builder;", "Lokhttp3/CipherSuite;", "([Lokhttp3/CipherSuite;)Lokhttp3/ConnectionSpec$Builder;", "supportsTlsExtensions", "(Z)Lokhttp3/ConnectionSpec$Builder;", "tlsVersions", "Lokhttp3/TlsVersion;", "([Lokhttp3/TlsVersion;)Lokhttp3/ConnectionSpec$Builder;", "[Ljava/lang/String;", "getCipherSuites$okhttp", "()[Ljava/lang/String;", "setCipherSuites$okhttp", "([Ljava/lang/String;)V", "Z", "getSupportsTlsExtensions$okhttp", "()Z", "setSupportsTlsExtensions$okhttp", "getTls$okhttp", "setTls$okhttp", "getTlsVersions$okhttp", "setTlsVersions$okhttp", "okhttp"})
  @SourceDebugExtension({"SMAP\nConnectionSpec.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConnectionSpec.kt\nokhttp3/ConnectionSpec$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,350:1\n1#2:351\n11065#3:352\n11400#3,3:353\n11065#3:358\n11400#3,3:359\n37#4,2:356\n37#4,2:362\n*S KotlinDebug\n*F\n+ 1 ConnectionSpec.kt\nokhttp3/ConnectionSpec$Builder\n*L\n225#1:352\n225#1:353,3\n244#1:358\n244#1:359,3\n225#1:356,2\n244#1:362,2\n*E\n"})
  public static final class Builder {
    private boolean tls;
    
    @Nullable
    private String[] cipherSuites;
    
    @Nullable
    private String[] tlsVersions;
    
    private boolean supportsTlsExtensions;
    
    public final boolean getTls$okhttp() {
      return this.tls;
    }
    
    public final void setTls$okhttp(boolean <set-?>) {
      this.tls = <set-?>;
    }
    
    @Nullable
    public final String[] getCipherSuites$okhttp() {
      return this.cipherSuites;
    }
    
    public final void setCipherSuites$okhttp(@Nullable String[] <set-?>) {
      this.cipherSuites = <set-?>;
    }
    
    @Nullable
    public final String[] getTlsVersions$okhttp() {
      return this.tlsVersions;
    }
    
    public final void setTlsVersions$okhttp(@Nullable String[] <set-?>) {
      this.tlsVersions = <set-?>;
    }
    
    public final boolean getSupportsTlsExtensions$okhttp() {
      return this.supportsTlsExtensions;
    }
    
    public final void setSupportsTlsExtensions$okhttp(boolean <set-?>) {
      this.supportsTlsExtensions = <set-?>;
    }
    
    public Builder(boolean tls) {
      this.tls = tls;
    }
    
    public Builder(@NotNull ConnectionSpec connectionSpec) {
      this.tls = connectionSpec.isTls();
      this.cipherSuites = connectionSpec.cipherSuitesAsString;
      this.tlsVersions = connectionSpec.tlsVersionsAsString;
      this.supportsTlsExtensions = connectionSpec.supportsTlsExtensions();
    }
    
    @NotNull
    public final Builder allEnabledCipherSuites() {
      Builder builder1 = this, $this$allEnabledCipherSuites_u24lambda_u241 = builder1;
      int $i$a$-apply-ConnectionSpec$Builder$allEnabledCipherSuites$1 = 0;
      if (!$this$allEnabledCipherSuites_u24lambda_u241.tls) {
        int $i$a$-require-ConnectionSpec$Builder$allEnabledCipherSuites$1$1 = 0;
        String str = "no cipher suites for cleartext connections";
        throw new IllegalArgumentException(str.toString());
      } 
      $this$allEnabledCipherSuites_u24lambda_u241.cipherSuites = null;
      return builder1;
    }
    
    @NotNull
    public final Builder cipherSuites(@NotNull CipherSuite... cipherSuites) {
      Intrinsics.checkNotNullParameter(cipherSuites, "cipherSuites");
      Builder $this$cipherSuites_u24lambda_u244 = this;
      int $i$a$-apply-ConnectionSpec$Builder$cipherSuites$1 = 0;
      if (!$this$cipherSuites_u24lambda_u244.tls) {
        int $i$a$-require-ConnectionSpec$Builder$cipherSuites$1$1 = 0;
        String str = "no cipher suites for cleartext connections";
        throw new IllegalArgumentException(str.toString());
      } 
      CipherSuite[] arrayOfCipherSuite1 = cipherSuites;
      int $i$f$map = 0;
      CipherSuite[] arrayOfCipherSuite2 = arrayOfCipherSuite1;
      Collection<String> destination$iv$iv = new ArrayList(arrayOfCipherSuite1.length);
      int $i$f$mapTo = 0;
      byte b;
      int i;
      for (b = 0, i = arrayOfCipherSuite2.length; b < i; ) {
        Object item$iv$iv = arrayOfCipherSuite2[b];
        Object object1 = item$iv$iv;
        Collection<String> collection = destination$iv$iv;
        int $i$a$-map-ConnectionSpec$Builder$cipherSuites$1$strings$1 = 0;
        collection.add(object1.javaName());
      } 
      List list = (List)destination$iv$iv;
      int $i$f$toTypedArray = 0;
      Collection thisCollection$iv = list;
      String[] strings = (String[])thisCollection$iv.toArray((Object[])new String[0]);
      return $this$cipherSuites_u24lambda_u244.cipherSuites(Arrays.<String>copyOf(strings, strings.length));
    }
    
    @NotNull
    public final Builder cipherSuites(@NotNull String... cipherSuites) {
      Intrinsics.checkNotNullParameter(cipherSuites, "cipherSuites");
      Builder builder1 = this, $this$cipherSuites_u24lambda_u247 = builder1;
      int $i$a$-apply-ConnectionSpec$Builder$cipherSuites$2 = 0;
      if (!$this$cipherSuites_u24lambda_u247.tls) {
        int $i$a$-require-ConnectionSpec$Builder$cipherSuites$2$1 = 0;
        String str = "no cipher suites for cleartext connections";
        throw new IllegalArgumentException(str.toString());
      } 
      if (!(!((cipherSuites.length == 0) ? 1 : 0) ? 1 : 0)) {
        int $i$a$-require-ConnectionSpec$Builder$cipherSuites$2$2 = 0;
        String str = "At least one cipher suite is required";
        throw new IllegalArgumentException(str.toString());
      } 
      $this$cipherSuites_u24lambda_u247.cipherSuites = (String[])cipherSuites.clone();
      return builder1;
    }
    
    @NotNull
    public final Builder allEnabledTlsVersions() {
      Builder builder1 = this, $this$allEnabledTlsVersions_u24lambda_u249 = builder1;
      int $i$a$-apply-ConnectionSpec$Builder$allEnabledTlsVersions$1 = 0;
      if (!$this$allEnabledTlsVersions_u24lambda_u249.tls) {
        int $i$a$-require-ConnectionSpec$Builder$allEnabledTlsVersions$1$1 = 0;
        String str = "no TLS versions for cleartext connections";
        throw new IllegalArgumentException(str.toString());
      } 
      $this$allEnabledTlsVersions_u24lambda_u249.tlsVersions = null;
      return builder1;
    }
    
    @NotNull
    public final Builder tlsVersions(@NotNull TlsVersion... tlsVersions) {
      Intrinsics.checkNotNullParameter(tlsVersions, "tlsVersions");
      Builder $this$tlsVersions_u24lambda_u2412 = this;
      int $i$a$-apply-ConnectionSpec$Builder$tlsVersions$1 = 0;
      if (!$this$tlsVersions_u24lambda_u2412.tls) {
        int $i$a$-require-ConnectionSpec$Builder$tlsVersions$1$1 = 0;
        String str = "no TLS versions for cleartext connections";
        throw new IllegalArgumentException(str.toString());
      } 
      TlsVersion[] arrayOfTlsVersion1 = tlsVersions;
      int $i$f$map = 0;
      TlsVersion[] arrayOfTlsVersion2 = arrayOfTlsVersion1;
      Collection<String> destination$iv$iv = new ArrayList(arrayOfTlsVersion1.length);
      int $i$f$mapTo = 0;
      byte b;
      int i;
      for (b = 0, i = arrayOfTlsVersion2.length; b < i; ) {
        Object item$iv$iv = arrayOfTlsVersion2[b];
        Object object1 = item$iv$iv;
        Collection<String> collection = destination$iv$iv;
        int $i$a$-map-ConnectionSpec$Builder$tlsVersions$1$strings$1 = 0;
        collection.add(object1.javaName());
      } 
      List list = (List)destination$iv$iv;
      int $i$f$toTypedArray = 0;
      Collection thisCollection$iv = list;
      String[] strings = (String[])thisCollection$iv.toArray((Object[])new String[0]);
      return $this$tlsVersions_u24lambda_u2412.tlsVersions(Arrays.<String>copyOf(strings, strings.length));
    }
    
    @NotNull
    public final Builder tlsVersions(@NotNull String... tlsVersions) {
      Intrinsics.checkNotNullParameter(tlsVersions, "tlsVersions");
      Builder builder1 = this, $this$tlsVersions_u24lambda_u2415 = builder1;
      int $i$a$-apply-ConnectionSpec$Builder$tlsVersions$2 = 0;
      if (!$this$tlsVersions_u24lambda_u2415.tls) {
        int $i$a$-require-ConnectionSpec$Builder$tlsVersions$2$1 = 0;
        String str = "no TLS versions for cleartext connections";
        throw new IllegalArgumentException(str.toString());
      } 
      if (!(!((tlsVersions.length == 0) ? 1 : 0) ? 1 : 0)) {
        int $i$a$-require-ConnectionSpec$Builder$tlsVersions$2$2 = 0;
        String str = "At least one TLS version is required";
        throw new IllegalArgumentException(str.toString());
      } 
      $this$tlsVersions_u24lambda_u2415.tlsVersions = (String[])tlsVersions.clone();
      return builder1;
    }
    
    @Deprecated(message = "since OkHttp 3.13 all TLS-connections are expected to support TLS extensions.\nIn a future release setting this to true will be unnecessary and setting it to false\nwill have no effect.")
    @NotNull
    public final Builder supportsTlsExtensions(boolean supportsTlsExtensions) {
      Builder builder1 = this, $this$supportsTlsExtensions_u24lambda_u2417 = builder1;
      int $i$a$-apply-ConnectionSpec$Builder$supportsTlsExtensions$1 = 0;
      if (!$this$supportsTlsExtensions_u24lambda_u2417.tls) {
        int $i$a$-require-ConnectionSpec$Builder$supportsTlsExtensions$1$1 = 0;
        String str = "no TLS extensions for cleartext connections";
        throw new IllegalArgumentException(str.toString());
      } 
      $this$supportsTlsExtensions_u24lambda_u2417.supportsTlsExtensions = supportsTlsExtensions;
      return builder1;
    }
    
    @NotNull
    public final ConnectionSpec build() {
      return new ConnectionSpec(this.tls, this.supportsTlsExtensions, this.cipherSuites, this.tlsVersions);
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000 \n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\021\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\007\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\032\020\006\032\b\022\004\022\0020\0050\0048\002X\004¢\006\006\n\004\b\006\020\007R\024\020\t\032\0020\b8\006X\004¢\006\006\n\004\b\t\020\nR\024\020\013\032\0020\b8\006X\004¢\006\006\n\004\b\013\020\nR\024\020\f\032\0020\b8\006X\004¢\006\006\n\004\b\f\020\nR\032\020\r\032\b\022\004\022\0020\0050\0048\002X\004¢\006\006\n\004\b\r\020\007R\024\020\016\032\0020\b8\006X\004¢\006\006\n\004\b\016\020\n¨\006\017"}, d2 = {"Lokhttp3/ConnectionSpec$Companion;", "", "<init>", "()V", "", "Lokhttp3/CipherSuite;", "APPROVED_CIPHER_SUITES", "[Lokhttp3/CipherSuite;", "Lokhttp3/ConnectionSpec;", "CLEARTEXT", "Lokhttp3/ConnectionSpec;", "COMPATIBLE_TLS", "MODERN_TLS", "RESTRICTED_CIPHER_SUITES", "RESTRICTED_TLS", "okhttp"})
  public static final class Companion {
    private Companion() {}
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  private final boolean isTls;
  
  private final boolean supportsTlsExtensions;
  
  @Nullable
  private final String[] cipherSuitesAsString;
  
  @Nullable
  private final String[] tlsVersionsAsString;
  
  @NotNull
  private static final CipherSuite[] RESTRICTED_CIPHER_SUITES;
  
  @NotNull
  private static final CipherSuite[] APPROVED_CIPHER_SUITES;
  
  @JvmField
  @NotNull
  public static final ConnectionSpec RESTRICTED_TLS;
  
  @JvmField
  @NotNull
  public static final ConnectionSpec MODERN_TLS;
  
  @JvmField
  @NotNull
  public static final ConnectionSpec COMPATIBLE_TLS;
  
  static {
    CipherSuite[] arrayOfCipherSuite3 = new CipherSuite[9];
    arrayOfCipherSuite3[0] = CipherSuite.TLS_AES_128_GCM_SHA256;
    arrayOfCipherSuite3[1] = CipherSuite.TLS_AES_256_GCM_SHA384;
    arrayOfCipherSuite3[2] = CipherSuite.TLS_CHACHA20_POLY1305_SHA256;
    arrayOfCipherSuite3[3] = CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256;
    arrayOfCipherSuite3[4] = CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256;
    arrayOfCipherSuite3[5] = CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384;
    arrayOfCipherSuite3[6] = CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384;
    arrayOfCipherSuite3[7] = CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256;
    arrayOfCipherSuite3[8] = CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256;
    RESTRICTED_CIPHER_SUITES = arrayOfCipherSuite3;
    arrayOfCipherSuite3 = new CipherSuite[16];
    arrayOfCipherSuite3[0] = CipherSuite.TLS_AES_128_GCM_SHA256;
    arrayOfCipherSuite3[1] = CipherSuite.TLS_AES_256_GCM_SHA384;
    arrayOfCipherSuite3[2] = CipherSuite.TLS_CHACHA20_POLY1305_SHA256;
    arrayOfCipherSuite3[3] = CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256;
    arrayOfCipherSuite3[4] = CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256;
    arrayOfCipherSuite3[5] = CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384;
    arrayOfCipherSuite3[6] = CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384;
    arrayOfCipherSuite3[7] = CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256;
    arrayOfCipherSuite3[8] = CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256;
    arrayOfCipherSuite3[9] = CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA;
    arrayOfCipherSuite3[10] = CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA;
    arrayOfCipherSuite3[11] = CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256;
    arrayOfCipherSuite3[12] = CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384;
    arrayOfCipherSuite3[13] = CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA;
    arrayOfCipherSuite3[14] = CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA;
    arrayOfCipherSuite3[15] = CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA;
    APPROVED_CIPHER_SUITES = arrayOfCipherSuite3;
    arrayOfCipherSuite3 = RESTRICTED_CIPHER_SUITES;
    TlsVersion[] arrayOfTlsVersion3 = new TlsVersion[2];
    arrayOfTlsVersion3[0] = TlsVersion.TLS_1_3;
    arrayOfTlsVersion3[1] = TlsVersion.TLS_1_2;
    RESTRICTED_TLS = (new Builder(true)).cipherSuites(Arrays.<CipherSuite>copyOf(arrayOfCipherSuite3, arrayOfCipherSuite3.length)).tlsVersions(arrayOfTlsVersion3).supportsTlsExtensions(true).build();
    CipherSuite[] arrayOfCipherSuite2 = APPROVED_CIPHER_SUITES;
    TlsVersion[] arrayOfTlsVersion2 = new TlsVersion[2];
    arrayOfTlsVersion2[0] = TlsVersion.TLS_1_3;
    arrayOfTlsVersion2[1] = TlsVersion.TLS_1_2;
    MODERN_TLS = (new Builder(true)).cipherSuites(Arrays.<CipherSuite>copyOf(arrayOfCipherSuite2, arrayOfCipherSuite2.length)).tlsVersions(arrayOfTlsVersion2).supportsTlsExtensions(true).build();
    CipherSuite[] arrayOfCipherSuite1 = APPROVED_CIPHER_SUITES;
    TlsVersion[] arrayOfTlsVersion1 = new TlsVersion[4];
    arrayOfTlsVersion1[0] = TlsVersion.TLS_1_3;
    arrayOfTlsVersion1[1] = TlsVersion.TLS_1_2;
    arrayOfTlsVersion1[2] = TlsVersion.TLS_1_1;
    arrayOfTlsVersion1[3] = TlsVersion.TLS_1_0;
    COMPATIBLE_TLS = (new Builder(true)).cipherSuites(Arrays.<CipherSuite>copyOf(arrayOfCipherSuite1, arrayOfCipherSuite1.length)).tlsVersions(arrayOfTlsVersion1).supportsTlsExtensions(true).build();
  }
  
  @JvmField
  @NotNull
  public static final ConnectionSpec CLEARTEXT = (new Builder(false)).build();
}
