package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.TimeZone;

public class TimeZoneSerializer extends StdScalarSerializer<TimeZone> {
  public TimeZoneSerializer() {
    super(TimeZone.class);
  }
  
  public void serialize(TimeZone value, JsonGenerator g, SerializerProvider provider) throws IOException {
    g.writeString(value.getID());
  }
  
  public void serializeWithType(TimeZone value, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
    WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer
        .typeId(value, TimeZone.class, JsonToken.VALUE_STRING));
    serialize(value, g, provider);
    typeSer.writeTypeSuffix(g, typeIdDef);
  }
}
