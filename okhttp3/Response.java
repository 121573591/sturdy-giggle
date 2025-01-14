package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.connection.Exchange;
import okio.Buffer;
import okio.BufferedSource;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000p\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020\b\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\020 \n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\020\n\002\030\002\n\002\b\026\n\002\020\013\n\002\b\013\030\0002\0020\001:\001XB}\b\000\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\006\020\007\032\0020\006\022\006\020\t\032\0020\b\022\b\020\013\032\004\030\0010\n\022\006\020\r\032\0020\f\022\b\020\017\032\004\030\0010\016\022\b\020\020\032\004\030\0010\000\022\b\020\021\032\004\030\0010\000\022\b\020\022\032\004\030\0010\000\022\006\020\024\032\0020\023\022\006\020\025\032\0020\023\022\b\020\027\032\004\030\0010\026¢\006\004\b\030\020\031J\021\020\017\032\004\030\0010\016H\007¢\006\004\b\032\020\033J\017\020\037\032\0020\034H\007¢\006\004\b\035\020\036J\021\020\021\032\004\030\0010\000H\007¢\006\004\b \020!J\023\020$\032\b\022\004\022\0020#0\"¢\006\004\b$\020%J\017\020'\032\0020&H\026¢\006\004\b'\020(J\017\020\t\032\0020\bH\007¢\006\004\b)\020*J\021\020\013\032\004\030\0010\nH\007¢\006\004\b+\020,J%\020/\032\004\030\0010\0062\006\020-\032\0020\0062\n\b\002\020.\032\004\030\0010\006H\007¢\006\004\b/\0200J\017\020\r\032\0020\fH\007¢\006\004\b1\0202J\033\020\r\032\b\022\004\022\0020\0060\"2\006\020-\032\0020\006¢\006\004\b\r\0203J\017\020\007\032\0020\006H\007¢\006\004\b4\0205J\021\020\020\032\004\030\0010\000H\007¢\006\004\b6\020!J\r\0208\032\00207¢\006\004\b8\0209J\025\020;\032\0020\0162\006\020:\032\0020\023¢\006\004\b;\020<J\021\020\022\032\004\030\0010\000H\007¢\006\004\b=\020!J\017\020\005\032\0020\004H\007¢\006\004\b>\020?J\017\020\025\032\0020\023H\007¢\006\004\b@\020AJ\017\020\003\032\0020\002H\007¢\006\004\bB\020CJ\017\020\024\032\0020\023H\007¢\006\004\bD\020AJ\017\020E\032\0020\006H\026¢\006\004\bE\0205J\r\020F\032\0020\f¢\006\004\bF\0202R\031\020\017\032\004\030\0010\0168\007¢\006\f\n\004\b\017\020G\032\004\b\017\020\033R\021\020\037\032\0020\0348G¢\006\006\032\004\b\037\020\036R\031\020\021\032\004\030\0010\0008\007¢\006\f\n\004\b\021\020H\032\004\b\021\020!R\027\020\t\032\0020\b8\007¢\006\f\n\004\b\t\020I\032\004\b\t\020*R\034\020\027\032\004\030\0010\0268\001X\004¢\006\f\n\004\b\027\020J\032\004\b\027\020KR\031\020\013\032\004\030\0010\n8\007¢\006\f\n\004\b\013\020L\032\004\b\013\020,R\027\020\r\032\0020\f8\007¢\006\f\n\004\b\r\020M\032\004\b\r\0202R\021\020O\032\0020N8F¢\006\006\032\004\bO\020PR\021\020Q\032\0020N8F¢\006\006\032\004\bQ\020PR\030\020R\032\004\030\0010\0348\002@\002X\016¢\006\006\n\004\bR\020SR\027\020\007\032\0020\0068\007¢\006\f\n\004\b\007\020T\032\004\b\007\0205R\031\020\020\032\004\030\0010\0008\007¢\006\f\n\004\b\020\020H\032\004\b\020\020!R\031\020\022\032\004\030\0010\0008\007¢\006\f\n\004\b\022\020H\032\004\b\022\020!R\027\020\005\032\0020\0048\007¢\006\f\n\004\b\005\020U\032\004\b\005\020?R\027\020\025\032\0020\0238\007¢\006\f\n\004\b\025\020V\032\004\b\025\020AR\027\020\003\032\0020\0028\007¢\006\f\n\004\b\003\020W\032\004\b\003\020CR\027\020\024\032\0020\0238\007¢\006\f\n\004\b\024\020V\032\004\b\024\020A¨\006Y"}, d2 = {"Lokhttp3/Response;", "Ljava/io/Closeable;", "Lokhttp3/Request;", "request", "Lokhttp3/Protocol;", "protocol", "", "message", "", "code", "Lokhttp3/Handshake;", "handshake", "Lokhttp3/Headers;", "headers", "Lokhttp3/ResponseBody;", "body", "networkResponse", "cacheResponse", "priorResponse", "", "sentRequestAtMillis", "receivedResponseAtMillis", "Lokhttp3/internal/connection/Exchange;", "exchange", "<init>", "(Lokhttp3/Request;Lokhttp3/Protocol;Ljava/lang/String;ILokhttp3/Handshake;Lokhttp3/Headers;Lokhttp3/ResponseBody;Lokhttp3/Response;Lokhttp3/Response;Lokhttp3/Response;JJLokhttp3/internal/connection/Exchange;)V", "-deprecated_body", "()Lokhttp3/ResponseBody;", "Lokhttp3/CacheControl;", "-deprecated_cacheControl", "()Lokhttp3/CacheControl;", "cacheControl", "-deprecated_cacheResponse", "()Lokhttp3/Response;", "", "Lokhttp3/Challenge;", "challenges", "()Ljava/util/List;", "", "close", "()V", "-deprecated_code", "()I", "-deprecated_handshake", "()Lokhttp3/Handshake;", "name", "defaultValue", "header", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "-deprecated_headers", "()Lokhttp3/Headers;", "(Ljava/lang/String;)Ljava/util/List;", "-deprecated_message", "()Ljava/lang/String;", "-deprecated_networkResponse", "Lokhttp3/Response$Builder;", "newBuilder", "()Lokhttp3/Response$Builder;", "byteCount", "peekBody", "(J)Lokhttp3/ResponseBody;", "-deprecated_priorResponse", "-deprecated_protocol", "()Lokhttp3/Protocol;", "-deprecated_receivedResponseAtMillis", "()J", "-deprecated_request", "()Lokhttp3/Request;", "-deprecated_sentRequestAtMillis", "toString", "trailers", "Lokhttp3/ResponseBody;", "Lokhttp3/Response;", "I", "Lokhttp3/internal/connection/Exchange;", "()Lokhttp3/internal/connection/Exchange;", "Lokhttp3/Handshake;", "Lokhttp3/Headers;", "", "isRedirect", "()Z", "isSuccessful", "lazyCacheControl", "Lokhttp3/CacheControl;", "Ljava/lang/String;", "Lokhttp3/Protocol;", "J", "Lokhttp3/Request;", "Builder", "okhttp"})
@SourceDebugExtension({"SMAP\nResponse.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Response.kt\nokhttp3/Response\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,455:1\n1#2:456\n*E\n"})
public final class Response implements Closeable {
  @NotNull
  private final Request request;
  
  @NotNull
  private final Protocol protocol;
  
  @NotNull
  private final String message;
  
  private final int code;
  
  @Nullable
  private final Handshake handshake;
  
  @NotNull
  private final Headers headers;
  
  @Nullable
  private final ResponseBody body;
  
  @Nullable
  private final Response networkResponse;
  
  @Nullable
  private final Response cacheResponse;
  
  @Nullable
  private final Response priorResponse;
  
  private final long sentRequestAtMillis;
  
  private final long receivedResponseAtMillis;
  
  @Nullable
  private final Exchange exchange;
  
  @Nullable
  private CacheControl lazyCacheControl;
  
  public Response(@NotNull Request request, @NotNull Protocol protocol, @NotNull String message, int code, @Nullable Handshake handshake, @NotNull Headers headers, @Nullable ResponseBody body, @Nullable Response networkResponse, @Nullable Response cacheResponse, @Nullable Response priorResponse, long sentRequestAtMillis, long receivedResponseAtMillis, @Nullable Exchange exchange) {
    this.request = request;
    this.protocol = protocol;
    this.message = message;
    this.code = code;
    this.handshake = handshake;
    this.headers = headers;
    this.body = body;
    this.networkResponse = networkResponse;
    this.cacheResponse = cacheResponse;
    this.priorResponse = priorResponse;
    this.sentRequestAtMillis = sentRequestAtMillis;
    this.receivedResponseAtMillis = receivedResponseAtMillis;
    this.exchange = exchange;
  }
  
  @JvmName(name = "request")
  @NotNull
  public final Request request() {
    return this.request;
  }
  
  @JvmName(name = "protocol")
  @NotNull
  public final Protocol protocol() {
    return this.protocol;
  }
  
  @JvmName(name = "message")
  @NotNull
  public final String message() {
    return this.message;
  }
  
  @JvmName(name = "code")
  public final int code() {
    return this.code;
  }
  
  @JvmName(name = "handshake")
  @Nullable
  public final Handshake handshake() {
    return this.handshake;
  }
  
  @JvmName(name = "headers")
  @NotNull
  public final Headers headers() {
    return this.headers;
  }
  
  @JvmName(name = "body")
  @Nullable
  public final ResponseBody body() {
    return this.body;
  }
  
  @JvmName(name = "networkResponse")
  @Nullable
  public final Response networkResponse() {
    return this.networkResponse;
  }
  
  @JvmName(name = "cacheResponse")
  @Nullable
  public final Response cacheResponse() {
    return this.cacheResponse;
  }
  
  @JvmName(name = "priorResponse")
  @Nullable
  public final Response priorResponse() {
    return this.priorResponse;
  }
  
  @JvmName(name = "sentRequestAtMillis")
  public final long sentRequestAtMillis() {
    return this.sentRequestAtMillis;
  }
  
  @JvmName(name = "receivedResponseAtMillis")
  public final long receivedResponseAtMillis() {
    return this.receivedResponseAtMillis;
  }
  
  @JvmName(name = "exchange")
  @Nullable
  public final Exchange exchange() {
    return this.exchange;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "request", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_request")
  @NotNull
  public final Request -deprecated_request() {
    return this.request;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "protocol", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_protocol")
  @NotNull
  public final Protocol -deprecated_protocol() {
    return this.protocol;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "code", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_code")
  public final int -deprecated_code() {
    return this.code;
  }
  
  public final boolean isSuccessful() {
    int i = this.code;
    return (200 <= i) ? ((i < 300)) : false;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "message", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_message")
  @NotNull
  public final String -deprecated_message() {
    return this.message;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "handshake", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_handshake")
  @Nullable
  public final Handshake -deprecated_handshake() {
    return this.handshake;
  }
  
  @NotNull
  public final List<String> headers(@NotNull String name) {
    Intrinsics.checkNotNullParameter(name, "name");
    return this.headers.values(name);
  }
  
  @JvmOverloads
  @Nullable
  public final String header(@NotNull String name, @Nullable String defaultValue) {
    Intrinsics.checkNotNullParameter(name, "name");
    if (this.headers.get(name) == null)
      this.headers.get(name); 
    return defaultValue;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "headers", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_headers")
  @NotNull
  public final Headers -deprecated_headers() {
    return this.headers;
  }
  
  @NotNull
  public final Headers trailers() throws IOException {
    if (this.exchange == null) {
      int $i$a$-checkNotNull-Response$trailers$1 = 0;
      String str = "trailers not available";
      throw new IllegalStateException(str.toString());
    } 
    return this.exchange.trailers();
  }
  
  @NotNull
  public final ResponseBody peekBody(long byteCount) throws IOException {
    Intrinsics.checkNotNull(this.body);
    BufferedSource peeked = this.body.source().peek();
    Buffer buffer = new Buffer();
    peeked.request(byteCount);
    buffer.write((Source)peeked, Math.min(byteCount, peeked.getBuffer().size()));
    return ResponseBody.Companion.create((BufferedSource)buffer, this.body.contentType(), buffer.size());
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "body", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_body")
  @Nullable
  public final ResponseBody -deprecated_body() {
    return this.body;
  }
  
  @NotNull
  public final Builder newBuilder() {
    return new Builder(this);
  }
  
  public final boolean isRedirect() {
    switch (this.code) {
      case 300:
      case 301:
      case 302:
      case 303:
      case 307:
      case 308:
      
    } 
    return false;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "networkResponse", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_networkResponse")
  @Nullable
  public final Response -deprecated_networkResponse() {
    return this.networkResponse;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "cacheResponse", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_cacheResponse")
  @Nullable
  public final Response -deprecated_cacheResponse() {
    return this.cacheResponse;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "priorResponse", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_priorResponse")
  @Nullable
  public final Response -deprecated_priorResponse() {
    return this.priorResponse;
  }
  
  @NotNull
  public final List<Challenge> challenges() {
    switch (this.code) {
      case 401:
      
      case 407:
      
    } 
    return CollectionsKt.emptyList();
  }
  
  @JvmName(name = "cacheControl")
  @NotNull
  public final CacheControl cacheControl() {
    CacheControl result = this.lazyCacheControl;
    if (result == null) {
      result = CacheControl.Companion.parse(this.headers);
      this.lazyCacheControl = result;
    } 
    return result;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "cacheControl", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_cacheControl")
  @NotNull
  public final CacheControl -deprecated_cacheControl() {
    return cacheControl();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "sentRequestAtMillis", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_sentRequestAtMillis")
  public final long -deprecated_sentRequestAtMillis() {
    return this.sentRequestAtMillis;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "receivedResponseAtMillis", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_receivedResponseAtMillis")
  public final long -deprecated_receivedResponseAtMillis() {
    return this.receivedResponseAtMillis;
  }
  
  public void close() {
    if (this.body == null) {
      int $i$a$-checkNotNull-Response$close$1 = 0;
      String str = "response is not eligible for a body and must not be closed";
      throw new IllegalStateException(str.toString());
    } 
    this.body.close();
  }
  
  @NotNull
  public String toString() {
    return "Response{protocol=" + this.protocol + ", code=" + this.code + ", message=" + this.message + ", url=" + this.request.url() + '}';
  }
  
  @JvmOverloads
  @Nullable
  public final String header(@NotNull String name) {
    Intrinsics.checkNotNullParameter(name, "name");
    return header$default(this, name, null, 2, null);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000l\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\004\n\002\030\002\n\002\b\006\n\002\020\002\n\002\b\003\n\002\020\b\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\b\n\002\030\002\n\002\b\002\n\002\020\t\n\002\b\003\n\002\030\002\n\002\b\032\n\002\030\002\n\002\b \b\026\030\0002\0020\001B\t\b\026¢\006\004\b\002\020\003B\021\b\020\022\006\020\005\032\0020\004¢\006\004\b\002\020\006J\037\020\n\032\0020\0002\006\020\b\032\0020\0072\006\020\t\032\0020\007H\026¢\006\004\b\n\020\013J\031\020\r\032\0020\0002\b\020\r\032\004\030\0010\fH\026¢\006\004\b\r\020\016J\017\020\017\032\0020\004H\026¢\006\004\b\017\020\020J\031\020\021\032\0020\0002\b\020\021\032\004\030\0010\004H\026¢\006\004\b\021\020\022J\031\020\024\032\0020\0232\b\020\005\032\004\030\0010\004H\002¢\006\004\b\024\020\006J!\020\025\032\0020\0232\006\020\b\032\0020\0072\b\020\005\032\004\030\0010\004H\002¢\006\004\b\025\020\026J\027\020\030\032\0020\0002\006\020\030\032\0020\027H\026¢\006\004\b\030\020\031J\031\020\033\032\0020\0002\b\020\033\032\004\030\0010\032H\026¢\006\004\b\033\020\034J\037\020\035\032\0020\0002\006\020\b\032\0020\0072\006\020\t\032\0020\007H\026¢\006\004\b\035\020\013J\027\020\037\032\0020\0002\006\020\037\032\0020\036H\026¢\006\004\b\037\020 J\027\020%\032\0020\0232\006\020\"\032\0020!H\000¢\006\004\b#\020$J\027\020&\032\0020\0002\006\020&\032\0020\007H\026¢\006\004\b&\020'J\031\020(\032\0020\0002\b\020(\032\004\030\0010\004H\026¢\006\004\b(\020\022J\031\020)\032\0020\0002\b\020)\032\004\030\0010\004H\026¢\006\004\b)\020\022J\027\020+\032\0020\0002\006\020+\032\0020*H\026¢\006\004\b+\020,J\027\020.\032\0020\0002\006\020.\032\0020-H\026¢\006\004\b.\020/J\027\0200\032\0020\0002\006\020\b\032\0020\007H\026¢\006\004\b0\020'J\027\0202\032\0020\0002\006\0202\032\00201H\026¢\006\004\b2\0203J\027\0204\032\0020\0002\006\0204\032\0020-H\026¢\006\004\b4\020/R$\020\r\032\004\030\0010\f8\000@\000X\016¢\006\022\n\004\b\r\0205\032\004\b6\0207\"\004\b8\0209R$\020\021\032\004\030\0010\0048\000@\000X\016¢\006\022\n\004\b\021\020:\032\004\b;\020\020\"\004\b<\020\006R\"\020\030\032\0020\0278\000@\000X\016¢\006\022\n\004\b\030\020=\032\004\b>\020?\"\004\b@\020AR$\020B\032\004\030\0010!8\000@\000X\016¢\006\022\n\004\bB\020C\032\004\bD\020E\"\004\bF\020$R$\020\033\032\004\030\0010\0328\000@\000X\016¢\006\022\n\004\b\033\020G\032\004\bH\020I\"\004\bJ\020KR\"\020\037\032\0020L8\000@\000X\016¢\006\022\n\004\b\037\020M\032\004\bN\020O\"\004\bP\020QR$\020&\032\004\030\0010\0078\000@\000X\016¢\006\022\n\004\b&\020R\032\004\bS\020T\"\004\bU\020VR$\020(\032\004\030\0010\0048\000@\000X\016¢\006\022\n\004\b(\020:\032\004\bW\020\020\"\004\bX\020\006R$\020)\032\004\030\0010\0048\000@\000X\016¢\006\022\n\004\b)\020:\032\004\bY\020\020\"\004\bZ\020\006R$\020+\032\004\030\0010*8\000@\000X\016¢\006\022\n\004\b+\020[\032\004\b\\\020]\"\004\b^\020_R\"\020.\032\0020-8\000@\000X\016¢\006\022\n\004\b.\020`\032\004\ba\020b\"\004\bc\020dR$\0202\032\004\030\001018\000@\000X\016¢\006\022\n\004\b2\020e\032\004\bf\020g\"\004\bh\020iR\"\0204\032\0020-8\000@\000X\016¢\006\022\n\004\b4\020`\032\004\bj\020b\"\004\bk\020d¨\006l"}, d2 = {"Lokhttp3/Response$Builder;", "", "<init>", "()V", "Lokhttp3/Response;", "response", "(Lokhttp3/Response;)V", "", "name", "value", "addHeader", "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Response$Builder;", "Lokhttp3/ResponseBody;", "body", "(Lokhttp3/ResponseBody;)Lokhttp3/Response$Builder;", "build", "()Lokhttp3/Response;", "cacheResponse", "(Lokhttp3/Response;)Lokhttp3/Response$Builder;", "", "checkPriorResponse", "checkSupportResponse", "(Ljava/lang/String;Lokhttp3/Response;)V", "", "code", "(I)Lokhttp3/Response$Builder;", "Lokhttp3/Handshake;", "handshake", "(Lokhttp3/Handshake;)Lokhttp3/Response$Builder;", "header", "Lokhttp3/Headers;", "headers", "(Lokhttp3/Headers;)Lokhttp3/Response$Builder;", "Lokhttp3/internal/connection/Exchange;", "deferredTrailers", "initExchange$okhttp", "(Lokhttp3/internal/connection/Exchange;)V", "initExchange", "message", "(Ljava/lang/String;)Lokhttp3/Response$Builder;", "networkResponse", "priorResponse", "Lokhttp3/Protocol;", "protocol", "(Lokhttp3/Protocol;)Lokhttp3/Response$Builder;", "", "receivedResponseAtMillis", "(J)Lokhttp3/Response$Builder;", "removeHeader", "Lokhttp3/Request;", "request", "(Lokhttp3/Request;)Lokhttp3/Response$Builder;", "sentRequestAtMillis", "Lokhttp3/ResponseBody;", "getBody$okhttp", "()Lokhttp3/ResponseBody;", "setBody$okhttp", "(Lokhttp3/ResponseBody;)V", "Lokhttp3/Response;", "getCacheResponse$okhttp", "setCacheResponse$okhttp", "I", "getCode$okhttp", "()I", "setCode$okhttp", "(I)V", "exchange", "Lokhttp3/internal/connection/Exchange;", "getExchange$okhttp", "()Lokhttp3/internal/connection/Exchange;", "setExchange$okhttp", "Lokhttp3/Handshake;", "getHandshake$okhttp", "()Lokhttp3/Handshake;", "setHandshake$okhttp", "(Lokhttp3/Handshake;)V", "Lokhttp3/Headers$Builder;", "Lokhttp3/Headers$Builder;", "getHeaders$okhttp", "()Lokhttp3/Headers$Builder;", "setHeaders$okhttp", "(Lokhttp3/Headers$Builder;)V", "Ljava/lang/String;", "getMessage$okhttp", "()Ljava/lang/String;", "setMessage$okhttp", "(Ljava/lang/String;)V", "getNetworkResponse$okhttp", "setNetworkResponse$okhttp", "getPriorResponse$okhttp", "setPriorResponse$okhttp", "Lokhttp3/Protocol;", "getProtocol$okhttp", "()Lokhttp3/Protocol;", "setProtocol$okhttp", "(Lokhttp3/Protocol;)V", "J", "getReceivedResponseAtMillis$okhttp", "()J", "setReceivedResponseAtMillis$okhttp", "(J)V", "Lokhttp3/Request;", "getRequest$okhttp", "()Lokhttp3/Request;", "setRequest$okhttp", "(Lokhttp3/Request;)V", "getSentRequestAtMillis$okhttp", "setSentRequestAtMillis$okhttp", "okhttp"})
  @SourceDebugExtension({"SMAP\nResponse.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Response.kt\nokhttp3/Response$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,455:1\n1#2:456\n*E\n"})
  public static class Builder {
    @Nullable
    private Request request;
    
    @Nullable
    private Protocol protocol;
    
    @Nullable
    public final Request getRequest$okhttp() {
      return this.request;
    }
    
    public final void setRequest$okhttp(@Nullable Request <set-?>) {
      this.request = <set-?>;
    }
    
    @Nullable
    public final Protocol getProtocol$okhttp() {
      return this.protocol;
    }
    
    public final void setProtocol$okhttp(@Nullable Protocol <set-?>) {
      this.protocol = <set-?>;
    }
    
    private int code = -1;
    
    @Nullable
    private String message;
    
    @Nullable
    private Handshake handshake;
    
    @NotNull
    private Headers.Builder headers;
    
    @Nullable
    private ResponseBody body;
    
    @Nullable
    private Response networkResponse;
    
    @Nullable
    private Response cacheResponse;
    
    @Nullable
    private Response priorResponse;
    
    private long sentRequestAtMillis;
    
    private long receivedResponseAtMillis;
    
    @Nullable
    private Exchange exchange;
    
    public final int getCode$okhttp() {
      return this.code;
    }
    
    public final void setCode$okhttp(int <set-?>) {
      this.code = <set-?>;
    }
    
    @Nullable
    public final String getMessage$okhttp() {
      return this.message;
    }
    
    public final void setMessage$okhttp(@Nullable String <set-?>) {
      this.message = <set-?>;
    }
    
    @Nullable
    public final Handshake getHandshake$okhttp() {
      return this.handshake;
    }
    
    public final void setHandshake$okhttp(@Nullable Handshake <set-?>) {
      this.handshake = <set-?>;
    }
    
    @NotNull
    public final Headers.Builder getHeaders$okhttp() {
      return this.headers;
    }
    
    public final void setHeaders$okhttp(@NotNull Headers.Builder <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.headers = <set-?>;
    }
    
    @Nullable
    public final ResponseBody getBody$okhttp() {
      return this.body;
    }
    
    public final void setBody$okhttp(@Nullable ResponseBody <set-?>) {
      this.body = <set-?>;
    }
    
    @Nullable
    public final Response getNetworkResponse$okhttp() {
      return this.networkResponse;
    }
    
    public final void setNetworkResponse$okhttp(@Nullable Response <set-?>) {
      this.networkResponse = <set-?>;
    }
    
    @Nullable
    public final Response getCacheResponse$okhttp() {
      return this.cacheResponse;
    }
    
    public final void setCacheResponse$okhttp(@Nullable Response <set-?>) {
      this.cacheResponse = <set-?>;
    }
    
    @Nullable
    public final Response getPriorResponse$okhttp() {
      return this.priorResponse;
    }
    
    public final void setPriorResponse$okhttp(@Nullable Response <set-?>) {
      this.priorResponse = <set-?>;
    }
    
    public final long getSentRequestAtMillis$okhttp() {
      return this.sentRequestAtMillis;
    }
    
    public final void setSentRequestAtMillis$okhttp(long <set-?>) {
      this.sentRequestAtMillis = <set-?>;
    }
    
    public final long getReceivedResponseAtMillis$okhttp() {
      return this.receivedResponseAtMillis;
    }
    
    public final void setReceivedResponseAtMillis$okhttp(long <set-?>) {
      this.receivedResponseAtMillis = <set-?>;
    }
    
    @Nullable
    public final Exchange getExchange$okhttp() {
      return this.exchange;
    }
    
    public final void setExchange$okhttp(@Nullable Exchange <set-?>) {
      this.exchange = <set-?>;
    }
    
    public Builder() {
      this.headers = new Headers.Builder();
    }
    
    public Builder(@NotNull Response response) {
      this.request = response.request();
      this.protocol = response.protocol();
      this.code = response.code();
      this.message = response.message();
      this.handshake = response.handshake();
      this.headers = response.headers().newBuilder();
      this.body = response.body();
      this.networkResponse = response.networkResponse();
      this.cacheResponse = response.cacheResponse();
      this.priorResponse = response.priorResponse();
      this.sentRequestAtMillis = response.sentRequestAtMillis();
      this.receivedResponseAtMillis = response.receivedResponseAtMillis();
      this.exchange = response.exchange();
    }
    
    @NotNull
    public Builder request(@NotNull Request request) {
      Intrinsics.checkNotNullParameter(request, "request");
      Builder builder1 = this, $this$request_u24lambda_u240 = builder1;
      int $i$a$-apply-Response$Builder$request$1 = 0;
      $this$request_u24lambda_u240.request = request;
      return builder1;
    }
    
    @NotNull
    public Builder protocol(@NotNull Protocol protocol) {
      Intrinsics.checkNotNullParameter(protocol, "protocol");
      Builder builder1 = this, $this$protocol_u24lambda_u241 = builder1;
      int $i$a$-apply-Response$Builder$protocol$1 = 0;
      $this$protocol_u24lambda_u241.protocol = protocol;
      return builder1;
    }
    
    @NotNull
    public Builder code(int code) {
      Builder builder1 = this, $this$code_u24lambda_u242 = builder1;
      int $i$a$-apply-Response$Builder$code$1 = 0;
      $this$code_u24lambda_u242.code = code;
      return builder1;
    }
    
    @NotNull
    public Builder message(@NotNull String message) {
      Intrinsics.checkNotNullParameter(message, "message");
      Builder builder1 = this, $this$message_u24lambda_u243 = builder1;
      int $i$a$-apply-Response$Builder$message$1 = 0;
      $this$message_u24lambda_u243.message = message;
      return builder1;
    }
    
    @NotNull
    public Builder handshake(@Nullable Handshake handshake) {
      Builder builder1 = this, $this$handshake_u24lambda_u244 = builder1;
      int $i$a$-apply-Response$Builder$handshake$1 = 0;
      $this$handshake_u24lambda_u244.handshake = handshake;
      return builder1;
    }
    
    @NotNull
    public Builder header(@NotNull String name, @NotNull String value) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(value, "value");
      Builder builder1 = this, $this$header_u24lambda_u245 = builder1;
      int $i$a$-apply-Response$Builder$header$1 = 0;
      $this$header_u24lambda_u245.headers.set(name, value);
      return builder1;
    }
    
    @NotNull
    public Builder addHeader(@NotNull String name, @NotNull String value) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(value, "value");
      Builder builder1 = this, $this$addHeader_u24lambda_u246 = builder1;
      int $i$a$-apply-Response$Builder$addHeader$1 = 0;
      $this$addHeader_u24lambda_u246.headers.add(name, value);
      return builder1;
    }
    
    @NotNull
    public Builder removeHeader(@NotNull String name) {
      Intrinsics.checkNotNullParameter(name, "name");
      Builder builder1 = this, $this$removeHeader_u24lambda_u247 = builder1;
      int $i$a$-apply-Response$Builder$removeHeader$1 = 0;
      $this$removeHeader_u24lambda_u247.headers.removeAll(name);
      return builder1;
    }
    
    @NotNull
    public Builder headers(@NotNull Headers headers) {
      Intrinsics.checkNotNullParameter(headers, "headers");
      Builder builder1 = this, $this$headers_u24lambda_u248 = builder1;
      int $i$a$-apply-Response$Builder$headers$1 = 0;
      $this$headers_u24lambda_u248.headers = headers.newBuilder();
      return builder1;
    }
    
    @NotNull
    public Builder body(@Nullable ResponseBody body) {
      Builder builder1 = this, $this$body_u24lambda_u249 = builder1;
      int $i$a$-apply-Response$Builder$body$1 = 0;
      $this$body_u24lambda_u249.body = body;
      return builder1;
    }
    
    @NotNull
    public Builder networkResponse(@Nullable Response networkResponse) {
      Builder builder1 = this, $this$networkResponse_u24lambda_u2410 = builder1;
      int $i$a$-apply-Response$Builder$networkResponse$1 = 0;
      $this$networkResponse_u24lambda_u2410.checkSupportResponse("networkResponse", networkResponse);
      $this$networkResponse_u24lambda_u2410.networkResponse = networkResponse;
      return builder1;
    }
    
    @NotNull
    public Builder cacheResponse(@Nullable Response cacheResponse) {
      Builder builder1 = this, $this$cacheResponse_u24lambda_u2411 = builder1;
      int $i$a$-apply-Response$Builder$cacheResponse$1 = 0;
      $this$cacheResponse_u24lambda_u2411.checkSupportResponse("cacheResponse", cacheResponse);
      $this$cacheResponse_u24lambda_u2411.cacheResponse = cacheResponse;
      return builder1;
    }
    
    private final void checkSupportResponse(String name, Response response) {
      Response response1 = response, $this$checkSupportResponse_u24lambda_u2416 = response1;
      int $i$a$-apply-Response$Builder$checkSupportResponse$1 = 0;
      if (!(($this$checkSupportResponse_u24lambda_u2416.body() == null) ? 1 : 0)) {
        int $i$a$-require-Response$Builder$checkSupportResponse$1$1 = 0;
        String str = name + ".body != null";
        throw new IllegalArgumentException(str.toString());
      } 
      if (!(($this$checkSupportResponse_u24lambda_u2416.networkResponse() == null) ? 1 : 0)) {
        int $i$a$-require-Response$Builder$checkSupportResponse$1$2 = 0;
        String str = name + ".networkResponse != null";
        throw new IllegalArgumentException(str.toString());
      } 
      if (!(($this$checkSupportResponse_u24lambda_u2416.cacheResponse() == null) ? 1 : 0)) {
        int $i$a$-require-Response$Builder$checkSupportResponse$1$3 = 0;
        String str = name + ".cacheResponse != null";
        throw new IllegalArgumentException(str.toString());
      } 
      if (!(($this$checkSupportResponse_u24lambda_u2416.priorResponse() == null) ? 1 : 0)) {
        int $i$a$-require-Response$Builder$checkSupportResponse$1$4 = 0;
        String str = name + ".priorResponse != null";
        throw new IllegalArgumentException(str.toString());
      } 
    }
    
    @NotNull
    public Builder priorResponse(@Nullable Response priorResponse) {
      Builder builder1 = this, $this$priorResponse_u24lambda_u2417 = builder1;
      int $i$a$-apply-Response$Builder$priorResponse$1 = 0;
      $this$priorResponse_u24lambda_u2417.checkPriorResponse(priorResponse);
      $this$priorResponse_u24lambda_u2417.priorResponse = priorResponse;
      return builder1;
    }
    
    private final void checkPriorResponse(Response response) {
      Response response1 = response, $this$checkPriorResponse_u24lambda_u2419 = response1;
      int $i$a$-apply-Response$Builder$checkPriorResponse$1 = 0;
      if (!(($this$checkPriorResponse_u24lambda_u2419.body() == null) ? 1 : 0)) {
        int $i$a$-require-Response$Builder$checkPriorResponse$1$1 = 0;
        String str = "priorResponse.body != null";
        throw new IllegalArgumentException(str.toString());
      } 
    }
    
    @NotNull
    public Builder sentRequestAtMillis(long sentRequestAtMillis) {
      Builder builder1 = this, $this$sentRequestAtMillis_u24lambda_u2420 = builder1;
      int $i$a$-apply-Response$Builder$sentRequestAtMillis$1 = 0;
      $this$sentRequestAtMillis_u24lambda_u2420.sentRequestAtMillis = sentRequestAtMillis;
      return builder1;
    }
    
    @NotNull
    public Builder receivedResponseAtMillis(long receivedResponseAtMillis) {
      Builder builder1 = this, $this$receivedResponseAtMillis_u24lambda_u2421 = builder1;
      int $i$a$-apply-Response$Builder$receivedResponseAtMillis$1 = 0;
      $this$receivedResponseAtMillis_u24lambda_u2421.receivedResponseAtMillis = receivedResponseAtMillis;
      return builder1;
    }
    
    public final void initExchange$okhttp(@NotNull Exchange deferredTrailers) {
      Intrinsics.checkNotNullParameter(deferredTrailers, "deferredTrailers");
      this.exchange = deferredTrailers;
    }
    
    @NotNull
    public Response build() {
      Protocol protocol;
      if (!((this.code >= 0) ? 1 : 0)) {
        int $i$a$-check-Response$Builder$build$1 = 0;
        String str = "code < 0: " + this.code;
        throw new IllegalStateException(str.toString());
      } 
      if (this.request == null) {
        int $i$a$-checkNotNull-Response$Builder$build$2 = 0;
        String str = "request == null";
        throw new IllegalStateException(str.toString());
      } 
      if (this.protocol == null) {
        Protocol protocol1 = this.protocol;
        int $i$a$-checkNotNull-Response$Builder$build$3 = 0;
        String str = "protocol == null";
        throw new IllegalStateException(str.toString());
      } 
      if (this.message == null) {
        Protocol protocol1;
        String str4 = this.message;
        int $i$a$-checkNotNull-Response$Builder$build$4 = 0;
        String str5 = "message == null", str3 = str5;
        throw new IllegalStateException(str3.toString());
      } 
      Exchange exchange = this.exchange;
      long l1 = this.receivedResponseAtMillis, l2 = this.sentRequestAtMillis;
      Response response1 = this.priorResponse, response2 = this.cacheResponse, response3 = this.networkResponse;
      ResponseBody responseBody = this.body;
      Headers headers = this.headers.build();
      Handshake handshake = this.handshake;
      int i = this.code;
      String str1 = this.message, str2 = str1;
      return new Response((Request)protocol, (Protocol)str2, str1, i, handshake, headers, responseBody, response3, response2, response1, l2, l1, exchange);
    }
  }
}
