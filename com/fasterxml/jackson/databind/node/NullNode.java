package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class NullNode extends ValueNode {
  private static final long serialVersionUID = 1L;
  
  public static final NullNode instance = new NullNode();
  
  protected Object readResolve() {
    return instance;
  }
  
  public static NullNode getInstance() {
    return instance;
  }
  
  public JsonNodeType getNodeType() {
    return JsonNodeType.NULL;
  }
  
  public JsonToken asToken() {
    return JsonToken.VALUE_NULL;
  }
  
  public String asText(String defaultValue) {
    return defaultValue;
  }
  
  public String asText() {
    return "null";
  }
  
  public JsonNode requireNonNull() {
    return (JsonNode)_reportRequiredViolation("requireNonNull() called on `NullNode`", new Object[0]);
  }
  
  public final void serialize(JsonGenerator g, SerializerProvider provider) throws IOException {
    provider.defaultSerializeNull(g);
  }
  
  public boolean equals(Object o) {
    return (o == this || o instanceof NullNode);
  }
  
  public int hashCode() {
    return JsonNodeType.NULL.ordinal();
  }
}
