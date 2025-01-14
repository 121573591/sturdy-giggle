package okhttp3.internal.concurrent;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000,\n\002\030\002\n\002\020\000\n\002\020\016\n\000\n\002\020\013\n\002\b\003\n\002\030\002\n\000\n\002\020\002\n\002\b\003\n\002\020\t\n\002\b\023\b&\030\0002\0020\001B\031\022\006\020\003\032\0020\002\022\b\b\002\020\005\032\0020\004¢\006\004\b\006\020\007J\027\020\r\032\0020\n2\006\020\t\032\0020\bH\000¢\006\004\b\013\020\fJ\017\020\017\032\0020\016H&¢\006\004\b\017\020\020J\017\020\021\032\0020\002H\026¢\006\004\b\021\020\022R\027\020\005\032\0020\0048\006¢\006\f\n\004\b\005\020\023\032\004\b\024\020\025R\027\020\003\032\0020\0028\006¢\006\f\n\004\b\003\020\026\032\004\b\027\020\022R\"\020\030\032\0020\0168\000@\000X\016¢\006\022\n\004\b\030\020\031\032\004\b\032\020\020\"\004\b\033\020\034R$\020\t\032\004\030\0010\b8\000@\000X\016¢\006\022\n\004\b\t\020\035\032\004\b\036\020\037\"\004\b \020\f¨\006!"}, d2 = {"Lokhttp3/internal/concurrent/Task;", "", "", "name", "", "cancelable", "<init>", "(Ljava/lang/String;Z)V", "Lokhttp3/internal/concurrent/TaskQueue;", "queue", "", "initQueue$okhttp", "(Lokhttp3/internal/concurrent/TaskQueue;)V", "initQueue", "", "runOnce", "()J", "toString", "()Ljava/lang/String;", "Z", "getCancelable", "()Z", "Ljava/lang/String;", "getName", "nextExecuteNanoTime", "J", "getNextExecuteNanoTime$okhttp", "setNextExecuteNanoTime$okhttp", "(J)V", "Lokhttp3/internal/concurrent/TaskQueue;", "getQueue$okhttp", "()Lokhttp3/internal/concurrent/TaskQueue;", "setQueue$okhttp", "okhttp"})
@SourceDebugExtension({"SMAP\nTask.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Task.kt\nokhttp3/internal/concurrent/Task\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,71:1\n1#2:72\n*E\n"})
public abstract class Task {
  @NotNull
  private final String name;
  
  private final boolean cancelable;
  
  @Nullable
  private TaskQueue queue;
  
  private long nextExecuteNanoTime;
  
  public Task(@NotNull String name, boolean cancelable) {
    this.name = name;
    this.cancelable = cancelable;
    this.nextExecuteNanoTime = -1L;
  }
  
  @NotNull
  public final String getName() {
    return this.name;
  }
  
  public final boolean getCancelable() {
    return this.cancelable;
  }
  
  @Nullable
  public final TaskQueue getQueue$okhttp() {
    return this.queue;
  }
  
  public final void setQueue$okhttp(@Nullable TaskQueue <set-?>) {
    this.queue = <set-?>;
  }
  
  public final long getNextExecuteNanoTime$okhttp() {
    return this.nextExecuteNanoTime;
  }
  
  public final void setNextExecuteNanoTime$okhttp(long <set-?>) {
    this.nextExecuteNanoTime = <set-?>;
  }
  
  public abstract long runOnce();
  
  public final void initQueue$okhttp(@NotNull TaskQueue queue) {
    Intrinsics.checkNotNullParameter(queue, "queue");
    if (this.queue == queue)
      return; 
    if (!((this.queue == null) ? 1 : 0)) {
      int $i$a$-check-Task$initQueue$1 = 0;
      String str = "task is in multiple queues";
      throw new IllegalStateException(str.toString());
    } 
    this.queue = queue;
  }
  
  @NotNull
  public String toString() {
    return this.name;
  }
}
