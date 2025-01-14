package okio;

import java.io.IOException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\0002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\004\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\004\b&\030\0002\0020\001B\017\022\006\020\002\032\0020\001¢\006\004\b\003\020\004J\017\020\006\032\0020\005H\026¢\006\004\b\006\020\007J\017\020\002\032\0020\001H\007¢\006\004\b\b\020\tJ\037\020\016\032\0020\f2\006\020\013\032\0020\n2\006\020\r\032\0020\fH\026¢\006\004\b\016\020\017J\017\020\021\032\0020\020H\026¢\006\004\b\021\020\022J\017\020\024\032\0020\023H\026¢\006\004\b\024\020\025R\027\020\002\032\0020\0018\007¢\006\f\n\004\b\002\020\026\032\004\b\002\020\t¨\006\027"}, d2 = {"Lokio/ForwardingSource;", "Lokio/Source;", "delegate", "<init>", "(Lokio/Source;)V", "", "close", "()V", "-deprecated_delegate", "()Lokio/Source;", "Lokio/Buffer;", "sink", "", "byteCount", "read", "(Lokio/Buffer;J)J", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "", "toString", "()Ljava/lang/String;", "Lokio/Source;", "okio"})
public abstract class ForwardingSource implements Source {
  @NotNull
  private final Source delegate;
  
  public ForwardingSource(@NotNull Source delegate) {
    this.delegate = delegate;
  }
  
  @JvmName(name = "delegate")
  @NotNull
  public final Source delegate() {
    return this.delegate;
  }
  
  public long read(@NotNull Buffer sink, long byteCount) throws IOException {
    Intrinsics.checkNotNullParameter(sink, "sink");
    return this.delegate.read(sink, byteCount);
  }
  
  @NotNull
  public Timeout timeout() {
    return this.delegate.timeout();
  }
  
  public void close() throws IOException {
    this.delegate.close();
  }
  
  @NotNull
  public String toString() {
    return getClass().getSimpleName() + '(' + this.delegate + ')';
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "delegate", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_delegate")
  @NotNull
  public final Source -deprecated_delegate() {
    return this.delegate;
  }
}
