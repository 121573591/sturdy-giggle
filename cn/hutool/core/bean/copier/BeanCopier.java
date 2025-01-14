package cn.hutool.core.bean.copier;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.copier.Copier;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Map;

public class BeanCopier<T> implements Copier<T>, Serializable {
  private static final long serialVersionUID = 1L;
  
  private final Copier<T> copier;
  
  public static <T> BeanCopier<T> create(Object source, T target, CopyOptions copyOptions) {
    return create(source, target, target.getClass(), copyOptions);
  }
  
  public static <T> BeanCopier<T> create(Object source, T target, Type destType, CopyOptions copyOptions) {
    return new BeanCopier<>(source, target, destType, copyOptions);
  }
  
  public BeanCopier(Object source, T target, Type targetType, CopyOptions copyOptions) {
    Copier<T> copier;
    Assert.notNull(source, "Source bean must be not null!", new Object[0]);
    Assert.notNull(target, "Target bean must be not null!", new Object[0]);
    if (source instanceof Map) {
      if (target instanceof Map) {
        copier = new MapToMapCopier((Map)source, (Map)target, targetType, copyOptions);
      } else {
        copier = new MapToBeanCopier<>((Map<?, ?>)source, target, targetType, copyOptions);
      } 
    } else if (source instanceof ValueProvider) {
      copier = new ValueProviderToBeanCopier<>((ValueProvider<String>)source, target, targetType, copyOptions);
    } else if (target instanceof Map) {
      copier = new BeanToMapCopier(source, (Map)target, targetType, copyOptions);
    } else {
      copier = new BeanToBeanCopier<>(source, target, targetType, copyOptions);
    } 
    this.copier = copier;
  }
  
  public T copy() {
    return (T)this.copier.copy();
  }
}
