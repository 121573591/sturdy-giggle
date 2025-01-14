package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig;
import com.fasterxml.jackson.databind.ext.OptionalHandlerFactory;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.impl.IndexedListSerializer;
import com.fasterxml.jackson.databind.ser.impl.IndexedStringListSerializer;
import com.fasterxml.jackson.databind.ser.impl.IteratorSerializer;
import com.fasterxml.jackson.databind.ser.impl.MapEntrySerializer;
import com.fasterxml.jackson.databind.ser.impl.StringArraySerializer;
import com.fasterxml.jackson.databind.ser.impl.StringCollectionSerializer;
import com.fasterxml.jackson.databind.ser.std.AtomicReferenceSerializer;
import com.fasterxml.jackson.databind.ser.std.BooleanSerializer;
import com.fasterxml.jackson.databind.ser.std.ByteBufferSerializer;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.fasterxml.jackson.databind.ser.std.CollectionSerializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.EnumSerializer;
import com.fasterxml.jackson.databind.ser.std.EnumSetSerializer;
import com.fasterxml.jackson.databind.ser.std.InetAddressSerializer;
import com.fasterxml.jackson.databind.ser.std.InetSocketAddressSerializer;
import com.fasterxml.jackson.databind.ser.std.IterableSerializer;
import com.fasterxml.jackson.databind.ser.std.JsonValueSerializer;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;
import com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer;
import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers;
import com.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.fasterxml.jackson.databind.ser.std.TimeZoneSerializer;
import com.fasterxml.jackson.databind.ser.std.ToEmptyObjectSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.TokenBufferSerializer;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

public abstract class BasicSerializerFactory extends SerializerFactory implements Serializable {
  protected static final HashMap<String, JsonSerializer<?>> _concrete;
  
  protected static final HashMap<String, Class<? extends JsonSerializer<?>>> _concreteLazy;
  
  protected final SerializerFactoryConfig _factoryConfig;
  
  static {
    HashMap<String, Class<? extends JsonSerializer<?>>> concLazy = new HashMap<>();
    HashMap<String, JsonSerializer<?>> concrete = new HashMap<>();
    concrete.put(String.class.getName(), new StringSerializer());
    ToStringSerializer sls = ToStringSerializer.instance;
    concrete.put(StringBuffer.class.getName(), sls);
    concrete.put(StringBuilder.class.getName(), sls);
    concrete.put(Character.class.getName(), sls);
    concrete.put(char.class.getName(), sls);
    NumberSerializers.addAll(concrete);
    concrete.put(boolean.class.getName(), new BooleanSerializer(true));
    concrete.put(Boolean.class.getName(), new BooleanSerializer(false));
    concrete.put(BigInteger.class.getName(), new NumberSerializer(BigInteger.class));
    concrete.put(BigDecimal.class.getName(), new NumberSerializer(BigDecimal.class));
    concrete.put(Calendar.class.getName(), CalendarSerializer.instance);
    concrete.put(Date.class.getName(), DateSerializer.instance);
    for (Map.Entry<Class<?>, Object> en : (Iterable<Map.Entry<Class<?>, Object>>)StdJdkSerializers.all()) {
      Object value = en.getValue();
      if (value instanceof JsonSerializer) {
        concrete.put(((Class)en.getKey()).getName(), (JsonSerializer)value);
        continue;
      } 
      Class<? extends JsonSerializer<?>> cls = (Class<? extends JsonSerializer<?>>)value;
      concLazy.put(((Class)en.getKey()).getName(), cls);
    } 
    concLazy.put(TokenBuffer.class.getName(), TokenBufferSerializer.class);
    _concrete = concrete;
    _concreteLazy = concLazy;
  }
  
  protected BasicSerializerFactory(SerializerFactoryConfig config) {
    this._factoryConfig = (config == null) ? new SerializerFactoryConfig() : config;
  }
  
  public SerializerFactoryConfig getFactoryConfig() {
    return this._factoryConfig;
  }
  
  public final SerializerFactory withAdditionalSerializers(Serializers additional) {
    return withConfig(this._factoryConfig.withAdditionalSerializers(additional));
  }
  
  public final SerializerFactory withAdditionalKeySerializers(Serializers additional) {
    return withConfig(this._factoryConfig.withAdditionalKeySerializers(additional));
  }
  
  public final SerializerFactory withSerializerModifier(BeanSerializerModifier modifier) {
    return withConfig(this._factoryConfig.withSerializerModifier(modifier));
  }
  
  public JsonSerializer<Object> createKeySerializer(SerializerProvider ctxt, JavaType keyType, JsonSerializer<Object> defaultImpl) throws JsonMappingException {
    SerializationConfig config = ctxt.getConfig();
    BeanDescription beanDesc = config.introspect(keyType);
    JsonSerializer<?> ser = null;
    if (this._factoryConfig.hasKeySerializers())
      for (Serializers serializers : this._factoryConfig.keySerializers()) {
        ser = serializers.findSerializer(config, keyType, beanDesc);
        if (ser != null)
          break; 
      }  
    if (ser == null) {
      ser = _findKeySerializer(ctxt, (Annotated)beanDesc.getClassInfo());
      if (ser == null) {
        ser = defaultImpl;
        if (ser == null) {
          ser = StdKeySerializers.getStdKeySerializer(config, keyType.getRawClass(), false);
          if (ser == null) {
            AnnotatedMember acc = beanDesc.findJsonKeyAccessor();
            if (acc == null)
              acc = beanDesc.findJsonValueAccessor(); 
            if (acc != null) {
              JsonSerializer<?> delegate = createKeySerializer(ctxt, acc.getType(), defaultImpl);
              if (config.canOverrideAccessModifiers())
                ClassUtil.checkAndFixAccess(acc.getMember(), config
                    .isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS)); 
              JsonValueSerializer jsonValueSerializer = JsonValueSerializer.construct(config, acc, null, delegate);
            } else {
              ser = StdKeySerializers.getFallbackKeySerializer(config, keyType.getRawClass(), beanDesc
                  .getClassInfo());
            } 
          } 
        } 
      } 
    } 
    if (this._factoryConfig.hasSerializerModifiers())
      for (BeanSerializerModifier mod : this._factoryConfig.serializerModifiers())
        ser = mod.modifyKeySerializer(config, keyType, beanDesc, ser);  
    return (JsonSerializer)ser;
  }
  
  @Deprecated
  public JsonSerializer<Object> createKeySerializer(SerializationConfig config, JavaType keyType, JsonSerializer<Object> defaultImpl) {
    BeanDescription beanDesc = config.introspect(keyType);
    JsonSerializer<?> ser = null;
    if (this._factoryConfig.hasKeySerializers())
      for (Serializers serializers : this._factoryConfig.keySerializers()) {
        ser = serializers.findSerializer(config, keyType, beanDesc);
        if (ser != null)
          break; 
      }  
    if (ser == null) {
      ser = defaultImpl;
      if (ser == null) {
        ser = StdKeySerializers.getStdKeySerializer(config, keyType.getRawClass(), false);
        if (ser == null)
          ser = StdKeySerializers.getFallbackKeySerializer(config, keyType.getRawClass(), beanDesc
              .getClassInfo()); 
      } 
    } 
    if (this._factoryConfig.hasSerializerModifiers())
      for (BeanSerializerModifier mod : this._factoryConfig.serializerModifiers())
        ser = mod.modifyKeySerializer(config, keyType, beanDesc, ser);  
    return (JsonSerializer)ser;
  }
  
  public TypeSerializer createTypeSerializer(SerializationConfig config, JavaType baseType) {
    BeanDescription bean = config.introspectClassAnnotations(baseType.getRawClass());
    AnnotatedClass ac = bean.getClassInfo();
    AnnotationIntrospector ai = config.getAnnotationIntrospector();
    TypeResolverBuilder<?> b = ai.findTypeResolver((MapperConfig)config, ac, baseType);
    Collection<NamedType> subtypes = null;
    if (b == null) {
      b = config.getDefaultTyper(baseType);
    } else {
      subtypes = config.getSubtypeResolver().collectAndResolveSubtypesByClass((MapperConfig)config, ac);
    } 
    if (b == null)
      return null; 
    return b.buildTypeSerializer(config, baseType, subtypes);
  }
  
  protected final JsonSerializer<?> findSerializerByLookup(JavaType type, SerializationConfig config, BeanDescription beanDesc, boolean staticTyping) {
    Class<?> raw = type.getRawClass();
    String clsName = raw.getName();
    JsonSerializer<?> ser = _concrete.get(clsName);
    if (ser == null) {
      Class<? extends JsonSerializer<?>> serClass = _concreteLazy.get(clsName);
      if (serClass != null)
        return (JsonSerializer)ClassUtil.createInstance(serClass, false); 
    } 
    return ser;
  }
  
  protected final JsonSerializer<?> findSerializerByAnnotations(SerializerProvider prov, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
    Class<?> raw = type.getRawClass();
    if (JsonSerializable.class.isAssignableFrom(raw))
      return (JsonSerializer<?>)SerializableSerializer.instance; 
    AnnotatedMember valueAccessor = beanDesc.findJsonValueAccessor();
    if (valueAccessor != null) {
      if (prov.canOverrideAccessModifiers())
        ClassUtil.checkAndFixAccess(valueAccessor.getMember(), prov
            .isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS)); 
      JavaType valueType = valueAccessor.getType();
      JsonSerializer<Object> valueSerializer = findSerializerFromAnnotation(prov, (Annotated)valueAccessor);
      if (valueSerializer == null)
        valueSerializer = (JsonSerializer<Object>)valueType.getValueHandler(); 
      TypeSerializer typeSerializer = (TypeSerializer)valueType.getTypeHandler();
      if (typeSerializer == null)
        typeSerializer = createTypeSerializer(prov.getConfig(), valueType); 
      return (JsonSerializer<?>)JsonValueSerializer.construct(prov.getConfig(), valueAccessor, typeSerializer, valueSerializer);
    } 
    return null;
  }
  
  protected final JsonSerializer<?> findSerializerByPrimaryType(SerializerProvider prov, JavaType type, BeanDescription beanDesc, boolean staticTyping) throws JsonMappingException {
    if (type.isEnumType())
      return buildEnumSerializer(prov.getConfig(), type, beanDesc); 
    Class<?> raw = type.getRawClass();
    JsonSerializer<?> ser = findOptionalStdSerializer(prov, type, beanDesc, staticTyping);
    if (ser != null)
      return ser; 
    if (Calendar.class.isAssignableFrom(raw))
      return (JsonSerializer<?>)CalendarSerializer.instance; 
    if (Date.class.isAssignableFrom(raw))
      return (JsonSerializer<?>)DateSerializer.instance; 
    if (Map.Entry.class.isAssignableFrom(raw)) {
      JavaType mapEntryType = type.findSuperType(Map.Entry.class);
      JavaType kt = mapEntryType.containedTypeOrUnknown(0);
      JavaType vt = mapEntryType.containedTypeOrUnknown(1);
      return buildMapEntrySerializer(prov, type, beanDesc, staticTyping, kt, vt);
    } 
    if (ByteBuffer.class.isAssignableFrom(raw))
      return (JsonSerializer<?>)new ByteBufferSerializer(); 
    if (InetAddress.class.isAssignableFrom(raw))
      return (JsonSerializer<?>)new InetAddressSerializer(); 
    if (InetSocketAddress.class.isAssignableFrom(raw))
      return (JsonSerializer<?>)new InetSocketAddressSerializer(); 
    if (TimeZone.class.isAssignableFrom(raw))
      return (JsonSerializer<?>)new TimeZoneSerializer(); 
    if (Charset.class.isAssignableFrom(raw))
      return (JsonSerializer<?>)ToStringSerializer.instance; 
    if (Number.class.isAssignableFrom(raw)) {
      JsonFormat.Value format = beanDesc.findExpectedFormat(null);
      switch (format.getShape()) {
        case DYNAMIC:
          return (JsonSerializer<?>)ToStringSerializer.instance;
        case STATIC:
        case DEFAULT_TYPING:
          return null;
      } 
      return (JsonSerializer<?>)NumberSerializer.instance;
    } 
    if (ClassLoader.class.isAssignableFrom(raw))
      return (JsonSerializer<?>)new ToEmptyObjectSerializer(type); 
    return null;
  }
  
  protected JsonSerializer<?> findOptionalStdSerializer(SerializerProvider prov, JavaType type, BeanDescription beanDesc, boolean staticTyping) throws JsonMappingException {
    return OptionalHandlerFactory.instance.findSerializer(prov.getConfig(), type, beanDesc);
  }
  
  protected final JsonSerializer<?> findSerializerByAddonType(SerializationConfig config, JavaType javaType, BeanDescription beanDesc, boolean staticTyping) throws JsonMappingException {
    Class<?> rawType = javaType.getRawClass();
    if (Iterator.class.isAssignableFrom(rawType)) {
      JavaType[] params = config.getTypeFactory().findTypeParameters(javaType, Iterator.class);
      JavaType vt = (params == null || params.length != 1) ? TypeFactory.unknownType() : params[0];
      return buildIteratorSerializer(config, javaType, beanDesc, staticTyping, vt);
    } 
    if (Iterable.class.isAssignableFrom(rawType)) {
      JavaType[] params = config.getTypeFactory().findTypeParameters(javaType, Iterable.class);
      JavaType vt = (params == null || params.length != 1) ? TypeFactory.unknownType() : params[0];
      return buildIterableSerializer(config, javaType, beanDesc, staticTyping, vt);
    } 
    if (CharSequence.class.isAssignableFrom(rawType))
      return (JsonSerializer<?>)ToStringSerializer.instance; 
    return null;
  }
  
  protected JsonSerializer<Object> findSerializerFromAnnotation(SerializerProvider prov, Annotated a) throws JsonMappingException {
    Object serDef = prov.getAnnotationIntrospector().findSerializer(a);
    if (serDef == null)
      return null; 
    JsonSerializer<Object> ser = prov.serializerInstance(a, serDef);
    return (JsonSerializer)findConvertingSerializer(prov, a, ser);
  }
  
  protected JsonSerializer<?> findConvertingSerializer(SerializerProvider prov, Annotated a, JsonSerializer<?> ser) throws JsonMappingException {
    Converter<Object, Object> conv = findConverter(prov, a);
    if (conv == null)
      return ser; 
    JavaType delegateType = conv.getOutputType(prov.getTypeFactory());
    return (JsonSerializer<?>)new StdDelegatingSerializer(conv, delegateType, ser);
  }
  
  protected Converter<Object, Object> findConverter(SerializerProvider prov, Annotated a) throws JsonMappingException {
    Object convDef = prov.getAnnotationIntrospector().findSerializationConverter(a);
    if (convDef == null)
      return null; 
    return prov.converterInstance(a, convDef);
  }
  
  protected JsonSerializer<?> buildContainerSerializer(SerializerProvider prov, JavaType type, BeanDescription beanDesc, boolean staticTyping) throws JsonMappingException {
    SerializationConfig config = prov.getConfig();
    if (!staticTyping && type.useStaticType() && (
      !type.isContainerType() || !type.getContentType().isJavaLangObject()))
      staticTyping = true; 
    JavaType elementType = type.getContentType();
    TypeSerializer elementTypeSerializer = createTypeSerializer(config, elementType);
    if (elementTypeSerializer != null)
      staticTyping = false; 
    JsonSerializer<Object> elementValueSerializer = _findContentSerializer(prov, (Annotated)beanDesc
        .getClassInfo());
    if (type.isMapLikeType()) {
      MapLikeType mlt = (MapLikeType)type;
      JsonSerializer<Object> keySerializer = _findKeySerializer(prov, (Annotated)beanDesc.getClassInfo());
      if (mlt instanceof MapType)
        return buildMapSerializer(prov, (MapType)mlt, beanDesc, staticTyping, keySerializer, elementTypeSerializer, elementValueSerializer); 
      JsonSerializer<?> ser = null;
      MapLikeType mlType = (MapLikeType)type;
      for (Serializers serializers : customSerializers()) {
        ser = serializers.findMapLikeSerializer(config, mlType, beanDesc, keySerializer, elementTypeSerializer, elementValueSerializer);
        if (ser != null)
          break; 
      } 
      if (ser == null)
        ser = findSerializerByAnnotations(prov, type, beanDesc); 
      if (ser != null && 
        this._factoryConfig.hasSerializerModifiers())
        for (BeanSerializerModifier mod : this._factoryConfig.serializerModifiers())
          ser = mod.modifyMapLikeSerializer(config, mlType, beanDesc, ser);  
      return ser;
    } 
    if (type.isCollectionLikeType()) {
      CollectionLikeType clt = (CollectionLikeType)type;
      if (clt instanceof CollectionType)
        return buildCollectionSerializer(prov, (CollectionType)clt, beanDesc, staticTyping, elementTypeSerializer, elementValueSerializer); 
      JsonSerializer<?> ser = null;
      CollectionLikeType clType = (CollectionLikeType)type;
      for (Serializers serializers : customSerializers()) {
        ser = serializers.findCollectionLikeSerializer(config, clType, beanDesc, elementTypeSerializer, elementValueSerializer);
        if (ser != null)
          break; 
      } 
      if (ser == null)
        ser = findSerializerByAnnotations(prov, type, beanDesc); 
      if (ser != null && 
        this._factoryConfig.hasSerializerModifiers())
        for (BeanSerializerModifier mod : this._factoryConfig.serializerModifiers())
          ser = mod.modifyCollectionLikeSerializer(config, clType, beanDesc, ser);  
      return ser;
    } 
    if (type.isArrayType())
      return buildArraySerializer(prov, (ArrayType)type, beanDesc, staticTyping, elementTypeSerializer, elementValueSerializer); 
    return null;
  }
  
  protected JsonSerializer<?> buildCollectionSerializer(SerializerProvider prov, CollectionType type, BeanDescription beanDesc, boolean staticTyping, TypeSerializer elementTypeSerializer, JsonSerializer<Object> elementValueSerializer) throws JsonMappingException {
    ContainerSerializer<?> containerSerializer;
    JsonSerializer<?> jsonSerializer1;
    SerializationConfig config = prov.getConfig();
    JsonSerializer<?> ser = null;
    for (Serializers serializers : customSerializers()) {
      ser = serializers.findCollectionSerializer(config, type, beanDesc, elementTypeSerializer, elementValueSerializer);
      if (ser != null)
        break; 
    } 
    if (ser == null) {
      ser = findSerializerByAnnotations(prov, (JavaType)type, beanDesc);
      if (ser == null) {
        JsonFormat.Value format = beanDesc.findExpectedFormat(null);
        if (format.getShape() == JsonFormat.Shape.OBJECT)
          return null; 
        Class<?> raw = type.getRawClass();
        if (EnumSet.class.isAssignableFrom(raw)) {
          JavaType enumType = type.getContentType();
          if (!enumType.isEnumImplType())
            enumType = null; 
          ser = buildEnumSetSerializer(enumType);
        } else {
          StringCollectionSerializer stringCollectionSerializer;
          Class<?> elementRaw = type.getContentType().getRawClass();
          if (isIndexedList(raw)) {
            if (elementRaw == String.class) {
              if (ClassUtil.isJacksonStdImpl(elementValueSerializer))
                IndexedStringListSerializer indexedStringListSerializer = IndexedStringListSerializer.instance; 
            } else {
              containerSerializer = buildIndexedListSerializer(type.getContentType(), staticTyping, elementTypeSerializer, elementValueSerializer);
            } 
          } else if (elementRaw == String.class) {
            if (ClassUtil.isJacksonStdImpl(elementValueSerializer))
              stringCollectionSerializer = StringCollectionSerializer.instance; 
          } 
          if (stringCollectionSerializer == null)
            containerSerializer = buildCollectionSerializer(type.getContentType(), staticTyping, elementTypeSerializer, elementValueSerializer); 
        } 
      } 
    } 
    if (this._factoryConfig.hasSerializerModifiers())
      for (BeanSerializerModifier mod : this._factoryConfig.serializerModifiers())
        jsonSerializer1 = mod.modifyCollectionSerializer(config, type, beanDesc, (JsonSerializer<?>)containerSerializer);  
    return jsonSerializer1;
  }
  
  protected boolean isIndexedList(Class<?> cls) {
    return RandomAccess.class.isAssignableFrom(cls);
  }
  
  public ContainerSerializer<?> buildIndexedListSerializer(JavaType elemType, boolean staticTyping, TypeSerializer vts, JsonSerializer<Object> valueSerializer) {
    return (ContainerSerializer<?>)new IndexedListSerializer(elemType, staticTyping, vts, valueSerializer);
  }
  
  public ContainerSerializer<?> buildCollectionSerializer(JavaType elemType, boolean staticTyping, TypeSerializer vts, JsonSerializer<Object> valueSerializer) {
    return (ContainerSerializer<?>)new CollectionSerializer(elemType, staticTyping, vts, valueSerializer);
  }
  
  public JsonSerializer<?> buildEnumSetSerializer(JavaType enumType) {
    return (JsonSerializer<?>)new EnumSetSerializer(enumType);
  }
  
  protected JsonSerializer<?> buildMapSerializer(SerializerProvider prov, MapType type, BeanDescription beanDesc, boolean staticTyping, JsonSerializer<Object> keySerializer, TypeSerializer elementTypeSerializer, JsonSerializer<Object> elementValueSerializer) throws JsonMappingException {
    MapSerializer mapSerializer;
    JsonSerializer<?> jsonSerializer1;
    JsonFormat.Value format = beanDesc.findExpectedFormat(null);
    if (format.getShape() == JsonFormat.Shape.OBJECT)
      return null; 
    JsonSerializer<?> ser = null;
    SerializationConfig config = prov.getConfig();
    for (Serializers serializers : customSerializers()) {
      ser = serializers.findMapSerializer(config, type, beanDesc, keySerializer, elementTypeSerializer, elementValueSerializer);
      if (ser != null)
        break; 
    } 
    if (ser == null) {
      ser = findSerializerByAnnotations(prov, (JavaType)type, beanDesc);
      if (ser == null) {
        Object filterId = findFilterId(config, beanDesc);
        JsonIgnoreProperties.Value ignorals = config.getDefaultPropertyIgnorals(Map.class, beanDesc
            .getClassInfo());
        Set<String> ignored = (ignorals == null) ? null : ignorals.findIgnoredForSerialization();
        JsonIncludeProperties.Value inclusions = config.getDefaultPropertyInclusions(Map.class, beanDesc
            .getClassInfo());
        Set<String> included = (inclusions == null) ? null : inclusions.getIncluded();
        MapSerializer mapSer = MapSerializer.construct(ignored, included, (JavaType)type, staticTyping, elementTypeSerializer, keySerializer, elementValueSerializer, filterId);
        mapSerializer = _checkMapContentInclusion(prov, beanDesc, mapSer);
      } 
    } 
    if (this._factoryConfig.hasSerializerModifiers())
      for (BeanSerializerModifier mod : this._factoryConfig.serializerModifiers())
        jsonSerializer1 = mod.modifyMapSerializer(config, type, beanDesc, (JsonSerializer<?>)mapSerializer);  
    return jsonSerializer1;
  }
  
  protected MapSerializer _checkMapContentInclusion(SerializerProvider prov, BeanDescription beanDesc, MapSerializer mapSer) throws JsonMappingException {
    JavaType contentType = mapSer.getContentType();
    JsonInclude.Value inclV = _findInclusionWithContent(prov, beanDesc, contentType, Map.class);
    JsonInclude.Include incl = (inclV == null) ? JsonInclude.Include.USE_DEFAULTS : inclV.getContentInclusion();
    if (incl == JsonInclude.Include.USE_DEFAULTS || incl == JsonInclude.Include.ALWAYS) {
      if (!prov.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES))
        return mapSer.withContentInclusion(null, true); 
      return mapSer;
    } 
    boolean suppressNulls = true;
    switch (incl) {
      case DYNAMIC:
        valueToSuppress = BeanUtil.getDefaultValue(contentType);
        if (valueToSuppress != null && 
          valueToSuppress.getClass().isArray())
          valueToSuppress = ArrayBuilders.getArrayComparator(valueToSuppress); 
        return mapSer.withContentInclusion(valueToSuppress, suppressNulls);
      case STATIC:
        valueToSuppress = contentType.isReferenceType() ? MapSerializer.MARKER_FOR_EMPTY : null;
        return mapSer.withContentInclusion(valueToSuppress, suppressNulls);
      case DEFAULT_TYPING:
        valueToSuppress = MapSerializer.MARKER_FOR_EMPTY;
        return mapSer.withContentInclusion(valueToSuppress, suppressNulls);
      case null:
        valueToSuppress = prov.includeFilterInstance(null, inclV.getContentFilter());
        if (valueToSuppress != null)
          suppressNulls = prov.includeFilterSuppressNulls(valueToSuppress); 
        return mapSer.withContentInclusion(valueToSuppress, suppressNulls);
    } 
    Object valueToSuppress = null;
    return mapSer.withContentInclusion(valueToSuppress, suppressNulls);
  }
  
  protected JsonSerializer<?> buildMapEntrySerializer(SerializerProvider prov, JavaType type, BeanDescription beanDesc, boolean staticTyping, JavaType keyType, JavaType valueType) throws JsonMappingException {
    JsonFormat.Value formatOverride = prov.getDefaultPropertyFormat(Map.Entry.class);
    JsonFormat.Value formatFromAnnotation = beanDesc.findExpectedFormat(null);
    JsonFormat.Value format = JsonFormat.Value.merge(formatFromAnnotation, formatOverride);
    if (format.getShape() == JsonFormat.Shape.OBJECT)
      return null; 
    MapEntrySerializer ser = new MapEntrySerializer(valueType, keyType, valueType, staticTyping, createTypeSerializer(prov.getConfig(), valueType), null);
    JavaType contentType = ser.getContentType();
    JsonInclude.Value inclV = _findInclusionWithContent(prov, beanDesc, contentType, Map.Entry.class);
    JsonInclude.Include incl = (inclV == null) ? JsonInclude.Include.USE_DEFAULTS : inclV.getContentInclusion();
    if (incl == JsonInclude.Include.USE_DEFAULTS || incl == JsonInclude.Include.ALWAYS)
      return (JsonSerializer<?>)ser; 
    boolean suppressNulls = true;
    switch (incl) {
      case DYNAMIC:
        valueToSuppress = BeanUtil.getDefaultValue(contentType);
        if (valueToSuppress != null && 
          valueToSuppress.getClass().isArray())
          valueToSuppress = ArrayBuilders.getArrayComparator(valueToSuppress); 
        return (JsonSerializer<?>)ser.withContentInclusion(valueToSuppress, suppressNulls);
      case STATIC:
        valueToSuppress = contentType.isReferenceType() ? MapSerializer.MARKER_FOR_EMPTY : null;
        return (JsonSerializer<?>)ser.withContentInclusion(valueToSuppress, suppressNulls);
      case DEFAULT_TYPING:
        valueToSuppress = MapSerializer.MARKER_FOR_EMPTY;
        return (JsonSerializer<?>)ser.withContentInclusion(valueToSuppress, suppressNulls);
      case null:
        valueToSuppress = prov.includeFilterInstance(null, inclV.getContentFilter());
        if (valueToSuppress != null)
          suppressNulls = prov.includeFilterSuppressNulls(valueToSuppress); 
        return (JsonSerializer<?>)ser.withContentInclusion(valueToSuppress, suppressNulls);
    } 
    Object valueToSuppress = null;
    return (JsonSerializer<?>)ser.withContentInclusion(valueToSuppress, suppressNulls);
  }
  
  protected JsonInclude.Value _findInclusionWithContent(SerializerProvider prov, BeanDescription beanDesc, JavaType contentType, Class<?> configType) throws JsonMappingException {
    SerializationConfig config = prov.getConfig();
    JsonInclude.Value inclV = beanDesc.findPropertyInclusion(config.getDefaultPropertyInclusion());
    inclV = config.getDefaultPropertyInclusion(configType, inclV);
    JsonInclude.Value valueIncl = config.getDefaultPropertyInclusion(contentType.getRawClass(), null);
    if (valueIncl != null) {
      switch (valueIncl.getValueInclusion()) {
        case null:
          return inclV;
        case null:
          inclV = inclV.withContentFilter(valueIncl.getContentFilter());
      } 
      inclV = inclV.withContentInclusion(valueIncl.getValueInclusion());
    } 
  }
  
  protected JsonSerializer<?> buildArraySerializer(SerializerProvider prov, ArrayType type, BeanDescription beanDesc, boolean staticTyping, TypeSerializer elementTypeSerializer, JsonSerializer<Object> elementValueSerializer) throws JsonMappingException {
    ObjectArraySerializer objectArraySerializer;
    JsonSerializer<?> jsonSerializer1;
    SerializationConfig config = prov.getConfig();
    JsonSerializer<?> ser = null;
    for (Serializers serializers : customSerializers()) {
      ser = serializers.findArraySerializer(config, type, beanDesc, elementTypeSerializer, elementValueSerializer);
      if (ser != null)
        break; 
    } 
    if (ser == null) {
      Class<?> raw = type.getRawClass();
      if (elementValueSerializer == null || ClassUtil.isJacksonStdImpl(elementValueSerializer))
        if (String[].class == raw) {
          StringArraySerializer stringArraySerializer = StringArraySerializer.instance;
        } else {
          ser = StdArraySerializers.findStandardImpl(raw);
        }  
      if (ser == null)
        objectArraySerializer = new ObjectArraySerializer(type.getContentType(), staticTyping, elementTypeSerializer, elementValueSerializer); 
    } 
    if (this._factoryConfig.hasSerializerModifiers())
      for (BeanSerializerModifier mod : this._factoryConfig.serializerModifiers())
        jsonSerializer1 = mod.modifyArraySerializer(config, type, beanDesc, (JsonSerializer<?>)objectArraySerializer);  
    return jsonSerializer1;
  }
  
  public JsonSerializer<?> findReferenceSerializer(SerializerProvider prov, ReferenceType refType, BeanDescription beanDesc, boolean staticTyping) throws JsonMappingException {
    JavaType contentType = refType.getContentType();
    TypeSerializer contentTypeSerializer = (TypeSerializer)contentType.getTypeHandler();
    SerializationConfig config = prov.getConfig();
    if (contentTypeSerializer == null)
      contentTypeSerializer = createTypeSerializer(config, contentType); 
    JsonSerializer<Object> contentSerializer = (JsonSerializer<Object>)contentType.getValueHandler();
    for (Serializers serializers : customSerializers()) {
      JsonSerializer<?> ser = serializers.findReferenceSerializer(config, refType, beanDesc, contentTypeSerializer, contentSerializer);
      if (ser != null)
        return ser; 
    } 
    if (refType.isTypeOrSubTypeOf(AtomicReference.class))
      return buildAtomicReferenceSerializer(prov, refType, beanDesc, staticTyping, contentTypeSerializer, contentSerializer); 
    return null;
  }
  
  protected JsonSerializer<?> buildAtomicReferenceSerializer(SerializerProvider prov, ReferenceType refType, BeanDescription beanDesc, boolean staticTyping, TypeSerializer contentTypeSerializer, JsonSerializer<Object> contentSerializer) throws JsonMappingException {
    Object valueToSuppress;
    boolean suppressNulls;
    JavaType contentType = refType.getReferencedType();
    JsonInclude.Value inclV = _findInclusionWithContent(prov, beanDesc, contentType, AtomicReference.class);
    JsonInclude.Include incl = (inclV == null) ? JsonInclude.Include.USE_DEFAULTS : inclV.getContentInclusion();
    if (incl == JsonInclude.Include.USE_DEFAULTS || incl == JsonInclude.Include.ALWAYS) {
      valueToSuppress = null;
      suppressNulls = false;
    } else {
      AtomicReferenceSerializer ser;
      suppressNulls = true;
      switch (incl) {
        case DYNAMIC:
          valueToSuppress = BeanUtil.getDefaultValue(contentType);
          if (valueToSuppress != null && 
            valueToSuppress.getClass().isArray())
            valueToSuppress = ArrayBuilders.getArrayComparator(valueToSuppress); 
          ser = new AtomicReferenceSerializer(refType, staticTyping, contentTypeSerializer, contentSerializer);
          return (JsonSerializer<?>)ser.withContentInclusion(valueToSuppress, suppressNulls);
        case STATIC:
          valueToSuppress = contentType.isReferenceType() ? MapSerializer.MARKER_FOR_EMPTY : null;
          ser = new AtomicReferenceSerializer(refType, staticTyping, contentTypeSerializer, contentSerializer);
          return (JsonSerializer<?>)ser.withContentInclusion(valueToSuppress, suppressNulls);
        case DEFAULT_TYPING:
          valueToSuppress = MapSerializer.MARKER_FOR_EMPTY;
          ser = new AtomicReferenceSerializer(refType, staticTyping, contentTypeSerializer, contentSerializer);
          return (JsonSerializer<?>)ser.withContentInclusion(valueToSuppress, suppressNulls);
        case null:
          valueToSuppress = prov.includeFilterInstance(null, inclV.getContentFilter());
          if (valueToSuppress == null) {
            suppressNulls = true;
          } else {
            suppressNulls = prov.includeFilterSuppressNulls(valueToSuppress);
          } 
          ser = new AtomicReferenceSerializer(refType, staticTyping, contentTypeSerializer, contentSerializer);
          return (JsonSerializer<?>)ser.withContentInclusion(valueToSuppress, suppressNulls);
      } 
      valueToSuppress = null;
    } 
    AtomicReferenceSerializer atomicReferenceSerializer = new AtomicReferenceSerializer(refType, staticTyping, contentTypeSerializer, contentSerializer);
    return (JsonSerializer<?>)atomicReferenceSerializer.withContentInclusion(valueToSuppress, suppressNulls);
  }
  
  protected JsonSerializer<?> buildIteratorSerializer(SerializationConfig config, JavaType type, BeanDescription beanDesc, boolean staticTyping, JavaType valueType) throws JsonMappingException {
    return (JsonSerializer<?>)new IteratorSerializer(valueType, staticTyping, createTypeSerializer(config, valueType));
  }
  
  protected JsonSerializer<?> buildIterableSerializer(SerializationConfig config, JavaType type, BeanDescription beanDesc, boolean staticTyping, JavaType valueType) throws JsonMappingException {
    return (JsonSerializer<?>)new IterableSerializer(valueType, staticTyping, createTypeSerializer(config, valueType));
  }
  
  protected JsonSerializer<?> buildEnumSerializer(SerializationConfig config, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
    JsonSerializer<?> jsonSerializer;
    JsonFormat.Value format = beanDesc.findExpectedFormat(null);
    if (format.getShape() == JsonFormat.Shape.OBJECT) {
      ((BasicBeanDescription)beanDesc).removeProperty("declaringClass");
      if (type.isEnumType())
        _removeEnumSelfReferences((BasicBeanDescription)beanDesc); 
      return null;
    } 
    Class<Enum<?>> enumClass = type.getRawClass();
    EnumSerializer enumSerializer = EnumSerializer.construct(enumClass, config, beanDesc, format);
    if (this._factoryConfig.hasSerializerModifiers())
      for (BeanSerializerModifier mod : this._factoryConfig.serializerModifiers())
        jsonSerializer = mod.modifyEnumSerializer(config, type, beanDesc, (JsonSerializer<?>)enumSerializer);  
    return jsonSerializer;
  }
  
  private void _removeEnumSelfReferences(BasicBeanDescription beanDesc) {
    Class<?> aClass = ClassUtil.findEnumType(beanDesc.getBeanClass());
    Iterator<BeanPropertyDefinition> it = beanDesc.findProperties().iterator();
    while (it.hasNext()) {
      BeanPropertyDefinition property = it.next();
      JavaType propType = property.getPrimaryType();
      if (propType.isEnumType() && propType.isTypeOrSubTypeOf(aClass))
        it.remove(); 
    } 
  }
  
  protected JsonSerializer<Object> _findKeySerializer(SerializerProvider prov, Annotated a) throws JsonMappingException {
    AnnotationIntrospector intr = prov.getAnnotationIntrospector();
    Object serDef = intr.findKeySerializer(a);
    if (serDef != null)
      return prov.serializerInstance(a, serDef); 
    return null;
  }
  
  protected JsonSerializer<Object> _findContentSerializer(SerializerProvider prov, Annotated a) throws JsonMappingException {
    AnnotationIntrospector intr = prov.getAnnotationIntrospector();
    Object serDef = intr.findContentSerializer(a);
    if (serDef != null)
      return prov.serializerInstance(a, serDef); 
    return null;
  }
  
  protected Object findFilterId(SerializationConfig config, BeanDescription beanDesc) {
    return config.getAnnotationIntrospector().findFilterId((Annotated)beanDesc.getClassInfo());
  }
  
  protected boolean usesStaticTyping(SerializationConfig config, BeanDescription beanDesc) {
    JsonSerialize.Typing t = config.getAnnotationIntrospector().findSerializationTyping((Annotated)beanDesc.getClassInfo());
    if (t != null)
      switch (t) {
        case DYNAMIC:
          return false;
        case STATIC:
          return true;
      }  
    return config.isEnabled(MapperFeature.USE_STATIC_TYPING);
  }
  
  public abstract SerializerFactory withConfig(SerializerFactoryConfig paramSerializerFactoryConfig);
  
  public abstract JsonSerializer<Object> createSerializer(SerializerProvider paramSerializerProvider, JavaType paramJavaType) throws JsonMappingException;
  
  protected abstract Iterable<Serializers> customSerializers();
}
