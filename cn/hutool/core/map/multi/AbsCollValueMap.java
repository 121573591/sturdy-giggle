package cn.hutool.core.map.multi;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapWrapper;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbsCollValueMap<K, V, C extends Collection<V>> extends MapWrapper<K, C> {
  private static final long serialVersionUID = 1L;
  
  protected static final int DEFAULT_COLLECTION_INITIAL_CAPACITY = 3;
  
  public AbsCollValueMap() {
    this(16);
  }
  
  public AbsCollValueMap(int initialCapacity) {
    this(initialCapacity, 0.75F);
  }
  
  public AbsCollValueMap(Map<? extends K, C> m) {
    this(0.75F, m);
  }
  
  public AbsCollValueMap(float loadFactor, Map<? extends K, C> m) {
    this(m.size(), loadFactor);
    putAll(m);
  }
  
  public AbsCollValueMap(int initialCapacity, float loadFactor) {
    super(new HashMap<>(initialCapacity, loadFactor));
  }
  
  public void putAllValues(Map<? extends K, ? extends Collection<V>> m) {
    if (null != m)
      m.forEach((key, valueColl) -> {
            if (null != valueColl)
              valueColl.forEach(()); 
          }); 
  }
  
  public void putValue(K key, V value) {
    Collection<V> collection = (Collection)get(key);
    if (null == collection) {
      collection = (Collection<V>)createCollection();
      put(key, collection);
    } 
    collection.add(value);
  }
  
  public V get(K key, int index) {
    Collection<V> collection = (Collection<V>)get(key);
    return (V)CollUtil.get(collection, index);
  }
  
  public boolean removeValue(K key, V value) {
    Collection collection = (Collection)get(key);
    return (null != collection && collection.remove(value));
  }
  
  public boolean removeValues(K key, Collection<V> values) {
    Collection collection = (Collection)get(key);
    return (null != collection && collection.removeAll(values));
  }
  
  protected abstract C createCollection();
}
