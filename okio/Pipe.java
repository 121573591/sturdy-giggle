package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000T\n\002\030\002\n\002\020\000\n\002\020\t\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\003\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\004\n\002\020\013\n\002\b\006\n\002\030\002\n\002\b\b\n\002\030\002\n\002\b\017\030\0002\0020\001B\017\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\r\020\007\032\0020\006¢\006\004\b\007\020\bJ\025\020\013\032\0020\0062\006\020\n\032\0020\t¢\006\004\b\013\020\fJ\017\020\n\032\0020\tH\007¢\006\004\b\r\020\016J\017\020\022\032\0020\017H\007¢\006\004\b\020\020\021J-\020\026\032\0020\006*\0020\t2\027\020\025\032\023\022\004\022\0020\t\022\004\022\0020\0060\023¢\006\002\b\024H\b¢\006\004\b\026\020\027R\032\020\031\032\0020\0308\000X\004¢\006\f\n\004\b\031\020\032\032\004\b\033\020\034R\"\020\036\032\0020\0358\000@\000X\016¢\006\022\n\004\b\036\020\037\032\004\b \020!\"\004\b\"\020#R\027\020%\032\0020$8\006¢\006\f\n\004\b%\020&\032\004\b'\020(R$\020)\032\004\030\0010\t8\000@\000X\016¢\006\022\n\004\b)\020*\032\004\b+\020\016\"\004\b,\020\fR\027\020.\032\0020-8\006¢\006\f\n\004\b.\020/\032\004\b0\0201R\032\020\003\032\0020\0028\000X\004¢\006\f\n\004\b\003\0202\032\004\b3\0204R\027\020\n\032\0020\t8G¢\006\f\n\004\b\n\020*\032\004\b\n\020\016R\"\0205\032\0020\0358\000@\000X\016¢\006\022\n\004\b5\020\037\032\004\b6\020!\"\004\b7\020#R\027\020\022\032\0020\0178G¢\006\f\n\004\b\022\0208\032\004\b\022\020\021R\"\0209\032\0020\0358\000@\000X\016¢\006\022\n\004\b9\020\037\032\004\b:\020!\"\004\b;\020#¨\006<"}, d2 = {"Lokio/Pipe;", "", "", "maxBufferSize", "<init>", "(J)V", "", "cancel", "()V", "Lokio/Sink;", "sink", "fold", "(Lokio/Sink;)V", "-deprecated_sink", "()Lokio/Sink;", "Lokio/Source;", "-deprecated_source", "()Lokio/Source;", "source", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "block", "forward", "(Lokio/Sink;Lkotlin/jvm/functions/Function1;)V", "Lokio/Buffer;", "buffer", "Lokio/Buffer;", "getBuffer$okio", "()Lokio/Buffer;", "", "canceled", "Z", "getCanceled$okio", "()Z", "setCanceled$okio", "(Z)V", "Ljava/util/concurrent/locks/Condition;", "condition", "Ljava/util/concurrent/locks/Condition;", "getCondition", "()Ljava/util/concurrent/locks/Condition;", "foldedSink", "Lokio/Sink;", "getFoldedSink$okio", "setFoldedSink$okio", "Ljava/util/concurrent/locks/ReentrantLock;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "getLock", "()Ljava/util/concurrent/locks/ReentrantLock;", "J", "getMaxBufferSize$okio", "()J", "sinkClosed", "getSinkClosed$okio", "setSinkClosed$okio", "Lokio/Source;", "sourceClosed", "getSourceClosed$okio", "setSourceClosed$okio", "okio"})
@SourceDebugExtension({"SMAP\nPipe.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Pipe.kt\nokio/Pipe\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Timeout.kt\nokio/Timeout\n*L\n1#1,257:1\n1#2:258\n268#3,26:259\n*S KotlinDebug\n*F\n+ 1 Pipe.kt\nokio/Pipe\n*L\n217#1:259,26\n*E\n"})
public final class Pipe {
  private final long maxBufferSize;
  
  @NotNull
  private final Buffer buffer;
  
  private boolean canceled;
  
  private boolean sinkClosed;
  
  private boolean sourceClosed;
  
  @Nullable
  private Sink foldedSink;
  
  @NotNull
  private final ReentrantLock lock;
  
  @NotNull
  private final Condition condition;
  
  @NotNull
  private final Sink sink;
  
  @NotNull
  private final Source source;
  
  public Pipe(long maxBufferSize) {
    this.maxBufferSize = maxBufferSize;
    this.buffer = new Buffer();
    this.lock = new ReentrantLock();
    Intrinsics.checkNotNullExpressionValue(this.lock.newCondition(), "newCondition(...)");
    this.condition = this.lock.newCondition();
    if (!((this.maxBufferSize >= 1L) ? 1 : 0)) {
      int $i$a$-require-Pipe$1 = 0;
      String str = "maxBufferSize < 1: " + this.maxBufferSize;
      throw new IllegalArgumentException(str.toString());
    } 
    this.sink = new Pipe$sink$1();
    this.source = new Pipe$source$1();
  }
  
  public final long getMaxBufferSize$okio() {
    return this.maxBufferSize;
  }
  
  @NotNull
  public final Buffer getBuffer$okio() {
    return this.buffer;
  }
  
  public final boolean getCanceled$okio() {
    return this.canceled;
  }
  
  public final void setCanceled$okio(boolean <set-?>) {
    this.canceled = <set-?>;
  }
  
  public final boolean getSinkClosed$okio() {
    return this.sinkClosed;
  }
  
  public final void setSinkClosed$okio(boolean <set-?>) {
    this.sinkClosed = <set-?>;
  }
  
  public final boolean getSourceClosed$okio() {
    return this.sourceClosed;
  }
  
  public final void setSourceClosed$okio(boolean <set-?>) {
    this.sourceClosed = <set-?>;
  }
  
  @Nullable
  public final Sink getFoldedSink$okio() {
    return this.foldedSink;
  }
  
  public final void setFoldedSink$okio(@Nullable Sink <set-?>) {
    this.foldedSink = <set-?>;
  }
  
  @NotNull
  public final ReentrantLock getLock() {
    return this.lock;
  }
  
  @NotNull
  public final Condition getCondition() {
    return this.condition;
  }
  
  @JvmName(name = "sink")
  @NotNull
  public final Sink sink() {
    return this.sink;
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000'\n\000\n\002\030\002\n\002\020\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\005*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\026¢\006\004\b\003\020\004J\017\020\005\032\0020\002H\026¢\006\004\b\005\020\004J\017\020\007\032\0020\006H\026¢\006\004\b\007\020\bJ\037\020\r\032\0020\0022\006\020\n\032\0020\t2\006\020\f\032\0020\013H\026¢\006\004\b\r\020\016R\024\020\007\032\0020\0068\002X\004¢\006\006\n\004\b\007\020\017¨\006\020"}, d2 = {"okio/Pipe.sink.1", "Lokio/Sink;", "", "close", "()V", "flush", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "Lokio/Buffer;", "source", "", "byteCount", "write", "(Lokio/Buffer;J)V", "Lokio/Timeout;", "okio"})
  @SourceDebugExtension({"SMAP\nPipe.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Pipe.kt\nokio/Pipe$sink$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Pipe.kt\nokio/Pipe\n+ 4 Timeout.kt\nokio/Timeout\n*L\n1#1,257:1\n1#2:258\n217#3:259\n218#3:286\n217#3:287\n218#3:314\n217#3:315\n218#3:342\n268#4,26:260\n268#4,26:288\n268#4,26:316\n*S KotlinDebug\n*F\n+ 1 Pipe.kt\nokio/Pipe$sink$1\n*L\n87#1:259\n87#1:286\n106#1:287\n106#1:314\n124#1:315\n124#1:342\n87#1:260,26\n106#1:288,26\n124#1:316,26\n*E\n"})
  public static final class Pipe$sink$1 implements Sink {
    @NotNull
    private final Timeout timeout = new Timeout();
    
    public void write(@NotNull Buffer source, long byteCount) {
      Intrinsics.checkNotNullParameter(source, "source");
      long l1 = 0L;
      l1 = byteCount;
      Object delegate = null;
      ReentrantLock reentrantLock = Pipe.this.getLock();
      Pipe pipe = Pipe.this;
      reentrantLock.lock();
      try {
        int $i$a$-withLock-Pipe$sink$1$write$1 = 0;
        if (!(!pipe.getSinkClosed$okio() ? 1 : 0)) {
          int $i$a$-check-Pipe$sink$1$write$1$1 = 0;
          String str = "closed";
          throw new IllegalStateException(str.toString());
        } 
        if (pipe.getCanceled$okio())
          throw new IOException("canceled"); 
        while (true) {
          if (l1 > 0L) {
            if (pipe.getFoldedSink$okio() != null) {
              Sink it = pipe.getFoldedSink$okio();
              int $i$a$-let-Pipe$sink$1$write$1$2 = 0;
              delegate = it;
              break;
            } 
            pipe.getFoldedSink$okio();
            if (pipe.getSourceClosed$okio())
              throw new IOException("source is closed"); 
            long bufferSpaceAvailable = pipe.getMaxBufferSize$okio() - pipe.getBuffer$okio().size();
            if (bufferSpaceAvailable == 0L) {
              this.timeout.awaitSignal(pipe.getCondition());
              if (pipe.getCanceled$okio())
                throw new IOException("canceled"); 
              continue;
            } 
            long bytesToWrite = Math.min(bufferSpaceAvailable, l1);
            pipe.getBuffer$okio().write(source, bytesToWrite);
            l1 -= bytesToWrite;
            pipe.getCondition().signalAll();
            continue;
          } 
          break;
        } 
        Unit unit = Unit.INSTANCE;
      } finally {
        reentrantLock.unlock();
      } 
      Object object1 = delegate;
      pipe = Pipe.this;
      Object object2 = object1;
      int $i$f$forward = 0;
      Timeout timeout1 = object2.timeout(), other$iv$iv = pipe.sink().timeout();
      int $i$f$intersectWith = 0;
      long originalTimeout$iv$iv = timeout1.timeoutNanos();
      timeout1.timeout(Timeout.Companion.minTimeout(other$iv$iv.timeoutNanos(), timeout1.timeoutNanos()), TimeUnit.NANOSECONDS);
      if (timeout1.hasDeadline()) {
        long originalDeadline$iv$iv = timeout1.deadlineNanoTime();
        if (other$iv$iv.hasDeadline())
          timeout1.deadlineNanoTime(Math.min(timeout1.deadlineNanoTime(), other$iv$iv.deadlineNanoTime())); 
        try {
          int $i$a$-intersectWith-Pipe$forward$1$iv = 0;
          Object object = object2;
          int $i$a$-forward-Pipe$sink$1$write$2 = 0;
          object.write(source, l1);
          Unit unit = Unit.INSTANCE;
        } finally {
          timeout1.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
          if (other$iv$iv.hasDeadline())
            timeout1.deadlineNanoTime(originalDeadline$iv$iv); 
        } 
      } else {
        if (other$iv$iv.hasDeadline())
          timeout1.deadlineNanoTime(other$iv$iv.deadlineNanoTime()); 
        try {
          int $i$a$-intersectWith-Pipe$forward$1$iv = 0;
          Object object = object2;
          int $i$a$-forward-Pipe$sink$1$write$2 = 0;
          object.write(source, l1);
          Unit unit = Unit.INSTANCE;
        } finally {
          timeout1.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
          if (other$iv$iv.hasDeadline())
            timeout1.clearDeadline(); 
        } 
      } 
    }
    
    public void flush() {
      Object delegate = null;
      ReentrantLock reentrantLock = Pipe.this.getLock();
      Pipe pipe = Pipe.this;
      reentrantLock.lock();
      try {
        int $i$a$-withLock-Pipe$sink$1$flush$1 = 0;
        if (!(!pipe.getSinkClosed$okio() ? 1 : 0)) {
          int $i$a$-check-Pipe$sink$1$flush$1$1 = 0;
          String str = "closed";
          throw new IllegalStateException(str.toString());
        } 
        if (pipe.getCanceled$okio())
          throw new IOException("canceled"); 
        if (pipe.getFoldedSink$okio() != null) {
          Sink it = pipe.getFoldedSink$okio();
          int $i$a$-let-Pipe$sink$1$flush$1$2 = 0;
          delegate = it;
        } else {
          pipe.getFoldedSink$okio();
          if (pipe.getSourceClosed$okio() && pipe.getBuffer$okio().size() > 0L)
            throw new IOException("source is closed"); 
        } 
        Unit unit = Unit.INSTANCE;
      } finally {
        reentrantLock.unlock();
      } 
      Object object1 = delegate;
      pipe = Pipe.this;
      Object object2 = object1;
      int $i$f$forward = 0;
      Timeout timeout1 = object2.timeout(), other$iv$iv = pipe.sink().timeout();
      int $i$f$intersectWith = 0;
      long originalTimeout$iv$iv = timeout1.timeoutNanos();
      timeout1.timeout(Timeout.Companion.minTimeout(other$iv$iv.timeoutNanos(), timeout1.timeoutNanos()), TimeUnit.NANOSECONDS);
      if (timeout1.hasDeadline()) {
        long originalDeadline$iv$iv = timeout1.deadlineNanoTime();
        if (other$iv$iv.hasDeadline())
          timeout1.deadlineNanoTime(Math.min(timeout1.deadlineNanoTime(), other$iv$iv.deadlineNanoTime())); 
        try {
          int $i$a$-intersectWith-Pipe$forward$1$iv = 0;
          Object object = object2;
          int $i$a$-forward-Pipe$sink$1$flush$2 = 0;
          object.flush();
          Unit unit = Unit.INSTANCE;
        } finally {
          timeout1.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
          if (other$iv$iv.hasDeadline())
            timeout1.deadlineNanoTime(originalDeadline$iv$iv); 
        } 
      } else {
        if (other$iv$iv.hasDeadline())
          timeout1.deadlineNanoTime(other$iv$iv.deadlineNanoTime()); 
        try {
          int $i$a$-intersectWith-Pipe$forward$1$iv = 0;
          Object object = object2;
          int $i$a$-forward-Pipe$sink$1$flush$2 = 0;
          object.flush();
          object = Unit.INSTANCE;
        } finally {
          timeout1.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
          if (other$iv$iv.hasDeadline())
            timeout1.clearDeadline(); 
        } 
      } 
    }
    
    public void close() {
      Object delegate = null;
      ReentrantLock reentrantLock = Pipe.this.getLock();
      Pipe pipe = Pipe.this;
      reentrantLock.lock();
      try {
        int $i$a$-withLock-Pipe$sink$1$close$1 = 0;
        if (pipe.getSinkClosed$okio())
          return; 
        if (pipe.getFoldedSink$okio() != null) {
          Sink it = pipe.getFoldedSink$okio();
          int $i$a$-let-Pipe$sink$1$close$1$1 = 0;
          delegate = it;
        } else {
          pipe.getFoldedSink$okio();
          if (pipe.getSourceClosed$okio() && pipe.getBuffer$okio().size() > 0L)
            throw new IOException("source is closed"); 
          pipe.setSinkClosed$okio(true);
          pipe.getCondition().signalAll();
        } 
        Unit unit = Unit.INSTANCE;
      } finally {
        reentrantLock.unlock();
      } 
      Object object1 = delegate;
      pipe = Pipe.this;
      Object object2 = object1;
      int $i$f$forward = 0;
      Timeout timeout1 = object2.timeout(), other$iv$iv = pipe.sink().timeout();
      int $i$f$intersectWith = 0;
      long originalTimeout$iv$iv = timeout1.timeoutNanos();
      timeout1.timeout(Timeout.Companion.minTimeout(other$iv$iv.timeoutNanos(), timeout1.timeoutNanos()), TimeUnit.NANOSECONDS);
      if (timeout1.hasDeadline()) {
        long originalDeadline$iv$iv = timeout1.deadlineNanoTime();
        if (other$iv$iv.hasDeadline())
          timeout1.deadlineNanoTime(Math.min(timeout1.deadlineNanoTime(), other$iv$iv.deadlineNanoTime())); 
        try {
          int $i$a$-intersectWith-Pipe$forward$1$iv = 0;
          Object object = object2;
          int $i$a$-forward-Pipe$sink$1$close$2 = 0;
          object.close();
          Unit unit = Unit.INSTANCE;
        } finally {
          timeout1.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
          if (other$iv$iv.hasDeadline())
            timeout1.deadlineNanoTime(originalDeadline$iv$iv); 
        } 
      } else {
        if (other$iv$iv.hasDeadline())
          timeout1.deadlineNanoTime(other$iv$iv.deadlineNanoTime()); 
        try {
          int $i$a$-intersectWith-Pipe$forward$1$iv = 0;
          Object object = object2;
          int $i$a$-forward-Pipe$sink$1$close$2 = 0;
          object.close();
          object = Unit.INSTANCE;
        } finally {
          timeout1.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
          if (other$iv$iv.hasDeadline())
            timeout1.clearDeadline(); 
        } 
      } 
    }
    
    @NotNull
    public Timeout timeout() {
      return this.timeout;
    }
  }
  
  @JvmName(name = "source")
  @NotNull
  public final Source source() {
    return this.source;
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000'\n\000\n\002\030\002\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\030\002\n\002\b\004*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\026¢\006\004\b\003\020\004J\037\020\t\032\0020\0072\006\020\006\032\0020\0052\006\020\b\032\0020\007H\026¢\006\004\b\t\020\nJ\017\020\f\032\0020\013H\026¢\006\004\b\f\020\rR\024\020\f\032\0020\0138\002X\004¢\006\006\n\004\b\f\020\016¨\006\017"}, d2 = {"okio/Pipe.source.1", "Lokio/Source;", "", "close", "()V", "Lokio/Buffer;", "sink", "", "byteCount", "read", "(Lokio/Buffer;J)J", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "Lokio/Timeout;", "okio"})
  @SourceDebugExtension({"SMAP\nPipe.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Pipe.kt\nokio/Pipe$source$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,257:1\n1#2:258\n*E\n"})
  public static final class Pipe$source$1 implements Source {
    @NotNull
    private final Timeout timeout;
    
    Pipe$source$1() {
      this.timeout = new Timeout();
    }
    
    public long read(@NotNull Buffer sink, long byteCount) {
      Intrinsics.checkNotNullParameter(sink, "sink");
      ReentrantLock reentrantLock = Pipe.this.getLock();
      Pipe pipe = Pipe.this;
      reentrantLock.lock();
      try {
        int $i$a$-withLock-Pipe$source$1$read$1 = 0;
        if (!(!pipe.getSourceClosed$okio() ? 1 : 0)) {
          int $i$a$-check-Pipe$source$1$read$1$1 = 0;
          String str = "closed";
          throw new IllegalStateException(str.toString());
        } 
        if (pipe.getCanceled$okio())
          throw new IOException("canceled"); 
        while (pipe.getBuffer$okio().size() == 0L) {
          if (pipe.getSinkClosed$okio())
            return -1L; 
          this.timeout.awaitSignal(pipe.getCondition());
          if (pipe.getCanceled$okio())
            throw new IOException("canceled"); 
        } 
        long result = pipe.getBuffer$okio().read(sink, byteCount);
        pipe.getCondition().signalAll();
        return result;
      } finally {
        reentrantLock.unlock();
      } 
    }
    
    public void close() {
      ReentrantLock reentrantLock = Pipe.this.getLock();
      Pipe pipe = Pipe.this;
      reentrantLock.lock();
      try {
        int $i$a$-withLock-Pipe$source$1$close$1 = 0;
        pipe.setSourceClosed$okio(true);
        pipe.getCondition().signalAll();
        Unit unit = Unit.INSTANCE;
      } finally {
        reentrantLock.unlock();
      } 
    }
    
    @NotNull
    public Timeout timeout() {
      return this.timeout;
    }
  }
  
  public final void fold(@NotNull Sink sink) throws IOException {
    Intrinsics.checkNotNullParameter(sink, "sink");
    boolean closed = false;
    Object sinkBuffer = null;
    ReentrantLock reentrantLock = this.lock;
    reentrantLock.lock();
    try {
    
    } finally {
      reentrantLock.unlock();
    } 
  }
  
  private final void forward(Sink $this$forward, Function1 block) {
    int $i$f$forward = 0;
    Timeout timeout1 = $this$forward.timeout(), other$iv = sink().timeout();
    int $i$f$intersectWith = 0;
    long originalTimeout$iv = timeout1.timeoutNanos();
    timeout1.timeout(Timeout.Companion.minTimeout(other$iv.timeoutNanos(), timeout1.timeoutNanos()), TimeUnit.NANOSECONDS);
    if (timeout1.hasDeadline()) {
      long originalDeadline$iv = timeout1.deadlineNanoTime();
      if (other$iv.hasDeadline())
        timeout1.deadlineNanoTime(Math.min(timeout1.deadlineNanoTime(), other$iv.deadlineNanoTime())); 
      try {
        int $i$a$-intersectWith-Pipe$forward$1 = 0;
        block.invoke($this$forward);
        Unit unit = Unit.INSTANCE;
      } finally {
        InlineMarker.finallyStart(1);
        timeout1.timeout(originalTimeout$iv, TimeUnit.NANOSECONDS);
        if (other$iv.hasDeadline())
          timeout1.deadlineNanoTime(originalDeadline$iv); 
        InlineMarker.finallyEnd(1);
      } 
    } else {
      if (other$iv.hasDeadline())
        timeout1.deadlineNanoTime(other$iv.deadlineNanoTime()); 
      try {
        int $i$a$-intersectWith-Pipe$forward$1 = 0;
        block.invoke($this$forward);
        Unit unit = Unit.INSTANCE;
      } finally {
        InlineMarker.finallyStart(1);
        timeout1.timeout(originalTimeout$iv, TimeUnit.NANOSECONDS);
        if (other$iv.hasDeadline())
          timeout1.clearDeadline(); 
        InlineMarker.finallyEnd(1);
      } 
    } 
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "sink", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_sink")
  @NotNull
  public final Sink -deprecated_sink() {
    return this.sink;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "source", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_source")
  @NotNull
  public final Source -deprecated_source() {
    return this.source;
  }
  
  public final void cancel() {
    ReentrantLock reentrantLock = this.lock;
    reentrantLock.lock();
    try {
      int $i$a$-withLock-Pipe$cancel$1 = 0;
      this.canceled = true;
      this.buffer.clear();
      this.condition.signalAll();
      Unit unit = Unit.INSTANCE;
    } finally {
      reentrantLock.unlock();
    } 
  }
}
