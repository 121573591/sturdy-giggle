package okhttp3.internal.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000V\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\020\016\n\002\b\003\n\002\020\002\n\002\b\002\n\002\020\013\n\002\b\003\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\024\n\002\020!\n\002\b\006\n\002\020 \n\002\b\t\030\0002\0020\001:\001?B\031\b\000\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007J\r\020\t\032\0020\b¢\006\004\b\t\020\nJ\017\020\016\032\0020\013H\000¢\006\004\b\f\020\rJ?\020\024\032\0020\b2\006\020\005\032\0020\0042\b\b\002\020\020\032\0020\0172\b\b\002\020\021\032\0020\0132\016\b\004\020\023\032\b\022\004\022\0020\b0\022H\bø\001\000¢\006\004\b\024\020\025J\r\020\027\032\0020\026¢\006\004\b\027\020\030J5\020\031\032\0020\b2\006\020\005\032\0020\0042\b\b\002\020\020\032\0020\0172\016\b\004\020\023\032\b\022\004\022\0020\0170\022H\bø\001\000¢\006\004\b\031\020\032J\037\020\031\032\0020\b2\006\020\034\032\0020\0332\b\b\002\020\020\032\0020\017¢\006\004\b\031\020\035J'\020!\032\0020\0132\006\020\034\032\0020\0332\006\020\020\032\0020\0172\006\020\036\032\0020\013H\000¢\006\004\b\037\020 J\r\020\"\032\0020\b¢\006\004\b\"\020\nJ\017\020#\032\0020\004H\026¢\006\004\b#\020$R$\020%\032\004\030\0010\0338\000@\000X\016¢\006\022\n\004\b%\020&\032\004\b'\020(\"\004\b)\020*R\"\020+\032\0020\0138\000@\000X\016¢\006\022\n\004\b+\020,\032\004\b-\020\r\"\004\b.\020/R \0201\032\b\022\004\022\0020\033008\000X\004¢\006\f\n\004\b1\0202\032\004\b3\0204R\032\020\005\032\0020\0048\000X\004¢\006\f\n\004\b\005\0205\032\004\b6\020$R\027\0209\032\b\022\004\022\0020\033078F¢\006\006\032\004\b8\0204R\"\020\"\032\0020\0138\000@\000X\016¢\006\022\n\004\b\"\020,\032\004\b:\020\r\"\004\b;\020/R\032\020\003\032\0020\0028\000X\004¢\006\f\n\004\b\003\020<\032\004\b=\020>\002\007\n\005\b20\001¨\006@"}, d2 = {"Lokhttp3/internal/concurrent/TaskQueue;", "", "Lokhttp3/internal/concurrent/TaskRunner;", "taskRunner", "", "name", "<init>", "(Lokhttp3/internal/concurrent/TaskRunner;Ljava/lang/String;)V", "", "cancelAll", "()V", "", "cancelAllAndDecide$okhttp", "()Z", "cancelAllAndDecide", "", "delayNanos", "cancelable", "Lkotlin/Function0;", "block", "execute", "(Ljava/lang/String;JZLkotlin/jvm/functions/Function0;)V", "Ljava/util/concurrent/CountDownLatch;", "idleLatch", "()Ljava/util/concurrent/CountDownLatch;", "schedule", "(Ljava/lang/String;JLkotlin/jvm/functions/Function0;)V", "Lokhttp3/internal/concurrent/Task;", "task", "(Lokhttp3/internal/concurrent/Task;J)V", "recurrence", "scheduleAndDecide$okhttp", "(Lokhttp3/internal/concurrent/Task;JZ)Z", "scheduleAndDecide", "shutdown", "toString", "()Ljava/lang/String;", "activeTask", "Lokhttp3/internal/concurrent/Task;", "getActiveTask$okhttp", "()Lokhttp3/internal/concurrent/Task;", "setActiveTask$okhttp", "(Lokhttp3/internal/concurrent/Task;)V", "cancelActiveTask", "Z", "getCancelActiveTask$okhttp", "setCancelActiveTask$okhttp", "(Z)V", "", "futureTasks", "Ljava/util/List;", "getFutureTasks$okhttp", "()Ljava/util/List;", "Ljava/lang/String;", "getName$okhttp", "", "getScheduledTasks", "scheduledTasks", "getShutdown$okhttp", "setShutdown$okhttp", "Lokhttp3/internal/concurrent/TaskRunner;", "getTaskRunner$okhttp", "()Lokhttp3/internal/concurrent/TaskRunner;", "AwaitIdleTask", "okhttp"})
@SourceDebugExtension({"SMAP\nTaskQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TaskQueue.kt\nokhttp3/internal/concurrent/TaskQueue\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 TaskLogger.kt\nokhttp3/internal/concurrent/TaskLoggerKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 Util.kt\nokhttp3/internal/Util\n*L\n1#1,218:1\n1#2:219\n25#3,4:220\n25#3,4:224\n25#3,4:228\n25#3,4:232\n25#3,4:251\n350#4,7:236\n615#5,4:243\n615#5,4:247\n*S KotlinDebug\n*F\n+ 1 TaskQueue.kt\nokhttp3/internal/concurrent/TaskQueue\n*L\n65#1:220,4\n68#1:224,4\n153#1:228,4\n159#1:232,4\n208#1:251,4\n165#1:236,7\n179#1:243,4\n189#1:247,4\n*E\n"})
public final class TaskQueue {
  @NotNull
  private final TaskRunner taskRunner;
  
  @NotNull
  private final String name;
  
  private boolean shutdown;
  
  @Nullable
  private Task activeTask;
  
  @NotNull
  private final List<Task> futureTasks;
  
  private boolean cancelActiveTask;
  
  public TaskQueue(@NotNull TaskRunner taskRunner, @NotNull String name) {
    this.taskRunner = taskRunner;
    this.name = name;
    this.futureTasks = new ArrayList<>();
  }
  
  @NotNull
  public final TaskRunner getTaskRunner$okhttp() {
    return this.taskRunner;
  }
  
  @NotNull
  public final String getName$okhttp() {
    return this.name;
  }
  
  public final boolean getShutdown$okhttp() {
    return this.shutdown;
  }
  
  public final void setShutdown$okhttp(boolean <set-?>) {
    this.shutdown = <set-?>;
  }
  
  @Nullable
  public final Task getActiveTask$okhttp() {
    return this.activeTask;
  }
  
  public final void setActiveTask$okhttp(@Nullable Task <set-?>) {
    this.activeTask = <set-?>;
  }
  
  @NotNull
  public final List<Task> getFutureTasks$okhttp() {
    return this.futureTasks;
  }
  
  public final boolean getCancelActiveTask$okhttp() {
    return this.cancelActiveTask;
  }
  
  public final void setCancelActiveTask$okhttp(boolean <set-?>) {
    this.cancelActiveTask = <set-?>;
  }
  
  @NotNull
  public final List<Task> getScheduledTasks() {
    synchronized (this.taskRunner) {
      int $i$a$-synchronized-TaskQueue$scheduledTasks$1 = 0;
      List<Task> list = CollectionsKt.toList(this.futureTasks);
    } 
    return list;
  }
  
  public final void schedule(@NotNull Task task, long delayNanos) {
    // Byte code:
    //   0: aload_1
    //   1: ldc 'task'
    //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_0
    //   7: getfield taskRunner : Lokhttp3/internal/concurrent/TaskRunner;
    //   10: astore #4
    //   12: aload #4
    //   14: monitorenter
    //   15: nop
    //   16: iconst_0
    //   17: istore #5
    //   19: aload_0
    //   20: getfield shutdown : Z
    //   23: ifeq -> 128
    //   26: aload_1
    //   27: invokevirtual getCancelable : ()Z
    //   30: ifeq -> 79
    //   33: iconst_0
    //   34: istore #6
    //   36: getstatic okhttp3/internal/concurrent/TaskRunner.Companion : Lokhttp3/internal/concurrent/TaskRunner$Companion;
    //   39: invokevirtual getLogger : ()Ljava/util/logging/Logger;
    //   42: getstatic java/util/logging/Level.FINE : Ljava/util/logging/Level;
    //   45: invokevirtual isLoggable : (Ljava/util/logging/Level;)Z
    //   48: ifeq -> 73
    //   51: aload_1
    //   52: aload_0
    //   53: astore #7
    //   55: astore #8
    //   57: iconst_0
    //   58: istore #9
    //   60: ldc 'schedule canceled (queue is shutdown)'
    //   62: astore #10
    //   64: aload #8
    //   66: aload #7
    //   68: aload #10
    //   70: invokestatic access$log : (Lokhttp3/internal/concurrent/Task;Lokhttp3/internal/concurrent/TaskQueue;Ljava/lang/String;)V
    //   73: nop
    //   74: nop
    //   75: aload #4
    //   77: monitorexit
    //   78: return
    //   79: iconst_0
    //   80: istore #6
    //   82: getstatic okhttp3/internal/concurrent/TaskRunner.Companion : Lokhttp3/internal/concurrent/TaskRunner$Companion;
    //   85: invokevirtual getLogger : ()Ljava/util/logging/Logger;
    //   88: getstatic java/util/logging/Level.FINE : Ljava/util/logging/Level;
    //   91: invokevirtual isLoggable : (Ljava/util/logging/Level;)Z
    //   94: ifeq -> 119
    //   97: aload_1
    //   98: aload_0
    //   99: astore #7
    //   101: astore #8
    //   103: iconst_0
    //   104: istore #9
    //   106: ldc 'schedule failed (queue is shutdown)'
    //   108: astore #10
    //   110: aload #8
    //   112: aload #7
    //   114: aload #10
    //   116: invokestatic access$log : (Lokhttp3/internal/concurrent/Task;Lokhttp3/internal/concurrent/TaskQueue;Ljava/lang/String;)V
    //   119: nop
    //   120: new java/util/concurrent/RejectedExecutionException
    //   123: dup
    //   124: invokespecial <init> : ()V
    //   127: athrow
    //   128: aload_0
    //   129: aload_1
    //   130: lload_2
    //   131: iconst_0
    //   132: invokevirtual scheduleAndDecide$okhttp : (Lokhttp3/internal/concurrent/Task;JZ)Z
    //   135: ifeq -> 146
    //   138: aload_0
    //   139: getfield taskRunner : Lokhttp3/internal/concurrent/TaskRunner;
    //   142: aload_0
    //   143: invokevirtual kickCoordinator$okhttp : (Lokhttp3/internal/concurrent/TaskQueue;)V
    //   146: nop
    //   147: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   150: astore #5
    //   152: aload #4
    //   154: monitorexit
    //   155: goto -> 166
    //   158: astore #5
    //   160: aload #4
    //   162: monitorexit
    //   163: aload #5
    //   165: athrow
    //   166: return
    // Line number table:
    //   Java source line number -> byte code offset
    //   #62	-> 6
    //   #63	-> 19
    //   #64	-> 26
    //   #65	-> 33
    //   #220	-> 36
    //   #221	-> 51
    //   #65	-> 60
    //   #221	-> 70
    //   #223	-> 73
    //   #66	-> 74
    //   #68	-> 79
    //   #224	-> 82
    //   #225	-> 97
    //   #68	-> 106
    //   #225	-> 116
    //   #227	-> 119
    //   #69	-> 120
    //   #72	-> 128
    //   #73	-> 138
    //   #75	-> 146
    //   #62	-> 150
    //   #76	-> 166
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   60	2	9	$i$a$-taskLog-TaskQueue$schedule$1$1	I
    //   36	38	6	$i$f$taskLog	I
    //   106	2	9	$i$a$-taskLog-TaskQueue$schedule$1$2	I
    //   82	38	6	$i$f$taskLog	I
    //   19	56	5	$i$a$-synchronized-TaskQueue$schedule$1	I
    //   79	68	5	$i$a$-synchronized-TaskQueue$schedule$1	I
    //   0	167	0	this	Lokhttp3/internal/concurrent/TaskQueue;
    //   0	167	1	task	Lokhttp3/internal/concurrent/Task;
    //   0	167	2	delayNanos	J
    // Exception table:
    //   from	to	target	type
    //   15	75	158	finally
    //   79	152	158	finally
    //   158	160	158	finally
  }
  
  public final void schedule(@NotNull String name, long delayNanos, @NotNull Function0<Long> block) {
    Intrinsics.checkNotNullParameter(name, "name");
    Intrinsics.checkNotNullParameter(block, "block");
    int $i$f$schedule = 0;
    schedule(new TaskQueue$schedule$2(name, block), delayNanos);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 176, d1 = {"\000\021\n\000\n\002\030\002\n\002\020\t\n\002\b\003*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\026¢\006\004\b\003\020\004¨\006\005"}, d2 = {"okhttp3/internal/concurrent/TaskQueue.schedule.2", "Lokhttp3/internal/concurrent/Task;", "", "runOnce", "()J", "okhttp"})
  @SourceDebugExtension({"SMAP\nTaskQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TaskQueue.kt\nokhttp3/internal/concurrent/TaskQueue$schedule$2\n*L\n1#1,218:1\n*E\n"})
  public static final class TaskQueue$schedule$2 extends Task {
    public TaskQueue$schedule$2(String $name, Function0<Long> $block) {
      super($name, false, 2, null);
    }
    
    public long runOnce() {
      return ((Number)this.$block.invoke()).longValue();
    }
  }
  
  public final void execute(@NotNull String name, long delayNanos, boolean cancelable, @NotNull Function0<Unit> block) {
    Intrinsics.checkNotNullParameter(name, "name");
    Intrinsics.checkNotNullParameter(block, "block");
    int $i$f$execute = 0;
    schedule(new TaskQueue$execute$1(name, cancelable, block), delayNanos);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 176, d1 = {"\000\021\n\000\n\002\030\002\n\002\020\t\n\002\b\003*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\026¢\006\004\b\003\020\004¨\006\005"}, d2 = {"okhttp3/internal/concurrent/TaskQueue.execute.1", "Lokhttp3/internal/concurrent/Task;", "", "runOnce", "()J", "okhttp"})
  @SourceDebugExtension({"SMAP\nTaskQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TaskQueue.kt\nokhttp3/internal/concurrent/TaskQueue$execute$1\n*L\n1#1,218:1\n*E\n"})
  public static final class TaskQueue$execute$1 extends Task {
    public TaskQueue$execute$1(String $name, boolean $cancelable, Function0<Unit> $block) {
      super($name, $cancelable);
    }
    
    public long runOnce() {
      this.$block.invoke();
      return -1L;
    }
  }
  
  @NotNull
  public final CountDownLatch idleLatch() {
    synchronized (this.taskRunner) {
      int $i$a$-synchronized-TaskQueue$idleLatch$1 = 0;
      if (this.activeTask == null && this.futureTasks.isEmpty())
        return new CountDownLatch(0); 
      Task existingTask = this.activeTask;
      if (existingTask instanceof AwaitIdleTask)
        return ((AwaitIdleTask)existingTask).getLatch(); 
      for (Task futureTask : this.futureTasks) {
        if (futureTask instanceof AwaitIdleTask)
          return ((AwaitIdleTask)futureTask).getLatch(); 
      } 
      AwaitIdleTask newTask = new AwaitIdleTask();
      if (scheduleAndDecide$okhttp(newTask, 0L, false))
        this.taskRunner.kickCoordinator$okhttp(this); 
      return newTask.getLatch();
    } 
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\005\b\002\030\0002\0020\001B\007¢\006\004\b\002\020\003J\017\020\005\032\0020\004H\026¢\006\004\b\005\020\006R\027\020\b\032\0020\0078\006¢\006\f\n\004\b\b\020\t\032\004\b\n\020\013¨\006\f"}, d2 = {"Lokhttp3/internal/concurrent/TaskQueue$AwaitIdleTask;", "Lokhttp3/internal/concurrent/Task;", "<init>", "()V", "", "runOnce", "()J", "Ljava/util/concurrent/CountDownLatch;", "latch", "Ljava/util/concurrent/CountDownLatch;", "getLatch", "()Ljava/util/concurrent/CountDownLatch;", "okhttp"})
  private static final class AwaitIdleTask extends Task {
    @NotNull
    private final CountDownLatch latch;
    
    public AwaitIdleTask() {
      super(Util.okHttpName + " awaitIdle", false);
      this.latch = new CountDownLatch(1);
    }
    
    @NotNull
    public final CountDownLatch getLatch() {
      return this.latch;
    }
    
    public long runOnce() {
      this.latch.countDown();
      return -1L;
    }
  }
  
  public final boolean scheduleAndDecide$okhttp(@NotNull Task task, long delayNanos, boolean recurrence) {
    // Byte code:
    //   0: aload_1
    //   1: ldc 'task'
    //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_1
    //   7: aload_0
    //   8: invokevirtual initQueue$okhttp : (Lokhttp3/internal/concurrent/TaskQueue;)V
    //   11: aload_0
    //   12: getfield taskRunner : Lokhttp3/internal/concurrent/TaskRunner;
    //   15: invokevirtual getBackend : ()Lokhttp3/internal/concurrent/TaskRunner$Backend;
    //   18: invokeinterface nanoTime : ()J
    //   23: lstore #5
    //   25: lload #5
    //   27: lload_2
    //   28: ladd
    //   29: lstore #7
    //   31: aload_0
    //   32: getfield futureTasks : Ljava/util/List;
    //   35: aload_1
    //   36: invokeinterface indexOf : (Ljava/lang/Object;)I
    //   41: istore #9
    //   43: iload #9
    //   45: iconst_m1
    //   46: if_icmpeq -> 115
    //   49: aload_1
    //   50: invokevirtual getNextExecuteNanoTime$okhttp : ()J
    //   53: lload #7
    //   55: lcmp
    //   56: ifgt -> 103
    //   59: iconst_0
    //   60: istore #10
    //   62: getstatic okhttp3/internal/concurrent/TaskRunner.Companion : Lokhttp3/internal/concurrent/TaskRunner$Companion;
    //   65: invokevirtual getLogger : ()Ljava/util/logging/Logger;
    //   68: getstatic java/util/logging/Level.FINE : Ljava/util/logging/Level;
    //   71: invokevirtual isLoggable : (Ljava/util/logging/Level;)Z
    //   74: ifeq -> 100
    //   77: aload_1
    //   78: aload_0
    //   79: astore #19
    //   81: astore #18
    //   83: iconst_0
    //   84: istore #11
    //   86: ldc_w 'already scheduled'
    //   89: astore #20
    //   91: aload #18
    //   93: aload #19
    //   95: aload #20
    //   97: invokestatic access$log : (Lokhttp3/internal/concurrent/Task;Lokhttp3/internal/concurrent/TaskQueue;Ljava/lang/String;)V
    //   100: nop
    //   101: iconst_0
    //   102: ireturn
    //   103: aload_0
    //   104: getfield futureTasks : Ljava/util/List;
    //   107: iload #9
    //   109: invokeinterface remove : (I)Ljava/lang/Object;
    //   114: pop
    //   115: aload_1
    //   116: lload #7
    //   118: invokevirtual setNextExecuteNanoTime$okhttp : (J)V
    //   121: iconst_0
    //   122: istore #10
    //   124: getstatic okhttp3/internal/concurrent/TaskRunner.Companion : Lokhttp3/internal/concurrent/TaskRunner$Companion;
    //   127: invokevirtual getLogger : ()Ljava/util/logging/Logger;
    //   130: getstatic java/util/logging/Level.FINE : Ljava/util/logging/Level;
    //   133: invokevirtual isLoggable : (Ljava/util/logging/Level;)Z
    //   136: ifeq -> 222
    //   139: aload_1
    //   140: aload_0
    //   141: astore #19
    //   143: astore #18
    //   145: iconst_0
    //   146: istore #11
    //   148: iload #4
    //   150: ifeq -> 183
    //   153: new java/lang/StringBuilder
    //   156: dup
    //   157: invokespecial <init> : ()V
    //   160: ldc_w 'run again after '
    //   163: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: lload #7
    //   168: lload #5
    //   170: lsub
    //   171: invokestatic formatDuration : (J)Ljava/lang/String;
    //   174: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: invokevirtual toString : ()Ljava/lang/String;
    //   180: goto -> 210
    //   183: new java/lang/StringBuilder
    //   186: dup
    //   187: invokespecial <init> : ()V
    //   190: ldc_w 'scheduled after '
    //   193: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: lload #7
    //   198: lload #5
    //   200: lsub
    //   201: invokestatic formatDuration : (J)Ljava/lang/String;
    //   204: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: invokevirtual toString : ()Ljava/lang/String;
    //   210: nop
    //   211: astore #20
    //   213: aload #18
    //   215: aload #19
    //   217: aload #20
    //   219: invokestatic access$log : (Lokhttp3/internal/concurrent/Task;Lokhttp3/internal/concurrent/TaskQueue;Ljava/lang/String;)V
    //   222: nop
    //   223: aload_0
    //   224: getfield futureTasks : Ljava/util/List;
    //   227: astore #11
    //   229: iconst_0
    //   230: istore #12
    //   232: iconst_0
    //   233: istore #13
    //   235: aload #11
    //   237: invokeinterface iterator : ()Ljava/util/Iterator;
    //   242: astore #14
    //   244: aload #14
    //   246: invokeinterface hasNext : ()Z
    //   251: ifeq -> 305
    //   254: aload #14
    //   256: invokeinterface next : ()Ljava/lang/Object;
    //   261: astore #15
    //   263: aload #15
    //   265: checkcast okhttp3/internal/concurrent/Task
    //   268: astore #16
    //   270: iconst_0
    //   271: istore #17
    //   273: aload #16
    //   275: invokevirtual getNextExecuteNanoTime$okhttp : ()J
    //   278: lload #5
    //   280: lsub
    //   281: lload_2
    //   282: lcmp
    //   283: ifle -> 290
    //   286: iconst_1
    //   287: goto -> 291
    //   290: iconst_0
    //   291: ifeq -> 299
    //   294: iload #13
    //   296: goto -> 306
    //   299: iinc #13, 1
    //   302: goto -> 244
    //   305: iconst_m1
    //   306: istore #10
    //   308: iload #10
    //   310: iconst_m1
    //   311: if_icmpne -> 325
    //   314: aload_0
    //   315: getfield futureTasks : Ljava/util/List;
    //   318: invokeinterface size : ()I
    //   323: istore #10
    //   325: aload_0
    //   326: getfield futureTasks : Ljava/util/List;
    //   329: iload #10
    //   331: aload_1
    //   332: invokeinterface add : (ILjava/lang/Object;)V
    //   337: iload #10
    //   339: ifne -> 346
    //   342: iconst_1
    //   343: goto -> 347
    //   346: iconst_0
    //   347: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #144	-> 6
    //   #146	-> 11
    //   #147	-> 25
    //   #150	-> 31
    //   #151	-> 43
    //   #152	-> 49
    //   #153	-> 59
    //   #228	-> 62
    //   #229	-> 77
    //   #153	-> 86
    //   #229	-> 97
    //   #231	-> 100
    //   #154	-> 101
    //   #156	-> 103
    //   #158	-> 115
    //   #159	-> 121
    //   #232	-> 124
    //   #233	-> 139
    //   #160	-> 148
    //   #161	-> 183
    //   #160	-> 210
    //   #233	-> 219
    //   #235	-> 222
    //   #165	-> 223
    //   #236	-> 232
    //   #237	-> 235
    //   #238	-> 263
    //   #165	-> 273
    //   #238	-> 291
    //   #239	-> 294
    //   #240	-> 299
    //   #242	-> 305
    //   #165	-> 306
    //   #166	-> 308
    //   #167	-> 325
    //   #170	-> 337
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   86	3	11	$i$a$-taskLog-TaskQueue$scheduleAndDecide$1	I
    //   62	39	10	$i$f$taskLog	I
    //   148	63	11	$i$a$-taskLog-TaskQueue$scheduleAndDecide$2	I
    //   124	99	10	$i$f$taskLog	I
    //   273	18	17	$i$a$-indexOfFirst-TaskQueue$scheduleAndDecide$insertAt$1	I
    //   270	21	16	it	Lokhttp3/internal/concurrent/Task;
    //   263	39	15	item$iv	Ljava/lang/Object;
    //   232	74	12	$i$f$indexOfFirst	I
    //   235	71	13	index$iv	I
    //   229	77	11	$this$indexOfFirst$iv	Ljava/util/List;
    //   25	323	5	now	J
    //   31	317	7	executeNanoTime	J
    //   43	305	9	existingIndex	I
    //   308	40	10	insertAt	I
    //   0	348	0	this	Lokhttp3/internal/concurrent/TaskQueue;
    //   0	348	1	task	Lokhttp3/internal/concurrent/Task;
    //   0	348	2	delayNanos	J
    //   0	348	4	recurrence	Z
  }
  
  public final void cancelAll() {
    Object $this$assertThreadDoesntHoldLock$iv = this;
    int $i$f$assertThreadDoesntHoldLock = 0;
    if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); 
    synchronized (this.taskRunner) {
      int $i$a$-synchronized-TaskQueue$cancelAll$1 = 0;
      if (cancelAllAndDecide$okhttp())
        this.taskRunner.kickCoordinator$okhttp(this); 
      Unit unit = Unit.INSTANCE;
    } 
  }
  
  public final void shutdown() {
    Object $this$assertThreadDoesntHoldLock$iv = this;
    int $i$f$assertThreadDoesntHoldLock = 0;
    if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); 
    synchronized (this.taskRunner) {
      int $i$a$-synchronized-TaskQueue$shutdown$1 = 0;
      this.shutdown = true;
      if (cancelAllAndDecide$okhttp())
        this.taskRunner.kickCoordinator$okhttp(this); 
      Unit unit = Unit.INSTANCE;
    } 
  }
  
  public final boolean cancelAllAndDecide$okhttp() {
    // Byte code:
    //   0: aload_0
    //   1: getfield activeTask : Lokhttp3/internal/concurrent/Task;
    //   4: ifnull -> 26
    //   7: aload_0
    //   8: getfield activeTask : Lokhttp3/internal/concurrent/Task;
    //   11: dup
    //   12: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   15: invokevirtual getCancelable : ()Z
    //   18: ifeq -> 26
    //   21: aload_0
    //   22: iconst_1
    //   23: putfield cancelActiveTask : Z
    //   26: iconst_0
    //   27: istore_1
    //   28: aload_0
    //   29: getfield futureTasks : Ljava/util/List;
    //   32: invokeinterface size : ()I
    //   37: iconst_1
    //   38: isub
    //   39: istore_2
    //   40: iconst_m1
    //   41: iload_2
    //   42: if_icmpge -> 139
    //   45: aload_0
    //   46: getfield futureTasks : Ljava/util/List;
    //   49: iload_2
    //   50: invokeinterface get : (I)Ljava/lang/Object;
    //   55: checkcast okhttp3/internal/concurrent/Task
    //   58: invokevirtual getCancelable : ()Z
    //   61: ifeq -> 133
    //   64: aload_0
    //   65: getfield futureTasks : Ljava/util/List;
    //   68: iload_2
    //   69: invokeinterface get : (I)Ljava/lang/Object;
    //   74: checkcast okhttp3/internal/concurrent/Task
    //   77: astore_3
    //   78: iconst_0
    //   79: istore #4
    //   81: getstatic okhttp3/internal/concurrent/TaskRunner.Companion : Lokhttp3/internal/concurrent/TaskRunner$Companion;
    //   84: invokevirtual getLogger : ()Ljava/util/logging/Logger;
    //   87: getstatic java/util/logging/Level.FINE : Ljava/util/logging/Level;
    //   90: invokevirtual isLoggable : (Ljava/util/logging/Level;)Z
    //   93: ifeq -> 119
    //   96: aload_3
    //   97: aload_0
    //   98: astore #7
    //   100: astore #6
    //   102: iconst_0
    //   103: istore #5
    //   105: ldc_w 'canceled'
    //   108: astore #8
    //   110: aload #6
    //   112: aload #7
    //   114: aload #8
    //   116: invokestatic access$log : (Lokhttp3/internal/concurrent/Task;Lokhttp3/internal/concurrent/TaskQueue;Ljava/lang/String;)V
    //   119: nop
    //   120: iconst_1
    //   121: istore_1
    //   122: aload_0
    //   123: getfield futureTasks : Ljava/util/List;
    //   126: iload_2
    //   127: invokeinterface remove : (I)Ljava/lang/Object;
    //   132: pop
    //   133: iinc #2, -1
    //   136: goto -> 40
    //   139: iload_1
    //   140: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #201	-> 0
    //   #202	-> 21
    //   #205	-> 26
    //   #206	-> 28
    //   #207	-> 45
    //   #208	-> 64
    //   #251	-> 81
    //   #252	-> 96
    //   #208	-> 105
    //   #252	-> 116
    //   #254	-> 119
    //   #209	-> 120
    //   #210	-> 122
    //   #206	-> 133
    //   #213	-> 139
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   105	3	5	$i$a$-taskLog-TaskQueue$cancelAllAndDecide$1	I
    //   81	39	4	$i$f$taskLog	I
    //   78	42	3	task$iv	Lokhttp3/internal/concurrent/Task;
    //   40	99	2	i	I
    //   28	113	1	tasksCanceled	Z
    //   0	141	0	this	Lokhttp3/internal/concurrent/TaskQueue;
  }
  
  @NotNull
  public String toString() {
    return this.name;
  }
}
