package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public class PlaceholderForType extends IdentityEqualityType {
  private static final long serialVersionUID = 1L;
  
  protected final int _ordinal;
  
  protected JavaType _actualType;
  
  public PlaceholderForType(int ordinal) {
    super(Object.class, TypeBindings.emptyBindings(), 
        TypeFactory.unknownType(), null, 1, null, null, false);
    this._ordinal = ordinal;
  }
  
  public JavaType actualType() {
    return this._actualType;
  }
  
  public void actualType(JavaType t) {
    this._actualType = t;
  }
  
  protected String buildCanonicalName() {
    return toString();
  }
  
  public StringBuilder getGenericSignature(StringBuilder sb) {
    return getErasedSignature(sb);
  }
  
  public StringBuilder getErasedSignature(StringBuilder sb) {
    sb.append('$').append(this._ordinal + 1);
    return sb;
  }
  
  public JavaType withTypeHandler(Object h) {
    return _unsupported();
  }
  
  public JavaType withContentTypeHandler(Object h) {
    return _unsupported();
  }
  
  public JavaType withValueHandler(Object h) {
    return _unsupported();
  }
  
  public JavaType withContentValueHandler(Object h) {
    return _unsupported();
  }
  
  public JavaType withContentType(JavaType contentType) {
    return _unsupported();
  }
  
  public JavaType withStaticTyping() {
    return _unsupported();
  }
  
  public JavaType refine(Class<?> rawType, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
    return _unsupported();
  }
  
  public boolean isContainerType() {
    return false;
  }
  
  public String toString() {
    return getErasedSignature(new StringBuilder()).toString();
  }
  
  private <T> T _unsupported() {
    throw new UnsupportedOperationException("Operation should not be attempted on " + getClass().getName());
  }
}
