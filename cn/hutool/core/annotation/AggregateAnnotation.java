package cn.hutool.core.annotation;

import java.lang.annotation.Annotation;

public interface AggregateAnnotation extends Annotation {
  boolean isAnnotationPresent(Class<? extends Annotation> paramClass);
  
  Annotation[] getAnnotations();
}
