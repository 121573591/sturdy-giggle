package cn.hutool.core.lang.func;

import java.util.function.Supplier;

@FunctionalInterface
public interface Supplier1<T, P1> {
  T get(P1 paramP1);
  
  default Supplier<T> toSupplier(P1 p1) {
    return () -> get((P1)p1);
  }
}
