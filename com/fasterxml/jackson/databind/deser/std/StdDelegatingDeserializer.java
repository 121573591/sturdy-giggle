package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.Collection;

public class StdDelegatingDeserializer<T> extends StdDeserializer<T> implements ContextualDeserializer, ResolvableDeserializer {
  private static final long serialVersionUID = 1L;
  
  protected final Converter<Object, T> _converter;
  
  protected final JavaType _delegateType;
  
  protected final JsonDeserializer<Object> _delegateDeserializer;
  
  public StdDelegatingDeserializer(Converter<?, T> converter) {
    super(Object.class);
    this._converter = (Converter)converter;
    this._delegateType = null;
    this._delegateDeserializer = null;
  }
  
  public StdDelegatingDeserializer(Converter<Object, T> converter, JavaType delegateType, JsonDeserializer<?> delegateDeserializer) {
    super(delegateType);
    this._converter = converter;
    this._delegateType = delegateType;
    this._delegateDeserializer = (JsonDeserializer)delegateDeserializer;
  }
  
  protected StdDelegatingDeserializer(StdDelegatingDeserializer<T> src) {
    super(src);
    this._converter = src._converter;
    this._delegateType = src._delegateType;
    this._delegateDeserializer = src._delegateDeserializer;
  }
  
  protected StdDelegatingDeserializer<T> withDelegate(Converter<Object, T> converter, JavaType delegateType, JsonDeserializer<?> delegateDeserializer) {
    ClassUtil.verifyMustOverride(StdDelegatingDeserializer.class, this, "withDelegate");
    return new StdDelegatingDeserializer(converter, delegateType, delegateDeserializer);
  }
  
  public JsonDeserializer<T> unwrappingDeserializer(NameTransformer unwrapper) {
    ClassUtil.verifyMustOverride(StdDelegatingDeserializer.class, this, "unwrappingDeserializer");
    return replaceDelegatee(this._delegateDeserializer.unwrappingDeserializer(unwrapper));
  }
  
  public JsonDeserializer<T> replaceDelegatee(JsonDeserializer<?> delegatee) {
    ClassUtil.verifyMustOverride(StdDelegatingDeserializer.class, this, "replaceDelegatee");
    if (delegatee == this._delegateDeserializer)
      return this; 
    return new StdDelegatingDeserializer(this._converter, this._delegateType, delegatee);
  }
  
  public void resolve(DeserializationContext ctxt) throws JsonMappingException {
    if (this._delegateDeserializer != null && this._delegateDeserializer instanceof ResolvableDeserializer)
      ((ResolvableDeserializer)this._delegateDeserializer).resolve(ctxt); 
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
    if (this._delegateDeserializer != null) {
      JsonDeserializer<?> deser = ctxt.handleSecondaryContextualization(this._delegateDeserializer, property, this._delegateType);
      if (deser != this._delegateDeserializer)
        return withDelegate(this._converter, this._delegateType, deser); 
      return this;
    } 
    JavaType delegateType = this._converter.getInputType(ctxt.getTypeFactory());
    return withDelegate(this._converter, delegateType, ctxt
        .findContextualValueDeserializer(delegateType, property));
  }
  
  public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    Object delegateValue = this._delegateDeserializer.deserialize(p, ctxt);
    if (delegateValue == null)
      return null; 
    return convertValue(delegateValue);
  }
  
  public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
    Object delegateValue = this._delegateDeserializer.deserialize(p, ctxt);
    if (delegateValue == null)
      return null; 
    return convertValue(delegateValue);
  }
  
  public T deserialize(JsonParser p, DeserializationContext ctxt, Object intoValue) throws IOException {
    if (this._delegateType.getRawClass().isAssignableFrom(intoValue.getClass()))
      return (T)this._delegateDeserializer.deserialize(p, ctxt, intoValue); 
    return (T)_handleIncompatibleUpdateValue(p, ctxt, intoValue);
  }
  
  public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer, T intoValue) throws IOException, JacksonException {
    if (!this._delegateType.getRawClass().isAssignableFrom(intoValue.getClass()))
      return _handleIncompatibleUpdateValue(p, ctxt, intoValue); 
    return this._delegateDeserializer.deserialize(p, ctxt, intoValue);
  }
  
  protected Object _handleIncompatibleUpdateValue(JsonParser p, DeserializationContext ctxt, Object intoValue) throws IOException {
    throw new UnsupportedOperationException(
        String.format("Cannot update object of type %s (using deserializer for type %s)" + intoValue
          .getClass().getName(), new Object[] { this._delegateType }));
  }
  
  public Class<?> handledType() {
    return this._delegateDeserializer.handledType();
  }
  
  public LogicalType logicalType() {
    return this._delegateDeserializer.logicalType();
  }
  
  public boolean isCachable() {
    return (this._delegateDeserializer != null && this._delegateDeserializer.isCachable());
  }
  
  public JsonDeserializer<?> getDelegatee() {
    return this._delegateDeserializer;
  }
  
  public Collection<Object> getKnownPropertyNames() {
    return this._delegateDeserializer.getKnownPropertyNames();
  }
  
  public T getNullValue(DeserializationContext ctxt) throws JsonMappingException {
    return _convertIfNonNull(this._delegateDeserializer.getNullValue(ctxt));
  }
  
  public AccessPattern getNullAccessPattern() {
    return this._delegateDeserializer.getNullAccessPattern();
  }
  
  public Object getAbsentValue(DeserializationContext ctxt) throws JsonMappingException {
    return _convertIfNonNull(this._delegateDeserializer.getAbsentValue(ctxt));
  }
  
  public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
    return _convertIfNonNull(this._delegateDeserializer.getEmptyValue(ctxt));
  }
  
  public AccessPattern getEmptyAccessPattern() {
    return this._delegateDeserializer.getEmptyAccessPattern();
  }
  
  public Boolean supportsUpdate(DeserializationConfig config) {
    return this._delegateDeserializer.supportsUpdate(config);
  }
  
  protected T convertValue(Object delegateValue) {
    return (T)this._converter.convert(delegateValue);
  }
  
  protected T _convertIfNonNull(Object delegateValue) {
    return (delegateValue == null) ? null : (T)this._converter
      .convert(delegateValue);
  }
}
