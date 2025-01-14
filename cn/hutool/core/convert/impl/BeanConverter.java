package cn.hutool.core.convert.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.BeanCopier;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.convert.AbstractConverter;
import cn.hutool.core.convert.ConvertException;
import cn.hutool.core.map.MapProxy;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import java.lang.reflect.Type;
import java.util.Map;

public class BeanConverter<T> extends AbstractConverter<T> {
  private static final long serialVersionUID = 1L;
  
  private final Type beanType;
  
  private final Class<T> beanClass;
  
  private final CopyOptions copyOptions;
  
  public BeanConverter(Type beanType) {
    this(beanType, CopyOptions.create().setIgnoreError(true));
  }
  
  public BeanConverter(Class<T> beanClass) {
    this(beanClass, CopyOptions.create().setIgnoreError(true));
  }
  
  public BeanConverter(Type beanType, CopyOptions copyOptions) {
    this.beanType = beanType;
    this.beanClass = TypeUtil.getClass(beanType);
    this.copyOptions = copyOptions;
  }
  
  protected T convertInternal(Object value) {
    Class<?>[] interfaces = this.beanClass.getInterfaces();
    for (Class<?> anInterface : interfaces) {
      if ("cn.hutool.json.JSONBeanParser".equals(anInterface.getName())) {
        T obj = (T)ReflectUtil.newInstanceIfPossible(this.beanClass);
        ReflectUtil.invoke(obj, "parse", new Object[] { value });
        return obj;
      } 
    } 
    if (value instanceof Map || value instanceof cn.hutool.core.bean.copier.ValueProvider || 
      
      BeanUtil.isBean(value.getClass())) {
      if (value instanceof Map && this.beanClass.isInterface())
        return (T)MapProxy.create((Map)value).toProxyBean(this.beanClass); 
      return (T)BeanCopier.create(value, ReflectUtil.newInstanceIfPossible(this.beanClass), this.beanType, this.copyOptions).copy();
    } 
    if (value instanceof byte[])
      return (T)ObjectUtil.deserialize((byte[])value, new Class[0]); 
    if (StrUtil.isEmptyIfStr(value))
      return null; 
    throw new ConvertException("Unsupported source type: {}", new Object[] { value.getClass() });
  }
  
  public Class<T> getTargetType() {
    return this.beanClass;
  }
}
