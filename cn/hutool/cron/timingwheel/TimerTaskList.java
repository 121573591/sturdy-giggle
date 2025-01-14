package cn.hutool.cron.timingwheel;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class TimerTaskList implements Delayed {
  private final AtomicLong expire;
  
  private final TimerTask root;
  
  public TimerTaskList() {
    this.expire = new AtomicLong(-1L);
    this.root = new TimerTask(null, -1L);
    this.root.prev = this.root;
    this.root.next = this.root;
  }
  
  public boolean setExpiration(long expire) {
    return (this.expire.getAndSet(expire) != expire);
  }
  
  public long getExpire() {
    return this.expire.get();
  }
  
  public void addTask(TimerTask timerTask) {
    synchronized (this) {
      if (timerTask.timerTaskList == null) {
        timerTask.timerTaskList = this;
        TimerTask tail = this.root.prev;
        timerTask.next = this.root;
        timerTask.prev = tail;
        tail.next = timerTask;
        this.root.prev = timerTask;
      } 
    } 
  }
  
  public void removeTask(TimerTask timerTask) {
    synchronized (this) {
      if (equals(timerTask.timerTaskList)) {
        timerTask.next.prev = timerTask.prev;
        timerTask.prev.next = timerTask.next;
        timerTask.timerTaskList = null;
        timerTask.next = null;
        timerTask.prev = null;
      } 
    } 
  }
  
  public synchronized void flush(Consumer<TimerTask> flush) {
    TimerTask timerTask = this.root.next;
    while (false == timerTask.equals(this.root)) {
      removeTask(timerTask);
      flush.accept(timerTask);
      timerTask = this.root.next;
    } 
    this.expire.set(-1L);
  }
  
  public long getDelay(TimeUnit unit) {
    return Math.max(0L, unit.convert(this.expire.get() - System.currentTimeMillis(), TimeUnit.MILLISECONDS));
  }
  
  public int compareTo(Delayed o) {
    if (o instanceof TimerTaskList)
      return Long.compare(this.expire.get(), ((TimerTaskList)o).expire.get()); 
    return 0;
  }
}
