package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class FailingSerializer extends StdSerializer<Object> {
  protected final String _msg;
  
  public FailingSerializer(String msg) {
    super(Object.class);
    this._msg = msg;
  }
  
  public void serialize(Object value, JsonGenerator g, SerializerProvider ctxt) throws IOException {
    ctxt.reportMappingProblem(this._msg, new Object[0]);
  }
}
