package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import java.io.IOException;
import java.nio.file.Path;

public class NioPathSerializer extends StdScalarSerializer<Path> {
  private static final long serialVersionUID = 1L;
  
  public NioPathSerializer() {
    super(Path.class);
  }
  
  public void serialize(Path value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeString(value.toUri().toString());
  }
  
  public void serializeWithType(Path value, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
    WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer
        .typeId(value, Path.class, JsonToken.VALUE_STRING));
    serialize(value, g, provider);
    typeSer.writeTypeSuffix(g, typeIdDef);
  }
}
