package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

@JacksonStdImpl
public class ObjectArrayDeserializer extends ContainerDeserializerBase<Object[]> implements ContextualDeserializer {
  private static final long serialVersionUID = 1L;
  
  protected final boolean _untyped;
  
  protected final Class<?> _elementClass;
  
  protected JsonDeserializer<Object> _elementDeserializer;
  
  protected final TypeDeserializer _elementTypeDeserializer;
  
  protected final Object[] _emptyValue;
  
  public ObjectArrayDeserializer(JavaType arrayType0, JsonDeserializer<Object> elemDeser, TypeDeserializer elemTypeDeser) {
    super(arrayType0, (NullValueProvider)null, (Boolean)null);
    ArrayType arrayType = (ArrayType)arrayType0;
    this._elementClass = arrayType.getContentType().getRawClass();
    this._untyped = (this._elementClass == Object.class);
    this._elementDeserializer = elemDeser;
    this._elementTypeDeserializer = elemTypeDeser;
    this._emptyValue = arrayType.getEmptyArray();
  }
  
  protected ObjectArrayDeserializer(ObjectArrayDeserializer base, JsonDeserializer<Object> elemDeser, TypeDeserializer elemTypeDeser, NullValueProvider nuller, Boolean unwrapSingle) {
    super(base, nuller, unwrapSingle);
    this._elementClass = base._elementClass;
    this._untyped = base._untyped;
    this._emptyValue = base._emptyValue;
    this._elementDeserializer = elemDeser;
    this._elementTypeDeserializer = elemTypeDeser;
  }
  
  public ObjectArrayDeserializer withDeserializer(TypeDeserializer elemTypeDeser, JsonDeserializer<?> elemDeser) {
    return withResolved(elemTypeDeser, elemDeser, this._nullProvider, this._unwrapSingle);
  }
  
  public ObjectArrayDeserializer withResolved(TypeDeserializer elemTypeDeser, JsonDeserializer<?> elemDeser, NullValueProvider nuller, Boolean unwrapSingle) {
    if (Objects.equals(unwrapSingle, this._unwrapSingle) && nuller == this._nullProvider && elemDeser == this._elementDeserializer && elemTypeDeser == this._elementTypeDeserializer)
      return this; 
    return new ObjectArrayDeserializer(this, (JsonDeserializer)elemDeser, elemTypeDeser, nuller, unwrapSingle);
  }
  
  public boolean isCachable() {
    return (this._elementDeserializer == null && this._elementTypeDeserializer == null);
  }
  
  public LogicalType logicalType() {
    return LogicalType.Array;
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
    JsonDeserializer<?> valueDeser = this._elementDeserializer;
    Boolean unwrapSingle = findFormatFeature(ctxt, property, this._containerType.getRawClass(), JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    valueDeser = findConvertingContentDeserializer(ctxt, property, valueDeser);
    JavaType vt = this._containerType.getContentType();
    if (valueDeser == null) {
      valueDeser = ctxt.findContextualValueDeserializer(vt, property);
    } else {
      valueDeser = ctxt.handleSecondaryContextualization(valueDeser, property, vt);
    } 
    TypeDeserializer elemTypeDeser = this._elementTypeDeserializer;
    if (elemTypeDeser != null)
      elemTypeDeser = elemTypeDeser.forProperty(property); 
    NullValueProvider nuller = findContentNullProvider(ctxt, property, valueDeser);
    return withResolved(elemTypeDeser, valueDeser, nuller, unwrapSingle);
  }
  
  public JsonDeserializer<Object> getContentDeserializer() {
    return this._elementDeserializer;
  }
  
  public AccessPattern getEmptyAccessPattern() {
    return AccessPattern.CONSTANT;
  }
  
  public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
    return this._emptyValue;
  }
  
  public Object[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    Object[] result;
    if (!p.isExpectedStartArrayToken())
      return handleNonArray(p, ctxt); 
    ObjectBuffer buffer = ctxt.leaseObjectBuffer();
    Object[] chunk = buffer.resetAndStart();
    int ix = 0;
    TypeDeserializer typeDeser = this._elementTypeDeserializer;
    try {
      JsonToken t;
      while ((t = p.nextToken()) != JsonToken.END_ARRAY) {
        Object value;
        if (t == JsonToken.VALUE_NULL) {
          if (this._skipNullValues)
            continue; 
          value = this._nullProvider.getNullValue(ctxt);
        } else if (typeDeser == null) {
          value = this._elementDeserializer.deserialize(p, ctxt);
        } else {
          value = this._elementDeserializer.deserializeWithType(p, ctxt, typeDeser);
        } 
        if (ix >= chunk.length) {
          chunk = buffer.appendCompletedChunk(chunk);
          ix = 0;
        } 
        chunk[ix++] = value;
      } 
    } catch (Exception e) {
      throw JsonMappingException.wrapWithPath(e, chunk, buffer.bufferedSize() + ix);
    } 
    if (this._untyped) {
      result = buffer.completeAndClearBuffer(chunk, ix);
    } else {
      result = buffer.completeAndClearBuffer(chunk, ix, this._elementClass);
    } 
    ctxt.returnObjectBuffer(buffer);
    return result;
  }
  
  public Object[] deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
    return (Object[])typeDeserializer.deserializeTypedFromArray(p, ctxt);
  }
  
  public Object[] deserialize(JsonParser p, DeserializationContext ctxt, Object[] intoValue) throws IOException {
    Object[] result;
    if (!p.isExpectedStartArrayToken()) {
      Object[] arr = handleNonArray(p, ctxt);
      if (arr == null)
        return intoValue; 
      int offset = intoValue.length;
      Object[] arrayOfObject1 = Arrays.copyOf(intoValue, offset + arr.length);
      System.arraycopy(arr, 0, arrayOfObject1, offset, arr.length);
      return arrayOfObject1;
    } 
    ObjectBuffer buffer = ctxt.leaseObjectBuffer();
    int ix = intoValue.length;
    Object[] chunk = buffer.resetAndStart(intoValue, ix);
    TypeDeserializer typeDeser = this._elementTypeDeserializer;
    try {
      JsonToken t;
      while ((t = p.nextToken()) != JsonToken.END_ARRAY) {
        Object value;
        if (t == JsonToken.VALUE_NULL) {
          if (this._skipNullValues)
            continue; 
          value = this._nullProvider.getNullValue(ctxt);
        } else if (typeDeser == null) {
          value = this._elementDeserializer.deserialize(p, ctxt);
        } else {
          value = this._elementDeserializer.deserializeWithType(p, ctxt, typeDeser);
        } 
        if (ix >= chunk.length) {
          chunk = buffer.appendCompletedChunk(chunk);
          ix = 0;
        } 
        chunk[ix++] = value;
      } 
    } catch (Exception e) {
      throw JsonMappingException.wrapWithPath(e, chunk, buffer.bufferedSize() + ix);
    } 
    if (this._untyped) {
      result = buffer.completeAndClearBuffer(chunk, ix);
    } else {
      result = buffer.completeAndClearBuffer(chunk, ix, this._elementClass);
    } 
    ctxt.returnObjectBuffer(buffer);
    return result;
  }
  
  protected Byte[] deserializeFromBase64(JsonParser p, DeserializationContext ctxt) throws IOException {
    byte[] b = p.getBinaryValue(ctxt.getBase64Variant());
    Byte[] result = new Byte[b.length];
    for (int i = 0, len = b.length; i < len; i++)
      result[i] = Byte.valueOf(b[i]); 
    return result;
  }
  
  protected Object[] handleNonArray(JsonParser p, DeserializationContext ctxt) throws IOException {
    Object value, result[];
    boolean canWrap = (this._unwrapSingle == Boolean.TRUE || (this._unwrapSingle == null && ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)));
    if (!canWrap) {
      if (p.hasToken(JsonToken.VALUE_STRING)) {
        if (this._elementClass == Byte.class)
          return (Object[])deserializeFromBase64(p, ctxt); 
        return _deserializeFromString(p, ctxt);
      } 
      return (Object[])ctxt.handleUnexpectedToken(this._containerType, p);
    } 
    if (p.hasToken(JsonToken.VALUE_NULL)) {
      if (this._skipNullValues)
        return this._emptyValue; 
      value = this._nullProvider.getNullValue(ctxt);
    } else {
      if (p.hasToken(JsonToken.VALUE_STRING)) {
        String textValue = p.getText();
        if (textValue.isEmpty()) {
          CoercionAction act = ctxt.findCoercionAction(logicalType(), handledType(), CoercionInputShape.EmptyString);
          if (act != CoercionAction.Fail)
            return (Object[])_deserializeFromEmptyString(p, ctxt, act, handledType(), "empty String (\"\")"); 
        } else if (_isBlank(textValue)) {
          CoercionAction act = ctxt.findCoercionFromBlankString(logicalType(), handledType(), CoercionAction.Fail);
          if (act != CoercionAction.Fail)
            return (Object[])_deserializeFromEmptyString(p, ctxt, act, handledType(), "blank String (all whitespace)"); 
        } 
      } 
      if (this._elementTypeDeserializer == null) {
        value = this._elementDeserializer.deserialize(p, ctxt);
      } else {
        value = this._elementDeserializer.deserializeWithType(p, ctxt, this._elementTypeDeserializer);
      } 
    } 
    if (this._untyped) {
      result = new Object[1];
    } else {
      result = (Object[])Array.newInstance(this._elementClass, 1);
    } 
    result[0] = value;
    return result;
  }
}
