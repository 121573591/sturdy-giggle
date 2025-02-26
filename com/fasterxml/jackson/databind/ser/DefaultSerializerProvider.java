package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.CacheProvider;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public abstract class DefaultSerializerProvider extends SerializerProvider implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected transient Map<Object, WritableObjectId> _seenObjectIds;
  
  protected transient ArrayList<ObjectIdGenerator<?>> _objectIdGenerators;
  
  protected transient JsonGenerator _generator;
  
  protected DefaultSerializerProvider() {}
  
  protected DefaultSerializerProvider(SerializerProvider src, SerializationConfig config, SerializerFactory f) {
    super(src, config, f);
  }
  
  protected DefaultSerializerProvider(DefaultSerializerProvider src) {
    super(src);
  }
  
  protected DefaultSerializerProvider(DefaultSerializerProvider src, CacheProvider cp) {
    super(src, new SerializerCache(cp
          .forSerializerCache(src._config)));
  }
  
  public abstract DefaultSerializerProvider createInstance(SerializationConfig paramSerializationConfig, SerializerFactory paramSerializerFactory);
  
  public DefaultSerializerProvider copy() {
    throw new IllegalStateException("DefaultSerializerProvider sub-class not overriding copy()");
  }
  
  public JsonSerializer<Object> serializerInstance(Annotated annotated, Object serDef) throws JsonMappingException {
    JsonSerializer<?> ser;
    if (serDef == null)
      return null; 
    if (serDef instanceof JsonSerializer) {
      ser = (JsonSerializer)serDef;
    } else {
      if (!(serDef instanceof Class))
        reportBadDefinition(annotated.getType(), "AnnotationIntrospector returned serializer definition of type " + serDef
            
            .getClass().getName() + "; expected type JsonSerializer or Class<JsonSerializer> instead"); 
      Class<?> serClass = (Class)serDef;
      if (serClass == JsonSerializer.None.class || ClassUtil.isBogusClass(serClass))
        return null; 
      if (!JsonSerializer.class.isAssignableFrom(serClass))
        reportBadDefinition(annotated.getType(), "AnnotationIntrospector returned Class " + serClass
            
            .getName() + "; expected Class<JsonSerializer>"); 
      HandlerInstantiator hi = this._config.getHandlerInstantiator();
      ser = (hi == null) ? null : hi.serializerInstance(this._config, annotated, serClass);
      if (ser == null)
        ser = (JsonSerializer)ClassUtil.createInstance(serClass, this._config
            .canOverrideAccessModifiers()); 
    } 
    return _handleResolvable(ser);
  }
  
  public Object includeFilterInstance(BeanPropertyDefinition forProperty, Class<?> filterClass) {
    if (filterClass == null)
      return null; 
    HandlerInstantiator hi = this._config.getHandlerInstantiator();
    Object filter = (hi == null) ? null : hi.includeFilterInstance(this._config, forProperty, filterClass);
    if (filter == null)
      filter = ClassUtil.createInstance(filterClass, this._config
          .canOverrideAccessModifiers()); 
    return filter;
  }
  
  public boolean includeFilterSuppressNulls(Object filter) throws JsonMappingException {
    if (filter == null)
      return true; 
    try {
      return filter.equals((Object)null);
    } catch (Exception e) {
      String msg = String.format("Problem determining whether filter of type '%s' should filter out `null` values: (%s) %s", new Object[] { filter
            
            .getClass().getName(), e.getClass().getName(), ClassUtil.exceptionMessage(e) });
      reportBadDefinition(filter.getClass(), msg, e);
      return false;
    } 
  }
  
  public abstract DefaultSerializerProvider withCaches(CacheProvider paramCacheProvider);
  
  public WritableObjectId findObjectId(Object forPojo, ObjectIdGenerator<?> generatorType) {
    if (this._seenObjectIds == null) {
      this._seenObjectIds = _createObjectIdMap();
    } else {
      WritableObjectId writableObjectId = this._seenObjectIds.get(forPojo);
      if (writableObjectId != null)
        return writableObjectId; 
    } 
    ObjectIdGenerator<?> generator = null;
    if (this._objectIdGenerators == null) {
      this._objectIdGenerators = new ArrayList<>(8);
    } else {
      for (int i = 0, len = this._objectIdGenerators.size(); i < len; i++) {
        ObjectIdGenerator<?> gen = this._objectIdGenerators.get(i);
        if (gen.canUseFor(generatorType)) {
          generator = gen;
          break;
        } 
      } 
    } 
    if (generator == null) {
      generator = generatorType.newForSerialization(this);
      this._objectIdGenerators.add(generator);
    } 
    WritableObjectId oid = new WritableObjectId(generator);
    this._seenObjectIds.put(forPojo, oid);
    return oid;
  }
  
  protected Map<Object, WritableObjectId> _createObjectIdMap() {
    if (isEnabled(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID))
      return new HashMap<>(); 
    return new IdentityHashMap<>();
  }
  
  public boolean hasSerializerFor(Class<?> cls, AtomicReference<Throwable> cause) {
    if (cls == Object.class && 
      !this._config.isEnabled(SerializationFeature.FAIL_ON_EMPTY_BEANS))
      return true; 
    try {
      JsonSerializer<?> ser = _findExplicitUntypedSerializer(cls);
      return (ser != null);
    } catch (JsonMappingException e) {
      if (cause != null)
        cause.set(e); 
    } catch (RuntimeException e) {
      if (cause == null)
        throw e; 
      cause.set(e);
    } 
    return false;
  }
  
  public JsonGenerator getGenerator() {
    return this._generator;
  }
  
  public void serializeValue(JsonGenerator gen, Object value) throws IOException {
    this._generator = gen;
    if (value == null) {
      _serializeNull(gen);
      return;
    } 
    Class<?> cls = value.getClass();
    JsonSerializer<Object> ser = findTypedValueSerializer(cls, true, null);
    PropertyName rootName = this._config.getFullRootName();
    if (rootName == null) {
      if (this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE)) {
        _serialize(gen, value, ser, this._config.findRootName(cls));
        return;
      } 
    } else if (!rootName.isEmpty()) {
      _serialize(gen, value, ser, rootName);
      return;
    } 
    _serialize(gen, value, ser);
  }
  
  public void serializeValue(JsonGenerator gen, Object value, JavaType rootType) throws IOException {
    this._generator = gen;
    if (value == null) {
      _serializeNull(gen);
      return;
    } 
    if (!rootType.getRawClass().isAssignableFrom(value.getClass()))
      _reportIncompatibleRootType(value, rootType); 
    JsonSerializer<Object> ser = findTypedValueSerializer(rootType, true, null);
    PropertyName rootName = this._config.getFullRootName();
    if (rootName == null) {
      if (this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE)) {
        _serialize(gen, value, ser, this._config.findRootName(rootType));
        return;
      } 
    } else if (!rootName.isEmpty()) {
      _serialize(gen, value, ser, rootName);
      return;
    } 
    _serialize(gen, value, ser);
  }
  
  public void serializeValue(JsonGenerator gen, Object value, JavaType rootType, JsonSerializer<Object> ser) throws IOException {
    this._generator = gen;
    if (value == null) {
      _serializeNull(gen);
      return;
    } 
    if (rootType != null && !rootType.getRawClass().isAssignableFrom(value.getClass()))
      _reportIncompatibleRootType(value, rootType); 
    if (ser == null)
      ser = findTypedValueSerializer(rootType, true, null); 
    PropertyName rootName = this._config.getFullRootName();
    if (rootName == null) {
      if (this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE)) {
        rootName = (rootType == null) ? this._config.findRootName(value.getClass()) : this._config.findRootName(rootType);
        _serialize(gen, value, ser, rootName);
        return;
      } 
    } else if (!rootName.isEmpty()) {
      _serialize(gen, value, ser, rootName);
      return;
    } 
    _serialize(gen, value, ser);
  }
  
  public void serializePolymorphic(JsonGenerator gen, Object value, JavaType rootType, JsonSerializer<Object> valueSer, TypeSerializer typeSer) throws IOException {
    boolean wrap;
    this._generator = gen;
    if (value == null) {
      _serializeNull(gen);
      return;
    } 
    if (rootType != null && !rootType.getRawClass().isAssignableFrom(value.getClass()))
      _reportIncompatibleRootType(value, rootType); 
    if (valueSer == null)
      if (rootType != null && rootType.isContainerType()) {
        valueSer = findValueSerializer(rootType, null);
      } else {
        valueSer = findValueSerializer(value.getClass(), null);
      }  
    PropertyName rootName = this._config.getFullRootName();
    if (rootName == null) {
      wrap = this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
      if (wrap) {
        gen.writeStartObject();
        PropertyName pname = this._config.findRootName(value.getClass());
        gen.writeFieldName(pname.simpleAsEncoded((MapperConfig)this._config));
      } 
    } else if (rootName.isEmpty()) {
      wrap = false;
    } else {
      wrap = true;
      gen.writeStartObject();
      gen.writeFieldName(rootName.getSimpleName());
    } 
    try {
      valueSer.serializeWithType(value, gen, this, typeSer);
      if (wrap)
        gen.writeEndObject(); 
    } catch (Exception e) {
      throw _wrapAsIOE(gen, e);
    } 
  }
  
  private final void _serialize(JsonGenerator gen, Object value, JsonSerializer<Object> ser, PropertyName rootName) throws IOException {
    try {
      gen.writeStartObject();
      gen.writeFieldName(rootName.simpleAsEncoded((MapperConfig)this._config));
      ser.serialize(value, gen, this);
      gen.writeEndObject();
    } catch (Exception e) {
      throw _wrapAsIOE(gen, e);
    } 
  }
  
  private final void _serialize(JsonGenerator gen, Object value, JsonSerializer<Object> ser) throws IOException {
    try {
      ser.serialize(value, gen, this);
    } catch (Exception e) {
      throw _wrapAsIOE(gen, e);
    } 
  }
  
  protected void _serializeNull(JsonGenerator gen) throws IOException {
    JsonSerializer<Object> ser = getDefaultNullValueSerializer();
    try {
      ser.serialize(null, gen, this);
    } catch (Exception e) {
      throw _wrapAsIOE(gen, e);
    } 
  }
  
  private IOException _wrapAsIOE(JsonGenerator g, Exception e) {
    if (e instanceof IOException)
      return (IOException)e; 
    String msg = ClassUtil.exceptionMessage(e);
    if (msg == null)
      msg = "[no message for " + e.getClass().getName() + "]"; 
    return (IOException)new JsonMappingException((Closeable)g, msg, e);
  }
  
  public int cachedSerializersCount() {
    return this._serializerCache.size();
  }
  
  public void flushCachedSerializers() {
    this._serializerCache.flush();
  }
  
  public void acceptJsonFormatVisitor(JavaType javaType, JsonFormatVisitorWrapper visitor) throws JsonMappingException {
    if (javaType == null)
      throw new IllegalArgumentException("A class must be provided"); 
    visitor.setProvider(this);
    findValueSerializer(javaType, null).acceptJsonFormatVisitor(visitor, javaType);
  }
  
  @Deprecated
  public JsonSchema generateJsonSchema(Class<?> type) throws JsonMappingException {
    JsonSerializer<Object> ser = findValueSerializer(type, null);
    JsonNode schemaNode = (ser instanceof SchemaAware) ? ((SchemaAware)ser).getSchema(this, null) : JsonSchema.getDefaultSchemaNode();
    if (!(schemaNode instanceof ObjectNode))
      throw new IllegalArgumentException("Class " + type.getName() + " would not be serialized as a JSON object and therefore has no schema"); 
    return new JsonSchema((ObjectNode)schemaNode);
  }
  
  public static final class Impl extends DefaultSerializerProvider {
    private static final long serialVersionUID = 1L;
    
    public Impl() {}
    
    public Impl(Impl src) {
      super(src);
    }
    
    protected Impl(SerializerProvider src, SerializationConfig config, SerializerFactory f) {
      super(src, config, f);
    }
    
    protected Impl(Impl src, CacheProvider cp) {
      super(src, cp);
    }
    
    public DefaultSerializerProvider copy() {
      return new Impl(this);
    }
    
    public Impl createInstance(SerializationConfig config, SerializerFactory jsf) {
      return new Impl(this, config, jsf);
    }
    
    public DefaultSerializerProvider withCaches(CacheProvider cp) {
      return new Impl(this, cp);
    }
  }
}
