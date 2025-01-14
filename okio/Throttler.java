package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000:\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\t\n\002\b\n\n\002\020\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\b\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\005\030\0002\0020\001B\t\b\026¢\006\004\b\002\020\003B\021\b\000\022\006\020\005\032\0020\004¢\006\004\b\002\020\006J\037\020\013\032\0020\0042\006\020\007\032\0020\0042\006\020\b\032\0020\004H\000¢\006\004\b\t\020\nJ+\020\f\032\0020\0172\006\020\f\032\0020\0042\b\b\002\020\r\032\0020\0042\b\b\002\020\016\032\0020\004H\007¢\006\004\b\f\020\020J\025\020\022\032\0020\0212\006\020\022\032\0020\021¢\006\004\b\022\020\023J\025\020\025\032\0020\0242\006\020\025\032\0020\024¢\006\004\b\025\020\026J\027\020\031\032\0020\0042\006\020\b\032\0020\004H\000¢\006\004\b\027\020\030J\023\020\032\032\0020\004*\0020\004H\002¢\006\004\b\032\020\030J\023\020\033\032\0020\004*\0020\004H\002¢\006\004\b\033\020\030R\026\020\005\032\0020\0048\002@\002X\016¢\006\006\n\004\b\005\020\034R\026\020\f\032\0020\0048\002@\002X\016¢\006\006\n\004\b\f\020\034R\027\020\036\032\0020\0358\006¢\006\f\n\004\b\036\020\037\032\004\b \020!R\027\020#\032\0020\"8\006¢\006\f\n\004\b#\020$\032\004\b%\020&R\026\020\016\032\0020\0048\002@\002X\016¢\006\006\n\004\b\016\020\034R\026\020\r\032\0020\0048\002@\002X\016¢\006\006\n\004\b\r\020\034¨\006'"}, d2 = {"Lokio/Throttler;", "", "<init>", "()V", "", "allocatedUntil", "(J)V", "now", "byteCount", "byteCountOrWaitNanos$okio", "(JJ)J", "byteCountOrWaitNanos", "bytesPerSecond", "waitByteCount", "maxByteCount", "", "(JJJ)V", "Lokio/Sink;", "sink", "(Lokio/Sink;)Lokio/Sink;", "Lokio/Source;", "source", "(Lokio/Source;)Lokio/Source;", "take$okio", "(J)J", "take", "bytesToNanos", "nanosToBytes", "J", "Ljava/util/concurrent/locks/Condition;", "condition", "Ljava/util/concurrent/locks/Condition;", "getCondition", "()Ljava/util/concurrent/locks/Condition;", "Ljava/util/concurrent/locks/ReentrantLock;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "getLock", "()Ljava/util/concurrent/locks/ReentrantLock;", "okio"})
public final class Throttler {
  private long allocatedUntil;
  
  private long bytesPerSecond;
  
  private long waitByteCount;
  
  private long maxByteCount;
  
  @NotNull
  private final ReentrantLock lock;
  
  @NotNull
  private final Condition condition;
  
  public Throttler(long allocatedUntil) {
    this.allocatedUntil = allocatedUntil;
    this.waitByteCount = 8192L;
    this.maxByteCount = 262144L;
    this.lock = new ReentrantLock();
    Intrinsics.checkNotNullExpressionValue(this.lock.newCondition(), "newCondition(...)");
    this.condition = this.lock.newCondition();
  }
  
  @NotNull
  public final ReentrantLock getLock() {
    return this.lock;
  }
  
  @NotNull
  public final Condition getCondition() {
    return this.condition;
  }
  
  public Throttler() {
    this(System.nanoTime());
  }
  
  @JvmOverloads
  public final void bytesPerSecond(long bytesPerSecond, long waitByteCount, long maxByteCount) {
    ReentrantLock reentrantLock = this.lock;
    reentrantLock.lock();
    try {
      int $i$a$-withLock-Throttler$bytesPerSecond$1 = 0;
      if (!((bytesPerSecond >= 0L) ? 1 : 0)) {
        String str = "Failed requirement.";
        throw new IllegalArgumentException(str.toString());
      } 
      if (!((waitByteCount > 0L) ? 1 : 0)) {
        String str = "Failed requirement.";
        throw new IllegalArgumentException(str.toString());
      } 
      if (!((maxByteCount >= waitByteCount) ? 1 : 0)) {
        String str = "Failed requirement.";
        throw new IllegalArgumentException(str.toString());
      } 
      this.bytesPerSecond = bytesPerSecond;
      this.waitByteCount = waitByteCount;
      this.maxByteCount = maxByteCount;
      this.condition.signalAll();
      Unit unit = Unit.INSTANCE;
    } finally {
      reentrantLock.unlock();
    } 
  }
  
  public final long take$okio(long byteCount) {
    if (!((byteCount > 0L) ? 1 : 0)) {
      String str = "Failed requirement.";
      throw new IllegalArgumentException(str.toString());
    } 
    ReentrantLock reentrantLock = this.lock;
    reentrantLock.lock();
    try {
      int $i$a$-withLock-Throttler$take$1 = 0;
      while (true) {
        long now = System.nanoTime();
        long byteCountOrWaitNanos = byteCountOrWaitNanos$okio(now, byteCount);
        if (byteCountOrWaitNanos >= 0L)
          return byteCountOrWaitNanos; 
        this.condition.awaitNanos(-byteCountOrWaitNanos);
      } 
    } finally {
      reentrantLock.unlock();
    } 
  }
  
  public final long byteCountOrWaitNanos$okio(long now, long byteCount) {
    if (this.bytesPerSecond == 0L)
      return byteCount; 
    long idleInNanos = Math.max(this.allocatedUntil - now, 0L);
    long immediateBytes = this.maxByteCount - nanosToBytes(idleInNanos);
    if (immediateBytes >= byteCount) {
      this.allocatedUntil = now + idleInNanos + bytesToNanos(byteCount);
      return byteCount;
    } 
    if (immediateBytes >= this.waitByteCount) {
      this.allocatedUntil = now + bytesToNanos(this.maxByteCount);
      return immediateBytes;
    } 
    long minByteCount = Math.min(this.waitByteCount, byteCount);
    long minWaitNanos = idleInNanos + bytesToNanos(minByteCount - this.maxByteCount);
    if (minWaitNanos == 0L) {
      this.allocatedUntil = now + bytesToNanos(this.maxByteCount);
      return minByteCount;
    } 
    return -minWaitNanos;
  }
  
  private final long nanosToBytes(long $this$nanosToBytes) {
    return $this$nanosToBytes * this.bytesPerSecond / 1000000000L;
  }
  
  private final long bytesToNanos(long $this$bytesToNanos) {
    return $this$bytesToNanos * 1000000000L / this.bytesPerSecond;
  }
  
  @NotNull
  public final Source source(@NotNull Source source) {
    Intrinsics.checkNotNullParameter(source, "source");
    return new Throttler$source$1(this);
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000\027\n\000\n\002\030\002\n\002\030\002\n\000\n\002\020\t\n\002\b\004*\001\000\b\n\030\0002\0020\001J\037\020\006\032\0020\0042\006\020\003\032\0020\0022\006\020\005\032\0020\004H\026¢\006\004\b\006\020\007¨\006\b"}, d2 = {"okio/Throttler.source.1", "Lokio/ForwardingSource;", "Lokio/Buffer;", "sink", "", "byteCount", "read", "(Lokio/Buffer;J)J", "okio"})
  public static final class Throttler$source$1 extends ForwardingSource {
    Throttler$source$1(Throttler $receiver) {
      super($source);
    }
    
    public long read(@NotNull Buffer sink, long byteCount) {
      Intrinsics.checkNotNullParameter(sink, "sink");
      try {
        long toRead = Throttler.this.take$okio(byteCount);
        return super.read(sink, toRead);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new InterruptedIOException("interrupted");
      } 
    }
  }
  
  @NotNull
  public final Sink sink(@NotNull Sink sink) {
    Intrinsics.checkNotNullParameter(sink, "sink");
    return new Throttler$sink$1(this);
  }
  
  @JvmOverloads
  public final void bytesPerSecond(long bytesPerSecond, long waitByteCount) {
    bytesPerSecond$default(this, bytesPerSecond, waitByteCount, 0L, 4, null);
  }
  
  @JvmOverloads
  public final void bytesPerSecond(long bytesPerSecond) {
    bytesPerSecond$default(this, bytesPerSecond, 0L, 0L, 6, null);
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000\035\n\000\n\002\030\002\n\002\030\002\n\000\n\002\020\t\n\000\n\002\020\002\n\002\b\003*\001\000\b\n\030\0002\0020\001J\037\020\007\032\0020\0062\006\020\003\032\0020\0022\006\020\005\032\0020\004H\026¢\006\004\b\007\020\b¨\006\t"}, d2 = {"okio/Throttler.sink.1", "Lokio/ForwardingSink;", "Lokio/Buffer;", "source", "", "byteCount", "", "write", "(Lokio/Buffer;J)V", "okio"})
  public static final class Throttler$sink$1 extends ForwardingSink {
    Throttler$sink$1(Throttler $receiver) {
      super($sink);
    }
    
    public void write(@NotNull Buffer source, long byteCount) throws IOException {
      Intrinsics.checkNotNullParameter(source, "source");
      try {
        long remaining = byteCount;
        while (remaining > 0L) {
          long toWrite = Throttler.this.take$okio(remaining);
          super.write(source, toWrite);
          remaining -= toWrite;
        } 
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new InterruptedIOException("interrupted");
      } 
    }
  }
}
