package cn.hutool.core.lang.loader;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AtomicLoader<T> implements Loader<T>, Serializable {
  private static final long serialVersionUID = 1L;
  
  private final AtomicReference<T> reference = new AtomicReference<>();
  
  public T get() {
    T result = this.reference.get();
    if (result == null) {
      result = init();
      if (false == this.reference.compareAndSet(null, result))
        result = this.reference.get(); 
    } 
    return result;
  }
  
  protected abstract T init();
}
