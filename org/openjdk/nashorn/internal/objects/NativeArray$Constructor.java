package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;

final class NativeArray$Constructor extends ScriptFunction {
  private Object isArray;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$isArray() {
    return this.isArray;
  }
  
  public void S$isArray(Object paramObject) {
    this.isArray = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_1
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'isArray'
    //   11: iconst_2
    //   12: ldc
    //   14: ldc
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   28: putstatic org/openjdk/nashorn/internal/objects/NativeArray$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   31: return
  }
  
  NativeArray$Constructor() {
    // Byte code:
    //   0: aload_0
    //   1: ldc 'Array'
    //   3: ldc
    //   5: getstatic org/openjdk/nashorn/internal/objects/NativeArray$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   8: iconst_5
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
    //   26: dup
    //   27: iconst_1
    //   28: new org/openjdk/nashorn/internal/runtime/Specialization
    //   31: dup
    //   32: ldc
    //   34: iconst_0
    //   35: iconst_0
    //   36: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   39: aastore
    //   40: dup
    //   41: iconst_2
    //   42: new org/openjdk/nashorn/internal/runtime/Specialization
    //   45: dup
    //   46: ldc
    //   48: iconst_0
    //   49: iconst_0
    //   50: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   53: aastore
    //   54: dup
    //   55: iconst_3
    //   56: new org/openjdk/nashorn/internal/runtime/Specialization
    //   59: dup
    //   60: ldc
    //   62: iconst_0
    //   63: iconst_0
    //   64: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   67: aastore
    //   68: dup
    //   69: iconst_4
    //   70: new org/openjdk/nashorn/internal/runtime/Specialization
    //   73: dup
    //   74: ldc
    //   76: iconst_0
    //   77: iconst_0
    //   78: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   81: aastore
    //   82: invokespecial <init> : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;Lorg/openjdk/nashorn/internal/runtime/PropertyMap;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)V
    //   85: aload_0
    //   86: ldc 'isArray'
    //   88: ldc
    //   90: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   93: dup
    //   94: ldc 'Array.isArray'
    //   96: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   99: putfield isArray : Ljava/lang/Object;
    //   102: aload_0
    //   103: new org/openjdk/nashorn/internal/objects/NativeArray$Prototype
    //   106: dup
    //   107: invokespecial <init> : ()V
    //   110: dup
    //   111: aload_0
    //   112: invokestatic setConstructor : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   115: invokevirtual setPrototype : (Ljava/lang/Object;)V
    //   118: aload_0
    //   119: iconst_1
    //   120: invokevirtual setArity : (I)V
    //   123: aload_0
    //   124: ldc 'Array'
    //   126: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   129: return
  }
}
