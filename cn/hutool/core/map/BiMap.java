package cn.hutool.core.map;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BiMap<K, V> extends MapWrapper<K, V> {
  private static final long serialVersionUID = 1L;
  
  private Map<V, K> inverse;
  
  public BiMap(Map<K, V> raw) {
    super(raw);
  }
  
  public V put(K key, V value) {
    V oldValue = super.put(key, value);
    if (null != this.inverse) {
      if (null != oldValue)
        this.inverse.remove(oldValue); 
      this.inverse.put(value, key);
    } 
    return super.put(key, value);
  }
  
  public void putAll(Map<? extends K, ? extends V> m) {
    super.putAll(m);
    if (null != this.inverse)
      m.forEach((key, value) -> this.inverse.put((V)value, (K)key)); 
  }
  
  public V remove(Object key) {
    V v = super.remove(key);
    if (null != this.inverse && null != v)
      this.inverse.remove(v); 
    return v;
  }
  
  public boolean remove(Object key, Object value) {
    return (super.remove(key, value) && null != this.inverse && this.inverse.remove(value, key));
  }
  
  public void clear() {
    super.clear();
    this.inverse = null;
  }
  
  public Map<V, K> getInverse() {
    if (null == this.inverse)
      this.inverse = MapUtil.inverse(getRaw()); 
    return this.inverse;
  }
  
  public K getKey(V value) {
    return getInverse().get(value);
  }
  
  public V putIfAbsent(K key, V value) {
    if (null != this.inverse)
      this.inverse.putIfAbsent(value, key); 
    return super.putIfAbsent(key, value);
  }
  
  public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
    V result = super.computeIfAbsent(key, mappingFunction);
    resetInverseMap();
    return result;
  }
  
  public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
    V result = super.computeIfPresent(key, remappingFunction);
    resetInverseMap();
    return result;
  }
  
  public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
    V result = super.compute(key, remappingFunction);
    resetInverseMap();
    return result;
  }
  
  public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
    V result = super.merge(key, value, remappingFunction);
    resetInverseMap();
    return result;
  }
  
  private void resetInverseMap() {
    if (null != this.inverse)
      this.inverse = null; 
  }
}
