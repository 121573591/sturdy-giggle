package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

public class FileSerializer extends StdScalarSerializer<File> {
  public FileSerializer() {
    super(File.class);
  }
  
  public void serialize(File value, JsonGenerator g, SerializerProvider provider) throws IOException {
    g.writeString(value.getAbsolutePath());
  }
  
  @Deprecated
  public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
    return (JsonNode)createSchemaNode("string", true);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
    visitStringFormat(visitor, typeHint);
  }
}
