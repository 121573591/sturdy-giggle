package cn.hutool.cron;

import cn.hutool.core.util.StrUtil;
import cn.hutool.cron.pattern.CronPattern;
import cn.hutool.cron.task.CronTask;
import cn.hutool.cron.task.Task;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TaskTable implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public static final int DEFAULT_CAPACITY = 10;
  
  private final ReadWriteLock lock;
  
  private final List<String> ids;
  
  private final List<CronPattern> patterns;
  
  private final List<Task> tasks;
  
  private int size;
  
  public TaskTable() {
    this(10);
  }
  
  public TaskTable(int initialCapacity) {
    this.lock = new ReentrantReadWriteLock();
    this.ids = new ArrayList<>(initialCapacity);
    this.patterns = new ArrayList<>(initialCapacity);
    this.tasks = new ArrayList<>(initialCapacity);
  }
  
  public TaskTable add(String id, CronPattern pattern, Task task) {
    Lock writeLock = this.lock.writeLock();
    writeLock.lock();
    try {
      if (this.ids.contains(id))
        throw new CronException("Id [{}] has been existed!", new Object[] { id }); 
      this.ids.add(id);
      this.patterns.add(pattern);
      this.tasks.add(task);
      this.size++;
    } finally {
      writeLock.unlock();
    } 
    return this;
  }
  
  public List<String> getIds() {
    Lock readLock = this.lock.readLock();
    readLock.lock();
    try {
      return Collections.unmodifiableList(this.ids);
    } finally {
      readLock.unlock();
    } 
  }
  
  public List<CronPattern> getPatterns() {
    Lock readLock = this.lock.readLock();
    readLock.lock();
    try {
      return Collections.unmodifiableList(this.patterns);
    } finally {
      readLock.unlock();
    } 
  }
  
  public List<Task> getTasks() {
    Lock readLock = this.lock.readLock();
    readLock.lock();
    try {
      return Collections.unmodifiableList(this.tasks);
    } finally {
      readLock.unlock();
    } 
  }
  
  public boolean remove(String id) {
    Lock writeLock = this.lock.writeLock();
    writeLock.lock();
    try {
      int index = this.ids.indexOf(id);
      if (index < 0)
        return false; 
      this.tasks.remove(index);
      this.patterns.remove(index);
      this.ids.remove(index);
      this.size--;
    } finally {
      writeLock.unlock();
    } 
    return true;
  }
  
  public boolean updatePattern(String id, CronPattern pattern) {
    Lock writeLock = this.lock.writeLock();
    writeLock.lock();
    try {
      int index = this.ids.indexOf(id);
      if (index > -1) {
        this.patterns.set(index, pattern);
        return true;
      } 
    } finally {
      writeLock.unlock();
    } 
    return false;
  }
  
  public Task getTask(int index) {
    Lock readLock = this.lock.readLock();
    readLock.lock();
    try {
      return this.tasks.get(index);
    } finally {
      readLock.unlock();
    } 
  }
  
  public Task getTask(String id) {
    int index = this.ids.indexOf(id);
    if (index > -1)
      return getTask(index); 
    return null;
  }
  
  public CronPattern getPattern(int index) {
    Lock readLock = this.lock.readLock();
    readLock.lock();
    try {
      return this.patterns.get(index);
    } finally {
      readLock.unlock();
    } 
  }
  
  public int size() {
    return this.size;
  }
  
  public boolean isEmpty() {
    return (this.size < 1);
  }
  
  public CronPattern getPattern(String id) {
    int index = this.ids.indexOf(id);
    if (index > -1)
      return getPattern(index); 
    return null;
  }
  
  public void executeTaskIfMatch(Scheduler scheduler, long millis) {
    Lock readLock = this.lock.readLock();
    readLock.lock();
    try {
      executeTaskIfMatchInternal(scheduler, millis);
    } finally {
      readLock.unlock();
    } 
  }
  
  public String toString() {
    StringBuilder builder = StrUtil.builder();
    for (int i = 0; i < this.size; i++) {
      builder.append(StrUtil.format("[{}] [{}] [{}]\n", new Object[] { this.ids
              .get(i), this.patterns.get(i), this.tasks.get(i) }));
    } 
    return builder.toString();
  }
  
  protected void executeTaskIfMatchInternal(Scheduler scheduler, long millis) {
    for (int i = 0; i < this.size; i++) {
      if (((CronPattern)this.patterns.get(i)).match(scheduler.config.timezone, millis, scheduler.config.matchSecond))
        scheduler.taskExecutorManager.spawnExecutor(new CronTask(this.ids.get(i), this.patterns.get(i), this.tasks.get(i))); 
    } 
  }
}
