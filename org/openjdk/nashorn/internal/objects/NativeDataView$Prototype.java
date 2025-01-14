package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.PrototypeObject;

final class NativeDataView$Prototype extends PrototypeObject {
  private Object getInt8;
  
  private Object getUint8;
  
  private Object getInt16;
  
  private Object getUint16;
  
  private Object getInt32;
  
  private Object getUint32;
  
  private Object getFloat32;
  
  private Object getFloat64;
  
  private Object setInt8;
  
  private Object setUint8;
  
  private Object setInt16;
  
  private Object setUint16;
  
  private Object setInt32;
  
  private Object setUint32;
  
  private Object setFloat32;
  
  private Object setFloat64;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$getInt8() {
    return this.getInt8;
  }
  
  public void S$getInt8(Object paramObject) {
    this.getInt8 = paramObject;
  }
  
  public Object G$getUint8() {
    return this.getUint8;
  }
  
  public void S$getUint8(Object paramObject) {
    this.getUint8 = paramObject;
  }
  
  public Object G$getInt16() {
    return this.getInt16;
  }
  
  public void S$getInt16(Object paramObject) {
    this.getInt16 = paramObject;
  }
  
  public Object G$getUint16() {
    return this.getUint16;
  }
  
  public void S$getUint16(Object paramObject) {
    this.getUint16 = paramObject;
  }
  
  public Object G$getInt32() {
    return this.getInt32;
  }
  
  public void S$getInt32(Object paramObject) {
    this.getInt32 = paramObject;
  }
  
  public Object G$getUint32() {
    return this.getUint32;
  }
  
  public void S$getUint32(Object paramObject) {
    this.getUint32 = paramObject;
  }
  
  public Object G$getFloat32() {
    return this.getFloat32;
  }
  
  public void S$getFloat32(Object paramObject) {
    this.getFloat32 = paramObject;
  }
  
  public Object G$getFloat64() {
    return this.getFloat64;
  }
  
  public void S$getFloat64(Object paramObject) {
    this.getFloat64 = paramObject;
  }
  
  public Object G$setInt8() {
    return this.setInt8;
  }
  
  public void S$setInt8(Object paramObject) {
    this.setInt8 = paramObject;
  }
  
  public Object G$setUint8() {
    return this.setUint8;
  }
  
  public void S$setUint8(Object paramObject) {
    this.setUint8 = paramObject;
  }
  
  public Object G$setInt16() {
    return this.setInt16;
  }
  
  public void S$setInt16(Object paramObject) {
    this.setInt16 = paramObject;
  }
  
  public Object G$setUint16() {
    return this.setUint16;
  }
  
  public void S$setUint16(Object paramObject) {
    this.setUint16 = paramObject;
  }
  
  public Object G$setInt32() {
    return this.setInt32;
  }
  
  public void S$setInt32(Object paramObject) {
    this.setInt32 = paramObject;
  }
  
  public Object G$setUint32() {
    return this.setUint32;
  }
  
  public void S$setUint32(Object paramObject) {
    this.setUint32 = paramObject;
  }
  
  public Object G$setFloat32() {
    return this.setFloat32;
  }
  
  public void S$setFloat32(Object paramObject) {
    this.setFloat32 = paramObject;
  }
  
  public Object G$setFloat64() {
    return this.setFloat64;
  }
  
  public void S$setFloat64(Object paramObject) {
    this.setFloat64 = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: bipush #16
    //   6: invokespecial <init> : (I)V
    //   9: dup
    //   10: ldc 'getInt8'
    //   12: iconst_2
    //   13: ldc
    //   15: ldc
    //   17: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   20: invokeinterface add : (Ljava/lang/Object;)Z
    //   25: pop
    //   26: dup
    //   27: ldc 'getUint8'
    //   29: iconst_2
    //   30: ldc
    //   32: ldc
    //   34: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   37: invokeinterface add : (Ljava/lang/Object;)Z
    //   42: pop
    //   43: dup
    //   44: ldc 'getInt16'
    //   46: iconst_2
    //   47: ldc
    //   49: ldc
    //   51: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   54: invokeinterface add : (Ljava/lang/Object;)Z
    //   59: pop
    //   60: dup
    //   61: ldc 'getUint16'
    //   63: iconst_2
    //   64: ldc
    //   66: ldc
    //   68: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   71: invokeinterface add : (Ljava/lang/Object;)Z
    //   76: pop
    //   77: dup
    //   78: ldc 'getInt32'
    //   80: iconst_2
    //   81: ldc
    //   83: ldc
    //   85: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   88: invokeinterface add : (Ljava/lang/Object;)Z
    //   93: pop
    //   94: dup
    //   95: ldc 'getUint32'
    //   97: iconst_2
    //   98: ldc
    //   100: ldc
    //   102: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   105: invokeinterface add : (Ljava/lang/Object;)Z
    //   110: pop
    //   111: dup
    //   112: ldc 'getFloat32'
    //   114: iconst_2
    //   115: ldc
    //   117: ldc
    //   119: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   122: invokeinterface add : (Ljava/lang/Object;)Z
    //   127: pop
    //   128: dup
    //   129: ldc 'getFloat64'
    //   131: iconst_2
    //   132: ldc
    //   134: ldc
    //   136: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   139: invokeinterface add : (Ljava/lang/Object;)Z
    //   144: pop
    //   145: dup
    //   146: ldc 'setInt8'
    //   148: iconst_2
    //   149: ldc
    //   151: ldc
    //   153: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   156: invokeinterface add : (Ljava/lang/Object;)Z
    //   161: pop
    //   162: dup
    //   163: ldc 'setUint8'
    //   165: iconst_2
    //   166: ldc
    //   168: ldc
    //   170: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   173: invokeinterface add : (Ljava/lang/Object;)Z
    //   178: pop
    //   179: dup
    //   180: ldc 'setInt16'
    //   182: iconst_2
    //   183: ldc
    //   185: ldc
    //   187: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   190: invokeinterface add : (Ljava/lang/Object;)Z
    //   195: pop
    //   196: dup
    //   197: ldc 'setUint16'
    //   199: iconst_2
    //   200: ldc
    //   202: ldc
    //   204: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   207: invokeinterface add : (Ljava/lang/Object;)Z
    //   212: pop
    //   213: dup
    //   214: ldc 'setInt32'
    //   216: iconst_2
    //   217: ldc
    //   219: ldc
    //   221: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   224: invokeinterface add : (Ljava/lang/Object;)Z
    //   229: pop
    //   230: dup
    //   231: ldc 'setUint32'
    //   233: iconst_2
    //   234: ldc
    //   236: ldc
    //   238: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   241: invokeinterface add : (Ljava/lang/Object;)Z
    //   246: pop
    //   247: dup
    //   248: ldc 'setFloat32'
    //   250: iconst_2
    //   251: ldc
    //   253: ldc
    //   255: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   258: invokeinterface add : (Ljava/lang/Object;)Z
    //   263: pop
    //   264: dup
    //   265: ldc 'setFloat64'
    //   267: iconst_2
    //   268: ldc
    //   270: ldc
    //   272: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   275: invokeinterface add : (Ljava/lang/Object;)Z
    //   280: pop
    //   281: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   284: putstatic org/openjdk/nashorn/internal/objects/NativeDataView$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   287: return
  }
  
  NativeDataView$Prototype() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic org/openjdk/nashorn/internal/objects/NativeDataView$Prototype.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   4: invokespecial <init> : (Lorg/openjdk/nashorn/internal/runtime/PropertyMap;)V
    //   7: aload_0
    //   8: ldc 'getInt8'
    //   10: ldc
    //   12: iconst_1
    //   13: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   16: dup
    //   17: iconst_0
    //   18: new org/openjdk/nashorn/internal/runtime/Specialization
    //   21: dup
    //   22: ldc
    //   24: iconst_0
    //   25: iconst_0
    //   26: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   29: aastore
    //   30: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   33: dup
    //   34: ldc_w 'DataView.prototype.getInt8'
    //   37: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   40: putfield getInt8 : Ljava/lang/Object;
    //   43: aload_0
    //   44: ldc 'getUint8'
    //   46: ldc_w
    //   49: iconst_1
    //   50: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   53: dup
    //   54: iconst_0
    //   55: new org/openjdk/nashorn/internal/runtime/Specialization
    //   58: dup
    //   59: ldc_w
    //   62: iconst_0
    //   63: iconst_0
    //   64: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   67: aastore
    //   68: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   71: dup
    //   72: ldc_w 'DataView.prototype.getUint8'
    //   75: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   78: putfield getUint8 : Ljava/lang/Object;
    //   81: aload_0
    //   82: ldc 'getInt16'
    //   84: ldc_w
    //   87: iconst_2
    //   88: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   91: dup
    //   92: iconst_0
    //   93: new org/openjdk/nashorn/internal/runtime/Specialization
    //   96: dup
    //   97: ldc_w
    //   100: iconst_0
    //   101: iconst_0
    //   102: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   105: aastore
    //   106: dup
    //   107: iconst_1
    //   108: new org/openjdk/nashorn/internal/runtime/Specialization
    //   111: dup
    //   112: ldc_w
    //   115: iconst_0
    //   116: iconst_0
    //   117: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   120: aastore
    //   121: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   124: dup
    //   125: iconst_1
    //   126: invokevirtual setArity : (I)V
    //   129: dup
    //   130: ldc_w 'DataView.prototype.getInt16'
    //   133: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   136: putfield getInt16 : Ljava/lang/Object;
    //   139: aload_0
    //   140: ldc 'getUint16'
    //   142: ldc_w
    //   145: iconst_2
    //   146: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   149: dup
    //   150: iconst_0
    //   151: new org/openjdk/nashorn/internal/runtime/Specialization
    //   154: dup
    //   155: ldc_w
    //   158: iconst_0
    //   159: iconst_0
    //   160: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   163: aastore
    //   164: dup
    //   165: iconst_1
    //   166: new org/openjdk/nashorn/internal/runtime/Specialization
    //   169: dup
    //   170: ldc_w
    //   173: iconst_0
    //   174: iconst_0
    //   175: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   178: aastore
    //   179: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   182: dup
    //   183: iconst_1
    //   184: invokevirtual setArity : (I)V
    //   187: dup
    //   188: ldc_w 'DataView.prototype.getUint16'
    //   191: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   194: putfield getUint16 : Ljava/lang/Object;
    //   197: aload_0
    //   198: ldc 'getInt32'
    //   200: ldc_w
    //   203: iconst_2
    //   204: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   207: dup
    //   208: iconst_0
    //   209: new org/openjdk/nashorn/internal/runtime/Specialization
    //   212: dup
    //   213: ldc_w
    //   216: iconst_0
    //   217: iconst_0
    //   218: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   221: aastore
    //   222: dup
    //   223: iconst_1
    //   224: new org/openjdk/nashorn/internal/runtime/Specialization
    //   227: dup
    //   228: ldc_w
    //   231: iconst_0
    //   232: iconst_0
    //   233: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   236: aastore
    //   237: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   240: dup
    //   241: iconst_1
    //   242: invokevirtual setArity : (I)V
    //   245: dup
    //   246: ldc_w 'DataView.prototype.getInt32'
    //   249: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   252: putfield getInt32 : Ljava/lang/Object;
    //   255: aload_0
    //   256: ldc 'getUint32'
    //   258: ldc_w
    //   261: iconst_2
    //   262: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   265: dup
    //   266: iconst_0
    //   267: new org/openjdk/nashorn/internal/runtime/Specialization
    //   270: dup
    //   271: ldc_w
    //   274: iconst_0
    //   275: iconst_0
    //   276: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   279: aastore
    //   280: dup
    //   281: iconst_1
    //   282: new org/openjdk/nashorn/internal/runtime/Specialization
    //   285: dup
    //   286: ldc_w
    //   289: iconst_0
    //   290: iconst_0
    //   291: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   294: aastore
    //   295: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   298: dup
    //   299: iconst_1
    //   300: invokevirtual setArity : (I)V
    //   303: dup
    //   304: ldc_w 'DataView.prototype.getUint32'
    //   307: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   310: putfield getUint32 : Ljava/lang/Object;
    //   313: aload_0
    //   314: ldc 'getFloat32'
    //   316: ldc_w
    //   319: iconst_2
    //   320: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   323: dup
    //   324: iconst_0
    //   325: new org/openjdk/nashorn/internal/runtime/Specialization
    //   328: dup
    //   329: ldc_w
    //   332: iconst_0
    //   333: iconst_0
    //   334: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   337: aastore
    //   338: dup
    //   339: iconst_1
    //   340: new org/openjdk/nashorn/internal/runtime/Specialization
    //   343: dup
    //   344: ldc_w
    //   347: iconst_0
    //   348: iconst_0
    //   349: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   352: aastore
    //   353: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   356: dup
    //   357: iconst_1
    //   358: invokevirtual setArity : (I)V
    //   361: dup
    //   362: ldc_w 'DataView.prototype.getFloat32'
    //   365: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   368: putfield getFloat32 : Ljava/lang/Object;
    //   371: aload_0
    //   372: ldc 'getFloat64'
    //   374: ldc_w
    //   377: iconst_2
    //   378: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   381: dup
    //   382: iconst_0
    //   383: new org/openjdk/nashorn/internal/runtime/Specialization
    //   386: dup
    //   387: ldc_w
    //   390: iconst_0
    //   391: iconst_0
    //   392: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   395: aastore
    //   396: dup
    //   397: iconst_1
    //   398: new org/openjdk/nashorn/internal/runtime/Specialization
    //   401: dup
    //   402: ldc_w
    //   405: iconst_0
    //   406: iconst_0
    //   407: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   410: aastore
    //   411: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   414: dup
    //   415: iconst_1
    //   416: invokevirtual setArity : (I)V
    //   419: dup
    //   420: ldc_w 'DataView.prototype.getFloat64'
    //   423: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   426: putfield getFloat64 : Ljava/lang/Object;
    //   429: aload_0
    //   430: ldc 'setInt8'
    //   432: ldc_w
    //   435: iconst_1
    //   436: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   439: dup
    //   440: iconst_0
    //   441: new org/openjdk/nashorn/internal/runtime/Specialization
    //   444: dup
    //   445: ldc_w
    //   448: iconst_0
    //   449: iconst_0
    //   450: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   453: aastore
    //   454: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   457: dup
    //   458: iconst_2
    //   459: invokevirtual setArity : (I)V
    //   462: dup
    //   463: ldc_w 'DataView.prototype.setInt8'
    //   466: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   469: putfield setInt8 : Ljava/lang/Object;
    //   472: aload_0
    //   473: ldc 'setUint8'
    //   475: ldc_w
    //   478: iconst_1
    //   479: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   482: dup
    //   483: iconst_0
    //   484: new org/openjdk/nashorn/internal/runtime/Specialization
    //   487: dup
    //   488: ldc_w
    //   491: iconst_0
    //   492: iconst_0
    //   493: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   496: aastore
    //   497: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   500: dup
    //   501: iconst_2
    //   502: invokevirtual setArity : (I)V
    //   505: dup
    //   506: ldc_w 'DataView.prototype.setUint8'
    //   509: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   512: putfield setUint8 : Ljava/lang/Object;
    //   515: aload_0
    //   516: ldc 'setInt16'
    //   518: ldc_w
    //   521: iconst_2
    //   522: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   525: dup
    //   526: iconst_0
    //   527: new org/openjdk/nashorn/internal/runtime/Specialization
    //   530: dup
    //   531: ldc_w
    //   534: iconst_0
    //   535: iconst_0
    //   536: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   539: aastore
    //   540: dup
    //   541: iconst_1
    //   542: new org/openjdk/nashorn/internal/runtime/Specialization
    //   545: dup
    //   546: ldc_w
    //   549: iconst_0
    //   550: iconst_0
    //   551: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   554: aastore
    //   555: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   558: dup
    //   559: iconst_2
    //   560: invokevirtual setArity : (I)V
    //   563: dup
    //   564: ldc_w 'DataView.prototype.setInt16'
    //   567: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   570: putfield setInt16 : Ljava/lang/Object;
    //   573: aload_0
    //   574: ldc 'setUint16'
    //   576: ldc_w
    //   579: iconst_2
    //   580: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   583: dup
    //   584: iconst_0
    //   585: new org/openjdk/nashorn/internal/runtime/Specialization
    //   588: dup
    //   589: ldc_w
    //   592: iconst_0
    //   593: iconst_0
    //   594: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   597: aastore
    //   598: dup
    //   599: iconst_1
    //   600: new org/openjdk/nashorn/internal/runtime/Specialization
    //   603: dup
    //   604: ldc_w
    //   607: iconst_0
    //   608: iconst_0
    //   609: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   612: aastore
    //   613: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   616: dup
    //   617: iconst_2
    //   618: invokevirtual setArity : (I)V
    //   621: dup
    //   622: ldc_w 'DataView.prototype.setUint16'
    //   625: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   628: putfield setUint16 : Ljava/lang/Object;
    //   631: aload_0
    //   632: ldc 'setInt32'
    //   634: ldc_w
    //   637: iconst_2
    //   638: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   641: dup
    //   642: iconst_0
    //   643: new org/openjdk/nashorn/internal/runtime/Specialization
    //   646: dup
    //   647: ldc_w
    //   650: iconst_0
    //   651: iconst_0
    //   652: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   655: aastore
    //   656: dup
    //   657: iconst_1
    //   658: new org/openjdk/nashorn/internal/runtime/Specialization
    //   661: dup
    //   662: ldc_w
    //   665: iconst_0
    //   666: iconst_0
    //   667: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   670: aastore
    //   671: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   674: dup
    //   675: iconst_2
    //   676: invokevirtual setArity : (I)V
    //   679: dup
    //   680: ldc_w 'DataView.prototype.setInt32'
    //   683: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   686: putfield setInt32 : Ljava/lang/Object;
    //   689: aload_0
    //   690: ldc 'setUint32'
    //   692: ldc_w
    //   695: iconst_2
    //   696: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   699: dup
    //   700: iconst_0
    //   701: new org/openjdk/nashorn/internal/runtime/Specialization
    //   704: dup
    //   705: ldc_w
    //   708: iconst_0
    //   709: iconst_0
    //   710: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   713: aastore
    //   714: dup
    //   715: iconst_1
    //   716: new org/openjdk/nashorn/internal/runtime/Specialization
    //   719: dup
    //   720: ldc_w
    //   723: iconst_0
    //   724: iconst_0
    //   725: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   728: aastore
    //   729: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   732: dup
    //   733: iconst_2
    //   734: invokevirtual setArity : (I)V
    //   737: dup
    //   738: ldc_w 'DataView.prototype.setUint32'
    //   741: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   744: putfield setUint32 : Ljava/lang/Object;
    //   747: aload_0
    //   748: ldc 'setFloat32'
    //   750: ldc_w
    //   753: iconst_2
    //   754: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   757: dup
    //   758: iconst_0
    //   759: new org/openjdk/nashorn/internal/runtime/Specialization
    //   762: dup
    //   763: ldc_w
    //   766: iconst_0
    //   767: iconst_0
    //   768: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   771: aastore
    //   772: dup
    //   773: iconst_1
    //   774: new org/openjdk/nashorn/internal/runtime/Specialization
    //   777: dup
    //   778: ldc_w
    //   781: iconst_0
    //   782: iconst_0
    //   783: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   786: aastore
    //   787: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   790: dup
    //   791: iconst_2
    //   792: invokevirtual setArity : (I)V
    //   795: dup
    //   796: ldc_w 'DataView.prototype.setFloat32'
    //   799: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   802: putfield setFloat32 : Ljava/lang/Object;
    //   805: aload_0
    //   806: ldc 'setFloat64'
    //   808: ldc_w
    //   811: iconst_2
    //   812: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   815: dup
    //   816: iconst_0
    //   817: new org/openjdk/nashorn/internal/runtime/Specialization
    //   820: dup
    //   821: ldc_w
    //   824: iconst_0
    //   825: iconst_0
    //   826: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   829: aastore
    //   830: dup
    //   831: iconst_1
    //   832: new org/openjdk/nashorn/internal/runtime/Specialization
    //   835: dup
    //   836: ldc_w
    //   839: iconst_0
    //   840: iconst_0
    //   841: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   844: aastore
    //   845: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   848: dup
    //   849: iconst_2
    //   850: invokevirtual setArity : (I)V
    //   853: dup
    //   854: ldc_w 'DataView.prototype.setFloat64'
    //   857: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   860: putfield setFloat64 : Ljava/lang/Object;
    //   863: return
  }
  
  public String getClassName() {
    return "DataView";
  }
}
