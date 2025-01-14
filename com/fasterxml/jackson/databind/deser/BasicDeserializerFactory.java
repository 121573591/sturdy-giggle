package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.EnumNamingStrategy;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.ConfigOverride;
import com.fasterxml.jackson.databind.cfg.ConstructorDetector;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate;
import com.fasterxml.jackson.databind.deser.impl.CreatorCollector;
import com.fasterxml.jackson.databind.deser.impl.JDKValueInstantiators;
import com.fasterxml.jackson.databind.deser.impl.JavaUtilCollectionsDeserializers;
import com.fasterxml.jackson.databind.deser.std.ArrayBlockingQueueDeserializer;
import com.fasterxml.jackson.databind.deser.std.AtomicReferenceDeserializer;
import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumSetDeserializer;
import com.fasterxml.jackson.databind.deser.std.JdkDeserializers;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.fasterxml.jackson.databind.deser.std.MapDeserializer;
import com.fasterxml.jackson.databind.deser.std.MapEntryDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializers;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.deser.std.TokenBufferDeserializer;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.ext.OptionalHandlerFactory;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.EnumNamingStrategyFactory;
import com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicReference;

public abstract class BasicDeserializerFactory extends DeserializerFactory implements Serializable {
  private static final Class<?> CLASS_OBJECT = Object.class;
  
  private static final Class<?> CLASS_STRING = String.class;
  
  private static final Class<?> CLASS_CHAR_SEQUENCE = CharSequence.class;
  
  private static final Class<?> CLASS_ITERABLE = Iterable.class;
  
  private static final Class<?> CLASS_MAP_ENTRY = Map.Entry.class;
  
  private static final Class<?> CLASS_SERIALIZABLE = Serializable.class;
  
  protected static final PropertyName UNWRAPPED_CREATOR_PARAM_NAME = new PropertyName("@JsonUnwrapped");
  
  protected final DeserializerFactoryConfig _factoryConfig;
  
  protected BasicDeserializerFactory(DeserializerFactoryConfig config) {
    this._factoryConfig = config;
  }
  
  public DeserializerFactoryConfig getFactoryConfig() {
    return this._factoryConfig;
  }
  
  protected abstract DeserializerFactory withConfig(DeserializerFactoryConfig paramDeserializerFactoryConfig);
  
  public final DeserializerFactory withAdditionalDeserializers(Deserializers additional) {
    return withConfig(this._factoryConfig.withAdditionalDeserializers(additional));
  }
  
  public final DeserializerFactory withAdditionalKeyDeserializers(KeyDeserializers additional) {
    return withConfig(this._factoryConfig.withAdditionalKeyDeserializers(additional));
  }
  
  public final DeserializerFactory withDeserializerModifier(BeanDeserializerModifier modifier) {
    return withConfig(this._factoryConfig.withDeserializerModifier(modifier));
  }
  
  public final DeserializerFactory withAbstractTypeResolver(AbstractTypeResolver resolver) {
    return withConfig(this._factoryConfig.withAbstractTypeResolver(resolver));
  }
  
  public final DeserializerFactory withValueInstantiators(ValueInstantiators instantiators) {
    return withConfig(this._factoryConfig.withValueInstantiators(instantiators));
  }
  
  public JavaType mapAbstractType(DeserializationConfig config, JavaType type) throws JsonMappingException {
    while (true) {
      JavaType next = _mapAbstractType2(config, type);
      if (next == null)
        return type; 
      Class<?> prevCls = type.getRawClass();
      Class<?> nextCls = next.getRawClass();
      if (prevCls == nextCls || !prevCls.isAssignableFrom(nextCls))
        throw new IllegalArgumentException("Invalid abstract type resolution from " + type + " to " + next + ": latter is not a subtype of former"); 
      type = next;
    } 
  }
  
  private JavaType _mapAbstractType2(DeserializationConfig config, JavaType type) throws JsonMappingException {
    Class<?> currClass = type.getRawClass();
    if (this._factoryConfig.hasAbstractTypeResolvers())
      for (AbstractTypeResolver resolver : this._factoryConfig.abstractTypeResolvers()) {
        JavaType concrete = resolver.findTypeMapping(config, type);
        if (concrete != null && !concrete.hasRawClass(currClass))
          return concrete; 
      }  
    return null;
  }
  
  public ValueInstantiator findValueInstantiator(DeserializationContext ctxt, BeanDescription beanDesc) throws JsonMappingException {
    DeserializationConfig config = ctxt.getConfig();
    ValueInstantiator instantiator = null;
    AnnotatedClass ac = beanDesc.getClassInfo();
    Object instDef = ctxt.getAnnotationIntrospector().findValueInstantiator(ac);
    if (instDef != null)
      instantiator = _valueInstantiatorInstance(config, (Annotated)ac, instDef); 
    if (instantiator == null) {
      instantiator = JDKValueInstantiators.findStdValueInstantiator(config, beanDesc.getBeanClass());
      if (instantiator == null)
        instantiator = _constructDefaultValueInstantiator(ctxt, beanDesc); 
    } 
    if (this._factoryConfig.hasValueInstantiators())
      for (ValueInstantiators insts : this._factoryConfig.valueInstantiators()) {
        instantiator = insts.findValueInstantiator(config, beanDesc, instantiator);
        if (instantiator == null)
          ctxt.reportBadTypeDefinition(beanDesc, "Broken registered ValueInstantiators (of type %s): returned null ValueInstantiator", new Object[] { insts
                
                .getClass().getName() }); 
      }  
    if (instantiator != null)
      instantiator = instantiator.createContextual(ctxt, beanDesc); 
    return instantiator;
  }
  
  protected ValueInstantiator _constructDefaultValueInstantiator(DeserializationContext ctxt, BeanDescription beanDesc) throws JsonMappingException {
    DeserializationConfig config = ctxt.getConfig();
    VisibilityChecker<?> vchecker = config.getDefaultVisibilityChecker(beanDesc.getBeanClass(), beanDesc
        .getClassInfo());
    ConstructorDetector ctorDetector = config.getConstructorDetector();
    CreatorCollector creators = new CreatorCollector(beanDesc, (MapperConfig)config);
    Map<AnnotatedWithParams, BeanPropertyDefinition[]> creatorDefs = _findCreatorsFromProperties(ctxt, beanDesc);
    CreatorCollectionState ccState = new CreatorCollectionState(ctxt, beanDesc, vchecker, creators, creatorDefs);
    _addExplicitFactoryCreators(ctxt, ccState, !ctorDetector.requireCtorAnnotation());
    if (beanDesc.getType().isConcrete()) {
      boolean isNonStaticInnerClass = beanDesc.isNonStaticInnerClass();
      if (!isNonStaticInnerClass) {
        boolean findImplicit = ctorDetector.shouldIntrospectorImplicitConstructors(beanDesc.getBeanClass());
        _addExplicitConstructorCreators(ctxt, ccState, findImplicit);
        if (ccState.hasImplicitConstructorCandidates() && 
          
          !ccState.hasExplicitConstructors())
          _addImplicitConstructorCreators(ctxt, ccState, ccState.implicitConstructorCandidates()); 
      } 
    } 
    if (ccState.hasImplicitFactoryCandidates() && 
      !ccState.hasExplicitFactories() && !ccState.hasExplicitConstructors())
      _addImplicitFactoryCreators(ctxt, ccState, ccState.implicitFactoryCandidates()); 
    return ccState.creators.constructValueInstantiator(ctxt);
  }
  
  protected Map<AnnotatedWithParams, BeanPropertyDefinition[]> _findCreatorsFromProperties(DeserializationContext ctxt, BeanDescription beanDesc) throws JsonMappingException {
    Map<AnnotatedWithParams, BeanPropertyDefinition[]> result = (Map)Collections.emptyMap();
    for (BeanPropertyDefinition propDef : beanDesc.findProperties()) {
      Iterator<AnnotatedParameter> it = propDef.getConstructorParameters();
      while (it.hasNext()) {
        AnnotatedParameter param = it.next();
        AnnotatedWithParams owner = param.getOwner();
        BeanPropertyDefinition[] defs = result.get(owner);
        int index = param.getIndex();
        if (defs == null) {
          if (result.isEmpty())
            result = (Map)new LinkedHashMap<>(); 
          defs = new BeanPropertyDefinition[owner.getParameterCount()];
          result.put(owner, defs);
        } else if (defs[index] != null) {
          ctxt.reportBadTypeDefinition(beanDesc, "Conflict: parameter #%d of %s bound to more than one property; %s vs %s", new Object[] { Integer.valueOf(index), owner, defs[index], propDef });
        } 
        defs[index] = propDef;
      } 
    } 
    return result;
  }
  
  public ValueInstantiator _valueInstantiatorInstance(DeserializationConfig config, Annotated annotated, Object instDef) throws JsonMappingException {
    if (instDef == null)
      return null; 
    if (instDef instanceof ValueInstantiator)
      return (ValueInstantiator)instDef; 
    if (!(instDef instanceof Class))
      throw new IllegalStateException("AnnotationIntrospector returned key deserializer definition of type " + instDef
          .getClass().getName() + "; expected type KeyDeserializer or Class<KeyDeserializer> instead"); 
    Class<?> instClass = (Class)instDef;
    if (ClassUtil.isBogusClass(instClass))
      return null; 
    if (!ValueInstantiator.class.isAssignableFrom(instClass))
      throw new IllegalStateException("AnnotationIntrospector returned Class " + instClass.getName() + "; expected Class<ValueInstantiator>"); 
    HandlerInstantiator hi = config.getHandlerInstantiator();
    if (hi != null) {
      ValueInstantiator inst = hi.valueInstantiatorInstance((MapperConfig)config, annotated, instClass);
      if (inst != null)
        return inst; 
    } 
    return (ValueInstantiator)ClassUtil.createInstance(instClass, config
        .canOverrideAccessModifiers());
  }
  
  @Deprecated
  protected void _addRecordConstructor(DeserializationContext ctxt, CreatorCollectionState ccState, AnnotatedConstructor canonical, List<String> implicitNames) throws JsonMappingException {
    int argCount = canonical.getParameterCount();
    AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
    SettableBeanProperty[] properties = new SettableBeanProperty[argCount];
    for (int i = 0; i < argCount; i++) {
      AnnotatedParameter param = canonical.getParameter(i);
      JacksonInject.Value injectable = intr.findInjectableValue((AnnotatedMember)param);
      PropertyName name = intr.findNameForDeserialization((Annotated)param);
      if (name == null || name.isEmpty())
        name = PropertyName.construct(implicitNames.get(i)); 
      properties[i] = constructCreatorProperty(ctxt, ccState.beanDesc, name, i, param, injectable);
    } 
    ccState.creators.addPropertyCreator((AnnotatedWithParams)canonical, false, properties);
  }
  
  protected void _addExplicitConstructorCreators(DeserializationContext ctxt, CreatorCollectionState ccState, boolean findImplicit) throws JsonMappingException {
    BeanDescription beanDesc = ccState.beanDesc;
    CreatorCollector creators = ccState.creators;
    AnnotationIntrospector intr = ccState.annotationIntrospector();
    VisibilityChecker<?> vchecker = ccState.vchecker;
    Map<AnnotatedWithParams, BeanPropertyDefinition[]> creatorParams = ccState.creatorParams;
    AnnotatedConstructor defaultCtor = beanDesc.findDefaultConstructor();
    if (defaultCtor != null && (
      !creators.hasDefaultCreator() || _hasCreatorAnnotation(ctxt, (Annotated)defaultCtor)))
      creators.setDefaultCreator((AnnotatedWithParams)defaultCtor); 
    for (AnnotatedConstructor ctor : beanDesc.getConstructors()) {
      JsonCreator.Mode creatorMode = intr.findCreatorAnnotation((MapperConfig)ctxt.getConfig(), (Annotated)ctor);
      if (JsonCreator.Mode.DISABLED == creatorMode)
        continue; 
      if (creatorMode == null) {
        if (findImplicit && vchecker.isCreatorVisible((AnnotatedMember)ctor))
          ccState.addImplicitConstructorCandidate(CreatorCandidate.construct(intr, (AnnotatedWithParams)ctor, creatorParams
                .get(ctor))); 
        continue;
      } 
      switch (creatorMode) {
        case DELEGATING:
          _addExplicitDelegatingCreator(ctxt, beanDesc, creators, 
              CreatorCandidate.construct(intr, (AnnotatedWithParams)ctor, null));
          break;
        case PROPERTIES:
          _addExplicitPropertyCreator(ctxt, beanDesc, creators, 
              CreatorCandidate.construct(intr, (AnnotatedWithParams)ctor, creatorParams.get(ctor)));
          break;
        default:
          _addExplicitAnyCreator(ctxt, beanDesc, creators, 
              CreatorCandidate.construct(intr, (AnnotatedWithParams)ctor, creatorParams.get(ctor)), ctxt
              .getConfig().getConstructorDetector());
          break;
      } 
      ccState.increaseExplicitConstructorCount();
    } 
  }
  
  protected void _addImplicitConstructorCreators(DeserializationContext ctxt, CreatorCollectionState ccState, List<CreatorCandidate> ctorCandidates) throws JsonMappingException {
    DeserializationConfig config = ctxt.getConfig();
    BeanDescription beanDesc = ccState.beanDesc;
    CreatorCollector creators = ccState.creators;
    AnnotationIntrospector intr = ccState.annotationIntrospector();
    VisibilityChecker<?> vchecker = ccState.vchecker;
    List<AnnotatedWithParams> implicitCtors = null;
    boolean preferPropsBased = (config.getConstructorDetector().singleArgCreatorDefaultsToProperties() && !beanDesc.isRecordType());
    for (CreatorCandidate candidate : ctorCandidates) {
      int argCount = candidate.paramCount();
      AnnotatedWithParams ctor = candidate.creator();
      if (argCount == 1) {
        BeanPropertyDefinition propDef = candidate.propertyDef(0);
        boolean useProps = (preferPropsBased || _checkIfCreatorPropertyBased(beanDesc, intr, ctor, propDef));
        if (useProps) {
          SettableBeanProperty[] arrayOfSettableBeanProperty = new SettableBeanProperty[1];
          JacksonInject.Value injection = candidate.injection(0);
          PropertyName name = candidate.paramName(0);
          if (name == null) {
            name = candidate.findImplicitParamName(0);
            if (name == null && injection == null)
              continue; 
          } 
          arrayOfSettableBeanProperty[0] = constructCreatorProperty(ctxt, beanDesc, name, 0, candidate
              .parameter(0), injection);
          creators.addPropertyCreator(ctor, false, arrayOfSettableBeanProperty);
          continue;
        } 
        _handleSingleArgumentCreator(creators, ctor, false, vchecker
            
            .isCreatorVisible((AnnotatedMember)ctor));
        if (propDef != null)
          ((POJOPropertyBuilder)propDef).removeConstructors(); 
        continue;
      } 
      int nonAnnotatedParamIndex = -1;
      SettableBeanProperty[] properties = new SettableBeanProperty[argCount];
      int explicitNameCount = 0;
      int implicitWithCreatorCount = 0;
      int injectCount = 0;
      for (int i = 0; i < argCount; i++) {
        AnnotatedParameter param = ctor.getParameter(i);
        BeanPropertyDefinition propDef = candidate.propertyDef(i);
        JacksonInject.Value injectable = intr.findInjectableValue((AnnotatedMember)param);
        PropertyName name = (propDef == null) ? null : propDef.getFullName();
        if (propDef != null && (propDef
          
          .isExplicitlyNamed() || beanDesc.isRecordType())) {
          explicitNameCount++;
          properties[i] = constructCreatorProperty(ctxt, beanDesc, name, i, param, injectable);
        } else if (injectable != null) {
          injectCount++;
          properties[i] = constructCreatorProperty(ctxt, beanDesc, name, i, param, injectable);
        } else {
          NameTransformer unwrapper = intr.findUnwrappingNameTransformer((AnnotatedMember)param);
          if (unwrapper != null) {
            _reportUnwrappedCreatorProperty(ctxt, beanDesc, param);
          } else if (nonAnnotatedParamIndex < 0) {
            nonAnnotatedParamIndex = i;
          } 
        } 
      } 
      int namedCount = explicitNameCount + implicitWithCreatorCount;
      if (explicitNameCount > 0 || injectCount > 0) {
        if (namedCount + injectCount == argCount) {
          creators.addPropertyCreator(ctor, false, properties);
          continue;
        } 
        if (explicitNameCount == 0 && injectCount + 1 == argCount) {
          creators.addDelegatingCreator(ctor, false, properties, 0);
          continue;
        } 
        PropertyName impl = candidate.findImplicitParamName(nonAnnotatedParamIndex);
        if (impl == null || impl.isEmpty())
          ctxt.reportBadTypeDefinition(beanDesc, "Argument #%d of constructor %s has no property name annotation; must have name when multiple-parameter constructor annotated as Creator", new Object[] { Integer.valueOf(nonAnnotatedParamIndex), ctor }); 
      } 
      if (!creators.hasDefaultCreator()) {
        if (implicitCtors == null)
          implicitCtors = new LinkedList<>(); 
        implicitCtors.add(ctor);
      } 
    } 
    if (implicitCtors != null && !creators.hasDelegatingCreator() && 
      !creators.hasPropertyBasedCreator())
      _checkImplicitlyNamedConstructors(ctxt, beanDesc, vchecker, intr, creators, implicitCtors); 
  }
  
  protected void _addExplicitFactoryCreators(DeserializationContext ctxt, CreatorCollectionState ccState, boolean findImplicit) throws JsonMappingException {
    BeanDescription beanDesc = ccState.beanDesc;
    CreatorCollector creators = ccState.creators;
    AnnotationIntrospector intr = ccState.annotationIntrospector();
    VisibilityChecker<?> vchecker = ccState.vchecker;
    Map<AnnotatedWithParams, BeanPropertyDefinition[]> creatorParams = ccState.creatorParams;
    for (AnnotatedMethod factory : beanDesc.getFactoryMethods()) {
      JsonCreator.Mode creatorMode = intr.findCreatorAnnotation((MapperConfig)ctxt.getConfig(), (Annotated)factory);
      int argCount = factory.getParameterCount();
      if (creatorMode == null) {
        if (findImplicit && argCount == 1 && vchecker.isCreatorVisible((AnnotatedMember)factory))
          ccState.addImplicitFactoryCandidate(CreatorCandidate.construct(intr, (AnnotatedWithParams)factory, null)); 
        continue;
      } 
      if (creatorMode == JsonCreator.Mode.DISABLED)
        continue; 
      if (argCount == 0) {
        creators.setDefaultCreator((AnnotatedWithParams)factory);
        continue;
      } 
      switch (creatorMode) {
        case DELEGATING:
          _addExplicitDelegatingCreator(ctxt, beanDesc, creators, 
              CreatorCandidate.construct(intr, (AnnotatedWithParams)factory, null));
          break;
        case PROPERTIES:
          _addExplicitPropertyCreator(ctxt, beanDesc, creators, 
              CreatorCandidate.construct(intr, (AnnotatedWithParams)factory, creatorParams.get(factory)));
          break;
        default:
          _addExplicitAnyCreator(ctxt, beanDesc, creators, 
              CreatorCandidate.construct(intr, (AnnotatedWithParams)factory, creatorParams.get(factory)), ConstructorDetector.DEFAULT);
          break;
      } 
      ccState.increaseExplicitFactoryCount();
    } 
  }
  
  protected void _addImplicitFactoryCreators(DeserializationContext ctxt, CreatorCollectionState ccState, List<CreatorCandidate> factoryCandidates) throws JsonMappingException {
    BeanDescription beanDesc = ccState.beanDesc;
    CreatorCollector creators = ccState.creators;
    AnnotationIntrospector intr = ccState.annotationIntrospector();
    VisibilityChecker<?> vchecker = ccState.vchecker;
    Map<AnnotatedWithParams, BeanPropertyDefinition[]> creatorParams = ccState.creatorParams;
    for (CreatorCandidate candidate : factoryCandidates) {
      int argCount = candidate.paramCount();
      AnnotatedWithParams factory = candidate.creator();
      BeanPropertyDefinition[] propDefs = creatorParams.get(factory);
      if (argCount != 1)
        continue; 
      BeanPropertyDefinition argDef = candidate.propertyDef(0);
      boolean useProps = _checkIfCreatorPropertyBased(beanDesc, intr, factory, argDef);
      if (!useProps) {
        _handleSingleArgumentCreator(creators, factory, false, vchecker
            .isCreatorVisible((AnnotatedMember)factory));
        if (argDef != null)
          ((POJOPropertyBuilder)argDef).removeConstructors(); 
        continue;
      } 
      AnnotatedParameter nonAnnotatedParam = null;
      SettableBeanProperty[] properties = new SettableBeanProperty[argCount];
      int implicitNameCount = 0;
      int explicitNameCount = 0;
      int injectCount = 0;
      for (int i = 0; i < argCount; i++) {
        AnnotatedParameter param = factory.getParameter(i);
        BeanPropertyDefinition propDef = (propDefs == null) ? null : propDefs[i];
        JacksonInject.Value injectable = intr.findInjectableValue((AnnotatedMember)param);
        PropertyName name = (propDef == null) ? null : propDef.getFullName();
        if (propDef != null && propDef.isExplicitlyNamed()) {
          explicitNameCount++;
          properties[i] = constructCreatorProperty(ctxt, beanDesc, name, i, param, injectable);
        } else if (injectable != null) {
          injectCount++;
          properties[i] = constructCreatorProperty(ctxt, beanDesc, name, i, param, injectable);
        } else {
          NameTransformer unwrapper = intr.findUnwrappingNameTransformer((AnnotatedMember)param);
          if (unwrapper != null) {
            _reportUnwrappedCreatorProperty(ctxt, beanDesc, param);
          } else if (nonAnnotatedParam == null) {
            nonAnnotatedParam = param;
          } 
        } 
      } 
      int namedCount = explicitNameCount + implicitNameCount;
      if (explicitNameCount > 0 || injectCount > 0) {
        if (namedCount + injectCount == argCount) {
          creators.addPropertyCreator(factory, false, properties);
          continue;
        } 
        if (explicitNameCount == 0 && injectCount + 1 == argCount) {
          creators.addDelegatingCreator(factory, false, properties, 0);
          continue;
        } 
        ctxt.reportBadTypeDefinition(beanDesc, "Argument #%d of factory method %s has no property name annotation; must have name when multiple-parameter constructor annotated as Creator", new Object[] { Integer.valueOf((nonAnnotatedParam == null) ? -1 : nonAnnotatedParam.getIndex()), factory });
      } 
    } 
  }
  
  protected void _addExplicitDelegatingCreator(DeserializationContext ctxt, BeanDescription beanDesc, CreatorCollector creators, CreatorCandidate candidate) throws JsonMappingException {
    int ix = -1;
    int argCount = candidate.paramCount();
    SettableBeanProperty[] properties = new SettableBeanProperty[argCount];
    for (int i = 0; i < argCount; i++) {
      AnnotatedParameter param = candidate.parameter(i);
      JacksonInject.Value injectId = candidate.injection(i);
      if (injectId != null) {
        properties[i] = constructCreatorProperty(ctxt, beanDesc, null, i, param, injectId);
      } else if (ix < 0) {
        ix = i;
      } else {
        ctxt.reportBadTypeDefinition(beanDesc, "More than one argument (#%d and #%d) left as delegating for Creator %s: only one allowed", new Object[] { Integer.valueOf(ix), Integer.valueOf(i), candidate });
      } 
    } 
    if (ix < 0)
      ctxt.reportBadTypeDefinition(beanDesc, "No argument left as delegating for Creator %s: exactly one required", new Object[] { candidate }); 
    if (argCount == 1) {
      _handleSingleArgumentCreator(creators, candidate.creator(), true, true);
      BeanPropertyDefinition paramDef = candidate.propertyDef(0);
      if (paramDef != null)
        ((POJOPropertyBuilder)paramDef).removeConstructors(); 
      return;
    } 
    creators.addDelegatingCreator(candidate.creator(), true, properties, ix);
  }
  
  protected void _addExplicitPropertyCreator(DeserializationContext ctxt, BeanDescription beanDesc, CreatorCollector creators, CreatorCandidate candidate) throws JsonMappingException {
    int paramCount = candidate.paramCount();
    SettableBeanProperty[] properties = new SettableBeanProperty[paramCount];
    for (int i = 0; i < paramCount; i++) {
      JacksonInject.Value injectId = candidate.injection(i);
      AnnotatedParameter param = candidate.parameter(i);
      PropertyName name = candidate.paramName(i);
      if (name == null) {
        NameTransformer unwrapper = ctxt.getAnnotationIntrospector().findUnwrappingNameTransformer((AnnotatedMember)param);
        if (unwrapper != null)
          _reportUnwrappedCreatorProperty(ctxt, beanDesc, param); 
        name = candidate.findImplicitParamName(i);
        _validateNamedPropertyParameter(ctxt, beanDesc, candidate, i, name, injectId);
      } 
      properties[i] = constructCreatorProperty(ctxt, beanDesc, name, i, param, injectId);
    } 
    creators.addPropertyCreator(candidate.creator(), true, properties);
  }
  
  @Deprecated
  protected void _addExplicitAnyCreator(DeserializationContext ctxt, BeanDescription beanDesc, CreatorCollector creators, CreatorCandidate candidate) throws JsonMappingException {
    _addExplicitAnyCreator(ctxt, beanDesc, creators, candidate, ctxt
        .getConfig().getConstructorDetector());
  }
  
  protected void _addExplicitAnyCreator(DeserializationContext ctxt, BeanDescription beanDesc, CreatorCollector creators, CreatorCandidate candidate, ConstructorDetector ctorDetector) throws JsonMappingException {
    boolean useProps;
    if (1 != candidate.paramCount()) {
      if (!ctorDetector.singleArgCreatorDefaultsToProperties()) {
        int oneNotInjected = candidate.findOnlyParamWithoutInjection();
        if (oneNotInjected >= 0)
          if (ctorDetector.singleArgCreatorDefaultsToDelegating() || candidate
            .paramName(oneNotInjected) == null) {
            _addExplicitDelegatingCreator(ctxt, beanDesc, creators, candidate);
            return;
          }  
      } 
      _addExplicitPropertyCreator(ctxt, beanDesc, creators, candidate);
      return;
    } 
    AnnotatedParameter param = candidate.parameter(0);
    JacksonInject.Value injectId = candidate.injection(0);
    PropertyName paramName = null;
    switch (ctorDetector.singleArgMode()) {
      case DELEGATING:
        useProps = false;
        break;
      case PROPERTIES:
        useProps = true;
        paramName = candidate.paramName(0);
        if (paramName == null)
          _validateNamedPropertyParameter(ctxt, beanDesc, candidate, 0, paramName, injectId); 
        break;
      case REQUIRE_MODE:
        ctxt.reportBadTypeDefinition(beanDesc, "Single-argument constructor (%s) is annotated but no 'mode' defined; `CreatorDetector`configured with `SingleArgConstructor.REQUIRE_MODE`", new Object[] { candidate
              
              .creator() });
        return;
      default:
        paramDef = candidate.propertyDef(0);
        paramName = candidate.explicitParamName(0);
        useProps = (paramName != null);
        if (!useProps) {
          if (beanDesc.findJsonValueAccessor() != null)
            break; 
          if (injectId != null) {
            useProps = true;
            break;
          } 
          if (paramDef != null) {
            paramName = candidate.paramName(0);
            useProps = (paramName != null && paramDef.couldSerialize());
          } 
        } 
        break;
    } 
    if (useProps) {
      SettableBeanProperty[] properties = { constructCreatorProperty(ctxt, beanDesc, paramName, 0, param, injectId) };
      creators.addPropertyCreator(candidate.creator(), true, properties);
      return;
    } 
    _handleSingleArgumentCreator(creators, candidate.creator(), true, true);
    BeanPropertyDefinition paramDef = candidate.propertyDef(0);
    if (paramDef != null)
      ((POJOPropertyBuilder)paramDef).removeConstructors(); 
  }
  
  private boolean _checkIfCreatorPropertyBased(BeanDescription beanDesc, AnnotationIntrospector intr, AnnotatedWithParams creator, BeanPropertyDefinition propDef) {
    if (propDef != null && propDef.isExplicitlyNamed())
      return true; 
    if (beanDesc.findJsonValueAccessor() != null)
      return false; 
    if (intr.findInjectableValue((AnnotatedMember)creator.getParameter(0)) != null)
      return true; 
    if (propDef != null) {
      String implName = propDef.getName();
      if (implName != null && !implName.isEmpty() && 
        propDef.couldSerialize())
        return true; 
      if (!propDef.isExplicitlyNamed() && beanDesc.isRecordType())
        return true; 
    } 
    return false;
  }
  
  private void _checkImplicitlyNamedConstructors(DeserializationContext ctxt, BeanDescription beanDesc, VisibilityChecker<?> vchecker, AnnotationIntrospector intr, CreatorCollector creators, List<AnnotatedWithParams> implicitCtors) throws JsonMappingException {
    AnnotatedWithParams found = null;
    SettableBeanProperty[] foundProps = null;
    label34: for (AnnotatedWithParams ctor : implicitCtors) {
      if (!vchecker.isCreatorVisible((AnnotatedMember)ctor))
        continue; 
      int argCount = ctor.getParameterCount();
      SettableBeanProperty[] properties = new SettableBeanProperty[argCount];
      for (int i = 0; i < argCount; ) {
        AnnotatedParameter param = ctor.getParameter(i);
        PropertyName name = _findParamName(param, intr);
        if (name != null) {
          if (name.isEmpty())
            continue label34; 
          properties[i] = constructCreatorProperty(ctxt, beanDesc, name, param.getIndex(), param, null);
          i++;
        } 
        continue label34;
      } 
      if (found != null) {
        found = null;
        break;
      } 
      found = ctor;
      foundProps = properties;
    } 
    if (found != null) {
      creators.addPropertyCreator(found, false, foundProps);
      BasicBeanDescription bbd = (BasicBeanDescription)beanDesc;
      for (SettableBeanProperty prop : foundProps) {
        PropertyName pn = prop.getFullName();
        if (!bbd.hasProperty(pn)) {
          SimpleBeanPropertyDefinition simpleBeanPropertyDefinition = SimpleBeanPropertyDefinition.construct((MapperConfig)ctxt
              .getConfig(), prop.getMember(), pn);
          bbd.addProperty((BeanPropertyDefinition)simpleBeanPropertyDefinition);
        } 
      } 
    } 
  }
  
  protected boolean _handleSingleArgumentCreator(CreatorCollector creators, AnnotatedWithParams ctor, boolean isCreator, boolean isVisible) {
    Class<?> type = ctor.getRawParameterType(0);
    if (type == String.class || type == CLASS_CHAR_SEQUENCE) {
      if (isCreator || isVisible)
        creators.addStringCreator(ctor, isCreator); 
      return true;
    } 
    if (type == int.class || type == Integer.class) {
      if (isCreator || isVisible)
        creators.addIntCreator(ctor, isCreator); 
      return true;
    } 
    if (type == long.class || type == Long.class) {
      if (isCreator || isVisible)
        creators.addLongCreator(ctor, isCreator); 
      return true;
    } 
    if (type == double.class || type == Double.class) {
      if (isCreator || isVisible)
        creators.addDoubleCreator(ctor, isCreator); 
      return true;
    } 
    if (type == boolean.class || type == Boolean.class) {
      if (isCreator || isVisible)
        creators.addBooleanCreator(ctor, isCreator); 
      return true;
    } 
    if (type == BigInteger.class && (
      isCreator || isVisible))
      creators.addBigIntegerCreator(ctor, isCreator); 
    if (type == BigDecimal.class && (
      isCreator || isVisible))
      creators.addBigDecimalCreator(ctor, isCreator); 
    if (isCreator) {
      creators.addDelegatingCreator(ctor, isCreator, null, 0);
      return true;
    } 
    return false;
  }
  
  protected void _validateNamedPropertyParameter(DeserializationContext ctxt, BeanDescription beanDesc, CreatorCandidate candidate, int paramIndex, PropertyName name, JacksonInject.Value injectId) throws JsonMappingException {
    if (name == null && injectId == null)
      ctxt.reportBadTypeDefinition(beanDesc, "Argument #%d of constructor %s has no property name (and is not Injectable): can not use as property-based Creator", new Object[] { Integer.valueOf(paramIndex), candidate }); 
  }
  
  protected void _reportUnwrappedCreatorProperty(DeserializationContext ctxt, BeanDescription beanDesc, AnnotatedParameter param) throws JsonMappingException {
    ctxt.reportBadTypeDefinition(beanDesc, "Cannot define Creator parameter %d as `@JsonUnwrapped`: combination not yet supported", new Object[] { Integer.valueOf(param.getIndex()) });
  }
  
  protected SettableBeanProperty constructCreatorProperty(DeserializationContext ctxt, BeanDescription beanDesc, PropertyName name, int index, AnnotatedParameter param, JacksonInject.Value injectable) throws JsonMappingException {
    PropertyName wrapperName;
    DeserializationConfig config = ctxt.getConfig();
    AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
    if (intr == null) {
      metadata = PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
      wrapperName = null;
    } else {
      Boolean b = intr.hasRequiredMarker((AnnotatedMember)param);
      String desc = intr.findPropertyDescription((Annotated)param);
      Integer idx = intr.findPropertyIndex((Annotated)param);
      String def = intr.findPropertyDefaultValue((Annotated)param);
      metadata = PropertyMetadata.construct(b, desc, idx, def);
      wrapperName = intr.findWrapperName((Annotated)param);
    } 
    JavaType type = resolveMemberAndTypeAnnotations(ctxt, (AnnotatedMember)param, param.getType());
    BeanProperty.Std property = new BeanProperty.Std(name, type, wrapperName, (AnnotatedMember)param, metadata);
    TypeDeserializer typeDeser = (TypeDeserializer)type.getTypeHandler();
    if (typeDeser == null)
      typeDeser = findTypeDeserializer(config, type); 
    PropertyMetadata metadata = _getSetterInfo(ctxt, (BeanProperty)property, metadata);
    SettableBeanProperty prop = CreatorProperty.construct(name, type, property.getWrapperName(), typeDeser, beanDesc
        .getClassAnnotations(), param, index, injectable, metadata);
    JsonDeserializer<?> deser = findDeserializerFromAnnotation(ctxt, (Annotated)param);
    if (deser == null)
      deser = (JsonDeserializer)type.getValueHandler(); 
    if (deser != null) {
      deser = ctxt.handlePrimaryContextualization(deser, (BeanProperty)prop, type);
      prop = prop.withValueDeserializer(deser);
    } 
    return prop;
  }
  
  private PropertyName _findParamName(AnnotatedParameter param, AnnotationIntrospector intr) {
    if (intr != null) {
      PropertyName name = intr.findNameForDeserialization((Annotated)param);
      if (name != null)
        if (!name.isEmpty())
          return name;  
      String str = intr.findImplicitPropertyName((AnnotatedMember)param);
      if (str != null && !str.isEmpty())
        return PropertyName.construct(str); 
    } 
    return null;
  }
  
  protected PropertyMetadata _getSetterInfo(DeserializationContext ctxt, BeanProperty prop, PropertyMetadata metadata) {
    AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
    DeserializationConfig config = ctxt.getConfig();
    boolean needMerge = true;
    Nulls valueNulls = null;
    Nulls contentNulls = null;
    AnnotatedMember prim = prop.getMember();
    if (prim != null) {
      if (intr != null) {
        JsonSetter.Value setterInfo = intr.findSetterInfo((Annotated)prim);
        if (setterInfo != null) {
          valueNulls = setterInfo.nonDefaultValueNulls();
          contentNulls = setterInfo.nonDefaultContentNulls();
        } 
      } 
      if (needMerge || valueNulls == null || contentNulls == null) {
        ConfigOverride co = config.getConfigOverride(prop.getType().getRawClass());
        JsonSetter.Value setterInfo = co.getSetterInfo();
        if (setterInfo != null) {
          if (valueNulls == null)
            valueNulls = setterInfo.nonDefaultValueNulls(); 
          if (contentNulls == null)
            contentNulls = setterInfo.nonDefaultContentNulls(); 
        } 
      } 
    } 
    if (needMerge || valueNulls == null || contentNulls == null) {
      JsonSetter.Value setterInfo = config.getDefaultSetterInfo();
      if (valueNulls == null)
        valueNulls = setterInfo.nonDefaultValueNulls(); 
      if (contentNulls == null)
        contentNulls = setterInfo.nonDefaultContentNulls(); 
    } 
    if (valueNulls != null || contentNulls != null)
      metadata = metadata.withNulls(valueNulls, contentNulls); 
    return metadata;
  }
  
  public JsonDeserializer<?> createArrayDeserializer(DeserializationContext ctxt, ArrayType type, BeanDescription beanDesc) throws JsonMappingException {
    ObjectArrayDeserializer objectArrayDeserializer;
    JsonDeserializer<?> jsonDeserializer1;
    DeserializationConfig config = ctxt.getConfig();
    JavaType elemType = type.getContentType();
    JsonDeserializer<Object> contentDeser = (JsonDeserializer<Object>)elemType.getValueHandler();
    TypeDeserializer elemTypeDeser = (TypeDeserializer)elemType.getTypeHandler();
    if (elemTypeDeser == null)
      elemTypeDeser = findTypeDeserializer(config, elemType); 
    JsonDeserializer<?> deser = _findCustomArrayDeserializer(type, config, beanDesc, elemTypeDeser, contentDeser);
    if (deser == null) {
      StringArrayDeserializer stringArrayDeserializer;
      if (contentDeser == null)
        if (elemType.isPrimitive()) {
          deser = PrimitiveArrayDeserializers.forType(elemType.getRawClass());
        } else if (elemType.hasRawClass(String.class)) {
          stringArrayDeserializer = StringArrayDeserializer.instance;
        }  
      if (stringArrayDeserializer == null)
        objectArrayDeserializer = new ObjectArrayDeserializer((JavaType)type, contentDeser, elemTypeDeser); 
    } 
    if (this._factoryConfig.hasDeserializerModifiers())
      for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers())
        jsonDeserializer1 = mod.modifyArrayDeserializer(config, type, beanDesc, (JsonDeserializer<?>)objectArrayDeserializer);  
    return jsonDeserializer1;
  }
  
  public JsonDeserializer<?> createCollectionDeserializer(DeserializationContext ctxt, CollectionType type, BeanDescription beanDesc) throws JsonMappingException {
    EnumSetDeserializer enumSetDeserializer;
    CollectionDeserializer collectionDeserializer;
    JsonDeserializer<?> jsonDeserializer1;
    JavaType contentType = type.getContentType();
    JsonDeserializer<Object> contentDeser = (JsonDeserializer<Object>)contentType.getValueHandler();
    DeserializationConfig config = ctxt.getConfig();
    TypeDeserializer contentTypeDeser = (TypeDeserializer)contentType.getTypeHandler();
    if (contentTypeDeser == null)
      contentTypeDeser = findTypeDeserializer(config, contentType); 
    JsonDeserializer<?> deser = _findCustomCollectionDeserializer(type, config, beanDesc, contentTypeDeser, contentDeser);
    if (deser == null) {
      Class<?> collectionClass = type.getRawClass();
      if (contentDeser == null)
        if (EnumSet.class.isAssignableFrom(collectionClass))
          enumSetDeserializer = new EnumSetDeserializer(contentType, null);  
    } 
    if (enumSetDeserializer == null) {
      if (type.isInterface() || type.isAbstract()) {
        CollectionType implType = _mapAbstractCollectionType((JavaType)type, config);
        if (implType == null) {
          if (type.getTypeHandler() == null)
            throw new IllegalArgumentException("Cannot find a deserializer for non-concrete Collection type " + type); 
          jsonDeserializer1 = AbstractDeserializer.constructForNonPOJO(beanDesc);
        } else {
          type = implType;
          beanDesc = config.introspectForCreation((JavaType)type);
        } 
      } 
      if (jsonDeserializer1 == null) {
        ValueInstantiator inst = findValueInstantiator(ctxt, beanDesc);
        if (!inst.canCreateUsingDefault()) {
          if (type.hasRawClass(ArrayBlockingQueue.class))
            return (JsonDeserializer<?>)new ArrayBlockingQueueDeserializer((JavaType)type, contentDeser, contentTypeDeser, inst); 
          JsonDeserializer<?> jsonDeserializer = JavaUtilCollectionsDeserializers.findForCollection(ctxt, (JavaType)type);
          if (jsonDeserializer != null)
            return jsonDeserializer; 
        } 
        if (contentType.hasRawClass(String.class)) {
          StringCollectionDeserializer stringCollectionDeserializer = new StringCollectionDeserializer((JavaType)type, contentDeser, inst);
        } else {
          collectionDeserializer = new CollectionDeserializer((JavaType)type, contentDeser, contentTypeDeser, inst);
        } 
      } 
    } 
    if (this._factoryConfig.hasDeserializerModifiers())
      for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers())
        jsonDeserializer1 = mod.modifyCollectionDeserializer(config, type, beanDesc, (JsonDeserializer<?>)collectionDeserializer);  
    return jsonDeserializer1;
  }
  
  protected CollectionType _mapAbstractCollectionType(JavaType type, DeserializationConfig config) {
    Class<?> collectionClass = ContainerDefaultMappings.findCollectionFallback(type);
    if (collectionClass != null)
      return (CollectionType)config.getTypeFactory()
        .constructSpecializedType(type, collectionClass, true); 
    return null;
  }
  
  public JsonDeserializer<?> createCollectionLikeDeserializer(DeserializationContext ctxt, CollectionLikeType type, BeanDescription beanDesc) throws JsonMappingException {
    JavaType contentType = type.getContentType();
    JsonDeserializer<Object> contentDeser = (JsonDeserializer<Object>)contentType.getValueHandler();
    DeserializationConfig config = ctxt.getConfig();
    TypeDeserializer contentTypeDeser = (TypeDeserializer)contentType.getTypeHandler();
    if (contentTypeDeser == null)
      contentTypeDeser = findTypeDeserializer(config, contentType); 
    JsonDeserializer<?> deser = _findCustomCollectionLikeDeserializer(type, config, beanDesc, contentTypeDeser, contentDeser);
    if (deser != null)
      if (this._factoryConfig.hasDeserializerModifiers())
        for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers())
          deser = mod.modifyCollectionLikeDeserializer(config, type, beanDesc, deser);   
    return deser;
  }
  
  public JsonDeserializer<?> createMapDeserializer(DeserializationContext ctxt, MapType type, BeanDescription beanDesc) throws JsonMappingException {
    MapDeserializer mapDeserializer;
    JsonDeserializer<?> jsonDeserializer1;
    DeserializationConfig config = ctxt.getConfig();
    JavaType keyType = type.getKeyType();
    JavaType contentType = type.getContentType();
    JsonDeserializer<Object> contentDeser = (JsonDeserializer<Object>)contentType.getValueHandler();
    KeyDeserializer keyDes = (KeyDeserializer)keyType.getValueHandler();
    TypeDeserializer contentTypeDeser = (TypeDeserializer)contentType.getTypeHandler();
    if (contentTypeDeser == null)
      contentTypeDeser = findTypeDeserializer(config, contentType); 
    JsonDeserializer<?> deser = _findCustomMapDeserializer(type, config, beanDesc, keyDes, contentTypeDeser, contentDeser);
    if (deser == null) {
      EnumMapDeserializer enumMapDeserializer;
      Class<?> mapClass = type.getRawClass();
      if (EnumMap.class.isAssignableFrom(mapClass)) {
        ValueInstantiator inst;
        if (mapClass == EnumMap.class) {
          inst = null;
        } else {
          inst = findValueInstantiator(ctxt, beanDesc);
        } 
        if (!keyType.isEnumImplType())
          throw new IllegalArgumentException("Cannot construct EnumMap; generic (key) type not available"); 
        enumMapDeserializer = new EnumMapDeserializer((JavaType)type, inst, null, contentDeser, contentTypeDeser, null);
      } 
      if (enumMapDeserializer == null) {
        if (type.isInterface() || type.isAbstract()) {
          MapType fallback = _mapAbstractMapType((JavaType)type, config);
          if (fallback != null) {
            type = fallback;
            mapClass = type.getRawClass();
            beanDesc = config.introspectForCreation((JavaType)type);
          } else {
            if (type.getTypeHandler() == null)
              throw new IllegalArgumentException("Cannot find a deserializer for non-concrete Map type " + type); 
            jsonDeserializer1 = AbstractDeserializer.constructForNonPOJO(beanDesc);
          } 
        } else {
          jsonDeserializer1 = JavaUtilCollectionsDeserializers.findForMap(ctxt, (JavaType)type);
          if (jsonDeserializer1 != null)
            return jsonDeserializer1; 
        } 
        if (jsonDeserializer1 == null) {
          ValueInstantiator inst = findValueInstantiator(ctxt, beanDesc);
          MapDeserializer md = new MapDeserializer((JavaType)type, inst, keyDes, contentDeser, contentTypeDeser);
          JsonIgnoreProperties.Value ignorals = config.getDefaultPropertyIgnorals(Map.class, beanDesc
              .getClassInfo());
          Set<String> ignored = (ignorals == null) ? null : ignorals.findIgnoredForDeserialization();
          md.setIgnorableProperties(ignored);
          JsonIncludeProperties.Value inclusions = config.getDefaultPropertyInclusions(Map.class, beanDesc
              .getClassInfo());
          Set<String> included = (inclusions == null) ? null : inclusions.getIncluded();
          md.setIncludableProperties(included);
          mapDeserializer = md;
        } 
      } 
    } 
    if (this._factoryConfig.hasDeserializerModifiers())
      for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers())
        jsonDeserializer1 = mod.modifyMapDeserializer(config, type, beanDesc, (JsonDeserializer<?>)mapDeserializer);  
    return jsonDeserializer1;
  }
  
  protected MapType _mapAbstractMapType(JavaType type, DeserializationConfig config) {
    Class<?> mapClass = ContainerDefaultMappings.findMapFallback(type);
    if (mapClass != null)
      return (MapType)config.getTypeFactory()
        .constructSpecializedType(type, mapClass, true); 
    return null;
  }
  
  public JsonDeserializer<?> createMapLikeDeserializer(DeserializationContext ctxt, MapLikeType type, BeanDescription beanDesc) throws JsonMappingException {
    JavaType keyType = type.getKeyType();
    JavaType contentType = type.getContentType();
    DeserializationConfig config = ctxt.getConfig();
    JsonDeserializer<Object> contentDeser = (JsonDeserializer<Object>)contentType.getValueHandler();
    KeyDeserializer keyDes = (KeyDeserializer)keyType.getValueHandler();
    TypeDeserializer contentTypeDeser = (TypeDeserializer)contentType.getTypeHandler();
    if (contentTypeDeser == null)
      contentTypeDeser = findTypeDeserializer(config, contentType); 
    JsonDeserializer<?> deser = _findCustomMapLikeDeserializer(type, config, beanDesc, keyDes, contentTypeDeser, contentDeser);
    if (deser != null)
      if (this._factoryConfig.hasDeserializerModifiers())
        for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers())
          deser = mod.modifyMapLikeDeserializer(config, type, beanDesc, deser);   
    return deser;
  }
  
  public JsonDeserializer<?> createEnumDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
    EnumDeserializer enumDeserializer;
    JsonDeserializer<?> jsonDeserializer1;
    DeserializationConfig config = ctxt.getConfig();
    Class<?> enumClass = type.getRawClass();
    JsonDeserializer<?> deser = _findCustomEnumDeserializer(enumClass, config, beanDesc);
    if (deser == null) {
      if (enumClass == Enum.class)
        return AbstractDeserializer.constructForNonPOJO(beanDesc); 
      ValueInstantiator valueInstantiator = _constructDefaultValueInstantiator(ctxt, beanDesc);
      SettableBeanProperty[] creatorProps = (valueInstantiator == null) ? null : valueInstantiator.getFromObjectArguments(ctxt.getConfig());
      for (AnnotatedMethod factory : beanDesc.getFactoryMethods()) {
        if (_hasCreatorAnnotation(ctxt, (Annotated)factory)) {
          if (factory.getParameterCount() == 0) {
            deser = EnumDeserializer.deserializerForNoArgsCreator(config, enumClass, factory);
            break;
          } 
          Class<?> returnType = factory.getRawReturnType();
          if (!returnType.isAssignableFrom(enumClass))
            ctxt.reportBadDefinition(type, String.format("Invalid `@JsonCreator` annotated Enum factory method [%s]: needs to return compatible type", new Object[] { factory
                    
                    .toString() })); 
          deser = EnumDeserializer.deserializerForCreator(config, enumClass, factory, valueInstantiator, creatorProps);
          break;
        } 
      } 
      if (deser == null)
        enumDeserializer = new EnumDeserializer(constructEnumResolver(enumClass, config, beanDesc), config.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS), constructEnumNamingStrategyResolver(config, beanDesc.getClassInfo()), EnumResolver.constructUsingToString(config, beanDesc.getClassInfo())); 
    } 
    if (this._factoryConfig.hasDeserializerModifiers())
      for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers())
        jsonDeserializer1 = mod.modifyEnumDeserializer(config, type, beanDesc, (JsonDeserializer<?>)enumDeserializer);  
    return jsonDeserializer1;
  }
  
  public JsonDeserializer<?> createTreeDeserializer(DeserializationConfig config, JavaType nodeType, BeanDescription beanDesc) throws JsonMappingException {
    Class<? extends JsonNode> nodeClass = nodeType.getRawClass();
    JsonDeserializer<?> custom = _findCustomTreeNodeDeserializer(nodeClass, config, beanDesc);
    if (custom != null)
      return custom; 
    return JsonNodeDeserializer.getDeserializer(nodeClass);
  }
  
  public JsonDeserializer<?> createReferenceDeserializer(DeserializationContext ctxt, ReferenceType type, BeanDescription beanDesc) throws JsonMappingException {
    JavaType contentType = type.getContentType();
    JsonDeserializer<Object> contentDeser = (JsonDeserializer<Object>)contentType.getValueHandler();
    DeserializationConfig config = ctxt.getConfig();
    TypeDeserializer contentTypeDeser = (TypeDeserializer)contentType.getTypeHandler();
    if (contentTypeDeser == null)
      contentTypeDeser = findTypeDeserializer(config, contentType); 
    JsonDeserializer<?> deser = _findCustomReferenceDeserializer(type, config, beanDesc, contentTypeDeser, contentDeser);
    if (deser == null)
      if (type.isTypeOrSubTypeOf(AtomicReference.class)) {
        ValueInstantiator inst;
        Class<?> rawType = type.getRawClass();
        if (rawType == AtomicReference.class) {
          inst = null;
        } else {
          inst = findValueInstantiator(ctxt, beanDesc);
        } 
        return (JsonDeserializer<?>)new AtomicReferenceDeserializer((JavaType)type, inst, contentTypeDeser, contentDeser);
      }  
    if (deser != null)
      if (this._factoryConfig.hasDeserializerModifiers())
        for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers())
          deser = mod.modifyReferenceDeserializer(config, type, beanDesc, deser);   
    return deser;
  }
  
  public TypeDeserializer findTypeDeserializer(DeserializationConfig config, JavaType baseType) throws JsonMappingException {
    BeanDescription bean = config.introspectClassAnnotations(baseType.getRawClass());
    AnnotatedClass ac = bean.getClassInfo();
    AnnotationIntrospector ai = config.getAnnotationIntrospector();
    TypeResolverBuilder<?> b = ai.findTypeResolver((MapperConfig)config, ac, baseType);
    if (b == null) {
      b = config.getDefaultTyper(baseType);
      if (b == null)
        return null; 
    } 
    Collection<NamedType> subtypes = config.getSubtypeResolver().collectAndResolveSubtypesByTypeId((MapperConfig)config, ac);
    if (b.getDefaultImpl() == null && baseType.isAbstract()) {
      JavaType defaultType = mapAbstractType(config, baseType);
      if (defaultType != null && !defaultType.hasRawClass(baseType.getRawClass()))
        b = b.withDefaultImpl(defaultType.getRawClass()); 
    } 
    try {
      return b.buildTypeDeserializer(config, baseType, subtypes);
    } catch (IllegalArgumentException|IllegalStateException e0) {
      throw InvalidDefinitionException.from((JsonParser)null, 
          ClassUtil.exceptionMessage(e0), baseType)
        .withCause(e0);
    } 
  }
  
  protected JsonDeserializer<?> findOptionalStdDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
    return OptionalHandlerFactory.instance.findDeserializer(type, ctxt.getConfig(), beanDesc);
  }
  
  public KeyDeserializer createKeyDeserializer(DeserializationContext ctxt, JavaType type) throws JsonMappingException {
    DeserializationConfig config = ctxt.getConfig();
    BeanDescription beanDesc = null;
    KeyDeserializer deser = null;
    if (this._factoryConfig.hasKeyDeserializers()) {
      beanDesc = config.introspectClassAnnotations(type);
      for (KeyDeserializers d : this._factoryConfig.keyDeserializers()) {
        deser = d.findKeyDeserializer(type, config, beanDesc);
        if (deser != null)
          break; 
      } 
    } 
    if (deser == null) {
      if (beanDesc == null)
        beanDesc = config.introspectClassAnnotations(type.getRawClass()); 
      deser = findKeyDeserializerFromAnnotation(ctxt, (Annotated)beanDesc.getClassInfo());
      if (deser == null)
        if (type.isEnumType()) {
          deser = _createEnumKeyDeserializer(ctxt, type);
        } else {
          deser = StdKeyDeserializers.findStringBasedKeyDeserializer(config, type);
        }  
    } 
    if (deser != null && 
      this._factoryConfig.hasDeserializerModifiers())
      for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers())
        deser = mod.modifyKeyDeserializer(config, type, deser);  
    return deser;
  }
  
  private KeyDeserializer _createEnumKeyDeserializer(DeserializationContext ctxt, JavaType type) throws JsonMappingException {
    DeserializationConfig config = ctxt.getConfig();
    Class<?> enumClass = type.getRawClass();
    BeanDescription beanDesc = config.introspect(type);
    KeyDeserializer des = findKeyDeserializerFromAnnotation(ctxt, (Annotated)beanDesc.getClassInfo());
    if (des != null)
      return des; 
    JsonDeserializer<?> custom = _findCustomEnumDeserializer(enumClass, config, beanDesc);
    if (custom != null)
      return StdKeyDeserializers.constructDelegatingKeyDeserializer(config, type, custom); 
    JsonDeserializer<?> valueDesForKey = findDeserializerFromAnnotation(ctxt, (Annotated)beanDesc.getClassInfo());
    if (valueDesForKey != null)
      return StdKeyDeserializers.constructDelegatingKeyDeserializer(config, type, valueDesForKey); 
    EnumResolver enumRes = constructEnumResolver(enumClass, config, beanDesc);
    EnumResolver byEnumNamingResolver = constructEnumNamingStrategyResolver(config, beanDesc.getClassInfo());
    EnumResolver byToStringResolver = EnumResolver.constructUsingToString(config, beanDesc.getClassInfo());
    EnumResolver byIndexResolver = EnumResolver.constructUsingIndex(config, beanDesc.getClassInfo());
    for (AnnotatedMethod factory : beanDesc.getFactoryMethods()) {
      if (_hasCreatorAnnotation(ctxt, (Annotated)factory)) {
        int argCount = factory.getParameterCount();
        if (argCount == 1) {
          Class<?> returnType = factory.getRawReturnType();
          if (returnType.isAssignableFrom(enumClass)) {
            if (factory.getRawParameterType(0) != String.class)
              continue; 
            if (config.canOverrideAccessModifiers())
              ClassUtil.checkAndFixAccess(factory.getMember(), ctxt
                  .isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS)); 
            return StdKeyDeserializers.constructEnumKeyDeserializer(enumRes, factory, byEnumNamingResolver, byToStringResolver, byIndexResolver);
          } 
        } 
        throw new IllegalArgumentException("Unsuitable method (" + factory + ") decorated with @JsonCreator (for Enum type " + enumClass
            .getName() + ")");
      } 
    } 
    return StdKeyDeserializers.constructEnumKeyDeserializer(enumRes, byEnumNamingResolver, byToStringResolver, byIndexResolver);
  }
  
  public boolean hasExplicitDeserializerFor(DeserializationConfig config, Class<?> valueType) {
    while (valueType.isArray())
      valueType = valueType.getComponentType(); 
    if (Enum.class.isAssignableFrom(valueType))
      return true; 
    String clsName = valueType.getName();
    if (clsName.startsWith("java.")) {
      if (Collection.class.isAssignableFrom(valueType))
        return true; 
      if (Map.class.isAssignableFrom(valueType))
        return true; 
      if (Number.class.isAssignableFrom(valueType))
        return (NumberDeserializers.find(valueType, clsName) != null); 
      if (JdkDeserializers.hasDeserializerFor(valueType) || valueType == CLASS_STRING || valueType == Boolean.class || valueType == EnumMap.class || valueType == AtomicReference.class)
        return true; 
      if (DateDeserializers.hasDeserializerFor(valueType))
        return true; 
    } else {
      if (clsName.startsWith("com.fasterxml."))
        return (JsonNode.class.isAssignableFrom(valueType) || valueType == TokenBuffer.class); 
      return OptionalHandlerFactory.instance.hasDeserializerFor(valueType);
    } 
    return false;
  }
  
  public TypeDeserializer findPropertyTypeDeserializer(DeserializationConfig config, JavaType baseType, AnnotatedMember annotated) throws JsonMappingException {
    AnnotationIntrospector ai = config.getAnnotationIntrospector();
    TypeResolverBuilder<?> b = ai.findPropertyTypeResolver((MapperConfig)config, annotated, baseType);
    if (b == null)
      return findTypeDeserializer(config, baseType); 
    Collection<NamedType> subtypes = config.getSubtypeResolver().collectAndResolveSubtypesByTypeId((MapperConfig)config, annotated, baseType);
    try {
      return b.buildTypeDeserializer(config, baseType, subtypes);
    } catch (IllegalArgumentException|IllegalStateException e0) {
      throw InvalidDefinitionException.from((JsonParser)null, 
          ClassUtil.exceptionMessage(e0), baseType)
        .withCause(e0);
    } 
  }
  
  public TypeDeserializer findPropertyContentTypeDeserializer(DeserializationConfig config, JavaType containerType, AnnotatedMember propertyEntity) throws JsonMappingException {
    AnnotationIntrospector ai = config.getAnnotationIntrospector();
    TypeResolverBuilder<?> b = ai.findPropertyContentTypeResolver((MapperConfig)config, propertyEntity, containerType);
    JavaType contentType = containerType.getContentType();
    if (b == null)
      return findTypeDeserializer(config, contentType); 
    Collection<NamedType> subtypes = config.getSubtypeResolver().collectAndResolveSubtypesByTypeId((MapperConfig)config, propertyEntity, contentType);
    return b.buildTypeDeserializer(config, contentType, subtypes);
  }
  
  public JsonDeserializer<?> findDefaultDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
    Class<?> rawType = type.getRawClass();
    if (rawType == CLASS_OBJECT || rawType == CLASS_SERIALIZABLE) {
      JavaType lt, mt;
      DeserializationConfig config = ctxt.getConfig();
      if (this._factoryConfig.hasAbstractTypeResolvers()) {
        lt = _findRemappedType(config, List.class);
        mt = _findRemappedType(config, Map.class);
      } else {
        lt = mt = null;
      } 
      return (JsonDeserializer<?>)new UntypedObjectDeserializer(lt, mt);
    } 
    if (rawType == CLASS_STRING || rawType == CLASS_CHAR_SEQUENCE)
      return (JsonDeserializer<?>)StringDeserializer.instance; 
    if (rawType == CLASS_ITERABLE) {
      TypeFactory tf = ctxt.getTypeFactory();
      JavaType[] tps = tf.findTypeParameters(type, CLASS_ITERABLE);
      JavaType elemType = (tps == null || tps.length != 1) ? TypeFactory.unknownType() : tps[0];
      CollectionType ct = tf.constructCollectionType(Collection.class, elemType);
      return createCollectionDeserializer(ctxt, ct, beanDesc);
    } 
    if (rawType == CLASS_MAP_ENTRY) {
      JavaType kt = type.containedTypeOrUnknown(0);
      JavaType vt = type.containedTypeOrUnknown(1);
      TypeDeserializer vts = (TypeDeserializer)vt.getTypeHandler();
      if (vts == null)
        vts = findTypeDeserializer(ctxt.getConfig(), vt); 
      JsonDeserializer<Object> valueDeser = (JsonDeserializer<Object>)vt.getValueHandler();
      KeyDeserializer keyDes = (KeyDeserializer)kt.getValueHandler();
      return (JsonDeserializer<?>)new MapEntryDeserializer(type, keyDes, valueDeser, vts);
    } 
    String clsName = rawType.getName();
    if (rawType.isPrimitive() || clsName.startsWith("java.")) {
      JsonDeserializer<?> jsonDeserializer = NumberDeserializers.find(rawType, clsName);
      if (jsonDeserializer == null)
        jsonDeserializer = DateDeserializers.find(rawType, clsName); 
      if (jsonDeserializer != null)
        return jsonDeserializer; 
    } 
    if (rawType == TokenBuffer.class)
      return (JsonDeserializer<?>)new TokenBufferDeserializer(); 
    JsonDeserializer<?> deser = findOptionalStdDeserializer(ctxt, type, beanDesc);
    if (deser != null)
      return deser; 
    return JdkDeserializers.find(ctxt, rawType, clsName);
  }
  
  protected JavaType _findRemappedType(DeserializationConfig config, Class<?> rawType) throws JsonMappingException {
    JavaType type = mapAbstractType(config, config.constructType(rawType));
    return (type == null || type.hasRawClass(rawType)) ? null : type;
  }
  
  protected JsonDeserializer<?> _findCustomTreeNodeDeserializer(Class<? extends JsonNode> type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
    for (Deserializers d : this._factoryConfig.deserializers()) {
      JsonDeserializer<?> deser = d.findTreeNodeDeserializer(type, config, beanDesc);
      if (deser != null)
        return deser; 
    } 
    return null;
  }
  
  protected JsonDeserializer<?> _findCustomReferenceDeserializer(ReferenceType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer contentTypeDeserializer, JsonDeserializer<?> contentDeserializer) throws JsonMappingException {
    for (Deserializers d : this._factoryConfig.deserializers()) {
      JsonDeserializer<?> deser = d.findReferenceDeserializer(type, config, beanDesc, contentTypeDeserializer, contentDeserializer);
      if (deser != null)
        return deser; 
    } 
    return null;
  }
  
  protected JsonDeserializer<Object> _findCustomBeanDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
    for (Deserializers d : this._factoryConfig.deserializers()) {
      JsonDeserializer<?> deser = d.findBeanDeserializer(type, config, beanDesc);
      if (deser != null)
        return (JsonDeserializer)deser; 
    } 
    return null;
  }
  
  protected JsonDeserializer<?> _findCustomArrayDeserializer(ArrayType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
    for (Deserializers d : this._factoryConfig.deserializers()) {
      JsonDeserializer<?> deser = d.findArrayDeserializer(type, config, beanDesc, elementTypeDeserializer, elementDeserializer);
      if (deser != null)
        return deser; 
    } 
    return null;
  }
  
  protected JsonDeserializer<?> _findCustomCollectionDeserializer(CollectionType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
    for (Deserializers d : this._factoryConfig.deserializers()) {
      JsonDeserializer<?> deser = d.findCollectionDeserializer(type, config, beanDesc, elementTypeDeserializer, elementDeserializer);
      if (deser != null)
        return deser; 
    } 
    return null;
  }
  
  protected JsonDeserializer<?> _findCustomCollectionLikeDeserializer(CollectionLikeType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
    for (Deserializers d : this._factoryConfig.deserializers()) {
      JsonDeserializer<?> deser = d.findCollectionLikeDeserializer(type, config, beanDesc, elementTypeDeserializer, elementDeserializer);
      if (deser != null)
        return deser; 
    } 
    return null;
  }
  
  protected JsonDeserializer<?> _findCustomEnumDeserializer(Class<?> type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
    for (Deserializers d : this._factoryConfig.deserializers()) {
      JsonDeserializer<?> deser = d.findEnumDeserializer(type, config, beanDesc);
      if (deser != null)
        return deser; 
    } 
    return null;
  }
  
  protected JsonDeserializer<?> _findCustomMapDeserializer(MapType type, DeserializationConfig config, BeanDescription beanDesc, KeyDeserializer keyDeserializer, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
    for (Deserializers d : this._factoryConfig.deserializers()) {
      JsonDeserializer<?> deser = d.findMapDeserializer(type, config, beanDesc, keyDeserializer, elementTypeDeserializer, elementDeserializer);
      if (deser != null)
        return deser; 
    } 
    return null;
  }
  
  protected JsonDeserializer<?> _findCustomMapLikeDeserializer(MapLikeType type, DeserializationConfig config, BeanDescription beanDesc, KeyDeserializer keyDeserializer, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
    for (Deserializers d : this._factoryConfig.deserializers()) {
      JsonDeserializer<?> deser = d.findMapLikeDeserializer(type, config, beanDesc, keyDeserializer, elementTypeDeserializer, elementDeserializer);
      if (deser != null)
        return deser; 
    } 
    return null;
  }
  
  protected JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationContext ctxt, Annotated ann) throws JsonMappingException {
    AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
    if (intr != null) {
      Object deserDef = intr.findDeserializer(ann);
      if (deserDef != null)
        return ctxt.deserializerInstance(ann, deserDef); 
    } 
    return null;
  }
  
  protected KeyDeserializer findKeyDeserializerFromAnnotation(DeserializationContext ctxt, Annotated ann) throws JsonMappingException {
    AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
    if (intr != null) {
      Object deserDef = intr.findKeyDeserializer(ann);
      if (deserDef != null)
        return ctxt.keyDeserializerInstance(ann, deserDef); 
    } 
    return null;
  }
  
  protected JsonDeserializer<Object> findContentDeserializerFromAnnotation(DeserializationContext ctxt, Annotated ann) throws JsonMappingException {
    AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
    if (intr != null) {
      Object deserDef = intr.findContentDeserializer(ann);
      if (deserDef != null)
        return ctxt.deserializerInstance(ann, deserDef); 
    } 
    return null;
  }
  
  protected JavaType resolveMemberAndTypeAnnotations(DeserializationContext ctxt, AnnotatedMember member, JavaType type) throws JsonMappingException {
    MapLikeType mapLikeType;
    AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
    if (intr == null)
      return type; 
    if (type.isMapLikeType()) {
      JavaType keyType = type.getKeyType();
      if (keyType != null) {
        Object kdDef = intr.findKeyDeserializer((Annotated)member);
        KeyDeserializer kd = ctxt.keyDeserializerInstance((Annotated)member, kdDef);
        if (kd != null) {
          mapLikeType = ((MapLikeType)type).withKeyValueHandler(kd);
          keyType = mapLikeType.getKeyType();
        } 
      } 
    } 
    if (mapLikeType.hasContentType()) {
      Object cdDef = intr.findContentDeserializer((Annotated)member);
      JsonDeserializer<?> cd = ctxt.deserializerInstance((Annotated)member, cdDef);
      if (cd != null)
        javaType = mapLikeType.withContentValueHandler(cd); 
      TypeDeserializer contentTypeDeser = findPropertyContentTypeDeserializer(ctxt
          .getConfig(), javaType, member);
      if (contentTypeDeser != null)
        javaType = javaType.withContentTypeHandler(contentTypeDeser); 
    } 
    TypeDeserializer valueTypeDeser = findPropertyTypeDeserializer(ctxt.getConfig(), javaType, member);
    if (valueTypeDeser != null)
      javaType = javaType.withTypeHandler(valueTypeDeser); 
    JavaType javaType = intr.refineDeserializationType((MapperConfig)ctxt.getConfig(), (Annotated)member, javaType);
    return javaType;
  }
  
  protected EnumResolver constructEnumResolver(Class<?> enumClass, DeserializationConfig config, BeanDescription beanDesc) {
    AnnotatedMember jvAcc = beanDesc.findJsonValueAccessor();
    if (jvAcc != null) {
      if (config.canOverrideAccessModifiers())
        ClassUtil.checkAndFixAccess(jvAcc.getMember(), config
            .isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS)); 
      return EnumResolver.constructUsingMethod(config, beanDesc.getClassInfo(), jvAcc);
    } 
    return EnumResolver.constructFor(config, beanDesc.getClassInfo());
  }
  
  protected EnumResolver constructEnumNamingStrategyResolver(DeserializationConfig config, AnnotatedClass annotatedClass) {
    Object namingDef = config.getAnnotationIntrospector().findEnumNamingStrategy((MapperConfig)config, annotatedClass);
    EnumNamingStrategy enumNamingStrategy = EnumNamingStrategyFactory.createEnumNamingStrategyInstance(namingDef, config
        .canOverrideAccessModifiers());
    return (enumNamingStrategy == null) ? null : 
      EnumResolver.constructUsingEnumNamingStrategy(config, annotatedClass, enumNamingStrategy);
  }
  
  @Deprecated
  protected EnumResolver constructEnumNamingStrategyResolver(DeserializationConfig config, Class<?> enumClass, AnnotatedClass annotatedClass) {
    Object namingDef = config.getAnnotationIntrospector().findEnumNamingStrategy((MapperConfig)config, annotatedClass);
    EnumNamingStrategy enumNamingStrategy = EnumNamingStrategyFactory.createEnumNamingStrategyInstance(namingDef, config
        .canOverrideAccessModifiers());
    return (enumNamingStrategy == null) ? null : 
      EnumResolver.constructUsingEnumNamingStrategy(config, enumClass, enumNamingStrategy);
  }
  
  protected boolean _hasCreatorAnnotation(DeserializationContext ctxt, Annotated ann) {
    AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
    if (intr != null) {
      JsonCreator.Mode mode = intr.findCreatorAnnotation((MapperConfig)ctxt.getConfig(), ann);
      return (mode != null && mode != JsonCreator.Mode.DISABLED);
    } 
    return false;
  }
  
  @Deprecated
  protected JavaType modifyTypeByAnnotation(DeserializationContext ctxt, Annotated a, JavaType type) throws JsonMappingException {
    AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
    if (intr == null)
      return type; 
    return intr.refineDeserializationType((MapperConfig)ctxt.getConfig(), a, type);
  }
  
  @Deprecated
  protected JavaType resolveType(DeserializationContext ctxt, BeanDescription beanDesc, JavaType type, AnnotatedMember member) throws JsonMappingException {
    return resolveMemberAndTypeAnnotations(ctxt, member, type);
  }
  
  @Deprecated
  protected AnnotatedMethod _findJsonValueFor(DeserializationConfig config, JavaType enumType) {
    if (enumType == null)
      return null; 
    BeanDescription beanDesc = config.introspect(enumType);
    return beanDesc.findJsonValueMethod();
  }
  
  protected static class ContainerDefaultMappings {
    static final HashMap<String, Class<? extends Collection>> _collectionFallbacks;
    
    static final HashMap<String, Class<? extends Map>> _mapFallbacks;
    
    static {
      HashMap<String, Class<? extends Collection>> hashMap = new HashMap<>();
      Class<ArrayList> clazz1 = ArrayList.class;
      Class<HashSet> clazz2 = HashSet.class;
      hashMap.put(Collection.class.getName(), clazz1);
      hashMap.put(List.class.getName(), clazz1);
      hashMap.put(Set.class.getName(), clazz2);
      hashMap.put(SortedSet.class.getName(), TreeSet.class);
      hashMap.put(Queue.class.getName(), LinkedList.class);
      hashMap.put(AbstractList.class.getName(), clazz1);
      hashMap.put(AbstractSet.class.getName(), clazz2);
      hashMap.put(Deque.class.getName(), LinkedList.class);
      hashMap.put(NavigableSet.class.getName(), TreeSet.class);
      hashMap.put("java.util.SequencedCollection", clazz1);
      hashMap.put("java.util.SequencedSet", LinkedHashSet.class);
      _collectionFallbacks = hashMap;
      HashMap<String, Class<? extends Map>> fallbacks = new HashMap<>();
      Class<LinkedHashMap> clazz = LinkedHashMap.class;
      fallbacks.put(Map.class.getName(), clazz);
      fallbacks.put(AbstractMap.class.getName(), clazz);
      fallbacks.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
      fallbacks.put(SortedMap.class.getName(), TreeMap.class);
      fallbacks.put(NavigableMap.class.getName(), TreeMap.class);
      fallbacks.put(ConcurrentNavigableMap.class.getName(), ConcurrentSkipListMap.class);
      fallbacks.put("java.util.SequencedMap", LinkedHashMap.class);
      _mapFallbacks = fallbacks;
    }
    
    public static Class<?> findCollectionFallback(JavaType type) {
      return _collectionFallbacks.get(type.getRawClass().getName());
    }
    
    public static Class<?> findMapFallback(JavaType type) {
      return _mapFallbacks.get(type.getRawClass().getName());
    }
  }
  
  protected static class CreatorCollectionState {
    public final DeserializationContext context;
    
    public final BeanDescription beanDesc;
    
    public final VisibilityChecker<?> vchecker;
    
    public final CreatorCollector creators;
    
    public final Map<AnnotatedWithParams, BeanPropertyDefinition[]> creatorParams;
    
    private List<CreatorCandidate> _implicitFactoryCandidates;
    
    private int _explicitFactoryCount;
    
    private List<CreatorCandidate> _implicitConstructorCandidates;
    
    private int _explicitConstructorCount;
    
    public CreatorCollectionState(DeserializationContext ctxt, BeanDescription bd, VisibilityChecker<?> vc, CreatorCollector cc, Map<AnnotatedWithParams, BeanPropertyDefinition[]> cp) {
      this.context = ctxt;
      this.beanDesc = bd;
      this.vchecker = vc;
      this.creators = cc;
      this.creatorParams = cp;
    }
    
    public AnnotationIntrospector annotationIntrospector() {
      return this.context.getAnnotationIntrospector();
    }
    
    public void addImplicitFactoryCandidate(CreatorCandidate cc) {
      if (this._implicitFactoryCandidates == null)
        this._implicitFactoryCandidates = new LinkedList<>(); 
      this._implicitFactoryCandidates.add(cc);
    }
    
    public void increaseExplicitFactoryCount() {
      this._explicitFactoryCount++;
    }
    
    public boolean hasExplicitFactories() {
      return (this._explicitFactoryCount > 0);
    }
    
    public boolean hasImplicitFactoryCandidates() {
      return (this._implicitFactoryCandidates != null);
    }
    
    public List<CreatorCandidate> implicitFactoryCandidates() {
      return this._implicitFactoryCandidates;
    }
    
    public void addImplicitConstructorCandidate(CreatorCandidate cc) {
      if (this._implicitConstructorCandidates == null)
        this._implicitConstructorCandidates = new LinkedList<>(); 
      this._implicitConstructorCandidates.add(cc);
    }
    
    public void increaseExplicitConstructorCount() {
      this._explicitConstructorCount++;
    }
    
    public boolean hasExplicitConstructors() {
      return (this._explicitConstructorCount > 0);
    }
    
    public boolean hasImplicitConstructorCandidates() {
      return (this._implicitConstructorCandidates != null);
    }
    
    public List<CreatorCandidate> implicitConstructorCandidates() {
      return this._implicitConstructorCandidates;
    }
  }
}
