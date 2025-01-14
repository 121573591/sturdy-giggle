package okhttp3;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000<\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\b\n\000\n\002\020\016\n\000\n\002\020\002\n\002\b\003\n\002\020\003\n\000\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\005\b&\030\0002\0020\001B\007¢\006\004\b\002\020\003J'\020\013\032\0020\n2\006\020\005\032\0020\0042\006\020\007\032\0020\0062\006\020\t\032\0020\bH\026¢\006\004\b\013\020\fJ'\020\r\032\0020\n2\006\020\005\032\0020\0042\006\020\007\032\0020\0062\006\020\t\032\0020\bH\026¢\006\004\b\r\020\fJ)\020\022\032\0020\n2\006\020\005\032\0020\0042\006\020\017\032\0020\0162\b\020\021\032\004\030\0010\020H\026¢\006\004\b\022\020\023J\037\020\025\032\0020\n2\006\020\005\032\0020\0042\006\020\024\032\0020\bH\026¢\006\004\b\025\020\026J\037\020\025\032\0020\n2\006\020\005\032\0020\0042\006\020\030\032\0020\027H\026¢\006\004\b\025\020\031J\037\020\032\032\0020\n2\006\020\005\032\0020\0042\006\020\021\032\0020\020H\026¢\006\004\b\032\020\033¨\006\034"}, d2 = {"Lokhttp3/WebSocketListener;", "", "<init>", "()V", "Lokhttp3/WebSocket;", "webSocket", "", "code", "", "reason", "", "onClosed", "(Lokhttp3/WebSocket;ILjava/lang/String;)V", "onClosing", "", "t", "Lokhttp3/Response;", "response", "onFailure", "(Lokhttp3/WebSocket;Ljava/lang/Throwable;Lokhttp3/Response;)V", "text", "onMessage", "(Lokhttp3/WebSocket;Ljava/lang/String;)V", "Lokio/ByteString;", "bytes", "(Lokhttp3/WebSocket;Lokio/ByteString;)V", "onOpen", "(Lokhttp3/WebSocket;Lokhttp3/Response;)V", "okhttp"})
public abstract class WebSocketListener {
  public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
    Intrinsics.checkNotNullParameter(webSocket, "webSocket");
    Intrinsics.checkNotNullParameter(response, "response");
  }
  
  public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
    Intrinsics.checkNotNullParameter(webSocket, "webSocket");
    Intrinsics.checkNotNullParameter(text, "text");
  }
  
  public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
    Intrinsics.checkNotNullParameter(webSocket, "webSocket");
    Intrinsics.checkNotNullParameter(bytes, "bytes");
  }
  
  public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
    Intrinsics.checkNotNullParameter(webSocket, "webSocket");
    Intrinsics.checkNotNullParameter(reason, "reason");
  }
  
  public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
    Intrinsics.checkNotNullParameter(webSocket, "webSocket");
    Intrinsics.checkNotNullParameter(reason, "reason");
  }
  
  public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
    Intrinsics.checkNotNullParameter(webSocket, "webSocket");
    Intrinsics.checkNotNullParameter(t, "t");
  }
}
