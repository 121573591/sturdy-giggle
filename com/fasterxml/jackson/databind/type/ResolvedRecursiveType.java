package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public class ResolvedRecursiveType extends IdentityEqualityType {
  private static final long serialVersionUID = 1L;
  
  protected JavaType _referencedType;
  
  public ResolvedRecursiveType(Class<?> erasedType, TypeBindings bindings) {
    super(erasedType, bindings, (JavaType)null, (JavaType[])null, 0, (Object)null, (Object)null, false);
  }
  
  public void setReference(JavaType ref) {
    if (this._referencedType != null)
      throw new IllegalStateException("Trying to re-set self reference; old value = " + this._referencedType + ", new = " + ref); 
    this._referencedType = ref;
  }
  
  public JavaType getSuperClass() {
    if (this._referencedType != null)
      return this._referencedType.getSuperClass(); 
    return super.getSuperClass();
  }
  
  public JavaType getSelfReferencedType() {
    return this._referencedType;
  }
  
  public TypeBindings getBindings() {
    if (this._referencedType != null)
      return this._referencedType.getBindings(); 
    return super.getBindings();
  }
  
  public StringBuilder getGenericSignature(StringBuilder sb) {
    if (this._referencedType != null)
      return this._referencedType.getErasedSignature(sb); 
    return sb.append("?");
  }
  
  public StringBuilder getErasedSignature(StringBuilder sb) {
    if (this._referencedType != null)
      return this._referencedType.getErasedSignature(sb); 
    return sb;
  }
  
  public JavaType withContentType(JavaType contentType) {
    return this;
  }
  
  public JavaType withTypeHandler(Object h) {
    return this;
  }
  
  public JavaType withContentTypeHandler(Object h) {
    return this;
  }
  
  public JavaType withValueHandler(Object h) {
    return this;
  }
  
  public JavaType withContentValueHandler(Object h) {
    return this;
  }
  
  public JavaType withStaticTyping() {
    return this;
  }
  
  public JavaType refine(Class<?> rawType, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
    return null;
  }
  
  public boolean isContainerType() {
    return false;
  }
  
  public String toString() {
    StringBuilder sb = (new StringBuilder(40)).append("[recursive type; ");
    if (this._referencedType == null) {
      sb.append("UNRESOLVED");
    } else {
      sb.append(this._referencedType.getRawClass().getName());
    } 
    return sb.toString();
  }
}
