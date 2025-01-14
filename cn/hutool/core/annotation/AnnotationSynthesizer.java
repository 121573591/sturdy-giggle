package cn.hutool.core.annotation;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;

public interface AnnotationSynthesizer {
  Object getSource();
  
  SynthesizedAnnotationSelector getAnnotationSelector();
  
  Collection<SynthesizedAnnotationPostProcessor> getAnnotationPostProcessors();
  
  SynthesizedAnnotation getSynthesizedAnnotation(Class<?> paramClass);
  
  Map<Class<? extends Annotation>, SynthesizedAnnotation> getAllSynthesizedAnnotation();
  
  <T extends Annotation> T synthesize(Class<T> paramClass);
}
