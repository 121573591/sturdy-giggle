package cn.hutool.cache.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CacheObjIterator<K, V> implements Iterator<CacheObj<K, V>>, Serializable {
  private static final long serialVersionUID = 1L;
  
  private final Iterator<CacheObj<K, V>> iterator;
  
  private CacheObj<K, V> nextValue;
  
  CacheObjIterator(Iterator<CacheObj<K, V>> iterator) {
    this.iterator = iterator;
    nextValue();
  }
  
  public boolean hasNext() {
    return (this.nextValue != null);
  }
  
  public CacheObj<K, V> next() {
    if (false == hasNext())
      throw new NoSuchElementException(); 
    CacheObj<K, V> cachedObject = this.nextValue;
    nextValue();
    return cachedObject;
  }
  
  public void remove() {
    throw new UnsupportedOperationException("Cache values Iterator is not support to modify.");
  }
  
  private void nextValue() {
    while (this.iterator.hasNext()) {
      this.nextValue = this.iterator.next();
      if (!this.nextValue.isExpired())
        return; 
    } 
    this.nextValue = null;
  }
}
