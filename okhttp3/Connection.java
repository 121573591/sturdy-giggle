package okhttp3;

import java.net.Socket;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000(\n\002\030\002\n\002\020\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\bf\030\0002\0020\001J\021\020\003\032\004\030\0010\002H&¢\006\004\b\003\020\004J\017\020\006\032\0020\005H&¢\006\004\b\006\020\007J\017\020\t\032\0020\bH&¢\006\004\b\t\020\nJ\017\020\f\032\0020\013H&¢\006\004\b\f\020\r¨\006\016"}, d2 = {"Lokhttp3/Connection;", "", "Lokhttp3/Handshake;", "handshake", "()Lokhttp3/Handshake;", "Lokhttp3/Protocol;", "protocol", "()Lokhttp3/Protocol;", "Lokhttp3/Route;", "route", "()Lokhttp3/Route;", "Ljava/net/Socket;", "socket", "()Ljava/net/Socket;", "okhttp"})
public interface Connection {
  @NotNull
  Route route();
  
  @NotNull
  Socket socket();
  
  @Nullable
  Handshake handshake();
  
  @NotNull
  Protocol protocol();
}
