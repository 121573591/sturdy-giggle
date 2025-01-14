package cn.hutool.core.lang.hash;

@FunctionalInterface
public interface Hash64<T> extends Hash<T> {
  long hash64(T paramT);
  
  default Number hash(T t) {
    return Long.valueOf(hash64(t));
  }
}
