package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;

final class NativeSymbol$Constructor extends ScriptFunction {
  private Object _for;
  
  private Object keyFor;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$iterator() {
    return NativeSymbol.iterator;
  }
  
  public Object G$_for() {
    return this._for;
  }
  
  public void S$_for(Object paramObject) {
    this._for = paramObject;
  }
  
  public Object G$keyFor() {
    return this.keyFor;
  }
  
  public void S$keyFor(Object paramObject) {
    this.keyFor = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_3
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'iterator'
    //   11: bipush #7
    //   13: ldc
    //   15: aconst_null
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: dup
    //   26: ldc 'for'
    //   28: iconst_2
    //   29: ldc
    //   31: ldc
    //   33: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   36: invokeinterface add : (Ljava/lang/Object;)Z
    //   41: pop
    //   42: dup
    //   43: ldc 'keyFor'
    //   45: iconst_2
    //   46: ldc
    //   48: ldc
    //   50: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   53: invokeinterface add : (Ljava/lang/Object;)Z
    //   58: pop
    //   59: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   62: putstatic org/openjdk/nashorn/internal/objects/NativeSymbol$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   65: return
  }
  
  NativeSymbol$Constructor() {
    // Byte code:
    //   0: aload_0
    //   1: ldc 'Symbol'
    //   3: ldc
    //   5: getstatic org/openjdk/nashorn/internal/objects/NativeSymbol$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   8: aconst_null
    //   9: invokespecial <init> : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;Lorg/openjdk/nashorn/internal/runtime/PropertyMap;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)V
    //   12: aload_0
    //   13: ldc 'for'
    //   15: ldc
    //   17: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   20: dup
    //   21: ldc 'Symbol.for'
    //   23: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   26: putfield _for : Ljava/lang/Object;
    //   29: aload_0
    //   30: ldc 'keyFor'
    //   32: ldc
    //   34: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   37: dup
    //   38: ldc 'Symbol.keyFor'
    //   40: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   43: putfield keyFor : Ljava/lang/Object;
    //   46: aload_0
    //   47: new org/openjdk/nashorn/internal/objects/NativeSymbol$Prototype
    //   50: dup
    //   51: invokespecial <init> : ()V
    //   54: dup
    //   55: aload_0
    //   56: invokestatic setConstructor : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   59: invokevirtual setPrototype : (Ljava/lang/Object;)V
    //   62: aload_0
    //   63: iconst_1
    //   64: invokevirtual setArity : (I)V
    //   67: aload_0
    //   68: ldc 'Symbol'
    //   70: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   73: return
  }
}
