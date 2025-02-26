package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.io.Serializable;
import java.util.Set;

public class UnwrappingBeanSerializer extends BeanSerializerBase implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected final NameTransformer _nameTransformer;
  
  public UnwrappingBeanSerializer(BeanSerializerBase src, NameTransformer transformer) {
    super(src, transformer);
    this._nameTransformer = transformer;
  }
  
  public UnwrappingBeanSerializer(UnwrappingBeanSerializer src, ObjectIdWriter objectIdWriter) {
    super(src, objectIdWriter);
    this._nameTransformer = src._nameTransformer;
  }
  
  public UnwrappingBeanSerializer(UnwrappingBeanSerializer src, ObjectIdWriter objectIdWriter, Object filterId) {
    super(src, objectIdWriter, filterId);
    this._nameTransformer = src._nameTransformer;
  }
  
  protected UnwrappingBeanSerializer(UnwrappingBeanSerializer src, Set<String> toIgnore) {
    this(src, toIgnore, (Set<String>)null);
  }
  
  protected UnwrappingBeanSerializer(UnwrappingBeanSerializer src, Set<String> toIgnore, Set<String> toInclude) {
    super(src, toIgnore, toInclude);
    this._nameTransformer = src._nameTransformer;
  }
  
  protected UnwrappingBeanSerializer(UnwrappingBeanSerializer src, BeanPropertyWriter[] properties, BeanPropertyWriter[] filteredProperties) {
    super(src, properties, filteredProperties);
    this._nameTransformer = src._nameTransformer;
  }
  
  public JsonSerializer<Object> unwrappingSerializer(NameTransformer transformer) {
    return (JsonSerializer<Object>)new UnwrappingBeanSerializer(this, transformer);
  }
  
  public boolean isUnwrappingSerializer() {
    return true;
  }
  
  public BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectIdWriter) {
    return new UnwrappingBeanSerializer(this, objectIdWriter);
  }
  
  public BeanSerializerBase withFilterId(Object filterId) {
    return new UnwrappingBeanSerializer(this, this._objectIdWriter, filterId);
  }
  
  protected BeanSerializerBase withByNameInclusion(Set<String> toIgnore, Set<String> toInclude) {
    return new UnwrappingBeanSerializer(this, toIgnore, toInclude);
  }
  
  protected BeanSerializerBase withProperties(BeanPropertyWriter[] properties, BeanPropertyWriter[] filteredProperties) {
    return new UnwrappingBeanSerializer(this, properties, filteredProperties);
  }
  
  protected BeanSerializerBase asArraySerializer() {
    return this;
  }
  
  public final void serialize(Object bean, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.setCurrentValue(bean);
    if (this._objectIdWriter != null) {
      _serializeWithObjectId(bean, gen, provider, false);
      return;
    } 
    if (this._propertyFilterId != null) {
      serializeFieldsFiltered(bean, gen, provider);
    } else {
      serializeFields(bean, gen, provider);
    } 
  }
  
  public void serializeWithType(Object bean, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
    if (provider.isEnabled(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS))
      provider.reportBadDefinition(handledType(), "Unwrapped property requires use of type information: cannot serialize without disabling `SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS`"); 
    gen.setCurrentValue(bean);
    if (this._objectIdWriter != null) {
      _serializeWithObjectId(bean, gen, provider, typeSer);
      return;
    } 
    if (this._propertyFilterId != null) {
      serializeFieldsFiltered(bean, gen, provider);
    } else {
      serializeFields(bean, gen, provider);
    } 
  }
  
  public String toString() {
    return "UnwrappingBeanSerializer for " + handledType().getName();
  }
}
