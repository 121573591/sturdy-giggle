package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;

final class NativeJSON$Constructor extends ScriptObject {
  private Object parse;
  
  private Object stringify;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$parse() {
    return this.parse;
  }
  
  public void S$parse(Object paramObject) {
    this.parse = paramObject;
  }
  
  public Object G$stringify() {
    return this.stringify;
  }
  
  public void S$stringify(Object paramObject) {
    this.stringify = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_2
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'parse'
    //   11: iconst_2
    //   12: ldc
    //   14: ldc
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: dup
    //   26: ldc 'stringify'
    //   28: iconst_2
    //   29: ldc
    //   31: ldc
    //   33: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   36: invokeinterface add : (Ljava/lang/Object;)Z
    //   41: pop
    //   42: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   45: putstatic org/openjdk/nashorn/internal/objects/NativeJSON$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   48: return
  }
  
  NativeJSON$Constructor() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic org/openjdk/nashorn/internal/objects/NativeJSON$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   4: invokespecial <init> : (Lorg/openjdk/nashorn/internal/runtime/PropertyMap;)V
    //   7: aload_0
    //   8: ldc 'parse'
    //   10: ldc
    //   12: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   15: dup
    //   16: ldc 'JSON.parse'
    //   18: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   21: putfield parse : Ljava/lang/Object;
    //   24: aload_0
    //   25: ldc 'stringify'
    //   27: ldc
    //   29: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   32: dup
    //   33: ldc 'JSON.stringify'
    //   35: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   38: putfield stringify : Ljava/lang/Object;
    //   41: return
  }
  
  public String getClassName() {
    return "JSON";
  }
}
