package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.DatatypeFeature;
import com.fasterxml.jackson.databind.cfg.JsonNodeFeature;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

public class ObjectNode extends ContainerNode<ObjectNode> implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected final Map<String, JsonNode> _children;
  
  public ObjectNode(JsonNodeFactory nc) {
    super(nc);
    this._children = new LinkedHashMap<>();
  }
  
  public ObjectNode(JsonNodeFactory nc, Map<String, JsonNode> children) {
    super(nc);
    this._children = Objects.<Map<String, JsonNode>>requireNonNull(children, "Must not pass `null` for 'children' argument");
  }
  
  protected JsonNode _at(JsonPointer ptr) {
    return get(ptr.getMatchingProperty());
  }
  
  public ObjectNode deepCopy() {
    ObjectNode ret = new ObjectNode(this._nodeFactory);
    for (Map.Entry<String, JsonNode> entry : this._children.entrySet())
      ret._children.put(entry.getKey(), ((JsonNode)entry.getValue()).deepCopy()); 
    return ret;
  }
  
  @Deprecated
  public ObjectNode with(String exprOrProperty) {
    JsonPointer ptr = _jsonPointerIfValid(exprOrProperty);
    if (ptr != null)
      return withObject(ptr); 
    JsonNode n = this._children.get(exprOrProperty);
    if (n != null) {
      if (n instanceof ObjectNode)
        return (ObjectNode)n; 
      throw new UnsupportedOperationException("Property '" + exprOrProperty + "' has value that is not of type `ObjectNode` (but `" + n
          
          .getClass().getName() + "`)");
    } 
    ObjectNode result = objectNode();
    this._children.put(exprOrProperty, result);
    return result;
  }
  
  public ObjectNode withObject(String exprOrProperty) {
    JsonPointer ptr = _jsonPointerIfValid(exprOrProperty);
    if (ptr != null)
      return withObject(ptr); 
    return withObjectProperty(exprOrProperty);
  }
  
  public ObjectNode withObjectProperty(String propName) {
    JsonNode child = this._children.get(propName);
    if (child == null || child.isNull())
      return putObject(propName); 
    if (child.isObject())
      return (ObjectNode)child; 
    return _reportWrongNodeType("Cannot replace `JsonNode` of type `%s` with `ObjectNode` for property \"%s\" (default mode `OverwriteMode.%s`)", new Object[] { child
          
          .getClass().getName(), propName, JsonNode.OverwriteMode.NULLS });
  }
  
  public ArrayNode withArray(String exprOrProperty) {
    JsonPointer ptr = _jsonPointerIfValid(exprOrProperty);
    if (ptr != null)
      return withArray(ptr); 
    JsonNode n = this._children.get(exprOrProperty);
    if (n != null) {
      if (n instanceof ArrayNode)
        return (ArrayNode)n; 
      throw new UnsupportedOperationException("Property '" + exprOrProperty + "' has value that is not of type `ArrayNode` (but `" + n
          
          .getClass().getName() + "`)");
    } 
    ArrayNode result = arrayNode();
    this._children.put(exprOrProperty, result);
    return result;
  }
  
  public ArrayNode withArrayProperty(String propName) {
    JsonNode child = this._children.get(propName);
    if (child == null || child.isNull())
      return putArray(propName); 
    if (child.isArray())
      return (ArrayNode)child; 
    return (ArrayNode)_reportWrongNodeType("Cannot replace `JsonNode` of type `%s` with `ArrayNode` for property \"%s\" with (default mode `OverwriteMode.%s`)", new Object[] { child
          
          .getClass().getName(), propName, JsonNode.OverwriteMode.NULLS });
  }
  
  protected ObjectNode _withObject(JsonPointer origPtr, JsonPointer currentPtr, JsonNode.OverwriteMode overwriteMode, boolean preferIndex) {
    if (currentPtr.matches())
      return this; 
    JsonNode n = _at(currentPtr);
    if (n != null && n instanceof BaseJsonNode) {
      ObjectNode found = ((BaseJsonNode)n)._withObject(origPtr, currentPtr.tail(), overwriteMode, preferIndex);
      if (found != null)
        return found; 
      _withXxxVerifyReplace(origPtr, currentPtr, overwriteMode, preferIndex, n);
    } 
    return _withObjectAddTailProperty(currentPtr, preferIndex);
  }
  
  protected ArrayNode _withArray(JsonPointer origPtr, JsonPointer currentPtr, JsonNode.OverwriteMode overwriteMode, boolean preferIndex) {
    if (currentPtr.matches())
      return null; 
    JsonNode n = _at(currentPtr);
    if (n != null && n instanceof BaseJsonNode) {
      ArrayNode found = ((BaseJsonNode)n)._withArray(origPtr, currentPtr.tail(), overwriteMode, preferIndex);
      if (found != null)
        return found; 
      _withXxxVerifyReplace(origPtr, currentPtr, overwriteMode, preferIndex, n);
    } 
    return _withArrayAddTailProperty(currentPtr, preferIndex);
  }
  
  protected ObjectNode _withObjectAddTailProperty(JsonPointer tail, boolean preferIndex) {
    String propName = tail.getMatchingProperty();
    tail = tail.tail();
    if (tail.matches())
      return putObject(propName); 
    if (preferIndex && tail.mayMatchElement())
      return putArray(propName)._withObjectAddTailElement(tail, preferIndex); 
    return putObject(propName)._withObjectAddTailProperty(tail, preferIndex);
  }
  
  protected ArrayNode _withArrayAddTailProperty(JsonPointer tail, boolean preferIndex) {
    String propName = tail.getMatchingProperty();
    tail = tail.tail();
    if (tail.matches())
      return putArray(propName); 
    if (preferIndex && tail.mayMatchElement())
      return putArray(propName)._withArrayAddTailElement(tail, preferIndex); 
    return putObject(propName)._withArrayAddTailProperty(tail, preferIndex);
  }
  
  public boolean isEmpty(SerializerProvider serializers) {
    return this._children.isEmpty();
  }
  
  public JsonNodeType getNodeType() {
    return JsonNodeType.OBJECT;
  }
  
  public final boolean isObject() {
    return true;
  }
  
  public JsonToken asToken() {
    return JsonToken.START_OBJECT;
  }
  
  public int size() {
    return this._children.size();
  }
  
  public boolean isEmpty() {
    return this._children.isEmpty();
  }
  
  public Iterator<JsonNode> elements() {
    return this._children.values().iterator();
  }
  
  public JsonNode get(int index) {
    return null;
  }
  
  public JsonNode get(String propertyName) {
    return this._children.get(propertyName);
  }
  
  public Iterator<String> fieldNames() {
    return this._children.keySet().iterator();
  }
  
  public JsonNode path(int index) {
    return MissingNode.getInstance();
  }
  
  public JsonNode path(String propertyName) {
    JsonNode n = this._children.get(propertyName);
    if (n != null)
      return n; 
    return MissingNode.getInstance();
  }
  
  public JsonNode required(String propertyName) {
    JsonNode n = this._children.get(propertyName);
    if (n != null)
      return n; 
    return (JsonNode)_reportRequiredViolation("No value for property '%s' of `ObjectNode`", new Object[] { propertyName });
  }
  
  public Iterator<Map.Entry<String, JsonNode>> fields() {
    return this._children.entrySet().iterator();
  }
  
  public Set<Map.Entry<String, JsonNode>> properties() {
    return this._children.entrySet();
  }
  
  public boolean equals(Comparator<JsonNode> comparator, JsonNode o) {
    if (!(o instanceof ObjectNode))
      return false; 
    ObjectNode other = (ObjectNode)o;
    Map<String, JsonNode> m1 = this._children;
    Map<String, JsonNode> m2 = other._children;
    int len = m1.size();
    if (m2.size() != len)
      return false; 
    for (Map.Entry<String, JsonNode> entry : m1.entrySet()) {
      JsonNode v2 = m2.get(entry.getKey());
      if (v2 == null || !((JsonNode)entry.getValue()).equals(comparator, v2))
        return false; 
    } 
    return true;
  }
  
  public JsonNode findValue(String propertyName) {
    JsonNode jsonNode = this._children.get(propertyName);
    if (jsonNode != null)
      return jsonNode; 
    for (JsonNode child : this._children.values()) {
      JsonNode value = child.findValue(propertyName);
      if (value != null)
        return value; 
    } 
    return null;
  }
  
  public List<JsonNode> findValues(String propertyName, List<JsonNode> foundSoFar) {
    for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
      if (propertyName.equals(entry.getKey())) {
        if (foundSoFar == null)
          foundSoFar = new ArrayList<>(); 
        foundSoFar.add(entry.getValue());
        continue;
      } 
      foundSoFar = ((JsonNode)entry.getValue()).findValues(propertyName, foundSoFar);
    } 
    return foundSoFar;
  }
  
  public List<String> findValuesAsText(String propertyName, List<String> foundSoFar) {
    for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
      if (propertyName.equals(entry.getKey())) {
        if (foundSoFar == null)
          foundSoFar = new ArrayList<>(); 
        foundSoFar.add(((JsonNode)entry.getValue()).asText());
        continue;
      } 
      foundSoFar = ((JsonNode)entry.getValue()).findValuesAsText(propertyName, foundSoFar);
    } 
    return foundSoFar;
  }
  
  public ObjectNode findParent(String propertyName) {
    JsonNode jsonNode = this._children.get(propertyName);
    if (jsonNode != null)
      return this; 
    for (JsonNode child : this._children.values()) {
      JsonNode value = child.findParent(propertyName);
      if (value != null)
        return (ObjectNode)value; 
    } 
    return null;
  }
  
  public List<JsonNode> findParents(String propertyName, List<JsonNode> foundSoFar) {
    for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
      if (propertyName.equals(entry.getKey())) {
        if (foundSoFar == null)
          foundSoFar = new ArrayList<>(); 
        foundSoFar.add(this);
        continue;
      } 
      foundSoFar = ((JsonNode)entry.getValue()).findParents(propertyName, foundSoFar);
    } 
    return foundSoFar;
  }
  
  public void serialize(JsonGenerator g, SerializerProvider provider) throws IOException {
    if (provider != null) {
      boolean trimEmptyArray = !provider.isEnabled(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
      boolean skipNulls = !provider.isEnabled((DatatypeFeature)JsonNodeFeature.WRITE_NULL_PROPERTIES);
      if (trimEmptyArray || skipNulls) {
        g.writeStartObject(this);
        serializeFilteredContents(g, provider, trimEmptyArray, skipNulls);
        g.writeEndObject();
        return;
      } 
    } 
    g.writeStartObject(this);
    for (Map.Entry<String, JsonNode> en : _contentsToSerialize(provider).entrySet()) {
      JsonNode value = en.getValue();
      g.writeFieldName(en.getKey());
      value.serialize(g, provider);
    } 
    g.writeEndObject();
  }
  
  public void serializeWithType(JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
    boolean trimEmptyArray = false;
    boolean skipNulls = false;
    if (provider != null) {
      trimEmptyArray = !provider.isEnabled(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
      skipNulls = !provider.isEnabled((DatatypeFeature)JsonNodeFeature.WRITE_NULL_PROPERTIES);
    } 
    WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer
        .typeId(this, JsonToken.START_OBJECT));
    if (trimEmptyArray || skipNulls) {
      serializeFilteredContents(g, provider, trimEmptyArray, skipNulls);
    } else {
      for (Map.Entry<String, JsonNode> en : _contentsToSerialize(provider).entrySet()) {
        JsonNode value = en.getValue();
        g.writeFieldName(en.getKey());
        value.serialize(g, provider);
      } 
    } 
    typeSer.writeTypeSuffix(g, typeIdDef);
  }
  
  protected void serializeFilteredContents(JsonGenerator g, SerializerProvider ctxt, boolean trimEmptyArray, boolean skipNulls) throws IOException {
    for (Map.Entry<String, JsonNode> en : _contentsToSerialize(ctxt).entrySet()) {
      JsonNode value = en.getValue();
      if (trimEmptyArray && value.isArray() && value.isEmpty(ctxt))
        continue; 
      if (skipNulls && value.isNull())
        continue; 
      g.writeFieldName(en.getKey());
      value.serialize(g, ctxt);
    } 
  }
  
  protected Map<String, JsonNode> _contentsToSerialize(SerializerProvider ctxt) {
    if (ctxt.isEnabled((DatatypeFeature)JsonNodeFeature.WRITE_PROPERTIES_SORTED) && 
      !this._children.isEmpty())
      return new TreeMap<>(this._children); 
    return this._children;
  }
  
  public <T extends JsonNode> T set(String propertyName, JsonNode value) {
    if (value == null)
      value = nullNode(); 
    this._children.put(propertyName, value);
    return (T)this;
  }
  
  public <T extends JsonNode> T setAll(Map<String, ? extends JsonNode> properties) {
    for (Map.Entry<String, ? extends JsonNode> en : properties.entrySet()) {
      JsonNode n = en.getValue();
      if (n == null)
        n = nullNode(); 
      this._children.put(en.getKey(), n);
    } 
    return (T)this;
  }
  
  public <T extends JsonNode> T setAll(ObjectNode other) {
    this._children.putAll(other._children);
    return (T)this;
  }
  
  public JsonNode replace(String propertyName, JsonNode value) {
    if (value == null)
      value = nullNode(); 
    return this._children.put(propertyName, value);
  }
  
  public <T extends JsonNode> T without(String propertyName) {
    this._children.remove(propertyName);
    return (T)this;
  }
  
  public <T extends JsonNode> T without(Collection<String> propertyNames) {
    this._children.keySet().removeAll(propertyNames);
    return (T)this;
  }
  
  @Deprecated
  public JsonNode put(String propertyName, JsonNode value) {
    if (value == null)
      value = nullNode(); 
    return this._children.put(propertyName, value);
  }
  
  public JsonNode putIfAbsent(String propertyName, JsonNode value) {
    if (value == null)
      value = nullNode(); 
    return this._children.putIfAbsent(propertyName, value);
  }
  
  public JsonNode remove(String propertyName) {
    return this._children.remove(propertyName);
  }
  
  public ObjectNode remove(Collection<String> propertyNames) {
    this._children.keySet().removeAll(propertyNames);
    return this;
  }
  
  public ObjectNode removeAll() {
    this._children.clear();
    return this;
  }
  
  @Deprecated
  public JsonNode putAll(Map<String, ? extends JsonNode> properties) {
    return setAll(properties);
  }
  
  @Deprecated
  public JsonNode putAll(ObjectNode other) {
    return setAll(other);
  }
  
  public ObjectNode retain(Collection<String> propertyNames) {
    this._children.keySet().retainAll(propertyNames);
    return this;
  }
  
  public ObjectNode retain(String... propertyNames) {
    return retain(Arrays.asList(propertyNames));
  }
  
  public ArrayNode putArray(String propertyName) {
    ArrayNode n = arrayNode();
    _put(propertyName, n);
    return n;
  }
  
  public ObjectNode putObject(String propertyName) {
    ObjectNode n = objectNode();
    _put(propertyName, n);
    return n;
  }
  
  public ObjectNode putPOJO(String propertyName, Object pojo) {
    return _put(propertyName, pojoNode(pojo));
  }
  
  public ObjectNode putRawValue(String propertyName, RawValue raw) {
    return _put(propertyName, rawValueNode(raw));
  }
  
  public ObjectNode putNull(String propertyName) {
    this._children.put(propertyName, nullNode());
    return this;
  }
  
  public ObjectNode put(String propertyName, short v) {
    return _put(propertyName, numberNode(v));
  }
  
  public ObjectNode put(String fieldName, Short v) {
    return _put(fieldName, (v == null) ? nullNode() : 
        numberNode(v.shortValue()));
  }
  
  public ObjectNode put(String fieldName, int v) {
    return _put(fieldName, numberNode(v));
  }
  
  public ObjectNode put(String fieldName, Integer v) {
    return _put(fieldName, (v == null) ? nullNode() : 
        numberNode(v.intValue()));
  }
  
  public ObjectNode put(String fieldName, long v) {
    return _put(fieldName, numberNode(v));
  }
  
  public ObjectNode put(String fieldName, Long v) {
    return _put(fieldName, (v == null) ? nullNode() : 
        numberNode(v.longValue()));
  }
  
  public ObjectNode put(String fieldName, float v) {
    return _put(fieldName, numberNode(v));
  }
  
  public ObjectNode put(String fieldName, Float v) {
    return _put(fieldName, (v == null) ? nullNode() : 
        numberNode(v.floatValue()));
  }
  
  public ObjectNode put(String fieldName, double v) {
    return _put(fieldName, numberNode(v));
  }
  
  public ObjectNode put(String fieldName, Double v) {
    return _put(fieldName, (v == null) ? nullNode() : 
        numberNode(v.doubleValue()));
  }
  
  public ObjectNode put(String fieldName, BigDecimal v) {
    return _put(fieldName, (v == null) ? nullNode() : 
        numberNode(v));
  }
  
  public ObjectNode put(String fieldName, BigInteger v) {
    return _put(fieldName, (v == null) ? nullNode() : 
        numberNode(v));
  }
  
  public ObjectNode put(String fieldName, String v) {
    return _put(fieldName, (v == null) ? nullNode() : 
        textNode(v));
  }
  
  public ObjectNode put(String fieldName, boolean v) {
    return _put(fieldName, booleanNode(v));
  }
  
  public ObjectNode put(String fieldName, Boolean v) {
    return _put(fieldName, (v == null) ? nullNode() : 
        booleanNode(v.booleanValue()));
  }
  
  public ObjectNode put(String fieldName, byte[] v) {
    return _put(fieldName, (v == null) ? nullNode() : 
        binaryNode(v));
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (o == null)
      return false; 
    if (o instanceof ObjectNode)
      return _childrenEqual((ObjectNode)o); 
    return false;
  }
  
  protected boolean _childrenEqual(ObjectNode other) {
    return this._children.equals(other._children);
  }
  
  public int hashCode() {
    return this._children.hashCode();
  }
  
  protected ObjectNode _put(String fieldName, JsonNode value) {
    this._children.put(fieldName, value);
    return this;
  }
}
