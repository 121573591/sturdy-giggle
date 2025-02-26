package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.core.exc.StreamConstraintsException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.ExceptionUtil;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class BaseJsonNode extends JsonNode implements Serializable {
  private static final long serialVersionUID = 1L;
  
  Object writeReplace() {
    return NodeSerialization.from(this);
  }
  
  public final JsonNode findPath(String fieldName) {
    JsonNode value = findValue(fieldName);
    if (value == null)
      return MissingNode.getInstance(); 
    return value;
  }
  
  public abstract int hashCode();
  
  public JsonNode required(String fieldName) {
    return (JsonNode)_reportRequiredViolation("Node of type `%s` has no fields", new Object[] { getClass().getSimpleName() });
  }
  
  public JsonNode required(int index) {
    return (JsonNode)_reportRequiredViolation("Node of type `%s` has no indexed values", new Object[] { getClass().getSimpleName() });
  }
  
  public JsonParser traverse() {
    return (JsonParser)new TreeTraversingParser(this);
  }
  
  public JsonParser traverse(ObjectCodec codec) {
    return (JsonParser)new TreeTraversingParser(this, codec);
  }
  
  public abstract JsonToken asToken();
  
  public JsonParser.NumberType numberType() {
    return null;
  }
  
  public ObjectNode withObject(JsonPointer ptr, JsonNode.OverwriteMode overwriteMode, boolean preferIndex) {
    if (ptr.matches()) {
      if (this instanceof ObjectNode)
        return (ObjectNode)this; 
      _reportWrongNodeType("Can only call `withObject()` with empty JSON Pointer on `ObjectNode`, not `%s`", new Object[] { getClass().getName() });
    } 
    ObjectNode n = _withObject(ptr, ptr, overwriteMode, preferIndex);
    if (n == null)
      _reportWrongNodeType("Cannot replace context node (of type `%s`) using `withObject()` with  JSON Pointer '%s'", new Object[] { getClass().getName(), ptr }); 
    return n;
  }
  
  protected ObjectNode _withObject(JsonPointer origPtr, JsonPointer currentPtr, JsonNode.OverwriteMode overwriteMode, boolean preferIndex) {
    return null;
  }
  
  protected void _withXxxVerifyReplace(JsonPointer origPtr, JsonPointer currentPtr, JsonNode.OverwriteMode overwriteMode, boolean preferIndex, JsonNode toReplace) {
    if (!_withXxxMayReplace(toReplace, overwriteMode))
      _reportWrongNodeType("Cannot replace `JsonNode` of type `%s` for property \"%s\" in JSON Pointer \"%s\" (mode `OverwriteMode.%s`)", new Object[] { toReplace
            
            .getClass().getName(), currentPtr.getMatchingProperty(), origPtr, overwriteMode }); 
  }
  
  protected boolean _withXxxMayReplace(JsonNode node, JsonNode.OverwriteMode overwriteMode) {
    switch (overwriteMode) {
      case NONE:
        return false;
      case NULLS:
        return node.isNull();
      case SCALARS:
        return !node.isContainerNode();
    } 
    return true;
  }
  
  public ArrayNode withArray(JsonPointer ptr, JsonNode.OverwriteMode overwriteMode, boolean preferIndex) {
    if (ptr.matches()) {
      if (this instanceof ArrayNode)
        return (ArrayNode)this; 
      _reportWrongNodeType("Can only call `withArray()` with empty JSON Pointer on `ArrayNode`, not `%s`", new Object[] { getClass().getName() });
    } 
    ArrayNode n = _withArray(ptr, ptr, overwriteMode, preferIndex);
    if (n == null)
      _reportWrongNodeType("Cannot replace context node (of type `%s`) using `withArray()` with  JSON Pointer '%s'", new Object[] { getClass().getName(), ptr }); 
    return n;
  }
  
  protected ArrayNode _withArray(JsonPointer origPtr, JsonPointer currentPtr, JsonNode.OverwriteMode overwriteMode, boolean preferIndex) {
    return null;
  }
  
  public abstract void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) throws IOException;
  
  public abstract void serializeWithType(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) throws IOException;
  
  public String toString() {
    return InternalNodeMapper.nodeToString(this);
  }
  
  public String toPrettyString() {
    return InternalNodeMapper.nodeToPrettyString(this);
  }
  
  protected <T> T _reportWrongNodeType(String msgTemplate, Object... args) {
    throw new UnsupportedOperationException(String.format(msgTemplate, args));
  }
  
  protected <T> T _reportWrongNodeOperation(String msgTemplate, Object... args) {
    throw new UnsupportedOperationException(String.format(msgTemplate, args));
  }
  
  protected JsonPointer _jsonPointerIfValid(String exprOrProperty) {
    if (exprOrProperty.isEmpty() || exprOrProperty.charAt(0) == '/')
      return JsonPointer.compile(exprOrProperty); 
    return null;
  }
  
  protected BigInteger _bigIntFromBigDec(BigDecimal value) {
    try {
      StreamReadConstraints.defaults().validateBigIntegerScale(value.scale());
    } catch (StreamConstraintsException e) {
      ExceptionUtil.throwSneaky((IOException)e);
    } 
    return value.toBigInteger();
  }
}
