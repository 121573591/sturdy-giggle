package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Iterator;
import java.util.Map;

abstract class NodeCursor extends JsonStreamContext {
  protected final NodeCursor _parent;
  
  protected String _currentName;
  
  protected Object _currentValue;
  
  public NodeCursor(int contextType, NodeCursor p) {
    this._type = contextType;
    this._index = -1;
    this._parent = p;
  }
  
  public final NodeCursor getParent() {
    return this._parent;
  }
  
  public final String getCurrentName() {
    return this._currentName;
  }
  
  public void overrideCurrentName(String name) {
    this._currentName = name;
  }
  
  public Object getCurrentValue() {
    return this._currentValue;
  }
  
  public void setCurrentValue(Object v) {
    this._currentValue = v;
  }
  
  public final NodeCursor iterateChildren() {
    JsonNode n = currentNode();
    if (n == null)
      throw new IllegalStateException("No current node"); 
    if (n.isArray())
      return new ArrayCursor(n, this); 
    if (n.isObject())
      return new ObjectCursor(n, this); 
    throw new IllegalStateException("Current node of type " + n.getClass().getName());
  }
  
  public abstract JsonToken nextToken();
  
  public abstract JsonNode currentNode();
  
  public abstract NodeCursor startObject();
  
  public abstract NodeCursor startArray();
  
  protected static final class RootCursor extends NodeCursor {
    protected JsonNode _node;
    
    protected boolean _done = false;
    
    public RootCursor(JsonNode n, NodeCursor p) {
      super(0, p);
      this._node = n;
    }
    
    public void overrideCurrentName(String name) {}
    
    public JsonToken nextToken() {
      if (!this._done) {
        this._index++;
        this._done = true;
        return this._node.asToken();
      } 
      this._node = null;
      return null;
    }
    
    public JsonNode currentNode() {
      return this._done ? this._node : null;
    }
    
    public NodeCursor startArray() {
      return new NodeCursor.ArrayCursor(this._node, this);
    }
    
    public NodeCursor startObject() {
      return new NodeCursor.ObjectCursor(this._node, this);
    }
  }
  
  protected static final class ArrayCursor extends NodeCursor {
    protected Iterator<JsonNode> _contents;
    
    protected JsonNode _currentElement;
    
    public ArrayCursor(JsonNode n, NodeCursor p) {
      super(1, p);
      this._contents = n.elements();
    }
    
    public JsonToken nextToken() {
      if (!this._contents.hasNext()) {
        this._currentElement = null;
        return JsonToken.END_ARRAY;
      } 
      this._index++;
      this._currentElement = this._contents.next();
      return this._currentElement.asToken();
    }
    
    public JsonNode currentNode() {
      return this._currentElement;
    }
    
    public NodeCursor startArray() {
      return new ArrayCursor(this._currentElement, this);
    }
    
    public NodeCursor startObject() {
      return new NodeCursor.ObjectCursor(this._currentElement, this);
    }
  }
  
  protected static final class ObjectCursor extends NodeCursor {
    protected Iterator<Map.Entry<String, JsonNode>> _contents;
    
    protected Map.Entry<String, JsonNode> _current;
    
    protected boolean _needEntry;
    
    public ObjectCursor(JsonNode n, NodeCursor p) {
      super(2, p);
      this._contents = n.fields();
      this._needEntry = true;
    }
    
    public JsonToken nextToken() {
      if (this._needEntry) {
        if (!this._contents.hasNext()) {
          this._currentName = null;
          this._current = null;
          return JsonToken.END_OBJECT;
        } 
        this._index++;
        this._needEntry = false;
        this._current = this._contents.next();
        this._currentName = (this._current == null) ? null : this._current.getKey();
        return JsonToken.FIELD_NAME;
      } 
      this._needEntry = true;
      return ((JsonNode)this._current.getValue()).asToken();
    }
    
    public JsonNode currentNode() {
      return (this._current == null) ? null : this._current.getValue();
    }
    
    public NodeCursor startArray() {
      return new NodeCursor.ArrayCursor(currentNode(), this);
    }
    
    public NodeCursor startObject() {
      return new ObjectCursor(currentNode(), this);
    }
  }
}
