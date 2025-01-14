package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.PrototypeObject;

final class NativeRegExp$Prototype extends PrototypeObject {
  private Object compile;
  
  private Object exec;
  
  private Object test;
  
  private Object toString;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$compile() {
    return this.compile;
  }
  
  public void S$compile(Object paramObject) {
    this.compile = paramObject;
  }
  
  public Object G$exec() {
    return this.exec;
  }
  
  public void S$exec(Object paramObject) {
    this.exec = paramObject;
  }
  
  public Object G$test() {
    return this.test;
  }
  
  public void S$test(Object paramObject) {
    this.test = paramObject;
  }
  
  public Object G$toString() {
    return this.toString;
  }
  
  public void S$toString(Object paramObject) {
    this.toString = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_4
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'compile'
    //   11: iconst_2
    //   12: ldc
    //   14: ldc
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: dup
    //   26: ldc 'exec'
    //   28: iconst_2
    //   29: ldc
    //   31: ldc
    //   33: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   36: invokeinterface add : (Ljava/lang/Object;)Z
    //   41: pop
    //   42: dup
    //   43: ldc 'test'
    //   45: iconst_2
    //   46: ldc
    //   48: ldc
    //   50: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   53: invokeinterface add : (Ljava/lang/Object;)Z
    //   58: pop
    //   59: dup
    //   60: ldc 'toString'
    //   62: iconst_2
    //   63: ldc
    //   65: ldc
    //   67: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   70: invokeinterface add : (Ljava/lang/Object;)Z
    //   75: pop
    //   76: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   79: putstatic org/openjdk/nashorn/internal/objects/NativeRegExp$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   82: return
  }
  
  NativeRegExp$Prototype() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic org/openjdk/nashorn/internal/objects/NativeRegExp$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   4: invokespecial <init> : (Lorg/openjdk/nashorn/internal/runtime/PropertyMap;)V
    //   7: aload_0
    //   8: ldc 'compile'
    //   10: ldc
    //   12: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   15: dup
    //   16: ldc 'RegExp.prototype.compile'
    //   18: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   21: putfield compile : Ljava/lang/Object;
    //   24: aload_0
    //   25: ldc 'exec'
    //   27: ldc
    //   29: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   32: dup
    //   33: ldc 'RegExp.prototype.exec'
    //   35: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   38: putfield exec : Ljava/lang/Object;
    //   41: aload_0
    //   42: ldc 'test'
    //   44: ldc
    //   46: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   49: dup
    //   50: ldc 'RegExp.prototype.test'
    //   52: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   55: putfield test : Ljava/lang/Object;
    //   58: aload_0
    //   59: ldc 'toString'
    //   61: ldc
    //   63: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   66: dup
    //   67: ldc 'RegExp.prototype.toString'
    //   69: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   72: putfield toString : Ljava/lang/Object;
    //   75: return
  }
  
  public String getClassName() {
    return "RegExp";
  }
}
