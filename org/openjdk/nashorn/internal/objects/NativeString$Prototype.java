package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.PrototypeObject;

final class NativeString$Prototype extends PrototypeObject {
  private Object toString;
  
  private Object valueOf;
  
  private Object charAt;
  
  private Object charCodeAt;
  
  private Object concat;
  
  private Object indexOf;
  
  private Object lastIndexOf;
  
  private Object localeCompare;
  
  private Object match;
  
  private Object replace;
  
  private Object search;
  
  private Object slice;
  
  private Object split;
  
  private Object substr;
  
  private Object substring;
  
  private Object toLowerCase;
  
  private Object toLocaleLowerCase;
  
  private Object toUpperCase;
  
  private Object toLocaleUpperCase;
  
  private Object trim;
  
  private Object trimLeft;
  
  private Object trimRight;
  
  private Object getIterator;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$toString() {
    return this.toString;
  }
  
  public void S$toString(Object paramObject) {
    this.toString = paramObject;
  }
  
  public Object G$valueOf() {
    return this.valueOf;
  }
  
  public void S$valueOf(Object paramObject) {
    this.valueOf = paramObject;
  }
  
  public Object G$charAt() {
    return this.charAt;
  }
  
  public void S$charAt(Object paramObject) {
    this.charAt = paramObject;
  }
  
  public Object G$charCodeAt() {
    return this.charCodeAt;
  }
  
  public void S$charCodeAt(Object paramObject) {
    this.charCodeAt = paramObject;
  }
  
  public Object G$concat() {
    return this.concat;
  }
  
  public void S$concat(Object paramObject) {
    this.concat = paramObject;
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
  
  public Object G$localeCompare() {
    return this.localeCompare;
  }
  
  public void S$localeCompare(Object paramObject) {
    this.localeCompare = paramObject;
  }
  
  public Object G$match() {
    return this.match;
  }
  
  public void S$match(Object paramObject) {
    this.match = paramObject;
  }
  
  public Object G$replace() {
    return this.replace;
  }
  
  public void S$replace(Object paramObject) {
    this.replace = paramObject;
  }
  
  public Object G$search() {
    return this.search;
  }
  
  public void S$search(Object paramObject) {
    this.search = paramObject;
  }
  
  public Object G$slice() {
    return this.slice;
  }
  
  public void S$slice(Object paramObject) {
    this.slice = paramObject;
  }
  
  public Object G$split() {
    return this.split;
  }
  
  public void S$split(Object paramObject) {
    this.split = paramObject;
  }
  
  public Object G$substr() {
    return this.substr;
  }
  
  public void S$substr(Object paramObject) {
    this.substr = paramObject;
  }
  
  public Object G$substring() {
    return this.substring;
  }
  
  public void S$substring(Object paramObject) {
    this.substring = paramObject;
  }
  
  public Object G$toLowerCase() {
    return this.toLowerCase;
  }
  
  public void S$toLowerCase(Object paramObject) {
    this.toLowerCase = paramObject;
  }
  
  public Object G$toLocaleLowerCase() {
    return this.toLocaleLowerCase;
  }
  
  public void S$toLocaleLowerCase(Object paramObject) {
    this.toLocaleLowerCase = paramObject;
  }
  
  public Object G$toUpperCase() {
    return this.toUpperCase;
  }
  
  public void S$toUpperCase(Object paramObject) {
    this.toUpperCase = paramObject;
  }
  
  public Object G$toLocaleUpperCase() {
    return this.toLocaleUpperCase;
  }
  
  public void S$toLocaleUpperCase(Object paramObject) {
    this.toLocaleUpperCase = paramObject;
  }
  
  public Object G$trim() {
    return this.trim;
  }
  
  public void S$trim(Object paramObject) {
    this.trim = paramObject;
  }
  
  public Object G$trimLeft() {
    return this.trimLeft;
  }
  
  public void S$trimLeft(Object paramObject) {
    this.trimLeft = paramObject;
  }
  
  public Object G$trimRight() {
    return this.trimRight;
  }
  
  public void S$trimRight(Object paramObject) {
    this.trimRight = paramObject;
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
    //   4: bipush #23
    //   6: invokespecial <init> : (I)V
    //   9: dup
    //   10: ldc 'toString'
    //   12: iconst_2
    //   13: ldc
    //   15: ldc
    //   17: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   20: invokeinterface add : (Ljava/lang/Object;)Z
    //   25: pop
    //   26: dup
    //   27: ldc 'valueOf'
    //   29: iconst_2
    //   30: ldc
    //   32: ldc
    //   34: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   37: invokeinterface add : (Ljava/lang/Object;)Z
    //   42: pop
    //   43: dup
    //   44: ldc 'charAt'
    //   46: iconst_2
    //   47: ldc
    //   49: ldc
    //   51: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   54: invokeinterface add : (Ljava/lang/Object;)Z
    //   59: pop
    //   60: dup
    //   61: ldc 'charCodeAt'
    //   63: iconst_2
    //   64: ldc
    //   66: ldc
    //   68: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   71: invokeinterface add : (Ljava/lang/Object;)Z
    //   76: pop
    //   77: dup
    //   78: ldc 'concat'
    //   80: iconst_2
    //   81: ldc
    //   83: ldc
    //   85: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   88: invokeinterface add : (Ljava/lang/Object;)Z
    //   93: pop
    //   94: dup
    //   95: ldc 'indexOf'
    //   97: iconst_2
    //   98: ldc
    //   100: ldc
    //   102: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   105: invokeinterface add : (Ljava/lang/Object;)Z
    //   110: pop
    //   111: dup
    //   112: ldc 'lastIndexOf'
    //   114: iconst_2
    //   115: ldc
    //   117: ldc
    //   119: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   122: invokeinterface add : (Ljava/lang/Object;)Z
    //   127: pop
    //   128: dup
    //   129: ldc 'localeCompare'
    //   131: iconst_2
    //   132: ldc
    //   134: ldc
    //   136: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   139: invokeinterface add : (Ljava/lang/Object;)Z
    //   144: pop
    //   145: dup
    //   146: ldc 'match'
    //   148: iconst_2
    //   149: ldc
    //   151: ldc
    //   153: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   156: invokeinterface add : (Ljava/lang/Object;)Z
    //   161: pop
    //   162: dup
    //   163: ldc 'replace'
    //   165: iconst_2
    //   166: ldc
    //   168: ldc
    //   170: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   173: invokeinterface add : (Ljava/lang/Object;)Z
    //   178: pop
    //   179: dup
    //   180: ldc 'search'
    //   182: iconst_2
    //   183: ldc
    //   185: ldc
    //   187: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   190: invokeinterface add : (Ljava/lang/Object;)Z
    //   195: pop
    //   196: dup
    //   197: ldc 'slice'
    //   199: iconst_2
    //   200: ldc
    //   202: ldc
    //   204: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   207: invokeinterface add : (Ljava/lang/Object;)Z
    //   212: pop
    //   213: dup
    //   214: ldc 'split'
    //   216: iconst_2
    //   217: ldc
    //   219: ldc
    //   221: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   224: invokeinterface add : (Ljava/lang/Object;)Z
    //   229: pop
    //   230: dup
    //   231: ldc 'substr'
    //   233: iconst_2
    //   234: ldc
    //   236: ldc
    //   238: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   241: invokeinterface add : (Ljava/lang/Object;)Z
    //   246: pop
    //   247: dup
    //   248: ldc 'substring'
    //   250: iconst_2
    //   251: ldc
    //   253: ldc
    //   255: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   258: invokeinterface add : (Ljava/lang/Object;)Z
    //   263: pop
    //   264: dup
    //   265: ldc 'toLowerCase'
    //   267: iconst_2
    //   268: ldc
    //   270: ldc_w
    //   273: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   276: invokeinterface add : (Ljava/lang/Object;)Z
    //   281: pop
    //   282: dup
    //   283: ldc_w 'toLocaleLowerCase'
    //   286: iconst_2
    //   287: ldc_w
    //   290: ldc_w
    //   293: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   296: invokeinterface add : (Ljava/lang/Object;)Z
    //   301: pop
    //   302: dup
    //   303: ldc_w 'toUpperCase'
    //   306: iconst_2
    //   307: ldc_w
    //   310: ldc_w
    //   313: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   316: invokeinterface add : (Ljava/lang/Object;)Z
    //   321: pop
    //   322: dup
    //   323: ldc_w 'toLocaleUpperCase'
    //   326: iconst_2
    //   327: ldc_w
    //   330: ldc_w
    //   333: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   336: invokeinterface add : (Ljava/lang/Object;)Z
    //   341: pop
    //   342: dup
    //   343: ldc_w 'trim'
    //   346: iconst_2
    //   347: ldc_w
    //   350: ldc_w
    //   353: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   356: invokeinterface add : (Ljava/lang/Object;)Z
    //   361: pop
    //   362: dup
    //   363: ldc_w 'trimLeft'
    //   366: iconst_2
    //   367: ldc_w
    //   370: ldc_w
    //   373: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   376: invokeinterface add : (Ljava/lang/Object;)Z
    //   381: pop
    //   382: dup
    //   383: ldc_w 'trimRight'
    //   386: iconst_2
    //   387: ldc_w
    //   390: ldc_w
    //   393: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   396: invokeinterface add : (Ljava/lang/Object;)Z
    //   401: pop
    //   402: dup
    //   403: getstatic org/openjdk/nashorn/internal/objects/NativeSymbol.iterator : Lorg/openjdk/nashorn/internal/runtime/Symbol;
    //   406: iconst_2
    //   407: ldc_w
    //   410: ldc_w
    //   413: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   416: invokeinterface add : (Ljava/lang/Object;)Z
    //   421: pop
    //   422: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   425: putstatic org/openjdk/nashorn/internal/objects/NativeString$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   428: return
  }
  
  NativeString$Prototype() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic org/openjdk/nashorn/internal/objects/NativeString$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   4: invokespecial <init> : (Lorg/openjdk/nashorn/internal/runtime/PropertyMap;)V
    //   7: aload_0
    //   8: ldc 'toString'
    //   10: ldc_w
    //   13: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   16: dup
    //   17: ldc_w 'String.prototype.toString'
    //   20: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   23: putfield toString : Ljava/lang/Object;
    //   26: aload_0
    //   27: ldc 'valueOf'
    //   29: ldc_w
    //   32: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   35: dup
    //   36: ldc_w 'String.prototype.valueOf'
    //   39: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   42: putfield valueOf : Ljava/lang/Object;
    //   45: aload_0
    //   46: ldc 'charAt'
    //   48: ldc_w
    //   51: iconst_2
    //   52: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   55: dup
    //   56: iconst_0
    //   57: new org/openjdk/nashorn/internal/runtime/Specialization
    //   60: dup
    //   61: ldc_w
    //   64: iconst_0
    //   65: iconst_0
    //   66: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   69: aastore
    //   70: dup
    //   71: iconst_1
    //   72: new org/openjdk/nashorn/internal/runtime/Specialization
    //   75: dup
    //   76: ldc_w
    //   79: iconst_0
    //   80: iconst_0
    //   81: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   84: aastore
    //   85: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   88: dup
    //   89: ldc_w 'String.prototype.charAt'
    //   92: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   95: putfield charAt : Ljava/lang/Object;
    //   98: aload_0
    //   99: ldc 'charCodeAt'
    //   101: ldc_w
    //   104: iconst_2
    //   105: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   108: dup
    //   109: iconst_0
    //   110: new org/openjdk/nashorn/internal/runtime/Specialization
    //   113: dup
    //   114: ldc_w
    //   117: ldc_w org/openjdk/nashorn/internal/objects/NativeString$CharCodeAtLinkLogic
    //   120: iconst_0
    //   121: iconst_0
    //   122: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;Ljava/lang/Class;ZZ)V
    //   125: aastore
    //   126: dup
    //   127: iconst_1
    //   128: new org/openjdk/nashorn/internal/runtime/Specialization
    //   131: dup
    //   132: ldc_w
    //   135: ldc_w org/openjdk/nashorn/internal/objects/NativeString$CharCodeAtLinkLogic
    //   138: iconst_0
    //   139: iconst_0
    //   140: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;Ljava/lang/Class;ZZ)V
    //   143: aastore
    //   144: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   147: dup
    //   148: ldc_w 'String.prototype.charCodeAt'
    //   151: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   154: putfield charCodeAt : Ljava/lang/Object;
    //   157: aload_0
    //   158: ldc 'concat'
    //   160: ldc_w
    //   163: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   166: dup
    //   167: iconst_1
    //   168: invokevirtual setArity : (I)V
    //   171: dup
    //   172: ldc_w 'String.prototype.concat'
    //   175: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   178: putfield concat : Ljava/lang/Object;
    //   181: aload_0
    //   182: ldc 'indexOf'
    //   184: ldc_w
    //   187: iconst_3
    //   188: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   191: dup
    //   192: iconst_0
    //   193: new org/openjdk/nashorn/internal/runtime/Specialization
    //   196: dup
    //   197: ldc_w
    //   200: iconst_0
    //   201: iconst_0
    //   202: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   205: aastore
    //   206: dup
    //   207: iconst_1
    //   208: new org/openjdk/nashorn/internal/runtime/Specialization
    //   211: dup
    //   212: ldc_w
    //   215: iconst_0
    //   216: iconst_0
    //   217: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   220: aastore
    //   221: dup
    //   222: iconst_2
    //   223: new org/openjdk/nashorn/internal/runtime/Specialization
    //   226: dup
    //   227: ldc_w
    //   230: iconst_0
    //   231: iconst_0
    //   232: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   235: aastore
    //   236: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   239: dup
    //   240: iconst_1
    //   241: invokevirtual setArity : (I)V
    //   244: dup
    //   245: ldc_w 'String.prototype.indexOf'
    //   248: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   251: putfield indexOf : Ljava/lang/Object;
    //   254: aload_0
    //   255: ldc 'lastIndexOf'
    //   257: ldc_w
    //   260: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   263: dup
    //   264: iconst_1
    //   265: invokevirtual setArity : (I)V
    //   268: dup
    //   269: ldc_w 'String.prototype.lastIndexOf'
    //   272: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   275: putfield lastIndexOf : Ljava/lang/Object;
    //   278: aload_0
    //   279: ldc 'localeCompare'
    //   281: ldc_w
    //   284: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   287: dup
    //   288: ldc_w 'String.prototype.localeCompare'
    //   291: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   294: putfield localeCompare : Ljava/lang/Object;
    //   297: aload_0
    //   298: ldc 'match'
    //   300: ldc_w
    //   303: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   306: dup
    //   307: ldc_w 'String.prototype.match'
    //   310: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   313: putfield match : Ljava/lang/Object;
    //   316: aload_0
    //   317: ldc 'replace'
    //   319: ldc_w
    //   322: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   325: dup
    //   326: ldc_w 'String.prototype.replace'
    //   329: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   332: putfield replace : Ljava/lang/Object;
    //   335: aload_0
    //   336: ldc 'search'
    //   338: ldc_w
    //   341: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   344: dup
    //   345: ldc_w 'String.prototype.search'
    //   348: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   351: putfield search : Ljava/lang/Object;
    //   354: aload_0
    //   355: ldc 'slice'
    //   357: ldc_w
    //   360: iconst_4
    //   361: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   364: dup
    //   365: iconst_0
    //   366: new org/openjdk/nashorn/internal/runtime/Specialization
    //   369: dup
    //   370: ldc_w
    //   373: iconst_0
    //   374: iconst_0
    //   375: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   378: aastore
    //   379: dup
    //   380: iconst_1
    //   381: new org/openjdk/nashorn/internal/runtime/Specialization
    //   384: dup
    //   385: ldc_w
    //   388: iconst_0
    //   389: iconst_0
    //   390: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   393: aastore
    //   394: dup
    //   395: iconst_2
    //   396: new org/openjdk/nashorn/internal/runtime/Specialization
    //   399: dup
    //   400: ldc_w
    //   403: iconst_0
    //   404: iconst_0
    //   405: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   408: aastore
    //   409: dup
    //   410: iconst_3
    //   411: new org/openjdk/nashorn/internal/runtime/Specialization
    //   414: dup
    //   415: ldc_w
    //   418: iconst_0
    //   419: iconst_0
    //   420: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   423: aastore
    //   424: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   427: dup
    //   428: ldc_w 'String.prototype.slice'
    //   431: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   434: putfield slice : Ljava/lang/Object;
    //   437: aload_0
    //   438: ldc 'split'
    //   440: ldc_w
    //   443: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   446: dup
    //   447: ldc_w 'String.prototype.split'
    //   450: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   453: putfield split : Ljava/lang/Object;
    //   456: aload_0
    //   457: ldc 'substr'
    //   459: ldc_w
    //   462: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   465: dup
    //   466: ldc_w 'String.prototype.substr'
    //   469: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   472: putfield substr : Ljava/lang/Object;
    //   475: aload_0
    //   476: ldc 'substring'
    //   478: ldc_w
    //   481: iconst_4
    //   482: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   485: dup
    //   486: iconst_0
    //   487: new org/openjdk/nashorn/internal/runtime/Specialization
    //   490: dup
    //   491: ldc_w
    //   494: iconst_0
    //   495: iconst_0
    //   496: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   499: aastore
    //   500: dup
    //   501: iconst_1
    //   502: new org/openjdk/nashorn/internal/runtime/Specialization
    //   505: dup
    //   506: ldc_w
    //   509: iconst_0
    //   510: iconst_0
    //   511: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   514: aastore
    //   515: dup
    //   516: iconst_2
    //   517: new org/openjdk/nashorn/internal/runtime/Specialization
    //   520: dup
    //   521: ldc_w
    //   524: iconst_0
    //   525: iconst_0
    //   526: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   529: aastore
    //   530: dup
    //   531: iconst_3
    //   532: new org/openjdk/nashorn/internal/runtime/Specialization
    //   535: dup
    //   536: ldc_w
    //   539: iconst_0
    //   540: iconst_0
    //   541: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   544: aastore
    //   545: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   548: dup
    //   549: ldc_w 'String.prototype.substring'
    //   552: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   555: putfield substring : Ljava/lang/Object;
    //   558: aload_0
    //   559: ldc 'toLowerCase'
    //   561: ldc_w
    //   564: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   567: dup
    //   568: ldc_w 'String.prototype.toLowerCase'
    //   571: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   574: putfield toLowerCase : Ljava/lang/Object;
    //   577: aload_0
    //   578: ldc_w 'toLocaleLowerCase'
    //   581: ldc_w
    //   584: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   587: dup
    //   588: ldc_w 'String.prototype.toLocaleLowerCase'
    //   591: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   594: putfield toLocaleLowerCase : Ljava/lang/Object;
    //   597: aload_0
    //   598: ldc_w 'toUpperCase'
    //   601: ldc_w
    //   604: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   607: dup
    //   608: ldc_w 'String.prototype.toUpperCase'
    //   611: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   614: putfield toUpperCase : Ljava/lang/Object;
    //   617: aload_0
    //   618: ldc_w 'toLocaleUpperCase'
    //   621: ldc_w
    //   624: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   627: dup
    //   628: ldc_w 'String.prototype.toLocaleUpperCase'
    //   631: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   634: putfield toLocaleUpperCase : Ljava/lang/Object;
    //   637: aload_0
    //   638: ldc_w 'trim'
    //   641: ldc_w
    //   644: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   647: dup
    //   648: ldc_w 'String.prototype.trim'
    //   651: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   654: putfield trim : Ljava/lang/Object;
    //   657: aload_0
    //   658: ldc_w 'trimLeft'
    //   661: ldc_w
    //   664: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   667: dup
    //   668: ldc_w 'String.prototype.trimLeft'
    //   671: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   674: putfield trimLeft : Ljava/lang/Object;
    //   677: aload_0
    //   678: ldc_w 'trimRight'
    //   681: ldc_w
    //   684: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   687: dup
    //   688: ldc_w 'String.prototype.trimRight'
    //   691: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   694: putfield trimRight : Ljava/lang/Object;
    //   697: aload_0
    //   698: ldc_w 'Symbol[iterator]'
    //   701: ldc_w
    //   704: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   707: dup
    //   708: ldc_w 'String.prototype.@@iterator'
    //   711: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   714: putfield getIterator : Ljava/lang/Object;
    //   717: return
  }
  
  public String getClassName() {
    return "String";
  }
}
