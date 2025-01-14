package cn.hutool.core.map;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class TransMap<K, V> extends MapWrapper<K, V> {
  private static final long serialVersionUID = 1L;
  
  public TransMap(Supplier<Map<K, V>> mapFactory) {
    super(mapFactory);
  }
  
  public TransMap(Map<K, V> emptyMap) {
    super(emptyMap);
  }
  
  public V get(Object key) {
    return super.get(customKey(key));
  }
  
  public V put(K key, V value) {
    return super.put(customKey(key), customValue(value));
  }
  
  public void putAll(Map<? extends K, ? extends V> m) {
    m.forEach(this::put);
  }
  
  public boolean containsKey(Object key) {
    return super.containsKey(customKey(key));
  }
  
  public V remove(Object key) {
    return super.remove(customKey(key));
  }
  
  public boolean remove(Object key, Object value) {
    return super.remove(customKey(key), customValue(value));
  }
  
  public boolean replace(K key, V oldValue, V newValue) {
    return super.replace(customKey(key), customValue(oldValue), customValue(newValue));
  }
  
  public V replace(K key, V value) {
    return super.replace(customKey(key), customValue(value));
  }
  
  public V getOrDefault(Object key, V defaultValue) {
    return super.getOrDefault(customKey(key), customValue(defaultValue));
  }
  
  public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
    return super.computeIfPresent(customKey(key), (k, v) -> remappingFunction.apply(customKey(k), customValue(v)));
  }
  
  public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
    return super.compute(customKey(key), (k, v) -> remappingFunction.apply(customKey(k), customValue(v)));
  }
  
  public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
    return super.merge(customKey(key), customValue(value), (v1, v2) -> remappingFunction.apply(customValue(v1), customValue(v2)));
  }
  
  public V putIfAbsent(K key, V value) {
    return super.putIfAbsent(customKey(key), customValue(value));
  }
  
  public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
    return super.computeIfAbsent(customKey(key), mappingFunction);
  }
  
  protected abstract K customKey(Object paramObject);
  
  protected abstract V customValue(Object paramObject);
}
