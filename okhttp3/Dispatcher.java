package okhttp3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealCall;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000^\n\002\030\002\n\002\020\000\n\002\030\002\n\002\b\004\n\002\020\002\n\000\n\002\030\002\n\002\030\002\n\002\b\t\n\002\020\016\n\002\b\004\n\002\030\002\n\002\b\004\n\002\020\013\n\002\b\002\n\002\020 \n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\006\n\002\030\002\n\002\b\017\n\002\030\002\n\002\b\005\030\0002\0020\001B\021\b\026\022\006\020\003\032\0020\002¢\006\004\b\004\020\005B\007¢\006\004\b\004\020\006J\r\020\b\032\0020\007¢\006\004\b\b\020\006J\033\020\016\032\0020\0072\n\020\013\032\0060\tR\0020\nH\000¢\006\004\b\f\020\rJ\027\020\021\032\0020\0072\006\020\013\032\0020\nH\000¢\006\004\b\017\020\020J\017\020\003\032\0020\002H\007¢\006\004\b\022\020\023J\035\020\026\032\b\030\0010\tR\0020\n2\006\020\025\032\0020\024H\002¢\006\004\b\026\020\027J+\020\033\032\0020\007\"\004\b\000\020\0302\f\020\032\032\b\022\004\022\0028\0000\0312\006\020\013\032\0028\000H\002¢\006\004\b\033\020\034J\027\020\033\032\0020\0072\006\020\013\032\0020\nH\000¢\006\004\b\035\020\020J\033\020\033\032\0020\0072\n\020\013\032\0060\tR\0020\nH\000¢\006\004\b\035\020\rJ\017\020\037\032\0020\036H\002¢\006\004\b\037\020 J\023\020#\032\b\022\004\022\0020\"0!¢\006\004\b#\020$J\r\020&\032\0020%¢\006\004\b&\020'J\023\020(\032\b\022\004\022\0020\"0!¢\006\004\b(\020$J\r\020)\032\0020%¢\006\004\b)\020'R\021\020\003\032\0020\0028G¢\006\006\032\004\b\003\020\023R\030\020*\032\004\030\0010\0028\002@\002X\016¢\006\006\n\004\b*\020+R.\020.\032\004\030\0010,2\b\020-\032\004\030\0010,8F@FX\016¢\006\022\n\004\b.\020/\032\004\b0\0201\"\004\b2\0203R*\0204\032\0020%2\006\0204\032\0020%8F@FX\016¢\006\022\n\004\b4\0205\032\004\b6\020'\"\004\b7\0208R*\0209\032\0020%2\006\0209\032\0020%8F@FX\016¢\006\022\n\004\b9\0205\032\004\b:\020'\"\004\b;\0208R\036\020=\032\f\022\b\022\0060\tR\0020\n0<8\002X\004¢\006\006\n\004\b=\020>R\036\020?\032\f\022\b\022\0060\tR\0020\n0<8\002X\004¢\006\006\n\004\b?\020>R\032\020@\032\b\022\004\022\0020\n0<8\002X\004¢\006\006\n\004\b@\020>¨\006A"}, d2 = {"Lokhttp3/Dispatcher;", "", "Ljava/util/concurrent/ExecutorService;", "executorService", "<init>", "(Ljava/util/concurrent/ExecutorService;)V", "()V", "", "cancelAll", "Lokhttp3/internal/connection/RealCall$AsyncCall;", "Lokhttp3/internal/connection/RealCall;", "call", "enqueue$okhttp", "(Lokhttp3/internal/connection/RealCall$AsyncCall;)V", "enqueue", "executed$okhttp", "(Lokhttp3/internal/connection/RealCall;)V", "executed", "-deprecated_executorService", "()Ljava/util/concurrent/ExecutorService;", "", "host", "findExistingCallWithHost", "(Ljava/lang/String;)Lokhttp3/internal/connection/RealCall$AsyncCall;", "T", "Ljava/util/Deque;", "calls", "finished", "(Ljava/util/Deque;Ljava/lang/Object;)V", "finished$okhttp", "", "promoteAndExecute", "()Z", "", "Lokhttp3/Call;", "queuedCalls", "()Ljava/util/List;", "", "queuedCallsCount", "()I", "runningCalls", "runningCallsCount", "executorServiceOrNull", "Ljava/util/concurrent/ExecutorService;", "Ljava/lang/Runnable;", "<set-?>", "idleCallback", "Ljava/lang/Runnable;", "getIdleCallback", "()Ljava/lang/Runnable;", "setIdleCallback", "(Ljava/lang/Runnable;)V", "maxRequests", "I", "getMaxRequests", "setMaxRequests", "(I)V", "maxRequestsPerHost", "getMaxRequestsPerHost", "setMaxRequestsPerHost", "Ljava/util/ArrayDeque;", "readyAsyncCalls", "Ljava/util/ArrayDeque;", "runningAsyncCalls", "runningSyncCalls", "okhttp"})
@SourceDebugExtension({"SMAP\nDispatcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Dispatcher.kt\nokhttp3/Dispatcher\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Util.kt\nokhttp3/internal/Util\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,241:1\n1#2:242\n615#3,4:243\n1549#4:247\n1620#4,3:248\n1549#4:251\n1620#4,3:252\n*S KotlinDebug\n*F\n+ 1 Dispatcher.kt\nokhttp3/Dispatcher\n*L\n162#1:243,4\n222#1:247\n222#1:248,3\n227#1:251\n227#1:252,3\n*E\n"})
public final class Dispatcher {
  private int maxRequests = 64;
  
  public Dispatcher() {}
  
  public final synchronized int getMaxRequests() {
    return this.maxRequests;
  }
  
  public final void setMaxRequests(int maxRequests) {
    if (!((maxRequests >= 1) ? 1 : 0)) {
      int $i$a$-require-Dispatcher$maxRequests$1 = 0;
      String str = "max < 1: " + maxRequests;
      throw new IllegalArgumentException(str.toString());
    } 
    synchronized (this) {
      int $i$a$-synchronized-Dispatcher$maxRequests$2 = 0;
      this.maxRequests = maxRequests;
      Unit unit = Unit.INSTANCE;
    } 
    promoteAndExecute();
  }
  
  private int maxRequestsPerHost = 5;
  
  @Nullable
  private Runnable idleCallback;
  
  @Nullable
  private ExecutorService executorServiceOrNull;
  
  public final synchronized int getMaxRequestsPerHost() {
    return this.maxRequestsPerHost;
  }
  
  public final void setMaxRequestsPerHost(int maxRequestsPerHost) {
    if (!((maxRequestsPerHost >= 1) ? 1 : 0)) {
      int $i$a$-require-Dispatcher$maxRequestsPerHost$1 = 0;
      String str = "max < 1: " + maxRequestsPerHost;
      throw new IllegalArgumentException(str.toString());
    } 
    synchronized (this) {
      int $i$a$-synchronized-Dispatcher$maxRequestsPerHost$2 = 0;
      this.maxRequestsPerHost = maxRequestsPerHost;
      Unit unit = Unit.INSTANCE;
    } 
    promoteAndExecute();
  }
  
  @Nullable
  public final synchronized Runnable getIdleCallback() {
    return this.idleCallback;
  }
  
  public final synchronized void setIdleCallback(@Nullable Runnable <set-?>) {
    this.idleCallback = <set-?>;
  }
  
  @JvmName(name = "executorService")
  @NotNull
  public final synchronized ExecutorService executorService() {
    if (this.executorServiceOrNull == null)
      this.executorServiceOrNull = new ThreadPoolExecutor(0, 2147483647, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), Util.threadFactory(Util.okHttpName + " Dispatcher", false)); 
    Intrinsics.checkNotNull(this.executorServiceOrNull);
    return this.executorServiceOrNull;
  }
  
  @NotNull
  private final ArrayDeque<RealCall.AsyncCall> readyAsyncCalls = new ArrayDeque<>();
  
  @NotNull
  private final ArrayDeque<RealCall.AsyncCall> runningAsyncCalls = new ArrayDeque<>();
  
  @NotNull
  private final ArrayDeque<RealCall> runningSyncCalls = new ArrayDeque<>();
  
  public Dispatcher(@NotNull ExecutorService executorService) {
    this();
    this.executorServiceOrNull = executorService;
  }
  
  public final void enqueue$okhttp(@NotNull RealCall.AsyncCall call) {
    Intrinsics.checkNotNullParameter(call, "call");
    synchronized (this) {
      int $i$a$-synchronized-Dispatcher$enqueue$1 = 0;
      this.readyAsyncCalls.add(call);
      if (!call.getCall().getForWebSocket()) {
        RealCall.AsyncCall existingCall = findExistingCallWithHost(call.getHost());
        if (existingCall != null)
          call.reuseCallsPerHostFrom(existingCall); 
      } 
      Unit unit = Unit.INSTANCE;
    } 
    promoteAndExecute();
  }
  
  private final RealCall.AsyncCall findExistingCallWithHost(String host) {
    for (RealCall.AsyncCall existingCall : this.runningAsyncCalls) {
      if (Intrinsics.areEqual(existingCall.getHost(), host))
        return existingCall; 
    } 
    for (RealCall.AsyncCall existingCall : this.readyAsyncCalls) {
      if (Intrinsics.areEqual(existingCall.getHost(), host))
        return existingCall; 
    } 
    return null;
  }
  
  public final synchronized void cancelAll() {
    for (RealCall.AsyncCall call : this.readyAsyncCalls)
      call.getCall().cancel(); 
    for (RealCall.AsyncCall call : this.runningAsyncCalls)
      call.getCall().cancel(); 
    for (RealCall call : this.runningSyncCalls)
      call.cancel(); 
  }
  
  private final boolean promoteAndExecute() {
    Object $this$assertThreadDoesntHoldLock$iv = this;
    int $i$f$assertThreadDoesntHoldLock = 0;
    if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); 
    List<RealCall.AsyncCall> executableCalls = new ArrayList();
    boolean isRunning = false;
    synchronized (this) {
      int $i$a$-synchronized-Dispatcher$promoteAndExecute$1 = 0;
      Intrinsics.checkNotNullExpressionValue(this.readyAsyncCalls.iterator(), "readyAsyncCalls.iterator()");
      Iterator<RealCall.AsyncCall> iterator = this.readyAsyncCalls.iterator();
      while (iterator.hasNext()) {
        RealCall.AsyncCall asyncCall = iterator.next();
        if (this.runningAsyncCalls.size() < this.maxRequests)
          if (asyncCall.getCallsPerHost().get() < this.maxRequestsPerHost) {
            iterator.remove();
            asyncCall.getCallsPerHost().incrementAndGet();
            Intrinsics.checkNotNullExpressionValue(asyncCall, "asyncCall");
            executableCalls.add(asyncCall);
            this.runningAsyncCalls.add(asyncCall);
          }  
      } 
      isRunning = (runningCallsCount() > 0);
      Unit unit = Unit.INSTANCE;
    } 
    for (int i = 0, j = executableCalls.size(); i < j; i++) {
      RealCall.AsyncCall asyncCall = executableCalls.get(i);
      asyncCall.executeOn(executorService());
    } 
    return isRunning;
  }
  
  public final synchronized void executed$okhttp(@NotNull RealCall call) {
    Intrinsics.checkNotNullParameter(call, "call");
    this.runningSyncCalls.add(call);
  }
  
  public final void finished$okhttp(@NotNull RealCall.AsyncCall call) {
    Intrinsics.checkNotNullParameter(call, "call");
    call.getCallsPerHost().decrementAndGet();
    finished(this.runningAsyncCalls, call);
  }
  
  public final void finished$okhttp(@NotNull RealCall call) {
    Intrinsics.checkNotNullParameter(call, "call");
    finished(this.runningSyncCalls, call);
  }
  
  private final <T> void finished(Deque calls, Object call) {
    Object idleCallback = null;
    synchronized (this) {
      int $i$a$-synchronized-Dispatcher$finished$1 = 0;
      if (!calls.remove(call))
        throw new AssertionError("Call wasn't in-flight!"); 
      idleCallback = this.idleCallback;
      Unit unit = Unit.INSTANCE;
    } 
    boolean isRunning = promoteAndExecute();
    if (!isRunning && idleCallback != null)
      idleCallback.run(); 
  }
  
  @NotNull
  public final synchronized List<Call> queuedCalls() {
    Iterable<RealCall.AsyncCall> $this$map$iv = this.readyAsyncCalls;
    int $i$f$map = 0;
    Iterable<RealCall.AsyncCall> iterable1 = $this$map$iv;
    Collection<RealCall> destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
    int $i$f$mapTo = 0;
    for (RealCall.AsyncCall item$iv$iv : iterable1) {
      RealCall.AsyncCall asyncCall1 = item$iv$iv;
      Collection<RealCall> collection = destination$iv$iv;
      int $i$a$-map-Dispatcher$queuedCalls$1 = 0;
      collection.add(asyncCall1.getCall());
    } 
    Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableList((List)destination$iv$iv), "unmodifiableList(readyAsyncCalls.map { it.call })");
    return (List)Collections.unmodifiableList((List)destination$iv$iv);
  }
  
  @NotNull
  public final synchronized List<Call> runningCalls() {
    ArrayDeque<RealCall.AsyncCall> arrayDeque1 = this.runningAsyncCalls;
    ArrayDeque<RealCall> arrayDeque = this.runningSyncCalls;
    int $i$f$map = 0;
    ArrayDeque<RealCall.AsyncCall> arrayDeque2 = arrayDeque1;
    Collection<RealCall> destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayDeque1, 10));
    int $i$f$mapTo = 0;
    for (RealCall.AsyncCall item$iv$iv : arrayDeque2) {
      RealCall.AsyncCall asyncCall1 = item$iv$iv;
      Collection<RealCall> collection = destination$iv$iv;
      int $i$a$-map-Dispatcher$runningCalls$1 = 0;
      collection.add(asyncCall1.getCall());
    } 
    Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableList(CollectionsKt.plus(arrayDeque, destination$iv$iv)), "unmodifiableList(running…yncCalls.map { it.call })");
    return (List)Collections.unmodifiableList(CollectionsKt.plus(arrayDeque, destination$iv$iv));
  }
  
  public final synchronized int queuedCallsCount() {
    return this.readyAsyncCalls.size();
  }
  
  public final synchronized int runningCallsCount() {
    return this.runningAsyncCalls.size() + this.runningSyncCalls.size();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "executorService", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_executorService")
  @NotNull
  public final ExecutorService -deprecated_executorService() {
    return executorService();
  }
}
