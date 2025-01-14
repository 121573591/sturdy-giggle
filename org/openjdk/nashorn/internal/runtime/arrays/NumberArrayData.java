package org.openjdk.nashorn.internal.runtime.arrays;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import jdk.dynalink.linker.support.TypeUtilities;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

final class NumberArrayData extends ContinuousArrayData implements NumericElements {
  private double[] array;
  
  NumberArrayData(double[] array, int length) {
    super(length);
    assert array.length >= length;
    this.array = array;
  }
  
  public final Class<?> getElementType() {
    return double.class;
  }
  
  public final Class<?> getBoxedElementType() {
    return Double.class;
  }
  
  public final int getElementWeight() {
    return 3;
  }
  
  public final ContinuousArrayData widest(ContinuousArrayData otherData) {
    return (otherData instanceof IntOrLongElements) ? this : otherData;
  }
  
  public NumberArrayData copy() {
    return new NumberArrayData((double[])this.array.clone(), (int)length());
  }
  
  public Object[] asObjectArray() {
    return toObjectArray(true);
  }
  
  private Object[] toObjectArray(boolean trim) {
    assert length() <= this.array.length : "length exceeds internal array size";
    int len = (int)length();
    Object[] oarray = new Object[trim ? len : this.array.length];
    for (int index = 0; index < len; index++)
      oarray[index] = Double.valueOf(this.array[index]); 
    return oarray;
  }
  
  public Object asArrayOfType(Class<?> componentType) {
    if (componentType == double.class) {
      int len = (int)length();
      return (this.array.length == len) ? this.array.clone() : Arrays.copyOf(this.array, len);
    } 
    return super.asArrayOfType(componentType);
  }
  
  private static boolean canWiden(Class<?> type) {
    return (TypeUtilities.isWrapperType(type) && type != Boolean.class && type != Character.class);
  }
  
  public ContinuousArrayData convert(Class<?> type) {
    if (!canWiden(type)) {
      int len = (int)length();
      return new ObjectArrayData(toObjectArray(false), len);
    } 
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
    Arrays.fill(this.array, (int)newLength, this.array.length, 0.0D);
    return this;
  }
  
  public ArrayData set(int index, Object value, boolean strict) {
    if (value instanceof Double || (value != null && canWiden(value.getClass())))
      return set(index, ((Number)value).doubleValue(), strict); 
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
    this.array[index] = value;
    setLength(Math.max((index + 1), length()));
    return this;
  }
  
  private static final MethodHandle HAS_GET_ELEM = CompilerConstants.specialCall(MethodHandles.lookup(), NumberArrayData.class, "getElem", double.class, new Class[] { int.class }).methodHandle();
  
  private static final MethodHandle SET_ELEM = CompilerConstants.specialCall(MethodHandles.lookup(), NumberArrayData.class, "setElem", void.class, new Class[] { int.class, double.class }).methodHandle();
  
  private double getElem(int index) {
    if (has(index))
      return this.array[index]; 
    throw new ClassCastException();
  }
  
  private void setElem(int index, double elem) {
    if (hasRoomFor(index)) {
      this.array[index] = elem;
      return;
    } 
    throw new ClassCastException();
  }
  
  public MethodHandle getElementGetter(Class<?> returnType, int programPoint) {
    if (returnType == int.class)
      return null; 
    return getContinuousElementGetter(HAS_GET_ELEM, returnType, programPoint);
  }
  
  public MethodHandle getElementSetter(Class<?> elementType) {
    return elementType.isPrimitive() ? getContinuousElementSetter(Lookup.MH.asType(SET_ELEM, SET_ELEM.type().changeParameterType(2, elementType)), elementType) : null;
  }
  
  public int getInt(int index) {
    return JSType.toInt32(this.array[index]);
  }
  
  public double getDouble(int index) {
    return this.array[index];
  }
  
  public double getDoubleOptimistic(int index, int programPoint) {
    return this.array[index];
  }
  
  public Object getObject(int index) {
    return Double.valueOf(this.array[index]);
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
    double elem = this.array[newLength];
    this.array[newLength] = 0.0D;
    setLength(newLength);
    return Double.valueOf(elem);
  }
  
  public ArrayData slice(long from, long to) {
    long start = (from < 0L) ? (from + length()) : from;
    long newLength = to - start;
    return new NumberArrayData(Arrays.copyOfRange(this.array, (int)from, (int)to), (int)newLength);
  }
  
  public ArrayData fastSplice(int start, int removed, int added) throws UnsupportedOperationException {
    long oldLength = length();
    long newLength = oldLength - removed + added;
    if (newLength > 131072L && newLength > this.array.length)
      throw new UnsupportedOperationException(); 
    ArrayData returnValue = (removed == 0) ? EMPTY_ARRAY : new NumberArrayData(Arrays.copyOfRange(this.array, start, start + removed), removed);
    if (newLength != oldLength) {
      double[] newArray;
      if (newLength > this.array.length) {
        newArray = new double[ArrayData.nextSize((int)newLength)];
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
    return fastPush(arg);
  }
  
  public double fastPush(long arg) {
    return fastPush(arg);
  }
  
  public double fastPush(double arg) {
    int len = (int)length();
    if (len == this.array.length)
      this.array = Arrays.copyOf(this.array, nextSize(len)); 
    this.array[len] = arg;
    return increaseLength();
  }
  
  public double fastPopDouble() {
    if (length() == 0L)
      throw new ClassCastException(); 
    int newLength = (int)decreaseLength();
    double elem = this.array[newLength];
    this.array[newLength] = 0.0D;
    return elem;
  }
  
  public Object fastPopObject() {
    return Double.valueOf(fastPopDouble());
  }
  
  public ContinuousArrayData fastConcat(ContinuousArrayData otherData) {
    int otherLength = (int)otherData.length();
    int thisLength = (int)length();
    assert otherLength > 0 && thisLength > 0;
    double[] otherArray = ((NumberArrayData)otherData).array;
    int newLength = otherLength + thisLength;
    double[] newArray = new double[ArrayData.alignUp(newLength)];
    System.arraycopy(this.array, 0, newArray, 0, thisLength);
    System.arraycopy(otherArray, 0, newArray, thisLength, otherLength);
    return new NumberArrayData(newArray, newLength);
  }
  
  public String toString() {
    assert length() <= this.array.length : "" + length() + " > " + length();
    return getClass().getSimpleName() + ":" + getClass().getSimpleName();
  }
}
