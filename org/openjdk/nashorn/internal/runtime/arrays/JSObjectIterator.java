package org.openjdk.nashorn.internal.runtime.arrays;

import java.util.NoSuchElementException;
import org.openjdk.nashorn.api.scripting.JSObject;
import org.openjdk.nashorn.internal.runtime.JSType;

class JSObjectIterator extends ArrayLikeIterator<Object> {
  protected final JSObject obj;
  
  private final long length;
  
  JSObjectIterator(JSObject obj, boolean includeUndefined) {
    super(includeUndefined);
    this.obj = obj;
    this.length = JSType.toUint32(obj.hasMember("length") ? obj.getMember("length") : Integer.valueOf(0));
    this.index = 0L;
  }
  
  protected boolean indexInArray() {
    return (this.index < this.length);
  }
  
  public long getLength() {
    return this.length;
  }
  
  public boolean hasNext() {
    if (this.length == 0L)
      return false; 
    while (indexInArray() && 
      !this.obj.hasSlot((int)this.index) && !this.includeUndefined)
      bumpIndex(); 
    return indexInArray();
  }
  
  public Object next() {
    if (indexInArray())
      return this.obj.getSlot((int)bumpIndex()); 
    throw new NoSuchElementException();
  }
}
