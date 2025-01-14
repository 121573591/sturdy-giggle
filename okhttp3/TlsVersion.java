package okhttp3;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\020\n\002\030\002\n\002\020\020\n\002\020\016\n\002\b\r\b\001\030\000 \t2\b\022\004\022\0020\0000\001:\001\tB\021\b\002\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\017\020\003\032\0020\002H\007¢\006\004\b\006\020\007R\027\020\003\032\0020\0028\007¢\006\f\n\004\b\003\020\b\032\004\b\003\020\007j\002\b\nj\002\b\013j\002\b\fj\002\b\rj\002\b\016¨\006\017"}, d2 = {"Lokhttp3/TlsVersion;", "", "", "javaName", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "-deprecated_javaName", "()Ljava/lang/String;", "Ljava/lang/String;", "Companion", "TLS_1_3", "TLS_1_2", "TLS_1_1", "TLS_1_0", "SSL_3_0", "okhttp"})
public enum TlsVersion {
  TLS_1_3("TLSv1.3"),
  TLS_1_2("TLSv1.2"),
  TLS_1_1("TLSv1.1"),
  TLS_1_0("TLSv1"),
  SSL_3_0("SSLv3");
  
  @NotNull
  public static final Companion Companion;
  
  @NotNull
  private final String javaName;
  
  TlsVersion(String javaName) {
    this.javaName = javaName;
  }
  
  @JvmName(name = "javaName")
  @NotNull
  public final String javaName() {
    return this.javaName;
  }
  
  static {
    Companion = new Companion(null);
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "javaName", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_javaName")
  @NotNull
  public final String -deprecated_javaName() {
    return this.javaName;
  }
  
  @JvmStatic
  @NotNull
  public static final TlsVersion forJavaName(@NotNull String javaName) {
    return Companion.forJavaName(javaName);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\027\020\007\032\0020\0062\006\020\005\032\0020\004H\007¢\006\004\b\007\020\b¨\006\t"}, d2 = {"Lokhttp3/TlsVersion$Companion;", "", "<init>", "()V", "", "javaName", "Lokhttp3/TlsVersion;", "forJavaName", "(Ljava/lang/String;)Lokhttp3/TlsVersion;", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @JvmStatic
    @NotNull
    public final TlsVersion forJavaName(@NotNull String javaName) {
      Intrinsics.checkNotNullParameter(javaName, "javaName");
      String str = javaName;
      switch (str.hashCode()) {
        case 79201641:
          if (!str.equals("SSLv3"))
            break; 
        case 79923350:
          if (!str.equals("TLSv1"))
            break; 
        case -503070501:
          if (!str.equals("TLSv1.3"))
            break; 
        case -503070502:
          if (!str.equals("TLSv1.2"))
            break; 
        case -503070503:
          if (!str.equals("TLSv1.1"))
            break; 
      } 
      throw new IllegalArgumentException("Unexpected TLS version: " + javaName);
    }
  }
}
