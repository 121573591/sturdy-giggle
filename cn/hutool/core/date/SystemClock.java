package cn.hutool.core.date;

import java.sql.Timestamp;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SystemClock {
  private final long period;
  
  private volatile long now;
  
  public SystemClock(long period) {
    this.period = period;
    this.now = System.currentTimeMillis();
    scheduleClockUpdating();
  }
  
  private void scheduleClockUpdating() {
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
          Thread thread = new Thread(runnable, "System Clock");
          thread.setDaemon(true);
          return thread;
        });
    scheduler.scheduleAtFixedRate(() -> this.now = System.currentTimeMillis(), this.period, this.period, TimeUnit.MILLISECONDS);
  }
  
  private long currentTimeMillis() {
    return this.now;
  }
  
  private static class InstanceHolder {
    public static final SystemClock INSTANCE = new SystemClock(1L);
  }
  
  public static long now() {
    return InstanceHolder.INSTANCE.currentTimeMillis();
  }
  
  public static String nowDate() {
    return (new Timestamp(InstanceHolder.INSTANCE.currentTimeMillis())).toString();
  }
}
