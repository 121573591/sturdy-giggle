package cn.hutool.core.lang.intern;

import cn.hutool.core.map.WeakConcurrentMap;
import java.lang.ref.WeakReference;
import java.util.function.Function;

public class WeakInterner<T> implements Interner<T> {
  private final WeakConcurrentMap<T, WeakReference<T>> cache = new WeakConcurrentMap();
  
  public T intern(T sample) {
    if (sample == null)
      return null; 
    while (true) {
      T val = ((WeakReference<T>)this.cache.computeIfAbsent(sample, WeakReference::new)).get();
      if (val != null)
        return val; 
    } 
  }
}
