package okhttp3.internal.platform.android;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import kotlin.Metadata;

@Metadata(mv = {1, 8, 0}, k = 2, xi = 48, d1 = {"\000\f\n\002\030\002\n\002\020\b\n\002\b\004\"\030\020\004\032\0020\001*\0020\0008BX\004¢\006\006\032\004\b\002\020\003¨\006\005"}, d2 = {"Ljava/util/logging/LogRecord;", "", "getAndroidLevel", "(Ljava/util/logging/LogRecord;)I", "androidLevel", "okhttp"})
public final class AndroidLogKt {
  private static final int getAndroidLevel(LogRecord $this$androidLevel) {
    return 
      ($this$androidLevel.getLevel().intValue() > Level.INFO.intValue()) ? 5 : (
      ($this$androidLevel.getLevel().intValue() == Level.INFO.intValue()) ? 4 : 
      3);
  }
}
