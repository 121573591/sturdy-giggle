package okhttp3.internal.connection;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.EventListener;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.ws.RealWebSocket;
import okio.Buffer;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000|\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\020\t\n\000\n\002\020\013\n\002\b\005\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\004\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\007\n\002\030\002\n\002\b\t\n\002\030\002\n\002\b\026\030\0002\0020\001:\002STB'\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\006\020\007\032\0020\006\022\006\020\t\032\0020\b¢\006\004\b\n\020\013J9\020\024\032\0028\000\"\n\b\000\020\r*\004\030\0010\f2\006\020\017\032\0020\0162\006\020\021\032\0020\0202\006\020\022\032\0020\0202\006\020\023\032\0028\000¢\006\004\b\024\020\025J\r\020\027\032\0020\026¢\006\004\b\027\020\030J\035\020\035\032\0020\0342\006\020\032\032\0020\0312\006\020\033\032\0020\020¢\006\004\b\035\020\036J\r\020\037\032\0020\026¢\006\004\b\037\020\030J\r\020 \032\0020\026¢\006\004\b \020\030J\r\020!\032\0020\026¢\006\004\b!\020\030J\r\020#\032\0020\"¢\006\004\b#\020$J\r\020%\032\0020\026¢\006\004\b%\020\030J\r\020&\032\0020\026¢\006\004\b&\020\030J\025\020*\032\0020)2\006\020(\032\0020'¢\006\004\b*\020+J\027\020.\032\004\030\0010-2\006\020,\032\0020\020¢\006\004\b.\020/J\025\0200\032\0020\0262\006\020(\032\0020'¢\006\004\b0\0201J\r\0202\032\0020\026¢\006\004\b2\020\030J\027\0203\032\0020\0262\006\020\023\032\0020\fH\002¢\006\004\b3\0204J\r\0206\032\00205¢\006\004\b6\0207J\r\0208\032\0020\026¢\006\004\b8\020\030J\025\0209\032\0020\0262\006\020\032\032\0020\031¢\006\004\b9\020:R\032\020\003\032\0020\0028\000X\004¢\006\f\n\004\b\003\020;\032\004\b<\020=R\024\020\t\032\0020\b8\002X\004¢\006\006\n\004\b\t\020>R\032\020@\032\0020?8\000X\004¢\006\f\n\004\b@\020A\032\004\bB\020CR\032\020\005\032\0020\0048\000X\004¢\006\f\n\004\b\005\020D\032\004\bE\020FR\032\020\007\032\0020\0068\000X\004¢\006\f\n\004\b\007\020G\032\004\bH\020IR$\020K\032\0020\0202\006\020J\032\0020\0208\000@BX\016¢\006\f\n\004\bK\020L\032\004\bM\020NR\024\020P\032\0020\0208@X\004¢\006\006\032\004\bO\020NR$\020Q\032\0020\0202\006\020J\032\0020\0208\000@BX\016¢\006\f\n\004\bQ\020L\032\004\bR\020N¨\006U"}, d2 = {"Lokhttp3/internal/connection/Exchange;", "", "Lokhttp3/internal/connection/RealCall;", "call", "Lokhttp3/EventListener;", "eventListener", "Lokhttp3/internal/connection/ExchangeFinder;", "finder", "Lokhttp3/internal/http/ExchangeCodec;", "codec", "<init>", "(Lokhttp3/internal/connection/RealCall;Lokhttp3/EventListener;Lokhttp3/internal/connection/ExchangeFinder;Lokhttp3/internal/http/ExchangeCodec;)V", "Ljava/io/IOException;", "E", "", "bytesRead", "", "responseDone", "requestDone", "e", "bodyComplete", "(JZZLjava/io/IOException;)Ljava/io/IOException;", "", "cancel", "()V", "Lokhttp3/Request;", "request", "duplex", "Lokio/Sink;", "createRequestBody", "(Lokhttp3/Request;Z)Lokio/Sink;", "detachWithViolence", "finishRequest", "flushRequest", "Lokhttp3/internal/ws/RealWebSocket$Streams;", "newWebSocketStreams", "()Lokhttp3/internal/ws/RealWebSocket$Streams;", "noNewExchangesOnConnection", "noRequestBody", "Lokhttp3/Response;", "response", "Lokhttp3/ResponseBody;", "openResponseBody", "(Lokhttp3/Response;)Lokhttp3/ResponseBody;", "expectContinue", "Lokhttp3/Response$Builder;", "readResponseHeaders", "(Z)Lokhttp3/Response$Builder;", "responseHeadersEnd", "(Lokhttp3/Response;)V", "responseHeadersStart", "trackFailure", "(Ljava/io/IOException;)V", "Lokhttp3/Headers;", "trailers", "()Lokhttp3/Headers;", "webSocketUpgradeFailed", "writeRequestHeaders", "(Lokhttp3/Request;)V", "Lokhttp3/internal/connection/RealCall;", "getCall$okhttp", "()Lokhttp3/internal/connection/RealCall;", "Lokhttp3/internal/http/ExchangeCodec;", "Lokhttp3/internal/connection/RealConnection;", "connection", "Lokhttp3/internal/connection/RealConnection;", "getConnection$okhttp", "()Lokhttp3/internal/connection/RealConnection;", "Lokhttp3/EventListener;", "getEventListener$okhttp", "()Lokhttp3/EventListener;", "Lokhttp3/internal/connection/ExchangeFinder;", "getFinder$okhttp", "()Lokhttp3/internal/connection/ExchangeFinder;", "<set-?>", "hasFailure", "Z", "getHasFailure$okhttp", "()Z", "isCoalescedConnection$okhttp", "isCoalescedConnection", "isDuplex", "isDuplex$okhttp", "RequestBodySink", "ResponseBodySource", "okhttp"})
public final class Exchange {
  @NotNull
  private final RealCall call;
  
  @NotNull
  private final EventListener eventListener;
  
  @NotNull
  private final ExchangeFinder finder;
  
  @NotNull
  private final ExchangeCodec codec;
  
  private boolean isDuplex;
  
  private boolean hasFailure;
  
  @NotNull
  private final RealConnection connection;
  
  public Exchange(@NotNull RealCall call, @NotNull EventListener eventListener, @NotNull ExchangeFinder finder, @NotNull ExchangeCodec codec) {
    this.call = call;
    this.eventListener = eventListener;
    this.finder = finder;
    this.codec = codec;
    this.connection = this.codec.getConnection();
  }
  
  @NotNull
  public final RealCall getCall$okhttp() {
    return this.call;
  }
  
  @NotNull
  public final EventListener getEventListener$okhttp() {
    return this.eventListener;
  }
  
  @NotNull
  public final ExchangeFinder getFinder$okhttp() {
    return this.finder;
  }
  
  public final boolean isDuplex$okhttp() {
    return this.isDuplex;
  }
  
  public final boolean getHasFailure$okhttp() {
    return this.hasFailure;
  }
  
  @NotNull
  public final RealConnection getConnection$okhttp() {
    return this.connection;
  }
  
  public final boolean isCoalescedConnection$okhttp() {
    return !Intrinsics.areEqual(this.finder.getAddress$okhttp().url().host(), this.connection.route().address().url().host());
  }
  
  public final void writeRequestHeaders(@NotNull Request request) throws IOException {
    Intrinsics.checkNotNullParameter(request, "request");
    try {
      this.eventListener.requestHeadersStart(this.call);
      this.codec.writeRequestHeaders(request);
      this.eventListener.requestHeadersEnd(this.call, request);
    } catch (IOException e) {
      this.eventListener.requestFailed(this.call, e);
      trackFailure(e);
      throw e;
    } 
  }
  
  @NotNull
  public final Sink createRequestBody(@NotNull Request request, boolean duplex) throws IOException {
    Intrinsics.checkNotNullParameter(request, "request");
    this.isDuplex = duplex;
    Intrinsics.checkNotNull(request.body());
    long contentLength = request.body().contentLength();
    this.eventListener.requestBodyStart(this.call);
    Sink rawRequestBody = this.codec.createRequestBody(request, contentLength);
    return (Sink)new RequestBodySink(rawRequestBody, contentLength);
  }
  
  public final void flushRequest() throws IOException {
    try {
      this.codec.flushRequest();
    } catch (IOException e) {
      this.eventListener.requestFailed(this.call, e);
      trackFailure(e);
      throw e;
    } 
  }
  
  public final void finishRequest() throws IOException {
    try {
      this.codec.finishRequest();
    } catch (IOException e) {
      this.eventListener.requestFailed(this.call, e);
      trackFailure(e);
      throw e;
    } 
  }
  
  public final void responseHeadersStart() {
    this.eventListener.responseHeadersStart(this.call);
  }
  
  @Nullable
  public final Response.Builder readResponseHeaders(boolean expectContinue) throws IOException {
    try {
      Response.Builder result = this.codec.readResponseHeaders(expectContinue);
      if (result != null) {
        result.initExchange$okhttp(this);
      } else {
      
      } 
      return result;
    } catch (IOException e) {
      this.eventListener.responseFailed(this.call, e);
      trackFailure(e);
      throw e;
    } 
  }
  
  public final void responseHeadersEnd(@NotNull Response response) {
    Intrinsics.checkNotNullParameter(response, "response");
    this.eventListener.responseHeadersEnd(this.call, response);
  }
  
  @NotNull
  public final ResponseBody openResponseBody(@NotNull Response response) throws IOException {
    Intrinsics.checkNotNullParameter(response, "response");
    try {
      String contentType = Response.header$default(response, "Content-Type", null, 2, null);
      long contentLength = this.codec.reportedContentLength(response);
      Source rawSource = this.codec.openResponseBodySource(response);
      ResponseBodySource source = new ResponseBodySource(rawSource, contentLength);
      return (ResponseBody)new RealResponseBody(contentType, contentLength, Okio.buffer((Source)source));
    } catch (IOException e) {
      this.eventListener.responseFailed(this.call, e);
      trackFailure(e);
      throw e;
    } 
  }
  
  @NotNull
  public final Headers trailers() throws IOException {
    return this.codec.trailers();
  }
  
  @NotNull
  public final RealWebSocket.Streams newWebSocketStreams() throws SocketException {
    this.call.timeoutEarlyExit();
    return this.codec.getConnection().newWebSocketStreams$okhttp(this);
  }
  
  public final void webSocketUpgradeFailed() {
    bodyComplete(-1L, true, true, null);
  }
  
  public final void noNewExchangesOnConnection() {
    this.codec.getConnection().noNewExchanges$okhttp();
  }
  
  public final void cancel() {
    this.codec.cancel();
  }
  
  public final void detachWithViolence() {
    this.codec.cancel();
    this.call.messageDone$okhttp(this, true, true, null);
  }
  
  private final void trackFailure(IOException e) {
    this.hasFailure = true;
    this.finder.trackFailure(e);
    this.codec.getConnection().trackFailure$okhttp(this.call, e);
  }
  
  public final <E extends IOException> E bodyComplete(long bytesRead, boolean responseDone, boolean requestDone, IOException e) {
    if (e != null)
      trackFailure(e); 
    if (requestDone)
      if (e != null) {
        this.eventListener.requestFailed(this.call, e);
      } else {
        this.eventListener.requestBodyEnd(this.call, bytesRead);
      }  
    if (responseDone)
      if (e != null) {
        this.eventListener.responseFailed(this.call, e);
      } else {
        this.eventListener.responseBodyEnd(this.call, bytesRead);
      }  
    return this.call.messageDone$okhttp(this, requestDone, responseDone, (E)e);
  }
  
  public final void noRequestBody() {
    this.call.messageDone$okhttp(this, true, false, null);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0006\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\006\n\002\020\013\n\002\b\004\b\004\030\0002\0020\001B\027\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007J\017\020\t\032\0020\bH\026¢\006\004\b\t\020\nJ#\020\016\032\0028\000\"\n\b\000\020\f*\004\030\0010\0132\006\020\r\032\0028\000H\002¢\006\004\b\016\020\017J\017\020\020\032\0020\bH\026¢\006\004\b\020\020\nJ\037\020\024\032\0020\b2\006\020\022\032\0020\0212\006\020\023\032\0020\004H\026¢\006\004\b\024\020\025R\026\020\026\032\0020\0048\002@\002X\016¢\006\006\n\004\b\026\020\027R\026\020\031\032\0020\0308\002@\002X\016¢\006\006\n\004\b\031\020\032R\026\020\033\032\0020\0308\002@\002X\016¢\006\006\n\004\b\033\020\032R\024\020\005\032\0020\0048\002X\004¢\006\006\n\004\b\005\020\027¨\006\034"}, d2 = {"Lokhttp3/internal/connection/Exchange$RequestBodySink;", "Lokio/ForwardingSink;", "Lokio/Sink;", "delegate", "", "contentLength", "<init>", "(Lokhttp3/internal/connection/Exchange;Lokio/Sink;J)V", "", "close", "()V", "Ljava/io/IOException;", "E", "e", "complete", "(Ljava/io/IOException;)Ljava/io/IOException;", "flush", "Lokio/Buffer;", "source", "byteCount", "write", "(Lokio/Buffer;J)V", "bytesReceived", "J", "", "closed", "Z", "completed", "okhttp"})
  @SourceDebugExtension({"SMAP\nExchange.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Exchange.kt\nokhttp3/internal/connection/Exchange$RequestBodySink\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,333:1\n1#2:334\n*E\n"})
  private final class RequestBodySink extends ForwardingSink {
    private final long contentLength;
    
    private boolean completed;
    
    private long bytesReceived;
    
    private boolean closed;
    
    public RequestBodySink(Sink delegate, long contentLength) {
      super(delegate);
      this.contentLength = contentLength;
    }
    
    public void write(@NotNull Buffer source, long byteCount) throws IOException {
      Intrinsics.checkNotNullParameter(source, "source");
      if (!(!this.closed ? 1 : 0)) {
        int $i$a$-check-Exchange$RequestBodySink$write$1 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      if (this.contentLength != -1L && this.bytesReceived + byteCount > this.contentLength)
        throw new ProtocolException("expected " + this.contentLength + " bytes but received " + (this.bytesReceived + byteCount)); 
      try {
        super.write(source, byteCount);
        this.bytesReceived += byteCount;
      } catch (IOException e) {
        throw complete(e);
      } 
    }
    
    public void flush() throws IOException {
      try {
        super.flush();
      } catch (IOException e) {
        throw complete(e);
      } 
    }
    
    public void close() throws IOException {
      if (this.closed)
        return; 
      this.closed = true;
      if (this.contentLength != -1L && this.bytesReceived != this.contentLength)
        throw new ProtocolException("unexpected end of stream"); 
      try {
        super.close();
        complete(null);
      } catch (IOException e) {
        throw complete(e);
      } 
    }
    
    private final <E extends IOException> E complete(IOException e) {
      if (this.completed)
        return (E)e; 
      this.completed = true;
      return Exchange.this.bodyComplete(this.bytesReceived, false, true, (E)e);
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0006\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\006\n\002\020\013\n\002\b\005\b\004\030\0002\0020\001B\027\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007J\017\020\t\032\0020\bH\026¢\006\004\b\t\020\nJ!\020\016\032\0028\000\"\n\b\000\020\f*\004\030\0010\0132\006\020\r\032\0028\000¢\006\004\b\016\020\017J\037\020\023\032\0020\0042\006\020\021\032\0020\0202\006\020\022\032\0020\004H\026¢\006\004\b\023\020\024R\026\020\025\032\0020\0048\002@\002X\016¢\006\006\n\004\b\025\020\026R\026\020\030\032\0020\0278\002@\002X\016¢\006\006\n\004\b\030\020\031R\026\020\032\032\0020\0278\002@\002X\016¢\006\006\n\004\b\032\020\031R\024\020\005\032\0020\0048\002X\004¢\006\006\n\004\b\005\020\026R\026\020\033\032\0020\0278\002@\002X\016¢\006\006\n\004\b\033\020\031¨\006\034"}, d2 = {"Lokhttp3/internal/connection/Exchange$ResponseBodySource;", "Lokio/ForwardingSource;", "Lokio/Source;", "delegate", "", "contentLength", "<init>", "(Lokhttp3/internal/connection/Exchange;Lokio/Source;J)V", "", "close", "()V", "Ljava/io/IOException;", "E", "e", "complete", "(Ljava/io/IOException;)Ljava/io/IOException;", "Lokio/Buffer;", "sink", "byteCount", "read", "(Lokio/Buffer;J)J", "bytesReceived", "J", "", "closed", "Z", "completed", "invokeStartEvent", "okhttp"})
  @SourceDebugExtension({"SMAP\nExchange.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Exchange.kt\nokhttp3/internal/connection/Exchange$ResponseBodySource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,333:1\n1#2:334\n*E\n"})
  public final class ResponseBodySource extends ForwardingSource {
    private final long contentLength;
    
    private long bytesReceived;
    
    private boolean invokeStartEvent;
    
    private boolean completed;
    
    private boolean closed;
    
    public ResponseBodySource(Source delegate, long contentLength) {
      super(delegate);
      this.contentLength = contentLength;
      this.invokeStartEvent = true;
      if (this.contentLength == 0L)
        complete(null); 
    }
    
    public long read(@NotNull Buffer sink, long byteCount) throws IOException {
      Intrinsics.checkNotNullParameter(sink, "sink");
      if (!(!this.closed ? 1 : 0)) {
        int $i$a$-check-Exchange$ResponseBodySource$read$1 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      try {
        long read = delegate().read(sink, byteCount);
        if (this.invokeStartEvent) {
          this.invokeStartEvent = false;
          Exchange.this.getEventListener$okhttp().responseBodyStart(Exchange.this.getCall$okhttp());
        } 
        if (read == -1L) {
          complete(null);
          return -1L;
        } 
        long newBytesReceived = this.bytesReceived + read;
        if (this.contentLength != -1L && newBytesReceived > this.contentLength)
          throw new ProtocolException("expected " + this.contentLength + " bytes but received " + newBytesReceived); 
        this.bytesReceived = newBytesReceived;
        if (newBytesReceived == this.contentLength)
          complete(null); 
        return read;
      } catch (IOException e) {
        throw complete(e);
      } 
    }
    
    public void close() throws IOException {
      if (this.closed)
        return; 
      this.closed = true;
      try {
        super.close();
        complete(null);
      } catch (IOException e) {
        throw complete(e);
      } 
    }
    
    public final <E extends IOException> E complete(IOException e) {
      if (this.completed)
        return (E)e; 
      this.completed = true;
      if (e == null && this.invokeStartEvent) {
        this.invokeStartEvent = false;
        Exchange.this.getEventListener$okhttp().responseBodyStart(Exchange.this.getCall$okhttp());
      } 
      return Exchange.this.bodyComplete(this.bytesReceived, true, false, (E)e);
    }
  }
}
