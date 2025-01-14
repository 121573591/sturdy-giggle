package cn.hutool.core.lang.mutable;

import cn.hutool.core.util.ObjUtil;
import java.io.Serializable;

public class MutableObj<T> implements Mutable<T>, Serializable {
  private static final long serialVersionUID = 1L;
  
  private T value;
  
  public static <T> MutableObj<T> of(T value) {
    return new MutableObj<>(value);
  }
  
  public MutableObj() {}
  
  public MutableObj(T value) {
    this.value = value;
  }
  
  public T get() {
    return this.value;
  }
  
  public void set(T value) {
    this.value = value;
  }
  
  public boolean equals(Object obj) {
    if (obj == null)
      return false; 
    if (this == obj)
      return true; 
    if (getClass() == obj.getClass()) {
      MutableObj<?> that = (MutableObj)obj;
      return ObjUtil.equals(this.value, that.value);
    } 
    return false;
  }
  
  public int hashCode() {
    return (this.value == null) ? 0 : this.value.hashCode();
  }
  
  public String toString() {
    return (this.value == null) ? "null" : this.value.toString();
  }
}
