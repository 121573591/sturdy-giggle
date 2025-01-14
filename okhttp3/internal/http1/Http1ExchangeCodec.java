package okhttp3.internal.http1;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\001\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\007\n\002\030\002\n\000\n\002\030\002\n\002\b\b\n\002\030\002\n\002\b\003\n\002\020\013\n\000\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\003\n\002\020\016\n\002\b\t\n\002\030\002\n\002\b\006\n\002\020\b\n\002\b\016\030\000 R2\0020\001:\007STURVWXB)\022\b\020\003\032\004\030\0010\002\022\006\020\005\032\0020\004\022\006\020\007\032\0020\006\022\006\020\t\032\0020\b¢\006\004\b\n\020\013J\017\020\r\032\0020\fH\026¢\006\004\b\r\020\016J\037\020\024\032\0020\0232\006\020\020\032\0020\0172\006\020\022\032\0020\021H\026¢\006\004\b\024\020\025J\027\020\030\032\0020\f2\006\020\027\032\0020\026H\002¢\006\004\b\030\020\031J\017\020\032\032\0020\fH\026¢\006\004\b\032\020\016J\017\020\033\032\0020\fH\026¢\006\004\b\033\020\016J\017\020\034\032\0020\023H\002¢\006\004\b\034\020\035J\027\020!\032\0020 2\006\020\037\032\0020\036H\002¢\006\004\b!\020\"J\027\020$\032\0020 2\006\020#\032\0020\021H\002¢\006\004\b$\020%J\017\020&\032\0020\023H\002¢\006\004\b&\020\035J\017\020'\032\0020 H\002¢\006\004\b'\020(J\027\020+\032\0020 2\006\020*\032\0020)H\026¢\006\004\b+\020,J\031\0200\032\004\030\0010/2\006\020.\032\0020-H\026¢\006\004\b0\0201J\027\0202\032\0020\0212\006\020*\032\0020)H\026¢\006\004\b2\0203J\025\0204\032\0020\f2\006\020*\032\0020)¢\006\004\b4\0205J\017\0207\032\00206H\026¢\006\004\b7\0208J\035\020<\032\0020\f2\006\0209\032\002062\006\020;\032\0020:¢\006\004\b<\020=J\027\020>\032\0020\f2\006\020\020\032\0020\017H\026¢\006\004\b>\020?R\026\020\003\032\004\030\0010\0028\002X\004¢\006\006\n\004\b\003\020@R\032\020\005\032\0020\0048\026X\004¢\006\f\n\004\b\005\020A\032\004\bB\020CR\024\020E\032\0020D8\002X\004¢\006\006\n\004\bE\020FR\021\020G\032\0020-8F¢\006\006\032\004\bG\020HR\024\020\t\032\0020\b8\002X\004¢\006\006\n\004\b\t\020IR\024\020\007\032\0020\0068\002X\004¢\006\006\n\004\b\007\020JR\026\020L\032\0020K8\002@\002X\016¢\006\006\n\004\bL\020MR\030\0207\032\004\030\001068\002@\002X\016¢\006\006\n\004\b7\020NR\030\020O\032\0020-*\0020\0178BX\004¢\006\006\032\004\bO\020PR\030\020O\032\0020-*\0020)8BX\004¢\006\006\032\004\bO\020Q¨\006Y"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec;", "Lokhttp3/internal/http/ExchangeCodec;", "Lokhttp3/OkHttpClient;", "client", "Lokhttp3/internal/connection/RealConnection;", "connection", "Lokio/BufferedSource;", "source", "Lokio/BufferedSink;", "sink", "<init>", "(Lokhttp3/OkHttpClient;Lokhttp3/internal/connection/RealConnection;Lokio/BufferedSource;Lokio/BufferedSink;)V", "", "cancel", "()V", "Lokhttp3/Request;", "request", "", "contentLength", "Lokio/Sink;", "createRequestBody", "(Lokhttp3/Request;J)Lokio/Sink;", "Lokio/ForwardingTimeout;", "timeout", "detachTimeout", "(Lokio/ForwardingTimeout;)V", "finishRequest", "flushRequest", "newChunkedSink", "()Lokio/Sink;", "Lokhttp3/HttpUrl;", "url", "Lokio/Source;", "newChunkedSource", "(Lokhttp3/HttpUrl;)Lokio/Source;", "length", "newFixedLengthSource", "(J)Lokio/Source;", "newKnownLengthSink", "newUnknownLengthSource", "()Lokio/Source;", "Lokhttp3/Response;", "response", "openResponseBodySource", "(Lokhttp3/Response;)Lokio/Source;", "", "expectContinue", "Lokhttp3/Response$Builder;", "readResponseHeaders", "(Z)Lokhttp3/Response$Builder;", "reportedContentLength", "(Lokhttp3/Response;)J", "skipConnectBody", "(Lokhttp3/Response;)V", "Lokhttp3/Headers;", "trailers", "()Lokhttp3/Headers;", "headers", "", "requestLine", "writeRequest", "(Lokhttp3/Headers;Ljava/lang/String;)V", "writeRequestHeaders", "(Lokhttp3/Request;)V", "Lokhttp3/OkHttpClient;", "Lokhttp3/internal/connection/RealConnection;", "getConnection", "()Lokhttp3/internal/connection/RealConnection;", "Lokhttp3/internal/http1/HeadersReader;", "headersReader", "Lokhttp3/internal/http1/HeadersReader;", "isClosed", "()Z", "Lokio/BufferedSink;", "Lokio/BufferedSource;", "", "state", "I", "Lokhttp3/Headers;", "isChunked", "(Lokhttp3/Request;)Z", "(Lokhttp3/Response;)Z", "Companion", "AbstractSource", "ChunkedSink", "ChunkedSource", "FixedLengthSource", "KnownLengthSink", "UnknownLengthSource", "okhttp"})
@SourceDebugExtension({"SMAP\nHttp1ExchangeCodec.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http1ExchangeCodec.kt\nokhttp3/internal/http1/Http1ExchangeCodec\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,505:1\n1#2:506\n*E\n"})
public final class Http1ExchangeCodec implements ExchangeCodec {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @Nullable
  private final OkHttpClient client;
  
  @NotNull
  private final RealConnection connection;
  
  @NotNull
  private final BufferedSource source;
  
  @NotNull
  private final BufferedSink sink;
  
  private int state;
  
  @NotNull
  private final HeadersReader headersReader;
  
  @Nullable
  private Headers trailers;
  
  private static final long NO_CHUNK_YET = -1L;
  
  private static final int STATE_IDLE = 0;
  
  private static final int STATE_OPEN_REQUEST_BODY = 1;
  
  private static final int STATE_WRITING_REQUEST_BODY = 2;
  
  private static final int STATE_READ_RESPONSE_HEADERS = 3;
  
  private static final int STATE_OPEN_RESPONSE_BODY = 4;
  
  private static final int STATE_READING_RESPONSE_BODY = 5;
  
  private static final int STATE_CLOSED = 6;
  
  public Http1ExchangeCodec(@Nullable OkHttpClient client, @NotNull RealConnection connection, @NotNull BufferedSource source, @NotNull BufferedSink sink) {
    this.client = client;
    this.connection = connection;
    this.source = source;
    this.sink = sink;
    this.headersReader = new HeadersReader(this.source);
  }
  
  @NotNull
  public RealConnection getConnection() {
    return this.connection;
  }
  
  private final boolean isChunked(Response $this$isChunked) {
    return StringsKt.equals("chunked", Response.header$default($this$isChunked, "Transfer-Encoding", null, 2, null), true);
  }
  
  private final boolean isChunked(Request $this$isChunked) {
    return StringsKt.equals("chunked", $this$isChunked.header("Transfer-Encoding"), true);
  }
  
  public final boolean isClosed() {
    return (this.state == 6);
  }
  
  @NotNull
  public Sink createRequestBody(@NotNull Request request, long contentLength) {
    Intrinsics.checkNotNullParameter(request, "request");
    if (request.body() != null && request.body().isDuplex())
      throw new ProtocolException(
          "Duplex connections are not supported for HTTP/1"); 
    if (contentLength != -1L) {
    
    } else {
      throw new IllegalStateException(
          "Cannot stream a request body without chunked encoding or a known content length!");
    } 
    return isChunked(request) ? newChunkedSink() : (Sink)"JD-Core does not support Kotlin";
  }
  
  public void cancel() {
    getConnection().cancel();
  }
  
  public void writeRequestHeaders(@NotNull Request request) {
    Intrinsics.checkNotNullParameter(request, "request");
    Intrinsics.checkNotNullExpressionValue(getConnection().route().proxy().type(), "connection.route().proxy.type()");
    String requestLine = RequestLine.INSTANCE.get(request, getConnection().route().proxy().type());
    writeRequest(request.headers(), requestLine);
  }
  
  public long reportedContentLength(@NotNull Response response) {
    Intrinsics.checkNotNullParameter(response, "response");
    return 
      !HttpHeaders.promisesBody(response) ? 0L : (
      isChunked(response) ? -1L : 
      Util.headersContentLength(response));
  }
  
  @NotNull
  public Source openResponseBodySource(@NotNull Response response) {
    Intrinsics.checkNotNullParameter(response, "response");
    long contentLength = Util.headersContentLength(response);
    return !HttpHeaders.promisesBody(response) ? newFixedLengthSource(0L) : (isChunked(response) ? newChunkedSource(response.request().url()) : ((contentLength != -1L) ? 
      newFixedLengthSource(contentLength) : 
      
      newUnknownLengthSource()));
  }
  
  @NotNull
  public Headers trailers() {
    if (!((this.state == 6) ? 1 : 0)) {
      int $i$a$-check-Http1ExchangeCodec$trailers$1 = 0;
      String str = "too early; can't read the trailers yet";
      throw new IllegalStateException(str.toString());
    } 
    if (this.trailers == null);
    return Util.EMPTY_HEADERS;
  }
  
  public void flushRequest() {
    this.sink.flush();
  }
  
  public void finishRequest() {
    this.sink.flush();
  }
  
  public final void writeRequest(@NotNull Headers headers, @NotNull String requestLine) {
    Intrinsics.checkNotNullParameter(headers, "headers");
    Intrinsics.checkNotNullParameter(requestLine, "requestLine");
    if (!((this.state == 0) ? 1 : 0)) {
      int $i$a$-check-Http1ExchangeCodec$writeRequest$1 = 0;
      String str = "state: " + this.state;
      throw new IllegalStateException(str.toString());
    } 
    this.sink.writeUtf8(requestLine).writeUtf8("\r\n");
    for (int i = 0, j = headers.size(); i < j; i++)
      this.sink.writeUtf8(headers.name(i)).writeUtf8(": ").writeUtf8(headers.value(i)).writeUtf8("\r\n"); 
    this.sink.writeUtf8("\r\n");
    this.state = 1;
  }
  
  @Nullable
  public Response.Builder readResponseHeaders(boolean expectContinue) {
    if (!((this.state == 1 || this.state == 2 || this.state == 3) ? 1 : 0)) {
      int $i$a$-check-Http1ExchangeCodec$readResponseHeaders$1 = 0;
      String str = "state: " + this.state;
      throw new IllegalStateException(str.toString());
    } 
    try {
      StatusLine statusLine = StatusLine.Companion.parse(this.headersReader.readLine());
      Response.Builder responseBuilder = (new Response.Builder()).protocol(statusLine.protocol).code(statusLine.code).message(statusLine.message).headers(this.headersReader.readHeaders());
      this.state = 3;
      int i = statusLine.code;
      this.state = 3;
      this.state = 4;
      return (expectContinue && statusLine.code == 100) ? null : ((statusLine.code == 100) ? responseBuilder : (((102 <= i) ? ((i < 200)) : false) ? responseBuilder : responseBuilder));
    } catch (EOFException e) {
      String address = getConnection().route().address().url().redact();
      throw new IOException("unexpected end of stream on " + address, e);
    } 
  }
  
  private final Sink newChunkedSink() {
    if (!((this.state == 1) ? 1 : 0)) {
      int $i$a$-check-Http1ExchangeCodec$newChunkedSink$1 = 0;
      String str = "state: " + this.state;
      throw new IllegalStateException(str.toString());
    } 
    this.state = 2;
    return new ChunkedSink();
  }
  
  private final Sink newKnownLengthSink() {
    if (!((this.state == 1) ? 1 : 0)) {
      int $i$a$-check-Http1ExchangeCodec$newKnownLengthSink$1 = 0;
      String str = "state: " + this.state;
      throw new IllegalStateException(str.toString());
    } 
    this.state = 2;
    return new KnownLengthSink();
  }
  
  private final Source newFixedLengthSource(long length) {
    if (!((this.state == 4) ? 1 : 0)) {
      int $i$a$-check-Http1ExchangeCodec$newFixedLengthSource$1 = 0;
      String str = "state: " + this.state;
      throw new IllegalStateException(str.toString());
    } 
    this.state = 5;
    return new FixedLengthSource(length);
  }
  
  private final Source newChunkedSource(HttpUrl url) {
    if (!((this.state == 4) ? 1 : 0)) {
      int $i$a$-check-Http1ExchangeCodec$newChunkedSource$1 = 0;
      String str = "state: " + this.state;
      throw new IllegalStateException(str.toString());
    } 
    this.state = 5;
    return new ChunkedSource(url);
  }
  
  private final Source newUnknownLengthSource() {
    if (!((this.state == 4) ? 1 : 0)) {
      int $i$a$-check-Http1ExchangeCodec$newUnknownLengthSource$1 = 0;
      String str = "state: " + this.state;
      throw new IllegalStateException(str.toString());
    } 
    this.state = 5;
    getConnection().noNewExchanges$okhttp();
    return new UnknownLengthSource();
  }
  
  private final void detachTimeout(ForwardingTimeout timeout) {
    Timeout oldDelegate = timeout.delegate();
    timeout.setDelegate(Timeout.NONE);
    oldDelegate.clearDeadline();
    oldDelegate.clearTimeout();
  }
  
  public final void skipConnectBody(@NotNull Response response) {
    Intrinsics.checkNotNullParameter(response, "response");
    long contentLength = Util.headersContentLength(response);
    if (contentLength == -1L)
      return; 
    Source body = newFixedLengthSource(contentLength);
    Util.skipAll(body, 2147483647, TimeUnit.MILLISECONDS);
    body.close();
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000:\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\002\b\004\030\0002\0020\001B\007¢\006\004\b\002\020\003J\017\020\005\032\0020\004H\026¢\006\004\b\005\020\006J\017\020\007\032\0020\004H\026¢\006\004\b\007\020\006J\017\020\t\032\0020\bH\026¢\006\004\b\t\020\nJ\037\020\017\032\0020\0042\006\020\f\032\0020\0132\006\020\016\032\0020\rH\026¢\006\004\b\017\020\020R\026\020\022\032\0020\0218\002@\002X\016¢\006\006\n\004\b\022\020\023R\024\020\t\032\0020\0248\002X\004¢\006\006\n\004\b\t\020\025¨\006\026"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$KnownLengthSink;", "Lokio/Sink;", "<init>", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "", "close", "()V", "flush", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "Lokio/Buffer;", "source", "", "byteCount", "write", "(Lokio/Buffer;J)V", "", "closed", "Z", "Lokio/ForwardingTimeout;", "Lokio/ForwardingTimeout;", "okhttp"})
  @SourceDebugExtension({"SMAP\nHttp1ExchangeCodec.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http1ExchangeCodec.kt\nokhttp3/internal/http1/Http1ExchangeCodec$KnownLengthSink\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,505:1\n1#2:506\n*E\n"})
  private final class KnownLengthSink implements Sink {
    @NotNull
    private final ForwardingTimeout timeout = new ForwardingTimeout(Http1ExchangeCodec.this.sink.timeout());
    
    private boolean closed;
    
    @NotNull
    public Timeout timeout() {
      return (Timeout)this.timeout;
    }
    
    public void write(@NotNull Buffer source, long byteCount) {
      Intrinsics.checkNotNullParameter(source, "source");
      if (!(!this.closed ? 1 : 0)) {
        int $i$a$-check-Http1ExchangeCodec$KnownLengthSink$write$1 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      Util.checkOffsetAndCount(source.size(), 0L, byteCount);
      Http1ExchangeCodec.this.sink.write(source, byteCount);
    }
    
    public void flush() {
      if (this.closed)
        return; 
      Http1ExchangeCodec.this.sink.flush();
    }
    
    public void close() {
      if (this.closed)
        return; 
      this.closed = true;
      Http1ExchangeCodec.this.detachTimeout(this.timeout);
      Http1ExchangeCodec.this.state = 3;
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000:\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\002\b\004\030\0002\0020\001B\007¢\006\004\b\002\020\003J\017\020\005\032\0020\004H\026¢\006\004\b\005\020\006J\017\020\007\032\0020\004H\026¢\006\004\b\007\020\006J\017\020\t\032\0020\bH\026¢\006\004\b\t\020\nJ\037\020\017\032\0020\0042\006\020\f\032\0020\0132\006\020\016\032\0020\rH\026¢\006\004\b\017\020\020R\026\020\022\032\0020\0218\002@\002X\016¢\006\006\n\004\b\022\020\023R\024\020\t\032\0020\0248\002X\004¢\006\006\n\004\b\t\020\025¨\006\026"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$ChunkedSink;", "Lokio/Sink;", "<init>", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "", "close", "()V", "flush", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "Lokio/Buffer;", "source", "", "byteCount", "write", "(Lokio/Buffer;J)V", "", "closed", "Z", "Lokio/ForwardingTimeout;", "Lokio/ForwardingTimeout;", "okhttp"})
  @SourceDebugExtension({"SMAP\nHttp1ExchangeCodec.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http1ExchangeCodec.kt\nokhttp3/internal/http1/Http1ExchangeCodec$ChunkedSink\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,505:1\n1#2:506\n*E\n"})
  private final class ChunkedSink implements Sink {
    @NotNull
    private final ForwardingTimeout timeout = new ForwardingTimeout(Http1ExchangeCodec.this.sink.timeout());
    
    private boolean closed;
    
    @NotNull
    public Timeout timeout() {
      return (Timeout)this.timeout;
    }
    
    public void write(@NotNull Buffer source, long byteCount) {
      Intrinsics.checkNotNullParameter(source, "source");
      if (!(!this.closed ? 1 : 0)) {
        int $i$a$-check-Http1ExchangeCodec$ChunkedSink$write$1 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      if (byteCount == 0L)
        return; 
      Http1ExchangeCodec.this.sink.writeHexadecimalUnsignedLong(byteCount);
      Http1ExchangeCodec.this.sink.writeUtf8("\r\n");
      Http1ExchangeCodec.this.sink.write(source, byteCount);
      Http1ExchangeCodec.this.sink.writeUtf8("\r\n");
    }
    
    public synchronized void flush() {
      if (this.closed)
        return; 
      Http1ExchangeCodec.this.sink.flush();
    }
    
    public synchronized void close() {
      if (this.closed)
        return; 
      this.closed = true;
      Http1ExchangeCodec.this.sink.writeUtf8("0\r\n\r\n");
      Http1ExchangeCodec.this.detachTimeout(this.timeout);
      Http1ExchangeCodec.this.state = 3;
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000:\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\006\n\002\030\002\n\002\b\004\b¢\004\030\0002\0020\001B\007¢\006\004\b\002\020\003J\037\020\b\032\0020\0062\006\020\005\032\0020\0042\006\020\007\032\0020\006H\026¢\006\004\b\b\020\tJ\r\020\013\032\0020\n¢\006\004\b\013\020\fJ\017\020\016\032\0020\rH\026¢\006\004\b\016\020\017R\"\020\021\032\0020\0208\004@\004X\016¢\006\022\n\004\b\021\020\022\032\004\b\023\020\024\"\004\b\025\020\026R\032\020\016\032\0020\0278\004X\004¢\006\f\n\004\b\016\020\030\032\004\b\031\020\032¨\006\033"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokio/Source;", "<init>", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "Lokio/Buffer;", "sink", "", "byteCount", "read", "(Lokio/Buffer;J)J", "", "responseBodyComplete", "()V", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "", "closed", "Z", "getClosed", "()Z", "setClosed", "(Z)V", "Lokio/ForwardingTimeout;", "Lokio/ForwardingTimeout;", "getTimeout", "()Lokio/ForwardingTimeout;", "okhttp"})
  private abstract class AbstractSource implements Source {
    @NotNull
    private final ForwardingTimeout timeout = new ForwardingTimeout(Http1ExchangeCodec.this.source.timeout());
    
    private boolean closed;
    
    @NotNull
    protected final ForwardingTimeout getTimeout() {
      return this.timeout;
    }
    
    protected final boolean getClosed() {
      return this.closed;
    }
    
    protected final void setClosed(boolean <set-?>) {
      this.closed = <set-?>;
    }
    
    @NotNull
    public Timeout timeout() {
      return (Timeout)this.timeout;
    }
    
    public long read(@NotNull Buffer sink, long byteCount) {
      long l;
      Intrinsics.checkNotNullParameter(sink, "sink");
      try {
        l = Http1ExchangeCodec.this.source.read(sink, byteCount);
      } catch (IOException e) {
        Http1ExchangeCodec.this.getConnection().noNewExchanges$okhttp();
        responseBodyComplete();
        throw e;
      } 
      return l;
    }
    
    public final void responseBodyComplete() {
      if (Http1ExchangeCodec.this.state == 6)
        return; 
      if (Http1ExchangeCodec.this.state != 5)
        throw new IllegalStateException("state: " + Http1ExchangeCodec.this.state); 
      Http1ExchangeCodec.this.detachTimeout(this.timeout);
      Http1ExchangeCodec.this.state = 6;
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000$\n\002\030\002\n\002\030\002\n\002\030\002\n\002\020\t\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\006\b\004\030\0002\0060\001R\0020\002B\017\022\006\020\004\032\0020\003¢\006\004\b\005\020\006J\017\020\b\032\0020\007H\026¢\006\004\b\b\020\tJ\037\020\r\032\0020\0032\006\020\013\032\0020\n2\006\020\f\032\0020\003H\026¢\006\004\b\r\020\016R\026\020\004\032\0020\0038\002@\002X\016¢\006\006\n\004\b\004\020\017¨\006\020"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$FixedLengthSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "", "bytesRemaining", "<init>", "(Lokhttp3/internal/http1/Http1ExchangeCodec;J)V", "", "close", "()V", "Lokio/Buffer;", "sink", "byteCount", "read", "(Lokio/Buffer;J)J", "J", "okhttp"})
  @SourceDebugExtension({"SMAP\nHttp1ExchangeCodec.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http1ExchangeCodec.kt\nokhttp3/internal/http1/Http1ExchangeCodec$FixedLengthSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,505:1\n1#2:506\n*E\n"})
  private final class FixedLengthSource extends AbstractSource {
    private long bytesRemaining;
    
    public FixedLengthSource(long bytesRemaining) {
      this.bytesRemaining = bytesRemaining;
      if (this.bytesRemaining == 0L)
        responseBodyComplete(); 
    }
    
    public long read(@NotNull Buffer sink, long byteCount) {
      Intrinsics.checkNotNullParameter(sink, "sink");
      if (!((byteCount >= 0L) ? 1 : 0)) {
        int $i$a$-require-Http1ExchangeCodec$FixedLengthSource$read$1 = 0;
        String str = "byteCount < 0: " + byteCount;
        throw new IllegalArgumentException(str.toString());
      } 
      if (!(!getClosed() ? 1 : 0)) {
        int $i$a$-check-Http1ExchangeCodec$FixedLengthSource$read$2 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      if (this.bytesRemaining == 0L)
        return -1L; 
      long read = super.read(sink, Math.min(this.bytesRemaining, byteCount));
      if (read == -1L) {
        Http1ExchangeCodec.this.getConnection().noNewExchanges$okhttp();
        ProtocolException e = new ProtocolException("unexpected end of stream");
        responseBodyComplete();
        throw e;
      } 
      this.bytesRemaining -= read;
      if (this.bytesRemaining == 0L)
        responseBodyComplete(); 
      return read;
    }
    
    public void close() {
      if (getClosed())
        return; 
      if (this.bytesRemaining != 0L && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
        Http1ExchangeCodec.this.getConnection().noNewExchanges$okhttp();
        responseBodyComplete();
      } 
      setClosed(true);
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0002\n\002\030\002\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\006\n\002\020\013\n\002\b\004\b\004\030\0002\0060\001R\0020\002B\017\022\006\020\004\032\0020\003¢\006\004\b\005\020\006J\017\020\b\032\0020\007H\026¢\006\004\b\b\020\tJ\037\020\016\032\0020\f2\006\020\013\032\0020\n2\006\020\r\032\0020\fH\026¢\006\004\b\016\020\017J\017\020\020\032\0020\007H\002¢\006\004\b\020\020\tR\026\020\021\032\0020\f8\002@\002X\016¢\006\006\n\004\b\021\020\022R\026\020\024\032\0020\0238\002@\002X\016¢\006\006\n\004\b\024\020\025R\024\020\004\032\0020\0038\002X\004¢\006\006\n\004\b\004\020\026¨\006\027"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$ChunkedSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "Lokhttp3/HttpUrl;", "url", "<init>", "(Lokhttp3/internal/http1/Http1ExchangeCodec;Lokhttp3/HttpUrl;)V", "", "close", "()V", "Lokio/Buffer;", "sink", "", "byteCount", "read", "(Lokio/Buffer;J)J", "readChunkSize", "bytesRemainingInChunk", "J", "", "hasMoreChunks", "Z", "Lokhttp3/HttpUrl;", "okhttp"})
  @SourceDebugExtension({"SMAP\nHttp1ExchangeCodec.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http1ExchangeCodec.kt\nokhttp3/internal/http1/Http1ExchangeCodec$ChunkedSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,505:1\n1#2:506\n*E\n"})
  private final class ChunkedSource extends AbstractSource {
    @NotNull
    private final HttpUrl url;
    
    private long bytesRemainingInChunk;
    
    private boolean hasMoreChunks;
    
    public ChunkedSource(HttpUrl url) {
      this.url = url;
      this.bytesRemainingInChunk = -1L;
      this.hasMoreChunks = true;
    }
    
    public long read(@NotNull Buffer sink, long byteCount) {
      Intrinsics.checkNotNullParameter(sink, "sink");
      if (!((byteCount >= 0L) ? 1 : 0)) {
        int $i$a$-require-Http1ExchangeCodec$ChunkedSource$read$1 = 0;
        String str = "byteCount < 0: " + byteCount;
        throw new IllegalArgumentException(str.toString());
      } 
      if (!(!getClosed() ? 1 : 0)) {
        int $i$a$-check-Http1ExchangeCodec$ChunkedSource$read$2 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      if (!this.hasMoreChunks)
        return -1L; 
      if (this.bytesRemainingInChunk == 0L || this.bytesRemainingInChunk == -1L) {
        readChunkSize();
        if (!this.hasMoreChunks)
          return -1L; 
      } 
      long read = super.read(sink, Math.min(byteCount, this.bytesRemainingInChunk));
      if (read == -1L) {
        Http1ExchangeCodec.this.getConnection().noNewExchanges$okhttp();
        ProtocolException e = new ProtocolException("unexpected end of stream");
        responseBodyComplete();
        throw e;
      } 
      this.bytesRemainingInChunk -= read;
      return read;
    }
    
    private final void readChunkSize() {
      if (this.bytesRemainingInChunk != -1L)
        Http1ExchangeCodec.this.source.readUtf8LineStrict(); 
      try {
        this.bytesRemainingInChunk = Http1ExchangeCodec.this.source.readHexadecimalUnsignedLong();
        String extensions = StringsKt.trim(Http1ExchangeCodec.this.source.readUtf8LineStrict()).toString();
        if (this.bytesRemainingInChunk < 0L || (((((CharSequence)extensions).length() > 0)) && !StringsKt.startsWith$default(extensions, ";", false, 2, null)))
          throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.bytesRemainingInChunk + extensions + '"'); 
      } catch (NumberFormatException e) {
        throw new ProtocolException(e.getMessage());
      } 
      if (this.bytesRemainingInChunk == 0L) {
        this.hasMoreChunks = false;
        Http1ExchangeCodec.this.trailers = Http1ExchangeCodec.this.headersReader.readHeaders();
        Intrinsics.checkNotNull(Http1ExchangeCodec.this.client);
        Intrinsics.checkNotNull(Http1ExchangeCodec.this.trailers);
        HttpHeaders.receiveHeaders(Http1ExchangeCodec.this.client.cookieJar(), this.url, Http1ExchangeCodec.this.trailers);
        responseBodyComplete();
      } 
    }
    
    public void close() {
      if (getClosed())
        return; 
      if (this.hasMoreChunks && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
        Http1ExchangeCodec.this.getConnection().noNewExchanges$okhttp();
        responseBodyComplete();
      } 
      setClosed(true);
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000.\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\020\013\n\002\b\003\b\004\030\0002\0060\001R\0020\002B\007¢\006\004\b\003\020\004J\017\020\006\032\0020\005H\026¢\006\004\b\006\020\007J\037\020\f\032\0020\n2\006\020\t\032\0020\b2\006\020\013\032\0020\nH\026¢\006\004\b\f\020\rR\026\020\017\032\0020\0168\002@\002X\016¢\006\006\n\004\b\017\020\020¨\006\021"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$UnknownLengthSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "<init>", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "", "close", "()V", "Lokio/Buffer;", "sink", "", "byteCount", "read", "(Lokio/Buffer;J)J", "", "inputExhausted", "Z", "okhttp"})
  @SourceDebugExtension({"SMAP\nHttp1ExchangeCodec.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http1ExchangeCodec.kt\nokhttp3/internal/http1/Http1ExchangeCodec$UnknownLengthSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,505:1\n1#2:506\n*E\n"})
  private final class UnknownLengthSource extends AbstractSource {
    private boolean inputExhausted;
    
    public long read(@NotNull Buffer sink, long byteCount) {
      Intrinsics.checkNotNullParameter(sink, "sink");
      if (!((byteCount >= 0L) ? 1 : 0)) {
        int $i$a$-require-Http1ExchangeCodec$UnknownLengthSource$read$1 = 0;
        String str = "byteCount < 0: " + byteCount;
        throw new IllegalArgumentException(str.toString());
      } 
      if (!(!getClosed() ? 1 : 0)) {
        int $i$a$-check-Http1ExchangeCodec$UnknownLengthSource$read$2 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      if (this.inputExhausted)
        return -1L; 
      long read = super.read(sink, byteCount);
      if (read == -1L) {
        this.inputExhausted = true;
        responseBodyComplete();
        return -1L;
      } 
      return read;
    }
    
    public void close() {
      if (getClosed())
        return; 
      if (!this.inputExhausted)
        responseBodyComplete(); 
      setClosed(true);
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\t\n\002\b\002\n\002\020\b\n\002\b\t\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\024\020\005\032\0020\0048\002XT¢\006\006\n\004\b\005\020\006R\024\020\b\032\0020\0078\002XT¢\006\006\n\004\b\b\020\tR\024\020\n\032\0020\0078\002XT¢\006\006\n\004\b\n\020\tR\024\020\013\032\0020\0078\002XT¢\006\006\n\004\b\013\020\tR\024\020\f\032\0020\0078\002XT¢\006\006\n\004\b\f\020\tR\024\020\r\032\0020\0078\002XT¢\006\006\n\004\b\r\020\tR\024\020\016\032\0020\0078\002XT¢\006\006\n\004\b\016\020\tR\024\020\017\032\0020\0078\002XT¢\006\006\n\004\b\017\020\t¨\006\020"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$Companion;", "", "<init>", "()V", "", "NO_CHUNK_YET", "J", "", "STATE_CLOSED", "I", "STATE_IDLE", "STATE_OPEN_REQUEST_BODY", "STATE_OPEN_RESPONSE_BODY", "STATE_READING_RESPONSE_BODY", "STATE_READ_RESPONSE_HEADERS", "STATE_WRITING_REQUEST_BODY", "okhttp"})
  public static final class Companion {
    private Companion() {}
  }
}
