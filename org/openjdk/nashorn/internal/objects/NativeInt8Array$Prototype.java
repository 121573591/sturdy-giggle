package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.PrototypeObject;

final class NativeInt8Array$Prototype extends PrototypeObject {
  private Object set;
  
  private Object subarray;
  
  private Object getIterator;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$set() {
    return this.set;
  }
  
  public void S$set(Object paramObject) {
    this.set = paramObject;
  }
  
  public Object G$subarray() {
    return this.subarray;
  }
  
  public void S$subarray(Object paramObject) {
    this.subarray = paramObject;
  }
  
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
    //   4: iconst_3
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'set'
    //   11: iconst_2
    //   12: ldc
    //   14: ldc
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: dup
    //   26: ldc 'subarray'
    //   28: iconst_2
    //   29: ldc
    //   31: ldc
    //   33: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   36: invokeinterface add : (Ljava/lang/Object;)Z
    //   41: pop
    //   42: dup
    //   43: getstatic org/openjdk/nashorn/internal/objects/NativeSymbol.iterator : Lorg/openjdk/nashorn/internal/runtime/Symbol;
    //   46: iconst_2
    //   47: ldc
    //   49: ldc
    //   51: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   54: invokeinterface add : (Ljava/lang/Object;)Z
    //   59: pop
    //   60: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   63: putstatic org/openjdk/nashorn/internal/objects/NativeInt8Array$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   66: return
  }
  
  NativeInt8Array$Prototype() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic org/openjdk/nashorn/internal/objects/NativeInt8Array$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   4: invokespecial <init> : (Lorg/openjdk/nashorn/internal/runtime/PropertyMap;)V
    //   7: aload_0
    //   8: ldc 'set'
    //   10: ldc
    //   12: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   15: dup
    //   16: ldc 'Int8Array.prototype.set'
    //   18: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   21: putfield set : Ljava/lang/Object;
    //   24: aload_0
    //   25: ldc 'subarray'
    //   27: ldc
    //   29: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   32: dup
    //   33: ldc 'Int8Array.prototype.subarray'
    //   35: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   38: putfield subarray : Ljava/lang/Object;
    //   41: aload_0
    //   42: ldc 'Symbol[iterator]'
    //   44: ldc
    //   46: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   49: dup
    //   50: ldc 'Int8Array.prototype.@@iterator'
    //   52: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   55: putfield getIterator : Ljava/lang/Object;
    //   58: return
  }
  
  public String getClassName() {
    return "Int8Array";
  }
}
