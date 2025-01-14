package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.PrototypeObject;

final class NativeError$Prototype extends PrototypeObject {
  private Object name;
  
  private Object message;
  
  private Object printStackTrace;
  
  private Object getStackTrace;
  
  private Object toString;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$name() {
    return this.name;
  }
  
  public void S$name(Object paramObject) {
    this.name = paramObject;
  }
  
  public Object G$message() {
    return this.message;
  }
  
  public void S$message(Object paramObject) {
    this.message = paramObject;
  }
  
  public Object G$printStackTrace() {
    return this.printStackTrace;
  }
  
  public void S$printStackTrace(Object paramObject) {
    this.printStackTrace = paramObject;
  }
  
  public Object G$getStackTrace() {
    return this.getStackTrace;
  }
  
  public void S$getStackTrace(Object paramObject) {
    this.getStackTrace = paramObject;
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
    //   4: iconst_5
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'name'
    //   11: iconst_2
    //   12: ldc
    //   14: ldc
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: dup
    //   26: ldc 'message'
    //   28: iconst_2
    //   29: ldc
    //   31: ldc
    //   33: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   36: invokeinterface add : (Ljava/lang/Object;)Z
    //   41: pop
    //   42: dup
    //   43: ldc 'printStackTrace'
    //   45: iconst_2
    //   46: ldc
    //   48: ldc
    //   50: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   53: invokeinterface add : (Ljava/lang/Object;)Z
    //   58: pop
    //   59: dup
    //   60: ldc 'getStackTrace'
    //   62: iconst_2
    //   63: ldc
    //   65: ldc
    //   67: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   70: invokeinterface add : (Ljava/lang/Object;)Z
    //   75: pop
    //   76: dup
    //   77: ldc 'toString'
    //   79: iconst_2
    //   80: ldc
    //   82: ldc
    //   84: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   87: invokeinterface add : (Ljava/lang/Object;)Z
    //   92: pop
    //   93: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   96: putstatic org/openjdk/nashorn/internal/objects/NativeError$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   99: return
  }
  
  NativeError$Prototype() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic org/openjdk/nashorn/internal/objects/NativeError$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   4: invokespecial <init> : (Lorg/openjdk/nashorn/internal/runtime/PropertyMap;)V
    //   7: aload_0
    //   8: ldc 'printStackTrace'
    //   10: ldc
    //   12: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   15: dup
    //   16: ldc 'Error.prototype.printStackTrace'
    //   18: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   21: putfield printStackTrace : Ljava/lang/Object;
    //   24: aload_0
    //   25: ldc 'getStackTrace'
    //   27: ldc
    //   29: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   32: dup
    //   33: ldc 'Error.prototype.getStackTrace'
    //   35: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   38: putfield getStackTrace : Ljava/lang/Object;
    //   41: aload_0
    //   42: ldc 'toString'
    //   44: ldc
    //   46: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   49: dup
    //   50: ldc 'Error.prototype.toString'
    //   52: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   55: putfield toString : Ljava/lang/Object;
    //   58: return
  }
  
  public String getClassName() {
    return "Error";
  }
}
