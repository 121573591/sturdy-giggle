package cn.hutool.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;

public interface WrappedAnnotationAttribute extends AnnotationAttribute {
  AnnotationAttribute getOriginal();
  
  AnnotationAttribute getNonWrappedOriginal();
  
  AnnotationAttribute getLinked();
  
  Collection<AnnotationAttribute> getAllLinkedNonWrappedAttributes();
  
  default Annotation getAnnotation() {
    return getOriginal().getAnnotation();
  }
  
  default Method getAttribute() {
    return getOriginal().getAttribute();
  }
  
  boolean isValueEquivalentToDefaultValue();
  
  default Class<?> getAttributeType() {
    return getOriginal().getAttributeType();
  }
  
  default <T extends Annotation> T getAnnotation(Class<T> annotationType) {
    return getOriginal().getAnnotation(annotationType);
  }
  
  default boolean isWrapped() {
    return true;
  }
}
