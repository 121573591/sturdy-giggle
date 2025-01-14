package org.openjdk.nashorn.internal.objects;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.nio.ByteBuffer;
import java.util.Collections;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayData;
import org.openjdk.nashorn.internal.runtime.arrays.TypedArrayData;

public final class NativeUint8Array extends ArrayBufferView {
  public static final int BYTES_PER_ELEMENT = 1;
  
  private static PropertyMap $nasgenmap$;
  
  private static final ArrayBufferView.Factory FACTORY = new ArrayBufferView.Factory(1) {
      public ArrayBufferView construct(NativeArrayBuffer buffer, int byteOffset, int length) {
        return new NativeUint8Array(buffer, byteOffset, length);
      }
      
      public NativeUint8Array.Uint8ArrayData createArrayData(ByteBuffer nb, int start, int end) {
        return new NativeUint8Array.Uint8ArrayData(nb, start, end);
      }
      
      public String getClassName() {
        return "Uint8Array";
      }
    };
  
  static {
    $clinit$();
  }
  
  private static final class Uint8ArrayData extends TypedArrayData<ByteBuffer> {
    private static final MethodHandle GET_ELEM = CompilerConstants.specialCall(MethodHandles.lookup(), Uint8ArrayData.class, "getElem", int.class, new Class[] { int.class }).methodHandle();
    
    private static final MethodHandle SET_ELEM = CompilerConstants.specialCall(MethodHandles.lookup(), Uint8ArrayData.class, "setElem", void.class, new Class[] { int.class, int.class }).methodHandle();
    
    private Uint8ArrayData(ByteBuffer nb, int start, int end) {
      super(nb.position(start).limit(end).slice(), end - start);
    }
    
    protected MethodHandle getGetElem() {
      return GET_ELEM;
    }
    
    protected MethodHandle getSetElem() {
      return SET_ELEM;
    }
    
    private int getElem(int index) {
      try {
        return ((ByteBuffer)this.nb).get(index) & 0xFF;
      } catch (IndexOutOfBoundsException e) {
        throw new ClassCastException();
      } 
    }
    
    private void setElem(int index, int elem) {
      try {
        if (index < ((ByteBuffer)this.nb).limit())
          ((ByteBuffer)this.nb).put(index, (byte)elem); 
      } catch (IndexOutOfBoundsException e) {
        throw new ClassCastException();
      } 
    }
    
    public boolean isUnsigned() {
      return true;
    }
    
    public Class<?> getElementType() {
      return int.class;
    }
    
    public Class<?> getBoxedElementType() {
      return Integer.class;
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
  
  public static NativeUint8Array constructor(boolean newObj, Object self, Object... args) {
    return (NativeUint8Array)constructorImpl(newObj, args, FACTORY);
  }
  
  NativeUint8Array(NativeArrayBuffer buffer, int byteOffset, int length) {
    super(buffer, byteOffset, length);
  }
  
  protected ArrayBufferView.Factory factory() {
    return FACTORY;
  }
  
  protected static Object set(Object self, Object array, Object offset) {
    return ArrayBufferView.setImpl(self, array, offset);
  }
  
  protected static NativeUint8Array subarray(Object self, Object begin, Object end) {
    return (NativeUint8Array)ArrayBufferView.subarrayImpl(self, begin, end);
  }
  
  public static Object getIterator(Object self) {
    return ArrayIterator.newArrayValueIterator(self);
  }
  
  protected ScriptObject getPrototype(Global global) {
    return global.getUint8ArrayPrototype();
  }
  
  public static void $clinit$() {
    $nasgenmap$ = PropertyMap.newMap(Collections.EMPTY_LIST);
  }
}
