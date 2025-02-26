package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

abstract class IdentityEqualityType extends TypeBase {
  private static final long serialVersionUID = 1L;
  
  protected IdentityEqualityType(Class<?> raw, TypeBindings bindings, JavaType superClass, JavaType[] superInts, int hash, Object valueHandler, Object typeHandler, boolean asStatic) {
    super(raw, bindings, superClass, superInts, hash, valueHandler, typeHandler, asStatic);
  }
  
  public final boolean equals(Object o) {
    return (o == this);
  }
  
  public final int hashCode() {
    return System.identityHashCode(this);
  }
}
