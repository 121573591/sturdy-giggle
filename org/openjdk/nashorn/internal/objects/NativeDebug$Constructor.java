package org.openjdk.nashorn.internal.objects;

import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;

final class NativeDebug$Constructor extends ScriptObject {
  private Object getArrayDataClass;
  
  private Object getArrayData;
  
  private Object getContext;
  
  private Object map;
  
  private Object identical;
  
  private Object equalWithoutType;
  
  private Object diffPropertyMaps;
  
  private Object getClass;
  
  private Object equals;
  
  private Object toJavaString;
  
  private Object toIdentString;
  
  private Object isDebuggableFunction;
  
  private Object getListenerCount;
  
  private Object dumpCounters;
  
  private Object getEventQueueCapacity;
  
  private Object setEventQueueCapacity;
  
  private Object addRuntimeEvent;
  
  private Object expandEventQueueCapacity;
  
  private Object clearRuntimeEvents;
  
  private Object removeRuntimeEvent;
  
  private Object getRuntimeEvents;
  
  private Object getLastRuntimeEvent;
  
  private static final PropertyMap $nasgenmap$;
  
  public Object G$getArrayDataClass() {
    return this.getArrayDataClass;
  }
  
  public void S$getArrayDataClass(Object paramObject) {
    this.getArrayDataClass = paramObject;
  }
  
  public Object G$getArrayData() {
    return this.getArrayData;
  }
  
  public void S$getArrayData(Object paramObject) {
    this.getArrayData = paramObject;
  }
  
  public Object G$getContext() {
    return this.getContext;
  }
  
  public void S$getContext(Object paramObject) {
    this.getContext = paramObject;
  }
  
  public Object G$map() {
    return this.map;
  }
  
  public void S$map(Object paramObject) {
    this.map = paramObject;
  }
  
  public Object G$identical() {
    return this.identical;
  }
  
  public void S$identical(Object paramObject) {
    this.identical = paramObject;
  }
  
  public Object G$equalWithoutType() {
    return this.equalWithoutType;
  }
  
  public void S$equalWithoutType(Object paramObject) {
    this.equalWithoutType = paramObject;
  }
  
  public Object G$diffPropertyMaps() {
    return this.diffPropertyMaps;
  }
  
  public void S$diffPropertyMaps(Object paramObject) {
    this.diffPropertyMaps = paramObject;
  }
  
  public Object G$getClass() {
    return this.getClass;
  }
  
  public void S$getClass(Object paramObject) {
    this.getClass = paramObject;
  }
  
  public Object G$equals() {
    return this.equals;
  }
  
  public void S$equals(Object paramObject) {
    this.equals = paramObject;
  }
  
  public Object G$toJavaString() {
    return this.toJavaString;
  }
  
  public void S$toJavaString(Object paramObject) {
    this.toJavaString = paramObject;
  }
  
  public Object G$toIdentString() {
    return this.toIdentString;
  }
  
  public void S$toIdentString(Object paramObject) {
    this.toIdentString = paramObject;
  }
  
  public Object G$isDebuggableFunction() {
    return this.isDebuggableFunction;
  }
  
  public void S$isDebuggableFunction(Object paramObject) {
    this.isDebuggableFunction = paramObject;
  }
  
  public Object G$getListenerCount() {
    return this.getListenerCount;
  }
  
  public void S$getListenerCount(Object paramObject) {
    this.getListenerCount = paramObject;
  }
  
  public Object G$dumpCounters() {
    return this.dumpCounters;
  }
  
  public void S$dumpCounters(Object paramObject) {
    this.dumpCounters = paramObject;
  }
  
  public Object G$getEventQueueCapacity() {
    return this.getEventQueueCapacity;
  }
  
  public void S$getEventQueueCapacity(Object paramObject) {
    this.getEventQueueCapacity = paramObject;
  }
  
  public Object G$setEventQueueCapacity() {
    return this.setEventQueueCapacity;
  }
  
  public void S$setEventQueueCapacity(Object paramObject) {
    this.setEventQueueCapacity = paramObject;
  }
  
  public Object G$addRuntimeEvent() {
    return this.addRuntimeEvent;
  }
  
  public void S$addRuntimeEvent(Object paramObject) {
    this.addRuntimeEvent = paramObject;
  }
  
  public Object G$expandEventQueueCapacity() {
    return this.expandEventQueueCapacity;
  }
  
  public void S$expandEventQueueCapacity(Object paramObject) {
    this.expandEventQueueCapacity = paramObject;
  }
  
  public Object G$clearRuntimeEvents() {
    return this.clearRuntimeEvents;
  }
  
  public void S$clearRuntimeEvents(Object paramObject) {
    this.clearRuntimeEvents = paramObject;
  }
  
  public Object G$removeRuntimeEvent() {
    return this.removeRuntimeEvent;
  }
  
  public void S$removeRuntimeEvent(Object paramObject) {
    this.removeRuntimeEvent = paramObject;
  }
  
  public Object G$getRuntimeEvents() {
    return this.getRuntimeEvents;
  }
  
  public void S$getRuntimeEvents(Object paramObject) {
    this.getRuntimeEvents = paramObject;
  }
  
  public Object G$getLastRuntimeEvent() {
    return this.getLastRuntimeEvent;
  }
  
  public void S$getLastRuntimeEvent(Object paramObject) {
    this.getLastRuntimeEvent = paramObject;
  }
  
  static {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: bipush #22
    //   6: invokespecial <init> : (I)V
    //   9: dup
    //   10: ldc 'getArrayDataClass'
    //   12: iconst_2
    //   13: ldc
    //   15: ldc
    //   17: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   20: invokeinterface add : (Ljava/lang/Object;)Z
    //   25: pop
    //   26: dup
    //   27: ldc 'getArrayData'
    //   29: iconst_2
    //   30: ldc
    //   32: ldc
    //   34: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   37: invokeinterface add : (Ljava/lang/Object;)Z
    //   42: pop
    //   43: dup
    //   44: ldc 'getContext'
    //   46: iconst_2
    //   47: ldc
    //   49: ldc
    //   51: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   54: invokeinterface add : (Ljava/lang/Object;)Z
    //   59: pop
    //   60: dup
    //   61: ldc 'map'
    //   63: iconst_2
    //   64: ldc
    //   66: ldc
    //   68: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   71: invokeinterface add : (Ljava/lang/Object;)Z
    //   76: pop
    //   77: dup
    //   78: ldc 'identical'
    //   80: iconst_2
    //   81: ldc
    //   83: ldc
    //   85: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   88: invokeinterface add : (Ljava/lang/Object;)Z
    //   93: pop
    //   94: dup
    //   95: ldc 'equalWithoutType'
    //   97: iconst_2
    //   98: ldc
    //   100: ldc
    //   102: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   105: invokeinterface add : (Ljava/lang/Object;)Z
    //   110: pop
    //   111: dup
    //   112: ldc 'diffPropertyMaps'
    //   114: iconst_2
    //   115: ldc
    //   117: ldc
    //   119: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   122: invokeinterface add : (Ljava/lang/Object;)Z
    //   127: pop
    //   128: dup
    //   129: ldc 'getClass'
    //   131: iconst_2
    //   132: ldc
    //   134: ldc
    //   136: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   139: invokeinterface add : (Ljava/lang/Object;)Z
    //   144: pop
    //   145: dup
    //   146: ldc 'equals'
    //   148: iconst_2
    //   149: ldc
    //   151: ldc
    //   153: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   156: invokeinterface add : (Ljava/lang/Object;)Z
    //   161: pop
    //   162: dup
    //   163: ldc 'toJavaString'
    //   165: iconst_2
    //   166: ldc
    //   168: ldc
    //   170: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   173: invokeinterface add : (Ljava/lang/Object;)Z
    //   178: pop
    //   179: dup
    //   180: ldc 'toIdentString'
    //   182: iconst_2
    //   183: ldc
    //   185: ldc
    //   187: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   190: invokeinterface add : (Ljava/lang/Object;)Z
    //   195: pop
    //   196: dup
    //   197: ldc 'isDebuggableFunction'
    //   199: iconst_2
    //   200: ldc
    //   202: ldc
    //   204: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   207: invokeinterface add : (Ljava/lang/Object;)Z
    //   212: pop
    //   213: dup
    //   214: ldc 'getListenerCount'
    //   216: iconst_2
    //   217: ldc
    //   219: ldc
    //   221: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   224: invokeinterface add : (Ljava/lang/Object;)Z
    //   229: pop
    //   230: dup
    //   231: ldc 'dumpCounters'
    //   233: iconst_2
    //   234: ldc
    //   236: ldc
    //   238: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   241: invokeinterface add : (Ljava/lang/Object;)Z
    //   246: pop
    //   247: dup
    //   248: ldc 'getEventQueueCapacity'
    //   250: iconst_2
    //   251: ldc
    //   253: ldc
    //   255: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   258: invokeinterface add : (Ljava/lang/Object;)Z
    //   263: pop
    //   264: dup
    //   265: ldc 'setEventQueueCapacity'
    //   267: iconst_2
    //   268: ldc
    //   270: ldc
    //   272: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   275: invokeinterface add : (Ljava/lang/Object;)Z
    //   280: pop
    //   281: dup
    //   282: ldc 'addRuntimeEvent'
    //   284: iconst_2
    //   285: ldc_w
    //   288: ldc_w
    //   291: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   294: invokeinterface add : (Ljava/lang/Object;)Z
    //   299: pop
    //   300: dup
    //   301: ldc_w 'expandEventQueueCapacity'
    //   304: iconst_2
    //   305: ldc_w
    //   308: ldc_w
    //   311: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   314: invokeinterface add : (Ljava/lang/Object;)Z
    //   319: pop
    //   320: dup
    //   321: ldc_w 'clearRuntimeEvents'
    //   324: iconst_2
    //   325: ldc_w
    //   328: ldc_w
    //   331: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   334: invokeinterface add : (Ljava/lang/Object;)Z
    //   339: pop
    //   340: dup
    //   341: ldc_w 'removeRuntimeEvent'
    //   344: iconst_2
    //   345: ldc_w
    //   348: ldc_w
    //   351: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   354: invokeinterface add : (Ljava/lang/Object;)Z
    //   359: pop
    //   360: dup
    //   361: ldc_w 'getRuntimeEvents'
    //   364: iconst_2
    //   365: ldc_w
    //   368: ldc_w
    //   371: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   374: invokeinterface add : (Ljava/lang/Object;)Z
    //   379: pop
    //   380: dup
    //   381: ldc_w 'getLastRuntimeEvent'
    //   384: iconst_2
    //   385: ldc_w
    //   388: ldc_w
    //   391: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   394: invokeinterface add : (Ljava/lang/Object;)Z
    //   399: pop
    //   400: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   403: putstatic org/openjdk/nashorn/internal/objects/NativeDebug$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   406: return
  }
  
  NativeDebug$Constructor() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic org/openjdk/nashorn/internal/objects/NativeDebug$Constructor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   4: invokespecial <init> : (Lorg/openjdk/nashorn/internal/runtime/PropertyMap;)V
    //   7: aload_0
    //   8: ldc 'getArrayDataClass'
    //   10: ldc_w
    //   13: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   16: dup
    //   17: ldc_w 'Debug.getArrayDataClass'
    //   20: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   23: putfield getArrayDataClass : Ljava/lang/Object;
    //   26: aload_0
    //   27: ldc 'getArrayData'
    //   29: ldc_w
    //   32: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   35: dup
    //   36: ldc_w 'Debug.getArrayData'
    //   39: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   42: putfield getArrayData : Ljava/lang/Object;
    //   45: aload_0
    //   46: ldc 'getContext'
    //   48: ldc_w
    //   51: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   54: dup
    //   55: ldc_w 'Debug.getContext'
    //   58: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   61: putfield getContext : Ljava/lang/Object;
    //   64: aload_0
    //   65: ldc 'map'
    //   67: ldc_w
    //   70: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   73: dup
    //   74: ldc_w 'Debug.map'
    //   77: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   80: putfield map : Ljava/lang/Object;
    //   83: aload_0
    //   84: ldc 'identical'
    //   86: ldc_w
    //   89: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   92: dup
    //   93: ldc_w 'Debug.identical'
    //   96: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   99: putfield identical : Ljava/lang/Object;
    //   102: aload_0
    //   103: ldc 'equalWithoutType'
    //   105: ldc_w
    //   108: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   111: dup
    //   112: ldc_w 'Debug.equalWithoutType'
    //   115: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   118: putfield equalWithoutType : Ljava/lang/Object;
    //   121: aload_0
    //   122: ldc 'diffPropertyMaps'
    //   124: ldc_w
    //   127: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   130: dup
    //   131: ldc_w 'Debug.diffPropertyMaps'
    //   134: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   137: putfield diffPropertyMaps : Ljava/lang/Object;
    //   140: aload_0
    //   141: ldc 'getClass'
    //   143: ldc_w
    //   146: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   149: dup
    //   150: ldc_w 'Debug.getClass'
    //   153: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   156: putfield getClass : Ljava/lang/Object;
    //   159: aload_0
    //   160: ldc 'equals'
    //   162: ldc_w
    //   165: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   168: dup
    //   169: ldc_w 'Debug.equals'
    //   172: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   175: putfield equals : Ljava/lang/Object;
    //   178: aload_0
    //   179: ldc 'toJavaString'
    //   181: ldc_w
    //   184: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   187: dup
    //   188: ldc_w 'Debug.toJavaString'
    //   191: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   194: putfield toJavaString : Ljava/lang/Object;
    //   197: aload_0
    //   198: ldc 'toIdentString'
    //   200: ldc_w
    //   203: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   206: dup
    //   207: ldc_w 'Debug.toIdentString'
    //   210: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   213: putfield toIdentString : Ljava/lang/Object;
    //   216: aload_0
    //   217: ldc 'isDebuggableFunction'
    //   219: ldc_w
    //   222: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   225: dup
    //   226: ldc_w 'Debug.isDebuggableFunction'
    //   229: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   232: putfield isDebuggableFunction : Ljava/lang/Object;
    //   235: aload_0
    //   236: ldc 'getListenerCount'
    //   238: ldc_w
    //   241: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   244: dup
    //   245: ldc_w 'Debug.getListenerCount'
    //   248: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   251: putfield getListenerCount : Ljava/lang/Object;
    //   254: aload_0
    //   255: ldc 'dumpCounters'
    //   257: ldc_w
    //   260: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   263: dup
    //   264: ldc_w 'Debug.dumpCounters'
    //   267: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   270: putfield dumpCounters : Ljava/lang/Object;
    //   273: aload_0
    //   274: ldc 'getEventQueueCapacity'
    //   276: ldc_w
    //   279: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   282: dup
    //   283: ldc_w 'Debug.getEventQueueCapacity'
    //   286: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   289: putfield getEventQueueCapacity : Ljava/lang/Object;
    //   292: aload_0
    //   293: ldc 'setEventQueueCapacity'
    //   295: ldc_w
    //   298: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   301: dup
    //   302: ldc_w 'Debug.setEventQueueCapacity'
    //   305: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   308: putfield setEventQueueCapacity : Ljava/lang/Object;
    //   311: aload_0
    //   312: ldc 'addRuntimeEvent'
    //   314: ldc_w
    //   317: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   320: dup
    //   321: ldc_w 'Debug.addRuntimeEvent'
    //   324: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   327: putfield addRuntimeEvent : Ljava/lang/Object;
    //   330: aload_0
    //   331: ldc_w 'expandEventQueueCapacity'
    //   334: ldc_w
    //   337: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   340: dup
    //   341: ldc_w 'Debug.expandEventQueueCapacity'
    //   344: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   347: putfield expandEventQueueCapacity : Ljava/lang/Object;
    //   350: aload_0
    //   351: ldc_w 'clearRuntimeEvents'
    //   354: ldc_w
    //   357: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   360: dup
    //   361: ldc_w 'Debug.clearRuntimeEvents'
    //   364: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   367: putfield clearRuntimeEvents : Ljava/lang/Object;
    //   370: aload_0
    //   371: ldc_w 'removeRuntimeEvent'
    //   374: ldc_w
    //   377: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   380: dup
    //   381: ldc_w 'Debug.removeRuntimeEvent'
    //   384: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   387: putfield removeRuntimeEvent : Ljava/lang/Object;
    //   390: aload_0
    //   391: ldc_w 'getRuntimeEvents'
    //   394: ldc_w
    //   397: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   400: dup
    //   401: ldc_w 'Debug.getRuntimeEvents'
    //   404: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   407: putfield getRuntimeEvents : Ljava/lang/Object;
    //   410: aload_0
    //   411: ldc_w 'getLastRuntimeEvent'
    //   414: ldc_w
    //   417: invokestatic createBuiltin : (Ljava/lang/String;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/ScriptFunction;
    //   420: dup
    //   421: ldc_w 'Debug.getLastRuntimeEvent'
    //   424: invokevirtual setDocumentationKey : (Ljava/lang/String;)V
    //   427: putfield getLastRuntimeEvent : Ljava/lang/Object;
    //   430: return
  }
  
  public String getClassName() {
    return "Debug";
  }
}
