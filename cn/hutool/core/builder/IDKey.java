package cn.hutool.core.builder;

import java.io.Serializable;

final class IDKey implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private final Object value;
  
  private final int id;
  
  public IDKey(Object obj) {
    this.id = System.identityHashCode(obj);
    this.value = obj;
  }
  
  public int hashCode() {
    return this.id;
  }
  
  public boolean equals(Object other) {
    if (!(other instanceof IDKey))
      return false; 
    IDKey idKey = (IDKey)other;
    if (this.id != idKey.id)
      return false; 
    return (this.value == idKey.value);
  }
}
