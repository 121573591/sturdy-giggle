package org.openjdk.nashorn.internal.objects;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Collections;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayData;
import org.openjdk.nashorn.internal.runtime.arrays.TypedArrayData;

public final class NativeUint32Array extends ArrayBufferView {
  public static final int BYTES_PER_ELEMENT = 4;
  
  private static PropertyMap $nasgenmap$;
  
  private static final ArrayBufferView.Factory FACTORY = new ArrayBufferView.Factory(4) {
      public ArrayBufferView construct(NativeArrayBuffer buffer, int byteBegin, int length) {
        return new NativeUint32Array(buffer, byteBegin, length);
      }
      
      public NativeUint32Array.Uint32ArrayData createArrayData(ByteBuffer nb, int start, int end) {
        return new NativeUint32Array.Uint32ArrayData(nb.order(ByteOrder.nativeOrder()).asIntBuffer(), start, end);
      }
      
      public String getClassName() {
        return "Uint32Array";
      }
    };
  
  static {
    $clinit$();
  }
  
  private static final class Uint32ArrayData extends TypedArrayData<IntBuffer> {
    private static final MethodHandle GET_ELEM = CompilerConstants.specialCall(MethodHandles.lookup(), Uint32ArrayData.class, "getElem", double.class, new Class[] { int.class }).methodHandle();
    
    private static final MethodHandle SET_ELEM = CompilerConstants.specialCall(MethodHandles.lookup(), Uint32ArrayData.class, "setElem", void.class, new Class[] { int.class, int.class }).methodHandle();
    
    private Uint32ArrayData(IntBuffer nb, int start, int end) {
      super(nb.position(start).limit(end).slice(), end - start);
    }
    
    protected MethodHandle getGetElem() {
      return GET_ELEM;
    }
    
    protected MethodHandle getSetElem() {
      return SET_ELEM;
    }
    
    public MethodHandle getElementGetter(Class<?> returnType, int programPoint) {
      if (returnType == int.class)
        return null; 
      return getContinuousElementGetter(getClass(), GET_ELEM, returnType, programPoint);
    }
    
    private int getRawElem(int index) {
      try {
        return ((IntBuffer)this.nb).get(index);
      } catch (IndexOutOfBoundsException e) {
        throw new ClassCastException();
      } 
    }
    
    private double getElem(int index) {
      return JSType.toUint32(getRawElem(index));
    }
    
    private void setElem(int index, int elem) {
      try {
        if (index < ((IntBuffer)this.nb).limit())
          ((IntBuffer)this.nb).put(index, elem); 
      } catch (IndexOutOfBoundsException e) {
        throw new ClassCastException();
      } 
    }
    
    public boolean isUnsigned() {
      return true;
    }
    
    public Class<?> getElementType() {
      return double.class;
    }
    
    public Class<?> getBoxedElementType() {
      return Double.class;
    }
    
    public int getInt(int index) {
      return getRawElem(index);
    }
    
    public int getIntOptimistic(int index, int programPoint) {
      return JSType.toUint32Optimistic(getRawElem(index), programPoint);
    }
    
    public double getDouble(int index) {
      return getElem(index);
    }
    
    public double getDoubleOptimistic(int index, int programPoint) {
      return getElem(index);
    }
    
    public Object getObject(int index) {
      return Double.valueOf(getElem(index));
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
  
  public static NativeUint32Array constructor(boolean newObj, Object self, Object... args) {
    return (NativeUint32Array)constructorImpl(newObj, args, FACTORY);
  }
  
  NativeUint32Array(NativeArrayBuffer buffer, int byteOffset, int length) {
    super(buffer, byteOffset, length);
  }
  
  protected ArrayBufferView.Factory factory() {
    return FACTORY;
  }
  
  protected static Object set(Object self, Object array, Object offset) {
    return ArrayBufferView.setImpl(self, array, offset);
  }
  
  protected static NativeUint32Array subarray(Object self, Object begin, Object end) {
    return (NativeUint32Array)ArrayBufferView.subarrayImpl(self, begin, end);
  }
  
  public static Object getIterator(Object self) {
    return ArrayIterator.newArrayValueIterator(self);
  }
  
  protected ScriptObject getPrototype(Global global) {
    return global.getUint32ArrayPrototype();
  }
  
  public static void $clinit$() {
    $nasgenmap$ = PropertyMap.newMap(Collections.EMPTY_LIST);
  }
}
