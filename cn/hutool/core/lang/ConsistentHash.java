package cn.hutool.core.lang;

import cn.hutool.core.lang.hash.Hash32;
import cn.hutool.core.util.HashUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHash<T> implements Serializable {
  private static final long serialVersionUID = 1L;
  
  Hash32<Object> hashFunc;
  
  private final int numberOfReplicas;
  
  private final SortedMap<Integer, T> circle = new TreeMap<>();
  
  public ConsistentHash(int numberOfReplicas, Collection<T> nodes) {
    this.numberOfReplicas = numberOfReplicas;
    this.hashFunc = (key -> HashUtil.fnvHash(key.toString()));
    for (T node : nodes)
      add(node); 
  }
  
  public ConsistentHash(Hash32<Object> hashFunc, int numberOfReplicas, Collection<T> nodes) {
    this.numberOfReplicas = numberOfReplicas;
    this.hashFunc = hashFunc;
    for (T node : nodes)
      add(node); 
  }
  
  public void add(T node) {
    for (int i = 0; i < this.numberOfReplicas; i++)
      this.circle.put(Integer.valueOf(this.hashFunc.hash32(node.toString() + i)), node); 
  }
  
  public void remove(T node) {
    for (int i = 0; i < this.numberOfReplicas; i++)
      this.circle.remove(Integer.valueOf(this.hashFunc.hash32(node.toString() + i))); 
  }
  
  public T get(Object key) {
    if (this.circle.isEmpty())
      return null; 
    int hash = this.hashFunc.hash32(key);
    if (false == this.circle.containsKey(Integer.valueOf(hash))) {
      SortedMap<Integer, T> tailMap = this.circle.tailMap(Integer.valueOf(hash));
      hash = tailMap.isEmpty() ? ((Integer)this.circle.firstKey()).intValue() : ((Integer)tailMap.firstKey()).intValue();
    } 
    return this.circle.get(Integer.valueOf(hash));
  }
}
