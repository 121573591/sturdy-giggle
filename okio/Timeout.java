package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\0008\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\002\n\002\b\005\n\002\020\t\n\000\n\002\030\002\n\002\b\006\n\002\020\013\n\002\b\004\n\002\030\002\n\002\b\r\b\026\030\000 &2\0020\001:\001&B\007¢\006\004\b\002\020\003J\025\020\007\032\0020\0062\006\020\005\032\0020\004¢\006\004\b\007\020\bJ\017\020\t\032\0020\000H\026¢\006\004\b\t\020\nJ\017\020\013\032\0020\000H\026¢\006\004\b\013\020\nJ\035\020\020\032\0020\0002\006\020\r\032\0020\f2\006\020\017\032\0020\016¢\006\004\b\020\020\021J\017\020\022\032\0020\fH\026¢\006\004\b\022\020\023J\027\020\022\032\0020\0002\006\020\022\032\0020\fH\026¢\006\004\b\022\020\024J\017\020\026\032\0020\025H\026¢\006\004\b\026\020\027J/\020\034\032\0028\000\"\004\b\000\020\0302\006\020\031\032\0020\0002\f\020\033\032\b\022\004\022\0028\0000\032H\bø\001\000¢\006\004\b\034\020\035J\017\020\036\032\0020\006H\026¢\006\004\b\036\020\003J\037\020\037\032\0020\0002\006\020\037\032\0020\f2\006\020\017\032\0020\016H\026¢\006\004\b\037\020\021J\017\020 \032\0020\fH\026¢\006\004\b \020\023J\025\020\"\032\0020\0062\006\020!\032\0020\001¢\006\004\b\"\020#R\026\020\022\032\0020\f8\002@\002X\016¢\006\006\n\004\b\022\020$R\026\020\026\032\0020\0258\002@\002X\016¢\006\006\n\004\b\026\020%R\026\020 \032\0020\f8\002@\002X\016¢\006\006\n\004\b \020$\002\007\n\005\b20\001¨\006'"}, d2 = {"Lokio/Timeout;", "", "<init>", "()V", "Ljava/util/concurrent/locks/Condition;", "condition", "", "awaitSignal", "(Ljava/util/concurrent/locks/Condition;)V", "clearDeadline", "()Lokio/Timeout;", "clearTimeout", "", "duration", "Ljava/util/concurrent/TimeUnit;", "unit", "deadline", "(JLjava/util/concurrent/TimeUnit;)Lokio/Timeout;", "deadlineNanoTime", "()J", "(J)Lokio/Timeout;", "", "hasDeadline", "()Z", "T", "other", "Lkotlin/Function0;", "block", "intersectWith", "(Lokio/Timeout;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "throwIfReached", "timeout", "timeoutNanos", "monitor", "waitUntilNotified", "(Ljava/lang/Object;)V", "J", "Z", "Companion", "okio"})
@SourceDebugExtension({"SMAP\nTimeout.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Timeout.kt\nokio/Timeout\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,316:1\n1#2:317\n*E\n"})
public class Timeout {
  @NotNull
  public Timeout timeout(long timeout, @NotNull TimeUnit unit) {
    Intrinsics.checkNotNullParameter(unit, "unit");
    if (!((timeout >= 0L) ? 1 : 0)) {
      int $i$a$-require-Timeout$timeout$1 = 0;
      String str = "timeout < 0: " + timeout;
      throw new IllegalArgumentException(str.toString());
    } 
    this.timeoutNanos = unit.toNanos(timeout);
    return this;
  }
  
  public long timeoutNanos() {
    return this.timeoutNanos;
  }
  
  public boolean hasDeadline() {
    return this.hasDeadline;
  }
  
  public long deadlineNanoTime() {
    if (!this.hasDeadline) {
      int $i$a$-check-Timeout$deadlineNanoTime$1 = 0;
      String str = "No deadline";
      throw new IllegalStateException(str.toString());
    } 
    return this.deadlineNanoTime;
  }
  
  @NotNull
  public Timeout deadlineNanoTime(long deadlineNanoTime) {
    this.hasDeadline = true;
    this.deadlineNanoTime = deadlineNanoTime;
    return this;
  }
  
  @NotNull
  public final Timeout deadline(long duration, @NotNull TimeUnit unit) {
    Intrinsics.checkNotNullParameter(unit, "unit");
    if (!((duration > 0L) ? 1 : 0)) {
      int $i$a$-require-Timeout$deadline$1 = 0;
      String str = "duration <= 0: " + duration;
      throw new IllegalArgumentException(str.toString());
    } 
    return deadlineNanoTime(System.nanoTime() + unit.toNanos(duration));
  }
  
  @NotNull
  public Timeout clearTimeout() {
    this.timeoutNanos = 0L;
    return this;
  }
  
  @NotNull
  public Timeout clearDeadline() {
    this.hasDeadline = false;
    return this;
  }
  
  public void throwIfReached() throws IOException {
    if (Thread.currentThread().isInterrupted())
      throw new InterruptedIOException("interrupted"); 
    if (this.hasDeadline && this.deadlineNanoTime - System.nanoTime() <= 0L)
      throw new InterruptedIOException("deadline reached"); 
  }
  
  public final void awaitSignal(@NotNull Condition condition) throws InterruptedIOException {
    Intrinsics.checkNotNullParameter(condition, "condition");
    try {
      boolean hasDeadline = hasDeadline();
      long timeoutNanos = timeoutNanos();
      if (!hasDeadline && timeoutNanos == 0L) {
        condition.await();
        return;
      } 
      long start = System.nanoTime();
      long deadlineNanos = deadlineNanoTime() - start;
      long waitNanos = (hasDeadline && timeoutNanos != 0L) ? Math.min(timeoutNanos, deadlineNanos) : (hasDeadline ? (deadlineNanoTime() - start) : timeoutNanos);
      long elapsedNanos = 0L;
      if (waitNanos > 0L) {
        condition.await(waitNanos, TimeUnit.NANOSECONDS);
        elapsedNanos = System.nanoTime() - start;
      } 
      if (elapsedNanos >= waitNanos)
        throw new InterruptedIOException("timeout"); 
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new InterruptedIOException("interrupted");
    } 
  }
  
  public final void waitUntilNotified(@NotNull Object monitor) throws InterruptedIOException {
    Intrinsics.checkNotNullParameter(monitor, "monitor");
    try {
      boolean hasDeadline = hasDeadline();
      long timeoutNanos = timeoutNanos();
      if (!hasDeadline && timeoutNanos == 0L) {
        monitor.wait();
        return;
      } 
      long start = System.nanoTime();
      long deadlineNanos = deadlineNanoTime() - start;
      long waitNanos = (hasDeadline && timeoutNanos != 0L) ? Math.min(timeoutNanos, deadlineNanos) : (hasDeadline ? (deadlineNanoTime() - start) : timeoutNanos);
      long elapsedNanos = 0L;
      if (waitNanos > 0L) {
        long waitMillis = waitNanos / 1000000L;
        monitor.wait(waitMillis, (int)(waitNanos - waitMillis * 1000000L));
        elapsedNanos = System.nanoTime() - start;
      } 
      if (elapsedNanos >= waitNanos)
        throw new InterruptedIOException("timeout"); 
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new InterruptedIOException("interrupted");
    } 
  }
  
  public final <T> T intersectWith(@NotNull Timeout other, @NotNull Function0 block) {
    Intrinsics.checkNotNullParameter(other, "other");
    Intrinsics.checkNotNullParameter(block, "block");
    int $i$f$intersectWith = 0;
    long originalTimeout = timeoutNanos();
    timeout(Companion.minTimeout(other.timeoutNanos(), timeoutNanos()), TimeUnit.NANOSECONDS);
    if (hasDeadline()) {
      long originalDeadline = deadlineNanoTime();
      if (other.hasDeadline())
        deadlineNanoTime(Math.min(deadlineNanoTime(), other.deadlineNanoTime())); 
      try {
        return (T)block.invoke();
      } finally {
        InlineMarker.finallyStart(1);
        timeout(originalTimeout, TimeUnit.NANOSECONDS);
        if (other.hasDeadline())
          deadlineNanoTime(originalDeadline); 
        InlineMarker.finallyEnd(1);
      } 
    } 
    if (other.hasDeadline())
      deadlineNanoTime(other.deadlineNanoTime()); 
    try {
      return (T)block.invoke();
    } finally {
      InlineMarker.finallyStart(1);
      timeout(originalTimeout, TimeUnit.NANOSECONDS);
      if (other.hasDeadline())
        clearDeadline(); 
      InlineMarker.finallyEnd(1);
    } 
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\t\n\002\b\004\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\035\020\007\032\0020\0042\006\020\005\032\0020\0042\006\020\006\032\0020\004¢\006\004\b\007\020\bR\024\020\n\032\0020\t8\006X\004¢\006\006\n\004\b\n\020\013¨\006\f"}, d2 = {"Lokio/Timeout$Companion;", "", "<init>", "()V", "", "aNanos", "bNanos", "minTimeout", "(JJ)J", "Lokio/Timeout;", "NONE", "Lokio/Timeout;", "okio"})
  public static final class Companion {
    private Companion() {}
    
    public final long minTimeout(long aNanos, long bNanos) {
      return (aNanos == 0L) ? bNanos : ((bNanos == 0L) ? aNanos : ((aNanos < bNanos) ? aNanos : bNanos));
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  private boolean hasDeadline;
  
  private long deadlineNanoTime;
  
  private long timeoutNanos;
  
  @JvmField
  @NotNull
  public static final Timeout NONE = new Timeout$Companion$NONE$1();
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000!\n\000\n\002\030\002\n\002\020\t\n\002\b\002\n\002\020\002\n\002\b\003\n\002\030\002\n\002\b\003*\001\000\b\n\030\0002\0020\001J\027\020\003\032\0020\0012\006\020\003\032\0020\002H\026¢\006\004\b\003\020\004J\017\020\006\032\0020\005H\026¢\006\004\b\006\020\007J\037\020\b\032\0020\0012\006\020\b\032\0020\0022\006\020\n\032\0020\tH\026¢\006\004\b\b\020\013¨\006\f"}, d2 = {"okio/Timeout.Companion.NONE.1", "Lokio/Timeout;", "", "deadlineNanoTime", "(J)Lokio/Timeout;", "", "throwIfReached", "()V", "timeout", "Ljava/util/concurrent/TimeUnit;", "unit", "(JLjava/util/concurrent/TimeUnit;)Lokio/Timeout;", "okio"})
  public static final class Timeout$Companion$NONE$1 extends Timeout {
    @NotNull
    public Timeout timeout(long timeout, @NotNull TimeUnit unit) {
      Intrinsics.checkNotNullParameter(unit, "unit");
      return this;
    }
    
    @NotNull
    public Timeout deadlineNanoTime(long deadlineNanoTime) {
      return this;
    }
    
    public void throwIfReached() {}
  }
}
