package okhttp3.internal.http;

import kotlin.Metadata;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000$\n\002\030\002\n\002\030\002\n\002\020\016\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\007\030\0002\0020\001B!\022\b\020\003\032\004\030\0010\002\022\006\020\005\032\0020\004\022\006\020\007\032\0020\006¢\006\004\b\b\020\tJ\017\020\005\032\0020\004H\026¢\006\004\b\005\020\nJ\021\020\f\032\004\030\0010\013H\026¢\006\004\b\f\020\rJ\017\020\007\032\0020\006H\026¢\006\004\b\007\020\016R\024\020\005\032\0020\0048\002X\004¢\006\006\n\004\b\005\020\017R\026\020\003\032\004\030\0010\0028\002X\004¢\006\006\n\004\b\003\020\020R\024\020\007\032\0020\0068\002X\004¢\006\006\n\004\b\007\020\021¨\006\022"}, d2 = {"Lokhttp3/internal/http/RealResponseBody;", "Lokhttp3/ResponseBody;", "", "contentTypeString", "", "contentLength", "Lokio/BufferedSource;", "source", "<init>", "(Ljava/lang/String;JLokio/BufferedSource;)V", "()J", "Lokhttp3/MediaType;", "contentType", "()Lokhttp3/MediaType;", "()Lokio/BufferedSource;", "J", "Ljava/lang/String;", "Lokio/BufferedSource;", "okhttp"})
public final class RealResponseBody extends ResponseBody {
  @Nullable
  private final String contentTypeString;
  
  private final long contentLength;
  
  @NotNull
  private final BufferedSource source;
  
  public RealResponseBody(@Nullable String contentTypeString, long contentLength, @NotNull BufferedSource source) {
    this.contentTypeString = contentTypeString;
    this.contentLength = contentLength;
    this.source = source;
  }
  
  public long contentLength() {
    return this.contentLength;
  }
  
  @Nullable
  public MediaType contentType() {
    return (this.contentTypeString != null) ? MediaType.Companion.parse(this.contentTypeString) : null;
  }
  
  @NotNull
  public BufferedSource source() {
    return this.source;
  }
}
