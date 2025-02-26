package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteBufferSerializer extends StdScalarSerializer<ByteBuffer> {
  public ByteBufferSerializer() {
    super(ByteBuffer.class);
  }
  
  public void serialize(ByteBuffer bbuf, JsonGenerator gen, SerializerProvider provider) throws IOException {
    if (bbuf.hasArray()) {
      int pos = bbuf.position();
      gen.writeBinary(bbuf.array(), bbuf.arrayOffset() + pos, bbuf.limit() - pos);
      return;
    } 
    ByteBuffer copy = bbuf.asReadOnlyBuffer();
    ByteBufferBackedInputStream byteBufferBackedInputStream = new ByteBufferBackedInputStream(copy);
    gen.writeBinary((InputStream)byteBufferBackedInputStream, copy.remaining());
    byteBufferBackedInputStream.close();
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
    JsonArrayFormatVisitor v2 = visitor.expectArrayFormat(typeHint);
    if (v2 != null)
      v2.itemsFormat(JsonFormatTypes.INTEGER); 
  }
}
