package okhttp3;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\020\n\002\030\002\n\002\020\000\n\002\020\016\n\002\b\t\030\000 \n2\0020\001:\001\nB\021\b\002\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\017\020\003\032\0020\002H\007¢\006\004\b\006\020\007J\017\020\b\032\0020\002H\026¢\006\004\b\b\020\007R\027\020\003\032\0020\0028\007¢\006\f\n\004\b\003\020\t\032\004\b\003\020\007¨\006\013"}, d2 = {"Lokhttp3/CipherSuite;", "", "", "javaName", "<init>", "(Ljava/lang/String;)V", "-deprecated_javaName", "()Ljava/lang/String;", "toString", "Ljava/lang/String;", "Companion", "okhttp"})
public final class CipherSuite {
  private CipherSuite(String javaName) {
    this.javaName = javaName;
  }
  
  @JvmName(name = "javaName")
  @NotNull
  public final String javaName() {
    return this.javaName;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "javaName", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_javaName")
  @NotNull
  public final String -deprecated_javaName() {
    return this.javaName;
  }
  
  @NotNull
  public String toString() {
    return this.javaName;
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0006\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\005\n\002\020%\n\002\b\002\n\002\030\002\n\002\030\002\n\002\b}\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\027\020\007\032\0020\0062\006\020\005\032\0020\004H\007¢\006\004\b\007\020\bJ\037\020\013\032\0020\0062\006\020\005\032\0020\0042\006\020\n\032\0020\tH\002¢\006\004\b\013\020\fJ\027\020\r\032\0020\0042\006\020\005\032\0020\004H\002¢\006\004\b\r\020\016R \020\020\032\016\022\004\022\0020\004\022\004\022\0020\0060\0178\002X\004¢\006\006\n\004\b\020\020\021R*\020\024\032\022\022\004\022\0020\0040\022j\b\022\004\022\0020\004`\0238\000X\004¢\006\f\n\004\b\024\020\025\032\004\b\026\020\027R\024\020\030\032\0020\0068\006X\004¢\006\006\n\004\b\030\020\031R\024\020\032\032\0020\0068\006X\004¢\006\006\n\004\b\032\020\031R\024\020\033\032\0020\0068\006X\004¢\006\006\n\004\b\033\020\031R\024\020\034\032\0020\0068\006X\004¢\006\006\n\004\b\034\020\031R\024\020\035\032\0020\0068\006X\004¢\006\006\n\004\b\035\020\031R\024\020\036\032\0020\0068\006X\004¢\006\006\n\004\b\036\020\031R\024\020\037\032\0020\0068\006X\004¢\006\006\n\004\b\037\020\031R\024\020 \032\0020\0068\006X\004¢\006\006\n\004\b \020\031R\024\020!\032\0020\0068\006X\004¢\006\006\n\004\b!\020\031R\024\020\"\032\0020\0068\006X\004¢\006\006\n\004\b\"\020\031R\024\020#\032\0020\0068\006X\004¢\006\006\n\004\b#\020\031R\024\020$\032\0020\0068\006X\004¢\006\006\n\004\b$\020\031R\024\020%\032\0020\0068\006X\004¢\006\006\n\004\b%\020\031R\024\020&\032\0020\0068\006X\004¢\006\006\n\004\b&\020\031R\024\020'\032\0020\0068\006X\004¢\006\006\n\004\b'\020\031R\024\020(\032\0020\0068\006X\004¢\006\006\n\004\b(\020\031R\024\020)\032\0020\0068\006X\004¢\006\006\n\004\b)\020\031R\024\020*\032\0020\0068\006X\004¢\006\006\n\004\b*\020\031R\024\020+\032\0020\0068\006X\004¢\006\006\n\004\b+\020\031R\024\020,\032\0020\0068\006X\004¢\006\006\n\004\b,\020\031R\024\020-\032\0020\0068\006X\004¢\006\006\n\004\b-\020\031R\024\020.\032\0020\0068\006X\004¢\006\006\n\004\b.\020\031R\024\020/\032\0020\0068\006X\004¢\006\006\n\004\b/\020\031R\024\0200\032\0020\0068\006X\004¢\006\006\n\004\b0\020\031R\024\0201\032\0020\0068\006X\004¢\006\006\n\004\b1\020\031R\024\0202\032\0020\0068\006X\004¢\006\006\n\004\b2\020\031R\024\0203\032\0020\0068\006X\004¢\006\006\n\004\b3\020\031R\024\0204\032\0020\0068\006X\004¢\006\006\n\004\b4\020\031R\024\0205\032\0020\0068\006X\004¢\006\006\n\004\b5\020\031R\024\0206\032\0020\0068\006X\004¢\006\006\n\004\b6\020\031R\024\0207\032\0020\0068\006X\004¢\006\006\n\004\b7\020\031R\024\0208\032\0020\0068\006X\004¢\006\006\n\004\b8\020\031R\024\0209\032\0020\0068\006X\004¢\006\006\n\004\b9\020\031R\024\020:\032\0020\0068\006X\004¢\006\006\n\004\b:\020\031R\024\020;\032\0020\0068\006X\004¢\006\006\n\004\b;\020\031R\024\020<\032\0020\0068\006X\004¢\006\006\n\004\b<\020\031R\024\020=\032\0020\0068\006X\004¢\006\006\n\004\b=\020\031R\024\020>\032\0020\0068\006X\004¢\006\006\n\004\b>\020\031R\024\020?\032\0020\0068\006X\004¢\006\006\n\004\b?\020\031R\024\020@\032\0020\0068\006X\004¢\006\006\n\004\b@\020\031R\024\020A\032\0020\0068\006X\004¢\006\006\n\004\bA\020\031R\024\020B\032\0020\0068\006X\004¢\006\006\n\004\bB\020\031R\024\020C\032\0020\0068\006X\004¢\006\006\n\004\bC\020\031R\024\020D\032\0020\0068\006X\004¢\006\006\n\004\bD\020\031R\024\020E\032\0020\0068\006X\004¢\006\006\n\004\bE\020\031R\024\020F\032\0020\0068\006X\004¢\006\006\n\004\bF\020\031R\024\020G\032\0020\0068\006X\004¢\006\006\n\004\bG\020\031R\024\020H\032\0020\0068\006X\004¢\006\006\n\004\bH\020\031R\024\020I\032\0020\0068\006X\004¢\006\006\n\004\bI\020\031R\024\020J\032\0020\0068\006X\004¢\006\006\n\004\bJ\020\031R\024\020K\032\0020\0068\006X\004¢\006\006\n\004\bK\020\031R\024\020L\032\0020\0068\006X\004¢\006\006\n\004\bL\020\031R\024\020M\032\0020\0068\006X\004¢\006\006\n\004\bM\020\031R\024\020N\032\0020\0068\006X\004¢\006\006\n\004\bN\020\031R\024\020O\032\0020\0068\006X\004¢\006\006\n\004\bO\020\031R\024\020P\032\0020\0068\006X\004¢\006\006\n\004\bP\020\031R\024\020Q\032\0020\0068\006X\004¢\006\006\n\004\bQ\020\031R\024\020R\032\0020\0068\006X\004¢\006\006\n\004\bR\020\031R\024\020S\032\0020\0068\006X\004¢\006\006\n\004\bS\020\031R\024\020T\032\0020\0068\006X\004¢\006\006\n\004\bT\020\031R\024\020U\032\0020\0068\006X\004¢\006\006\n\004\bU\020\031R\024\020V\032\0020\0068\006X\004¢\006\006\n\004\bV\020\031R\024\020W\032\0020\0068\006X\004¢\006\006\n\004\bW\020\031R\024\020X\032\0020\0068\006X\004¢\006\006\n\004\bX\020\031R\024\020Y\032\0020\0068\006X\004¢\006\006\n\004\bY\020\031R\024\020Z\032\0020\0068\006X\004¢\006\006\n\004\bZ\020\031R\024\020[\032\0020\0068\006X\004¢\006\006\n\004\b[\020\031R\024\020\\\032\0020\0068\006X\004¢\006\006\n\004\b\\\020\031R\024\020]\032\0020\0068\006X\004¢\006\006\n\004\b]\020\031R\024\020^\032\0020\0068\006X\004¢\006\006\n\004\b^\020\031R\024\020_\032\0020\0068\006X\004¢\006\006\n\004\b_\020\031R\024\020`\032\0020\0068\006X\004¢\006\006\n\004\b`\020\031R\024\020a\032\0020\0068\006X\004¢\006\006\n\004\ba\020\031R\024\020b\032\0020\0068\006X\004¢\006\006\n\004\bb\020\031R\024\020c\032\0020\0068\006X\004¢\006\006\n\004\bc\020\031R\024\020d\032\0020\0068\006X\004¢\006\006\n\004\bd\020\031R\024\020e\032\0020\0068\006X\004¢\006\006\n\004\be\020\031R\024\020f\032\0020\0068\006X\004¢\006\006\n\004\bf\020\031R\024\020g\032\0020\0068\006X\004¢\006\006\n\004\bg\020\031R\024\020h\032\0020\0068\006X\004¢\006\006\n\004\bh\020\031R\024\020i\032\0020\0068\006X\004¢\006\006\n\004\bi\020\031R\024\020j\032\0020\0068\006X\004¢\006\006\n\004\bj\020\031R\024\020k\032\0020\0068\006X\004¢\006\006\n\004\bk\020\031R\024\020l\032\0020\0068\006X\004¢\006\006\n\004\bl\020\031R\024\020m\032\0020\0068\006X\004¢\006\006\n\004\bm\020\031R\024\020n\032\0020\0068\006X\004¢\006\006\n\004\bn\020\031R\024\020o\032\0020\0068\006X\004¢\006\006\n\004\bo\020\031R\024\020p\032\0020\0068\006X\004¢\006\006\n\004\bp\020\031R\024\020q\032\0020\0068\006X\004¢\006\006\n\004\bq\020\031R\024\020r\032\0020\0068\006X\004¢\006\006\n\004\br\020\031R\024\020s\032\0020\0068\006X\004¢\006\006\n\004\bs\020\031R\024\020t\032\0020\0068\006X\004¢\006\006\n\004\bt\020\031R\024\020u\032\0020\0068\006X\004¢\006\006\n\004\bu\020\031R\024\020v\032\0020\0068\006X\004¢\006\006\n\004\bv\020\031R\024\020w\032\0020\0068\006X\004¢\006\006\n\004\bw\020\031R\024\020x\032\0020\0068\006X\004¢\006\006\n\004\bx\020\031R\024\020y\032\0020\0068\006X\004¢\006\006\n\004\by\020\031R\024\020z\032\0020\0068\006X\004¢\006\006\n\004\bz\020\031R\024\020{\032\0020\0068\006X\004¢\006\006\n\004\b{\020\031R\024\020|\032\0020\0068\006X\004¢\006\006\n\004\b|\020\031R\024\020}\032\0020\0068\006X\004¢\006\006\n\004\b}\020\031R\024\020~\032\0020\0068\006X\004¢\006\006\n\004\b~\020\031R\024\020\032\0020\0068\006X\004¢\006\006\n\004\b\020\031R\026\020\001\032\0020\0068\006X\004¢\006\007\n\005\b\001\020\031R\026\020\001\032\0020\0068\006X\004¢\006\007\n\005\b\001\020\031R\026\020\001\032\0020\0068\006X\004¢\006\007\n\005\b\001\020\031R\026\020\001\032\0020\0068\006X\004¢\006\007\n\005\b\001\020\031R\026\020\001\032\0020\0068\006X\004¢\006\007\n\005\b\001\020\031R\026\020\001\032\0020\0068\006X\004¢\006\007\n\005\b\001\020\031R\026\020\001\032\0020\0068\006X\004¢\006\007\n\005\b\001\020\031R\026\020\001\032\0020\0068\006X\004¢\006\007\n\005\b\001\020\031R\026\020\001\032\0020\0068\006X\004¢\006\007\n\005\b\001\020\031R\026\020\001\032\0020\0068\006X\004¢\006\007\n\005\b\001\020\031R\026\020\001\032\0020\0068\006X\004¢\006\007\n\005\b\001\020\031R\026\020\001\032\0020\0068\006X\004¢\006\007\n\005\b\001\020\031R\026\020\001\032\0020\0068\006X\004¢\006\007\n\005\b\001\020\031R\026\020\001\032\0020\0068\006X\004¢\006\007\n\005\b\001\020\031R\026\020\001\032\0020\0068\006X\004¢\006\007\n\005\b\001\020\031R\026\020\001\032\0020\0068\006X\004¢\006\007\n\005\b\001\020\031¨\006\001"}, d2 = {"Lokhttp3/CipherSuite$Companion;", "", "<init>", "()V", "", "javaName", "Lokhttp3/CipherSuite;", "forJavaName", "(Ljava/lang/String;)Lokhttp3/CipherSuite;", "", "value", "init", "(Ljava/lang/String;I)Lokhttp3/CipherSuite;", "secondaryName", "(Ljava/lang/String;)Ljava/lang/String;", "", "INSTANCES", "Ljava/util/Map;", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "ORDER_BY_NAME", "Ljava/util/Comparator;", "getORDER_BY_NAME$okhttp", "()Ljava/util/Comparator;", "TLS_AES_128_CCM_8_SHA256", "Lokhttp3/CipherSuite;", "TLS_AES_128_CCM_SHA256", "TLS_AES_128_GCM_SHA256", "TLS_AES_256_GCM_SHA384", "TLS_CHACHA20_POLY1305_SHA256", "TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA", "TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA", "TLS_DHE_DSS_WITH_AES_128_CBC_SHA", "TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", "TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", "TLS_DHE_DSS_WITH_AES_256_CBC_SHA", "TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", "TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", "TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA", "TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA", "TLS_DHE_DSS_WITH_DES_CBC_SHA", "TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA", "TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA", "TLS_DHE_RSA_WITH_AES_128_CBC_SHA", "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", "TLS_DHE_RSA_WITH_AES_256_CBC_SHA", "TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", "TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", "TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA", "TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA", "TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256", "TLS_DHE_RSA_WITH_DES_CBC_SHA", "TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA", "TLS_DH_anon_EXPORT_WITH_RC4_40_MD5", "TLS_DH_anon_WITH_3DES_EDE_CBC_SHA", "TLS_DH_anon_WITH_AES_128_CBC_SHA", "TLS_DH_anon_WITH_AES_128_CBC_SHA256", "TLS_DH_anon_WITH_AES_128_GCM_SHA256", "TLS_DH_anon_WITH_AES_256_CBC_SHA", "TLS_DH_anon_WITH_AES_256_CBC_SHA256", "TLS_DH_anon_WITH_AES_256_GCM_SHA384", "TLS_DH_anon_WITH_DES_CBC_SHA", "TLS_DH_anon_WITH_RC4_128_MD5", "TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", "TLS_ECDHE_ECDSA_WITH_NULL_SHA", "TLS_ECDHE_ECDSA_WITH_RC4_128_SHA", "TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA", "TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA", "TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256", "TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", "TLS_ECDHE_RSA_WITH_NULL_SHA", "TLS_ECDHE_RSA_WITH_RC4_128_SHA", "TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA", "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA", "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256", "TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256", "TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA", "TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384", "TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384", "TLS_ECDH_ECDSA_WITH_NULL_SHA", "TLS_ECDH_ECDSA_WITH_RC4_128_SHA", "TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA", "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA", "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256", "TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256", "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA", "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384", "TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384", "TLS_ECDH_RSA_WITH_NULL_SHA", "TLS_ECDH_RSA_WITH_RC4_128_SHA", "TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA", "TLS_ECDH_anon_WITH_AES_128_CBC_SHA", "TLS_ECDH_anon_WITH_AES_256_CBC_SHA", "TLS_ECDH_anon_WITH_NULL_SHA", "TLS_ECDH_anon_WITH_RC4_128_SHA", "TLS_EMPTY_RENEGOTIATION_INFO_SCSV", "TLS_FALLBACK_SCSV", "TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5", "TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA", "TLS_KRB5_EXPORT_WITH_RC4_40_MD5", "TLS_KRB5_EXPORT_WITH_RC4_40_SHA", "TLS_KRB5_WITH_3DES_EDE_CBC_MD5", "TLS_KRB5_WITH_3DES_EDE_CBC_SHA", "TLS_KRB5_WITH_DES_CBC_MD5", "TLS_KRB5_WITH_DES_CBC_SHA", "TLS_KRB5_WITH_RC4_128_MD5", "TLS_KRB5_WITH_RC4_128_SHA", "TLS_PSK_WITH_3DES_EDE_CBC_SHA", "TLS_PSK_WITH_AES_128_CBC_SHA", "TLS_PSK_WITH_AES_256_CBC_SHA", "TLS_PSK_WITH_RC4_128_SHA", "TLS_RSA_EXPORT_WITH_DES40_CBC_SHA", "TLS_RSA_EXPORT_WITH_RC4_40_MD5", "TLS_RSA_WITH_3DES_EDE_CBC_SHA", "TLS_RSA_WITH_AES_128_CBC_SHA", "TLS_RSA_WITH_AES_128_CBC_SHA256", "TLS_RSA_WITH_AES_128_GCM_SHA256", "TLS_RSA_WITH_AES_256_CBC_SHA", "TLS_RSA_WITH_AES_256_CBC_SHA256", "TLS_RSA_WITH_AES_256_GCM_SHA384", "TLS_RSA_WITH_CAMELLIA_128_CBC_SHA", "TLS_RSA_WITH_CAMELLIA_256_CBC_SHA", "TLS_RSA_WITH_DES_CBC_SHA", "TLS_RSA_WITH_NULL_MD5", "TLS_RSA_WITH_NULL_SHA", "TLS_RSA_WITH_NULL_SHA256", "TLS_RSA_WITH_RC4_128_MD5", "TLS_RSA_WITH_RC4_128_SHA", "TLS_RSA_WITH_SEED_CBC_SHA", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @NotNull
    public final Comparator<String> getORDER_BY_NAME$okhttp() {
      return CipherSuite.ORDER_BY_NAME;
    }
    
    @JvmStatic
    @NotNull
    public final synchronized CipherSuite forJavaName(@NotNull String javaName) {
      Intrinsics.checkNotNullParameter(javaName, "javaName");
      CipherSuite result = (CipherSuite)CipherSuite.INSTANCES.get(javaName);
      if (result == null) {
        result = (CipherSuite)CipherSuite.INSTANCES.get(secondaryName(javaName));
        if (result == null)
          result = new CipherSuite(javaName, null); 
        CipherSuite.INSTANCES.put(javaName, result);
      } 
      return result;
    }
    
    private final String secondaryName(String javaName) {
      Intrinsics.checkNotNullExpressionValue(javaName.substring(4), "this as java.lang.String).substring(startIndex)");
      Intrinsics.checkNotNullExpressionValue(javaName.substring(4), "this as java.lang.String).substring(startIndex)");
      return StringsKt.startsWith$default(javaName, "TLS_", false, 2, null) ? ("SSL_" + javaName.substring(4)) : (StringsKt.startsWith$default(javaName, "SSL_", false, 2, null) ? ("TLS_" + javaName.substring(4)) : 
        javaName);
    }
    
    private final CipherSuite init(String javaName, int value) {
      CipherSuite suite = new CipherSuite(javaName, null);
      CipherSuite.INSTANCES.put(javaName, suite);
      return suite;
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final String javaName;
  
  @NotNull
  private static final Comparator<String> ORDER_BY_NAME = new CipherSuite$Companion$ORDER_BY_NAME$1();
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\035\n\000\n\002\030\002\n\002\020\016\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\003*\001\000\b\n\030\0002\022\022\004\022\0020\0020\001j\b\022\004\022\0020\002`\003J\037\020\007\032\0020\0062\006\020\004\032\0020\0022\006\020\005\032\0020\002H\026¢\006\004\b\007\020\b¨\006\t"}, d2 = {"okhttp3/CipherSuite.Companion.ORDER_BY_NAME.1", "Ljava/util/Comparator;", "", "Lkotlin/Comparator;", "a", "b", "", "compare", "(Ljava/lang/String;Ljava/lang/String;)I", "okhttp"})
  public static final class CipherSuite$Companion$ORDER_BY_NAME$1 implements Comparator<String> {
    public int compare(@NotNull String a, @NotNull String b) {
      Intrinsics.checkNotNullParameter(a, "a");
      Intrinsics.checkNotNullParameter(b, "b");
      int i = 4;
      int limit = Math.min(a.length(), b.length());
      while (i < limit) {
        char charA = a.charAt(i);
        char charB = b.charAt(i);
        if (charA != charB)
          return (Intrinsics.compare(charA, charB) < 0) ? -1 : 1; 
        i++;
      } 
      int lengthA = a.length();
      int lengthB = b.length();
      if (lengthA != lengthB)
        return (lengthA < lengthB) ? -1 : 1; 
      return 0;
    }
  }
  
  @NotNull
  private static final Map<String, CipherSuite> INSTANCES = new LinkedHashMap<>();
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_RSA_WITH_NULL_MD5 = Companion.init("SSL_RSA_WITH_NULL_MD5", 1);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_RSA_WITH_NULL_SHA = Companion.init("SSL_RSA_WITH_NULL_SHA", 2);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_RSA_EXPORT_WITH_RC4_40_MD5 = Companion.init("SSL_RSA_EXPORT_WITH_RC4_40_MD5", 3);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_RSA_WITH_RC4_128_MD5 = Companion.init("SSL_RSA_WITH_RC4_128_MD5", 4);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_RSA_WITH_RC4_128_SHA = Companion.init("SSL_RSA_WITH_RC4_128_SHA", 5);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_RSA_EXPORT_WITH_DES40_CBC_SHA = Companion.init("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA", 8);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_RSA_WITH_DES_CBC_SHA = Companion.init("SSL_RSA_WITH_DES_CBC_SHA", 9);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_RSA_WITH_3DES_EDE_CBC_SHA = Companion.init("SSL_RSA_WITH_3DES_EDE_CBC_SHA", 10);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA = Companion.init("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA", 17);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_DSS_WITH_DES_CBC_SHA = Companion.init("SSL_DHE_DSS_WITH_DES_CBC_SHA", 18);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA = Companion.init("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA", 19);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA = Companion.init("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA", 20);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_RSA_WITH_DES_CBC_SHA = Companion.init("SSL_DHE_RSA_WITH_DES_CBC_SHA", 21);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA = Companion.init("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA", 22);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DH_anon_EXPORT_WITH_RC4_40_MD5 = Companion.init("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5", 23);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DH_anon_WITH_RC4_128_MD5 = Companion.init("SSL_DH_anon_WITH_RC4_128_MD5", 24);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA = Companion.init("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA", 25);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DH_anon_WITH_DES_CBC_SHA = Companion.init("SSL_DH_anon_WITH_DES_CBC_SHA", 26);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DH_anon_WITH_3DES_EDE_CBC_SHA = Companion.init("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA", 27);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_KRB5_WITH_DES_CBC_SHA = Companion.init("TLS_KRB5_WITH_DES_CBC_SHA", 30);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_KRB5_WITH_3DES_EDE_CBC_SHA = Companion.init("TLS_KRB5_WITH_3DES_EDE_CBC_SHA", 31);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_KRB5_WITH_RC4_128_SHA = Companion.init("TLS_KRB5_WITH_RC4_128_SHA", 32);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_KRB5_WITH_DES_CBC_MD5 = Companion.init("TLS_KRB5_WITH_DES_CBC_MD5", 34);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_KRB5_WITH_3DES_EDE_CBC_MD5 = Companion.init("TLS_KRB5_WITH_3DES_EDE_CBC_MD5", 35);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_KRB5_WITH_RC4_128_MD5 = Companion.init("TLS_KRB5_WITH_RC4_128_MD5", 36);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA = Companion.init("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA", 38);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_KRB5_EXPORT_WITH_RC4_40_SHA = Companion.init("TLS_KRB5_EXPORT_WITH_RC4_40_SHA", 40);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5 = Companion.init("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5", 41);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_KRB5_EXPORT_WITH_RC4_40_MD5 = Companion.init("TLS_KRB5_EXPORT_WITH_RC4_40_MD5", 43);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_RSA_WITH_AES_128_CBC_SHA = Companion.init("TLS_RSA_WITH_AES_128_CBC_SHA", 47);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_DSS_WITH_AES_128_CBC_SHA = Companion.init("TLS_DHE_DSS_WITH_AES_128_CBC_SHA", 50);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_RSA_WITH_AES_128_CBC_SHA = Companion.init("TLS_DHE_RSA_WITH_AES_128_CBC_SHA", 51);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DH_anon_WITH_AES_128_CBC_SHA = Companion.init("TLS_DH_anon_WITH_AES_128_CBC_SHA", 52);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_RSA_WITH_AES_256_CBC_SHA = Companion.init("TLS_RSA_WITH_AES_256_CBC_SHA", 53);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_DSS_WITH_AES_256_CBC_SHA = Companion.init("TLS_DHE_DSS_WITH_AES_256_CBC_SHA", 56);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_RSA_WITH_AES_256_CBC_SHA = Companion.init("TLS_DHE_RSA_WITH_AES_256_CBC_SHA", 57);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DH_anon_WITH_AES_256_CBC_SHA = Companion.init("TLS_DH_anon_WITH_AES_256_CBC_SHA", 58);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_RSA_WITH_NULL_SHA256 = Companion.init("TLS_RSA_WITH_NULL_SHA256", 59);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_RSA_WITH_AES_128_CBC_SHA256 = Companion.init("TLS_RSA_WITH_AES_128_CBC_SHA256", 60);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_RSA_WITH_AES_256_CBC_SHA256 = Companion.init("TLS_RSA_WITH_AES_256_CBC_SHA256", 61);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_DSS_WITH_AES_128_CBC_SHA256 = Companion.init("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", 64);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_RSA_WITH_CAMELLIA_128_CBC_SHA = Companion.init("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA", 65);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA = Companion.init("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA", 68);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA = Companion.init("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA", 69);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_RSA_WITH_AES_128_CBC_SHA256 = Companion.init("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", 103);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_DSS_WITH_AES_256_CBC_SHA256 = Companion.init("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", 106);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_RSA_WITH_AES_256_CBC_SHA256 = Companion.init("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", 107);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DH_anon_WITH_AES_128_CBC_SHA256 = Companion.init("TLS_DH_anon_WITH_AES_128_CBC_SHA256", 108);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DH_anon_WITH_AES_256_CBC_SHA256 = Companion.init("TLS_DH_anon_WITH_AES_256_CBC_SHA256", 109);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_RSA_WITH_CAMELLIA_256_CBC_SHA = Companion.init("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA", 132);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA = Companion.init("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA", 135);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA = Companion.init("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA", 136);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_PSK_WITH_RC4_128_SHA = Companion.init("TLS_PSK_WITH_RC4_128_SHA", 138);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_PSK_WITH_3DES_EDE_CBC_SHA = Companion.init("TLS_PSK_WITH_3DES_EDE_CBC_SHA", 139);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_PSK_WITH_AES_128_CBC_SHA = Companion.init("TLS_PSK_WITH_AES_128_CBC_SHA", 140);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_PSK_WITH_AES_256_CBC_SHA = Companion.init("TLS_PSK_WITH_AES_256_CBC_SHA", 141);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_RSA_WITH_SEED_CBC_SHA = Companion.init("TLS_RSA_WITH_SEED_CBC_SHA", 150);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_RSA_WITH_AES_128_GCM_SHA256 = Companion.init("TLS_RSA_WITH_AES_128_GCM_SHA256", 156);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_RSA_WITH_AES_256_GCM_SHA384 = Companion.init("TLS_RSA_WITH_AES_256_GCM_SHA384", 157);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_RSA_WITH_AES_128_GCM_SHA256 = Companion.init("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", 158);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_RSA_WITH_AES_256_GCM_SHA384 = Companion.init("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", 159);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_DSS_WITH_AES_128_GCM_SHA256 = Companion.init("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", 162);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_DSS_WITH_AES_256_GCM_SHA384 = Companion.init("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", 163);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DH_anon_WITH_AES_128_GCM_SHA256 = Companion.init("TLS_DH_anon_WITH_AES_128_GCM_SHA256", 166);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DH_anon_WITH_AES_256_GCM_SHA384 = Companion.init("TLS_DH_anon_WITH_AES_256_GCM_SHA384", 167);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_EMPTY_RENEGOTIATION_INFO_SCSV = Companion.init("TLS_EMPTY_RENEGOTIATION_INFO_SCSV", 255);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_FALLBACK_SCSV = Companion.init("TLS_FALLBACK_SCSV", 22016);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_ECDSA_WITH_NULL_SHA = Companion.init("TLS_ECDH_ECDSA_WITH_NULL_SHA", 49153);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_ECDSA_WITH_RC4_128_SHA = Companion.init("TLS_ECDH_ECDSA_WITH_RC4_128_SHA", 49154);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA = Companion.init("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA", 49155);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA = Companion.init("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA", 49156);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA = Companion.init("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA", 49157);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_ECDSA_WITH_NULL_SHA = Companion.init("TLS_ECDHE_ECDSA_WITH_NULL_SHA", 49158);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_ECDSA_WITH_RC4_128_SHA = Companion.init("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA", 49159);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA = Companion.init("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", 49160);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA = Companion.init("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", 49161);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA = Companion.init("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", 49162);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_RSA_WITH_NULL_SHA = Companion.init("TLS_ECDH_RSA_WITH_NULL_SHA", 49163);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_RSA_WITH_RC4_128_SHA = Companion.init("TLS_ECDH_RSA_WITH_RC4_128_SHA", 49164);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA = Companion.init("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA", 49165);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_RSA_WITH_AES_128_CBC_SHA = Companion.init("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA", 49166);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_RSA_WITH_AES_256_CBC_SHA = Companion.init("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA", 49167);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_RSA_WITH_NULL_SHA = Companion.init("TLS_ECDHE_RSA_WITH_NULL_SHA", 49168);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_RSA_WITH_RC4_128_SHA = Companion.init("TLS_ECDHE_RSA_WITH_RC4_128_SHA", 49169);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA = Companion.init("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", 49170);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA = Companion.init("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", 49171);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA = Companion.init("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", 49172);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_anon_WITH_NULL_SHA = Companion.init("TLS_ECDH_anon_WITH_NULL_SHA", 49173);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_anon_WITH_RC4_128_SHA = Companion.init("TLS_ECDH_anon_WITH_RC4_128_SHA", 49174);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA = Companion.init("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA", 49175);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_anon_WITH_AES_128_CBC_SHA = Companion.init("TLS_ECDH_anon_WITH_AES_128_CBC_SHA", 49176);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_anon_WITH_AES_256_CBC_SHA = Companion.init("TLS_ECDH_anon_WITH_AES_256_CBC_SHA", 49177);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 = Companion.init("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", 49187);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 = Companion.init("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", 49188);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 = Companion.init("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256", 49189);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 = Companion.init("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384", 49190);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 = Companion.init("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", 49191);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 = Companion.init("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", 49192);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 = Companion.init("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256", 49193);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 = Companion.init("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384", 49194);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 = Companion.init("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", 49195);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 = Companion.init("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", 49196);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 = Companion.init("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256", 49197);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 = Companion.init("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384", 49198);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 = Companion.init("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", 49199);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 = Companion.init("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", 49200);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 = Companion.init("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256", 49201);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 = Companion.init("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384", 49202);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA = Companion.init("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA", 49205);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA = Companion.init("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA", 49206);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 = Companion.init("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 52392);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 = Companion.init("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", 52393);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256 = Companion.init("TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 52394);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256 = Companion.init("TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256", 52396);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_AES_128_GCM_SHA256 = Companion.init("TLS_AES_128_GCM_SHA256", 4865);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_AES_256_GCM_SHA384 = Companion.init("TLS_AES_256_GCM_SHA384", 4866);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_CHACHA20_POLY1305_SHA256 = Companion.init("TLS_CHACHA20_POLY1305_SHA256", 4867);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_AES_128_CCM_SHA256 = Companion.init("TLS_AES_128_CCM_SHA256", 4868);
  
  @JvmField
  @NotNull
  public static final CipherSuite TLS_AES_128_CCM_8_SHA256 = Companion.init("TLS_AES_128_CCM_8_SHA256", 4869);
  
  @JvmStatic
  @NotNull
  public static final synchronized CipherSuite forJavaName(@NotNull String javaName) {
    return Companion.forJavaName(javaName);
  }
}
