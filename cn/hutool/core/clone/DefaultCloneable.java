package cn.hutool.core.clone;

import cn.hutool.core.util.ReflectUtil;

public interface DefaultCloneable<T> extends java.lang.Cloneable {
  default T clone0() {
    try {
      return (T)ReflectUtil.invoke(this, "clone", new Object[0]);
    } catch (Exception e) {
      throw new CloneRuntimeException(e);
    } 
  }
}
