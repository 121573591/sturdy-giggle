package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;

final class NativeUint16Array$Constructor extends ScriptFunction {
  private static final PropertyMap $nasgenmap$;
  
  public int G$BYTES_PER_ELEMENT() {
    return NativeUint16Array.BYTES_PER_ELEMENT;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_1
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'BYTES_PER_ELEMENT'
    //   11: bipush #7
    //   13: ldc
    //   15: aconst_null
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   28: putstatic org/openjdk/nashorn/internal/objects/NativeUint16Array$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   31: return
  }
  
  NativeUint16Array$Constructor() {
    // Byte code:
    //   0: aload_0
    //   1: ldc 'Uint16Array'
    //   3: ldc
    //   5: getstatic org/openjdk/nashorn/internal/objects/NativeUint16Array$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   8: aconst_null
    //   9: invokespecial <init> : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;Lorg/openjdk/nashorn/internal/runtime/PropertyMap;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)V
    //   12: aload_0
    //   13: new org/openjdk/nashorn/internal/objects/NativeUint16Array$Prototype
    //   16: dup
    //   17: invokespecial <init> : ()V
    //   20: dup
    //   21: aload_0
    //   22: invokestatic setConstructor : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   25: invokevirtual setPrototype : (Ljava/lang/Object;)V
    //   28: aload_0
    //   29: iconst_1
    //   30: invokevirtual setArity : (I)V
    //   33: aload_0
    //   34: ldc 'Uint16Array'
    //   36: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   39: return
  }
}
