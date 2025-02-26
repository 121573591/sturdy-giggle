package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.util.EnumMap;

public class EnumMapDeserializer extends ContainerDeserializerBase<EnumMap<?, ?>> implements ContextualDeserializer, ResolvableDeserializer {
  private static final long serialVersionUID = 1L;
  
  protected final Class<?> _enumClass;
  
  protected KeyDeserializer _keyDeserializer;
  
  protected JsonDeserializer<Object> _valueDeserializer;
  
  protected final TypeDeserializer _valueTypeDeserializer;
  
  protected final ValueInstantiator _valueInstantiator;
  
  protected JsonDeserializer<Object> _delegateDeserializer;
  
  protected PropertyBasedCreator _propertyBasedCreator;
  
  public EnumMapDeserializer(JavaType mapType, ValueInstantiator valueInst, KeyDeserializer keyDeser, JsonDeserializer<?> valueDeser, TypeDeserializer vtd, NullValueProvider nuller) {
    super(mapType, nuller, (Boolean)null);
    this._enumClass = mapType.getKeyType().getRawClass();
    this._keyDeserializer = keyDeser;
    this._valueDeserializer = (JsonDeserializer)valueDeser;
    this._valueTypeDeserializer = vtd;
    this._valueInstantiator = valueInst;
  }
  
  protected EnumMapDeserializer(EnumMapDeserializer base, KeyDeserializer keyDeser, JsonDeserializer<?> valueDeser, TypeDeserializer vtd, NullValueProvider nuller) {
    super(base, nuller, base._unwrapSingle);
    this._enumClass = base._enumClass;
    this._keyDeserializer = keyDeser;
    this._valueDeserializer = (JsonDeserializer)valueDeser;
    this._valueTypeDeserializer = vtd;
    this._valueInstantiator = base._valueInstantiator;
    this._delegateDeserializer = base._delegateDeserializer;
    this._propertyBasedCreator = base._propertyBasedCreator;
  }
  
  @Deprecated
  public EnumMapDeserializer(JavaType mapType, KeyDeserializer keyDeser, JsonDeserializer<?> valueDeser, TypeDeserializer vtd) {
    this(mapType, (ValueInstantiator)null, keyDeser, valueDeser, vtd, (NullValueProvider)null);
  }
  
  public EnumMapDeserializer withResolved(KeyDeserializer keyDeserializer, JsonDeserializer<?> valueDeserializer, TypeDeserializer valueTypeDeser, NullValueProvider nuller) {
    if (keyDeserializer == this._keyDeserializer && nuller == this._nullProvider && valueDeserializer == this._valueDeserializer && valueTypeDeser == this._valueTypeDeserializer)
      return this; 
    return new EnumMapDeserializer(this, keyDeserializer, valueDeserializer, valueTypeDeser, nuller);
  }
  
  public void resolve(DeserializationContext ctxt) throws JsonMappingException {
    if (this._valueInstantiator != null)
      if (this._valueInstantiator.canCreateUsingDelegate()) {
        JavaType delegateType = this._valueInstantiator.getDelegateType(ctxt.getConfig());
        if (delegateType == null)
          ctxt.reportBadDefinition(this._containerType, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'", new Object[] { this._containerType, this._valueInstantiator
                  
                  .getClass().getName() })); 
        this._delegateDeserializer = findDeserializer(ctxt, delegateType, null);
      } else if (this._valueInstantiator.canCreateUsingArrayDelegate()) {
        JavaType delegateType = this._valueInstantiator.getArrayDelegateType(ctxt.getConfig());
        if (delegateType == null)
          ctxt.reportBadDefinition(this._containerType, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingArrayDelegate()', but null for 'getArrayDelegateType()'", new Object[] { this._containerType, this._valueInstantiator
                  
                  .getClass().getName() })); 
        this._delegateDeserializer = findDeserializer(ctxt, delegateType, null);
      } else if (this._valueInstantiator.canCreateFromObjectWith()) {
        SettableBeanProperty[] creatorProps = this._valueInstantiator.getFromObjectArguments(ctxt.getConfig());
        this._propertyBasedCreator = PropertyBasedCreator.construct(ctxt, this._valueInstantiator, creatorProps, ctxt
            .isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES));
      }  
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
    KeyDeserializer keyDeser = this._keyDeserializer;
    if (keyDeser == null)
      keyDeser = ctxt.findKeyDeserializer(this._containerType.getKeyType(), property); 
    JsonDeserializer<?> valueDeser = this._valueDeserializer;
    JavaType vt = this._containerType.getContentType();
    if (valueDeser == null) {
      valueDeser = ctxt.findContextualValueDeserializer(vt, property);
    } else {
      valueDeser = ctxt.handleSecondaryContextualization(valueDeser, property, vt);
    } 
    TypeDeserializer vtd = this._valueTypeDeserializer;
    if (vtd != null)
      vtd = vtd.forProperty(property); 
    return withResolved(keyDeser, valueDeser, vtd, findContentNullProvider(ctxt, property, valueDeser));
  }
  
  public boolean isCachable() {
    return (this._valueDeserializer == null && this._keyDeserializer == null && this._valueTypeDeserializer == null);
  }
  
  public LogicalType logicalType() {
    return LogicalType.Map;
  }
  
  public JsonDeserializer<Object> getContentDeserializer() {
    return this._valueDeserializer;
  }
  
  public ValueInstantiator getValueInstantiator() {
    return this._valueInstantiator;
  }
  
  public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
    return constructMap(ctxt);
  }
  
  public EnumMap<?, ?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    if (this._propertyBasedCreator != null)
      return _deserializeUsingProperties(p, ctxt); 
    if (this._delegateDeserializer != null)
      return (EnumMap<?, ?>)this._valueInstantiator.createUsingDelegate(ctxt, this._delegateDeserializer
          .deserialize(p, ctxt)); 
    switch (p.currentTokenId()) {
      case 1:
      case 2:
      case 5:
        return deserialize(p, ctxt, constructMap(ctxt));
      case 6:
        return _deserializeFromString(p, ctxt);
      case 3:
        return _deserializeFromArray(p, ctxt);
    } 
    return (EnumMap<?, ?>)ctxt.handleUnexpectedToken(getValueType(ctxt), p);
  }
  
  public EnumMap<?, ?> deserialize(JsonParser p, DeserializationContext ctxt, EnumMap<?, ?> result) throws IOException {
    p.setCurrentValue(result);
    JsonDeserializer<Object> valueDes = this._valueDeserializer;
    TypeDeserializer typeDeser = this._valueTypeDeserializer;
    if (p.isExpectedStartObjectToken()) {
      str = p.nextFieldName();
    } else {
      JsonToken t = p.currentToken();
      if (t != JsonToken.FIELD_NAME) {
        if (t == JsonToken.END_OBJECT)
          return result; 
        ctxt.reportWrongTokenException(this, JsonToken.FIELD_NAME, null, new Object[0]);
      } 
      str = p.currentName();
    } 
    String str;
    for (; str != null; str = p.nextFieldName()) {
      Object value;
      Enum<?> key = (Enum)this._keyDeserializer.deserializeKey(str, ctxt);
      JsonToken t = p.nextToken();
      if (key == null) {
        if (!ctxt.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL))
          return (EnumMap<?, ?>)ctxt.handleWeirdStringValue(this._enumClass, str, "value not one of declared Enum instance names for %s", new Object[] { this._containerType
                
                .getKeyType() }); 
        p.skipChildren();
        continue;
      } 
      try {
        if (t == JsonToken.VALUE_NULL) {
          if (this._skipNullValues)
            continue; 
          value = this._nullProvider.getNullValue(ctxt);
        } else if (typeDeser == null) {
          value = valueDes.deserialize(p, ctxt);
        } else {
          value = valueDes.deserializeWithType(p, ctxt, typeDeser);
        } 
      } catch (Exception e) {
        return (EnumMap<?, ?>)wrapAndThrow(ctxt, e, result, str);
      } 
      result.put(key, value);
      continue;
    } 
    return result;
  }
  
  public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
    return typeDeserializer.deserializeTypedFromObject(p, ctxt);
  }
  
  protected EnumMap<?, ?> constructMap(DeserializationContext ctxt) throws JsonMappingException {
    if (this._valueInstantiator == null)
      return new EnumMap<>(this._enumClass); 
    try {
      if (!this._valueInstantiator.canCreateUsingDefault())
        return (EnumMap<?, ?>)ctxt.handleMissingInstantiator(handledType(), 
            getValueInstantiator(), null, "no default constructor found", new Object[0]); 
      return (EnumMap<?, ?>)this._valueInstantiator.createUsingDefault(ctxt);
    } catch (IOException e) {
      return (EnumMap<?, ?>)ClassUtil.throwAsMappingException(ctxt, e);
    } 
  }
  
  public EnumMap<?, ?> _deserializeUsingProperties(JsonParser p, DeserializationContext ctxt) throws IOException {
    PropertyBasedCreator creator = this._propertyBasedCreator;
    PropertyValueBuffer buffer = creator.startBuilding(p, ctxt, null);
    if (p.isExpectedStartObjectToken()) {
      str = p.nextFieldName();
    } else if (p.hasToken(JsonToken.FIELD_NAME)) {
      str = p.currentName();
    } else {
      str = null;
    } 
    String str;
    for (; str != null; str = p.nextFieldName()) {
      Object value;
      JsonToken t = p.nextToken();
      SettableBeanProperty prop = creator.findCreatorProperty(str);
      if (prop != null) {
        if (buffer.assignParameter(prop, prop.deserialize(p, ctxt))) {
          EnumMap<?, ?> result;
          p.nextToken();
          try {
            result = (EnumMap<?, ?>)creator.build(ctxt, buffer);
          } catch (Exception e) {
            return (EnumMap<?, ?>)wrapAndThrow(ctxt, e, this._containerType.getRawClass(), str);
          } 
          return deserialize(p, ctxt, result);
        } 
        continue;
      } 
      Enum<?> key = (Enum)this._keyDeserializer.deserializeKey(str, ctxt);
      if (key == null) {
        if (!ctxt.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL))
          return (EnumMap<?, ?>)ctxt.handleWeirdStringValue(this._enumClass, str, "value not one of declared Enum instance names for %s", new Object[] { this._containerType
                
                .getKeyType() }); 
        p.nextToken();
        p.skipChildren();
        continue;
      } 
      try {
        if (t == JsonToken.VALUE_NULL) {
          if (this._skipNullValues)
            continue; 
          value = this._nullProvider.getNullValue(ctxt);
        } else if (this._valueTypeDeserializer == null) {
          value = this._valueDeserializer.deserialize(p, ctxt);
        } else {
          value = this._valueDeserializer.deserializeWithType(p, ctxt, this._valueTypeDeserializer);
        } 
      } catch (Exception e) {
        wrapAndThrow(ctxt, e, this._containerType.getRawClass(), str);
        return null;
      } 
      buffer.bufferMapProperty(key, value);
      continue;
    } 
    try {
      return (EnumMap<?, ?>)creator.build(ctxt, buffer);
    } catch (Exception e) {
      wrapAndThrow(ctxt, e, this._containerType.getRawClass(), str);
      return null;
    } 
  }
}
