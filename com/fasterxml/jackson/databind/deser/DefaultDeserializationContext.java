package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.CacheProvider;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class DefaultDeserializationContext extends DeserializationContext implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected transient LinkedHashMap<ObjectIdGenerator.IdKey, ReadableObjectId> _objectIds;
  
  private List<ObjectIdResolver> _objectIdResolvers;
  
  protected DefaultDeserializationContext(DeserializerFactory df, DeserializerCache cache) {
    super(df, cache);
  }
  
  protected DefaultDeserializationContext(DefaultDeserializationContext src, DeserializationConfig config, JsonParser p, InjectableValues values) {
    super(src, config, p, values);
  }
  
  protected DefaultDeserializationContext(DefaultDeserializationContext src, DeserializationConfig config) {
    super(src, config);
  }
  
  protected DefaultDeserializationContext(DefaultDeserializationContext src, DeserializerFactory factory) {
    super(src, factory);
  }
  
  protected DefaultDeserializationContext(DefaultDeserializationContext src) {
    super(src);
  }
  
  protected DefaultDeserializationContext(DefaultDeserializationContext src, CacheProvider cp) {
    super(src, new DeserializerCache(cp
          .forDeserializerCache(src._config)));
  }
  
  public DefaultDeserializationContext copy() {
    throw new IllegalStateException("DefaultDeserializationContext sub-class not overriding copy()");
  }
  
  public ReadableObjectId findObjectId(Object id, ObjectIdGenerator<?> gen, ObjectIdResolver resolverType) {
    if (id == null)
      return null; 
    ObjectIdGenerator.IdKey key = gen.key(id);
    if (this._objectIds == null) {
      this._objectIds = new LinkedHashMap<>();
    } else {
      ReadableObjectId readableObjectId = this._objectIds.get(key);
      if (readableObjectId != null)
        return readableObjectId; 
    } 
    ObjectIdResolver resolver = null;
    if (this._objectIdResolvers == null) {
      this._objectIdResolvers = new ArrayList<>(8);
    } else {
      for (ObjectIdResolver res : this._objectIdResolvers) {
        if (res.canUseFor(resolverType)) {
          resolver = res;
          break;
        } 
      } 
    } 
    if (resolver == null) {
      resolver = resolverType.newForDeserialization(this);
      this._objectIdResolvers.add(resolver);
    } 
    ReadableObjectId entry = createReadableObjectId(key);
    entry.setResolver(resolver);
    this._objectIds.put(key, entry);
    return entry;
  }
  
  protected ReadableObjectId createReadableObjectId(ObjectIdGenerator.IdKey key) {
    return new ReadableObjectId(key);
  }
  
  public void checkUnresolvedObjectId() throws UnresolvedForwardReference {
    if (this._objectIds == null)
      return; 
    if (!isEnabled(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS))
      return; 
    UnresolvedForwardReference exception = null;
    for (Map.Entry<ObjectIdGenerator.IdKey, ReadableObjectId> entry : this._objectIds.entrySet()) {
      ReadableObjectId roid = entry.getValue();
      if (!roid.hasReferringProperties())
        continue; 
      if (tryToResolveUnresolvedObjectId(roid))
        continue; 
      if (exception == null)
        exception = (new UnresolvedForwardReference(getParser(), "Unresolved forward references for: ")).withStackTrace(); 
      Object key = (roid.getKey()).key;
      for (Iterator<ReadableObjectId.Referring> iterator = roid.referringProperties(); iterator.hasNext(); ) {
        ReadableObjectId.Referring referring = iterator.next();
        exception.addUnresolvedId(key, referring.getBeanType(), referring.getLocation());
      } 
    } 
    if (exception != null)
      throw exception; 
  }
  
  protected boolean tryToResolveUnresolvedObjectId(ReadableObjectId roid) {
    return roid.tryToResolveUnresolved(this);
  }
  
  public JsonDeserializer<Object> deserializerInstance(Annotated ann, Object deserDef) throws JsonMappingException {
    JsonDeserializer<?> deser;
    if (deserDef == null)
      return null; 
    if (deserDef instanceof JsonDeserializer) {
      deser = (JsonDeserializer)deserDef;
    } else {
      if (!(deserDef instanceof Class))
        throw new IllegalStateException("AnnotationIntrospector returned deserializer definition of type " + deserDef.getClass().getName() + "; expected type JsonDeserializer or Class<JsonDeserializer> instead"); 
      Class<?> deserClass = (Class)deserDef;
      if (deserClass == JsonDeserializer.None.class || ClassUtil.isBogusClass(deserClass))
        return null; 
      if (!JsonDeserializer.class.isAssignableFrom(deserClass))
        throw new IllegalStateException("AnnotationIntrospector returned Class " + deserClass.getName() + "; expected Class<JsonDeserializer>"); 
      HandlerInstantiator hi = this._config.getHandlerInstantiator();
      deser = (hi == null) ? null : hi.deserializerInstance(this._config, ann, deserClass);
      if (deser == null)
        deser = (JsonDeserializer)ClassUtil.createInstance(deserClass, this._config
            .canOverrideAccessModifiers()); 
    } 
    if (deser instanceof ResolvableDeserializer)
      ((ResolvableDeserializer)deser).resolve(this); 
    return (JsonDeserializer)deser;
  }
  
  public final KeyDeserializer keyDeserializerInstance(Annotated ann, Object deserDef) throws JsonMappingException {
    KeyDeserializer deser;
    if (deserDef == null)
      return null; 
    if (deserDef instanceof KeyDeserializer) {
      deser = (KeyDeserializer)deserDef;
    } else {
      if (!(deserDef instanceof Class))
        throw new IllegalStateException("AnnotationIntrospector returned key deserializer definition of type " + deserDef
            .getClass().getName() + "; expected type KeyDeserializer or Class<KeyDeserializer> instead"); 
      Class<?> deserClass = (Class)deserDef;
      if (deserClass == KeyDeserializer.None.class || ClassUtil.isBogusClass(deserClass))
        return null; 
      if (!KeyDeserializer.class.isAssignableFrom(deserClass))
        throw new IllegalStateException("AnnotationIntrospector returned Class " + deserClass.getName() + "; expected Class<KeyDeserializer>"); 
      HandlerInstantiator hi = this._config.getHandlerInstantiator();
      deser = (hi == null) ? null : hi.keyDeserializerInstance(this._config, ann, deserClass);
      if (deser == null)
        deser = (KeyDeserializer)ClassUtil.createInstance(deserClass, this._config
            .canOverrideAccessModifiers()); 
    } 
    if (deser instanceof ResolvableDeserializer)
      ((ResolvableDeserializer)deser).resolve(this); 
    return deser;
  }
  
  public abstract DefaultDeserializationContext with(DeserializerFactory paramDeserializerFactory);
  
  public abstract DefaultDeserializationContext withCaches(CacheProvider paramCacheProvider);
  
  public abstract DefaultDeserializationContext createInstance(DeserializationConfig paramDeserializationConfig, JsonParser paramJsonParser, InjectableValues paramInjectableValues);
  
  public abstract DefaultDeserializationContext createDummyInstance(DeserializationConfig paramDeserializationConfig);
  
  public Object readRootValue(JsonParser p, JavaType valueType, JsonDeserializer<Object> deser, Object valueToUpdate) throws IOException {
    if (this._config.useRootWrapping())
      return _unwrapAndDeserialize(p, valueType, deser, valueToUpdate); 
    if (valueToUpdate == null)
      return deser.deserialize(p, this); 
    return deser.deserialize(p, this, valueToUpdate);
  }
  
  protected Object _unwrapAndDeserialize(JsonParser p, JavaType rootType, JsonDeserializer<Object> deser, Object valueToUpdate) throws IOException {
    Object result;
    PropertyName expRootName = this._config.findRootName(rootType);
    String expSimpleName = expRootName.getSimpleName();
    if (p.currentToken() != JsonToken.START_OBJECT)
      reportWrongTokenException(rootType, JsonToken.START_OBJECT, "Current token not START_OBJECT (needed to unwrap root name %s), but %s", new Object[] { ClassUtil.name(expSimpleName), p.currentToken() }); 
    if (p.nextToken() != JsonToken.FIELD_NAME)
      reportWrongTokenException(rootType, JsonToken.FIELD_NAME, "Current token not FIELD_NAME (to contain expected root name %s), but %s", new Object[] { ClassUtil.name(expSimpleName), p.currentToken() }); 
    String actualName = p.currentName();
    if (!expSimpleName.equals(actualName))
      reportPropertyInputMismatch(rootType, actualName, "Root name (%s) does not match expected (%s) for type %s", new Object[] { ClassUtil.name(actualName), ClassUtil.name(expSimpleName), ClassUtil.getTypeDescription(rootType) }); 
    p.nextToken();
    if (valueToUpdate == null) {
      result = deser.deserialize(p, this);
    } else {
      result = deser.deserialize(p, this, valueToUpdate);
    } 
    if (p.nextToken() != JsonToken.END_OBJECT)
      reportWrongTokenException(rootType, JsonToken.END_OBJECT, "Current token not END_OBJECT (to match wrapper object with root name %s), but %s", new Object[] { ClassUtil.name(expSimpleName), p.currentToken() }); 
    return result;
  }
  
  public static final class Impl extends DefaultDeserializationContext {
    private static final long serialVersionUID = 1L;
    
    public Impl(DeserializerFactory df) {
      super(df, new DeserializerCache());
    }
    
    private Impl(Impl src, DeserializationConfig config, JsonParser p, InjectableValues values) {
      super(src, config, p, values);
    }
    
    private Impl(Impl src) {
      super(src);
    }
    
    private Impl(Impl src, DeserializerFactory factory) {
      super(src, factory);
    }
    
    private Impl(Impl src, DeserializationConfig config) {
      super(src, config);
    }
    
    private Impl(Impl src, CacheProvider cp) {
      super(src, cp);
    }
    
    public DefaultDeserializationContext copy() {
      ClassUtil.verifyMustOverride(Impl.class, this, "copy");
      return new Impl(this);
    }
    
    public DefaultDeserializationContext createInstance(DeserializationConfig config, JsonParser p, InjectableValues values) {
      return new Impl(this, config, p, values);
    }
    
    public DefaultDeserializationContext createDummyInstance(DeserializationConfig config) {
      return new Impl(this, config);
    }
    
    public DefaultDeserializationContext with(DeserializerFactory factory) {
      return new Impl(this, factory);
    }
    
    public DefaultDeserializationContext withCaches(CacheProvider cp) {
      return new Impl(this, cp);
    }
  }
}
