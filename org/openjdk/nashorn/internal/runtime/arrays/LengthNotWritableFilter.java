package org.openjdk.nashorn.internal.runtime.arrays;

import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

final class LengthNotWritableFilter extends ArrayFilter {
  private final SortedMap<Long, Object> extraElements;
  
  LengthNotWritableFilter(ArrayData underlying) {
    this(underlying, new TreeMap<>());
  }
  
  private LengthNotWritableFilter(ArrayData underlying, SortedMap<Long, Object> extraElements) {
    super(underlying);
    this.extraElements = extraElements;
  }
  
  public ArrayData copy() {
    return new LengthNotWritableFilter(this.underlying.copy(), new TreeMap<>(this.extraElements));
  }
  
  public boolean has(int index) {
    return (super.has(index) || this.extraElements.containsKey(Long.valueOf(index)));
  }
  
  public void setLength(long length) {}
  
  public ArrayData ensure(long index) {
    return this;
  }
  
  public ArrayData slice(long from, long to) {
    return new LengthNotWritableFilter(this.underlying.slice(from, to), this.extraElements.subMap(Long.valueOf(from), Long.valueOf(to)));
  }
  
  private boolean checkAdd(long index, Object value) {
    if (index >= length()) {
      this.extraElements.put(Long.valueOf(index), value);
      return true;
    } 
    return false;
  }
  
  private Object get(long index) {
    Object obj = this.extraElements.get(Long.valueOf(index));
    if (obj == null)
      return ScriptRuntime.UNDEFINED; 
    return obj;
  }
  
  public int getInt(int index) {
    if (index >= length())
      return JSType.toInt32(get(index)); 
    return this.underlying.getInt(index);
  }
  
  public int getIntOptimistic(int index, int programPoint) {
    if (index >= length())
      return JSType.toInt32Optimistic(get(index), programPoint); 
    return this.underlying.getIntOptimistic(index, programPoint);
  }
  
  public double getDouble(int index) {
    if (index >= length())
      return JSType.toNumber(get(index)); 
    return this.underlying.getDouble(index);
  }
  
  public double getDoubleOptimistic(int index, int programPoint) {
    if (index >= length())
      return JSType.toNumberOptimistic(get(index), programPoint); 
    return this.underlying.getDoubleOptimistic(index, programPoint);
  }
  
  public Object getObject(int index) {
    if (index >= length())
      return get(index); 
    return this.underlying.getObject(index);
  }
  
  public ArrayData set(int index, Object value, boolean strict) {
    if (checkAdd(index, value))
      return this; 
    this.underlying = this.underlying.set(index, value, strict);
    return this;
  }
  
  public ArrayData set(int index, int value, boolean strict) {
    if (checkAdd(index, Integer.valueOf(value)))
      return this; 
    this.underlying = this.underlying.set(index, value, strict);
    return this;
  }
  
  public ArrayData set(int index, double value, boolean strict) {
    if (checkAdd(index, Double.valueOf(value)))
      return this; 
    this.underlying = this.underlying.set(index, value, strict);
    return this;
  }
  
  public ArrayData delete(int index) {
    this.extraElements.remove(Long.valueOf(ArrayIndex.toLongIndex(index)));
    this.underlying = this.underlying.delete(index);
    return this;
  }
  
  public ArrayData delete(long fromIndex, long toIndex) {
    for (Iterator<Long> iter = this.extraElements.keySet().iterator(); iter.hasNext(); ) {
      long next = ((Long)iter.next()).longValue();
      if (next >= fromIndex && next <= toIndex)
        iter.remove(); 
      if (next > toIndex)
        break; 
    } 
    this.underlying = this.underlying.delete(fromIndex, toIndex);
    return this;
  }
  
  public Iterator<Long> indexIterator() {
    List<Long> keys = computeIteratorKeys();
    keys.addAll(this.extraElements.keySet());
    return keys.iterator();
  }
}
