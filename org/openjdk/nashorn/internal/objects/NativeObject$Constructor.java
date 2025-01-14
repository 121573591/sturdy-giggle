package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;

final class NativeObject$Constructor extends ScriptFunction {
  private Object setIndexedPropertiesToExternalArrayData;
  
  private Object getPrototypeOf;
  
  private Object setPrototypeOf;
  
  private Object getOwnPropertyDescriptor;
  
  private Object getOwnPropertyNames;
  
  private Object getOwnPropertySymbols;
  
  private Object create;
  
  private Object defineProperty;
  
  private Object defineProperties;
  
  private Object seal;
  
  private Object freeze;
  
  private Object preventExtensions;
  
  private Object isSealed;
  
  private Object isFrozen;
  
  private Object isExtensible;
  
  private Object keys;
  
  private Object bindProperties;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$setIndexedPropertiesToExternalArrayData() {
    return this.setIndexedPropertiesToExternalArrayData;
  }
  
  public void S$setIndexedPropertiesToExternalArrayData(Object paramObject) {
    this.setIndexedPropertiesToExternalArrayData = paramObject;
  }
  
  public Object G$getPrototypeOf() {
    return this.getPrototypeOf;
  }
  
  public void S$getPrototypeOf(Object paramObject) {
    this.getPrototypeOf = paramObject;
  }
  
  public Object G$setPrototypeOf() {
    return this.setPrototypeOf;
  }
  
  public void S$setPrototypeOf(Object paramObject) {
    this.setPrototypeOf = paramObject;
  }
  
  public Object G$getOwnPropertyDescriptor() {
    return this.getOwnPropertyDescriptor;
  }
  
  public void S$getOwnPropertyDescriptor(Object paramObject) {
    this.getOwnPropertyDescriptor = paramObject;
  }
  
  public Object G$getOwnPropertyNames() {
    return this.getOwnPropertyNames;
  }
  
  public void S$getOwnPropertyNames(Object paramObject) {
    this.getOwnPropertyNames = paramObject;
  }
  
  public Object G$getOwnPropertySymbols() {
    return this.getOwnPropertySymbols;
  }
  
  public void S$getOwnPropertySymbols(Object paramObject) {
    this.getOwnPropertySymbols = paramObject;
  }
  
  public Object G$create() {
    return this.create;
  }
  
  public void S$create(Object paramObject) {
    this.create = paramObject;
  }
  
  public Object G$defineProperty() {
    return this.defineProperty;
  }
  
  public void S$defineProperty(Object paramObject) {
    this.defineProperty = paramObject;
  }
  
  public Object G$defineProperties() {
    return this.defineProperties;
  }
  
  public void S$defineProperties(Object paramObject) {
    this.defineProperties = paramObject;
  }
  
  public Object G$seal() {
    return this.seal;
  }
  
  public void S$seal(Object paramObject) {
    this.seal = paramObject;
  }
  
  public Object G$freeze() {
    return this.freeze;
  }
  
  public void S$freeze(Object paramObject) {
    this.freeze = paramObject;
  }
  
  public Object G$preventExtensions() {
    return this.preventExtensions;
  }
  
  public void S$preventExtensions(Object paramObject) {
    this.preventExtensions = paramObject;
  }
  
  public Object G$isSealed() {
    return this.isSealed;
  }
  
  public void S$isSealed(Object paramObject) {
    this.isSealed = paramObject;
  }
  
  public Object G$isFrozen() {
    return this.isFrozen;
  }
  
  public void S$isFrozen(Object paramObject) {
    this.isFrozen = paramObject;
  }
  
  public Object G$isExtensible() {
    return this.isExtensible;
  }
  
  public void S$isExtensible(Object paramObject) {
    this.isExtensible = paramObject;
  }
  
  public Object G$keys() {
    return this.keys;
  }
  
  public void S$keys(Object paramObject) {
    this.keys = paramObject;
  }
  
  public Object G$bindProperties() {
    return this.bindProperties;
  }
  
  public void S$bindProperties(Object paramObject) {
    this.bindProperties = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: bipush #17
    //   6: invokespecial <init> : (I)V
    //   9: dup
    //   10: ldc 'setIndexedPropertiesToExternalArrayData'
    //   12: iconst_2
    //   13: ldc
    //   15: ldc
    //   17: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   20: invokeinterface add : (Ljava/lang/Object;)Z
    //   25: pop
    //   26: dup
    //   27: ldc 'getPrototypeOf'
    //   29: iconst_2
    //   30: ldc
    //   32: ldc
    //   34: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   37: invokeinterface add : (Ljava/lang/Object;)Z
    //   42: pop
    //   43: dup
    //   44: ldc 'setPrototypeOf'
    //   46: iconst_2
    //   47: ldc
    //   49: ldc
    //   51: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   54: invokeinterface add : (Ljava/lang/Object;)Z
    //   59: pop
    //   60: dup
    //   61: ldc 'getOwnPropertyDescriptor'
    //   63: iconst_2
    //   64: ldc
    //   66: ldc
    //   68: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   71: invokeinterface add : (Ljava/lang/Object;)Z
    //   76: pop
    //   77: dup
    //   78: ldc 'getOwnPropertyNames'
    //   80: iconst_2
    //   81: ldc
    //   83: ldc
    //   85: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   88: invokeinterface add : (Ljava/lang/Object;)Z
    //   93: pop
    //   94: dup
    //   95: ldc 'getOwnPropertySymbols'
    //   97: iconst_2
    //   98: ldc
    //   100: ldc
    //   102: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   105: invokeinterface add : (Ljava/lang/Object;)Z
    //   110: pop
    //   111: dup
    //   112: ldc 'create'
    //   114: iconst_2
    //   115: ldc
    //   117: ldc
    //   119: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   122: invokeinterface add : (Ljava/lang/Object;)Z
    //   127: pop
    //   128: dup
    //   129: ldc 'defineProperty'
    //   131: iconst_2
    //   132: ldc
    //   134: ldc
    //   136: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   139: invokeinterface add : (Ljava/lang/Object;)Z
    //   144: pop
    //   145: dup
    //   146: ldc 'defineProperties'
    //   148: iconst_2
    //   149: ldc
    //   151: ldc
    //   153: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   156: invokeinterface add : (Ljava/lang/Object;)Z
    //   161: pop
    //   162: dup
    //   163: ldc 'seal'
    //   165: iconst_2
    //   166: ldc
    //   168: ldc
    //   170: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   173: invokeinterface add : (Ljava/lang/Object;)Z
    //   178: pop
    //   179: dup
    //   180: ldc 'freeze'
    //   182: iconst_2
    //   183: ldc
    //   185: ldc
    //   187: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   190: invokeinterface add : (Ljava/lang/Object;)Z
    //   195: pop
    //   196: dup
    //   197: ldc 'preventExtensions'
    //   199: iconst_2
    //   200: ldc
    //   202: ldc
    //   204: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   207: invokeinterface add : (Ljava/lang/Object;)Z
    //   212: pop
    //   213: dup
    //   214: ldc 'isSealed'
    //   216: iconst_2
    //   217: ldc
    //   219: ldc
    //   221: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   224: invokeinterface add : (Ljava/lang/Object;)Z
    //   229: pop
    //   230: dup
    //   231: ldc 'isFrozen'
    //   233: iconst_2
    //   234: ldc
    //   236: ldc
    //   238: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   241: invokeinterface add : (Ljava/lang/Object;)Z
    //   246: pop
    //   247: dup
    //   248: ldc 'isExtensible'
    //   250: iconst_2
    //   251: ldc
    //   253: ldc
    //   255: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   258: invokeinterface add : (Ljava/lang/Object;)Z
    //   263: pop
    //   264: dup
    //   265: ldc 'keys'
    //   267: iconst_2
    //   268: ldc
    //   270: ldc
    //   272: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   275: invokeinterface add : (Ljava/lang/Object;)Z
    //   280: pop
    //   281: dup
    //   282: ldc 'bindProperties'
    //   284: iconst_2
    //   285: ldc
    //   287: ldc
    //   289: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   292: invokeinterface add : (Ljava/lang/Object;)Z
    //   297: pop
    //   298: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   301: putstatic org/openjdk/nashorn/internal/objects/NativeObject$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   304: return
  }
  
  NativeObject$Constructor() {
    // Byte code:
    //   0: aload_0
    //   1: ldc 'Object'
    //   3: ldc
    //   5: getstatic org/openjdk/nashorn/internal/objects/NativeObject$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   8: aconst_null
    //   9: invokespecial <init> : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;Lorg/openjdk/nashorn/internal/runtime/PropertyMap;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)V
    //   12: aload_0
    //   13: ldc 'setIndexedPropertiesToExternalArrayData'
    //   15: ldc_w
    //   18: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   21: dup
    //   22: ldc_w 'Object.setIndexedPropertiesToExternalArrayData'
    //   25: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   28: putfield setIndexedPropertiesToExternalArrayData : Ljava/lang/Object;
    //   31: aload_0
    //   32: ldc 'getPrototypeOf'
    //   34: ldc_w
    //   37: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   40: dup
    //   41: ldc_w 'Object.getPrototypeOf'
    //   44: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   47: putfield getPrototypeOf : Ljava/lang/Object;
    //   50: aload_0
    //   51: ldc 'setPrototypeOf'
    //   53: ldc_w
    //   56: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   59: dup
    //   60: ldc_w 'Object.setPrototypeOf'
    //   63: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   66: putfield setPrototypeOf : Ljava/lang/Object;
    //   69: aload_0
    //   70: ldc 'getOwnPropertyDescriptor'
    //   72: ldc_w
    //   75: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   78: dup
    //   79: ldc_w 'Object.getOwnPropertyDescriptor'
    //   82: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   85: putfield getOwnPropertyDescriptor : Ljava/lang/Object;
    //   88: aload_0
    //   89: ldc 'getOwnPropertyNames'
    //   91: ldc_w
    //   94: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   97: dup
    //   98: ldc_w 'Object.getOwnPropertyNames'
    //   101: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   104: putfield getOwnPropertyNames : Ljava/lang/Object;
    //   107: aload_0
    //   108: ldc 'getOwnPropertySymbols'
    //   110: ldc_w
    //   113: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   116: dup
    //   117: ldc_w 'Object.getOwnPropertySymbols'
    //   120: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   123: putfield getOwnPropertySymbols : Ljava/lang/Object;
    //   126: aload_0
    //   127: ldc 'create'
    //   129: ldc_w
    //   132: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   135: dup
    //   136: ldc_w 'Object.create'
    //   139: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   142: putfield create : Ljava/lang/Object;
    //   145: aload_0
    //   146: ldc 'defineProperty'
    //   148: ldc_w
    //   151: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   154: dup
    //   155: ldc_w 'Object.defineProperty'
    //   158: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   161: putfield defineProperty : Ljava/lang/Object;
    //   164: aload_0
    //   165: ldc 'defineProperties'
    //   167: ldc_w
    //   170: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   173: dup
    //   174: ldc_w 'Object.defineProperties'
    //   177: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   180: putfield defineProperties : Ljava/lang/Object;
    //   183: aload_0
    //   184: ldc 'seal'
    //   186: ldc_w
    //   189: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   192: dup
    //   193: ldc_w 'Object.seal'
    //   196: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   199: putfield seal : Ljava/lang/Object;
    //   202: aload_0
    //   203: ldc 'freeze'
    //   205: ldc_w
    //   208: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   211: dup
    //   212: ldc_w 'Object.freeze'
    //   215: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   218: putfield freeze : Ljava/lang/Object;
    //   221: aload_0
    //   222: ldc 'preventExtensions'
    //   224: ldc_w
    //   227: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   230: dup
    //   231: ldc_w 'Object.preventExtensions'
    //   234: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   237: putfield preventExtensions : Ljava/lang/Object;
    //   240: aload_0
    //   241: ldc 'isSealed'
    //   243: ldc_w
    //   246: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   249: dup
    //   250: ldc_w 'Object.isSealed'
    //   253: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   256: putfield isSealed : Ljava/lang/Object;
    //   259: aload_0
    //   260: ldc 'isFrozen'
    //   262: ldc_w
    //   265: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   268: dup
    //   269: ldc_w 'Object.isFrozen'
    //   272: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   275: putfield isFrozen : Ljava/lang/Object;
    //   278: aload_0
    //   279: ldc 'isExtensible'
    //   281: ldc_w
    //   284: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   287: dup
    //   288: ldc_w 'Object.isExtensible'
    //   291: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   294: putfield isExtensible : Ljava/lang/Object;
    //   297: aload_0
    //   298: ldc 'keys'
    //   300: ldc_w
    //   303: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   306: dup
    //   307: ldc_w 'Object.keys'
    //   310: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   313: putfield keys : Ljava/lang/Object;
    //   316: aload_0
    //   317: ldc 'bindProperties'
    //   319: ldc_w
    //   322: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   325: dup
    //   326: ldc_w 'Object.bindProperties'
    //   329: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   332: putfield bindProperties : Ljava/lang/Object;
    //   335: aload_0
    //   336: new org/openjdk/nashorn/internal/objects/NativeObject$Prototype
    //   339: dup
    //   340: invokespecial <init> : ()V
    //   343: dup
    //   344: aload_0
    //   345: invokestatic setConstructor : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   348: invokevirtual setPrototype : (Ljava/lang/Object;)V
    //   351: aload_0
    //   352: ldc 'Object'
    //   354: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   357: return
  }
}
