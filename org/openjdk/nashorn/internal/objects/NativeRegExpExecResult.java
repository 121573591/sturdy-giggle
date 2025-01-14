package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayData;
import org.openjdk.nashorn.internal.runtime.regexp.RegExpResult;

public final class NativeRegExpExecResult extends ScriptObject {
  public Object index;
  
  public Object input;
  
  private static PropertyMap $nasgenmap$;
  
  NativeRegExpExecResult(RegExpResult result, Global global) {
    super(global.getArrayPrototype(), $nasgenmap$);
    setIsArray();
    setArray(ArrayData.allocate((Object[])result.getGroups().clone()));
    this.index = Integer.valueOf(result.getIndex());
    this.input = result.getInput();
  }
  
  public String getClassName() {
    return "Array";
  }
  
  public static Object length(Object self) {
    if (self instanceof ScriptObject)
      return Double.valueOf(JSType.toUint32(((ScriptObject)self).getArray().length())); 
    return Integer.valueOf(0);
  }
  
  public static void length(Object self, Object length) {
    if (self instanceof ScriptObject)
      ((ScriptObject)self).setLength(NativeArray.validLength(length)); 
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
    //   9: ldc 'index'
    //   11: iconst_0
    //   12: ldc
    //   14: ldc
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: dup
    //   26: ldc 'input'
    //   28: iconst_0
    //   29: ldc
    //   31: ldc
    //   33: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   36: invokeinterface add : (Ljava/lang/Object;)Z
    //   41: pop
    //   42: dup
    //   43: ldc 'length'
    //   45: bipush #6
    //   47: ldc
    //   49: ldc
    //   51: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   54: invokeinterface add : (Ljava/lang/Object;)Z
    //   59: pop
    //   60: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   63: putstatic org/openjdk/nashorn/internal/objects/NativeRegExpExecResult.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   66: return
  }
  
  public Object G$index() {
    return this.index;
  }
  
  public void S$index(Object paramObject) {
    this.index = paramObject;
  }
  
  public Object G$input() {
    return this.input;
  }
  
  public void S$input(Object paramObject) {
    this.input = paramObject;
  }
}
