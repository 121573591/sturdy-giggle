package cn.hutool.core.thread;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Consumer;

public class BlockPolicy implements RejectedExecutionHandler {
  private final Consumer<Runnable> handlerwhenshutdown;
  
  public BlockPolicy(Consumer<Runnable> handlerwhenshutdown) {
    this.handlerwhenshutdown = handlerwhenshutdown;
  }
  
  public BlockPolicy() {
    this(null);
  }
  
  public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
    if (false == e.isShutdown()) {
      try {
        e.getQueue().put(r);
      } catch (InterruptedException ex) {
        throw new RejectedExecutionException("Task " + r + " rejected from " + e);
      } 
    } else if (null != this.handlerwhenshutdown) {
      this.handlerwhenshutdown.accept(r);
    } 
  }
}
