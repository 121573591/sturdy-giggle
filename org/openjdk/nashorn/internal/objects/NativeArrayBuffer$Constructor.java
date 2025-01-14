package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;

final class NativeArrayBuffer$Constructor extends ScriptFunction {
  private Object isView;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$isView() {
    return this.isView;
  }
  
  public void S$isView(Object paramObject) {
    this.isView = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_1
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'isView'
    //   11: iconst_2
    //   12: ldc
    //   14: ldc
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   28: putstatic org/openjdk/nashorn/internal/objects/NativeArrayBuffer$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   31: return
  }
  
  NativeArrayBuffer$Constructor() {
    // Byte code:
    //   0: aload_0
    //   1: ldc 'ArrayBuffer'
    //   3: ldc
    //   5: getstatic org/openjdk/nashorn/internal/objects/NativeArrayBuffer$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   8: aconst_null
    //   9: invokespecial <init> : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;Lorg/openjdk/nashorn/internal/runtime/PropertyMap;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)V
    //   12: aload_0
    //   13: ldc 'isView'
    //   15: ldc
    //   17: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   20: dup
    //   21: ldc 'ArrayBuffer.isView'
    //   23: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   26: putfield isView : Ljava/lang/Object;
    //   29: aload_0
    //   30: new org/openjdk/nashorn/internal/objects/NativeArrayBuffer$Prototype
    //   33: dup
    //   34: invokespecial <init> : ()V
    //   37: dup
    //   38: aload_0
    //   39: invokestatic setConstructor : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   42: invokevirtual setPrototype : (Ljava/lang/Object;)V
    //   45: aload_0
    //   46: iconst_1
    //   47: invokevirtual setArity : (I)V
    //   50: aload_0
    //   51: ldc 'ArrayBuffer'
    //   53: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   56: return
  }
}
