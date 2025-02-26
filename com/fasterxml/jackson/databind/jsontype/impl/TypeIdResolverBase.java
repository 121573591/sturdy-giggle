package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;

public abstract class TypeIdResolverBase implements TypeIdResolver {
  protected final TypeFactory _typeFactory;
  
  protected final JavaType _baseType;
  
  protected TypeIdResolverBase() {
    this(null, null);
  }
  
  protected TypeIdResolverBase(JavaType baseType, TypeFactory typeFactory) {
    this._baseType = baseType;
    this._typeFactory = typeFactory;
  }
  
  public void init(JavaType bt) {}
  
  public String idFromBaseType() {
    return idFromValueAndType(null, this._baseType.getRawClass());
  }
  
  public JavaType typeFromId(DatabindContext context, String id) throws IOException {
    throw new IllegalStateException("Sub-class " + getClass().getName() + " MUST implement `typeFromId(DatabindContext,String)");
  }
  
  public String getDescForKnownTypeIds() {
    return null;
  }
}
