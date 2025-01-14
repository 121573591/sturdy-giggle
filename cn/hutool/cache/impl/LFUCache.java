package cn.hutool.cache.impl;

import cn.hutool.core.lang.mutable.Mutable;
import java.util.HashMap;
import java.util.Iterator;

public class LFUCache<K, V> extends StampedCache<K, V> {
  private static final long serialVersionUID = 1L;
  
  public LFUCache(int capacity) {
    this(capacity, 0L);
  }
  
  public LFUCache(int capacity, long timeout) {
    if (Integer.MAX_VALUE == capacity)
      capacity--; 
    this.capacity = capacity;
    this.timeout = timeout;
    this.cacheMap = new HashMap<>(capacity + 1, 1.0F);
  }
  
  protected int pruneCache() {
    int count = 0;
    CacheObj<K, V> comin = null;
    Iterator<CacheObj<K, V>> values = cacheObjIter();
    while (values.hasNext()) {
      CacheObj<K, V> co = values.next();
      if (co.isExpired() == true) {
        values.remove();
        onRemove(co.key, co.obj);
        count++;
        continue;
      } 
      if (comin == null || co.accessCount.get() < comin.accessCount.get())
        comin = co; 
    } 
    if (isFull() && comin != null) {
      long minAccessCount = comin.accessCount.get();
      values = cacheObjIter();
      while (values.hasNext()) {
        CacheObj<K, V> co1 = values.next();
        if (co1.accessCount.addAndGet(-minAccessCount) <= 0L) {
          values.remove();
          onRemove(co1.key, co1.obj);
          count++;
        } 
      } 
    } 
    return count;
  }
}
