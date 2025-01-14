package cn.hutool.cache.impl;

import cn.hutool.core.collection.CopiedIter;
import java.util.Iterator;
import java.util.concurrent.locks.StampedLock;

public abstract class StampedCache<K, V> extends AbstractCache<K, V> {
  private static final long serialVersionUID = 1L;
  
  protected final StampedLock lock = new StampedLock();
  
  public void put(K key, V object, long timeout) {
    long stamp = this.lock.writeLock();
    try {
      putWithoutLock(key, object, timeout);
    } finally {
      this.lock.unlockWrite(stamp);
    } 
  }
  
  public boolean containsKey(K key) {
    return (null != get(key, false, false));
  }
  
  public V get(K key, boolean isUpdateLastAccess) {
    return get(key, isUpdateLastAccess, true);
  }
  
  public Iterator<CacheObj<K, V>> cacheObjIterator() {
    CopiedIter<CacheObj<K, V>> copiedIterator;
    long stamp = this.lock.readLock();
    try {
      copiedIterator = CopiedIter.copyOf(cacheObjIter());
    } finally {
      this.lock.unlockRead(stamp);
    } 
    return new CacheObjIterator<>((Iterator<CacheObj<K, V>>)copiedIterator);
  }
  
  public final int prune() {
    long stamp = this.lock.writeLock();
    try {
      return pruneCache();
    } finally {
      this.lock.unlockWrite(stamp);
    } 
  }
  
  public void remove(K key) {
    CacheObj<K, V> co;
    long stamp = this.lock.writeLock();
    try {
      co = removeWithoutLock(key);
    } finally {
      this.lock.unlockWrite(stamp);
    } 
    if (null != co)
      onRemove(co.key, co.obj); 
  }
  
  public void clear() {
    long stamp = this.lock.writeLock();
    try {
      this.cacheMap.clear();
    } finally {
      this.lock.unlockWrite(stamp);
    } 
  }
  
  private V get(K key, boolean isUpdateLastAccess, boolean isUpdateCount) {
    long stamp = this.lock.tryOptimisticRead();
    CacheObj<K, V> co = getWithoutLock(key);
    if (false == this.lock.validate(stamp)) {
      stamp = this.lock.readLock();
      try {
        co = getWithoutLock(key);
      } finally {
        this.lock.unlockRead(stamp);
      } 
    } 
    if (null == co) {
      if (isUpdateCount)
        this.missCount.increment(); 
      return null;
    } 
    if (false == co.isExpired()) {
      if (isUpdateCount)
        this.hitCount.increment(); 
      return co.get(isUpdateLastAccess);
    } 
    return getOrRemoveExpired(key, isUpdateCount);
  }
  
  private V getOrRemoveExpired(K key, boolean isUpdateCount) {
    CacheObj<K, V> co;
    long stamp = this.lock.writeLock();
    try {
      co = getWithoutLock(key);
      if (null == co)
        return null; 
      if (false == co.isExpired()) {
        if (isUpdateCount)
          this.hitCount.increment(); 
        return co.getValue();
      } 
      co = removeWithoutLock(key);
    } finally {
      this.lock.unlockWrite(stamp);
    } 
    if (null != co)
      onRemove(co.key, co.obj); 
    return null;
  }
}
