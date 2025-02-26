package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.impl.ReadOnlyClassToSerializerMap;
import com.fasterxml.jackson.databind.util.LRUMap;
import com.fasterxml.jackson.databind.util.LookupCache;
import com.fasterxml.jackson.databind.util.TypeKey;
import java.util.concurrent.atomic.AtomicReference;

public final class SerializerCache {
  public static final int DEFAULT_MAX_CACHE_SIZE = 4000;
  
  @Deprecated
  public static final int DEFAULT_MAX_CACHED = 4000;
  
  private final LookupCache<TypeKey, JsonSerializer<Object>> _sharedMap;
  
  private final AtomicReference<ReadOnlyClassToSerializerMap> _readOnlyMap = new AtomicReference<>();
  
  public SerializerCache() {
    this(4000);
  }
  
  public SerializerCache(int maxCached) {
    int initial = Math.min(64, maxCached >> 2);
    this._sharedMap = (LookupCache<TypeKey, JsonSerializer<Object>>)new LRUMap(initial, maxCached);
  }
  
  public SerializerCache(LookupCache<TypeKey, JsonSerializer<Object>> cache) {
    this._sharedMap = cache;
  }
  
  public ReadOnlyClassToSerializerMap getReadOnlyLookupMap() {
    ReadOnlyClassToSerializerMap m = this._readOnlyMap.get();
    if (m != null)
      return m; 
    return _makeReadOnlyLookupMap();
  }
  
  private final synchronized ReadOnlyClassToSerializerMap _makeReadOnlyLookupMap() {
    ReadOnlyClassToSerializerMap m = this._readOnlyMap.get();
    if (m == null) {
      m = ReadOnlyClassToSerializerMap.from(this._sharedMap);
      this._readOnlyMap.set(m);
    } 
    return m;
  }
  
  public synchronized int size() {
    return this._sharedMap.size();
  }
  
  public JsonSerializer<Object> untypedValueSerializer(Class<?> type) {
    synchronized (this) {
      return (JsonSerializer<Object>)this._sharedMap.get(new TypeKey(type, false));
    } 
  }
  
  public JsonSerializer<Object> untypedValueSerializer(JavaType type) {
    synchronized (this) {
      return (JsonSerializer<Object>)this._sharedMap.get(new TypeKey(type, false));
    } 
  }
  
  public JsonSerializer<Object> typedValueSerializer(JavaType type) {
    synchronized (this) {
      return (JsonSerializer<Object>)this._sharedMap.get(new TypeKey(type, true));
    } 
  }
  
  public JsonSerializer<Object> typedValueSerializer(Class<?> cls) {
    synchronized (this) {
      return (JsonSerializer<Object>)this._sharedMap.get(new TypeKey(cls, true));
    } 
  }
  
  public void addTypedSerializer(JavaType type, JsonSerializer<Object> ser) {
    synchronized (this) {
      if (this._sharedMap.put(new TypeKey(type, true), ser) == null)
        this._readOnlyMap.set(null); 
    } 
  }
  
  public void addTypedSerializer(Class<?> cls, JsonSerializer<Object> ser) {
    synchronized (this) {
      if (this._sharedMap.put(new TypeKey(cls, true), ser) == null)
        this._readOnlyMap.set(null); 
    } 
  }
  
  public void addAndResolveNonTypedSerializer(Class<?> type, JsonSerializer<Object> ser, SerializerProvider provider) throws JsonMappingException {
    synchronized (this) {
      if (this._sharedMap.put(new TypeKey(type, false), ser) == null)
        this._readOnlyMap.set(null); 
      if (ser instanceof ResolvableSerializer)
        ((ResolvableSerializer)ser).resolve(provider); 
    } 
  }
  
  public void addAndResolveNonTypedSerializer(JavaType type, JsonSerializer<Object> ser, SerializerProvider provider) throws JsonMappingException {
    synchronized (this) {
      if (this._sharedMap.put(new TypeKey(type, false), ser) == null)
        this._readOnlyMap.set(null); 
      if (ser instanceof ResolvableSerializer)
        ((ResolvableSerializer)ser).resolve(provider); 
    } 
  }
  
  public void addAndResolveNonTypedSerializer(Class<?> rawType, JavaType fullType, JsonSerializer<Object> ser, SerializerProvider provider) throws JsonMappingException {
    synchronized (this) {
      Object ob1 = this._sharedMap.put(new TypeKey(rawType, false), ser);
      Object ob2 = this._sharedMap.put(new TypeKey(fullType, false), ser);
      if (ob1 == null || ob2 == null)
        this._readOnlyMap.set(null); 
      if (ser instanceof ResolvableSerializer)
        ((ResolvableSerializer)ser).resolve(provider); 
    } 
  }
  
  public synchronized void flush() {
    this._sharedMap.clear();
    this._readOnlyMap.set(null);
  }
}
