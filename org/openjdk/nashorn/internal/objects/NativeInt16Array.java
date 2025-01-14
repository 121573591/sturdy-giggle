package org.openjdk.nashorn.internal.objects;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.Collections;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayData;
import org.openjdk.nashorn.internal.runtime.arrays.TypedArrayData;

public final class NativeInt16Array extends ArrayBufferView {
  private static PropertyMap $nasgenmap$;
  
  public static final int BYTES_PER_ELEMENT = 2;
  
  private static final ArrayBufferView.Factory FACTORY = new ArrayBufferView.Factory(2) {
      public ArrayBufferView construct(NativeArrayBuffer buffer, int byteOffset, int length) {
        return new NativeInt16Array(buffer, byteOffset, length);
      }
      
      public NativeInt16Array.Int16ArrayData createArrayData(ByteBuffer nb, int start, int end) {
        return new NativeInt16Array.Int16ArrayData(nb.asShortBuffer(), start, end);
      }
      
      public String getClassName() {
        return "Int16Array";
      }
    };
  
  static {
    $clinit$();
  }
  
  private static final class Int16ArrayData extends TypedArrayData<ShortBuffer> {
    private static final MethodHandle GET_ELEM = CompilerConstants.specialCall(MethodHandles.lookup(), Int16ArrayData.class, "getElem", int.class, new Class[] { int.class }).methodHandle();
    
    private static final MethodHandle SET_ELEM = CompilerConstants.specialCall(MethodHandles.lookup(), Int16ArrayData.class, "setElem", void.class, new Class[] { int.class, int.class }).methodHandle();
    
    private Int16ArrayData(ShortBuffer nb, int start, int end) {
      super(nb.position(start).limit(end).slice(), end - start);
    }
    
    protected MethodHandle getGetElem() {
      return GET_ELEM;
    }
    
    protected MethodHandle getSetElem() {
      return SET_ELEM;
    }
    
    public Class<?> getElementType() {
      return int.class;
    }
    
    public Class<?> getBoxedElementType() {
      return Integer.class;
    }
    
    private int getElem(int index) {
      try {
        return ((ShortBuffer)this.nb).get(index);
      } catch (IndexOutOfBoundsException e) {
        throw new ClassCastException();
      } 
    }
    
    private void setElem(int index, int elem) {
      try {
        if (index < ((ShortBuffer)this.nb).limit())
          ((ShortBuffer)this.nb).put(index, (short)elem); 
      } catch (IndexOutOfBoundsException e) {
        throw new ClassCastException();
      } 
    }
    
    public int getInt(int index) {
      return getElem(index);
    }
    
    public int getIntOptimistic(int index, int programPoint) {
      return getElem(index);
    }
    
    public double getDouble(int index) {
      return getInt(index);
    }
    
    public double getDoubleOptimistic(int index, int programPoint) {
      return getElem(index);
    }
    
    public Object getObject(int index) {
      return Integer.valueOf(getInt(index));
    }
    
    public ArrayData set(int index, Object value, boolean strict) {
      return set(index, JSType.toInt32(value), strict);
    }
    
    public ArrayData set(int index, int value, boolean strict) {
      setElem(index, value);
      return (ArrayData)this;
    }
    
    public ArrayData set(int index, double value, boolean strict) {
      return set(index, (int)value, strict);
    }
  }
  
  public static NativeInt16Array constructor(boolean newObj, Object self, Object... args) {
    return (NativeInt16Array)constructorImpl(newObj, args, FACTORY);
  }
  
  NativeInt16Array(NativeArrayBuffer buffer, int byteOffset, int byteLength) {
    super(buffer, byteOffset, byteLength);
  }
  
  protected ArrayBufferView.Factory factory() {
    return FACTORY;
  }
  
  protected static Object set(Object self, Object array, Object offset) {
    return ArrayBufferView.setImpl(self, array, offset);
  }
  
  protected static NativeInt16Array subarray(Object self, Object begin, Object end) {
    return (NativeInt16Array)ArrayBufferView.subarrayImpl(self, begin, end);
  }
  
  public static Object getIterator(Object self) {
    return ArrayIterator.newArrayValueIterator(self);
  }
  
  protected ScriptObject getPrototype(Global global) {
    return global.getInt16ArrayPrototype();
  }
  
  public static void $clinit$() {
    $nasgenmap$ = PropertyMap.newMap(Collections.EMPTY_LIST);
  }
}
