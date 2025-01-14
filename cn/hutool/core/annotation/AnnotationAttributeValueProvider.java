package cn.hutool.core.annotation;

@FunctionalInterface
public interface AnnotationAttributeValueProvider {
  Object getAttributeValue(String paramString, Class<?> paramClass);
}
