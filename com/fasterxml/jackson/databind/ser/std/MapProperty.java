package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import java.io.IOException;

public class MapProperty extends PropertyWriter {
  private static final long serialVersionUID = 1L;
  
  private static final BeanProperty BOGUS_PROP = (BeanProperty)new BeanProperty.Bogus();
  
  protected final TypeSerializer _typeSerializer;
  
  protected final BeanProperty _property;
  
  protected Object _key;
  
  protected Object _value;
  
  protected JsonSerializer<Object> _keySerializer;
  
  protected JsonSerializer<Object> _valueSerializer;
  
  public MapProperty(TypeSerializer typeSer, BeanProperty prop) {
    super((prop == null) ? PropertyMetadata.STD_REQUIRED_OR_OPTIONAL : prop.getMetadata());
    this._typeSerializer = typeSer;
    this._property = (prop == null) ? BOGUS_PROP : prop;
  }
  
  public void reset(Object key, Object value, JsonSerializer<Object> keySer, JsonSerializer<Object> valueSer) {
    this._key = key;
    this._value = value;
    this._keySerializer = keySer;
    this._valueSerializer = valueSer;
  }
  
  @Deprecated
  public void reset(Object key, JsonSerializer<Object> keySer, JsonSerializer<Object> valueSer) {
    reset(key, this._value, keySer, valueSer);
  }
  
  public String getName() {
    if (this._key instanceof String)
      return (String)this._key; 
    return String.valueOf(this._key);
  }
  
  public Object getValue() {
    return this._value;
  }
  
  public void setValue(Object v) {
    this._value = v;
  }
  
  public PropertyName getFullName() {
    return new PropertyName(getName());
  }
  
  public <A extends java.lang.annotation.Annotation> A getAnnotation(Class<A> acls) {
    return (A)this._property.getAnnotation(acls);
  }
  
  public <A extends java.lang.annotation.Annotation> A getContextAnnotation(Class<A> acls) {
    return (A)this._property.getContextAnnotation(acls);
  }
  
  public void serializeAsField(Object map, JsonGenerator gen, SerializerProvider provider) throws IOException {
    this._keySerializer.serialize(this._key, gen, provider);
    if (this._typeSerializer == null) {
      this._valueSerializer.serialize(this._value, gen, provider);
    } else {
      this._valueSerializer.serializeWithType(this._value, gen, provider, this._typeSerializer);
    } 
  }
  
  public void serializeAsOmittedField(Object map, JsonGenerator gen, SerializerProvider provider) throws Exception {
    if (!gen.canOmitFields())
      gen.writeOmittedField(getName()); 
  }
  
  public void serializeAsElement(Object map, JsonGenerator gen, SerializerProvider provider) throws Exception {
    if (this._typeSerializer == null) {
      this._valueSerializer.serialize(this._value, gen, provider);
    } else {
      this._valueSerializer.serializeWithType(this._value, gen, provider, this._typeSerializer);
    } 
  }
  
  public void serializeAsPlaceholder(Object value, JsonGenerator gen, SerializerProvider provider) throws Exception {
    gen.writeNull();
  }
  
  public void depositSchemaProperty(JsonObjectFormatVisitor objectVisitor, SerializerProvider provider) throws JsonMappingException {
    this._property.depositSchemaProperty(objectVisitor, provider);
  }
  
  @Deprecated
  public void depositSchemaProperty(ObjectNode propertiesNode, SerializerProvider provider) throws JsonMappingException {}
  
  public JavaType getType() {
    return this._property.getType();
  }
  
  public PropertyName getWrapperName() {
    return this._property.getWrapperName();
  }
  
  public AnnotatedMember getMember() {
    return this._property.getMember();
  }
}
