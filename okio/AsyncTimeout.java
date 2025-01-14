package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000B\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\002\n\000\n\002\020\013\n\002\b\003\n\002\020\t\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\f\b\026\030\000 $2\0020\001:\002$%B\007¢\006\004\b\002\020\003J\031\020\006\032\0020\0042\b\020\005\032\004\030\0010\004H\001¢\006\004\b\006\020\007J\r\020\t\032\0020\b¢\006\004\b\t\020\003J\r\020\013\032\0020\n¢\006\004\b\013\020\fJ\031\020\r\032\0020\0042\b\020\005\032\004\030\0010\004H\024¢\006\004\b\r\020\007J\027\020\020\032\0020\0162\006\020\017\032\0020\016H\002¢\006\004\b\020\020\021J\025\020\023\032\0020\0222\006\020\023\032\0020\022¢\006\004\b\023\020\024J\025\020\026\032\0020\0252\006\020\026\032\0020\025¢\006\004\b\026\020\027J\017\020\030\032\0020\bH\024¢\006\004\b\030\020\003J'\020\034\032\0028\000\"\004\b\000\020\0312\f\020\033\032\b\022\004\022\0028\0000\032H\bø\001\000¢\006\004\b\034\020\035R\026\020\036\032\0020\n8\002@\002X\016¢\006\006\n\004\b\036\020\037R\030\020 \032\004\030\0010\0008\002@\002X\016¢\006\006\n\004\b \020!R\026\020\"\032\0020\0168\002@\002X\016¢\006\006\n\004\b\"\020#\002\007\n\005\b20\001¨\006&"}, d2 = {"Lokio/AsyncTimeout;", "Lokio/Timeout;", "<init>", "()V", "Ljava/io/IOException;", "cause", "access$newTimeoutException", "(Ljava/io/IOException;)Ljava/io/IOException;", "", "enter", "", "exit", "()Z", "newTimeoutException", "", "now", "remainingNanos", "(J)J", "Lokio/Sink;", "sink", "(Lokio/Sink;)Lokio/Sink;", "Lokio/Source;", "source", "(Lokio/Source;)Lokio/Source;", "timedOut", "T", "Lkotlin/Function0;", "block", "withTimeout", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "inQueue", "Z", "next", "Lokio/AsyncTimeout;", "timeoutAt", "J", "Companion", "Watchdog", "okio"})
public class AsyncTimeout extends Timeout {
  public final void enter() {
    long timeoutNanos = timeoutNanos();
    boolean hasDeadline = hasDeadline();
    if (timeoutNanos == 0L && !hasDeadline)
      return; 
    Companion.scheduleTimeout(this, timeoutNanos, hasDeadline);
  }
  
  public final boolean exit() {
    return Companion.cancelScheduledTimeout(this);
  }
  
  private final long remainingNanos(long now) {
    return this.timeoutAt - now;
  }
  
  protected void timedOut() {}
  
  @NotNull
  public final Sink sink(@NotNull Sink sink) {
    Intrinsics.checkNotNullParameter(sink, "sink");
    return new AsyncTimeout$sink$1(sink);
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000/\n\000\n\002\030\002\n\002\020\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\004*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\026¢\006\004\b\003\020\004J\017\020\005\032\0020\002H\026¢\006\004\b\005\020\004J\017\020\007\032\0020\006H\026¢\006\004\b\007\020\bJ\017\020\n\032\0020\tH\026¢\006\004\b\n\020\013J\037\020\020\032\0020\0022\006\020\r\032\0020\f2\006\020\017\032\0020\016H\026¢\006\004\b\020\020\021¨\006\022"}, d2 = {"okio/AsyncTimeout.sink.1", "Lokio/Sink;", "", "close", "()V", "flush", "Lokio/AsyncTimeout;", "timeout", "()Lokio/AsyncTimeout;", "", "toString", "()Ljava/lang/String;", "Lokio/Buffer;", "source", "", "byteCount", "write", "(Lokio/Buffer;J)V", "okio"})
  @SourceDebugExtension({"SMAP\nAsyncTimeout.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AsyncTimeout.kt\nokio/AsyncTimeout$sink$1\n+ 2 AsyncTimeout.kt\nokio/AsyncTimeout\n*L\n1#1,331:1\n146#2,11:332\n146#2,11:343\n146#2,11:354\n*S KotlinDebug\n*F\n+ 1 AsyncTimeout.kt\nokio/AsyncTimeout$sink$1\n*L\n102#1:332,11\n108#1:343,11\n112#1:354,11\n*E\n"})
  public static final class AsyncTimeout$sink$1 implements Sink {
    AsyncTimeout$sink$1(Sink $sink) {}
    
    public void write(@NotNull Buffer source, long byteCount) {
      Intrinsics.checkNotNullParameter(source, "source");
      -SegmentedByteString.checkOffsetAndCount(source.size(), 0L, byteCount);
      long remaining = byteCount;
      while (remaining > 0L) {
        long toWrite = 0L;
        Intrinsics.checkNotNull(source.head);
        Segment s = source.head;
        while (toWrite < 65536L) {
          int segmentSize = s.limit - s.pos;
          toWrite += segmentSize;
          if (toWrite >= remaining) {
            toWrite = remaining;
            break;
          } 
          Intrinsics.checkNotNull(s.next);
          s = s.next;
        } 
        AsyncTimeout asyncTimeout = AsyncTimeout.this;
        Sink sink = this.$sink;
        int $i$f$withTimeout = 0;
        boolean throwOnTimeout$iv = false;
        asyncTimeout.enter();
      } 
    }
    
    public void flush() {
      AsyncTimeout asyncTimeout = AsyncTimeout.this;
      Sink sink = this.$sink;
      int $i$f$withTimeout = 0;
      boolean throwOnTimeout$iv = false;
      asyncTimeout.enter();
      try {
        int $i$a$-withTimeout-AsyncTimeout$sink$1$flush$1 = 0;
        sink.flush();
        Object result$iv = Unit.INSTANCE;
        throwOnTimeout$iv = true;
        Object object1 = result$iv;
      } catch (IOException e$iv) {
        Object result$iv;
        throw !asyncTimeout.exit() ? (Throwable)result$iv : (Throwable)asyncTimeout.access$newTimeoutException(result$iv);
      } finally {
        boolean timedOut$iv = asyncTimeout.exit();
        if (timedOut$iv && throwOnTimeout$iv)
          throw asyncTimeout.access$newTimeoutException(null); 
      } 
    }
    
    public void close() {
      AsyncTimeout asyncTimeout = AsyncTimeout.this;
      Sink sink = this.$sink;
      int $i$f$withTimeout = 0;
      boolean throwOnTimeout$iv = false;
      asyncTimeout.enter();
      try {
        int $i$a$-withTimeout-AsyncTimeout$sink$1$close$1 = 0;
        sink.close();
        Object result$iv = Unit.INSTANCE;
        throwOnTimeout$iv = true;
        Object object1 = result$iv;
      } catch (IOException e$iv) {
        Object result$iv;
        throw !asyncTimeout.exit() ? (Throwable)result$iv : (Throwable)asyncTimeout.access$newTimeoutException(result$iv);
      } finally {
        boolean timedOut$iv = asyncTimeout.exit();
        if (timedOut$iv && throwOnTimeout$iv)
          throw asyncTimeout.access$newTimeoutException(null); 
      } 
    }
    
    @NotNull
    public AsyncTimeout timeout() {
      return AsyncTimeout.this;
    }
    
    @NotNull
    public String toString() {
      return "AsyncTimeout.sink(" + this.$sink + ')';
    }
  }
  
  @NotNull
  public final Source source(@NotNull Source source) {
    Intrinsics.checkNotNullParameter(source, "source");
    return new AsyncTimeout$source$1(source);
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000/\n\000\n\002\030\002\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\003*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\026¢\006\004\b\003\020\004J\037\020\t\032\0020\0072\006\020\006\032\0020\0052\006\020\b\032\0020\007H\026¢\006\004\b\t\020\nJ\017\020\f\032\0020\013H\026¢\006\004\b\f\020\rJ\017\020\017\032\0020\016H\026¢\006\004\b\017\020\020¨\006\021"}, d2 = {"okio/AsyncTimeout.source.1", "Lokio/Source;", "", "close", "()V", "Lokio/Buffer;", "sink", "", "byteCount", "read", "(Lokio/Buffer;J)J", "Lokio/AsyncTimeout;", "timeout", "()Lokio/AsyncTimeout;", "", "toString", "()Ljava/lang/String;", "okio"})
  @SourceDebugExtension({"SMAP\nAsyncTimeout.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AsyncTimeout.kt\nokio/AsyncTimeout$source$1\n+ 2 AsyncTimeout.kt\nokio/AsyncTimeout\n*L\n1#1,331:1\n146#2,11:332\n146#2,11:343\n*S KotlinDebug\n*F\n+ 1 AsyncTimeout.kt\nokio/AsyncTimeout$source$1\n*L\n128#1:332,11\n132#1:343,11\n*E\n"})
  public static final class AsyncTimeout$source$1 implements Source {
    AsyncTimeout$source$1(Source $source) {}
    
    public long read(@NotNull Buffer sink, long byteCount) {
      Intrinsics.checkNotNullParameter(sink, "sink");
      AsyncTimeout asyncTimeout = AsyncTimeout.this;
      Source source = this.$source;
      int $i$f$withTimeout = 0;
      boolean throwOnTimeout$iv = false;
      asyncTimeout.enter();
      try {
        int $i$a$-withTimeout-AsyncTimeout$source$1$read$1 = 0;
        long result$iv = source.read(sink, byteCount);
        throwOnTimeout$iv = true;
        long l1 = result$iv;
      } catch (IOException e$iv) {
        IOException iOException1;
        throw !asyncTimeout.exit() ? (Throwable)iOException1 : (Throwable)asyncTimeout.access$newTimeoutException(iOException1);
      } finally {
        boolean timedOut$iv = asyncTimeout.exit();
        if (timedOut$iv && throwOnTimeout$iv)
          throw asyncTimeout.access$newTimeoutException(null); 
      } 
      return l1;
    }
    
    public void close() {
      AsyncTimeout asyncTimeout = AsyncTimeout.this;
      Source source = this.$source;
      int $i$f$withTimeout = 0;
      boolean throwOnTimeout$iv = false;
      asyncTimeout.enter();
      try {
        int $i$a$-withTimeout-AsyncTimeout$source$1$close$1 = 0;
        source.close();
        Object result$iv = Unit.INSTANCE;
        throwOnTimeout$iv = true;
        Object object1 = result$iv;
      } catch (IOException e$iv) {
        Object result$iv;
        throw !asyncTimeout.exit() ? (Throwable)result$iv : (Throwable)asyncTimeout.access$newTimeoutException(result$iv);
      } finally {
        boolean timedOut$iv = asyncTimeout.exit();
        if (timedOut$iv && throwOnTimeout$iv)
          throw asyncTimeout.access$newTimeoutException(null); 
      } 
    }
    
    @NotNull
    public AsyncTimeout timeout() {
      return AsyncTimeout.this;
    }
    
    @NotNull
    public String toString() {
      return "AsyncTimeout.source(" + this.$source + ')';
    }
  }
  
  public final <T> T withTimeout(@NotNull Function0 block) {
    Intrinsics.checkNotNullParameter(block, "block");
    int $i$f$withTimeout = 0;
    boolean throwOnTimeout = false;
    enter();
    try {
      Object result = block.invoke();
      throwOnTimeout = true;
      return (T)result;
    } catch (IOException e) {
      Object result;
      throw !exit() ? (Throwable)result : (Throwable)access$newTimeoutException(result);
    } finally {
      InlineMarker.finallyStart(1);
      boolean timedOut = exit();
      if (timedOut && throwOnTimeout)
        throw access$newTimeoutException(null); 
      InlineMarker.finallyEnd(1);
    } 
  }
  
  @PublishedApi
  @NotNull
  public final IOException access$newTimeoutException(@Nullable IOException cause) {
    return newTimeoutException(cause);
  }
  
  @NotNull
  protected IOException newTimeoutException(@Nullable IOException cause) {
    InterruptedIOException e = new InterruptedIOException("timeout");
    if (cause != null)
      e.initCause(cause); 
    return e;
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\002\b\002\030\0002\0020\001B\t\b\000¢\006\004\b\002\020\003J\017\020\005\032\0020\004H\026¢\006\004\b\005\020\003¨\006\006"}, d2 = {"Lokio/AsyncTimeout$Watchdog;", "Ljava/lang/Thread;", "<init>", "()V", "", "run", "okio"})
  private static final class Watchdog extends Thread {
    public Watchdog() {
      super("Okio Watchdog");
      setDaemon(true);
    }
    
    public void run() {
      while (true) {
        try {
          Object timedOut = null;
          ReentrantLock reentrantLock = AsyncTimeout.Companion.getLock();
          reentrantLock.lock();
          try {
            int $i$a$-withLock-AsyncTimeout$Watchdog$run$1 = 0;
            timedOut = AsyncTimeout.Companion.awaitTimeout$okio();
            if (timedOut == AsyncTimeout.head) {
              AsyncTimeout.head = null;
              return;
            } 
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
          } finally {
            reentrantLock.unlock();
          } 
        } catch (InterruptedException interruptedException) {}
      } 
    }
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000D\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\004\n\002\020\013\n\002\b\002\n\002\020\t\n\002\b\002\n\002\020\002\n\002\b\005\n\002\020\b\n\002\b\002\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\005\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\021\020\007\032\004\030\0010\004H\000¢\006\004\b\005\020\006J\027\020\n\032\0020\t2\006\020\b\032\0020\004H\002¢\006\004\b\n\020\013J'\020\020\032\0020\0172\006\020\b\032\0020\0042\006\020\r\032\0020\f2\006\020\016\032\0020\tH\002¢\006\004\b\020\020\021R\024\020\022\032\0020\f8\002X\004¢\006\006\n\004\b\022\020\023R\024\020\024\032\0020\f8\002X\004¢\006\006\n\004\b\024\020\023R\024\020\026\032\0020\0258\002XT¢\006\006\n\004\b\026\020\027R\027\020\031\032\0020\0308\006¢\006\f\n\004\b\031\020\032\032\004\b\033\020\034R\030\020\035\032\004\030\0010\0048\002@\002X\016¢\006\006\n\004\b\035\020\036R\027\020 \032\0020\0378\006¢\006\f\n\004\b \020!\032\004\b\"\020#¨\006$"}, d2 = {"Lokio/AsyncTimeout$Companion;", "", "<init>", "()V", "Lokio/AsyncTimeout;", "awaitTimeout$okio", "()Lokio/AsyncTimeout;", "awaitTimeout", "node", "", "cancelScheduledTimeout", "(Lokio/AsyncTimeout;)Z", "", "timeoutNanos", "hasDeadline", "", "scheduleTimeout", "(Lokio/AsyncTimeout;JZ)V", "IDLE_TIMEOUT_MILLIS", "J", "IDLE_TIMEOUT_NANOS", "", "TIMEOUT_WRITE_SIZE", "I", "Ljava/util/concurrent/locks/Condition;", "condition", "Ljava/util/concurrent/locks/Condition;", "getCondition", "()Ljava/util/concurrent/locks/Condition;", "head", "Lokio/AsyncTimeout;", "Ljava/util/concurrent/locks/ReentrantLock;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "getLock", "()Ljava/util/concurrent/locks/ReentrantLock;", "okio"})
  @SourceDebugExtension({"SMAP\nAsyncTimeout.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AsyncTimeout.kt\nokio/AsyncTimeout$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,331:1\n1#2:332\n*E\n"})
  public static final class Companion {
    private Companion() {}
    
    @NotNull
    public final ReentrantLock getLock() {
      return AsyncTimeout.lock;
    }
    
    @NotNull
    public final Condition getCondition() {
      return AsyncTimeout.condition;
    }
    
    private final void scheduleTimeout(AsyncTimeout node, long timeoutNanos, boolean hasDeadline) {
      ReentrantLock reentrantLock = AsyncTimeout.Companion.getLock();
      reentrantLock.lock();
      try {
        int $i$a$-withLock-AsyncTimeout$Companion$scheduleTimeout$1 = 0;
        if (!(!node.inQueue ? 1 : 0)) {
          int $i$a$-check-AsyncTimeout$Companion$scheduleTimeout$1$1 = 0;
          String str = "Unbalanced enter/exit";
          throw new IllegalStateException(str.toString());
        } 
        node.inQueue = true;
        if (AsyncTimeout.head == null) {
          AsyncTimeout.head = new AsyncTimeout();
          (new AsyncTimeout.Watchdog()).start();
        } 
        long now = System.nanoTime();
        if (timeoutNanos != 0L && hasDeadline) {
          node.timeoutAt = now + Math.min(timeoutNanos, node.deadlineNanoTime() - now);
        } else if (timeoutNanos != 0L) {
          node.timeoutAt = now + timeoutNanos;
        } else if (hasDeadline) {
          node.timeoutAt = node.deadlineNanoTime();
        } else {
          throw new AssertionError();
        } 
        long remainingNanos = node.remainingNanos(now);
        Intrinsics.checkNotNull(AsyncTimeout.head);
        AsyncTimeout prev = AsyncTimeout.head;
        while (true) {
          Intrinsics.checkNotNull(prev.next);
          if (prev.next == null || remainingNanos < prev.next.remainingNanos(now)) {
            node.next = prev.next;
            prev.next = node;
            if (prev == AsyncTimeout.head)
              AsyncTimeout.Companion.getCondition().signal(); 
            break;
          } 
          Intrinsics.checkNotNull(prev.next);
          prev = prev.next;
        } 
        Unit unit = Unit.INSTANCE;
      } finally {
        reentrantLock.unlock();
      } 
    }
    
    private final boolean cancelScheduledTimeout(AsyncTimeout node) {
      ReentrantLock reentrantLock = AsyncTimeout.Companion.getLock();
      reentrantLock.lock();
      try {
        int $i$a$-withLock-AsyncTimeout$Companion$cancelScheduledTimeout$1 = 0;
        if (!node.inQueue)
          return false; 
        node.inQueue = false;
        AsyncTimeout prev = AsyncTimeout.head;
        while (prev != null) {
          if (prev.next == node) {
            prev.next = node.next;
            node.next = null;
            return false;
          } 
          prev = prev.next;
        } 
        return true;
      } finally {
        reentrantLock.unlock();
      } 
    }
    
    @Nullable
    public final AsyncTimeout awaitTimeout$okio() throws InterruptedException {
      Intrinsics.checkNotNull(AsyncTimeout.head);
      AsyncTimeout node = AsyncTimeout.head.next;
      if (node == null) {
        long startNanos = System.nanoTime();
        getCondition().await(AsyncTimeout.IDLE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        Intrinsics.checkNotNull(AsyncTimeout.head);
        return (AsyncTimeout.head.next == null && System.nanoTime() - startNanos >= AsyncTimeout.IDLE_TIMEOUT_NANOS) ? AsyncTimeout.head : null;
      } 
      long waitNanos = node.remainingNanos(System.nanoTime());
      if (waitNanos > 0L) {
        getCondition().await(waitNanos, TimeUnit.NANOSECONDS);
        return null;
      } 
      Intrinsics.checkNotNull(AsyncTimeout.head);
      AsyncTimeout.head.next = node.next;
      node.next = null;
      return node;
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  private boolean inQueue;
  
  @Nullable
  private AsyncTimeout next;
  
  private long timeoutAt;
  
  @NotNull
  private static final ReentrantLock lock = new ReentrantLock();
  
  @NotNull
  private static final Condition condition = lock.newCondition();
  
  private static final int TIMEOUT_WRITE_SIZE = 65536;
  
  static {
    Intrinsics.checkNotNullExpressionValue(lock.newCondition(), "newCondition(...)");
  }
  
  private static final long IDLE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(60L);
  
  private static final long IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(IDLE_TIMEOUT_MILLIS);
  
  @Nullable
  private static AsyncTimeout head;
}
