package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.PrototypeObject;

final class NativeSet$Prototype extends PrototypeObject {
  private Object add;
  
  private Object has;
  
  private Object clear;
  
  private Object delete;
  
  private Object entries;
  
  private Object keys;
  
  private Object values;
  
  private Object getIterator;
  
  private Object forEach;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$add() {
    return this.add;
  }
  
  public void S$add(Object paramObject) {
    this.add = paramObject;
  }
  
  public Object G$has() {
    return this.has;
  }
  
  public void S$has(Object paramObject) {
    this.has = paramObject;
  }
  
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
    //   4: bipush #10
    //   6: invokespecial <init> : (I)V
    //   9: dup
    //   10: ldc 'add'
    //   12: iconst_2
    //   13: ldc
    //   15: ldc
    //   17: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   20: invokeinterface add : (Ljava/lang/Object;)Z
    //   25: pop
    //   26: dup
    //   27: ldc 'has'
    //   29: iconst_2
    //   30: ldc
    //   32: ldc
    //   34: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   37: invokeinterface add : (Ljava/lang/Object;)Z
    //   42: pop
    //   43: dup
    //   44: ldc 'clear'
    //   46: iconst_2
    //   47: ldc
    //   49: ldc
    //   51: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   54: invokeinterface add : (Ljava/lang/Object;)Z
    //   59: pop
    //   60: dup
    //   61: ldc 'delete'
    //   63: iconst_2
    //   64: ldc
    //   66: ldc
    //   68: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   71: invokeinterface add : (Ljava/lang/Object;)Z
    //   76: pop
    //   77: dup
    //   78: ldc 'size'
    //   80: sipush #4098
    //   83: ldc
    //   85: aconst_null
    //   86: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   89: invokeinterface add : (Ljava/lang/Object;)Z
    //   94: pop
    //   95: dup
    //   96: ldc 'entries'
    //   98: iconst_2
    //   99: ldc
    //   101: ldc
    //   103: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   106: invokeinterface add : (Ljava/lang/Object;)Z
    //   111: pop
    //   112: dup
    //   113: ldc 'keys'
    //   115: iconst_2
    //   116: ldc
    //   118: ldc
    //   120: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   123: invokeinterface add : (Ljava/lang/Object;)Z
    //   128: pop
    //   129: dup
    //   130: ldc 'values'
    //   132: iconst_2
    //   133: ldc
    //   135: ldc
    //   137: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   140: invokeinterface add : (Ljava/lang/Object;)Z
    //   145: pop
    //   146: dup
    //   147: getstatic org/openjdk/nashorn/internal/objects/NativeSymbol.iterator : Lorg/openjdk/nashorn/internal/runtime/Symbol;
    //   150: iconst_2
    //   151: ldc
    //   153: ldc
    //   155: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   158: invokeinterface add : (Ljava/lang/Object;)Z
    //   163: pop
    //   164: dup
    //   165: ldc 'forEach'
    //   167: iconst_2
    //   168: ldc
    //   170: ldc
    //   172: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   175: invokeinterface add : (Ljava/lang/Object;)Z
    //   180: pop
    //   181: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   184: putstatic org/openjdk/nashorn/internal/objects/NativeSet$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   187: return
  }
  
  NativeSet$Prototype() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic org/openjdk/nashorn/internal/objects/NativeSet$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   4: invokespecial <init> : (Lorg/openjdk/nashorn/internal/runtime/PropertyMap;)V
    //   7: aload_0
    //   8: ldc 'add'
    //   10: ldc
    //   12: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   15: dup
    //   16: ldc 'Set.prototype.add'
    //   18: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   21: putfield add : Ljava/lang/Object;
    //   24: aload_0
    //   25: ldc 'has'
    //   27: ldc
    //   29: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   32: dup
    //   33: ldc 'Set.prototype.has'
    //   35: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   38: putfield has : Ljava/lang/Object;
    //   41: aload_0
    //   42: ldc 'clear'
    //   44: ldc
    //   46: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   49: dup
    //   50: ldc 'Set.prototype.clear'
    //   52: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   55: putfield clear : Ljava/lang/Object;
    //   58: aload_0
    //   59: ldc 'delete'
    //   61: ldc
    //   63: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   66: dup
    //   67: ldc 'Set.prototype.delete'
    //   69: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   72: putfield delete : Ljava/lang/Object;
    //   75: aload_0
    //   76: ldc 'entries'
    //   78: ldc
    //   80: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   83: dup
    //   84: ldc 'Set.prototype.entries'
    //   86: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   89: putfield entries : Ljava/lang/Object;
    //   92: aload_0
    //   93: ldc 'keys'
    //   95: ldc
    //   97: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   100: dup
    //   101: ldc 'Set.prototype.keys'
    //   103: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   106: putfield keys : Ljava/lang/Object;
    //   109: aload_0
    //   110: ldc 'values'
    //   112: ldc
    //   114: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   117: dup
    //   118: ldc 'Set.prototype.values'
    //   120: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   123: putfield values : Ljava/lang/Object;
    //   126: aload_0
    //   127: ldc 'Symbol[iterator]'
    //   129: ldc
    //   131: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   134: dup
    //   135: ldc 'Set.prototype.@@iterator'
    //   137: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   140: putfield getIterator : Ljava/lang/Object;
    //   143: aload_0
    //   144: ldc 'forEach'
    //   146: ldc
    //   148: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   151: dup
    //   152: iconst_1
    //   153: invokevirtual setArity : (I)V
    //   156: dup
    //   157: ldc 'Set.prototype.forEach'
    //   159: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   162: putfield forEach : Ljava/lang/Object;
    //   165: return
  }
  
  public String getClassName() {
    return "Set";
  }
}
