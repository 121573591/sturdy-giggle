package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;

public class AsExternalTypeDeserializer extends AsArrayTypeDeserializer {
  private static final long serialVersionUID = 1L;
  
  public AsExternalTypeDeserializer(JavaType bt, TypeIdResolver idRes, String typePropertyName, boolean typeIdVisible, JavaType defaultImpl) {
    super(bt, idRes, typePropertyName, typeIdVisible, defaultImpl);
  }
  
  public AsExternalTypeDeserializer(AsExternalTypeDeserializer src, BeanProperty property) {
    super(src, property);
  }
  
  public TypeDeserializer forProperty(BeanProperty prop) {
    if (prop == this._property)
      return this; 
    return new AsExternalTypeDeserializer(this, prop);
  }
  
  public JsonTypeInfo.As getTypeInclusion() {
    return JsonTypeInfo.As.EXTERNAL_PROPERTY;
  }
  
  protected boolean _usesExternalId() {
    return true;
  }
}
