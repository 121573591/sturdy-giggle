package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.util.Collection;
import java.util.Map;

public class SimpleType extends TypeBase {
  private static final long serialVersionUID = 1L;
  
  protected SimpleType(Class<?> cls) {
    this(cls, TypeBindings.emptyBindings(), (JavaType)null, (JavaType[])null);
  }
  
  protected SimpleType(Class<?> cls, TypeBindings bindings, JavaType superClass, JavaType[] superInts) {
    this(cls, bindings, superClass, superInts, (Object)null, (Object)null, false);
  }
  
  protected SimpleType(TypeBase base) {
    super(base);
  }
  
  protected SimpleType(Class<?> cls, TypeBindings bindings, JavaType superClass, JavaType[] superInts, Object valueHandler, Object typeHandler, boolean asStatic) {
    super(cls, bindings, superClass, superInts, ((bindings == null) ? 
        
        TypeBindings.emptyBindings() : bindings).hashCode(), valueHandler, typeHandler, asStatic);
  }
  
  protected SimpleType(Class<?> cls, TypeBindings bindings, JavaType superClass, JavaType[] superInts, int extraHash, Object valueHandler, Object typeHandler, boolean asStatic) {
    super(cls, bindings, superClass, superInts, extraHash, valueHandler, typeHandler, asStatic);
  }
  
  public static SimpleType constructUnsafe(Class<?> raw) {
    return new SimpleType(raw, null, null, null, null, null, false);
  }
  
  @Deprecated
  public static SimpleType construct(Class<?> cls) {
    if (Map.class.isAssignableFrom(cls))
      throw new IllegalArgumentException("Cannot construct SimpleType for a Map (class: " + cls.getName() + ")"); 
    if (Collection.class.isAssignableFrom(cls))
      throw new IllegalArgumentException("Cannot construct SimpleType for a Collection (class: " + cls.getName() + ")"); 
    if (cls.isArray())
      throw new IllegalArgumentException("Cannot construct SimpleType for an array (class: " + cls.getName() + ")"); 
    TypeBindings b = TypeBindings.emptyBindings();
    return new SimpleType(cls, b, 
        _buildSuperClass(cls.getSuperclass(), b), null, null, null, false);
  }
  
  public JavaType withContentType(JavaType contentType) {
    throw new IllegalArgumentException("Simple types have no content types; cannot call withContentType()");
  }
  
  public SimpleType withTypeHandler(Object h) {
    if (this._typeHandler == h)
      return this; 
    return new SimpleType(this._class, this._bindings, this._superClass, this._superInterfaces, this._valueHandler, h, this._asStatic);
  }
  
  public JavaType withContentTypeHandler(Object h) {
    throw new IllegalArgumentException("Simple types have no content types; cannot call withContenTypeHandler()");
  }
  
  public SimpleType withValueHandler(Object h) {
    if (h == this._valueHandler)
      return this; 
    return new SimpleType(this._class, this._bindings, this._superClass, this._superInterfaces, h, this._typeHandler, this._asStatic);
  }
  
  public SimpleType withContentValueHandler(Object h) {
    throw new IllegalArgumentException("Simple types have no content types; cannot call withContenValueHandler()");
  }
  
  public SimpleType withStaticTyping() {
    return this._asStatic ? this : new SimpleType(this._class, this._bindings, this._superClass, this._superInterfaces, this._valueHandler, this._typeHandler, true);
  }
  
  public JavaType refine(Class<?> rawType, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
    return null;
  }
  
  protected String buildCanonicalName() {
    StringBuilder sb = new StringBuilder();
    sb.append(this._class.getName());
    int count = this._bindings.size();
    if (count > 0 && _hasNTypeParameters(count)) {
      sb.append('<');
      for (int i = 0; i < count; i++) {
        JavaType t = containedType(i);
        if (i > 0)
          sb.append(','); 
        sb.append(t.toCanonical());
      } 
      sb.append('>');
    } 
    return sb.toString();
  }
  
  public boolean isContainerType() {
    return false;
  }
  
  public boolean hasContentType() {
    return false;
  }
  
  public StringBuilder getErasedSignature(StringBuilder sb) {
    return _classSignature(this._class, sb, true);
  }
  
  public StringBuilder getGenericSignature(StringBuilder sb) {
    _classSignature(this._class, sb, false);
    int count = this._bindings.size();
    if (count > 0) {
      sb.append('<');
      for (int i = 0; i < count; i++)
        sb = containedType(i).getGenericSignature(sb); 
      sb.append('>');
    } 
    sb.append(';');
    return sb;
  }
  
  private static JavaType _buildSuperClass(Class<?> superClass, TypeBindings b) {
    if (superClass == null)
      return null; 
    if (superClass == Object.class)
      return TypeFactory.unknownType(); 
    JavaType superSuper = _buildSuperClass(superClass.getSuperclass(), b);
    return new SimpleType(superClass, b, superSuper, null, null, null, false);
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder(40);
    sb.append("[simple type, class ").append(buildCanonicalName()).append(']');
    return sb.toString();
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (o == null)
      return false; 
    if (o.getClass() != getClass())
      return false; 
    SimpleType other = (SimpleType)o;
    if (other._class != this._class)
      return false; 
    TypeBindings b1 = this._bindings;
    TypeBindings b2 = other._bindings;
    return b1.equals(b2);
  }
}
