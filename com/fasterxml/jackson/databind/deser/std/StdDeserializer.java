package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.NullsAsEmptyProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsFailProvider;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public abstract class StdDeserializer<T> extends JsonDeserializer<T> implements Serializable, ValueInstantiator.Gettable {
  private static final long serialVersionUID = 1L;
  
  protected static final int F_MASK_INT_COERCIONS = DeserializationFeature.USE_BIG_INTEGER_FOR_INTS
    .getMask() | DeserializationFeature.USE_LONG_FOR_INTS
    .getMask();
  
  @Deprecated
  protected static final int F_MASK_ACCEPT_ARRAYS = DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS
    .getMask() | DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT
    .getMask();
  
  protected final Class<?> _valueClass;
  
  protected final JavaType _valueType;
  
  protected StdDeserializer(Class<?> vc) {
    this._valueClass = vc;
    this._valueType = null;
  }
  
  protected StdDeserializer(JavaType valueType) {
    this._valueClass = (valueType == null) ? Object.class : valueType.getRawClass();
    this._valueType = valueType;
  }
  
  protected StdDeserializer(StdDeserializer<?> src) {
    this._valueClass = src._valueClass;
    this._valueType = src._valueType;
  }
  
  public Class<?> handledType() {
    return this._valueClass;
  }
  
  @Deprecated
  public final Class<?> getValueClass() {
    return this._valueClass;
  }
  
  public JavaType getValueType() {
    return this._valueType;
  }
  
  public JavaType getValueType(DeserializationContext ctxt) {
    if (this._valueType != null)
      return this._valueType; 
    return ctxt.constructType(this._valueClass);
  }
  
  public ValueInstantiator getValueInstantiator() {
    return null;
  }
  
  protected boolean isDefaultDeserializer(JsonDeserializer<?> deserializer) {
    return ClassUtil.isJacksonStdImpl(deserializer);
  }
  
  protected boolean isDefaultKeyDeserializer(KeyDeserializer keyDeser) {
    return ClassUtil.isJacksonStdImpl(keyDeser);
  }
  
  public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
    return typeDeserializer.deserializeTypedFromAny(p, ctxt);
  }
  
  protected T _deserializeFromArray(JsonParser p, DeserializationContext ctxt) throws IOException {
    CoercionAction act = _findCoercionFromEmptyArray(ctxt);
    boolean unwrap = ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
    if (unwrap || act != CoercionAction.Fail) {
      JsonToken t = p.nextToken();
      if (t == JsonToken.END_ARRAY) {
        switch (act) {
          case AsEmpty:
            return (T)getEmptyValue(ctxt);
          case AsNull:
          case TryConvert:
            return (T)getNullValue(ctxt);
        } 
      } else if (unwrap) {
        T parsed = _deserializeWrappedValue(p, ctxt);
        if (p.nextToken() != JsonToken.END_ARRAY)
          handleMissingEndArrayForSingle(p, ctxt); 
        return parsed;
      } 
    } 
    return (T)ctxt.handleUnexpectedToken(getValueType(ctxt), JsonToken.START_ARRAY, p, null, new Object[0]);
  }
  
  @Deprecated
  protected T _deserializeFromEmpty(JsonParser p, DeserializationContext ctxt) throws IOException {
    if (p.hasToken(JsonToken.START_ARRAY) && 
      ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)) {
      JsonToken t = p.nextToken();
      if (t == JsonToken.END_ARRAY)
        return null; 
      return (T)ctxt.handleUnexpectedToken(getValueType(ctxt), p);
    } 
    return (T)ctxt.handleUnexpectedToken(getValueType(ctxt), p);
  }
  
  protected T _deserializeFromString(JsonParser p, DeserializationContext ctxt) throws IOException {
    ValueInstantiator inst = getValueInstantiator();
    Class<?> rawTargetType = handledType();
    String value = p.getValueAsString();
    if (inst != null && inst.canCreateFromString())
      return (T)inst.createFromString(ctxt, value); 
    if (value.isEmpty()) {
      CoercionAction act = ctxt.findCoercionAction(logicalType(), rawTargetType, CoercionInputShape.EmptyString);
      return (T)_deserializeFromEmptyString(p, ctxt, act, rawTargetType, "empty String (\"\")");
    } 
    if (_isBlank(value)) {
      CoercionAction act = ctxt.findCoercionFromBlankString(logicalType(), rawTargetType, CoercionAction.Fail);
      return (T)_deserializeFromEmptyString(p, ctxt, act, rawTargetType, "blank String (all whitespace)");
    } 
    if (inst != null) {
      value = value.trim();
      if (inst.canCreateFromInt() && 
        ctxt.findCoercionAction(LogicalType.Integer, Integer.class, CoercionInputShape.String) == CoercionAction.TryConvert)
        return (T)inst.createFromInt(ctxt, _parseIntPrimitive(ctxt, value)); 
      if (inst.canCreateFromLong() && 
        ctxt.findCoercionAction(LogicalType.Integer, Long.class, CoercionInputShape.String) == CoercionAction.TryConvert)
        return (T)inst.createFromLong(ctxt, _parseLongPrimitive(ctxt, value)); 
      if (inst.canCreateFromBoolean())
        if (ctxt.findCoercionAction(LogicalType.Boolean, Boolean.class, CoercionInputShape.String) == CoercionAction.TryConvert) {
          String str = value.trim();
          if ("true".equals(str))
            return (T)inst.createFromBoolean(ctxt, true); 
          if ("false".equals(str))
            return (T)inst.createFromBoolean(ctxt, false); 
        }  
    } 
    return (T)ctxt.handleMissingInstantiator(rawTargetType, inst, ctxt.getParser(), "no String-argument constructor/factory method to deserialize from String value ('%s')", new Object[] { value });
  }
  
  protected Object _deserializeFromEmptyString(JsonParser p, DeserializationContext ctxt, CoercionAction act, Class<?> rawTargetType, String desc) throws IOException {
    switch (act) {
      case AsEmpty:
        return getEmptyValue(ctxt);
      case Fail:
        _checkCoercionFail(ctxt, act, rawTargetType, "", "empty String (\"\")");
        break;
    } 
    return null;
  }
  
  protected T _deserializeWrappedValue(JsonParser p, DeserializationContext ctxt) throws IOException {
    if (p.hasToken(JsonToken.START_ARRAY)) {
      T result = (T)handleNestedArrayForSingle(p, ctxt);
      return result;
    } 
    return (T)deserialize(p, ctxt);
  }
  
  @Deprecated
  protected final boolean _parseBooleanPrimitive(DeserializationContext ctxt, JsonParser p, Class<?> targetType) throws IOException {
    return _parseBooleanPrimitive(p, ctxt);
  }
  
  protected final boolean _parseBooleanPrimitive(JsonParser p, DeserializationContext ctxt) throws IOException {
    switch (p.currentTokenId()) {
      case 6:
        text = p.getText();
        break;
      case 7:
        return Boolean.TRUE.equals(_coerceBooleanFromInt(p, ctxt, boolean.class));
      case 9:
        return true;
      case 10:
        return false;
      case 11:
        _verifyNullForPrimitive(ctxt);
        return false;
      case 1:
        text = ctxt.extractScalarFromObject(p, this, boolean.class);
        break;
      case 3:
        if (ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
          if (p.nextToken() == JsonToken.START_ARRAY)
            return ((Boolean)handleNestedArrayForSingle(p, ctxt)).booleanValue(); 
          boolean parsed = _parseBooleanPrimitive(p, ctxt);
          _verifyEndArrayForSingle(p, ctxt);
          return parsed;
        } 
      default:
        return ((Boolean)ctxt.handleUnexpectedToken(boolean.class, p)).booleanValue();
    } 
    CoercionAction act = _checkFromStringCoercion(ctxt, text, LogicalType.Boolean, boolean.class);
    if (act == CoercionAction.AsNull) {
      _verifyNullForPrimitive(ctxt);
      return false;
    } 
    if (act == CoercionAction.AsEmpty)
      return false; 
    String text = text.trim();
    int len = text.length();
    if (len == 4) {
      if (_isTrue(text))
        return true; 
    } else if (len == 5 && 
      _isFalse(text)) {
      return false;
    } 
    if (_hasTextualNull(text)) {
      _verifyNullForPrimitiveCoercion(ctxt, text);
      return false;
    } 
    Boolean b = (Boolean)ctxt.handleWeirdStringValue(boolean.class, text, "only \"true\"/\"True\"/\"TRUE\" or \"false\"/\"False\"/\"FALSE\" recognized", new Object[0]);
    return Boolean.TRUE.equals(b);
  }
  
  protected boolean _isTrue(String text) {
    char c = text.charAt(0);
    if (c == 't')
      return "true".equals(text); 
    if (c == 'T')
      return ("TRUE".equals(text) || "True".equals(text)); 
    return false;
  }
  
  protected boolean _isFalse(String text) {
    char c = text.charAt(0);
    if (c == 'f')
      return "false".equals(text); 
    if (c == 'F')
      return ("FALSE".equals(text) || "False".equals(text)); 
    return false;
  }
  
  protected final Boolean _parseBoolean(JsonParser p, DeserializationContext ctxt, Class<?> targetType) throws IOException {
    switch (p.currentTokenId()) {
      case 6:
        text = p.getText();
        break;
      case 7:
        return _coerceBooleanFromInt(p, ctxt, targetType);
      case 9:
        return Boolean.valueOf(true);
      case 10:
        return Boolean.valueOf(false);
      case 11:
        return null;
      case 1:
        text = ctxt.extractScalarFromObject(p, this, targetType);
        break;
      case 3:
        return (Boolean)_deserializeFromArray(p, ctxt);
      default:
        return (Boolean)ctxt.handleUnexpectedToken(targetType, p);
    } 
    CoercionAction act = _checkFromStringCoercion(ctxt, text, LogicalType.Boolean, targetType);
    if (act == CoercionAction.AsNull)
      return null; 
    if (act == CoercionAction.AsEmpty)
      return Boolean.valueOf(false); 
    String text = text.trim();
    int len = text.length();
    if (len == 4) {
      if (_isTrue(text))
        return Boolean.valueOf(true); 
    } else if (len == 5 && 
      _isFalse(text)) {
      return Boolean.valueOf(false);
    } 
    if (_checkTextualNull(ctxt, text))
      return null; 
    return (Boolean)ctxt.handleWeirdStringValue(targetType, text, "only \"true\" or \"false\" recognized", new Object[0]);
  }
  
  protected final byte _parseBytePrimitive(JsonParser p, DeserializationContext ctxt) throws IOException {
    int value;
    switch (p.currentTokenId()) {
      case 6:
        text = p.getText();
        break;
      case 8:
        act = _checkFloatToIntCoercion(p, ctxt, byte.class);
        if (act == CoercionAction.AsNull)
          return 0; 
        if (act == CoercionAction.AsEmpty)
          return 0; 
        return p.getByteValue();
      case 7:
        return p.getByteValue();
      case 11:
        _verifyNullForPrimitive(ctxt);
        return 0;
      case 1:
        text = ctxt.extractScalarFromObject(p, this, byte.class);
        break;
      case 3:
        if (ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
          if (p.nextToken() == JsonToken.START_ARRAY)
            return ((Byte)handleNestedArrayForSingle(p, ctxt)).byteValue(); 
          byte parsed = _parseBytePrimitive(p, ctxt);
          _verifyEndArrayForSingle(p, ctxt);
          return parsed;
        } 
      default:
        return ((Byte)ctxt.handleUnexpectedToken(ctxt.constructType(byte.class), p)).byteValue();
    } 
    CoercionAction act = _checkFromStringCoercion(ctxt, text, LogicalType.Integer, byte.class);
    if (act == CoercionAction.AsNull) {
      _verifyNullForPrimitive(ctxt);
      return 0;
    } 
    if (act == CoercionAction.AsEmpty)
      return 0; 
    String text = text.trim();
    if (_hasTextualNull(text)) {
      _verifyNullForPrimitiveCoercion(ctxt, text);
      return 0;
    } 
    try {
      value = NumberInput.parseInt(text);
    } catch (IllegalArgumentException iae) {
      return ((Byte)ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid `byte` value", new Object[0])).byteValue();
    } 
    if (_byteOverflow(value))
      return ((Byte)ctxt.handleWeirdStringValue(this._valueClass, text, "overflow, value cannot be represented as 8-bit value", new Object[0])).byteValue(); 
    return (byte)value;
  }
  
  protected final short _parseShortPrimitive(JsonParser p, DeserializationContext ctxt) throws IOException {
    int value;
    switch (p.currentTokenId()) {
      case 6:
        text = p.getText();
        break;
      case 8:
        act = _checkFloatToIntCoercion(p, ctxt, short.class);
        if (act == CoercionAction.AsNull)
          return 0; 
        if (act == CoercionAction.AsEmpty)
          return 0; 
        return p.getShortValue();
      case 7:
        return p.getShortValue();
      case 11:
        _verifyNullForPrimitive(ctxt);
        return 0;
      case 1:
        text = ctxt.extractScalarFromObject(p, this, short.class);
        break;
      case 3:
        if (ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
          if (p.nextToken() == JsonToken.START_ARRAY)
            return ((Short)handleNestedArrayForSingle(p, ctxt)).shortValue(); 
          short parsed = _parseShortPrimitive(p, ctxt);
          _verifyEndArrayForSingle(p, ctxt);
          return parsed;
        } 
      default:
        return ((Short)ctxt.handleUnexpectedToken(ctxt.constructType(short.class), p)).shortValue();
    } 
    CoercionAction act = _checkFromStringCoercion(ctxt, text, LogicalType.Integer, short.class);
    if (act == CoercionAction.AsNull) {
      _verifyNullForPrimitive(ctxt);
      return 0;
    } 
    if (act == CoercionAction.AsEmpty)
      return 0; 
    String text = text.trim();
    if (_hasTextualNull(text)) {
      _verifyNullForPrimitiveCoercion(ctxt, text);
      return 0;
    } 
    try {
      value = NumberInput.parseInt(text);
    } catch (IllegalArgumentException iae) {
      return ((Short)ctxt.handleWeirdStringValue(short.class, text, "not a valid `short` value", new Object[0])).shortValue();
    } 
    if (_shortOverflow(value))
      return ((Short)ctxt.handleWeirdStringValue(short.class, text, "overflow, value cannot be represented as 16-bit value", new Object[0])).shortValue(); 
    return (short)value;
  }
  
  protected final int _parseIntPrimitive(JsonParser p, DeserializationContext ctxt) throws IOException {
    switch (p.currentTokenId()) {
      case 6:
        text = p.getText();
        break;
      case 8:
        act = _checkFloatToIntCoercion(p, ctxt, int.class);
        if (act == CoercionAction.AsNull)
          return 0; 
        if (act == CoercionAction.AsEmpty)
          return 0; 
        return p.getValueAsInt();
      case 7:
        return p.getIntValue();
      case 11:
        _verifyNullForPrimitive(ctxt);
        return 0;
      case 1:
        text = ctxt.extractScalarFromObject(p, this, int.class);
        break;
      case 3:
        if (ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
          if (p.nextToken() == JsonToken.START_ARRAY)
            return ((Integer)handleNestedArrayForSingle(p, ctxt)).intValue(); 
          int parsed = _parseIntPrimitive(p, ctxt);
          _verifyEndArrayForSingle(p, ctxt);
          return parsed;
        } 
      default:
        return ((Number)ctxt.handleUnexpectedToken(int.class, p)).intValue();
    } 
    CoercionAction act = _checkFromStringCoercion(ctxt, text, LogicalType.Integer, int.class);
    if (act == CoercionAction.AsNull) {
      _verifyNullForPrimitive(ctxt);
      return 0;
    } 
    if (act == CoercionAction.AsEmpty)
      return 0; 
    String text = text.trim();
    if (_hasTextualNull(text)) {
      _verifyNullForPrimitiveCoercion(ctxt, text);
      return 0;
    } 
    return _parseIntPrimitive(ctxt, text);
  }
  
  protected final int _parseIntPrimitive(DeserializationContext ctxt, String text) throws IOException {
    try {
      if (text.length() > 9) {
        long l = NumberInput.parseLong(text);
        if (_intOverflow(l)) {
          Number v = (Number)ctxt.handleWeirdStringValue(int.class, text, "Overflow: numeric value (%s) out of range of int (%d -%d)", new Object[] { text, 
                
                Integer.valueOf(-2147483648), Integer.valueOf(2147483647) });
          return _nonNullNumber(v).intValue();
        } 
        return (int)l;
      } 
      return NumberInput.parseInt(text);
    } catch (IllegalArgumentException iae) {
      Number v = (Number)ctxt.handleWeirdStringValue(int.class, text, "not a valid `int` value", new Object[0]);
      return _nonNullNumber(v).intValue();
    } 
  }
  
  protected final Integer _parseInteger(JsonParser p, DeserializationContext ctxt, Class<?> targetType) throws IOException {
    switch (p.currentTokenId()) {
      case 6:
        text = p.getText();
        break;
      case 8:
        act = _checkFloatToIntCoercion(p, ctxt, targetType);
        if (act == CoercionAction.AsNull)
          return (Integer)getNullValue(ctxt); 
        if (act == CoercionAction.AsEmpty)
          return (Integer)getEmptyValue(ctxt); 
        return Integer.valueOf(p.getValueAsInt());
      case 7:
        return Integer.valueOf(p.getIntValue());
      case 11:
        return (Integer)getNullValue(ctxt);
      case 1:
        text = ctxt.extractScalarFromObject(p, this, targetType);
        break;
      case 3:
        return (Integer)_deserializeFromArray(p, ctxt);
      default:
        return (Integer)ctxt.handleUnexpectedToken(getValueType(ctxt), p);
    } 
    CoercionAction act = _checkFromStringCoercion(ctxt, text);
    if (act == CoercionAction.AsNull)
      return (Integer)getNullValue(ctxt); 
    if (act == CoercionAction.AsEmpty)
      return (Integer)getEmptyValue(ctxt); 
    String text = text.trim();
    if (_checkTextualNull(ctxt, text))
      return (Integer)getNullValue(ctxt); 
    return _parseInteger(ctxt, text);
  }
  
  protected final Integer _parseInteger(DeserializationContext ctxt, String text) throws IOException {
    try {
      if (text.length() > 9) {
        long l = NumberInput.parseLong(text);
        if (_intOverflow(l))
          return (Integer)ctxt.handleWeirdStringValue(Integer.class, text, "Overflow: numeric value (%s) out of range of `java.lang.Integer` (%d -%d)", new Object[] { text, 
                
                Integer.valueOf(-2147483648), Integer.valueOf(2147483647) }); 
        return Integer.valueOf((int)l);
      } 
      return Integer.valueOf(NumberInput.parseInt(text));
    } catch (IllegalArgumentException iae) {
      return (Integer)ctxt.handleWeirdStringValue(Integer.class, text, "not a valid `java.lang.Integer` value", new Object[0]);
    } 
  }
  
  protected final long _parseLongPrimitive(JsonParser p, DeserializationContext ctxt) throws IOException {
    switch (p.currentTokenId()) {
      case 6:
        text = p.getText();
        break;
      case 8:
        act = _checkFloatToIntCoercion(p, ctxt, long.class);
        if (act == CoercionAction.AsNull)
          return 0L; 
        if (act == CoercionAction.AsEmpty)
          return 0L; 
        return p.getValueAsLong();
      case 7:
        return p.getLongValue();
      case 11:
        _verifyNullForPrimitive(ctxt);
        return 0L;
      case 1:
        text = ctxt.extractScalarFromObject(p, this, long.class);
        break;
      case 3:
        if (ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
          if (p.nextToken() == JsonToken.START_ARRAY)
            return ((Long)handleNestedArrayForSingle(p, ctxt)).longValue(); 
          long parsed = _parseLongPrimitive(p, ctxt);
          _verifyEndArrayForSingle(p, ctxt);
          return parsed;
        } 
      default:
        return ((Number)ctxt.handleUnexpectedToken(long.class, p)).longValue();
    } 
    CoercionAction act = _checkFromStringCoercion(ctxt, text, LogicalType.Integer, long.class);
    if (act == CoercionAction.AsNull) {
      _verifyNullForPrimitive(ctxt);
      return 0L;
    } 
    if (act == CoercionAction.AsEmpty)
      return 0L; 
    String text = text.trim();
    if (_hasTextualNull(text)) {
      _verifyNullForPrimitiveCoercion(ctxt, text);
      return 0L;
    } 
    return _parseLongPrimitive(ctxt, text);
  }
  
  protected final long _parseLongPrimitive(DeserializationContext ctxt, String text) throws IOException {
    try {
      return NumberInput.parseLong(text);
    } catch (IllegalArgumentException illegalArgumentException) {
      Number v = (Number)ctxt.handleWeirdStringValue(long.class, text, "not a valid `long` value", new Object[0]);
      return _nonNullNumber(v).longValue();
    } 
  }
  
  protected final Long _parseLong(JsonParser p, DeserializationContext ctxt, Class<?> targetType) throws IOException {
    switch (p.currentTokenId()) {
      case 6:
        text = p.getText();
        break;
      case 8:
        act = _checkFloatToIntCoercion(p, ctxt, targetType);
        if (act == CoercionAction.AsNull)
          return (Long)getNullValue(ctxt); 
        if (act == CoercionAction.AsEmpty)
          return (Long)getEmptyValue(ctxt); 
        return Long.valueOf(p.getValueAsLong());
      case 11:
        return (Long)getNullValue(ctxt);
      case 7:
        return Long.valueOf(p.getLongValue());
      case 1:
        text = ctxt.extractScalarFromObject(p, this, targetType);
        break;
      case 3:
        return (Long)_deserializeFromArray(p, ctxt);
      default:
        return (Long)ctxt.handleUnexpectedToken(getValueType(ctxt), p);
    } 
    CoercionAction act = _checkFromStringCoercion(ctxt, text);
    if (act == CoercionAction.AsNull)
      return (Long)getNullValue(ctxt); 
    if (act == CoercionAction.AsEmpty)
      return (Long)getEmptyValue(ctxt); 
    String text = text.trim();
    if (_checkTextualNull(ctxt, text))
      return (Long)getNullValue(ctxt); 
    return _parseLong(ctxt, text);
  }
  
  protected final Long _parseLong(DeserializationContext ctxt, String text) throws IOException {
    try {
      return Long.valueOf(NumberInput.parseLong(text));
    } catch (IllegalArgumentException illegalArgumentException) {
      return (Long)ctxt.handleWeirdStringValue(Long.class, text, "not a valid `java.lang.Long` value", new Object[0]);
    } 
  }
  
  protected final float _parseFloatPrimitive(JsonParser p, DeserializationContext ctxt) throws IOException {
    CoercionAction coercionAction1;
    switch (p.currentTokenId()) {
      case 6:
        text = p.getText();
        break;
      case 7:
        coercionAction1 = _checkIntToFloatCoercion(p, ctxt, float.class);
        if (coercionAction1 == CoercionAction.AsNull)
          return 0.0F; 
        if (coercionAction1 == CoercionAction.AsEmpty)
          return 0.0F; 
      case 8:
        return p.getFloatValue();
      case 11:
        _verifyNullForPrimitive(ctxt);
        return 0.0F;
      case 1:
        text = ctxt.extractScalarFromObject(p, this, float.class);
        break;
      case 3:
        if (ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
          if (p.nextToken() == JsonToken.START_ARRAY)
            return ((Float)handleNestedArrayForSingle(p, ctxt)).floatValue(); 
          float parsed = _parseFloatPrimitive(p, ctxt);
          _verifyEndArrayForSingle(p, ctxt);
          return parsed;
        } 
      default:
        return ((Number)ctxt.handleUnexpectedToken(float.class, p)).floatValue();
    } 
    Float nan = _checkFloatSpecialValue(text);
    if (nan != null)
      return nan.floatValue(); 
    CoercionAction act = _checkFromStringCoercion(ctxt, text, LogicalType.Integer, float.class);
    if (act == CoercionAction.AsNull) {
      _verifyNullForPrimitive(ctxt);
      return 0.0F;
    } 
    if (act == CoercionAction.AsEmpty)
      return 0.0F; 
    String text = text.trim();
    if (_hasTextualNull(text)) {
      _verifyNullForPrimitiveCoercion(ctxt, text);
      return 0.0F;
    } 
    return _parseFloatPrimitive(p, ctxt, text);
  }
  
  protected final float _parseFloatPrimitive(DeserializationContext ctxt, String text) throws IOException {
    try {
      return NumberInput.parseFloat(text);
    } catch (IllegalArgumentException illegalArgumentException) {
      Number v = (Number)ctxt.handleWeirdStringValue(float.class, text, "not a valid `float` value", new Object[0]);
      return _nonNullNumber(v).floatValue();
    } 
  }
  
  protected final float _parseFloatPrimitive(JsonParser p, DeserializationContext ctxt, String text) throws IOException {
    try {
      return NumberInput.parseFloat(text, p.isEnabled(StreamReadFeature.USE_FAST_DOUBLE_PARSER));
    } catch (IllegalArgumentException illegalArgumentException) {
      Number v = (Number)ctxt.handleWeirdStringValue(float.class, text, "not a valid `float` value", new Object[0]);
      return _nonNullNumber(v).floatValue();
    } 
  }
  
  protected Float _checkFloatSpecialValue(String text) {
    if (!text.isEmpty())
      switch (text.charAt(0)) {
        case 'I':
          if (_isPosInf(text))
            return Float.valueOf(Float.POSITIVE_INFINITY); 
          break;
        case 'N':
          if (_isNaN(text))
            return Float.valueOf(Float.NaN); 
          break;
        case '-':
          if (_isNegInf(text))
            return Float.valueOf(Float.NEGATIVE_INFINITY); 
          break;
      }  
    return null;
  }
  
  protected final double _parseDoublePrimitive(JsonParser p, DeserializationContext ctxt) throws IOException {
    CoercionAction coercionAction1;
    switch (p.currentTokenId()) {
      case 6:
        text = p.getText();
        break;
      case 7:
        coercionAction1 = _checkIntToFloatCoercion(p, ctxt, double.class);
        if (coercionAction1 == CoercionAction.AsNull)
          return 0.0D; 
        if (coercionAction1 == CoercionAction.AsEmpty)
          return 0.0D; 
      case 8:
        return p.getDoubleValue();
      case 11:
        _verifyNullForPrimitive(ctxt);
        return 0.0D;
      case 1:
        text = ctxt.extractScalarFromObject(p, this, double.class);
        break;
      case 3:
        if (ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
          if (p.nextToken() == JsonToken.START_ARRAY)
            return ((Double)handleNestedArrayForSingle(p, ctxt)).doubleValue(); 
          double parsed = _parseDoublePrimitive(p, ctxt);
          _verifyEndArrayForSingle(p, ctxt);
          return parsed;
        } 
      default:
        return ((Number)ctxt.handleUnexpectedToken(double.class, p)).doubleValue();
    } 
    Double nan = _checkDoubleSpecialValue(text);
    if (nan != null)
      return nan.doubleValue(); 
    CoercionAction act = _checkFromStringCoercion(ctxt, text, LogicalType.Integer, double.class);
    if (act == CoercionAction.AsNull) {
      _verifyNullForPrimitive(ctxt);
      return 0.0D;
    } 
    if (act == CoercionAction.AsEmpty)
      return 0.0D; 
    String text = text.trim();
    if (_hasTextualNull(text)) {
      _verifyNullForPrimitiveCoercion(ctxt, text);
      return 0.0D;
    } 
    return _parseDoublePrimitive(p, ctxt, text);
  }
  
  protected final double _parseDoublePrimitive(DeserializationContext ctxt, String text) throws IOException {
    try {
      return _parseDouble(text);
    } catch (IllegalArgumentException illegalArgumentException) {
      Number v = (Number)ctxt.handleWeirdStringValue(double.class, text, "not a valid `double` value (as String to convert)", new Object[0]);
      return _nonNullNumber(v).doubleValue();
    } 
  }
  
  protected final double _parseDoublePrimitive(JsonParser p, DeserializationContext ctxt, String text) throws IOException {
    try {
      return _parseDouble(text, p.isEnabled(StreamReadFeature.USE_FAST_DOUBLE_PARSER));
    } catch (IllegalArgumentException illegalArgumentException) {
      Number v = (Number)ctxt.handleWeirdStringValue(double.class, text, "not a valid `double` value (as String to convert)", new Object[0]);
      return _nonNullNumber(v).doubleValue();
    } 
  }
  
  protected static final double _parseDouble(String numStr) throws NumberFormatException {
    return _parseDouble(numStr, false);
  }
  
  protected static final double _parseDouble(String numStr, boolean useFastParser) throws NumberFormatException {
    return NumberInput.parseDouble(numStr, useFastParser);
  }
  
  protected Double _checkDoubleSpecialValue(String text) {
    if (!text.isEmpty())
      switch (text.charAt(0)) {
        case 'I':
          if (_isPosInf(text))
            return Double.valueOf(Double.POSITIVE_INFINITY); 
          break;
        case 'N':
          if (_isNaN(text))
            return Double.valueOf(Double.NaN); 
          break;
        case '-':
          if (_isNegInf(text))
            return Double.valueOf(Double.NEGATIVE_INFINITY); 
          break;
      }  
    return null;
  }
  
  protected Date _parseDate(JsonParser p, DeserializationContext ctxt) throws IOException {
    String text;
    long ts;
    switch (p.currentTokenId()) {
      case 6:
        text = p.getText();
        return _parseDate(text.trim(), ctxt);
      case 7:
        try {
          ts = p.getLongValue();
        } catch (StreamReadException e) {
          Number v = (Number)ctxt.handleWeirdNumberValue(this._valueClass, p.getNumberValue(), "not a valid 64-bit `long` for creating `java.util.Date`", new Object[0]);
          ts = v.longValue();
        } 
        return new Date(ts);
      case 11:
        return (Date)getNullValue(ctxt);
      case 1:
        text = ctxt.extractScalarFromObject(p, this, this._valueClass);
        return _parseDate(text.trim(), ctxt);
      case 3:
        return _parseDateFromArray(p, ctxt);
    } 
    return (Date)ctxt.handleUnexpectedToken(this._valueClass, p);
  }
  
  protected Date _parseDateFromArray(JsonParser p, DeserializationContext ctxt) throws IOException {
    CoercionAction act = _findCoercionFromEmptyArray(ctxt);
    boolean unwrap = ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
    if (unwrap || act != CoercionAction.Fail) {
      JsonToken t = p.nextToken();
      if (t == JsonToken.END_ARRAY) {
        switch (act) {
          case AsEmpty:
            return (Date)getEmptyValue(ctxt);
          case AsNull:
          case TryConvert:
            return (Date)getNullValue(ctxt);
        } 
      } else if (unwrap) {
        if (t == JsonToken.START_ARRAY)
          return (Date)handleNestedArrayForSingle(p, ctxt); 
        Date parsed = _parseDate(p, ctxt);
        _verifyEndArrayForSingle(p, ctxt);
        return parsed;
      } 
    } 
    return (Date)ctxt.handleUnexpectedToken(this._valueClass, JsonToken.START_ARRAY, p, null, new Object[0]);
  }
  
  protected Date _parseDate(String value, DeserializationContext ctxt) throws IOException {
    try {
      if (value.isEmpty()) {
        CoercionAction act = _checkFromStringCoercion(ctxt, value);
        switch (act) {
          case AsEmpty:
            return new Date(0L);
        } 
        return null;
      } 
      if (_hasTextualNull(value))
        return null; 
      return ctxt.parseDate(value);
    } catch (IllegalArgumentException iae) {
      return (Date)ctxt.handleWeirdStringValue(this._valueClass, value, "not a valid representation (error: %s)", new Object[] { ClassUtil.exceptionMessage(iae) });
    } 
  }
  
  @Deprecated
  protected final String _parseString(JsonParser p, DeserializationContext ctxt) throws IOException {
    return _parseString(p, ctxt, 
        
        (NullValueProvider)NullsConstantProvider.nuller());
  }
  
  protected final String _parseString(JsonParser p, DeserializationContext ctxt, NullValueProvider nullProvider) throws IOException {
    Object ob;
    CoercionAction act = CoercionAction.TryConvert;
    Class<?> rawTargetType = String.class;
    switch (p.currentTokenId()) {
      case 6:
        return p.getText();
      case 12:
        ob = p.getEmbeddedObject();
        if (ob instanceof byte[])
          return ctxt.getBase64Variant().encode((byte[])ob, false); 
        if (ob == null)
          return null; 
        return ob.toString();
      case 1:
        return ctxt.extractScalarFromObject(p, this, rawTargetType);
      case 7:
        act = _checkIntToStringCoercion(p, ctxt, rawTargetType);
        break;
      case 8:
        act = _checkFloatToStringCoercion(p, ctxt, rawTargetType);
        break;
      case 9:
      case 10:
        act = _checkBooleanToStringCoercion(p, ctxt, rawTargetType);
        break;
    } 
    if (act == CoercionAction.AsNull)
      return (String)nullProvider.getNullValue(ctxt); 
    if (act == CoercionAction.AsEmpty)
      return ""; 
    if (p.currentToken().isScalarValue()) {
      String text = p.getValueAsString();
      if (text != null)
        return text; 
    } 
    return (String)ctxt.handleUnexpectedToken(rawTargetType, p);
  }
  
  protected boolean _hasTextualNull(String value) {
    return "null".equals(value);
  }
  
  protected final boolean _isNegInf(String text) {
    return ("-Infinity".equals(text) || "-INF".equals(text));
  }
  
  protected final boolean _isPosInf(String text) {
    return ("Infinity".equals(text) || "INF".equals(text));
  }
  
  protected final boolean _isNaN(String text) {
    return "NaN".equals(text);
  }
  
  protected static final boolean _isBlank(String text) {
    int len = text.length();
    for (int i = 0; i < len; i++) {
      if (text.charAt(i) > ' ')
        return false; 
    } 
    return true;
  }
  
  protected CoercionAction _checkFromStringCoercion(DeserializationContext ctxt, String value) throws IOException {
    return _checkFromStringCoercion(ctxt, value, logicalType(), handledType());
  }
  
  protected CoercionAction _checkFromStringCoercion(DeserializationContext ctxt, String value, LogicalType logicalType, Class<?> rawTargetType) throws IOException {
    if (value.isEmpty()) {
      CoercionAction coercionAction = ctxt.findCoercionAction(logicalType, rawTargetType, CoercionInputShape.EmptyString);
      return _checkCoercionFail(ctxt, coercionAction, rawTargetType, value, "empty String (\"\")");
    } 
    if (_isBlank(value)) {
      CoercionAction coercionAction = ctxt.findCoercionFromBlankString(logicalType, rawTargetType, CoercionAction.Fail);
      return _checkCoercionFail(ctxt, coercionAction, rawTargetType, value, "blank String (all whitespace)");
    } 
    if (ctxt.isEnabled(StreamReadCapability.UNTYPED_SCALARS))
      return CoercionAction.TryConvert; 
    CoercionAction act = ctxt.findCoercionAction(logicalType, rawTargetType, CoercionInputShape.String);
    if (act == CoercionAction.Fail)
      ctxt.reportInputMismatch(this, "Cannot coerce String value (\"%s\") to %s (but might if coercion using `CoercionConfig` was enabled)", new Object[] { value, 
            
            _coercedTypeDesc() }); 
    return act;
  }
  
  protected CoercionAction _checkFloatToIntCoercion(JsonParser p, DeserializationContext ctxt, Class<?> rawTargetType) throws IOException {
    CoercionAction act = ctxt.findCoercionAction(LogicalType.Integer, rawTargetType, CoercionInputShape.Float);
    if (act == CoercionAction.Fail)
      return _checkCoercionFail(ctxt, act, rawTargetType, p.getNumberValue(), "Floating-point value (" + p
          .getText() + ")"); 
    return act;
  }
  
  protected CoercionAction _checkIntToStringCoercion(JsonParser p, DeserializationContext ctxt, Class<?> rawTargetType) throws IOException {
    return _checkToStringCoercion(p, ctxt, rawTargetType, p.getNumberValue(), CoercionInputShape.Integer);
  }
  
  protected CoercionAction _checkFloatToStringCoercion(JsonParser p, DeserializationContext ctxt, Class<?> rawTargetType) throws IOException {
    return _checkToStringCoercion(p, ctxt, rawTargetType, p.getNumberValue(), CoercionInputShape.Float);
  }
  
  protected CoercionAction _checkBooleanToStringCoercion(JsonParser p, DeserializationContext ctxt, Class<?> rawTargetType) throws IOException {
    return _checkToStringCoercion(p, ctxt, rawTargetType, Boolean.valueOf(p.getBooleanValue()), CoercionInputShape.Boolean);
  }
  
  protected CoercionAction _checkToStringCoercion(JsonParser p, DeserializationContext ctxt, Class<?> rawTargetType, Object inputValue, CoercionInputShape inputShape) throws IOException {
    CoercionAction act = ctxt.findCoercionAction(LogicalType.Textual, rawTargetType, inputShape);
    if (act == CoercionAction.Fail)
      return _checkCoercionFail(ctxt, act, rawTargetType, inputValue, inputShape
          .name() + " value (" + p.getText() + ")"); 
    return act;
  }
  
  protected CoercionAction _checkIntToFloatCoercion(JsonParser p, DeserializationContext ctxt, Class<?> rawTargetType) throws IOException {
    CoercionAction act = ctxt.findCoercionAction(LogicalType.Float, rawTargetType, CoercionInputShape.Integer);
    if (act == CoercionAction.Fail)
      return _checkCoercionFail(ctxt, act, rawTargetType, p.getNumberValue(), "Integer value (" + p
          .getText() + ")"); 
    return act;
  }
  
  protected Boolean _coerceBooleanFromInt(JsonParser p, DeserializationContext ctxt, Class<?> rawTargetType) throws IOException {
    CoercionAction act = ctxt.findCoercionAction(LogicalType.Boolean, rawTargetType, CoercionInputShape.Integer);
    switch (act) {
      case Fail:
        _checkCoercionFail(ctxt, act, rawTargetType, p.getNumberValue(), "Integer value (" + p
            .getText() + ")");
        return Boolean.FALSE;
      case AsNull:
        return null;
      case AsEmpty:
        return Boolean.FALSE;
    } 
    if (p.getNumberType() == JsonParser.NumberType.INT)
      return Boolean.valueOf((p.getIntValue() != 0)); 
    return Boolean.valueOf(!"0".equals(p.getText()));
  }
  
  protected CoercionAction _checkCoercionFail(DeserializationContext ctxt, CoercionAction act, Class<?> targetType, Object inputValue, String inputDesc) throws IOException {
    if (act == CoercionAction.Fail)
      ctxt.reportBadCoercion(this, targetType, inputValue, "Cannot coerce %s to %s (but could if coercion was enabled using `CoercionConfig`)", new Object[] { inputDesc, 
            
            _coercedTypeDesc(targetType) }); 
    return act;
  }
  
  protected boolean _checkTextualNull(DeserializationContext ctxt, String text) throws JsonMappingException {
    if (_hasTextualNull(text)) {
      if (!ctxt.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS))
        _reportFailedNullCoerce(ctxt, true, (Enum<?>)MapperFeature.ALLOW_COERCION_OF_SCALARS, "String \"null\""); 
      return true;
    } 
    return false;
  }
  
  protected Object _coerceIntegral(JsonParser p, DeserializationContext ctxt) throws IOException {
    if (ctxt.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS))
      return p.getBigIntegerValue(); 
    if (ctxt.isEnabled(DeserializationFeature.USE_LONG_FOR_INTS))
      return Long.valueOf(p.getLongValue()); 
    return p.getNumberValue();
  }
  
  protected final void _verifyNullForPrimitive(DeserializationContext ctxt) throws JsonMappingException {
    if (ctxt.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES))
      ctxt.reportInputMismatch(this, "Cannot coerce `null` to %s (disable `DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES` to allow)", new Object[] { _coercedTypeDesc() }); 
  }
  
  protected final void _verifyNullForPrimitiveCoercion(DeserializationContext ctxt, String str) throws JsonMappingException {
    DeserializationFeature deserializationFeature;
    boolean enable;
    if (!ctxt.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
      MapperFeature mapperFeature = MapperFeature.ALLOW_COERCION_OF_SCALARS;
      enable = true;
    } else if (ctxt.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
      deserializationFeature = DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;
      enable = false;
    } else {
      return;
    } 
    String strDesc = str.isEmpty() ? "empty String (\"\")" : String.format("String \"%s\"", new Object[] { str });
    _reportFailedNullCoerce(ctxt, enable, (Enum<?>)deserializationFeature, strDesc);
  }
  
  protected void _reportFailedNullCoerce(DeserializationContext ctxt, boolean state, Enum<?> feature, String inputDesc) throws JsonMappingException {
    String enableDesc = state ? "enable" : "disable";
    ctxt.reportInputMismatch(this, "Cannot coerce %s to Null value as %s (%s `%s.%s` to allow)", new Object[] { inputDesc, 
          _coercedTypeDesc(), enableDesc, feature.getDeclaringClass().getSimpleName(), feature.name() });
  }
  
  protected String _coercedTypeDesc() {
    boolean structured;
    String typeDesc;
    JavaType t = getValueType();
    if (t != null && !t.isPrimitive()) {
      structured = (t.isContainerType() || t.isReferenceType());
      typeDesc = ClassUtil.getTypeDescription(t);
    } else {
      Class<?> cls = handledType();
      structured = ClassUtil.isCollectionMapOrArray(cls);
      typeDesc = ClassUtil.getClassDescription(cls);
    } 
    if (structured)
      return "element of " + typeDesc; 
    return typeDesc + " value";
  }
  
  protected String _coercedTypeDesc(Class<?> rawTargetType) {
    String typeDesc = ClassUtil.getClassDescription(rawTargetType);
    if (ClassUtil.isCollectionMapOrArray(rawTargetType))
      return "element of " + typeDesc; 
    return typeDesc + " value";
  }
  
  @Deprecated
  protected boolean _parseBooleanFromInt(JsonParser p, DeserializationContext ctxt) throws IOException {
    _verifyNumberForScalarCoercion(ctxt, p);
    return !"0".equals(p.getText());
  }
  
  @Deprecated
  protected void _verifyStringForScalarCoercion(DeserializationContext ctxt, String str) throws JsonMappingException {
    MapperFeature feat = MapperFeature.ALLOW_COERCION_OF_SCALARS;
    if (!ctxt.isEnabled(feat))
      ctxt.reportInputMismatch(this, "Cannot coerce String \"%s\" to %s (enable `%s.%s` to allow)", new Object[] { str, 
            _coercedTypeDesc(), feat.getDeclaringClass().getSimpleName(), feat.name() }); 
  }
  
  @Deprecated
  protected Object _coerceEmptyString(DeserializationContext ctxt, boolean isPrimitive) throws JsonMappingException {
    DeserializationFeature deserializationFeature;
    boolean enable;
    if (!ctxt.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
      MapperFeature mapperFeature = MapperFeature.ALLOW_COERCION_OF_SCALARS;
      enable = true;
    } else if (isPrimitive && ctxt.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
      deserializationFeature = DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;
      enable = false;
    } else {
      return getNullValue(ctxt);
    } 
    _reportFailedNullCoerce(ctxt, enable, (Enum<?>)deserializationFeature, "empty String (\"\")");
    return null;
  }
  
  @Deprecated
  protected void _failDoubleToIntCoercion(JsonParser p, DeserializationContext ctxt, String type) throws IOException {
    ctxt.reportInputMismatch(handledType(), "Cannot coerce a floating-point value ('%s') into %s (enable `DeserializationFeature.ACCEPT_FLOAT_AS_INT` to allow)", new Object[] { p
          
          .getValueAsString(), type });
  }
  
  @Deprecated
  protected final void _verifyNullForScalarCoercion(DeserializationContext ctxt, String str) throws JsonMappingException {
    if (!ctxt.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
      String strDesc = str.isEmpty() ? "empty String (\"\")" : String.format("String \"%s\"", new Object[] { str });
      _reportFailedNullCoerce(ctxt, true, (Enum<?>)MapperFeature.ALLOW_COERCION_OF_SCALARS, strDesc);
    } 
  }
  
  @Deprecated
  protected void _verifyNumberForScalarCoercion(DeserializationContext ctxt, JsonParser p) throws IOException {
    MapperFeature feat = MapperFeature.ALLOW_COERCION_OF_SCALARS;
    if (!ctxt.isEnabled(feat)) {
      String valueDesc = p.getText();
      ctxt.reportInputMismatch(this, "Cannot coerce Number (%s) to %s (enable `%s.%s` to allow)", new Object[] { valueDesc, 
            _coercedTypeDesc(), feat.getDeclaringClass().getSimpleName(), feat.name() });
    } 
  }
  
  @Deprecated
  protected Object _coerceNullToken(DeserializationContext ctxt, boolean isPrimitive) throws JsonMappingException {
    if (isPrimitive)
      _verifyNullForPrimitive(ctxt); 
    return getNullValue(ctxt);
  }
  
  @Deprecated
  protected Object _coerceTextualNull(DeserializationContext ctxt, boolean isPrimitive) throws JsonMappingException {
    if (!ctxt.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS))
      _reportFailedNullCoerce(ctxt, true, (Enum<?>)MapperFeature.ALLOW_COERCION_OF_SCALARS, "String \"null\""); 
    return getNullValue(ctxt);
  }
  
  @Deprecated
  protected boolean _isEmptyOrTextualNull(String value) {
    return (value.isEmpty() || "null".equals(value));
  }
  
  protected JsonDeserializer<Object> findDeserializer(DeserializationContext ctxt, JavaType type, BeanProperty property) throws JsonMappingException {
    return ctxt.findContextualValueDeserializer(type, property);
  }
  
  protected final boolean _isIntNumber(String text) {
    int len = text.length();
    if (len > 0) {
      int i;
      char c = text.charAt(0);
      if (c == '-' || c == '+') {
        if (len == 1)
          return false; 
        i = 1;
      } else {
        i = 0;
      } 
      for (; i < len; i++) {
        int ch = text.charAt(i);
        if (ch > 57 || ch < 48)
          return false; 
      } 
      return true;
    } 
    return false;
  }
  
  protected JsonDeserializer<?> findConvertingContentDeserializer(DeserializationContext ctxt, BeanProperty prop, JsonDeserializer<?> existingDeserializer) throws JsonMappingException {
    AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
    if (_neitherNull(intr, prop)) {
      AnnotatedMember member = prop.getMember();
      if (member != null) {
        Object convDef = intr.findDeserializationContentConverter(member);
        if (convDef != null) {
          Converter<Object, Object> conv = ctxt.converterInstance((Annotated)prop.getMember(), convDef);
          JavaType delegateType = conv.getInputType(ctxt.getTypeFactory());
          if (existingDeserializer == null)
            existingDeserializer = ctxt.findContextualValueDeserializer(delegateType, prop); 
          return new StdDelegatingDeserializer(conv, delegateType, existingDeserializer);
        } 
      } 
    } 
    return existingDeserializer;
  }
  
  protected JsonFormat.Value findFormatOverrides(DeserializationContext ctxt, BeanProperty prop, Class<?> typeForDefaults) {
    if (prop != null)
      return prop.findPropertyFormat((MapperConfig)ctxt.getConfig(), typeForDefaults); 
    return ctxt.getDefaultPropertyFormat(typeForDefaults);
  }
  
  protected Boolean findFormatFeature(DeserializationContext ctxt, BeanProperty prop, Class<?> typeForDefaults, JsonFormat.Feature feat) {
    JsonFormat.Value format = findFormatOverrides(ctxt, prop, typeForDefaults);
    if (format != null)
      return format.getFeature(feat); 
    return null;
  }
  
  protected final NullValueProvider findValueNullProvider(DeserializationContext ctxt, SettableBeanProperty prop, PropertyMetadata propMetadata) throws JsonMappingException {
    if (prop != null)
      return _findNullProvider(ctxt, (BeanProperty)prop, propMetadata.getValueNulls(), prop
          .getValueDeserializer()); 
    return null;
  }
  
  protected NullValueProvider findContentNullProvider(DeserializationContext ctxt, BeanProperty prop, JsonDeserializer<?> valueDeser) throws JsonMappingException {
    Nulls nulls = findContentNullStyle(ctxt, prop);
    if (nulls == Nulls.SKIP)
      return (NullValueProvider)NullsConstantProvider.skipper(); 
    if (nulls == Nulls.FAIL) {
      if (prop == null) {
        JavaType type = ctxt.constructType(valueDeser.handledType());
        if (type.isContainerType())
          type = type.getContentType(); 
        return (NullValueProvider)NullsFailProvider.constructForRootValue(type);
      } 
      return (NullValueProvider)NullsFailProvider.constructForProperty(prop, prop.getType().getContentType());
    } 
    NullValueProvider prov = _findNullProvider(ctxt, prop, nulls, valueDeser);
    if (prov != null)
      return prov; 
    return (NullValueProvider)valueDeser;
  }
  
  protected Nulls findContentNullStyle(DeserializationContext ctxt, BeanProperty prop) throws JsonMappingException {
    if (prop != null)
      return prop.getMetadata().getContentNulls(); 
    return ctxt.getConfig().getDefaultSetterInfo().getContentNulls();
  }
  
  protected final NullValueProvider _findNullProvider(DeserializationContext ctxt, BeanProperty prop, Nulls nulls, JsonDeserializer<?> valueDeser) throws JsonMappingException {
    if (nulls == Nulls.FAIL) {
      if (prop == null) {
        Class<?> rawType = (valueDeser == null) ? Object.class : valueDeser.handledType();
        return (NullValueProvider)NullsFailProvider.constructForRootValue(ctxt.constructType(rawType));
      } 
      return (NullValueProvider)NullsFailProvider.constructForProperty(prop);
    } 
    if (nulls == Nulls.AS_EMPTY) {
      if (valueDeser == null)
        return null; 
      if (valueDeser instanceof BeanDeserializerBase) {
        BeanDeserializerBase bd = (BeanDeserializerBase)valueDeser;
        ValueInstantiator vi = bd.getValueInstantiator();
        if (!vi.canCreateUsingDefault()) {
          JavaType type = (prop == null) ? bd.getValueType() : prop.getType();
          return (NullValueProvider)ctxt.reportBadDefinition(type, 
              String.format("Cannot create empty instance of %s, no default Creator", new Object[] { type }));
        } 
      } 
      AccessPattern access = valueDeser.getEmptyAccessPattern();
      if (access == AccessPattern.ALWAYS_NULL)
        return (NullValueProvider)NullsConstantProvider.nuller(); 
      if (access == AccessPattern.CONSTANT)
        return (NullValueProvider)NullsConstantProvider.forValue(valueDeser.getEmptyValue(ctxt)); 
      return (NullValueProvider)new NullsAsEmptyProvider(valueDeser);
    } 
    if (nulls == Nulls.SKIP)
      return (NullValueProvider)NullsConstantProvider.skipper(); 
    return null;
  }
  
  protected CoercionAction _findCoercionFromEmptyString(DeserializationContext ctxt) {
    return ctxt.findCoercionAction(logicalType(), handledType(), CoercionInputShape.EmptyString);
  }
  
  protected CoercionAction _findCoercionFromEmptyArray(DeserializationContext ctxt) {
    return ctxt.findCoercionAction(logicalType(), handledType(), CoercionInputShape.EmptyArray);
  }
  
  protected CoercionAction _findCoercionFromBlankString(DeserializationContext ctxt) {
    return ctxt.findCoercionFromBlankString(logicalType(), handledType(), CoercionAction.Fail);
  }
  
  protected void handleUnknownProperty(JsonParser p, DeserializationContext ctxt, Object<?> instanceOrClass, String propName) throws IOException {
    if (instanceOrClass == null)
      instanceOrClass = (Object<?>)handledType(); 
    if (ctxt.handleUnknownProperty(p, this, instanceOrClass, propName))
      return; 
    p.skipChildren();
  }
  
  protected void handleMissingEndArrayForSingle(JsonParser p, DeserializationContext ctxt) throws IOException {
    ctxt.reportWrongTokenException(this, JsonToken.END_ARRAY, "Attempted to unwrap '%s' value from an array (with `DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS`) but it contains more than one value", new Object[] { handledType().getName() });
  }
  
  protected Object handleNestedArrayForSingle(JsonParser p, DeserializationContext ctxt) throws IOException {
    String msg = String.format("Cannot deserialize instance of %s out of %s token: nested Arrays not allowed with %s", new Object[] { ClassUtil.nameOf(this._valueClass), JsonToken.START_ARRAY, "DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS" });
    return ctxt.handleUnexpectedToken(getValueType(ctxt), p.currentToken(), p, msg, new Object[0]);
  }
  
  protected void _verifyEndArrayForSingle(JsonParser p, DeserializationContext ctxt) throws IOException {
    JsonToken t = p.nextToken();
    if (t != JsonToken.END_ARRAY)
      handleMissingEndArrayForSingle(p, ctxt); 
  }
  
  protected static final boolean _neitherNull(Object a, Object b) {
    return (a != null && b != null);
  }
  
  protected final boolean _byteOverflow(int value) {
    return (value < -128 || value > 255);
  }
  
  protected final boolean _shortOverflow(int value) {
    return (value < -32768 || value > 32767);
  }
  
  protected final boolean _intOverflow(long value) {
    return (value < -2147483648L || value > 2147483647L);
  }
  
  protected Number _nonNullNumber(Number n) {
    if (n == null)
      n = Integer.valueOf(0); 
    return n;
  }
}
