package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Member;

public class VirtualAnnotatedMember extends AnnotatedMember implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected final Class<?> _declaringClass;
  
  protected final JavaType _type;
  
  protected final String _name;
  
  public VirtualAnnotatedMember(TypeResolutionContext typeContext, Class<?> declaringClass, String name, JavaType type) {
    super(typeContext, null);
    this._declaringClass = declaringClass;
    this._type = type;
    this._name = name;
  }
  
  public Annotated withAnnotations(AnnotationMap fallback) {
    return this;
  }
  
  public Field getAnnotated() {
    return null;
  }
  
  public int getModifiers() {
    return 0;
  }
  
  public String getName() {
    return this._name;
  }
  
  public Class<?> getRawType() {
    return this._type.getRawClass();
  }
  
  public JavaType getType() {
    return this._type;
  }
  
  public Class<?> getDeclaringClass() {
    return this._declaringClass;
  }
  
  public Member getMember() {
    return null;
  }
  
  public void setValue(Object pojo, Object value) throws IllegalArgumentException {
    throw new IllegalArgumentException("Cannot set virtual property '" + this._name + "'");
  }
  
  public Object getValue(Object pojo) throws IllegalArgumentException {
    throw new IllegalArgumentException("Cannot get virtual property '" + this._name + "'");
  }
  
  public int getAnnotationCount() {
    return 0;
  }
  
  public int hashCode() {
    return this._name.hashCode();
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!ClassUtil.hasClass(o, getClass()))
      return false; 
    VirtualAnnotatedMember other = (VirtualAnnotatedMember)o;
    return (other._declaringClass == this._declaringClass && other._name
      .equals(this._name));
  }
  
  public String toString() {
    return "[virtual " + getFullName() + "]";
  }
}
