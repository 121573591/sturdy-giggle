package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Objects;

public class EnumSetDeserializer extends StdDeserializer<EnumSet<?>> implements ContextualDeserializer {
  private static final long serialVersionUID = 1L;
  
  protected final JavaType _enumType;
  
  protected JsonDeserializer<Enum<?>> _enumDeserializer;
  
  protected final NullValueProvider _nullProvider;
  
  protected final boolean _skipNullValues;
  
  protected final Boolean _unwrapSingle;
  
  public EnumSetDeserializer(JavaType enumType, JsonDeserializer<?> deser) {
    super(EnumSet.class);
    this._enumType = enumType;
    if (!enumType.isEnumType())
      throw new IllegalArgumentException("Type " + enumType + " not Java Enum type"); 
    this._enumDeserializer = (JsonDeserializer)deser;
    this._unwrapSingle = null;
    this._nullProvider = null;
    this._skipNullValues = false;
  }
  
  @Deprecated
  protected EnumSetDeserializer(EnumSetDeserializer base, JsonDeserializer<?> deser, Boolean unwrapSingle) {
    this(base, deser, base._nullProvider, unwrapSingle);
  }
  
  protected EnumSetDeserializer(EnumSetDeserializer base, JsonDeserializer<?> deser, NullValueProvider nuller, Boolean unwrapSingle) {
    super(base);
    this._enumType = base._enumType;
    this._enumDeserializer = (JsonDeserializer)deser;
    this._nullProvider = nuller;
    this._skipNullValues = NullsConstantProvider.isSkipper(nuller);
    this._unwrapSingle = unwrapSingle;
  }
  
  public EnumSetDeserializer withDeserializer(JsonDeserializer<?> deser) {
    if (this._enumDeserializer == deser)
      return this; 
    return new EnumSetDeserializer(this, deser, this._nullProvider, this._unwrapSingle);
  }
  
  @Deprecated
  public EnumSetDeserializer withResolved(JsonDeserializer<?> deser, Boolean unwrapSingle) {
    return withResolved(deser, this._nullProvider, unwrapSingle);
  }
  
  public EnumSetDeserializer withResolved(JsonDeserializer<?> deser, NullValueProvider nuller, Boolean unwrapSingle) {
    if (Objects.equals(this._unwrapSingle, unwrapSingle) && this._enumDeserializer == deser && this._nullProvider == deser)
      return this; 
    return new EnumSetDeserializer(this, deser, nuller, unwrapSingle);
  }
  
  public boolean isCachable() {
    if (this._enumType.getValueHandler() != null)
      return false; 
    return true;
  }
  
  public LogicalType logicalType() {
    return LogicalType.Collection;
  }
  
  public Boolean supportsUpdate(DeserializationConfig config) {
    return Boolean.TRUE;
  }
  
  public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
    return constructSet();
  }
  
  public AccessPattern getEmptyAccessPattern() {
    return AccessPattern.DYNAMIC;
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
    Boolean unwrapSingle = findFormatFeature(ctxt, property, EnumSet.class, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    JsonDeserializer<?> deser = this._enumDeserializer;
    if (deser == null) {
      deser = ctxt.findContextualValueDeserializer(this._enumType, property);
    } else {
      deser = ctxt.handleSecondaryContextualization(deser, property, this._enumType);
    } 
    return withResolved(deser, findContentNullProvider(ctxt, property, deser), unwrapSingle);
  }
  
  public EnumSet<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    EnumSet result = constructSet();
    if (!p.isExpectedStartArrayToken())
      return handleNonArray(p, ctxt, result); 
    return _deserialize(p, ctxt, result);
  }
  
  public EnumSet<?> deserialize(JsonParser p, DeserializationContext ctxt, EnumSet<?> result) throws IOException {
    if (!p.isExpectedStartArrayToken())
      return handleNonArray(p, ctxt, result); 
    return _deserialize(p, ctxt, result);
  }
  
  protected final EnumSet<?> _deserialize(JsonParser p, DeserializationContext ctxt, EnumSet<Enum<?>> result) throws IOException {
    try {
      JsonToken t;
      while ((t = p.nextToken()) != JsonToken.END_ARRAY) {
        Enum<?> value;
        if (t == JsonToken.VALUE_NULL) {
          if (this._skipNullValues)
            continue; 
          value = (Enum)this._nullProvider.getNullValue(ctxt);
        } else {
          value = (Enum)this._enumDeserializer.deserialize(p, ctxt);
        } 
        if (value != null)
          result.add(value); 
      } 
    } catch (Exception e) {
      throw JsonMappingException.wrapWithPath(e, result, result.size());
    } 
    return result;
  }
  
  public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
    return typeDeserializer.deserializeTypedFromArray(p, ctxt);
  }
  
  private EnumSet constructSet() {
    return EnumSet.noneOf(this._enumType.getRawClass());
  }
  
  protected EnumSet<?> handleNonArray(JsonParser p, DeserializationContext ctxt, EnumSet<Enum<?>> result) throws IOException {
    boolean canWrap = (this._unwrapSingle == Boolean.TRUE || (this._unwrapSingle == null && ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)));
    if (!canWrap)
      return (EnumSet)ctxt.handleUnexpectedToken(EnumSet.class, p); 
    if (p.hasToken(JsonToken.VALUE_NULL))
      return (EnumSet)ctxt.handleUnexpectedToken(this._enumType, p); 
    try {
      Enum<?> value = (Enum)this._enumDeserializer.deserialize(p, ctxt);
      if (value != null)
        result.add(value); 
    } catch (Exception e) {
      throw JsonMappingException.wrapWithPath(e, result, result.size());
    } 
    return result;
  }
}
