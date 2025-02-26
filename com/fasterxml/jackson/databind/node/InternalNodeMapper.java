package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

final class InternalNodeMapper {
  private static final JsonMapper JSON_MAPPER = new JsonMapper();
  
  private static final ObjectWriter STD_WRITER = JSON_MAPPER.writer();
  
  private static final ObjectWriter PRETTY_WRITER = JSON_MAPPER.writer()
    .withDefaultPrettyPrinter();
  
  private static final ObjectReader NODE_READER = JSON_MAPPER.readerFor(JsonNode.class);
  
  public static String nodeToString(BaseJsonNode n) {
    try {
      return STD_WRITER.writeValueAsString(_wrapper(n));
    } catch (IOException e) {
      throw new RuntimeException(e);
    } 
  }
  
  public static String nodeToPrettyString(BaseJsonNode n) {
    try {
      return PRETTY_WRITER.writeValueAsString(_wrapper(n));
    } catch (IOException e) {
      throw new RuntimeException(e);
    } 
  }
  
  public static byte[] valueToBytes(Object value) throws IOException {
    return JSON_MAPPER.writeValueAsBytes(value);
  }
  
  public static JsonNode bytesToNode(byte[] json) throws IOException {
    return (JsonNode)NODE_READER.readValue(json);
  }
  
  private static JsonSerializable _wrapper(BaseJsonNode root) {
    return (JsonSerializable)new WrapperForSerializer(root);
  }
  
  protected static class WrapperForSerializer extends JsonSerializable.Base {
    protected final BaseJsonNode _root;
    
    protected SerializerProvider _context;
    
    public WrapperForSerializer(BaseJsonNode root) {
      this._root = root;
    }
    
    public void serialize(JsonGenerator g, SerializerProvider ctxt) throws IOException {
      this._context = ctxt;
      _serializeNonRecursive(g, this._root);
    }
    
    public void serializeWithType(JsonGenerator g, SerializerProvider ctxt, TypeSerializer typeSer) throws IOException {
      serialize(g, ctxt);
    }
    
    protected void _serializeNonRecursive(JsonGenerator g, JsonNode node) throws IOException {
      if (node instanceof ObjectNode) {
        g.writeStartObject(this, node.size());
        _serializeNonRecursive(g, new InternalNodeMapper.IteratorStack(), node.fields());
      } else if (node instanceof ArrayNode) {
        g.writeStartArray(this, node.size());
        _serializeNonRecursive(g, new InternalNodeMapper.IteratorStack(), node.elements());
      } else {
        node.serialize(g, this._context);
      } 
    }
    
    protected void _serializeNonRecursive(JsonGenerator g, InternalNodeMapper.IteratorStack stack, Iterator<?> rootIterator) throws IOException {
      Iterator<?> currIt = rootIterator;
      do {
        while (currIt.hasNext()) {
          JsonNode value;
          Object elem = currIt.next();
          if (elem instanceof Map.Entry) {
            Map.Entry<String, JsonNode> en = (Map.Entry<String, JsonNode>)elem;
            g.writeFieldName(en.getKey());
            value = en.getValue();
          } else {
            value = (JsonNode)elem;
          } 
          if (value instanceof ObjectNode) {
            stack.push(currIt);
            currIt = value.fields();
            g.writeStartObject(value, value.size());
            continue;
          } 
          if (value instanceof ArrayNode) {
            stack.push(currIt);
            currIt = value.elements();
            g.writeStartArray(value, value.size());
            continue;
          } 
          if (value instanceof POJONode) {
            try {
              value.serialize(g, this._context);
            } catch (IOException|RuntimeException e) {
              g.writeString(String.format("[ERROR: (%s) %s]", new Object[] { e
                      .getClass().getName(), e.getMessage() }));
            } 
            continue;
          } 
          value.serialize(g, this._context);
        } 
        if (g.getOutputContext().inArray()) {
          g.writeEndArray();
        } else {
          g.writeEndObject();
        } 
        currIt = stack.popOrNull();
      } while (currIt != null);
    }
  }
  
  static final class IteratorStack {
    private Iterator<?>[] _stack;
    
    private int _top;
    
    private int _end;
    
    public void push(Iterator<?> it) {
      if (this._top < this._end) {
        this._stack[this._top++] = it;
        return;
      } 
      if (this._stack == null) {
        this._end = 10;
        this._stack = (Iterator<?>[])new Iterator[this._end];
      } else {
        this._end += Math.min(4000, Math.max(20, this._end >> 1));
        this._stack = (Iterator<?>[])Arrays.<Iterator>copyOf((Iterator[])this._stack, this._end);
      } 
      this._stack[this._top++] = it;
    }
    
    public Iterator<?> popOrNull() {
      if (this._top == 0)
        return null; 
      return this._stack[--this._top];
    }
  }
}
