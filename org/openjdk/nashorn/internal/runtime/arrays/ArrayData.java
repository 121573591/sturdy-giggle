package org.openjdk.nashorn.internal.runtime.arrays;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.objects.Global;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyDescriptor;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.UnwarrantedOptimismException;

public abstract class ArrayData {
  protected static final int CHUNK_SIZE = 32;
  
  public static final ArrayData EMPTY_ARRAY = new UntouchedArrayData();
  
  private long length;
  
  protected static final CompilerConstants.Call THROW_UNWARRANTED = CompilerConstants.staticCall(MethodHandles.lookup(), ArrayData.class, "throwUnwarranted", void.class, new Class[] { ArrayData.class, int.class, int.class });
  
  private static class UntouchedArrayData extends ContinuousArrayData {
    private UntouchedArrayData() {
      super(0L);
    }
    
    private ArrayData toRealArrayData() {
      return new IntArrayData(0);
    }
    
    private ArrayData toRealArrayData(int index) {
      IntArrayData newData = new IntArrayData(index + 1);
      return new DeletedRangeArrayFilter(newData, 0L, index);
    }
    
    public ContinuousArrayData copy() {
      assert length() == 0L;
      return this;
    }
    
    public Object asArrayOfType(Class<?> componentType) {
      return Array.newInstance(componentType, 0);
    }
    
    public Object[] asObjectArray() {
      return ScriptRuntime.EMPTY_ARRAY;
    }
    
    public ArrayData ensure(long safeIndex) {
      assert safeIndex >= 0L;
      if (safeIndex >= 131072L)
        return new SparseArrayData(this, safeIndex + 1L); 
      return toRealArrayData((int)safeIndex);
    }
    
    public ArrayData convert(Class<?> type) {
      return toRealArrayData().convert(type);
    }
    
    public ArrayData delete(int index) {
      return new DeletedRangeArrayFilter(this, index, index);
    }
    
    public ArrayData delete(long fromIndex, long toIndex) {
      return new DeletedRangeArrayFilter(this, fromIndex, toIndex);
    }
    
    public ArrayData shiftLeft(int by) {
      return this;
    }
    
    public ArrayData shiftRight(int by) {
      return this;
    }
    
    public ArrayData shrink(long newLength) {
      return this;
    }
    
    public ArrayData set(int index, Object value, boolean strict) {
      return toRealArrayData(index).set(index, value, strict);
    }
    
    public ArrayData set(int index, int value, boolean strict) {
      return toRealArrayData(index).set(index, value, strict);
    }
    
    public ArrayData set(int index, double value, boolean strict) {
      return toRealArrayData(index).set(index, value, strict);
    }
    
    public int getInt(int index) {
      throw new ArrayIndexOutOfBoundsException(index);
    }
    
    public double getDouble(int index) {
      throw new ArrayIndexOutOfBoundsException(index);
    }
    
    public Object getObject(int index) {
      throw new ArrayIndexOutOfBoundsException(index);
    }
    
    public boolean has(int index) {
      return false;
    }
    
    public Object pop() {
      return ScriptRuntime.UNDEFINED;
    }
    
    public ArrayData push(boolean strict, Object item) {
      return toRealArrayData().push(strict, item);
    }
    
    public ArrayData slice(long from, long to) {
      return this;
    }
    
    public ContinuousArrayData fastConcat(ContinuousArrayData otherData) {
      return otherData.copy();
    }
    
    public String toString() {
      return getClass().getSimpleName();
    }
    
    public MethodHandle getElementGetter(Class<?> returnType, int programPoint) {
      return null;
    }
    
    public MethodHandle getElementSetter(Class<?> elementType) {
      return null;
    }
    
    public Class<?> getElementType() {
      return int.class;
    }
    
    public Class<?> getBoxedElementType() {
      return Integer.class;
    }
  }
  
  protected ArrayData(long length) {
    this.length = length;
  }
  
  public static ArrayData initialArray() {
    return new IntArrayData();
  }
  
  protected static void throwUnwarranted(ArrayData data, int programPoint, int index) {
    throw new UnwarrantedOptimismException(data.getObject(index), programPoint);
  }
  
  protected static int alignUp(int size) {
    return size + 32 - 1 & 0xFFFFFFE0;
  }
  
  public static ArrayData allocate(long length) {
    if (length == 0L)
      return new IntArrayData(); 
    if (length >= 131072L)
      return new SparseArrayData(EMPTY_ARRAY, length); 
    return new DeletedRangeArrayFilter(new IntArrayData((int)length), 0L, length - 1L);
  }
  
  public static ArrayData allocate(Object array) {
    Class<?> clazz = array.getClass();
    if (clazz == int[].class)
      return new IntArrayData((int[])array, ((int[])array).length); 
    if (clazz == double[].class)
      return new NumberArrayData((double[])array, ((double[])array).length); 
    return new ObjectArrayData((Object[])array, ((Object[])array).length);
  }
  
  public static ArrayData allocate(int[] array) {
    return new IntArrayData(array, array.length);
  }
  
  public static ArrayData allocate(double[] array) {
    return new NumberArrayData(array, array.length);
  }
  
  public static ArrayData allocate(Object[] array) {
    return new ObjectArrayData(array, array.length);
  }
  
  public static ArrayData allocate(ByteBuffer buf) {
    return new ByteBufferArrayData(buf);
  }
  
  public static ArrayData freeze(ArrayData underlying) {
    return new FrozenArrayFilter(underlying);
  }
  
  public static ArrayData seal(ArrayData underlying) {
    return new SealedArrayFilter(underlying);
  }
  
  public static ArrayData preventExtension(ArrayData underlying) {
    return new NonExtensibleArrayFilter(underlying);
  }
  
  public static ArrayData setIsLengthNotWritable(ArrayData underlying) {
    return new LengthNotWritableFilter(underlying);
  }
  
  public final long length() {
    return this.length;
  }
  
  public abstract ArrayData copy();
  
  public abstract Object[] asObjectArray();
  
  public Object asArrayOfType(Class<?> componentType) {
    return JSType.convertArray(asObjectArray(), componentType);
  }
  
  public void setLength(long length) {
    this.length = length;
  }
  
  protected final long increaseLength() {
    return ++this.length;
  }
  
  protected final long decreaseLength() {
    return --this.length;
  }
  
  public abstract ArrayData shiftLeft(int paramInt);
  
  public abstract ArrayData shiftRight(int paramInt);
  
  public abstract ArrayData ensure(long paramLong);
  
  public abstract ArrayData shrink(long paramLong);
  
  public abstract ArrayData set(int paramInt, Object paramObject, boolean paramBoolean);
  
  public abstract ArrayData set(int paramInt1, int paramInt2, boolean paramBoolean);
  
  public abstract ArrayData set(int paramInt, double paramDouble, boolean paramBoolean);
  
  public ArrayData setEmpty(int index) {
    return this;
  }
  
  public ArrayData setEmpty(long lo, long hi) {
    return this;
  }
  
  public abstract int getInt(int paramInt);
  
  public Type getOptimisticType() {
    return Type.OBJECT;
  }
  
  public int getIntOptimistic(int index, int programPoint) {
    throw new UnwarrantedOptimismException(getObject(index), programPoint, getOptimisticType());
  }
  
  public abstract double getDouble(int paramInt);
  
  public double getDoubleOptimistic(int index, int programPoint) {
    throw new UnwarrantedOptimismException(getObject(index), programPoint, getOptimisticType());
  }
  
  public abstract Object getObject(int paramInt);
  
  public abstract boolean has(int paramInt);
  
  public boolean canDelete(int index, boolean strict) {
    return true;
  }
  
  public boolean canDelete(long longIndex, boolean strict) {
    return true;
  }
  
  public final ArrayData safeDelete(long fromIndex, long toIndex, boolean strict) {
    if (fromIndex <= toIndex && canDelete(fromIndex, strict))
      return delete(fromIndex, toIndex); 
    return this;
  }
  
  public PropertyDescriptor getDescriptor(Global global, int index) {
    return global.newDataDescriptor(getObject(index), true, true, true);
  }
  
  public abstract ArrayData delete(int paramInt);
  
  public abstract ArrayData delete(long paramLong1, long paramLong2);
  
  public abstract ArrayData convert(Class<?> paramClass);
  
  public ArrayData push(boolean strict, Object... items) {
    if (items.length == 0)
      return this; 
    Class<?> widest = widestType(items);
    ArrayData newData = convert(widest);
    long pos = newData.length;
    for (Object item : items) {
      newData = newData.ensure(pos);
      newData.set((int)pos++, item, strict);
    } 
    return newData;
  }
  
  public ArrayData push(boolean strict, Object item) {
    return push(strict, new Object[] { item });
  }
  
  public abstract Object pop();
  
  public abstract ArrayData slice(long paramLong1, long paramLong2);
  
  public ArrayData fastSplice(int start, int removed, int added) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }
  
  static Class<?> widestType(Object... items) {
    assert items.length > 0;
    Class<?> widest = Integer.class;
    for (Object item : items) {
      if (item == null)
        return Object.class; 
      Class<?> itemClass = item.getClass();
      if (itemClass == Double.class || itemClass == Float.class || itemClass == Long.class) {
        if (widest == Integer.class)
          widest = Double.class; 
      } else if (itemClass != Integer.class && itemClass != Short.class && itemClass != Byte.class) {
        return Object.class;
      } 
    } 
    return widest;
  }
  
  protected List<Long> computeIteratorKeys() {
    List<Long> keys = new ArrayList<>();
    long len = length();
    long i;
    for (i = 0L; i < len; i = nextIndex(i)) {
      if (has((int)i))
        keys.add(Long.valueOf(i)); 
    } 
    return keys;
  }
  
  public Iterator<Long> indexIterator() {
    return computeIteratorKeys().iterator();
  }
  
  public static int nextSize(int size) {
    return alignUp(size + 1) * 2;
  }
  
  long nextIndex(long index) {
    return index + 1L;
  }
  
  static Object invoke(MethodHandle mh, Object arg) {
    try {
      return mh.invoke(arg);
    } catch (RuntimeException|Error e) {
      throw e;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } 
  }
  
  public GuardedInvocation findFastCallMethod(Class<? extends ArrayData> clazz, CallSiteDescriptor desc, LinkRequest request) {
    return null;
  }
  
  public GuardedInvocation findFastGetIndexMethod(Class<? extends ArrayData> clazz, CallSiteDescriptor desc, LinkRequest request) {
    return null;
  }
  
  public GuardedInvocation findFastSetIndexMethod(Class<? extends ArrayData> clazz, CallSiteDescriptor desc, LinkRequest request) {
    return null;
  }
}
