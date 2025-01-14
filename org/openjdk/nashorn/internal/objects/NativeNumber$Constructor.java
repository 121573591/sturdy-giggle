package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;

final class NativeNumber$Constructor extends ScriptFunction {
  private static final PropertyMap $nasgenmap$;
  
  public double G$MAX_VALUE() {
    return NativeNumber.MAX_VALUE;
  }
  
  public double G$MIN_VALUE() {
    return NativeNumber.MIN_VALUE;
  }
  
  public double G$NaN() {
    return NativeNumber.NaN;
  }
  
  public double G$NEGATIVE_INFINITY() {
    return NativeNumber.NEGATIVE_INFINITY;
  }
  
  public double G$POSITIVE_INFINITY() {
    return NativeNumber.POSITIVE_INFINITY;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_5
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'MAX_VALUE'
    //   11: bipush #7
    //   13: ldc
    //   15: aconst_null
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: dup
    //   26: ldc 'MIN_VALUE'
    //   28: bipush #7
    //   30: ldc
    //   32: aconst_null
    //   33: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   36: invokeinterface add : (Ljava/lang/Object;)Z
    //   41: pop
    //   42: dup
    //   43: ldc 'NaN'
    //   45: bipush #7
    //   47: ldc
    //   49: aconst_null
    //   50: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   53: invokeinterface add : (Ljava/lang/Object;)Z
    //   58: pop
    //   59: dup
    //   60: ldc 'NEGATIVE_INFINITY'
    //   62: bipush #7
    //   64: ldc
    //   66: aconst_null
    //   67: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   70: invokeinterface add : (Ljava/lang/Object;)Z
    //   75: pop
    //   76: dup
    //   77: ldc 'POSITIVE_INFINITY'
    //   79: bipush #7
    //   81: ldc
    //   83: aconst_null
    //   84: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   87: invokeinterface add : (Ljava/lang/Object;)Z
    //   92: pop
    //   93: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   96: putstatic org/openjdk/nashorn/internal/objects/NativeNumber$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   99: return
  }
  
  NativeNumber$Constructor() {
    // Byte code:
    //   0: aload_0
    //   1: ldc 'Number'
    //   3: ldc
    //   5: getstatic org/openjdk/nashorn/internal/objects/NativeNumber$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   8: aconst_null
    //   9: invokespecial <init> : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;Lorg/openjdk/nashorn/internal/runtime/PropertyMap;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)V
    //   12: aload_0
    //   13: new org/openjdk/nashorn/internal/objects/NativeNumber$Prototype
    //   16: dup
    //   17: invokespecial <init> : ()V
    //   20: dup
    //   21: aload_0
    //   22: invokestatic setConstructor : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   25: invokevirtual setPrototype : (Ljava/lang/Object;)V
    //   28: aload_0
    //   29: iconst_1
    //   30: invokevirtual setArity : (I)V
    //   33: aload_0
    //   34: ldc 'Number'
    //   36: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   39: return
  }
}
