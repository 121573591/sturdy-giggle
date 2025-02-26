package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.introspect.AccessorNamingStrategy;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

public abstract class MapperConfigBase<CFG extends ConfigFeature, T extends MapperConfigBase<CFG, T>> extends MapperConfig<T> implements Serializable {
  protected static final ConfigOverride EMPTY_OVERRIDE = ConfigOverride.empty();
  
  private static final long DEFAULT_MAPPER_FEATURES = MapperFeature.collectLongDefaults();
  
  private static final long AUTO_DETECT_MASK = MapperFeature.AUTO_DETECT_FIELDS
    .getLongMask() | MapperFeature.AUTO_DETECT_GETTERS
    .getLongMask() | MapperFeature.AUTO_DETECT_IS_GETTERS
    .getLongMask() | MapperFeature.AUTO_DETECT_SETTERS
    .getLongMask() | MapperFeature.AUTO_DETECT_CREATORS
    .getLongMask();
  
  protected final SimpleMixInResolver _mixIns;
  
  protected final SubtypeResolver _subtypeResolver;
  
  protected final PropertyName _rootName;
  
  protected final Class<?> _view;
  
  protected final ContextAttributes _attributes;
  
  protected final RootNameLookup _rootNames;
  
  protected final ConfigOverrides _configOverrides;
  
  protected final DatatypeFeatures _datatypeFeatures;
  
  protected MapperConfigBase(BaseSettings base, SubtypeResolver str, SimpleMixInResolver mixins, RootNameLookup rootNames, ConfigOverrides configOverrides, DatatypeFeatures datatypeFeatures) {
    super(base, DEFAULT_MAPPER_FEATURES);
    this._mixIns = mixins;
    this._subtypeResolver = str;
    this._rootNames = rootNames;
    this._rootName = null;
    this._view = null;
    this._attributes = ContextAttributes.getEmpty();
    this._configOverrides = configOverrides;
    this._datatypeFeatures = datatypeFeatures;
  }
  
  protected MapperConfigBase(MapperConfigBase<CFG, T> src, SubtypeResolver str, SimpleMixInResolver mixins, RootNameLookup rootNames, ConfigOverrides configOverrides) {
    super(src, src._base.copy());
    this._mixIns = mixins;
    this._subtypeResolver = str;
    this._rootNames = rootNames;
    this._rootName = src._rootName;
    this._view = src._view;
    this._attributes = src._attributes;
    this._configOverrides = configOverrides;
    this._datatypeFeatures = src._datatypeFeatures;
  }
  
  protected MapperConfigBase(MapperConfigBase<CFG, T> src) {
    super(src);
    this._mixIns = src._mixIns;
    this._subtypeResolver = src._subtypeResolver;
    this._rootNames = src._rootNames;
    this._rootName = src._rootName;
    this._view = src._view;
    this._attributes = src._attributes;
    this._configOverrides = src._configOverrides;
    this._datatypeFeatures = src._datatypeFeatures;
  }
  
  protected MapperConfigBase(MapperConfigBase<CFG, T> src, BaseSettings base) {
    super(src, base);
    this._mixIns = src._mixIns;
    this._subtypeResolver = src._subtypeResolver;
    this._rootNames = src._rootNames;
    this._rootName = src._rootName;
    this._view = src._view;
    this._attributes = src._attributes;
    this._configOverrides = src._configOverrides;
    this._datatypeFeatures = src._datatypeFeatures;
  }
  
  protected MapperConfigBase(MapperConfigBase<CFG, T> src, long mapperFeatures) {
    super(src, mapperFeatures);
    this._mixIns = src._mixIns;
    this._subtypeResolver = src._subtypeResolver;
    this._rootNames = src._rootNames;
    this._rootName = src._rootName;
    this._view = src._view;
    this._attributes = src._attributes;
    this._configOverrides = src._configOverrides;
    this._datatypeFeatures = src._datatypeFeatures;
  }
  
  protected MapperConfigBase(MapperConfigBase<CFG, T> src, SubtypeResolver str) {
    super(src);
    this._mixIns = src._mixIns;
    this._subtypeResolver = str;
    this._rootNames = src._rootNames;
    this._rootName = src._rootName;
    this._view = src._view;
    this._attributes = src._attributes;
    this._configOverrides = src._configOverrides;
    this._datatypeFeatures = src._datatypeFeatures;
  }
  
  protected MapperConfigBase(MapperConfigBase<CFG, T> src, PropertyName rootName) {
    super(src);
    this._mixIns = src._mixIns;
    this._subtypeResolver = src._subtypeResolver;
    this._rootNames = src._rootNames;
    this._rootName = rootName;
    this._view = src._view;
    this._attributes = src._attributes;
    this._configOverrides = src._configOverrides;
    this._datatypeFeatures = src._datatypeFeatures;
  }
  
  protected MapperConfigBase(MapperConfigBase<CFG, T> src, Class<?> view) {
    super(src);
    this._mixIns = src._mixIns;
    this._subtypeResolver = src._subtypeResolver;
    this._rootNames = src._rootNames;
    this._rootName = src._rootName;
    this._view = view;
    this._attributes = src._attributes;
    this._configOverrides = src._configOverrides;
    this._datatypeFeatures = src._datatypeFeatures;
  }
  
  protected MapperConfigBase(MapperConfigBase<CFG, T> src, SimpleMixInResolver mixins) {
    super(src);
    this._mixIns = mixins;
    this._subtypeResolver = src._subtypeResolver;
    this._rootNames = src._rootNames;
    this._rootName = src._rootName;
    this._view = src._view;
    this._attributes = src._attributes;
    this._configOverrides = src._configOverrides;
    this._datatypeFeatures = src._datatypeFeatures;
  }
  
  protected MapperConfigBase(MapperConfigBase<CFG, T> src, ContextAttributes attr) {
    super(src);
    this._mixIns = src._mixIns;
    this._subtypeResolver = src._subtypeResolver;
    this._rootNames = src._rootNames;
    this._rootName = src._rootName;
    this._view = src._view;
    this._attributes = attr;
    this._configOverrides = src._configOverrides;
    this._datatypeFeatures = src._datatypeFeatures;
  }
  
  protected MapperConfigBase(MapperConfigBase<CFG, T> src, DatatypeFeatures datatypeFeatures) {
    super(src);
    this._mixIns = src._mixIns;
    this._subtypeResolver = src._subtypeResolver;
    this._rootNames = src._rootNames;
    this._rootName = src._rootName;
    this._view = src._view;
    this._attributes = src._attributes;
    this._configOverrides = src._configOverrides;
    this._datatypeFeatures = datatypeFeatures;
  }
  
  protected DatatypeFeatures _datatypeFeatures() {
    return this._datatypeFeatures;
  }
  
  public final T with(MapperFeature... features) {
    long newMapperFlags = this._mapperFeatures;
    for (MapperFeature f : features)
      newMapperFlags |= f.getLongMask(); 
    if (newMapperFlags == this._mapperFeatures)
      return (T)this; 
    return _withMapperFeatures(newMapperFlags);
  }
  
  public final T without(MapperFeature... features) {
    long newMapperFlags = this._mapperFeatures;
    for (MapperFeature f : features)
      newMapperFlags &= f.getLongMask() ^ 0xFFFFFFFFFFFFFFFFL; 
    if (newMapperFlags == this._mapperFeatures)
      return (T)this; 
    return _withMapperFeatures(newMapperFlags);
  }
  
  public final T with(MapperFeature feature, boolean state) {
    long newMapperFlags;
    if (state) {
      newMapperFlags = this._mapperFeatures | feature.getLongMask();
    } else {
      newMapperFlags = this._mapperFeatures & (feature.getLongMask() ^ 0xFFFFFFFFFFFFFFFFL);
    } 
    if (newMapperFlags == this._mapperFeatures)
      return (T)this; 
    return _withMapperFeatures(newMapperFlags);
  }
  
  public final T with(DatatypeFeature feature) {
    return _with(_datatypeFeatures().with(feature));
  }
  
  public final T withFeatures(DatatypeFeature... features) {
    return _with(_datatypeFeatures().withFeatures(features));
  }
  
  public final T without(DatatypeFeature feature) {
    return _with(_datatypeFeatures().without(feature));
  }
  
  public final T withoutFeatures(DatatypeFeature... features) {
    return _with(_datatypeFeatures().withoutFeatures(features));
  }
  
  public final T with(DatatypeFeature feature, boolean state) {
    DatatypeFeatures features = _datatypeFeatures();
    features = state ? features.with(feature) : features.without(feature);
    return _with(features);
  }
  
  public final T with(AnnotationIntrospector ai) {
    return _withBase(this._base.withAnnotationIntrospector(ai));
  }
  
  public final T withAppendedAnnotationIntrospector(AnnotationIntrospector ai) {
    return _withBase(this._base.withAppendedAnnotationIntrospector(ai));
  }
  
  public final T withInsertedAnnotationIntrospector(AnnotationIntrospector ai) {
    return _withBase(this._base.withInsertedAnnotationIntrospector(ai));
  }
  
  public final T with(ClassIntrospector ci) {
    return _withBase(this._base.withClassIntrospector(ci));
  }
  
  public T withAttributes(Map<?, ?> attributes) {
    return with(getAttributes().withSharedAttributes(attributes));
  }
  
  public T withAttribute(Object key, Object value) {
    return with(getAttributes().withSharedAttribute(key, value));
  }
  
  public T withoutAttribute(Object key) {
    return with(getAttributes().withoutSharedAttribute(key));
  }
  
  public final T with(TypeFactory tf) {
    return _withBase(this._base.withTypeFactory(tf));
  }
  
  public final T with(TypeResolverBuilder<?> trb) {
    return _withBase(this._base.withTypeResolverBuilder(trb));
  }
  
  public final T with(PropertyNamingStrategy pns) {
    return _withBase(this._base.withPropertyNamingStrategy(pns));
  }
  
  public final T with(AccessorNamingStrategy.Provider p) {
    return _withBase(this._base.withAccessorNaming(p));
  }
  
  public final T with(HandlerInstantiator hi) {
    return _withBase(this._base.withHandlerInstantiator(hi));
  }
  
  public T with(CacheProvider provider) {
    return _withBase(this._base.with(Objects.<CacheProvider>requireNonNull(provider)));
  }
  
  public final T with(Base64Variant base64) {
    return _withBase(this._base.with(base64));
  }
  
  public T with(DateFormat df) {
    return _withBase(this._base.withDateFormat(df));
  }
  
  public final T with(Locale l) {
    return _withBase(this._base.with(l));
  }
  
  public final T with(TimeZone tz) {
    return _withBase(this._base.with(tz));
  }
  
  public T withRootName(String rootName) {
    if (rootName == null)
      return withRootName((PropertyName)null); 
    return withRootName(PropertyName.construct(rootName));
  }
  
  public final DatatypeFeatures getDatatypeFeatures() {
    return this._datatypeFeatures;
  }
  
  public final SubtypeResolver getSubtypeResolver() {
    return this._subtypeResolver;
  }
  
  @Deprecated
  public final String getRootName() {
    return (this._rootName == null) ? null : this._rootName.getSimpleName();
  }
  
  public final PropertyName getFullRootName() {
    return this._rootName;
  }
  
  public final Class<?> getActiveView() {
    return this._view;
  }
  
  public final ContextAttributes getAttributes() {
    return this._attributes;
  }
  
  public final ConfigOverride getConfigOverride(Class<?> type) {
    ConfigOverride override = this._configOverrides.findOverride(type);
    return (override == null) ? EMPTY_OVERRIDE : override;
  }
  
  public final ConfigOverride findConfigOverride(Class<?> type) {
    return this._configOverrides.findOverride(type);
  }
  
  public final JsonInclude.Value getDefaultPropertyInclusion() {
    return this._configOverrides.getDefaultInclusion();
  }
  
  public final JsonInclude.Value getDefaultPropertyInclusion(Class<?> baseType) {
    JsonInclude.Value v = getConfigOverride(baseType).getInclude();
    JsonInclude.Value def = getDefaultPropertyInclusion();
    if (def == null)
      return v; 
    return def.withOverrides(v);
  }
  
  public final JsonInclude.Value getDefaultInclusion(Class<?> baseType, Class<?> propertyType) {
    JsonInclude.Value v = getConfigOverride(propertyType).getIncludeAsProperty();
    JsonInclude.Value def = getDefaultPropertyInclusion(baseType);
    if (def == null)
      return v; 
    return def.withOverrides(v);
  }
  
  public final JsonFormat.Value getDefaultPropertyFormat(Class<?> type) {
    return this._configOverrides.findFormatDefaults(type);
  }
  
  public final JsonIgnoreProperties.Value getDefaultPropertyIgnorals(Class<?> type) {
    ConfigOverride overrides = this._configOverrides.findOverride(type);
    if (overrides != null) {
      JsonIgnoreProperties.Value v = overrides.getIgnorals();
      if (v != null)
        return v; 
    } 
    return null;
  }
  
  public final JsonIgnoreProperties.Value getDefaultPropertyIgnorals(Class<?> baseType, AnnotatedClass actualClass) {
    AnnotationIntrospector intr = getAnnotationIntrospector();
    JsonIgnoreProperties.Value base = (intr == null) ? null : intr.findPropertyIgnoralByName(this, (Annotated)actualClass);
    JsonIgnoreProperties.Value overrides = getDefaultPropertyIgnorals(baseType);
    return JsonIgnoreProperties.Value.merge(base, overrides);
  }
  
  public final JsonIncludeProperties.Value getDefaultPropertyInclusions(Class<?> baseType, AnnotatedClass actualClass) {
    AnnotationIntrospector intr = getAnnotationIntrospector();
    return (intr == null) ? null : intr.findPropertyInclusionByName(this, (Annotated)actualClass);
  }
  
  public final VisibilityChecker<?> getDefaultVisibilityChecker() {
    VisibilityChecker<?> vchecker = this._configOverrides.getDefaultVisibility();
    if ((this._mapperFeatures & AUTO_DETECT_MASK) != AUTO_DETECT_MASK) {
      if (!isEnabled(MapperFeature.AUTO_DETECT_FIELDS))
        vchecker = vchecker.withFieldVisibility(JsonAutoDetect.Visibility.NONE); 
      if (!isEnabled(MapperFeature.AUTO_DETECT_GETTERS))
        vchecker = vchecker.withGetterVisibility(JsonAutoDetect.Visibility.NONE); 
      if (!isEnabled(MapperFeature.AUTO_DETECT_IS_GETTERS))
        vchecker = vchecker.withIsGetterVisibility(JsonAutoDetect.Visibility.NONE); 
      if (!isEnabled(MapperFeature.AUTO_DETECT_SETTERS))
        vchecker = vchecker.withSetterVisibility(JsonAutoDetect.Visibility.NONE); 
      if (!isEnabled(MapperFeature.AUTO_DETECT_CREATORS))
        vchecker = vchecker.withCreatorVisibility(JsonAutoDetect.Visibility.NONE); 
    } 
    return vchecker;
  }
  
  public final VisibilityChecker<?> getDefaultVisibilityChecker(Class<?> baseType, AnnotatedClass actualClass) {
    VisibilityChecker<?> vc;
    if (ClassUtil.isJDKClass(baseType)) {
      VisibilityChecker.Std std = VisibilityChecker.Std.allPublicInstance();
    } else {
      vc = getDefaultVisibilityChecker();
      if (ClassUtil.isRecordType(baseType))
        if (isEnabled(MapperFeature.AUTO_DETECT_CREATORS))
          vc = vc.withCreatorVisibility(JsonAutoDetect.Visibility.DEFAULT);  
    } 
    AnnotationIntrospector intr = getAnnotationIntrospector();
    if (intr != null)
      vc = intr.findAutoDetectVisibility(actualClass, vc); 
    ConfigOverride overrides = this._configOverrides.findOverride(baseType);
    if (overrides != null)
      vc = vc.withOverrides(overrides.getVisibility()); 
    return vc;
  }
  
  public final JsonSetter.Value getDefaultSetterInfo() {
    return this._configOverrides.getDefaultSetterInfo();
  }
  
  public Boolean getDefaultMergeable() {
    return this._configOverrides.getDefaultMergeable();
  }
  
  public Boolean getDefaultMergeable(Class<?> baseType) {
    ConfigOverride cfg = this._configOverrides.findOverride(baseType);
    if (cfg != null) {
      Boolean b = cfg.getMergeable();
      if (b != null)
        return b; 
    } 
    return this._configOverrides.getDefaultMergeable();
  }
  
  public PropertyName findRootName(JavaType rootType) {
    if (this._rootName != null)
      return this._rootName; 
    return this._rootNames.findRootName(rootType, this);
  }
  
  public PropertyName findRootName(Class<?> rawRootType) {
    if (this._rootName != null)
      return this._rootName; 
    return this._rootNames.findRootName(rawRootType, this);
  }
  
  public final Class<?> findMixInClassFor(Class<?> cls) {
    return this._mixIns.findMixInClassFor(cls);
  }
  
  public ClassIntrospector.MixInResolver copy() {
    throw new UnsupportedOperationException();
  }
  
  public final int mixInCount() {
    return this._mixIns.localSize();
  }
  
  protected abstract T _withBase(BaseSettings paramBaseSettings);
  
  protected abstract T _withMapperFeatures(long paramLong);
  
  protected abstract T _with(DatatypeFeatures paramDatatypeFeatures);
  
  public abstract T with(ContextAttributes paramContextAttributes);
  
  public abstract T withRootName(PropertyName paramPropertyName);
  
  public abstract T with(SubtypeResolver paramSubtypeResolver);
  
  public abstract T withView(Class<?> paramClass);
}
