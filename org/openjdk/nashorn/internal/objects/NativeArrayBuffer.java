package org.openjdk.nashorn.internal.objects;

import java.nio.ByteBuffer;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

public final class NativeArrayBuffer extends ScriptObject {
  private final ByteBuffer nb;
  
  private static PropertyMap $nasgenmap$;
  
  protected NativeArrayBuffer(ByteBuffer nb, Global global) {
    super(global.getArrayBufferPrototype(), $nasgenmap$);
    this.nb = nb;
  }
  
  protected NativeArrayBuffer(ByteBuffer nb) {
    this(nb, Global.instance());
  }
  
  protected NativeArrayBuffer(int byteLength) {
    this(ByteBuffer.allocateDirect(byteLength));
  }
  
  protected NativeArrayBuffer(NativeArrayBuffer other, int begin, int end) {
    this(cloneBuffer(other.getNioBuffer(), begin, end));
  }
  
  public static NativeArrayBuffer constructor(boolean newObj, Object self, Object... args) {
    if (!newObj)
      throw ECMAErrors.typeError("constructor.requires.new", new String[] { "ArrayBuffer" }); 
    if (args.length == 0)
      return new NativeArrayBuffer(0); 
    Object arg0 = args[0];
    if (arg0 instanceof ByteBuffer)
      return new NativeArrayBuffer((ByteBuffer)arg0); 
    return new NativeArrayBuffer(JSType.toInt32(arg0));
  }
  
  private static ByteBuffer cloneBuffer(ByteBuffer original, int begin, int end) {
    ByteBuffer clone = ByteBuffer.allocateDirect(original.capacity());
    original.rewind();
    clone.put(original);
    original.rewind();
    clone.flip();
    clone.position(begin);
    clone.limit(end);
    return clone.slice();
  }
  
  ByteBuffer getNioBuffer() {
    return this.nb;
  }
  
  public String getClassName() {
    return "ArrayBuffer";
  }
  
  public static int byteLength(Object self) {
    return ((NativeArrayBuffer)self).getByteLength();
  }
  
  public static boolean isView(Object self, Object obj) {
    return obj instanceof ArrayBufferView;
  }
  
  public static NativeArrayBuffer slice(Object self, Object begin0, Object end0) {
    NativeArrayBuffer arrayBuffer = (NativeArrayBuffer)self;
    int byteLength = arrayBuffer.getByteLength();
    int begin = adjustIndex(JSType.toInt32(begin0), byteLength);
    int end = adjustIndex((end0 != ScriptRuntime.UNDEFINED) ? JSType.toInt32(end0) : byteLength, byteLength);
    return new NativeArrayBuffer(arrayBuffer, begin, Math.max(end, begin));
  }
  
  public static Object slice(Object self, int begin, int end) {
    NativeArrayBuffer arrayBuffer = (NativeArrayBuffer)self;
    int byteLength = arrayBuffer.getByteLength();
    return new NativeArrayBuffer(arrayBuffer, adjustIndex(begin, byteLength), Math.max(adjustIndex(end, byteLength), begin));
  }
  
  public static Object slice(Object self, int begin) {
    return slice(self, begin, ((NativeArrayBuffer)self).getByteLength());
  }
  
  static int adjustIndex(int index, int length) {
    return (index < 0) ? clamp(index + length, length) : clamp(index, length);
  }
  
  private static int clamp(int index, int length) {
    if (index < 0)
      return 0; 
    if (index > length)
      return length; 
    return index;
  }
  
  int getByteLength() {
    return this.nb.limit();
  }
  
  ByteBuffer getBuffer() {
    return this.nb;
  }
  
  ByteBuffer getBuffer(int offset) {
    return this.nb.duplicate().position(offset);
  }
  
  ByteBuffer getBuffer(int offset, int length) {
    return getBuffer(offset).limit(length);
  }
  
  static {
    $clinit$();
  }
  
  public static void $clinit$() {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_1
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'byteLength'
    //   11: bipush #7
    //   13: ldc
    //   15: aconst_null
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   28: putstatic org/openjdk/nashorn/internal/objects/NativeArrayBuffer.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   31: return
  }
}
