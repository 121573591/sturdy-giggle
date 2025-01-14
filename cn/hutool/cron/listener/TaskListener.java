package cn.hutool.cron.listener;

import cn.hutool.cron.TaskExecutor;

public interface TaskListener {
  void onStart(TaskExecutor paramTaskExecutor);
  
  void onSucceeded(TaskExecutor paramTaskExecutor);
  
  void onFailed(TaskExecutor paramTaskExecutor, Throwable paramThrowable);
}
