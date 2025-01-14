package cn.hutool.core.thread;

import cn.hutool.core.exceptions.UtilException;
import java.io.Closeable;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public class SyncFinisher implements Closeable {
  private final Set<Worker> workers;
  
  private final int threadSize;
  
  private ExecutorService executorService;
  
  private boolean isBeginAtSameTime;
  
  private final CountDownLatch beginLatch;
  
  private CountDownLatch endLatch;
  
  private Thread.UncaughtExceptionHandler exceptionHandler;
  
  public SyncFinisher(int threadSize) {
    this.beginLatch = new CountDownLatch(1);
    this.threadSize = threadSize;
    this.workers = new LinkedHashSet<>();
  }
  
  public SyncFinisher setBeginAtSameTime(boolean isBeginAtSameTime) {
    this.isBeginAtSameTime = isBeginAtSameTime;
    return this;
  }
  
  public SyncFinisher setExceptionHandler(Thread.UncaughtExceptionHandler exceptionHandler) {
    this.exceptionHandler = exceptionHandler;
    return this;
  }
  
  public SyncFinisher addRepeatWorker(final Runnable runnable) {
    for (int i = 0; i < this.threadSize; i++) {
      addWorker(new Worker() {
            public void work() {
              runnable.run();
            }
          });
    } 
    return this;
  }
  
  public SyncFinisher addWorker(final Runnable runnable) {
    return addWorker(new Worker() {
          public void work() {
            runnable.run();
          }
        });
  }
  
  public synchronized SyncFinisher addWorker(Worker worker) {
    this.workers.add(worker);
    return this;
  }
  
  public void start() {
    start(true);
  }
  
  public void start(boolean sync) {
    this.endLatch = new CountDownLatch(this.workers.size());
    if (null == this.executorService || this.executorService.isShutdown())
      this.executorService = buildExecutor(); 
    for (Worker worker : this.workers) {
      if (null != this.exceptionHandler) {
        this.executorService.execute(worker);
        continue;
      } 
      this.executorService.submit(worker);
    } 
    this.beginLatch.countDown();
    if (sync)
      try {
        this.endLatch.await();
      } catch (InterruptedException e) {
        throw new UtilException(e);
      }  
  }
  
  public void stop() {
    if (null != this.executorService) {
      this.executorService.shutdown();
      this.executorService = null;
    } 
    clearWorker();
  }
  
  public void stopNow() {
    if (null != this.executorService) {
      this.executorService.shutdownNow();
      this.executorService = null;
    } 
    clearWorker();
  }
  
  public void clearWorker() {
    this.workers.clear();
  }
  
  public long count() {
    return this.endLatch.getCount();
  }
  
  public void close() throws IOException {
    stop();
  }
  
  public abstract class Worker implements Runnable {
    public void run() {
      if (SyncFinisher.this.isBeginAtSameTime)
        try {
          SyncFinisher.this.beginLatch.await();
        } catch (InterruptedException e) {
          throw new UtilException(e);
        }  
      try {
        work();
      } finally {
        SyncFinisher.this.endLatch.countDown();
      } 
    }
    
    public abstract void work();
  }
  
  private ExecutorService buildExecutor() {
    return ExecutorBuilder.create()
      .setCorePoolSize(this.threadSize)
      .setThreadFactory(new NamedThreadFactory("hutool-", null, false, this.exceptionHandler))
      .build();
  }
}
