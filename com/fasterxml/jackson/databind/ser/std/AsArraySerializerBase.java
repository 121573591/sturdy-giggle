package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

public abstract class AsArraySerializerBase<T> extends ContainerSerializer<T> implements ContextualSerializer {
  protected final JavaType _elementType;
  
  protected final BeanProperty _property;
  
  protected final boolean _staticTyping;
  
  protected final Boolean _unwrapSingle;
  
  protected final TypeSerializer _valueTypeSerializer;
  
  protected final JsonSerializer<Object> _elementSerializer;
  
  protected PropertySerializerMap _dynamicSerializers;
  
  protected AsArraySerializerBase(Class<?> cls, JavaType et, boolean staticTyping, TypeSerializer vts, JsonSerializer<Object> elementSerializer) {
    this(cls, et, staticTyping, vts, (BeanProperty)null, elementSerializer, (Boolean)null);
  }
  
  @Deprecated
  protected AsArraySerializerBase(Class<?> cls, JavaType et, boolean staticTyping, TypeSerializer vts, BeanProperty property, JsonSerializer<Object> elementSerializer) {
    this(cls, et, staticTyping, vts, property, elementSerializer, (Boolean)null);
  }
  
  protected AsArraySerializerBase(Class<?> cls, JavaType elementType, boolean staticTyping, TypeSerializer vts, BeanProperty property, JsonSerializer<?> elementSerializer, Boolean unwrapSingle) {
    super(cls, false);
    this._elementType = elementType;
    this._staticTyping = (staticTyping || (elementType != null && elementType.isFinal()));
    this._valueTypeSerializer = vts;
    this._property = property;
    this._elementSerializer = (JsonSerializer)elementSerializer;
    this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
    this._unwrapSingle = unwrapSingle;
  }
  
  protected AsArraySerializerBase(AsArraySerializerBase<?> src, BeanProperty property, TypeSerializer vts, JsonSerializer<?> elementSerializer, Boolean unwrapSingle) {
    super(src);
    this._elementType = src._elementType;
    this._staticTyping = src._staticTyping;
    this._valueTypeSerializer = vts;
    this._property = property;
    this._elementSerializer = (JsonSerializer)elementSerializer;
    this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
    this._unwrapSingle = unwrapSingle;
  }
  
  @Deprecated
  protected AsArraySerializerBase(AsArraySerializerBase<?> src, BeanProperty property, TypeSerializer vts, JsonSerializer<?> elementSerializer) {
    this(src, property, vts, elementSerializer, src._unwrapSingle);
  }
  
  @Deprecated
  public final AsArraySerializerBase<T> withResolved(BeanProperty property, TypeSerializer vts, JsonSerializer<?> elementSerializer) {
    return withResolved(property, vts, elementSerializer, this._unwrapSingle);
  }
  
  public abstract AsArraySerializerBase<T> withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean);
  
  public JsonSerializer<?> createContextual(SerializerProvider serializers, BeanProperty property) throws JsonMappingException {
    TypeSerializer typeSer = this._valueTypeSerializer;
    if (typeSer != null)
      typeSer = typeSer.forProperty(property); 
    JsonSerializer<?> ser = null;
    Boolean unwrapSingle = null;
    if (property != null) {
      AnnotationIntrospector intr = serializers.getAnnotationIntrospector();
      AnnotatedMember m = property.getMember();
      if (m != null) {
        Object serDef = intr.findContentSerializer((Annotated)m);
        if (serDef != null)
          ser = serializers.serializerInstance((Annotated)m, serDef); 
      } 
    } 
    JsonFormat.Value format = findFormatOverrides(serializers, property, handledType());
    if (format != null)
      unwrapSingle = format.getFeature(JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED); 
    if (ser == null)
      ser = this._elementSerializer; 
    ser = findContextualConvertingSerializer(serializers, property, ser);
    if (ser == null)
      if (this._elementType != null && 
        this._staticTyping && !this._elementType.isJavaLangObject())
        ser = serializers.findContentValueSerializer(this._elementType, property);  
    if (ser != this._elementSerializer || property != this._property || this._valueTypeSerializer != typeSer || 
      
      !Objects.equals(this._unwrapSingle, unwrapSingle))
      return (JsonSerializer<?>)withResolved(property, typeSer, ser, unwrapSingle); 
    return (JsonSerializer<?>)this;
  }
  
  public JavaType getContentType() {
    return this._elementType;
  }
  
  public JsonSerializer<?> getContentSerializer() {
    return this._elementSerializer;
  }
  
  public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    if (provider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) && 
      hasSingleElement(value)) {
      serializeContents(value, gen, provider);
      return;
    } 
    gen.writeStartArray(value);
    serializeContents(value, gen, provider);
    gen.writeEndArray();
  }
  
  public void serializeWithType(T value, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
    WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer
        .typeId(value, JsonToken.START_ARRAY));
    g.setCurrentValue(value);
    serializeContents(value, g, provider);
    typeSer.writeTypeSuffix(g, typeIdDef);
  }
  
  protected abstract void serializeContents(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) throws IOException;
  
  @Deprecated
  public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
    ObjectNode o = createSchemaNode("array", true);
    if (this._elementSerializer != null) {
      JsonNode schemaNode = null;
      if (this._elementSerializer instanceof SchemaAware)
        schemaNode = ((SchemaAware)this._elementSerializer).getSchema(provider, null); 
      if (schemaNode == null)
        schemaNode = JsonSchema.getDefaultSchemaNode(); 
      o.set("items", schemaNode);
    } 
    return (JsonNode)o;
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
    JsonSerializer<?> valueSer = this._elementSerializer;
    if (valueSer == null)
      if (this._elementType != null)
        valueSer = visitor.getProvider().findContentValueSerializer(this._elementType, this._property);  
    visitArrayFormat(visitor, typeHint, valueSer, this._elementType);
  }
  
  protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap map, Class<?> type, SerializerProvider provider) throws JsonMappingException {
    PropertySerializerMap.SerializerAndMapResult result = map.findAndAddSecondarySerializer(type, provider, this._property);
    if (map != result.map)
      this._dynamicSerializers = result.map; 
    return result.serializer;
  }
  
  protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap map, JavaType type, SerializerProvider provider) throws JsonMappingException {
    PropertySerializerMap.SerializerAndMapResult result = map.findAndAddSecondarySerializer(type, provider, this._property);
    if (map != result.map)
      this._dynamicSerializers = result.map; 
    return result.serializer;
  }
}
