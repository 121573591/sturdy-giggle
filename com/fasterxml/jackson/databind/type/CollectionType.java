package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.lang.reflect.TypeVariable;

public final class CollectionType extends CollectionLikeType {
  private static final long serialVersionUID = 1L;
  
  private CollectionType(Class<?> collT, TypeBindings bindings, JavaType superClass, JavaType[] superInts, JavaType elemT, Object valueHandler, Object typeHandler, boolean asStatic) {
    super(collT, bindings, superClass, superInts, elemT, valueHandler, typeHandler, asStatic);
  }
  
  protected CollectionType(TypeBase base, JavaType elemT) {
    super(base, elemT);
  }
  
  public static CollectionType construct(Class<?> rawType, TypeBindings bindings, JavaType superClass, JavaType[] superInts, JavaType elemT) {
    return new CollectionType(rawType, bindings, superClass, superInts, elemT, null, null, false);
  }
  
  @Deprecated
  public static CollectionType construct(Class<?> rawType, JavaType elemT) {
    TypeBindings bindings;
    TypeVariable[] arrayOfTypeVariable = (TypeVariable[])rawType.getTypeParameters();
    if (arrayOfTypeVariable == null || arrayOfTypeVariable.length != 1) {
      bindings = TypeBindings.emptyBindings();
    } else {
      bindings = TypeBindings.create(rawType, elemT);
    } 
    return new CollectionType(rawType, bindings, 
        
        _bogusSuperClass(rawType), null, elemT, null, null, false);
  }
  
  public JavaType withContentType(JavaType contentType) {
    if (this._elementType == contentType)
      return this; 
    return new CollectionType(this._class, this._bindings, this._superClass, this._superInterfaces, contentType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public CollectionType withTypeHandler(Object h) {
    return new CollectionType(this._class, this._bindings, this._superClass, this._superInterfaces, this._elementType, this._valueHandler, h, this._asStatic);
  }
  
  public CollectionType withContentTypeHandler(Object h) {
    return new CollectionType(this._class, this._bindings, this._superClass, this._superInterfaces, this._elementType
        .withTypeHandler(h), this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public CollectionType withValueHandler(Object h) {
    return new CollectionType(this._class, this._bindings, this._superClass, this._superInterfaces, this._elementType, h, this._typeHandler, this._asStatic);
  }
  
  public CollectionType withContentValueHandler(Object h) {
    return new CollectionType(this._class, this._bindings, this._superClass, this._superInterfaces, this._elementType
        .withValueHandler(h), this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public CollectionType withStaticTyping() {
    if (this._asStatic)
      return this; 
    return new CollectionType(this._class, this._bindings, this._superClass, this._superInterfaces, this._elementType
        .withStaticTyping(), this._valueHandler, this._typeHandler, true);
  }
  
  public JavaType refine(Class<?> rawType, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
    return new CollectionType(rawType, bindings, superClass, superInterfaces, this._elementType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public String toString() {
    return "[collection type; class " + this._class.getName() + ", contains " + this._elementType + "]";
  }
}
