package cn.hutool.core.bean;

import cn.hutool.core.lang.func.Func0;
import cn.hutool.core.map.ReferenceConcurrentMap;
import cn.hutool.core.map.WeakConcurrentMap;
import java.beans.PropertyDescriptor;
import java.util.Map;

public enum BeanInfoCache {
  INSTANCE;
  
  private final WeakConcurrentMap<Class<?>, Map<String, PropertyDescriptor>> ignoreCasePdCache;
  
  private final WeakConcurrentMap<Class<?>, Map<String, PropertyDescriptor>> pdCache;
  
  BeanInfoCache() {
    this.pdCache = new WeakConcurrentMap();
    this.ignoreCasePdCache = new WeakConcurrentMap();
  }
  
  public Map<String, PropertyDescriptor> getPropertyDescriptorMap(Class<?> beanClass, boolean ignoreCase) {
    return (Map<String, PropertyDescriptor>)getCache(ignoreCase).get(beanClass);
  }
  
  public Map<String, PropertyDescriptor> getPropertyDescriptorMap(Class<?> beanClass, boolean ignoreCase, Func0<Map<String, PropertyDescriptor>> supplier) {
    return (Map<String, PropertyDescriptor>)getCache(ignoreCase).computeIfAbsent(beanClass, key -> (Map)supplier.callWithRuntimeException());
  }
  
  public void putPropertyDescriptorMap(Class<?> beanClass, Map<String, PropertyDescriptor> fieldNamePropertyDescriptorMap, boolean ignoreCase) {
    getCache(ignoreCase).put(beanClass, fieldNamePropertyDescriptorMap);
  }
  
  public void clear() {
    this.pdCache.clear();
    this.ignoreCasePdCache.clear();
  }
  
  private ReferenceConcurrentMap<Class<?>, Map<String, PropertyDescriptor>> getCache(boolean ignoreCase) {
    return ignoreCase ? (ReferenceConcurrentMap<Class<?>, Map<String, PropertyDescriptor>>)this.ignoreCasePdCache : (ReferenceConcurrentMap<Class<?>, Map<String, PropertyDescriptor>>)this.pdCache;
  }
}
