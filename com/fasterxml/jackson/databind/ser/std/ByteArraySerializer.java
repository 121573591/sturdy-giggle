package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.lang.reflect.Type;

@JacksonStdImpl
public class ByteArraySerializer extends StdSerializer<byte[]> {
  private static final long serialVersionUID = 1L;
  
  public ByteArraySerializer() {
    super((Class)byte[].class);
  }
  
  public boolean isEmpty(SerializerProvider prov, byte[] value) {
    return (value.length == 0);
  }
  
  public void serialize(byte[] value, JsonGenerator g, SerializerProvider provider) throws IOException {
    g.writeBinary(provider.getConfig().getBase64Variant(), value, 0, value.length);
  }
  
  public void serializeWithType(byte[] value, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
    WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer
        .typeId(value, JsonToken.VALUE_EMBEDDED_OBJECT));
    g.writeBinary(provider.getConfig().getBase64Variant(), value, 0, value.length);
    typeSer.writeTypeSuffix(g, typeIdDef);
  }
  
  @Deprecated
  public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
    ObjectNode o = createSchemaNode("array", true);
    ObjectNode itemSchema = createSchemaNode("byte");
    return o.set("items", (JsonNode)itemSchema);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
    JsonArrayFormatVisitor v2 = visitor.expectArrayFormat(typeHint);
    if (v2 != null)
      v2.itemsFormat(JsonFormatTypes.INTEGER); 
  }
}
