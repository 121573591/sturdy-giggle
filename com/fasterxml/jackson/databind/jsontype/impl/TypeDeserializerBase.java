package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.NullifyingDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class TypeDeserializerBase extends TypeDeserializer implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected final TypeIdResolver _idResolver;
  
  protected final JavaType _baseType;
  
  protected final BeanProperty _property;
  
  protected final JavaType _defaultImpl;
  
  protected final String _typePropertyName;
  
  protected final boolean _typeIdVisible;
  
  protected final Map<String, JsonDeserializer<Object>> _deserializers;
  
  protected JsonDeserializer<Object> _defaultImplDeserializer;
  
  protected TypeDeserializerBase(JavaType baseType, TypeIdResolver idRes, String typePropertyName, boolean typeIdVisible, JavaType defaultImpl) {
    this._baseType = baseType;
    this._idResolver = idRes;
    this._typePropertyName = ClassUtil.nonNullString(typePropertyName);
    this._typeIdVisible = typeIdVisible;
    this._deserializers = new ConcurrentHashMap<>(16, 0.75F, 2);
    this._defaultImpl = defaultImpl;
    this._property = null;
  }
  
  protected TypeDeserializerBase(TypeDeserializerBase src, BeanProperty property) {
    this._baseType = src._baseType;
    this._idResolver = src._idResolver;
    this._typePropertyName = src._typePropertyName;
    this._typeIdVisible = src._typeIdVisible;
    this._deserializers = src._deserializers;
    this._defaultImpl = src._defaultImpl;
    this._defaultImplDeserializer = src._defaultImplDeserializer;
    this._property = property;
  }
  
  public abstract TypeDeserializer forProperty(BeanProperty paramBeanProperty);
  
  public abstract JsonTypeInfo.As getTypeInclusion();
  
  public String baseTypeName() {
    return this._baseType.getRawClass().getName();
  }
  
  public final String getPropertyName() {
    return this._typePropertyName;
  }
  
  public TypeIdResolver getTypeIdResolver() {
    return this._idResolver;
  }
  
  public Class<?> getDefaultImpl() {
    return ClassUtil.rawClass(this._defaultImpl);
  }
  
  public boolean hasDefaultImpl() {
    return (this._defaultImpl != null);
  }
  
  public JavaType baseType() {
    return this._baseType;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append('[').append(getClass().getName());
    sb.append("; base-type:").append(this._baseType);
    sb.append("; id-resolver: ").append(this._idResolver);
    sb.append(']');
    return sb.toString();
  }
  
  protected final JsonDeserializer<Object> _findDeserializer(DeserializationContext ctxt, String typeId) throws IOException {
    JsonDeserializer<Object> deser = this._deserializers.get(typeId);
    if (deser == null) {
      JavaType type = this._idResolver.typeFromId((DatabindContext)ctxt, typeId);
      if (type == null) {
        deser = _findDefaultImplDeserializer(ctxt);
        if (deser == null) {
          JavaType actual = _handleUnknownTypeId(ctxt, typeId);
          if (actual == null)
            return (JsonDeserializer<Object>)NullifyingDeserializer.instance; 
          deser = ctxt.findContextualValueDeserializer(actual, this._property);
        } 
      } else {
        if (this._baseType != null && this._baseType
          .getClass() == type.getClass())
          if (!type.hasGenericTypes())
            try {
              type = ctxt.constructSpecializedType(this._baseType, type.getRawClass());
            } catch (IllegalArgumentException e) {
              throw ctxt.invalidTypeIdException(this._baseType, typeId, e.getMessage());
            }   
        deser = ctxt.findContextualValueDeserializer(type, this._property);
      } 
      this._deserializers.put(typeId, deser);
    } 
    return deser;
  }
  
  protected final JsonDeserializer<Object> _findDefaultImplDeserializer(DeserializationContext ctxt) throws IOException {
    if (this._defaultImpl == null) {
      if (!ctxt.isEnabled(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE))
        return (JsonDeserializer<Object>)NullifyingDeserializer.instance; 
      return null;
    } 
    Class<?> raw = this._defaultImpl.getRawClass();
    if (ClassUtil.isBogusClass(raw))
      return (JsonDeserializer<Object>)NullifyingDeserializer.instance; 
    synchronized (this._defaultImpl) {
      if (this._defaultImplDeserializer == null)
        this._defaultImplDeserializer = ctxt.findContextualValueDeserializer(this._defaultImpl, this._property); 
      return this._defaultImplDeserializer;
    } 
  }
  
  @Deprecated
  protected Object _deserializeWithNativeTypeId(JsonParser jp, DeserializationContext ctxt) throws IOException {
    return _deserializeWithNativeTypeId(jp, ctxt, jp.getTypeId());
  }
  
  protected Object _deserializeWithNativeTypeId(JsonParser p, DeserializationContext ctxt, Object typeId) throws IOException {
    JsonDeserializer<Object> deser;
    if (typeId == null) {
      deser = _findDefaultImplDeserializer(ctxt);
      if (deser == null)
        return ctxt.reportInputMismatch(baseType(), "No (native) type id found when one was expected for polymorphic type handling", new Object[0]); 
    } else {
      String typeIdStr = (typeId instanceof String) ? (String)typeId : String.valueOf(typeId);
      deser = _findDeserializer(ctxt, typeIdStr);
    } 
    return deser.deserialize(p, ctxt);
  }
  
  protected JavaType _handleUnknownTypeId(DeserializationContext ctxt, String typeId) throws IOException {
    String extraDesc = this._idResolver.getDescForKnownTypeIds();
    if (extraDesc == null) {
      extraDesc = "type ids are not statically known";
    } else {
      extraDesc = "known type ids = " + extraDesc;
    } 
    if (this._property != null)
      extraDesc = String.format("%s (for POJO property '%s')", new Object[] { extraDesc, this._property
            .getName() }); 
    return ctxt.handleUnknownTypeId(this._baseType, typeId, this._idResolver, extraDesc);
  }
  
  protected JavaType _handleMissingTypeId(DeserializationContext ctxt, String extraDesc) throws IOException {
    return ctxt.handleMissingTypeId(this._baseType, this._idResolver, extraDesc);
  }
}
