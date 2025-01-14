package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;

final class NativeError$Constructor extends ScriptFunction {
  private Object captureStackTrace;
  
  private Object dumpStack;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$captureStackTrace() {
    return this.captureStackTrace;
  }
  
  public void S$captureStackTrace(Object paramObject) {
    this.captureStackTrace = paramObject;
  }
  
  public Object G$dumpStack() {
    return this.dumpStack;
  }
  
  public void S$dumpStack(Object paramObject) {
    this.dumpStack = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_2
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'captureStackTrace'
    //   11: iconst_2
    //   12: ldc
    //   14: ldc
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: dup
    //   26: ldc 'dumpStack'
    //   28: iconst_2
    //   29: ldc
    //   31: ldc
    //   33: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   36: invokeinterface add : (Ljava/lang/Object;)Z
    //   41: pop
    //   42: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   45: putstatic org/openjdk/nashorn/internal/objects/NativeError$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   48: return
  }
  
  NativeError$Constructor() {
    // Byte code:
    //   0: aload_0
    //   1: ldc 'Error'
    //   3: ldc
    //   5: getstatic org/openjdk/nashorn/internal/objects/NativeError$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   8: aconst_null
    //   9: invokespecial <init> : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;Lorg/openjdk/nashorn/internal/runtime/PropertyMap;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)V
    //   12: aload_0
    //   13: ldc 'captureStackTrace'
    //   15: ldc
    //   17: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   20: dup
    //   21: ldc 'Error.captureStackTrace'
    //   23: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   26: putfield captureStackTrace : Ljava/lang/Object;
    //   29: aload_0
    //   30: ldc 'dumpStack'
    //   32: ldc
    //   34: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   37: dup
    //   38: ldc 'Error.dumpStack'
    //   40: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   43: putfield dumpStack : Ljava/lang/Object;
    //   46: aload_0
    //   47: new org/openjdk/nashorn/internal/objects/NativeError$Prototype
    //   50: dup
    //   51: invokespecial <init> : ()V
    //   54: dup
    //   55: aload_0
    //   56: invokestatic setConstructor : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   59: invokevirtual setPrototype : (Ljava/lang/Object;)V
    //   62: aload_0
    //   63: ldc 'Error'
    //   65: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   68: return
  }
}
