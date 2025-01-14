package org.openjdk.nashorn.internal.runtime.arrays;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

final class ObjectArrayData extends ContinuousArrayData implements AnyElements {
  private Object[] array;
  
  ObjectArrayData(Object[] array, int length) {
    super(length);
    assert array.length >= length;
    this.array = array;
  }
  
  public final Class<?> getElementType() {
    return Object.class;
  }
  
  public final Class<?> getBoxedElementType() {
    return getElementType();
  }
  
  public final int getElementWeight() {
    return 4;
  }
  
  public final ContinuousArrayData widest(ContinuousArrayData otherData) {
    return (otherData instanceof NumericElements) ? this : otherData;
  }
  
  public ObjectArrayData copy() {
    return new ObjectArrayData((Object[])this.array.clone(), (int)length());
  }
  
  public Object[] asObjectArray() {
    return (this.array.length == length()) ? (Object[])this.array.clone() : asObjectArrayCopy();
  }
  
  private Object[] asObjectArrayCopy() {
    long len = length();
    assert len <= 2147483647L;
    Object[] copy = new Object[(int)len];
    System.arraycopy(this.array, 0, copy, 0, (int)len);
    return copy;
  }
  
  public ObjectArrayData convert(Class<?> type) {
    return this;
  }
  
  public ArrayData shiftLeft(int by) {
    if (by >= length()) {
      shrink(0L);
    } else {
      System.arraycopy(this.array, by, this.array, 0, this.array.length - by);
    } 
    setLength(Math.max(0L, length() - by));
    return this;
  }
  
  public ArrayData shiftRight(int by) {
    ArrayData newData = ensure(by + length() - 1L);
    if (newData != this) {
      newData.shiftRight(by);
      return newData;
    } 
    System.arraycopy(this.array, 0, this.array, by, this.array.length - by);
    return this;
  }
  
  public ArrayData ensure(long safeIndex) {
    if (safeIndex >= 131072L)
      return new SparseArrayData(this, safeIndex + 1L); 
    int alen = this.array.length;
    if (safeIndex >= alen) {
      int newLength = ArrayData.nextSize((int)safeIndex);
      this.array = Arrays.copyOf(this.array, newLength);
    } 
    if (safeIndex >= length())
      setLength(safeIndex + 1L); 
    return this;
  }
  
  public ArrayData shrink(long newLength) {
    Arrays.fill(this.array, (int)newLength, this.array.length, ScriptRuntime.UNDEFINED);
    return this;
  }
  
  public ArrayData set(int index, Object value, boolean strict) {
    this.array[index] = value;
    setLength(Math.max((index + 1), length()));
    return this;
  }
  
  public ArrayData set(int index, int value, boolean strict) {
    this.array[index] = Integer.valueOf(value);
    setLength(Math.max((index + 1), length()));
    return this;
  }
  
  public ArrayData set(int index, double value, boolean strict) {
    this.array[index] = Double.valueOf(value);
    setLength(Math.max((index + 1), length()));
    return this;
  }
  
  public ArrayData setEmpty(int index) {
    this.array[index] = ScriptRuntime.EMPTY;
    return this;
  }
  
  public ArrayData setEmpty(long lo, long hi) {
    Arrays.fill(this.array, (int)Math.max(lo, 0L), (int)Math.min(hi + 1L, 2147483647L), ScriptRuntime.EMPTY);
    return this;
  }
  
  private static final MethodHandle HAS_GET_ELEM = CompilerConstants.specialCall(MethodHandles.lookup(), ObjectArrayData.class, "getElem", Object.class, new Class[] { int.class }).methodHandle();
  
  private static final MethodHandle SET_ELEM = CompilerConstants.specialCall(MethodHandles.lookup(), ObjectArrayData.class, "setElem", void.class, new Class[] { int.class, Object.class }).methodHandle();
  
  private Object getElem(int index) {
    if (has(index))
      return this.array[index]; 
    throw new ClassCastException();
  }
  
  private void setElem(int index, Object elem) {
    if (hasRoomFor(index)) {
      this.array[index] = elem;
      return;
    } 
    throw new ClassCastException();
  }
  
  public MethodHandle getElementGetter(Class<?> returnType, int programPoint) {
    if (returnType.isPrimitive())
      return null; 
    return getContinuousElementGetter(HAS_GET_ELEM, returnType, programPoint);
  }
  
  public MethodHandle getElementSetter(Class<?> elementType) {
    return getContinuousElementSetter(SET_ELEM, Object.class);
  }
  
  public int getInt(int index) {
    return JSType.toInt32(this.array[index]);
  }
  
  public double getDouble(int index) {
    return JSType.toNumber(this.array[index]);
  }
  
  public Object getObject(int index) {
    return this.array[index];
  }
  
  public boolean has(int index) {
    return (0 <= index && index < length());
  }
  
  public ArrayData delete(int index) {
    setEmpty(index);
    return new DeletedRangeArrayFilter(this, index, index);
  }
  
  public ArrayData delete(long fromIndex, long toIndex) {
    setEmpty(fromIndex, toIndex);
    return new DeletedRangeArrayFilter(this, fromIndex, toIndex);
  }
  
  public double fastPush(int arg) {
    return fastPush(Integer.valueOf(arg));
  }
  
  public double fastPush(long arg) {
    return fastPush(Long.valueOf(arg));
  }
  
  public double fastPush(double arg) {
    return fastPush(Double.valueOf(arg));
  }
  
  public double fastPush(Object arg) {
    int len = (int)length();
    if (len == this.array.length)
      this.array = Arrays.copyOf(this.array, nextSize(len)); 
    this.array[len] = arg;
    return increaseLength();
  }
  
  public Object fastPopObject() {
    if (length() == 0L)
      return ScriptRuntime.UNDEFINED; 
    int newLength = (int)decreaseLength();
    Object elem = this.array[newLength];
    this.array[newLength] = ScriptRuntime.EMPTY;
    return elem;
  }
  
  public Object pop() {
    if (length() == 0L)
      return ScriptRuntime.UNDEFINED; 
    int newLength = (int)length() - 1;
    Object elem = this.array[newLength];
    setEmpty(newLength);
    setLength(newLength);
    return elem;
  }
  
  public ArrayData slice(long from, long to) {
    long start = (from < 0L) ? (from + length()) : from;
    long newLength = to - start;
    return new ObjectArrayData(Arrays.copyOfRange(this.array, (int)from, (int)to), (int)newLength);
  }
  
  public ArrayData push(boolean strict, Object item) {
    long len = length();
    ArrayData newData = ensure(len);
    if (newData == this) {
      this.array[(int)len] = item;
      return this;
    } 
    return newData.set((int)len, item, strict);
  }
  
  public ArrayData fastSplice(int start, int removed, int added) throws UnsupportedOperationException {
    long oldLength = length();
    long newLength = oldLength - removed + added;
    if (newLength > 131072L && newLength > this.array.length)
      throw new UnsupportedOperationException(); 
    ArrayData returnValue = (removed == 0) ? EMPTY_ARRAY : new ObjectArrayData(Arrays.copyOfRange(this.array, start, start + removed), removed);
    if (newLength != oldLength) {
      Object[] newArray;
      if (newLength > this.array.length) {
        newArray = new Object[ArrayData.nextSize((int)newLength)];
        System.arraycopy(this.array, 0, newArray, 0, start);
      } else {
        newArray = this.array;
      } 
      System.arraycopy(this.array, start + removed, newArray, start + added, (int)(oldLength - start - removed));
      this.array = newArray;
      setLength(newLength);
    } 
    return returnValue;
  }
  
  public ContinuousArrayData fastConcat(ContinuousArrayData otherData) {
    int otherLength = (int)otherData.length();
    int thisLength = (int)length();
    assert otherLength > 0 && thisLength > 0;
    Object[] otherArray = ((ObjectArrayData)otherData).array;
    int newLength = otherLength + thisLength;
    Object[] newArray = new Object[ArrayData.alignUp(newLength)];
    System.arraycopy(this.array, 0, newArray, 0, thisLength);
    System.arraycopy(otherArray, 0, newArray, thisLength, otherLength);
    return new ObjectArrayData(newArray, newLength);
  }
  
  public String toString() {
    assert length() <= this.array.length : "" + length() + " > " + length();
    return getClass().getSimpleName() + ":" + getClass().getSimpleName();
  }
}
