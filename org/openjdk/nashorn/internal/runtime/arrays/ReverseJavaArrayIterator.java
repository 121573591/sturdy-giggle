package org.openjdk.nashorn.internal.runtime.arrays;

import java.lang.reflect.Array;

final class ReverseJavaArrayIterator extends JavaArrayIterator {
  public ReverseJavaArrayIterator(Object array, boolean includeUndefined) {
    super(array, includeUndefined);
    this.index = (Array.getLength(array) - 1);
  }
  
  public boolean isReverse() {
    return true;
  }
  
  protected boolean indexInArray() {
    return (this.index >= 0L);
  }
  
  protected long bumpIndex() {
    return this.index--;
  }
}
