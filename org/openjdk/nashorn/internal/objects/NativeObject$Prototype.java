package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.PrototypeObject;

final class NativeObject$Prototype extends PrototypeObject {
  private Object toString;
  
  private Object toLocaleString;
  
  private Object valueOf;
  
  private Object hasOwnProperty;
  
  private Object isPrototypeOf;
  
  private Object propertyIsEnumerable;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$toString() {
    return this.toString;
  }
  
  public void S$toString(Object paramObject) {
    this.toString = paramObject;
  }
  
  public Object G$toLocaleString() {
    return this.toLocaleString;
  }
  
  public void S$toLocaleString(Object paramObject) {
    this.toLocaleString = paramObject;
  }
  
  public Object G$valueOf() {
    return this.valueOf;
  }
  
  public void S$valueOf(Object paramObject) {
    this.valueOf = paramObject;
  }
  
  public Object G$hasOwnProperty() {
    return this.hasOwnProperty;
  }
  
  public void S$hasOwnProperty(Object paramObject) {
    this.hasOwnProperty = paramObject;
  }
  
  public Object G$isPrototypeOf() {
    return this.isPrototypeOf;
  }
  
  public void S$isPrototypeOf(Object paramObject) {
    this.isPrototypeOf = paramObject;
  }
  
  public Object G$propertyIsEnumerable() {
    return this.propertyIsEnumerable;
  }
  
  public void S$propertyIsEnumerable(Object paramObject) {
    this.propertyIsEnumerable = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: bipush #6
    //   6: invokespecial <init> : (I)V
    //   9: dup
    //   10: ldc 'toString'
    //   12: iconst_2
    //   13: ldc
    //   15: ldc
    //   17: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   20: invokeinterface add : (Ljava/lang/Object;)Z
    //   25: pop
    //   26: dup
    //   27: ldc 'toLocaleString'
    //   29: iconst_2
    //   30: ldc
    //   32: ldc
    //   34: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   37: invokeinterface add : (Ljava/lang/Object;)Z
    //   42: pop
    //   43: dup
    //   44: ldc 'valueOf'
    //   46: iconst_2
    //   47: ldc
    //   49: ldc
    //   51: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   54: invokeinterface add : (Ljava/lang/Object;)Z
    //   59: pop
    //   60: dup
    //   61: ldc 'hasOwnProperty'
    //   63: iconst_2
    //   64: ldc
    //   66: ldc
    //   68: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   71: invokeinterface add : (Ljava/lang/Object;)Z
    //   76: pop
    //   77: dup
    //   78: ldc 'isPrototypeOf'
    //   80: iconst_2
    //   81: ldc
    //   83: ldc
    //   85: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   88: invokeinterface add : (Ljava/lang/Object;)Z
    //   93: pop
    //   94: dup
    //   95: ldc 'propertyIsEnumerable'
    //   97: iconst_2
    //   98: ldc
    //   100: ldc
    //   102: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   105: invokeinterface add : (Ljava/lang/Object;)Z
    //   110: pop
    //   111: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   114: putstatic org/openjdk/nashorn/internal/objects/NativeObject$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   117: return
  }
  
  NativeObject$Prototype() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic org/openjdk/nashorn/internal/objects/NativeObject$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   4: invokespecial <init> : (Lorg/openjdk/nashorn/internal/runtime/PropertyMap;)V
    //   7: aload_0
    //   8: ldc 'toString'
    //   10: ldc
    //   12: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   15: dup
    //   16: ldc 'Object.prototype.toString'
    //   18: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   21: putfield toString : Ljava/lang/Object;
    //   24: aload_0
    //   25: ldc 'toLocaleString'
    //   27: ldc
    //   29: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   32: dup
    //   33: ldc 'Object.prototype.toLocaleString'
    //   35: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   38: putfield toLocaleString : Ljava/lang/Object;
    //   41: aload_0
    //   42: ldc 'valueOf'
    //   44: ldc
    //   46: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   49: dup
    //   50: ldc 'Object.prototype.valueOf'
    //   52: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   55: putfield valueOf : Ljava/lang/Object;
    //   58: aload_0
    //   59: ldc 'hasOwnProperty'
    //   61: ldc
    //   63: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   66: dup
    //   67: ldc 'Object.prototype.hasOwnProperty'
    //   69: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   72: putfield hasOwnProperty : Ljava/lang/Object;
    //   75: aload_0
    //   76: ldc 'isPrototypeOf'
    //   78: ldc
    //   80: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   83: dup
    //   84: ldc 'Object.prototype.isPrototypeOf'
    //   86: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   89: putfield isPrototypeOf : Ljava/lang/Object;
    //   92: aload_0
    //   93: ldc 'propertyIsEnumerable'
    //   95: ldc
    //   97: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   100: dup
    //   101: ldc 'Object.prototype.propertyIsEnumerable'
    //   103: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   106: putfield propertyIsEnumerable : Ljava/lang/Object;
    //   109: return
  }
  
  public String getClassName() {
    return "Object";
  }
}
