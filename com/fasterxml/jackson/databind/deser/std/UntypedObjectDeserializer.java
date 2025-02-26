package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JacksonStdImpl
public class UntypedObjectDeserializer extends StdDeserializer<Object> implements ResolvableDeserializer, ContextualDeserializer {
  private static final long serialVersionUID = 1L;
  
  protected static final Object[] NO_OBJECTS = new Object[0];
  
  protected JsonDeserializer<Object> _mapDeserializer;
  
  protected JsonDeserializer<Object> _listDeserializer;
  
  protected JsonDeserializer<Object> _stringDeserializer;
  
  protected JsonDeserializer<Object> _numberDeserializer;
  
  protected JavaType _listType;
  
  protected JavaType _mapType;
  
  protected final boolean _nonMerging;
  
  @Deprecated
  public UntypedObjectDeserializer() {
    this((JavaType)null, (JavaType)null);
  }
  
  public UntypedObjectDeserializer(JavaType listType, JavaType mapType) {
    super(Object.class);
    this._listType = listType;
    this._mapType = mapType;
    this._nonMerging = false;
  }
  
  public UntypedObjectDeserializer(UntypedObjectDeserializer base, JsonDeserializer<?> mapDeser, JsonDeserializer<?> listDeser, JsonDeserializer<?> stringDeser, JsonDeserializer<?> numberDeser) {
    super(Object.class);
    this._mapDeserializer = (JsonDeserializer)mapDeser;
    this._listDeserializer = (JsonDeserializer)listDeser;
    this._stringDeserializer = (JsonDeserializer)stringDeser;
    this._numberDeserializer = (JsonDeserializer)numberDeser;
    this._listType = base._listType;
    this._mapType = base._mapType;
    this._nonMerging = base._nonMerging;
  }
  
  protected UntypedObjectDeserializer(UntypedObjectDeserializer base, boolean nonMerging) {
    super(Object.class);
    this._mapDeserializer = base._mapDeserializer;
    this._listDeserializer = base._listDeserializer;
    this._stringDeserializer = base._stringDeserializer;
    this._numberDeserializer = base._numberDeserializer;
    this._listType = base._listType;
    this._mapType = base._mapType;
    this._nonMerging = nonMerging;
  }
  
  public void resolve(DeserializationContext ctxt) throws JsonMappingException {
    JavaType obType = ctxt.constructType(Object.class);
    JavaType stringType = ctxt.constructType(String.class);
    TypeFactory tf = ctxt.getTypeFactory();
    if (this._listType == null) {
      this._listDeserializer = _clearIfStdImpl(_findCustomDeser(ctxt, (JavaType)tf.constructCollectionType(List.class, obType)));
    } else {
      this._listDeserializer = _findCustomDeser(ctxt, this._listType);
    } 
    if (this._mapType == null) {
      this._mapDeserializer = _clearIfStdImpl(_findCustomDeser(ctxt, (JavaType)tf.constructMapType(Map.class, stringType, obType)));
    } else {
      this._mapDeserializer = _findCustomDeser(ctxt, this._mapType);
    } 
    this._stringDeserializer = _clearIfStdImpl(_findCustomDeser(ctxt, stringType));
    this._numberDeserializer = _clearIfStdImpl(_findCustomDeser(ctxt, tf.constructType(Number.class)));
    JavaType unknown = TypeFactory.unknownType();
    this._mapDeserializer = ctxt.handleSecondaryContextualization(this._mapDeserializer, null, unknown);
    this._listDeserializer = ctxt.handleSecondaryContextualization(this._listDeserializer, null, unknown);
    this._stringDeserializer = ctxt.handleSecondaryContextualization(this._stringDeserializer, null, unknown);
    this._numberDeserializer = ctxt.handleSecondaryContextualization(this._numberDeserializer, null, unknown);
  }
  
  protected JsonDeserializer<Object> _findCustomDeser(DeserializationContext ctxt, JavaType type) throws JsonMappingException {
    return ctxt.findNonContextualValueDeserializer(type);
  }
  
  protected JsonDeserializer<Object> _clearIfStdImpl(JsonDeserializer<Object> deser) {
    return ClassUtil.isJacksonStdImpl(deser) ? null : deser;
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
    boolean preventMerge = (property == null && Boolean.FALSE.equals(ctxt.getConfig().getDefaultMergeable(Object.class)));
    if (this._stringDeserializer == null && this._numberDeserializer == null && this._mapDeserializer == null && this._listDeserializer == null && 
      
      getClass() == UntypedObjectDeserializer.class)
      return UntypedObjectDeserializerNR.instance(preventMerge); 
    if (preventMerge != this._nonMerging)
      return new UntypedObjectDeserializer(this, preventMerge); 
    return this;
  }
  
  public boolean isCachable() {
    return true;
  }
  
  public LogicalType logicalType() {
    return LogicalType.Untyped;
  }
  
  public Boolean supportsUpdate(DeserializationConfig config) {
    return null;
  }
  
  public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    switch (p.currentTokenId()) {
      case 1:
      case 2:
      case 5:
        if (this._mapDeserializer != null)
          return this._mapDeserializer.deserialize(p, ctxt); 
        return mapObject(p, ctxt);
      case 3:
        if (ctxt.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY))
          return mapArrayToArray(p, ctxt); 
        if (this._listDeserializer != null)
          return this._listDeserializer.deserialize(p, ctxt); 
        return mapArray(p, ctxt);
      case 12:
        return p.getEmbeddedObject();
      case 6:
        if (this._stringDeserializer != null)
          return this._stringDeserializer.deserialize(p, ctxt); 
        return p.getText();
      case 7:
        if (this._numberDeserializer != null)
          return this._numberDeserializer.deserialize(p, ctxt); 
        if (ctxt.hasSomeOfFeatures(F_MASK_INT_COERCIONS))
          return _coerceIntegral(p, ctxt); 
        return p.getNumberValue();
      case 8:
        if (this._numberDeserializer != null)
          return this._numberDeserializer.deserialize(p, ctxt); 
        if (ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))
          return p.getDecimalValue(); 
        return p.getNumberValue();
      case 9:
        return Boolean.TRUE;
      case 10:
        return Boolean.FALSE;
      case 11:
        return null;
    } 
    return ctxt.handleUnexpectedToken(Object.class, p);
  }
  
  public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
    switch (p.currentTokenId()) {
      case 1:
      case 3:
      case 5:
        return typeDeserializer.deserializeTypedFromAny(p, ctxt);
      case 12:
        return p.getEmbeddedObject();
      case 6:
        if (this._stringDeserializer != null)
          return this._stringDeserializer.deserialize(p, ctxt); 
        return p.getText();
      case 7:
        if (this._numberDeserializer != null)
          return this._numberDeserializer.deserialize(p, ctxt); 
        if (ctxt.hasSomeOfFeatures(F_MASK_INT_COERCIONS))
          return _coerceIntegral(p, ctxt); 
        return p.getNumberValue();
      case 8:
        if (this._numberDeserializer != null)
          return this._numberDeserializer.deserialize(p, ctxt); 
        if (ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))
          return p.getDecimalValue(); 
        return p.getNumberValue();
      case 9:
        return Boolean.TRUE;
      case 10:
        return Boolean.FALSE;
      case 11:
        return null;
    } 
    return ctxt.handleUnexpectedToken(Object.class, p);
  }
  
  public Object deserialize(JsonParser p, DeserializationContext ctxt, Object intoValue) throws IOException {
    if (this._nonMerging)
      return deserialize(p, ctxt); 
    switch (p.currentTokenId()) {
      case 1:
      case 2:
      case 5:
        if (this._mapDeserializer != null)
          return this._mapDeserializer.deserialize(p, ctxt, intoValue); 
        if (intoValue instanceof Map)
          return mapObject(p, ctxt, (Map<Object, Object>)intoValue); 
        return mapObject(p, ctxt);
      case 3:
        if (this._listDeserializer != null)
          return this._listDeserializer.deserialize(p, ctxt, intoValue); 
        if (intoValue instanceof Collection)
          return mapArray(p, ctxt, (Collection<Object>)intoValue); 
        if (ctxt.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY))
          return mapArrayToArray(p, ctxt); 
        return mapArray(p, ctxt);
      case 12:
        return p.getEmbeddedObject();
      case 6:
        if (this._stringDeserializer != null)
          return this._stringDeserializer.deserialize(p, ctxt, intoValue); 
        return p.getText();
      case 7:
        if (this._numberDeserializer != null)
          return this._numberDeserializer.deserialize(p, ctxt, intoValue); 
        if (ctxt.hasSomeOfFeatures(F_MASK_INT_COERCIONS))
          return _coerceIntegral(p, ctxt); 
        return p.getNumberValue();
      case 8:
        if (this._numberDeserializer != null)
          return this._numberDeserializer.deserialize(p, ctxt, intoValue); 
        if (ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))
          return p.getDecimalValue(); 
        return p.getNumberValue();
      case 9:
        return Boolean.TRUE;
      case 10:
        return Boolean.FALSE;
      case 11:
        return null;
    } 
    return deserialize(p, ctxt);
  }
  
  protected Object mapArray(JsonParser p, DeserializationContext ctxt) throws IOException {
    if (p.nextToken() == JsonToken.END_ARRAY)
      return new ArrayList(2); 
    Object value = deserialize(p, ctxt);
    if (p.nextToken() == JsonToken.END_ARRAY) {
      ArrayList<Object> l = new ArrayList(2);
      l.add(value);
      return l;
    } 
    Object value2 = deserialize(p, ctxt);
    if (p.nextToken() == JsonToken.END_ARRAY) {
      ArrayList<Object> l = new ArrayList(2);
      l.add(value);
      l.add(value2);
      return l;
    } 
    ObjectBuffer buffer = ctxt.leaseObjectBuffer();
    Object[] values = buffer.resetAndStart();
    int ptr = 0;
    values[ptr++] = value;
    values[ptr++] = value2;
    int totalSize = ptr;
    do {
      value = deserialize(p, ctxt);
      totalSize++;
      if (ptr >= values.length) {
        values = buffer.appendCompletedChunk(values);
        ptr = 0;
      } 
      values[ptr++] = value;
    } while (p.nextToken() != JsonToken.END_ARRAY);
    ArrayList<Object> result = new ArrayList(totalSize);
    buffer.completeAndClearBuffer(values, ptr, result);
    ctxt.returnObjectBuffer(buffer);
    return result;
  }
  
  protected Object mapArray(JsonParser p, DeserializationContext ctxt, Collection<Object> result) throws IOException {
    while (p.nextToken() != JsonToken.END_ARRAY)
      result.add(deserialize(p, ctxt)); 
    return result;
  }
  
  protected Object mapObject(JsonParser p, DeserializationContext ctxt) throws IOException {
    String key1;
    JsonToken t = p.currentToken();
    if (t == JsonToken.START_OBJECT) {
      key1 = p.nextFieldName();
    } else if (t == JsonToken.FIELD_NAME) {
      key1 = p.currentName();
    } else {
      if (t != JsonToken.END_OBJECT)
        return ctxt.handleUnexpectedToken(handledType(), p); 
      key1 = null;
    } 
    if (key1 == null)
      return new LinkedHashMap<>(2); 
    p.nextToken();
    Object value1 = deserialize(p, ctxt);
    String key2 = p.nextFieldName();
    if (key2 == null) {
      LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>(2);
      linkedHashMap.put(key1, value1);
      return linkedHashMap;
    } 
    p.nextToken();
    Object value2 = deserialize(p, ctxt);
    String key = p.nextFieldName();
    if (key == null) {
      LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>(4);
      linkedHashMap.put(key1, value1);
      if (linkedHashMap.put(key2, value2) != null)
        return _mapObjectWithDups(p, ctxt, linkedHashMap, key1, value1, value2, key); 
      return linkedHashMap;
    } 
    LinkedHashMap<String, Object> result = new LinkedHashMap<>();
    result.put(key1, value1);
    if (result.put(key2, value2) != null)
      return _mapObjectWithDups(p, ctxt, result, key1, value1, value2, key); 
    while (true) {
      p.nextToken();
      Object newValue = deserialize(p, ctxt);
      Object oldValue = result.put(key, newValue);
      if (oldValue != null)
        return _mapObjectWithDups(p, ctxt, result, key, oldValue, newValue, p
            .nextFieldName()); 
      if ((key = p.nextFieldName()) == null)
        return result; 
    } 
  }
  
  protected Object _mapObjectWithDups(JsonParser p, DeserializationContext ctxt, Map<String, Object> result, String key, Object oldValue, Object newValue, String nextKey) throws IOException {
    boolean squashDups = ctxt.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES);
    if (squashDups)
      _squashDups(result, key, oldValue, newValue); 
    while (nextKey != null) {
      p.nextToken();
      newValue = deserialize(p, ctxt);
      oldValue = result.put(nextKey, newValue);
      if (oldValue != null && squashDups)
        _squashDups(result, key, oldValue, newValue); 
      nextKey = p.nextFieldName();
    } 
    return result;
  }
  
  private void _squashDups(Map<String, Object> result, String key, Object oldValue, Object newValue) {
    if (oldValue instanceof List) {
      ((List<Object>)oldValue).add(newValue);
      result.put(key, oldValue);
    } else {
      ArrayList<Object> l = new ArrayList();
      l.add(oldValue);
      l.add(newValue);
      result.put(key, l);
    } 
  }
  
  protected Object[] mapArrayToArray(JsonParser p, DeserializationContext ctxt) throws IOException {
    if (p.nextToken() == JsonToken.END_ARRAY)
      return NO_OBJECTS; 
    ObjectBuffer buffer = ctxt.leaseObjectBuffer();
    Object[] values = buffer.resetAndStart();
    int ptr = 0;
    while (true) {
      Object value = deserialize(p, ctxt);
      if (ptr >= values.length) {
        values = buffer.appendCompletedChunk(values);
        ptr = 0;
      } 
      values[ptr++] = value;
      if (p.nextToken() == JsonToken.END_ARRAY) {
        Object[] result = buffer.completeAndClearBuffer(values, ptr);
        ctxt.returnObjectBuffer(buffer);
        return result;
      } 
    } 
  }
  
  protected Object mapObject(JsonParser p, DeserializationContext ctxt, Map<Object, Object> m) throws IOException {
    JsonToken t = p.currentToken();
    if (t == JsonToken.START_OBJECT)
      t = p.nextToken(); 
    if (t == JsonToken.END_OBJECT)
      return m; 
    String key = p.currentName();
    while (true) {
      Object newV;
      p.nextToken();
      Object old = m.get(key);
      if (old != null) {
        newV = deserialize(p, ctxt, old);
      } else {
        newV = deserialize(p, ctxt);
      } 
      if (newV != old)
        m.put(key, newV); 
      if ((key = p.nextFieldName()) == null)
        return m; 
    } 
  }
  
  @JacksonStdImpl
  @Deprecated
  public static class Vanilla extends StdDeserializer<Object> {
    private static final long serialVersionUID = 1L;
    
    public static final Vanilla std = new Vanilla();
    
    protected final boolean _nonMerging;
    
    public Vanilla() {
      this(false);
    }
    
    protected Vanilla(boolean nonMerging) {
      super(Object.class);
      this._nonMerging = nonMerging;
    }
    
    public static Vanilla instance(boolean nonMerging) {
      if (nonMerging)
        return new Vanilla(true); 
      return std;
    }
    
    public LogicalType logicalType() {
      return LogicalType.Untyped;
    }
    
    public Boolean supportsUpdate(DeserializationConfig config) {
      return this._nonMerging ? Boolean.FALSE : null;
    }
    
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonToken t;
      switch (p.currentTokenId()) {
        case 1:
          t = p.nextToken();
          if (t == JsonToken.END_OBJECT)
            return new LinkedHashMap<>(2); 
        case 5:
          return mapObject(p, ctxt);
        case 3:
          t = p.nextToken();
          if (t == JsonToken.END_ARRAY) {
            if (ctxt.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY))
              return UntypedObjectDeserializer.NO_OBJECTS; 
            return new ArrayList(2);
          } 
          if (ctxt.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY))
            return mapArrayToArray(p, ctxt); 
          return mapArray(p, ctxt);
        case 12:
          return p.getEmbeddedObject();
        case 6:
          return p.getText();
        case 7:
          if (ctxt.hasSomeOfFeatures(F_MASK_INT_COERCIONS))
            return _coerceIntegral(p, ctxt); 
          return p.getNumberValue();
        case 8:
          if (ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))
            return p.getDecimalValue(); 
          return p.getNumberValue();
        case 9:
          return Boolean.TRUE;
        case 10:
          return Boolean.FALSE;
        case 2:
          return new LinkedHashMap<>(2);
        case 11:
          return null;
      } 
      return ctxt.handleUnexpectedToken(Object.class, p);
    }
    
    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
      switch (p.currentTokenId()) {
        case 1:
        case 3:
        case 5:
          return typeDeserializer.deserializeTypedFromAny(p, ctxt);
        case 6:
          return p.getText();
        case 7:
          if (ctxt.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS))
            return p.getBigIntegerValue(); 
          return p.getNumberValue();
        case 8:
          if (ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))
            return p.getDecimalValue(); 
          return p.getNumberValue();
        case 9:
          return Boolean.TRUE;
        case 10:
          return Boolean.FALSE;
        case 12:
          return p.getEmbeddedObject();
        case 11:
          return null;
      } 
      return ctxt.handleUnexpectedToken(Object.class, p);
    }
    
    public Object deserialize(JsonParser p, DeserializationContext ctxt, Object intoValue) throws IOException {
      JsonToken t;
      if (this._nonMerging)
        return deserialize(p, ctxt); 
      switch (p.currentTokenId()) {
        case 2:
        case 4:
          return intoValue;
        case 1:
          t = p.nextToken();
          if (t == JsonToken.END_OBJECT)
            return intoValue; 
        case 5:
          if (intoValue instanceof Map) {
            Map<Object, Object> m = (Map<Object, Object>)intoValue;
            String key = p.currentName();
            while (true) {
              Object newV;
              p.nextToken();
              Object old = m.get(key);
              if (old != null) {
                newV = deserialize(p, ctxt, old);
              } else {
                newV = deserialize(p, ctxt);
              } 
              if (newV != old)
                m.put(key, newV); 
              if ((key = p.nextFieldName()) == null)
                return intoValue; 
            } 
          } 
          break;
        case 3:
          t = p.nextToken();
          if (t == JsonToken.END_ARRAY)
            return intoValue; 
          if (intoValue instanceof Collection) {
            Collection<Object> c = (Collection<Object>)intoValue;
            while (true) {
              c.add(deserialize(p, ctxt));
              if (p.nextToken() == JsonToken.END_ARRAY)
                return intoValue; 
            } 
          } 
          break;
      } 
      return deserialize(p, ctxt);
    }
    
    protected Object mapArray(JsonParser p, DeserializationContext ctxt) throws IOException {
      Object value = deserialize(p, ctxt);
      if (p.nextToken() == JsonToken.END_ARRAY) {
        ArrayList<Object> l = new ArrayList(2);
        l.add(value);
        return l;
      } 
      ObjectBuffer buffer = ctxt.leaseObjectBuffer();
      Object[] values = buffer.resetAndStart();
      int ptr = 0;
      values[ptr++] = value;
      int totalSize = ptr;
      do {
        value = deserialize(p, ctxt);
        totalSize++;
        if (ptr >= values.length) {
          values = buffer.appendCompletedChunk(values);
          ptr = 0;
        } 
        values[ptr++] = value;
      } while (p.nextToken() != JsonToken.END_ARRAY);
      ArrayList<Object> result = new ArrayList(totalSize);
      buffer.completeAndClearBuffer(values, ptr, result);
      ctxt.returnObjectBuffer(buffer);
      return result;
    }
    
    protected Object[] mapArrayToArray(JsonParser p, DeserializationContext ctxt) throws IOException {
      ObjectBuffer buffer = ctxt.leaseObjectBuffer();
      Object[] values = buffer.resetAndStart();
      int ptr = 0;
      while (true) {
        Object value = deserialize(p, ctxt);
        if (ptr >= values.length) {
          values = buffer.appendCompletedChunk(values);
          ptr = 0;
        } 
        values[ptr++] = value;
        if (p.nextToken() == JsonToken.END_ARRAY) {
          Object[] result = buffer.completeAndClearBuffer(values, ptr);
          ctxt.returnObjectBuffer(buffer);
          return result;
        } 
      } 
    }
    
    protected Object mapObject(JsonParser p, DeserializationContext ctxt) throws IOException {
      String key1 = p.currentName();
      p.nextToken();
      Object value1 = deserialize(p, ctxt);
      String key = p.nextFieldName();
      if (key == null) {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>(2);
        linkedHashMap.put(key1, value1);
        return linkedHashMap;
      } 
      LinkedHashMap<String, Object> result = new LinkedHashMap<>();
      result.put(key1, value1);
      while (true) {
        p.nextToken();
        Object newValue = deserialize(p, ctxt);
        Object oldValue = result.put(key, newValue);
        if (oldValue != null)
          return _mapObjectWithDups(p, ctxt, result, key, oldValue, newValue, p
              .nextFieldName()); 
        if ((key = p.nextFieldName()) == null)
          return result; 
      } 
    }
    
    protected Object _mapObjectWithDups(JsonParser p, DeserializationContext ctxt, Map<String, Object> result, String initialKey, Object oldValue, Object newValue, String nextKey) throws IOException {
      boolean squashDups = ctxt.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES);
      if (squashDups)
        _squashDups(result, initialKey, oldValue, newValue); 
      while (nextKey != null) {
        p.nextToken();
        newValue = deserialize(p, ctxt);
        oldValue = result.put(nextKey, newValue);
        if (oldValue != null && squashDups)
          _squashDups(result, nextKey, oldValue, newValue); 
        nextKey = p.nextFieldName();
      } 
      return result;
    }
    
    private void _squashDups(Map<String, Object> result, String key, Object oldValue, Object newValue) {
      if (oldValue instanceof List) {
        ((List<Object>)oldValue).add(newValue);
        result.put(key, oldValue);
      } else {
        ArrayList<Object> l = new ArrayList();
        l.add(oldValue);
        l.add(newValue);
        result.put(key, l);
      } 
    }
  }
}
