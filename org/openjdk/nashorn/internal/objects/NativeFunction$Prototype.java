package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.PrototypeObject;

final class NativeFunction$Prototype extends PrototypeObject {
  private Object toString;
  
  private Object apply;
  
  private Object call;
  
  private Object bind;
  
  private Object toSource;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$toString() {
    return this.toString;
  }
  
  public void S$toString(Object paramObject) {
    this.toString = paramObject;
  }
  
  public Object G$apply() {
    return this.apply;
  }
  
  public void S$apply(Object paramObject) {
    this.apply = paramObject;
  }
  
  public Object G$call() {
    return this.call;
  }
  
  public void S$call(Object paramObject) {
    this.call = paramObject;
  }
  
  public Object G$bind() {
    return this.bind;
  }
  
  public void S$bind(Object paramObject) {
    this.bind = paramObject;
  }
  
  public Object G$toSource() {
    return this.toSource;
  }
  
  public void S$toSource(Object paramObject) {
    this.toSource = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_5
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
    //   26: ldc 'apply'
    //   28: iconst_2
    //   29: ldc
    //   31: ldc
    //   33: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   36: invokeinterface add : (Ljava/lang/Object;)Z
    //   41: pop
    //   42: dup
    //   43: ldc 'call'
    //   45: iconst_2
    //   46: ldc
    //   48: ldc
    //   50: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   53: invokeinterface add : (Ljava/lang/Object;)Z
    //   58: pop
    //   59: dup
    //   60: ldc 'bind'
    //   62: iconst_2
    //   63: ldc
    //   65: ldc
    //   67: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   70: invokeinterface add : (Ljava/lang/Object;)Z
    //   75: pop
    //   76: dup
    //   77: ldc 'toSource'
    //   79: iconst_2
    //   80: ldc
    //   82: ldc
    //   84: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   87: invokeinterface add : (Ljava/lang/Object;)Z
    //   92: pop
    //   93: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   96: putstatic org/openjdk/nashorn/internal/objects/NativeFunction$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   99: return
  }
  
  NativeFunction$Prototype() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic org/openjdk/nashorn/internal/objects/NativeFunction$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   4: invokespecial <init> : (Lorg/openjdk/nashorn/internal/runtime/PropertyMap;)V
    //   7: aload_0
    //   8: ldc 'toString'
    //   10: ldc
    //   12: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   15: dup
    //   16: ldc 'Function.prototype.toString'
    //   18: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   21: putfield toString : Ljava/lang/Object;
    //   24: aload_0
    //   25: ldc 'apply'
    //   27: ldc
    //   29: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   32: dup
    //   33: ldc 'Function.prototype.apply'
    //   35: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   38: putfield apply : Ljava/lang/Object;
    //   41: aload_0
    //   42: ldc 'call'
    //   44: ldc
    //   46: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   49: dup
    //   50: iconst_1
    //   51: invokevirtual setArity : (I)V
    //   54: dup
    //   55: ldc 'Function.prototype.call'
    //   57: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   60: putfield call : Ljava/lang/Object;
    //   63: aload_0
    //   64: ldc 'bind'
    //   66: ldc
    //   68: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   71: dup
    //   72: iconst_1
    //   73: invokevirtual setArity : (I)V
    //   76: dup
    //   77: ldc 'Function.prototype.bind'
    //   79: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   82: putfield bind : Ljava/lang/Object;
    //   85: aload_0
    //   86: ldc 'toSource'
    //   88: ldc
    //   90: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   93: dup
    //   94: ldc 'Function.prototype.toSource'
    //   96: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   99: putfield toSource : Ljava/lang/Object;
    //   102: return
  }
  
  public String getClassName() {
    return "Function";
  }
}
