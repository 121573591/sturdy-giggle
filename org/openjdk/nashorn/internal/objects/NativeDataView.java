package org.openjdk.nashorn.internal.objects;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

public class NativeDataView extends ScriptObject {
  private static PropertyMap $nasgenmap$;
  
  public final Object buffer;
  
  public final int byteOffset;
  
  public final int byteLength;
  
  private final ByteBuffer buf;
  
  private NativeDataView(NativeArrayBuffer arrBuf) {
    this(arrBuf, arrBuf.getBuffer(), 0);
  }
  
  private NativeDataView(NativeArrayBuffer arrBuf, int offset) {
    this(arrBuf, bufferFrom(arrBuf, offset), offset);
  }
  
  private NativeDataView(NativeArrayBuffer arrBuf, int offset, int length) {
    this(arrBuf, bufferFrom(arrBuf, offset, length), offset, length);
  }
  
  private NativeDataView(NativeArrayBuffer arrBuf, ByteBuffer buf, int offset) {
    this(arrBuf, buf, offset, buf.capacity() - offset);
  }
  
  private NativeDataView(NativeArrayBuffer arrBuf, ByteBuffer buf, int offset, int length) {
    super(Global.instance().getDataViewPrototype(), $nasgenmap$);
    this.buffer = arrBuf;
    this.byteOffset = offset;
    this.byteLength = length;
    this.buf = buf;
  }
  
  public static NativeDataView constructor(boolean newObj, Object self, Object... args) {
    if (args.length == 0 || !(args[0] instanceof NativeArrayBuffer))
      throw ECMAErrors.typeError("not.an.arraybuffer.in.dataview", new String[0]); 
    NativeArrayBuffer arrBuf = (NativeArrayBuffer)args[0];
    switch (args.length) {
      case 1:
        return new NativeDataView(arrBuf);
      case 2:
        return new NativeDataView(arrBuf, JSType.toInt32(args[1]));
    } 
    return new NativeDataView(arrBuf, JSType.toInt32(args[1]), JSType.toInt32(args[2]));
  }
  
  public static NativeDataView constructor(boolean newObj, Object self, Object arrBuf, int offset) {
    if (!(arrBuf instanceof NativeArrayBuffer))
      throw ECMAErrors.typeError("not.an.arraybuffer.in.dataview", new String[0]); 
    return new NativeDataView((NativeArrayBuffer)arrBuf, offset);
  }
  
  public static NativeDataView constructor(boolean newObj, Object self, Object arrBuf, int offset, int length) {
    if (!(arrBuf instanceof NativeArrayBuffer))
      throw ECMAErrors.typeError("not.an.arraybuffer.in.dataview", new String[0]); 
    return new NativeDataView((NativeArrayBuffer)arrBuf, offset, length);
  }
  
  public static int getInt8(Object self, Object byteOffset) {
    try {
      return getBuffer(self).get(JSType.toInt32(byteOffset));
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static int getInt8(Object self, int byteOffset) {
    try {
      return getBuffer(self).get(byteOffset);
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static int getUint8(Object self, Object byteOffset) {
    try {
      return 0xFF & getBuffer(self).get(JSType.toInt32(byteOffset));
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static int getUint8(Object self, int byteOffset) {
    try {
      return 0xFF & getBuffer(self).get(byteOffset);
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static int getInt16(Object self, Object byteOffset, Object littleEndian) {
    try {
      return getBuffer(self, littleEndian).getShort(JSType.toInt32(byteOffset));
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static int getInt16(Object self, int byteOffset) {
    try {
      return getBuffer(self, false).getShort(byteOffset);
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static int getInt16(Object self, int byteOffset, boolean littleEndian) {
    try {
      return getBuffer(self, littleEndian).getShort(byteOffset);
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static int getUint16(Object self, Object byteOffset, Object littleEndian) {
    try {
      return 0xFFFF & getBuffer(self, littleEndian).getShort(JSType.toInt32(byteOffset));
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static int getUint16(Object self, int byteOffset) {
    try {
      return 0xFFFF & getBuffer(self, false).getShort(byteOffset);
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static int getUint16(Object self, int byteOffset, boolean littleEndian) {
    try {
      return 0xFFFF & getBuffer(self, littleEndian).getShort(byteOffset);
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static int getInt32(Object self, Object byteOffset, Object littleEndian) {
    try {
      return getBuffer(self, littleEndian).getInt(JSType.toInt32(byteOffset));
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static int getInt32(Object self, int byteOffset) {
    try {
      return getBuffer(self, false).getInt(byteOffset);
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static int getInt32(Object self, int byteOffset, boolean littleEndian) {
    try {
      return getBuffer(self, littleEndian).getInt(byteOffset);
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static double getUint32(Object self, Object byteOffset, Object littleEndian) {
    try {
      return (0xFFFFFFFFL & getBuffer(self, littleEndian).getInt(JSType.toInt32(byteOffset)));
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static double getUint32(Object self, int byteOffset) {
    try {
      return JSType.toUint32(getBuffer(self, false).getInt(JSType.toInt32(byteOffset)));
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static double getUint32(Object self, int byteOffset, boolean littleEndian) {
    try {
      return JSType.toUint32(getBuffer(self, littleEndian).getInt(JSType.toInt32(byteOffset)));
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static double getFloat32(Object self, Object byteOffset, Object littleEndian) {
    try {
      return getBuffer(self, littleEndian).getFloat(JSType.toInt32(byteOffset));
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static double getFloat32(Object self, int byteOffset) {
    try {
      return getBuffer(self, false).getFloat(byteOffset);
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static double getFloat32(Object self, int byteOffset, boolean littleEndian) {
    try {
      return getBuffer(self, littleEndian).getFloat(byteOffset);
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static double getFloat64(Object self, Object byteOffset, Object littleEndian) {
    try {
      return getBuffer(self, littleEndian).getDouble(JSType.toInt32(byteOffset));
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static double getFloat64(Object self, int byteOffset) {
    try {
      return getBuffer(self, false).getDouble(byteOffset);
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static double getFloat64(Object self, int byteOffset, boolean littleEndian) {
    try {
      return getBuffer(self, littleEndian).getDouble(byteOffset);
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setInt8(Object self, Object byteOffset, Object value) {
    try {
      getBuffer(self).put(JSType.toInt32(byteOffset), (byte)JSType.toInt32(value));
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setInt8(Object self, int byteOffset, int value) {
    try {
      getBuffer(self).put(byteOffset, (byte)value);
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setUint8(Object self, Object byteOffset, Object value) {
    try {
      getBuffer(self).put(JSType.toInt32(byteOffset), (byte)JSType.toInt32(value));
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setUint8(Object self, int byteOffset, int value) {
    try {
      getBuffer(self).put(byteOffset, (byte)value);
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setInt16(Object self, Object byteOffset, Object value, Object littleEndian) {
    try {
      getBuffer(self, littleEndian).putShort(JSType.toInt32(byteOffset), (short)JSType.toInt32(value));
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setInt16(Object self, int byteOffset, int value) {
    try {
      getBuffer(self, false).putShort(byteOffset, (short)value);
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setInt16(Object self, int byteOffset, int value, boolean littleEndian) {
    try {
      getBuffer(self, littleEndian).putShort(byteOffset, (short)value);
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setUint16(Object self, Object byteOffset, Object value, Object littleEndian) {
    try {
      getBuffer(self, littleEndian).putShort(JSType.toInt32(byteOffset), (short)JSType.toInt32(value));
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setUint16(Object self, int byteOffset, int value) {
    try {
      getBuffer(self, false).putShort(byteOffset, (short)value);
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setUint16(Object self, int byteOffset, int value, boolean littleEndian) {
    try {
      getBuffer(self, littleEndian).putShort(byteOffset, (short)value);
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setInt32(Object self, Object byteOffset, Object value, Object littleEndian) {
    try {
      getBuffer(self, littleEndian).putInt(JSType.toInt32(byteOffset), JSType.toInt32(value));
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setInt32(Object self, int byteOffset, int value) {
    try {
      getBuffer(self, false).putInt(byteOffset, value);
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setInt32(Object self, int byteOffset, int value, boolean littleEndian) {
    try {
      getBuffer(self, littleEndian).putInt(byteOffset, value);
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setUint32(Object self, Object byteOffset, Object value, Object littleEndian) {
    try {
      getBuffer(self, littleEndian).putInt(JSType.toInt32(byteOffset), (int)JSType.toUint32(value));
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setUint32(Object self, int byteOffset, double value) {
    try {
      getBuffer(self, false).putInt(byteOffset, (int)JSType.toUint32(value));
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setUint32(Object self, int byteOffset, double value, boolean littleEndian) {
    try {
      getBuffer(self, littleEndian).putInt(byteOffset, (int)JSType.toUint32(value));
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setFloat32(Object self, Object byteOffset, Object value, Object littleEndian) {
    try {
      getBuffer(self, littleEndian).putFloat((int)JSType.toUint32(byteOffset), (float)JSType.toNumber(value));
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setFloat32(Object self, int byteOffset, double value) {
    try {
      getBuffer(self, false).putFloat(byteOffset, (float)value);
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setFloat32(Object self, int byteOffset, double value, boolean littleEndian) {
    try {
      getBuffer(self, littleEndian).putFloat(byteOffset, (float)value);
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setFloat64(Object self, Object byteOffset, Object value, Object littleEndian) {
    try {
      getBuffer(self, littleEndian).putDouble((int)JSType.toUint32(byteOffset), JSType.toNumber(value));
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setFloat64(Object self, int byteOffset, double value) {
    try {
      getBuffer(self, false).putDouble(byteOffset, value);
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  public static Object setFloat64(Object self, int byteOffset, double value, boolean littleEndian) {
    try {
      getBuffer(self, littleEndian).putDouble(byteOffset, value);
      return ScriptRuntime.UNDEFINED;
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.offset", new String[0]);
    } 
  }
  
  private static ByteBuffer bufferFrom(NativeArrayBuffer nab, int offset) {
    try {
      return nab.getBuffer(offset);
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.constructor.offset", new String[0]);
    } 
  }
  
  private static ByteBuffer bufferFrom(NativeArrayBuffer nab, int offset, int length) {
    try {
      return nab.getBuffer(offset, length);
    } catch (IllegalArgumentException iae) {
      throw ECMAErrors.rangeError(iae, "dataview.constructor.offset", new String[0]);
    } 
  }
  
  private static NativeDataView checkSelf(Object self) {
    if (!(self instanceof NativeDataView))
      throw ECMAErrors.typeError("not.an.arraybuffer.in.dataview", new String[] { ScriptRuntime.safeToString(self) }); 
    return (NativeDataView)self;
  }
  
  private static ByteBuffer getBuffer(Object self) {
    return (checkSelf(self)).buf;
  }
  
  private static ByteBuffer getBuffer(Object self, Object littleEndian) {
    return getBuffer(self, JSType.toBoolean(littleEndian));
  }
  
  private static ByteBuffer getBuffer(Object self, boolean littleEndian) {
    return getBuffer(self).order(littleEndian ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
  }
  
  static {
    $clinit$();
  }
  
  public static void $clinit$() {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_3
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
    //   65: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   68: putstatic org/openjdk/nashorn/internal/objects/NativeDataView.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   71: return
  }
  
  public Object G$buffer() {
    return this.buffer;
  }
  
  public int G$byteOffset() {
    return this.byteOffset;
  }
  
  public int G$byteLength() {
    return this.byteLength;
  }
}
