package org.openjdk.nashorn.internal.runtime.arrays;

import org.openjdk.nashorn.api.scripting.JSObject;
import org.openjdk.nashorn.internal.runtime.JSType;

final class ReverseJSObjectIterator extends JSObjectIterator {
  ReverseJSObjectIterator(JSObject obj, boolean includeUndefined) {
    super(obj, includeUndefined);
    this.index = JSType.toUint32(obj.hasMember("length") ? obj.getMember("length") : Integer.valueOf(0)) - 1L;
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
