package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.util.JacksonFeature;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.DatatypeFeature;
import com.fasterxml.jackson.databind.cfg.DatatypeFeatures;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.deser.DeserializerCache;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LinkedNode;
import com.fasterxml.jackson.databind.util.Named;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

public abstract class DeserializationContext extends DatabindContext implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected final DeserializerCache _cache;
  
  protected final DeserializerFactory _factory;
  
  protected final DeserializationConfig _config;
  
  protected final int _featureFlags;
  
  protected final JacksonFeatureSet<StreamReadCapability> _readCapabilities;
  
  protected final Class<?> _view;
  
  protected transient JsonParser _parser;
  
  protected final InjectableValues _injectableValues;
  
  protected transient ArrayBuilders _arrayBuilders;
  
  protected transient ObjectBuffer _objectBuffer;
  
  protected transient DateFormat _dateFormat;
  
  protected transient ContextAttributes _attributes;
  
  protected LinkedNode<JavaType> _currentType;
  
  protected DeserializationContext(DeserializerFactory df, DeserializerCache cache) {
    this._factory = Objects.<DeserializerFactory>requireNonNull(df);
    if (cache == null)
      cache = new DeserializerCache(); 
    this._cache = cache;
    this._featureFlags = 0;
    this._readCapabilities = null;
    this._config = null;
    this._injectableValues = null;
    this._view = null;
    this._attributes = null;
  }
  
  protected DeserializationContext(DeserializationContext src, DeserializerFactory factory) {
    this._cache = src._cache;
    this._factory = factory;
    this._config = src._config;
    this._featureFlags = src._featureFlags;
    this._readCapabilities = src._readCapabilities;
    this._view = src._view;
    this._parser = src._parser;
    this._injectableValues = src._injectableValues;
    this._attributes = src._attributes;
  }
  
  protected DeserializationContext(DeserializationContext src, DeserializerCache cache) {
    this._cache = cache;
    this._factory = src._factory;
    this._config = src._config;
    this._featureFlags = src._featureFlags;
    this._readCapabilities = src._readCapabilities;
    this._view = src._view;
    this._parser = src._parser;
    this._injectableValues = src._injectableValues;
    this._attributes = src._attributes;
  }
  
  protected DeserializationContext(DeserializationContext src, DeserializationConfig config, JsonParser p, InjectableValues injectableValues) {
    this._cache = src._cache;
    this._factory = src._factory;
    this._readCapabilities = (p == null) ? null : p.getReadCapabilities();
    this._config = config;
    this._featureFlags = config.getDeserializationFeatures();
    this._view = config.getActiveView();
    this._parser = p;
    this._injectableValues = injectableValues;
    this._attributes = config.getAttributes();
  }
  
  protected DeserializationContext(DeserializationContext src, DeserializationConfig config) {
    this._cache = src._cache;
    this._factory = src._factory;
    this._readCapabilities = null;
    this._config = config;
    this._featureFlags = config.getDeserializationFeatures();
    this._view = null;
    this._parser = null;
    this._injectableValues = null;
    this._attributes = null;
  }
  
  protected DeserializationContext(DeserializationContext src) {
    this._cache = src._cache.emptyCopy();
    this._factory = src._factory;
    this._config = src._config;
    this._featureFlags = src._featureFlags;
    this._readCapabilities = src._readCapabilities;
    this._view = src._view;
    this._injectableValues = src._injectableValues;
    this._attributes = null;
  }
  
  public DeserializationConfig getConfig() {
    return this._config;
  }
  
  public final Class<?> getActiveView() {
    return this._view;
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
  
  public final AnnotationIntrospector getAnnotationIntrospector() {
    return this._config.getAnnotationIntrospector();
  }
  
  public final TypeFactory getTypeFactory() {
    return this._config.getTypeFactory();
  }
  
  public JavaType constructSpecializedType(JavaType baseType, Class<?> subclass) throws IllegalArgumentException {
    if (baseType.hasRawClass(subclass))
      return baseType; 
    return getConfig().getTypeFactory().constructSpecializedType(baseType, subclass, false);
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
  
  public DeserializationContext setAttribute(Object key, Object value) {
    this._attributes = this._attributes.withPerCallAttribute(key, value);
    return this;
  }
  
  public JavaType getContextualType() {
    return (this._currentType == null) ? null : (JavaType)this._currentType.value();
  }
  
  public DeserializerFactory getFactory() {
    return this._factory;
  }
  
  public final boolean isEnabled(DeserializationFeature feat) {
    return ((this._featureFlags & feat.getMask()) != 0);
  }
  
  public final boolean isEnabled(StreamReadCapability cap) {
    return this._readCapabilities.isEnabled((JacksonFeature)cap);
  }
  
  public final int getDeserializationFeatures() {
    return this._featureFlags;
  }
  
  public final boolean hasDeserializationFeatures(int featureMask) {
    return ((this._featureFlags & featureMask) == featureMask);
  }
  
  public final boolean hasSomeOfFeatures(int featureMask) {
    return ((this._featureFlags & featureMask) != 0);
  }
  
  public final JsonParser getParser() {
    return this._parser;
  }
  
  public final Object findInjectableValue(Object valueId, BeanProperty forProperty, Object beanInstance) throws JsonMappingException {
    if (this._injectableValues == null)
      return reportBadDefinition(ClassUtil.classOf(valueId), String.format("No 'injectableValues' configured, cannot inject value with id [%s]", new Object[] { valueId })); 
    return this._injectableValues.findInjectableValue(valueId, this, forProperty, beanInstance);
  }
  
  public final Base64Variant getBase64Variant() {
    return this._config.getBase64Variant();
  }
  
  public final JsonNodeFactory getNodeFactory() {
    return this._config.getNodeFactory();
  }
  
  public CoercionAction findCoercionAction(LogicalType targetType, Class<?> targetClass, CoercionInputShape inputShape) {
    return this._config.findCoercionAction(targetType, targetClass, inputShape);
  }
  
  public CoercionAction findCoercionFromBlankString(LogicalType targetType, Class<?> targetClass, CoercionAction actionIfBlankNotAllowed) {
    return this._config.findCoercionFromBlankString(targetType, targetClass, actionIfBlankNotAllowed);
  }
  
  public TokenBuffer bufferForInputBuffering(JsonParser p) {
    return new TokenBuffer(p, this);
  }
  
  public final TokenBuffer bufferForInputBuffering() {
    return bufferForInputBuffering(getParser());
  }
  
  public TokenBuffer bufferAsCopyOfValue(JsonParser p) throws IOException {
    TokenBuffer buf = bufferForInputBuffering(p);
    buf.copyCurrentStructure(p);
    return buf;
  }
  
  public boolean hasValueDeserializerFor(JavaType type, AtomicReference<Throwable> cause) {
    try {
      return this._cache.hasValueDeserializerFor(this, this._factory, type);
    } catch (DatabindException e) {
      if (cause != null)
        cause.set(e); 
    } catch (RuntimeException e) {
      if (cause == null)
        throw e; 
      cause.set(e);
    } 
    return false;
  }
  
  public final JsonDeserializer<Object> findContextualValueDeserializer(JavaType type, BeanProperty prop) throws JsonMappingException {
    JsonDeserializer<Object> deser = this._cache.findValueDeserializer(this, this._factory, type);
    if (deser != null)
      deser = (JsonDeserializer)handleSecondaryContextualization(deser, prop, type); 
    return deser;
  }
  
  public final JsonDeserializer<Object> findNonContextualValueDeserializer(JavaType type) throws JsonMappingException {
    return this._cache.findValueDeserializer(this, this._factory, type);
  }
  
  public final JsonDeserializer<Object> findRootValueDeserializer(JavaType type) throws JsonMappingException {
    JsonDeserializer<Object> deser = this._cache.findValueDeserializer(this, this._factory, type);
    if (deser == null)
      return null; 
    deser = (JsonDeserializer)handleSecondaryContextualization(deser, (BeanProperty)null, type);
    TypeDeserializer typeDeser = this._factory.findTypeDeserializer(this._config, type);
    if (typeDeser != null) {
      typeDeser = typeDeser.forProperty(null);
      return (JsonDeserializer<Object>)new TypeWrappedDeserializer(typeDeser, deser);
    } 
    return deser;
  }
  
  public final KeyDeserializer findKeyDeserializer(JavaType keyType, BeanProperty prop) throws JsonMappingException {
    KeyDeserializer kd;
    try {
      kd = this._cache.findKeyDeserializer(this, this._factory, keyType);
    } catch (IllegalArgumentException iae) {
      reportBadDefinition(keyType, ClassUtil.exceptionMessage(iae));
      kd = null;
    } 
    if (kd instanceof ContextualKeyDeserializer)
      kd = ((ContextualKeyDeserializer)kd).createContextual(this, prop); 
    return kd;
  }
  
  public final JavaType constructType(Class<?> cls) {
    return (cls == null) ? null : this._config.constructType(cls);
  }
  
  public Class<?> findClass(String className) throws ClassNotFoundException {
    return getTypeFactory().findClass(className);
  }
  
  public final ObjectBuffer leaseObjectBuffer() {
    ObjectBuffer buf = this._objectBuffer;
    if (buf == null) {
      buf = new ObjectBuffer();
    } else {
      this._objectBuffer = null;
    } 
    return buf;
  }
  
  public final void returnObjectBuffer(ObjectBuffer buf) {
    if (this._objectBuffer == null || buf
      .initialCapacity() >= this._objectBuffer.initialCapacity())
      this._objectBuffer = buf; 
  }
  
  public final ArrayBuilders getArrayBuilders() {
    if (this._arrayBuilders == null)
      this._arrayBuilders = new ArrayBuilders(); 
    return this._arrayBuilders;
  }
  
  public JsonDeserializer<?> handlePrimaryContextualization(JsonDeserializer<?> deser, BeanProperty prop, JavaType type) throws JsonMappingException {
    if (deser instanceof ContextualDeserializer) {
      this._currentType = new LinkedNode(type, this._currentType);
      try {
        deser = ((ContextualDeserializer)deser).createContextual(this, prop);
      } finally {
        this._currentType = this._currentType.next();
      } 
    } 
    return deser;
  }
  
  public JsonDeserializer<?> handleSecondaryContextualization(JsonDeserializer<?> deser, BeanProperty prop, JavaType type) throws JsonMappingException {
    if (deser instanceof ContextualDeserializer) {
      this._currentType = new LinkedNode(type, this._currentType);
      try {
        deser = ((ContextualDeserializer)deser).createContextual(this, prop);
      } finally {
        this._currentType = this._currentType.next();
      } 
    } 
    return deser;
  }
  
  public Date parseDate(String dateStr) throws IllegalArgumentException {
    try {
      DateFormat df = _getDateFormat();
      return df.parse(dateStr);
    } catch (ParseException e) {
      throw new IllegalArgumentException(String.format("Failed to parse Date value '%s': %s", new Object[] { dateStr, 
              
              ClassUtil.exceptionMessage(e) }));
    } 
  }
  
  public Calendar constructCalendar(Date d) {
    Calendar c = Calendar.getInstance(getTimeZone());
    c.setTime(d);
    return c;
  }
  
  public String extractScalarFromObject(JsonParser p, JsonDeserializer<?> deser, Class<?> scalarType) throws IOException {
    return (String)handleUnexpectedToken(scalarType, p);
  }
  
  public <T> T readValue(JsonParser p, Class<T> type) throws IOException {
    return readValue(p, getTypeFactory().constructType(type));
  }
  
  public <T> T readValue(JsonParser p, JavaType type) throws IOException {
    JsonDeserializer<Object> deser = findRootValueDeserializer(type);
    if (deser == null)
      return reportBadDefinition(type, "Could not find JsonDeserializer for type " + 
          ClassUtil.getTypeDescription(type)); 
    return (T)deser.deserialize(p, this);
  }
  
  public <T> T readPropertyValue(JsonParser p, BeanProperty prop, Class<T> type) throws IOException {
    return readPropertyValue(p, prop, getTypeFactory().constructType(type));
  }
  
  public <T> T readPropertyValue(JsonParser p, BeanProperty prop, JavaType type) throws IOException {
    JsonDeserializer<Object> deser = findContextualValueDeserializer(type, prop);
    if (deser == null)
      return reportBadDefinition(type, String.format("Could not find JsonDeserializer for type %s (via property %s)", new Object[] { ClassUtil.getTypeDescription(type), ClassUtil.nameOf(prop) })); 
    return (T)deser.deserialize(p, this);
  }
  
  public JsonNode readTree(JsonParser p) throws IOException {
    JsonToken t = p.currentToken();
    if (t == null) {
      t = p.nextToken();
      if (t == null)
        return getNodeFactory().missingNode(); 
    } 
    if (t == JsonToken.VALUE_NULL)
      return (JsonNode)getNodeFactory().nullNode(); 
    return (JsonNode)findRootValueDeserializer(this._config.constructType(JsonNode.class))
      .deserialize(p, this);
  }
  
  public <T> T readTreeAsValue(JsonNode n, Class<T> targetType) throws IOException {
    if (n == null)
      return null; 
    try (TreeTraversingParser p = _treeAsTokens(n)) {
      return (T)readValue((JsonParser)p, (Class)targetType);
    } 
  }
  
  public <T> T readTreeAsValue(JsonNode n, JavaType targetType) throws IOException {
    if (n == null)
      return null; 
    try (TreeTraversingParser p = _treeAsTokens(n)) {
      return (T)readValue((JsonParser)p, targetType);
    } 
  }
  
  private TreeTraversingParser _treeAsTokens(JsonNode n) throws IOException {
    ObjectCodec codec = (this._parser == null) ? null : this._parser.getCodec();
    TreeTraversingParser p = new TreeTraversingParser(n, codec);
    p.nextToken();
    return p;
  }
  
  public boolean handleUnknownProperty(JsonParser p, JsonDeserializer<?> deser, Object instanceOrClass, String propName) throws IOException {
    LinkedNode<DeserializationProblemHandler> h = this._config.getProblemHandlers();
    while (h != null) {
      if (((DeserializationProblemHandler)h.value()).handleUnknownProperty(this, p, deser, instanceOrClass, propName))
        return true; 
      h = h.next();
    } 
    if (!isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
      p.skipChildren();
      return true;
    } 
    Collection<Object> propIds = (deser == null) ? null : deser.getKnownPropertyNames();
    throw UnrecognizedPropertyException.from(this._parser, instanceOrClass, propName, propIds);
  }
  
  public Object handleWeirdKey(Class<?> keyClass, String keyValue, String msg, Object... msgArgs) throws IOException {
    msg = _format(msg, msgArgs);
    LinkedNode<DeserializationProblemHandler> h = this._config.getProblemHandlers();
    while (h != null) {
      Object key = ((DeserializationProblemHandler)h.value()).handleWeirdKey(this, keyClass, keyValue, msg);
      if (key != DeserializationProblemHandler.NOT_HANDLED) {
        if (key == null || keyClass.isInstance(key))
          return key; 
        throw weirdStringException(keyValue, keyClass, String.format("DeserializationProblemHandler.handleWeirdStringValue() for type %s returned value of type %s", new Object[] { ClassUtil.getClassDescription(keyClass), 
                ClassUtil.getClassDescription(key) }));
      } 
      h = h.next();
    } 
    throw weirdKeyException(keyClass, keyValue, msg);
  }
  
  public Object handleWeirdStringValue(Class<?> targetClass, String value, String msg, Object... msgArgs) throws IOException {
    msg = _format(msg, msgArgs);
    LinkedNode<DeserializationProblemHandler> h = this._config.getProblemHandlers();
    while (h != null) {
      Object instance = ((DeserializationProblemHandler)h.value()).handleWeirdStringValue(this, targetClass, value, msg);
      if (instance != DeserializationProblemHandler.NOT_HANDLED) {
        if (_isCompatible(targetClass, instance))
          return instance; 
        throw weirdStringException(value, targetClass, String.format("DeserializationProblemHandler.handleWeirdStringValue() for type %s returned value of type %s", new Object[] { ClassUtil.getClassDescription(targetClass), 
                ClassUtil.getClassDescription(instance) }));
      } 
      h = h.next();
    } 
    throw weirdStringException(value, targetClass, msg);
  }
  
  public Object handleWeirdNumberValue(Class<?> targetClass, Number value, String msg, Object... msgArgs) throws IOException {
    msg = _format(msg, msgArgs);
    LinkedNode<DeserializationProblemHandler> h = this._config.getProblemHandlers();
    while (h != null) {
      Object key = ((DeserializationProblemHandler)h.value()).handleWeirdNumberValue(this, targetClass, value, msg);
      if (key != DeserializationProblemHandler.NOT_HANDLED) {
        if (_isCompatible(targetClass, key))
          return key; 
        throw weirdNumberException(value, targetClass, _format("DeserializationProblemHandler.handleWeirdNumberValue() for type %s returned value of type %s", new Object[] { ClassUtil.getClassDescription(targetClass), 
                ClassUtil.getClassDescription(key) }));
      } 
      h = h.next();
    } 
    throw weirdNumberException(value, targetClass, msg);
  }
  
  public Object handleWeirdNativeValue(JavaType targetType, Object badValue, JsonParser p) throws IOException {
    LinkedNode<DeserializationProblemHandler> h = this._config.getProblemHandlers();
    Class<?> raw = targetType.getRawClass();
    for (; h != null; h = h.next()) {
      Object goodValue = ((DeserializationProblemHandler)h.value()).handleWeirdNativeValue(this, targetType, badValue, p);
      if (goodValue != DeserializationProblemHandler.NOT_HANDLED) {
        if (goodValue == null || raw.isInstance(goodValue))
          return goodValue; 
        throw JsonMappingException.from(p, _format("DeserializationProblemHandler.handleWeirdNativeValue() for type %s returned value of type %s", new Object[] { ClassUtil.getClassDescription(targetType), 
                ClassUtil.getClassDescription(goodValue) }));
      } 
    } 
    throw weirdNativeValueException(badValue, raw);
  }
  
  public Object handleMissingInstantiator(Class<?> instClass, ValueInstantiator valueInst, JsonParser p, String msg, Object... msgArgs) throws IOException {
    if (p == null)
      p = getParser(); 
    msg = _format(msg, msgArgs);
    LinkedNode<DeserializationProblemHandler> h = this._config.getProblemHandlers();
    while (h != null) {
      Object instance = ((DeserializationProblemHandler)h.value()).handleMissingInstantiator(this, instClass, valueInst, p, msg);
      if (instance != DeserializationProblemHandler.NOT_HANDLED) {
        if (_isCompatible(instClass, instance))
          return instance; 
        reportBadDefinition(constructType(instClass), String.format("DeserializationProblemHandler.handleMissingInstantiator() for type %s returned value of type %s", new Object[] { ClassUtil.getClassDescription(instClass), 
                ClassUtil.getClassDescription(instance) }));
      } 
      h = h.next();
    } 
    if (valueInst == null) {
      msg = String.format("Cannot construct instance of %s: %s", new Object[] { ClassUtil.nameOf(instClass), msg });
      return reportBadDefinition(instClass, msg);
    } 
    if (!valueInst.canInstantiate()) {
      msg = String.format("Cannot construct instance of %s (no Creators, like default constructor, exist): %s", new Object[] { ClassUtil.nameOf(instClass), msg });
      return reportBadDefinition(instClass, msg);
    } 
    msg = String.format("Cannot construct instance of %s (although at least one Creator exists): %s", new Object[] { ClassUtil.nameOf(instClass), msg });
    return reportInputMismatch(instClass, msg, new Object[0]);
  }
  
  public Object handleInstantiationProblem(Class<?> instClass, Object argument, Throwable t) throws IOException {
    LinkedNode<DeserializationProblemHandler> h = this._config.getProblemHandlers();
    while (h != null) {
      Object instance = ((DeserializationProblemHandler)h.value()).handleInstantiationProblem(this, instClass, argument, t);
      if (instance != DeserializationProblemHandler.NOT_HANDLED) {
        if (_isCompatible(instClass, instance))
          return instance; 
        reportBadDefinition(constructType(instClass), String.format("DeserializationProblemHandler.handleInstantiationProblem() for type %s returned value of type %s", new Object[] { ClassUtil.getClassDescription(instClass), 
                ClassUtil.classNameOf(instance) }));
      } 
      h = h.next();
    } 
    ClassUtil.throwIfIOE(t);
    if (!isEnabled(DeserializationFeature.WRAP_EXCEPTIONS))
      ClassUtil.throwIfRTE(t); 
    throw instantiationException(instClass, t);
  }
  
  public Object handleUnexpectedToken(Class<?> instClass, JsonParser p) throws IOException {
    return handleUnexpectedToken(constructType(instClass), p.currentToken(), p, (String)null, new Object[0]);
  }
  
  public Object handleUnexpectedToken(Class<?> instClass, JsonToken t, JsonParser p, String msg, Object... msgArgs) throws IOException {
    return handleUnexpectedToken(constructType(instClass), t, p, msg, msgArgs);
  }
  
  public Object handleUnexpectedToken(JavaType targetType, JsonParser p) throws IOException {
    return handleUnexpectedToken(targetType, p.currentToken(), p, (String)null, new Object[0]);
  }
  
  public Object handleUnexpectedToken(JavaType targetType, JsonToken t, JsonParser p, String msg, Object... msgArgs) throws IOException {
    msg = _format(msg, msgArgs);
    LinkedNode<DeserializationProblemHandler> h = this._config.getProblemHandlers();
    while (h != null) {
      Object instance = ((DeserializationProblemHandler)h.value()).handleUnexpectedToken(this, targetType, t, p, msg);
      if (instance != DeserializationProblemHandler.NOT_HANDLED) {
        if (_isCompatible(targetType.getRawClass(), instance))
          return instance; 
        reportBadDefinition(targetType, String.format("DeserializationProblemHandler.handleUnexpectedToken() for type %s returned value of type %s", new Object[] { ClassUtil.getTypeDescription(targetType), 
                ClassUtil.classNameOf(instance) }));
      } 
      h = h.next();
    } 
    if (msg == null) {
      String targetDesc = ClassUtil.getTypeDescription(targetType);
      if (t == null) {
        msg = String.format("Unexpected end-of-input when trying read value of type %s", new Object[] { targetDesc });
      } else {
        msg = String.format("Cannot deserialize value of type %s from %s (token `JsonToken.%s`)", new Object[] { targetDesc, 
              _shapeForToken(t), t });
      } 
    } 
    if (t != null && t.isScalarValue())
      p.getText(); 
    reportInputMismatch(targetType, msg, new Object[0]);
    return null;
  }
  
  public JavaType handleUnknownTypeId(JavaType baseType, String id, TypeIdResolver idResolver, String extraDesc) throws IOException {
    LinkedNode<DeserializationProblemHandler> h = this._config.getProblemHandlers();
    while (h != null) {
      JavaType type = ((DeserializationProblemHandler)h.value()).handleUnknownTypeId(this, baseType, id, idResolver, extraDesc);
      if (type != null) {
        if (type.hasRawClass(Void.class))
          return null; 
        if (type.isTypeOrSubTypeOf(baseType.getRawClass()))
          return type; 
        throw invalidTypeIdException(baseType, id, "problem handler tried to resolve into non-subtype: " + 
            
            ClassUtil.getTypeDescription(type));
      } 
      h = h.next();
    } 
    if (!isEnabled(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE))
      return null; 
    throw invalidTypeIdException(baseType, id, extraDesc);
  }
  
  public JavaType handleMissingTypeId(JavaType baseType, TypeIdResolver idResolver, String extraDesc) throws IOException {
    LinkedNode<DeserializationProblemHandler> h = this._config.getProblemHandlers();
    while (h != null) {
      JavaType type = ((DeserializationProblemHandler)h.value()).handleMissingTypeId(this, baseType, idResolver, extraDesc);
      if (type != null) {
        if (type.hasRawClass(Void.class))
          return null; 
        if (type.isTypeOrSubTypeOf(baseType.getRawClass()))
          return type; 
        throw invalidTypeIdException(baseType, null, "problem handler tried to resolve into non-subtype: " + 
            
            ClassUtil.getTypeDescription(type));
      } 
      h = h.next();
    } 
    throw missingTypeIdException(baseType, extraDesc);
  }
  
  public void handleBadMerge(JsonDeserializer<?> deser) throws JsonMappingException {
    if (!isEnabled(MapperFeature.IGNORE_MERGE_FOR_UNMERGEABLE)) {
      JavaType type = constructType(deser.handledType());
      String msg = String.format("Invalid configuration: values of type %s cannot be merged", new Object[] { ClassUtil.getTypeDescription(type) });
      throw InvalidDefinitionException.from(getParser(), msg, type);
    } 
  }
  
  protected boolean _isCompatible(Class<?> target, Object value) {
    if (value == null || target.isInstance(value))
      return true; 
    return (target.isPrimitive() && 
      ClassUtil.wrapperType(target).isInstance(value));
  }
  
  public void reportWrongTokenException(JsonDeserializer<?> deser, JsonToken expToken, String msg, Object... msgArgs) throws JsonMappingException {
    msg = _format(msg, msgArgs);
    throw wrongTokenException(getParser(), deser.handledType(), expToken, msg);
  }
  
  public void reportWrongTokenException(JavaType targetType, JsonToken expToken, String msg, Object... msgArgs) throws JsonMappingException {
    msg = _format(msg, msgArgs);
    throw wrongTokenException(getParser(), targetType, expToken, msg);
  }
  
  public void reportWrongTokenException(Class<?> targetType, JsonToken expToken, String msg, Object... msgArgs) throws JsonMappingException {
    msg = _format(msg, msgArgs);
    throw wrongTokenException(getParser(), targetType, expToken, msg);
  }
  
  public <T> T reportUnresolvedObjectId(ObjectIdReader oidReader, Object bean) throws JsonMappingException {
    String msg = String.format("No Object Id found for an instance of %s, to assign to property '%s'", new Object[] { ClassUtil.classNameOf(bean), oidReader.propertyName });
    return reportInputMismatch((BeanProperty)oidReader.idProperty, msg, new Object[0]);
  }
  
  public <T> T reportInputMismatch(JsonDeserializer<?> src, String msg, Object... msgArgs) throws JsonMappingException {
    msg = _format(msg, msgArgs);
    throw MismatchedInputException.from(getParser(), src.handledType(), msg);
  }
  
  public <T> T reportInputMismatch(Class<?> targetType, String msg, Object... msgArgs) throws JsonMappingException {
    msg = _format(msg, msgArgs);
    throw MismatchedInputException.from(getParser(), targetType, msg);
  }
  
  public <T> T reportInputMismatch(JavaType targetType, String msg, Object... msgArgs) throws JsonMappingException {
    msg = _format(msg, msgArgs);
    throw MismatchedInputException.from(getParser(), targetType, msg);
  }
  
  public <T> T reportInputMismatch(BeanProperty prop, String msg, Object... msgArgs) throws JsonMappingException {
    msg = _format(msg, msgArgs);
    JavaType type = (prop == null) ? null : prop.getType();
    MismatchedInputException e = MismatchedInputException.from(getParser(), type, msg);
    if (prop != null) {
      AnnotatedMember member = prop.getMember();
      if (member != null)
        e.prependPath(member.getDeclaringClass(), prop.getName()); 
    } 
    throw e;
  }
  
  public <T> T reportPropertyInputMismatch(Class<?> targetType, String propertyName, String msg, Object... msgArgs) throws JsonMappingException {
    msg = _format(msg, msgArgs);
    MismatchedInputException e = MismatchedInputException.from(getParser(), targetType, msg);
    if (propertyName != null)
      e.prependPath(targetType, propertyName); 
    throw e;
  }
  
  public <T> T reportPropertyInputMismatch(JavaType targetType, String propertyName, String msg, Object... msgArgs) throws JsonMappingException {
    return reportPropertyInputMismatch(targetType.getRawClass(), propertyName, msg, msgArgs);
  }
  
  public <T> T reportBadCoercion(JsonDeserializer<?> src, Class<?> targetType, Object inputValue, String msg, Object... msgArgs) throws JsonMappingException {
    msg = _format(msg, msgArgs);
    InvalidFormatException e = InvalidFormatException.from(getParser(), msg, inputValue, targetType);
    throw e;
  }
  
  public <T> T reportTrailingTokens(Class<?> targetType, JsonParser p, JsonToken trailingToken) throws JsonMappingException {
    throw MismatchedInputException.from(p, targetType, String.format("Trailing token (of type %s) found after value (bound as %s): not allowed as per `DeserializationFeature.FAIL_ON_TRAILING_TOKENS`", new Object[] { trailingToken, 
            
            ClassUtil.nameOf(targetType) }));
  }
  
  public <T> T reportBadTypeDefinition(BeanDescription bean, String msg, Object... msgArgs) throws JsonMappingException {
    msg = _format(msg, msgArgs);
    String beanDesc = ClassUtil.nameOf(bean.getBeanClass());
    msg = String.format("Invalid type definition for type %s: %s", new Object[] { beanDesc, msg });
    throw InvalidDefinitionException.from(this._parser, msg, bean, null);
  }
  
  public <T> T reportBadPropertyDefinition(BeanDescription bean, BeanPropertyDefinition prop, String msg, Object... msgArgs) throws JsonMappingException {
    msg = _format(msg, msgArgs);
    String propName = ClassUtil.nameOf((Named)prop);
    String beanDesc = ClassUtil.nameOf(bean.getBeanClass());
    msg = String.format("Invalid definition for property %s (of type %s): %s", new Object[] { propName, beanDesc, msg });
    throw InvalidDefinitionException.from(this._parser, msg, bean, prop);
  }
  
  public <T> T reportBadDefinition(JavaType type, String msg) throws JsonMappingException {
    throw InvalidDefinitionException.from(this._parser, msg, type);
  }
  
  public JsonMappingException wrongTokenException(JsonParser p, JavaType targetType, JsonToken expToken, String extra) {
    String msg = String.format("Unexpected token (%s), expected %s", new Object[] { p
          .currentToken(), expToken });
    msg = _colonConcat(msg, extra);
    return (JsonMappingException)MismatchedInputException.from(p, targetType, msg);
  }
  
  public JsonMappingException wrongTokenException(JsonParser p, Class<?> targetType, JsonToken expToken, String extra) {
    String msg = String.format("Unexpected token (%s), expected %s", new Object[] { p
          .currentToken(), expToken });
    msg = _colonConcat(msg, extra);
    return (JsonMappingException)MismatchedInputException.from(p, targetType, msg);
  }
  
  public JsonMappingException weirdKeyException(Class<?> keyClass, String keyValue, String msg) {
    return (JsonMappingException)InvalidFormatException.from(this._parser, 
        String.format("Cannot deserialize Map key of type %s from String %s: %s", new Object[] { ClassUtil.nameOf(keyClass), _quotedString(keyValue), msg }), keyValue, keyClass);
  }
  
  public JsonMappingException weirdStringException(String value, Class<?> instClass, String msgBase) {
    String msg = String.format("Cannot deserialize value of type %s from String %s: %s", new Object[] { ClassUtil.nameOf(instClass), _quotedString(value), msgBase });
    return (JsonMappingException)InvalidFormatException.from(this._parser, msg, value, instClass);
  }
  
  public JsonMappingException weirdNumberException(Number value, Class<?> instClass, String msg) {
    return (JsonMappingException)InvalidFormatException.from(this._parser, 
        String.format("Cannot deserialize value of type %s from number %s: %s", new Object[] { ClassUtil.nameOf(instClass), String.valueOf(value), msg }), value, instClass);
  }
  
  public JsonMappingException weirdNativeValueException(Object value, Class<?> instClass) {
    return (JsonMappingException)InvalidFormatException.from(this._parser, String.format("Cannot deserialize value of type %s from native value (`JsonToken.VALUE_EMBEDDED_OBJECT`) of type %s: incompatible types", new Object[] { ClassUtil.nameOf(instClass), ClassUtil.classNameOf(value) }), value, instClass);
  }
  
  public JsonMappingException instantiationException(Class<?> instClass, Throwable cause) {
    String excMsg;
    if (cause == null) {
      excMsg = "N/A";
    } else if ((excMsg = ClassUtil.exceptionMessage(cause)) == null) {
      excMsg = ClassUtil.nameOf(cause.getClass());
    } 
    String msg = String.format("Cannot construct instance of %s, problem: %s", new Object[] { ClassUtil.nameOf(instClass), excMsg });
    return (JsonMappingException)ValueInstantiationException.from(this._parser, msg, constructType(instClass), cause);
  }
  
  public JsonMappingException instantiationException(Class<?> instClass, String msg0) {
    return (JsonMappingException)ValueInstantiationException.from(this._parser, 
        String.format("Cannot construct instance of %s: %s", new Object[] { ClassUtil.nameOf(instClass), msg0 }), constructType(instClass));
  }
  
  public JsonMappingException invalidTypeIdException(JavaType baseType, String typeId, String extraDesc) {
    String msg = String.format("Could not resolve type id '%s' as a subtype of %s", new Object[] { typeId, 
          ClassUtil.getTypeDescription(baseType) });
    return (JsonMappingException)InvalidTypeIdException.from(this._parser, _colonConcat(msg, extraDesc), baseType, typeId);
  }
  
  public JsonMappingException missingTypeIdException(JavaType baseType, String extraDesc) {
    String msg = String.format("Could not resolve subtype of %s", new Object[] { baseType });
    return (JsonMappingException)InvalidTypeIdException.from(this._parser, _colonConcat(msg, extraDesc), baseType, null);
  }
  
  protected DateFormat _getDateFormat() {
    if (this._dateFormat != null)
      return this._dateFormat; 
    DateFormat df = this._config.getDateFormat();
    this._dateFormat = df = (DateFormat)df.clone();
    return df;
  }
  
  protected String _shapeForToken(JsonToken t) {
    return JsonToken.valueDescFor(t);
  }
  
  public abstract ReadableObjectId findObjectId(Object paramObject, ObjectIdGenerator<?> paramObjectIdGenerator, ObjectIdResolver paramObjectIdResolver);
  
  public abstract void checkUnresolvedObjectId() throws UnresolvedForwardReference;
  
  public abstract JsonDeserializer<Object> deserializerInstance(Annotated paramAnnotated, Object paramObject) throws JsonMappingException;
  
  public abstract KeyDeserializer keyDeserializerInstance(Annotated paramAnnotated, Object paramObject) throws JsonMappingException;
}
