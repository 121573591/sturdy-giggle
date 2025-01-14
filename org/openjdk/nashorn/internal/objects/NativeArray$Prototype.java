package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.PrototypeObject;

final class NativeArray$Prototype extends PrototypeObject {
  private Object toString;
  
  private Object assertNumeric;
  
  private Object toLocaleString;
  
  private Object concat;
  
  private Object join;
  
  private Object pop;
  
  private Object push;
  
  private Object reverse;
  
  private Object shift;
  
  private Object slice;
  
  private Object sort;
  
  private Object splice;
  
  private Object unshift;
  
  private Object indexOf;
  
  private Object lastIndexOf;
  
  private Object every;
  
  private Object some;
  
  private Object forEach;
  
  private Object map;
  
  private Object filter;
  
  private Object reduce;
  
  private Object reduceRight;
  
  private Object entries;
  
  private Object keys;
  
  private Object values;
  
  private Object getIterator;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$toString() {
    return this.toString;
  }
  
  public void S$toString(Object paramObject) {
    this.toString = paramObject;
  }
  
  public Object G$assertNumeric() {
    return this.assertNumeric;
  }
  
  public void S$assertNumeric(Object paramObject) {
    this.assertNumeric = paramObject;
  }
  
  public Object G$toLocaleString() {
    return this.toLocaleString;
  }
  
  public void S$toLocaleString(Object paramObject) {
    this.toLocaleString = paramObject;
  }
  
  public Object G$concat() {
    return this.concat;
  }
  
  public void S$concat(Object paramObject) {
    this.concat = paramObject;
  }
  
  public Object G$join() {
    return this.join;
  }
  
  public void S$join(Object paramObject) {
    this.join = paramObject;
  }
  
  public Object G$pop() {
    return this.pop;
  }
  
  public void S$pop(Object paramObject) {
    this.pop = paramObject;
  }
  
  public Object G$push() {
    return this.push;
  }
  
  public void S$push(Object paramObject) {
    this.push = paramObject;
  }
  
  public Object G$reverse() {
    return this.reverse;
  }
  
  public void S$reverse(Object paramObject) {
    this.reverse = paramObject;
  }
  
  public Object G$shift() {
    return this.shift;
  }
  
  public void S$shift(Object paramObject) {
    this.shift = paramObject;
  }
  
  public Object G$slice() {
    return this.slice;
  }
  
  public void S$slice(Object paramObject) {
    this.slice = paramObject;
  }
  
  public Object G$sort() {
    return this.sort;
  }
  
  public void S$sort(Object paramObject) {
    this.sort = paramObject;
  }
  
  public Object G$splice() {
    return this.splice;
  }
  
  public void S$splice(Object paramObject) {
    this.splice = paramObject;
  }
  
  public Object G$unshift() {
    return this.unshift;
  }
  
  public void S$unshift(Object paramObject) {
    this.unshift = paramObject;
  }
  
  public Object G$indexOf() {
    return this.indexOf;
  }
  
  public void S$indexOf(Object paramObject) {
    this.indexOf = paramObject;
  }
  
  public Object G$lastIndexOf() {
    return this.lastIndexOf;
  }
  
  public void S$lastIndexOf(Object paramObject) {
    this.lastIndexOf = paramObject;
  }
  
  public Object G$every() {
    return this.every;
  }
  
  public void S$every(Object paramObject) {
    this.every = paramObject;
  }
  
  public Object G$some() {
    return this.some;
  }
  
  public void S$some(Object paramObject) {
    this.some = paramObject;
  }
  
  public Object G$forEach() {
    return this.forEach;
  }
  
  public void S$forEach(Object paramObject) {
    this.forEach = paramObject;
  }
  
  public Object G$map() {
    return this.map;
  }
  
  public void S$map(Object paramObject) {
    this.map = paramObject;
  }
  
  public Object G$filter() {
    return this.filter;
  }
  
  public void S$filter(Object paramObject) {
    this.filter = paramObject;
  }
  
  public Object G$reduce() {
    return this.reduce;
  }
  
  public void S$reduce(Object paramObject) {
    this.reduce = paramObject;
  }
  
  public Object G$reduceRight() {
    return this.reduceRight;
  }
  
  public void S$reduceRight(Object paramObject) {
    this.reduceRight = paramObject;
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
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: bipush #27
    //   6: invokespecial <init> : (I)V
    //   9: dup
    //   10: ldc 'length'
    //   12: bipush #6
    //   14: ldc
    //   16: ldc
    //   18: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   21: invokeinterface add : (Ljava/lang/Object;)Z
    //   26: pop
    //   27: dup
    //   28: ldc 'toString'
    //   30: iconst_2
    //   31: ldc
    //   33: ldc
    //   35: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   38: invokeinterface add : (Ljava/lang/Object;)Z
    //   43: pop
    //   44: dup
    //   45: ldc 'assertNumeric'
    //   47: iconst_2
    //   48: ldc
    //   50: ldc
    //   52: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   55: invokeinterface add : (Ljava/lang/Object;)Z
    //   60: pop
    //   61: dup
    //   62: ldc 'toLocaleString'
    //   64: iconst_2
    //   65: ldc
    //   67: ldc
    //   69: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   72: invokeinterface add : (Ljava/lang/Object;)Z
    //   77: pop
    //   78: dup
    //   79: ldc 'concat'
    //   81: iconst_2
    //   82: ldc
    //   84: ldc
    //   86: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   89: invokeinterface add : (Ljava/lang/Object;)Z
    //   94: pop
    //   95: dup
    //   96: ldc 'join'
    //   98: iconst_2
    //   99: ldc
    //   101: ldc
    //   103: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   106: invokeinterface add : (Ljava/lang/Object;)Z
    //   111: pop
    //   112: dup
    //   113: ldc 'pop'
    //   115: iconst_2
    //   116: ldc
    //   118: ldc
    //   120: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   123: invokeinterface add : (Ljava/lang/Object;)Z
    //   128: pop
    //   129: dup
    //   130: ldc 'push'
    //   132: iconst_2
    //   133: ldc
    //   135: ldc
    //   137: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   140: invokeinterface add : (Ljava/lang/Object;)Z
    //   145: pop
    //   146: dup
    //   147: ldc 'reverse'
    //   149: iconst_2
    //   150: ldc
    //   152: ldc
    //   154: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   157: invokeinterface add : (Ljava/lang/Object;)Z
    //   162: pop
    //   163: dup
    //   164: ldc 'shift'
    //   166: iconst_2
    //   167: ldc
    //   169: ldc
    //   171: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   174: invokeinterface add : (Ljava/lang/Object;)Z
    //   179: pop
    //   180: dup
    //   181: ldc 'slice'
    //   183: iconst_2
    //   184: ldc
    //   186: ldc
    //   188: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   191: invokeinterface add : (Ljava/lang/Object;)Z
    //   196: pop
    //   197: dup
    //   198: ldc 'sort'
    //   200: iconst_2
    //   201: ldc
    //   203: ldc
    //   205: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   208: invokeinterface add : (Ljava/lang/Object;)Z
    //   213: pop
    //   214: dup
    //   215: ldc 'splice'
    //   217: iconst_2
    //   218: ldc
    //   220: ldc_w
    //   223: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   226: invokeinterface add : (Ljava/lang/Object;)Z
    //   231: pop
    //   232: dup
    //   233: ldc_w 'unshift'
    //   236: iconst_2
    //   237: ldc_w
    //   240: ldc_w
    //   243: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   246: invokeinterface add : (Ljava/lang/Object;)Z
    //   251: pop
    //   252: dup
    //   253: ldc_w 'indexOf'
    //   256: iconst_2
    //   257: ldc_w
    //   260: ldc_w
    //   263: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   266: invokeinterface add : (Ljava/lang/Object;)Z
    //   271: pop
    //   272: dup
    //   273: ldc_w 'lastIndexOf'
    //   276: iconst_2
    //   277: ldc_w
    //   280: ldc_w
    //   283: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   286: invokeinterface add : (Ljava/lang/Object;)Z
    //   291: pop
    //   292: dup
    //   293: ldc_w 'every'
    //   296: iconst_2
    //   297: ldc_w
    //   300: ldc_w
    //   303: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   306: invokeinterface add : (Ljava/lang/Object;)Z
    //   311: pop
    //   312: dup
    //   313: ldc_w 'some'
    //   316: iconst_2
    //   317: ldc_w
    //   320: ldc_w
    //   323: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   326: invokeinterface add : (Ljava/lang/Object;)Z
    //   331: pop
    //   332: dup
    //   333: ldc_w 'forEach'
    //   336: iconst_2
    //   337: ldc_w
    //   340: ldc_w
    //   343: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   346: invokeinterface add : (Ljava/lang/Object;)Z
    //   351: pop
    //   352: dup
    //   353: ldc_w 'map'
    //   356: iconst_2
    //   357: ldc_w
    //   360: ldc_w
    //   363: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   366: invokeinterface add : (Ljava/lang/Object;)Z
    //   371: pop
    //   372: dup
    //   373: ldc_w 'filter'
    //   376: iconst_2
    //   377: ldc_w
    //   380: ldc_w
    //   383: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   386: invokeinterface add : (Ljava/lang/Object;)Z
    //   391: pop
    //   392: dup
    //   393: ldc_w 'reduce'
    //   396: iconst_2
    //   397: ldc_w
    //   400: ldc_w
    //   403: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   406: invokeinterface add : (Ljava/lang/Object;)Z
    //   411: pop
    //   412: dup
    //   413: ldc_w 'reduceRight'
    //   416: iconst_2
    //   417: ldc_w
    //   420: ldc_w
    //   423: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   426: invokeinterface add : (Ljava/lang/Object;)Z
    //   431: pop
    //   432: dup
    //   433: ldc_w 'entries'
    //   436: iconst_2
    //   437: ldc_w
    //   440: ldc_w
    //   443: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   446: invokeinterface add : (Ljava/lang/Object;)Z
    //   451: pop
    //   452: dup
    //   453: ldc_w 'keys'
    //   456: iconst_2
    //   457: ldc_w
    //   460: ldc_w
    //   463: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   466: invokeinterface add : (Ljava/lang/Object;)Z
    //   471: pop
    //   472: dup
    //   473: ldc_w 'values'
    //   476: iconst_2
    //   477: ldc_w
    //   480: ldc_w
    //   483: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   486: invokeinterface add : (Ljava/lang/Object;)Z
    //   491: pop
    //   492: dup
    //   493: getstatic org/openjdk/nashorn/internal/objects/NativeSymbol.iterator : Lorg/openjdk/nashorn/internal/runtime/Symbol;
    //   496: iconst_2
    //   497: ldc_w
    //   500: ldc_w
    //   503: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   506: invokeinterface add : (Ljava/lang/Object;)Z
    //   511: pop
    //   512: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   515: putstatic org/openjdk/nashorn/internal/objects/NativeArray$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   518: return
  }
  
  NativeArray$Prototype() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic org/openjdk/nashorn/internal/objects/NativeArray$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   4: invokespecial <init> : (Lorg/openjdk/nashorn/internal/runtime/PropertyMap;)V
    //   7: aload_0
    //   8: ldc 'toString'
    //   10: ldc_w
    //   13: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   16: dup
    //   17: ldc_w 'Array.prototype.toString'
    //   20: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   23: putfield toString : Ljava/lang/Object;
    //   26: aload_0
    //   27: ldc 'assertNumeric'
    //   29: ldc_w
    //   32: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   35: dup
    //   36: ldc_w 'Array.prototype.assertNumeric'
    //   39: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   42: putfield assertNumeric : Ljava/lang/Object;
    //   45: aload_0
    //   46: ldc 'toLocaleString'
    //   48: ldc_w
    //   51: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   54: dup
    //   55: ldc_w 'Array.prototype.toLocaleString'
    //   58: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   61: putfield toLocaleString : Ljava/lang/Object;
    //   64: aload_0
    //   65: ldc 'concat'
    //   67: ldc_w
    //   70: iconst_3
    //   71: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   74: dup
    //   75: iconst_0
    //   76: new org/openjdk/nashorn/internal/runtime/Specialization
    //   79: dup
    //   80: ldc_w
    //   83: ldc_w org/openjdk/nashorn/internal/objects/NativeArray$ConcatLinkLogic
    //   86: iconst_0
    //   87: iconst_0
    //   88: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;Ljava/lang/Class;ZZ)V
    //   91: aastore
    //   92: dup
    //   93: iconst_1
    //   94: new org/openjdk/nashorn/internal/runtime/Specialization
    //   97: dup
    //   98: ldc_w
    //   101: ldc_w org/openjdk/nashorn/internal/objects/NativeArray$ConcatLinkLogic
    //   104: iconst_0
    //   105: iconst_0
    //   106: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;Ljava/lang/Class;ZZ)V
    //   109: aastore
    //   110: dup
    //   111: iconst_2
    //   112: new org/openjdk/nashorn/internal/runtime/Specialization
    //   115: dup
    //   116: ldc_w
    //   119: ldc_w org/openjdk/nashorn/internal/objects/NativeArray$ConcatLinkLogic
    //   122: iconst_0
    //   123: iconst_0
    //   124: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;Ljava/lang/Class;ZZ)V
    //   127: aastore
    //   128: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   131: dup
    //   132: iconst_1
    //   133: invokevirtual setArity : (I)V
    //   136: dup
    //   137: ldc_w 'Array.prototype.concat'
    //   140: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   143: putfield concat : Ljava/lang/Object;
    //   146: aload_0
    //   147: ldc 'join'
    //   149: ldc_w
    //   152: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   155: dup
    //   156: ldc_w 'Array.prototype.join'
    //   159: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   162: putfield join : Ljava/lang/Object;
    //   165: aload_0
    //   166: ldc 'pop'
    //   168: ldc_w
    //   171: iconst_3
    //   172: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   175: dup
    //   176: iconst_0
    //   177: new org/openjdk/nashorn/internal/runtime/Specialization
    //   180: dup
    //   181: ldc_w
    //   184: ldc_w org/openjdk/nashorn/internal/objects/NativeArray$PopLinkLogic
    //   187: iconst_0
    //   188: iconst_0
    //   189: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;Ljava/lang/Class;ZZ)V
    //   192: aastore
    //   193: dup
    //   194: iconst_1
    //   195: new org/openjdk/nashorn/internal/runtime/Specialization
    //   198: dup
    //   199: ldc_w
    //   202: ldc_w org/openjdk/nashorn/internal/objects/NativeArray$PopLinkLogic
    //   205: iconst_0
    //   206: iconst_0
    //   207: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;Ljava/lang/Class;ZZ)V
    //   210: aastore
    //   211: dup
    //   212: iconst_2
    //   213: new org/openjdk/nashorn/internal/runtime/Specialization
    //   216: dup
    //   217: ldc_w
    //   220: ldc_w org/openjdk/nashorn/internal/objects/NativeArray$PopLinkLogic
    //   223: iconst_0
    //   224: iconst_0
    //   225: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;Ljava/lang/Class;ZZ)V
    //   228: aastore
    //   229: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   232: dup
    //   233: ldc_w 'Array.prototype.pop'
    //   236: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   239: putfield pop : Ljava/lang/Object;
    //   242: aload_0
    //   243: ldc 'push'
    //   245: ldc_w
    //   248: iconst_4
    //   249: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   252: dup
    //   253: iconst_0
    //   254: new org/openjdk/nashorn/internal/runtime/Specialization
    //   257: dup
    //   258: ldc_w
    //   261: ldc_w org/openjdk/nashorn/internal/objects/NativeArray$PushLinkLogic
    //   264: iconst_0
    //   265: iconst_0
    //   266: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;Ljava/lang/Class;ZZ)V
    //   269: aastore
    //   270: dup
    //   271: iconst_1
    //   272: new org/openjdk/nashorn/internal/runtime/Specialization
    //   275: dup
    //   276: ldc_w
    //   279: ldc_w org/openjdk/nashorn/internal/objects/NativeArray$PushLinkLogic
    //   282: iconst_0
    //   283: iconst_0
    //   284: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;Ljava/lang/Class;ZZ)V
    //   287: aastore
    //   288: dup
    //   289: iconst_2
    //   290: new org/openjdk/nashorn/internal/runtime/Specialization
    //   293: dup
    //   294: ldc_w
    //   297: ldc_w org/openjdk/nashorn/internal/objects/NativeArray$PushLinkLogic
    //   300: iconst_0
    //   301: iconst_0
    //   302: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;Ljava/lang/Class;ZZ)V
    //   305: aastore
    //   306: dup
    //   307: iconst_3
    //   308: new org/openjdk/nashorn/internal/runtime/Specialization
    //   311: dup
    //   312: ldc_w
    //   315: iconst_0
    //   316: iconst_0
    //   317: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   320: aastore
    //   321: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   324: dup
    //   325: iconst_1
    //   326: invokevirtual setArity : (I)V
    //   329: dup
    //   330: ldc_w 'Array.prototype.push'
    //   333: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   336: putfield push : Ljava/lang/Object;
    //   339: aload_0
    //   340: ldc 'reverse'
    //   342: ldc_w
    //   345: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   348: dup
    //   349: ldc_w 'Array.prototype.reverse'
    //   352: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   355: putfield reverse : Ljava/lang/Object;
    //   358: aload_0
    //   359: ldc 'shift'
    //   361: ldc_w
    //   364: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   367: dup
    //   368: ldc_w 'Array.prototype.shift'
    //   371: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   374: putfield shift : Ljava/lang/Object;
    //   377: aload_0
    //   378: ldc 'slice'
    //   380: ldc_w
    //   383: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   386: dup
    //   387: ldc_w 'Array.prototype.slice'
    //   390: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   393: putfield slice : Ljava/lang/Object;
    //   396: aload_0
    //   397: ldc 'sort'
    //   399: ldc_w
    //   402: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   405: dup
    //   406: ldc_w 'Array.prototype.sort'
    //   409: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   412: putfield sort : Ljava/lang/Object;
    //   415: aload_0
    //   416: ldc 'splice'
    //   418: ldc_w
    //   421: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   424: dup
    //   425: iconst_2
    //   426: invokevirtual setArity : (I)V
    //   429: dup
    //   430: ldc_w 'Array.prototype.splice'
    //   433: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   436: putfield splice : Ljava/lang/Object;
    //   439: aload_0
    //   440: ldc_w 'unshift'
    //   443: ldc_w
    //   446: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   449: dup
    //   450: iconst_1
    //   451: invokevirtual setArity : (I)V
    //   454: dup
    //   455: ldc_w 'Array.prototype.unshift'
    //   458: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   461: putfield unshift : Ljava/lang/Object;
    //   464: aload_0
    //   465: ldc_w 'indexOf'
    //   468: ldc_w
    //   471: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   474: dup
    //   475: iconst_1
    //   476: invokevirtual setArity : (I)V
    //   479: dup
    //   480: ldc_w 'Array.prototype.indexOf'
    //   483: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   486: putfield indexOf : Ljava/lang/Object;
    //   489: aload_0
    //   490: ldc_w 'lastIndexOf'
    //   493: ldc_w
    //   496: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   499: dup
    //   500: iconst_1
    //   501: invokevirtual setArity : (I)V
    //   504: dup
    //   505: ldc_w 'Array.prototype.lastIndexOf'
    //   508: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   511: putfield lastIndexOf : Ljava/lang/Object;
    //   514: aload_0
    //   515: ldc_w 'every'
    //   518: ldc_w
    //   521: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   524: dup
    //   525: iconst_1
    //   526: invokevirtual setArity : (I)V
    //   529: dup
    //   530: ldc_w 'Array.prototype.every'
    //   533: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   536: putfield every : Ljava/lang/Object;
    //   539: aload_0
    //   540: ldc_w 'some'
    //   543: ldc_w
    //   546: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   549: dup
    //   550: iconst_1
    //   551: invokevirtual setArity : (I)V
    //   554: dup
    //   555: ldc_w 'Array.prototype.some'
    //   558: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   561: putfield some : Ljava/lang/Object;
    //   564: aload_0
    //   565: ldc_w 'forEach'
    //   568: ldc_w
    //   571: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   574: dup
    //   575: iconst_1
    //   576: invokevirtual setArity : (I)V
    //   579: dup
    //   580: ldc_w 'Array.prototype.forEach'
    //   583: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   586: putfield forEach : Ljava/lang/Object;
    //   589: aload_0
    //   590: ldc_w 'map'
    //   593: ldc_w
    //   596: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   599: dup
    //   600: iconst_1
    //   601: invokevirtual setArity : (I)V
    //   604: dup
    //   605: ldc_w 'Array.prototype.map'
    //   608: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   611: putfield map : Ljava/lang/Object;
    //   614: aload_0
    //   615: ldc_w 'filter'
    //   618: ldc_w
    //   621: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   624: dup
    //   625: iconst_1
    //   626: invokevirtual setArity : (I)V
    //   629: dup
    //   630: ldc_w 'Array.prototype.filter'
    //   633: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   636: putfield filter : Ljava/lang/Object;
    //   639: aload_0
    //   640: ldc_w 'reduce'
    //   643: ldc_w
    //   646: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   649: dup
    //   650: iconst_1
    //   651: invokevirtual setArity : (I)V
    //   654: dup
    //   655: ldc_w 'Array.prototype.reduce'
    //   658: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   661: putfield reduce : Ljava/lang/Object;
    //   664: aload_0
    //   665: ldc_w 'reduceRight'
    //   668: ldc_w
    //   671: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   674: dup
    //   675: iconst_1
    //   676: invokevirtual setArity : (I)V
    //   679: dup
    //   680: ldc_w 'Array.prototype.reduceRight'
    //   683: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   686: putfield reduceRight : Ljava/lang/Object;
    //   689: aload_0
    //   690: ldc_w 'entries'
    //   693: ldc_w
    //   696: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   699: dup
    //   700: ldc_w 'Array.prototype.entries'
    //   703: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   706: putfield entries : Ljava/lang/Object;
    //   709: aload_0
    //   710: ldc_w 'keys'
    //   713: ldc_w
    //   716: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   719: dup
    //   720: ldc_w 'Array.prototype.keys'
    //   723: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   726: putfield keys : Ljava/lang/Object;
    //   729: aload_0
    //   730: ldc_w 'values'
    //   733: ldc_w
    //   736: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   739: dup
    //   740: ldc_w 'Array.prototype.values'
    //   743: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   746: putfield values : Ljava/lang/Object;
    //   749: aload_0
    //   750: ldc_w 'Symbol[iterator]'
    //   753: ldc_w
    //   756: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   759: dup
    //   760: ldc_w 'Array.prototype.@@iterator'
    //   763: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   766: putfield getIterator : Ljava/lang/Object;
    //   769: return
  }
  
  public String getClassName() {
    return "Array";
  }
}
