package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.List;

public abstract class ValueNode extends BaseJsonNode {
  private static final long serialVersionUID = 1L;
  
  protected JsonNode _at(JsonPointer ptr) {
    return null;
  }
  
  public <T extends JsonNode> T deepCopy() {
    return (T)this;
  }
  
  public void serializeWithType(JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
    WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer
        .typeId(this, asToken()));
    serialize(g, provider);
    typeSer.writeTypeSuffix(g, typeIdDef);
  }
  
  public boolean isEmpty() {
    return true;
  }
  
  public final JsonNode get(int index) {
    return null;
  }
  
  public final JsonNode path(int index) {
    return MissingNode.getInstance();
  }
  
  public final boolean has(int index) {
    return false;
  }
  
  public final boolean hasNonNull(int index) {
    return false;
  }
  
  public final JsonNode get(String fieldName) {
    return null;
  }
  
  public final JsonNode path(String fieldName) {
    return MissingNode.getInstance();
  }
  
  public final boolean has(String fieldName) {
    return false;
  }
  
  public final boolean hasNonNull(String fieldName) {
    return false;
  }
  
  public final JsonNode findValue(String fieldName) {
    return null;
  }
  
  public final ObjectNode findParent(String fieldName) {
    return null;
  }
  
  public final List<JsonNode> findValues(String fieldName, List<JsonNode> foundSoFar) {
    return foundSoFar;
  }
  
  public final List<String> findValuesAsText(String fieldName, List<String> foundSoFar) {
    return foundSoFar;
  }
  
  public final List<JsonNode> findParents(String fieldName, List<JsonNode> foundSoFar) {
    return foundSoFar;
  }
  
  public abstract JsonToken asToken();
}
