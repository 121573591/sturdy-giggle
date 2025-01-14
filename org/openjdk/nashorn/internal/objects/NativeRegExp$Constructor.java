package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;

final class NativeRegExp$Constructor extends ScriptFunction {
  private static final PropertyMap $nasgenmap$;
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: bipush #15
    //   6: invokespecial <init> : (I)V
    //   9: dup
    //   10: ldc 'input'
    //   12: iconst_5
    //   13: ldc
    //   15: aconst_null
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: dup
    //   26: ldc 'multiline'
    //   28: iconst_5
    //   29: ldc
    //   31: aconst_null
    //   32: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   35: invokeinterface add : (Ljava/lang/Object;)Z
    //   40: pop
    //   41: dup
    //   42: ldc 'lastMatch'
    //   44: iconst_5
    //   45: ldc
    //   47: aconst_null
    //   48: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   51: invokeinterface add : (Ljava/lang/Object;)Z
    //   56: pop
    //   57: dup
    //   58: ldc 'lastParen'
    //   60: iconst_5
    //   61: ldc
    //   63: aconst_null
    //   64: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   67: invokeinterface add : (Ljava/lang/Object;)Z
    //   72: pop
    //   73: dup
    //   74: ldc 'leftContext'
    //   76: iconst_5
    //   77: ldc
    //   79: aconst_null
    //   80: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   83: invokeinterface add : (Ljava/lang/Object;)Z
    //   88: pop
    //   89: dup
    //   90: ldc 'rightContext'
    //   92: iconst_5
    //   93: ldc
    //   95: aconst_null
    //   96: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   99: invokeinterface add : (Ljava/lang/Object;)Z
    //   104: pop
    //   105: dup
    //   106: ldc '$1'
    //   108: iconst_5
    //   109: ldc
    //   111: aconst_null
    //   112: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   115: invokeinterface add : (Ljava/lang/Object;)Z
    //   120: pop
    //   121: dup
    //   122: ldc '$2'
    //   124: iconst_5
    //   125: ldc
    //   127: aconst_null
    //   128: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   131: invokeinterface add : (Ljava/lang/Object;)Z
    //   136: pop
    //   137: dup
    //   138: ldc '$3'
    //   140: iconst_5
    //   141: ldc
    //   143: aconst_null
    //   144: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   147: invokeinterface add : (Ljava/lang/Object;)Z
    //   152: pop
    //   153: dup
    //   154: ldc '$4'
    //   156: iconst_5
    //   157: ldc
    //   159: aconst_null
    //   160: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   163: invokeinterface add : (Ljava/lang/Object;)Z
    //   168: pop
    //   169: dup
    //   170: ldc '$5'
    //   172: iconst_5
    //   173: ldc
    //   175: aconst_null
    //   176: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   179: invokeinterface add : (Ljava/lang/Object;)Z
    //   184: pop
    //   185: dup
    //   186: ldc '$6'
    //   188: iconst_5
    //   189: ldc
    //   191: aconst_null
    //   192: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   195: invokeinterface add : (Ljava/lang/Object;)Z
    //   200: pop
    //   201: dup
    //   202: ldc '$7'
    //   204: iconst_5
    //   205: ldc
    //   207: aconst_null
    //   208: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   211: invokeinterface add : (Ljava/lang/Object;)Z
    //   216: pop
    //   217: dup
    //   218: ldc '$8'
    //   220: iconst_5
    //   221: ldc
    //   223: aconst_null
    //   224: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   227: invokeinterface add : (Ljava/lang/Object;)Z
    //   232: pop
    //   233: dup
    //   234: ldc '$9'
    //   236: iconst_5
    //   237: ldc
    //   239: aconst_null
    //   240: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   243: invokeinterface add : (Ljava/lang/Object;)Z
    //   248: pop
    //   249: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   252: putstatic org/openjdk/nashorn/internal/objects/NativeRegExp$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   255: return
  }
  
  NativeRegExp$Constructor() {
    // Byte code:
    //   0: aload_0
    //   1: ldc 'RegExp'
    //   3: ldc
    //   5: getstatic org/openjdk/nashorn/internal/objects/NativeRegExp$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   8: iconst_3
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
    //   54: invokespecial <init> : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;Lorg/openjdk/nashorn/internal/runtime/PropertyMap;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)V
    //   57: aload_0
    //   58: new org/openjdk/nashorn/internal/objects/NativeRegExp$Prototype
    //   61: dup
    //   62: invokespecial <init> : ()V
    //   65: dup
    //   66: aload_0
    //   67: invokestatic setConstructor : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   70: invokevirtual setPrototype : (Ljava/lang/Object;)V
    //   73: aload_0
    //   74: iconst_2
    //   75: invokevirtual setArity : (I)V
    //   78: aload_0
    //   79: ldc 'RegExp'
    //   81: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   84: return
  }
}
