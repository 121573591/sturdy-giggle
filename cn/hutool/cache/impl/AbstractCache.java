package cn.hutool.cache.impl;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheListener;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.lang.func.Func0;
import cn.hutool.core.lang.mutable.Mutable;
import cn.hutool.core.lang.mutable.MutableObj;
import cn.hutool.core.map.SafeConcurrentHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public abstract class AbstractCache<K, V> implements Cache<K, V> {
  private static final long serialVersionUID = 1L;
  
  protected Map<Mutable<K>, CacheObj<K, V>> cacheMap;
  
  protected final SafeConcurrentHashMap<K, Lock> keyLockMap = new SafeConcurrentHashMap();
  
  protected int capacity;
  
  protected long timeout;
  
  protected boolean existCustomTimeout;
  
  protected LongAdder hitCount = new LongAdder();
  
  protected LongAdder missCount = new LongAdder();
  
  protected CacheListener<K, V> listener;
  
  public void put(K key, V object) {
    put(key, object, this.timeout);
  }
  
  protected void putWithoutLock(K key, V object, long timeout) {
    CacheObj<K, V> co = new CacheObj<>(key, object, timeout);
    if (timeout != 0L)
      this.existCustomTimeout = true; 
    if (isFull())
      pruneCache(); 
    this.cacheMap.put(MutableObj.of(key), co);
  }
  
  public long getHitCount() {
    return this.hitCount.sum();
  }
  
  public long getMissCount() {
    return this.missCount.sum();
  }
  
  public V get(K key, boolean isUpdateLastAccess, Func0<V> supplier) {
    return get(key, isUpdateLastAccess, this.timeout, supplier);
  }
  
  public V get(K key, boolean isUpdateLastAccess, long timeout, Func0<V> supplier) {
    V v = (V)get(key, isUpdateLastAccess);
    if (null == v && null != supplier) {
      Lock keyLock = (Lock)this.keyLockMap.computeIfAbsent(key, k -> new ReentrantLock());
      keyLock.lock();
      try {
        CacheObj<K, V> co = getWithoutLock(key);
        if (null == co || co.isExpired()) {
          try {
            v = (V)supplier.call();
          } catch (Exception e) {
            throw ExceptionUtil.wrapRuntime(e);
          } 
          put(key, v, timeout);
        } else {
          v = co.get(isUpdateLastAccess);
        } 
      } finally {
        keyLock.unlock();
        this.keyLockMap.remove(key);
      } 
    } 
    return v;
  }
  
  protected CacheObj<K, V> getWithoutLock(K key) {
    return this.cacheMap.get(MutableObj.of(key));
  }
  
  public Iterator<V> iterator() {
    CacheObjIterator<K, V> copiedIterator = (CacheObjIterator<K, V>)cacheObjIterator();
    return new CacheValuesIterator<>(copiedIterator);
  }
  
  public int capacity() {
    return this.capacity;
  }
  
  public long timeout() {
    return this.timeout;
  }
  
  protected boolean isPruneExpiredActive() {
    return (this.timeout != 0L || this.existCustomTimeout);
  }
  
  public boolean isFull() {
    return (this.capacity > 0 && this.cacheMap.size() >= this.capacity);
  }
  
  public int size() {
    return this.cacheMap.size();
  }
  
  public boolean isEmpty() {
    return this.cacheMap.isEmpty();
  }
  
  public String toString() {
    return this.cacheMap.toString();
  }
  
  public AbstractCache<K, V> setListener(CacheListener<K, V> listener) {
    this.listener = listener;
    return this;
  }
  
  public Set<K> keySet() {
    return (Set<K>)this.cacheMap.keySet().stream().map(Mutable::get).collect(Collectors.toSet());
  }
  
  protected void onRemove(K key, V cachedObject) {
    CacheListener<K, V> listener = this.listener;
    if (null != listener)
      listener.onRemove(key, cachedObject); 
  }
  
  protected CacheObj<K, V> removeWithoutLock(K key) {
    return this.cacheMap.remove(MutableObj.of(key));
  }
  
  protected Iterator<CacheObj<K, V>> cacheObjIter() {
    return this.cacheMap.values().iterator();
  }
  
  protected abstract int pruneCache();
}
