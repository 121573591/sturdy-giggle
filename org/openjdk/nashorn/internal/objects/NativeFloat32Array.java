package org.openjdk.nashorn.internal.objects;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Collections;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayData;
import org.openjdk.nashorn.internal.runtime.arrays.TypedArrayData;

public final class NativeFloat32Array extends ArrayBufferView {
  public static final int BYTES_PER_ELEMENT = 4;
  
  private static PropertyMap $nasgenmap$;
  
  private static final ArrayBufferView.Factory FACTORY = new ArrayBufferView.Factory(4) {
      public ArrayBufferView construct(NativeArrayBuffer buffer, int byteOffset, int length) {
        return new NativeFloat32Array(buffer, byteOffset, length);
      }
      
      public NativeFloat32Array.Float32ArrayData createArrayData(ByteBuffer nb, int start, int end) {
        return new NativeFloat32Array.Float32ArrayData(nb.asFloatBuffer(), start, end);
      }
      
      public String getClassName() {
        return "Float32Array";
      }
    };
  
  static {
    $clinit$();
  }
  
  private static final class Float32ArrayData extends TypedArrayData<FloatBuffer> {
    private static final MethodHandle GET_ELEM = CompilerConstants.specialCall(MethodHandles.lookup(), Float32ArrayData.class, "getElem", double.class, new Class[] { int.class }).methodHandle();
    
    private static final MethodHandle SET_ELEM = CompilerConstants.specialCall(MethodHandles.lookup(), Float32ArrayData.class, "setElem", void.class, new Class[] { int.class, double.class }).methodHandle();
    
    private Float32ArrayData(FloatBuffer nb, int start, int end) {
      super(nb.position(start).limit(end).slice(), end - start);
    }
    
    public Class<?> getElementType() {
      return double.class;
    }
    
    public Class<?> getBoxedElementType() {
      return Double.class;
    }
    
    protected MethodHandle getGetElem() {
      return GET_ELEM;
    }
    
    protected MethodHandle getSetElem() {
      return SET_ELEM;
    }
    
    private double getElem(int index) {
      try {
        return ((FloatBuffer)this.nb).get(index);
      } catch (IndexOutOfBoundsException e) {
        throw new ClassCastException();
      } 
    }
    
    private void setElem(int index, double elem) {
      try {
        if (index < ((FloatBuffer)this.nb).limit())
          ((FloatBuffer)this.nb).put(index, (float)elem); 
      } catch (IndexOutOfBoundsException e) {
        throw new ClassCastException();
      } 
    }
    
    public MethodHandle getElementGetter(Class<?> returnType, int programPoint) {
      if (returnType == int.class)
        return null; 
      return getContinuousElementGetter(getClass(), GET_ELEM, returnType, programPoint);
    }
    
    public int getInt(int index) {
      return (int)getDouble(index);
    }
    
    public double getDouble(int index) {
      return getElem(index);
    }
    
    public double getDoubleOptimistic(int index, int programPoint) {
      return getElem(index);
    }
    
    public Object getObject(int index) {
      return Double.valueOf(getDouble(index));
    }
    
    public ArrayData set(int index, Object value, boolean strict) {
      return set(index, JSType.toNumber(value), strict);
    }
    
    public ArrayData set(int index, int value, boolean strict) {
      return set(index, value, strict);
    }
    
    public ArrayData set(int index, double value, boolean strict) {
      setElem(index, value);
      return (ArrayData)this;
    }
  }
  
  public static NativeFloat32Array constructor(boolean newObj, Object self, Object... args) {
    return (NativeFloat32Array)constructorImpl(newObj, args, FACTORY);
  }
  
  NativeFloat32Array(NativeArrayBuffer buffer, int byteOffset, int length) {
    super(buffer, byteOffset, length);
  }
  
  protected ArrayBufferView.Factory factory() {
    return FACTORY;
  }
  
  protected boolean isFloatArray() {
    return true;
  }
  
  protected static Object set(Object self, Object array, Object offset) {
    return ArrayBufferView.setImpl(self, array, offset);
  }
  
  protected static NativeFloat32Array subarray(Object self, Object begin, Object end) {
    return (NativeFloat32Array)ArrayBufferView.subarrayImpl(self, begin, end);
  }
  
  public static Object getIterator(Object self) {
    return ArrayIterator.newArrayValueIterator(self);
  }
  
  protected ScriptObject getPrototype(Global global) {
    return global.getFloat32ArrayPrototype();
  }
  
  public static void $clinit$() {
    $nasgenmap$ = PropertyMap.newMap(Collections.EMPTY_LIST);
  }
}
