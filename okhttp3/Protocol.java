package okhttp3;

import java.io.IOException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\020\n\002\030\002\n\002\020\020\n\002\020\016\n\002\b\016\b\001\030\000 \t2\b\022\004\022\0020\0000\001:\001\tB\021\b\002\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\017\020\006\032\0020\002H\026¢\006\004\b\006\020\007R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\bj\002\b\nj\002\b\013j\002\b\fj\002\b\rj\002\b\016j\002\b\017¨\006\020"}, d2 = {"Lokhttp3/Protocol;", "", "", "protocol", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "toString", "()Ljava/lang/String;", "Ljava/lang/String;", "Companion", "HTTP_1_0", "HTTP_1_1", "SPDY_3", "HTTP_2", "H2_PRIOR_KNOWLEDGE", "QUIC", "okhttp"})
public enum Protocol {
  HTTP_1_0("http/1.0"),
  HTTP_1_1("http/1.1"),
  SPDY_3(
    "spdy/3.1"),
  HTTP_2("h2"),
  H2_PRIOR_KNOWLEDGE("h2_prior_knowledge"),
  QUIC("quic");
  
  @NotNull
  public static final Companion Companion;
  
  @NotNull
  private final String protocol;
  
  Protocol(String protocol) {
    this.protocol = protocol;
  }
  
  static {
    Companion = new Companion(null);
  }
  
  @NotNull
  public String toString() {
    return this.protocol;
  }
  
  @JvmStatic
  @NotNull
  public static final Protocol get(@NotNull String protocol) throws IOException {
    return Companion.get(protocol);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\027\020\007\032\0020\0062\006\020\005\032\0020\004H\007¢\006\004\b\007\020\b¨\006\t"}, d2 = {"Lokhttp3/Protocol$Companion;", "", "<init>", "()V", "", "protocol", "Lokhttp3/Protocol;", "get", "(Ljava/lang/String;)Lokhttp3/Protocol;", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @JvmStatic
    @NotNull
    public final Protocol get(@NotNull String protocol) throws IOException {
      Intrinsics.checkNotNullParameter(protocol, "protocol");
      String str = protocol;
      if (Intrinsics.areEqual(str, Protocol.QUIC.protocol)) {
      
      } else {
        throw new IOException("Unexpected protocol: " + protocol);
      } 
      return Intrinsics.areEqual(str, Protocol.HTTP_1_0.protocol) ? Protocol.HTTP_1_0 : (Intrinsics.areEqual(str, Protocol.HTTP_1_1.protocol) ? Protocol.HTTP_1_1 : (Intrinsics.areEqual(str, Protocol.H2_PRIOR_KNOWLEDGE.protocol) ? Protocol.H2_PRIOR_KNOWLEDGE : (Intrinsics.areEqual(str, Protocol.HTTP_2.protocol) ? Protocol.HTTP_2 : (Intrinsics.areEqual(str, Protocol.SPDY_3.protocol) ? Protocol.SPDY_3 : (Protocol)"JD-Core does not support Kotlin"))));
    }
  }
}
