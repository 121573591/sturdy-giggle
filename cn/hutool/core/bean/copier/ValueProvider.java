package cn.hutool.core.bean.copier;

import java.lang.reflect.Type;

public interface ValueProvider<T> {
  Object value(T paramT, Type paramType);
  
  boolean containsKey(T paramT);
}
