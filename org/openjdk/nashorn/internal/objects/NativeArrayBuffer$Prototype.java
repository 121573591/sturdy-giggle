package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.PrototypeObject;

final class NativeArrayBuffer$Prototype extends PrototypeObject {
  private Object slice;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$slice() {
    return this.slice;
  }
  
  public void S$slice(Object paramObject) {
    this.slice = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_1
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'slice'
    //   11: iconst_2
    //   12: ldc
    //   14: ldc
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   28: putstatic org/openjdk/nashorn/internal/objects/NativeArrayBuffer$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   31: return
  }
  
  NativeArrayBuffer$Prototype() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic org/openjdk/nashorn/internal/objects/NativeArrayBuffer$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   4: invokespecial <init> : (Lorg/openjdk/nashorn/internal/runtime/PropertyMap;)V
    //   7: aload_0
    //   8: ldc 'slice'
    //   10: ldc
    //   12: iconst_2
    //   13: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   16: dup
    //   17: iconst_0
    //   18: new org/openjdk/nashorn/internal/runtime/Specialization
    //   21: dup
    //   22: ldc
    //   24: iconst_0
    //   25: iconst_0
    //   26: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   29: aastore
    //   30: dup
    //   31: iconst_1
    //   32: new org/openjdk/nashorn/internal/runtime/Specialization
    //   35: dup
    //   36: ldc
    //   38: iconst_0
    //   39: iconst_0
    //   40: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   43: aastore
    //   44: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   47: dup
    //   48: ldc 'ArrayBuffer.prototype.slice'
    //   50: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   53: putfield slice : Ljava/lang/Object;
    //   56: return
  }
  
  public String getClassName() {
    return "ArrayBuffer";
  }
}
