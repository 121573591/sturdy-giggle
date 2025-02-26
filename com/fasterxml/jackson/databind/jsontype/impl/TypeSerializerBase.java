package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public abstract class TypeSerializerBase extends TypeSerializer {
  protected final TypeIdResolver _idResolver;
  
  protected final BeanProperty _property;
  
  protected TypeSerializerBase(TypeIdResolver idRes, BeanProperty property) {
    this._idResolver = idRes;
    this._property = property;
  }
  
  public abstract JsonTypeInfo.As getTypeInclusion();
  
  public String getPropertyName() {
    return null;
  }
  
  public TypeIdResolver getTypeIdResolver() {
    return this._idResolver;
  }
  
  public WritableTypeId writeTypePrefix(JsonGenerator g, WritableTypeId idMetadata) throws IOException {
    _generateTypeId(idMetadata);
    if (idMetadata.id == null)
      return null; 
    return g.writeTypePrefix(idMetadata);
  }
  
  public WritableTypeId writeTypeSuffix(JsonGenerator g, WritableTypeId idMetadata) throws IOException {
    if (idMetadata == null)
      return null; 
    return g.writeTypeSuffix(idMetadata);
  }
  
  protected void _generateTypeId(WritableTypeId idMetadata) {
    Object id = idMetadata.id;
    if (id == null) {
      Object value = idMetadata.forValue;
      Class<?> typeForId = idMetadata.forValueType;
      if (typeForId == null) {
        id = idFromValue(value);
      } else {
        id = idFromValueAndType(value, typeForId);
      } 
      idMetadata.id = id;
    } 
  }
  
  protected String idFromValue(Object value) {
    String id = this._idResolver.idFromValue(value);
    if (id == null)
      handleMissingId(value); 
    return id;
  }
  
  protected String idFromValueAndType(Object value, Class<?> type) {
    String id = this._idResolver.idFromValueAndType(value, type);
    if (id == null)
      handleMissingId(value); 
    return id;
  }
  
  protected void handleMissingId(Object value) {}
}
