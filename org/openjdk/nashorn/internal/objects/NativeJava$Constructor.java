package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;

final class NativeJava$Constructor extends ScriptObject {
  private Object isType;
  
  private Object synchronizedFunc;
  
  private Object isJavaMethod;
  
  private Object isJavaFunction;
  
  private Object isJavaObject;
  
  private Object isScriptObject;
  
  private Object isScriptFunction;
  
  private Object type;
  
  private Object typeName;
  
  private Object to;
  
  private Object from;
  
  private Object extend;
  
  private Object _super;
  
  private Object asJSONCompatible;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$isType() {
    return this.isType;
  }
  
  public void S$isType(Object paramObject) {
    this.isType = paramObject;
  }
  
  public Object G$synchronizedFunc() {
    return this.synchronizedFunc;
  }
  
  public void S$synchronizedFunc(Object paramObject) {
    this.synchronizedFunc = paramObject;
  }
  
  public Object G$isJavaMethod() {
    return this.isJavaMethod;
  }
  
  public void S$isJavaMethod(Object paramObject) {
    this.isJavaMethod = paramObject;
  }
  
  public Object G$isJavaFunction() {
    return this.isJavaFunction;
  }
  
  public void S$isJavaFunction(Object paramObject) {
    this.isJavaFunction = paramObject;
  }
  
  public Object G$isJavaObject() {
    return this.isJavaObject;
  }
  
  public void S$isJavaObject(Object paramObject) {
    this.isJavaObject = paramObject;
  }
  
  public Object G$isScriptObject() {
    return this.isScriptObject;
  }
  
  public void S$isScriptObject(Object paramObject) {
    this.isScriptObject = paramObject;
  }
  
  public Object G$isScriptFunction() {
    return this.isScriptFunction;
  }
  
  public void S$isScriptFunction(Object paramObject) {
    this.isScriptFunction = paramObject;
  }
  
  public Object G$type() {
    return this.type;
  }
  
  public void S$type(Object paramObject) {
    this.type = paramObject;
  }
  
  public Object G$typeName() {
    return this.typeName;
  }
  
  public void S$typeName(Object paramObject) {
    this.typeName = paramObject;
  }
  
  public Object G$to() {
    return this.to;
  }
  
  public void S$to(Object paramObject) {
    this.to = paramObject;
  }
  
  public Object G$from() {
    return this.from;
  }
  
  public void S$from(Object paramObject) {
    this.from = paramObject;
  }
  
  public Object G$extend() {
    return this.extend;
  }
  
  public void S$extend(Object paramObject) {
    this.extend = paramObject;
  }
  
  public Object G$_super() {
    return this._super;
  }
  
  public void S$_super(Object paramObject) {
    this._super = paramObject;
  }
  
  public Object G$asJSONCompatible() {
    return this.asJSONCompatible;
  }
  
  public void S$asJSONCompatible(Object paramObject) {
    this.asJSONCompatible = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: bipush #14
    //   6: invokespecial <init> : (I)V
    //   9: dup
    //   10: ldc 'isType'
    //   12: iconst_2
    //   13: ldc
    //   15: ldc
    //   17: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   20: invokeinterface add : (Ljava/lang/Object;)Z
    //   25: pop
    //   26: dup
    //   27: ldc 'synchronized'
    //   29: iconst_2
    //   30: ldc
    //   32: ldc
    //   34: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   37: invokeinterface add : (Ljava/lang/Object;)Z
    //   42: pop
    //   43: dup
    //   44: ldc 'isJavaMethod'
    //   46: iconst_2
    //   47: ldc
    //   49: ldc
    //   51: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   54: invokeinterface add : (Ljava/lang/Object;)Z
    //   59: pop
    //   60: dup
    //   61: ldc 'isJavaFunction'
    //   63: iconst_2
    //   64: ldc
    //   66: ldc
    //   68: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   71: invokeinterface add : (Ljava/lang/Object;)Z
    //   76: pop
    //   77: dup
    //   78: ldc 'isJavaObject'
    //   80: iconst_2
    //   81: ldc
    //   83: ldc
    //   85: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   88: invokeinterface add : (Ljava/lang/Object;)Z
    //   93: pop
    //   94: dup
    //   95: ldc 'isScriptObject'
    //   97: iconst_2
    //   98: ldc
    //   100: ldc
    //   102: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   105: invokeinterface add : (Ljava/lang/Object;)Z
    //   110: pop
    //   111: dup
    //   112: ldc 'isScriptFunction'
    //   114: iconst_2
    //   115: ldc
    //   117: ldc
    //   119: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   122: invokeinterface add : (Ljava/lang/Object;)Z
    //   127: pop
    //   128: dup
    //   129: ldc 'type'
    //   131: iconst_2
    //   132: ldc
    //   134: ldc
    //   136: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   139: invokeinterface add : (Ljava/lang/Object;)Z
    //   144: pop
    //   145: dup
    //   146: ldc 'typeName'
    //   148: iconst_2
    //   149: ldc
    //   151: ldc
    //   153: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   156: invokeinterface add : (Ljava/lang/Object;)Z
    //   161: pop
    //   162: dup
    //   163: ldc 'to'
    //   165: iconst_2
    //   166: ldc
    //   168: ldc
    //   170: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   173: invokeinterface add : (Ljava/lang/Object;)Z
    //   178: pop
    //   179: dup
    //   180: ldc 'from'
    //   182: iconst_2
    //   183: ldc
    //   185: ldc
    //   187: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   190: invokeinterface add : (Ljava/lang/Object;)Z
    //   195: pop
    //   196: dup
    //   197: ldc 'extend'
    //   199: iconst_2
    //   200: ldc
    //   202: ldc
    //   204: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   207: invokeinterface add : (Ljava/lang/Object;)Z
    //   212: pop
    //   213: dup
    //   214: ldc 'super'
    //   216: iconst_2
    //   217: ldc
    //   219: ldc
    //   221: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   224: invokeinterface add : (Ljava/lang/Object;)Z
    //   229: pop
    //   230: dup
    //   231: ldc 'asJSONCompatible'
    //   233: iconst_2
    //   234: ldc
    //   236: ldc
    //   238: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   241: invokeinterface add : (Ljava/lang/Object;)Z
    //   246: pop
    //   247: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   250: putstatic org/openjdk/nashorn/internal/objects/NativeJava$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   253: return
  }
  
  NativeJava$Constructor() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic org/openjdk/nashorn/internal/objects/NativeJava$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   4: invokespecial <init> : (Lorg/openjdk/nashorn/internal/runtime/PropertyMap;)V
    //   7: aload_0
    //   8: ldc 'isType'
    //   10: ldc
    //   12: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   15: dup
    //   16: ldc 'Java.isType'
    //   18: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   21: putfield isType : Ljava/lang/Object;
    //   24: aload_0
    //   25: ldc 'synchronized'
    //   27: ldc
    //   29: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   32: dup
    //   33: ldc 'Java.synchronized'
    //   35: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   38: putfield synchronizedFunc : Ljava/lang/Object;
    //   41: aload_0
    //   42: ldc 'isJavaMethod'
    //   44: ldc
    //   46: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   49: dup
    //   50: ldc 'Java.isJavaMethod'
    //   52: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   55: putfield isJavaMethod : Ljava/lang/Object;
    //   58: aload_0
    //   59: ldc 'isJavaFunction'
    //   61: ldc
    //   63: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   66: dup
    //   67: ldc 'Java.isJavaFunction'
    //   69: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   72: putfield isJavaFunction : Ljava/lang/Object;
    //   75: aload_0
    //   76: ldc 'isJavaObject'
    //   78: ldc
    //   80: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   83: dup
    //   84: ldc 'Java.isJavaObject'
    //   86: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   89: putfield isJavaObject : Ljava/lang/Object;
    //   92: aload_0
    //   93: ldc 'isScriptObject'
    //   95: ldc
    //   97: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   100: dup
    //   101: ldc 'Java.isScriptObject'
    //   103: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   106: putfield isScriptObject : Ljava/lang/Object;
    //   109: aload_0
    //   110: ldc 'isScriptFunction'
    //   112: ldc_w
    //   115: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   118: dup
    //   119: ldc_w 'Java.isScriptFunction'
    //   122: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   125: putfield isScriptFunction : Ljava/lang/Object;
    //   128: aload_0
    //   129: ldc 'type'
    //   131: ldc_w
    //   134: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   137: dup
    //   138: ldc_w 'Java.type'
    //   141: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   144: putfield type : Ljava/lang/Object;
    //   147: aload_0
    //   148: ldc 'typeName'
    //   150: ldc_w
    //   153: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   156: dup
    //   157: ldc_w 'Java.typeName'
    //   160: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   163: putfield typeName : Ljava/lang/Object;
    //   166: aload_0
    //   167: ldc 'to'
    //   169: ldc_w
    //   172: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   175: dup
    //   176: ldc_w 'Java.to'
    //   179: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   182: putfield to : Ljava/lang/Object;
    //   185: aload_0
    //   186: ldc 'from'
    //   188: ldc_w
    //   191: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   194: dup
    //   195: ldc_w 'Java.from'
    //   198: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   201: putfield from : Ljava/lang/Object;
    //   204: aload_0
    //   205: ldc 'extend'
    //   207: ldc_w
    //   210: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   213: dup
    //   214: ldc_w 'Java.extend'
    //   217: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   220: putfield extend : Ljava/lang/Object;
    //   223: aload_0
    //   224: ldc 'super'
    //   226: ldc_w
    //   229: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   232: dup
    //   233: ldc_w 'Java.super'
    //   236: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   239: putfield _super : Ljava/lang/Object;
    //   242: aload_0
    //   243: ldc 'asJSONCompatible'
    //   245: ldc_w
    //   248: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   251: dup
    //   252: ldc_w 'Java.asJSONCompatible'
    //   255: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   258: putfield asJSONCompatible : Ljava/lang/Object;
    //   261: return
  }
  
  public String getClassName() {
    return "Java";
  }
}
