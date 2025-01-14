package cn.hutool.core.annotation;

import cn.hutool.core.annotation.scanner.AnnotationScanner;
import cn.hutool.core.annotation.scanner.MetaAnnotationScanner;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Opt;
import cn.hutool.core.util.ObjectUtil;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GenericSynthesizedAggregateAnnotation extends AbstractAnnotationSynthesizer<List<Annotation>> implements SynthesizedAggregateAnnotation {
  private final Object root;
  
  private final int verticalDistance;
  
  private final int horizontalDistance;
  
  private final SynthesizedAnnotationAttributeProcessor attributeProcessor;
  
  public GenericSynthesizedAggregateAnnotation(Annotation... source) {
    this(Arrays.asList(source), (AnnotationScanner)new MetaAnnotationScanner());
  }
  
  public GenericSynthesizedAggregateAnnotation(List<Annotation> source, AnnotationScanner annotationScanner) {
    this(source, SynthesizedAnnotationSelector.NEAREST_AND_OLDEST_PRIORITY, new CacheableSynthesizedAnnotationAttributeProcessor(), 
        
        Arrays.asList(new SynthesizedAnnotationPostProcessor[] { SynthesizedAnnotationPostProcessor.ALIAS_ANNOTATION_POST_PROCESSOR, SynthesizedAnnotationPostProcessor.MIRROR_LINK_ANNOTATION_POST_PROCESSOR, SynthesizedAnnotationPostProcessor.ALIAS_LINK_ANNOTATION_POST_PROCESSOR }, ), annotationScanner);
  }
  
  public GenericSynthesizedAggregateAnnotation(List<Annotation> source, SynthesizedAnnotationSelector annotationSelector, SynthesizedAnnotationAttributeProcessor attributeProcessor, Collection<SynthesizedAnnotationPostProcessor> annotationPostProcessors, AnnotationScanner annotationScanner) {
    this((Object)null, 0, 0, source, annotationSelector, attributeProcessor, annotationPostProcessors, annotationScanner);
  }
  
  GenericSynthesizedAggregateAnnotation(Object root, int verticalDistance, int horizontalDistance, List<Annotation> source, SynthesizedAnnotationSelector annotationSelector, SynthesizedAnnotationAttributeProcessor attributeProcessor, Collection<SynthesizedAnnotationPostProcessor> annotationPostProcessors, AnnotationScanner annotationScanner) {
    super(source, annotationSelector, annotationPostProcessors, annotationScanner);
    Assert.notNull(attributeProcessor, "attributeProcessor must not null", new Object[0]);
    this.root = ObjectUtil.defaultIfNull(root, this);
    this.verticalDistance = verticalDistance;
    this.horizontalDistance = horizontalDistance;
    this.attributeProcessor = attributeProcessor;
  }
  
  public Object getRoot() {
    return this.root;
  }
  
  public int getVerticalDistance() {
    return this.verticalDistance;
  }
  
  public int getHorizontalDistance() {
    return this.horizontalDistance;
  }
  
  protected Map<Class<? extends Annotation>, SynthesizedAnnotation> loadAnnotations() {
    Map<Class<? extends Annotation>, SynthesizedAnnotation> annotationMap = new LinkedHashMap<>();
    for (int i = 0; i < this.source.size(); i++) {
      Annotation sourceAnnotation = this.source.get(i);
      Assert.isFalse(AnnotationUtil.isSynthesizedAnnotation(sourceAnnotation), "source [{}] has been synthesized", new Object[0]);
      annotationMap.put(sourceAnnotation.annotationType(), new MetaAnnotation(sourceAnnotation, sourceAnnotation, 0, i));
      Assert.isTrue(this.annotationScanner
          .support(sourceAnnotation.annotationType()), "annotation scanner [{}] cannot support scan [{}]", new Object[] { this.annotationScanner, sourceAnnotation
            
            .annotationType() });
      this.annotationScanner.scan((index, annotation) -> {
            SynthesizedAnnotation oldAnnotation = (SynthesizedAnnotation)annotationMap.get(annotation.annotationType());
            SynthesizedAnnotation newAnnotation = new MetaAnnotation(sourceAnnotation, annotation, index.intValue() + 1, annotationMap.size());
            if (ObjectUtil.isNull(oldAnnotation)) {
              annotationMap.put(annotation.annotationType(), newAnnotation);
            } else {
              annotationMap.put(annotation.annotationType(), this.annotationSelector.choose(oldAnnotation, newAnnotation));
            } 
          }sourceAnnotation
          
          .annotationType(), null);
    } 
    return annotationMap;
  }
  
  public SynthesizedAnnotationAttributeProcessor getAnnotationAttributeProcessor() {
    return this.attributeProcessor;
  }
  
  public Object getAttributeValue(String attributeName, Class<?> attributeType) {
    return this.attributeProcessor.getAttributeValue(attributeName, attributeType, this.synthesizedAnnotationMap.values());
  }
  
  public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
    return (T)Opt.ofNullable(annotationType)
      .map(this.synthesizedAnnotationMap::get)
      .map(SynthesizedAnnotation::getAnnotation)
      .map(annotationType::cast)
      .orElse(null);
  }
  
  public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
    return this.synthesizedAnnotationMap.containsKey(annotationType);
  }
  
  public Annotation[] getAnnotations() {
    return (Annotation[])this.synthesizedAnnotationMap.values().stream()
      .map(SynthesizedAnnotation::getAnnotation)
      .toArray(x$0 -> new Annotation[x$0]);
  }
  
  public <T extends Annotation> T synthesize(Class<T> annotationType, SynthesizedAnnotation annotation) {
    return SynthesizedAnnotationProxy.create(annotationType, this, annotation);
  }
  
  public static class MetaAnnotation extends GenericSynthesizedAnnotation<Annotation, Annotation> {
    protected MetaAnnotation(Annotation root, Annotation annotation, int verticalDistance, int horizontalDistance) {
      super(root, annotation, verticalDistance, horizontalDistance);
    }
  }
}
