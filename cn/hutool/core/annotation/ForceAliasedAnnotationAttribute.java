package cn.hutool.core.annotation;

public class ForceAliasedAnnotationAttribute extends AbstractWrappedAnnotationAttribute {
  protected ForceAliasedAnnotationAttribute(AnnotationAttribute origin, AnnotationAttribute linked) {
    super(origin, linked);
  }
  
  public Object getValue() {
    return this.linked.getValue();
  }
  
  public boolean isValueEquivalentToDefaultValue() {
    return this.linked.isValueEquivalentToDefaultValue();
  }
  
  public Class<?> getAttributeType() {
    return this.linked.getAttributeType();
  }
}
