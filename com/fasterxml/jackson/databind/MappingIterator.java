package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MappingIterator<T> implements Iterator<T>, Closeable {
  protected static final MappingIterator<?> EMPTY_ITERATOR = new MappingIterator(null, null, null, null, false, null);
  
  protected static final int STATE_CLOSED = 0;
  
  protected static final int STATE_NEED_RESYNC = 1;
  
  protected static final int STATE_MAY_HAVE_VALUE = 2;
  
  protected static final int STATE_HAS_VALUE = 3;
  
  protected final JavaType _type;
  
  protected final DeserializationContext _context;
  
  protected final JsonDeserializer<T> _deserializer;
  
  protected final JsonParser _parser;
  
  protected final JsonStreamContext _seqContext;
  
  protected final T _updatedValue;
  
  protected final boolean _closeParser;
  
  protected int _state;
  
  protected MappingIterator(JavaType type, JsonParser p, DeserializationContext ctxt, JsonDeserializer<?> deser, boolean managedParser, Object valueToUpdate) {
    this._type = type;
    this._parser = p;
    this._context = ctxt;
    this._deserializer = (JsonDeserializer)deser;
    this._closeParser = managedParser;
    if (valueToUpdate == null) {
      this._updatedValue = null;
    } else {
      this._updatedValue = (T)valueToUpdate;
    } 
    if (p == null) {
      this._seqContext = null;
      this._state = 0;
    } else {
      JsonStreamContext sctxt = p.getParsingContext();
      if (managedParser && p.isExpectedStartArrayToken()) {
        p.clearCurrentToken();
      } else {
        JsonToken t = p.currentToken();
        if (t == JsonToken.START_OBJECT || t == JsonToken.START_ARRAY)
          sctxt = sctxt.getParent(); 
      } 
      this._seqContext = sctxt;
      this._state = 2;
    } 
  }
  
  public static <T> MappingIterator<T> emptyIterator() {
    return (MappingIterator)EMPTY_ITERATOR;
  }
  
  public boolean hasNext() {
    try {
      return hasNextValue();
    } catch (JsonMappingException e) {
      return ((Boolean)_handleMappingException(e)).booleanValue();
    } catch (IOException e) {
      return ((Boolean)_handleIOException(e)).booleanValue();
    } 
  }
  
  public T next() {
    try {
      return nextValue();
    } catch (JsonMappingException e) {
      return _handleMappingException(e);
    } catch (IOException e) {
      return _handleIOException(e);
    } 
  }
  
  public void remove() {
    throw new UnsupportedOperationException();
  }
  
  public void close() throws IOException {
    if (this._state != 0) {
      this._state = 0;
      if (this._parser != null)
        this._parser.close(); 
    } 
  }
  
  public boolean hasNextValue() throws IOException {
    JsonToken t;
    switch (this._state) {
      case 0:
        return false;
      case 1:
        _resync();
      case 2:
        if (this._parser == null)
          return false; 
        t = this._parser.currentToken();
        if (t == null) {
          t = this._parser.nextToken();
          if (t == null || t == JsonToken.END_ARRAY) {
            this._state = 0;
            if (this._closeParser)
              this._parser.close(); 
            return false;
          } 
        } 
        this._state = 3;
        return true;
    } 
    return true;
  }
  
  public T nextValue() throws IOException {
    switch (this._state) {
      case 0:
        return _throwNoSuchElement();
      case 1:
      case 2:
        if (!hasNextValue())
          return _throwNoSuchElement(); 
        break;
    } 
    int nextState = 1;
    try {
      T value;
      if (this._updatedValue == null) {
        value = this._deserializer.deserialize(this._parser, this._context);
      } else {
        this._deserializer.deserialize(this._parser, this._context, this._updatedValue);
        value = this._updatedValue;
      } 
      nextState = 2;
      return value;
    } finally {
      this._state = nextState;
      this._parser.clearCurrentToken();
    } 
  }
  
  public List<T> readAll() throws IOException {
    return readAll(new ArrayList<>());
  }
  
  public <L extends List<? super T>> L readAll(L resultList) throws IOException {
    while (hasNextValue())
      resultList.add(nextValue()); 
    return resultList;
  }
  
  public <C extends java.util.Collection<? super T>> C readAll(C results) throws IOException {
    while (hasNextValue())
      results.add(nextValue()); 
    return results;
  }
  
  public JsonParser getParser() {
    return this._parser;
  }
  
  public FormatSchema getParserSchema() {
    return this._parser.getSchema();
  }
  
  public JsonLocation getCurrentLocation() {
    return this._parser.getCurrentLocation();
  }
  
  protected void _resync() throws IOException {
    JsonParser p = this._parser;
    if (p.getParsingContext() == this._seqContext)
      return; 
    while (true) {
      JsonToken t = p.nextToken();
      if (t == JsonToken.END_ARRAY || t == JsonToken.END_OBJECT) {
        if (p.getParsingContext() == this._seqContext) {
          p.clearCurrentToken();
          return;
        } 
        continue;
      } 
      if (t == JsonToken.START_ARRAY || t == JsonToken.START_OBJECT) {
        p.skipChildren();
        continue;
      } 
      if (t == null)
        break; 
    } 
  }
  
  protected <R> R _throwNoSuchElement() {
    throw new NoSuchElementException();
  }
  
  protected <R> R _handleMappingException(JsonMappingException e) {
    throw new RuntimeJsonMappingException(e.getMessage(), e);
  }
  
  protected <R> R _handleIOException(IOException e) {
    throw new RuntimeException(e.getMessage(), e);
  }
}
