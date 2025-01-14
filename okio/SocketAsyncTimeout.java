package okio;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000 \n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\004\b\002\030\0002\0020\001B\017\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\031\020\b\032\0020\0062\b\020\007\032\004\030\0010\006H\024¢\006\004\b\b\020\tJ\017\020\013\032\0020\nH\024¢\006\004\b\013\020\fR\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\r¨\006\016"}, d2 = {"Lokio/SocketAsyncTimeout;", "Lokio/AsyncTimeout;", "Ljava/net/Socket;", "socket", "<init>", "(Ljava/net/Socket;)V", "Ljava/io/IOException;", "cause", "newTimeoutException", "(Ljava/io/IOException;)Ljava/io/IOException;", "", "timedOut", "()V", "Ljava/net/Socket;", "okio"})
final class SocketAsyncTimeout extends AsyncTimeout {
  @NotNull
  private final Socket socket;
  
  public SocketAsyncTimeout(@NotNull Socket socket) {
    this.socket = socket;
  }
  
  @NotNull
  protected IOException newTimeoutException(@Nullable IOException cause) {
    SocketTimeoutException ioe = new SocketTimeoutException("timeout");
    if (cause != null)
      ioe.initCause(cause); 
    return ioe;
  }
  
  protected void timedOut() {
    try {
      this.socket.close();
    } catch (Exception e) {
      Okio__JvmOkioKt.access$getLogger$p().log(Level.WARNING, "Failed to close timed out socket " + this.socket, e);
    } catch (AssertionError e) {
      if (Okio.isAndroidGetsocknameError(e)) {
        Okio__JvmOkioKt.access$getLogger$p().log(Level.WARNING, "Failed to close timed out socket " + this.socket, e);
      } else {
        throw e;
      } 
    } 
  }
}
