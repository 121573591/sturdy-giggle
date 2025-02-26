package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Objects;

public final class AnnotatedMethod extends AnnotatedWithParams implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected final transient Method _method;
  
  protected Class<?>[] _paramClasses;
  
  protected Serialization _serialization;
  
  public AnnotatedMethod(TypeResolutionContext ctxt, Method method, AnnotationMap classAnn, AnnotationMap[] paramAnnotations) {
    super(ctxt, classAnn, paramAnnotations);
    if (method == null)
      throw new IllegalArgumentException("Cannot construct AnnotatedMethod with null Method"); 
    this._method = method;
  }
  
  protected AnnotatedMethod(Serialization ser) {
    super((TypeResolutionContext)null, (AnnotationMap)null, (AnnotationMap[])null);
    this._method = null;
    this._serialization = ser;
  }
  
  public AnnotatedMethod withAnnotations(AnnotationMap ann) {
    return new AnnotatedMethod(this._typeContext, this._method, ann, this._paramAnnotations);
  }
  
  public Method getAnnotated() {
    return this._method;
  }
  
  public int getModifiers() {
    return this._method.getModifiers();
  }
  
  public String getName() {
    return this._method.getName();
  }
  
  public JavaType getType() {
    return this._typeContext.resolveType(this._method.getGenericReturnType());
  }
  
  public Class<?> getRawType() {
    return this._method.getReturnType();
  }
  
  public final Object call() throws Exception {
    return this._method.invoke((Object)null, new Object[0]);
  }
  
  public final Object call(Object[] args) throws Exception {
    return this._method.invoke((Object)null, args);
  }
  
  public final Object call1(Object arg) throws Exception {
    return this._method.invoke((Object)null, new Object[] { arg });
  }
  
  public final Object callOn(Object pojo) throws Exception {
    return this._method.invoke(pojo, (Object[])null);
  }
  
  public final Object callOnWith(Object pojo, Object... args) throws Exception {
    return this._method.invoke(pojo, args);
  }
  
  public int getParameterCount() {
    return this._method.getParameterCount();
  }
  
  public Class<?> getRawParameterType(int index) {
    Class<?>[] types = getRawParameterTypes();
    return (index >= types.length) ? null : types[index];
  }
  
  public JavaType getParameterType(int index) {
    Type[] types = this._method.getGenericParameterTypes();
    if (index >= types.length)
      return null; 
    return this._typeContext.resolveType(types[index]);
  }
  
  @Deprecated
  public Type getGenericParameterType(int index) {
    Type[] types = getGenericParameterTypes();
    if (index >= types.length)
      return null; 
    return types[index];
  }
  
  public Class<?> getDeclaringClass() {
    return this._method.getDeclaringClass();
  }
  
  public Method getMember() {
    return this._method;
  }
  
  public void setValue(Object pojo, Object value) throws IllegalArgumentException {
    try {
      this._method.invoke(pojo, new Object[] { value });
    } catch (IllegalAccessException|java.lang.reflect.InvocationTargetException e) {
      throw new IllegalArgumentException("Failed to setValue() with method " + 
          getFullName() + ": " + ClassUtil.exceptionMessage(e), e);
    } 
  }
  
  public Object getValue(Object pojo) throws IllegalArgumentException {
    try {
      return this._method.invoke(pojo, (Object[])null);
    } catch (IllegalAccessException|java.lang.reflect.InvocationTargetException e) {
      throw new IllegalArgumentException("Failed to getValue() with method " + 
          getFullName() + ": " + ClassUtil.exceptionMessage(e), e);
    } 
  }
  
  public String getFullName() {
    String methodName = super.getFullName();
    switch (getParameterCount()) {
      case 0:
        return methodName + "()";
      case 1:
        return methodName + "(" + getRawParameterType(0).getName() + ")";
    } 
    return String.format("%s(%d params)", new Object[] { super.getFullName(), Integer.valueOf(getParameterCount()) });
  }
  
  public Class<?>[] getRawParameterTypes() {
    if (this._paramClasses == null)
      this._paramClasses = this._method.getParameterTypes(); 
    return this._paramClasses;
  }
  
  @Deprecated
  public Type[] getGenericParameterTypes() {
    return this._method.getGenericParameterTypes();
  }
  
  public Class<?> getRawReturnType() {
    return this._method.getReturnType();
  }
  
  @Deprecated
  public boolean hasReturnType() {
    Class<?> rt = getRawReturnType();
    return (rt != void.class);
  }
  
  public String toString() {
    return "[method " + getFullName() + "]";
  }
  
  public int hashCode() {
    return this._method.hashCode();
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!ClassUtil.hasClass(o, getClass()))
      return false; 
    AnnotatedMethod other = (AnnotatedMethod)o;
    return Objects.equals(this._method, other._method);
  }
  
  Object writeReplace() {
    return new AnnotatedMethod(new Serialization(this._method));
  }
  
  Object readResolve() {
    Class<?> clazz = this._serialization.clazz;
    try {
      Method m = clazz.getDeclaredMethod(this._serialization.name, this._serialization.args);
      if (!m.isAccessible())
        ClassUtil.checkAndFixAccess(m, false); 
      return new AnnotatedMethod(null, m, null, null);
    } catch (Exception e) {
      throw new IllegalArgumentException("Could not find method '" + this._serialization.name + "' from Class '" + clazz
          .getName());
    } 
  }
  
  private static final class Serialization implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected Class<?> clazz;
    
    protected String name;
    
    protected Class<?>[] args;
    
    public Serialization(Method setter) {
      this.clazz = setter.getDeclaringClass();
      this.name = setter.getName();
      this.args = setter.getParameterTypes();
    }
  }
}
