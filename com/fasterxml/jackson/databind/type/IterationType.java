package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.databind.JavaType;
import java.util.Objects;

public class IterationType extends SimpleType {
  private static final long serialVersionUID = 1L;
  
  protected final JavaType _iteratedType;
  
  protected IterationType(Class<?> cls, TypeBindings bindings, JavaType superClass, JavaType[] superInts, JavaType iteratedType, Object valueHandler, Object typeHandler, boolean asStatic) {
    super(cls, bindings, superClass, superInts, Objects.hashCode(iteratedType), valueHandler, typeHandler, asStatic);
    this._iteratedType = iteratedType;
  }
  
  protected IterationType(TypeBase base, JavaType iteratedType) {
    super(base);
    this._iteratedType = iteratedType;
  }
  
  public static IterationType upgradeFrom(JavaType baseType, JavaType iteratedType) {
    Objects.requireNonNull(iteratedType);
    if (baseType instanceof TypeBase)
      return new IterationType((TypeBase)baseType, iteratedType); 
    throw new IllegalArgumentException("Cannot upgrade from an instance of " + baseType.getClass());
  }
  
  public static IterationType construct(Class<?> cls, TypeBindings bindings, JavaType superClass, JavaType[] superInts, JavaType iteratedType) {
    return new IterationType(cls, bindings, superClass, superInts, iteratedType, null, null, false);
  }
  
  public JavaType withContentType(JavaType contentType) {
    if (this._iteratedType == contentType)
      return this; 
    return new IterationType(this._class, this._bindings, this._superClass, this._superInterfaces, contentType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public IterationType withTypeHandler(Object h) {
    if (h == this._typeHandler)
      return this; 
    return new IterationType(this._class, this._bindings, this._superClass, this._superInterfaces, this._iteratedType, this._valueHandler, h, this._asStatic);
  }
  
  public IterationType withContentTypeHandler(Object h) {
    if (h == this._iteratedType.getTypeHandler())
      return this; 
    return new IterationType(this._class, this._bindings, this._superClass, this._superInterfaces, this._iteratedType
        .withTypeHandler(h), this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public IterationType withValueHandler(Object h) {
    if (h == this._valueHandler)
      return this; 
    return new IterationType(this._class, this._bindings, this._superClass, this._superInterfaces, this._iteratedType, h, this._typeHandler, this._asStatic);
  }
  
  public IterationType withContentValueHandler(Object h) {
    if (h == this._iteratedType.getValueHandler())
      return this; 
    return new IterationType(this._class, this._bindings, this._superClass, this._superInterfaces, this._iteratedType
        .withValueHandler(h), this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public IterationType withStaticTyping() {
    if (this._asStatic)
      return this; 
    return new IterationType(this._class, this._bindings, this._superClass, this._superInterfaces, this._iteratedType
        .withStaticTyping(), this._valueHandler, this._typeHandler, true);
  }
  
  public JavaType refine(Class<?> rawType, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
    return new IterationType(rawType, this._bindings, superClass, superInterfaces, this._iteratedType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  protected String buildCanonicalName() {
    StringBuilder sb = new StringBuilder();
    sb.append(this._class.getName());
    if (this._iteratedType != null && _hasNTypeParameters(1)) {
      sb.append('<');
      sb.append(this._iteratedType.toCanonical());
      sb.append('>');
    } 
    return sb.toString();
  }
  
  public JavaType getContentType() {
    return this._iteratedType;
  }
  
  public boolean hasContentType() {
    return true;
  }
  
  public boolean isIterationType() {
    return true;
  }
  
  public StringBuilder getErasedSignature(StringBuilder sb) {
    return _classSignature(this._class, sb, true);
  }
  
  public StringBuilder getGenericSignature(StringBuilder sb) {
    _classSignature(this._class, sb, false);
    sb.append('<');
    sb = this._iteratedType.getGenericSignature(sb);
    sb.append(">;");
    return sb;
  }
}
