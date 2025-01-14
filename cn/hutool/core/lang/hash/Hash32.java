package cn.hutool.core.lang.hash;

@FunctionalInterface
public interface Hash32<T> extends Hash<T> {
  int hash32(T paramT);
  
  default Number hash(T t) {
    return Integer.valueOf(hash32(t));
  }
}
