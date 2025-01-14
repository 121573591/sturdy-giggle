package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.ScriptFunction;

final class NativeWeakSet$Constructor extends ScriptFunction {
  NativeWeakSet$Constructor() {
    // Byte code:
    //   0: aload_0
    //   1: ldc 'WeakSet'
    //   3: ldc
    //   5: aconst_null
    //   6: invokespecial <init> : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)V
    //   9: aload_0
    //   10: new org/openjdk/nashorn/internal/objects/NativeWeakSet$Prototype
    //   13: dup
    //   14: invokespecial <init> : ()V
    //   17: dup
    //   18: aload_0
    //   19: invokestatic setConstructor : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   22: invokevirtual setPrototype : (Ljava/lang/Object;)V
    //   25: aload_0
    //   26: iconst_0
    //   27: invokevirtual setArity : (I)V
    //   30: aload_0
    //   31: ldc 'WeakSet'
    //   33: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   36: return
  }
}
