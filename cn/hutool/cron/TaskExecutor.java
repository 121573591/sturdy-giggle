package cn.hutool.cron;

import cn.hutool.cron.task.CronTask;
import cn.hutool.cron.task.Task;

public class TaskExecutor implements Runnable {
  private final Scheduler scheduler;
  
  private final CronTask task;
  
  public Task getTask() {
    return this.task.getRaw();
  }
  
  public CronTask getCronTask() {
    return this.task;
  }
  
  public TaskExecutor(Scheduler scheduler, CronTask task) {
    this.scheduler = scheduler;
    this.task = task;
  }
  
  public void run() {
    try {
      this.scheduler.listenerManager.notifyTaskStart(this);
      this.task.execute();
      this.scheduler.listenerManager.notifyTaskSucceeded(this);
    } catch (Exception e) {
      this.scheduler.listenerManager.notifyTaskFailed(this, e);
    } finally {
      this.scheduler.taskExecutorManager.notifyExecutorCompleted(this);
    } 
  }
}
