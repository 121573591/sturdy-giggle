package org.openjdk.nashorn.internal.runtime.arrays;

import org.openjdk.nashorn.internal.runtime.ScriptObject;

class ScriptArrayIterator extends ArrayLikeIterator<Object> {
  protected final ScriptObject array;
  
  protected final long length;
  
  protected ScriptArrayIterator(ScriptObject array, boolean includeUndefined) {
    super(includeUndefined);
    this.array = array;
    this.length = array.getArray().length();
  }
  
  protected boolean indexInArray() {
    return (this.index < this.length);
  }
  
  public Object next() {
    return this.array.get(bumpIndex());
  }
  
  public long getLength() {
    return this.length;
  }
  
  public boolean hasNext() {
    if (!this.includeUndefined)
      while (indexInArray() && 
        !this.array.has(this.index))
        bumpIndex();  
    return indexInArray();
  }
  
  public void remove() {
    this.array.delete(this.index, false);
  }
}
