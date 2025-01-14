package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.PrototypeObject;

final class NativeNumber$Prototype extends PrototypeObject {
  private Object toFixed;
  
  private Object toExponential;
  
  private Object toPrecision;
  
  private Object toString;
  
  private Object toLocaleString;
  
  private Object valueOf;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$toFixed() {
    return this.toFixed;
  }
  
  public void S$toFixed(Object paramObject) {
    this.toFixed = paramObject;
  }
  
  public Object G$toExponential() {
    return this.toExponential;
  }
  
  public void S$toExponential(Object paramObject) {
    this.toExponential = paramObject;
  }
  
  public Object G$toPrecision() {
    return this.toPrecision;
  }
  
  public void S$toPrecision(Object paramObject) {
    this.toPrecision = paramObject;
  }
  
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
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: bipush #6
    //   6: invokespecial <init> : (I)V
    //   9: dup
    //   10: ldc 'toFixed'
    //   12: iconst_2
    //   13: ldc
    //   15: ldc
    //   17: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   20: invokeinterface add : (Ljava/lang/Object;)Z
    //   25: pop
    //   26: dup
    //   27: ldc 'toExponential'
    //   29: iconst_2
    //   30: ldc
    //   32: ldc
    //   34: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   37: invokeinterface add : (Ljava/lang/Object;)Z
    //   42: pop
    //   43: dup
    //   44: ldc 'toPrecision'
    //   46: iconst_2
    //   47: ldc
    //   49: ldc
    //   51: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   54: invokeinterface add : (Ljava/lang/Object;)Z
    //   59: pop
    //   60: dup
    //   61: ldc 'toString'
    //   63: iconst_2
    //   64: ldc
    //   66: ldc
    //   68: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   71: invokeinterface add : (Ljava/lang/Object;)Z
    //   76: pop
    //   77: dup
    //   78: ldc 'toLocaleString'
    //   80: iconst_2
    //   81: ldc
    //   83: ldc
    //   85: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   88: invokeinterface add : (Ljava/lang/Object;)Z
    //   93: pop
    //   94: dup
    //   95: ldc 'valueOf'
    //   97: iconst_2
    //   98: ldc
    //   100: ldc
    //   102: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   105: invokeinterface add : (Ljava/lang/Object;)Z
    //   110: pop
    //   111: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   114: putstatic org/openjdk/nashorn/internal/objects/NativeNumber$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   117: return
  }
  
  NativeNumber$Prototype() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic org/openjdk/nashorn/internal/objects/NativeNumber$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   4: invokespecial <init> : (Lorg/openjdk/nashorn/internal/runtime/PropertyMap;)V
    //   7: aload_0
    //   8: ldc 'toFixed'
    //   10: ldc
    //   12: iconst_1
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
    //   30: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   33: dup
    //   34: ldc 'Number.prototype.toFixed'
    //   36: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   39: putfield toFixed : Ljava/lang/Object;
    //   42: aload_0
    //   43: ldc 'toExponential'
    //   45: ldc
    //   47: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   50: dup
    //   51: ldc 'Number.prototype.toExponential'
    //   53: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   56: putfield toExponential : Ljava/lang/Object;
    //   59: aload_0
    //   60: ldc 'toPrecision'
    //   62: ldc
    //   64: iconst_1
    //   65: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   68: dup
    //   69: iconst_0
    //   70: new org/openjdk/nashorn/internal/runtime/Specialization
    //   73: dup
    //   74: ldc
    //   76: iconst_0
    //   77: iconst_0
    //   78: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   81: aastore
    //   82: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   85: dup
    //   86: ldc 'Number.prototype.toPrecision'
    //   88: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   91: putfield toPrecision : Ljava/lang/Object;
    //   94: aload_0
    //   95: ldc 'toString'
    //   97: ldc
    //   99: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   102: dup
    //   103: ldc 'Number.prototype.toString'
    //   105: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   108: putfield toString : Ljava/lang/Object;
    //   111: aload_0
    //   112: ldc 'toLocaleString'
    //   114: ldc
    //   116: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   119: dup
    //   120: ldc 'Number.prototype.toLocaleString'
    //   122: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   125: putfield toLocaleString : Ljava/lang/Object;
    //   128: aload_0
    //   129: ldc 'valueOf'
    //   131: ldc
    //   133: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   136: dup
    //   137: ldc 'Number.prototype.valueOf'
    //   139: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   142: putfield valueOf : Ljava/lang/Object;
    //   145: return
  }
  
  public String getClassName() {
    return "Number";
  }
}
