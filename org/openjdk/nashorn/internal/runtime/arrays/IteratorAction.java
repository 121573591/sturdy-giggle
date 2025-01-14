package org.openjdk.nashorn.internal.runtime.arrays;

import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.linker.Bootstrap;

public abstract class IteratorAction<T> {
  protected final Object self;
  
  protected Object thisArg;
  
  protected final Object callbackfn;
  
  protected T result;
  
  protected long index;
  
  private final ArrayLikeIterator<Object> iter;
  
  public IteratorAction(Object self, Object callbackfn, Object thisArg, T initialResult) {
    this(self, callbackfn, thisArg, initialResult, ArrayLikeIterator.arrayLikeIterator(self));
  }
  
  public IteratorAction(Object self, Object callbackfn, Object thisArg, T initialResult, ArrayLikeIterator<Object> iter) {
    this.self = self;
    this.callbackfn = callbackfn;
    this.result = initialResult;
    this.iter = iter;
    this.thisArg = thisArg;
  }
  
  protected void applyLoopBegin(ArrayLikeIterator<Object> iterator) {}
  
  public final T apply() {
    boolean strict = Bootstrap.isStrictCallable(this.callbackfn);
    this.thisArg = (this.thisArg == ScriptRuntime.UNDEFINED && !strict) ? Context.getGlobal() : this.thisArg;
    applyLoopBegin(this.iter);
    boolean reverse = this.iter.isReverse();
    while (this.iter.hasNext()) {
      Object val = this.iter.next();
      this.index = this.iter.nextIndex() + (reverse ? 1L : -1L);
      try {
        if (!forEach(val, this.index))
          return this.result; 
      } catch (RuntimeException|Error e) {
        throw e;
      } catch (Throwable t) {
        throw new RuntimeException(t);
      } 
    } 
    return this.result;
  }
  
  protected abstract boolean forEach(Object paramObject, double paramDouble) throws Throwable;
}
