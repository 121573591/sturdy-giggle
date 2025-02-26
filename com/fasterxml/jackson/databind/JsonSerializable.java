package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public interface JsonSerializable {
  void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) throws IOException;
  
  void serializeWithType(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) throws IOException;
  
  public static abstract class Base implements JsonSerializable {
    public boolean isEmpty(SerializerProvider serializers) {
      return false;
    }
  }
}
