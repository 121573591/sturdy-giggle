package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.PrototypeObject;

final class NativeMap$Prototype extends PrototypeObject {
  private Object clear;
  
  private Object delete;
  
  private Object has;
  
  private Object set;
  
  private Object get;
  
  private Object entries;
  
  private Object keys;
  
  private Object values;
  
  private Object getIterator;
  
  private Object forEach;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$clear() {
    return this.clear;
  }
  
  public void S$clear(Object paramObject) {
    this.clear = paramObject;
  }
  
  public Object G$delete() {
    return this.delete;
  }
  
  public void S$delete(Object paramObject) {
    this.delete = paramObject;
  }
  
  public Object G$has() {
    return this.has;
  }
  
  public void S$has(Object paramObject) {
    this.has = paramObject;
  }
  
  public Object G$set() {
    return this.set;
  }
  
  public void S$set(Object paramObject) {
    this.set = paramObject;
  }
  
  public Object G$get() {
    return this.get;
  }
  
  public void S$get(Object paramObject) {
    this.get = paramObject;
  }
  
  public Object G$entries() {
    return this.entries;
  }
  
  public void S$entries(Object paramObject) {
    this.entries = paramObject;
  }
  
  public Object G$keys() {
    return this.keys;
  }
  
  public void S$keys(Object paramObject) {
    this.keys = paramObject;
  }
  
  public Object G$values() {
    return this.values;
  }
  
  public void S$values(Object paramObject) {
    this.values = paramObject;
  }
  
  public Object G$getIterator() {
    return this.getIterator;
  }
  
  public void S$getIterator(Object paramObject) {
    this.getIterator = paramObject;
  }
  
  public Object G$forEach() {
    return this.forEach;
  }
  
  public void S$forEach(Object paramObject) {
    this.forEach = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: bipush #11
    //   6: invokespecial <init> : (I)V
    //   9: dup
    //   10: ldc 'clear'
    //   12: iconst_2
    //   13: ldc
    //   15: ldc
    //   17: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   20: invokeinterface add : (Ljava/lang/Object;)Z
    //   25: pop
    //   26: dup
    //   27: ldc 'delete'
    //   29: iconst_2
    //   30: ldc
    //   32: ldc
    //   34: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   37: invokeinterface add : (Ljava/lang/Object;)Z
    //   42: pop
    //   43: dup
    //   44: ldc 'has'
    //   46: iconst_2
    //   47: ldc
    //   49: ldc
    //   51: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   54: invokeinterface add : (Ljava/lang/Object;)Z
    //   59: pop
    //   60: dup
    //   61: ldc 'set'
    //   63: iconst_2
    //   64: ldc
    //   66: ldc
    //   68: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   71: invokeinterface add : (Ljava/lang/Object;)Z
    //   76: pop
    //   77: dup
    //   78: ldc 'get'
    //   80: iconst_2
    //   81: ldc
    //   83: ldc
    //   85: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   88: invokeinterface add : (Ljava/lang/Object;)Z
    //   93: pop
    //   94: dup
    //   95: ldc 'size'
    //   97: sipush #4098
    //   100: ldc
    //   102: aconst_null
    //   103: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   106: invokeinterface add : (Ljava/lang/Object;)Z
    //   111: pop
    //   112: dup
    //   113: ldc 'entries'
    //   115: iconst_2
    //   116: ldc
    //   118: ldc
    //   120: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   123: invokeinterface add : (Ljava/lang/Object;)Z
    //   128: pop
    //   129: dup
    //   130: ldc 'keys'
    //   132: iconst_2
    //   133: ldc
    //   135: ldc
    //   137: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   140: invokeinterface add : (Ljava/lang/Object;)Z
    //   145: pop
    //   146: dup
    //   147: ldc 'values'
    //   149: iconst_2
    //   150: ldc
    //   152: ldc
    //   154: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   157: invokeinterface add : (Ljava/lang/Object;)Z
    //   162: pop
    //   163: dup
    //   164: getstatic org/openjdk/nashorn/internal/objects/NativeSymbol.iterator : Lorg/openjdk/nashorn/internal/runtime/Symbol;
    //   167: iconst_2
    //   168: ldc
    //   170: ldc
    //   172: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   175: invokeinterface add : (Ljava/lang/Object;)Z
    //   180: pop
    //   181: dup
    //   182: ldc 'forEach'
    //   184: iconst_2
    //   185: ldc
    //   187: ldc
    //   189: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   192: invokeinterface add : (Ljava/lang/Object;)Z
    //   197: pop
    //   198: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   201: putstatic org/openjdk/nashorn/internal/objects/NativeMap$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   204: return
  }
  
  NativeMap$Prototype() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic org/openjdk/nashorn/internal/objects/NativeMap$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   4: invokespecial <init> : (Lorg/openjdk/nashorn/internal/runtime/PropertyMap;)V
    //   7: aload_0
    //   8: ldc 'clear'
    //   10: ldc
    //   12: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   15: dup
    //   16: ldc 'Map.prototype.clear'
    //   18: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   21: putfield clear : Ljava/lang/Object;
    //   24: aload_0
    //   25: ldc 'delete'
    //   27: ldc
    //   29: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   32: dup
    //   33: ldc 'Map.prototype.delete'
    //   35: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   38: putfield delete : Ljava/lang/Object;
    //   41: aload_0
    //   42: ldc 'has'
    //   44: ldc
    //   46: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   49: dup
    //   50: ldc 'Map.prototype.has'
    //   52: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   55: putfield has : Ljava/lang/Object;
    //   58: aload_0
    //   59: ldc 'set'
    //   61: ldc
    //   63: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   66: dup
    //   67: ldc 'Map.prototype.set'
    //   69: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   72: putfield set : Ljava/lang/Object;
    //   75: aload_0
    //   76: ldc 'get'
    //   78: ldc
    //   80: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   83: dup
    //   84: ldc 'Map.prototype.get'
    //   86: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   89: putfield get : Ljava/lang/Object;
    //   92: aload_0
    //   93: ldc 'entries'
    //   95: ldc
    //   97: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   100: dup
    //   101: ldc 'Map.prototype.entries'
    //   103: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   106: putfield entries : Ljava/lang/Object;
    //   109: aload_0
    //   110: ldc 'keys'
    //   112: ldc
    //   114: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   117: dup
    //   118: ldc 'Map.prototype.keys'
    //   120: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   123: putfield keys : Ljava/lang/Object;
    //   126: aload_0
    //   127: ldc 'values'
    //   129: ldc
    //   131: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   134: dup
    //   135: ldc 'Map.prototype.values'
    //   137: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   140: putfield values : Ljava/lang/Object;
    //   143: aload_0
    //   144: ldc 'Symbol[iterator]'
    //   146: ldc
    //   148: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   151: dup
    //   152: ldc 'Map.prototype.@@iterator'
    //   154: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   157: putfield getIterator : Ljava/lang/Object;
    //   160: aload_0
    //   161: ldc 'forEach'
    //   163: ldc
    //   165: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   168: dup
    //   169: iconst_1
    //   170: invokevirtual setArity : (I)V
    //   173: dup
    //   174: ldc 'Map.prototype.forEach'
    //   176: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   179: putfield forEach : Ljava/lang/Object;
    //   182: return
  }
  
  public String getClassName() {
    return "Map";
  }
}
