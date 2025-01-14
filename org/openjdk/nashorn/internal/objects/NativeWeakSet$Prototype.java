package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.PrototypeObject;

final class NativeWeakSet$Prototype extends PrototypeObject {
  private Object add;
  
  private Object has;
  
  private Object delete;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$add() {
    return this.add;
  }
  
  public void S$add(Object paramObject) {
    this.add = paramObject;
  }
  
  public Object G$has() {
    return this.has;
  }
  
  public void S$has(Object paramObject) {
    this.has = paramObject;
  }
  
  public Object G$delete() {
    return this.delete;
  }
  
  public void S$delete(Object paramObject) {
    this.delete = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_3
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'add'
    //   11: iconst_2
    //   12: ldc
    //   14: ldc
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: dup
    //   26: ldc 'has'
    //   28: iconst_2
    //   29: ldc
    //   31: ldc
    //   33: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   36: invokeinterface add : (Ljava/lang/Object;)Z
    //   41: pop
    //   42: dup
    //   43: ldc 'delete'
    //   45: iconst_2
    //   46: ldc
    //   48: ldc
    //   50: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   53: invokeinterface add : (Ljava/lang/Object;)Z
    //   58: pop
    //   59: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   62: putstatic org/openjdk/nashorn/internal/objects/NativeWeakSet$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   65: return
  }
  
  NativeWeakSet$Prototype() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic org/openjdk/nashorn/internal/objects/NativeWeakSet$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   4: invokespecial <init> : (Lorg/openjdk/nashorn/internal/runtime/PropertyMap;)V
    //   7: aload_0
    //   8: ldc 'add'
    //   10: ldc
    //   12: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   15: dup
    //   16: ldc 'WeakSet.prototype.add'
    //   18: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   21: putfield add : Ljava/lang/Object;
    //   24: aload_0
    //   25: ldc 'has'
    //   27: ldc
    //   29: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   32: dup
    //   33: ldc 'WeakSet.prototype.has'
    //   35: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   38: putfield has : Ljava/lang/Object;
    //   41: aload_0
    //   42: ldc 'delete'
    //   44: ldc
    //   46: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   49: dup
    //   50: ldc 'WeakSet.prototype.delete'
    //   52: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   55: putfield delete : Ljava/lang/Object;
    //   58: return
  }
  
  public String getClassName() {
    return "WeakSet";
  }
}
