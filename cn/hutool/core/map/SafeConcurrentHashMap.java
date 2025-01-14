package cn.hutool.core.map;

import cn.hutool.core.util.JdkUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class SafeConcurrentHashMap<K, V> extends ConcurrentHashMap<K, V> {
  private static final long serialVersionUID = 1L;
  
  public SafeConcurrentHashMap() {}
  
  public SafeConcurrentHashMap(int initialCapacity) {
    super(initialCapacity);
  }
  
  public SafeConcurrentHashMap(Map<? extends K, ? extends V> m) {
    super(m);
  }
  
  public SafeConcurrentHashMap(int initialCapacity, float loadFactor) {
    super(initialCapacity, loadFactor);
  }
  
  public SafeConcurrentHashMap(int initialCapacity, float loadFactor, int concurrencyLevel) {
    super(initialCapacity, loadFactor, concurrencyLevel);
  }
  
  public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
    if (JdkUtil.IS_JDK8)
      return MapUtil.computeIfAbsentForJdk8(this, key, mappingFunction); 
    return super.computeIfAbsent(key, mappingFunction);
  }
}
