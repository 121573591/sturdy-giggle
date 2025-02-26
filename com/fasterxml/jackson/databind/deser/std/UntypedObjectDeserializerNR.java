package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JacksonStdImpl
final class UntypedObjectDeserializerNR extends StdDeserializer<Object> {
  private static final long serialVersionUID = 1L;
  
  protected static final Object[] NO_OBJECTS = new Object[0];
  
  public static final UntypedObjectDeserializerNR std = new UntypedObjectDeserializerNR();
  
  protected final boolean _nonMerging;
  
  public UntypedObjectDeserializerNR() {
    this(false);
  }
  
  protected UntypedObjectDeserializerNR(boolean nonMerging) {
    super(Object.class);
    this._nonMerging = nonMerging;
  }
  
  public static UntypedObjectDeserializerNR instance(boolean nonMerging) {
    if (nonMerging)
      return new UntypedObjectDeserializerNR(true); 
    return std;
  }
  
  public LogicalType logicalType() {
    return LogicalType.Untyped;
  }
  
  public Boolean supportsUpdate(DeserializationConfig config) {
    return this._nonMerging ? Boolean.FALSE : null;
  }
  
  public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    switch (p.currentTokenId()) {
      case 1:
        return _deserializeNR(p, ctxt, 
            Scope.rootObjectScope(ctxt.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES)));
      case 2:
        return Scope.emptyMap();
      case 5:
        return _deserializeObjectAtName(p, ctxt);
      case 3:
        return _deserializeNR(p, ctxt, Scope.rootArrayScope());
      case 6:
        return p.getText();
      case 7:
        if (ctxt.hasSomeOfFeatures(F_MASK_INT_COERCIONS))
          return _coerceIntegral(p, ctxt); 
        return p.getNumberValue();
      case 8:
        if (ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))
          return p.getDecimalValue(); 
        return p.getNumberValue();
      case 9:
        return Boolean.TRUE;
      case 10:
        return Boolean.FALSE;
      case 11:
        return null;
      case 12:
        return p.getEmbeddedObject();
    } 
    return ctxt.handleUnexpectedToken(getValueType(ctxt), p);
  }
  
  public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
    switch (p.currentTokenId()) {
      case 1:
      case 3:
      case 5:
        return typeDeserializer.deserializeTypedFromAny(p, ctxt);
    } 
    return _deserializeAnyScalar(p, ctxt, p.currentTokenId());
  }
  
  public Object deserialize(JsonParser p, DeserializationContext ctxt, Object intoValue) throws IOException {
    JsonToken t;
    if (this._nonMerging)
      return deserialize(p, ctxt); 
    switch (p.currentTokenId()) {
      case 2:
      case 4:
        return intoValue;
      case 1:
        t = p.nextToken();
        if (t == JsonToken.END_OBJECT)
          return intoValue; 
      case 5:
        if (intoValue instanceof Map) {
          Map<Object, Object> m = (Map<Object, Object>)intoValue;
          String key = p.currentName();
          while (true) {
            Object newV;
            p.nextToken();
            Object old = m.get(key);
            if (old != null) {
              newV = deserialize(p, ctxt, old);
            } else {
              newV = deserialize(p, ctxt);
            } 
            if (newV != old)
              m.put(key, newV); 
            if ((key = p.nextFieldName()) == null)
              return intoValue; 
          } 
        } 
        break;
      case 3:
        t = p.nextToken();
        if (t == JsonToken.END_ARRAY)
          return intoValue; 
        if (intoValue instanceof Collection) {
          Collection<Object> c = (Collection<Object>)intoValue;
          while (true) {
            c.add(deserialize(p, ctxt));
            if (p.nextToken() == JsonToken.END_ARRAY)
              return intoValue; 
          } 
        } 
        break;
    } 
    return deserialize(p, ctxt);
  }
  
  private Object _deserializeObjectAtName(JsonParser p, DeserializationContext ctxt) throws IOException {
    Scope rootObject = Scope.rootObjectScope(ctxt.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES));
    String key = p.currentName();
    for (; key != null; key = p.nextFieldName()) {
      Object value;
      JsonToken t = p.nextToken();
      if (t == null)
        t = JsonToken.NOT_AVAILABLE; 
      switch (t.id()) {
        case 1:
          value = _deserializeNR(p, ctxt, rootObject.childObject());
          break;
        case 2:
          return rootObject.finishRootObject();
        case 3:
          value = _deserializeNR(p, ctxt, rootObject.childArray());
          break;
        default:
          value = _deserializeAnyScalar(p, ctxt, t.id());
          break;
      } 
      rootObject.putValue(key, value);
    } 
    return rootObject.finishRootObject();
  }
  
  private Object _deserializeNR(JsonParser p, DeserializationContext ctxt, Scope rootScope) throws IOException {
    // Byte code:
    //   0: aload_2
    //   1: getstatic com/fasterxml/jackson/databind/deser/std/UntypedObjectDeserializerNR.F_MASK_INT_COERCIONS : I
    //   4: invokevirtual hasSomeOfFeatures : (I)Z
    //   7: istore #4
    //   9: aload_2
    //   10: getstatic com/fasterxml/jackson/databind/DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY : Lcom/fasterxml/jackson/databind/DeserializationFeature;
    //   13: invokevirtual isEnabled : (Lcom/fasterxml/jackson/databind/DeserializationFeature;)Z
    //   16: istore #5
    //   18: aload_3
    //   19: astore #6
    //   21: aload #6
    //   23: invokevirtual isObject : ()Z
    //   26: ifeq -> 288
    //   29: aload_1
    //   30: invokevirtual nextFieldName : ()Ljava/lang/String;
    //   33: astore #7
    //   35: aload #7
    //   37: ifnull -> 266
    //   40: aload_1
    //   41: invokevirtual nextToken : ()Lcom/fasterxml/jackson/core/JsonToken;
    //   44: astore #9
    //   46: aload #9
    //   48: ifnonnull -> 56
    //   51: getstatic com/fasterxml/jackson/core/JsonToken.NOT_AVAILABLE : Lcom/fasterxml/jackson/core/JsonToken;
    //   54: astore #9
    //   56: aload #9
    //   58: invokevirtual id : ()I
    //   61: tableswitch default -> 237, 1 -> 124, 2 -> 237, 3 -> 136, 4 -> 237, 5 -> 237, 6 -> 148, 7 -> 157, 8 -> 180, 9 -> 206, 10 -> 214, 11 -> 222, 12 -> 228
    //   124: aload #6
    //   126: aload #7
    //   128: invokevirtual childObject : (Ljava/lang/String;)Lcom/fasterxml/jackson/databind/deser/std/UntypedObjectDeserializerNR$Scope;
    //   131: astore #6
    //   133: goto -> 257
    //   136: aload #6
    //   138: aload #7
    //   140: invokevirtual childArray : (Ljava/lang/String;)Lcom/fasterxml/jackson/databind/deser/std/UntypedObjectDeserializerNR$Scope;
    //   143: astore #6
    //   145: goto -> 21
    //   148: aload_1
    //   149: invokevirtual getText : ()Ljava/lang/String;
    //   152: astore #8
    //   154: goto -> 248
    //   157: iload #4
    //   159: ifeq -> 171
    //   162: aload_0
    //   163: aload_1
    //   164: aload_2
    //   165: invokevirtual _coerceIntegral : (Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;)Ljava/lang/Object;
    //   168: goto -> 175
    //   171: aload_1
    //   172: invokevirtual getNumberValue : ()Ljava/lang/Number;
    //   175: astore #8
    //   177: goto -> 248
    //   180: aload_2
    //   181: getstatic com/fasterxml/jackson/databind/DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS : Lcom/fasterxml/jackson/databind/DeserializationFeature;
    //   184: invokevirtual isEnabled : (Lcom/fasterxml/jackson/databind/DeserializationFeature;)Z
    //   187: ifeq -> 197
    //   190: aload_1
    //   191: invokevirtual getDecimalValue : ()Ljava/math/BigDecimal;
    //   194: goto -> 201
    //   197: aload_1
    //   198: invokevirtual getNumberValue : ()Ljava/lang/Number;
    //   201: astore #8
    //   203: goto -> 248
    //   206: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   209: astore #8
    //   211: goto -> 248
    //   214: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   217: astore #8
    //   219: goto -> 248
    //   222: aconst_null
    //   223: astore #8
    //   225: goto -> 248
    //   228: aload_1
    //   229: invokevirtual getEmbeddedObject : ()Ljava/lang/Object;
    //   232: astore #8
    //   234: goto -> 248
    //   237: aload_2
    //   238: aload_0
    //   239: aload_2
    //   240: invokevirtual getValueType : (Lcom/fasterxml/jackson/databind/DeserializationContext;)Lcom/fasterxml/jackson/databind/JavaType;
    //   243: aload_1
    //   244: invokevirtual handleUnexpectedToken : (Lcom/fasterxml/jackson/databind/JavaType;Lcom/fasterxml/jackson/core/JsonParser;)Ljava/lang/Object;
    //   247: areturn
    //   248: aload #6
    //   250: aload #7
    //   252: aload #8
    //   254: invokevirtual putValue : (Ljava/lang/String;Ljava/lang/Object;)V
    //   257: aload_1
    //   258: invokevirtual nextFieldName : ()Ljava/lang/String;
    //   261: astore #7
    //   263: goto -> 35
    //   266: aload #6
    //   268: aload_3
    //   269: if_acmpne -> 278
    //   272: aload #6
    //   274: invokevirtual finishRootObject : ()Ljava/lang/Object;
    //   277: areturn
    //   278: aload #6
    //   280: invokevirtual finishBranchObject : ()Lcom/fasterxml/jackson/databind/deser/std/UntypedObjectDeserializerNR$Scope;
    //   283: astore #6
    //   285: goto -> 21
    //   288: aload_1
    //   289: invokevirtual nextToken : ()Lcom/fasterxml/jackson/core/JsonToken;
    //   292: astore #7
    //   294: aload #7
    //   296: ifnonnull -> 304
    //   299: getstatic com/fasterxml/jackson/core/JsonToken.NOT_AVAILABLE : Lcom/fasterxml/jackson/core/JsonToken;
    //   302: astore #7
    //   304: aload #7
    //   306: invokevirtual id : ()I
    //   309: tableswitch default -> 507, 1 -> 372, 2 -> 507, 3 -> 382, 4 -> 392, 5 -> 507, 6 -> 418, 7 -> 427, 8 -> 450, 9 -> 476, 10 -> 484, 11 -> 492, 12 -> 498
    //   372: aload #6
    //   374: invokevirtual childObject : ()Lcom/fasterxml/jackson/databind/deser/std/UntypedObjectDeserializerNR$Scope;
    //   377: astore #6
    //   379: goto -> 21
    //   382: aload #6
    //   384: invokevirtual childArray : ()Lcom/fasterxml/jackson/databind/deser/std/UntypedObjectDeserializerNR$Scope;
    //   387: astore #6
    //   389: goto -> 21
    //   392: aload #6
    //   394: aload_3
    //   395: if_acmpne -> 406
    //   398: aload #6
    //   400: iload #5
    //   402: invokevirtual finishRootArray : (Z)Ljava/lang/Object;
    //   405: areturn
    //   406: aload #6
    //   408: iload #5
    //   410: invokevirtual finishBranchArray : (Z)Lcom/fasterxml/jackson/databind/deser/std/UntypedObjectDeserializerNR$Scope;
    //   413: astore #6
    //   415: goto -> 21
    //   418: aload_1
    //   419: invokevirtual getText : ()Ljava/lang/String;
    //   422: astore #8
    //   424: goto -> 518
    //   427: iload #4
    //   429: ifeq -> 441
    //   432: aload_0
    //   433: aload_1
    //   434: aload_2
    //   435: invokevirtual _coerceIntegral : (Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;)Ljava/lang/Object;
    //   438: goto -> 445
    //   441: aload_1
    //   442: invokevirtual getNumberValue : ()Ljava/lang/Number;
    //   445: astore #8
    //   447: goto -> 518
    //   450: aload_2
    //   451: getstatic com/fasterxml/jackson/databind/DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS : Lcom/fasterxml/jackson/databind/DeserializationFeature;
    //   454: invokevirtual isEnabled : (Lcom/fasterxml/jackson/databind/DeserializationFeature;)Z
    //   457: ifeq -> 467
    //   460: aload_1
    //   461: invokevirtual getDecimalValue : ()Ljava/math/BigDecimal;
    //   464: goto -> 471
    //   467: aload_1
    //   468: invokevirtual getNumberValue : ()Ljava/lang/Number;
    //   471: astore #8
    //   473: goto -> 518
    //   476: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   479: astore #8
    //   481: goto -> 518
    //   484: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   487: astore #8
    //   489: goto -> 518
    //   492: aconst_null
    //   493: astore #8
    //   495: goto -> 518
    //   498: aload_1
    //   499: invokevirtual getEmbeddedObject : ()Ljava/lang/Object;
    //   502: astore #8
    //   504: goto -> 518
    //   507: aload_2
    //   508: aload_0
    //   509: aload_2
    //   510: invokevirtual getValueType : (Lcom/fasterxml/jackson/databind/DeserializationContext;)Lcom/fasterxml/jackson/databind/JavaType;
    //   513: aload_1
    //   514: invokevirtual handleUnexpectedToken : (Lcom/fasterxml/jackson/databind/JavaType;Lcom/fasterxml/jackson/core/JsonParser;)Ljava/lang/Object;
    //   517: areturn
    //   518: aload #6
    //   520: aload #8
    //   522: invokevirtual addValue : (Ljava/lang/Object;)V
    //   525: goto -> 288
    // Line number table:
    //   Java source line number -> byte code offset
    //   #219	-> 0
    //   #220	-> 9
    //   #222	-> 18
    //   #226	-> 21
    //   #227	-> 29
    //   #230	-> 35
    //   #232	-> 40
    //   #233	-> 46
    //   #234	-> 51
    //   #236	-> 56
    //   #238	-> 124
    //   #240	-> 133
    //   #242	-> 136
    //   #244	-> 145
    //   #246	-> 148
    //   #247	-> 154
    //   #249	-> 157
    //   #250	-> 177
    //   #252	-> 180
    //   #253	-> 191
    //   #254	-> 203
    //   #256	-> 206
    //   #257	-> 211
    //   #259	-> 214
    //   #260	-> 219
    //   #262	-> 222
    //   #263	-> 225
    //   #265	-> 228
    //   #266	-> 234
    //   #268	-> 237
    //   #270	-> 248
    //   #230	-> 257
    //   #273	-> 266
    //   #274	-> 272
    //   #276	-> 278
    //   #277	-> 285
    //   #281	-> 288
    //   #282	-> 294
    //   #283	-> 299
    //   #286	-> 304
    //   #288	-> 372
    //   #289	-> 379
    //   #291	-> 382
    //   #292	-> 389
    //   #294	-> 392
    //   #295	-> 398
    //   #297	-> 406
    //   #298	-> 415
    //   #300	-> 418
    //   #301	-> 424
    //   #303	-> 427
    //   #304	-> 447
    //   #306	-> 450
    //   #307	-> 461
    //   #308	-> 473
    //   #310	-> 476
    //   #311	-> 481
    //   #313	-> 484
    //   #314	-> 489
    //   #316	-> 492
    //   #317	-> 495
    //   #319	-> 498
    //   #320	-> 504
    //   #322	-> 507
    //   #324	-> 518
    //   #325	-> 525
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   154	3	8	value	Ljava/lang/Object;
    //   177	3	8	value	Ljava/lang/Object;
    //   203	3	8	value	Ljava/lang/Object;
    //   211	3	8	value	Ljava/lang/Object;
    //   219	3	8	value	Ljava/lang/Object;
    //   225	3	8	value	Ljava/lang/Object;
    //   234	3	8	value	Ljava/lang/Object;
    //   248	9	8	value	Ljava/lang/Object;
    //   46	211	9	t	Lcom/fasterxml/jackson/core/JsonToken;
    //   35	250	7	propName	Ljava/lang/String;
    //   424	3	8	value	Ljava/lang/Object;
    //   447	3	8	value	Ljava/lang/Object;
    //   473	3	8	value	Ljava/lang/Object;
    //   481	3	8	value	Ljava/lang/Object;
    //   489	3	8	value	Ljava/lang/Object;
    //   495	3	8	value	Ljava/lang/Object;
    //   504	3	8	value	Ljava/lang/Object;
    //   294	231	7	t	Lcom/fasterxml/jackson/core/JsonToken;
    //   518	7	8	value	Ljava/lang/Object;
    //   0	528	0	this	Lcom/fasterxml/jackson/databind/deser/std/UntypedObjectDeserializerNR;
    //   0	528	1	p	Lcom/fasterxml/jackson/core/JsonParser;
    //   0	528	2	ctxt	Lcom/fasterxml/jackson/databind/DeserializationContext;
    //   0	528	3	rootScope	Lcom/fasterxml/jackson/databind/deser/std/UntypedObjectDeserializerNR$Scope;
    //   9	519	4	intCoercions	Z
    //   18	510	5	useJavaArray	Z
    //   21	507	6	currScope	Lcom/fasterxml/jackson/databind/deser/std/UntypedObjectDeserializerNR$Scope;
  }
  
  private Object _deserializeAnyScalar(JsonParser p, DeserializationContext ctxt, int tokenType) throws IOException {
    switch (tokenType) {
      case 6:
        return p.getText();
      case 7:
        if (ctxt.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS))
          return p.getBigIntegerValue(); 
        return p.getNumberValue();
      case 8:
        if (ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))
          return p.getDecimalValue(); 
        return p.getNumberValue();
      case 9:
        return Boolean.TRUE;
      case 10:
        return Boolean.FALSE;
      case 12:
        return p.getEmbeddedObject();
      case 11:
        return null;
    } 
    return ctxt.handleUnexpectedToken(getValueType(ctxt), p);
  }
  
  protected Object _mapObjectWithDups(JsonParser p, DeserializationContext ctxt, Map<String, Object> result, String initialKey, Object oldValue, Object newValue, String nextKey) throws IOException {
    boolean squashDups = ctxt.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES);
    if (squashDups)
      _squashDups(result, initialKey, oldValue, newValue); 
    while (nextKey != null) {
      p.nextToken();
      newValue = deserialize(p, ctxt);
      oldValue = result.put(nextKey, newValue);
      if (oldValue != null && squashDups)
        _squashDups(result, nextKey, oldValue, newValue); 
      nextKey = p.nextFieldName();
    } 
    return result;
  }
  
  private void _squashDups(Map<String, Object> result, String key, Object oldValue, Object newValue) {
    if (oldValue instanceof List) {
      ((List<Object>)oldValue).add(newValue);
      result.put(key, oldValue);
    } else {
      ArrayList<Object> l = new ArrayList();
      l.add(oldValue);
      l.add(newValue);
      result.put(key, l);
    } 
  }
  
  private static final class Scope {
    private final Scope _parent;
    
    private Scope _child;
    
    private boolean _isObject;
    
    private boolean _squashDups;
    
    private String _deferredKey;
    
    private Map<String, Object> _map;
    
    private List<Object> _list;
    
    private Scope(Scope p) {
      this._parent = p;
      this._isObject = false;
      this._squashDups = false;
    }
    
    private Scope(Scope p, boolean isObject, boolean squashDups) {
      this._parent = p;
      this._isObject = isObject;
      this._squashDups = squashDups;
    }
    
    public static Scope rootObjectScope(boolean squashDups) {
      return new Scope(null, true, squashDups);
    }
    
    public static Scope rootArrayScope() {
      return new Scope(null);
    }
    
    private Scope resetAsArray() {
      this._isObject = false;
      return this;
    }
    
    private Scope resetAsObject(boolean squashDups) {
      this._isObject = true;
      this._squashDups = squashDups;
      return this;
    }
    
    public Scope childObject() {
      if (this._child == null)
        return new Scope(this, true, this._squashDups); 
      return this._child.resetAsObject(this._squashDups);
    }
    
    public Scope childObject(String deferredKey) {
      this._deferredKey = deferredKey;
      if (this._child == null)
        return new Scope(this, true, this._squashDups); 
      return this._child.resetAsObject(this._squashDups);
    }
    
    public Scope childArray() {
      if (this._child == null)
        return new Scope(this); 
      return this._child.resetAsArray();
    }
    
    public Scope childArray(String deferredKey) {
      this._deferredKey = deferredKey;
      if (this._child == null)
        return new Scope(this); 
      return this._child.resetAsArray();
    }
    
    public boolean isObject() {
      return this._isObject;
    }
    
    public void putValue(String key, Object value) {
      if (this._squashDups) {
        _putValueHandleDups(key, value);
        return;
      } 
      if (this._map == null)
        this._map = new LinkedHashMap<>(); 
      this._map.put(key, value);
    }
    
    public Scope putDeferredValue(Object value) {
      String key = Objects.<String>requireNonNull(this._deferredKey);
      this._deferredKey = null;
      if (this._squashDups) {
        _putValueHandleDups(key, value);
        return this;
      } 
      if (this._map == null)
        this._map = new LinkedHashMap<>(); 
      this._map.put(key, value);
      return this;
    }
    
    public void addValue(Object value) {
      if (this._list == null)
        this._list = new ArrayList(); 
      this._list.add(value);
    }
    
    public Object finishRootObject() {
      if (this._map == null)
        return emptyMap(); 
      return this._map;
    }
    
    public Scope finishBranchObject() {
      Object<String, Object> value;
      if (this._map == null) {
        Object<Object, Object> object = (Object<Object, Object>)new LinkedHashMap<>();
      } else {
        value = (Object<String, Object>)this._map;
        this._map = null;
      } 
      if (this._parent.isObject())
        return this._parent.putDeferredValue(value); 
      this._parent.addValue(value);
      return this._parent;
    }
    
    public Object finishRootArray(boolean asJavaArray) {
      if (this._list == null) {
        if (asJavaArray)
          return UntypedObjectDeserializerNR.NO_OBJECTS; 
        return emptyList();
      } 
      if (asJavaArray)
        return this._list.toArray(UntypedObjectDeserializerNR.NO_OBJECTS); 
      return this._list;
    }
    
    public Scope finishBranchArray(boolean asJavaArray) {
      Object<Object> value;
      if (this._list == null) {
        if (asJavaArray) {
          value = (Object<Object>)UntypedObjectDeserializerNR.NO_OBJECTS;
        } else {
          value = (Object<Object>)emptyList();
        } 
      } else {
        if (asJavaArray) {
          value = this._list.toArray(UntypedObjectDeserializerNR.NO_OBJECTS);
        } else {
          value = (Object<Object>)this._list;
        } 
        this._list = null;
      } 
      if (this._parent.isObject())
        return this._parent.putDeferredValue(value); 
      this._parent.addValue(value);
      return this._parent;
    }
    
    private void _putValueHandleDups(String key, Object newValue) {
      if (this._map == null) {
        this._map = new LinkedHashMap<>();
        this._map.put(key, newValue);
        return;
      } 
      Object old = this._map.put(key, newValue);
      if (old != null)
        if (old instanceof List) {
          ((List<Object>)old).add(newValue);
          this._map.put(key, old);
        } else {
          ArrayList<Object> l = new ArrayList();
          l.add(old);
          l.add(newValue);
          this._map.put(key, l);
        }  
    }
    
    public static Map<String, Object> emptyMap() {
      return new LinkedHashMap<>(2);
    }
    
    public static List<Object> emptyList() {
      return new ArrayList(2);
    }
  }
}
