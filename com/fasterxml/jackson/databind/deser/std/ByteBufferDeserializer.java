package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.ByteBufferBackedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class ByteBufferDeserializer extends StdScalarDeserializer<ByteBuffer> {
  private static final long serialVersionUID = 1L;
  
  protected ByteBufferDeserializer() {
    super(ByteBuffer.class);
  }
  
  public LogicalType logicalType() {
    return LogicalType.Binary;
  }
  
  public ByteBuffer deserialize(JsonParser parser, DeserializationContext cx) throws IOException {
    byte[] b = parser.getBinaryValue();
    return ByteBuffer.wrap(b);
  }
  
  public ByteBuffer deserialize(JsonParser jp, DeserializationContext ctxt, ByteBuffer intoValue) throws IOException {
    ByteBufferBackedOutputStream byteBufferBackedOutputStream = new ByteBufferBackedOutputStream(intoValue);
    jp.readBinaryValue(ctxt.getBase64Variant(), (OutputStream)byteBufferBackedOutputStream);
    byteBufferBackedOutputStream.close();
    return intoValue;
  }
}
