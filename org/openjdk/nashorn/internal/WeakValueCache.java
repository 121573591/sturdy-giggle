package org.openjdk.nashorn.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.function.Function;

public final class WeakValueCache<K, V> {
  private final HashMap<K, KeyValueReference<K, V>> map = new HashMap<>();
  
  private final ReferenceQueue<V> refQueue = new ReferenceQueue<>();
  
  public V get(K key) {
    while (true) {
      KeyValueReference<?, ?> keyValueReference = (KeyValueReference)this.refQueue.poll();
      if (keyValueReference == null)
        break; 
      this.map.remove(keyValueReference.key, keyValueReference);
    } 
    KeyValueReference<K, V> ref = this.map.get(key);
    if (ref != null)
      return ref.get(); 
    return null;
  }
  
  public V getOrCreate(K key, Function<? super K, ? extends V> creator) {
    V value = get(key);
    if (value == null) {
      value = creator.apply(key);
      this.map.put(key, new KeyValueReference<>(key, value));
    } 
    return value;
  }
  
  private static class KeyValueReference<K, V> extends WeakReference<V> {
    final K key;
    
    KeyValueReference(K key, V value) {
      super(value);
      this.key = key;
    }
  }
}
