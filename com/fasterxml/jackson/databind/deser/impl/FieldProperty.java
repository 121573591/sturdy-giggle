package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.lang.reflect.Field;

public final class FieldProperty extends SettableBeanProperty {
  private static final long serialVersionUID = 1L;
  
  protected final AnnotatedField _annotated;
  
  protected final transient Field _field;
  
  protected final boolean _skipNulls;
  
  public FieldProperty(BeanPropertyDefinition propDef, JavaType type, TypeDeserializer typeDeser, Annotations contextAnnotations, AnnotatedField field) {
    super(propDef, type, typeDeser, contextAnnotations);
    this._annotated = field;
    this._field = field.getAnnotated();
    this._skipNulls = NullsConstantProvider.isSkipper(this._nullProvider);
  }
  
  protected FieldProperty(FieldProperty src, JsonDeserializer<?> deser, NullValueProvider nva) {
    super(src, deser, nva);
    this._annotated = src._annotated;
    this._field = src._field;
    this._skipNulls = NullsConstantProvider.isSkipper(nva);
  }
  
  protected FieldProperty(FieldProperty src, PropertyName newName) {
    super(src, newName);
    this._annotated = src._annotated;
    this._field = src._field;
    this._skipNulls = src._skipNulls;
  }
  
  protected FieldProperty(FieldProperty src) {
    super(src);
    this._annotated = src._annotated;
    Field f = this._annotated.getAnnotated();
    if (f == null)
      throw new IllegalArgumentException("Missing field (broken JDK (de)serialization?)"); 
    this._field = f;
    this._skipNulls = src._skipNulls;
  }
  
  public SettableBeanProperty withName(PropertyName newName) {
    return new FieldProperty(this, newName);
  }
  
  public SettableBeanProperty withValueDeserializer(JsonDeserializer<?> deser) {
    if (this._valueDeserializer == deser)
      return this; 
    NullValueProvider nvp = (this._valueDeserializer == this._nullProvider) ? (NullValueProvider)deser : this._nullProvider;
    return new FieldProperty(this, deser, nvp);
  }
  
  public SettableBeanProperty withNullProvider(NullValueProvider nva) {
    return new FieldProperty(this, this._valueDeserializer, nva);
  }
  
  public void fixAccess(DeserializationConfig config) {
    ClassUtil.checkAndFixAccess(this._field, config
        .isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
  }
  
  public <A extends java.lang.annotation.Annotation> A getAnnotation(Class<A> acls) {
    return (this._annotated == null) ? null : (A)this._annotated.getAnnotation(acls);
  }
  
  public AnnotatedMember getMember() {
    return (AnnotatedMember)this._annotated;
  }
  
  public void deserializeAndSet(JsonParser p, DeserializationContext ctxt, Object instance) throws IOException {
    Object value;
    if (p.hasToken(JsonToken.VALUE_NULL)) {
      if (this._skipNulls)
        return; 
      value = this._nullProvider.getNullValue(ctxt);
    } else if (this._valueTypeDeserializer == null) {
      value = this._valueDeserializer.deserialize(p, ctxt);
      if (value == null) {
        if (this._skipNulls)
          return; 
        value = this._nullProvider.getNullValue(ctxt);
      } 
    } else {
      value = this._valueDeserializer.deserializeWithType(p, ctxt, this._valueTypeDeserializer);
    } 
    try {
      this._field.set(instance, value);
    } catch (Exception e) {
      _throwAsIOE(p, e, value);
    } 
  }
  
  public Object deserializeSetAndReturn(JsonParser p, DeserializationContext ctxt, Object instance) throws IOException {
    Object value;
    if (p.hasToken(JsonToken.VALUE_NULL)) {
      if (this._skipNulls)
        return instance; 
      value = this._nullProvider.getNullValue(ctxt);
    } else if (this._valueTypeDeserializer == null) {
      value = this._valueDeserializer.deserialize(p, ctxt);
      if (value == null) {
        if (this._skipNulls)
          return instance; 
        value = this._nullProvider.getNullValue(ctxt);
      } 
    } else {
      value = this._valueDeserializer.deserializeWithType(p, ctxt, this._valueTypeDeserializer);
    } 
    try {
      this._field.set(instance, value);
    } catch (Exception e) {
      _throwAsIOE(p, e, value);
    } 
    return instance;
  }
  
  public void set(Object instance, Object value) throws IOException {
    try {
      this._field.set(instance, value);
    } catch (Exception e) {
      _throwAsIOE(e, value);
    } 
  }
  
  public Object setAndReturn(Object instance, Object value) throws IOException {
    try {
      this._field.set(instance, value);
    } catch (Exception e) {
      _throwAsIOE(e, value);
    } 
    return instance;
  }
  
  Object readResolve() {
    return new FieldProperty(this);
  }
}
