package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import java.io.IOException;

public abstract class StdScalarDeserializer<T> extends StdDeserializer<T> {
  private static final long serialVersionUID = 1L;
  
  protected StdScalarDeserializer(Class<?> vc) {
    super(vc);
  }
  
  protected StdScalarDeserializer(JavaType valueType) {
    super(valueType);
  }
  
  protected StdScalarDeserializer(StdScalarDeserializer<?> src) {
    super(src);
  }
  
  public LogicalType logicalType() {
    return LogicalType.OtherScalar;
  }
  
  public Boolean supportsUpdate(DeserializationConfig config) {
    return Boolean.FALSE;
  }
  
  public AccessPattern getNullAccessPattern() {
    return AccessPattern.ALWAYS_NULL;
  }
  
  public AccessPattern getEmptyAccessPattern() {
    return AccessPattern.CONSTANT;
  }
  
  public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
    return typeDeserializer.deserializeTypedFromScalar(p, ctxt);
  }
  
  public T deserialize(JsonParser p, DeserializationContext ctxt, T intoValue) throws IOException {
    ctxt.handleBadMerge(this);
    return (T)deserialize(p, ctxt);
  }
}
