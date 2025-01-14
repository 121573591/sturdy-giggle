package org.openjdk.nashorn.internal.runtime.arrays;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

final class IntArrayData extends ContinuousArrayData implements IntElements {
  private int[] array;
  
  IntArrayData() {
    this(new int[32], 0);
  }
  
  IntArrayData(int length) {
    super(length);
    this.array = new int[ArrayData.nextSize(length)];
  }
  
  IntArrayData(int[] array, int length) {
    super(length);
    assert array == null || array.length >= length;
    this.array = array;
  }
  
  public final Class<?> getElementType() {
    return int.class;
  }
  
  public final Class<?> getBoxedElementType() {
    return Integer.class;
  }
  
  public final int getElementWeight() {
    return 1;
  }
  
  public final ContinuousArrayData widest(ContinuousArrayData otherData) {
    return otherData;
  }
  
  private static final MethodHandle HAS_GET_ELEM = CompilerConstants.specialCall(MethodHandles.lookup(), IntArrayData.class, "getElem", int.class, new Class[] { int.class }).methodHandle();
  
  private static final MethodHandle SET_ELEM = CompilerConstants.specialCall(MethodHandles.lookup(), IntArrayData.class, "setElem", void.class, new Class[] { int.class, int.class }).methodHandle();
  
  public Object[] asObjectArray() {
    return toObjectArray(true);
  }
  
  private int getElem(int index) {
    if (has(index))
      return this.array[index]; 
    throw new ClassCastException();
  }
  
  private void setElem(int index, int elem) {
    if (hasRoomFor(index)) {
      this.array[index] = elem;
      return;
    } 
    throw new ClassCastException();
  }
  
  public MethodHandle getElementGetter(Class<?> returnType, int programPoint) {
    return getContinuousElementGetter(HAS_GET_ELEM, returnType, programPoint);
  }
  
  public MethodHandle getElementSetter(Class<?> elementType) {
    return (elementType == int.class) ? getContinuousElementSetter(SET_ELEM, elementType) : null;
  }
  
  public IntArrayData copy() {
    return new IntArrayData((int[])this.array.clone(), (int)length());
  }
  
  public Object asArrayOfType(Class<?> componentType) {
    if (componentType == int.class) {
      int len = (int)length();
      return (this.array.length == len) ? this.array.clone() : Arrays.copyOf(this.array, len);
    } 
    return super.asArrayOfType(componentType);
  }
  
  private Object[] toObjectArray(boolean trim) {
    assert length() <= this.array.length : "length exceeds internal array size";
    int len = (int)length();
    Object[] oarray = new Object[trim ? len : this.array.length];
    for (int index = 0; index < len; index++)
      oarray[index] = Integer.valueOf(this.array[index]); 
    return oarray;
  }
  
  private double[] toDoubleArray() {
    assert length() <= this.array.length : "length exceeds internal array size";
    int len = (int)length();
    double[] darray = new double[this.array.length];
    for (int index = 0; index < len; index++)
      darray[index] = this.array[index]; 
    return darray;
  }
  
  private NumberArrayData convertToDouble() {
    return new NumberArrayData(toDoubleArray(), (int)length());
  }
  
  private ObjectArrayData convertToObject() {
    return new ObjectArrayData(toObjectArray(false), (int)length());
  }
  
  public ArrayData convert(Class<?> type) {
    if (type == Integer.class || type == Byte.class || type == Short.class)
      return this; 
    if (type == Double.class || type == Float.class)
      return convertToDouble(); 
    return convertToObject();
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
    Arrays.fill(this.array, (int)newLength, this.array.length, 0);
    return this;
  }
  
  public ArrayData set(int index, Object value, boolean strict) {
    if (JSType.isRepresentableAsInt(value))
      return set(index, JSType.toInt32(value), strict); 
    if (value == ScriptRuntime.UNDEFINED)
      return (new UndefinedArrayFilter(this)).set(index, value, strict); 
    ArrayData newData = convert((value == null) ? Object.class : value.getClass());
    return newData.set(index, value, strict);
  }
  
  public ArrayData set(int index, int value, boolean strict) {
    this.array[index] = value;
    setLength(Math.max((index + 1), length()));
    return this;
  }
  
  public ArrayData set(int index, double value, boolean strict) {
    if (JSType.isRepresentableAsInt(value)) {
      this.array[index] = (int)(long)value;
      setLength(Math.max((index + 1), length()));
      return this;
    } 
    return convert(Double.class).set(index, value, strict);
  }
  
  public int getInt(int index) {
    return this.array[index];
  }
  
  public int getIntOptimistic(int index, int programPoint) {
    return this.array[index];
  }
  
  public double getDouble(int index) {
    return this.array[index];
  }
  
  public double getDoubleOptimistic(int index, int programPoint) {
    return this.array[index];
  }
  
  public Object getObject(int index) {
    return Integer.valueOf(this.array[index]);
  }
  
  public boolean has(int index) {
    return (0 <= index && index < length());
  }
  
  public ArrayData delete(int index) {
    return new DeletedRangeArrayFilter(this, index, index);
  }
  
  public ArrayData delete(long fromIndex, long toIndex) {
    return new DeletedRangeArrayFilter(this, fromIndex, toIndex);
  }
  
  public Object pop() {
    int len = (int)length();
    if (len == 0)
      return ScriptRuntime.UNDEFINED; 
    int newLength = len - 1;
    int elem = this.array[newLength];
    this.array[newLength] = 0;
    setLength(newLength);
    return Integer.valueOf(elem);
  }
  
  public ArrayData slice(long from, long to) {
    return new IntArrayData(Arrays.copyOfRange(this.array, (int)from, (int)to), (int)(to - ((from < 0L) ? (from + length()) : from)));
  }
  
  public ArrayData fastSplice(int start, int removed, int added) throws UnsupportedOperationException {
    long oldLength = length();
    long newLength = oldLength - removed + added;
    if (newLength > 131072L && newLength > this.array.length)
      throw new UnsupportedOperationException(); 
    ArrayData returnValue = (removed == 0) ? EMPTY_ARRAY : new IntArrayData(Arrays.copyOfRange(this.array, start, start + removed), removed);
    if (newLength != oldLength) {
      int[] newArray;
      if (newLength > this.array.length) {
        newArray = new int[ArrayData.nextSize((int)newLength)];
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
  
  public double fastPush(int arg) {
    int len = (int)length();
    if (len == this.array.length)
      this.array = Arrays.copyOf(this.array, nextSize(len)); 
    this.array[len] = arg;
    return increaseLength();
  }
  
  public int fastPopInt() {
    if (length() == 0L)
      throw new ClassCastException(); 
    int newLength = (int)decreaseLength();
    int elem = this.array[newLength];
    this.array[newLength] = 0;
    return elem;
  }
  
  public double fastPopDouble() {
    return fastPopInt();
  }
  
  public Object fastPopObject() {
    return Integer.valueOf(fastPopInt());
  }
  
  public ContinuousArrayData fastConcat(ContinuousArrayData otherData) {
    int otherLength = (int)otherData.length();
    int thisLength = (int)length();
    assert otherLength > 0 && thisLength > 0;
    int[] otherArray = ((IntArrayData)otherData).array;
    int newLength = otherLength + thisLength;
    int[] newArray = new int[ArrayData.alignUp(newLength)];
    System.arraycopy(this.array, 0, newArray, 0, thisLength);
    System.arraycopy(otherArray, 0, newArray, thisLength, otherLength);
    return new IntArrayData(newArray, newLength);
  }
  
  public String toString() {
    assert length() <= this.array.length : "" + length() + " > " + length();
    return getClass().getSimpleName() + ":" + getClass().getSimpleName();
  }
}
