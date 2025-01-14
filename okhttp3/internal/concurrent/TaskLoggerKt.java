package okhttp3.internal.concurrent;

import java.util.Arrays;
import java.util.logging.Level;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 2, xi = 48, d1 = {"\000,\n\002\020\t\n\000\n\002\020\016\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\003\n\002\030\002\n\002\b\007\032\025\020\003\032\0020\0022\006\020\001\032\0020\000¢\006\004\b\003\020\004\032'\020\013\032\0020\n2\006\020\006\032\0020\0052\006\020\b\032\0020\0072\006\020\t\032\0020\002H\002¢\006\004\b\013\020\f\0327\020\020\032\0028\000\"\004\b\000\020\r2\006\020\006\032\0020\0052\006\020\b\032\0020\0072\f\020\017\032\b\022\004\022\0028\0000\016H\bø\001\000¢\006\004\b\020\020\021\0321\020\023\032\0020\n2\006\020\006\032\0020\0052\006\020\b\032\0020\0072\f\020\022\032\b\022\004\022\0020\0020\016H\bø\001\000¢\006\004\b\023\020\024\002\007\n\005\b20\001¨\006\025"}, d2 = {"", "ns", "", "formatDuration", "(J)Ljava/lang/String;", "Lokhttp3/internal/concurrent/Task;", "task", "Lokhttp3/internal/concurrent/TaskQueue;", "queue", "message", "", "log", "(Lokhttp3/internal/concurrent/Task;Lokhttp3/internal/concurrent/TaskQueue;Ljava/lang/String;)V", "T", "Lkotlin/Function0;", "block", "logElapsed", "(Lokhttp3/internal/concurrent/Task;Lokhttp3/internal/concurrent/TaskQueue;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "messageBlock", "taskLog", "(Lokhttp3/internal/concurrent/Task;Lokhttp3/internal/concurrent/TaskQueue;Lkotlin/jvm/functions/Function0;)V", "okhttp"})
public final class TaskLoggerKt {
  public static final void taskLog(@NotNull Task task, @NotNull TaskQueue queue, @NotNull Function0 messageBlock) {
    Intrinsics.checkNotNullParameter(task, "task");
    Intrinsics.checkNotNullParameter(queue, "queue");
    Intrinsics.checkNotNullParameter(messageBlock, "messageBlock");
    int $i$f$taskLog = 0;
    if (TaskRunner.Companion.getLogger().isLoggable(Level.FINE))
      access$log(task, queue, (String)messageBlock.invoke()); 
  }
  
  public static final <T> T logElapsed(@NotNull Task task, @NotNull TaskQueue queue, @NotNull Function0 block) {
    Intrinsics.checkNotNullParameter(task, "task");
    Intrinsics.checkNotNullParameter(queue, "queue");
    Intrinsics.checkNotNullParameter(block, "block");
    int $i$f$logElapsed = 0;
    long startNs = -1L;
    boolean loggingEnabled = TaskRunner.Companion.getLogger().isLoggable(Level.FINE);
    if (loggingEnabled) {
      startNs = queue.getTaskRunner$okhttp().getBackend().nanoTime();
      access$log(task, queue, "starting");
    } 
    boolean completedNormally = false;
    try {
      Object result = block.invoke();
      completedNormally = true;
      return (T)result;
    } finally {
      InlineMarker.finallyStart(1);
      if (loggingEnabled) {
        long elapsedNs = queue.getTaskRunner$okhttp().getBackend().nanoTime() - startNs;
        if (completedNormally) {
          access$log(task, queue, "finished run in " + formatDuration(elapsedNs));
        } else {
          access$log(task, queue, "failed a run in " + formatDuration(elapsedNs));
        } 
      } 
      InlineMarker.finallyEnd(1);
    } 
  }
  
  private static final void log(Task task, TaskQueue queue, String message) {
    String str = "%-22s";
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = message;
    arrayOfObject = arrayOfObject;
    Intrinsics.checkNotNullExpressionValue(String.format(str, Arrays.copyOf(arrayOfObject, arrayOfObject.length)), "format(format, *args)");
    TaskRunner.Companion.getLogger().fine(queue.getName$okhttp() + ' ' + String.format(str, Arrays.copyOf(arrayOfObject, arrayOfObject.length)) + ": " + task.getName());
  }
  
  @NotNull
  public static final String formatDuration(long ns) {
    String s = 
      (ns <= -999500000L) ? (((ns - 500000000L) / 1000000000L) + " s ") : (
      (ns <= -999500L) ? (((ns - 500000L) / 1000000L) + " ms") : (
      (ns <= 0L) ? (((ns - 500L) / 1000L) + " µs") : (
      (ns < 999500L) ? (((ns + 500L) / 1000L) + " µs") : (
      (ns < 999500000L) ? (((ns + 500000L) / 1000000L) + " ms") : (((
      ns + 500000000L) / 1000000000L) + " s ")))));
    String str1 = "%6s";
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = s;
    arrayOfObject = arrayOfObject;
    Intrinsics.checkNotNullExpressionValue(String.format(str1, Arrays.copyOf(arrayOfObject, arrayOfObject.length)), "format(format, *args)");
    return String.format(str1, Arrays.copyOf(arrayOfObject, arrayOfObject.length));
  }
}
