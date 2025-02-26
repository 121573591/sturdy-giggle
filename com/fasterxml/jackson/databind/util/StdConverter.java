package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

public abstract class StdConverter<IN, OUT> implements Converter<IN, OUT> {
  public abstract OUT convert(IN paramIN);
  
  public JavaType getInputType(TypeFactory typeFactory) {
    return _findConverterType(typeFactory).containedType(0);
  }
  
  public JavaType getOutputType(TypeFactory typeFactory) {
    return _findConverterType(typeFactory).containedType(1);
  }
  
  protected JavaType _findConverterType(TypeFactory tf) {
    JavaType thisType = tf.constructType(getClass());
    JavaType convType = thisType.findSuperType(Converter.class);
    if (convType == null || convType.containedTypeCount() < 2)
      throw new IllegalStateException("Cannot find OUT type parameter for Converter of type " + getClass().getName()); 
    return convType;
  }
}
