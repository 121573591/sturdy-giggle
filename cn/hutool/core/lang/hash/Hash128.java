package cn.hutool.core.lang.hash;

@FunctionalInterface
public interface Hash128<T> extends Hash<T> {
  Number128 hash128(T paramT);
  
  default Number hash(T t) {
    return hash128(t);
  }
}
