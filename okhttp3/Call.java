package okhttp3;

import java.io.IOException;
import kotlin.Metadata;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0008\n\002\030\002\n\002\020\032\n\002\020\002\n\002\b\004\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\bf\030\0002\0020\001:\001\030J\017\020\003\032\0020\002H&¢\006\004\b\003\020\004J\017\020\005\032\0020\000H&¢\006\004\b\005\020\006J\027\020\t\032\0020\0022\006\020\b\032\0020\007H&¢\006\004\b\t\020\nJ\017\020\f\032\0020\013H&¢\006\004\b\f\020\rJ\017\020\017\032\0020\016H&¢\006\004\b\017\020\020J\017\020\021\032\0020\016H&¢\006\004\b\021\020\020J\017\020\023\032\0020\022H&¢\006\004\b\023\020\024J\017\020\026\032\0020\025H&¢\006\004\b\026\020\027¨\006\031"}, d2 = {"Lokhttp3/Call;", "", "", "cancel", "()V", "clone", "()Lokhttp3/Call;", "Lokhttp3/Callback;", "responseCallback", "enqueue", "(Lokhttp3/Callback;)V", "Lokhttp3/Response;", "execute", "()Lokhttp3/Response;", "", "isCanceled", "()Z", "isExecuted", "Lokhttp3/Request;", "request", "()Lokhttp3/Request;", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "Factory", "okhttp"})
public interface Call extends Cloneable {
  @NotNull
  Request request();
  
  @NotNull
  Response execute() throws IOException;
  
  void enqueue(@NotNull Callback paramCallback);
  
  void cancel();
  
  boolean isExecuted();
  
  boolean isCanceled();
  
  @NotNull
  Timeout timeout();
  
  @NotNull
  Call clone();
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\026\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\bæ\001\030\0002\0020\001J\027\020\005\032\0020\0042\006\020\003\032\0020\002H&¢\006\004\b\005\020\006¨\006\007"}, d2 = {"Lokhttp3/Call$Factory;", "", "Lokhttp3/Request;", "request", "Lokhttp3/Call;", "newCall", "(Lokhttp3/Request;)Lokhttp3/Call;", "okhttp"})
  public static interface Factory {
    @NotNull
    Call newCall(@NotNull Request param1Request);
  }
}
