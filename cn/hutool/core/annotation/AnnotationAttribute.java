package cn.hutool.core.annotation;

import cn.hutool.core.util.ReflectUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public interface AnnotationAttribute {
  Annotation getAnnotation();
  
  Method getAttribute();
  
  default Class<?> getAnnotationType() {
    return getAttribute().getDeclaringClass();
  }
  
  default String getAttributeName() {
    return getAttribute().getName();
  }
  
  default Object getValue() {
    return ReflectUtil.invoke(getAnnotation(), getAttribute(), new Object[0]);
  }
  
  boolean isValueEquivalentToDefaultValue();
  
  default Class<?> getAttributeType() {
    return getAttribute().getReturnType();
  }
  
  default <T extends Annotation> T getAnnotation(Class<T> annotationType) {
    return getAttribute().getAnnotation(annotationType);
  }
  
  default boolean isWrapped() {
    return false;
  }
}
