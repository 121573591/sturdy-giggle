package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Modifier;

public abstract class Annotated {
  public abstract <A extends Annotation> A getAnnotation(Class<A> paramClass);
  
  public abstract boolean hasAnnotation(Class<?> paramClass);
  
  public abstract boolean hasOneOf(Class<? extends Annotation>[] paramArrayOfClass);
  
  public abstract AnnotatedElement getAnnotated();
  
  protected abstract int getModifiers();
  
  public boolean isPublic() {
    return Modifier.isPublic(getModifiers());
  }
  
  public abstract String getName();
  
  public abstract JavaType getType();
  
  public abstract Class<?> getRawType();
  
  @Deprecated
  public abstract Iterable<Annotation> annotations();
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
  
  public abstract String toString();
}
