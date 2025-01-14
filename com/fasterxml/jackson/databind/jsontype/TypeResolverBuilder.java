package com.fasterxml.jackson.databind.jsontype;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import java.util.Collection;

public interface TypeResolverBuilder<T extends TypeResolverBuilder<T>> {
  Class<?> getDefaultImpl();
  
  TypeSerializer buildTypeSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, Collection<NamedType> paramCollection);
  
  TypeDeserializer buildTypeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, Collection<NamedType> paramCollection);
  
  T init(JsonTypeInfo.Id paramId, TypeIdResolver paramTypeIdResolver);
  
  default T init(JsonTypeInfo.Value settings, TypeIdResolver res) {
    return init(settings.getIdType(), res);
  }
  
  T inclusion(JsonTypeInfo.As paramAs);
  
  T typeProperty(String paramString);
  
  T defaultImpl(Class<?> paramClass);
  
  T typeIdVisibility(boolean paramBoolean);
  
  default T withDefaultImpl(Class<?> defaultImpl) {
    return defaultImpl(defaultImpl);
  }
  
  default T withSettings(JsonTypeInfo.Value typeInfo) {
    throw new IllegalStateException("TypeResolveBuilder implementation " + 
        getClass().getName() + " must implement `withSettings()`");
  }
}
