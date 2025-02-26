package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.impl.ErrorThrowingDeserializer;
import com.fasterxml.jackson.databind.deser.impl.FieldProperty;
import com.fasterxml.jackson.databind.deser.impl.MethodProperty;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.deser.impl.SetterlessProperty;
import com.fasterxml.jackson.databind.deser.impl.UnsupportedTypeDeserializer;
import com.fasterxml.jackson.databind.deser.std.ThrowableDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.SubTypeValidator;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.IgnorePropertiesUtil;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BeanDeserializerFactory extends BasicDeserializerFactory implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private static final Class<?>[] INIT_CAUSE_PARAMS = new Class[] { Throwable.class };
  
  public static final BeanDeserializerFactory instance = new BeanDeserializerFactory(new DeserializerFactoryConfig());
  
  public BeanDeserializerFactory(DeserializerFactoryConfig config) {
    super(config);
  }
  
  public DeserializerFactory withConfig(DeserializerFactoryConfig config) {
    if (this._factoryConfig == config)
      return this; 
    ClassUtil.verifyMustOverride(BeanDeserializerFactory.class, this, "withConfig");
    return new BeanDeserializerFactory(config);
  }
  
  public JsonDeserializer<Object> createBeanDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
    DeserializationConfig config = ctxt.getConfig();
    JsonDeserializer<?> deser = _findCustomBeanDeserializer(type, config, beanDesc);
    if (deser != null) {
      if (this._factoryConfig.hasDeserializerModifiers())
        for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers())
          deser = mod.modifyDeserializer(ctxt.getConfig(), beanDesc, deser);  
      return (JsonDeserializer)deser;
    } 
    if (type.isThrowable())
      return buildThrowableDeserializer(ctxt, type, beanDesc); 
    if (type.isAbstract() && !type.isPrimitive() && !type.isEnumType()) {
      JavaType concreteType = materializeAbstractType(ctxt, type, beanDesc);
      if (concreteType != null) {
        beanDesc = config.introspect(concreteType);
        return buildBeanDeserializer(ctxt, concreteType, beanDesc);
      } 
    } 
    deser = findStdDeserializer(ctxt, type, beanDesc);
    if (deser != null)
      return (JsonDeserializer)deser; 
    if (!isPotentialBeanType(type.getRawClass()))
      return null; 
    _validateSubType(ctxt, type, beanDesc);
    deser = _findUnsupportedTypeDeserializer(ctxt, type, beanDesc);
    if (deser != null)
      return (JsonDeserializer)deser; 
    return buildBeanDeserializer(ctxt, type, beanDesc);
  }
  
  public JsonDeserializer<Object> createBuilderBasedDeserializer(DeserializationContext ctxt, JavaType valueType, BeanDescription valueBeanDesc, Class<?> builderClass) throws JsonMappingException {
    JavaType builderType;
    if (ctxt.isEnabled(MapperFeature.INFER_BUILDER_TYPE_BINDINGS)) {
      builderType = ctxt.getTypeFactory().constructParametricType(builderClass, valueType.getBindings());
    } else {
      builderType = ctxt.constructType(builderClass);
    } 
    BeanDescription builderDesc = ctxt.getConfig().introspectForBuilder(builderType, valueBeanDesc);
    return buildBuilderBasedDeserializer(ctxt, valueType, builderDesc);
  }
  
  protected JsonDeserializer<?> findStdDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
    JsonDeserializer<?> deser = findDefaultDeserializer(ctxt, type, beanDesc);
    if (deser != null && 
      this._factoryConfig.hasDeserializerModifiers())
      for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers())
        deser = mod.modifyDeserializer(ctxt.getConfig(), beanDesc, deser);  
    return deser;
  }
  
  protected JsonDeserializer<Object> _findUnsupportedTypeDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
    String errorMsg = BeanUtil.checkUnsupportedType(type);
    if (errorMsg != null)
      if (ctxt.getConfig().findMixInClassFor(type.getRawClass()) == null)
        return (JsonDeserializer<Object>)new UnsupportedTypeDeserializer(type, errorMsg);  
    return null;
  }
  
  protected JavaType materializeAbstractType(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
    for (AbstractTypeResolver r : this._factoryConfig.abstractTypeResolvers()) {
      JavaType concrete = r.resolveAbstractType(ctxt.getConfig(), beanDesc);
      if (concrete != null)
        return concrete; 
    } 
    return null;
  }
  
  public JsonDeserializer<Object> buildBeanDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
    ValueInstantiator valueInstantiator;
    JsonDeserializer<?> deserializer;
    try {
      valueInstantiator = findValueInstantiator(ctxt, beanDesc);
    } catch (NoClassDefFoundError error) {
      return (JsonDeserializer<Object>)new ErrorThrowingDeserializer(error);
    } catch (IllegalArgumentException e0) {
      throw InvalidDefinitionException.from(ctxt.getParser(), 
          ClassUtil.exceptionMessage(e0), beanDesc, null)
        
        .withCause(e0);
    } 
    BeanDeserializerBuilder builder = constructBeanDeserializerBuilder(ctxt, beanDesc);
    builder.setValueInstantiator(valueInstantiator);
    addBeanProps(ctxt, beanDesc, builder);
    addObjectIdReader(ctxt, beanDesc, builder);
    addBackReferenceProperties(ctxt, beanDesc, builder);
    addInjectables(ctxt, beanDesc, builder);
    DeserializationConfig config = ctxt.getConfig();
    if (this._factoryConfig.hasDeserializerModifiers())
      for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers())
        builder = mod.updateBuilder(config, beanDesc, builder);  
    if (type.isAbstract() && !valueInstantiator.canInstantiate()) {
      deserializer = builder.buildAbstract();
    } else {
      deserializer = builder.build();
    } 
    if (this._factoryConfig.hasDeserializerModifiers())
      for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers())
        deserializer = mod.modifyDeserializer(config, beanDesc, deserializer);  
    return (JsonDeserializer)deserializer;
  }
  
  protected JsonDeserializer<Object> buildBuilderBasedDeserializer(DeserializationContext ctxt, JavaType valueType, BeanDescription builderDesc) throws JsonMappingException {
    ValueInstantiator valueInstantiator;
    try {
      valueInstantiator = findValueInstantiator(ctxt, builderDesc);
    } catch (NoClassDefFoundError error) {
      return (JsonDeserializer<Object>)new ErrorThrowingDeserializer(error);
    } catch (IllegalArgumentException e) {
      throw InvalidDefinitionException.from(ctxt.getParser(), 
          ClassUtil.exceptionMessage(e), builderDesc, null);
    } 
    DeserializationConfig config = ctxt.getConfig();
    BeanDeserializerBuilder builder = constructBeanDeserializerBuilder(ctxt, builderDesc);
    builder.setValueInstantiator(valueInstantiator);
    addBeanProps(ctxt, builderDesc, builder);
    addObjectIdReader(ctxt, builderDesc, builder);
    addBackReferenceProperties(ctxt, builderDesc, builder);
    addInjectables(ctxt, builderDesc, builder);
    JsonPOJOBuilder.Value builderConfig = builderDesc.findPOJOBuilderConfig();
    String buildMethodName = (builderConfig == null) ? "build" : builderConfig.buildMethodName;
    AnnotatedMethod buildMethod = builderDesc.findMethod(buildMethodName, null);
    if (buildMethod != null && 
      config.canOverrideAccessModifiers())
      ClassUtil.checkAndFixAccess(buildMethod.getMember(), config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS)); 
    builder.setPOJOBuilder(buildMethod, builderConfig);
    if (this._factoryConfig.hasDeserializerModifiers())
      for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers())
        builder = mod.updateBuilder(config, builderDesc, builder);  
    JsonDeserializer<?> deserializer = builder.buildBuilderBased(valueType, buildMethodName);
    if (this._factoryConfig.hasDeserializerModifiers())
      for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers())
        deserializer = mod.modifyDeserializer(config, builderDesc, deserializer);  
    return (JsonDeserializer)deserializer;
  }
  
  protected void addObjectIdReader(DeserializationContext ctxt, BeanDescription beanDesc, BeanDeserializerBuilder builder) throws JsonMappingException {
    JavaType idType;
    SettableBeanProperty idProp;
    ObjectIdGenerator<?> gen;
    ObjectIdInfo objectIdInfo = beanDesc.getObjectIdInfo();
    if (objectIdInfo == null)
      return; 
    Class<?> implClass = objectIdInfo.getGeneratorType();
    ObjectIdResolver resolver = ctxt.objectIdResolverInstance((Annotated)beanDesc.getClassInfo(), objectIdInfo);
    if (implClass == ObjectIdGenerators.PropertyGenerator.class) {
      PropertyName propName = objectIdInfo.getPropertyName();
      idProp = builder.findProperty(propName);
      if (idProp == null)
        throw new IllegalArgumentException(String.format("Invalid Object Id definition for %s: cannot find property with name %s", new Object[] { ClassUtil.getTypeDescription(beanDesc.getType()), 
                ClassUtil.name(propName) })); 
      idType = idProp.getType();
      PropertyBasedObjectIdGenerator propertyBasedObjectIdGenerator = new PropertyBasedObjectIdGenerator(objectIdInfo.getScope());
    } else {
      JavaType type = ctxt.constructType(implClass);
      idType = ctxt.getTypeFactory().findTypeParameters(type, ObjectIdGenerator.class)[0];
      idProp = null;
      gen = ctxt.objectIdGeneratorInstance((Annotated)beanDesc.getClassInfo(), objectIdInfo);
    } 
    JsonDeserializer<?> deser = ctxt.findRootValueDeserializer(idType);
    builder.setObjectIdReader(ObjectIdReader.construct(idType, objectIdInfo
          .getPropertyName(), gen, deser, idProp, resolver));
  }
  
  public JsonDeserializer<Object> buildThrowableDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
    ThrowableDeserializer throwableDeserializer;
    JsonDeserializer<?> jsonDeserializer1;
    DeserializationConfig config = ctxt.getConfig();
    BeanDeserializerBuilder builder = constructBeanDeserializerBuilder(ctxt, beanDesc);
    builder.setValueInstantiator(findValueInstantiator(ctxt, beanDesc));
    addBeanProps(ctxt, beanDesc, builder);
    Iterator<SettableBeanProperty> it = builder.getProperties();
    while (it.hasNext()) {
      SettableBeanProperty prop = it.next();
      if ("setCause".equals(prop.getMember().getName())) {
        it.remove();
        break;
      } 
    } 
    AnnotatedMethod am = beanDesc.findMethod("initCause", INIT_CAUSE_PARAMS);
    if (am != null) {
      String name = "cause";
      PropertyNamingStrategy pts = config.getPropertyNamingStrategy();
      if (pts != null)
        name = pts.nameForSetterMethod((MapperConfig)config, am, "cause"); 
      SimpleBeanPropertyDefinition propDef = SimpleBeanPropertyDefinition.construct((MapperConfig)ctxt.getConfig(), (AnnotatedMember)am, new PropertyName(name));
      SettableBeanProperty prop = constructSettableProperty(ctxt, beanDesc, (BeanPropertyDefinition)propDef, am
          .getParameterType(0));
      if (prop != null)
        builder.addOrReplaceProperty(prop, true); 
    } 
    if (this._factoryConfig.hasDeserializerModifiers())
      for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers())
        builder = mod.updateBuilder(config, beanDesc, builder);  
    JsonDeserializer<?> deserializer = builder.build();
    if (deserializer instanceof BeanDeserializer)
      throwableDeserializer = ThrowableDeserializer.construct(ctxt, (BeanDeserializer)deserializer); 
    if (this._factoryConfig.hasDeserializerModifiers())
      for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers())
        jsonDeserializer1 = mod.modifyDeserializer(config, beanDesc, (JsonDeserializer<?>)throwableDeserializer);  
    return (JsonDeserializer)jsonDeserializer1;
  }
  
  protected BeanDeserializerBuilder constructBeanDeserializerBuilder(DeserializationContext ctxt, BeanDescription beanDesc) {
    return new BeanDeserializerBuilder(beanDesc, ctxt);
  }
  
  protected void addBeanProps(DeserializationContext ctxt, BeanDescription beanDesc, BeanDeserializerBuilder builder) throws JsonMappingException {
    Set<String> ignored;
    boolean isConcrete = !beanDesc.getType().isAbstract();
    SettableBeanProperty[] creatorProps = isConcrete ? builder.getValueInstantiator().getFromObjectArguments(ctxt.getConfig()) : null;
    boolean hasCreatorProps = (creatorProps != null);
    JsonIgnoreProperties.Value ignorals = ctxt.getConfig().getDefaultPropertyIgnorals(beanDesc.getBeanClass(), beanDesc
        .getClassInfo());
    if (ignorals != null) {
      boolean ignoreAny = ignorals.getIgnoreUnknown();
      builder.setIgnoreUnknownProperties(ignoreAny);
      ignored = ignorals.findIgnoredForDeserialization();
      for (String propName : ignored)
        builder.addIgnorable(propName); 
    } else {
      ignored = Collections.emptySet();
    } 
    JsonIncludeProperties.Value inclusions = ctxt.getConfig().getDefaultPropertyInclusions(beanDesc.getBeanClass(), beanDesc
        .getClassInfo());
    Set<String> included = null;
    if (inclusions != null) {
      included = inclusions.getIncluded();
      if (included != null)
        for (String propName : included)
          builder.addIncludable(propName);  
    } 
    AnnotatedMember anySetter = beanDesc.findAnySetterAccessor();
    if (anySetter != null) {
      builder.setAnySetter(constructAnySetter(ctxt, beanDesc, anySetter));
    } else {
      Collection<String> ignored2 = beanDesc.getIgnoredPropertyNames();
      if (ignored2 != null)
        for (String propName : ignored2)
          builder.addIgnorable(propName);  
    } 
    boolean useGettersAsSetters = (ctxt.isEnabled(MapperFeature.USE_GETTERS_AS_SETTERS) && ctxt.isEnabled(MapperFeature.AUTO_DETECT_GETTERS));
    List<BeanPropertyDefinition> propDefs = filterBeanProps(ctxt, beanDesc, builder, beanDesc
        .findProperties(), ignored, included);
    if (this._factoryConfig.hasDeserializerModifiers())
      for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers())
        propDefs = mod.updateProperties(ctxt.getConfig(), beanDesc, propDefs);  
    for (BeanPropertyDefinition propDef : propDefs) {
      SettableBeanProperty prop = null;
      if (propDef.hasSetter()) {
        AnnotatedMethod setter = propDef.getSetter();
        JavaType propertyType = setter.getParameterType(0);
        prop = constructSettableProperty(ctxt, beanDesc, propDef, propertyType);
      } else if (propDef.hasField()) {
        AnnotatedField field = propDef.getField();
        JavaType propertyType = field.getType();
        prop = constructSettableProperty(ctxt, beanDesc, propDef, propertyType);
      } else {
        AnnotatedMethod getter = propDef.getGetter();
        if (getter != null)
          if (useGettersAsSetters && _isSetterlessType(getter.getRawType())) {
            if (!builder.hasIgnorable(propDef.getName()))
              prop = constructSetterlessProperty(ctxt, beanDesc, propDef); 
          } else if (!propDef.hasConstructorParameter()) {
            PropertyMetadata md = propDef.getMetadata();
            if (md.getMergeInfo() != null)
              prop = constructSetterlessProperty(ctxt, beanDesc, propDef); 
          }  
      } 
      if (hasCreatorProps && propDef.hasConstructorParameter()) {
        String name = propDef.getName();
        CreatorProperty cprop = null;
        for (SettableBeanProperty cp : creatorProps) {
          if (name.equals(cp.getName()) && cp instanceof CreatorProperty) {
            cprop = (CreatorProperty)cp;
            break;
          } 
        } 
        if (cprop == null) {
          List<String> n = new ArrayList<>();
          for (SettableBeanProperty cp : creatorProps)
            n.add(cp.getName()); 
          ctxt.reportBadPropertyDefinition(beanDesc, propDef, "Could not find creator property with name %s (known Creator properties: %s)", new Object[] { ClassUtil.name(name), n });
          continue;
        } 
        if (prop != null)
          cprop.setFallbackSetter(prop); 
        Class<?>[] views = propDef.findViews();
        if (views == null)
          views = beanDesc.findDefaultViews(); 
        cprop.setViews(views);
        builder.addCreatorProperty(cprop);
        continue;
      } 
      if (prop != null) {
        Class<?>[] views = propDef.findViews();
        if (views == null)
          views = beanDesc.findDefaultViews(); 
        prop.setViews(views);
        builder.addProperty(prop);
      } 
    } 
  }
  
  private boolean _isSetterlessType(Class<?> rawType) {
    return (Collection.class.isAssignableFrom(rawType) || Map.class
      .isAssignableFrom(rawType));
  }
  
  @Deprecated
  protected List<BeanPropertyDefinition> filterBeanProps(DeserializationContext ctxt, BeanDescription beanDesc, BeanDeserializerBuilder builder, List<BeanPropertyDefinition> propDefsIn, Set<String> ignored) throws JsonMappingException {
    return filterBeanProps(ctxt, beanDesc, builder, propDefsIn, ignored, (Set<String>)null);
  }
  
  protected List<BeanPropertyDefinition> filterBeanProps(DeserializationContext ctxt, BeanDescription beanDesc, BeanDeserializerBuilder builder, List<BeanPropertyDefinition> propDefsIn, Set<String> ignored, Set<String> included) {
    ArrayList<BeanPropertyDefinition> result = new ArrayList<>(Math.max(4, propDefsIn.size()));
    HashMap<Class<?>, Boolean> ignoredTypes = new HashMap<>();
    for (BeanPropertyDefinition property : propDefsIn) {
      String name = property.getName();
      if (IgnorePropertiesUtil.shouldIgnore(name, ignored, included))
        continue; 
      if (!property.hasConstructorParameter()) {
        Class<?> rawPropertyType = property.getRawPrimaryType();
        if (rawPropertyType != null && 
          isIgnorableType(ctxt.getConfig(), property, rawPropertyType, ignoredTypes)) {
          builder.addIgnorable(name);
          continue;
        } 
      } 
      result.add(property);
    } 
    return result;
  }
  
  protected void addBackReferenceProperties(DeserializationContext ctxt, BeanDescription beanDesc, BeanDeserializerBuilder builder) throws JsonMappingException {
    List<BeanPropertyDefinition> refProps = beanDesc.findBackReferences();
    if (refProps != null)
      for (BeanPropertyDefinition refProp : refProps) {
        String refName = refProp.findReferenceName();
        builder.addBackReferenceProperty(refName, constructSettableProperty(ctxt, beanDesc, refProp, refProp
              .getPrimaryType()));
      }  
  }
  
  @Deprecated
  protected void addReferenceProperties(DeserializationContext ctxt, BeanDescription beanDesc, BeanDeserializerBuilder builder) throws JsonMappingException {
    addBackReferenceProperties(ctxt, beanDesc, builder);
  }
  
  protected void addInjectables(DeserializationContext ctxt, BeanDescription beanDesc, BeanDeserializerBuilder builder) throws JsonMappingException {
    Map<Object, AnnotatedMember> raw = beanDesc.findInjectables();
    if (raw != null)
      for (Map.Entry<Object, AnnotatedMember> entry : raw.entrySet()) {
        AnnotatedMember m = entry.getValue();
        builder.addInjectable(PropertyName.construct(m.getName()), m
            .getType(), beanDesc
            .getClassAnnotations(), m, entry.getKey());
      }  
  }
  
  protected SettableAnyProperty constructAnySetter(DeserializationContext ctxt, BeanDescription beanDesc, AnnotatedMember mutator) throws JsonMappingException {
    BeanProperty.Std std;
    JavaType keyType, valueType;
    boolean isField = mutator instanceof AnnotatedField;
    if (mutator instanceof AnnotatedMethod) {
      AnnotatedMethod am = (AnnotatedMethod)mutator;
      keyType = am.getParameterType(0);
      valueType = am.getParameterType(1);
      valueType = resolveMemberAndTypeAnnotations(ctxt, mutator, valueType);
      std = new BeanProperty.Std(PropertyName.construct(mutator.getName()), valueType, null, mutator, PropertyMetadata.STD_OPTIONAL);
    } else if (isField) {
      AnnotatedField af = (AnnotatedField)mutator;
      JavaType fieldType = af.getType();
      if (fieldType.isMapLikeType()) {
        fieldType = resolveMemberAndTypeAnnotations(ctxt, mutator, fieldType);
        keyType = fieldType.getKeyType();
        valueType = fieldType.getContentType();
        std = new BeanProperty.Std(PropertyName.construct(mutator.getName()), fieldType, null, mutator, PropertyMetadata.STD_OPTIONAL);
      } else {
        if (fieldType.hasRawClass(JsonNode.class) || fieldType
          .hasRawClass(ObjectNode.class)) {
          fieldType = resolveMemberAndTypeAnnotations(ctxt, mutator, fieldType);
          valueType = ctxt.constructType(JsonNode.class);
          std = new BeanProperty.Std(PropertyName.construct(mutator.getName()), fieldType, null, mutator, PropertyMetadata.STD_OPTIONAL);
          return SettableAnyProperty.constructForJsonNodeField(ctxt, (BeanProperty)std, mutator, valueType, ctxt
              
              .findRootValueDeserializer(valueType));
        } 
        return (SettableAnyProperty)ctxt.reportBadDefinition(beanDesc.getType(), String.format("Unsupported type for any-setter: %s -- only support `Map`s, `JsonNode` and `ObjectNode` ", new Object[] { ClassUtil.getTypeDescription(fieldType) }));
      } 
    } else {
      return (SettableAnyProperty)ctxt.reportBadDefinition(beanDesc.getType(), String.format("Unrecognized mutator type for any-setter: %s", new Object[] { ClassUtil.nameOf(mutator.getClass()) }));
    } 
    KeyDeserializer keyDeser = findKeyDeserializerFromAnnotation(ctxt, (Annotated)mutator);
    if (keyDeser == null)
      keyDeser = (KeyDeserializer)keyType.getValueHandler(); 
    if (keyDeser == null) {
      keyDeser = ctxt.findKeyDeserializer(keyType, (BeanProperty)std);
    } else if (keyDeser instanceof ContextualKeyDeserializer) {
      keyDeser = ((ContextualKeyDeserializer)keyDeser).createContextual(ctxt, (BeanProperty)std);
    } 
    JsonDeserializer<Object> deser = findContentDeserializerFromAnnotation(ctxt, (Annotated)mutator);
    if (deser == null)
      deser = (JsonDeserializer<Object>)valueType.getValueHandler(); 
    if (deser != null)
      deser = ctxt.handlePrimaryContextualization(deser, (BeanProperty)std, valueType); 
    TypeDeserializer typeDeser = (TypeDeserializer)valueType.getTypeHandler();
    if (isField)
      return SettableAnyProperty.constructForMapField(ctxt, (BeanProperty)std, mutator, valueType, keyDeser, deser, typeDeser); 
    return SettableAnyProperty.constructForMethod(ctxt, (BeanProperty)std, mutator, valueType, keyDeser, deser, typeDeser);
  }
  
  protected SettableBeanProperty constructSettableProperty(DeserializationContext ctxt, BeanDescription beanDesc, BeanPropertyDefinition propDef, JavaType propType0) throws JsonMappingException {
    FieldProperty fieldProperty;
    SettableBeanProperty settableBeanProperty;
    AnnotatedMember mutator = propDef.getNonConstructorMutator();
    if (mutator == null)
      ctxt.reportBadPropertyDefinition(beanDesc, propDef, "No non-constructor mutator available", new Object[0]); 
    JavaType type = resolveMemberAndTypeAnnotations(ctxt, mutator, propType0);
    TypeDeserializer typeDeser = (TypeDeserializer)type.getTypeHandler();
    if (mutator instanceof AnnotatedMethod) {
      MethodProperty methodProperty = new MethodProperty(propDef, type, typeDeser, beanDesc.getClassAnnotations(), (AnnotatedMethod)mutator);
    } else {
      fieldProperty = new FieldProperty(propDef, type, typeDeser, beanDesc.getClassAnnotations(), (AnnotatedField)mutator);
    } 
    JsonDeserializer<?> deser = findDeserializerFromAnnotation(ctxt, (Annotated)mutator);
    if (deser == null)
      deser = (JsonDeserializer)type.getValueHandler(); 
    if (deser != null) {
      deser = ctxt.handlePrimaryContextualization(deser, (BeanProperty)fieldProperty, type);
      settableBeanProperty = fieldProperty.withValueDeserializer(deser);
    } 
    AnnotationIntrospector.ReferenceProperty ref = propDef.findReferenceType();
    if (ref != null && ref.isManagedReference())
      settableBeanProperty.setManagedReferenceName(ref.getName()); 
    ObjectIdInfo objectIdInfo = propDef.findObjectIdInfo();
    if (objectIdInfo != null)
      settableBeanProperty.setObjectIdInfo(objectIdInfo); 
    return settableBeanProperty;
  }
  
  protected SettableBeanProperty constructSetterlessProperty(DeserializationContext ctxt, BeanDescription beanDesc, BeanPropertyDefinition propDef) throws JsonMappingException {
    SettableBeanProperty settableBeanProperty;
    AnnotatedMethod getter = propDef.getGetter();
    JavaType type = resolveMemberAndTypeAnnotations(ctxt, (AnnotatedMember)getter, getter.getType());
    TypeDeserializer typeDeser = (TypeDeserializer)type.getTypeHandler();
    SetterlessProperty setterlessProperty = new SetterlessProperty(propDef, type, typeDeser, beanDesc.getClassAnnotations(), getter);
    JsonDeserializer<?> deser = findDeserializerFromAnnotation(ctxt, (Annotated)getter);
    if (deser == null)
      deser = (JsonDeserializer)type.getValueHandler(); 
    if (deser != null) {
      deser = ctxt.handlePrimaryContextualization(deser, (BeanProperty)setterlessProperty, type);
      settableBeanProperty = setterlessProperty.withValueDeserializer(deser);
    } 
    return settableBeanProperty;
  }
  
  protected boolean isPotentialBeanType(Class<?> type) {
    String typeStr = ClassUtil.canBeABeanType(type);
    if (typeStr != null)
      throw new IllegalArgumentException("Cannot deserialize Class " + type.getName() + " (of type " + typeStr + ") as a Bean"); 
    if (ClassUtil.isProxyType(type))
      throw new IllegalArgumentException("Cannot deserialize Proxy class " + type.getName() + " as a Bean"); 
    typeStr = ClassUtil.isLocalType(type, true);
    if (typeStr != null)
      throw new IllegalArgumentException("Cannot deserialize Class " + type.getName() + " (of type " + typeStr + ") as a Bean"); 
    return true;
  }
  
  protected boolean isIgnorableType(DeserializationConfig config, BeanPropertyDefinition propDef, Class<?> type, Map<Class<?>, Boolean> ignoredTypes) {
    Boolean status = ignoredTypes.get(type);
    if (status != null)
      return status.booleanValue(); 
    if (type == String.class || type.isPrimitive()) {
      status = Boolean.FALSE;
    } else {
      status = config.getConfigOverride(type).getIsIgnoredType();
      if (status == null) {
        BeanDescription desc = config.introspectClassAnnotations(type);
        status = config.getAnnotationIntrospector().isIgnorableType(desc.getClassInfo());
        if (status == null)
          status = Boolean.FALSE; 
      } 
    } 
    ignoredTypes.put(type, status);
    return status.booleanValue();
  }
  
  protected void _validateSubType(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
    SubTypeValidator.instance().validateSubType(ctxt, type, beanDesc);
  }
}
