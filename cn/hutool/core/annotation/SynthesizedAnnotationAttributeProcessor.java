package cn.hutool.core.annotation;

import java.util.Collection;

@FunctionalInterface
public interface SynthesizedAnnotationAttributeProcessor {
  <R> R getAttributeValue(String paramString, Class<R> paramClass, Collection<? extends SynthesizedAnnotation> paramCollection);
}
