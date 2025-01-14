package cn.hutool.core.annotation;

import cn.hutool.core.lang.Assert;

public class MirroredAnnotationAttribute extends AbstractWrappedAnnotationAttribute {
  public MirroredAnnotationAttribute(AnnotationAttribute origin, AnnotationAttribute linked) {
    super(origin, linked);
  }
  
  public Object getValue() {
    boolean originIsDefault = this.original.isValueEquivalentToDefaultValue();
    boolean targetIsDefault = this.linked.isValueEquivalentToDefaultValue();
    Object originValue = this.original.getValue();
    Object targetValue = this.linked.getValue();
    if (originIsDefault == targetIsDefault) {
      Assert.equals(originValue, targetValue, "the values of attributes [{}] and [{}] that mirror each other are different: [{}] <==> [{}]", new Object[] { this.original
            
            .getAttribute(), this.linked.getAttribute(), originValue, targetValue });
      return originValue;
    } 
    return originIsDefault ? targetValue : originValue;
  }
  
  public boolean isValueEquivalentToDefaultValue() {
    return (this.original.isValueEquivalentToDefaultValue() && this.linked.isValueEquivalentToDefaultValue());
  }
}
