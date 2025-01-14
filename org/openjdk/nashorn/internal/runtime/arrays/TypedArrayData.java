package org.openjdk.nashorn.internal.runtime.arrays;

import java.lang.invoke.MethodHandle;
import java.nio.Buffer;
import org.openjdk.nashorn.internal.lookup.Lookup;

public abstract class TypedArrayData<T extends Buffer> extends ContinuousArrayData {
  protected final T nb;
  
  protected TypedArrayData(T nb, int elementLength) {
    super(elementLength);
    this.nb = nb;
  }
  
  public final int getElementLength() {
    return (int)length();
  }
  
  public boolean isUnsigned() {
    return false;
  }
  
  public boolean isClamped() {
    return false;
  }
  
  public boolean canDelete(int index, boolean strict) {
    return false;
  }
  
  public boolean canDelete(long longIndex, boolean strict) {
    return false;
  }
  
  public TypedArrayData<T> copy() {
    throw new UnsupportedOperationException();
  }
  
  public Object[] asObjectArray() {
    throw new UnsupportedOperationException();
  }
  
  public ArrayData shiftLeft(int by) {
    throw new UnsupportedOperationException();
  }
  
  public ArrayData shiftRight(int by) {
    throw new UnsupportedOperationException();
  }
  
  public ArrayData ensure(long safeIndex) {
    return this;
  }
  
  public ArrayData shrink(long newLength) {
    throw new UnsupportedOperationException();
  }
  
  public final boolean has(int index) {
    return (0 <= index && index < length());
  }
  
  public ArrayData delete(int index) {
    return this;
  }
  
  public ArrayData delete(long fromIndex, long toIndex) {
    return this;
  }
  
  public TypedArrayData<T> convert(Class<?> type) {
    throw new UnsupportedOperationException();
  }
  
  public Object pop() {
    throw new UnsupportedOperationException();
  }
  
  public ArrayData slice(long from, long to) {
    throw new UnsupportedOperationException();
  }
  
  public MethodHandle getElementGetter(Class<?> returnType, int programPoint) {
    MethodHandle getter = getContinuousElementGetter((Class)getClass(), getGetElem(), returnType, programPoint);
    if (getter != null)
      return Lookup.filterReturnType(getter, returnType); 
    return null;
  }
  
  public MethodHandle getElementSetter(Class<?> elementType) {
    return getContinuousElementSetter((Class)getClass(), Lookup.filterArgumentType(getSetElem(), 2, elementType), elementType);
  }
  
  protected MethodHandle getContinuousElementSetter(Class<? extends ContinuousArrayData> clazz, MethodHandle setHas, Class<?> elementType) {
    MethodHandle mh = Lookup.filterArgumentType(setHas, 2, elementType);
    return Lookup.MH.asType(mh, mh.type().changeParameterType(0, clazz));
  }
  
  protected abstract MethodHandle getGetElem();
  
  protected abstract MethodHandle getSetElem();
}
