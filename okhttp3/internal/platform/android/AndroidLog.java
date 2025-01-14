package okhttp3.internal.platform.android;

import android.util.Log;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;
import okhttp3.internal.SuppressSignatureCheck;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http2.Http2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000<\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\b\n\002\b\002\n\002\020\003\n\000\n\002\020\002\n\002\b\f\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020$\n\002\b\003\bÇ\002\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J1\020\016\032\0020\0132\006\020\005\032\0020\0042\006\020\007\032\0020\0062\006\020\b\032\0020\0042\b\020\n\032\004\030\0010\tH\000¢\006\004\b\f\020\rJ\r\020\017\032\0020\013¢\006\004\b\017\020\003J\037\020\022\032\0020\0132\006\020\020\032\0020\0042\006\020\021\032\0020\004H\002¢\006\004\b\022\020\023J\027\020\024\032\0020\0042\006\020\005\032\0020\004H\002¢\006\004\b\024\020\025R\024\020\026\032\0020\0068\002XT¢\006\006\n\004\b\026\020\027R\032\020\032\032\b\022\004\022\0020\0310\0308\002X\004¢\006\006\n\004\b\032\020\033R \020\035\032\016\022\004\022\0020\004\022\004\022\0020\0040\0348\002X\004¢\006\006\n\004\b\035\020\036¨\006\037"}, d2 = {"Lokhttp3/internal/platform/android/AndroidLog;", "", "<init>", "()V", "", "loggerName", "", "logLevel", "message", "", "t", "", "androidLog$okhttp", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/Throwable;)V", "androidLog", "enable", "logger", "tag", "enableLogging", "(Ljava/lang/String;Ljava/lang/String;)V", "loggerTag", "(Ljava/lang/String;)Ljava/lang/String;", "MAX_LOG_LENGTH", "I", "Ljava/util/concurrent/CopyOnWriteArraySet;", "Ljava/util/logging/Logger;", "configuredLoggers", "Ljava/util/concurrent/CopyOnWriteArraySet;", "", "knownLoggers", "Ljava/util/Map;", "okhttp"})
@SuppressSignatureCheck
public final class AndroidLog {
  @NotNull
  public static final AndroidLog INSTANCE = new AndroidLog();
  
  private static final int MAX_LOG_LENGTH = 4000;
  
  @NotNull
  private static final CopyOnWriteArraySet<Logger> configuredLoggers = new CopyOnWriteArraySet<>();
  
  @NotNull
  private static final Map<String, String> knownLoggers;
  
  static {
    LinkedHashMap<Object, Object> linkedHashMap1 = new LinkedHashMap<>(), $this$knownLoggers_u24lambda_u240 = linkedHashMap1;
    int $i$a$-apply-AndroidLog$knownLoggers$1 = 0;
    OkHttpClient.class.getPackage();
    String packageName = (OkHttpClient.class.getPackage() != null) ? OkHttpClient.class.getPackage().getName() : null;
    if (packageName != null)
      $this$knownLoggers_u24lambda_u240.put(packageName, "OkHttp"); 
    LinkedHashMap<Object, Object> linkedHashMap2 = $this$knownLoggers_u24lambda_u240;
    Intrinsics.checkNotNullExpressionValue(OkHttpClient.class.getName(), "OkHttpClient::class.java.name");
    String str1 = OkHttpClient.class.getName(), str2 = "okhttp.OkHttpClient";
    linkedHashMap2.put(str1, str2);
    linkedHashMap2 = $this$knownLoggers_u24lambda_u240;
    Intrinsics.checkNotNullExpressionValue(Http2.class.getName(), "Http2::class.java.name");
    str1 = Http2.class.getName();
    str2 = "okhttp.Http2";
    linkedHashMap2.put(str1, str2);
    linkedHashMap2 = $this$knownLoggers_u24lambda_u240;
    Intrinsics.checkNotNullExpressionValue(TaskRunner.class.getName(), "TaskRunner::class.java.name");
    str1 = TaskRunner.class.getName();
    str2 = "okhttp.TaskRunner";
    linkedHashMap2.put(str1, str2);
    $this$knownLoggers_u24lambda_u240.put("okhttp3.mockwebserver.MockWebServer", "okhttp.MockWebServer");
    knownLoggers = MapsKt.toMap(linkedHashMap1);
  }
  
  public final void androidLog$okhttp(@NotNull String loggerName, int logLevel, @NotNull String message, @Nullable Throwable t) {
    Intrinsics.checkNotNullParameter(loggerName, "loggerName");
    Intrinsics.checkNotNullParameter(message, "message");
    String tag = loggerTag(loggerName);
    if (Log.isLoggable(tag, logLevel)) {
      String logMessage = message;
      if (t != null)
        logMessage = logMessage + '\n' + Log.getStackTraceString(t); 
      int i = 0;
      int length = logMessage.length();
      while (i < length) {
        int newline = StringsKt.indexOf$default(logMessage, '\n', i, false, 4, null);
        newline = (newline != -1) ? newline : length;
        while (true) {
          int end = Math.min(newline, i + 4000);
          Intrinsics.checkNotNullExpressionValue(logMessage.substring(i, end), "this as java.lang.String…ing(startIndex, endIndex)");
          Log.println(logLevel, tag, logMessage.substring(i, end));
          i = end;
          if (i >= newline)
            i++; 
        } 
      } 
    } 
  }
  
  private final String loggerTag(String loggerName) {
    if ((String)knownLoggers.get(loggerName) == null)
      (String)knownLoggers.get(loggerName); 
    return StringsKt.take(loggerName, 23);
  }
  
  public final void enable() {
    for (Map.Entry<String, String> entry : knownLoggers.entrySet()) {
      String logger = (String)entry.getKey(), tag = (String)entry.getValue();
      enableLogging(logger, tag);
    } 
  }
  
  private final void enableLogging(String logger, String tag) {
    Logger logger1 = Logger.getLogger(logger);
    if (configuredLoggers.add(logger1)) {
      logger1.setUseParentHandlers(false);
      logger1.setLevel(
          Log.isLoggable(tag, 3) ? Level.FINE : (
          Log.isLoggable(tag, 4) ? Level.INFO : 
          Level.WARNING));
      logger1.addHandler(AndroidLogHandler.INSTANCE);
    } 
  }
}
