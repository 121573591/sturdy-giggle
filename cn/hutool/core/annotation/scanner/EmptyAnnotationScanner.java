package cn.hutool.core.annotation.scanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class EmptyAnnotationScanner implements AnnotationScanner {
  public boolean support(AnnotatedElement annotatedEle) {
    return true;
  }
  
  public List<Annotation> getAnnotations(AnnotatedElement annotatedEle) {
    return Collections.emptyList();
  }
  
  public void scan(BiConsumer<Integer, Annotation> consumer, AnnotatedElement annotatedEle, Predicate<Annotation> filter) {}
}
