package cn.hutool.extra.cglib;

import cn.hutool.core.map.WeakConcurrentMap;
import cn.hutool.core.util.StrUtil;
import java.lang.invoke.SerializedLambda;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

public enum BeanCopierCache {
  INSTANCE;
  
  private final WeakConcurrentMap<String, BeanCopier> cache;
  
  BeanCopierCache() {
    this.cache = new WeakConcurrentMap();
  }
  
  public BeanCopier get(Class<?> srcClass, Class<?> targetClass, Converter converter) {
    return get(srcClass, targetClass, (null != converter));
  }
  
  public BeanCopier get(Class<?> srcClass, Class<?> targetClass, boolean useConverter) {
    String key = genKey(srcClass, targetClass, useConverter);
    return (BeanCopier)this.cache.computeIfAbsent(key, () -> BeanCopier.create(srcClass, targetClass, useConverter));
  }
  
  private String genKey(Class<?> srcClass, Class<?> targetClass, boolean useConverter) {
    StringBuilder key = StrUtil.builder().append(srcClass.getName()).append('#').append(targetClass.getName()).append('#').append(useConverter ? 1 : 0);
    return key.toString();
  }
}
