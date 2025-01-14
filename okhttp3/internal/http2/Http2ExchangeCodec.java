package okhttp3.internal.http2;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000r\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\013\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\030\000 62\0020\001:\0016B'\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\006\020\007\032\0020\006\022\006\020\t\032\0020\b¢\006\004\b\n\020\013J\017\020\r\032\0020\fH\026¢\006\004\b\r\020\016J\037\020\024\032\0020\0232\006\020\020\032\0020\0172\006\020\022\032\0020\021H\026¢\006\004\b\024\020\025J\017\020\026\032\0020\fH\026¢\006\004\b\026\020\016J\017\020\027\032\0020\fH\026¢\006\004\b\027\020\016J\027\020\033\032\0020\0322\006\020\031\032\0020\030H\026¢\006\004\b\033\020\034J\031\020 \032\004\030\0010\0372\006\020\036\032\0020\035H\026¢\006\004\b \020!J\027\020\"\032\0020\0212\006\020\031\032\0020\030H\026¢\006\004\b\"\020#J\017\020%\032\0020$H\026¢\006\004\b%\020&J\027\020'\032\0020\f2\006\020\020\032\0020\017H\026¢\006\004\b'\020(R\026\020)\032\0020\0358\002@\002X\016¢\006\006\n\004\b)\020*R\024\020\007\032\0020\0068\002X\004¢\006\006\n\004\b\007\020+R\032\020\005\032\0020\0048\026X\004¢\006\f\n\004\b\005\020,\032\004\b-\020.R\024\020\t\032\0020\b8\002X\004¢\006\006\n\004\b\t\020/R\024\0201\032\002008\002X\004¢\006\006\n\004\b1\0202R\030\0204\032\004\030\001038\002@\002X\016¢\006\006\n\004\b4\0205¨\0067"}, d2 = {"Lokhttp3/internal/http2/Http2ExchangeCodec;", "Lokhttp3/internal/http/ExchangeCodec;", "Lokhttp3/OkHttpClient;", "client", "Lokhttp3/internal/connection/RealConnection;", "connection", "Lokhttp3/internal/http/RealInterceptorChain;", "chain", "Lokhttp3/internal/http2/Http2Connection;", "http2Connection", "<init>", "(Lokhttp3/OkHttpClient;Lokhttp3/internal/connection/RealConnection;Lokhttp3/internal/http/RealInterceptorChain;Lokhttp3/internal/http2/Http2Connection;)V", "", "cancel", "()V", "Lokhttp3/Request;", "request", "", "contentLength", "Lokio/Sink;", "createRequestBody", "(Lokhttp3/Request;J)Lokio/Sink;", "finishRequest", "flushRequest", "Lokhttp3/Response;", "response", "Lokio/Source;", "openResponseBodySource", "(Lokhttp3/Response;)Lokio/Source;", "", "expectContinue", "Lokhttp3/Response$Builder;", "readResponseHeaders", "(Z)Lokhttp3/Response$Builder;", "reportedContentLength", "(Lokhttp3/Response;)J", "Lokhttp3/Headers;", "trailers", "()Lokhttp3/Headers;", "writeRequestHeaders", "(Lokhttp3/Request;)V", "canceled", "Z", "Lokhttp3/internal/http/RealInterceptorChain;", "Lokhttp3/internal/connection/RealConnection;", "getConnection", "()Lokhttp3/internal/connection/RealConnection;", "Lokhttp3/internal/http2/Http2Connection;", "Lokhttp3/Protocol;", "protocol", "Lokhttp3/Protocol;", "Lokhttp3/internal/http2/Http2Stream;", "stream", "Lokhttp3/internal/http2/Http2Stream;", "Companion", "okhttp"})
public final class Http2ExchangeCodec implements ExchangeCodec {
  public Http2ExchangeCodec(@NotNull OkHttpClient client, @NotNull RealConnection connection, @NotNull RealInterceptorChain chain, @NotNull Http2Connection http2Connection) {
    this.connection = connection;
    this.chain = chain;
    this.http2Connection = http2Connection;
    this.protocol = client.protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE) ? 
      Protocol.H2_PRIOR_KNOWLEDGE : 
      
      Protocol.HTTP_2;
  }
  
  @NotNull
  public RealConnection getConnection() {
    return this.connection;
  }
  
  @NotNull
  public Sink createRequestBody(@NotNull Request request, long contentLength) {
    Intrinsics.checkNotNullParameter(request, "request");
    Intrinsics.checkNotNull(this.stream);
    return this.stream.getSink();
  }
  
  public void writeRequestHeaders(@NotNull Request request) {
    Intrinsics.checkNotNullParameter(request, "request");
    if (this.stream != null)
      return; 
    boolean hasRequestBody = (request.body() != null);
    List<Header> requestHeaders = Companion.http2HeadersList(request);
    this.stream = this.http2Connection.newStream(requestHeaders, hasRequestBody);
    if (this.canceled) {
      Intrinsics.checkNotNull(this.stream);
      this.stream.closeLater(ErrorCode.CANCEL);
      throw new IOException("Canceled");
    } 
    Intrinsics.checkNotNull(this.stream);
    this.stream.readTimeout().timeout(this.chain.getReadTimeoutMillis$okhttp(), TimeUnit.MILLISECONDS);
    Intrinsics.checkNotNull(this.stream);
    this.stream.writeTimeout().timeout(this.chain.getWriteTimeoutMillis$okhttp(), TimeUnit.MILLISECONDS);
  }
  
  public void flushRequest() {
    this.http2Connection.flush();
  }
  
  public void finishRequest() {
    Intrinsics.checkNotNull(this.stream);
    this.stream.getSink().close();
  }
  
  @Nullable
  public Response.Builder readResponseHeaders(boolean expectContinue) {
    Http2Stream stream;
    if (this.stream == null)
      throw new IOException("stream wasn't created"); 
    Headers headers = stream.takeHeaders();
    Response.Builder responseBuilder = Companion.readHttp2HeadersList(headers, this.protocol);
    return (expectContinue && responseBuilder.getCode$okhttp() == 100) ? 
      null : 
      
      responseBuilder;
  }
  
  public long reportedContentLength(@NotNull Response response) {
    Intrinsics.checkNotNullParameter(response, "response");
    return 
      !HttpHeaders.promisesBody(response) ? 0L : 
      Util.headersContentLength(response);
  }
  
  @NotNull
  public Source openResponseBodySource(@NotNull Response response) {
    Intrinsics.checkNotNullParameter(response, "response");
    Intrinsics.checkNotNull(this.stream);
    return this.stream.getSource$okhttp();
  }
  
  @NotNull
  public Headers trailers() {
    Intrinsics.checkNotNull(this.stream);
    return this.stream.trailers();
  }
  
  public void cancel() {
    this.canceled = true;
    if (this.stream != null) {
      this.stream.closeLater(ErrorCode.CANCEL);
    } else {
    
    } 
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000:\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\r\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\033\020\b\032\b\022\004\022\0020\0070\0062\006\020\005\032\0020\004¢\006\004\b\b\020\tJ\035\020\017\032\0020\0162\006\020\013\032\0020\n2\006\020\r\032\0020\f¢\006\004\b\017\020\020R\024\020\022\032\0020\0218\002XT¢\006\006\n\004\b\022\020\023R\024\020\024\032\0020\0218\002XT¢\006\006\n\004\b\024\020\023R\024\020\025\032\0020\0218\002XT¢\006\006\n\004\b\025\020\023R\032\020\026\032\b\022\004\022\0020\0210\0068\002X\004¢\006\006\n\004\b\026\020\027R\032\020\030\032\b\022\004\022\0020\0210\0068\002X\004¢\006\006\n\004\b\030\020\027R\024\020\031\032\0020\0218\002XT¢\006\006\n\004\b\031\020\023R\024\020\032\032\0020\0218\002XT¢\006\006\n\004\b\032\020\023R\024\020\033\032\0020\0218\002XT¢\006\006\n\004\b\033\020\023R\024\020\034\032\0020\0218\002XT¢\006\006\n\004\b\034\020\023R\024\020\035\032\0020\0218\002XT¢\006\006\n\004\b\035\020\023¨\006\036"}, d2 = {"Lokhttp3/internal/http2/Http2ExchangeCodec$Companion;", "", "<init>", "()V", "Lokhttp3/Request;", "request", "", "Lokhttp3/internal/http2/Header;", "http2HeadersList", "(Lokhttp3/Request;)Ljava/util/List;", "Lokhttp3/Headers;", "headerBlock", "Lokhttp3/Protocol;", "protocol", "Lokhttp3/Response$Builder;", "readHttp2HeadersList", "(Lokhttp3/Headers;Lokhttp3/Protocol;)Lokhttp3/Response$Builder;", "", "CONNECTION", "Ljava/lang/String;", "ENCODING", "HOST", "HTTP_2_SKIPPED_REQUEST_HEADERS", "Ljava/util/List;", "HTTP_2_SKIPPED_RESPONSE_HEADERS", "KEEP_ALIVE", "PROXY_CONNECTION", "TE", "TRANSFER_ENCODING", "UPGRADE", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @NotNull
    public final List<Header> http2HeadersList(@NotNull Request request) {
      Intrinsics.checkNotNullParameter(request, "request");
      Headers headers = request.headers();
      ArrayList<Header> result = new ArrayList(headers.size() + 4);
      result.add(new Header(Header.TARGET_METHOD, request.method()));
      result.add(new Header(Header.TARGET_PATH, RequestLine.INSTANCE.requestPath(request.url())));
      String host = request.header("Host");
      if (host != null)
        result.add(new Header(Header.TARGET_AUTHORITY, host)); 
      result.add(new Header(Header.TARGET_SCHEME, request.url().scheme()));
      for (int i = 0, j = headers.size(); i < j; i++) {
        String str1 = headers.name(i);
        Intrinsics.checkNotNullExpressionValue(Locale.US, "US");
        Intrinsics.checkNotNullExpressionValue(str1.toLowerCase(Locale.US), "this as java.lang.String).toLowerCase(locale)");
        String name = str1.toLowerCase(Locale.US);
        if (!Http2ExchangeCodec.HTTP_2_SKIPPED_REQUEST_HEADERS.contains(name) || (
          Intrinsics.areEqual(name, "te") && Intrinsics.areEqual(headers.value(i), "trailers")))
          result.add(new Header(name, headers.value(i))); 
      } 
      return result;
    }
    
    @NotNull
    public final Response.Builder readHttp2HeadersList(@NotNull Headers headerBlock, @NotNull Protocol protocol) {
      Intrinsics.checkNotNullParameter(headerBlock, "headerBlock");
      Intrinsics.checkNotNullParameter(protocol, "protocol");
      StatusLine statusLine = null;
      Headers.Builder headersBuilder = new Headers.Builder();
      for (int i = 0, j = headerBlock.size(); i < j; i++) {
        String name = headerBlock.name(i);
        String value = headerBlock.value(i);
        if (Intrinsics.areEqual(name, ":status")) {
          statusLine = StatusLine.Companion.parse("HTTP/1.1 " + value);
        } else if (!Http2ExchangeCodec.HTTP_2_SKIPPED_RESPONSE_HEADERS.contains(name)) {
          headersBuilder.addLenient$okhttp(name, value);
        } 
      } 
      if (statusLine == null)
        throw new ProtocolException("Expected ':status' header not present"); 
      return (new Response.Builder())
        .protocol(protocol)
        .code(statusLine.code)
        .message(statusLine.message)
        .headers(headersBuilder.build());
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final RealConnection connection;
  
  @NotNull
  private final RealInterceptorChain chain;
  
  @NotNull
  private final Http2Connection http2Connection;
  
  @Nullable
  private volatile Http2Stream stream;
  
  @NotNull
  private final Protocol protocol;
  
  private volatile boolean canceled;
  
  @NotNull
  private static final String CONNECTION = "connection";
  
  @NotNull
  private static final String HOST = "host";
  
  @NotNull
  private static final String KEEP_ALIVE = "keep-alive";
  
  @NotNull
  private static final String PROXY_CONNECTION = "proxy-connection";
  
  @NotNull
  private static final String TRANSFER_ENCODING = "transfer-encoding";
  
  @NotNull
  private static final String TE = "te";
  
  @NotNull
  private static final String ENCODING = "encoding";
  
  @NotNull
  private static final String UPGRADE = "upgrade";
  
  @NotNull
  private static final List<String> HTTP_2_SKIPPED_REQUEST_HEADERS;
  
  @NotNull
  private static final List<String> HTTP_2_SKIPPED_RESPONSE_HEADERS;
  
  static {
    String[] arrayOfString = new String[12];
    arrayOfString[0] = "connection";
    arrayOfString[1] = "host";
    arrayOfString[2] = "keep-alive";
    arrayOfString[3] = "proxy-connection";
    arrayOfString[4] = "te";
    arrayOfString[5] = "transfer-encoding";
    arrayOfString[6] = "encoding";
    arrayOfString[7] = "upgrade";
    arrayOfString[8] = ":method";
    arrayOfString[9] = ":path";
    arrayOfString[10] = ":scheme";
    arrayOfString[11] = ":authority";
    HTTP_2_SKIPPED_REQUEST_HEADERS = Util.immutableListOf((Object[])arrayOfString);
    arrayOfString = new String[8];
    arrayOfString[0] = "connection";
    arrayOfString[1] = "host";
    arrayOfString[2] = "keep-alive";
    arrayOfString[3] = "proxy-connection";
    arrayOfString[4] = "te";
    arrayOfString[5] = "transfer-encoding";
    arrayOfString[6] = "encoding";
    arrayOfString[7] = "upgrade";
    HTTP_2_SKIPPED_RESPONSE_HEADERS = Util.immutableListOf((Object[])arrayOfString);
  }
}
