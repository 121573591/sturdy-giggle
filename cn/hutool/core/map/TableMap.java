package cn.hutool.core.map;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class TableMap<K, V> implements Map<K, V>, Iterable<Map.Entry<K, V>>, Serializable {
  private static final long serialVersionUID = 1L;
  
  private static final int DEFAULT_CAPACITY = 10;
  
  private final List<K> keys;
  
  private final List<V> values;
  
  public TableMap() {
    this(10);
  }
  
  public TableMap(int size) {
    this.keys = new ArrayList<>(size);
    this.values = new ArrayList<>(size);
  }
  
  public TableMap(K[] keys, V[] values) {
    this.keys = CollUtil.toList((Object[])keys);
    this.values = CollUtil.toList((Object[])values);
  }
  
  public int size() {
    return this.keys.size();
  }
  
  public boolean isEmpty() {
    return CollUtil.isEmpty(this.keys);
  }
  
  public boolean containsKey(Object key) {
    return this.keys.contains(key);
  }
  
  public boolean containsValue(Object value) {
    return this.values.contains(value);
  }
  
  public V get(Object key) {
    int index = this.keys.indexOf(key);
    if (index > -1)
      return this.values.get(index); 
    return null;
  }
  
  public K getKey(V value) {
    int index = this.values.indexOf(value);
    if (index > -1)
      return this.keys.get(index); 
    return null;
  }
  
  public List<V> getValues(K key) {
    return CollUtil.getAny(this.values, 
        
        ListUtil.indexOfAll(this.keys, ele -> ObjectUtil.equal(ele, key)));
  }
  
  public List<K> getKeys(V value) {
    return CollUtil.getAny(this.keys, 
        
        ListUtil.indexOfAll(this.values, ele -> ObjectUtil.equal(ele, value)));
  }
  
  public V put(K key, V value) {
    this.keys.add(key);
    this.values.add(value);
    return null;
  }
  
  public V remove(Object key) {
    V lastValue = null;
    int index;
    while ((index = this.keys.indexOf(key)) > -1)
      lastValue = removeByIndex(index); 
    return lastValue;
  }
  
  public V removeByIndex(int index) {
    this.keys.remove(index);
    return this.values.remove(index);
  }
  
  public void putAll(Map<? extends K, ? extends V> m) {
    for (Map.Entry<? extends K, ? extends V> entry : m.entrySet())
      put(entry.getKey(), entry.getValue()); 
  }
  
  public void clear() {
    this.keys.clear();
    this.values.clear();
  }
  
  public Set<K> keySet() {
    return new HashSet<>(this.keys);
  }
  
  public List<K> keys() {
    return Collections.unmodifiableList(this.keys);
  }
  
  public Collection<V> values() {
    return Collections.unmodifiableList(this.values);
  }
  
  public Set<Map.Entry<K, V>> entrySet() {
    Set<Map.Entry<K, V>> hashSet = new LinkedHashSet<>();
    for (int i = 0; i < size(); i++)
      hashSet.add(MapUtil.entry(this.keys.get(i), this.values.get(i))); 
    return hashSet;
  }
  
  public Iterator<Map.Entry<K, V>> iterator() {
    return new Iterator<Map.Entry<K, V>>() {
        private final Iterator<K> keysIter = TableMap.this.keys.iterator();
        
        private final Iterator<V> valuesIter = TableMap.this.values.iterator();
        
        public boolean hasNext() {
          return (this.keysIter.hasNext() && this.valuesIter.hasNext());
        }
        
        public Map.Entry<K, V> next() {
          return MapUtil.entry(this.keysIter.next(), this.valuesIter.next());
        }
        
        public void remove() {
          this.keysIter.remove();
          this.valuesIter.remove();
        }
      };
  }
  
  public String toString() {
    return "TableMap{keys=" + this.keys + ", values=" + this.values + '}';
  }
  
  public void forEach(BiConsumer<? super K, ? super V> action) {
    for (int i = 0; i < size(); i++)
      action.accept(this.keys.get(i), this.values.get(i)); 
  }
  
  public boolean remove(Object key, Object value) {
    boolean removed = false;
    for (int i = 0; i < size(); i++) {
      if (ObjUtil.equals(key, this.keys.get(i)) && ObjUtil.equals(value, this.values.get(i))) {
        removeByIndex(i);
        removed = true;
        i--;
      } 
    } 
    return removed;
  }
  
  public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
    for (int i = 0; i < size(); i++) {
      V newValue = function.apply(this.keys.get(i), this.values.get(i));
      this.values.set(i, newValue);
    } 
  }
  
  public boolean replace(K key, V oldValue, V newValue) {
    for (int i = 0; i < size(); i++) {
      if (ObjUtil.equals(key, this.keys.get(i)) && ObjUtil.equals(oldValue, this.values.get(i))) {
        this.values.set(i, newValue);
        return true;
      } 
    } 
    return false;
  }
  
  public V replace(K key, V value) {
    V lastValue = null;
    for (int i = 0; i < size(); i++) {
      if (ObjUtil.equals(key, this.keys.get(i)))
        lastValue = this.values.set(i, value); 
    } 
    return lastValue;
  }
  
  public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
    if (null == remappingFunction)
      return null; 
    V lastValue = null;
    for (int i = 0; i < size(); i++) {
      if (ObjUtil.equals(key, this.keys.get(i))) {
        V newValue = remappingFunction.apply(key, this.values.get(i));
        if (null != newValue) {
          lastValue = this.values.set(i, newValue);
        } else {
          removeByIndex(i);
          i--;
        } 
      } 
    } 
    return lastValue;
  }
}
