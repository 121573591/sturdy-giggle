package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;

@FunctionalInterface
public interface IOConsumer<T> {
  public static final IOConsumer<?> NOOP_IO_CONSUMER = t -> {
    
    };
  
  static <T> IOConsumer<T> noop() {
    return (IOConsumer)NOOP_IO_CONSUMER;
  }
  
  default IOConsumer<T> andThen(IOConsumer<? super T> after) {
    Objects.requireNonNull(after, "after");
    return t -> {
        accept((T)t);
        after.accept(t);
      };
  }
  
  void accept(T paramT) throws IOException;
}
