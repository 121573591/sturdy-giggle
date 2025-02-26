package org.openjdk.nashorn.internal.runtime.arrays;

import java.lang.reflect.Array;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

final class DeletedRangeArrayFilter extends ArrayFilter {
  private long lo;
  
  private long hi;
  
  DeletedRangeArrayFilter(ArrayData underlying, long lo, long hi) {
    super(maybeSparse(underlying, hi));
    this.lo = lo;
    this.hi = hi;
  }
  
  private static ArrayData maybeSparse(ArrayData underlying, long hi) {
    if (hi < 131072L || underlying instanceof SparseArrayData)
      return underlying; 
    return new SparseArrayData(underlying, underlying.length());
  }
  
  private boolean isEmpty() {
    return (this.lo > this.hi);
  }
  
  private boolean isDeleted(int index) {
    long longIndex = ArrayIndex.toLongIndex(index);
    return (this.lo <= longIndex && longIndex <= this.hi);
  }
  
  public ArrayData copy() {
    return new DeletedRangeArrayFilter(this.underlying.copy(), this.lo, this.hi);
  }
  
  public Object[] asObjectArray() {
    Object[] value = super.asObjectArray();
    if (this.lo < 2147483647L) {
      int end = (int)Math.min(this.hi + 1L, 2147483647L);
      for (int i = (int)this.lo; i < end; i++)
        value[i] = ScriptRuntime.UNDEFINED; 
    } 
    return value;
  }
  
  public Object asArrayOfType(Class<?> componentType) {
    Object value = super.asArrayOfType(componentType);
    Object undefValue = convertUndefinedValue(componentType);
    if (this.lo < 2147483647L) {
      int end = (int)Math.min(this.hi + 1L, 2147483647L);
      for (int i = (int)this.lo; i < end; i++)
        Array.set(value, i, undefValue); 
    } 
    return value;
  }
  
  public ArrayData ensure(long safeIndex) {
    if (safeIndex >= 131072L && safeIndex >= length())
      return new SparseArrayData(this, safeIndex + 1L); 
    return super.ensure(safeIndex);
  }
  
  public ArrayData shiftLeft(int by) {
    super.shiftLeft(by);
    this.lo = Math.max(0L, this.lo - by);
    this.hi = Math.max(-1L, this.hi - by);
    return isEmpty() ? getUnderlying() : this;
  }
  
  public ArrayData shiftRight(int by) {
    super.shiftRight(by);
    long len = length();
    this.lo = Math.min(len, this.lo + by);
    this.hi = Math.min(len - 1L, this.hi + by);
    return isEmpty() ? getUnderlying() : this;
  }
  
  public ArrayData shrink(long newLength) {
    super.shrink(newLength);
    this.lo = Math.min(newLength, this.lo);
    this.hi = Math.min(newLength - 1L, this.hi);
    return isEmpty() ? getUnderlying() : this;
  }
  
  public ArrayData set(int index, Object value, boolean strict) {
    long longIndex = ArrayIndex.toLongIndex(index);
    if (longIndex < this.lo || longIndex > this.hi)
      return super.set(index, value, strict); 
    if (longIndex > this.lo && longIndex < this.hi)
      return getDeletedArrayFilter().set(index, value, strict); 
    if (longIndex == this.lo) {
      this.lo++;
    } else {
      assert longIndex == this.hi;
      this.hi--;
    } 
    return isEmpty() ? getUnderlying().set(index, value, strict) : super.set(index, value, strict);
  }
  
  public ArrayData set(int index, int value, boolean strict) {
    long longIndex = ArrayIndex.toLongIndex(index);
    if (longIndex < this.lo || longIndex > this.hi)
      return super.set(index, value, strict); 
    if (longIndex > this.lo && longIndex < this.hi)
      return getDeletedArrayFilter().set(index, value, strict); 
    if (longIndex == this.lo) {
      this.lo++;
    } else {
      assert longIndex == this.hi;
      this.hi--;
    } 
    return isEmpty() ? getUnderlying().set(index, value, strict) : super.set(index, value, strict);
  }
  
  public ArrayData set(int index, double value, boolean strict) {
    long longIndex = ArrayIndex.toLongIndex(index);
    if (longIndex < this.lo || longIndex > this.hi)
      return super.set(index, value, strict); 
    if (longIndex > this.lo && longIndex < this.hi)
      return getDeletedArrayFilter().set(index, value, strict); 
    if (longIndex == this.lo) {
      this.lo++;
    } else {
      assert longIndex == this.hi;
      this.hi--;
    } 
    return isEmpty() ? getUnderlying().set(index, value, strict) : super.set(index, value, strict);
  }
  
  public boolean has(int index) {
    return (super.has(index) && !isDeleted(index));
  }
  
  private ArrayData getDeletedArrayFilter() {
    ArrayData deleteFilter = new DeletedArrayFilter(getUnderlying());
    deleteFilter.delete(this.lo, this.hi);
    return deleteFilter;
  }
  
  public ArrayData delete(int index) {
    long longIndex = ArrayIndex.toLongIndex(index);
    this.underlying.setEmpty(index);
    if (longIndex + 1L == this.lo) {
      this.lo = longIndex;
    } else if (longIndex - 1L == this.hi) {
      this.hi = longIndex;
    } else if (longIndex < this.lo || this.hi < longIndex) {
      return getDeletedArrayFilter().delete(index);
    } 
    return this;
  }
  
  public ArrayData delete(long fromIndex, long toIndex) {
    if (fromIndex > this.hi + 1L || toIndex < this.lo - 1L)
      return getDeletedArrayFilter().delete(fromIndex, toIndex); 
    this.lo = Math.min(fromIndex, this.lo);
    this.hi = Math.max(toIndex, this.hi);
    this.underlying.setEmpty(this.lo, this.hi);
    return this;
  }
  
  public Object pop() {
    int index = (int)length() - 1;
    if (super.has(index)) {
      boolean isDeleted = isDeleted(index);
      Object value = super.pop();
      this.lo = Math.min((index + 1), this.lo);
      this.hi = Math.min(index, this.hi);
      return isDeleted ? ScriptRuntime.UNDEFINED : value;
    } 
    return super.pop();
  }
  
  public ArrayData slice(long from, long to) {
    return new DeletedRangeArrayFilter(this.underlying.slice(from, to), Math.max(0L, this.lo - from), Math.max(0L, this.hi - from));
  }
}
