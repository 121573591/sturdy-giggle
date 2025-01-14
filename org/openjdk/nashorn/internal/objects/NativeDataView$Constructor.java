package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.ScriptFunction;

final class NativeDataView$Constructor extends ScriptFunction {
  NativeDataView$Constructor() {
    // Byte code:
    //   0: aload_0
    //   1: ldc 'DataView'
    //   3: ldc
    //   5: iconst_2
    //   6: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   9: dup
    //   10: iconst_0
    //   11: new org/openjdk/nashorn/internal/runtime/Specialization
    //   14: dup
    //   15: ldc
    //   17: iconst_0
    //   18: iconst_0
    //   19: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   22: aastore
    //   23: dup
    //   24: iconst_1
    //   25: new org/openjdk/nashorn/internal/runtime/Specialization
    //   28: dup
    //   29: ldc
    //   31: iconst_0
    //   32: iconst_0
    //   33: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   36: aastore
    //   37: invokespecial <init> : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)V
    //   40: aload_0
    //   41: new org/openjdk/nashorn/internal/objects/NativeDataView$Prototype
    //   44: dup
    //   45: invokespecial <init> : ()V
    //   48: dup
    //   49: aload_0
    //   50: invokestatic setConstructor : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   53: invokevirtual setPrototype : (Ljava/lang/Object;)V
    //   56: aload_0
    //   57: iconst_1
    //   58: invokevirtual setArity : (I)V
    //   61: aload_0
    //   62: ldc 'DataView'
    //   64: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   67: return
  }
}
