package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000,\n\002\030\002\n\002\030\002\n\002\b\006\n\002\020\t\n\002\b\003\n\002\020\013\n\002\b\004\n\002\020\002\n\002\b\003\n\002\030\002\n\002\b\005\b\026\030\0002\0020\001B\017\022\006\020\002\032\0020\001¢\006\004\b\003\020\004J\017\020\005\032\0020\001H\026¢\006\004\b\005\020\006J\017\020\007\032\0020\001H\026¢\006\004\b\007\020\006J\017\020\t\032\0020\bH\026¢\006\004\b\t\020\nJ\027\020\t\032\0020\0012\006\020\t\032\0020\bH\026¢\006\004\b\t\020\013J\017\020\r\032\0020\fH\026¢\006\004\b\r\020\016J\025\020\017\032\0020\0002\006\020\002\032\0020\001¢\006\004\b\017\020\020J\017\020\022\032\0020\021H\026¢\006\004\b\022\020\023J\037\020\024\032\0020\0012\006\020\024\032\0020\b2\006\020\026\032\0020\025H\026¢\006\004\b\024\020\027J\017\020\030\032\0020\bH\026¢\006\004\b\030\020\nR\"\020\002\032\0020\0018\007@\006X\016¢\006\022\n\004\b\002\020\031\032\004\b\002\020\006\"\004\b\017\020\004¨\006\032"}, d2 = {"Lokio/ForwardingTimeout;", "Lokio/Timeout;", "delegate", "<init>", "(Lokio/Timeout;)V", "clearDeadline", "()Lokio/Timeout;", "clearTimeout", "", "deadlineNanoTime", "()J", "(J)Lokio/Timeout;", "", "hasDeadline", "()Z", "setDelegate", "(Lokio/Timeout;)Lokio/ForwardingTimeout;", "", "throwIfReached", "()V", "timeout", "Ljava/util/concurrent/TimeUnit;", "unit", "(JLjava/util/concurrent/TimeUnit;)Lokio/Timeout;", "timeoutNanos", "Lokio/Timeout;", "okio"})
public class ForwardingTimeout extends Timeout {
  @NotNull
  private Timeout delegate;
  
  @JvmName(name = "delegate")
  @NotNull
  public final Timeout delegate() {
    return this.delegate;
  }
  
  public ForwardingTimeout(@NotNull Timeout delegate) {
    this.delegate = delegate;
  }
  
  @NotNull
  public final ForwardingTimeout setDelegate(@NotNull Timeout delegate) {
    Intrinsics.checkNotNullParameter(delegate, "delegate");
    this.delegate = delegate;
    return this;
  }
  
  @NotNull
  public Timeout timeout(long timeout, @NotNull TimeUnit unit) {
    Intrinsics.checkNotNullParameter(unit, "unit");
    return this.delegate.timeout(timeout, unit);
  }
  
  public long timeoutNanos() {
    return this.delegate.timeoutNanos();
  }
  
  public boolean hasDeadline() {
    return this.delegate.hasDeadline();
  }
  
  public long deadlineNanoTime() {
    return this.delegate.deadlineNanoTime();
  }
  
  @NotNull
  public Timeout deadlineNanoTime(long deadlineNanoTime) {
    return this.delegate.deadlineNanoTime(
        deadlineNanoTime);
  }
  
  @NotNull
  public Timeout clearTimeout() {
    return this.delegate.clearTimeout();
  }
  
  @NotNull
  public Timeout clearDeadline() {
    return this.delegate.clearDeadline();
  }
  
  public void throwIfReached() throws IOException {
    this.delegate.throwIfReached();
  }
}
