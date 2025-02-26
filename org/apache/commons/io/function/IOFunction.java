package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@FunctionalInterface
public interface IOFunction<T, R> {
  default <V> IOFunction<V, R> compose(IOFunction<? super V, ? extends T> before) {
    Objects.requireNonNull(before, "before");
    return v -> apply(before.apply(v));
  }
  
  default <V> IOFunction<V, R> compose(Function<? super V, ? extends T> before) {
    Objects.requireNonNull(before, "before");
    return v -> apply(before.apply(v));
  }
  
  default IOSupplier<R> compose(IOSupplier<? extends T> before) {
    Objects.requireNonNull(before, "before");
    return () -> apply(before.get());
  }
  
  default IOSupplier<R> compose(Supplier<? extends T> before) {
    Objects.requireNonNull(before, "before");
    return () -> apply(before.get());
  }
  
  default <V> IOFunction<T, V> andThen(IOFunction<? super R, ? extends V> after) {
    Objects.requireNonNull(after, "after");
    return t -> after.apply(apply((T)t));
  }
  
  default <V> IOFunction<T, V> andThen(Function<? super R, ? extends V> after) {
    Objects.requireNonNull(after, "after");
    return t -> after.apply(apply((T)t));
  }
  
  default IOConsumer<T> andThen(IOConsumer<? super R> after) {
    Objects.requireNonNull(after, "after");
    return t -> after.accept(apply((T)t));
  }
  
  default IOConsumer<T> andThen(Consumer<? super R> after) {
    Objects.requireNonNull(after, "after");
    return t -> after.accept(apply((T)t));
  }
  
  static <T> IOFunction<T, T> identity() {
    return t -> t;
  }
  
  R apply(T paramT) throws IOException;
}
