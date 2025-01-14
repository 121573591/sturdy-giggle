package cn.hutool.core.annotation;

import cn.hutool.core.annotation.scanner.AnnotationScanner;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractAnnotationSynthesizer<T> implements AnnotationSynthesizer {
  protected final T source;
  
  protected final Map<Class<? extends Annotation>, SynthesizedAnnotation> synthesizedAnnotationMap;
  
  private final Map<Class<? extends Annotation>, Annotation> synthesizedProxyAnnotations;
  
  protected final SynthesizedAnnotationSelector annotationSelector;
  
  protected final Collection<SynthesizedAnnotationPostProcessor> postProcessors;
  
  protected final AnnotationScanner annotationScanner;
  
  protected AbstractAnnotationSynthesizer(T source, SynthesizedAnnotationSelector annotationSelector, Collection<SynthesizedAnnotationPostProcessor> annotationPostProcessors, AnnotationScanner annotationScanner) {
    Assert.notNull(source, "source must not null", new Object[0]);
    Assert.notNull(annotationSelector, "annotationSelector must not null", new Object[0]);
    Assert.notNull(annotationPostProcessors, "annotationPostProcessors must not null", new Object[0]);
    Assert.notNull(annotationPostProcessors, "annotationScanner must not null", new Object[0]);
    this.source = source;
    this.annotationSelector = annotationSelector;
    this.annotationScanner = annotationScanner;
    this.postProcessors = CollUtil.unmodifiable(
        CollUtil.sort(annotationPostProcessors, Comparator.comparing(SynthesizedAnnotationPostProcessor::order)));
    this.synthesizedProxyAnnotations = new LinkedHashMap<>();
    this.synthesizedAnnotationMap = MapUtil.unmodifiable(loadAnnotations());
    annotationPostProcessors.forEach(processor -> this.synthesizedAnnotationMap.values().forEach(()));
  }
  
  public T getSource() {
    return this.source;
  }
  
  public SynthesizedAnnotationSelector getAnnotationSelector() {
    return this.annotationSelector;
  }
  
  public Collection<SynthesizedAnnotationPostProcessor> getAnnotationPostProcessors() {
    return this.postProcessors;
  }
  
  public SynthesizedAnnotation getSynthesizedAnnotation(Class<?> annotationType) {
    return this.synthesizedAnnotationMap.get(annotationType);
  }
  
  public Map<Class<? extends Annotation>, SynthesizedAnnotation> getAllSynthesizedAnnotation() {
    return this.synthesizedAnnotationMap;
  }
  
  public <A extends Annotation> A synthesize(Class<A> annotationType) {
    Annotation annotation = this.synthesizedProxyAnnotations.get(annotationType);
    if (Objects.nonNull(annotation))
      return (A)annotation; 
    synchronized (this.synthesizedProxyAnnotations) {
      annotation = this.synthesizedProxyAnnotations.get(annotationType);
      if (Objects.isNull(annotation)) {
        SynthesizedAnnotation synthesizedAnnotation = this.synthesizedAnnotationMap.get(annotationType);
        annotation = synthesize(annotationType, synthesizedAnnotation);
        this.synthesizedProxyAnnotations.put(annotationType, annotation);
      } 
    } 
    return (A)annotation;
  }
  
  protected abstract Map<Class<? extends Annotation>, SynthesizedAnnotation> loadAnnotations();
  
  protected abstract <A extends Annotation> A synthesize(Class<A> paramClass, SynthesizedAnnotation paramSynthesizedAnnotation);
}
