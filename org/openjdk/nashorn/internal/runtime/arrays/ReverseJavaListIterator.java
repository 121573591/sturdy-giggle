package org.openjdk.nashorn.internal.runtime.arrays;

import java.util.List;

final class ReverseJavaListIterator extends JavaListIterator {
  public ReverseJavaListIterator(List<?> list, boolean includeUndefined) {
    super(list, includeUndefined);
    this.index = (list.size() - 1);
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
