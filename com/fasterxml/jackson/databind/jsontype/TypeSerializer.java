package com.fasterxml.jackson.databind.jsontype;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.BeanProperty;
import java.io.IOException;

public abstract class TypeSerializer {
  public abstract TypeSerializer forProperty(BeanProperty paramBeanProperty);
  
  public abstract JsonTypeInfo.As getTypeInclusion();
  
  public abstract String getPropertyName();
  
  public abstract TypeIdResolver getTypeIdResolver();
  
  public WritableTypeId typeId(Object value, JsonToken valueShape) {
    WritableTypeId typeIdDef = new WritableTypeId(value, valueShape);
    switch (getTypeInclusion()) {
      case EXISTING_PROPERTY:
        typeIdDef.include = WritableTypeId.Inclusion.PAYLOAD_PROPERTY;
        typeIdDef.asProperty = getPropertyName();
        return typeIdDef;
      case EXTERNAL_PROPERTY:
        typeIdDef.include = WritableTypeId.Inclusion.PARENT_PROPERTY;
        typeIdDef.asProperty = getPropertyName();
        return typeIdDef;
      case PROPERTY:
        typeIdDef.include = WritableTypeId.Inclusion.METADATA_PROPERTY;
        typeIdDef.asProperty = getPropertyName();
        return typeIdDef;
      case WRAPPER_ARRAY:
        typeIdDef.include = WritableTypeId.Inclusion.WRAPPER_ARRAY;
        return typeIdDef;
      case WRAPPER_OBJECT:
        typeIdDef.include = WritableTypeId.Inclusion.WRAPPER_OBJECT;
        return typeIdDef;
    } 
    VersionUtil.throwInternal();
    return typeIdDef;
  }
  
  public WritableTypeId typeId(Object value, JsonToken valueShape, Object id) {
    WritableTypeId typeId = typeId(value, valueShape);
    typeId.id = id;
    return typeId;
  }
  
  public WritableTypeId typeId(Object value, Class<?> typeForId, JsonToken valueShape) {
    WritableTypeId typeId = typeId(value, valueShape);
    typeId.forValueType = typeForId;
    return typeId;
  }
  
  public abstract WritableTypeId writeTypePrefix(JsonGenerator paramJsonGenerator, WritableTypeId paramWritableTypeId) throws IOException;
  
  public abstract WritableTypeId writeTypeSuffix(JsonGenerator paramJsonGenerator, WritableTypeId paramWritableTypeId) throws IOException;
  
  @Deprecated
  public void writeTypePrefixForScalar(Object value, JsonGenerator g) throws IOException {
    writeTypePrefix(g, typeId(value, JsonToken.VALUE_STRING));
  }
  
  @Deprecated
  public void writeTypePrefixForObject(Object value, JsonGenerator g) throws IOException {
    writeTypePrefix(g, typeId(value, JsonToken.START_OBJECT));
  }
  
  @Deprecated
  public void writeTypePrefixForArray(Object value, JsonGenerator g) throws IOException {
    writeTypePrefix(g, typeId(value, JsonToken.START_ARRAY));
  }
  
  @Deprecated
  public void writeTypeSuffixForScalar(Object value, JsonGenerator g) throws IOException {
    _writeLegacySuffix(g, typeId(value, JsonToken.VALUE_STRING));
  }
  
  @Deprecated
  public void writeTypeSuffixForObject(Object value, JsonGenerator g) throws IOException {
    _writeLegacySuffix(g, typeId(value, JsonToken.START_OBJECT));
  }
  
  @Deprecated
  public void writeTypeSuffixForArray(Object value, JsonGenerator g) throws IOException {
    _writeLegacySuffix(g, typeId(value, JsonToken.START_ARRAY));
  }
  
  @Deprecated
  public void writeTypePrefixForScalar(Object value, JsonGenerator g, Class<?> type) throws IOException {
    writeTypePrefix(g, typeId(value, type, JsonToken.VALUE_STRING));
  }
  
  @Deprecated
  public void writeTypePrefixForObject(Object value, JsonGenerator g, Class<?> type) throws IOException {
    writeTypePrefix(g, typeId(value, type, JsonToken.START_OBJECT));
  }
  
  @Deprecated
  public void writeTypePrefixForArray(Object value, JsonGenerator g, Class<?> type) throws IOException {
    writeTypePrefix(g, typeId(value, type, JsonToken.START_ARRAY));
  }
  
  @Deprecated
  public void writeCustomTypePrefixForScalar(Object value, JsonGenerator g, String typeId) throws IOException {
    writeTypePrefix(g, typeId(value, JsonToken.VALUE_STRING, typeId));
  }
  
  @Deprecated
  public void writeCustomTypePrefixForObject(Object value, JsonGenerator g, String typeId) throws IOException {
    writeTypePrefix(g, typeId(value, JsonToken.START_OBJECT, typeId));
  }
  
  @Deprecated
  public void writeCustomTypePrefixForArray(Object value, JsonGenerator g, String typeId) throws IOException {
    writeTypePrefix(g, typeId(value, JsonToken.START_ARRAY, typeId));
  }
  
  @Deprecated
  public void writeCustomTypeSuffixForScalar(Object value, JsonGenerator g, String typeId) throws IOException {
    _writeLegacySuffix(g, typeId(value, JsonToken.VALUE_STRING, typeId));
  }
  
  @Deprecated
  public void writeCustomTypeSuffixForObject(Object value, JsonGenerator g, String typeId) throws IOException {
    _writeLegacySuffix(g, typeId(value, JsonToken.START_OBJECT, typeId));
  }
  
  @Deprecated
  public void writeCustomTypeSuffixForArray(Object value, JsonGenerator g, String typeId) throws IOException {
    _writeLegacySuffix(g, typeId(value, JsonToken.START_ARRAY, typeId));
  }
  
  protected final void _writeLegacySuffix(JsonGenerator g, WritableTypeId typeId) throws IOException {
    typeId.wrapperWritten = !g.canWriteTypeId();
    writeTypeSuffix(g, typeId);
  }
}
