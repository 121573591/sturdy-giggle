package okhttp3;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000R\n\002\030\002\n\002\020\000\n\002\020\"\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\016\n\000\n\002\030\002\n\002\020 \n\002\030\002\n\000\n\002\020\002\n\002\b\003\n\002\020\021\n\002\030\002\n\002\b\004\n\002\020\013\n\002\b\004\n\002\020\b\n\002\b\017\030\000 *2\0020\001:\003+*,B#\b\000\022\f\020\004\032\b\022\004\022\0020\0030\002\022\n\b\002\020\006\032\004\030\0010\005¢\006\004\b\007\020\bJ+\020\022\032\0020\0172\006\020\n\032\0020\t2\022\020\016\032\016\022\n\022\b\022\004\022\0020\r0\f0\013H\000¢\006\004\b\020\020\021J+\020\022\032\0020\0172\006\020\n\032\0020\t2\022\020\025\032\n\022\006\b\001\022\0020\0240\023\"\0020\024H\007¢\006\004\b\022\020\026J#\020\022\032\0020\0172\006\020\n\032\0020\t2\f\020\025\032\b\022\004\022\0020\0240\f¢\006\004\b\022\020\027J\032\020\032\032\0020\0312\b\020\030\032\004\030\0010\001H\002¢\006\004\b\032\020\033J\033\020\034\032\b\022\004\022\0020\0030\f2\006\020\n\032\0020\t¢\006\004\b\034\020\035J\017\020\037\032\0020\036H\026¢\006\004\b\037\020 J\027\020#\032\0020\0002\006\020\006\032\0020\005H\000¢\006\004\b!\020\"R\034\020\006\032\004\030\0010\0058\000X\004¢\006\f\n\004\b\006\020$\032\004\b%\020&R\035\020\004\032\b\022\004\022\0020\0030\0028\006¢\006\f\n\004\b\004\020'\032\004\b(\020)¨\006-"}, d2 = {"Lokhttp3/CertificatePinner;", "", "", "Lokhttp3/CertificatePinner$Pin;", "pins", "Lokhttp3/internal/tls/CertificateChainCleaner;", "certificateChainCleaner", "<init>", "(Ljava/util/Set;Lokhttp3/internal/tls/CertificateChainCleaner;)V", "", "hostname", "Lkotlin/Function0;", "", "Ljava/security/cert/X509Certificate;", "cleanedPeerCertificatesFn", "", "check$okhttp", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)V", "check", "", "Ljava/security/cert/Certificate;", "peerCertificates", "(Ljava/lang/String;[Ljava/security/cert/Certificate;)V", "(Ljava/lang/String;Ljava/util/List;)V", "other", "", "equals", "(Ljava/lang/Object;)Z", "findMatchingPins", "(Ljava/lang/String;)Ljava/util/List;", "", "hashCode", "()I", "withCertificateChainCleaner$okhttp", "(Lokhttp3/internal/tls/CertificateChainCleaner;)Lokhttp3/CertificatePinner;", "withCertificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "getCertificateChainCleaner$okhttp", "()Lokhttp3/internal/tls/CertificateChainCleaner;", "Ljava/util/Set;", "getPins", "()Ljava/util/Set;", "Companion", "Builder", "Pin", "okhttp"})
@SourceDebugExtension({"SMAP\nCertificatePinner.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CertificatePinner.kt\nokhttp3/CertificatePinner\n+ 2 Util.kt\nokhttp3/internal/Util\n*L\n1#1,370:1\n625#2,8:371\n*S KotlinDebug\n*F\n+ 1 CertificatePinner.kt\nokhttp3/CertificatePinner\n*L\n216#1:371,8\n*E\n"})
public final class CertificatePinner {
  public CertificatePinner(@NotNull Set<Pin> pins, @Nullable CertificateChainCleaner certificateChainCleaner) {
    this.pins = pins;
    this.certificateChainCleaner = certificateChainCleaner;
  }
  
  @NotNull
  public final Set<Pin> getPins() {
    return this.pins;
  }
  
  @Nullable
  public final CertificateChainCleaner getCertificateChainCleaner$okhttp() {
    return this.certificateChainCleaner;
  }
  
  public final void check(@NotNull String hostname, @NotNull List<? extends Certificate> peerCertificates) throws SSLPeerUnverifiedException {
    Intrinsics.checkNotNullParameter(hostname, "hostname");
    Intrinsics.checkNotNullParameter(peerCertificates, "peerCertificates");
    check$okhttp(hostname, new CertificatePinner$check$1(peerCertificates, hostname));
  }
  
  @Metadata(mv = {1, 8, 0}, k = 3, xi = 48, d1 = {"\000\f\n\002\020 \n\002\030\002\n\002\b\003\020\004\032\b\022\004\022\0020\0010\000H\n¢\006\004\b\002\020\003"}, d2 = {"", "Ljava/security/cert/X509Certificate;", "invoke", "()Ljava/util/List;", "<anonymous>"})
  @SourceDebugExtension({"SMAP\nCertificatePinner.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CertificatePinner.kt\nokhttp3/CertificatePinner$check$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,370:1\n1549#2:371\n1620#2,3:372\n*S KotlinDebug\n*F\n+ 1 CertificatePinner.kt\nokhttp3/CertificatePinner$check$1\n*L\n152#1:371\n152#1:372,3\n*E\n"})
  static final class CertificatePinner$check$1 extends Lambda implements Function0<List<? extends X509Certificate>> {
    @NotNull
    public final List<X509Certificate> invoke() {
      if (CertificatePinner.this.getCertificateChainCleaner$okhttp() == null || CertificatePinner.this.getCertificateChainCleaner$okhttp().clean(this.$peerCertificates, this.$hostname) == null)
        CertificatePinner.this.getCertificateChainCleaner$okhttp().clean(this.$peerCertificates, this.$hostname); 
      List<Certificate> list1 = this.$peerCertificates;
      int $i$f$map = 0;
      List<Certificate> list2 = list1;
      Collection<X509Certificate> destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
      int $i$f$mapTo = 0;
      for (Certificate item$iv$iv : list2) {
        Certificate certificate1 = item$iv$iv;
        Collection<X509Certificate> collection = destination$iv$iv;
        int $i$a$-map-CertificatePinner$check$1$1 = 0;
        Intrinsics.checkNotNull(certificate1, "null cannot be cast to non-null type java.security.cert.X509Certificate");
        collection.add((X509Certificate)certificate1);
      } 
      return (List<X509Certificate>)destination$iv$iv;
    }
    
    CertificatePinner$check$1(List<Certificate> $peerCertificates, String $hostname) {
      super(0);
    }
  }
  
  public final void check$okhttp(@NotNull String hostname, @NotNull Function0 cleanedPeerCertificatesFn) {
    Intrinsics.checkNotNullParameter(hostname, "hostname");
    Intrinsics.checkNotNullParameter(cleanedPeerCertificatesFn, "cleanedPeerCertificatesFn");
    List<Pin> pins = findMatchingPins(hostname);
    if (pins.isEmpty())
      return; 
    List peerCertificates = (List)cleanedPeerCertificatesFn.invoke();
    for (X509Certificate peerCertificate : peerCertificates) {
      ByteString sha1 = null;
      ByteString sha256 = null;
      for (Pin pin : pins) {
        String str = pin.getHashAlgorithm();
        if (Intrinsics.areEqual(str, "sha256")) {
          if (sha256 == null)
            sha256 = Companion.sha256Hash(peerCertificate); 
          if (Intrinsics.areEqual(pin.getHash(), sha256))
            return; 
          continue;
        } 
        if (Intrinsics.areEqual(str, "sha1")) {
          if (sha1 == null)
            sha1 = Companion.sha1Hash(peerCertificate); 
          if (Intrinsics.areEqual(pin.getHash(), sha1))
            return; 
          continue;
        } 
        throw new AssertionError("unsupported hashAlgorithm: " + pin.getHashAlgorithm());
      } 
    } 
    StringBuilder stringBuilder1 = new StringBuilder(), $this$check_u24lambda_u240 = stringBuilder1;
    int $i$a$-buildString-CertificatePinner$check$message$1 = 0;
    $this$check_u24lambda_u240.append("Certificate pinning failure!");
    $this$check_u24lambda_u240.append("\n  Peer certificate chain:");
    for (X509Certificate element : peerCertificates) {
      $this$check_u24lambda_u240.append("\n    ");
      $this$check_u24lambda_u240.append(Companion.pin(element));
      $this$check_u24lambda_u240.append(": ");
      $this$check_u24lambda_u240.append(element.getSubjectDN().getName());
    } 
    $this$check_u24lambda_u240.append("\n  Pinned certificates for ");
    $this$check_u24lambda_u240.append(hostname);
    $this$check_u24lambda_u240.append(":");
    for (Pin pin : pins) {
      $this$check_u24lambda_u240.append("\n    ");
      $this$check_u24lambda_u240.append(pin);
    } 
    Intrinsics.checkNotNullExpressionValue(stringBuilder1.toString(), "StringBuilder().apply(builderAction).toString()");
    String message = stringBuilder1.toString();
    throw new SSLPeerUnverifiedException(message);
  }
  
  @Deprecated(message = "replaced with {@link #check(String, List)}.", replaceWith = @ReplaceWith(expression = "check(hostname, peerCertificates.toList())", imports = {}))
  public final void check(@NotNull String hostname, @NotNull Certificate... peerCertificates) throws SSLPeerUnverifiedException {
    Intrinsics.checkNotNullParameter(hostname, "hostname");
    Intrinsics.checkNotNullParameter(peerCertificates, "peerCertificates");
    check(hostname, ArraysKt.toList((Object[])peerCertificates));
  }
  
  @NotNull
  public final List<Pin> findMatchingPins(@NotNull String hostname) {
    Intrinsics.checkNotNullParameter(hostname, "hostname");
    Iterable<Pin> $this$filterList$iv = this.pins;
    int $i$f$filterList = 0;
    List<Pin> result$iv = CollectionsKt.emptyList();
    for (Pin i$iv : $this$filterList$iv) {
      Pin $this$findMatchingPins_u24lambda_u241 = i$iv;
      int $i$a$-filterList-CertificatePinner$findMatchingPins$1 = 0;
      if ($this$findMatchingPins_u24lambda_u241.matchesHostname(hostname)) {
        if (result$iv.isEmpty())
          result$iv = new ArrayList(); 
        Intrinsics.checkNotNull(result$iv, "null cannot be cast to non-null type kotlin.collections.MutableList<T of okhttp3.internal.Util.filterList>");
        TypeIntrinsics.asMutableList(result$iv).add(i$iv);
      } 
    } 
    return result$iv;
  }
  
  @NotNull
  public final CertificatePinner withCertificateChainCleaner$okhttp(@NotNull CertificateChainCleaner certificateChainCleaner) {
    Intrinsics.checkNotNullParameter(certificateChainCleaner, "certificateChainCleaner");
    return Intrinsics.areEqual(this.certificateChainCleaner, certificateChainCleaner) ? this : new CertificatePinner(this.pins, certificateChainCleaner);
  }
  
  public boolean equals(@Nullable Object other) {
    return (other instanceof CertificatePinner && Intrinsics.areEqual(((CertificatePinner)other).pins, this.pins) && Intrinsics.areEqual(((CertificatePinner)other).certificateChainCleaner, this.certificateChainCleaner));
  }
  
  public int hashCode() {
    int result = 37;
    result = 41 * result + this.pins.hashCode();
    result = 41 * result + ((this.certificateChainCleaner != null) ? this.certificateChainCleaner.hashCode() : 0);
    return result;
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0000\n\002\030\002\n\002\020\000\n\002\020\016\n\002\b\005\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\002\n\002\030\002\n\002\b\b\n\002\030\002\n\002\b\t\030\0002\0020\001B\027\022\006\020\003\032\0020\002\022\006\020\004\032\0020\002¢\006\004\b\005\020\006J\032\020\t\032\0020\b2\b\020\007\032\004\030\0010\001H\002¢\006\004\b\t\020\nJ\017\020\f\032\0020\013H\026¢\006\004\b\f\020\rJ\025\020\020\032\0020\b2\006\020\017\032\0020\016¢\006\004\b\020\020\021J\025\020\023\032\0020\b2\006\020\022\032\0020\002¢\006\004\b\023\020\024J\017\020\025\032\0020\002H\026¢\006\004\b\025\020\026R\027\020\030\032\0020\0278\006¢\006\f\n\004\b\030\020\031\032\004\b\032\020\033R\027\020\034\032\0020\0028\006¢\006\f\n\004\b\034\020\035\032\004\b\036\020\026R\027\020\003\032\0020\0028\006¢\006\f\n\004\b\003\020\035\032\004\b\037\020\026¨\006 "}, d2 = {"Lokhttp3/CertificatePinner$Pin;", "", "", "pattern", "pin", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Ljava/security/cert/X509Certificate;", "certificate", "matchesCertificate", "(Ljava/security/cert/X509Certificate;)Z", "hostname", "matchesHostname", "(Ljava/lang/String;)Z", "toString", "()Ljava/lang/String;", "Lokio/ByteString;", "hash", "Lokio/ByteString;", "getHash", "()Lokio/ByteString;", "hashAlgorithm", "Ljava/lang/String;", "getHashAlgorithm", "getPattern", "okhttp"})
  public static final class Pin {
    @NotNull
    private final String pattern;
    
    @NotNull
    private final String hashAlgorithm;
    
    @NotNull
    private final ByteString hash;
    
    public Pin(@NotNull String pattern, @NotNull String pin) {
      if (!(((StringsKt.startsWith$default(pattern, "*.", false, 2, null) && StringsKt.indexOf$default(pattern, "*", 1, false, 4, null) == -1) || (StringsKt.startsWith$default(pattern, "**.", false, 2, null) && StringsKt.indexOf$default(pattern, "*", 2, false, 4, null) == -1) || StringsKt.indexOf$default(pattern, "*", 0, false, 6, null) == -1) ? 1 : 0)) {
        int $i$a$-require-CertificatePinner$Pin$1 = 0;
        String str = "Unexpected pattern: " + pattern;
        throw new IllegalArgumentException(str.toString());
      } 
      if (HostnamesKt.toCanonicalHost(pattern) == null) {
        HostnamesKt.toCanonicalHost(pattern);
        throw new IllegalArgumentException("Invalid pattern: " + pattern);
      } 
      ((Pin)HostnamesKt.toCanonicalHost(pattern)).pattern = HostnamesKt.toCanonicalHost(pattern);
      if (StringsKt.startsWith$default(pin, "sha1/", false, 2, null)) {
        this.hashAlgorithm = "sha1";
        Intrinsics.checkNotNullExpressionValue(pin.substring(5), "this as java.lang.String).substring(startIndex)");
        if (ByteString.Companion.decodeBase64(pin.substring(5)) == null) {
          ByteString.Companion.decodeBase64(pin.substring(5));
          throw new IllegalArgumentException("Invalid pin hash: " + pin);
        } 
        ((Pin)ByteString.Companion.decodeBase64(pin.substring(5))).hash = ByteString.Companion.decodeBase64(pin.substring(5));
      } else if (StringsKt.startsWith$default(pin, "sha256/", false, 2, null)) {
        this.hashAlgorithm = "sha256";
        Intrinsics.checkNotNullExpressionValue(pin.substring(7), "this as java.lang.String).substring(startIndex)");
        if (ByteString.Companion.decodeBase64(pin.substring(7)) == null) {
          ByteString.Companion.decodeBase64(pin.substring(7));
          throw new IllegalArgumentException("Invalid pin hash: " + pin);
        } 
        ((Pin)ByteString.Companion.decodeBase64(pin.substring(7))).hash = ByteString.Companion.decodeBase64(pin.substring(7));
      } else {
        throw new IllegalArgumentException("pins must start with 'sha256/' or 'sha1/': " + pin);
      } 
    }
    
    @NotNull
    public final String getPattern() {
      return this.pattern;
    }
    
    @NotNull
    public final String getHashAlgorithm() {
      return this.hashAlgorithm;
    }
    
    @NotNull
    public final ByteString getHash() {
      return this.hash;
    }
    
    public final boolean matchesHostname(@NotNull String hostname) {
      Intrinsics.checkNotNullParameter(hostname, "hostname");
      int suffixLength = this.pattern.length() - 3;
      int prefixLength = hostname.length() - suffixLength;
      suffixLength = this.pattern.length() - 1;
      prefixLength = hostname.length() - suffixLength;
      return StringsKt.startsWith$default(this.pattern, "**.", false, 2, null) ? ((StringsKt.regionMatches$default(hostname, hostname.length() - suffixLength, this.pattern, 3, suffixLength, false, 16, null) && (prefixLength == 0 || hostname.charAt(prefixLength - 1) == '.'))) : (StringsKt.startsWith$default(this.pattern, "*.", false, 2, null) ? ((StringsKt.regionMatches$default(hostname, hostname.length() - suffixLength, this.pattern, 1, suffixLength, false, 16, null) && StringsKt.lastIndexOf$default(hostname, '.', prefixLength - 1, false, 4, null) == -1)) : Intrinsics.areEqual(hostname, this.pattern));
    }
    
    public final boolean matchesCertificate(@NotNull X509Certificate certificate) {
      Intrinsics.checkNotNullParameter(certificate, "certificate");
      String str = this.hashAlgorithm;
      return Intrinsics.areEqual(str, "sha256") ? Intrinsics.areEqual(this.hash, CertificatePinner.Companion.sha256Hash(certificate)) : (Intrinsics.areEqual(str, "sha1") ? Intrinsics.areEqual(this.hash, CertificatePinner.Companion.sha1Hash(certificate)) : false);
    }
    
    @NotNull
    public String toString() {
      return this.hashAlgorithm + '/' + this.hash.base64();
    }
    
    public boolean equals(@Nullable Object other) {
      if (this == other)
        return true; 
      if (!(other instanceof Pin))
        return false; 
      if (!Intrinsics.areEqual(this.pattern, ((Pin)other).pattern))
        return false; 
      if (!Intrinsics.areEqual(this.hashAlgorithm, ((Pin)other).hashAlgorithm))
        return false; 
      if (!Intrinsics.areEqual(this.hash, ((Pin)other).hash))
        return false; 
      return true;
    }
    
    public int hashCode() {
      int result = this.pattern.hashCode();
      result = 31 * result + this.hashAlgorithm.hashCode();
      result = 31 * result + this.hash.hashCode();
      return result;
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000.\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\021\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020!\n\002\030\002\n\002\b\004\030\0002\0020\001B\007¢\006\004\b\002\020\003J)\020\b\032\0020\0002\006\020\005\032\0020\0042\022\020\007\032\n\022\006\b\001\022\0020\0040\006\"\0020\004¢\006\004\b\b\020\tJ\r\020\013\032\0020\n¢\006\004\b\013\020\fR\035\020\007\032\b\022\004\022\0020\0160\r8\006¢\006\f\n\004\b\007\020\017\032\004\b\020\020\021¨\006\022"}, d2 = {"Lokhttp3/CertificatePinner$Builder;", "", "<init>", "()V", "", "pattern", "", "pins", "add", "(Ljava/lang/String;[Ljava/lang/String;)Lokhttp3/CertificatePinner$Builder;", "Lokhttp3/CertificatePinner;", "build", "()Lokhttp3/CertificatePinner;", "", "Lokhttp3/CertificatePinner$Pin;", "Ljava/util/List;", "getPins", "()Ljava/util/List;", "okhttp"})
  public static final class Builder {
    @NotNull
    private final List<CertificatePinner.Pin> pins = new ArrayList<>();
    
    @NotNull
    public final List<CertificatePinner.Pin> getPins() {
      return this.pins;
    }
    
    @NotNull
    public final Builder add(@NotNull String pattern, @NotNull String... pins) {
      Intrinsics.checkNotNullParameter(pattern, "pattern");
      Intrinsics.checkNotNullParameter(pins, "pins");
      Builder builder1 = this, $this$add_u24lambda_u240 = builder1;
      int $i$a$-apply-CertificatePinner$Builder$add$1 = 0;
      byte b;
      int i;
      for (b = 0, i = pins.length; b < i; ) {
        String pin = pins[b];
        $this$add_u24lambda_u240.pins.add(new CertificatePinner.Pin(pattern, pin));
        b++;
      } 
      return builder1;
    }
    
    @NotNull
    public final CertificatePinner build() {
      return new CertificatePinner(CollectionsKt.toSet(this.pins), null, 2, null);
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000.\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\016\n\002\b\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\027\020\007\032\0020\0062\006\020\005\032\0020\004H\007¢\006\004\b\007\020\bJ\023\020\013\032\0020\n*\0020\tH\007¢\006\004\b\013\020\fJ\023\020\r\032\0020\n*\0020\tH\007¢\006\004\b\r\020\fR\024\020\017\032\0020\0168\006X\004¢\006\006\n\004\b\017\020\020¨\006\021"}, d2 = {"Lokhttp3/CertificatePinner$Companion;", "", "<init>", "()V", "Ljava/security/cert/Certificate;", "certificate", "", "pin", "(Ljava/security/cert/Certificate;)Ljava/lang/String;", "Ljava/security/cert/X509Certificate;", "Lokio/ByteString;", "sha1Hash", "(Ljava/security/cert/X509Certificate;)Lokio/ByteString;", "sha256Hash", "Lokhttp3/CertificatePinner;", "DEFAULT", "Lokhttp3/CertificatePinner;", "okhttp"})
  @SourceDebugExtension({"SMAP\nCertificatePinner.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CertificatePinner.kt\nokhttp3/CertificatePinner$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,370:1\n1#2:371\n*E\n"})
  public static final class Companion {
    private Companion() {}
    
    @JvmStatic
    @NotNull
    public final ByteString sha1Hash(@NotNull X509Certificate $this$sha1Hash) {
      Intrinsics.checkNotNullParameter($this$sha1Hash, "<this>");
      Intrinsics.checkNotNullExpressionValue($this$sha1Hash.getPublicKey().getEncoded(), "publicKey.encoded");
      return ByteString.Companion.of$default(ByteString.Companion, $this$sha1Hash.getPublicKey().getEncoded(), 0, 0, 3, null).sha1();
    }
    
    @JvmStatic
    @NotNull
    public final ByteString sha256Hash(@NotNull X509Certificate $this$sha256Hash) {
      Intrinsics.checkNotNullParameter($this$sha256Hash, "<this>");
      Intrinsics.checkNotNullExpressionValue($this$sha256Hash.getPublicKey().getEncoded(), "publicKey.encoded");
      return ByteString.Companion.of$default(ByteString.Companion, $this$sha256Hash.getPublicKey().getEncoded(), 0, 0, 3, null).sha256();
    }
    
    @JvmStatic
    @NotNull
    public final String pin(@NotNull Certificate certificate) {
      Intrinsics.checkNotNullParameter(certificate, "certificate");
      if (!(certificate instanceof X509Certificate)) {
        int $i$a$-require-CertificatePinner$Companion$pin$1 = 0;
        String str = "Certificate pinning requires X509 certificates";
        throw new IllegalArgumentException(str.toString());
      } 
      return "sha256/" + sha256Hash((X509Certificate)certificate).base64();
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final Set<Pin> pins;
  
  @Nullable
  private final CertificateChainCleaner certificateChainCleaner;
  
  @JvmField
  @NotNull
  public static final CertificatePinner DEFAULT = (new Builder()).build();
  
  @JvmStatic
  @NotNull
  public static final ByteString sha1Hash(@NotNull X509Certificate $this$sha1Hash) {
    return Companion.sha1Hash($this$sha1Hash);
  }
  
  @JvmStatic
  @NotNull
  public static final ByteString sha256Hash(@NotNull X509Certificate $this$sha256Hash) {
    return Companion.sha256Hash($this$sha256Hash);
  }
  
  @JvmStatic
  @NotNull
  public static final String pin(@NotNull Certificate certificate) {
    return Companion.pin(certificate);
  }
}
