package cn.hutool.core.annotation;

import cn.hutool.core.map.TableMap;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;

public class CombinationAnnotationElement implements AnnotatedElement, Serializable {
  private static final long serialVersionUID = 1L;
  
  private Map<Class<? extends Annotation>, Annotation> annotationMap;
  
  private Map<Class<? extends Annotation>, Annotation> declaredAnnotationMap;
  
  private final Predicate<Annotation> predicate;
  
  public static CombinationAnnotationElement of(AnnotatedElement element, Predicate<Annotation> predicate) {
    return new CombinationAnnotationElement(element, predicate);
  }
  
  public CombinationAnnotationElement(AnnotatedElement element) {
    this(element, null);
  }
  
  public CombinationAnnotationElement(AnnotatedElement element, Predicate<Annotation> predicate) {
    this.predicate = predicate;
    init(element);
  }
  
  public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
    return this.annotationMap.containsKey(annotationClass);
  }
  
  public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
    Annotation annotation = this.annotationMap.get(annotationClass);
    return (annotation == null) ? null : (T)annotation;
  }
  
  public Annotation[] getAnnotations() {
    Collection<Annotation> annotations = this.annotationMap.values();
    return annotations.<Annotation>toArray(new Annotation[0]);
  }
  
  public Annotation[] getDeclaredAnnotations() {
    Collection<Annotation> annotations = this.declaredAnnotationMap.values();
    return annotations.<Annotation>toArray(new Annotation[0]);
  }
  
  private void init(AnnotatedElement element) {
    Annotation[] declaredAnnotations = element.getDeclaredAnnotations();
    this.declaredAnnotationMap = (Map<Class<? extends Annotation>, Annotation>)new TableMap();
    parseDeclared(declaredAnnotations);
    Annotation[] annotations = element.getAnnotations();
    if (Arrays.equals((Object[])declaredAnnotations, (Object[])annotations)) {
      this.annotationMap = this.declaredAnnotationMap;
    } else {
      this.annotationMap = (Map<Class<? extends Annotation>, Annotation>)new TableMap();
      parse(annotations);
    } 
  }
  
  private void parseDeclared(Annotation[] annotations) {
    for (Annotation annotation : annotations) {
      Class<? extends Annotation> annotationType = annotation.annotationType();
      if (AnnotationUtil.isNotJdkMateAnnotation(annotationType) && false == this.declaredAnnotationMap
        .containsKey(annotationType)) {
        if (test(annotation))
          this.declaredAnnotationMap.put(annotationType, annotation); 
        parseDeclared(annotationType.getDeclaredAnnotations());
      } 
    } 
  }
  
  private void parse(Annotation[] annotations) {
    for (Annotation annotation : annotations) {
      Class<? extends Annotation> annotationType = annotation.annotationType();
      if (AnnotationUtil.isNotJdkMateAnnotation(annotationType) && false == this.annotationMap
        .containsKey(annotationType)) {
        if (test(annotation))
          this.annotationMap.put(annotationType, annotation); 
        parse(annotationType.getAnnotations());
      } 
    } 
  }
  
  private boolean test(Annotation annotation) {
    return (null == this.predicate || this.predicate.test(annotation));
  }
}
