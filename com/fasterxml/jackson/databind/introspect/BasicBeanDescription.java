package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BasicBeanDescription extends BeanDescription {
  private static final Class<?>[] NO_VIEWS = new Class[0];
  
  protected final POJOPropertiesCollector _propCollector;
  
  protected final MapperConfig<?> _config;
  
  protected final AnnotationIntrospector _annotationIntrospector;
  
  protected final AnnotatedClass _classInfo;
  
  protected Class<?>[] _defaultViews;
  
  protected boolean _defaultViewsResolved;
  
  protected List<BeanPropertyDefinition> _properties;
  
  protected ObjectIdInfo _objectIdInfo;
  
  protected BasicBeanDescription(POJOPropertiesCollector coll, JavaType type, AnnotatedClass classDef) {
    super(type);
    this._propCollector = coll;
    this._config = coll.getConfig();
    if (this._config == null) {
      this._annotationIntrospector = null;
    } else {
      this._annotationIntrospector = this._config.getAnnotationIntrospector();
    } 
    this._classInfo = classDef;
  }
  
  protected BasicBeanDescription(MapperConfig<?> config, JavaType type, AnnotatedClass classDef, List<BeanPropertyDefinition> props) {
    super(type);
    this._propCollector = null;
    this._config = config;
    if (this._config == null) {
      this._annotationIntrospector = null;
    } else {
      this._annotationIntrospector = this._config.getAnnotationIntrospector();
    } 
    this._classInfo = classDef;
    this._properties = props;
  }
  
  protected BasicBeanDescription(POJOPropertiesCollector coll) {
    this(coll, coll.getType(), coll.getClassDef());
    this._objectIdInfo = coll.getObjectIdInfo();
  }
  
  public static BasicBeanDescription forDeserialization(POJOPropertiesCollector coll) {
    return new BasicBeanDescription(coll);
  }
  
  public static BasicBeanDescription forSerialization(POJOPropertiesCollector coll) {
    return new BasicBeanDescription(coll);
  }
  
  public static BasicBeanDescription forOtherUse(MapperConfig<?> config, JavaType type, AnnotatedClass ac) {
    return new BasicBeanDescription(config, type, ac, 
        Collections.emptyList());
  }
  
  protected List<BeanPropertyDefinition> _properties() {
    if (this._properties == null)
      this._properties = this._propCollector.getProperties(); 
    return this._properties;
  }
  
  public boolean removeProperty(String propName) {
    Iterator<BeanPropertyDefinition> it = _properties().iterator();
    while (it.hasNext()) {
      BeanPropertyDefinition prop = it.next();
      if (prop.getName().equals(propName)) {
        it.remove();
        return true;
      } 
    } 
    return false;
  }
  
  public boolean addProperty(BeanPropertyDefinition def) {
    if (hasProperty(def.getFullName()))
      return false; 
    _properties().add(def);
    return true;
  }
  
  public boolean hasProperty(PropertyName name) {
    return (findProperty(name) != null);
  }
  
  public BeanPropertyDefinition findProperty(PropertyName name) {
    for (BeanPropertyDefinition prop : _properties()) {
      if (prop.hasName(name))
        return prop; 
    } 
    return null;
  }
  
  public AnnotatedClass getClassInfo() {
    return this._classInfo;
  }
  
  public ObjectIdInfo getObjectIdInfo() {
    return this._objectIdInfo;
  }
  
  public List<BeanPropertyDefinition> findProperties() {
    return _properties();
  }
  
  public AnnotatedMember findJsonKeyAccessor() {
    return (this._propCollector == null) ? null : this._propCollector
      .getJsonKeyAccessor();
  }
  
  @Deprecated
  public AnnotatedMethod findJsonValueMethod() {
    return (this._propCollector == null) ? null : this._propCollector
      .getJsonValueMethod();
  }
  
  public AnnotatedMember findJsonValueAccessor() {
    return (this._propCollector == null) ? null : this._propCollector
      .getJsonValueAccessor();
  }
  
  public Set<String> getIgnoredPropertyNames() {
    Set<String> ign = (this._propCollector == null) ? null : this._propCollector.getIgnoredPropertyNames();
    if (ign == null)
      return Collections.emptySet(); 
    return ign;
  }
  
  public boolean hasKnownClassAnnotations() {
    return this._classInfo.hasAnnotations();
  }
  
  public Annotations getClassAnnotations() {
    return this._classInfo.getAnnotations();
  }
  
  @Deprecated
  public TypeBindings bindingsForBeanType() {
    return this._type.getBindings();
  }
  
  @Deprecated
  public JavaType resolveType(Type jdkType) {
    return this._config.getTypeFactory().resolveMemberType(jdkType, this._type.getBindings());
  }
  
  public AnnotatedConstructor findDefaultConstructor() {
    return this._classInfo.getDefaultConstructor();
  }
  
  public AnnotatedMember findAnySetterAccessor() throws IllegalArgumentException {
    if (this._propCollector != null) {
      AnnotatedMethod anyMethod = this._propCollector.getAnySetterMethod();
      if (anyMethod != null) {
        Class<?> type = anyMethod.getRawParameterType(0);
        if (type != String.class && type != Object.class)
          throw new IllegalArgumentException(String.format("Invalid 'any-setter' annotation on method '%s()': first argument not of type String or Object, but %s", new Object[] { anyMethod
                  
                  .getName(), type.getName() })); 
        return anyMethod;
      } 
      AnnotatedMember anyField = this._propCollector.getAnySetterField();
      if (anyField != null) {
        Class<?> type = anyField.getRawType();
        if (!Map.class.isAssignableFrom(type) && 
          !JsonNode.class.isAssignableFrom(type))
          throw new IllegalArgumentException(String.format("Invalid 'any-setter' annotation on field '%s': type is not instance of `java.util.Map` or `JsonNode`", new Object[] { anyField
                  
                  .getName() })); 
        return anyField;
      } 
    } 
    return null;
  }
  
  public Map<Object, AnnotatedMember> findInjectables() {
    if (this._propCollector != null)
      return this._propCollector.getInjectables(); 
    return Collections.emptyMap();
  }
  
  public List<AnnotatedConstructor> getConstructors() {
    return this._classInfo.getConstructors();
  }
  
  public List<AnnotatedAndMetadata<AnnotatedConstructor, JsonCreator.Mode>> getConstructorsWithMode() {
    List<AnnotatedConstructor> allCtors = this._classInfo.getConstructors();
    if (allCtors.isEmpty())
      return Collections.emptyList(); 
    List<AnnotatedAndMetadata<AnnotatedConstructor, JsonCreator.Mode>> result = new ArrayList<>();
    for (AnnotatedConstructor ctor : allCtors) {
      JsonCreator.Mode mode = this._annotationIntrospector.findCreatorAnnotation(this._config, ctor);
      if (mode == JsonCreator.Mode.DISABLED)
        continue; 
      result.add(AnnotatedAndMetadata.of(ctor, mode));
    } 
    return result;
  }
  
  public Object instantiateBean(boolean fixAccess) {
    AnnotatedConstructor ac = this._classInfo.getDefaultConstructor();
    if (ac == null)
      return null; 
    if (fixAccess)
      ac.fixAccess(this._config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS)); 
    try {
      return ac.call();
    } catch (Exception e) {
      Throwable t = e;
      while (t.getCause() != null)
        t = t.getCause(); 
      ClassUtil.throwIfError(t);
      ClassUtil.throwIfRTE(t);
      throw new IllegalArgumentException("Failed to instantiate bean of type " + this._classInfo
          .getAnnotated().getName() + ": (" + t.getClass().getName() + ") " + 
          ClassUtil.exceptionMessage(t), t);
    } 
  }
  
  public AnnotatedMethod findMethod(String name, Class<?>[] paramTypes) {
    return this._classInfo.findMethod(name, paramTypes);
  }
  
  public JsonFormat.Value findExpectedFormat(JsonFormat.Value defValue) {
    if (this._annotationIntrospector != null) {
      JsonFormat.Value value = this._annotationIntrospector.findFormat(this._classInfo);
      if (value != null)
        if (defValue == null) {
          defValue = value;
        } else {
          defValue = defValue.withOverrides(value);
        }  
    } 
    JsonFormat.Value v = this._config.getDefaultPropertyFormat(this._classInfo.getRawType());
    if (v != null)
      if (defValue == null) {
        defValue = v;
      } else {
        defValue = defValue.withOverrides(v);
      }  
    return defValue;
  }
  
  public Class<?>[] findDefaultViews() {
    if (!this._defaultViewsResolved) {
      this._defaultViewsResolved = true;
      Class<?>[] def = (this._annotationIntrospector == null) ? null : this._annotationIntrospector.findViews(this._classInfo);
      if (def == null && 
        !this._config.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION))
        def = NO_VIEWS; 
      this._defaultViews = def;
    } 
    return this._defaultViews;
  }
  
  public Converter<Object, Object> findSerializationConverter() {
    if (this._annotationIntrospector == null)
      return null; 
    return _createConverter(this._annotationIntrospector.findSerializationConverter(this._classInfo));
  }
  
  public JsonInclude.Value findPropertyInclusion(JsonInclude.Value defValue) {
    if (this._annotationIntrospector != null) {
      JsonInclude.Value incl = this._annotationIntrospector.findPropertyInclusion(this._classInfo);
      if (incl != null)
        return (defValue == null) ? incl : defValue.withOverrides(incl); 
    } 
    return defValue;
  }
  
  public AnnotatedMember findAnyGetter() throws IllegalArgumentException {
    if (this._propCollector != null) {
      AnnotatedMember anyGetter = this._propCollector.getAnyGetterMethod();
      if (anyGetter != null) {
        Class<?> type = anyGetter.getRawType();
        if (!Map.class.isAssignableFrom(type))
          throw new IllegalArgumentException(String.format("Invalid 'any-getter' annotation on method %s(): return type is not instance of java.util.Map", new Object[] { anyGetter
                  
                  .getName() })); 
        return anyGetter;
      } 
      AnnotatedMember anyField = this._propCollector.getAnyGetterField();
      if (anyField != null) {
        Class<?> type = anyField.getRawType();
        if (!Map.class.isAssignableFrom(type))
          throw new IllegalArgumentException(String.format("Invalid 'any-getter' annotation on field '%s': type is not instance of java.util.Map", new Object[] { anyField
                  
                  .getName() })); 
        return anyField;
      } 
    } 
    return null;
  }
  
  public List<BeanPropertyDefinition> findBackReferences() {
    List<BeanPropertyDefinition> result = null;
    HashSet<String> names = null;
    for (BeanPropertyDefinition property : _properties()) {
      AnnotationIntrospector.ReferenceProperty refDef = property.findReferenceType();
      if (refDef == null || !refDef.isBackReference())
        continue; 
      String refName = refDef.getName();
      if (result == null) {
        result = new ArrayList<>();
        names = new HashSet<>();
        names.add(refName);
      } else if (!names.add(refName)) {
        throw new IllegalArgumentException("Multiple back-reference properties with name " + ClassUtil.name(refName));
      } 
      result.add(property);
    } 
    return result;
  }
  
  @Deprecated
  public Map<String, AnnotatedMember> findBackReferenceProperties() {
    List<BeanPropertyDefinition> props = findBackReferences();
    if (props == null)
      return null; 
    Map<String, AnnotatedMember> result = new HashMap<>();
    for (BeanPropertyDefinition prop : props)
      result.put(prop.getName(), prop.getMutator()); 
    return result;
  }
  
  public List<AnnotatedMethod> getFactoryMethods() {
    List<AnnotatedMethod> candidates = this._classInfo.getFactoryMethods();
    if (candidates.isEmpty())
      return candidates; 
    List<AnnotatedMethod> result = null;
    for (AnnotatedMethod am : candidates) {
      if (isFactoryMethod(am)) {
        if (result == null)
          result = new ArrayList<>(); 
        result.add(am);
      } 
    } 
    if (result == null)
      return Collections.emptyList(); 
    return result;
  }
  
  public List<AnnotatedAndMetadata<AnnotatedMethod, JsonCreator.Mode>> getFactoryMethodsWithMode() {
    List<AnnotatedMethod> candidates = this._classInfo.getFactoryMethods();
    if (candidates.isEmpty())
      return Collections.emptyList(); 
    List<AnnotatedAndMetadata<AnnotatedMethod, JsonCreator.Mode>> result = null;
    for (AnnotatedMethod am : candidates) {
      AnnotatedAndMetadata<AnnotatedMethod, JsonCreator.Mode> match = findFactoryMethodMetadata(am);
      if (match != null) {
        if (result == null)
          result = new ArrayList<>(); 
        result.add(match);
      } 
    } 
    if (result == null)
      return Collections.emptyList(); 
    return result;
  }
  
  @Deprecated
  public Constructor<?> findSingleArgConstructor(Class<?>... argTypes) {
    for (AnnotatedConstructor ac : this._classInfo.getConstructors()) {
      if (ac.getParameterCount() == 1) {
        Class<?> actArg = ac.getRawParameterType(0);
        for (Class<?> expArg : argTypes) {
          if (expArg == actArg)
            return ac.getAnnotated(); 
        } 
      } 
    } 
    return null;
  }
  
  @Deprecated
  public Method findFactoryMethod(Class<?>... expArgTypes) {
    for (AnnotatedMethod am : this._classInfo.getFactoryMethods()) {
      if (isFactoryMethod(am) && am.getParameterCount() == 1) {
        Class<?> actualArgType = am.getRawParameterType(0);
        for (Class<?> expArgType : expArgTypes) {
          if (actualArgType.isAssignableFrom(expArgType))
            return am.getAnnotated(); 
        } 
      } 
    } 
    return null;
  }
  
  protected boolean isFactoryMethod(AnnotatedMethod am) {
    Class<?> rt = am.getRawReturnType();
    if (!getBeanClass().isAssignableFrom(rt))
      return false; 
    JsonCreator.Mode mode = this._annotationIntrospector.findCreatorAnnotation(this._config, am);
    if (mode != null && mode != JsonCreator.Mode.DISABLED)
      return true; 
    String name = am.getName();
    if ("valueOf".equals(name) && 
      am.getParameterCount() == 1)
      return true; 
    if ("fromString".equals(name) && 
      am.getParameterCount() == 1) {
      Class<?> cls = am.getRawParameterType(0);
      if (cls == String.class || CharSequence.class.isAssignableFrom(cls))
        return true; 
    } 
    return false;
  }
  
  protected AnnotatedAndMetadata<AnnotatedMethod, JsonCreator.Mode> findFactoryMethodMetadata(AnnotatedMethod am) {
    Class<?> rt = am.getRawReturnType();
    if (!getBeanClass().isAssignableFrom(rt))
      return null; 
    JsonCreator.Mode mode = this._annotationIntrospector.findCreatorAnnotation(this._config, am);
    if (mode != null) {
      if (mode == JsonCreator.Mode.DISABLED)
        return null; 
      return AnnotatedAndMetadata.of(am, mode);
    } 
    String name = am.getName();
    if ("valueOf".equals(name) && 
      am.getParameterCount() == 1)
      return AnnotatedAndMetadata.of(am, mode); 
    if ("fromString".equals(name) && 
      am.getParameterCount() == 1) {
      Class<?> cls = am.getRawParameterType(0);
      if (cls == String.class || CharSequence.class.isAssignableFrom(cls))
        return AnnotatedAndMetadata.of(am, mode); 
    } 
    return null;
  }
  
  @Deprecated
  protected PropertyName _findCreatorPropertyName(AnnotatedParameter param) {
    PropertyName name = this._annotationIntrospector.findNameForDeserialization(param);
    if (name == null || name.isEmpty()) {
      String str = this._annotationIntrospector.findImplicitPropertyName(param);
      if (str != null && !str.isEmpty())
        name = PropertyName.construct(str); 
    } 
    return name;
  }
  
  public Class<?> findPOJOBuilder() {
    return (this._annotationIntrospector == null) ? null : this._annotationIntrospector
      .findPOJOBuilder(this._classInfo);
  }
  
  public JsonPOJOBuilder.Value findPOJOBuilderConfig() {
    return (this._annotationIntrospector == null) ? null : this._annotationIntrospector
      .findPOJOBuilderConfig(this._classInfo);
  }
  
  public Converter<Object, Object> findDeserializationConverter() {
    if (this._annotationIntrospector == null)
      return null; 
    return _createConverter(this._annotationIntrospector.findDeserializationConverter(this._classInfo));
  }
  
  public String findClassDescription() {
    return (this._annotationIntrospector == null) ? null : this._annotationIntrospector
      .findClassDescription(this._classInfo);
  }
  
  @Deprecated
  public LinkedHashMap<String, AnnotatedField> _findPropertyFields(Collection<String> ignoredProperties, boolean forSerialization) {
    LinkedHashMap<String, AnnotatedField> results = new LinkedHashMap<>();
    for (BeanPropertyDefinition property : _properties()) {
      AnnotatedField f = property.getField();
      if (f != null) {
        String name = property.getName();
        if (ignoredProperties != null && 
          ignoredProperties.contains(name))
          continue; 
        results.put(name, f);
      } 
    } 
    return results;
  }
  
  protected Converter<Object, Object> _createConverter(Object converterDef) {
    if (converterDef == null)
      return null; 
    if (converterDef instanceof Converter)
      return (Converter<Object, Object>)converterDef; 
    if (!(converterDef instanceof Class))
      throw new IllegalStateException("AnnotationIntrospector returned Converter definition of type " + converterDef
          .getClass().getName() + "; expected type Converter or Class<Converter> instead"); 
    Class<?> converterClass = (Class)converterDef;
    if (converterClass == Converter.None.class || ClassUtil.isBogusClass(converterClass))
      return null; 
    if (!Converter.class.isAssignableFrom(converterClass))
      throw new IllegalStateException("AnnotationIntrospector returned Class " + converterClass
          .getName() + "; expected Class<Converter>"); 
    HandlerInstantiator hi = this._config.getHandlerInstantiator();
    Converter<?, ?> conv = (hi == null) ? null : hi.converterInstance(this._config, this._classInfo, converterClass);
    if (conv == null)
      conv = (Converter<?, ?>)ClassUtil.createInstance(converterClass, this._config
          .canOverrideAccessModifiers()); 
    return (Converter)conv;
  }
}
