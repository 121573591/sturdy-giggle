package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

public final class NativeSyntaxError extends ScriptObject {
  public Object instMessage;
  
  public Object nashornException;
  
  private static PropertyMap $nasgenmap$;
  
  NativeSyntaxError(Object msg, Global global) {
    super(global.getSyntaxErrorPrototype(), $nasgenmap$);
    if (msg != ScriptRuntime.UNDEFINED) {
      this.instMessage = JSType.toString(msg);
    } else {
      delete("message", false);
    } 
    NativeError.initException(this);
  }
  
  private NativeSyntaxError(Object msg) {
    this(msg, Global.instance());
  }
  
  public String getClassName() {
    return "Error";
  }
  
  public static NativeSyntaxError constructor(boolean newObj, Object self, Object msg) {
    return new NativeSyntaxError(msg);
  }
  
  static {
    $clinit$();
  }
  
  public static void $clinit$() {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_2
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'message'
    //   11: iconst_2
    //   12: ldc
    //   14: ldc
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: dup
    //   26: ldc 'nashornException'
    //   28: iconst_2
    //   29: ldc
    //   31: ldc
    //   33: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   36: invokeinterface add : (Ljava/lang/Object;)Z
    //   41: pop
    //   42: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   45: putstatic org/openjdk/nashorn/internal/objects/NativeSyntaxError.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   48: return
  }
  
  public Object G$instMessage() {
    return this.instMessage;
  }
  
  public void S$instMessage(Object paramObject) {
    this.instMessage = paramObject;
  }
  
  public Object G$nashornException() {
    return this.nashornException;
  }
  
  public void S$nashornException(Object paramObject) {
    this.nashornException = paramObject;
  }
}
