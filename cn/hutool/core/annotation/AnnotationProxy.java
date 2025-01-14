package cn.hutool.core.annotation;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AnnotationProxy<T extends Annotation> implements Annotation, InvocationHandler, Serializable {
  private static final long serialVersionUID = 1L;
  
  private final T annotation;
  
  private final Class<T> type;
  
  private final Map<String, Object> attributes;
  
  public AnnotationProxy(T annotation) {
    this.annotation = annotation;
    this.type = (Class)annotation.annotationType();
    this.attributes = initAttributes();
  }
  
  public Class<? extends Annotation> annotationType() {
    return this.type;
  }
  
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Alias alias = method.<Alias>getAnnotation(Alias.class);
    if (null != alias) {
      String name = alias.value();
      if (StrUtil.isNotBlank(name)) {
        if (false == this.attributes.containsKey(name))
          throw new IllegalArgumentException(StrUtil.format("No method for alias: [{}]", new Object[] { name })); 
        return this.attributes.get(name);
      } 
    } 
    Object value = this.attributes.get(method.getName());
    if (value != null)
      return value; 
    return method.invoke(this, args);
  }
  
  private Map<String, Object> initAttributes() {
    Method[] methods = ReflectUtil.getMethods(this.type);
    Map<String, Object> attributes = new HashMap<>(methods.length, 1.0F);
    for (Method method : methods) {
      if (!method.isSynthetic())
        attributes.put(method.getName(), ReflectUtil.invoke(this.annotation, method, new Object[0])); 
    } 
    return attributes;
  }
}
