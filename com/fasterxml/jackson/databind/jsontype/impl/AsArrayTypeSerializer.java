package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public class AsArrayTypeSerializer extends TypeSerializerBase {
  public AsArrayTypeSerializer(TypeIdResolver idRes, BeanProperty property) {
    super(idRes, property);
  }
  
  public AsArrayTypeSerializer forProperty(BeanProperty prop) {
    return (this._property == prop) ? this : new AsArrayTypeSerializer(this._idResolver, prop);
  }
  
  public JsonTypeInfo.As getTypeInclusion() {
    return JsonTypeInfo.As.WRAPPER_ARRAY;
  }
}
