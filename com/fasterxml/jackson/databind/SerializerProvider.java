package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.DatatypeFeature;
import com.fasterxml.jackson.databind.cfg.DatatypeFeatures;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.ser.SerializerCache;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.impl.FailingSerializer;
import com.fasterxml.jackson.databind.ser.impl.ReadOnlyClassToSerializerMap;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class SerializerProvider extends DatabindContext {
  protected static final boolean CACHE_UNKNOWN_MAPPINGS = false;
  
  public static final JsonSerializer<Object> DEFAULT_NULL_KEY_SERIALIZER = (JsonSerializer<Object>)new FailingSerializer("Null key for a Map not allowed in JSON (use a converting NullKeySerializer?)");
  
  protected static final JsonSerializer<Object> DEFAULT_UNKNOWN_SERIALIZER = (JsonSerializer<Object>)new UnknownSerializer();
  
  protected final SerializationConfig _config;
  
  protected final Class<?> _serializationView;
  
  protected final SerializerFactory _serializerFactory;
  
  protected final SerializerCache _serializerCache;
  
  protected transient ContextAttributes _attributes;
  
  protected JsonSerializer<Object> _unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
  
  protected JsonSerializer<Object> _keySerializer;
  
  protected JsonSerializer<Object> _nullValueSerializer = (JsonSerializer<Object>)NullSerializer.instance;
  
  protected JsonSerializer<Object> _nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
  
  protected final ReadOnlyClassToSerializerMap _knownSerializers;
  
  protected DateFormat _dateFormat;
  
  protected final boolean _stdNullValueSerializer;
  
  public SerializerProvider() {
    this._config = null;
    this._serializerFactory = null;
    this._serializerCache = new SerializerCache();
    this._knownSerializers = null;
    this._serializationView = null;
    this._attributes = null;
    this._stdNullValueSerializer = true;
  }
  
  protected SerializerProvider(SerializerProvider src, SerializationConfig config, SerializerFactory f) {
    this._serializerFactory = f;
    this._config = config;
    this._serializerCache = src._serializerCache;
    this._unknownTypeSerializer = src._unknownTypeSerializer;
    this._keySerializer = src._keySerializer;
    this._nullValueSerializer = src._nullValueSerializer;
    this._nullKeySerializer = src._nullKeySerializer;
    this._stdNullValueSerializer = (this._nullValueSerializer == DEFAULT_NULL_KEY_SERIALIZER);
    this._serializationView = config.getActiveView();
    this._attributes = config.getAttributes();
    this._knownSerializers = this._serializerCache.getReadOnlyLookupMap();
  }
  
  protected SerializerProvider(SerializerProvider src) {
    this._config = null;
    this._serializationView = null;
    this._serializerFactory = null;
    this._knownSerializers = null;
    this._serializerCache = new SerializerCache();
    this._unknownTypeSerializer = src._unknownTypeSerializer;
    this._keySerializer = src._keySerializer;
    this._nullValueSerializer = src._nullValueSerializer;
    this._nullKeySerializer = src._nullKeySerializer;
    this._stdNullValueSerializer = src._stdNullValueSerializer;
  }
  
  protected SerializerProvider(SerializerProvider src, SerializerCache serializerCache) {
    this._serializerCache = serializerCache;
    this._config = src._config;
    this._serializationView = src._serializationView;
    this._serializerFactory = src._serializerFactory;
    this._attributes = src._attributes;
    this._knownSerializers = src._knownSerializers;
    this._unknownTypeSerializer = src._unknownTypeSerializer;
    this._nullValueSerializer = src._nullValueSerializer;
    this._nullKeySerializer = src._nullKeySerializer;
    this._keySerializer = src._keySerializer;
    this._stdNullValueSerializer = src._stdNullValueSerializer;
  }
  
  public void setDefaultKeySerializer(JsonSerializer<Object> ks) {
    if (ks == null)
      throw new IllegalArgumentException("Cannot pass null JsonSerializer"); 
    this._keySerializer = ks;
  }
  
  public void setNullValueSerializer(JsonSerializer<Object> nvs) {
    if (nvs == null)
      throw new IllegalArgumentException("Cannot pass null JsonSerializer"); 
    this._nullValueSerializer = nvs;
  }
  
  public void setNullKeySerializer(JsonSerializer<Object> nks) {
    if (nks == null)
      throw new IllegalArgumentException("Cannot pass null JsonSerializer"); 
    this._nullKeySerializer = nks;
  }
  
  public final SerializationConfig getConfig() {
    return this._config;
  }
  
  public final AnnotationIntrospector getAnnotationIntrospector() {
    return this._config.getAnnotationIntrospector();
  }
  
  public final TypeFactory getTypeFactory() {
    return this._config.getTypeFactory();
  }
  
  public JavaType constructSpecializedType(JavaType baseType, Class<?> subclass) throws IllegalArgumentException {
    if (baseType.hasRawClass(subclass))
      return baseType; 
    return getConfig().getTypeFactory().constructSpecializedType(baseType, subclass, true);
  }
  
  public final Class<?> getActiveView() {
    return this._serializationView;
  }
  
  public final boolean canOverrideAccessModifiers() {
    return this._config.canOverrideAccessModifiers();
  }
  
  public final boolean isEnabled(MapperFeature feature) {
    return this._config.isEnabled(feature);
  }
  
  public final boolean isEnabled(DatatypeFeature feature) {
    return this._config.isEnabled(feature);
  }
  
  public final DatatypeFeatures getDatatypeFeatures() {
    return this._config.getDatatypeFeatures();
  }
  
  public final JsonFormat.Value getDefaultPropertyFormat(Class<?> baseType) {
    return this._config.getDefaultPropertyFormat(baseType);
  }
  
  public final JsonInclude.Value getDefaultPropertyInclusion(Class<?> baseType) {
    return this._config.getDefaultPropertyInclusion(baseType);
  }
  
  public Locale getLocale() {
    return this._config.getLocale();
  }
  
  public TimeZone getTimeZone() {
    return this._config.getTimeZone();
  }
  
  public Object getAttribute(Object key) {
    return this._attributes.getAttribute(key);
  }
  
  public SerializerProvider setAttribute(Object key, Object value) {
    this._attributes = this._attributes.withPerCallAttribute(key, value);
    return this;
  }
  
  public final boolean isEnabled(SerializationFeature feature) {
    return this._config.isEnabled(feature);
  }
  
  public final boolean hasSerializationFeatures(int featureMask) {
    return this._config.hasSerializationFeatures(featureMask);
  }
  
  public final FilterProvider getFilterProvider() {
    return this._config.getFilterProvider();
  }
  
  public JsonGenerator getGenerator() {
    return null;
  }
  
  public TokenBuffer bufferForValueConversion(ObjectCodec oc) {
    return new TokenBuffer(oc, false);
  }
  
  public final TokenBuffer bufferForValueConversion() {
    return bufferForValueConversion((ObjectCodec)null);
  }
  
  public JsonSerializer<Object> findValueSerializer(Class<?> valueType, BeanProperty property) throws JsonMappingException {
    JsonSerializer<Object> ser = this._knownSerializers.untypedValueSerializer(valueType);
    if (ser == null) {
      ser = this._serializerCache.untypedValueSerializer(valueType);
      if (ser == null) {
        ser = this._serializerCache.untypedValueSerializer(this._config.constructType(valueType));
        if (ser == null) {
          ser = _createAndCacheUntypedSerializer(valueType);
          if (ser == null) {
            ser = getUnknownTypeSerializer(valueType);
            return ser;
          } 
        } 
      } 
    } 
    return (JsonSerializer)handleSecondaryContextualization(ser, property);
  }
  
  public JsonSerializer<Object> findValueSerializer(JavaType valueType, BeanProperty property) throws JsonMappingException {
    if (valueType == null)
      reportMappingProblem("Null passed for `valueType` of `findValueSerializer()`", new Object[0]); 
    JsonSerializer<Object> ser = this._knownSerializers.untypedValueSerializer(valueType);
    if (ser == null) {
      ser = this._serializerCache.untypedValueSerializer(valueType);
      if (ser == null) {
        ser = _createAndCacheUntypedSerializer(valueType);
        if (ser == null) {
          ser = getUnknownTypeSerializer(valueType.getRawClass());
          return ser;
        } 
      } 
    } 
    return (JsonSerializer)handleSecondaryContextualization(ser, property);
  }
  
  public JsonSerializer<Object> findValueSerializer(Class<?> valueType) throws JsonMappingException {
    JsonSerializer<Object> ser = this._knownSerializers.untypedValueSerializer(valueType);
    if (ser == null) {
      ser = this._serializerCache.untypedValueSerializer(valueType);
      if (ser == null) {
        ser = this._serializerCache.untypedValueSerializer(this._config.constructType(valueType));
        if (ser == null) {
          ser = _createAndCacheUntypedSerializer(valueType);
          if (ser == null)
            ser = getUnknownTypeSerializer(valueType); 
        } 
      } 
    } 
    return ser;
  }
  
  public JsonSerializer<Object> findValueSerializer(JavaType valueType) throws JsonMappingException {
    JsonSerializer<Object> ser = this._knownSerializers.untypedValueSerializer(valueType);
    if (ser == null) {
      ser = this._serializerCache.untypedValueSerializer(valueType);
      if (ser == null) {
        ser = _createAndCacheUntypedSerializer(valueType);
        if (ser == null)
          ser = getUnknownTypeSerializer(valueType.getRawClass()); 
      } 
    } 
    return ser;
  }
  
  public JsonSerializer<Object> findPrimaryPropertySerializer(JavaType valueType, BeanProperty property) throws JsonMappingException {
    JsonSerializer<Object> ser = this._knownSerializers.untypedValueSerializer(valueType);
    if (ser == null) {
      ser = this._serializerCache.untypedValueSerializer(valueType);
      if (ser == null) {
        ser = _createAndCacheUntypedSerializer(valueType);
        if (ser == null) {
          ser = getUnknownTypeSerializer(valueType.getRawClass());
          return ser;
        } 
      } 
    } 
    return (JsonSerializer)handlePrimaryContextualization(ser, property);
  }
  
  public JsonSerializer<Object> findPrimaryPropertySerializer(Class<?> valueType, BeanProperty property) throws JsonMappingException {
    JsonSerializer<Object> ser = this._knownSerializers.untypedValueSerializer(valueType);
    if (ser == null) {
      ser = this._serializerCache.untypedValueSerializer(valueType);
      if (ser == null) {
        ser = this._serializerCache.untypedValueSerializer(this._config.constructType(valueType));
        if (ser == null) {
          ser = _createAndCacheUntypedSerializer(valueType);
          if (ser == null) {
            ser = getUnknownTypeSerializer(valueType);
            return ser;
          } 
        } 
      } 
    } 
    return (JsonSerializer)handlePrimaryContextualization(ser, property);
  }
  
  public JsonSerializer<Object> findContentValueSerializer(JavaType valueType, BeanProperty property) throws JsonMappingException {
    JsonSerializer<Object> ser = this._knownSerializers.untypedValueSerializer(valueType);
    if (ser == null) {
      ser = this._serializerCache.untypedValueSerializer(valueType);
      if (ser == null) {
        ser = _createAndCacheUntypedSerializer(valueType);
        if (ser == null) {
          ser = getUnknownTypeSerializer(valueType.getRawClass());
          return ser;
        } 
      } 
    } 
    return (JsonSerializer)handleSecondaryContextualization(ser, property);
  }
  
  public JsonSerializer<Object> findContentValueSerializer(Class<?> valueType, BeanProperty property) throws JsonMappingException {
    JsonSerializer<Object> ser = this._knownSerializers.untypedValueSerializer(valueType);
    if (ser == null) {
      ser = this._serializerCache.untypedValueSerializer(valueType);
      if (ser == null) {
        ser = this._serializerCache.untypedValueSerializer(this._config.constructType(valueType));
        if (ser == null) {
          ser = _createAndCacheUntypedSerializer(valueType);
          if (ser == null) {
            ser = getUnknownTypeSerializer(valueType);
            return ser;
          } 
        } 
      } 
    } 
    return (JsonSerializer)handleSecondaryContextualization(ser, property);
  }
  
  public JsonSerializer<Object> findTypedValueSerializer(Class<?> valueType, boolean cache, BeanProperty property) throws JsonMappingException {
    TypeWrappedSerializer typeWrappedSerializer;
    JsonSerializer<Object> ser = this._knownSerializers.typedValueSerializer(valueType);
    if (ser != null)
      return ser; 
    ser = this._serializerCache.typedValueSerializer(valueType);
    if (ser != null)
      return ser; 
    ser = findValueSerializer(valueType, property);
    TypeSerializer typeSer = this._serializerFactory.createTypeSerializer(this._config, this._config
        .constructType(valueType));
    if (typeSer != null) {
      typeSer = typeSer.forProperty(property);
      typeWrappedSerializer = new TypeWrappedSerializer(typeSer, ser);
    } 
    if (cache)
      this._serializerCache.addTypedSerializer(valueType, (JsonSerializer)typeWrappedSerializer); 
    return (JsonSerializer<Object>)typeWrappedSerializer;
  }
  
  public JsonSerializer<Object> findTypedValueSerializer(JavaType valueType, boolean cache, BeanProperty property) throws JsonMappingException {
    TypeWrappedSerializer typeWrappedSerializer;
    JsonSerializer<Object> ser = this._knownSerializers.typedValueSerializer(valueType);
    if (ser != null)
      return ser; 
    ser = this._serializerCache.typedValueSerializer(valueType);
    if (ser != null)
      return ser; 
    ser = findValueSerializer(valueType, property);
    TypeSerializer typeSer = this._serializerFactory.createTypeSerializer(this._config, valueType);
    if (typeSer != null) {
      typeSer = typeSer.forProperty(property);
      typeWrappedSerializer = new TypeWrappedSerializer(typeSer, ser);
    } 
    if (cache)
      this._serializerCache.addTypedSerializer(valueType, (JsonSerializer)typeWrappedSerializer); 
    return (JsonSerializer<Object>)typeWrappedSerializer;
  }
  
  public TypeSerializer findTypeSerializer(JavaType javaType) throws JsonMappingException {
    return this._serializerFactory.createTypeSerializer(this._config, javaType);
  }
  
  public JsonSerializer<Object> findKeySerializer(JavaType keyType, BeanProperty property) throws JsonMappingException {
    JsonSerializer<Object> ser = this._serializerFactory.createKeySerializer(this, keyType, this._keySerializer);
    return _handleContextualResolvable(ser, property);
  }
  
  public JsonSerializer<Object> findKeySerializer(Class<?> rawKeyType, BeanProperty property) throws JsonMappingException {
    return findKeySerializer(this._config.constructType(rawKeyType), property);
  }
  
  public JsonSerializer<Object> getDefaultNullKeySerializer() {
    return this._nullKeySerializer;
  }
  
  public JsonSerializer<Object> getDefaultNullValueSerializer() {
    return this._nullValueSerializer;
  }
  
  public JsonSerializer<Object> findNullKeySerializer(JavaType serializationType, BeanProperty property) throws JsonMappingException {
    return this._nullKeySerializer;
  }
  
  public JsonSerializer<Object> findNullValueSerializer(BeanProperty property) throws JsonMappingException {
    return this._nullValueSerializer;
  }
  
  public JsonSerializer<Object> getUnknownTypeSerializer(Class<?> unknownType) {
    if (unknownType == Object.class)
      return this._unknownTypeSerializer; 
    return (JsonSerializer<Object>)new UnknownSerializer(unknownType);
  }
  
  public boolean isUnknownTypeSerializer(JsonSerializer<?> ser) {
    if (ser == this._unknownTypeSerializer || ser == null)
      return true; 
    if (isEnabled(SerializationFeature.FAIL_ON_EMPTY_BEANS) && 
      ser.getClass() == UnknownSerializer.class)
      return true; 
    return false;
  }
  
  public JsonSerializer<?> handlePrimaryContextualization(JsonSerializer<?> ser, BeanProperty property) throws JsonMappingException {
    if (ser != null && 
      ser instanceof ContextualSerializer)
      ser = ((ContextualSerializer)ser).createContextual(this, property); 
    return ser;
  }
  
  public JsonSerializer<?> handleSecondaryContextualization(JsonSerializer<?> ser, BeanProperty property) throws JsonMappingException {
    if (ser != null && 
      ser instanceof ContextualSerializer)
      ser = ((ContextualSerializer)ser).createContextual(this, property); 
    return ser;
  }
  
  public final void defaultSerializeValue(Object value, JsonGenerator gen) throws IOException {
    if (value == null) {
      if (this._stdNullValueSerializer) {
        gen.writeNull();
      } else {
        this._nullValueSerializer.serialize(null, gen, this);
      } 
    } else {
      Class<?> cls = value.getClass();
      findTypedValueSerializer(cls, true, (BeanProperty)null).serialize(value, gen, this);
    } 
  }
  
  public final void defaultSerializeField(String fieldName, Object value, JsonGenerator gen) throws IOException {
    gen.writeFieldName(fieldName);
    if (value == null) {
      if (this._stdNullValueSerializer) {
        gen.writeNull();
      } else {
        this._nullValueSerializer.serialize(null, gen, this);
      } 
    } else {
      Class<?> cls = value.getClass();
      findTypedValueSerializer(cls, true, (BeanProperty)null).serialize(value, gen, this);
    } 
  }
  
  public final void defaultSerializeDateValue(long timestamp, JsonGenerator gen) throws IOException {
    if (isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)) {
      gen.writeNumber(timestamp);
    } else {
      gen.writeString(_dateFormat().format(new Date(timestamp)));
    } 
  }
  
  public final void defaultSerializeDateValue(Date date, JsonGenerator gen) throws IOException {
    if (isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)) {
      gen.writeNumber(date.getTime());
    } else {
      gen.writeString(_dateFormat().format(date));
    } 
  }
  
  public void defaultSerializeDateKey(long timestamp, JsonGenerator gen) throws IOException {
    if (isEnabled(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
      gen.writeFieldName(String.valueOf(timestamp));
    } else {
      gen.writeFieldName(_dateFormat().format(new Date(timestamp)));
    } 
  }
  
  public void defaultSerializeDateKey(Date date, JsonGenerator gen) throws IOException {
    if (isEnabled(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
      gen.writeFieldName(String.valueOf(date.getTime()));
    } else {
      gen.writeFieldName(_dateFormat().format(date));
    } 
  }
  
  public final void defaultSerializeNull(JsonGenerator gen) throws IOException {
    if (this._stdNullValueSerializer) {
      gen.writeNull();
    } else {
      this._nullValueSerializer.serialize(null, gen, this);
    } 
  }
  
  public void reportMappingProblem(String message, Object... args) throws JsonMappingException {
    throw mappingException(message, args);
  }
  
  public <T> T reportBadTypeDefinition(BeanDescription bean, String msg, Object... msgArgs) throws JsonMappingException {
    String beanDesc = "N/A";
    if (bean != null)
      beanDesc = ClassUtil.nameOf(bean.getBeanClass()); 
    msg = String.format("Invalid type definition for type %s: %s", new Object[] { beanDesc, 
          _format(msg, msgArgs) });
    throw InvalidDefinitionException.from(getGenerator(), msg, bean, null);
  }
  
  public <T> T reportBadPropertyDefinition(BeanDescription bean, BeanPropertyDefinition prop, String message, Object... msgArgs) throws JsonMappingException {
    message = _format(message, msgArgs);
    String propName = "N/A";
    if (prop != null)
      propName = _quotedString(prop.getName()); 
    String beanDesc = "N/A";
    if (bean != null)
      beanDesc = ClassUtil.nameOf(bean.getBeanClass()); 
    message = String.format("Invalid definition for property %s (of type %s): %s", new Object[] { propName, beanDesc, message });
    throw InvalidDefinitionException.from(getGenerator(), message, bean, prop);
  }
  
  public <T> T reportBadDefinition(JavaType type, String msg) throws JsonMappingException {
    throw InvalidDefinitionException.from(getGenerator(), msg, type);
  }
  
  public <T> T reportBadDefinition(JavaType type, String msg, Throwable cause) throws JsonMappingException {
    throw InvalidDefinitionException.from(getGenerator(), msg, type)
      .withCause(cause);
  }
  
  public <T> T reportBadDefinition(Class<?> raw, String msg, Throwable cause) throws JsonMappingException {
    throw InvalidDefinitionException.from(getGenerator(), msg, constructType(raw))
      .withCause(cause);
  }
  
  public void reportMappingProblem(Throwable t, String message, Object... msgArgs) throws JsonMappingException {
    message = _format(message, msgArgs);
    throw JsonMappingException.from(getGenerator(), message, t);
  }
  
  public JsonMappingException invalidTypeIdException(JavaType baseType, String typeId, String extraDesc) {
    String msg = String.format("Could not resolve type id '%s' as a subtype of %s", new Object[] { typeId, 
          ClassUtil.getTypeDescription(baseType) });
    return (JsonMappingException)InvalidTypeIdException.from(null, _colonConcat(msg, extraDesc), baseType, typeId);
  }
  
  @Deprecated
  public JsonMappingException mappingException(String message, Object... msgArgs) {
    return JsonMappingException.from(getGenerator(), _format(message, msgArgs));
  }
  
  @Deprecated
  protected JsonMappingException mappingException(Throwable t, String message, Object... msgArgs) {
    return JsonMappingException.from(getGenerator(), _format(message, msgArgs), t);
  }
  
  protected void _reportIncompatibleRootType(Object value, JavaType rootType) throws IOException {
    if (rootType.isPrimitive()) {
      Class<?> wrapperType = ClassUtil.wrapperType(rootType.getRawClass());
      if (wrapperType.isAssignableFrom(value.getClass()))
        return; 
    } 
    reportBadDefinition(rootType, String.format("Incompatible types: declared root type (%s) vs %s", new Object[] { rootType, 
            
            ClassUtil.classNameOf(value) }));
  }
  
  protected JsonSerializer<Object> _findExplicitUntypedSerializer(Class<?> runtimeType) throws JsonMappingException {
    JsonSerializer<Object> ser = this._knownSerializers.untypedValueSerializer(runtimeType);
    if (ser == null) {
      ser = this._serializerCache.untypedValueSerializer(runtimeType);
      if (ser == null)
        ser = _createAndCacheUntypedSerializer(runtimeType); 
    } 
    if (isUnknownTypeSerializer(ser))
      return null; 
    return ser;
  }
  
  protected JsonSerializer<Object> _createAndCacheUntypedSerializer(Class<?> rawType) throws JsonMappingException {
    JsonSerializer<Object> ser;
    JavaType fullType = this._config.constructType(rawType);
    try {
      ser = _createUntypedSerializer(fullType);
    } catch (IllegalArgumentException iae) {
      reportBadDefinition(fullType, ClassUtil.exceptionMessage(iae));
      ser = null;
    } 
    if (ser != null)
      this._serializerCache.addAndResolveNonTypedSerializer(rawType, fullType, ser, this); 
    return ser;
  }
  
  protected JsonSerializer<Object> _createAndCacheUntypedSerializer(JavaType type) throws JsonMappingException {
    JsonSerializer<Object> ser;
    try {
      ser = _createUntypedSerializer(type);
    } catch (IllegalArgumentException iae) {
      ser = null;
      reportMappingProblem(iae, ClassUtil.exceptionMessage(iae), new Object[0]);
    } 
    if (ser != null)
      this._serializerCache.addAndResolveNonTypedSerializer(type, ser, this); 
    return ser;
  }
  
  protected JsonSerializer<Object> _createUntypedSerializer(JavaType type) throws JsonMappingException {
    return this._serializerFactory.createSerializer(this, type);
  }
  
  protected JsonSerializer<Object> _handleContextualResolvable(JsonSerializer<?> ser, BeanProperty property) throws JsonMappingException {
    if (ser instanceof ResolvableSerializer)
      ((ResolvableSerializer)ser).resolve(this); 
    return (JsonSerializer)handleSecondaryContextualization(ser, property);
  }
  
  protected JsonSerializer<Object> _handleResolvable(JsonSerializer<?> ser) throws JsonMappingException {
    if (ser instanceof ResolvableSerializer)
      ((ResolvableSerializer)ser).resolve(this); 
    return (JsonSerializer)ser;
  }
  
  protected final DateFormat _dateFormat() {
    if (this._dateFormat != null)
      return this._dateFormat; 
    DateFormat df = this._config.getDateFormat();
    this._dateFormat = df = (DateFormat)df.clone();
    return df;
  }
  
  public abstract WritableObjectId findObjectId(Object paramObject, ObjectIdGenerator<?> paramObjectIdGenerator);
  
  public abstract JsonSerializer<Object> serializerInstance(Annotated paramAnnotated, Object paramObject) throws JsonMappingException;
  
  public abstract Object includeFilterInstance(BeanPropertyDefinition paramBeanPropertyDefinition, Class<?> paramClass) throws JsonMappingException;
  
  public abstract boolean includeFilterSuppressNulls(Object paramObject) throws JsonMappingException;
}
