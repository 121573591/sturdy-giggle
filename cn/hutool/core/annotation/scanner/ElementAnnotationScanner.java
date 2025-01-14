package cn.hutool.core.annotation.scanner;

import cn.hutool.core.util.ObjectUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ElementAnnotationScanner implements AnnotationScanner {
  public boolean support(AnnotatedElement annotatedEle) {
    return ObjectUtil.isNotNull(annotatedEle);
  }
  
  public void scan(BiConsumer<Integer, Annotation> consumer, AnnotatedElement annotatedEle, Predicate<Annotation> filter) {
    filter = (Predicate<Annotation>)ObjectUtil.defaultIfNull(filter, a -> ());
    Stream.<Annotation>of(annotatedEle.getAnnotations())
      .filter(filter)
      .forEach(annotation -> consumer.accept(Integer.valueOf(0), annotation));
  }
}
