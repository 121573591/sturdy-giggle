package okhttp3.internal.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000P\n\002\030\002\n\002\020\000\n\002\030\002\n\002\b\003\n\002\020 \n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\000\n\002\020\002\n\002\b\022\n\002\020!\n\002\b\002\n\002\020\013\n\002\b\004\n\002\020\b\n\002\b\003\n\002\030\002\n\002\b\006\030\000 02\0020\001:\003102B\017\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\023\020\b\032\b\022\004\022\0020\0070\006¢\006\004\b\b\020\tJ\037\020\017\032\0020\0162\006\020\013\032\0020\n2\006\020\r\032\0020\fH\002¢\006\004\b\017\020\020J\017\020\021\032\004\030\0010\n¢\006\004\b\021\020\022J\027\020\023\032\0020\0162\006\020\013\032\0020\nH\002¢\006\004\b\023\020\024J\r\020\025\032\0020\016¢\006\004\b\025\020\026J\027\020\032\032\0020\0162\006\020\027\032\0020\007H\000¢\006\004\b\030\020\031J\r\020\033\032\0020\007¢\006\004\b\033\020\034J\027\020\035\032\0020\0162\006\020\013\032\0020\nH\002¢\006\004\b\035\020\024R\027\020\003\032\0020\0028\006¢\006\f\n\004\b\003\020\036\032\004\b\037\020 R\032\020\"\032\b\022\004\022\0020\0070!8\002X\004¢\006\006\n\004\b\"\020#R\026\020%\032\0020$8\002@\002X\016¢\006\006\n\004\b%\020&R\026\020'\032\0020\f8\002@\002X\016¢\006\006\n\004\b'\020(R\026\020*\032\0020)8\002@\002X\016¢\006\006\n\004\b*\020+R\032\020,\032\b\022\004\022\0020\0070!8\002X\004¢\006\006\n\004\b,\020#R\024\020.\032\0020-8\002X\004¢\006\006\n\004\b.\020/¨\0063"}, d2 = {"Lokhttp3/internal/concurrent/TaskRunner;", "", "Lokhttp3/internal/concurrent/TaskRunner$Backend;", "backend", "<init>", "(Lokhttp3/internal/concurrent/TaskRunner$Backend;)V", "", "Lokhttp3/internal/concurrent/TaskQueue;", "activeQueues", "()Ljava/util/List;", "Lokhttp3/internal/concurrent/Task;", "task", "", "delayNanos", "", "afterRun", "(Lokhttp3/internal/concurrent/Task;J)V", "awaitTaskToRun", "()Lokhttp3/internal/concurrent/Task;", "beforeRun", "(Lokhttp3/internal/concurrent/Task;)V", "cancelAll", "()V", "taskQueue", "kickCoordinator$okhttp", "(Lokhttp3/internal/concurrent/TaskQueue;)V", "kickCoordinator", "newQueue", "()Lokhttp3/internal/concurrent/TaskQueue;", "runTask", "Lokhttp3/internal/concurrent/TaskRunner$Backend;", "getBackend", "()Lokhttp3/internal/concurrent/TaskRunner$Backend;", "", "busyQueues", "Ljava/util/List;", "", "coordinatorWaiting", "Z", "coordinatorWakeUpAt", "J", "", "nextQueueName", "I", "readyQueues", "Ljava/lang/Runnable;", "runnable", "Ljava/lang/Runnable;", "Companion", "Backend", "RealBackend", "okhttp"})
@SourceDebugExtension({"SMAP\nTaskRunner.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TaskRunner.kt\nokhttp3/internal/concurrent/TaskRunner\n+ 2 Util.kt\nokhttp3/internal/Util\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,314:1\n608#2,4:315\n608#2,4:319\n615#2,4:323\n608#2,4:327\n608#2,4:331\n1#3:335\n*S KotlinDebug\n*F\n+ 1 TaskRunner.kt\nokhttp3/internal/concurrent/TaskRunner\n*L\n79#1:315,4\n97#1:319,4\n108#1:323,4\n126#1:327,4\n152#1:331,4\n*E\n"})
public final class TaskRunner {
  public TaskRunner(@NotNull Backend backend) {
    this.backend = backend;
    this.nextQueueName = 10000;
    this.busyQueues = new ArrayList<>();
    this.readyQueues = new ArrayList<>();
    this.runnable = new TaskRunner$runnable$1();
  }
  
  @NotNull
  public final Backend getBackend() {
    return this.backend;
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\021\n\000\n\002\030\002\n\002\020\002\n\002\b\003*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\026¢\006\004\b\003\020\004¨\006\005"}, d2 = {"okhttp3/internal/concurrent/TaskRunner.runnable.1", "Ljava/lang/Runnable;", "", "run", "()V", "okhttp"})
  @SourceDebugExtension({"SMAP\nTaskRunner.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TaskRunner.kt\nokhttp3/internal/concurrent/TaskRunner$runnable$1\n+ 2 TaskLogger.kt\nokhttp3/internal/concurrent/TaskLoggerKt\n*L\n1#1,314:1\n35#2,19:315\n*S KotlinDebug\n*F\n+ 1 TaskRunner.kt\nokhttp3/internal/concurrent/TaskRunner$runnable$1\n*L\n62#1:315,19\n*E\n"})
  public static final class TaskRunner$runnable$1 implements Runnable {
    public void run() {
      while (true) {
        Task task;
        TaskRunner taskRunner1 = TaskRunner.this, taskRunner2 = TaskRunner.this;
        synchronized (taskRunner1) {
          int $i$a$-synchronized-TaskRunner$runnable$1$run$task$1 = 0;
          Task task1 = 
            taskRunner2.awaitTaskToRun();
        } 
        if (task1 == null)
          return; 
        Intrinsics.checkNotNull(task.getQueue$okhttp());
        TaskQueue taskQueue = task.getQueue$okhttp();
        taskRunner1 = TaskRunner.this;
        int $i$f$logElapsed = 0;
        long startNs$iv = -1L;
        boolean loggingEnabled$iv = TaskRunner.Companion.getLogger().isLoggable(Level.FINE);
        if (loggingEnabled$iv) {
          startNs$iv = taskQueue.getTaskRunner$okhttp().getBackend().nanoTime();
          TaskLoggerKt.access$log(task, taskQueue, "starting");
        } 
        boolean completedNormally$iv = false;
        try {
          Object result$iv;
          int $i$a$-logElapsed-TaskRunner$runnable$1$run$1 = 0;
          boolean completedNormally = false;
          try {
            taskRunner1.runTask(task);
            completedNormally = true;
          } finally {
            taskRunner1.getBackend().execute(this);
          } 
          completedNormally$iv = true;
          Object object1 = result$iv;
        } finally {
          if (loggingEnabled$iv) {
            long elapsedNs$iv = taskQueue.getTaskRunner$okhttp().getBackend().nanoTime() - startNs$iv;
            if (completedNormally$iv) {
              TaskLoggerKt.access$log(task, taskQueue, "finished run in " + TaskLoggerKt.formatDuration(elapsedNs$iv));
            } else {
              TaskLoggerKt.access$log(task, taskQueue, "failed a run in " + TaskLoggerKt.formatDuration(elapsedNs$iv));
            } 
          } 
        } 
      } 
    }
  }
  
  public final void kickCoordinator$okhttp(@NotNull TaskQueue taskQueue) {
    Intrinsics.checkNotNullParameter(taskQueue, "taskQueue");
    Object $this$assertThreadHoldsLock$iv = this;
    int $i$f$assertThreadHoldsLock = 0;
    if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv); 
    if (taskQueue.getActiveTask$okhttp() == null)
      if (!taskQueue.getFutureTasks$okhttp().isEmpty()) {
        Util.addIfAbsent(this.readyQueues, taskQueue);
      } else {
        this.readyQueues.remove(taskQueue);
      }  
    if (this.coordinatorWaiting) {
      this.backend.coordinatorNotify(this);
    } else {
      this.backend.execute(this.runnable);
    } 
  }
  
  private final void beforeRun(Task task) {
    Object $this$assertThreadHoldsLock$iv = this;
    int $i$f$assertThreadHoldsLock = 0;
    if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv); 
    task.setNextExecuteNanoTime$okhttp(-1L);
    Intrinsics.checkNotNull(task.getQueue$okhttp());
    TaskQueue queue = task.getQueue$okhttp();
    queue.getFutureTasks$okhttp().remove(task);
    this.readyQueues.remove(queue);
    queue.setActiveTask$okhttp(task);
    this.busyQueues.add(queue);
  }
  
  private final void runTask(Task task) {
    Object $this$assertThreadDoesntHoldLock$iv = this;
    int $i$f$assertThreadDoesntHoldLock = 0;
    if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); 
    Thread currentThread = Thread.currentThread();
    String oldName = currentThread.getName();
    currentThread.setName(task.getName());
    long delayNanos = 0L;
    delayNanos = -1L;
    try {
      delayNanos = task.runOnce();
    } finally {
      synchronized (this) {
        int $i$a$-synchronized-TaskRunner$runTask$1 = 0;
        afterRun(task, delayNanos);
        Unit unit = Unit.INSTANCE;
      } 
      currentThread.setName(oldName);
    } 
  }
  
  private final void afterRun(Task task, long delayNanos) {
    Object $this$assertThreadHoldsLock$iv = this;
    int $i$f$assertThreadHoldsLock = 0;
    if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv); 
    Intrinsics.checkNotNull(task.getQueue$okhttp());
    TaskQueue queue = task.getQueue$okhttp();
    if (!((queue.getActiveTask$okhttp() == task) ? 1 : 0)) {
      String str = "Check failed.";
      throw new IllegalStateException(str.toString());
    } 
    boolean cancelActiveTask = queue.getCancelActiveTask$okhttp();
    queue.setCancelActiveTask$okhttp(false);
    queue.setActiveTask$okhttp(null);
    this.busyQueues.remove(queue);
    if (delayNanos != -1L && !cancelActiveTask && !queue.getShutdown$okhttp())
      queue.scheduleAndDecide$okhttp(task, delayNanos, true); 
    if (!queue.getFutureTasks$okhttp().isEmpty())
      this.readyQueues.add(queue); 
  }
  
  @Nullable
  public final Task awaitTaskToRun() {
    Object $this$assertThreadHoldsLock$iv = this;
    int $i$f$assertThreadHoldsLock = 0;
    if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv); 
    while (true) {
      if (this.readyQueues.isEmpty())
        return null; 
      long now = this.backend.nanoTime();
      long minDelayNanos = Long.MAX_VALUE;
      Task readyTask = null;
      boolean multipleReadyTasks = false;
      for (TaskQueue queue : this.readyQueues) {
        Task candidate = queue.getFutureTasks$okhttp().get(0);
        long candidateDelay = Math.max(0L, candidate.getNextExecuteNanoTime$okhttp() - now);
        if (candidateDelay > 0L) {
          minDelayNanos = Math.min(candidateDelay, minDelayNanos);
          continue;
        } 
        if (readyTask != null) {
          multipleReadyTasks = true;
          break;
        } 
        readyTask = candidate;
      } 
      if (readyTask != null) {
        beforeRun(readyTask);
        if (multipleReadyTasks || (!this.coordinatorWaiting && (!this.readyQueues.isEmpty())))
          this.backend.execute(this.runnable); 
        return readyTask;
      } 
      if (this.coordinatorWaiting) {
        if (minDelayNanos < this.coordinatorWakeUpAt - now)
          this.backend.coordinatorNotify(this); 
        return null;
      } 
      this.coordinatorWaiting = true;
      this.coordinatorWakeUpAt = now + minDelayNanos;
      try {
        this.backend.coordinatorWait(this, minDelayNanos);
      } catch (InterruptedException _) {
        cancelAll();
      } finally {
        this.coordinatorWaiting = false;
      } 
    } 
  }
  
  @NotNull
  public final TaskQueue newQueue() {
    synchronized (this) {
      int $i$a$-synchronized-TaskRunner$newQueue$name$1 = 0;
      int i = this.nextQueueName;
      this.nextQueueName = i + 1;
      $i$a$-synchronized-TaskRunner$newQueue$name$1 = i;
    } 
    int name = $i$a$-synchronized-TaskRunner$newQueue$name$1;
    return new TaskQueue(this, 'Q' + name);
  }
  
  @NotNull
  public final List<TaskQueue> activeQueues() {
    synchronized (this) {
      int $i$a$-synchronized-TaskRunner$activeQueues$1 = 0;
      return CollectionsKt.plus(this.busyQueues, this.readyQueues);
    } 
  }
  
  public final void cancelAll() {
    int i;
    for (i = this.busyQueues.size() - 1; -1 < i; i--)
      ((TaskQueue)this.busyQueues.get(i)).cancelAllAndDecide$okhttp(); 
    for (i = this.readyQueues.size() - 1; -1 < i; i--) {
      TaskQueue queue = this.readyQueues.get(i);
      queue.cancelAllAndDecide$okhttp();
      if (queue.getFutureTasks$okhttp().isEmpty())
        this.readyQueues.remove(i); 
    } 
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0006\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\020\002\n\002\b\003\n\002\020\t\n\002\b\003\n\002\030\002\n\002\b\007\n\002\030\002\n\002\b\003\030\0002\0020\001B\017\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\027\020\t\032\0020\b2\006\020\007\032\0020\006H\026¢\006\004\b\t\020\nJ\027\020\013\032\0020\b2\006\020\007\032\0020\006H\026¢\006\004\b\013\020\nJ\037\020\016\032\0020\b2\006\020\007\032\0020\0062\006\020\r\032\0020\fH\026¢\006\004\b\016\020\017J\027\020\022\032\0020\b2\006\020\021\032\0020\020H\026¢\006\004\b\022\020\023J\017\020\024\032\0020\fH\026¢\006\004\b\024\020\025J\r\020\026\032\0020\b¢\006\004\b\026\020\027R\024\020\031\032\0020\0308\002X\004¢\006\006\n\004\b\031\020\032¨\006\033"}, d2 = {"Lokhttp3/internal/concurrent/TaskRunner$RealBackend;", "Lokhttp3/internal/concurrent/TaskRunner$Backend;", "Ljava/util/concurrent/ThreadFactory;", "threadFactory", "<init>", "(Ljava/util/concurrent/ThreadFactory;)V", "Lokhttp3/internal/concurrent/TaskRunner;", "taskRunner", "", "beforeTask", "(Lokhttp3/internal/concurrent/TaskRunner;)V", "coordinatorNotify", "", "nanos", "coordinatorWait", "(Lokhttp3/internal/concurrent/TaskRunner;J)V", "Ljava/lang/Runnable;", "runnable", "execute", "(Ljava/lang/Runnable;)V", "nanoTime", "()J", "shutdown", "()V", "Ljava/util/concurrent/ThreadPoolExecutor;", "executor", "Ljava/util/concurrent/ThreadPoolExecutor;", "okhttp"})
  @SourceDebugExtension({"SMAP\nTaskRunner.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TaskRunner.kt\nokhttp3/internal/concurrent/TaskRunner$RealBackend\n+ 2 Util.kt\nokhttp3/internal/Util\n*L\n1#1,314:1\n560#2:315\n*S KotlinDebug\n*F\n+ 1 TaskRunner.kt\nokhttp3/internal/concurrent/TaskRunner$RealBackend\n*L\n281#1:315\n*E\n"})
  public static final class RealBackend implements Backend {
    @NotNull
    private final ThreadPoolExecutor executor;
    
    public RealBackend(@NotNull ThreadFactory threadFactory) {
      this.executor = new ThreadPoolExecutor(0, 2147483647, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), threadFactory);
    }
    
    public void beforeTask(@NotNull TaskRunner taskRunner) {
      Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
    }
    
    public long nanoTime() {
      return System.nanoTime();
    }
    
    public void coordinatorNotify(@NotNull TaskRunner taskRunner) {
      Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
      Object $this$notify$iv = taskRunner;
      int $i$f$notify = 0;
      $this$notify$iv.notify();
    }
    
    public void coordinatorWait(@NotNull TaskRunner taskRunner, long nanos) throws InterruptedException {
      Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
      long ms = nanos / 1000000L;
      long ns = nanos - ms * 1000000L;
      if (ms > 0L || nanos > 0L)
        taskRunner.wait(ms, (int)ns); 
    }
    
    public void execute(@NotNull Runnable runnable) {
      Intrinsics.checkNotNullParameter(runnable, "runnable");
      this.executor.execute(runnable);
    }
    
    public final void shutdown() {
      this.executor.shutdown();
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\024\020\005\032\0020\0048\006X\004¢\006\006\n\004\b\005\020\006R\027\020\b\032\0020\0078\006¢\006\f\n\004\b\b\020\t\032\004\b\n\020\013¨\006\f"}, d2 = {"Lokhttp3/internal/concurrent/TaskRunner$Companion;", "", "<init>", "()V", "Lokhttp3/internal/concurrent/TaskRunner;", "INSTANCE", "Lokhttp3/internal/concurrent/TaskRunner;", "Ljava/util/logging/Logger;", "logger", "Ljava/util/logging/Logger;", "getLogger", "()Ljava/util/logging/Logger;", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @NotNull
    public final Logger getLogger() {
      return TaskRunner.logger;
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final Backend backend;
  
  private int nextQueueName;
  
  private boolean coordinatorWaiting;
  
  private long coordinatorWakeUpAt;
  
  @NotNull
  private final List<TaskQueue> busyQueues;
  
  @NotNull
  private final List<TaskQueue> readyQueues;
  
  @NotNull
  private final Runnable runnable;
  
  @JvmField
  @NotNull
  public static final TaskRunner INSTANCE = new TaskRunner(new RealBackend(Util.threadFactory(Util.okHttpName + " TaskRunner", true)));
  
  @NotNull
  private static final Logger logger = Logger.getLogger(TaskRunner.class.getName());
  
  static {
    Intrinsics.checkNotNullExpressionValue(Logger.getLogger(TaskRunner.class.getName()), "getLogger(TaskRunner::class.java.name)");
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000&\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\020\002\n\002\b\003\n\002\020\t\n\002\b\003\n\002\030\002\n\002\b\006\bf\030\0002\0020\001J\027\020\005\032\0020\0042\006\020\003\032\0020\002H&¢\006\004\b\005\020\006J\027\020\007\032\0020\0042\006\020\003\032\0020\002H&¢\006\004\b\007\020\006J\037\020\n\032\0020\0042\006\020\003\032\0020\0022\006\020\t\032\0020\bH&¢\006\004\b\n\020\013J\027\020\016\032\0020\0042\006\020\r\032\0020\fH&¢\006\004\b\016\020\017J\017\020\020\032\0020\bH&¢\006\004\b\020\020\021¨\006\022"}, d2 = {"Lokhttp3/internal/concurrent/TaskRunner$Backend;", "", "Lokhttp3/internal/concurrent/TaskRunner;", "taskRunner", "", "beforeTask", "(Lokhttp3/internal/concurrent/TaskRunner;)V", "coordinatorNotify", "", "nanos", "coordinatorWait", "(Lokhttp3/internal/concurrent/TaskRunner;J)V", "Ljava/lang/Runnable;", "runnable", "execute", "(Ljava/lang/Runnable;)V", "nanoTime", "()J", "okhttp"})
  public static interface Backend {
    void beforeTask(@NotNull TaskRunner param1TaskRunner);
    
    long nanoTime();
    
    void coordinatorNotify(@NotNull TaskRunner param1TaskRunner);
    
    void coordinatorWait(@NotNull TaskRunner param1TaskRunner, long param1Long);
    
    void execute(@NotNull Runnable param1Runnable);
  }
}
