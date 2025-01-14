package okhttp3.internal.cache;

import java.io.IOException;
import kotlin.Metadata;
import okio.Sink;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\030\n\002\030\002\n\002\020\000\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\003\bf\030\0002\0020\001J\017\020\003\032\0020\002H&¢\006\004\b\003\020\004J\017\020\006\032\0020\005H&¢\006\004\b\006\020\007¨\006\b"}, d2 = {"Lokhttp3/internal/cache/CacheRequest;", "", "", "abort", "()V", "Lokio/Sink;", "body", "()Lokio/Sink;", "okhttp"})
public interface CacheRequest {
  @NotNull
  Sink body() throws IOException;
  
  void abort();
}
