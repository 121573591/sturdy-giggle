package cn.hutool.core.annotation.scanner;

import cn.hutool.core.map.multi.ListValueMap;
import cn.hutool.core.util.ObjectUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class GenericAnnotationScanner implements AnnotationScanner {
  private final AnnotationScanner typeScanner;
  
  private final AnnotationScanner methodScanner;
  
  private final AnnotationScanner metaScanner;
  
  private final AnnotationScanner elementScanner;
  
  public boolean support(AnnotatedElement annotatedEle) {
    return true;
  }
  
  public GenericAnnotationScanner(boolean enableScanMetaAnnotation, boolean enableScanSupperClass, boolean enableScanSupperInterface) {
    this.metaScanner = enableScanMetaAnnotation ? new MetaAnnotationScanner() : new EmptyAnnotationScanner();
    this
      .typeScanner = new TypeAnnotationScanner(enableScanSupperClass, enableScanSupperInterface, a -> true, Collections.emptySet());
    this
      .methodScanner = new MethodAnnotationScanner(enableScanSupperClass, enableScanSupperInterface, a -> true, Collections.emptySet());
    this.elementScanner = new ElementAnnotationScanner();
  }
  
  public void scan(BiConsumer<Integer, Annotation> consumer, AnnotatedElement annotatedEle, Predicate<Annotation> filter) {
    filter = (Predicate<Annotation>)ObjectUtil.defaultIfNull(filter, a -> ());
    if (ObjectUtil.isNull(annotatedEle))
      return; 
    if (annotatedEle instanceof Class) {
      scanElements(this.typeScanner, consumer, annotatedEle, filter);
    } else if (annotatedEle instanceof java.lang.reflect.Method) {
      scanElements(this.methodScanner, consumer, annotatedEle, filter);
    } else {
      scanElements(this.elementScanner, consumer, annotatedEle, filter);
    } 
  }
  
  private void scanElements(AnnotationScanner scanner, BiConsumer<Integer, Annotation> consumer, AnnotatedElement annotatedEle, Predicate<Annotation> filter) {
    ListValueMap<Integer, Annotation> classAnnotations = new ListValueMap(new LinkedHashMap<>());
    scanner.scan((index, annotation) -> {
          if (filter.test(annotation))
            classAnnotations.putValue(index, annotation); 
        }annotatedEle, filter);
    classAnnotations.forEach((index, annotations) -> annotations.forEach(()));
  }
}
