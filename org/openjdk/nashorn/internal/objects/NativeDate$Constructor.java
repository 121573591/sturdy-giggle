package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;

final class NativeDate$Constructor extends ScriptFunction {
  private Object parse;
  
  private Object UTC;
  
  private Object now;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$parse() {
    return this.parse;
  }
  
  public void S$parse(Object paramObject) {
    this.parse = paramObject;
  }
  
  public Object G$UTC() {
    return this.UTC;
  }
  
  public void S$UTC(Object paramObject) {
    this.UTC = paramObject;
  }
  
  public Object G$now() {
    return this.now;
  }
  
  public void S$now(Object paramObject) {
    this.now = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_3
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'parse'
    //   11: iconst_2
    //   12: ldc
    //   14: ldc
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: dup
    //   26: ldc 'UTC'
    //   28: iconst_2
    //   29: ldc
    //   31: ldc
    //   33: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   36: invokeinterface add : (Ljava/lang/Object;)Z
    //   41: pop
    //   42: dup
    //   43: ldc 'now'
    //   45: iconst_2
    //   46: ldc
    //   48: ldc
    //   50: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   53: invokeinterface add : (Ljava/lang/Object;)Z
    //   58: pop
    //   59: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   62: putstatic org/openjdk/nashorn/internal/objects/NativeDate$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   65: return
  }
  
  NativeDate$Constructor() {
    // Byte code:
    //   0: aload_0
    //   1: ldc 'Date'
    //   3: ldc
    //   5: getstatic org/openjdk/nashorn/internal/objects/NativeDate$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   8: iconst_1
    //   9: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   12: dup
    //   13: iconst_0
    //   14: new org/openjdk/nashorn/internal/runtime/Specialization
    //   17: dup
    //   18: ldc
    //   20: iconst_0
    //   21: iconst_0
    //   22: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   25: aastore
    //   26: invokespecial <init> : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;Lorg/openjdk/nashorn/internal/runtime/PropertyMap;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)V
    //   29: aload_0
    //   30: ldc 'parse'
    //   32: ldc
    //   34: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   37: dup
    //   38: ldc 'Date.parse'
    //   40: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   43: putfield parse : Ljava/lang/Object;
    //   46: aload_0
    //   47: ldc 'UTC'
    //   49: ldc
    //   51: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   54: dup
    //   55: bipush #7
    //   57: invokevirtual setArity : (I)V
    //   60: dup
    //   61: ldc 'Date.UTC'
    //   63: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   66: putfield UTC : Ljava/lang/Object;
    //   69: aload_0
    //   70: ldc 'now'
    //   72: ldc
    //   74: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   77: dup
    //   78: ldc 'Date.now'
    //   80: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   83: putfield now : Ljava/lang/Object;
    //   86: aload_0
    //   87: new org/openjdk/nashorn/internal/objects/NativeDate$Prototype
    //   90: dup
    //   91: invokespecial <init> : ()V
    //   94: dup
    //   95: aload_0
    //   96: invokestatic setConstructor : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   99: invokevirtual setPrototype : (Ljava/lang/Object;)V
    //   102: aload_0
    //   103: bipush #7
    //   105: invokevirtual setArity : (I)V
    //   108: aload_0
    //   109: ldc 'Date'
    //   111: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   114: return
  }
}
