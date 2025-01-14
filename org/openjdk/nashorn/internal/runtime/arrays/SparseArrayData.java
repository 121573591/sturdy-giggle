package org.openjdk.nashorn.internal.runtime.arrays;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

class SparseArrayData extends ArrayData {
  static final int MAX_DENSE_LENGTH = 131072;
  
  private ArrayData underlying;
  
  private final long maxDenseLength;
  
  private TreeMap<Long, Object> sparseMap;
  
  SparseArrayData(ArrayData underlying, long length) {
    this(underlying, length, new TreeMap<>());
  }
  
  private SparseArrayData(ArrayData underlying, long length, TreeMap<Long, Object> sparseMap) {
    super(length);
    assert underlying.length() <= length;
    this.underlying = underlying;
    this.maxDenseLength = underlying.length();
    this.sparseMap = sparseMap;
  }
  
  public ArrayData copy() {
    return new SparseArrayData(this.underlying.copy(), length(), new TreeMap<>(this.sparseMap));
  }
  
  public Object[] asObjectArray() {
    int len = (int)Math.min(length(), 2147483647L);
    int underlyingLength = (int)Math.min(len, this.underlying.length());
    Object[] objArray = new Object[len];
    for (int i = 0; i < underlyingLength; i++)
      objArray[i] = this.underlying.getObject(i); 
    Arrays.fill(objArray, underlyingLength, len, ScriptRuntime.UNDEFINED);
    for (Map.Entry<Long, Object> entry : this.sparseMap.entrySet()) {
      long key = ((Long)entry.getKey()).longValue();
      if (key < 2147483647L)
        objArray[(int)key] = entry.getValue(); 
    } 
    return objArray;
  }
  
  public ArrayData shiftLeft(int by) {
    this.underlying = this.underlying.shiftLeft(by);
    TreeMap<Long, Object> newSparseMap = new TreeMap<>();
    for (Map.Entry<Long, Object> entry : this.sparseMap.entrySet()) {
      long newIndex = ((Long)entry.getKey()).longValue() - by;
      if (newIndex >= 0L) {
        if (newIndex < this.maxDenseLength) {
          long oldLength = this.underlying.length();
          this
            
            .underlying = this.underlying.ensure(newIndex).set((int)newIndex, entry.getValue(), false).safeDelete(oldLength, newIndex - 1L, false);
          continue;
        } 
        newSparseMap.put(Long.valueOf(newIndex), entry.getValue());
      } 
    } 
    this.sparseMap = newSparseMap;
    setLength(Math.max(length() - by, 0L));
    return this.sparseMap.isEmpty() ? this.underlying : this;
  }
  
  public ArrayData shiftRight(int by) {
    TreeMap<Long, Object> newSparseMap = new TreeMap<>();
    long len = this.underlying.length();
    if (len + by > this.maxDenseLength) {
      long tempLength = Math.max(0L, this.maxDenseLength - by);
      long i;
      for (i = tempLength; i < len; i++) {
        if (this.underlying.has((int)i))
          newSparseMap.put(Long.valueOf(i + by), this.underlying.getObject((int)i)); 
      } 
      this.underlying = this.underlying.shrink((int)tempLength);
      this.underlying.setLength(tempLength);
    } 
    this.underlying = this.underlying.shiftRight(by);
    for (Map.Entry<Long, Object> entry : this.sparseMap.entrySet()) {
      long newIndex = ((Long)entry.getKey()).longValue() + by;
      newSparseMap.put(Long.valueOf(newIndex), entry.getValue());
    } 
    this.sparseMap = newSparseMap;
    setLength(length() + by);
    return this;
  }
  
  public ArrayData ensure(long safeIndex) {
    if (safeIndex >= length())
      setLength(safeIndex + 1L); 
    return this;
  }
  
  public ArrayData shrink(long newLength) {
    if (newLength < this.underlying.length()) {
      this.underlying = this.underlying.shrink(newLength);
      this.underlying.setLength(newLength);
      this.sparseMap.clear();
      setLength(newLength);
    } 
    this.sparseMap.subMap(Long.valueOf(newLength), Long.valueOf(Long.MAX_VALUE)).clear();
    setLength(newLength);
    return this;
  }
  
  public ArrayData set(int index, Object value, boolean strict) {
    if (index >= 0 && index < this.maxDenseLength) {
      long oldLength = this.underlying.length();
      this.underlying = this.underlying.ensure(index).set(index, value, strict).safeDelete(oldLength, (index - 1), strict);
      setLength(Math.max(this.underlying.length(), length()));
    } else {
      Long longIndex = indexToKey(index);
      this.sparseMap.put(longIndex, value);
      setLength(Math.max(longIndex.longValue() + 1L, length()));
    } 
    return this;
  }
  
  public ArrayData set(int index, int value, boolean strict) {
    if (index >= 0 && index < this.maxDenseLength) {
      long oldLength = this.underlying.length();
      this.underlying = this.underlying.ensure(index).set(index, value, strict).safeDelete(oldLength, (index - 1), strict);
      setLength(Math.max(this.underlying.length(), length()));
    } else {
      Long longIndex = indexToKey(index);
      this.sparseMap.put(longIndex, Integer.valueOf(value));
      setLength(Math.max(longIndex.longValue() + 1L, length()));
    } 
    return this;
  }
  
  public ArrayData set(int index, double value, boolean strict) {
    if (index >= 0 && index < this.maxDenseLength) {
      long oldLength = this.underlying.length();
      this.underlying = this.underlying.ensure(index).set(index, value, strict).safeDelete(oldLength, (index - 1), strict);
      setLength(Math.max(this.underlying.length(), length()));
    } else {
      Long longIndex = indexToKey(index);
      this.sparseMap.put(longIndex, Double.valueOf(value));
      setLength(Math.max(longIndex.longValue() + 1L, length()));
    } 
    return this;
  }
  
  public ArrayData setEmpty(int index) {
    this.underlying.setEmpty(index);
    return this;
  }
  
  public ArrayData setEmpty(long lo, long hi) {
    this.underlying.setEmpty(lo, hi);
    return this;
  }
  
  public Type getOptimisticType() {
    return this.underlying.getOptimisticType();
  }
  
  public int getInt(int index) {
    if (index >= 0 && index < this.maxDenseLength)
      return this.underlying.getInt(index); 
    return JSType.toInt32(this.sparseMap.get(indexToKey(index)));
  }
  
  public int getIntOptimistic(int index, int programPoint) {
    if (index >= 0 && index < this.maxDenseLength)
      return this.underlying.getIntOptimistic(index, programPoint); 
    return JSType.toInt32Optimistic(this.sparseMap.get(indexToKey(index)), programPoint);
  }
  
  public double getDouble(int index) {
    if (index >= 0 && index < this.maxDenseLength)
      return this.underlying.getDouble(index); 
    return JSType.toNumber(this.sparseMap.get(indexToKey(index)));
  }
  
  public double getDoubleOptimistic(int index, int programPoint) {
    if (index >= 0 && index < this.maxDenseLength)
      return this.underlying.getDouble(index); 
    return JSType.toNumberOptimistic(this.sparseMap.get(indexToKey(index)), programPoint);
  }
  
  public Object getObject(int index) {
    if (index >= 0 && index < this.maxDenseLength)
      return this.underlying.getObject(index); 
    Long key = indexToKey(index);
    if (this.sparseMap.containsKey(key))
      return this.sparseMap.get(key); 
    return ScriptRuntime.UNDEFINED;
  }
  
  public boolean has(int index) {
    if (index >= 0 && index < this.maxDenseLength)
      return (index < this.underlying.length() && this.underlying.has(index)); 
    return this.sparseMap.containsKey(indexToKey(index));
  }
  
  public ArrayData delete(int index) {
    if (index >= 0 && index < this.maxDenseLength) {
      if (index < this.underlying.length())
        this.underlying = this.underlying.delete(index); 
    } else {
      this.sparseMap.remove(indexToKey(index));
    } 
    return this;
  }
  
  public ArrayData delete(long fromIndex, long toIndex) {
    if (fromIndex < this.maxDenseLength && fromIndex < this.underlying.length())
      this.underlying = this.underlying.delete(fromIndex, Math.min(toIndex, this.underlying.length() - 1L)); 
    if (toIndex >= this.maxDenseLength)
      this.sparseMap.subMap(Long.valueOf(fromIndex), true, Long.valueOf(toIndex), true).clear(); 
    return this;
  }
  
  private static Long indexToKey(int index) {
    return Long.valueOf(ArrayIndex.toLongIndex(index));
  }
  
  public ArrayData convert(Class<?> type) {
    this.underlying = this.underlying.convert(type);
    return this;
  }
  
  public Object pop() {
    long len = length();
    long underlyingLen = this.underlying.length();
    if (len == 0L)
      return ScriptRuntime.UNDEFINED; 
    if (len == underlyingLen) {
      Object result = this.underlying.pop();
      setLength(this.underlying.length());
      return result;
    } 
    setLength(len - 1L);
    Long key = Long.valueOf(len - 1L);
    return this.sparseMap.containsKey(key) ? this.sparseMap.remove(key) : ScriptRuntime.UNDEFINED;
  }
  
  public ArrayData slice(long from, long to) {
    assert to <= length();
    long start = (from < 0L) ? (from + length()) : from;
    long newLength = to - start;
    long underlyingLength = this.underlying.length();
    if (start >= 0L && to <= this.maxDenseLength) {
      if (newLength <= underlyingLength)
        return this.underlying.slice(from, to); 
      return this.underlying.slice(from, to).ensure(newLength - 1L).delete(underlyingLength, newLength);
    } 
    ArrayData sliced = EMPTY_ARRAY;
    sliced = sliced.ensure(newLength - 1L);
    long i;
    for (i = start; i < to; i = nextIndex(i)) {
      if (has((int)i))
        sliced = sliced.set((int)(i - start), getObject((int)i), false); 
    } 
    assert sliced.length() == newLength;
    return sliced;
  }
  
  public long nextIndex(long index) {
    if (index < this.underlying.length() - 1L)
      return this.underlying.nextIndex(index); 
    Long nextKey = this.sparseMap.higherKey(Long.valueOf(index));
    if (nextKey != null)
      return nextKey.longValue(); 
    return length();
  }
}
