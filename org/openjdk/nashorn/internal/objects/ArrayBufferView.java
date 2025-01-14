package org.openjdk.nashorn.internal.objects;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayData;
import org.openjdk.nashorn.internal.runtime.arrays.TypedArrayData;

public abstract class ArrayBufferView extends ScriptObject {
  private final NativeArrayBuffer buffer;
  
  private final int byteOffset;
  
  private static PropertyMap $nasgenmap$;
  
  static {
    $clinit$();
  }
  
  private ArrayBufferView(NativeArrayBuffer buffer, int byteOffset, int elementLength, Global global) {
    super($nasgenmap$);
    int bytesPerElement = bytesPerElement();
    checkConstructorArgs(buffer.getByteLength(), bytesPerElement, byteOffset, elementLength);
    setProto(getPrototype(global));
    this.buffer = buffer;
    this.byteOffset = byteOffset;
    assert byteOffset % bytesPerElement == 0;
    int start = byteOffset / bytesPerElement;
    ByteBuffer newNioBuffer = buffer.getNioBuffer().duplicate().order(ByteOrder.nativeOrder());
    TypedArrayData<?> typedArrayData = factory().createArrayData(newNioBuffer, start, start + elementLength);
    setArray((ArrayData)typedArrayData);
  }
  
  protected ArrayBufferView(NativeArrayBuffer buffer, int byteOffset, int elementLength) {
    this(buffer, byteOffset, elementLength, Global.instance());
  }
  
  private static void checkConstructorArgs(int byteLength, int bytesPerElement, int byteOffset, int elementLength) {
    if (byteOffset < 0 || elementLength < 0)
      throw new RuntimeException("byteOffset or length must not be negative, byteOffset=" + byteOffset + ", elementLength=" + elementLength + ", bytesPerElement=" + bytesPerElement); 
    if (byteOffset + elementLength * bytesPerElement > byteLength)
      throw new RuntimeException("byteOffset + byteLength out of range, byteOffset=" + byteOffset + ", elementLength=" + elementLength + ", bytesPerElement=" + bytesPerElement); 
    if (byteOffset % bytesPerElement != 0)
      throw new RuntimeException("byteOffset must be a multiple of the element size, byteOffset=" + byteOffset + " bytesPerElement=" + bytesPerElement); 
  }
  
  private int bytesPerElement() {
    return (factory()).bytesPerElement;
  }
  
  public static Object buffer(Object self) {
    return ((ArrayBufferView)self).buffer;
  }
  
  public static int byteOffset(Object self) {
    return ((ArrayBufferView)self).byteOffset;
  }
  
  public static int byteLength(Object self) {
    ArrayBufferView view = (ArrayBufferView)self;
    return ((TypedArrayData)view.getArray()).getElementLength() * view.bytesPerElement();
  }
  
  public static int length(Object self) {
    return ((ArrayBufferView)self).elementLength();
  }
  
  public final Object getLength() {
    return Integer.valueOf(elementLength());
  }
  
  private int elementLength() {
    return ((TypedArrayData)getArray()).getElementLength();
  }
  
  protected static abstract class Factory {
    final int bytesPerElement;
    
    final int maxElementLength;
    
    public Factory(int bytesPerElement) {
      this.bytesPerElement = bytesPerElement;
      this.maxElementLength = Integer.MAX_VALUE / bytesPerElement;
    }
    
    public final ArrayBufferView construct(int elementLength) {
      if (elementLength > this.maxElementLength)
        throw ECMAErrors.rangeError("inappropriate.array.buffer.length", new String[] { JSType.toString(elementLength) }); 
      return construct(new NativeArrayBuffer(elementLength * this.bytesPerElement), 0, elementLength);
    }
    
    public abstract ArrayBufferView construct(NativeArrayBuffer param1NativeArrayBuffer, int param1Int1, int param1Int2);
    
    public abstract TypedArrayData<?> createArrayData(ByteBuffer param1ByteBuffer, int param1Int1, int param1Int2);
    
    public abstract String getClassName();
  }
  
  public final String getClassName() {
    return factory().getClassName();
  }
  
  protected boolean isFloatArray() {
    return false;
  }
  
  protected static ArrayBufferView constructorImpl(boolean newObj, Object[] args, Factory factory) {
    ArrayBufferView dest;
    int length;
    Object arg0 = (args.length != 0) ? args[0] : Integer.valueOf(0);
    if (!newObj)
      throw ECMAErrors.typeError("constructor.requires.new", new String[] { factory.getClassName() }); 
    if (arg0 instanceof NativeArrayBuffer) {
      NativeArrayBuffer buffer = (NativeArrayBuffer)arg0;
      int byteOffset = (args.length > 1) ? JSType.toInt32(args[1]) : 0;
      if (args.length > 2) {
        length = JSType.toInt32(args[2]);
      } else {
        if ((buffer.getByteLength() - byteOffset) % factory.bytesPerElement != 0)
          throw new RuntimeException("buffer.byteLength - byteOffset must be a multiple of the element size"); 
        length = (buffer.getByteLength() - byteOffset) / factory.bytesPerElement;
      } 
      return factory.construct(buffer, byteOffset, length);
    } 
    if (arg0 instanceof ArrayBufferView) {
      length = ((ArrayBufferView)arg0).elementLength();
      dest = factory.construct(length);
    } else if (arg0 instanceof NativeArray) {
      length = lengthToInt(((NativeArray)arg0).getArray().length());
      dest = factory.construct(length);
    } else {
      double dlen = JSType.toNumber(arg0);
      length = lengthToInt(Double.isInfinite(dlen) ? 0L : JSType.toLong(dlen));
      return factory.construct(length);
    } 
    copyElements(dest, length, (ScriptObject)arg0, 0);
    return dest;
  }
  
  protected static Object setImpl(Object self, Object array, Object offset0) {
    int length;
    ArrayBufferView dest = (ArrayBufferView)self;
    if (array instanceof ArrayBufferView) {
      length = ((ArrayBufferView)array).elementLength();
    } else if (array instanceof NativeArray) {
      length = (int)(((NativeArray)array).getArray().length() & 0x7FFFFFFFL);
    } else {
      throw new RuntimeException("argument is not of array type");
    } 
    ScriptObject source = (ScriptObject)array;
    int offset = JSType.toInt32(offset0);
    if (dest.elementLength() < length + offset || offset < 0)
      throw new RuntimeException("offset or array length out of bounds"); 
    copyElements(dest, length, source, offset);
    return ScriptRuntime.UNDEFINED;
  }
  
  private static void copyElements(ArrayBufferView dest, int length, ScriptObject source, int offset) {
    if (!dest.isFloatArray()) {
      for (int i = 0, j = offset; i < length; i++, j++)
        dest.set(j, source.getInt(i, -1), 0); 
    } else {
      for (int i = 0, j = offset; i < length; i++, j++)
        dest.set(j, source.getDouble(i, -1), 0); 
    } 
  }
  
  private static int lengthToInt(long length) {
    if (length > 2147483647L || length < 0L)
      throw ECMAErrors.rangeError("inappropriate.array.buffer.length", new String[] { JSType.toString(length) }); 
    return (int)(length & 0x7FFFFFFFL);
  }
  
  protected static ScriptObject subarrayImpl(Object self, Object begin0, Object end0) {
    ArrayBufferView arrayView = (ArrayBufferView)self;
    int byteOffset = arrayView.byteOffset;
    int bytesPerElement = arrayView.bytesPerElement();
    int elementLength = arrayView.elementLength();
    int begin = NativeArrayBuffer.adjustIndex(JSType.toInt32(begin0), elementLength);
    int end = NativeArrayBuffer.adjustIndex((end0 != ScriptRuntime.UNDEFINED) ? JSType.toInt32(end0) : elementLength, elementLength);
    int length = Math.max(end - begin, 0);
    assert byteOffset % bytesPerElement == 0;
    return arrayView.factory().construct(arrayView.buffer, begin * bytesPerElement + byteOffset, length);
  }
  
  protected GuardedInvocation findGetIndexMethod(CallSiteDescriptor desc, LinkRequest request) {
    GuardedInvocation inv = getArray().findFastGetIndexMethod(getArray().getClass(), desc, request);
    if (inv != null)
      return inv; 
    return super.findGetIndexMethod(desc, request);
  }
  
  protected GuardedInvocation findSetIndexMethod(CallSiteDescriptor desc, LinkRequest request) {
    GuardedInvocation inv = getArray().findFastSetIndexMethod(getArray().getClass(), desc, request);
    if (inv != null)
      return inv; 
    return super.findSetIndexMethod(desc, request);
  }
  
  protected abstract Factory factory();
  
  protected abstract ScriptObject getPrototype(Global paramGlobal);
  
  public static void $clinit$() {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_4
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc_w 'buffer'
    //   12: bipush #7
    //   14: ldc_w
    //   17: aconst_null
    //   18: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   21: invokeinterface add : (Ljava/lang/Object;)Z
    //   26: pop
    //   27: dup
    //   28: ldc_w 'byteOffset'
    //   31: bipush #7
    //   33: ldc_w
    //   36: aconst_null
    //   37: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   40: invokeinterface add : (Ljava/lang/Object;)Z
    //   45: pop
    //   46: dup
    //   47: ldc_w 'byteLength'
    //   50: bipush #7
    //   52: ldc_w
    //   55: aconst_null
    //   56: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   59: invokeinterface add : (Ljava/lang/Object;)Z
    //   64: pop
    //   65: dup
    //   66: ldc_w 'length'
    //   69: bipush #7
    //   71: ldc_w
    //   74: aconst_null
    //   75: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   78: invokeinterface add : (Ljava/lang/Object;)Z
    //   83: pop
    //   84: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   87: putstatic org/openjdk/nashorn/internal/objects/ArrayBufferView.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   90: return
  }
}
