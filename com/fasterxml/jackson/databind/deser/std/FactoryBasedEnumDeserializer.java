package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;

class FactoryBasedEnumDeserializer extends StdDeserializer<Object> implements ContextualDeserializer {
  private static final long serialVersionUID = 1L;
  
  protected final JavaType _inputType;
  
  protected final AnnotatedMethod _factory;
  
  protected final JsonDeserializer<?> _deser;
  
  protected final ValueInstantiator _valueInstantiator;
  
  protected final SettableBeanProperty[] _creatorProps;
  
  protected final boolean _hasArgs;
  
  private volatile transient PropertyBasedCreator _propCreator;
  
  public FactoryBasedEnumDeserializer(Class<?> cls, AnnotatedMethod f, JavaType paramType, ValueInstantiator valueInstantiator, SettableBeanProperty[] creatorProps) {
    super(cls);
    this._factory = f;
    this._hasArgs = true;
    this._inputType = (paramType.hasRawClass(String.class) || paramType.hasRawClass(CharSequence.class)) ? null : paramType;
    this._deser = null;
    this._valueInstantiator = valueInstantiator;
    this._creatorProps = creatorProps;
  }
  
  public FactoryBasedEnumDeserializer(Class<?> cls, AnnotatedMethod f) {
    super(cls);
    this._factory = f;
    this._hasArgs = false;
    this._inputType = null;
    this._deser = null;
    this._valueInstantiator = null;
    this._creatorProps = null;
  }
  
  protected FactoryBasedEnumDeserializer(FactoryBasedEnumDeserializer base, JsonDeserializer<?> deser) {
    super(base._valueClass);
    this._inputType = base._inputType;
    this._factory = base._factory;
    this._hasArgs = base._hasArgs;
    this._valueInstantiator = base._valueInstantiator;
    this._creatorProps = base._creatorProps;
    this._deser = deser;
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
    if (this._deser == null && this._inputType != null && this._creatorProps == null)
      return new FactoryBasedEnumDeserializer(this, ctxt
          .findContextualValueDeserializer(this._inputType, property)); 
    return this;
  }
  
  public Boolean supportsUpdate(DeserializationConfig config) {
    return Boolean.FALSE;
  }
  
  public LogicalType logicalType() {
    return LogicalType.Enum;
  }
  
  public boolean isCachable() {
    return true;
  }
  
  public ValueInstantiator getValueInstantiator() {
    return this._valueInstantiator;
  }
  
  public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    Object value;
    if (this._deser != null) {
      value = this._deser.deserialize(p, ctxt);
    } else if (this._hasArgs) {
      if (this._creatorProps != null) {
        if (p.isExpectedStartObjectToken()) {
          PropertyBasedCreator pc = this._propCreator;
          if (pc == null)
            this._propCreator = pc = PropertyBasedCreator.construct(ctxt, this._valueInstantiator, this._creatorProps, ctxt
                
                .isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)); 
          p.nextToken();
          return deserializeEnumUsingPropertyBased(p, ctxt, pc);
        } 
        if (!this._valueInstantiator.canCreateFromString()) {
          JavaType targetType = getValueType(ctxt);
          JsonToken jsonToken = p.currentToken();
          return ctxt.reportInputMismatch(targetType, "Input mismatch reading Enum %s: properties-based `@JsonCreator` (%s) expects Object Value, got %s (`JsonToken.%s`)", new Object[] { ClassUtil.getTypeDescription(targetType), this._factory, 
                JsonToken.valueDescFor(jsonToken), jsonToken.name() });
        } 
      } 
      JsonToken t = p.currentToken();
      boolean unwrapping = (t == JsonToken.START_ARRAY && ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS));
      if (unwrapping)
        t = p.nextToken(); 
      if (t == null || !t.isScalarValue()) {
        JavaType targetType = getValueType(ctxt);
        return ctxt.reportInputMismatch(targetType, "Input mismatch reading Enum %s: properties-based `@JsonCreator` (%s) expects String Value, got %s (`JsonToken.%s`)", new Object[] { ClassUtil.getTypeDescription(targetType), this._factory, 
              JsonToken.valueDescFor(t), t.name() });
      } 
      value = p.getValueAsString();
      if (unwrapping && 
        p.nextToken() != JsonToken.END_ARRAY)
        handleMissingEndArrayForSingle(p, ctxt); 
    } else {
      p.skipChildren();
      try {
        return this._factory.call();
      } catch (Exception e) {
        Throwable t = ClassUtil.throwRootCauseIfIOE(e);
        return ctxt.handleInstantiationProblem(this._valueClass, null, t);
      } 
    } 
    try {
      return this._factory.callOnWith(this._valueClass, new Object[] { value });
    } catch (Exception e) {
      Throwable t = ClassUtil.throwRootCauseIfIOE(e);
      if (t instanceof IllegalArgumentException)
        if (ctxt.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL))
          return null;  
      return ctxt.handleInstantiationProblem(this._valueClass, value, t);
    } 
  }
  
  public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
    return typeDeserializer.deserializeTypedFromAny(p, ctxt);
  }
  
  protected Object deserializeEnumUsingPropertyBased(JsonParser p, DeserializationContext ctxt, PropertyBasedCreator creator) throws IOException {
    PropertyValueBuffer buffer = creator.startBuilding(p, ctxt, null);
    JsonToken t = p.currentToken();
    for (; t == JsonToken.FIELD_NAME; t = p.nextToken()) {
      String propName = p.currentName();
      p.nextToken();
      SettableBeanProperty creatorProp = creator.findCreatorProperty(propName);
      if (!buffer.readIdProperty(propName) || creatorProp != null)
        if (creatorProp != null) {
          buffer.assignParameter(creatorProp, _deserializeWithErrorWrapping(p, ctxt, creatorProp));
        } else {
          p.skipChildren();
        }  
    } 
    return creator.build(ctxt, buffer);
  }
  
  protected final Object _deserializeWithErrorWrapping(JsonParser p, DeserializationContext ctxt, SettableBeanProperty prop) throws IOException {
    try {
      return prop.deserialize(p, ctxt);
    } catch (Exception e) {
      return wrapAndThrow(e, handledType(), prop.getName(), ctxt);
    } 
  }
  
  protected Object wrapAndThrow(Throwable t, Object bean, String fieldName, DeserializationContext ctxt) throws IOException {
    throw JsonMappingException.wrapWithPath(throwOrReturnThrowable(t, ctxt), bean, fieldName);
  }
  
  private Throwable throwOrReturnThrowable(Throwable t, DeserializationContext ctxt) throws IOException {
    t = ClassUtil.getRootCause(t);
    ClassUtil.throwIfError(t);
    boolean wrap = (ctxt == null || ctxt.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS));
    if (t instanceof IOException) {
      if (!wrap || !(t instanceof com.fasterxml.jackson.core.JacksonException))
        throw (IOException)t; 
    } else if (!wrap) {
      ClassUtil.throwIfRTE(t);
    } 
    return t;
  }
}
