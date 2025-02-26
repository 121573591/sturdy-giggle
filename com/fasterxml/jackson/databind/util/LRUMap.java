package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap;
import java.io.Serializable;
import java.util.Map;
import java.util.function.BiConsumer;

public class LRUMap<K, V> implements LookupCache<K, V>, Serializable {
  private static final long serialVersionUID = 2L;
  
  protected final int _initialEntries;
  
  protected final int _maxEntries;
  
  protected final transient PrivateMaxEntriesMap<K, V> _map;
  
  public LRUMap(int initialEntries, int maxEntries) {
    this._initialEntries = initialEntries;
    this._maxEntries = maxEntries;
    this
      
      ._map = (new PrivateMaxEntriesMap.Builder()).initialCapacity(initialEntries).maximumCapacity(maxEntries).concurrencyLevel(4).build();
  }
  
  public LookupCache<K, V> emptyCopy() {
    return new LRUMap(this._initialEntries, this._maxEntries);
  }
  
  public V put(K key, V value) {
    return (V)this._map.put(key, value);
  }
  
  public V putIfAbsent(K key, V value) {
    return (V)this._map.putIfAbsent(key, value);
  }
  
  public V get(Object key) {
    return (V)this._map.get(key);
  }
  
  public void clear() {
    this._map.clear();
  }
  
  public int size() {
    return this._map.size();
  }
  
  public void contents(BiConsumer<K, V> consumer) {
    for (Map.Entry<K, V> entry : (Iterable<Map.Entry<K, V>>)this._map.entrySet())
      consumer.accept(entry.getKey(), entry.getValue()); 
  }
  
  protected Object readResolve() {
    return new LRUMap(this._initialEntries, this._maxEntries);
  }
}
