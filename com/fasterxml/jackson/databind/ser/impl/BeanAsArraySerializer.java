package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.Set;

public class BeanAsArraySerializer extends BeanSerializerBase {
  private static final long serialVersionUID = 1L;
  
  protected final BeanSerializerBase _defaultSerializer;
  
  public BeanAsArraySerializer(BeanSerializerBase src) {
    super(src, (ObjectIdWriter)null);
    this._defaultSerializer = src;
  }
  
  protected BeanAsArraySerializer(BeanSerializerBase src, Set<String> toIgnore) {
    this(src, toIgnore, (Set<String>)null);
  }
  
  protected BeanAsArraySerializer(BeanSerializerBase src, Set<String> toIgnore, Set<String> toInclude) {
    super(src, toIgnore, toInclude);
    this._defaultSerializer = src;
  }
  
  protected BeanAsArraySerializer(BeanSerializerBase src, ObjectIdWriter oiw, Object filterId) {
    super(src, oiw, filterId);
    this._defaultSerializer = src;
  }
  
  public JsonSerializer<Object> unwrappingSerializer(NameTransformer transformer) {
    return this._defaultSerializer.unwrappingSerializer(transformer);
  }
  
  public boolean isUnwrappingSerializer() {
    return false;
  }
  
  public BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectIdWriter) {
    return this._defaultSerializer.withObjectIdWriter(objectIdWriter);
  }
  
  public BeanSerializerBase withFilterId(Object filterId) {
    return new BeanAsArraySerializer(this, this._objectIdWriter, filterId);
  }
  
  protected BeanAsArraySerializer withByNameInclusion(Set<String> toIgnore, Set<String> toInclude) {
    return new BeanAsArraySerializer(this, toIgnore, toInclude);
  }
  
  protected BeanSerializerBase withProperties(BeanPropertyWriter[] properties, BeanPropertyWriter[] filteredProperties) {
    return this;
  }
  
  protected BeanSerializerBase asArraySerializer() {
    return this;
  }
  
  public void serializeWithType(Object bean, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
    if (this._objectIdWriter != null) {
      _serializeWithObjectId(bean, gen, provider, typeSer);
      return;
    } 
    WritableTypeId typeIdDef = _typeIdDef(typeSer, bean, JsonToken.START_ARRAY);
    typeSer.writeTypePrefix(gen, typeIdDef);
    gen.setCurrentValue(bean);
    serializeAsArray(bean, gen, provider);
    typeSer.writeTypeSuffix(gen, typeIdDef);
  }
  
  public final void serialize(Object bean, JsonGenerator gen, SerializerProvider provider) throws IOException {
    if (provider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) && 
      hasSingleElement(provider)) {
      serializeAsArray(bean, gen, provider);
      return;
    } 
    gen.writeStartArray(bean);
    serializeAsArray(bean, gen, provider);
    gen.writeEndArray();
  }
  
  private boolean hasSingleElement(SerializerProvider provider) {
    BeanPropertyWriter[] props;
    if (this._filteredProps != null && provider.getActiveView() != null) {
      props = this._filteredProps;
    } else {
      props = this._props;
    } 
    return (props.length == 1);
  }
  
  protected final void serializeAsArray(Object bean, JsonGenerator gen, SerializerProvider provider) throws IOException {
    BeanPropertyWriter[] props;
    if (this._filteredProps != null && provider.getActiveView() != null) {
      props = this._filteredProps;
    } else {
      props = this._props;
    } 
    int i = 0;
    try {
      for (int len = props.length; i < len; i++) {
        BeanPropertyWriter prop = props[i];
        if (prop == null) {
          gen.writeNull();
        } else {
          prop.serializeAsElement(bean, gen, provider);
        } 
      } 
    } catch (Exception e) {
      wrapAndThrow(provider, e, bean, props[i].getName());
    } catch (StackOverflowError e) {
      JsonMappingException jsonMappingException = JsonMappingException.from(gen, "Infinite recursion (StackOverflowError)", e);
      jsonMappingException.prependPath(bean, props[i].getName());
      throw jsonMappingException;
    } 
  }
  
  public String toString() {
    return "BeanAsArraySerializer for " + handledType().getName();
  }
}
