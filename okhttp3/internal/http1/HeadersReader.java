package okhttp3.internal.http1;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.Headers;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000(\n\002\030\002\n\002\020\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\002\n\002\020\t\n\002\b\007\030\000 \0222\0020\001:\001\022B\017\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\r\020\007\032\0020\006¢\006\004\b\007\020\bJ\r\020\n\032\0020\t¢\006\004\b\n\020\013R\026\020\r\032\0020\f8\002@\002X\016¢\006\006\n\004\b\r\020\016R\027\020\003\032\0020\0028\006¢\006\f\n\004\b\003\020\017\032\004\b\020\020\021¨\006\023"}, d2 = {"Lokhttp3/internal/http1/HeadersReader;", "", "Lokio/BufferedSource;", "source", "<init>", "(Lokio/BufferedSource;)V", "Lokhttp3/Headers;", "readHeaders", "()Lokhttp3/Headers;", "", "readLine", "()Ljava/lang/String;", "", "headerLimit", "J", "Lokio/BufferedSource;", "getSource", "()Lokio/BufferedSource;", "Companion", "okhttp"})
public final class HeadersReader {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final BufferedSource source;
  
  private long headerLimit;
  
  private static final int HEADER_LIMIT = 262144;
  
  public HeadersReader(@NotNull BufferedSource source) {
    this.source = source;
    this.headerLimit = 262144L;
  }
  
  @NotNull
  public final BufferedSource getSource() {
    return this.source;
  }
  
  @NotNull
  public final String readLine() {
    String line = this.source.readUtf8LineStrict(this.headerLimit);
    this.headerLimit -= line.length();
    return line;
  }
  
  @NotNull
  public final Headers readHeaders() {
    Headers.Builder result = new Headers.Builder();
    while (true) {
      String line = readLine();
      if (!((line.length() == 0) ? 1 : 0)) {
        result.addLenient$okhttp(line);
        continue;
      } 
      break;
    } 
    return result.build();
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\024\020\005\032\0020\0048\002XT¢\006\006\n\004\b\005\020\006¨\006\007"}, d2 = {"Lokhttp3/internal/http1/HeadersReader$Companion;", "", "<init>", "()V", "", "HEADER_LIMIT", "I", "okhttp"})
  public static final class Companion {
    private Companion() {}
  }
}
