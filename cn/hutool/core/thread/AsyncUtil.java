package cn.hutool.core.thread;

import java.util.concurrent.CompletableFuture;

public class AsyncUtil {
  public static void waitAll(CompletableFuture<?>... tasks) {
    try {
      CompletableFuture.allOf(tasks).get();
    } catch (InterruptedException|java.util.concurrent.ExecutionException e) {
      throw new ThreadException(e);
    } 
  }
  
  public static <T> T waitAny(CompletableFuture<?>... tasks) {
    try {
      return (T)CompletableFuture.anyOf(tasks).get();
    } catch (InterruptedException|java.util.concurrent.ExecutionException e) {
      throw new ThreadException(e);
    } 
  }
  
  public static <T> T get(CompletableFuture<T> task) {
    try {
      return task.get();
    } catch (InterruptedException|java.util.concurrent.ExecutionException e) {
      throw new ThreadException(e);
    } 
  }
}
