package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonNumberFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;

public abstract class StdSerializer<T> extends JsonSerializer<T> implements JsonFormatVisitable, SchemaAware, Serializable {
  private static final long serialVersionUID = 1L;
  
  private static final Object KEY_CONTENT_CONVERTER_LOCK = new Object();
  
  protected final Class<T> _handledType;
  
  protected StdSerializer(Class<T> t) {
    this._handledType = t;
  }
  
  protected StdSerializer(JavaType type) {
    this._handledType = type.getRawClass();
  }
  
  protected StdSerializer(Class<?> t, boolean dummy) {
    this._handledType = (Class)t;
  }
  
  protected StdSerializer(StdSerializer<?> src) {
    this._handledType = src._handledType;
  }
  
  public Class<T> handledType() {
    return this._handledType;
  }
  
  public abstract void serialize(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) throws IOException;
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
    visitor.expectAnyFormat(typeHint);
  }
  
  @Deprecated
  public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
    return (JsonNode)createSchemaNode("string");
  }
  
  @Deprecated
  public JsonNode getSchema(SerializerProvider provider, Type typeHint, boolean isOptional) throws JsonMappingException {
    ObjectNode schema = (ObjectNode)getSchema(provider, typeHint);
    if (!isOptional)
      schema.put("required", true); 
    return (JsonNode)schema;
  }
  
  protected ObjectNode createSchemaNode(String type) {
    ObjectNode schema = JsonNodeFactory.instance.objectNode();
    schema.put("type", type);
    return schema;
  }
  
  protected ObjectNode createSchemaNode(String type, boolean isOptional) {
    ObjectNode schema = createSchemaNode(type);
    if (!isOptional)
      schema.put("required", true); 
    return schema;
  }
  
  protected void visitStringFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
    visitor.expectStringFormat(typeHint);
  }
  
  protected void visitStringFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint, JsonValueFormat format) throws JsonMappingException {
    JsonStringFormatVisitor v2 = visitor.expectStringFormat(typeHint);
    if (v2 != null)
      v2.format(format); 
  }
  
  protected void visitIntFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint, JsonParser.NumberType numberType) throws JsonMappingException {
    JsonIntegerFormatVisitor v2 = visitor.expectIntegerFormat(typeHint);
    if (_neitherNull(v2, numberType))
      v2.numberType(numberType); 
  }
  
  protected void visitIntFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint, JsonParser.NumberType numberType, JsonValueFormat format) throws JsonMappingException {
    JsonIntegerFormatVisitor v2 = visitor.expectIntegerFormat(typeHint);
    if (v2 != null) {
      if (numberType != null)
        v2.numberType(numberType); 
      if (format != null)
        v2.format(format); 
    } 
  }
  
  protected void visitFloatFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint, JsonParser.NumberType numberType) throws JsonMappingException {
    JsonNumberFormatVisitor v2 = visitor.expectNumberFormat(typeHint);
    if (v2 != null)
      v2.numberType(numberType); 
  }
  
  protected void visitArrayFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint, JsonSerializer<?> itemSerializer, JavaType itemType) throws JsonMappingException {
    JsonArrayFormatVisitor v2 = visitor.expectArrayFormat(typeHint);
    if (_neitherNull(v2, itemSerializer))
      v2.itemsFormat((JsonFormatVisitable)itemSerializer, itemType); 
  }
  
  protected void visitArrayFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint, JsonFormatTypes itemType) throws JsonMappingException {
    JsonArrayFormatVisitor v2 = visitor.expectArrayFormat(typeHint);
    if (v2 != null)
      v2.itemsFormat(itemType); 
  }
  
  public void wrapAndThrow(SerializerProvider provider, Throwable t, Object bean, String fieldName) throws IOException {
    while (t instanceof java.lang.reflect.InvocationTargetException && t.getCause() != null)
      t = t.getCause(); 
    ClassUtil.throwIfError(t);
    boolean wrap = (provider == null || provider.isEnabled(SerializationFeature.WRAP_EXCEPTIONS));
    if (t instanceof IOException) {
      if (!wrap || !(t instanceof com.fasterxml.jackson.core.JacksonException))
        throw (IOException)t; 
    } else if (!wrap) {
      ClassUtil.throwIfRTE(t);
    } 
    throw JsonMappingException.wrapWithPath(t, bean, fieldName);
  }
  
  public void wrapAndThrow(SerializerProvider provider, Throwable t, Object bean, int index) throws IOException {
    while (t instanceof java.lang.reflect.InvocationTargetException && t.getCause() != null)
      t = t.getCause(); 
    ClassUtil.throwIfError(t);
    boolean wrap = (provider == null || provider.isEnabled(SerializationFeature.WRAP_EXCEPTIONS));
    if (t instanceof IOException) {
      if (!wrap || !(t instanceof com.fasterxml.jackson.core.JacksonException))
        throw (IOException)t; 
    } else if (!wrap) {
      ClassUtil.throwIfRTE(t);
    } 
    throw JsonMappingException.wrapWithPath(t, bean, index);
  }
  
  protected JsonSerializer<?> findContextualConvertingSerializer(SerializerProvider provider, BeanProperty property, JsonSerializer<?> existingSerializer) throws JsonMappingException {
    Map<Object, Object> conversions = (Map<Object, Object>)provider.getAttribute(KEY_CONTENT_CONVERTER_LOCK);
    if (conversions != null) {
      Object lock = conversions.get(property);
      if (lock != null)
        return existingSerializer; 
    } else {
      conversions = new IdentityHashMap<>();
      provider.setAttribute(KEY_CONTENT_CONVERTER_LOCK, conversions);
    } 
    conversions.put(property, Boolean.TRUE);
    try {
      JsonSerializer<?> ser = findConvertingContentSerializer(provider, property, existingSerializer);
      if (ser != null)
        return provider.handleSecondaryContextualization(ser, property); 
    } finally {
      conversions.remove(property);
    } 
    return existingSerializer;
  }
  
  @Deprecated
  protected JsonSerializer<?> findConvertingContentSerializer(SerializerProvider provider, BeanProperty prop, JsonSerializer<?> existingSerializer) throws JsonMappingException {
    AnnotationIntrospector intr = provider.getAnnotationIntrospector();
    if (_neitherNull(intr, prop)) {
      AnnotatedMember m = prop.getMember();
      if (m != null) {
        Object convDef = intr.findSerializationContentConverter(m);
        if (convDef != null) {
          Converter<Object, Object> conv = provider.converterInstance((Annotated)prop.getMember(), convDef);
          JavaType delegateType = conv.getOutputType(provider.getTypeFactory());
          if (existingSerializer == null && !delegateType.isJavaLangObject())
            existingSerializer = provider.findValueSerializer(delegateType); 
          return new StdDelegatingSerializer(conv, delegateType, existingSerializer);
        } 
      } 
    } 
    return existingSerializer;
  }
  
  protected PropertyFilter findPropertyFilter(SerializerProvider provider, Object filterId, Object valueToFilter) throws JsonMappingException {
    FilterProvider filters = provider.getFilterProvider();
    if (filters == null)
      return (PropertyFilter)provider.reportBadDefinition(handledType(), "Cannot resolve PropertyFilter with id '" + filterId + "'; no FilterProvider configured"); 
    return filters.findPropertyFilter(filterId, valueToFilter);
  }
  
  protected JsonFormat.Value findFormatOverrides(SerializerProvider provider, BeanProperty prop, Class<?> typeForDefaults) {
    if (prop != null)
      return prop.findPropertyFormat((MapperConfig)provider.getConfig(), typeForDefaults); 
    return provider.getDefaultPropertyFormat(typeForDefaults);
  }
  
  protected Boolean findFormatFeature(SerializerProvider provider, BeanProperty prop, Class<?> typeForDefaults, JsonFormat.Feature feat) {
    JsonFormat.Value format = findFormatOverrides(provider, prop, typeForDefaults);
    if (format != null)
      return format.getFeature(feat); 
    return null;
  }
  
  protected JsonInclude.Value findIncludeOverrides(SerializerProvider provider, BeanProperty prop, Class<?> typeForDefaults) {
    if (prop != null)
      return prop.findPropertyInclusion((MapperConfig)provider.getConfig(), typeForDefaults); 
    return provider.getDefaultPropertyInclusion(typeForDefaults);
  }
  
  protected JsonSerializer<?> findAnnotatedContentSerializer(SerializerProvider serializers, BeanProperty property) throws JsonMappingException {
    if (property != null) {
      AnnotatedMember m = property.getMember();
      AnnotationIntrospector intr = serializers.getAnnotationIntrospector();
      if (m != null) {
        Object serDef = intr.findContentSerializer((Annotated)m);
        if (serDef != null)
          return serializers.serializerInstance((Annotated)m, serDef); 
      } 
    } 
    return null;
  }
  
  protected boolean isDefaultSerializer(JsonSerializer<?> serializer) {
    return ClassUtil.isJacksonStdImpl(serializer);
  }
  
  protected static final boolean _neitherNull(Object a, Object b) {
    return (a != null && b != null);
  }
  
  protected static final boolean _nonEmpty(Collection<?> c) {
    return (c != null && !c.isEmpty());
  }
}
