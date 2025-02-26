package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.lang.reflect.Type;

public abstract class StdScalarSerializer<T> extends StdSerializer<T> {
  protected StdScalarSerializer(Class<T> t) {
    super(t);
  }
  
  protected StdScalarSerializer(Class<?> t, boolean dummy) {
    super((Class)t);
  }
  
  protected StdScalarSerializer(StdScalarSerializer<?> src) {
    super(src);
  }
  
  public void serializeWithType(T value, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
    WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer
        .typeId(value, JsonToken.VALUE_STRING));
    serialize(value, g, provider);
    typeSer.writeTypeSuffix(g, typeIdDef);
  }
  
  @Deprecated
  public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
    return (JsonNode)createSchemaNode("string", true);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
    visitStringFormat(visitor, typeHint);
  }
}
