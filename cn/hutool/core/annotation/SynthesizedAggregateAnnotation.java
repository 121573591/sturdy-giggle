package cn.hutool.core.annotation;

import java.lang.annotation.Annotation;

public interface SynthesizedAggregateAnnotation extends AggregateAnnotation, Hierarchical, AnnotationSynthesizer, AnnotationAttributeValueProvider {
  default int getVerticalDistance() {
    return 0;
  }
  
  default int getHorizontalDistance() {
    return 0;
  }
  
  <T extends Annotation> T getAnnotation(Class<T> paramClass);
  
  SynthesizedAnnotationAttributeProcessor getAnnotationAttributeProcessor();
  
  default Class<? extends Annotation> annotationType() {
    return (Class)getClass();
  }
  
  Object getAttributeValue(String paramString, Class<?> paramClass);
}
