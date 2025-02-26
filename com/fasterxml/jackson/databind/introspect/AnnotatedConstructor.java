package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.Objects;

public final class AnnotatedConstructor extends AnnotatedWithParams {
  private static final long serialVersionUID = 1L;
  
  protected final Constructor<?> _constructor;
  
  protected Serialization _serialization;
  
  public AnnotatedConstructor(TypeResolutionContext ctxt, Constructor<?> constructor, AnnotationMap classAnn, AnnotationMap[] paramAnn) {
    super(ctxt, classAnn, paramAnn);
    this._constructor = Objects.<Constructor>requireNonNull(constructor);
  }
  
  protected AnnotatedConstructor(Serialization ser) {
    super((TypeResolutionContext)null, (AnnotationMap)null, (AnnotationMap[])null);
    this._constructor = null;
    this._serialization = ser;
  }
  
  public AnnotatedConstructor withAnnotations(AnnotationMap ann) {
    return new AnnotatedConstructor(this._typeContext, this._constructor, ann, this._paramAnnotations);
  }
  
  public Constructor<?> getAnnotated() {
    return this._constructor;
  }
  
  public int getModifiers() {
    return this._constructor.getModifiers();
  }
  
  public String getName() {
    return this._constructor.getName();
  }
  
  public JavaType getType() {
    return this._typeContext.resolveType(getRawType());
  }
  
  public Class<?> getRawType() {
    return this._constructor.getDeclaringClass();
  }
  
  public int getParameterCount() {
    return this._constructor.getParameterCount();
  }
  
  public Class<?> getRawParameterType(int index) {
    Class<?>[] types = this._constructor.getParameterTypes();
    return (index >= types.length) ? null : types[index];
  }
  
  public JavaType getParameterType(int index) {
    Type[] types = this._constructor.getGenericParameterTypes();
    if (index >= types.length)
      return null; 
    return this._typeContext.resolveType(types[index]);
  }
  
  @Deprecated
  public Type getGenericParameterType(int index) {
    Type[] types = this._constructor.getGenericParameterTypes();
    if (index >= types.length)
      return null; 
    return types[index];
  }
  
  public final Object call() throws Exception {
    return this._constructor.newInstance((Object[])null);
  }
  
  public final Object call(Object[] args) throws Exception {
    return this._constructor.newInstance(args);
  }
  
  public final Object call1(Object arg) throws Exception {
    return this._constructor.newInstance(new Object[] { arg });
  }
  
  public Class<?> getDeclaringClass() {
    return this._constructor.getDeclaringClass();
  }
  
  public Member getMember() {
    return this._constructor;
  }
  
  public void setValue(Object pojo, Object value) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Cannot call setValue() on constructor of " + 
        getDeclaringClass().getName());
  }
  
  public Object getValue(Object pojo) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Cannot call getValue() on constructor of " + 
        getDeclaringClass().getName());
  }
  
  public String toString() {
    int argCount = this._constructor.getParameterCount();
    return String.format("[constructor for %s (%d arg%s), annotations: %s", new Object[] { ClassUtil.nameOf(this._constructor.getDeclaringClass()), Integer.valueOf(argCount), (argCount == 1) ? "" : "s", this._annotations });
  }
  
  public int hashCode() {
    return Objects.hashCode(this._constructor);
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!ClassUtil.hasClass(o, getClass()))
      return false; 
    AnnotatedConstructor other = (AnnotatedConstructor)o;
    return Objects.equals(this._constructor, other._constructor);
  }
  
  Object writeReplace() {
    return new AnnotatedConstructor(new Serialization(this._constructor));
  }
  
  Object readResolve() {
    Class<?> clazz = this._serialization.clazz;
    try {
      Constructor<?> ctor = clazz.getDeclaredConstructor(this._serialization.args);
      if (!ctor.isAccessible())
        ClassUtil.checkAndFixAccess(ctor, false); 
      return new AnnotatedConstructor(null, ctor, null, null);
    } catch (Exception e) {
      throw new IllegalArgumentException("Could not find constructor with " + this._serialization.args.length + " args from Class '" + clazz
          .getName());
    } 
  }
  
  private static final class Serialization implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected Class<?> clazz;
    
    protected Class<?>[] args;
    
    public Serialization(Constructor<?> ctor) {
      this.clazz = ctor.getDeclaringClass();
      this.args = ctor.getParameterTypes();
    }
  }
}
