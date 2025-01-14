package okhttp3;

import java.io.IOException;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000$\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\004\bf\030\0002\0020\001J\037\020\007\032\0020\0062\006\020\003\032\0020\0022\006\020\005\032\0020\004H&¢\006\004\b\007\020\bJ\037\020\013\032\0020\0062\006\020\003\032\0020\0022\006\020\n\032\0020\tH&¢\006\004\b\013\020\f¨\006\r"}, d2 = {"Lokhttp3/Callback;", "", "Lokhttp3/Call;", "call", "Ljava/io/IOException;", "e", "", "onFailure", "(Lokhttp3/Call;Ljava/io/IOException;)V", "Lokhttp3/Response;", "response", "onResponse", "(Lokhttp3/Call;Lokhttp3/Response;)V", "okhttp"})
public interface Callback {
  void onFailure(@NotNull Call paramCall, @NotNull IOException paramIOException);
  
  void onResponse(@NotNull Call paramCall, @NotNull Response paramResponse) throws IOException;
}
