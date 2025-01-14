package okhttp3.internal.proxy;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\002\n\002\b\002\n\002\020 \n\002\030\002\n\002\b\003\bÆ\002\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J-\020\013\032\0020\n2\b\020\005\032\004\030\0010\0042\b\020\007\032\004\030\0010\0062\b\020\t\032\004\030\0010\bH\026¢\006\004\b\013\020\fJ\037\020\017\032\b\022\004\022\0020\0160\r2\b\020\005\032\004\030\0010\004H\026¢\006\004\b\017\020\020¨\006\021"}, d2 = {"Lokhttp3/internal/proxy/NullProxySelector;", "Ljava/net/ProxySelector;", "<init>", "()V", "Ljava/net/URI;", "uri", "Ljava/net/SocketAddress;", "sa", "Ljava/io/IOException;", "ioe", "", "connectFailed", "(Ljava/net/URI;Ljava/net/SocketAddress;Ljava/io/IOException;)V", "", "Ljava/net/Proxy;", "select", "(Ljava/net/URI;)Ljava/util/List;", "okhttp"})
@SourceDebugExtension({"SMAP\nNullProxySelector.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NullProxySelector.kt\nokhttp3/internal/proxy/NullProxySelector\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,36:1\n1#2:37\n*E\n"})
public final class NullProxySelector extends ProxySelector {
  @NotNull
  public static final NullProxySelector INSTANCE = new NullProxySelector();
  
  @NotNull
  public List<Proxy> select(@Nullable URI uri) {
    if (uri == null) {
      int $i$a$-requireNotNull-NullProxySelector$select$1 = 0;
      String str = "uri must not be null";
      throw new IllegalArgumentException(str.toString());
    } 
    return CollectionsKt.listOf(Proxy.NO_PROXY);
  }
  
  public void connectFailed(@Nullable URI uri, @Nullable SocketAddress sa, @Nullable IOException ioe) {}
}
