package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;

public class IteratorResult extends ScriptObject {
  private static PropertyMap $nasgenmap$;
  
  public Object value;
  
  public Object done;
  
  IteratorResult(Object value, Boolean done, Global global) {
    super(global.getObjectPrototype(), $nasgenmap$);
    this.value = value;
    this.done = done;
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
    //   9: ldc 'value'
    //   11: iconst_0
    //   12: ldc
    //   14: ldc
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: dup
    //   26: ldc 'done'
    //   28: iconst_0
    //   29: ldc
    //   31: ldc
    //   33: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   36: invokeinterface add : (Ljava/lang/Object;)Z
    //   41: pop
    //   42: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   45: putstatic org/openjdk/nashorn/internal/objects/IteratorResult.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   48: return
  }
  
  public Object G$value() {
    return this.value;
  }
  
  public void S$value(Object paramObject) {
    this.value = paramObject;
  }
  
  public Object G$done() {
    return this.done;
  }
  
  public void S$done(Object paramObject) {
    this.done = paramObject;
  }
}
