package org.openjdk.nashorn.internal.objects;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.nio.ByteBuffer;
import java.util.Collections;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayData;
import org.openjdk.nashorn.internal.runtime.arrays.TypedArrayData;

public final class NativeUint8ClampedArray extends ArrayBufferView {
  public static final int BYTES_PER_ELEMENT = 1;
  
  private static PropertyMap $nasgenmap$;
  
  private static final ArrayBufferView.Factory FACTORY = new ArrayBufferView.Factory(1) {
      public ArrayBufferView construct(NativeArrayBuffer buffer, int byteOffset, int length) {
        return new NativeUint8ClampedArray(buffer, byteOffset, length);
      }
      
      public NativeUint8ClampedArray.Uint8ClampedArrayData createArrayData(ByteBuffer nb, int start, int end) {
        return new NativeUint8ClampedArray.Uint8ClampedArrayData(nb, start, end);
      }
      
      public String getClassName() {
        return "Uint8ClampedArray";
      }
    };
  
  static {
    $clinit$();
  }
  
  private static final class Uint8ClampedArrayData extends TypedArrayData<ByteBuffer> {
    private static final MethodHandle GET_ELEM = CompilerConstants.specialCall(MethodHandles.lookup(), Uint8ClampedArrayData.class, "getElem", int.class, new Class[] { int.class }).methodHandle();
    
    private static final MethodHandle SET_ELEM = CompilerConstants.specialCall(MethodHandles.lookup(), Uint8ClampedArrayData.class, "setElem", void.class, new Class[] { int.class, int.class }).methodHandle();
    
    private static final MethodHandle RINT_D = CompilerConstants.staticCall(MethodHandles.lookup(), Uint8ClampedArrayData.class, "rint", double.class, new Class[] { double.class }).methodHandle();
    
    private static final MethodHandle RINT_O = CompilerConstants.staticCall(MethodHandles.lookup(), Uint8ClampedArrayData.class, "rint", Object.class, new Class[] { Object.class }).methodHandle();
    
    private Uint8ClampedArrayData(ByteBuffer nb, int start, int end) {
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
      return int.class;
    }
    
    private int getElem(int index) {
      try {
        return ((ByteBuffer)this.nb).get(index) & 0xFF;
      } catch (IndexOutOfBoundsException e) {
        throw new ClassCastException();
      } 
    }
    
    public MethodHandle getElementSetter(Class<?> elementType) {
      MethodHandle setter = super.getElementSetter(elementType);
      if (setter != null) {
        if (elementType == Object.class)
          return Lookup.MH.filterArguments(setter, 2, new MethodHandle[] { RINT_O }); 
        if (elementType == double.class)
          return Lookup.MH.filterArguments(setter, 2, new MethodHandle[] { RINT_D }); 
      } 
      return setter;
    }
    
    private void setElem(int index, int elem) {
      try {
        if (index < ((ByteBuffer)this.nb).limit()) {
          byte clamped;
          if ((elem & 0xFFFFFF00) == 0) {
            clamped = (byte)elem;
          } else {
            clamped = (elem < 0) ? 0 : -1;
          } 
          ((ByteBuffer)this.nb).put(index, clamped);
        } 
      } catch (IndexOutOfBoundsException e) {
        throw new ClassCastException();
      } 
    }
    
    public boolean isClamped() {
      return true;
    }
    
    public boolean isUnsigned() {
      return true;
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
      return set(index, JSType.toNumber(value), strict);
    }
    
    public ArrayData set(int index, int value, boolean strict) {
      setElem(index, value);
      return (ArrayData)this;
    }
    
    public ArrayData set(int index, double value, boolean strict) {
      return set(index, (int)rint(value), strict);
    }
    
    private static double rint(double rint) {
      return (int)Math.rint(rint);
    }
    
    private static Object rint(Object rint) {
      return Double.valueOf(rint(JSType.toNumber(rint)));
    }
  }
  
  public static NativeUint8ClampedArray constructor(boolean newObj, Object self, Object... args) {
    return (NativeUint8ClampedArray)constructorImpl(newObj, args, FACTORY);
  }
  
  NativeUint8ClampedArray(NativeArrayBuffer buffer, int byteOffset, int length) {
    super(buffer, byteOffset, length);
  }
  
  protected ArrayBufferView.Factory factory() {
    return FACTORY;
  }
  
  protected static Object set(Object self, Object array, Object offset) {
    return ArrayBufferView.setImpl(self, array, offset);
  }
  
  protected static NativeUint8ClampedArray subarray(Object self, Object begin, Object end) {
    return (NativeUint8ClampedArray)ArrayBufferView.subarrayImpl(self, begin, end);
  }
  
  public static Object getIterator(Object self) {
    return ArrayIterator.newArrayValueIterator(self);
  }
  
  protected ScriptObject getPrototype(Global global) {
    return global.getUint8ClampedArrayPrototype();
  }
  
  public static void $clinit$() {
    $nasgenmap$ = PropertyMap.newMap(Collections.EMPTY_LIST);
  }
}
