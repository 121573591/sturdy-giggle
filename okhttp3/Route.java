package okhttp3;

import java.net.InetSocketAddress;
import java.net.Proxy;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0004\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\006\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\b\n\002\020\016\n\002\b\006\030\0002\0020\001B\037\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\006\020\007\032\0020\006¢\006\004\b\b\020\tJ\017\020\003\032\0020\002H\007¢\006\004\b\n\020\013J\032\020\016\032\0020\r2\b\020\f\032\004\030\0010\001H\002¢\006\004\b\016\020\017J\017\020\021\032\0020\020H\026¢\006\004\b\021\020\022J\017\020\005\032\0020\004H\007¢\006\004\b\023\020\024J\r\020\025\032\0020\r¢\006\004\b\025\020\026J\017\020\007\032\0020\006H\007¢\006\004\b\027\020\030J\017\020\032\032\0020\031H\026¢\006\004\b\032\020\033R\027\020\003\032\0020\0028\007¢\006\f\n\004\b\003\020\034\032\004\b\003\020\013R\027\020\005\032\0020\0048\007¢\006\f\n\004\b\005\020\035\032\004\b\005\020\024R\027\020\007\032\0020\0068\007¢\006\f\n\004\b\007\020\036\032\004\b\007\020\030¨\006\037"}, d2 = {"Lokhttp3/Route;", "", "Lokhttp3/Address;", "address", "Ljava/net/Proxy;", "proxy", "Ljava/net/InetSocketAddress;", "socketAddress", "<init>", "(Lokhttp3/Address;Ljava/net/Proxy;Ljava/net/InetSocketAddress;)V", "-deprecated_address", "()Lokhttp3/Address;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "-deprecated_proxy", "()Ljava/net/Proxy;", "requiresTunnel", "()Z", "-deprecated_socketAddress", "()Ljava/net/InetSocketAddress;", "", "toString", "()Ljava/lang/String;", "Lokhttp3/Address;", "Ljava/net/Proxy;", "Ljava/net/InetSocketAddress;", "okhttp"})
public final class Route {
  @NotNull
  private final Address address;
  
  @NotNull
  private final Proxy proxy;
  
  @NotNull
  private final InetSocketAddress socketAddress;
  
  public Route(@NotNull Address address, @NotNull Proxy proxy, @NotNull InetSocketAddress socketAddress) {
    this.address = address;
    this.proxy = proxy;
    this.socketAddress = socketAddress;
  }
  
  @JvmName(name = "address")
  @NotNull
  public final Address address() {
    return this.address;
  }
  
  @JvmName(name = "proxy")
  @NotNull
  public final Proxy proxy() {
    return this.proxy;
  }
  
  @JvmName(name = "socketAddress")
  @NotNull
  public final InetSocketAddress socketAddress() {
    return this.socketAddress;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "address", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_address")
  @NotNull
  public final Address -deprecated_address() {
    return this.address;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "proxy", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_proxy")
  @NotNull
  public final Proxy -deprecated_proxy() {
    return this.proxy;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "socketAddress", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_socketAddress")
  @NotNull
  public final InetSocketAddress -deprecated_socketAddress() {
    return this.socketAddress;
  }
  
  public final boolean requiresTunnel() {
    return (this.address.sslSocketFactory() != null && this.proxy.type() == Proxy.Type.HTTP);
  }
  
  public boolean equals(@Nullable Object other) {
    return (other instanceof Route && 
      Intrinsics.areEqual(((Route)other).address, this.address) && 
      Intrinsics.areEqual(((Route)other).proxy, this.proxy) && 
      Intrinsics.areEqual(((Route)other).socketAddress, this.socketAddress));
  }
  
  public int hashCode() {
    int result = 17;
    result = 31 * result + this.address.hashCode();
    result = 31 * result + this.proxy.hashCode();
    result = 31 * result + this.socketAddress.hashCode();
    return result;
  }
  
  @NotNull
  public String toString() {
    return "Route{" + this.socketAddress + '}';
  }
}
