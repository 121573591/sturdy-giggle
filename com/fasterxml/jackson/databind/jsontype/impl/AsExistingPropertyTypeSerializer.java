package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public class AsExistingPropertyTypeSerializer extends AsPropertyTypeSerializer {
  public AsExistingPropertyTypeSerializer(TypeIdResolver idRes, BeanProperty property, String propName) {
    super(idRes, property, propName);
  }
  
  public AsExistingPropertyTypeSerializer forProperty(BeanProperty prop) {
    return (this._property == prop) ? this : new AsExistingPropertyTypeSerializer(this._idResolver, prop, this._typePropertyName);
  }
  
  public JsonTypeInfo.As getTypeInclusion() {
    return JsonTypeInfo.As.EXISTING_PROPERTY;
  }
}
