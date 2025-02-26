package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.EnumNaming;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.ext.Java7Support;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.AttributePropertyWriter;
import com.fasterxml.jackson.databind.ser.std.RawSerializer;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.ExceptionUtil;
import com.fasterxml.jackson.databind.util.LRUMap;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JacksonAnnotationIntrospector extends AnnotationIntrospector implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private static final Class<? extends Annotation>[] ANNOTATIONS_TO_INFER_SER = new Class[] { JsonSerialize.class, JsonView.class, JsonFormat.class, JsonTypeInfo.class, JsonRawValue.class, JsonUnwrapped.class, JsonBackReference.class, JsonManagedReference.class };
  
  private static final Class<? extends Annotation>[] ANNOTATIONS_TO_INFER_DESER = new Class[] { JsonDeserialize.class, JsonView.class, JsonFormat.class, JsonTypeInfo.class, JsonUnwrapped.class, JsonBackReference.class, JsonManagedReference.class, JsonMerge.class };
  
  private static final Java7Support _java7Helper;
  
  static {
    Java7Support x = null;
    try {
      x = Java7Support.instance();
    } catch (Throwable t) {
      ExceptionUtil.rethrowIfFatal(t);
    } 
    _java7Helper = x;
  }
  
  protected transient LRUMap<String, Boolean> _annotationsInside = new LRUMap(48, 48);
  
  protected boolean _cfgConstructorPropertiesImpliesCreator = true;
  
  public Version version() {
    return PackageVersion.VERSION;
  }
  
  protected Object readResolve() {
    if (this._annotationsInside == null)
      this._annotationsInside = new LRUMap(48, 48); 
    return this;
  }
  
  public JacksonAnnotationIntrospector setConstructorPropertiesImpliesCreator(boolean b) {
    this._cfgConstructorPropertiesImpliesCreator = b;
    return this;
  }
  
  public boolean isAnnotationBundle(Annotation ann) {
    Class<?> type = ann.annotationType();
    String typeName = type.getName();
    Boolean b = (Boolean)this._annotationsInside.get(typeName);
    if (b == null) {
      b = Boolean.valueOf((type.getAnnotation(JacksonAnnotationsInside.class) != null));
      this._annotationsInside.putIfAbsent(typeName, b);
    } 
    return b.booleanValue();
  }
  
  @Deprecated
  public String findEnumValue(Enum<?> value) {
    try {
      Field f = value.getDeclaringClass().getField(value.name());
      if (f != null) {
        JsonProperty prop = f.<JsonProperty>getAnnotation(JsonProperty.class);
        if (prop != null) {
          String n = prop.value();
          if (n != null && !n.isEmpty())
            return n; 
        } 
      } 
    } catch (SecurityException securityException) {
    
    } catch (NoSuchFieldException noSuchFieldException) {}
    return value.name();
  }
  
  @Deprecated
  public String[] findEnumValues(Class<?> enumType, Enum<?>[] enumValues, String[] names) {
    HashMap<String, String> expl = null;
    for (Field f : enumType.getDeclaredFields()) {
      if (f.isEnumConstant()) {
        JsonProperty prop = f.<JsonProperty>getAnnotation(JsonProperty.class);
        if (prop != null) {
          String n = prop.value();
          if (!n.isEmpty()) {
            if (expl == null)
              expl = new HashMap<>(); 
            expl.put(f.getName(), n);
          } 
        } 
      } 
    } 
    if (expl != null)
      for (int i = 0, end = enumValues.length; i < end; i++) {
        String defName = enumValues[i].name();
        String explValue = expl.get(defName);
        if (explValue != null)
          names[i] = explValue; 
      }  
    return names;
  }
  
  public String[] findEnumValues(MapperConfig<?> config, AnnotatedClass annotatedClass, Enum<?>[] enumValues, String[] names) {
    Map<String, String> enumToPropertyMap = new LinkedHashMap<>();
    for (AnnotatedField field : annotatedClass.fields()) {
      JsonProperty property = field.<JsonProperty>getAnnotation(JsonProperty.class);
      if (property != null) {
        String propValue = property.value();
        if (propValue != null && !propValue.isEmpty())
          enumToPropertyMap.put(field.getName(), propValue); 
      } 
    } 
    for (int i = 0, end = enumValues.length; i < end; i++) {
      String defName = enumValues[i].name();
      String explValue = enumToPropertyMap.get(defName);
      if (explValue != null)
        names[i] = explValue; 
    } 
    return names;
  }
  
  @Deprecated
  public void findEnumAliases(Class<?> enumType, Enum<?>[] enumValues, String[][] aliasList) {
    for (Field f : enumType.getDeclaredFields()) {
      if (f.isEnumConstant()) {
        JsonAlias aliasAnnotation = f.<JsonAlias>getAnnotation(JsonAlias.class);
        if (aliasAnnotation != null) {
          String[] aliases = aliasAnnotation.value();
          if (aliases.length != 0) {
            String name = f.getName();
            for (int i = 0, end = enumValues.length; i < end; i++) {
              if (name.equals(enumValues[i].name()))
                aliasList[i] = aliases; 
            } 
          } 
        } 
      } 
    } 
  }
  
  public void findEnumAliases(MapperConfig<?> config, AnnotatedClass annotatedClass, Enum<?>[] enumValues, String[][] aliasList) {
    HashMap<String, String[]> enumToAliasMap = (HashMap)new HashMap<>();
    for (AnnotatedField field : annotatedClass.fields()) {
      JsonAlias alias = field.<JsonAlias>getAnnotation(JsonAlias.class);
      if (alias != null)
        enumToAliasMap.putIfAbsent(field.getName(), alias.value()); 
    } 
    for (int i = 0, end = enumValues.length; i < end; i++) {
      Enum<?> enumValue = enumValues[i];
      aliasList[i] = enumToAliasMap.getOrDefault(enumValue.name(), new String[0]);
    } 
  }
  
  @Deprecated
  public Enum<?> findDefaultEnumValue(Class<Enum<?>> enumCls) {
    return ClassUtil.findFirstAnnotatedEnumValue(enumCls, JsonEnumDefaultValue.class);
  }
  
  public Enum<?> findDefaultEnumValue(AnnotatedClass annotatedClass, Enum<?>[] enumValues) {
    for (Annotated field : annotatedClass.fields()) {
      if (!field.getType().isEnumType())
        continue; 
      JsonEnumDefaultValue found = (JsonEnumDefaultValue)_findAnnotation(field, JsonEnumDefaultValue.class);
      if (found == null)
        continue; 
      for (Enum<?> enumValue : enumValues) {
        if (enumValue.name().equals(field.getName()))
          return enumValue; 
      } 
    } 
    return null;
  }
  
  public PropertyName findRootName(AnnotatedClass ac) {
    JsonRootName ann = (JsonRootName)_findAnnotation(ac, JsonRootName.class);
    if (ann == null)
      return null; 
    String ns = ann.namespace();
    if (ns != null && ns.isEmpty())
      ns = null; 
    return PropertyName.construct(ann.value(), ns);
  }
  
  public Boolean isIgnorableType(AnnotatedClass ac) {
    JsonIgnoreType ignore = (JsonIgnoreType)_findAnnotation(ac, JsonIgnoreType.class);
    return (ignore == null) ? null : Boolean.valueOf(ignore.value());
  }
  
  public JsonIgnoreProperties.Value findPropertyIgnoralByName(MapperConfig<?> config, Annotated a) {
    JsonIgnoreProperties v = (JsonIgnoreProperties)_findAnnotation(a, JsonIgnoreProperties.class);
    if (v == null)
      return JsonIgnoreProperties.Value.empty(); 
    return JsonIgnoreProperties.Value.from(v);
  }
  
  @Deprecated
  public JsonIgnoreProperties.Value findPropertyIgnorals(Annotated ac) {
    return findPropertyIgnoralByName((MapperConfig<?>)null, ac);
  }
  
  public JsonIncludeProperties.Value findPropertyInclusionByName(MapperConfig<?> config, Annotated a) {
    JsonIncludeProperties v = (JsonIncludeProperties)_findAnnotation(a, JsonIncludeProperties.class);
    if (v == null)
      return JsonIncludeProperties.Value.all(); 
    return JsonIncludeProperties.Value.from(v);
  }
  
  public Object findFilterId(Annotated a) {
    JsonFilter ann = (JsonFilter)_findAnnotation(a, JsonFilter.class);
    if (ann != null) {
      String id = ann.value();
      if (!id.isEmpty())
        return id; 
    } 
    return null;
  }
  
  public Object findNamingStrategy(AnnotatedClass ac) {
    JsonNaming ann = (JsonNaming)_findAnnotation(ac, JsonNaming.class);
    return (ann == null) ? null : ann.value();
  }
  
  public Object findEnumNamingStrategy(MapperConfig<?> config, AnnotatedClass ac) {
    EnumNaming ann = (EnumNaming)_findAnnotation(ac, EnumNaming.class);
    return (ann == null) ? null : ann.value();
  }
  
  public String findClassDescription(AnnotatedClass ac) {
    JsonClassDescription ann = (JsonClassDescription)_findAnnotation(ac, JsonClassDescription.class);
    return (ann == null) ? null : ann.value();
  }
  
  public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass ac, VisibilityChecker<?> checker) {
    JsonAutoDetect ann = (JsonAutoDetect)_findAnnotation(ac, JsonAutoDetect.class);
    return (ann == null) ? checker : (VisibilityChecker<?>)checker.with(ann);
  }
  
  public String findImplicitPropertyName(AnnotatedMember m) {
    PropertyName n = _findConstructorName(m);
    return (n == null) ? null : n.getSimpleName();
  }
  
  public List<PropertyName> findPropertyAliases(Annotated m) {
    JsonAlias ann = (JsonAlias)_findAnnotation(m, JsonAlias.class);
    if (ann == null)
      return null; 
    String[] strs = ann.value();
    int len = strs.length;
    if (len == 0)
      return Collections.emptyList(); 
    List<PropertyName> result = new ArrayList<>(len);
    for (int i = 0; i < len; i++)
      result.add(PropertyName.construct(strs[i])); 
    return result;
  }
  
  public boolean hasIgnoreMarker(AnnotatedMember m) {
    return _isIgnorable(m);
  }
  
  public Boolean hasRequiredMarker(AnnotatedMember m) {
    JsonProperty ann = (JsonProperty)_findAnnotation(m, JsonProperty.class);
    if (ann != null)
      return Boolean.valueOf(ann.required()); 
    return null;
  }
  
  public JsonProperty.Access findPropertyAccess(Annotated m) {
    JsonProperty ann = (JsonProperty)_findAnnotation(m, JsonProperty.class);
    if (ann != null)
      return ann.access(); 
    return null;
  }
  
  public String findPropertyDescription(Annotated ann) {
    JsonPropertyDescription desc = (JsonPropertyDescription)_findAnnotation(ann, JsonPropertyDescription.class);
    return (desc == null) ? null : desc.value();
  }
  
  public Integer findPropertyIndex(Annotated ann) {
    JsonProperty prop = (JsonProperty)_findAnnotation(ann, JsonProperty.class);
    if (prop != null) {
      int ix = prop.index();
      if (ix != -1)
        return Integer.valueOf(ix); 
    } 
    return null;
  }
  
  public String findPropertyDefaultValue(Annotated ann) {
    JsonProperty prop = (JsonProperty)_findAnnotation(ann, JsonProperty.class);
    if (prop == null)
      return null; 
    String str = prop.defaultValue();
    return str.isEmpty() ? null : str;
  }
  
  public JsonFormat.Value findFormat(Annotated ann) {
    JsonFormat f = (JsonFormat)_findAnnotation(ann, JsonFormat.class);
    return (f == null) ? null : JsonFormat.Value.from(f);
  }
  
  public AnnotationIntrospector.ReferenceProperty findReferenceType(AnnotatedMember member) {
    JsonManagedReference ref1 = (JsonManagedReference)_findAnnotation(member, JsonManagedReference.class);
    if (ref1 != null)
      return AnnotationIntrospector.ReferenceProperty.managed(ref1.value()); 
    JsonBackReference ref2 = (JsonBackReference)_findAnnotation(member, JsonBackReference.class);
    if (ref2 != null)
      return AnnotationIntrospector.ReferenceProperty.back(ref2.value()); 
    return null;
  }
  
  public NameTransformer findUnwrappingNameTransformer(AnnotatedMember member) {
    JsonUnwrapped ann = (JsonUnwrapped)_findAnnotation(member, JsonUnwrapped.class);
    if (ann == null || !ann.enabled())
      return null; 
    String prefix = ann.prefix();
    String suffix = ann.suffix();
    return NameTransformer.simpleTransformer(prefix, suffix);
  }
  
  public JacksonInject.Value findInjectableValue(AnnotatedMember m) {
    JacksonInject ann = (JacksonInject)_findAnnotation(m, JacksonInject.class);
    if (ann == null)
      return null; 
    JacksonInject.Value v = JacksonInject.Value.from(ann);
    if (!v.hasId()) {
      Object id;
      if (!(m instanceof AnnotatedMethod)) {
        id = m.getRawType().getName();
      } else {
        AnnotatedMethod am = (AnnotatedMethod)m;
        if (am.getParameterCount() == 0) {
          id = m.getRawType().getName();
        } else {
          id = am.getRawParameterType(0).getName();
        } 
      } 
      v = v.withId(id);
    } 
    return v;
  }
  
  @Deprecated
  public Object findInjectableValueId(AnnotatedMember m) {
    JacksonInject.Value v = findInjectableValue(m);
    return (v == null) ? null : v.getId();
  }
  
  public Class<?>[] findViews(Annotated a) {
    JsonView ann = (JsonView)_findAnnotation(a, JsonView.class);
    return (ann == null) ? null : ann.value();
  }
  
  public AnnotatedMethod resolveSetterConflict(MapperConfig<?> config, AnnotatedMethod setter1, AnnotatedMethod setter2) {
    Class<?> cls1 = setter1.getRawParameterType(0);
    Class<?> cls2 = setter2.getRawParameterType(0);
    if (cls1.isPrimitive()) {
      if (!cls2.isPrimitive())
        return setter1; 
      return null;
    } 
    if (cls2.isPrimitive())
      return setter2; 
    if (cls1 == String.class) {
      if (cls2 != String.class)
        return setter1; 
    } else if (cls2 == String.class) {
      return setter2;
    } 
    return null;
  }
  
  public PropertyName findRenameByField(MapperConfig<?> config, AnnotatedField f, PropertyName implName) {
    return null;
  }
  
  public JsonTypeInfo.Value findPolymorphicTypeInfo(MapperConfig<?> config, Annotated ann) {
    JsonTypeInfo t = (JsonTypeInfo)_findAnnotation(ann, JsonTypeInfo.class);
    return (t == null) ? null : JsonTypeInfo.Value.from(t);
  }
  
  public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> config, AnnotatedClass ac, JavaType baseType) {
    return _findTypeResolver(config, ac, baseType);
  }
  
  public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> config, AnnotatedMember am, JavaType baseType) {
    if (baseType.isContainerType() || baseType.isReferenceType())
      return null; 
    return _findTypeResolver(config, am, baseType);
  }
  
  public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> config, AnnotatedMember am, JavaType containerType) {
    if (containerType.getContentType() == null)
      throw new IllegalArgumentException("Must call method with a container or reference type (got " + containerType + ")"); 
    return _findTypeResolver(config, am, containerType);
  }
  
  public List<NamedType> findSubtypes(Annotated a) {
    JsonSubTypes t = (JsonSubTypes)_findAnnotation(a, JsonSubTypes.class);
    if (t == null)
      return null; 
    JsonSubTypes.Type[] types = t.value();
    if (t.failOnRepeatedNames())
      return findSubtypesCheckRepeatedNames(a.getName(), types); 
    ArrayList<NamedType> result = new ArrayList<>(types.length);
    for (JsonSubTypes.Type type : types) {
      result.add(new NamedType(type.value(), type.name()));
      for (String name : type.names())
        result.add(new NamedType(type.value(), name)); 
    } 
    return result;
  }
  
  private List<NamedType> findSubtypesCheckRepeatedNames(String annotatedTypeName, JsonSubTypes.Type[] types) {
    ArrayList<NamedType> result = new ArrayList<>(types.length);
    Set<String> seenNames = new HashSet<>();
    for (JsonSubTypes.Type type : types) {
      String typeName = type.name();
      if (!typeName.isEmpty() && seenNames.contains(typeName))
        throw new IllegalArgumentException("Annotated type [" + annotatedTypeName + "] got repeated subtype name [" + typeName + "]"); 
      seenNames.add(typeName);
      result.add(new NamedType(type.value(), typeName));
      for (String altName : type.names()) {
        if (!altName.isEmpty() && seenNames.contains(altName))
          throw new IllegalArgumentException("Annotated type [" + annotatedTypeName + "] got repeated subtype name [" + altName + "]"); 
        seenNames.add(altName);
        result.add(new NamedType(type.value(), altName));
      } 
    } 
    return result;
  }
  
  public String findTypeName(AnnotatedClass ac) {
    JsonTypeName tn = (JsonTypeName)_findAnnotation(ac, JsonTypeName.class);
    return (tn == null) ? null : tn.value();
  }
  
  public Boolean isTypeId(AnnotatedMember member) {
    return Boolean.valueOf(_hasAnnotation(member, JsonTypeId.class));
  }
  
  public ObjectIdInfo findObjectIdInfo(Annotated ann) {
    JsonIdentityInfo info = (JsonIdentityInfo)_findAnnotation(ann, JsonIdentityInfo.class);
    if (info == null || info.generator() == ObjectIdGenerators.None.class)
      return null; 
    PropertyName name = PropertyName.construct(info.property());
    return new ObjectIdInfo(name, info.scope(), info.generator(), info.resolver());
  }
  
  public ObjectIdInfo findObjectReferenceInfo(Annotated ann, ObjectIdInfo objectIdInfo) {
    JsonIdentityReference ref = (JsonIdentityReference)_findAnnotation(ann, JsonIdentityReference.class);
    if (ref == null)
      return objectIdInfo; 
    if (objectIdInfo == null)
      objectIdInfo = ObjectIdInfo.empty(); 
    return objectIdInfo.withAlwaysAsId(ref.alwaysAsId());
  }
  
  public Object findSerializer(Annotated a) {
    JsonSerialize ann = (JsonSerialize)_findAnnotation(a, JsonSerialize.class);
    if (ann != null) {
      Class<? extends JsonSerializer> serClass = ann.using();
      if (serClass != JsonSerializer.None.class)
        return serClass; 
    } 
    JsonRawValue annRaw = (JsonRawValue)_findAnnotation(a, JsonRawValue.class);
    if (annRaw != null && annRaw.value()) {
      Class<?> cls = a.getRawType();
      return new RawSerializer(cls);
    } 
    return null;
  }
  
  public Object findKeySerializer(Annotated a) {
    JsonSerialize ann = (JsonSerialize)_findAnnotation(a, JsonSerialize.class);
    if (ann != null) {
      Class<? extends JsonSerializer> serClass = ann.keyUsing();
      if (serClass != JsonSerializer.None.class)
        return serClass; 
    } 
    return null;
  }
  
  public Object findContentSerializer(Annotated a) {
    JsonSerialize ann = (JsonSerialize)_findAnnotation(a, JsonSerialize.class);
    if (ann != null) {
      Class<? extends JsonSerializer> serClass = ann.contentUsing();
      if (serClass != JsonSerializer.None.class)
        return serClass; 
    } 
    return null;
  }
  
  public Object findNullSerializer(Annotated a) {
    JsonSerialize ann = (JsonSerialize)_findAnnotation(a, JsonSerialize.class);
    if (ann != null) {
      Class<? extends JsonSerializer> serClass = ann.nullsUsing();
      if (serClass != JsonSerializer.None.class)
        return serClass; 
    } 
    return null;
  }
  
  public JsonInclude.Value findPropertyInclusion(Annotated a) {
    JsonInclude inc = (JsonInclude)_findAnnotation(a, JsonInclude.class);
    JsonInclude.Value value = (inc == null) ? JsonInclude.Value.empty() : JsonInclude.Value.from(inc);
    if (value.getValueInclusion() == JsonInclude.Include.USE_DEFAULTS)
      value = _refinePropertyInclusion(a, value); 
    return value;
  }
  
  private JsonInclude.Value _refinePropertyInclusion(Annotated a, JsonInclude.Value value) {
    JsonSerialize ann = (JsonSerialize)_findAnnotation(a, JsonSerialize.class);
    if (ann != null)
      switch (ann.include()) {
        case ALWAYS:
          return value.withValueInclusion(JsonInclude.Include.ALWAYS);
        case NON_NULL:
          return value.withValueInclusion(JsonInclude.Include.NON_NULL);
        case NON_DEFAULT:
          return value.withValueInclusion(JsonInclude.Include.NON_DEFAULT);
        case NON_EMPTY:
          return value.withValueInclusion(JsonInclude.Include.NON_EMPTY);
      }  
    return value;
  }
  
  public JsonSerialize.Typing findSerializationTyping(Annotated a) {
    JsonSerialize ann = (JsonSerialize)_findAnnotation(a, JsonSerialize.class);
    return (ann == null) ? null : ann.typing();
  }
  
  public Object findSerializationConverter(Annotated a) {
    JsonSerialize ann = (JsonSerialize)_findAnnotation(a, JsonSerialize.class);
    return (ann == null) ? null : _classIfExplicit(ann.converter(), Converter.None.class);
  }
  
  public Object findSerializationContentConverter(AnnotatedMember a) {
    JsonSerialize ann = (JsonSerialize)_findAnnotation(a, JsonSerialize.class);
    return (ann == null) ? null : _classIfExplicit(ann.contentConverter(), Converter.None.class);
  }
  
  public JavaType refineSerializationType(MapperConfig<?> config, Annotated a, JavaType baseType) throws JsonMappingException {
    MapLikeType mapLikeType;
    JavaType javaType1, type = baseType;
    TypeFactory tf = config.getTypeFactory();
    JsonSerialize jsonSer = (JsonSerialize)_findAnnotation(a, JsonSerialize.class);
    Class<?> serClass = (jsonSer == null) ? null : _classIfExplicit(jsonSer.as());
    if (serClass != null)
      if (type.hasRawClass(serClass)) {
        type = type.withStaticTyping();
      } else {
        Class<?> currRaw = type.getRawClass();
        try {
          if (serClass.isAssignableFrom(currRaw)) {
            type = tf.constructGeneralizedType(type, serClass);
          } else if (currRaw.isAssignableFrom(serClass)) {
            type = tf.constructSpecializedType(type, serClass);
          } else if (_primitiveAndWrapper(currRaw, serClass)) {
            type = type.withStaticTyping();
          } else {
            throw _databindException(
                String.format("Cannot refine serialization type %s into %s; types not related", new Object[] { type, serClass.getName() }));
          } 
        } catch (IllegalArgumentException iae) {
          throw _databindException(iae, 
              String.format("Failed to widen type %s with annotation (value %s), from '%s': %s", new Object[] { type, serClass.getName(), a.getName(), iae.getMessage() }));
        } 
      }  
    if (type.isMapLikeType()) {
      JavaType keyType = type.getKeyType();
      Class<?> keyClass = (jsonSer == null) ? null : _classIfExplicit(jsonSer.keyAs());
      if (keyClass != null) {
        if (keyType.hasRawClass(keyClass)) {
          keyType = keyType.withStaticTyping();
        } else {
          Class<?> currRaw = keyType.getRawClass();
          try {
            if (keyClass.isAssignableFrom(currRaw)) {
              keyType = tf.constructGeneralizedType(keyType, keyClass);
            } else if (currRaw.isAssignableFrom(keyClass)) {
              keyType = tf.constructSpecializedType(keyType, keyClass);
            } else if (_primitiveAndWrapper(currRaw, keyClass)) {
              keyType = keyType.withStaticTyping();
            } else {
              throw _databindException(
                  String.format("Cannot refine serialization key type %s into %s; types not related", new Object[] { keyType, keyClass.getName() }));
            } 
          } catch (IllegalArgumentException iae) {
            throw _databindException(iae, 
                String.format("Failed to widen key type of %s with concrete-type annotation (value %s), from '%s': %s", new Object[] { type, keyClass.getName(), a.getName(), iae.getMessage() }));
          } 
        } 
        mapLikeType = ((MapLikeType)type).withKeyType(keyType);
      } 
    } 
    JavaType contentType = mapLikeType.getContentType();
    if (contentType != null) {
      Class<?> contentClass = (jsonSer == null) ? null : _classIfExplicit(jsonSer.contentAs());
      if (contentClass != null) {
        if (contentType.hasRawClass(contentClass)) {
          contentType = contentType.withStaticTyping();
        } else {
          Class<?> currRaw = contentType.getRawClass();
          try {
            if (contentClass.isAssignableFrom(currRaw)) {
              contentType = tf.constructGeneralizedType(contentType, contentClass);
            } else if (currRaw.isAssignableFrom(contentClass)) {
              contentType = tf.constructSpecializedType(contentType, contentClass);
            } else if (_primitiveAndWrapper(currRaw, contentClass)) {
              contentType = contentType.withStaticTyping();
            } else {
              throw _databindException(
                  String.format("Cannot refine serialization content type %s into %s; types not related", new Object[] { contentType, contentClass.getName() }));
            } 
          } catch (IllegalArgumentException iae) {
            throw _databindException(iae, 
                String.format("Internal error: failed to refine value type of %s with concrete-type annotation (value %s), from '%s': %s", new Object[] { mapLikeType, contentClass.getName(), a.getName(), iae.getMessage() }));
          } 
        } 
        javaType1 = mapLikeType.withContentType(contentType);
      } 
    } 
    return javaType1;
  }
  
  public String[] findSerializationPropertyOrder(AnnotatedClass ac) {
    JsonPropertyOrder order = (JsonPropertyOrder)_findAnnotation(ac, JsonPropertyOrder.class);
    return (order == null) ? null : order.value();
  }
  
  public Boolean findSerializationSortAlphabetically(Annotated ann) {
    return _findSortAlpha(ann);
  }
  
  private final Boolean _findSortAlpha(Annotated ann) {
    JsonPropertyOrder order = (JsonPropertyOrder)_findAnnotation(ann, JsonPropertyOrder.class);
    if (order != null && order.alphabetic())
      return Boolean.TRUE; 
    return null;
  }
  
  public void findAndAddVirtualProperties(MapperConfig<?> config, AnnotatedClass ac, List<BeanPropertyWriter> properties) {
    JsonAppend ann = (JsonAppend)_findAnnotation(ac, JsonAppend.class);
    if (ann == null)
      return; 
    boolean prepend = ann.prepend();
    JavaType propType = null;
    JsonAppend.Attr[] attrs = ann.attrs();
    for (int i = 0, len = attrs.length; i < len; i++) {
      if (propType == null)
        propType = config.constructType(Object.class); 
      BeanPropertyWriter bpw = _constructVirtualProperty(attrs[i], config, ac, propType);
      if (prepend) {
        properties.add(i, bpw);
      } else {
        properties.add(bpw);
      } 
    } 
    JsonAppend.Prop[] props = ann.props();
    for (int j = 0, k = props.length; j < k; j++) {
      BeanPropertyWriter bpw = _constructVirtualProperty(props[j], config, ac);
      if (prepend) {
        properties.add(j, bpw);
      } else {
        properties.add(bpw);
      } 
    } 
  }
  
  protected BeanPropertyWriter _constructVirtualProperty(JsonAppend.Attr attr, MapperConfig<?> config, AnnotatedClass ac, JavaType type) {
    PropertyMetadata metadata = attr.required() ? PropertyMetadata.STD_REQUIRED : PropertyMetadata.STD_OPTIONAL;
    String attrName = attr.value();
    PropertyName propName = _propertyName(attr.propName(), attr.propNamespace());
    if (!propName.hasSimpleName())
      propName = PropertyName.construct(attrName); 
    AnnotatedMember member = new VirtualAnnotatedMember(ac, ac.getRawType(), attrName, type);
    SimpleBeanPropertyDefinition propDef = SimpleBeanPropertyDefinition.construct(config, member, propName, metadata, attr
        .include());
    return (BeanPropertyWriter)AttributePropertyWriter.construct(attrName, (BeanPropertyDefinition)propDef, ac
        .getAnnotations(), type);
  }
  
  protected BeanPropertyWriter _constructVirtualProperty(JsonAppend.Prop prop, MapperConfig<?> config, AnnotatedClass ac) {
    PropertyMetadata metadata = prop.required() ? PropertyMetadata.STD_REQUIRED : PropertyMetadata.STD_OPTIONAL;
    PropertyName propName = _propertyName(prop.name(), prop.namespace());
    JavaType type = config.constructType(prop.type());
    AnnotatedMember member = new VirtualAnnotatedMember(ac, ac.getRawType(), propName.getSimpleName(), type);
    SimpleBeanPropertyDefinition propDef = SimpleBeanPropertyDefinition.construct(config, member, propName, metadata, prop
        .include());
    Class<?> implClass = prop.value();
    HandlerInstantiator hi = config.getHandlerInstantiator();
    VirtualBeanPropertyWriter bpw = (hi == null) ? null : hi.virtualPropertyWriterInstance(config, implClass);
    if (bpw == null)
      bpw = (VirtualBeanPropertyWriter)ClassUtil.createInstance(implClass, config
          .canOverrideAccessModifiers()); 
    return (BeanPropertyWriter)bpw.withConfig(config, ac, (BeanPropertyDefinition)propDef, type);
  }
  
  public PropertyName findNameForSerialization(Annotated a) {
    boolean useDefault = false;
    JsonGetter jg = (JsonGetter)_findAnnotation(a, JsonGetter.class);
    if (jg != null) {
      String s = jg.value();
      if (!s.isEmpty())
        return PropertyName.construct(s); 
      useDefault = true;
    } 
    JsonProperty pann = (JsonProperty)_findAnnotation(a, JsonProperty.class);
    if (pann != null) {
      String ns = pann.namespace();
      if (ns != null && ns.isEmpty())
        ns = null; 
      return PropertyName.construct(pann.value(), ns);
    } 
    if (useDefault || _hasOneOf(a, (Class[])ANNOTATIONS_TO_INFER_SER))
      return PropertyName.USE_DEFAULT; 
    return null;
  }
  
  public Boolean hasAsKey(MapperConfig<?> config, Annotated a) {
    JsonKey ann = (JsonKey)_findAnnotation(a, JsonKey.class);
    if (ann == null)
      return null; 
    return Boolean.valueOf(ann.value());
  }
  
  public Boolean hasAsValue(Annotated a) {
    JsonValue ann = (JsonValue)_findAnnotation(a, JsonValue.class);
    if (ann == null)
      return null; 
    return Boolean.valueOf(ann.value());
  }
  
  public Boolean hasAnyGetter(Annotated a) {
    JsonAnyGetter ann = (JsonAnyGetter)_findAnnotation(a, JsonAnyGetter.class);
    if (ann == null)
      return null; 
    return Boolean.valueOf(ann.enabled());
  }
  
  @Deprecated
  public boolean hasAnyGetterAnnotation(AnnotatedMethod am) {
    return _hasAnnotation(am, JsonAnyGetter.class);
  }
  
  @Deprecated
  public boolean hasAsValueAnnotation(AnnotatedMethod am) {
    JsonValue ann = (JsonValue)_findAnnotation(am, JsonValue.class);
    return (ann != null && ann.value());
  }
  
  public Object findDeserializer(Annotated a) {
    JsonDeserialize ann = (JsonDeserialize)_findAnnotation(a, JsonDeserialize.class);
    if (ann != null) {
      Class<? extends JsonDeserializer> deserClass = ann.using();
      if (deserClass != JsonDeserializer.None.class)
        return deserClass; 
    } 
    return null;
  }
  
  public Object findKeyDeserializer(Annotated a) {
    JsonDeserialize ann = (JsonDeserialize)_findAnnotation(a, JsonDeserialize.class);
    if (ann != null) {
      Class<? extends KeyDeserializer> deserClass = ann.keyUsing();
      if (deserClass != KeyDeserializer.None.class)
        return deserClass; 
    } 
    return null;
  }
  
  public Object findContentDeserializer(Annotated a) {
    JsonDeserialize ann = (JsonDeserialize)_findAnnotation(a, JsonDeserialize.class);
    if (ann != null) {
      Class<? extends JsonDeserializer> deserClass = ann.contentUsing();
      if (deserClass != JsonDeserializer.None.class)
        return deserClass; 
    } 
    return null;
  }
  
  public Object findDeserializationConverter(Annotated a) {
    JsonDeserialize ann = (JsonDeserialize)_findAnnotation(a, JsonDeserialize.class);
    return (ann == null) ? null : _classIfExplicit(ann.converter(), Converter.None.class);
  }
  
  public Object findDeserializationContentConverter(AnnotatedMember a) {
    JsonDeserialize ann = (JsonDeserialize)_findAnnotation(a, JsonDeserialize.class);
    return (ann == null) ? null : _classIfExplicit(ann.contentConverter(), Converter.None.class);
  }
  
  public JavaType refineDeserializationType(MapperConfig<?> config, Annotated a, JavaType baseType) throws JsonMappingException {
    MapLikeType mapLikeType;
    JavaType javaType1, type = baseType;
    TypeFactory tf = config.getTypeFactory();
    JsonDeserialize jsonDeser = (JsonDeserialize)_findAnnotation(a, JsonDeserialize.class);
    Class<?> valueClass = (jsonDeser == null) ? null : _classIfExplicit(jsonDeser.as());
    if (valueClass != null && !type.hasRawClass(valueClass) && 
      !_primitiveAndWrapper(type, valueClass))
      try {
        type = tf.constructSpecializedType(type, valueClass);
      } catch (IllegalArgumentException iae) {
        throw _databindException(iae, 
            String.format("Failed to narrow type %s with annotation (value %s), from '%s': %s", new Object[] { type, valueClass.getName(), a.getName(), iae.getMessage() }));
      }  
    if (type.isMapLikeType()) {
      JavaType keyType = type.getKeyType();
      Class<?> keyClass = (jsonDeser == null) ? null : _classIfExplicit(jsonDeser.keyAs());
      if (keyClass != null && 
        !_primitiveAndWrapper(keyType, keyClass))
        try {
          keyType = tf.constructSpecializedType(keyType, keyClass);
          mapLikeType = ((MapLikeType)type).withKeyType(keyType);
        } catch (IllegalArgumentException iae) {
          throw _databindException(iae, 
              String.format("Failed to narrow key type of %s with concrete-type annotation (value %s), from '%s': %s", new Object[] { mapLikeType, keyClass.getName(), a.getName(), iae.getMessage() }));
        }  
    } 
    JavaType contentType = mapLikeType.getContentType();
    if (contentType != null) {
      Class<?> contentClass = (jsonDeser == null) ? null : _classIfExplicit(jsonDeser.contentAs());
      if (contentClass != null && 
        !_primitiveAndWrapper(contentType, contentClass))
        try {
          contentType = tf.constructSpecializedType(contentType, contentClass);
          javaType1 = mapLikeType.withContentType(contentType);
        } catch (IllegalArgumentException iae) {
          throw _databindException(iae, 
              String.format("Failed to narrow value type of %s with concrete-type annotation (value %s), from '%s': %s", new Object[] { javaType1, contentClass.getName(), a.getName(), iae.getMessage() }));
        }  
    } 
    return javaType1;
  }
  
  public Object findValueInstantiator(AnnotatedClass ac) {
    JsonValueInstantiator ann = (JsonValueInstantiator)_findAnnotation(ac, JsonValueInstantiator.class);
    return (ann == null) ? null : ann.value();
  }
  
  public Class<?> findPOJOBuilder(AnnotatedClass ac) {
    JsonDeserialize ann = (JsonDeserialize)_findAnnotation(ac, JsonDeserialize.class);
    return (ann == null) ? null : _classIfExplicit(ann.builder());
  }
  
  public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass ac) {
    JsonPOJOBuilder ann = (JsonPOJOBuilder)_findAnnotation(ac, JsonPOJOBuilder.class);
    return (ann == null) ? null : new JsonPOJOBuilder.Value(ann);
  }
  
  public PropertyName findNameForDeserialization(Annotated a) {
    boolean useDefault = false;
    JsonSetter js = (JsonSetter)_findAnnotation(a, JsonSetter.class);
    if (js != null) {
      String s = js.value();
      if (s.isEmpty()) {
        useDefault = true;
      } else {
        return PropertyName.construct(s);
      } 
    } 
    JsonProperty pann = (JsonProperty)_findAnnotation(a, JsonProperty.class);
    if (pann != null) {
      String ns = pann.namespace();
      if (ns != null && ns.isEmpty())
        ns = null; 
      return PropertyName.construct(pann.value(), ns);
    } 
    if (useDefault || _hasOneOf(a, (Class[])ANNOTATIONS_TO_INFER_DESER))
      return PropertyName.USE_DEFAULT; 
    return null;
  }
  
  public Boolean hasAnySetter(Annotated a) {
    JsonAnySetter ann = (JsonAnySetter)_findAnnotation(a, JsonAnySetter.class);
    return (ann == null) ? null : Boolean.valueOf(ann.enabled());
  }
  
  public JsonSetter.Value findSetterInfo(Annotated a) {
    return JsonSetter.Value.from((JsonSetter)_findAnnotation(a, JsonSetter.class));
  }
  
  public Boolean findMergeInfo(Annotated a) {
    JsonMerge ann = (JsonMerge)_findAnnotation(a, JsonMerge.class);
    return (ann == null) ? null : ann.value().asBoolean();
  }
  
  @Deprecated
  public boolean hasAnySetterAnnotation(AnnotatedMethod am) {
    return _hasAnnotation(am, JsonAnySetter.class);
  }
  
  @Deprecated
  public boolean hasCreatorAnnotation(Annotated a) {
    JsonCreator ann = (JsonCreator)_findAnnotation(a, JsonCreator.class);
    if (ann != null)
      return (ann.mode() != JsonCreator.Mode.DISABLED); 
    if (this._cfgConstructorPropertiesImpliesCreator && 
      a instanceof AnnotatedConstructor && 
      _java7Helper != null) {
      Boolean b = _java7Helper.hasCreatorAnnotation(a);
      if (b != null)
        return b.booleanValue(); 
    } 
    return false;
  }
  
  @Deprecated
  public JsonCreator.Mode findCreatorBinding(Annotated a) {
    JsonCreator ann = (JsonCreator)_findAnnotation(a, JsonCreator.class);
    return (ann == null) ? null : ann.mode();
  }
  
  public JsonCreator.Mode findCreatorAnnotation(MapperConfig<?> config, Annotated a) {
    JsonCreator ann = (JsonCreator)_findAnnotation(a, JsonCreator.class);
    if (ann != null)
      return ann.mode(); 
    if (this._cfgConstructorPropertiesImpliesCreator && config
      .isEnabled(MapperFeature.INFER_CREATOR_FROM_CONSTRUCTOR_PROPERTIES))
      if (a instanceof AnnotatedConstructor && 
        _java7Helper != null) {
        Boolean b = _java7Helper.hasCreatorAnnotation(a);
        if (b != null && b.booleanValue())
          return JsonCreator.Mode.PROPERTIES; 
      }  
    return null;
  }
  
  protected boolean _isIgnorable(Annotated a) {
    JsonIgnore ann = (JsonIgnore)_findAnnotation(a, JsonIgnore.class);
    if (ann != null)
      return ann.value(); 
    if (_java7Helper != null) {
      Boolean b = _java7Helper.findTransient(a);
      if (b != null)
        return b.booleanValue(); 
    } 
    return false;
  }
  
  protected Class<?> _classIfExplicit(Class<?> cls) {
    if (cls == null || ClassUtil.isBogusClass(cls))
      return null; 
    return cls;
  }
  
  protected Class<?> _classIfExplicit(Class<?> cls, Class<?> implicit) {
    cls = _classIfExplicit(cls);
    return (cls == null || cls == implicit) ? null : cls;
  }
  
  protected PropertyName _propertyName(String localName, String namespace) {
    if (localName.isEmpty())
      return PropertyName.USE_DEFAULT; 
    if (namespace == null || namespace.isEmpty())
      return PropertyName.construct(localName); 
    return PropertyName.construct(localName, namespace);
  }
  
  protected PropertyName _findConstructorName(Annotated a) {
    if (a instanceof AnnotatedParameter) {
      AnnotatedParameter p = (AnnotatedParameter)a;
      AnnotatedWithParams ctor = p.getOwner();
      if (ctor != null && 
        _java7Helper != null) {
        PropertyName name = _java7Helper.findConstructorName(p);
        if (name != null)
          return name; 
      } 
    } 
    return null;
  }
  
  protected TypeResolverBuilder<?> _findTypeResolver(MapperConfig<?> config, Annotated ann, JavaType baseType) {
    JsonTypeInfo.Value typeInfo = findPolymorphicTypeInfo(config, ann);
    JsonTypeResolver resAnn = (JsonTypeResolver)_findAnnotation(ann, JsonTypeResolver.class);
    if (resAnn != null) {
      if (typeInfo == null)
        return null; 
      b = config.typeResolverBuilderInstance(ann, resAnn.value());
    } else {
      if (typeInfo == null)
        return null; 
      if (typeInfo.getIdType() == JsonTypeInfo.Id.NONE)
        return (TypeResolverBuilder<?>)_constructNoTypeResolverBuilder(); 
      b = _constructStdTypeResolverBuilder(config, typeInfo, baseType);
    } 
    JsonTypeIdResolver idResInfo = (JsonTypeIdResolver)_findAnnotation(ann, JsonTypeIdResolver.class);
    TypeIdResolver idRes = (idResInfo == null) ? null : config.typeIdResolverInstance(ann, idResInfo.value());
    if (idRes != null)
      idRes.init(baseType); 
    JsonTypeInfo.As inclusion = typeInfo.getInclusionType();
    if (inclusion == JsonTypeInfo.As.EXTERNAL_PROPERTY && ann instanceof AnnotatedClass)
      typeInfo = typeInfo.withInclusionType(JsonTypeInfo.As.PROPERTY); 
    Class<?> defaultImpl = typeInfo.getDefaultImpl();
    if (defaultImpl != null && defaultImpl != JsonTypeInfo.None.class && !defaultImpl.isAnnotation())
      typeInfo = typeInfo.withDefaultImpl(defaultImpl); 
    TypeResolverBuilder<?> b = b.init(typeInfo, idRes);
    return b;
  }
  
  protected StdTypeResolverBuilder _constructStdTypeResolverBuilder() {
    return new StdTypeResolverBuilder();
  }
  
  protected TypeResolverBuilder<?> _constructStdTypeResolverBuilder(MapperConfig<?> config, JsonTypeInfo.Value typeInfo, JavaType baseType) {
    return (TypeResolverBuilder<?>)new StdTypeResolverBuilder(typeInfo);
  }
  
  protected StdTypeResolverBuilder _constructNoTypeResolverBuilder() {
    return StdTypeResolverBuilder.noTypeInfoBuilder();
  }
  
  private boolean _primitiveAndWrapper(Class<?> baseType, Class<?> refinement) {
    if (baseType.isPrimitive())
      return (baseType == ClassUtil.primitiveType(refinement)); 
    if (refinement.isPrimitive())
      return (refinement == ClassUtil.primitiveType(baseType)); 
    return false;
  }
  
  private boolean _primitiveAndWrapper(JavaType baseType, Class<?> refinement) {
    if (baseType.isPrimitive())
      return baseType.hasRawClass(ClassUtil.primitiveType(refinement)); 
    if (refinement.isPrimitive())
      return (refinement == ClassUtil.primitiveType(baseType.getRawClass())); 
    return false;
  }
  
  private JsonMappingException _databindException(String msg) {
    return new JsonMappingException(null, msg);
  }
  
  private JsonMappingException _databindException(Throwable t, String msg) {
    return new JsonMappingException(null, msg, t);
  }
}
