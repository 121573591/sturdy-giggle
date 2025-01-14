package cn.hutool.cron.timingwheel;

import cn.hutool.log.StaticLog;
import java.util.function.Consumer;

public class TimingWheel {
  private final long tickMs;
  
  private final int wheelSize;
  
  private final long interval;
  
  private final TimerTaskList[] timerTaskLists;
  
  private long currentTime;
  
  private volatile TimingWheel overflowWheel;
  
  private final Consumer<TimerTaskList> consumer;
  
  public TimingWheel(long tickMs, int wheelSize, Consumer<TimerTaskList> consumer) {
    this(tickMs, wheelSize, System.currentTimeMillis(), consumer);
  }
  
  public TimingWheel(long tickMs, int wheelSize, long currentTime, Consumer<TimerTaskList> consumer) {
    this.tickMs = tickMs;
    this.wheelSize = wheelSize;
    this.interval = tickMs * wheelSize;
    this.timerTaskLists = new TimerTaskList[wheelSize];
    initTimerTaskList();
    this.currentTime = currentTime - currentTime % tickMs;
    this.consumer = consumer;
  }
  
  public boolean addTask(TimerTask timerTask) {
    long expiration = timerTask.getDelayMs();
    if (expiration < this.currentTime + this.tickMs)
      return false; 
    if (expiration < this.currentTime + this.interval) {
      long virtualId = expiration / this.tickMs;
      int index = (int)(virtualId % this.wheelSize);
      StaticLog.debug("tickMs: {} ------index: {} ------expiration: {}", new Object[] { Long.valueOf(this.tickMs), Integer.valueOf(index), Long.valueOf(expiration) });
      TimerTaskList timerTaskList = this.timerTaskLists[index];
      timerTaskList.addTask(timerTask);
      if (timerTaskList.setExpiration(virtualId * this.tickMs))
        this.consumer.accept(timerTaskList); 
    } else {
      TimingWheel timeWheel = getOverflowWheel();
      timeWheel.addTask(timerTask);
    } 
    return true;
  }
  
  public void advanceClock(long timestamp) {
    if (timestamp >= this.currentTime + this.tickMs) {
      this.currentTime = timestamp - timestamp % this.tickMs;
      if (this.overflowWheel != null)
        getOverflowWheel().advanceClock(timestamp); 
    } 
  }
  
  private TimingWheel getOverflowWheel() {
    if (this.overflowWheel == null)
      synchronized (this) {
        if (this.overflowWheel == null)
          this.overflowWheel = new TimingWheel(this.interval, this.wheelSize, this.currentTime, this.consumer); 
      }  
    return this.overflowWheel;
  }
  
  private void initTimerTaskList() {
    for (int i = 0; i < this.timerTaskLists.length; i++)
      this.timerTaskLists[i] = new TimerTaskList(); 
  }
}
