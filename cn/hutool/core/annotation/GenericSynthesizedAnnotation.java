package cn.hutool.core.annotation;

import cn.hutool.core.lang.Opt;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenericSynthesizedAnnotation<R, T extends Annotation> implements SynthesizedAnnotation {
  private final R root;
  
  private final T annotation;
  
  private final Map<String, AnnotationAttribute> attributeMethodCaches;
  
  private final int verticalDistance;
  
  private final int horizontalDistance;
  
  protected GenericSynthesizedAnnotation(R root, T annotation, int verticalDistance, int horizontalDistance) {
    this.root = root;
    this.annotation = annotation;
    this.verticalDistance = verticalDistance;
    this.horizontalDistance = horizontalDistance;
    this.attributeMethodCaches = new HashMap<>();
    this.attributeMethodCaches.putAll(loadAttributeMethods());
  }
  
  protected Map<String, AnnotationAttribute> loadAttributeMethods() {
    return (Map<String, AnnotationAttribute>)Stream.<Method>of(ClassUtil.getDeclaredMethods(this.annotation.annotationType()))
      .filter(AnnotationUtil::isAttributeMethod)
      .collect(Collectors.toMap(Method::getName, method -> new CacheableAnnotationAttribute((Annotation)this.annotation, method)));
  }
  
  public boolean hasAttribute(String attributeName) {
    return this.attributeMethodCaches.containsKey(attributeName);
  }
  
  public boolean hasAttribute(String attributeName, Class<?> returnType) {
    return Opt.ofNullable(this.attributeMethodCaches.get(attributeName))
      .filter(method -> ClassUtil.isAssignable(returnType, method.getAttributeType()))
      .isPresent();
  }
  
  public Map<String, AnnotationAttribute> getAttributes() {
    return this.attributeMethodCaches;
  }
  
  public void setAttribute(String attributeName, AnnotationAttribute attribute) {
    this.attributeMethodCaches.put(attributeName, attribute);
  }
  
  public void replaceAttribute(String attributeName, UnaryOperator<AnnotationAttribute> operator) {
    AnnotationAttribute old = this.attributeMethodCaches.get(attributeName);
    if (ObjectUtil.isNotNull(old))
      this.attributeMethodCaches.put(attributeName, operator.apply(old)); 
  }
  
  public Object getAttributeValue(String attributeName) {
    return Opt.ofNullable(this.attributeMethodCaches.get(attributeName))
      .map(AnnotationAttribute::getValue)
      .get();
  }
  
  public R getRoot() {
    return this.root;
  }
  
  public T getAnnotation() {
    return this.annotation;
  }
  
  public int getVerticalDistance() {
    return this.verticalDistance;
  }
  
  public int getHorizontalDistance() {
    return this.horizontalDistance;
  }
  
  public Class<? extends Annotation> annotationType() {
    return this.annotation.annotationType();
  }
  
  public Object getAttributeValue(String attributeName, Class<?> attributeType) {
    return Opt.ofNullable(this.attributeMethodCaches.get(attributeName))
      .filter(method -> ClassUtil.isAssignable(attributeType, method.getAttributeType()))
      .map(AnnotationAttribute::getValue)
      .get();
  }
}
