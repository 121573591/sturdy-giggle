package okhttp3.internal.tls;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okio.Utf8;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0006\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020 \n\002\020\016\n\002\b\002\n\002\020\b\n\002\b\004\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\020\bÆ\002\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\033\020\b\032\b\022\004\022\0020\0070\0062\006\020\005\032\0020\004¢\006\004\b\b\020\tJ%\020\f\032\b\022\004\022\0020\0070\0062\006\020\005\032\0020\0042\006\020\013\032\0020\nH\002¢\006\004\b\f\020\rJ\035\020\020\032\0020\0172\006\020\016\032\0020\0072\006\020\005\032\0020\004¢\006\004\b\020\020\021J\037\020\020\032\0020\0172\006\020\016\032\0020\0072\006\020\023\032\0020\022H\026¢\006\004\b\020\020\024J\037\020\026\032\0020\0172\006\020\025\032\0020\0072\006\020\005\032\0020\004H\002¢\006\004\b\026\020\021J#\020\026\032\0020\0172\b\020\025\032\004\030\0010\0072\b\020\027\032\004\030\0010\007H\002¢\006\004\b\026\020\030J\037\020\032\032\0020\0172\006\020\031\032\0020\0072\006\020\005\032\0020\004H\002¢\006\004\b\032\020\021J\023\020\033\032\0020\007*\0020\007H\002¢\006\004\b\033\020\034J\023\020\035\032\0020\017*\0020\007H\002¢\006\004\b\035\020\036R\024\020\037\032\0020\n8\002XT¢\006\006\n\004\b\037\020 R\024\020!\032\0020\n8\002XT¢\006\006\n\004\b!\020 ¨\006\""}, d2 = {"Lokhttp3/internal/tls/OkHostnameVerifier;", "Ljavax/net/ssl/HostnameVerifier;", "<init>", "()V", "Ljava/security/cert/X509Certificate;", "certificate", "", "", "allSubjectAltNames", "(Ljava/security/cert/X509Certificate;)Ljava/util/List;", "", "type", "getSubjectAltNames", "(Ljava/security/cert/X509Certificate;I)Ljava/util/List;", "host", "", "verify", "(Ljava/lang/String;Ljava/security/cert/X509Certificate;)Z", "Ljavax/net/ssl/SSLSession;", "session", "(Ljava/lang/String;Ljavax/net/ssl/SSLSession;)Z", "hostname", "verifyHostname", "pattern", "(Ljava/lang/String;Ljava/lang/String;)Z", "ipAddress", "verifyIpAddress", "asciiToLowercase", "(Ljava/lang/String;)Ljava/lang/String;", "isAscii", "(Ljava/lang/String;)Z", "ALT_DNS_NAME", "I", "ALT_IPA_NAME", "okhttp"})
@SourceDebugExtension({"SMAP\nOkHostnameVerifier.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OkHostnameVerifier.kt\nokhttp3/internal/tls/OkHostnameVerifier\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,208:1\n1747#2,3:209\n1747#2,3:212\n*S KotlinDebug\n*F\n+ 1 OkHostnameVerifier.kt\nokhttp3/internal/tls/OkHostnameVerifier\n*L\n63#1:209,3\n71#1:212,3\n*E\n"})
public final class OkHostnameVerifier implements HostnameVerifier {
  @NotNull
  public static final OkHostnameVerifier INSTANCE = new OkHostnameVerifier();
  
  private static final int ALT_DNS_NAME = 2;
  
  private static final int ALT_IPA_NAME = 7;
  
  public boolean verify(@NotNull String host, @NotNull SSLSession session) {
    boolean bool;
    Intrinsics.checkNotNullParameter(host, "host");
    Intrinsics.checkNotNullParameter(session, "session");
    try {
      Intrinsics.checkNotNull(session.getPeerCertificates()[0], "null cannot be cast to non-null type java.security.cert.X509Certificate");
      bool = verify(host, (X509Certificate)session.getPeerCertificates()[0]);
    } catch (SSLException _) {
      bool = false;
    } 
    return !isAscii(host) ? false : bool;
  }
  
  public final boolean verify(@NotNull String host, @NotNull X509Certificate certificate) {
    Intrinsics.checkNotNullParameter(host, "host");
    Intrinsics.checkNotNullParameter(certificate, "certificate");
    return 
      Util.canParseAsIpAddress(host) ? verifyIpAddress(host, certificate) : 
      verifyHostname(host, certificate);
  }
  
  private final boolean verifyIpAddress(String ipAddress, X509Certificate certificate) {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic toCanonicalHost : (Ljava/lang/String;)Ljava/lang/String;
    //   4: astore_3
    //   5: aload_0
    //   6: aload_2
    //   7: bipush #7
    //   9: invokespecial getSubjectAltNames : (Ljava/security/cert/X509Certificate;I)Ljava/util/List;
    //   12: checkcast java/lang/Iterable
    //   15: astore #4
    //   17: iconst_0
    //   18: istore #5
    //   20: aload #4
    //   22: instanceof java/util/Collection
    //   25: ifeq -> 45
    //   28: aload #4
    //   30: checkcast java/util/Collection
    //   33: invokeinterface isEmpty : ()Z
    //   38: ifeq -> 45
    //   41: iconst_0
    //   42: goto -> 100
    //   45: aload #4
    //   47: invokeinterface iterator : ()Ljava/util/Iterator;
    //   52: astore #6
    //   54: aload #6
    //   56: invokeinterface hasNext : ()Z
    //   61: ifeq -> 99
    //   64: aload #6
    //   66: invokeinterface next : ()Ljava/lang/Object;
    //   71: astore #7
    //   73: aload #7
    //   75: checkcast java/lang/String
    //   78: astore #8
    //   80: iconst_0
    //   81: istore #9
    //   83: aload_3
    //   84: aload #8
    //   86: invokestatic toCanonicalHost : (Ljava/lang/String;)Ljava/lang/String;
    //   89: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   92: ifeq -> 54
    //   95: iconst_1
    //   96: goto -> 100
    //   99: iconst_0
    //   100: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #61	-> 0
    //   #63	-> 5
    //   #209	-> 20
    //   #210	-> 45
    //   #64	-> 83
    //   #210	-> 92
    //   #211	-> 99
    //   #63	-> 100
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   83	9	9	$i$a$-any-OkHostnameVerifier$verifyIpAddress$1	I
    //   80	12	8	it	Ljava/lang/String;
    //   73	26	7	element$iv	Ljava/lang/Object;
    //   20	80	5	$i$f$any	I
    //   17	83	4	$this$any$iv	Ljava/lang/Iterable;
    //   5	96	3	canonicalIpAddress	Ljava/lang/String;
    //   0	101	0	this	Lokhttp3/internal/tls/OkHostnameVerifier;
    //   0	101	1	ipAddress	Ljava/lang/String;
    //   0	101	2	certificate	Ljava/security/cert/X509Certificate;
  }
  
  private final boolean verifyHostname(String hostname, X509Certificate certificate) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial asciiToLowercase : (Ljava/lang/String;)Ljava/lang/String;
    //   5: astore_3
    //   6: aload_0
    //   7: aload_2
    //   8: iconst_2
    //   9: invokespecial getSubjectAltNames : (Ljava/security/cert/X509Certificate;I)Ljava/util/List;
    //   12: checkcast java/lang/Iterable
    //   15: astore #4
    //   17: iconst_0
    //   18: istore #5
    //   20: aload #4
    //   22: instanceof java/util/Collection
    //   25: ifeq -> 45
    //   28: aload #4
    //   30: checkcast java/util/Collection
    //   33: invokeinterface isEmpty : ()Z
    //   38: ifeq -> 45
    //   41: iconst_0
    //   42: goto -> 100
    //   45: aload #4
    //   47: invokeinterface iterator : ()Ljava/util/Iterator;
    //   52: astore #6
    //   54: aload #6
    //   56: invokeinterface hasNext : ()Z
    //   61: ifeq -> 99
    //   64: aload #6
    //   66: invokeinterface next : ()Ljava/lang/Object;
    //   71: astore #7
    //   73: aload #7
    //   75: checkcast java/lang/String
    //   78: astore #8
    //   80: iconst_0
    //   81: istore #9
    //   83: getstatic okhttp3/internal/tls/OkHostnameVerifier.INSTANCE : Lokhttp3/internal/tls/OkHostnameVerifier;
    //   86: aload_3
    //   87: aload #8
    //   89: invokespecial verifyHostname : (Ljava/lang/String;Ljava/lang/String;)Z
    //   92: ifeq -> 54
    //   95: iconst_1
    //   96: goto -> 100
    //   99: iconst_0
    //   100: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #70	-> 0
    //   #71	-> 6
    //   #212	-> 20
    //   #213	-> 45
    //   #72	-> 83
    //   #213	-> 92
    //   #214	-> 99
    //   #71	-> 100
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   83	9	9	$i$a$-any-OkHostnameVerifier$verifyHostname$1	I
    //   80	12	8	it	Ljava/lang/String;
    //   73	26	7	element$iv	Ljava/lang/Object;
    //   20	80	5	$i$f$any	I
    //   17	83	4	$this$any$iv	Ljava/lang/Iterable;
    //   6	95	3	hostname	Ljava/lang/String;
    //   0	101	0	this	Lokhttp3/internal/tls/OkHostnameVerifier;
    //   0	101	1	hostname	Ljava/lang/String;
    //   0	101	2	certificate	Ljava/security/cert/X509Certificate;
  }
  
  private final String asciiToLowercase(String $this$asciiToLowercase) {
    String str = $this$asciiToLowercase;
    Intrinsics.checkNotNullExpressionValue(Locale.US, "US");
    Intrinsics.checkNotNullExpressionValue(str.toLowerCase(Locale.US), "this as java.lang.String).toLowerCase(locale)");
    return isAscii($this$asciiToLowercase) ? str.toLowerCase(Locale.US) : 
      $this$asciiToLowercase;
  }
  
  private final boolean isAscii(String $this$isAscii) {
    return ($this$isAscii.length() == (int)Utf8.size$default($this$isAscii, 0, 0, 3, null));
  }
  
  private final boolean verifyHostname(String hostname, String pattern) {
    String str1 = hostname;
    String str2 = pattern;
    String str3 = str1;
    if (((str3 == null || str3.length() == 0)) || 
      StringsKt.startsWith$default(str1, ".", false, 2, null) || 
      StringsKt.endsWith$default(str1, "..", false, 2, null))
      return false; 
    str3 = str2;
    if (((str3 == null || str3.length() == 0)) || 
      StringsKt.startsWith$default(str2, ".", false, 2, null) || 
      StringsKt.endsWith$default(str2, "..", false, 2, null))
      return false; 
    if (!StringsKt.endsWith$default(str1, ".", false, 2, null))
      str1 = str1 + '.'; 
    if (!StringsKt.endsWith$default(str2, ".", false, 2, null))
      str2 = str2 + '.'; 
    str2 = asciiToLowercase(str2);
    if (!StringsKt.contains$default(str2, "*", false, 2, null))
      return Intrinsics.areEqual(str1, str2); 
    if (!StringsKt.startsWith$default(str2, "*.", false, 2, null) || StringsKt.indexOf$default(str2, '*', 1, false, 4, null) != -1)
      return false; 
    if (str1.length() < str2.length())
      return false; 
    if (Intrinsics.areEqual("*.", str2))
      return false; 
    Intrinsics.checkNotNullExpressionValue(str2.substring(1), "this as java.lang.String).substring(startIndex)");
    String suffix = str2.substring(1);
    if (!StringsKt.endsWith$default(str1, suffix, false, 2, null))
      return false; 
    int suffixStartIndexInHostname = str1.length() - suffix.length();
    if (suffixStartIndexInHostname > 0 && 
      StringsKt.lastIndexOf$default(str1, '.', suffixStartIndexInHostname - 1, false, 4, null) != -1)
      return false; 
    return true;
  }
  
  @NotNull
  public final List<String> allSubjectAltNames(@NotNull X509Certificate certificate) {
    Intrinsics.checkNotNullParameter(certificate, "certificate");
    List<String> altIpaNames = getSubjectAltNames(certificate, 7);
    List<String> altDnsNames = getSubjectAltNames(certificate, 2);
    return CollectionsKt.plus(altIpaNames, altDnsNames);
  }
  
  private final List<String> getSubjectAltNames(X509Certificate certificate, int type) {
    try {
      Collection<List<?>> subjectAltNames;
      if (certificate.getSubjectAlternativeNames() == null) {
        certificate.getSubjectAlternativeNames();
        return CollectionsKt.emptyList();
      } 
      List<String> result = new ArrayList();
      for (List<?> subjectAltName : subjectAltNames) {
        if (subjectAltName != null && subjectAltName.size() >= 2 && 
          Intrinsics.areEqual(subjectAltName.get(0), Integer.valueOf(type))) {
          Object altName;
          if (subjectAltName.get(1) == null) {
            subjectAltName.get(1);
            continue;
          } 
          result.add((String)altName);
        } 
      } 
      return result;
    } catch (CertificateParsingException _) {
      return CollectionsKt.emptyList();
    } 
  }
}
