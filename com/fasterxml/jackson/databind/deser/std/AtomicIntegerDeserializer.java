package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.type.LogicalType;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDeserializer extends StdScalarDeserializer<AtomicInteger> {
  private static final long serialVersionUID = 1L;
  
  public AtomicIntegerDeserializer() {
    super(AtomicInteger.class);
  }
  
  public AtomicInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    if (p.isExpectedNumberIntToken())
      return new AtomicInteger(p.getIntValue()); 
    Integer I = _parseInteger(p, ctxt, AtomicInteger.class);
    return (I == null) ? null : new AtomicInteger(I.intValue());
  }
  
  public LogicalType logicalType() {
    return LogicalType.Integer;
  }
  
  public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
    return new AtomicInteger();
  }
}
