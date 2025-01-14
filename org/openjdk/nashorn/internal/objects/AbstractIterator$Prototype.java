package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.PrototypeObject;

final class AbstractIterator$Prototype extends PrototypeObject {
  private Object getIterator;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$getIterator() {
    return this.getIterator;
  }
  
  public void S$getIterator(Object paramObject) {
    this.getIterator = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_1
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: getstatic org/openjdk/nashorn/internal/objects/NativeSymbol.iterator : Lorg/openjdk/nashorn/internal/runtime/Symbol;
    //   12: iconst_2
    //   13: ldc
    //   15: ldc
    //   17: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   20: invokeinterface add : (Ljava/lang/Object;)Z
    //   25: pop
    //   26: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   29: putstatic org/openjdk/nashorn/internal/objects/AbstractIterator$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   32: return
  }
  
  AbstractIterator$Prototype() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic org/openjdk/nashorn/internal/objects/AbstractIterator$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   4: invokespecial <init> : (Lorg/openjdk/nashorn/internal/runtime/PropertyMap;)V
    //   7: aload_0
    //   8: ldc 'Symbol[iterator]'
    //   10: ldc
    //   12: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   15: dup
    //   16: ldc 'Iterator.prototype.@@iterator'
    //   18: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   21: putfield getIterator : Ljava/lang/Object;
    //   24: return
  }
  
  public String getClassName() {
    return "Iterator";
  }
}
