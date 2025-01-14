package cn.hutool.core.lang.func;

import java.util.function.Supplier;

@FunctionalInterface
public interface Supplier2<T, P1, P2> {
  T get(P1 paramP1, P2 paramP2);
  
  default Supplier<T> toSupplier(P1 p1, P2 p2) {
    return () -> get((P1)p1, (P2)p2);
  }
}
