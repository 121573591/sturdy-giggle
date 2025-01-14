package okhttp3.internal.http;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000L\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\000\n\002\020\b\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\024\030\0002\0020\001BO\022\006\020\003\032\0020\002\022\f\020\006\032\b\022\004\022\0020\0050\004\022\006\020\b\032\0020\007\022\b\020\n\032\004\030\0010\t\022\006\020\f\032\0020\013\022\006\020\r\032\0020\007\022\006\020\016\032\0020\007\022\006\020\017\032\0020\007¢\006\004\b\020\020\021J\017\020\003\032\0020\022H\026¢\006\004\b\003\020\023J\017\020\r\032\0020\007H\026¢\006\004\b\r\020\024J\021\020\026\032\004\030\0010\025H\026¢\006\004\b\026\020\027JM\020\032\032\0020\0002\b\b\002\020\b\032\0020\0072\n\b\002\020\n\032\004\030\0010\t2\b\b\002\020\f\032\0020\0132\b\b\002\020\r\032\0020\0072\b\b\002\020\016\032\0020\0072\b\b\002\020\017\032\0020\007H\000¢\006\004\b\030\020\031J\027\020\034\032\0020\0332\006\020\f\032\0020\013H\026¢\006\004\b\034\020\035J\017\020\016\032\0020\007H\026¢\006\004\b\016\020\024J\017\020\f\032\0020\013H\026¢\006\004\b\f\020\036J\037\020\"\032\0020\0012\006\020\037\032\0020\0072\006\020!\032\0020 H\026¢\006\004\b\"\020#J\037\020$\032\0020\0012\006\020\037\032\0020\0072\006\020!\032\0020 H\026¢\006\004\b$\020#J\037\020%\032\0020\0012\006\020\037\032\0020\0072\006\020!\032\0020 H\026¢\006\004\b%\020#J\017\020\017\032\0020\007H\026¢\006\004\b\017\020\024R\032\020\003\032\0020\0028\000X\004¢\006\f\n\004\b\003\020&\032\004\b'\020(R\026\020)\032\0020\0078\002@\002X\016¢\006\006\n\004\b)\020*R\032\020\r\032\0020\0078\000X\004¢\006\f\n\004\b\r\020*\032\004\b+\020\024R\034\020\n\032\004\030\0010\t8\000X\004¢\006\f\n\004\b\n\020,\032\004\b-\020.R\024\020\b\032\0020\0078\002X\004¢\006\006\n\004\b\b\020*R\032\020\006\032\b\022\004\022\0020\0050\0048\002X\004¢\006\006\n\004\b\006\020/R\032\020\016\032\0020\0078\000X\004¢\006\f\n\004\b\016\020*\032\004\b0\020\024R\032\020\f\032\0020\0138\000X\004¢\006\f\n\004\b\f\0201\032\004\b2\020\036R\032\020\017\032\0020\0078\000X\004¢\006\f\n\004\b\017\020*\032\004\b3\020\024¨\0064"}, d2 = {"Lokhttp3/internal/http/RealInterceptorChain;", "Lokhttp3/Interceptor$Chain;", "Lokhttp3/internal/connection/RealCall;", "call", "", "Lokhttp3/Interceptor;", "interceptors", "", "index", "Lokhttp3/internal/connection/Exchange;", "exchange", "Lokhttp3/Request;", "request", "connectTimeoutMillis", "readTimeoutMillis", "writeTimeoutMillis", "<init>", "(Lokhttp3/internal/connection/RealCall;Ljava/util/List;ILokhttp3/internal/connection/Exchange;Lokhttp3/Request;III)V", "Lokhttp3/Call;", "()Lokhttp3/Call;", "()I", "Lokhttp3/Connection;", "connection", "()Lokhttp3/Connection;", "copy$okhttp", "(ILokhttp3/internal/connection/Exchange;Lokhttp3/Request;III)Lokhttp3/internal/http/RealInterceptorChain;", "copy", "Lokhttp3/Response;", "proceed", "(Lokhttp3/Request;)Lokhttp3/Response;", "()Lokhttp3/Request;", "timeout", "Ljava/util/concurrent/TimeUnit;", "unit", "withConnectTimeout", "(ILjava/util/concurrent/TimeUnit;)Lokhttp3/Interceptor$Chain;", "withReadTimeout", "withWriteTimeout", "Lokhttp3/internal/connection/RealCall;", "getCall$okhttp", "()Lokhttp3/internal/connection/RealCall;", "calls", "I", "getConnectTimeoutMillis$okhttp", "Lokhttp3/internal/connection/Exchange;", "getExchange$okhttp", "()Lokhttp3/internal/connection/Exchange;", "Ljava/util/List;", "getReadTimeoutMillis$okhttp", "Lokhttp3/Request;", "getRequest$okhttp", "getWriteTimeoutMillis$okhttp", "okhttp"})
@SourceDebugExtension({"SMAP\nRealInterceptorChain.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealInterceptorChain.kt\nokhttp3/internal/http/RealInterceptorChain\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,123:1\n1#2:124\n*E\n"})
public final class RealInterceptorChain implements Interceptor.Chain {
  @NotNull
  private final RealCall call;
  
  @NotNull
  private final List<Interceptor> interceptors;
  
  private final int index;
  
  @Nullable
  private final Exchange exchange;
  
  @NotNull
  private final Request request;
  
  private final int connectTimeoutMillis;
  
  private final int readTimeoutMillis;
  
  private final int writeTimeoutMillis;
  
  private int calls;
  
  public RealInterceptorChain(@NotNull RealCall call, @NotNull List<Interceptor> interceptors, int index, @Nullable Exchange exchange, @NotNull Request request, int connectTimeoutMillis, int readTimeoutMillis, int writeTimeoutMillis) {
    this.call = call;
    this.interceptors = interceptors;
    this.index = index;
    this.exchange = exchange;
    this.request = request;
    this.connectTimeoutMillis = connectTimeoutMillis;
    this.readTimeoutMillis = readTimeoutMillis;
    this.writeTimeoutMillis = writeTimeoutMillis;
  }
  
  @NotNull
  public final RealCall getCall$okhttp() {
    return this.call;
  }
  
  @Nullable
  public final Exchange getExchange$okhttp() {
    return this.exchange;
  }
  
  @NotNull
  public final Request getRequest$okhttp() {
    return this.request;
  }
  
  public final int getConnectTimeoutMillis$okhttp() {
    return this.connectTimeoutMillis;
  }
  
  public final int getReadTimeoutMillis$okhttp() {
    return this.readTimeoutMillis;
  }
  
  public final int getWriteTimeoutMillis$okhttp() {
    return this.writeTimeoutMillis;
  }
  
  @NotNull
  public final RealInterceptorChain copy$okhttp(int index, @Nullable Exchange exchange, @NotNull Request request, int connectTimeoutMillis, int readTimeoutMillis, int writeTimeoutMillis) {
    Intrinsics.checkNotNullParameter(request, "request");
    return new RealInterceptorChain(this.call, this.interceptors, index, exchange, request, connectTimeoutMillis, 
        readTimeoutMillis, writeTimeoutMillis);
  }
  
  @Nullable
  public Connection connection() {
    return (this.exchange != null) ? (Connection)this.exchange.getConnection$okhttp() : null;
  }
  
  public int connectTimeoutMillis() {
    return this.connectTimeoutMillis;
  }
  
  @NotNull
  public Interceptor.Chain withConnectTimeout(int timeout, @NotNull TimeUnit unit) {
    Intrinsics.checkNotNullParameter(unit, "unit");
    if (!((this.exchange == null) ? 1 : 0)) {
      int $i$a$-check-RealInterceptorChain$withConnectTimeout$1 = 0;
      String str = "Timeouts can't be adjusted in a network interceptor";
      throw new IllegalStateException(str.toString());
    } 
    return copy$okhttp$default(this, 0, null, null, Util.checkDuration("connectTimeout", timeout, unit), 0, 0, 55, null);
  }
  
  public int readTimeoutMillis() {
    return this.readTimeoutMillis;
  }
  
  @NotNull
  public Interceptor.Chain withReadTimeout(int timeout, @NotNull TimeUnit unit) {
    Intrinsics.checkNotNullParameter(unit, "unit");
    if (!((this.exchange == null) ? 1 : 0)) {
      int $i$a$-check-RealInterceptorChain$withReadTimeout$1 = 0;
      String str = "Timeouts can't be adjusted in a network interceptor";
      throw new IllegalStateException(str.toString());
    } 
    return copy$okhttp$default(this, 0, null, null, 0, Util.checkDuration("readTimeout", timeout, unit), 0, 47, null);
  }
  
  public int writeTimeoutMillis() {
    return this.writeTimeoutMillis;
  }
  
  @NotNull
  public Interceptor.Chain withWriteTimeout(int timeout, @NotNull TimeUnit unit) {
    Intrinsics.checkNotNullParameter(unit, "unit");
    if (!((this.exchange == null) ? 1 : 0)) {
      int $i$a$-check-RealInterceptorChain$withWriteTimeout$1 = 0;
      String str = "Timeouts can't be adjusted in a network interceptor";
      throw new IllegalStateException(str.toString());
    } 
    return copy$okhttp$default(this, 0, null, null, 0, 0, Util.checkDuration("writeTimeout", timeout, unit), 31, null);
  }
  
  @NotNull
  public Call call() {
    return (Call)this.call;
  }
  
  @NotNull
  public Request request() {
    return this.request;
  }
  
  @NotNull
  public Response proceed(@NotNull Request request) throws IOException {
    Response response;
    Intrinsics.checkNotNullParameter(request, "request");
    if (!((this.index < this.interceptors.size()) ? 1 : 0)) {
      String str = "Check failed.";
      throw new IllegalStateException(str.toString());
    } 
    int i = this.calls;
    this.calls = i + 1;
    if (this.exchange != null) {
      if (!this.exchange.getFinder$okhttp().sameHostAndPort(request.url())) {
        int $i$a$-check-RealInterceptorChain$proceed$1 = 0;
        String str = "network interceptor " + this.interceptors.get(this.index - 1) + " must retain the same host and port";
        throw new IllegalStateException(str.toString());
      } 
      if (!((this.calls == 1) ? 1 : 0)) {
        int $i$a$-check-RealInterceptorChain$proceed$2 = 0;
        String str = "network interceptor " + this.interceptors.get(this.index - 1) + " must call proceed() exactly once";
        throw new IllegalStateException(str.toString());
      } 
    } 
    RealInterceptorChain next = copy$okhttp$default(this, this.index + 1, null, request, 0, 0, 0, 58, null);
    Interceptor interceptor = this.interceptors.get(this.index);
    if (interceptor.intercept(next) == null) {
      interceptor.intercept(next);
      throw new NullPointerException("interceptor " + interceptor + " returned null");
    } 
    if (this.exchange != null && !((this.index + 1 >= this.interceptors.size() || next.calls == 1) ? 1 : 0)) {
      int $i$a$-check-RealInterceptorChain$proceed$3 = 0;
      String str = "network interceptor " + interceptor + " must call proceed() exactly once";
      throw new IllegalStateException(str.toString());
    } 
    if (!((response.body() != null) ? 1 : 0)) {
      int $i$a$-check-RealInterceptorChain$proceed$4 = 0;
      String str = "interceptor " + interceptor + " returned a response with no body";
      throw new IllegalStateException(str.toString());
    } 
    return response;
  }
}
