package cn.hutool.core.lang;

import cn.hutool.core.collection.TransIter;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.lang.func.Func0;
import cn.hutool.core.lang.mutable.Mutable;
import cn.hutool.core.lang.mutable.MutableObj;
import cn.hutool.core.map.SafeConcurrentHashMap;
import cn.hutool.core.map.WeakConcurrentMap;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Predicate;

public class SimpleCache<K, V> implements Iterable<Map.Entry<K, V>>, Serializable {
  private static final long serialVersionUID = 1L;
  
  private final Map<Mutable<K>, V> rawMap;
  
  private final ReadWriteLock lock = new ReentrantReadWriteLock();
  
  protected final Map<K, Lock> keyLockMap = (Map<K, Lock>)new SafeConcurrentHashMap();
  
  public SimpleCache() {
    this((Map<Mutable<K>, V>)new WeakConcurrentMap());
  }
  
  public SimpleCache(Map<Mutable<K>, V> initMap) {
    this.rawMap = initMap;
  }
  
  public V get(K key) {
    this.lock.readLock().lock();
    try {
      return this.rawMap.get(MutableObj.of(key));
    } finally {
      this.lock.readLock().unlock();
    } 
  }
  
  public V get(K key, Func0<V> supplier) {
    return get(key, null, supplier);
  }
  
  public V get(K key, Predicate<V> validPredicate, Func0<V> supplier) {
    V v = get(key);
    if (null != validPredicate && null != v && false == validPredicate.test(v))
      v = null; 
    if (null == v && null != supplier) {
      Lock keyLock = this.keyLockMap.computeIfAbsent(key, k -> new ReentrantLock());
      keyLock.lock();
      try {
        v = get(key);
        if (null == v || (null != validPredicate && false == validPredicate.test(v))) {
          try {
            v = (V)supplier.call();
          } catch (Exception e) {
            throw ExceptionUtil.wrapRuntime(e);
          } 
          put(key, v);
        } 
      } finally {
        keyLock.unlock();
        this.keyLockMap.remove(key);
      } 
    } 
    return v;
  }
  
  public V put(K key, V value) {
    this.lock.writeLock().lock();
    try {
      this.rawMap.put(MutableObj.of(key), value);
    } finally {
      this.lock.writeLock().unlock();
    } 
    return value;
  }
  
  public V remove(K key) {
    this.lock.writeLock().lock();
    try {
      return this.rawMap.remove(MutableObj.of(key));
    } finally {
      this.lock.writeLock().unlock();
    } 
  }
  
  public void clear() {
    this.lock.writeLock().lock();
    try {
      this.rawMap.clear();
    } finally {
      this.lock.writeLock().unlock();
    } 
  }
  
  public Iterator<Map.Entry<K, V>> iterator() {
    return (Iterator<Map.Entry<K, V>>)new TransIter(this.rawMap.entrySet().iterator(), entry -> new Map.Entry<K, V>() {
          public K getKey() {
            return (K)((Mutable)entry.getKey()).get();
          }
          
          public V getValue() {
            return (V)entry.getValue();
          }
          
          public V setValue(Object value) {
            return (V)entry.setValue(value);
          }
        });
  }
}
