package com.fasterxml.jackson.databind.module;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SimpleDeserializers extends Deserializers.Base implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected HashMap<ClassKey, JsonDeserializer<?>> _classMappings = null;
  
  protected boolean _hasEnumDeserializer = false;
  
  public SimpleDeserializers() {}
  
  public SimpleDeserializers(Map<Class<?>, JsonDeserializer<?>> desers) {
    addDeserializers(desers);
  }
  
  public <T> void addDeserializer(Class<T> forClass, JsonDeserializer<? extends T> deser) {
    ClassKey key = new ClassKey(forClass);
    if (this._classMappings == null)
      this._classMappings = new HashMap<>(); 
    this._classMappings.put(key, deser);
    if (forClass == Enum.class)
      this._hasEnumDeserializer = true; 
  }
  
  public void addDeserializers(Map<Class<?>, JsonDeserializer<?>> desers) {
    for (Map.Entry<Class<?>, JsonDeserializer<?>> entry : desers.entrySet()) {
      Class<?> cls = entry.getKey();
      JsonDeserializer<Object> deser = (JsonDeserializer<Object>)entry.getValue();
      addDeserializer(cls, deser);
    } 
  }
  
  public JsonDeserializer<?> findArrayDeserializer(ArrayType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
    return _find((JavaType)type);
  }
  
  public JsonDeserializer<?> findBeanDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
    return _find(type);
  }
  
  public JsonDeserializer<?> findCollectionDeserializer(CollectionType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
    return _find((JavaType)type);
  }
  
  public JsonDeserializer<?> findCollectionLikeDeserializer(CollectionLikeType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
    return _find((JavaType)type);
  }
  
  public JsonDeserializer<?> findEnumDeserializer(Class<?> type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
    if (this._classMappings == null)
      return null; 
    JsonDeserializer<?> deser = this._classMappings.get(new ClassKey(type));
    if (deser == null)
      if (this._hasEnumDeserializer && type.isEnum())
        deser = this._classMappings.get(new ClassKey(Enum.class));  
    return deser;
  }
  
  public JsonDeserializer<?> findTreeNodeDeserializer(Class<? extends JsonNode> nodeType, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
    if (this._classMappings == null)
      return null; 
    return this._classMappings.get(new ClassKey(nodeType));
  }
  
  public JsonDeserializer<?> findReferenceDeserializer(ReferenceType refType, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer contentTypeDeserializer, JsonDeserializer<?> contentDeserializer) throws JsonMappingException {
    return _find((JavaType)refType);
  }
  
  public JsonDeserializer<?> findMapDeserializer(MapType type, DeserializationConfig config, BeanDescription beanDesc, KeyDeserializer keyDeserializer, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
    return _find((JavaType)type);
  }
  
  public JsonDeserializer<?> findMapLikeDeserializer(MapLikeType type, DeserializationConfig config, BeanDescription beanDesc, KeyDeserializer keyDeserializer, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
    return _find((JavaType)type);
  }
  
  public boolean hasDeserializerFor(DeserializationConfig config, Class<?> valueType) {
    return (this._classMappings != null && this._classMappings
      .containsKey(new ClassKey(valueType)));
  }
  
  private final JsonDeserializer<?> _find(JavaType type) {
    if (this._classMappings == null)
      return null; 
    return this._classMappings.get(new ClassKey(type.getRawClass()));
  }
}
