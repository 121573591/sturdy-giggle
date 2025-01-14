package okhttp3.internal.tls;

import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000:\n\002\030\002\n\002\030\002\n\002\020\021\n\002\030\002\n\002\b\003\n\002\020\000\n\000\n\002\020\013\n\002\b\005\n\002\020\b\n\002\b\002\n\002\020$\n\002\030\002\n\002\020\"\n\002\b\003\030\0002\0020\001B\033\022\022\020\004\032\n\022\006\b\001\022\0020\0030\002\"\0020\003¢\006\004\b\005\020\006J\032\020\n\032\0020\t2\b\020\b\032\004\030\0010\007H\002¢\006\004\b\n\020\013J\031\020\r\032\004\030\0010\0032\006\020\f\032\0020\003H\026¢\006\004\b\r\020\016J\017\020\020\032\0020\017H\026¢\006\004\b\020\020\021R&\020\025\032\024\022\004\022\0020\023\022\n\022\b\022\004\022\0020\0030\0240\0228\002X\004¢\006\006\n\004\b\025\020\026¨\006\027"}, d2 = {"Lokhttp3/internal/tls/BasicTrustRootIndex;", "Lokhttp3/internal/tls/TrustRootIndex;", "", "Ljava/security/cert/X509Certificate;", "caCerts", "<init>", "([Ljava/security/cert/X509Certificate;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "cert", "findByIssuerAndSignature", "(Ljava/security/cert/X509Certificate;)Ljava/security/cert/X509Certificate;", "", "hashCode", "()I", "", "Ljavax/security/auth/x500/X500Principal;", "", "subjectToCaCerts", "Ljava/util/Map;", "okhttp"})
@SourceDebugExtension({"SMAP\nBasicTrustRootIndex.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BasicTrustRootIndex.kt\nokhttp3/internal/tls/BasicTrustRootIndex\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,56:1\n372#2,7:57\n288#3,2:64\n*S KotlinDebug\n*F\n+ 1 BasicTrustRootIndex.kt\nokhttp3/internal/tls/BasicTrustRootIndex\n*L\n28#1:57,7\n37#1:64,2\n*E\n"})
public final class BasicTrustRootIndex implements TrustRootIndex {
  @NotNull
  private final Map<X500Principal, Set<X509Certificate>> subjectToCaCerts;
  
  public BasicTrustRootIndex(@NotNull X509Certificate... caCerts) {
    Map<Object, Object> map = new LinkedHashMap<>();
    byte b = 0;
    int i = caCerts.length;
    while (true) {
      X509Certificate caCert;
      if (b < i) {
        caCert = caCerts[b];
        Map<Object, Object> map1 = map;
        Intrinsics.checkNotNullExpressionValue(caCert.getSubjectX500Principal(), "caCert.subjectX500Principal");
        Object key$iv = caCert.getSubjectX500Principal();
        int $i$f$getOrPut = 0;
        Object value$iv = map1.get(key$iv);
        if (value$iv == null) {
          int $i$a$-getOrPut-BasicTrustRootIndex$1 = 0;
          Object answer$iv = new LinkedHashSet();
          map1.put(key$iv, answer$iv);
        } 
      } else {
        break;
      } 
      ((Set<X509Certificate>)value$iv).add(caCert);
      b++;
    } 
    this.subjectToCaCerts = (Map)map;
  }
  
  @Nullable
  public X509Certificate findByIssuerAndSignature(@NotNull X509Certificate cert) {
    // Byte code:
    //   0: aload_1
    //   1: ldc 'cert'
    //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_1
    //   7: invokevirtual getIssuerX500Principal : ()Ljavax/security/auth/x500/X500Principal;
    //   10: astore_2
    //   11: aload_0
    //   12: getfield subjectToCaCerts : Ljava/util/Map;
    //   15: aload_2
    //   16: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   21: checkcast java/util/Set
    //   24: dup
    //   25: ifnonnull -> 31
    //   28: pop
    //   29: aconst_null
    //   30: areturn
    //   31: astore_3
    //   32: aload_3
    //   33: checkcast java/lang/Iterable
    //   36: astore #4
    //   38: iconst_0
    //   39: istore #5
    //   41: aload #4
    //   43: invokeinterface iterator : ()Ljava/util/Iterator;
    //   48: astore #6
    //   50: aload #6
    //   52: invokeinterface hasNext : ()Z
    //   57: ifeq -> 104
    //   60: aload #6
    //   62: invokeinterface next : ()Ljava/lang/Object;
    //   67: astore #7
    //   69: aload #7
    //   71: checkcast java/security/cert/X509Certificate
    //   74: astore #8
    //   76: iconst_0
    //   77: istore #9
    //   79: nop
    //   80: aload_1
    //   81: aload #8
    //   83: invokevirtual getPublicKey : ()Ljava/security/PublicKey;
    //   86: invokevirtual verify : (Ljava/security/PublicKey;)V
    //   89: iconst_1
    //   90: goto -> 96
    //   93: astore #10
    //   95: iconst_0
    //   96: ifeq -> 50
    //   99: aload #7
    //   101: goto -> 105
    //   104: aconst_null
    //   105: checkcast java/security/cert/X509Certificate
    //   108: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #34	-> 6
    //   #35	-> 11
    //   #37	-> 32
    //   #64	-> 41
    //   #38	-> 79
    //   #39	-> 80
    //   #40	-> 89
    //   #41	-> 93
    //   #42	-> 95
    //   #64	-> 96
    //   #65	-> 104
    //   #37	-> 108
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   95	1	10	_	Ljava/lang/Exception;
    //   79	17	9	$i$a$-firstOrNull-BasicTrustRootIndex$findByIssuerAndSignature$1	I
    //   76	20	8	it	Ljava/security/cert/X509Certificate;
    //   69	35	7	element$iv	Ljava/lang/Object;
    //   41	64	5	$i$f$firstOrNull	I
    //   38	67	4	$this$firstOrNull$iv	Ljava/lang/Iterable;
    //   11	98	2	issuer	Ljavax/security/auth/x500/X500Principal;
    //   32	77	3	subjectCaCerts	Ljava/util/Set;
    //   0	109	0	this	Lokhttp3/internal/tls/BasicTrustRootIndex;
    //   0	109	1	cert	Ljava/security/cert/X509Certificate;
    // Exception table:
    //   from	to	target	type
    //   79	93	93	java/lang/Exception
  }
  
  public boolean equals(@Nullable Object other) {
    return (other == this || (other instanceof BasicTrustRootIndex && Intrinsics.areEqual(((BasicTrustRootIndex)other).subjectToCaCerts, this.subjectToCaCerts)));
  }
  
  public int hashCode() {
    return this.subjectToCaCerts.hashCode();
  }
}
