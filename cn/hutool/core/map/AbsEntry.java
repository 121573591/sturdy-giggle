package cn.hutool.core.map;

import cn.hutool.core.util.ObjectUtil;
import java.util.Map;

public abstract class AbsEntry<K, V> implements Map.Entry<K, V> {
  public V setValue(V value) {
    throw new UnsupportedOperationException("Entry is read only.");
  }
  
  public boolean equals(Object object) {
    if (object instanceof Map.Entry) {
      Map.Entry<?, ?> that = (Map.Entry<?, ?>)object;
      return (ObjectUtil.equals(getKey(), that.getKey()) && 
        ObjectUtil.equals(getValue(), that.getValue()));
    } 
    return false;
  }
  
  public int hashCode() {
    K k = getKey();
    V v = getValue();
    return ((k == null) ? 0 : k.hashCode()) ^ ((v == null) ? 0 : v.hashCode());
  }
  
  public String toString() {
    return (new StringBuilder()).append(getKey()).append("=").append(getValue()).toString();
  }
}
