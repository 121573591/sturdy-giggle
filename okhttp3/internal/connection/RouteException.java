package okhttp3.internal.connection;

import java.io.IOException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\004\n\002\020\002\n\002\b\b\030\0002\0060\001j\002`\002B\021\b\000\022\006\020\004\032\0020\003¢\006\004\b\005\020\006J\025\020\t\032\0020\b2\006\020\007\032\0020\003¢\006\004\b\t\020\006R\027\020\004\032\0020\0038\006¢\006\f\n\004\b\004\020\n\032\004\b\013\020\fR$\020\016\032\0020\0032\006\020\r\032\0020\0038\006@BX\016¢\006\f\n\004\b\016\020\n\032\004\b\017\020\f¨\006\020"}, d2 = {"Lokhttp3/internal/connection/RouteException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "Ljava/io/IOException;", "firstConnectException", "<init>", "(Ljava/io/IOException;)V", "e", "", "addConnectException", "Ljava/io/IOException;", "getFirstConnectException", "()Ljava/io/IOException;", "<set-?>", "lastConnectException", "getLastConnectException", "okhttp"})
public final class RouteException extends RuntimeException {
  @NotNull
  private final IOException firstConnectException;
  
  @NotNull
  private IOException lastConnectException;
  
  @NotNull
  public final IOException getFirstConnectException() {
    return this.firstConnectException;
  }
  
  public RouteException(@NotNull IOException firstConnectException) {
    super(firstConnectException);
    this.firstConnectException = firstConnectException;
    this.lastConnectException = this.firstConnectException;
  }
  
  @NotNull
  public final IOException getLastConnectException() {
    return this.lastConnectException;
  }
  
  public final void addConnectException(@NotNull IOException e) {
    Intrinsics.checkNotNullParameter(e, "e");
    ExceptionsKt.addSuppressed(this.firstConnectException, e);
    this.lastConnectException = e;
  }
}
