package cn.hutool.cron.listener;

import cn.hutool.cron.TaskExecutor;
import cn.hutool.log.StaticLog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskListenerManager implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private final List<TaskListener> listeners = new ArrayList<>();
  
  public TaskListenerManager addListener(TaskListener listener) {
    synchronized (this.listeners) {
      this.listeners.add(listener);
    } 
    return this;
  }
  
  public TaskListenerManager removeListener(TaskListener listener) {
    synchronized (this.listeners) {
      this.listeners.remove(listener);
    } 
    return this;
  }
  
  public void notifyTaskStart(TaskExecutor executor) {
    synchronized (this.listeners) {
      for (TaskListener taskListener : this.listeners) {
        TaskListener listener = taskListener;
        if (null != listener)
          listener.onStart(executor); 
      } 
    } 
  }
  
  public void notifyTaskSucceeded(TaskExecutor executor) {
    synchronized (this.listeners) {
      for (TaskListener listener : this.listeners)
        listener.onSucceeded(executor); 
    } 
  }
  
  public void notifyTaskFailed(TaskExecutor executor, Throwable exception) {
    synchronized (this.listeners) {
      int size = this.listeners.size();
      if (size > 0) {
        for (TaskListener listener : this.listeners)
          listener.onFailed(executor, exception); 
      } else {
        StaticLog.error(exception, exception.getMessage(), new Object[0]);
      } 
    } 
  }
}
