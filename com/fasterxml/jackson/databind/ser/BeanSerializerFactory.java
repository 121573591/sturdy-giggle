package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TokenStreamFactory;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.impl.FilteredBeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer;
import com.fasterxml.jackson.databind.ser.std.ToEmptyObjectSerializer;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.IgnorePropertiesUtil;
import com.fasterxml.jackson.databind.util.NativeImageUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BeanSerializerFactory extends BasicSerializerFactory implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public static final BeanSerializerFactory instance = new BeanSerializerFactory(null);
  
  protected BeanSerializerFactory(SerializerFactoryConfig config) {
    super(config);
  }
  
  public SerializerFactory withConfig(SerializerFactoryConfig config) {
    if (this._factoryConfig == config)
      return this; 
    if (getClass() != BeanSerializerFactory.class)
      throw new IllegalStateException("Subtype of BeanSerializerFactory (" + getClass().getName() + ") has not properly overridden method 'withAdditionalSerializers': cannot instantiate subtype with additional serializer definitions"); 
    return new BeanSerializerFactory(config);
  }
  
  protected Iterable<Serializers> customSerializers() {
    return this._factoryConfig.serializers();
  }
  
  public JsonSerializer<Object> createSerializer(SerializerProvider prov, JavaType origType) throws JsonMappingException {
    boolean staticTyping;
    JavaType type;
    SerializationConfig config = prov.getConfig();
    BeanDescription beanDesc = config.introspect(origType);
    JsonSerializer<?> ser = findSerializerFromAnnotation(prov, (Annotated)beanDesc.getClassInfo());
    if (ser != null)
      return (JsonSerializer)ser; 
    AnnotationIntrospector intr = config.getAnnotationIntrospector();
    if (intr == null) {
      type = origType;
    } else {
      try {
        type = intr.refineSerializationType((MapperConfig)config, (Annotated)beanDesc.getClassInfo(), origType);
      } catch (JsonMappingException e) {
        return (JsonSerializer<Object>)prov.reportBadTypeDefinition(beanDesc, e.getMessage(), new Object[0]);
      } 
    } 
    if (type == origType) {
      staticTyping = false;
    } else {
      staticTyping = true;
      if (!type.hasRawClass(origType.getRawClass()))
        beanDesc = config.introspect(type); 
    } 
    Converter<Object, Object> conv = beanDesc.findSerializationConverter();
    if (conv == null)
      return (JsonSerializer)_createSerializer2(prov, type, beanDesc, staticTyping); 
    JavaType delegateType = conv.getOutputType(prov.getTypeFactory());
    if (!delegateType.hasRawClass(type.getRawClass())) {
      beanDesc = config.introspect(delegateType);
      ser = findSerializerFromAnnotation(prov, (Annotated)beanDesc.getClassInfo());
    } 
    if (ser == null && !delegateType.isJavaLangObject())
      ser = _createSerializer2(prov, delegateType, beanDesc, true); 
    return (JsonSerializer<Object>)new StdDelegatingSerializer(conv, delegateType, ser);
  }
  
  protected JsonSerializer<?> _createSerializer2(SerializerProvider prov, JavaType type, BeanDescription beanDesc, boolean staticTyping) throws JsonMappingException {
    JsonSerializer<?> ser = null;
    SerializationConfig config = prov.getConfig();
    if (type.isContainerType()) {
      if (!staticTyping)
        staticTyping = usesStaticTyping(config, beanDesc); 
      ser = buildContainerSerializer(prov, type, beanDesc, staticTyping);
      if (ser != null)
        return ser; 
    } else {
      if (type.isReferenceType()) {
        ser = findReferenceSerializer(prov, (ReferenceType)type, beanDesc, staticTyping);
      } else {
        for (Serializers serializers : customSerializers()) {
          ser = serializers.findSerializer(config, type, beanDesc);
          if (ser != null)
            break; 
        } 
      } 
      if (ser == null)
        ser = findSerializerByAnnotations(prov, type, beanDesc); 
    } 
    if (ser == null) {
      ser = findSerializerByLookup(type, config, beanDesc, staticTyping);
      if (ser == null) {
        ser = findSerializerByPrimaryType(prov, type, beanDesc, staticTyping);
        if (ser == null) {
          ser = findBeanOrAddOnSerializer(prov, type, beanDesc, staticTyping);
          if (ser == null)
            ser = prov.getUnknownTypeSerializer(beanDesc.getBeanClass()); 
        } 
      } 
    } 
    if (ser != null)
      if (this._factoryConfig.hasSerializerModifiers())
        for (BeanSerializerModifier mod : this._factoryConfig.serializerModifiers())
          ser = mod.modifySerializer(config, beanDesc, ser);   
    return ser;
  }
  
  @Deprecated
  public JsonSerializer<Object> findBeanSerializer(SerializerProvider prov, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
    return findBeanOrAddOnSerializer(prov, type, beanDesc, prov.isEnabled(MapperFeature.USE_STATIC_TYPING));
  }
  
  public JsonSerializer<Object> findBeanOrAddOnSerializer(SerializerProvider prov, JavaType type, BeanDescription beanDesc, boolean staticTyping) throws JsonMappingException {
    if (!isPotentialBeanType(type.getRawClass()))
      if (!ClassUtil.isEnumType(type.getRawClass()))
        return null;  
    return constructBeanOrAddOnSerializer(prov, type, beanDesc, staticTyping);
  }
  
  public TypeSerializer findPropertyTypeSerializer(JavaType baseType, SerializationConfig config, AnnotatedMember accessor) throws JsonMappingException {
    TypeSerializer typeSer;
    AnnotationIntrospector ai = config.getAnnotationIntrospector();
    TypeResolverBuilder<?> b = ai.findPropertyTypeResolver((MapperConfig)config, accessor, baseType);
    if (b == null) {
      typeSer = createTypeSerializer(config, baseType);
    } else {
      Collection<NamedType> subtypes = config.getSubtypeResolver().collectAndResolveSubtypesByClass((MapperConfig)config, accessor, baseType);
      typeSer = b.buildTypeSerializer(config, baseType, subtypes);
    } 
    return typeSer;
  }
  
  public TypeSerializer findPropertyContentTypeSerializer(JavaType containerType, SerializationConfig config, AnnotatedMember accessor) throws JsonMappingException {
    TypeSerializer typeSer;
    JavaType contentType = containerType.getContentType();
    AnnotationIntrospector ai = config.getAnnotationIntrospector();
    TypeResolverBuilder<?> b = ai.findPropertyContentTypeResolver((MapperConfig)config, accessor, containerType);
    if (b == null) {
      typeSer = createTypeSerializer(config, contentType);
    } else {
      Collection<NamedType> subtypes = config.getSubtypeResolver().collectAndResolveSubtypesByClass((MapperConfig)config, accessor, contentType);
      typeSer = b.buildTypeSerializer(config, contentType, subtypes);
    } 
    return typeSer;
  }
  
  @Deprecated
  protected JsonSerializer<Object> constructBeanSerializer(SerializerProvider prov, BeanDescription beanDesc) throws JsonMappingException {
    return constructBeanOrAddOnSerializer(prov, beanDesc.getType(), beanDesc, prov.isEnabled(MapperFeature.USE_STATIC_TYPING));
  }
  
  protected JsonSerializer<Object> constructBeanOrAddOnSerializer(SerializerProvider prov, JavaType type, BeanDescription beanDesc, boolean staticTyping) throws JsonMappingException {
    if (beanDesc.getBeanClass() == Object.class)
      return prov.getUnknownTypeSerializer(Object.class); 
    JsonSerializer<?> ser = _findUnsupportedTypeSerializer(prov, type, beanDesc);
    if (ser != null)
      return (JsonSerializer)ser; 
    if (_isUnserializableJacksonType(prov, type))
      return (JsonSerializer<Object>)new ToEmptyObjectSerializer(type); 
    SerializationConfig config = prov.getConfig();
    BeanSerializerBuilder builder = constructBeanSerializerBuilder(beanDesc);
    builder.setConfig(config);
    List<BeanPropertyWriter> props = findBeanProperties(prov, beanDesc, builder);
    if (props == null) {
      props = new ArrayList<>();
    } else {
      props = removeOverlappingTypeIds(prov, beanDesc, builder, props);
    } 
    prov.getAnnotationIntrospector().findAndAddVirtualProperties((MapperConfig)config, beanDesc.getClassInfo(), props);
    if (this._factoryConfig.hasSerializerModifiers())
      for (BeanSerializerModifier mod : this._factoryConfig.serializerModifiers())
        props = mod.changeProperties(config, beanDesc, props);  
    props = filterUnwantedJDKProperties(config, beanDesc, props);
    props = filterBeanProperties(config, beanDesc, props);
    if (this._factoryConfig.hasSerializerModifiers())
      for (BeanSerializerModifier mod : this._factoryConfig.serializerModifiers())
        props = mod.orderProperties(config, beanDesc, props);  
    builder.setObjectIdWriter(constructObjectIdHandler(prov, beanDesc, props));
    builder.setProperties(props);
    builder.setFilterId(findFilterId(config, beanDesc));
    AnnotatedMember anyGetter = beanDesc.findAnyGetter();
    if (anyGetter != null) {
      MapSerializer mapSerializer;
      JavaType anyType = anyGetter.getType();
      JavaType valueType = anyType.getContentType();
      TypeSerializer typeSer = createTypeSerializer(config, valueType);
      JsonSerializer<?> anySer = findSerializerFromAnnotation(prov, (Annotated)anyGetter);
      if (anySer == null)
        mapSerializer = MapSerializer.construct((Set)null, anyType, config
            .isEnabled(MapperFeature.USE_STATIC_TYPING), typeSer, null, null, null); 
      PropertyName name = PropertyName.construct(anyGetter.getName());
      BeanProperty.Std anyProp = new BeanProperty.Std(name, valueType, null, anyGetter, PropertyMetadata.STD_OPTIONAL);
      builder.setAnyGetter(new AnyGetterWriter((BeanProperty)anyProp, anyGetter, (JsonSerializer<?>)mapSerializer));
    } 
    processViews(config, builder);
    if (this._factoryConfig.hasSerializerModifiers())
      for (BeanSerializerModifier mod : this._factoryConfig.serializerModifiers())
        builder = mod.updateBuilder(config, beanDesc, builder);  
    try {
      ser = builder.build();
    } catch (RuntimeException e) {
      return (JsonSerializer<Object>)prov.reportBadTypeDefinition(beanDesc, "Failed to construct BeanSerializer for %s: (%s) %s", new Object[] { beanDesc
            .getType(), e.getClass().getName(), e.getMessage() });
    } 
    if (ser == null) {
      if (type.isRecordType() && !NativeImageUtil.needsReflectionConfiguration(type.getRawClass()))
        return (JsonSerializer<Object>)builder.createDummy(); 
      ser = findSerializerByAddonType(config, type, beanDesc, staticTyping);
      if (ser == null)
        if (beanDesc.hasKnownClassAnnotations())
          return (JsonSerializer<Object>)builder.createDummy();  
    } 
    return (JsonSerializer)ser;
  }
  
  protected ObjectIdWriter constructObjectIdHandler(SerializerProvider prov, BeanDescription beanDesc, List<BeanPropertyWriter> props) throws JsonMappingException {
    ObjectIdInfo objectIdInfo = beanDesc.getObjectIdInfo();
    if (objectIdInfo == null)
      return null; 
    Class<?> implClass = objectIdInfo.getGeneratorType();
    if (implClass == ObjectIdGenerators.PropertyGenerator.class) {
      String propName = objectIdInfo.getPropertyName().getSimpleName();
      BeanPropertyWriter idProp = null;
      for (int i = 0, len = props.size();; i++) {
        if (i == len)
          throw new IllegalArgumentException(String.format("Invalid Object Id definition for %s: cannot find property with name %s", new Object[] { ClassUtil.getTypeDescription(beanDesc.getType()), ClassUtil.name(propName) })); 
        BeanPropertyWriter prop = props.get(i);
        if (propName.equals(prop.getName())) {
          idProp = prop;
          if (i > 0) {
            props.remove(i);
            props.add(0, idProp);
          } 
          break;
        } 
      } 
      JavaType javaType = idProp.getType();
      PropertyBasedObjectIdGenerator propertyBasedObjectIdGenerator = new PropertyBasedObjectIdGenerator(objectIdInfo, idProp);
      return ObjectIdWriter.construct(javaType, (PropertyName)null, (ObjectIdGenerator)propertyBasedObjectIdGenerator, objectIdInfo.getAlwaysAsId());
    } 
    JavaType type = prov.constructType(implClass);
    JavaType idType = prov.getTypeFactory().findTypeParameters(type, ObjectIdGenerator.class)[0];
    ObjectIdGenerator<?> gen = prov.objectIdGeneratorInstance((Annotated)beanDesc.getClassInfo(), objectIdInfo);
    return ObjectIdWriter.construct(idType, objectIdInfo.getPropertyName(), gen, objectIdInfo
        .getAlwaysAsId());
  }
  
  protected BeanPropertyWriter constructFilteredBeanWriter(BeanPropertyWriter writer, Class<?>[] inViews) {
    return FilteredBeanPropertyWriter.constructViewBased(writer, inViews);
  }
  
  protected PropertyBuilder constructPropertyBuilder(SerializationConfig config, BeanDescription beanDesc) {
    return new PropertyBuilder(config, beanDesc);
  }
  
  protected BeanSerializerBuilder constructBeanSerializerBuilder(BeanDescription beanDesc) {
    return new BeanSerializerBuilder(beanDesc);
  }
  
  protected boolean isPotentialBeanType(Class<?> type) {
    return (ClassUtil.canBeABeanType(type) == null && !ClassUtil.isProxyType(type));
  }
  
  protected List<BeanPropertyWriter> findBeanProperties(SerializerProvider prov, BeanDescription beanDesc, BeanSerializerBuilder builder) throws JsonMappingException {
    List<BeanPropertyDefinition> properties = beanDesc.findProperties();
    SerializationConfig config = prov.getConfig();
    removeIgnorableTypes(config, beanDesc, properties);
    if (config.isEnabled(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS))
      removeSetterlessGetters(config, beanDesc, properties); 
    if (properties.isEmpty())
      return null; 
    boolean staticTyping = usesStaticTyping(config, beanDesc);
    PropertyBuilder pb = constructPropertyBuilder(config, beanDesc);
    ArrayList<BeanPropertyWriter> result = new ArrayList<>(properties.size());
    for (BeanPropertyDefinition property : properties) {
      AnnotatedMember accessor = property.getAccessor();
      if (property.isTypeId()) {
        if (accessor != null)
          builder.setTypeId(accessor); 
        continue;
      } 
      AnnotationIntrospector.ReferenceProperty refType = property.findReferenceType();
      if (refType != null && refType.isBackReference())
        continue; 
      if (accessor instanceof com.fasterxml.jackson.databind.introspect.AnnotatedMethod) {
        result.add(_constructWriter(prov, property, pb, staticTyping, accessor));
        continue;
      } 
      result.add(_constructWriter(prov, property, pb, staticTyping, accessor));
    } 
    return result;
  }
  
  protected List<BeanPropertyWriter> filterBeanProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> props) {
    JsonIgnoreProperties.Value ignorals = config.getDefaultPropertyIgnorals(beanDesc.getBeanClass(), beanDesc
        .getClassInfo());
    Set<String> ignored = null;
    if (ignorals != null)
      ignored = ignorals.findIgnoredForSerialization(); 
    JsonIncludeProperties.Value inclusions = config.getDefaultPropertyInclusions(beanDesc.getBeanClass(), beanDesc
        .getClassInfo());
    Set<String> included = null;
    if (inclusions != null)
      included = inclusions.getIncluded(); 
    if (included != null || (ignored != null && !ignored.isEmpty())) {
      Iterator<BeanPropertyWriter> it = props.iterator();
      while (it.hasNext()) {
        if (IgnorePropertiesUtil.shouldIgnore(((BeanPropertyWriter)it.next()).getName(), ignored, included))
          it.remove(); 
      } 
    } 
    return props;
  }
  
  protected List<BeanPropertyWriter> filterUnwantedJDKProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> props) {
    if (beanDesc.getType().isTypeOrSubTypeOf(CharSequence.class))
      if (props.size() == 1) {
        BeanPropertyWriter prop = props.get(0);
        AnnotatedMember m = prop.getMember();
        if (m instanceof com.fasterxml.jackson.databind.introspect.AnnotatedMethod && "isEmpty"
          .equals(m.getName()) && m
          .getDeclaringClass() == CharSequence.class)
          props.remove(0); 
      }  
    return props;
  }
  
  protected void processViews(SerializationConfig config, BeanSerializerBuilder builder) {
    List<BeanPropertyWriter> props = builder.getProperties();
    boolean includeByDefault = config.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION);
    int propCount = props.size();
    int viewsFound = 0;
    BeanPropertyWriter[] filtered = new BeanPropertyWriter[propCount];
    for (int i = 0; i < propCount; i++) {
      BeanPropertyWriter bpw = props.get(i);
      Class<?>[] views = bpw.getViews();
      if (views == null || views.length == 0) {
        if (includeByDefault)
          filtered[i] = bpw; 
      } else {
        viewsFound++;
        filtered[i] = constructFilteredBeanWriter(bpw, views);
      } 
    } 
    if (includeByDefault && viewsFound == 0)
      return; 
    builder.setFilteredProperties(filtered);
  }
  
  protected void removeIgnorableTypes(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyDefinition> properties) {
    AnnotationIntrospector intr = config.getAnnotationIntrospector();
    HashMap<Class<?>, Boolean> ignores = new HashMap<>();
    Iterator<BeanPropertyDefinition> it = properties.iterator();
    while (it.hasNext()) {
      BeanPropertyDefinition property = it.next();
      AnnotatedMember accessor = property.getAccessor();
      if (accessor == null) {
        it.remove();
        continue;
      } 
      Class<?> type = property.getRawPrimaryType();
      Boolean result = ignores.get(type);
      if (result == null) {
        result = config.getConfigOverride(type).getIsIgnoredType();
        if (result == null) {
          BeanDescription desc = config.introspectClassAnnotations(type);
          AnnotatedClass ac = desc.getClassInfo();
          result = intr.isIgnorableType(ac);
          if (result == null)
            result = Boolean.FALSE; 
        } 
        ignores.put(type, result);
      } 
      if (result.booleanValue())
        it.remove(); 
    } 
  }
  
  protected void removeSetterlessGetters(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyDefinition> properties) {
    properties.removeIf(property -> (!property.couldDeserialize() && !property.isExplicitlyIncluded()));
  }
  
  protected List<BeanPropertyWriter> removeOverlappingTypeIds(SerializerProvider prov, BeanDescription beanDesc, BeanSerializerBuilder builder, List<BeanPropertyWriter> props) {
    for (int i = 0, end = props.size(); i < end; i++) {
      BeanPropertyWriter bpw = props.get(i);
      TypeSerializer td = bpw.getTypeSerializer();
      if (td != null && td.getTypeInclusion() == JsonTypeInfo.As.EXTERNAL_PROPERTY) {
        String n = td.getPropertyName();
        PropertyName typePropName = PropertyName.construct(n);
        for (BeanPropertyWriter w2 : props) {
          if (w2 != bpw && w2.wouldConflictWithName(typePropName)) {
            bpw.assignTypeSerializer(null);
            break;
          } 
        } 
      } 
    } 
    return props;
  }
  
  protected BeanPropertyWriter _constructWriter(SerializerProvider prov, BeanPropertyDefinition propDef, PropertyBuilder pb, boolean staticTyping, AnnotatedMember accessor) throws JsonMappingException {
    PropertyName name = propDef.getFullName();
    JavaType type = accessor.getType();
    BeanProperty.Std property = new BeanProperty.Std(name, type, propDef.getWrapperName(), accessor, propDef.getMetadata());
    JsonSerializer<?> annotatedSerializer = findSerializerFromAnnotation(prov, (Annotated)accessor);
    if (annotatedSerializer instanceof ResolvableSerializer)
      ((ResolvableSerializer)annotatedSerializer).resolve(prov); 
    annotatedSerializer = prov.handlePrimaryContextualization(annotatedSerializer, (BeanProperty)property);
    TypeSerializer contentTypeSer = null;
    if (type.isContainerType() || type.isReferenceType())
      contentTypeSer = findPropertyContentTypeSerializer(type, prov.getConfig(), accessor); 
    TypeSerializer typeSer = findPropertyTypeSerializer(type, prov.getConfig(), accessor);
    return pb.buildWriter(prov, propDef, type, annotatedSerializer, typeSer, contentTypeSer, accessor, staticTyping);
  }
  
  protected JsonSerializer<?> _findUnsupportedTypeSerializer(SerializerProvider ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
    String errorMsg = BeanUtil.checkUnsupportedType(type);
    if (errorMsg != null)
      if (ctxt.getConfig().findMixInClassFor(type.getRawClass()) == null)
        return (JsonSerializer<?>)new UnsupportedTypeSerializer(type, errorMsg);  
    return null;
  }
  
  protected boolean _isUnserializableJacksonType(SerializerProvider ctxt, JavaType type) {
    Class<?> raw = type.getRawClass();
    return (ObjectMapper.class.isAssignableFrom(raw) || ObjectReader.class
      .isAssignableFrom(raw) || ObjectWriter.class
      .isAssignableFrom(raw) || DatabindContext.class
      .isAssignableFrom(raw) || TokenStreamFactory.class
      .isAssignableFrom(raw) || JsonParser.class
      .isAssignableFrom(raw) || JsonGenerator.class
      .isAssignableFrom(raw));
  }
}
