package okhttp3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\b\004\bf\030\000 \b2\0020\001:\001\bJ\035\020\006\032\b\022\004\022\0020\0050\0042\006\020\003\032\0020\002H&¢\006\004\b\006\020\007¨\006\t"}, d2 = {"Lokhttp3/Dns;", "", "", "hostname", "", "Ljava/net/InetAddress;", "lookup", "(Ljava/lang/String;)Ljava/util/List;", "Companion", "okhttp"})
public interface Dns {
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\004\b\003\030\0002\0020\001:\001\007B\t\b\002¢\006\004\b\002\020\003R\027\020\005\032\0020\0048\006X\004¢\006\006\n\004\b\005\020\006¨\006\001¨\006\b"}, d2 = {"Lokhttp3/Dns$Companion;", "", "<init>", "()V", "Lokhttp3/Dns;", "SYSTEM", "Lokhttp3/Dns;", "DnsSystem", "okhttp"})
  public static final class Companion {
    @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\036\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\b\003\b\002\030\0002\0020\001B\007¢\006\004\b\002\020\003J\035\020\b\032\b\022\004\022\0020\0070\0062\006\020\005\032\0020\004H\026¢\006\004\b\b\020\t¨\006\n"}, d2 = {"Lokhttp3/Dns$Companion$DnsSystem;", "Lokhttp3/Dns;", "<init>", "()V", "", "hostname", "", "Ljava/net/InetAddress;", "lookup", "(Ljava/lang/String;)Ljava/util/List;", "okhttp"})
    private static final class DnsSystem implements Dns {
      @NotNull
      public List<InetAddress> lookup(@NotNull String hostname) {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        try {
          Intrinsics.checkNotNullExpressionValue(InetAddress.getAllByName(hostname), "getAllByName(hostname)");
          return ArraysKt.toList((Object[])InetAddress.getAllByName(hostname));
        } catch (NullPointerException e) {
          UnknownHostException unknownHostException1 = new UnknownHostException("Broken system behaviour for dns lookup of " + hostname), $this$lookup_u24lambda_u240 = unknownHostException1;
          int $i$a$-apply-Dns$Companion$DnsSystem$lookup$1 = 0;
          $this$lookup_u24lambda_u240.initCause(e);
          throw (Throwable)unknownHostException1;
        } 
      }
    }
  }
  
  @NotNull
  public static final Companion Companion = Companion.$$INSTANCE;
  
  @JvmField
  @NotNull
  public static final Dns SYSTEM = new Companion.DnsSystem();
  
  @NotNull
  List<InetAddress> lookup(@NotNull String paramString) throws UnknownHostException;
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\036\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\b\003\b\002\030\0002\0020\001B\007¢\006\004\b\002\020\003J\035\020\b\032\b\022\004\022\0020\0070\0062\006\020\005\032\0020\004H\026¢\006\004\b\b\020\t¨\006\n"}, d2 = {"Lokhttp3/Dns$Companion$DnsSystem;", "Lokhttp3/Dns;", "<init>", "()V", "", "hostname", "", "Ljava/net/InetAddress;", "lookup", "(Ljava/lang/String;)Ljava/util/List;", "okhttp"})
  private static final class DnsSystem implements Dns {
    @NotNull
    public List<InetAddress> lookup(@NotNull String hostname) {
      Intrinsics.checkNotNullParameter(hostname, "hostname");
      try {
        Intrinsics.checkNotNullExpressionValue(InetAddress.getAllByName(hostname), "getAllByName(hostname)");
        return ArraysKt.toList((Object[])InetAddress.getAllByName(hostname));
      } catch (NullPointerException e) {
        UnknownHostException unknownHostException1 = new UnknownHostException("Broken system behaviour for dns lookup of " + hostname), $this$lookup_u24lambda_u240 = unknownHostException1;
        int $i$a$-apply-Dns$Companion$DnsSystem$lookup$1 = 0;
        $this$lookup_u24lambda_u240.initCause(e);
        throw (Throwable)unknownHostException1;
      } 
    }
  }
}
