package okhttp3;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\026\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\030\002\n\002\b\005\bæ\001\030\000 \0072\0020\001:\002\b\007J\027\020\005\032\0020\0042\006\020\003\032\0020\002H&¢\006\004\b\005\020\006¨\006\t"}, d2 = {"Lokhttp3/Interceptor;", "", "Lokhttp3/Interceptor$Chain;", "chain", "Lokhttp3/Response;", "intercept", "(Lokhttp3/Interceptor$Chain;)Lokhttp3/Response;", "Companion", "Chain", "okhttp"})
public interface Interceptor {
  @NotNull
  public static final Companion Companion = Companion.$$INSTANCE;
  
  @NotNull
  Response intercept(@NotNull Chain paramChain) throws IOException;
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0006\n\002\030\002\n\002\020\000\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\007\bf\030\0002\0020\001J\017\020\003\032\0020\002H&¢\006\004\b\003\020\004J\017\020\006\032\0020\005H&¢\006\004\b\006\020\007J\021\020\t\032\004\030\0010\bH&¢\006\004\b\t\020\nJ\027\020\016\032\0020\r2\006\020\f\032\0020\013H&¢\006\004\b\016\020\017J\017\020\020\032\0020\005H&¢\006\004\b\020\020\007J\017\020\f\032\0020\013H&¢\006\004\b\f\020\021J\037\020\025\032\0020\0002\006\020\022\032\0020\0052\006\020\024\032\0020\023H&¢\006\004\b\025\020\026J\037\020\027\032\0020\0002\006\020\022\032\0020\0052\006\020\024\032\0020\023H&¢\006\004\b\027\020\026J\037\020\030\032\0020\0002\006\020\022\032\0020\0052\006\020\024\032\0020\023H&¢\006\004\b\030\020\026J\017\020\031\032\0020\005H&¢\006\004\b\031\020\007¨\006\032"}, d2 = {"Lokhttp3/Interceptor$Chain;", "", "Lokhttp3/Call;", "call", "()Lokhttp3/Call;", "", "connectTimeoutMillis", "()I", "Lokhttp3/Connection;", "connection", "()Lokhttp3/Connection;", "Lokhttp3/Request;", "request", "Lokhttp3/Response;", "proceed", "(Lokhttp3/Request;)Lokhttp3/Response;", "readTimeoutMillis", "()Lokhttp3/Request;", "timeout", "Ljava/util/concurrent/TimeUnit;", "unit", "withConnectTimeout", "(ILjava/util/concurrent/TimeUnit;)Lokhttp3/Interceptor$Chain;", "withReadTimeout", "withWriteTimeout", "writeTimeoutMillis", "okhttp"})
  public static interface Chain {
    @NotNull
    Request request();
    
    @NotNull
    Response proceed(@NotNull Request param1Request) throws IOException;
    
    @Nullable
    Connection connection();
    
    @NotNull
    Call call();
    
    int connectTimeoutMillis();
    
    @NotNull
    Chain withConnectTimeout(int param1Int, @NotNull TimeUnit param1TimeUnit);
    
    int readTimeoutMillis();
    
    @NotNull
    Chain withReadTimeout(int param1Int, @NotNull TimeUnit param1TimeUnit);
    
    int writeTimeoutMillis();
    
    @NotNull
    Chain withWriteTimeout(int param1Int, @NotNull TimeUnit param1TimeUnit);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000*\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J8\020\f\032\0020\0132#\b\004\020\n\032\035\022\023\022\0210\005¢\006\f\b\006\022\b\b\007\022\004\b\b(\b\022\004\022\0020\t0\004H\nø\001\000¢\006\004\b\f\020\r\002\007\n\005\b20\001¨\006\016"}, d2 = {"Lokhttp3/Interceptor$Companion;", "", "<init>", "()V", "Lkotlin/Function1;", "Lokhttp3/Interceptor$Chain;", "Lkotlin/ParameterName;", "name", "chain", "Lokhttp3/Response;", "block", "Lokhttp3/Interceptor;", "invoke", "(Lkotlin/jvm/functions/Function1;)Lokhttp3/Interceptor;", "okhttp"})
  public static final class Companion {
    @NotNull
    public final Interceptor invoke(@NotNull Function1<? super Interceptor.Chain, Response> block) {
      Intrinsics.checkNotNullParameter(block, "block");
      int $i$f$invoke = 0;
      return new Interceptor$Companion$invoke$1(block);
    }
    
    @Metadata(mv = {1, 8, 0}, k = 3, xi = 176, d1 = {"\000\016\n\002\030\002\n\000\n\002\030\002\n\002\b\003\020\005\032\0020\0022\006\020\001\032\0020\000H\n¢\006\004\b\003\020\004"}, d2 = {"Lokhttp3/Interceptor$Chain;", "it", "Lokhttp3/Response;", "intercept", "(Lokhttp3/Interceptor$Chain;)Lokhttp3/Response;", "<anonymous>"})
    @SourceDebugExtension({"SMAP\nInterceptor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Interceptor.kt\nokhttp3/Interceptor$Companion$invoke$1\n*L\n1#1,105:1\n*E\n"})
    public static final class Interceptor$Companion$invoke$1 implements Interceptor {
      public Interceptor$Companion$invoke$1(Function1<Interceptor.Chain, Response> $block) {}
      
      @NotNull
      public final Response intercept(@NotNull Interceptor.Chain it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (Response)this.$block.invoke(it);
      }
    }
  }
}
