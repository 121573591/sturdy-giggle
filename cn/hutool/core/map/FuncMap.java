package cn.hutool.core.map;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class FuncMap<K, V> extends TransMap<K, V> {
  private static final long serialVersionUID = 1L;
  
  private final Function<Object, K> keyFunc;
  
  private final Function<Object, V> valueFunc;
  
  public FuncMap(Supplier<Map<K, V>> mapFactory, Function<Object, K> keyFunc, Function<Object, V> valueFunc) {
    this(mapFactory.get(), keyFunc, valueFunc);
  }
  
  public FuncMap(Map<K, V> emptyMap, Function<Object, K> keyFunc, Function<Object, V> valueFunc) {
    super(emptyMap);
    this.keyFunc = keyFunc;
    this.valueFunc = valueFunc;
  }
  
  protected K customKey(Object key) {
    if (null != this.keyFunc)
      return this.keyFunc.apply(key); 
    return (K)key;
  }
  
  protected V customValue(Object value) {
    if (null != this.valueFunc)
      return this.valueFunc.apply(value); 
    return (V)value;
  }
}
