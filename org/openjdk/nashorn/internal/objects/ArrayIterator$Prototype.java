package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.PrototypeObject;

final class ArrayIterator$Prototype extends PrototypeObject {
  private Object next;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$next() {
    return this.next;
  }
  
  public void S$next(Object paramObject) {
    this.next = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_1
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'next'
    //   11: iconst_0
    //   12: ldc
    //   14: ldc
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   28: putstatic org/openjdk/nashorn/internal/objects/ArrayIterator$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   31: return
  }
  
  ArrayIterator$Prototype() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic org/openjdk/nashorn/internal/objects/ArrayIterator$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   4: invokespecial <init> : (Lorg/openjdk/nashorn/internal/runtime/PropertyMap;)V
    //   7: aload_0
    //   8: ldc 'next'
    //   10: ldc
    //   12: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   15: dup
    //   16: ldc 'ArrayIterator.prototype.next'
    //   18: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   21: putfield next : Ljava/lang/Object;
    //   24: return
  }
  
  public String getClassName() {
    return "ArrayIterator";
  }
}
