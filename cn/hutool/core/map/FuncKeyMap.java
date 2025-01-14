package cn.hutool.core.map;

import java.util.Map;
import java.util.function.Function;

public class FuncKeyMap<K, V> extends CustomKeyMap<K, V> {
  private static final long serialVersionUID = 1L;
  
  private final Function<Object, K> keyFunc;
  
  public FuncKeyMap(Map<K, V> emptyMap, Function<Object, K> keyFunc) {
    super(emptyMap);
    this.keyFunc = keyFunc;
  }
  
  protected K customKey(Object key) {
    if (null != this.keyFunc)
      return this.keyFunc.apply(key); 
    return (K)key;
  }
}
