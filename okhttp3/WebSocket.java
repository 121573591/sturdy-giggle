package okhttp3;

import kotlin.Metadata;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000<\n\002\030\002\n\002\020\000\n\002\020\002\n\002\b\002\n\002\020\b\n\000\n\002\020\016\n\000\n\002\020\013\n\002\b\002\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\004\bf\030\0002\0020\001:\001\030J\017\020\003\032\0020\002H&¢\006\004\b\003\020\004J!\020\n\032\0020\t2\006\020\006\032\0020\0052\b\020\b\032\004\030\0010\007H&¢\006\004\b\n\020\013J\017\020\r\032\0020\fH&¢\006\004\b\r\020\016J\017\020\020\032\0020\017H&¢\006\004\b\020\020\021J\027\020\023\032\0020\t2\006\020\022\032\0020\007H&¢\006\004\b\023\020\024J\027\020\023\032\0020\t2\006\020\026\032\0020\025H&¢\006\004\b\023\020\027¨\006\031"}, d2 = {"Lokhttp3/WebSocket;", "", "", "cancel", "()V", "", "code", "", "reason", "", "close", "(ILjava/lang/String;)Z", "", "queueSize", "()J", "Lokhttp3/Request;", "request", "()Lokhttp3/Request;", "text", "send", "(Ljava/lang/String;)Z", "Lokio/ByteString;", "bytes", "(Lokio/ByteString;)Z", "Factory", "okhttp"})
public interface WebSocket {
  @NotNull
  Request request();
  
  long queueSize();
  
  boolean send(@NotNull String paramString);
  
  boolean send(@NotNull ByteString paramByteString);
  
  boolean close(int paramInt, @Nullable String paramString);
  
  void cancel();
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\bæ\001\030\0002\0020\001J\037\020\007\032\0020\0062\006\020\003\032\0020\0022\006\020\005\032\0020\004H&¢\006\004\b\007\020\b¨\006\t"}, d2 = {"Lokhttp3/WebSocket$Factory;", "", "Lokhttp3/Request;", "request", "Lokhttp3/WebSocketListener;", "listener", "Lokhttp3/WebSocket;", "newWebSocket", "(Lokhttp3/Request;Lokhttp3/WebSocketListener;)Lokhttp3/WebSocket;", "okhttp"})
  public static interface Factory {
    @NotNull
    WebSocket newWebSocket(@NotNull Request param1Request, @NotNull WebSocketListener param1WebSocketListener);
  }
}
