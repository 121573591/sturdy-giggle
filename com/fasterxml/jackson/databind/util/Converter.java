package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

public interface Converter<IN, OUT> {
  OUT convert(IN paramIN);
  
  JavaType getInputType(TypeFactory paramTypeFactory);
  
  JavaType getOutputType(TypeFactory paramTypeFactory);
  
  public static abstract class None implements Converter<Object, Object> {}
}
