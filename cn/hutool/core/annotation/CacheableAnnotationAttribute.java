package cn.hutool.core.annotation;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class CacheableAnnotationAttribute implements AnnotationAttribute {
  private volatile boolean valueInvoked;
  
  private Object value;
  
  private boolean defaultValueInvoked;
  
  private Object defaultValue;
  
  private final Annotation annotation;
  
  private final Method attribute;
  
  public CacheableAnnotationAttribute(Annotation annotation, Method attribute) {
    Assert.notNull(annotation, "annotation must not null", new Object[0]);
    Assert.notNull(attribute, "attribute must not null", new Object[0]);
    this.annotation = annotation;
    this.attribute = attribute;
    this.valueInvoked = false;
    this.defaultValueInvoked = false;
  }
  
  public Annotation getAnnotation() {
    return this.annotation;
  }
  
  public Method getAttribute() {
    return this.attribute;
  }
  
  public Object getValue() {
    if (!this.valueInvoked)
      synchronized (this) {
        if (!this.valueInvoked) {
          this.valueInvoked = true;
          this.value = ReflectUtil.invoke(this.annotation, this.attribute, new Object[0]);
        } 
      }  
    return this.value;
  }
  
  public boolean isValueEquivalentToDefaultValue() {
    if (!this.defaultValueInvoked) {
      this.defaultValue = this.attribute.getDefaultValue();
      this.defaultValueInvoked = true;
    } 
    return ObjectUtil.equals(getValue(), this.defaultValue);
  }
}
