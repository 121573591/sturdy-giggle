package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public class ToStringSerializer extends ToStringSerializerBase {
  public static final ToStringSerializer instance = new ToStringSerializer();
  
  public ToStringSerializer() {
    super(Object.class);
  }
  
  public ToStringSerializer(Class<?> handledType) {
    super(handledType);
  }
  
  public final String valueToString(Object value) {
    return value.toString();
  }
}
