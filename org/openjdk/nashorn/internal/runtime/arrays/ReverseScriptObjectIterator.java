package org.openjdk.nashorn.internal.runtime.arrays;

import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ScriptObject;

final class ReverseScriptObjectIterator extends ScriptObjectIterator {
  ReverseScriptObjectIterator(ScriptObject obj, boolean includeUndefined) {
    super(obj, includeUndefined);
    this.index = JSType.toUint32(obj.getLength()) - 1L;
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
