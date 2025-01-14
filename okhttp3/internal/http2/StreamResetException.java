package okhttp3.internal.http2;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\020\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\005\030\0002\0020\001B\017\022\006\020\003\032\0020\002¢\006\004\b\004\020\005R\024\020\003\032\0020\0028\006X\004¢\006\006\n\004\b\003\020\006¨\006\007"}, d2 = {"Lokhttp3/internal/http2/StreamResetException;", "Ljava/io/IOException;", "Lokhttp3/internal/http2/ErrorCode;", "errorCode", "<init>", "(Lokhttp3/internal/http2/ErrorCode;)V", "Lokhttp3/internal/http2/ErrorCode;", "okhttp"})
public final class StreamResetException extends IOException {
  @JvmField
  @NotNull
  public final ErrorCode errorCode;
  
  public StreamResetException(@NotNull ErrorCode errorCode) {
    super("stream was reset: " + errorCode);
    this.errorCode = errorCode;
  }
}
