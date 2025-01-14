package cn.hutool.cron.timingwheel;

import cn.hutool.core.thread.ThreadUtil;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class SystemTimer {
  private final TimingWheel timeWheel;
  
  private final DelayQueue<TimerTaskList> delayQueue = new DelayQueue<>();
  
  private long delayQueueTimeout = 100L;
  
  private ExecutorService bossThreadPool;
  
  private volatile boolean isRunning;
  
  public SystemTimer() {
    this.timeWheel = new TimingWheel(1L, 20, this.delayQueue::offer);
  }
  
  public SystemTimer setDelayQueueTimeout(long delayQueueTimeout) {
    this.delayQueueTimeout = delayQueueTimeout;
    return this;
  }
  
  public SystemTimer start() {
    this.bossThreadPool = ThreadUtil.newSingleExecutor();
    this.isRunning = true;
    this.bossThreadPool.submit(() -> {
          do {
          
          } while (false != advanceClock());
        });
    return this;
  }
  
  public void stop() {
    this.isRunning = false;
    this.bossThreadPool.shutdown();
  }
  
  public void addTask(TimerTask timerTask) {
    if (false == this.timeWheel.addTask(timerTask))
      ThreadUtil.execAsync(timerTask.getTask()); 
  }
  
  private boolean advanceClock() {
    if (false == this.isRunning)
      return false; 
    try {
      TimerTaskList timerTaskList = poll();
      if (null != timerTaskList) {
        this.timeWheel.advanceClock(timerTaskList.getExpire());
        timerTaskList.flush(this::addTask);
      } 
    } catch (InterruptedException ignore) {
      return false;
    } 
    return true;
  }
  
  private TimerTaskList poll() throws InterruptedException {
    return (this.delayQueueTimeout > 0L) ? this.delayQueue
      .poll(this.delayQueueTimeout, TimeUnit.MILLISECONDS) : this.delayQueue
      .poll();
  }
}
