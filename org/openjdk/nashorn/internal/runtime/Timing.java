package org.openjdk.nashorn.internal.runtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;
import java.util.function.Supplier;
import org.openjdk.nashorn.internal.codegen.CompileUnit;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;
import org.openjdk.nashorn.internal.runtime.logging.Loggable;
import org.openjdk.nashorn.internal.runtime.logging.Logger;

@Logger(name = "time")
public final class Timing implements Loggable {
  private DebugLogger log;
  
  private TimeSupplier timeSupplier;
  
  private final boolean isEnabled;
  
  private final long startTime;
  
  private static final String LOGGER_NAME = ((Logger)Timing.class.<Logger>getAnnotation(Logger.class)).name();
  
  public Timing(boolean isEnabled) {
    this.isEnabled = isEnabled;
    this.startTime = System.nanoTime();
  }
  
  public String getLogInfo() {
    assert isEnabled();
    return this.timeSupplier.get();
  }
  
  public String[] getLogInfoLines() {
    assert isEnabled();
    return this.timeSupplier.getStrings();
  }
  
  boolean isEnabled() {
    return this.isEnabled;
  }
  
  public void accumulateTime(String module, long durationNano) {
    if (isEnabled()) {
      ensureInitialized(Context.getContextTrusted());
      this.timeSupplier.accumulateTime(module, durationNano);
    } 
  }
  
  private DebugLogger ensureInitialized(Context context) {
    if (isEnabled() && this.log == null) {
      this.log = initLogger(context);
      if (this.log.isEnabled()) {
        this.timeSupplier = new TimeSupplier();
        Runtime.getRuntime().addShutdownHook(new Thread() {
              public void run() {
                StringBuilder sb = new StringBuilder();
                for (String str : Timing.this.timeSupplier.getStrings())
                  sb.append('[')
                    .append(Timing.getLoggerName())
                    .append("] ")
                    .append(str)
                    .append('\n'); 
                System.err.print(sb);
              }
            });
      } 
    } 
    return this.log;
  }
  
  static String getLoggerName() {
    return LOGGER_NAME;
  }
  
  public DebugLogger initLogger(Context context) {
    return context.getLogger((Class)getClass());
  }
  
  public DebugLogger getLogger() {
    return this.log;
  }
  
  public static String toMillisPrint(long durationNano) {
    return Long.toString(TimeUnit.NANOSECONDS.toMillis(durationNano));
  }
  
  final class TimeSupplier implements Supplier<String> {
    private final Map<String, LongAdder> timings;
    
    private final LinkedBlockingQueue<String> orderedTimingNames;
    
    private final Function<String, LongAdder> newTimingCreator;
    
    TimeSupplier() {
      this.timings = new ConcurrentHashMap<>();
      this.orderedTimingNames = new LinkedBlockingQueue<>();
      this.newTimingCreator = (s -> {
          this.orderedTimingNames.add(s);
          return new LongAdder();
        });
    }
    
    String[] getStrings() {
      List<String> strs = new ArrayList<>();
      BufferedReader br = new BufferedReader(new StringReader(get()));
      try {
        String line;
        while ((line = br.readLine()) != null)
          strs.add(line); 
      } catch (IOException e) {
        throw new RuntimeException(e);
      } 
      return strs.<String>toArray(new String[0]);
    }
    
    public String get() {
      long t = System.nanoTime();
      long knownTime = 0L;
      int maxKeyLength = 0;
      int maxValueLength = 0;
      for (Map.Entry<String, LongAdder> entry : this.timings.entrySet()) {
        maxKeyLength = Math.max(maxKeyLength, ((String)entry.getKey()).length());
        maxValueLength = Math.max(maxValueLength, Timing.toMillisPrint(((LongAdder)entry.getValue()).longValue()).length());
      } 
      maxKeyLength++;
      StringBuilder sb = new StringBuilder();
      sb.append("Accumulated compilation phase timings:\n\n");
      for (String timingName : this.orderedTimingNames) {
        int len = sb.length();
        sb.append(timingName);
        len = sb.length() - len;
        while (len++ < maxKeyLength)
          sb.append(' '); 
        long duration = ((LongAdder)this.timings.get(timingName)).longValue();
        String strDuration = Timing.toMillisPrint(duration);
        len = strDuration.length();
        for (int i = 0; i < maxValueLength - len; i++)
          sb.append(' '); 
        sb.append(strDuration)
          .append(" ms\n");
        knownTime += duration;
      } 
      long total = t - Timing.this.startTime;
      return sb.append("\nTotal runtime: ")
        .append(Timing.toMillisPrint(total))
        .append(" ms (Non-runtime: ")
        .append(Timing.toMillisPrint(knownTime))
        .append(" ms [")
        .append((int)(knownTime * 100.0D / total))
        .append("%])")
        .append("\n\nEmitted compile units: ")
        .append(CompileUnit.getEmittedUnitCount())
        .append("\nCompile units installed as named classes: ")
        .append(Context.getNamedInstalledScriptCount())
        .append("\nCompile units installed as anonymous classes: ")
        .append(Context.getAnonymousInstalledScriptCount())
        .toString();
    }
    
    private void accumulateTime(String module, long duration) {
      ((LongAdder)this.timings.computeIfAbsent(module, this.newTimingCreator)).add(duration);
    }
  }
}
