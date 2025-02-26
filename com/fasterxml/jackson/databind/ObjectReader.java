package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.filter.FilteringParserDelegate;
import com.fasterxml.jackson.core.filter.JsonPointerBasedFilter;
import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.DatatypeFeature;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.DataInput;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectReader extends ObjectCodec implements Versioned, Serializable {
  private static final long serialVersionUID = 2L;
  
  protected final DeserializationConfig _config;
  
  protected final DefaultDeserializationContext _context;
  
  protected final JsonFactory _parserFactory;
  
  protected final boolean _unwrapRoot;
  
  private final TokenFilter _filter;
  
  protected final JavaType _valueType;
  
  protected final JsonDeserializer<Object> _rootDeserializer;
  
  protected final Object _valueToUpdate;
  
  protected final FormatSchema _schema;
  
  protected final InjectableValues _injectableValues;
  
  protected final DataFormatReaders _dataFormatReaders;
  
  protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;
  
  protected transient JavaType _jsonNodeType;
  
  protected ObjectReader(ObjectMapper mapper, DeserializationConfig config) {
    this(mapper, config, null, null, null, null);
  }
  
  protected ObjectReader(ObjectMapper mapper, DeserializationConfig config, JavaType valueType, Object valueToUpdate, FormatSchema schema, InjectableValues injectableValues) {
    this._config = config;
    this._context = mapper._deserializationContext;
    this._rootDeserializers = mapper._rootDeserializers;
    this._parserFactory = mapper._jsonFactory;
    this._valueType = valueType;
    this._valueToUpdate = valueToUpdate;
    this._schema = schema;
    this._injectableValues = injectableValues;
    this._unwrapRoot = config.useRootWrapping();
    this._rootDeserializer = _prefetchRootDeserializer(valueType);
    this._dataFormatReaders = null;
    this._filter = null;
  }
  
  protected ObjectReader(ObjectReader base, DeserializationConfig config, JavaType valueType, JsonDeserializer<Object> rootDeser, Object valueToUpdate, FormatSchema schema, InjectableValues injectableValues, DataFormatReaders dataFormatReaders) {
    this._config = config;
    this._context = base._context;
    this._rootDeserializers = base._rootDeserializers;
    this._parserFactory = base._parserFactory;
    this._valueType = valueType;
    this._rootDeserializer = rootDeser;
    this._valueToUpdate = valueToUpdate;
    this._schema = schema;
    this._injectableValues = injectableValues;
    this._unwrapRoot = config.useRootWrapping();
    this._dataFormatReaders = dataFormatReaders;
    this._filter = base._filter;
  }
  
  protected ObjectReader(ObjectReader base, DeserializationConfig config) {
    this._config = config;
    this._context = base._context;
    this._rootDeserializers = base._rootDeserializers;
    this._parserFactory = base._parserFactory;
    this._valueType = base._valueType;
    this._rootDeserializer = base._rootDeserializer;
    this._valueToUpdate = base._valueToUpdate;
    this._schema = base._schema;
    this._injectableValues = base._injectableValues;
    this._unwrapRoot = config.useRootWrapping();
    this._dataFormatReaders = base._dataFormatReaders;
    this._filter = base._filter;
  }
  
  protected ObjectReader(ObjectReader base, JsonFactory f) {
    this
      ._config = (DeserializationConfig)base._config.with(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, f.requiresPropertyOrdering());
    this._context = base._context;
    this._rootDeserializers = base._rootDeserializers;
    this._parserFactory = f;
    this._valueType = base._valueType;
    this._rootDeserializer = base._rootDeserializer;
    this._valueToUpdate = base._valueToUpdate;
    this._schema = base._schema;
    this._injectableValues = base._injectableValues;
    this._unwrapRoot = base._unwrapRoot;
    this._dataFormatReaders = base._dataFormatReaders;
    this._filter = base._filter;
  }
  
  protected ObjectReader(ObjectReader base, TokenFilter filter) {
    this._config = base._config;
    this._context = base._context;
    this._rootDeserializers = base._rootDeserializers;
    this._parserFactory = base._parserFactory;
    this._valueType = base._valueType;
    this._rootDeserializer = base._rootDeserializer;
    this._valueToUpdate = base._valueToUpdate;
    this._schema = base._schema;
    this._injectableValues = base._injectableValues;
    this._unwrapRoot = base._unwrapRoot;
    this._dataFormatReaders = base._dataFormatReaders;
    this._filter = filter;
  }
  
  public Version version() {
    return PackageVersion.VERSION;
  }
  
  protected ObjectReader _new(ObjectReader base, JsonFactory f) {
    return new ObjectReader(base, f);
  }
  
  protected ObjectReader _new(ObjectReader base, DeserializationConfig config) {
    return new ObjectReader(base, config);
  }
  
  protected ObjectReader _new(ObjectReader base, DeserializationConfig config, JavaType valueType, JsonDeserializer<Object> rootDeser, Object valueToUpdate, FormatSchema schema, InjectableValues injectableValues, DataFormatReaders dataFormatReaders) {
    return new ObjectReader(base, config, valueType, rootDeser, valueToUpdate, schema, injectableValues, dataFormatReaders);
  }
  
  protected <T> MappingIterator<T> _newIterator(JsonParser p, DeserializationContext ctxt, JsonDeserializer<?> deser, boolean parserManaged) {
    return new MappingIterator<>(this._valueType, p, ctxt, deser, parserManaged, this._valueToUpdate);
  }
  
  protected JsonToken _initForReading(DeserializationContext ctxt, JsonParser p) throws IOException {
    this._config.initialize(p, this._schema);
    JsonToken t = p.currentToken();
    if (t == null) {
      t = p.nextToken();
      if (t == null)
        ctxt.reportInputMismatch(this._valueType, "No content to map due to end-of-input", new Object[0]); 
    } 
    return t;
  }
  
  protected void _initForMultiRead(DeserializationContext ctxt, JsonParser p) throws IOException {
    this._config.initialize(p, this._schema);
  }
  
  public ObjectReader with(DeserializationFeature feature) {
    return _with(this._config.with(feature));
  }
  
  public ObjectReader with(DeserializationFeature first, DeserializationFeature... other) {
    return _with(this._config.with(first, other));
  }
  
  public ObjectReader withFeatures(DeserializationFeature... features) {
    return _with(this._config.withFeatures(features));
  }
  
  public ObjectReader without(DeserializationFeature feature) {
    return _with(this._config.without(feature));
  }
  
  public ObjectReader without(DeserializationFeature first, DeserializationFeature... other) {
    return _with(this._config.without(first, other));
  }
  
  public ObjectReader withoutFeatures(DeserializationFeature... features) {
    return _with(this._config.withoutFeatures(features));
  }
  
  public ObjectReader with(DatatypeFeature feature) {
    return _with((DeserializationConfig)this._config.with(feature));
  }
  
  public ObjectReader withFeatures(DatatypeFeature... features) {
    return _with((DeserializationConfig)this._config.withFeatures(features));
  }
  
  public ObjectReader without(DatatypeFeature feature) {
    return _with((DeserializationConfig)this._config.without(feature));
  }
  
  public ObjectReader withoutFeatures(DatatypeFeature... features) {
    return _with((DeserializationConfig)this._config.withoutFeatures(features));
  }
  
  public ObjectReader with(JsonParser.Feature feature) {
    return _with(this._config.with(feature));
  }
  
  public ObjectReader withFeatures(JsonParser.Feature... features) {
    return _with(this._config.withFeatures(features));
  }
  
  public ObjectReader without(JsonParser.Feature feature) {
    return _with(this._config.without(feature));
  }
  
  public ObjectReader withoutFeatures(JsonParser.Feature... features) {
    return _with(this._config.withoutFeatures(features));
  }
  
  public ObjectReader with(StreamReadFeature feature) {
    return _with(this._config.with(feature.mappedFeature()));
  }
  
  public ObjectReader without(StreamReadFeature feature) {
    return _with(this._config.without(feature.mappedFeature()));
  }
  
  public ObjectReader with(FormatFeature feature) {
    return _with(this._config.with(feature));
  }
  
  public ObjectReader withFeatures(FormatFeature... features) {
    return _with(this._config.withFeatures(features));
  }
  
  public ObjectReader without(FormatFeature feature) {
    return _with(this._config.without(feature));
  }
  
  public ObjectReader withoutFeatures(FormatFeature... features) {
    return _with(this._config.withoutFeatures(features));
  }
  
  public ObjectReader at(String pointerExpr) {
    _assertNotNull("pointerExpr", pointerExpr);
    return new ObjectReader(this, (TokenFilter)new JsonPointerBasedFilter(pointerExpr));
  }
  
  public ObjectReader at(JsonPointer pointer) {
    _assertNotNull("pointer", pointer);
    return new ObjectReader(this, (TokenFilter)new JsonPointerBasedFilter(pointer));
  }
  
  public ObjectReader with(DeserializationConfig config) {
    return _with(config);
  }
  
  public ObjectReader with(InjectableValues injectableValues) {
    if (this._injectableValues == injectableValues)
      return this; 
    return _new(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, this._schema, injectableValues, this._dataFormatReaders);
  }
  
  public ObjectReader with(JsonNodeFactory f) {
    return _with(this._config.with(f));
  }
  
  public ObjectReader with(JsonFactory f) {
    if (f == this._parserFactory)
      return this; 
    ObjectReader r = _new(this, f);
    if (f.getCodec() == null)
      f.setCodec(r); 
    return r;
  }
  
  public ObjectReader withRootName(String rootName) {
    return _with((DeserializationConfig)this._config.withRootName(rootName));
  }
  
  public ObjectReader withRootName(PropertyName rootName) {
    return _with(this._config.withRootName(rootName));
  }
  
  public ObjectReader withoutRootName() {
    return _with(this._config.withRootName(PropertyName.NO_NAME));
  }
  
  public ObjectReader with(FormatSchema schema) {
    if (this._schema == schema)
      return this; 
    _verifySchemaType(schema);
    return _new(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, schema, this._injectableValues, this._dataFormatReaders);
  }
  
  public ObjectReader forType(JavaType valueType) {
    if (valueType != null && valueType.equals(this._valueType))
      return this; 
    JsonDeserializer<Object> rootDeser = _prefetchRootDeserializer(valueType);
    DataFormatReaders det = this._dataFormatReaders;
    if (det != null)
      det = det.withType(valueType); 
    return _new(this, this._config, valueType, rootDeser, this._valueToUpdate, this._schema, this._injectableValues, det);
  }
  
  public ObjectReader forType(Class<?> valueType) {
    return forType(this._config.constructType(valueType));
  }
  
  public ObjectReader forType(Type valueType) {
    return forType(this._config.getTypeFactory().constructType(valueType));
  }
  
  public ObjectReader forType(TypeReference<?> valueTypeRef) {
    return forType(this._config.getTypeFactory().constructType(valueTypeRef.getType()));
  }
  
  @Deprecated
  public ObjectReader withType(JavaType valueType) {
    return forType(valueType);
  }
  
  @Deprecated
  public ObjectReader withType(Class<?> valueType) {
    return forType(this._config.constructType(valueType));
  }
  
  @Deprecated
  public ObjectReader withType(Type valueType) {
    return forType(this._config.getTypeFactory().constructType(valueType));
  }
  
  @Deprecated
  public ObjectReader withType(TypeReference<?> valueTypeRef) {
    return forType(this._config.getTypeFactory().constructType(valueTypeRef.getType()));
  }
  
  public ObjectReader withValueToUpdate(Object value) {
    JavaType t;
    if (value == this._valueToUpdate)
      return this; 
    if (value == null)
      return _new(this, this._config, this._valueType, this._rootDeserializer, null, this._schema, this._injectableValues, this._dataFormatReaders); 
    if (this._valueType == null) {
      t = this._config.constructType(value.getClass());
    } else {
      t = this._valueType;
    } 
    return _new(this, this._config, t, this._rootDeserializer, value, this._schema, this._injectableValues, this._dataFormatReaders);
  }
  
  public ObjectReader withView(Class<?> activeView) {
    return _with(this._config.withView(activeView));
  }
  
  public ObjectReader with(Locale l) {
    return _with((DeserializationConfig)this._config.with(l));
  }
  
  public ObjectReader with(TimeZone tz) {
    return _with((DeserializationConfig)this._config.with(tz));
  }
  
  public ObjectReader withHandler(DeserializationProblemHandler h) {
    return _with(this._config.withHandler(h));
  }
  
  public ObjectReader with(Base64Variant defaultBase64) {
    return _with((DeserializationConfig)this._config.with(defaultBase64));
  }
  
  public ObjectReader withFormatDetection(ObjectReader... readers) {
    return withFormatDetection(new DataFormatReaders(readers));
  }
  
  public ObjectReader withFormatDetection(DataFormatReaders readers) {
    return _new(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, this._schema, this._injectableValues, readers);
  }
  
  public ObjectReader with(ContextAttributes attrs) {
    return _with(this._config.with(attrs));
  }
  
  public ObjectReader withAttributes(Map<?, ?> attrs) {
    return _with((DeserializationConfig)this._config.withAttributes(attrs));
  }
  
  public ObjectReader withAttribute(Object key, Object value) {
    return _with((DeserializationConfig)this._config.withAttribute(key, value));
  }
  
  public ObjectReader withoutAttribute(Object key) {
    return _with((DeserializationConfig)this._config.withoutAttribute(key));
  }
  
  protected ObjectReader _with(DeserializationConfig newConfig) {
    if (newConfig == this._config)
      return this; 
    ObjectReader r = _new(this, newConfig);
    if (this._dataFormatReaders != null)
      r = r.withFormatDetection(this._dataFormatReaders.with(newConfig)); 
    return r;
  }
  
  public boolean isEnabled(DeserializationFeature f) {
    return this._config.isEnabled(f);
  }
  
  public boolean isEnabled(MapperFeature f) {
    return this._config.isEnabled(f);
  }
  
  public boolean isEnabled(DatatypeFeature f) {
    return this._config.isEnabled(f);
  }
  
  public boolean isEnabled(JsonParser.Feature f) {
    return this._config.isEnabled(f, this._parserFactory);
  }
  
  public boolean isEnabled(StreamReadFeature f) {
    return this._config.isEnabled(f.mappedFeature(), this._parserFactory);
  }
  
  public DeserializationConfig getConfig() {
    return this._config;
  }
  
  public JsonFactory getFactory() {
    return this._parserFactory;
  }
  
  public TypeFactory getTypeFactory() {
    return this._config.getTypeFactory();
  }
  
  public ContextAttributes getAttributes() {
    return this._config.getAttributes();
  }
  
  public InjectableValues getInjectableValues() {
    return this._injectableValues;
  }
  
  public JavaType getValueType() {
    return this._valueType;
  }
  
  public JsonParser createParser(File src) throws IOException {
    _assertNotNull("src", src);
    return this._config.initialize(this._parserFactory.createParser(src), this._schema);
  }
  
  public JsonParser createParser(URL src) throws IOException {
    _assertNotNull("src", src);
    return this._config.initialize(this._parserFactory.createParser(src), this._schema);
  }
  
  public JsonParser createParser(InputStream in) throws IOException {
    _assertNotNull("in", in);
    return this._config.initialize(this._parserFactory.createParser(in), this._schema);
  }
  
  public JsonParser createParser(Reader r) throws IOException {
    _assertNotNull("r", r);
    return this._config.initialize(this._parserFactory.createParser(r), this._schema);
  }
  
  public JsonParser createParser(byte[] content) throws IOException {
    _assertNotNull("content", content);
    return this._config.initialize(this._parserFactory.createParser(content), this._schema);
  }
  
  public JsonParser createParser(byte[] content, int offset, int len) throws IOException {
    _assertNotNull("content", content);
    return this._config.initialize(this._parserFactory.createParser(content, offset, len), this._schema);
  }
  
  public JsonParser createParser(String content) throws IOException {
    _assertNotNull("content", content);
    return this._config.initialize(this._parserFactory.createParser(content), this._schema);
  }
  
  public JsonParser createParser(char[] content) throws IOException {
    _assertNotNull("content", content);
    return this._config.initialize(this._parserFactory.createParser(content), this._schema);
  }
  
  public JsonParser createParser(char[] content, int offset, int len) throws IOException {
    _assertNotNull("content", content);
    return this._config.initialize(this._parserFactory.createParser(content, offset, len), this._schema);
  }
  
  public JsonParser createParser(DataInput content) throws IOException {
    _assertNotNull("content", content);
    return this._config.initialize(this._parserFactory.createParser(content), this._schema);
  }
  
  public JsonParser createNonBlockingByteArrayParser() throws IOException {
    return this._config.initialize(this._parserFactory.createNonBlockingByteArrayParser(), this._schema);
  }
  
  public <T> T readValue(JsonParser p) throws IOException {
    _assertNotNull("p", p);
    return (T)_bind(p, this._valueToUpdate);
  }
  
  public <T> T readValue(JsonParser p, Class<T> valueType) throws IOException {
    _assertNotNull("p", p);
    return forType(valueType).readValue(p);
  }
  
  public <T> T readValue(JsonParser p, TypeReference<T> valueTypeRef) throws IOException {
    _assertNotNull("p", p);
    return forType(valueTypeRef).readValue(p);
  }
  
  public <T> T readValue(JsonParser p, ResolvedType valueType) throws IOException {
    _assertNotNull("p", p);
    return forType((JavaType)valueType).readValue(p);
  }
  
  public <T> T readValue(JsonParser p, JavaType valueType) throws IOException {
    _assertNotNull("p", p);
    return forType(valueType).readValue(p);
  }
  
  public <T> Iterator<T> readValues(JsonParser p, Class<T> valueType) throws IOException {
    _assertNotNull("p", p);
    return forType(valueType).readValues(p);
  }
  
  public <T> Iterator<T> readValues(JsonParser p, TypeReference<T> valueTypeRef) throws IOException {
    _assertNotNull("p", p);
    return forType(valueTypeRef).readValues(p);
  }
  
  public <T> Iterator<T> readValues(JsonParser p, ResolvedType valueType) throws IOException {
    _assertNotNull("p", p);
    return readValues(p, (JavaType)valueType);
  }
  
  public <T> Iterator<T> readValues(JsonParser p, JavaType valueType) throws IOException {
    _assertNotNull("p", p);
    return forType(valueType).readValues(p);
  }
  
  public JsonNode createArrayNode() {
    return (JsonNode)this._config.getNodeFactory().arrayNode();
  }
  
  public JsonNode createObjectNode() {
    return (JsonNode)this._config.getNodeFactory().objectNode();
  }
  
  public JsonNode missingNode() {
    return this._config.getNodeFactory().missingNode();
  }
  
  public JsonNode nullNode() {
    return (JsonNode)this._config.getNodeFactory().nullNode();
  }
  
  public JsonParser treeAsTokens(TreeNode n) {
    _assertNotNull("n", n);
    ObjectReader codec = withValueToUpdate(null);
    return (JsonParser)new TreeTraversingParser((JsonNode)n, codec);
  }
  
  public <T extends TreeNode> T readTree(JsonParser p) throws IOException {
    _assertNotNull("p", p);
    return (T)_bindAsTreeOrNull(p);
  }
  
  public void writeTree(JsonGenerator g, TreeNode rootNode) {
    throw new UnsupportedOperationException();
  }
  
  public <T> T readValue(InputStream src) throws IOException {
    if (this._dataFormatReaders != null)
      return (T)_detectBindAndClose(this._dataFormatReaders.findFormat(src), false); 
    return (T)_bindAndClose(_considerFilter(createParser(src), false));
  }
  
  public <T> T readValue(InputStream src, Class<T> valueType) throws IOException {
    return forType(valueType).readValue(src);
  }
  
  public <T> T readValue(Reader src) throws IOException {
    if (this._dataFormatReaders != null)
      _reportUndetectableSource(src); 
    return (T)_bindAndClose(_considerFilter(createParser(src), false));
  }
  
  public <T> T readValue(Reader src, Class<T> valueType) throws IOException {
    return forType(valueType).readValue(src);
  }
  
  public <T> T readValue(String src) throws JsonProcessingException, JsonMappingException {
    if (this._dataFormatReaders != null)
      _reportUndetectableSource(src); 
    try {
      return (T)_bindAndClose(_considerFilter(createParser(src), false));
    } catch (JsonProcessingException e) {
      throw e;
    } catch (IOException e) {
      throw JsonMappingException.fromUnexpectedIOE(e);
    } 
  }
  
  public <T> T readValue(String src, Class<T> valueType) throws IOException {
    return forType(valueType).readValue(src);
  }
  
  public <T> T readValue(byte[] content) throws IOException {
    if (this._dataFormatReaders != null)
      return (T)_detectBindAndClose(content, 0, content.length); 
    return (T)_bindAndClose(_considerFilter(createParser(content), false));
  }
  
  public <T> T readValue(byte[] content, Class<T> valueType) throws IOException {
    return forType(valueType).readValue(content);
  }
  
  public <T> T readValue(byte[] buffer, int offset, int length) throws IOException {
    if (this._dataFormatReaders != null)
      return (T)_detectBindAndClose(buffer, offset, length); 
    return (T)_bindAndClose(_considerFilter(createParser(buffer, offset, length), false));
  }
  
  public <T> T readValue(byte[] buffer, int offset, int length, Class<T> valueType) throws IOException {
    return forType(valueType).readValue(buffer, offset, length);
  }
  
  public <T> T readValue(File src) throws IOException {
    if (this._dataFormatReaders != null)
      return (T)_detectBindAndClose(this._dataFormatReaders.findFormat(_inputStream(src)), true); 
    return (T)_bindAndClose(_considerFilter(createParser(src), false));
  }
  
  public <T> T readValue(File src, Class<T> valueType) throws IOException {
    return forType(valueType).readValue(src);
  }
  
  public <T> T readValue(URL src) throws IOException {
    if (this._dataFormatReaders != null)
      return (T)_detectBindAndClose(this._dataFormatReaders.findFormat(_inputStream(src)), true); 
    return (T)_bindAndClose(_considerFilter(createParser(src), false));
  }
  
  public <T> T readValue(URL src, Class<T> valueType) throws IOException {
    return forType(valueType).readValue(src);
  }
  
  public <T> T readValue(JsonNode content) throws IOException {
    _assertNotNull("content", content);
    if (this._dataFormatReaders != null)
      _reportUndetectableSource(content); 
    return (T)_bindAndClose(_considerFilter(treeAsTokens(content), false));
  }
  
  public <T> T readValue(JsonNode content, Class<T> valueType) throws IOException {
    return forType(valueType).readValue(content);
  }
  
  public <T> T readValue(DataInput src) throws IOException {
    if (this._dataFormatReaders != null)
      _reportUndetectableSource(src); 
    return (T)_bindAndClose(_considerFilter(createParser(src), false));
  }
  
  public <T> T readValue(DataInput content, Class<T> valueType) throws IOException {
    return forType(valueType).readValue(content);
  }
  
  public JsonNode readTree(InputStream src) throws IOException {
    if (this._dataFormatReaders != null)
      return _detectBindAndCloseAsTree(src); 
    return _bindAndCloseAsTree(_considerFilter(createParser(src), false));
  }
  
  public JsonNode readTree(Reader src) throws IOException {
    if (this._dataFormatReaders != null)
      _reportUndetectableSource(src); 
    return _bindAndCloseAsTree(_considerFilter(createParser(src), false));
  }
  
  public JsonNode readTree(String json) throws JsonProcessingException, JsonMappingException {
    if (this._dataFormatReaders != null)
      _reportUndetectableSource(json); 
    try {
      return _bindAndCloseAsTree(_considerFilter(createParser(json), false));
    } catch (JsonProcessingException e) {
      throw e;
    } catch (IOException e) {
      throw JsonMappingException.fromUnexpectedIOE(e);
    } 
  }
  
  public JsonNode readTree(byte[] json) throws IOException {
    _assertNotNull("json", json);
    if (this._dataFormatReaders != null)
      _reportUndetectableSource(json); 
    return _bindAndCloseAsTree(_considerFilter(createParser(json), false));
  }
  
  public JsonNode readTree(byte[] json, int offset, int len) throws IOException {
    if (this._dataFormatReaders != null)
      _reportUndetectableSource(json); 
    return _bindAndCloseAsTree(_considerFilter(createParser(json, offset, len), false));
  }
  
  public JsonNode readTree(DataInput src) throws IOException {
    if (this._dataFormatReaders != null)
      _reportUndetectableSource(src); 
    return _bindAndCloseAsTree(_considerFilter(createParser(src), false));
  }
  
  public <T> MappingIterator<T> readValues(JsonParser p) throws IOException {
    _assertNotNull("p", p);
    DefaultDeserializationContext defaultDeserializationContext = createDeserializationContext(p);
    return _newIterator(p, (DeserializationContext)defaultDeserializationContext, _findRootDeserializer((DeserializationContext)defaultDeserializationContext), false);
  }
  
  public <T> MappingIterator<T> readValues(InputStream src) throws IOException {
    if (this._dataFormatReaders != null)
      return _detectBindAndReadValues(this._dataFormatReaders.findFormat(src), false); 
    return _bindAndReadValues(_considerFilter(createParser(src), true));
  }
  
  public <T> MappingIterator<T> readValues(Reader src) throws IOException {
    if (this._dataFormatReaders != null)
      _reportUndetectableSource(src); 
    JsonParser p = _considerFilter(createParser(src), true);
    DefaultDeserializationContext defaultDeserializationContext = createDeserializationContext(p);
    _initForMultiRead((DeserializationContext)defaultDeserializationContext, p);
    p.nextToken();
    return _newIterator(p, (DeserializationContext)defaultDeserializationContext, _findRootDeserializer((DeserializationContext)defaultDeserializationContext), true);
  }
  
  public <T> MappingIterator<T> readValues(String json) throws IOException {
    if (this._dataFormatReaders != null)
      _reportUndetectableSource(json); 
    JsonParser p = _considerFilter(createParser(json), true);
    DefaultDeserializationContext defaultDeserializationContext = createDeserializationContext(p);
    _initForMultiRead((DeserializationContext)defaultDeserializationContext, p);
    p.nextToken();
    return _newIterator(p, (DeserializationContext)defaultDeserializationContext, _findRootDeserializer((DeserializationContext)defaultDeserializationContext), true);
  }
  
  public <T> MappingIterator<T> readValues(byte[] src, int offset, int length) throws IOException {
    if (this._dataFormatReaders != null)
      return _detectBindAndReadValues(this._dataFormatReaders.findFormat(src, offset, length), false); 
    return _bindAndReadValues(_considerFilter(createParser(src, offset, length), true));
  }
  
  public final <T> MappingIterator<T> readValues(byte[] src) throws IOException {
    _assertNotNull("src", src);
    return readValues(src, 0, src.length);
  }
  
  public <T> MappingIterator<T> readValues(File src) throws IOException {
    if (this._dataFormatReaders != null)
      return _detectBindAndReadValues(this._dataFormatReaders
          .findFormat(_inputStream(src)), false); 
    return _bindAndReadValues(_considerFilter(createParser(src), true));
  }
  
  public <T> MappingIterator<T> readValues(URL src) throws IOException {
    if (this._dataFormatReaders != null)
      return _detectBindAndReadValues(this._dataFormatReaders
          .findFormat(_inputStream(src)), true); 
    return _bindAndReadValues(_considerFilter(createParser(src), true));
  }
  
  public <T> MappingIterator<T> readValues(DataInput src) throws IOException {
    if (this._dataFormatReaders != null)
      _reportUndetectableSource(src); 
    return _bindAndReadValues(_considerFilter(createParser(src), true));
  }
  
  public <T> T treeToValue(TreeNode n, Class<T> valueType) throws JsonProcessingException {
    _assertNotNull("n", n);
    try {
      return readValue(treeAsTokens(n), valueType);
    } catch (JsonProcessingException e) {
      throw e;
    } catch (IOException e) {
      throw JsonMappingException.fromUnexpectedIOE(e);
    } 
  }
  
  public <T> T treeToValue(TreeNode n, JavaType valueType) throws JsonProcessingException {
    _assertNotNull("n", n);
    try {
      return readValue(treeAsTokens(n), valueType);
    } catch (JsonProcessingException e) {
      throw e;
    } catch (IOException e) {
      throw JsonMappingException.fromUnexpectedIOE(e);
    } 
  }
  
  public void writeValue(JsonGenerator gen, Object value) throws IOException {
    throw new UnsupportedOperationException("Not implemented for ObjectReader");
  }
  
  protected Object _bind(JsonParser p, Object valueToUpdate) throws IOException {
    Object result;
    DefaultDeserializationContext ctxt = createDeserializationContext(p);
    JsonToken t = _initForReading((DeserializationContext)ctxt, p);
    if (t == JsonToken.VALUE_NULL) {
      if (valueToUpdate == null) {
        result = _findRootDeserializer((DeserializationContext)ctxt).getNullValue((DeserializationContext)ctxt);
      } else {
        result = valueToUpdate;
      } 
    } else if (t == JsonToken.END_ARRAY || t == JsonToken.END_OBJECT) {
      result = valueToUpdate;
    } else {
      result = ctxt.readRootValue(p, this._valueType, _findRootDeserializer((DeserializationContext)ctxt), this._valueToUpdate);
    } 
    p.clearCurrentToken();
    if (this._config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS))
      _verifyNoTrailingTokens(p, (DeserializationContext)ctxt, this._valueType); 
    return result;
  }
  
  protected Object _bindAndClose(JsonParser p0) throws IOException {
    try (JsonParser p = p0) {
      Object result;
      DefaultDeserializationContext ctxt = createDeserializationContext(p);
      JsonToken t = _initForReading((DeserializationContext)ctxt, p);
      if (t == JsonToken.VALUE_NULL) {
        if (this._valueToUpdate == null) {
          result = _findRootDeserializer((DeserializationContext)ctxt).getNullValue((DeserializationContext)ctxt);
        } else {
          result = this._valueToUpdate;
        } 
      } else if (t == JsonToken.END_ARRAY || t == JsonToken.END_OBJECT) {
        result = this._valueToUpdate;
      } else {
        result = ctxt.readRootValue(p, this._valueType, _findRootDeserializer((DeserializationContext)ctxt), this._valueToUpdate);
      } 
      if (this._config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS))
        _verifyNoTrailingTokens(p, (DeserializationContext)ctxt, this._valueType); 
      return result;
    } 
  }
  
  protected final JsonNode _bindAndCloseAsTree(JsonParser p0) throws IOException {
    try (JsonParser p = p0) {
      return _bindAsTree(p);
    } 
  }
  
  protected final JsonNode _bindAsTree(JsonParser p) throws IOException {
    JsonNode resultNode;
    if (this._valueToUpdate != null)
      return (JsonNode)_bind(p, this._valueToUpdate); 
    this._config.initialize(p);
    if (this._schema != null)
      p.setSchema(this._schema); 
    JsonToken t = p.currentToken();
    if (t == null) {
      t = p.nextToken();
      if (t == null)
        return this._config.getNodeFactory().missingNode(); 
    } 
    DefaultDeserializationContext ctxt = createDeserializationContext(p);
    if (t == JsonToken.VALUE_NULL) {
      NullNode nullNode = this._config.getNodeFactory().nullNode();
    } else {
      resultNode = (JsonNode)ctxt.readRootValue(p, _jsonNodeType(), _findTreeDeserializer((DeserializationContext)ctxt), null);
    } 
    p.clearCurrentToken();
    if (this._config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS))
      _verifyNoTrailingTokens(p, (DeserializationContext)ctxt, _jsonNodeType()); 
    return resultNode;
  }
  
  protected final JsonNode _bindAsTreeOrNull(JsonParser p) throws IOException {
    JsonNode resultNode;
    if (this._valueToUpdate != null)
      return (JsonNode)_bind(p, this._valueToUpdate); 
    this._config.initialize(p);
    if (this._schema != null)
      p.setSchema(this._schema); 
    JsonToken t = p.currentToken();
    if (t == null) {
      t = p.nextToken();
      if (t == null)
        return null; 
    } 
    DefaultDeserializationContext ctxt = createDeserializationContext(p);
    if (t == JsonToken.VALUE_NULL) {
      NullNode nullNode = this._config.getNodeFactory().nullNode();
    } else {
      resultNode = (JsonNode)ctxt.readRootValue(p, _jsonNodeType(), _findTreeDeserializer((DeserializationContext)ctxt), null);
    } 
    p.clearCurrentToken();
    if (this._config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS))
      _verifyNoTrailingTokens(p, (DeserializationContext)ctxt, _jsonNodeType()); 
    return resultNode;
  }
  
  protected <T> MappingIterator<T> _bindAndReadValues(JsonParser p) throws IOException {
    DefaultDeserializationContext defaultDeserializationContext = createDeserializationContext(p);
    _initForMultiRead((DeserializationContext)defaultDeserializationContext, p);
    p.nextToken();
    return _newIterator(p, (DeserializationContext)defaultDeserializationContext, _findRootDeserializer((DeserializationContext)defaultDeserializationContext), true);
  }
  
  protected JsonParser _considerFilter(JsonParser p, boolean multiValue) {
    return (this._filter == null || FilteringParserDelegate.class.isInstance(p)) ? p : (JsonParser)new FilteringParserDelegate(p, this._filter, TokenFilter.Inclusion.ONLY_INCLUDE_ALL, multiValue);
  }
  
  protected final void _verifyNoTrailingTokens(JsonParser p, DeserializationContext ctxt, JavaType bindType) throws IOException {
    JsonToken t = p.nextToken();
    if (t != null) {
      Class<?> bt = ClassUtil.rawClass(bindType);
      if (bt == null && 
        this._valueToUpdate != null)
        bt = this._valueToUpdate.getClass(); 
      ctxt.reportTrailingTokens(bt, p, t);
    } 
  }
  
  protected Object _detectBindAndClose(byte[] src, int offset, int length) throws IOException {
    DataFormatReaders.Match match = this._dataFormatReaders.findFormat(src, offset, length);
    if (!match.hasMatch())
      _reportUnkownFormat(this._dataFormatReaders, match); 
    JsonParser p = match.createParserWithMatch();
    return match.getReader()._bindAndClose(p);
  }
  
  protected Object _detectBindAndClose(DataFormatReaders.Match match, boolean forceClosing) throws IOException {
    if (!match.hasMatch())
      _reportUnkownFormat(this._dataFormatReaders, match); 
    JsonParser p = match.createParserWithMatch();
    if (forceClosing)
      p.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE); 
    return match.getReader()._bindAndClose(p);
  }
  
  protected <T> MappingIterator<T> _detectBindAndReadValues(DataFormatReaders.Match match, boolean forceClosing) throws IOException {
    if (!match.hasMatch())
      _reportUnkownFormat(this._dataFormatReaders, match); 
    JsonParser p = match.createParserWithMatch();
    if (forceClosing)
      p.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE); 
    return match.getReader()._bindAndReadValues(p);
  }
  
  protected JsonNode _detectBindAndCloseAsTree(InputStream in) throws IOException {
    DataFormatReaders.Match match = this._dataFormatReaders.findFormat(in);
    if (!match.hasMatch())
      _reportUnkownFormat(this._dataFormatReaders, match); 
    JsonParser p = match.createParserWithMatch();
    p.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
    return match.getReader()._bindAndCloseAsTree(p);
  }
  
  protected void _reportUnkownFormat(DataFormatReaders detector, DataFormatReaders.Match match) throws IOException {
    throw new JsonParseException(null, "Cannot detect format from input, does not look like any of detectable formats " + detector
        .toString());
  }
  
  protected void _verifySchemaType(FormatSchema schema) {
    if (schema != null && 
      !this._parserFactory.canUseSchema(schema))
      throw new IllegalArgumentException("Cannot use FormatSchema of type " + schema.getClass().getName() + " for format " + this._parserFactory
          .getFormatName()); 
  }
  
  protected DefaultDeserializationContext createDeserializationContext(JsonParser p) {
    return this._context.createInstance(this._config, p, this._injectableValues);
  }
  
  protected DefaultDeserializationContext createDummyDeserializationContext() {
    return this._context.createDummyInstance(this._config);
  }
  
  protected InputStream _inputStream(URL src) throws IOException {
    return src.openStream();
  }
  
  protected InputStream _inputStream(File f) throws IOException {
    return new FileInputStream(f);
  }
  
  protected void _reportUndetectableSource(Object src) throws StreamReadException {
    throw new JsonParseException(null, "Cannot use source of type " + src
        .getClass().getName() + " with format auto-detection: must be byte- not char-based");
  }
  
  protected JsonDeserializer<Object> _findRootDeserializer(DeserializationContext ctxt) throws DatabindException {
    if (this._rootDeserializer != null)
      return this._rootDeserializer; 
    JavaType t = this._valueType;
    if (t == null)
      ctxt.reportBadDefinition((JavaType)null, "No value type configured for ObjectReader"); 
    JsonDeserializer<Object> deser = this._rootDeserializers.get(t);
    if (deser != null)
      return deser; 
    deser = ctxt.findRootValueDeserializer(t);
    if (deser == null)
      ctxt.reportBadDefinition(t, "Cannot find a deserializer for type " + t); 
    this._rootDeserializers.put(t, deser);
    return deser;
  }
  
  protected JsonDeserializer<Object> _findTreeDeserializer(DeserializationContext ctxt) throws DatabindException {
    JavaType nodeType = _jsonNodeType();
    JsonDeserializer<Object> deser = this._rootDeserializers.get(nodeType);
    if (deser == null) {
      deser = ctxt.findRootValueDeserializer(nodeType);
      if (deser == null)
        ctxt.reportBadDefinition(nodeType, "Cannot find a deserializer for type " + nodeType); 
      this._rootDeserializers.put(nodeType, deser);
    } 
    return deser;
  }
  
  protected JsonDeserializer<Object> _prefetchRootDeserializer(JavaType valueType) {
    if (valueType == null || !this._config.isEnabled(DeserializationFeature.EAGER_DESERIALIZER_FETCH))
      return null; 
    JsonDeserializer<Object> deser = this._rootDeserializers.get(valueType);
    if (deser == null)
      try {
        DefaultDeserializationContext defaultDeserializationContext = createDummyDeserializationContext();
        deser = defaultDeserializationContext.findRootValueDeserializer(valueType);
        if (deser != null)
          this._rootDeserializers.put(valueType, deser); 
        return deser;
      } catch (JacksonException jacksonException) {} 
    return deser;
  }
  
  protected final JavaType _jsonNodeType() {
    JavaType t = this._jsonNodeType;
    if (t == null) {
      t = getTypeFactory().constructType(JsonNode.class);
      this._jsonNodeType = t;
    } 
    return t;
  }
  
  protected final void _assertNotNull(String paramName, Object src) {
    if (src == null)
      throw new IllegalArgumentException(String.format("argument \"%s\" is null", new Object[] { paramName })); 
  }
}
