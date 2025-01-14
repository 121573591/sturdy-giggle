package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.PrototypeObject;

final class NativeBoolean$Prototype extends PrototypeObject {
  private Object toString;
  
  private Object valueOf;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$toString() {
    return this.toString;
  }
  
  public void S$toString(Object paramObject) {
    this.toString = paramObject;
  }
  
  public Object G$valueOf() {
    return this.valueOf;
  }
  
  public void S$valueOf(Object paramObject) {
    this.valueOf = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_2
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'toString'
    //   11: iconst_2
    //   12: ldc
    //   14: ldc
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: dup
    //   26: ldc 'valueOf'
    //   28: iconst_2
    //   29: ldc
    //   31: ldc
    //   33: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   36: invokeinterface add : (Ljava/lang/Object;)Z
    //   41: pop
    //   42: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   45: putstatic org/openjdk/nashorn/internal/objects/NativeBoolean$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   48: return
  }
  
  NativeBoolean$Prototype() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic org/openjdk/nashorn/internal/objects/NativeBoolean$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   4: invokespecial <init> : (Lorg/openjdk/nashorn/internal/runtime/PropertyMap;)V
    //   7: aload_0
    //   8: ldc 'toString'
    //   10: ldc
    //   12: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   15: dup
    //   16: ldc 'Boolean.prototype.toString'
    //   18: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   21: putfield toString : Ljava/lang/Object;
    //   24: aload_0
    //   25: ldc 'valueOf'
    //   27: ldc
    //   29: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   32: dup
    //   33: ldc 'Boolean.prototype.valueOf'
    //   35: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   38: putfield valueOf : Ljava/lang/Object;
    //   41: return
  }
  
  public String getClassName() {
    return "Boolean";
  }
}
