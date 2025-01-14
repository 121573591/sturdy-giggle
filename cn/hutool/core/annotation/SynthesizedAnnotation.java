package cn.hutool.core.annotation;

import cn.hutool.core.collection.CollUtil;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.function.UnaryOperator;

public interface SynthesizedAnnotation extends Annotation, Hierarchical, AnnotationAttributeValueProvider {
  Annotation getAnnotation();
  
  int getVerticalDistance();
  
  int getHorizontalDistance();
  
  boolean hasAttribute(String paramString, Class<?> paramClass);
  
  Map<String, AnnotationAttribute> getAttributes();
  
  default void setAttributes(Map<String, AnnotationAttribute> attributes) {
    if (CollUtil.isNotEmpty(attributes))
      attributes.forEach(this::setAttribute); 
  }
  
  void setAttribute(String paramString, AnnotationAttribute paramAnnotationAttribute);
  
  void replaceAttribute(String paramString, UnaryOperator<AnnotationAttribute> paramUnaryOperator);
  
  Object getAttributeValue(String paramString);
}
