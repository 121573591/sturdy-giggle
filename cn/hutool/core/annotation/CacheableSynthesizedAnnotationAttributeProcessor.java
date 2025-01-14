package cn.hutool.core.annotation;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.multi.RowKeyTable;
import cn.hutool.core.map.multi.Table;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class CacheableSynthesizedAnnotationAttributeProcessor implements SynthesizedAnnotationAttributeProcessor {
  private final Table<String, Class<?>, Object> valueCaches = (Table<String, Class<?>, Object>)new RowKeyTable();
  
  private final Comparator<Hierarchical> annotationComparator;
  
  public CacheableSynthesizedAnnotationAttributeProcessor(Comparator<Hierarchical> annotationComparator) {
    Assert.notNull(annotationComparator, "annotationComparator must not null", new Object[0]);
    this.annotationComparator = annotationComparator;
  }
  
  public CacheableSynthesizedAnnotationAttributeProcessor() {
    this(Hierarchical.DEFAULT_HIERARCHICAL_COMPARATOR);
  }
  
  public <T> T getAttributeValue(String attributeName, Class<T> attributeType, Collection<? extends SynthesizedAnnotation> synthesizedAnnotations) {
    Object value = this.valueCaches.get(attributeName, attributeType);
    if (Objects.isNull(value))
      synchronized (this.valueCaches) {
        value = this.valueCaches.get(attributeName, attributeType);
        if (Objects.isNull(value)) {
          value = synthesizedAnnotations.stream().filter(ma -> ma.hasAttribute(attributeName, attributeType)).min(this.annotationComparator).map(ma -> ma.getAttributeValue(attributeName)).orElse(null);
          this.valueCaches.put(attributeName, attributeType, value);
        } 
      }  
    return (T)value;
  }
}
