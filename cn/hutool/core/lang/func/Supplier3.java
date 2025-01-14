package cn.hutool.core.lang.func;

import java.util.function.Supplier;

@FunctionalInterface
public interface Supplier3<T, P1, P2, P3> {
  T get(P1 paramP1, P2 paramP2, P3 paramP3);
  
  default Supplier<T> toSupplier(P1 p1, P2 p2, P3 p3) {
    return () -> get((P1)p1, (P2)p2, (P3)p3);
  }
}
