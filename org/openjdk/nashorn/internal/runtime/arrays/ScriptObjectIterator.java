package org.openjdk.nashorn.internal.runtime.arrays;

import java.util.NoSuchElementException;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ScriptObject;

class ScriptObjectIterator extends ArrayLikeIterator<Object> {
  protected final ScriptObject obj;
  
  private final long length;
  
  ScriptObjectIterator(ScriptObject obj, boolean includeUndefined) {
    super(includeUndefined);
    this.obj = obj;
    this.length = JSType.toUint32(obj.getLength());
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
      !this.obj.has(this.index) && !this.includeUndefined)
      bumpIndex(); 
    return indexInArray();
  }
  
  public Object next() {
    if (indexInArray())
      return this.obj.get(bumpIndex()); 
    throw new NoSuchElementException();
  }
}
