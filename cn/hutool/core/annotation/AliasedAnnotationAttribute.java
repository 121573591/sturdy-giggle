package cn.hutool.core.annotation;

public class AliasedAnnotationAttribute extends AbstractWrappedAnnotationAttribute {
  protected AliasedAnnotationAttribute(AnnotationAttribute origin, AnnotationAttribute linked) {
    super(origin, linked);
  }
  
  public Object getValue() {
    return this.linked.isValueEquivalentToDefaultValue() ? super.getValue() : this.linked.getValue();
  }
  
  public boolean isValueEquivalentToDefaultValue() {
    return (this.linked.isValueEquivalentToDefaultValue() && this.original.isValueEquivalentToDefaultValue());
  }
}
