package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class JsonNode extends JsonSerializable.Base implements TreeNode, Iterable<JsonNode> {
  public enum OverwriteMode {
    NONE, NULLS, SCALARS, ALL;
  }
  
  public int size() {
    return 0;
  }
  
  public boolean isEmpty() {
    return (size() == 0);
  }
  
  public final boolean isValueNode() {
    switch (getNodeType()) {
      case ARRAY:
      case OBJECT:
      case MISSING:
        return false;
    } 
    return true;
  }
  
  public final boolean isContainerNode() {
    JsonNodeType type = getNodeType();
    return (type == JsonNodeType.OBJECT || type == JsonNodeType.ARRAY);
  }
  
  public boolean isMissingNode() {
    return false;
  }
  
  public boolean isArray() {
    return false;
  }
  
  public boolean isObject() {
    return false;
  }
  
  public JsonNode get(String fieldName) {
    return null;
  }
  
  public Iterator<String> fieldNames() {
    return ClassUtil.emptyIterator();
  }
  
  public final JsonNode at(JsonPointer ptr) {
    if (ptr.matches())
      return this; 
    JsonNode n = _at(ptr);
    if (n == null)
      return (JsonNode)MissingNode.getInstance(); 
    return n.at(ptr.tail());
  }
  
  public final JsonNode at(String jsonPtrExpr) {
    return at(JsonPointer.compile(jsonPtrExpr));
  }
  
  public final boolean isPojo() {
    return (getNodeType() == JsonNodeType.POJO);
  }
  
  public final boolean isNumber() {
    return (getNodeType() == JsonNodeType.NUMBER);
  }
  
  public boolean isIntegralNumber() {
    return false;
  }
  
  public boolean isFloatingPointNumber() {
    return false;
  }
  
  public boolean isShort() {
    return false;
  }
  
  public boolean isInt() {
    return false;
  }
  
  public boolean isLong() {
    return false;
  }
  
  public boolean isFloat() {
    return false;
  }
  
  public boolean isDouble() {
    return false;
  }
  
  public boolean isBigDecimal() {
    return false;
  }
  
  public boolean isBigInteger() {
    return false;
  }
  
  public final boolean isTextual() {
    return (getNodeType() == JsonNodeType.STRING);
  }
  
  public final boolean isBoolean() {
    return (getNodeType() == JsonNodeType.BOOLEAN);
  }
  
  public final boolean isNull() {
    return (getNodeType() == JsonNodeType.NULL);
  }
  
  public final boolean isBinary() {
    return (getNodeType() == JsonNodeType.BINARY);
  }
  
  public boolean canConvertToInt() {
    return false;
  }
  
  public boolean canConvertToLong() {
    return false;
  }
  
  public boolean canConvertToExactIntegral() {
    return isIntegralNumber();
  }
  
  public String textValue() {
    return null;
  }
  
  public byte[] binaryValue() throws IOException {
    return null;
  }
  
  public boolean booleanValue() {
    return false;
  }
  
  public Number numberValue() {
    return null;
  }
  
  public short shortValue() {
    return 0;
  }
  
  public int intValue() {
    return 0;
  }
  
  public long longValue() {
    return 0L;
  }
  
  public float floatValue() {
    return 0.0F;
  }
  
  public double doubleValue() {
    return 0.0D;
  }
  
  public BigDecimal decimalValue() {
    return BigDecimal.ZERO;
  }
  
  public BigInteger bigIntegerValue() {
    return BigInteger.ZERO;
  }
  
  public String asText(String defaultValue) {
    String str = asText();
    return (str == null) ? defaultValue : str;
  }
  
  public int asInt() {
    return asInt(0);
  }
  
  public int asInt(int defaultValue) {
    return defaultValue;
  }
  
  public long asLong() {
    return asLong(0L);
  }
  
  public long asLong(long defaultValue) {
    return defaultValue;
  }
  
  public double asDouble() {
    return asDouble(0.0D);
  }
  
  public double asDouble(double defaultValue) {
    return defaultValue;
  }
  
  public boolean asBoolean() {
    return asBoolean(false);
  }
  
  public boolean asBoolean(boolean defaultValue) {
    return defaultValue;
  }
  
  public <T extends JsonNode> T require() throws IllegalArgumentException {
    return _this();
  }
  
  public <T extends JsonNode> T requireNonNull() throws IllegalArgumentException {
    return _this();
  }
  
  public JsonNode required(String propertyName) throws IllegalArgumentException {
    return _reportRequiredViolation("Node of type `%s` has no fields", new Object[] { getClass().getName() });
  }
  
  public JsonNode required(int index) throws IllegalArgumentException {
    return _reportRequiredViolation("Node of type `%s` has no indexed values", new Object[] { getClass().getName() });
  }
  
  public JsonNode requiredAt(String pathExpr) throws IllegalArgumentException {
    return requiredAt(JsonPointer.compile(pathExpr));
  }
  
  public final JsonNode requiredAt(JsonPointer path) throws IllegalArgumentException {
    JsonPointer currentExpr = path;
    JsonNode curr = this;
    while (true) {
      if (currentExpr.matches())
        return curr; 
      curr = curr._at(currentExpr);
      if (curr == null)
        _reportRequiredViolation("No node at '%s' (unmatched part: '%s')", new Object[] { path, currentExpr }); 
      currentExpr = currentExpr.tail();
    } 
  }
  
  public boolean has(String fieldName) {
    return (get(fieldName) != null);
  }
  
  public boolean has(int index) {
    return (get(index) != null);
  }
  
  public boolean hasNonNull(String fieldName) {
    JsonNode n = get(fieldName);
    return (n != null && !n.isNull());
  }
  
  public boolean hasNonNull(int index) {
    JsonNode n = get(index);
    return (n != null && !n.isNull());
  }
  
  public final Iterator<JsonNode> iterator() {
    return elements();
  }
  
  public Iterator<JsonNode> elements() {
    return ClassUtil.emptyIterator();
  }
  
  public Iterator<Map.Entry<String, JsonNode>> fields() {
    return ClassUtil.emptyIterator();
  }
  
  public Set<Map.Entry<String, JsonNode>> properties() {
    return Collections.emptySet();
  }
  
  public final List<JsonNode> findValues(String fieldName) {
    List<JsonNode> result = findValues(fieldName, null);
    if (result == null)
      return Collections.emptyList(); 
    return result;
  }
  
  public final List<String> findValuesAsText(String fieldName) {
    List<String> result = findValuesAsText(fieldName, null);
    if (result == null)
      return Collections.emptyList(); 
    return result;
  }
  
  public final List<JsonNode> findParents(String fieldName) {
    List<JsonNode> result = findParents(fieldName, null);
    if (result == null)
      return Collections.emptyList(); 
    return result;
  }
  
  public ObjectNode withObject(String exprOrProperty) {
    throw new UnsupportedOperationException("`withObject(String)` not implemented by `" + 
        getClass().getName() + "`");
  }
  
  public final ObjectNode withObject(String expr, OverwriteMode overwriteMode, boolean preferIndex) {
    return withObject(JsonPointer.compile(expr), overwriteMode, preferIndex);
  }
  
  public final ObjectNode withObject(JsonPointer ptr) {
    return withObject(ptr, OverwriteMode.NULLS, true);
  }
  
  public ObjectNode withObject(JsonPointer ptr, OverwriteMode overwriteMode, boolean preferIndex) {
    throw new UnsupportedOperationException("`withObject(JsonPointer)` not implemented by `" + 
        getClass().getName() + "`");
  }
  
  public ObjectNode withObjectProperty(String propName) {
    throw new UnsupportedOperationException("`JsonNode` not of type `ObjectNode` (but `" + 
        getClass().getName() + ")`, cannot call `withObjectProperty(String)` on it");
  }
  
  @Deprecated
  public <T extends JsonNode> T with(String exprOrProperty) {
    throw new UnsupportedOperationException("`JsonNode` not of type `ObjectNode` (but " + 
        getClass().getName() + "), cannot call `with(String)` on it");
  }
  
  public <T extends JsonNode> T withArray(String exprOrProperty) {
    throw new UnsupportedOperationException("`JsonNode` not of type `ObjectNode` (but `" + 
        getClass().getName() + ")`, cannot call `withArray()` on it");
  }
  
  public ArrayNode withArray(String expr, OverwriteMode overwriteMode, boolean preferIndex) {
    return withArray(JsonPointer.compile(expr), overwriteMode, preferIndex);
  }
  
  public final ArrayNode withArray(JsonPointer ptr) {
    return withArray(ptr, OverwriteMode.NULLS, true);
  }
  
  public ArrayNode withArray(JsonPointer ptr, OverwriteMode overwriteMode, boolean preferIndex) {
    throw new UnsupportedOperationException("`withArray(JsonPointer)` not implemented by " + 
        getClass().getName());
  }
  
  public ArrayNode withArrayProperty(String propName) {
    throw new UnsupportedOperationException("`JsonNode` not of type `ObjectNode` (but `" + 
        getClass().getName() + ")`, cannot call `withArrayProperty(String)` on it");
  }
  
  public boolean equals(Comparator<JsonNode> comparator, JsonNode other) {
    return (comparator.compare(this, other) == 0);
  }
  
  public String toPrettyString() {
    return toString();
  }
  
  protected <T extends JsonNode> T _this() {
    return (T)this;
  }
  
  protected <T> T _reportRequiredViolation(String msgTemplate, Object... args) {
    throw new IllegalArgumentException(String.format(msgTemplate, args));
  }
  
  public abstract <T extends JsonNode> T deepCopy();
  
  public abstract JsonNode get(int paramInt);
  
  public abstract JsonNode path(String paramString);
  
  public abstract JsonNode path(int paramInt);
  
  protected abstract JsonNode _at(JsonPointer paramJsonPointer);
  
  public abstract JsonNodeType getNodeType();
  
  public abstract String asText();
  
  public abstract JsonNode findValue(String paramString);
  
  public abstract JsonNode findPath(String paramString);
  
  public abstract JsonNode findParent(String paramString);
  
  public abstract List<JsonNode> findValues(String paramString, List<JsonNode> paramList);
  
  public abstract List<String> findValuesAsText(String paramString, List<String> paramList);
  
  public abstract List<JsonNode> findParents(String paramString, List<JsonNode> paramList);
  
  public abstract String toString();
  
  public abstract boolean equals(Object paramObject);
}
