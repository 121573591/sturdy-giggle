package okhttp3.internal.http;

import java.io.IOException;
import kotlin.Metadata;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.connection.RealConnection;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000P\n\002\030\002\n\002\020\000\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\005\bf\030\000 #2\0020\001:\001#J\017\020\003\032\0020\002H&¢\006\004\b\003\020\004J\037\020\n\032\0020\t2\006\020\006\032\0020\0052\006\020\b\032\0020\007H&¢\006\004\b\n\020\013J\017\020\f\032\0020\002H&¢\006\004\b\f\020\004J\017\020\r\032\0020\002H&¢\006\004\b\r\020\004J\027\020\021\032\0020\0202\006\020\017\032\0020\016H&¢\006\004\b\021\020\022J\031\020\026\032\004\030\0010\0252\006\020\024\032\0020\023H&¢\006\004\b\026\020\027J\027\020\030\032\0020\0072\006\020\017\032\0020\016H&¢\006\004\b\030\020\031J\017\020\033\032\0020\032H&¢\006\004\b\033\020\034J\027\020\035\032\0020\0022\006\020\006\032\0020\005H&¢\006\004\b\035\020\036R\024\020\"\032\0020\0378&X¦\004¢\006\006\032\004\b \020!¨\006$"}, d2 = {"Lokhttp3/internal/http/ExchangeCodec;", "", "", "cancel", "()V", "Lokhttp3/Request;", "request", "", "contentLength", "Lokio/Sink;", "createRequestBody", "(Lokhttp3/Request;J)Lokio/Sink;", "finishRequest", "flushRequest", "Lokhttp3/Response;", "response", "Lokio/Source;", "openResponseBodySource", "(Lokhttp3/Response;)Lokio/Source;", "", "expectContinue", "Lokhttp3/Response$Builder;", "readResponseHeaders", "(Z)Lokhttp3/Response$Builder;", "reportedContentLength", "(Lokhttp3/Response;)J", "Lokhttp3/Headers;", "trailers", "()Lokhttp3/Headers;", "writeRequestHeaders", "(Lokhttp3/Request;)V", "Lokhttp3/internal/connection/RealConnection;", "getConnection", "()Lokhttp3/internal/connection/RealConnection;", "connection", "Companion", "okhttp"})
public interface ExchangeCodec {
  @NotNull
  public static final Companion Companion = Companion.$$INSTANCE;
  
  public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;
  
  @NotNull
  RealConnection getConnection();
  
  @NotNull
  Sink createRequestBody(@NotNull Request paramRequest, long paramLong) throws IOException;
  
  void writeRequestHeaders(@NotNull Request paramRequest) throws IOException;
  
  void flushRequest() throws IOException;
  
  void finishRequest() throws IOException;
  
  @Nullable
  Response.Builder readResponseHeaders(boolean paramBoolean) throws IOException;
  
  long reportedContentLength(@NotNull Response paramResponse) throws IOException;
  
  @NotNull
  Source openResponseBodySource(@NotNull Response paramResponse) throws IOException;
  
  @NotNull
  Headers trailers() throws IOException;
  
  void cancel();
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\024\020\005\032\0020\0048\006XT¢\006\006\n\004\b\005\020\006¨\006\007"}, d2 = {"Lokhttp3/internal/http/ExchangeCodec$Companion;", "", "<init>", "()V", "", "DISCARD_STREAM_TIMEOUT_MILLIS", "I", "okhttp"})
  public static final class Companion {
    public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;
  }
}
