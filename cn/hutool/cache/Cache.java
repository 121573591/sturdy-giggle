package cn.hutool.cache;

import cn.hutool.cache.impl.CacheObj;
import cn.hutool.core.lang.func.Func0;
import java.io.Serializable;
import java.util.Iterator;

public interface Cache<K, V> extends Iterable<V>, Serializable {
  int capacity();
  
  long timeout();
  
  void put(K paramK, V paramV);
  
  void put(K paramK, V paramV, long paramLong);
  
  default V get(K key) {
    return get(key, true);
  }
  
  default V get(K key, Func0<V> supplier) {
    return get(key, true, supplier);
  }
  
  V get(K paramK, boolean paramBoolean, Func0<V> paramFunc0);
  
  V get(K paramK, boolean paramBoolean, long paramLong, Func0<V> paramFunc0);
  
  V get(K paramK, boolean paramBoolean);
  
  Iterator<CacheObj<K, V>> cacheObjIterator();
  
  int prune();
  
  boolean isFull();
  
  void remove(K paramK);
  
  void clear();
  
  int size();
  
  boolean isEmpty();
  
  boolean containsKey(K paramK);
  
  default Cache<K, V> setListener(CacheListener<K, V> listener) {
    return this;
  }
}
