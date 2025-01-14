package okhttp3;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.RealConnectionPool;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000,\n\002\030\002\n\002\020\000\n\002\020\b\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\004\n\002\020\002\n\002\b\006\030\0002\0020\001B!\b\026\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\006\020\007\032\0020\006¢\006\004\b\b\020\tB\t\b\026¢\006\004\b\b\020\nB\021\b\000\022\006\020\f\032\0020\013¢\006\004\b\b\020\rJ\r\020\016\032\0020\002¢\006\004\b\016\020\017J\r\020\021\032\0020\020¢\006\004\b\021\020\nJ\r\020\022\032\0020\002¢\006\004\b\022\020\017R\032\020\f\032\0020\0138\000X\004¢\006\f\n\004\b\f\020\023\032\004\b\024\020\025¨\006\026"}, d2 = {"Lokhttp3/ConnectionPool;", "", "", "maxIdleConnections", "", "keepAliveDuration", "Ljava/util/concurrent/TimeUnit;", "timeUnit", "<init>", "(IJLjava/util/concurrent/TimeUnit;)V", "()V", "Lokhttp3/internal/connection/RealConnectionPool;", "delegate", "(Lokhttp3/internal/connection/RealConnectionPool;)V", "connectionCount", "()I", "", "evictAll", "idleConnectionCount", "Lokhttp3/internal/connection/RealConnectionPool;", "getDelegate$okhttp", "()Lokhttp3/internal/connection/RealConnectionPool;", "okhttp"})
public final class ConnectionPool {
  @NotNull
  private final RealConnectionPool delegate;
  
  public ConnectionPool(@NotNull RealConnectionPool delegate) {
    this.delegate = delegate;
  }
  
  @NotNull
  public final RealConnectionPool getDelegate$okhttp() {
    return this.delegate;
  }
  
  public ConnectionPool(int maxIdleConnections, long keepAliveDuration, @NotNull TimeUnit timeUnit) {
    this(new RealConnectionPool(
          TaskRunner.INSTANCE, 
          maxIdleConnections, 
          keepAliveDuration, 
          timeUnit));
  }
  
  public ConnectionPool() {
    this(5, 5L, TimeUnit.MINUTES);
  }
  
  public final int idleConnectionCount() {
    return this.delegate.idleConnectionCount();
  }
  
  public final int connectionCount() {
    return this.delegate.connectionCount();
  }
  
  public final void evictAll() {
    this.delegate.evictAll();
  }
}
