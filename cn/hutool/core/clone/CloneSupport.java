package cn.hutool.core.clone;

public class CloneSupport<T> implements Cloneable<T> {
  public T clone() {
    try {
      return (T)super.clone();
    } catch (CloneNotSupportedException e) {
      throw new CloneRuntimeException(e);
    } 
  }
}
