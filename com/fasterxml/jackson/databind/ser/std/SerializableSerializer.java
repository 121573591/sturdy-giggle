package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

@JacksonStdImpl
public class SerializableSerializer extends StdSerializer<JsonSerializable> {
  public static final SerializableSerializer instance = new SerializableSerializer();
  
  protected SerializableSerializer() {
    super(JsonSerializable.class);
  }
  
  public boolean isEmpty(SerializerProvider serializers, JsonSerializable value) {
    if (value instanceof JsonSerializable.Base)
      return ((JsonSerializable.Base)value).isEmpty(serializers); 
    return false;
  }
  
  public void serialize(JsonSerializable value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    value.serialize(gen, serializers);
  }
  
  public final void serializeWithType(JsonSerializable value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
    value.serializeWithType(gen, serializers, typeSer);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
    visitor.expectAnyFormat(typeHint);
  }
}
