package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.BeanUtil;
import java.io.IOException;
import java.util.Map;

@JacksonStdImpl
public class MapEntrySerializer extends ContainerSerializer<Map.Entry<?, ?>> implements ContextualSerializer {
  public static final Object MARKER_FOR_EMPTY = JsonInclude.Include.NON_EMPTY;
  
  protected final BeanProperty _property;
  
  protected final boolean _valueTypeIsStatic;
  
  protected final JavaType _entryType;
  
  protected final JavaType _keyType;
  
  protected final JavaType _valueType;
  
  protected JsonSerializer<Object> _keySerializer;
  
  protected JsonSerializer<Object> _valueSerializer;
  
  protected final TypeSerializer _valueTypeSerializer;
  
  protected PropertySerializerMap _dynamicValueSerializers;
  
  protected final Object _suppressableValue;
  
  protected final boolean _suppressNulls;
  
  public MapEntrySerializer(JavaType type, JavaType keyType, JavaType valueType, boolean staticTyping, TypeSerializer vts, BeanProperty property) {
    super(type);
    this._entryType = type;
    this._keyType = keyType;
    this._valueType = valueType;
    this._valueTypeIsStatic = staticTyping;
    this._valueTypeSerializer = vts;
    this._property = property;
    this._dynamicValueSerializers = PropertySerializerMap.emptyForProperties();
    this._suppressableValue = null;
    this._suppressNulls = false;
  }
  
  @Deprecated
  protected MapEntrySerializer(MapEntrySerializer src, BeanProperty property, TypeSerializer vts, JsonSerializer<?> keySer, JsonSerializer<?> valueSer) {
    this(src, property, vts, keySer, valueSer, src._suppressableValue, src._suppressNulls);
  }
  
  protected MapEntrySerializer(MapEntrySerializer src, BeanProperty property, TypeSerializer vts, JsonSerializer<?> keySer, JsonSerializer<?> valueSer, Object suppressableValue, boolean suppressNulls) {
    super(Map.class, false);
    this._entryType = src._entryType;
    this._keyType = src._keyType;
    this._valueType = src._valueType;
    this._valueTypeIsStatic = src._valueTypeIsStatic;
    this._valueTypeSerializer = src._valueTypeSerializer;
    this._keySerializer = (JsonSerializer)keySer;
    this._valueSerializer = (JsonSerializer)valueSer;
    this._dynamicValueSerializers = PropertySerializerMap.emptyForProperties();
    this._property = src._property;
    this._suppressableValue = suppressableValue;
    this._suppressNulls = suppressNulls;
  }
  
  public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer vts) {
    return new MapEntrySerializer(this, this._property, vts, this._keySerializer, this._valueSerializer, this._suppressableValue, this._suppressNulls);
  }
  
  public MapEntrySerializer withResolved(BeanProperty property, JsonSerializer<?> keySerializer, JsonSerializer<?> valueSerializer, Object suppressableValue, boolean suppressNulls) {
    return new MapEntrySerializer(this, property, this._valueTypeSerializer, keySerializer, valueSerializer, suppressableValue, suppressNulls);
  }
  
  public MapEntrySerializer withContentInclusion(Object suppressableValue, boolean suppressNulls) {
    if (this._suppressableValue == suppressableValue && this._suppressNulls == suppressNulls)
      return this; 
    return new MapEntrySerializer(this, this._property, this._valueTypeSerializer, this._keySerializer, this._valueSerializer, suppressableValue, suppressNulls);
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) throws JsonMappingException {
    JsonSerializer<?> ser = null;
    JsonSerializer<?> keySer = null;
    AnnotationIntrospector intr = provider.getAnnotationIntrospector();
    AnnotatedMember propertyAcc = (property == null) ? null : property.getMember();
    if (propertyAcc != null && intr != null) {
      Object serDef = intr.findKeySerializer((Annotated)propertyAcc);
      if (serDef != null)
        keySer = provider.serializerInstance((Annotated)propertyAcc, serDef); 
      serDef = intr.findContentSerializer((Annotated)propertyAcc);
      if (serDef != null)
        ser = provider.serializerInstance((Annotated)propertyAcc, serDef); 
    } 
    if (ser == null)
      ser = this._valueSerializer; 
    ser = findContextualConvertingSerializer(provider, property, ser);
    if (ser == null)
      if (this._valueTypeIsStatic && !this._valueType.isJavaLangObject())
        ser = provider.findContentValueSerializer(this._valueType, property);  
    if (keySer == null)
      keySer = this._keySerializer; 
    if (keySer == null) {
      keySer = provider.findKeySerializer(this._keyType, property);
    } else {
      keySer = provider.handleSecondaryContextualization(keySer, property);
    } 
    Object valueToSuppress = this._suppressableValue;
    boolean suppressNulls = this._suppressNulls;
    if (property != null) {
      JsonInclude.Value inclV = property.findPropertyInclusion((MapperConfig)provider.getConfig(), null);
      if (inclV != null) {
        JsonInclude.Include incl = inclV.getContentInclusion();
        if (incl != JsonInclude.Include.USE_DEFAULTS) {
          switch (incl) {
            case NON_DEFAULT:
              valueToSuppress = BeanUtil.getDefaultValue(this._valueType);
              suppressNulls = true;
              if (valueToSuppress != null && 
                valueToSuppress.getClass().isArray())
                valueToSuppress = ArrayBuilders.getArrayComparator(valueToSuppress); 
              return (JsonSerializer<?>)withResolved(property, keySer, ser, valueToSuppress, suppressNulls);
            case NON_ABSENT:
              suppressNulls = true;
              valueToSuppress = this._valueType.isReferenceType() ? MARKER_FOR_EMPTY : null;
              return (JsonSerializer<?>)withResolved(property, keySer, ser, valueToSuppress, suppressNulls);
            case NON_EMPTY:
              suppressNulls = true;
              valueToSuppress = MARKER_FOR_EMPTY;
              return (JsonSerializer<?>)withResolved(property, keySer, ser, valueToSuppress, suppressNulls);
            case CUSTOM:
              valueToSuppress = provider.includeFilterInstance(null, inclV.getContentFilter());
              if (valueToSuppress == null) {
                suppressNulls = true;
              } else {
                suppressNulls = provider.includeFilterSuppressNulls(valueToSuppress);
              } 
              return (JsonSerializer<?>)withResolved(property, keySer, ser, valueToSuppress, suppressNulls);
            case NON_NULL:
              valueToSuppress = null;
              suppressNulls = true;
              return (JsonSerializer<?>)withResolved(property, keySer, ser, valueToSuppress, suppressNulls);
          } 
          valueToSuppress = null;
          suppressNulls = false;
        } 
      } 
    } 
    return (JsonSerializer<?>)withResolved(property, keySer, ser, valueToSuppress, suppressNulls);
  }
  
  public JavaType getContentType() {
    return this._valueType;
  }
  
  public JsonSerializer<?> getContentSerializer() {
    return this._valueSerializer;
  }
  
  public boolean hasSingleElement(Map.Entry<?, ?> value) {
    return true;
  }
  
  public boolean isEmpty(SerializerProvider prov, Map.Entry<?, ?> entry) {
    Object value = entry.getValue();
    if (value == null)
      return this._suppressNulls; 
    if (this._suppressableValue == null)
      return false; 
    JsonSerializer<Object> valueSer = this._valueSerializer;
    if (valueSer == null) {
      Class<?> cc = value.getClass();
      valueSer = this._dynamicValueSerializers.serializerFor(cc);
      if (valueSer == null)
        try {
          valueSer = _findAndAddDynamic(this._dynamicValueSerializers, cc, prov);
        } catch (JsonMappingException e) {
          return false;
        }  
    } 
    if (this._suppressableValue == MARKER_FOR_EMPTY)
      return valueSer.isEmpty(prov, value); 
    return this._suppressableValue.equals(value);
  }
  
  public void serialize(Map.Entry<?, ?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeStartObject(value);
    serializeDynamic(value, gen, provider);
    gen.writeEndObject();
  }
  
  public void serializeWithType(Map.Entry<?, ?> value, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
    g.setCurrentValue(value);
    WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer
        .typeId(value, JsonToken.START_OBJECT));
    serializeDynamic(value, g, provider);
    typeSer.writeTypeSuffix(g, typeIdDef);
  }
  
  protected void serializeDynamic(Map.Entry<?, ?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    JsonSerializer<Object> keySerializer, valueSer;
    TypeSerializer vts = this._valueTypeSerializer;
    Object keyElem = value.getKey();
    if (keyElem == null) {
      keySerializer = provider.findNullKeySerializer(this._keyType, this._property);
    } else {
      keySerializer = this._keySerializer;
    } 
    Object valueElem = value.getValue();
    if (valueElem == null) {
      if (this._suppressNulls)
        return; 
      valueSer = provider.getDefaultNullValueSerializer();
    } else {
      valueSer = this._valueSerializer;
      if (valueSer == null) {
        Class<?> cc = valueElem.getClass();
        valueSer = this._dynamicValueSerializers.serializerFor(cc);
        if (valueSer == null)
          if (this._valueType.hasGenericTypes()) {
            valueSer = _findAndAddDynamic(this._dynamicValueSerializers, provider
                .constructSpecializedType(this._valueType, cc), provider);
          } else {
            valueSer = _findAndAddDynamic(this._dynamicValueSerializers, cc, provider);
          }  
      } 
      if (this._suppressableValue != null) {
        if (this._suppressableValue == MARKER_FOR_EMPTY && 
          valueSer.isEmpty(provider, valueElem))
          return; 
        if (this._suppressableValue.equals(valueElem))
          return; 
      } 
    } 
    keySerializer.serialize(keyElem, gen, provider);
    try {
      if (vts == null) {
        valueSer.serialize(valueElem, gen, provider);
      } else {
        valueSer.serializeWithType(valueElem, gen, provider, vts);
      } 
    } catch (Exception e) {
      String keyDesc = "" + keyElem;
      wrapAndThrow(provider, e, value, keyDesc);
    } 
  }
  
  protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap map, Class<?> type, SerializerProvider provider) throws JsonMappingException {
    PropertySerializerMap.SerializerAndMapResult result = map.findAndAddSecondarySerializer(type, provider, this._property);
    if (map != result.map)
      this._dynamicValueSerializers = result.map; 
    return result.serializer;
  }
  
  protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap map, JavaType type, SerializerProvider provider) throws JsonMappingException {
    PropertySerializerMap.SerializerAndMapResult result = map.findAndAddSecondarySerializer(type, provider, this._property);
    if (map != result.map)
      this._dynamicValueSerializers = result.map; 
    return result.serializer;
  }
}
