package okhttp3.internal.platform.android;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\004\bÆ\002\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\017\020\005\032\0020\004H\026¢\006\004\b\005\020\003J\017\020\006\032\0020\004H\026¢\006\004\b\006\020\003J\027\020\t\032\0020\0042\006\020\b\032\0020\007H\026¢\006\004\b\t\020\n¨\006\013"}, d2 = {"Lokhttp3/internal/platform/android/AndroidLogHandler;", "Ljava/util/logging/Handler;", "<init>", "()V", "", "close", "flush", "Ljava/util/logging/LogRecord;", "record", "publish", "(Ljava/util/logging/LogRecord;)V", "okhttp"})
public final class AndroidLogHandler extends Handler {
  @NotNull
  public static final AndroidLogHandler INSTANCE = new AndroidLogHandler();
  
  public void publish(@NotNull LogRecord record) {
    Intrinsics.checkNotNullParameter(record, "record");
    Intrinsics.checkNotNullExpressionValue(record.getLoggerName(), "record.loggerName");
    Intrinsics.checkNotNullExpressionValue(record.getMessage(), "record.message");
    AndroidLog.INSTANCE.androidLog$okhttp(record.getLoggerName(), AndroidLogKt.access$getAndroidLevel(record), record.getMessage(), record.getThrown());
  }
  
  public void flush() {}
  
  public void close() {}
}
