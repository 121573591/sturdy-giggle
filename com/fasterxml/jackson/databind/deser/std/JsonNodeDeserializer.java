package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.LogicalType;
import java.io.IOException;

public class JsonNodeDeserializer extends BaseNodeDeserializer<JsonNode> {
  private static final JsonNodeDeserializer instance = new JsonNodeDeserializer();
  
  protected JsonNodeDeserializer() {
    super(JsonNode.class, null);
  }
  
  protected JsonNodeDeserializer(JsonNodeDeserializer base, boolean mergeArrays, boolean mergeObjects) {
    super(base, mergeArrays, mergeObjects);
  }
  
  protected JsonDeserializer<?> _createWithMerge(boolean mergeArrays, boolean mergeObjects) {
    return new JsonNodeDeserializer(this, mergeArrays, mergeObjects);
  }
  
  public static JsonDeserializer<? extends JsonNode> getDeserializer(Class<?> nodeClass) {
    if (nodeClass == ObjectNode.class)
      return ObjectDeserializer.getInstance(); 
    if (nodeClass == ArrayNode.class)
      return ArrayDeserializer.getInstance(); 
    return instance;
  }
  
  public JsonNode getNullValue(DeserializationContext ctxt) {
    return (JsonNode)ctxt.getNodeFactory().nullNode();
  }
  
  public Object getAbsentValue(DeserializationContext ctxt) {
    return null;
  }
  
  public JsonNode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    BaseNodeDeserializer.ContainerStack stack = new BaseNodeDeserializer.ContainerStack();
    JsonNodeFactory nodeF = ctxt.getNodeFactory();
    switch (p.currentTokenId()) {
      case 1:
        return (JsonNode)_deserializeContainerNoRecursion(p, ctxt, nodeF, stack, (ContainerNode<?>)nodeF.objectNode());
      case 2:
        return (JsonNode)nodeF.objectNode();
      case 3:
        return (JsonNode)_deserializeContainerNoRecursion(p, ctxt, nodeF, stack, (ContainerNode<?>)nodeF.arrayNode());
      case 5:
        return (JsonNode)_deserializeObjectAtName(p, ctxt, nodeF, stack);
    } 
    return _deserializeAnyScalar(p, ctxt);
  }
  
  public Boolean supportsUpdate(DeserializationConfig config) {
    return this._supportsUpdates;
  }
  
  static final class ObjectDeserializer extends BaseNodeDeserializer<ObjectNode> {
    private static final long serialVersionUID = 1L;
    
    protected static final ObjectDeserializer _instance = new ObjectDeserializer();
    
    protected ObjectDeserializer() {
      super(ObjectNode.class, Boolean.valueOf(true));
    }
    
    public static ObjectDeserializer getInstance() {
      return _instance;
    }
    
    protected ObjectDeserializer(ObjectDeserializer base, boolean mergeArrays, boolean mergeObjects) {
      super(base, mergeArrays, mergeObjects);
    }
    
    protected JsonDeserializer<?> _createWithMerge(boolean mergeArrays, boolean mergeObjects) {
      return new ObjectDeserializer(this, mergeArrays, mergeObjects);
    }
    
    public ObjectNode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNodeFactory nodeF = ctxt.getNodeFactory();
      if (p.isExpectedStartObjectToken()) {
        ObjectNode root = nodeF.objectNode();
        _deserializeContainerNoRecursion(p, ctxt, nodeF, new BaseNodeDeserializer.ContainerStack(), (ContainerNode<?>)root);
        return root;
      } 
      if (p.hasToken(JsonToken.FIELD_NAME))
        return _deserializeObjectAtName(p, ctxt, nodeF, new BaseNodeDeserializer.ContainerStack()); 
      if (p.hasToken(JsonToken.END_OBJECT))
        return nodeF.objectNode(); 
      return (ObjectNode)ctxt.handleUnexpectedToken(ObjectNode.class, p);
    }
    
    public ObjectNode deserialize(JsonParser p, DeserializationContext ctxt, ObjectNode node) throws IOException {
      if (p.isExpectedStartObjectToken() || p.hasToken(JsonToken.FIELD_NAME))
        return (ObjectNode)updateObject(p, ctxt, node, new BaseNodeDeserializer.ContainerStack()); 
      return (ObjectNode)ctxt.handleUnexpectedToken(ObjectNode.class, p);
    }
  }
  
  static final class ArrayDeserializer extends BaseNodeDeserializer<ArrayNode> {
    private static final long serialVersionUID = 1L;
    
    protected static final ArrayDeserializer _instance = new ArrayDeserializer();
    
    protected ArrayDeserializer() {
      super(ArrayNode.class, Boolean.valueOf(true));
    }
    
    public static ArrayDeserializer getInstance() {
      return _instance;
    }
    
    protected ArrayDeserializer(ArrayDeserializer base, boolean mergeArrays, boolean mergeObjects) {
      super(base, mergeArrays, mergeObjects);
    }
    
    protected JsonDeserializer<?> _createWithMerge(boolean mergeArrays, boolean mergeObjects) {
      return new ArrayDeserializer(this, mergeArrays, mergeObjects);
    }
    
    public ArrayNode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      if (p.isExpectedStartArrayToken()) {
        JsonNodeFactory nodeF = ctxt.getNodeFactory();
        ArrayNode arrayNode = nodeF.arrayNode();
        _deserializeContainerNoRecursion(p, ctxt, nodeF, new BaseNodeDeserializer.ContainerStack(), (ContainerNode<?>)arrayNode);
        return arrayNode;
      } 
      return (ArrayNode)ctxt.handleUnexpectedToken(ArrayNode.class, p);
    }
    
    public ArrayNode deserialize(JsonParser p, DeserializationContext ctxt, ArrayNode arrayNode) throws IOException {
      if (p.isExpectedStartArrayToken()) {
        _deserializeContainerNoRecursion(p, ctxt, ctxt.getNodeFactory(), new BaseNodeDeserializer.ContainerStack(), (ContainerNode<?>)arrayNode);
        return arrayNode;
      } 
      return (ArrayNode)ctxt.handleUnexpectedToken(ArrayNode.class, p);
    }
  }
}
