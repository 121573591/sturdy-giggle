package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;

public class NumberDeserializers {
  private static final HashSet<String> _classNames = new HashSet<>();
  
  static {
    Class<?>[] numberTypes = new Class[] { 
        Boolean.class, Byte.class, Short.class, Character.class, Integer.class, Long.class, Float.class, Double.class, Number.class, BigDecimal.class, 
        BigInteger.class };
    for (Class<?> cls : numberTypes)
      _classNames.add(cls.getName()); 
  }
  
  public static JsonDeserializer<?> find(Class<?> rawType, String clsName) {
    if (rawType.isPrimitive()) {
      if (rawType == int.class)
        return IntegerDeserializer.primitiveInstance; 
      if (rawType == boolean.class)
        return BooleanDeserializer.primitiveInstance; 
      if (rawType == long.class)
        return LongDeserializer.primitiveInstance; 
      if (rawType == double.class)
        return DoubleDeserializer.primitiveInstance; 
      if (rawType == char.class)
        return CharacterDeserializer.primitiveInstance; 
      if (rawType == byte.class)
        return ByteDeserializer.primitiveInstance; 
      if (rawType == short.class)
        return ShortDeserializer.primitiveInstance; 
      if (rawType == float.class)
        return FloatDeserializer.primitiveInstance; 
      if (rawType == void.class)
        return NullifyingDeserializer.instance; 
    } else if (_classNames.contains(clsName)) {
      if (rawType == Integer.class)
        return IntegerDeserializer.wrapperInstance; 
      if (rawType == Boolean.class)
        return BooleanDeserializer.wrapperInstance; 
      if (rawType == Long.class)
        return LongDeserializer.wrapperInstance; 
      if (rawType == Double.class)
        return DoubleDeserializer.wrapperInstance; 
      if (rawType == Character.class)
        return CharacterDeserializer.wrapperInstance; 
      if (rawType == Byte.class)
        return ByteDeserializer.wrapperInstance; 
      if (rawType == Short.class)
        return ShortDeserializer.wrapperInstance; 
      if (rawType == Float.class)
        return FloatDeserializer.wrapperInstance; 
      if (rawType == Number.class)
        return NumberDeserializer.instance; 
      if (rawType == BigDecimal.class)
        return BigDecimalDeserializer.instance; 
      if (rawType == BigInteger.class)
        return BigIntegerDeserializer.instance; 
    } else {
      return null;
    } 
    throw new IllegalArgumentException("Internal error: can't find deserializer for " + rawType.getName());
  }
  
  protected static abstract class PrimitiveOrWrapperDeserializer<T> extends StdScalarDeserializer<T> {
    private static final long serialVersionUID = 1L;
    
    protected final LogicalType _logicalType;
    
    protected final T _nullValue;
    
    protected final T _emptyValue;
    
    protected final boolean _primitive;
    
    protected PrimitiveOrWrapperDeserializer(Class<T> vc, LogicalType logicalType, T nvl, T empty) {
      super(vc);
      this._logicalType = logicalType;
      this._nullValue = nvl;
      this._emptyValue = empty;
      this._primitive = vc.isPrimitive();
    }
    
    @Deprecated
    protected PrimitiveOrWrapperDeserializer(Class<T> vc, T nvl, T empty) {
      this(vc, LogicalType.OtherScalar, nvl, empty);
    }
    
    public AccessPattern getNullAccessPattern() {
      if (this._primitive)
        return AccessPattern.DYNAMIC; 
      if (this._nullValue == null)
        return AccessPattern.ALWAYS_NULL; 
      return AccessPattern.CONSTANT;
    }
    
    public final T getNullValue(DeserializationContext ctxt) throws JsonMappingException {
      if (this._primitive && ctxt.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES))
        ctxt.reportInputMismatch(this, "Cannot map `null` into type %s (set DeserializationConfig.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)", new Object[] { ClassUtil.classNameOf(handledType()) }); 
      return this._nullValue;
    }
    
    public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
      return this._emptyValue;
    }
    
    public final LogicalType logicalType() {
      return this._logicalType;
    }
  }
  
  @JacksonStdImpl
  public static final class BooleanDeserializer extends PrimitiveOrWrapperDeserializer<Boolean> {
    private static final long serialVersionUID = 1L;
    
    static final BooleanDeserializer primitiveInstance = new BooleanDeserializer((Class)boolean.class, Boolean.FALSE);
    
    static final BooleanDeserializer wrapperInstance = new BooleanDeserializer(Boolean.class, null);
    
    public BooleanDeserializer(Class<Boolean> cls, Boolean nvl) {
      super(cls, LogicalType.Boolean, nvl, Boolean.FALSE);
    }
    
    public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonToken t = p.currentToken();
      if (t == JsonToken.VALUE_TRUE)
        return Boolean.TRUE; 
      if (t == JsonToken.VALUE_FALSE)
        return Boolean.FALSE; 
      if (this._primitive)
        return Boolean.valueOf(_parseBooleanPrimitive(p, ctxt)); 
      return _parseBoolean(p, ctxt, this._valueClass);
    }
    
    public Boolean deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
      JsonToken t = p.currentToken();
      if (t == JsonToken.VALUE_TRUE)
        return Boolean.TRUE; 
      if (t == JsonToken.VALUE_FALSE)
        return Boolean.FALSE; 
      if (this._primitive)
        return Boolean.valueOf(_parseBooleanPrimitive(p, ctxt)); 
      return _parseBoolean(p, ctxt, this._valueClass);
    }
  }
  
  @JacksonStdImpl
  public static class ByteDeserializer extends PrimitiveOrWrapperDeserializer<Byte> {
    private static final long serialVersionUID = 1L;
    
    static final ByteDeserializer primitiveInstance = new ByteDeserializer((Class)byte.class, Byte.valueOf((byte)0));
    
    static final ByteDeserializer wrapperInstance = new ByteDeserializer(Byte.class, null);
    
    public ByteDeserializer(Class<Byte> cls, Byte nvl) {
      super(cls, LogicalType.Integer, nvl, Byte.valueOf((byte)0));
    }
    
    public Byte deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      if (p.isExpectedNumberIntToken())
        return Byte.valueOf(p.getByteValue()); 
      if (this._primitive)
        return Byte.valueOf(_parseBytePrimitive(p, ctxt)); 
      return _parseByte(p, ctxt);
    }
    
    protected Byte _parseByte(JsonParser p, DeserializationContext ctxt) throws IOException {
      int value;
      switch (p.currentTokenId()) {
        case 6:
          text = p.getText();
          break;
        case 8:
          act = _checkFloatToIntCoercion(p, ctxt, this._valueClass);
          if (act == CoercionAction.AsNull)
            return getNullValue(ctxt); 
          if (act == CoercionAction.AsEmpty)
            return (Byte)getEmptyValue(ctxt); 
          return Byte.valueOf(p.getByteValue());
        case 11:
          return getNullValue(ctxt);
        case 7:
          return Byte.valueOf(p.getByteValue());
        case 3:
          return _deserializeFromArray(p, ctxt);
        case 1:
          text = ctxt.extractScalarFromObject(p, this, this._valueClass);
          break;
        default:
          return (Byte)ctxt.handleUnexpectedToken(getValueType(ctxt), p);
      } 
      CoercionAction act = _checkFromStringCoercion(ctxt, text);
      if (act == CoercionAction.AsNull)
        return getNullValue(ctxt); 
      if (act == CoercionAction.AsEmpty)
        return (Byte)getEmptyValue(ctxt); 
      String text = text.trim();
      if (_checkTextualNull(ctxt, text))
        return getNullValue(ctxt); 
      try {
        value = NumberInput.parseInt(text);
      } catch (IllegalArgumentException iae) {
        return (Byte)ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid Byte value", new Object[0]);
      } 
      if (_byteOverflow(value))
        return (Byte)ctxt.handleWeirdStringValue(this._valueClass, text, "overflow, value cannot be represented as 8-bit value", new Object[0]); 
      return Byte.valueOf((byte)value);
    }
  }
  
  @JacksonStdImpl
  public static class ShortDeserializer extends PrimitiveOrWrapperDeserializer<Short> {
    private static final long serialVersionUID = 1L;
    
    static final ShortDeserializer primitiveInstance = new ShortDeserializer((Class)short.class, Short.valueOf((short)0));
    
    static final ShortDeserializer wrapperInstance = new ShortDeserializer(Short.class, null);
    
    public ShortDeserializer(Class<Short> cls, Short nvl) {
      super(cls, LogicalType.Integer, nvl, Short.valueOf((short)0));
    }
    
    public Short deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      if (p.isExpectedNumberIntToken())
        return Short.valueOf(p.getShortValue()); 
      if (this._primitive)
        return Short.valueOf(_parseShortPrimitive(p, ctxt)); 
      return _parseShort(p, ctxt);
    }
    
    protected Short _parseShort(JsonParser p, DeserializationContext ctxt) throws IOException {
      int value;
      switch (p.currentTokenId()) {
        case 6:
          text = p.getText();
          break;
        case 8:
          act = _checkFloatToIntCoercion(p, ctxt, this._valueClass);
          if (act == CoercionAction.AsNull)
            return getNullValue(ctxt); 
          if (act == CoercionAction.AsEmpty)
            return (Short)getEmptyValue(ctxt); 
          return Short.valueOf(p.getShortValue());
        case 11:
          return getNullValue(ctxt);
        case 7:
          return Short.valueOf(p.getShortValue());
        case 1:
          text = ctxt.extractScalarFromObject(p, this, this._valueClass);
          break;
        case 3:
          return _deserializeFromArray(p, ctxt);
        default:
          return (Short)ctxt.handleUnexpectedToken(getValueType(ctxt), p);
      } 
      CoercionAction act = _checkFromStringCoercion(ctxt, text);
      if (act == CoercionAction.AsNull)
        return getNullValue(ctxt); 
      if (act == CoercionAction.AsEmpty)
        return (Short)getEmptyValue(ctxt); 
      String text = text.trim();
      if (_checkTextualNull(ctxt, text))
        return getNullValue(ctxt); 
      try {
        value = NumberInput.parseInt(text);
      } catch (IllegalArgumentException iae) {
        return (Short)ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid Short value", new Object[0]);
      } 
      if (_shortOverflow(value))
        return (Short)ctxt.handleWeirdStringValue(this._valueClass, text, "overflow, value cannot be represented as 16-bit value", new Object[0]); 
      return Short.valueOf((short)value);
    }
  }
  
  @JacksonStdImpl
  public static class CharacterDeserializer extends PrimitiveOrWrapperDeserializer<Character> {
    private static final long serialVersionUID = 1L;
    
    static final CharacterDeserializer primitiveInstance = new CharacterDeserializer((Class)char.class, Character.valueOf(false));
    
    static final CharacterDeserializer wrapperInstance = new CharacterDeserializer(Character.class, null);
    
    public CharacterDeserializer(Class<Character> cls, Character nvl) {
      super(cls, LogicalType.Integer, nvl, 
          
          Character.valueOf(false));
    }
    
    public Character deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      int value;
      switch (p.currentTokenId()) {
        case 6:
          text = p.getText();
          break;
        case 7:
          act = ctxt.findCoercionAction(logicalType(), this._valueClass, CoercionInputShape.Integer);
          switch (act) {
            case Fail:
              _checkCoercionFail(ctxt, act, this._valueClass, p.getNumberValue(), "Integer value (" + p
                  .getText() + ")");
            case AsNull:
              return getNullValue(ctxt);
            case AsEmpty:
              return (Character)getEmptyValue(ctxt);
          } 
          value = p.getIntValue();
          if (value >= 0 && value <= 65535)
            return Character.valueOf((char)value); 
          return (Character)ctxt.handleWeirdNumberValue(handledType(), Integer.valueOf(value), "value outside valid Character range (0x0000 - 0xFFFF)", new Object[0]);
        case 11:
          if (this._primitive)
            _verifyNullForPrimitive(ctxt); 
          return getNullValue(ctxt);
        case 1:
          text = ctxt.extractScalarFromObject(p, this, this._valueClass);
          break;
        case 3:
          return _deserializeFromArray(p, ctxt);
        default:
          return (Character)ctxt.handleUnexpectedToken(getValueType(ctxt), p);
      } 
      if (text.length() == 1)
        return Character.valueOf(text.charAt(0)); 
      CoercionAction act = _checkFromStringCoercion(ctxt, text);
      if (act == CoercionAction.AsNull)
        return getNullValue(ctxt); 
      if (act == CoercionAction.AsEmpty)
        return (Character)getEmptyValue(ctxt); 
      String text = text.trim();
      if (_checkTextualNull(ctxt, text))
        return getNullValue(ctxt); 
      return (Character)ctxt.handleWeirdStringValue(handledType(), text, "Expected either Integer value code or 1-character String", new Object[0]);
    }
  }
  
  @JacksonStdImpl
  public static final class IntegerDeserializer extends PrimitiveOrWrapperDeserializer<Integer> {
    private static final long serialVersionUID = 1L;
    
    static final IntegerDeserializer primitiveInstance = new IntegerDeserializer((Class)int.class, Integer.valueOf(0));
    
    static final IntegerDeserializer wrapperInstance = new IntegerDeserializer(Integer.class, null);
    
    public IntegerDeserializer(Class<Integer> cls, Integer nvl) {
      super(cls, LogicalType.Integer, nvl, Integer.valueOf(0));
    }
    
    public boolean isCachable() {
      return true;
    }
    
    public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      if (p.isExpectedNumberIntToken())
        return Integer.valueOf(p.getIntValue()); 
      if (this._primitive)
        return Integer.valueOf(_parseIntPrimitive(p, ctxt)); 
      return _parseInteger(p, ctxt, Integer.class);
    }
    
    public Integer deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
      if (p.isExpectedNumberIntToken())
        return Integer.valueOf(p.getIntValue()); 
      if (this._primitive)
        return Integer.valueOf(_parseIntPrimitive(p, ctxt)); 
      return _parseInteger(p, ctxt, Integer.class);
    }
  }
  
  @JacksonStdImpl
  public static final class LongDeserializer extends PrimitiveOrWrapperDeserializer<Long> {
    private static final long serialVersionUID = 1L;
    
    static final LongDeserializer primitiveInstance = new LongDeserializer((Class)long.class, Long.valueOf(0L));
    
    static final LongDeserializer wrapperInstance = new LongDeserializer(Long.class, null);
    
    public LongDeserializer(Class<Long> cls, Long nvl) {
      super(cls, LogicalType.Integer, nvl, Long.valueOf(0L));
    }
    
    public boolean isCachable() {
      return true;
    }
    
    public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      if (p.isExpectedNumberIntToken())
        return Long.valueOf(p.getLongValue()); 
      if (this._primitive)
        return Long.valueOf(_parseLongPrimitive(p, ctxt)); 
      return _parseLong(p, ctxt, Long.class);
    }
  }
  
  @JacksonStdImpl
  public static class FloatDeserializer extends PrimitiveOrWrapperDeserializer<Float> {
    private static final long serialVersionUID = 1L;
    
    static final FloatDeserializer primitiveInstance = new FloatDeserializer((Class)float.class, Float.valueOf(0.0F));
    
    static final FloatDeserializer wrapperInstance = new FloatDeserializer(Float.class, null);
    
    public FloatDeserializer(Class<Float> cls, Float nvl) {
      super(cls, LogicalType.Float, nvl, Float.valueOf(0.0F));
    }
    
    public Float deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      if (p.hasToken(JsonToken.VALUE_NUMBER_FLOAT))
        return Float.valueOf(p.getFloatValue()); 
      if (this._primitive)
        return Float.valueOf(_parseFloatPrimitive(p, ctxt)); 
      return _parseFloat(p, ctxt);
    }
    
    protected final Float _parseFloat(JsonParser p, DeserializationContext ctxt) throws IOException {
      CoercionAction coercionAction1;
      switch (p.currentTokenId()) {
        case 6:
          text = p.getText();
          break;
        case 11:
          return getNullValue(ctxt);
        case 7:
          coercionAction1 = _checkIntToFloatCoercion(p, ctxt, this._valueClass);
          if (coercionAction1 == CoercionAction.AsNull)
            return getNullValue(ctxt); 
          if (coercionAction1 == CoercionAction.AsEmpty)
            return (Float)getEmptyValue(ctxt); 
        case 8:
          return Float.valueOf(p.getFloatValue());
        case 1:
          text = ctxt.extractScalarFromObject(p, this, this._valueClass);
          break;
        case 3:
          return _deserializeFromArray(p, ctxt);
        default:
          return (Float)ctxt.handleUnexpectedToken(getValueType(ctxt), p);
      } 
      Float nan = _checkFloatSpecialValue(text);
      if (nan != null)
        return nan; 
      CoercionAction act = _checkFromStringCoercion(ctxt, text);
      if (act == CoercionAction.AsNull)
        return getNullValue(ctxt); 
      if (act == CoercionAction.AsEmpty)
        return (Float)getEmptyValue(ctxt); 
      String text = text.trim();
      if (_checkTextualNull(ctxt, text))
        return getNullValue(ctxt); 
      try {
        return Float.valueOf(NumberInput.parseFloat(text, p.isEnabled(StreamReadFeature.USE_FAST_DOUBLE_PARSER)));
      } catch (IllegalArgumentException illegalArgumentException) {
        return (Float)ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid `Float` value", new Object[0]);
      } 
    }
  }
  
  @JacksonStdImpl
  public static class DoubleDeserializer extends PrimitiveOrWrapperDeserializer<Double> {
    private static final long serialVersionUID = 1L;
    
    static final DoubleDeserializer primitiveInstance = new DoubleDeserializer((Class)double.class, Double.valueOf(0.0D));
    
    static final DoubleDeserializer wrapperInstance = new DoubleDeserializer(Double.class, null);
    
    public DoubleDeserializer(Class<Double> cls, Double nvl) {
      super(cls, LogicalType.Float, nvl, Double.valueOf(0.0D));
    }
    
    public Double deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      if (p.hasToken(JsonToken.VALUE_NUMBER_FLOAT))
        return Double.valueOf(p.getDoubleValue()); 
      if (this._primitive)
        return Double.valueOf(_parseDoublePrimitive(p, ctxt)); 
      return _parseDouble(p, ctxt);
    }
    
    public Double deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
      if (p.hasToken(JsonToken.VALUE_NUMBER_FLOAT))
        return Double.valueOf(p.getDoubleValue()); 
      if (this._primitive)
        return Double.valueOf(_parseDoublePrimitive(p, ctxt)); 
      return _parseDouble(p, ctxt);
    }
    
    protected final Double _parseDouble(JsonParser p, DeserializationContext ctxt) throws IOException {
      CoercionAction coercionAction1;
      switch (p.currentTokenId()) {
        case 6:
          text = p.getText();
          break;
        case 11:
          return getNullValue(ctxt);
        case 7:
          coercionAction1 = _checkIntToFloatCoercion(p, ctxt, this._valueClass);
          if (coercionAction1 == CoercionAction.AsNull)
            return getNullValue(ctxt); 
          if (coercionAction1 == CoercionAction.AsEmpty)
            return (Double)getEmptyValue(ctxt); 
        case 8:
          return Double.valueOf(p.getDoubleValue());
        case 1:
          text = ctxt.extractScalarFromObject(p, this, this._valueClass);
          break;
        case 3:
          return _deserializeFromArray(p, ctxt);
        default:
          return (Double)ctxt.handleUnexpectedToken(getValueType(ctxt), p);
      } 
      Double nan = _checkDoubleSpecialValue(text);
      if (nan != null)
        return nan; 
      CoercionAction act = _checkFromStringCoercion(ctxt, text);
      if (act == CoercionAction.AsNull)
        return getNullValue(ctxt); 
      if (act == CoercionAction.AsEmpty)
        return (Double)getEmptyValue(ctxt); 
      String text = text.trim();
      if (_checkTextualNull(ctxt, text))
        return getNullValue(ctxt); 
      p.streamReadConstraints().validateFPLength(text.length());
      try {
        return Double.valueOf(_parseDouble(text, p.isEnabled(StreamReadFeature.USE_FAST_DOUBLE_PARSER)));
      } catch (IllegalArgumentException illegalArgumentException) {
        return (Double)ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid `Double` value", new Object[0]);
      } 
    }
  }
  
  @JacksonStdImpl
  public static class NumberDeserializer extends StdScalarDeserializer<Object> {
    public static final NumberDeserializer instance = new NumberDeserializer();
    
    public NumberDeserializer() {
      super(Number.class);
    }
    
    public final LogicalType logicalType() {
      return LogicalType.Integer;
    }
    
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      switch (p.currentTokenId()) {
        case 6:
          text = p.getText();
          break;
        case 7:
          if (ctxt.hasSomeOfFeatures(F_MASK_INT_COERCIONS))
            return _coerceIntegral(p, ctxt); 
          return p.getNumberValue();
        case 8:
          if (ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))
            if (!p.isNaN())
              return p.getDecimalValue();  
          return p.getNumberValue();
        case 1:
          text = ctxt.extractScalarFromObject(p, this, this._valueClass);
          break;
        case 3:
          return _deserializeFromArray(p, ctxt);
        default:
          return ctxt.handleUnexpectedToken(getValueType(ctxt), p);
      } 
      CoercionAction act = _checkFromStringCoercion(ctxt, text);
      if (act == CoercionAction.AsNull)
        return getNullValue(ctxt); 
      if (act == CoercionAction.AsEmpty)
        return getEmptyValue(ctxt); 
      String text = text.trim();
      if (_hasTextualNull(text))
        return getNullValue(ctxt); 
      if (_isPosInf(text))
        return Double.valueOf(Double.POSITIVE_INFINITY); 
      if (_isNegInf(text))
        return Double.valueOf(Double.NEGATIVE_INFINITY); 
      if (_isNaN(text))
        return Double.valueOf(Double.NaN); 
      try {
        if (!_isIntNumber(text)) {
          p.streamReadConstraints().validateFPLength(text.length());
          if (ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))
            return NumberInput.parseBigDecimal(text, p
                .isEnabled(StreamReadFeature.USE_FAST_BIG_NUMBER_PARSER)); 
          return Double.valueOf(
              NumberInput.parseDouble(text, p.isEnabled(StreamReadFeature.USE_FAST_DOUBLE_PARSER)));
        } 
        p.streamReadConstraints().validateIntegerLength(text.length());
        if (ctxt.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS))
          return NumberInput.parseBigInteger(text, p.isEnabled(StreamReadFeature.USE_FAST_BIG_NUMBER_PARSER)); 
        long value = NumberInput.parseLong(text);
        if (!ctxt.isEnabled(DeserializationFeature.USE_LONG_FOR_INTS) && 
          value <= 2147483647L && value >= -2147483648L)
          return Integer.valueOf((int)value); 
        return Long.valueOf(value);
      } catch (IllegalArgumentException iae) {
        return ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid number", new Object[0]);
      } 
    }
    
    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
      switch (p.currentTokenId()) {
        case 6:
        case 7:
        case 8:
          return deserialize(p, ctxt);
      } 
      return typeDeserializer.deserializeTypedFromScalar(p, ctxt);
    }
  }
  
  @JacksonStdImpl
  public static class BigIntegerDeserializer extends StdScalarDeserializer<BigInteger> {
    public static final BigIntegerDeserializer instance = new BigIntegerDeserializer();
    
    public BigIntegerDeserializer() {
      super(BigInteger.class);
    }
    
    public Object getEmptyValue(DeserializationContext ctxt) {
      return BigInteger.ZERO;
    }
    
    public final LogicalType logicalType() {
      return LogicalType.Integer;
    }
    
    public BigInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      BigDecimal bd;
      if (p.isExpectedNumberIntToken())
        return p.getBigIntegerValue(); 
      switch (p.currentTokenId()) {
        case 6:
          text = p.getText();
          break;
        case 8:
          act = _checkFloatToIntCoercion(p, ctxt, this._valueClass);
          if (act == CoercionAction.AsNull)
            return (BigInteger)getNullValue(ctxt); 
          if (act == CoercionAction.AsEmpty)
            return (BigInteger)getEmptyValue(ctxt); 
          bd = p.getDecimalValue();
          p.streamReadConstraints().validateBigIntegerScale(bd.scale());
          return bd.toBigInteger();
        case 1:
          text = ctxt.extractScalarFromObject(p, this, this._valueClass);
          break;
        case 3:
          return _deserializeFromArray(p, ctxt);
        default:
          return (BigInteger)ctxt.handleUnexpectedToken(getValueType(ctxt), p);
      } 
      CoercionAction act = _checkFromStringCoercion(ctxt, text);
      if (act == CoercionAction.AsNull)
        return (BigInteger)getNullValue(ctxt); 
      if (act == CoercionAction.AsEmpty)
        return (BigInteger)getEmptyValue(ctxt); 
      String text = text.trim();
      if (_hasTextualNull(text))
        return (BigInteger)getNullValue(ctxt); 
      p.streamReadConstraints().validateIntegerLength(text.length());
      try {
        return NumberInput.parseBigInteger(text, p.isEnabled(StreamReadFeature.USE_FAST_BIG_NUMBER_PARSER));
      } catch (IllegalArgumentException illegalArgumentException) {
        return (BigInteger)ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid representation", new Object[0]);
      } 
    }
  }
  
  @JacksonStdImpl
  public static class BigDecimalDeserializer extends StdScalarDeserializer<BigDecimal> {
    public static final BigDecimalDeserializer instance = new BigDecimalDeserializer();
    
    public BigDecimalDeserializer() {
      super(BigDecimal.class);
    }
    
    public Object getEmptyValue(DeserializationContext ctxt) {
      return BigDecimal.ZERO;
    }
    
    public final LogicalType logicalType() {
      return LogicalType.Float;
    }
    
    public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      switch (p.currentTokenId()) {
        case 7:
          act = _checkIntToFloatCoercion(p, ctxt, this._valueClass);
          if (act == CoercionAction.AsNull)
            return (BigDecimal)getNullValue(ctxt); 
          if (act == CoercionAction.AsEmpty)
            return (BigDecimal)getEmptyValue(ctxt); 
        case 8:
          return p.getDecimalValue();
        case 6:
          text = p.getText();
          break;
        case 1:
          text = ctxt.extractScalarFromObject(p, this, this._valueClass);
          break;
        case 3:
          return _deserializeFromArray(p, ctxt);
        default:
          return (BigDecimal)ctxt.handleUnexpectedToken(getValueType(ctxt), p);
      } 
      CoercionAction act = _checkFromStringCoercion(ctxt, text);
      if (act == CoercionAction.AsNull)
        return (BigDecimal)getNullValue(ctxt); 
      if (act == CoercionAction.AsEmpty)
        return (BigDecimal)getEmptyValue(ctxt); 
      String text = text.trim();
      if (_hasTextualNull(text))
        return (BigDecimal)getNullValue(ctxt); 
      p.streamReadConstraints().validateFPLength(text.length());
      try {
        return NumberInput.parseBigDecimal(text, p.isEnabled(StreamReadFeature.USE_FAST_BIG_NUMBER_PARSER));
      } catch (IllegalArgumentException illegalArgumentException) {
        return (BigDecimal)ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid representation", new Object[0]);
      } 
    }
  }
}
