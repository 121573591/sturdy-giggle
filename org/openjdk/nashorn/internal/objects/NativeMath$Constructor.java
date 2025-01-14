package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;

final class NativeMath$Constructor extends ScriptObject {
  private Object abs;
  
  private Object acos;
  
  private Object asin;
  
  private Object atan;
  
  private Object atan2;
  
  private Object ceil;
  
  private Object cos;
  
  private Object exp;
  
  private Object floor;
  
  private Object log;
  
  private Object max;
  
  private Object min;
  
  private Object pow;
  
  private Object random;
  
  private Object round;
  
  private Object sin;
  
  private Object sqrt;
  
  private Object tan;
  
  private static final PropertyMap $nasgenmap$;
  
  public double G$E() {
    return NativeMath.E;
  }
  
  public double G$LN10() {
    return NativeMath.LN10;
  }
  
  public double G$LN2() {
    return NativeMath.LN2;
  }
  
  public double G$LOG2E() {
    return NativeMath.LOG2E;
  }
  
  public double G$LOG10E() {
    return NativeMath.LOG10E;
  }
  
  public double G$PI() {
    return NativeMath.PI;
  }
  
  public double G$SQRT1_2() {
    return NativeMath.SQRT1_2;
  }
  
  public double G$SQRT2() {
    return NativeMath.SQRT2;
  }
  
  public Object G$abs() {
    return this.abs;
  }
  
  public void S$abs(Object paramObject) {
    this.abs = paramObject;
  }
  
  public Object G$acos() {
    return this.acos;
  }
  
  public void S$acos(Object paramObject) {
    this.acos = paramObject;
  }
  
  public Object G$asin() {
    return this.asin;
  }
  
  public void S$asin(Object paramObject) {
    this.asin = paramObject;
  }
  
  public Object G$atan() {
    return this.atan;
  }
  
  public void S$atan(Object paramObject) {
    this.atan = paramObject;
  }
  
  public Object G$atan2() {
    return this.atan2;
  }
  
  public void S$atan2(Object paramObject) {
    this.atan2 = paramObject;
  }
  
  public Object G$ceil() {
    return this.ceil;
  }
  
  public void S$ceil(Object paramObject) {
    this.ceil = paramObject;
  }
  
  public Object G$cos() {
    return this.cos;
  }
  
  public void S$cos(Object paramObject) {
    this.cos = paramObject;
  }
  
  public Object G$exp() {
    return this.exp;
  }
  
  public void S$exp(Object paramObject) {
    this.exp = paramObject;
  }
  
  public Object G$floor() {
    return this.floor;
  }
  
  public void S$floor(Object paramObject) {
    this.floor = paramObject;
  }
  
  public Object G$log() {
    return this.log;
  }
  
  public void S$log(Object paramObject) {
    this.log = paramObject;
  }
  
  public Object G$max() {
    return this.max;
  }
  
  public void S$max(Object paramObject) {
    this.max = paramObject;
  }
  
  public Object G$min() {
    return this.min;
  }
  
  public void S$min(Object paramObject) {
    this.min = paramObject;
  }
  
  public Object G$pow() {
    return this.pow;
  }
  
  public void S$pow(Object paramObject) {
    this.pow = paramObject;
  }
  
  public Object G$random() {
    return this.random;
  }
  
  public void S$random(Object paramObject) {
    this.random = paramObject;
  }
  
  public Object G$round() {
    return this.round;
  }
  
  public void S$round(Object paramObject) {
    this.round = paramObject;
  }
  
  public Object G$sin() {
    return this.sin;
  }
  
  public void S$sin(Object paramObject) {
    this.sin = paramObject;
  }
  
  public Object G$sqrt() {
    return this.sqrt;
  }
  
  public void S$sqrt(Object paramObject) {
    this.sqrt = paramObject;
  }
  
  public Object G$tan() {
    return this.tan;
  }
  
  public void S$tan(Object paramObject) {
    this.tan = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: bipush #26
    //   6: invokespecial <init> : (I)V
    //   9: dup
    //   10: ldc 'E'
    //   12: bipush #7
    //   14: ldc
    //   16: aconst_null
    //   17: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   20: invokeinterface add : (Ljava/lang/Object;)Z
    //   25: pop
    //   26: dup
    //   27: ldc 'LN10'
    //   29: bipush #7
    //   31: ldc
    //   33: aconst_null
    //   34: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   37: invokeinterface add : (Ljava/lang/Object;)Z
    //   42: pop
    //   43: dup
    //   44: ldc 'LN2'
    //   46: bipush #7
    //   48: ldc
    //   50: aconst_null
    //   51: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   54: invokeinterface add : (Ljava/lang/Object;)Z
    //   59: pop
    //   60: dup
    //   61: ldc 'LOG2E'
    //   63: bipush #7
    //   65: ldc
    //   67: aconst_null
    //   68: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   71: invokeinterface add : (Ljava/lang/Object;)Z
    //   76: pop
    //   77: dup
    //   78: ldc 'LOG10E'
    //   80: bipush #7
    //   82: ldc
    //   84: aconst_null
    //   85: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   88: invokeinterface add : (Ljava/lang/Object;)Z
    //   93: pop
    //   94: dup
    //   95: ldc 'PI'
    //   97: bipush #7
    //   99: ldc
    //   101: aconst_null
    //   102: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   105: invokeinterface add : (Ljava/lang/Object;)Z
    //   110: pop
    //   111: dup
    //   112: ldc 'SQRT1_2'
    //   114: bipush #7
    //   116: ldc
    //   118: aconst_null
    //   119: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   122: invokeinterface add : (Ljava/lang/Object;)Z
    //   127: pop
    //   128: dup
    //   129: ldc 'SQRT2'
    //   131: bipush #7
    //   133: ldc
    //   135: aconst_null
    //   136: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   139: invokeinterface add : (Ljava/lang/Object;)Z
    //   144: pop
    //   145: dup
    //   146: ldc 'abs'
    //   148: iconst_2
    //   149: ldc
    //   151: ldc
    //   153: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   156: invokeinterface add : (Ljava/lang/Object;)Z
    //   161: pop
    //   162: dup
    //   163: ldc 'acos'
    //   165: iconst_2
    //   166: ldc
    //   168: ldc
    //   170: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   173: invokeinterface add : (Ljava/lang/Object;)Z
    //   178: pop
    //   179: dup
    //   180: ldc 'asin'
    //   182: iconst_2
    //   183: ldc
    //   185: ldc
    //   187: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   190: invokeinterface add : (Ljava/lang/Object;)Z
    //   195: pop
    //   196: dup
    //   197: ldc 'atan'
    //   199: iconst_2
    //   200: ldc
    //   202: ldc
    //   204: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   207: invokeinterface add : (Ljava/lang/Object;)Z
    //   212: pop
    //   213: dup
    //   214: ldc 'atan2'
    //   216: iconst_2
    //   217: ldc
    //   219: ldc
    //   221: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   224: invokeinterface add : (Ljava/lang/Object;)Z
    //   229: pop
    //   230: dup
    //   231: ldc 'ceil'
    //   233: iconst_2
    //   234: ldc
    //   236: ldc
    //   238: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   241: invokeinterface add : (Ljava/lang/Object;)Z
    //   246: pop
    //   247: dup
    //   248: ldc 'cos'
    //   250: iconst_2
    //   251: ldc
    //   253: ldc
    //   255: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   258: invokeinterface add : (Ljava/lang/Object;)Z
    //   263: pop
    //   264: dup
    //   265: ldc 'exp'
    //   267: iconst_2
    //   268: ldc
    //   270: ldc
    //   272: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   275: invokeinterface add : (Ljava/lang/Object;)Z
    //   280: pop
    //   281: dup
    //   282: ldc 'floor'
    //   284: iconst_2
    //   285: ldc
    //   287: ldc
    //   289: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   292: invokeinterface add : (Ljava/lang/Object;)Z
    //   297: pop
    //   298: dup
    //   299: ldc 'log'
    //   301: iconst_2
    //   302: ldc
    //   304: ldc_w
    //   307: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   310: invokeinterface add : (Ljava/lang/Object;)Z
    //   315: pop
    //   316: dup
    //   317: ldc_w 'max'
    //   320: iconst_2
    //   321: ldc_w
    //   324: ldc_w
    //   327: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   330: invokeinterface add : (Ljava/lang/Object;)Z
    //   335: pop
    //   336: dup
    //   337: ldc_w 'min'
    //   340: iconst_2
    //   341: ldc_w
    //   344: ldc_w
    //   347: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   350: invokeinterface add : (Ljava/lang/Object;)Z
    //   355: pop
    //   356: dup
    //   357: ldc_w 'pow'
    //   360: iconst_2
    //   361: ldc_w
    //   364: ldc_w
    //   367: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   370: invokeinterface add : (Ljava/lang/Object;)Z
    //   375: pop
    //   376: dup
    //   377: ldc_w 'random'
    //   380: iconst_2
    //   381: ldc_w
    //   384: ldc_w
    //   387: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   390: invokeinterface add : (Ljava/lang/Object;)Z
    //   395: pop
    //   396: dup
    //   397: ldc_w 'round'
    //   400: iconst_2
    //   401: ldc_w
    //   404: ldc_w
    //   407: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   410: invokeinterface add : (Ljava/lang/Object;)Z
    //   415: pop
    //   416: dup
    //   417: ldc_w 'sin'
    //   420: iconst_2
    //   421: ldc_w
    //   424: ldc_w
    //   427: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   430: invokeinterface add : (Ljava/lang/Object;)Z
    //   435: pop
    //   436: dup
    //   437: ldc_w 'sqrt'
    //   440: iconst_2
    //   441: ldc_w
    //   444: ldc_w
    //   447: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   450: invokeinterface add : (Ljava/lang/Object;)Z
    //   455: pop
    //   456: dup
    //   457: ldc_w 'tan'
    //   460: iconst_2
    //   461: ldc_w
    //   464: ldc_w
    //   467: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   470: invokeinterface add : (Ljava/lang/Object;)Z
    //   475: pop
    //   476: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   479: putstatic org/openjdk/nashorn/internal/objects/NativeMath$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   482: return
  }
  
  NativeMath$Constructor() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic org/openjdk/nashorn/internal/objects/NativeMath$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   4: invokespecial <init> : (Lorg/openjdk/nashorn/internal/runtime/PropertyMap;)V
    //   7: aload_0
    //   8: ldc 'abs'
    //   10: ldc_w
    //   13: iconst_3
    //   14: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   17: dup
    //   18: iconst_0
    //   19: new org/openjdk/nashorn/internal/runtime/Specialization
    //   22: dup
    //   23: ldc_w
    //   26: iconst_0
    //   27: iconst_0
    //   28: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   31: aastore
    //   32: dup
    //   33: iconst_1
    //   34: new org/openjdk/nashorn/internal/runtime/Specialization
    //   37: dup
    //   38: ldc_w
    //   41: iconst_0
    //   42: iconst_0
    //   43: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   46: aastore
    //   47: dup
    //   48: iconst_2
    //   49: new org/openjdk/nashorn/internal/runtime/Specialization
    //   52: dup
    //   53: ldc_w
    //   56: iconst_0
    //   57: iconst_0
    //   58: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   61: aastore
    //   62: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   65: dup
    //   66: ldc_w 'Math.abs'
    //   69: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   72: putfield abs : Ljava/lang/Object;
    //   75: aload_0
    //   76: ldc 'acos'
    //   78: ldc_w
    //   81: iconst_1
    //   82: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   85: dup
    //   86: iconst_0
    //   87: new org/openjdk/nashorn/internal/runtime/Specialization
    //   90: dup
    //   91: ldc_w
    //   94: iconst_0
    //   95: iconst_0
    //   96: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   99: aastore
    //   100: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   103: dup
    //   104: ldc_w 'Math.acos'
    //   107: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   110: putfield acos : Ljava/lang/Object;
    //   113: aload_0
    //   114: ldc 'asin'
    //   116: ldc_w
    //   119: iconst_1
    //   120: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   123: dup
    //   124: iconst_0
    //   125: new org/openjdk/nashorn/internal/runtime/Specialization
    //   128: dup
    //   129: ldc_w
    //   132: iconst_0
    //   133: iconst_0
    //   134: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   137: aastore
    //   138: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   141: dup
    //   142: ldc_w 'Math.asin'
    //   145: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   148: putfield asin : Ljava/lang/Object;
    //   151: aload_0
    //   152: ldc 'atan'
    //   154: ldc_w
    //   157: iconst_1
    //   158: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   161: dup
    //   162: iconst_0
    //   163: new org/openjdk/nashorn/internal/runtime/Specialization
    //   166: dup
    //   167: ldc_w
    //   170: iconst_0
    //   171: iconst_0
    //   172: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   175: aastore
    //   176: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   179: dup
    //   180: ldc_w 'Math.atan'
    //   183: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   186: putfield atan : Ljava/lang/Object;
    //   189: aload_0
    //   190: ldc 'atan2'
    //   192: ldc_w
    //   195: iconst_1
    //   196: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   199: dup
    //   200: iconst_0
    //   201: new org/openjdk/nashorn/internal/runtime/Specialization
    //   204: dup
    //   205: ldc_w
    //   208: iconst_0
    //   209: iconst_0
    //   210: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   213: aastore
    //   214: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   217: dup
    //   218: ldc_w 'Math.atan2'
    //   221: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   224: putfield atan2 : Ljava/lang/Object;
    //   227: aload_0
    //   228: ldc 'ceil'
    //   230: ldc_w
    //   233: iconst_3
    //   234: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   237: dup
    //   238: iconst_0
    //   239: new org/openjdk/nashorn/internal/runtime/Specialization
    //   242: dup
    //   243: ldc_w
    //   246: iconst_0
    //   247: iconst_0
    //   248: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   251: aastore
    //   252: dup
    //   253: iconst_1
    //   254: new org/openjdk/nashorn/internal/runtime/Specialization
    //   257: dup
    //   258: ldc_w
    //   261: iconst_0
    //   262: iconst_0
    //   263: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   266: aastore
    //   267: dup
    //   268: iconst_2
    //   269: new org/openjdk/nashorn/internal/runtime/Specialization
    //   272: dup
    //   273: ldc_w
    //   276: iconst_0
    //   277: iconst_0
    //   278: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   281: aastore
    //   282: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   285: dup
    //   286: ldc_w 'Math.ceil'
    //   289: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   292: putfield ceil : Ljava/lang/Object;
    //   295: aload_0
    //   296: ldc 'cos'
    //   298: ldc_w
    //   301: iconst_1
    //   302: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   305: dup
    //   306: iconst_0
    //   307: new org/openjdk/nashorn/internal/runtime/Specialization
    //   310: dup
    //   311: ldc_w
    //   314: iconst_0
    //   315: iconst_0
    //   316: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   319: aastore
    //   320: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   323: dup
    //   324: ldc_w 'Math.cos'
    //   327: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   330: putfield cos : Ljava/lang/Object;
    //   333: aload_0
    //   334: ldc 'exp'
    //   336: ldc_w
    //   339: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   342: dup
    //   343: ldc_w 'Math.exp'
    //   346: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   349: putfield exp : Ljava/lang/Object;
    //   352: aload_0
    //   353: ldc 'floor'
    //   355: ldc_w
    //   358: iconst_3
    //   359: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   362: dup
    //   363: iconst_0
    //   364: new org/openjdk/nashorn/internal/runtime/Specialization
    //   367: dup
    //   368: ldc_w
    //   371: iconst_0
    //   372: iconst_0
    //   373: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   376: aastore
    //   377: dup
    //   378: iconst_1
    //   379: new org/openjdk/nashorn/internal/runtime/Specialization
    //   382: dup
    //   383: ldc_w
    //   386: iconst_0
    //   387: iconst_0
    //   388: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   391: aastore
    //   392: dup
    //   393: iconst_2
    //   394: new org/openjdk/nashorn/internal/runtime/Specialization
    //   397: dup
    //   398: ldc_w
    //   401: iconst_0
    //   402: iconst_0
    //   403: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   406: aastore
    //   407: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   410: dup
    //   411: ldc_w 'Math.floor'
    //   414: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   417: putfield floor : Ljava/lang/Object;
    //   420: aload_0
    //   421: ldc 'log'
    //   423: ldc_w
    //   426: iconst_1
    //   427: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   430: dup
    //   431: iconst_0
    //   432: new org/openjdk/nashorn/internal/runtime/Specialization
    //   435: dup
    //   436: ldc_w
    //   439: iconst_0
    //   440: iconst_0
    //   441: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   444: aastore
    //   445: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   448: dup
    //   449: ldc_w 'Math.log'
    //   452: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   455: putfield log : Ljava/lang/Object;
    //   458: aload_0
    //   459: ldc_w 'max'
    //   462: ldc_w
    //   465: iconst_5
    //   466: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   469: dup
    //   470: iconst_0
    //   471: new org/openjdk/nashorn/internal/runtime/Specialization
    //   474: dup
    //   475: ldc_w
    //   478: iconst_0
    //   479: iconst_0
    //   480: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   483: aastore
    //   484: dup
    //   485: iconst_1
    //   486: new org/openjdk/nashorn/internal/runtime/Specialization
    //   489: dup
    //   490: ldc_w
    //   493: iconst_0
    //   494: iconst_0
    //   495: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   498: aastore
    //   499: dup
    //   500: iconst_2
    //   501: new org/openjdk/nashorn/internal/runtime/Specialization
    //   504: dup
    //   505: ldc_w
    //   508: iconst_0
    //   509: iconst_0
    //   510: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   513: aastore
    //   514: dup
    //   515: iconst_3
    //   516: new org/openjdk/nashorn/internal/runtime/Specialization
    //   519: dup
    //   520: ldc_w
    //   523: iconst_0
    //   524: iconst_0
    //   525: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   528: aastore
    //   529: dup
    //   530: iconst_4
    //   531: new org/openjdk/nashorn/internal/runtime/Specialization
    //   534: dup
    //   535: ldc_w
    //   538: iconst_0
    //   539: iconst_0
    //   540: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   543: aastore
    //   544: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   547: dup
    //   548: iconst_2
    //   549: invokevirtual setArity : (I)V
    //   552: dup
    //   553: ldc_w 'Math.max'
    //   556: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   559: putfield max : Ljava/lang/Object;
    //   562: aload_0
    //   563: ldc_w 'min'
    //   566: ldc_w
    //   569: iconst_5
    //   570: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   573: dup
    //   574: iconst_0
    //   575: new org/openjdk/nashorn/internal/runtime/Specialization
    //   578: dup
    //   579: ldc_w
    //   582: iconst_0
    //   583: iconst_0
    //   584: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   587: aastore
    //   588: dup
    //   589: iconst_1
    //   590: new org/openjdk/nashorn/internal/runtime/Specialization
    //   593: dup
    //   594: ldc_w
    //   597: iconst_0
    //   598: iconst_0
    //   599: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   602: aastore
    //   603: dup
    //   604: iconst_2
    //   605: new org/openjdk/nashorn/internal/runtime/Specialization
    //   608: dup
    //   609: ldc_w
    //   612: iconst_0
    //   613: iconst_0
    //   614: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   617: aastore
    //   618: dup
    //   619: iconst_3
    //   620: new org/openjdk/nashorn/internal/runtime/Specialization
    //   623: dup
    //   624: ldc_w
    //   627: iconst_0
    //   628: iconst_0
    //   629: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   632: aastore
    //   633: dup
    //   634: iconst_4
    //   635: new org/openjdk/nashorn/internal/runtime/Specialization
    //   638: dup
    //   639: ldc_w
    //   642: iconst_0
    //   643: iconst_0
    //   644: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   647: aastore
    //   648: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   651: dup
    //   652: iconst_2
    //   653: invokevirtual setArity : (I)V
    //   656: dup
    //   657: ldc_w 'Math.min'
    //   660: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   663: putfield min : Ljava/lang/Object;
    //   666: aload_0
    //   667: ldc_w 'pow'
    //   670: ldc_w
    //   673: iconst_1
    //   674: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   677: dup
    //   678: iconst_0
    //   679: new org/openjdk/nashorn/internal/runtime/Specialization
    //   682: dup
    //   683: ldc_w
    //   686: iconst_0
    //   687: iconst_0
    //   688: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   691: aastore
    //   692: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   695: dup
    //   696: ldc_w 'Math.pow'
    //   699: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   702: putfield pow : Ljava/lang/Object;
    //   705: aload_0
    //   706: ldc_w 'random'
    //   709: ldc_w
    //   712: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   715: dup
    //   716: ldc_w 'Math.random'
    //   719: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   722: putfield random : Ljava/lang/Object;
    //   725: aload_0
    //   726: ldc_w 'round'
    //   729: ldc_w
    //   732: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   735: dup
    //   736: ldc_w 'Math.round'
    //   739: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   742: putfield round : Ljava/lang/Object;
    //   745: aload_0
    //   746: ldc_w 'sin'
    //   749: ldc_w
    //   752: iconst_1
    //   753: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   756: dup
    //   757: iconst_0
    //   758: new org/openjdk/nashorn/internal/runtime/Specialization
    //   761: dup
    //   762: ldc_w
    //   765: iconst_0
    //   766: iconst_0
    //   767: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   770: aastore
    //   771: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   774: dup
    //   775: ldc_w 'Math.sin'
    //   778: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   781: putfield sin : Ljava/lang/Object;
    //   784: aload_0
    //   785: ldc_w 'sqrt'
    //   788: ldc_w
    //   791: iconst_1
    //   792: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   795: dup
    //   796: iconst_0
    //   797: new org/openjdk/nashorn/internal/runtime/Specialization
    //   800: dup
    //   801: ldc_w
    //   804: iconst_0
    //   805: iconst_0
    //   806: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   809: aastore
    //   810: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   813: dup
    //   814: ldc_w 'Math.sqrt'
    //   817: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   820: putfield sqrt : Ljava/lang/Object;
    //   823: aload_0
    //   824: ldc_w 'tan'
    //   827: ldc_w
    //   830: iconst_1
    //   831: anewarray org/openjdk/nashorn/internal/runtime/Specialization
    //   834: dup
    //   835: iconst_0
    //   836: new org/openjdk/nashorn/internal/runtime/Specialization
    //   839: dup
    //   840: ldc_w
    //   843: iconst_0
    //   844: iconst_0
    //   845: invokespecial <init> : (Ljava/lang/invoke/MethodHandle;ZZ)V
    //   848: aastore
    //   849: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;[Lorg/openjdk/nashorn/internal/runtime/Specialization;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   852: dup
    //   853: ldc_w 'Math.tan'
    //   856: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   859: putfield tan : Ljava/lang/Object;
    //   862: return
  }
  
  public String getClassName() {
    return "Math";
  }
}
