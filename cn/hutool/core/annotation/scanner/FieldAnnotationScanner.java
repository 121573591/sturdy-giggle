package cn.hutool.core.annotation.scanner;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ObjectUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class FieldAnnotationScanner implements AnnotationScanner {
  public boolean support(AnnotatedElement annotatedEle) {
    return annotatedEle instanceof java.lang.reflect.Field;
  }
  
  public void scan(BiConsumer<Integer, Annotation> consumer, AnnotatedElement annotatedEle, Predicate<Annotation> filter) {
    filter = (Predicate<Annotation>)ObjectUtil.defaultIfNull(filter, a -> ());
    for (Annotation annotation : annotatedEle.getAnnotations()) {
      if (AnnotationUtil.isNotJdkMateAnnotation(annotation.annotationType()) && filter.test(annotation))
        consumer.accept(Integer.valueOf(0), annotation); 
    } 
  }
}
