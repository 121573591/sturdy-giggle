package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.lang.reflect.Type;

@JacksonStdImpl
public class ToEmptyObjectSerializer extends StdSerializer<Object> {
  protected ToEmptyObjectSerializer(Class<?> raw) {
    super(raw, false);
  }
  
  public ToEmptyObjectSerializer(JavaType type) {
    super(type);
  }
  
  public void serialize(Object value, JsonGenerator gen, SerializerProvider ctxt) throws IOException {
    gen.writeStartObject(value, 0);
    gen.writeEndObject();
  }
  
  public void serializeWithType(Object value, JsonGenerator gen, SerializerProvider ctxt, TypeSerializer typeSer) throws IOException {
    WritableTypeId typeIdDef = typeSer.writeTypePrefix(gen, typeSer
        .typeId(value, JsonToken.START_OBJECT));
    typeSer.writeTypeSuffix(gen, typeIdDef);
  }
  
  public boolean isEmpty(SerializerProvider provider, Object value) {
    return true;
  }
  
  @Deprecated
  public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
    return null;
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
    visitor.expectObjectFormat(typeHint);
  }
}
