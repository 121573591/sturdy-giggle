package okio;

import java.io.IOException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\0002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\005\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\005\b&\030\0002\0020\001B\017\022\006\020\002\032\0020\001¢\006\004\b\003\020\004J\017\020\006\032\0020\005H\026¢\006\004\b\006\020\007J\017\020\002\032\0020\001H\007¢\006\004\b\b\020\tJ\017\020\n\032\0020\005H\026¢\006\004\b\n\020\007J\017\020\f\032\0020\013H\026¢\006\004\b\f\020\rJ\017\020\017\032\0020\016H\026¢\006\004\b\017\020\020J\037\020\025\032\0020\0052\006\020\022\032\0020\0212\006\020\024\032\0020\023H\026¢\006\004\b\025\020\026R\027\020\002\032\0020\0018\007¢\006\f\n\004\b\002\020\027\032\004\b\002\020\t¨\006\030"}, d2 = {"Lokio/ForwardingSink;", "Lokio/Sink;", "delegate", "<init>", "(Lokio/Sink;)V", "", "close", "()V", "-deprecated_delegate", "()Lokio/Sink;", "flush", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "", "toString", "()Ljava/lang/String;", "Lokio/Buffer;", "source", "", "byteCount", "write", "(Lokio/Buffer;J)V", "Lokio/Sink;", "okio"})
public abstract class ForwardingSink implements Sink {
  @NotNull
  private final Sink delegate;
  
  public ForwardingSink(@NotNull Sink delegate) {
    this.delegate = delegate;
  }
  
  @JvmName(name = "delegate")
  @NotNull
  public final Sink delegate() {
    return this.delegate;
  }
  
  public void write(@NotNull Buffer source, long byteCount) throws IOException {
    Intrinsics.checkNotNullParameter(source, "source");
    this.delegate.write(source, byteCount);
  }
  
  public void flush() throws IOException {
    this.delegate.flush();
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
  public final Sink -deprecated_delegate() {
    return this.delegate;
  }
}
