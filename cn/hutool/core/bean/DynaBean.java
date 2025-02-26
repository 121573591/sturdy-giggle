package cn.hutool.core.bean;

import cn.hutool.core.clone.CloneSupport;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import java.io.Serializable;
import java.util.Map;

public class DynaBean extends CloneSupport<DynaBean> implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private final Class<?> beanClass;
  
  private final Object bean;
  
  public static DynaBean create(Object bean) {
    return new DynaBean(bean);
  }
  
  public static DynaBean create(Class<?> beanClass) {
    return new DynaBean(beanClass);
  }
  
  public static DynaBean create(Class<?> beanClass, Object... params) {
    return new DynaBean(beanClass, params);
  }
  
  public DynaBean(Class<?> beanClass, Object... params) {
    this(ReflectUtil.newInstance(beanClass, params));
  }
  
  public DynaBean(Class<?> beanClass) {
    this(ReflectUtil.newInstance(beanClass, new Object[0]));
  }
  
  public DynaBean(Object bean) {
    Assert.notNull(bean);
    if (bean instanceof DynaBean)
      bean = ((DynaBean)bean).getBean(); 
    this.bean = bean;
    this.beanClass = ClassUtil.getClass(bean);
  }
  
  public <T> T get(String fieldName) throws BeanException {
    if (Map.class.isAssignableFrom(this.beanClass))
      return (T)((Map)this.bean).get(fieldName); 
    PropDesc prop = BeanUtil.getBeanDesc(this.beanClass).getProp(fieldName);
    if (null == prop)
      throw new BeanException("No public field or get method for {}", new Object[] { fieldName }); 
    return (T)prop.getValue(this.bean);
  }
  
  public boolean containsProp(String fieldName) {
    if (Map.class.isAssignableFrom(this.beanClass))
      return ((Map)this.bean).containsKey(fieldName); 
    return (null != BeanUtil.getBeanDesc(this.beanClass).getProp(fieldName));
  }
  
  public <T> T safeGet(String fieldName) {
    try {
      return get(fieldName);
    } catch (Exception e) {
      return null;
    } 
  }
  
  public void set(String fieldName, Object value) throws BeanException {
    if (Map.class.isAssignableFrom(this.beanClass)) {
      ((Map<String, Object>)this.bean).put(fieldName, value);
    } else {
      PropDesc prop = BeanUtil.getBeanDesc(this.beanClass).getProp(fieldName);
      if (null == prop)
        throw new BeanException("No public field or set method for {}", new Object[] { fieldName }); 
      prop.setValue(this.bean, value);
    } 
  }
  
  public Object invoke(String methodName, Object... params) {
    return ReflectUtil.invoke(this.bean, methodName, params);
  }
  
  public <T> T getBean() {
    return (T)this.bean;
  }
  
  public <T> Class<T> getBeanClass() {
    return (Class)this.beanClass;
  }
  
  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = 31 * result + ((this.bean == null) ? 0 : this.bean.hashCode());
    return result;
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null)
      return false; 
    if (getClass() != obj.getClass())
      return false; 
    DynaBean other = (DynaBean)obj;
    if (this.bean == null)
      return (other.bean == null); 
    return this.bean.equals(other.bean);
  }
  
  public String toString() {
    return this.bean.toString();
  }
}
