package cn.hutool.core.map;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class FixedLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
  private static final long serialVersionUID = -629171177321416095L;
  
  private int capacity;
  
  private Consumer<Map.Entry<K, V>> removeListener;
  
  public FixedLinkedHashMap(int capacity) {
    super(capacity + 1, 1.0F, true);
    this.capacity = capacity;
  }
  
  public int getCapacity() {
    return this.capacity;
  }
  
  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }
  
  public void setRemoveListener(Consumer<Map.Entry<K, V>> removeListener) {
    this.removeListener = removeListener;
  }
  
  protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
    if (size() > this.capacity) {
      if (null != this.removeListener)
        this.removeListener.accept(eldest); 
      return true;
    } 
    return false;
  }
}
