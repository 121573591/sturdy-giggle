package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;

final class NativeString$Constructor extends ScriptFunction {
  private Object fromCharCode;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$fromCharCode() {
    return this.fromCharCode;
  }
  
  public void S$fromCharCode(Object paramObject) {
    this.fromCharCode = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_1
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'fromCharCode'
    //   11: iconst_2
    //   12: ldc
    //   14: ldc
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   28: putstatic org/openjdk/nashorn/internal/objects/NativeString$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   31: return
  }
  
  NativeString$Constructor() {
    // Byte code:
    //   0: aload_0
    //   1: ldc 'String'
    //   3: ldc
    //   5: getstatic org/openjdk/nashorn/internal/objects/NativeString$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   8: iconst_5
    //   9: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   12: dup
    //   13: iconst_0
    //   14: new org/openjdk/nashorn/internal/runtime/Specialization
    //   17: dup
    //   18: ldc
    //   20: iconst_0
    //   21: iconst_0
    //   22: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   25: aastore
    //   26: dup
    //   27: iconst_1
    //   28: new org/openjdk/nashorn/internal/runtime/Specialization
    //   31: dup
    //   32: ldc
    //   34: iconst_0
    //   35: iconst_0
    //   36: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   39: aastore
    //   40: dup
    //   41: iconst_2
    //   42: new org/openjdk/nashorn/internal/runtime/Specialization
    //   45: dup
    //   46: ldc
    //   48: iconst_0
    //   49: iconst_0
    //   50: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   53: aastore
    //   54: dup
    //   55: iconst_3
    //   56: new org/openjdk/nashorn/internal/runtime/Specialization
    //   59: dup
    //   60: ldc
    //   62: iconst_0
    //   63: iconst_0
    //   64: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   67: aastore
    //   68: dup
    //   69: iconst_4
    //   70: new org/openjdk/nashorn/internal/runtime/Specialization
    //   73: dup
    //   74: ldc
    //   76: iconst_0
    //   77: iconst_0
    //   78: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   81: aastore
    //   82: invokespecial <init> : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;Lorg/openjdk/nashorn/internal/runtime/PropertyMap;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)V
    //   85: aload_0
    //   86: ldc 'fromCharCode'
    //   88: ldc
    //   90: bipush #6
    //   92: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   95: dup
    //   96: iconst_0
    //   97: new org/openjdk/nashorn/internal/runtime/Specialization
    //   100: dup
    //   101: ldc
    //   103: iconst_0
    //   104: iconst_0
    //   105: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   108: aastore
    //   109: dup
    //   110: iconst_1
    //   111: new org/openjdk/nashorn/internal/runtime/Specialization
    //   114: dup
    //   115: ldc
    //   117: iconst_0
    //   118: iconst_0
    //   119: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   122: aastore
    //   123: dup
    //   124: iconst_2
    //   125: new org/openjdk/nashorn/internal/runtime/Specialization
    //   128: dup
    //   129: ldc
    //   131: iconst_0
    //   132: iconst_0
    //   133: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   136: aastore
    //   137: dup
    //   138: iconst_3
    //   139: new org/openjdk/nashorn/internal/runtime/Specialization
    //   142: dup
    //   143: ldc
    //   145: iconst_0
    //   146: iconst_0
    //   147: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   150: aastore
    //   151: dup
    //   152: iconst_4
    //   153: new org/openjdk/nashorn/internal/runtime/Specialization
    //   156: dup
    //   157: ldc
    //   159: iconst_0
    //   160: iconst_0
    //   161: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   164: aastore
    //   165: dup
    //   166: iconst_5
    //   167: new org/openjdk/nashorn/internal/runtime/Specialization
    //   170: dup
    //   171: ldc
    //   173: iconst_0
    //   174: iconst_0
    //   175: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   178: aastore
    //   179: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   182: dup
    //   183: iconst_1
    //   184: invokevirtual setArity : (I)V
    //   187: dup
    //   188: ldc 'String.fromCharCode'
    //   190: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   193: putfield fromCharCode : Ljava/lang/Object;
    //   196: aload_0
    //   197: new org/openjdk/nashorn/internal/objects/NativeString$Prototype
    //   200: dup
    //   201: invokespecial <init> : ()V
    //   204: dup
    //   205: aload_0
    //   206: invokestatic setConstructor : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   209: invokevirtual setPrototype : (Ljava/lang/Object;)V
    //   212: aload_0
    //   213: iconst_1
    //   214: invokevirtual setArity : (I)V
    //   217: aload_0
    //   218: ldc 'String'
    //   220: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   223: return
  }
}
