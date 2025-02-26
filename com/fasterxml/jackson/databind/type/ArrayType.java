package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.databind.JavaType;
import java.lang.reflect.Array;

public final class ArrayType extends TypeBase {
  private static final long serialVersionUID = 1L;
  
  protected final JavaType _componentType;
  
  protected final Object _emptyArray;
  
  protected ArrayType(JavaType componentType, TypeBindings bindings, Object emptyInstance, Object valueHandler, Object typeHandler, boolean asStatic) {
    super(emptyInstance.getClass(), bindings, (JavaType)null, (JavaType[])null, componentType
        .hashCode(), valueHandler, typeHandler, asStatic);
    this._componentType = componentType;
    this._emptyArray = emptyInstance;
  }
  
  public static ArrayType construct(JavaType componentType, TypeBindings bindings) {
    return construct(componentType, bindings, (Object)null, (Object)null);
  }
  
  public static ArrayType construct(JavaType componentType, TypeBindings bindings, Object valueHandler, Object typeHandler) {
    Object emptyInstance = Array.newInstance(componentType.getRawClass(), 0);
    return new ArrayType(componentType, bindings, emptyInstance, valueHandler, typeHandler, false);
  }
  
  public JavaType withContentType(JavaType contentType) {
    Object emptyInstance = Array.newInstance(contentType.getRawClass(), 0);
    return new ArrayType(contentType, this._bindings, emptyInstance, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public ArrayType withTypeHandler(Object h) {
    if (h == this._typeHandler)
      return this; 
    return new ArrayType(this._componentType, this._bindings, this._emptyArray, this._valueHandler, h, this._asStatic);
  }
  
  public ArrayType withContentTypeHandler(Object h) {
    if (h == this._componentType.getTypeHandler())
      return this; 
    return new ArrayType(this._componentType.withTypeHandler(h), this._bindings, this._emptyArray, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public ArrayType withValueHandler(Object h) {
    if (h == this._valueHandler)
      return this; 
    return new ArrayType(this._componentType, this._bindings, this._emptyArray, h, this._typeHandler, this._asStatic);
  }
  
  public ArrayType withContentValueHandler(Object h) {
    if (h == this._componentType.getValueHandler())
      return this; 
    return new ArrayType(this._componentType.withValueHandler(h), this._bindings, this._emptyArray, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public ArrayType withStaticTyping() {
    if (this._asStatic)
      return this; 
    return new ArrayType(this._componentType.withStaticTyping(), this._bindings, this._emptyArray, this._valueHandler, this._typeHandler, true);
  }
  
  public JavaType refine(Class<?> contentClass, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
    return null;
  }
  
  public boolean isArrayType() {
    return true;
  }
  
  public boolean isAbstract() {
    return false;
  }
  
  public boolean isConcrete() {
    return true;
  }
  
  public boolean hasGenericTypes() {
    return this._componentType.hasGenericTypes();
  }
  
  public boolean isContainerType() {
    return true;
  }
  
  public JavaType getContentType() {
    return this._componentType;
  }
  
  public Object getContentValueHandler() {
    return this._componentType.getValueHandler();
  }
  
  public Object getContentTypeHandler() {
    return this._componentType.getTypeHandler();
  }
  
  public boolean hasHandlers() {
    return (super.hasHandlers() || this._componentType.hasHandlers());
  }
  
  public StringBuilder getGenericSignature(StringBuilder sb) {
    sb.append('[');
    return this._componentType.getGenericSignature(sb);
  }
  
  public StringBuilder getErasedSignature(StringBuilder sb) {
    sb.append('[');
    return this._componentType.getErasedSignature(sb);
  }
  
  public Object[] getEmptyArray() {
    return (Object[])this._emptyArray;
  }
  
  public String toString() {
    return "[array type, component type: " + this._componentType + "]";
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (o == null)
      return false; 
    if (o.getClass() != getClass())
      return false; 
    ArrayType other = (ArrayType)o;
    return this._componentType.equals(other._componentType);
  }
}
