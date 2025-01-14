package cn.hutool.cache.impl;

import cn.hutool.core.collection.CopiedIter;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

public abstract class ReentrantCache<K, V> extends AbstractCache<K, V> {
  private static final long serialVersionUID = 1L;
  
  protected final ReentrantLock lock = new ReentrantLock();
  
  public void put(K key, V object, long timeout) {
    this.lock.lock();
    try {
      putWithoutLock(key, object, timeout);
    } finally {
      this.lock.unlock();
    } 
  }
  
  public boolean containsKey(K key) {
    return (null != getOrRemoveExpired(key, false, false));
  }
  
  public V get(K key, boolean isUpdateLastAccess) {
    return getOrRemoveExpired(key, isUpdateLastAccess, true);
  }
  
  public Iterator<CacheObj<K, V>> cacheObjIterator() {
    CopiedIter<CacheObj<K, V>> copiedIterator;
    this.lock.lock();
    try {
      copiedIterator = CopiedIter.copyOf(cacheObjIter());
    } finally {
      this.lock.unlock();
    } 
    return new CacheObjIterator<>((Iterator<CacheObj<K, V>>)copiedIterator);
  }
  
  public final int prune() {
    this.lock.lock();
    try {
      return pruneCache();
    } finally {
      this.lock.unlock();
    } 
  }
  
  public void remove(K key) {
    CacheObj<K, V> co;
    this.lock.lock();
    try {
      co = removeWithoutLock(key);
    } finally {
      this.lock.unlock();
    } 
    if (null != co)
      onRemove(co.key, co.obj); 
  }
  
  public void clear() {
    this.lock.lock();
    try {
      this.cacheMap.clear();
    } finally {
      this.lock.unlock();
    } 
  }
  
  public String toString() {
    this.lock.lock();
    try {
      return super.toString();
    } finally {
      this.lock.unlock();
    } 
  }
  
  private V getOrRemoveExpired(K key, boolean isUpdateLastAccess, boolean isUpdateCount) {
    CacheObj<K, V> co;
    this.lock.lock();
    try {
      co = getWithoutLock(key);
      if (null != co && co.isExpired()) {
        removeWithoutLock(key);
        co = null;
      } 
    } finally {
      this.lock.unlock();
    } 
    if (null == co) {
      if (isUpdateCount)
        this.missCount.increment(); 
      return null;
    } 
    if (isUpdateCount)
      this.hitCount.increment(); 
    return co.get(isUpdateLastAccess);
  }
}
