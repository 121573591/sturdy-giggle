package cn.hutool.core.annotation.scanner;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MetaAnnotationScanner implements AnnotationScanner {
  private final boolean includeSupperMetaAnnotation;
  
  public MetaAnnotationScanner(boolean includeSupperMetaAnnotation) {
    this.includeSupperMetaAnnotation = includeSupperMetaAnnotation;
  }
  
  public MetaAnnotationScanner() {
    this(true);
  }
  
  public boolean support(AnnotatedElement annotatedEle) {
    return (annotatedEle instanceof Class && ClassUtil.isAssignable(Annotation.class, (Class)annotatedEle));
  }
  
  public List<Annotation> getAnnotations(AnnotatedElement annotatedEle) {
    List<Annotation> annotations = new ArrayList<>();
    scan((index, annotation) -> annotations.add(annotation), annotatedEle, annotation -> ObjectUtil.notEqual(annotation, annotatedEle));
    return annotations;
  }
  
  public void scan(BiConsumer<Integer, Annotation> consumer, AnnotatedElement annotatedEle, Predicate<Annotation> filter) {
    filter = (Predicate<Annotation>)ObjectUtil.defaultIfNull(filter, a -> ());
    Set<Class<? extends Annotation>> accessed = new HashSet<>();
    Deque<List<Class<? extends Annotation>>> deque = CollUtil.newLinkedList((Object[])new List[] { CollUtil.newArrayList((Object[])new Class[] { (Class)annotatedEle }) });
    int distance = 0;
    do {
      List<Class<? extends Annotation>> annotationTypes = deque.removeFirst();
      for (Class<? extends Annotation> type : annotationTypes) {
        List<Annotation> metaAnnotations = (List<Annotation>)Stream.<Annotation>of(type.getAnnotations()).filter(a -> !AnnotationUtil.isJdkMetaAnnotation(a.annotationType())).filter(filter).collect(Collectors.toList());
        for (Annotation metaAnnotation : metaAnnotations)
          consumer.accept(Integer.valueOf(distance), metaAnnotation); 
        accessed.add(type);
        List<Class<? extends Annotation>> next = (List<Class<? extends Annotation>>)metaAnnotations.stream().map(Annotation::annotationType).filter(t -> !accessed.contains(t)).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(next))
          deque.addLast(next); 
      } 
      distance++;
    } while (this.includeSupperMetaAnnotation && !deque.isEmpty());
  }
}
