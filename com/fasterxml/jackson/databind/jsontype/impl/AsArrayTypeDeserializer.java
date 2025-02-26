package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;

public class AsArrayTypeDeserializer extends TypeDeserializerBase implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public AsArrayTypeDeserializer(JavaType bt, TypeIdResolver idRes, String typePropertyName, boolean typeIdVisible, JavaType defaultImpl) {
    super(bt, idRes, typePropertyName, typeIdVisible, defaultImpl);
  }
  
  public AsArrayTypeDeserializer(AsArrayTypeDeserializer src, BeanProperty property) {
    super(src, property);
  }
  
  public TypeDeserializer forProperty(BeanProperty prop) {
    return (prop == this._property) ? this : new AsArrayTypeDeserializer(this, prop);
  }
  
  public JsonTypeInfo.As getTypeInclusion() {
    return JsonTypeInfo.As.WRAPPER_ARRAY;
  }
  
  public Object deserializeTypedFromArray(JsonParser jp, DeserializationContext ctxt) throws IOException {
    return _deserialize(jp, ctxt);
  }
  
  public Object deserializeTypedFromObject(JsonParser jp, DeserializationContext ctxt) throws IOException {
    return _deserialize(jp, ctxt);
  }
  
  public Object deserializeTypedFromScalar(JsonParser jp, DeserializationContext ctxt) throws IOException {
    return _deserialize(jp, ctxt);
  }
  
  public Object deserializeTypedFromAny(JsonParser jp, DeserializationContext ctxt) throws IOException {
    return _deserialize(jp, ctxt);
  }
  
  protected Object _deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    JsonParserSequence jsonParserSequence;
    if (p.canReadTypeId()) {
      Object object = p.getTypeId();
      if (object != null)
        return _deserializeWithNativeTypeId(p, ctxt, object); 
    } 
    boolean hadStartArray = p.isExpectedStartArrayToken();
    String typeId = _locateTypeId(p, ctxt);
    JsonDeserializer<Object> deser = _findDeserializer(ctxt, typeId);
    if (this._typeIdVisible && 
      
      !_usesExternalId() && p
      .hasToken(JsonToken.START_OBJECT)) {
      TokenBuffer tb = ctxt.bufferForInputBuffering(p);
      tb.writeStartObject();
      tb.writeFieldName(this._typePropertyName);
      tb.writeString(typeId);
      p.clearCurrentToken();
      jsonParserSequence = JsonParserSequence.createFlattened(false, tb.asParser(p), p);
      jsonParserSequence.nextToken();
    } 
    if (hadStartArray && jsonParserSequence.currentToken() == JsonToken.END_ARRAY)
      return deser.getNullValue(ctxt); 
    Object value = deser.deserialize((JsonParser)jsonParserSequence, ctxt);
    if (hadStartArray && jsonParserSequence.nextToken() != JsonToken.END_ARRAY)
      ctxt.reportWrongTokenException(baseType(), JsonToken.END_ARRAY, "expected closing `JsonToken.END_ARRAY` after type information and deserialized value", new Object[0]); 
    return value;
  }
  
  protected String _locateTypeId(JsonParser p, DeserializationContext ctxt) throws IOException {
    if (!p.isExpectedStartArrayToken()) {
      if (this._defaultImpl != null)
        return this._idResolver.idFromBaseType(); 
      ctxt.reportWrongTokenException(baseType(), JsonToken.START_ARRAY, "need Array value to contain `As.WRAPPER_ARRAY` type information for class " + 
          baseTypeName(), new Object[0]);
      return null;
    } 
    JsonToken t = p.nextToken();
    if (t == JsonToken.VALUE_STRING || (t != null && t
      
      .isScalarValue())) {
      String result = p.getText();
      p.nextToken();
      return result;
    } 
    ctxt.reportWrongTokenException(baseType(), JsonToken.VALUE_STRING, "need String, Number of Boolean value that contains type id (for subtype of %s)", new Object[] { baseTypeName() });
    return null;
  }
  
  protected boolean _usesExternalId() {
    return false;
  }
}
