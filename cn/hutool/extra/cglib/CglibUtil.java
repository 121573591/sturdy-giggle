package cn.hutool.extra.cglib;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReflectUtil;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanMap;
import net.sf.cglib.core.Converter;

public class CglibUtil {
  public static <T> T copy(Object source, Class<T> targetClass) {
    return copy(source, targetClass, (Converter)null);
  }
  
  public static <T> T copy(Object source, Class<T> targetClass, Converter converter) {
    T target = (T)ReflectUtil.newInstanceIfPossible(targetClass);
    copy(source, target, converter);
    return target;
  }
  
  public static void copy(Object source, Object target) {
    copy(source, target, (Converter)null);
  }
  
  public static void copy(Object source, Object target, Converter converter) {
    Assert.notNull(source, "Source bean must be not null.", new Object[0]);
    Assert.notNull(target, "Target bean must be not null.", new Object[0]);
    Class<?> sourceClass = source.getClass();
    Class<?> targetClass = target.getClass();
    BeanCopier beanCopier = BeanCopierCache.INSTANCE.get(sourceClass, targetClass, converter);
    beanCopier.copy(source, target, converter);
  }
  
  public static <S, T> List<T> copyList(Collection<S> source, Supplier<T> target) {
    return copyList(source, target, null, null);
  }
  
  public static <S, T> List<T> copyList(Collection<S> source, Supplier<T> target, Converter converter) {
    return copyList(source, target, converter, null);
  }
  
  public static <S, T> List<T> copyList(Collection<S> source, Supplier<T> target, BiConsumer<S, T> callback) {
    return copyList(source, target, null, callback);
  }
  
  public static <S, T> List<T> copyList(Collection<S> source, Supplier<T> target, Converter converter, BiConsumer<S, T> callback) {
    return (List<T>)source.stream().map(s -> {
          T t = target.get();
          copy(s, t, converter);
          if (callback != null)
            callback.accept(s, t); 
          return (Function)t;
        }).collect(Collectors.toList());
  }
  
  public static BeanMap toMap(Object bean) {
    return BeanMap.create(bean);
  }
  
  public static <T> T fillBean(Map map, T bean) {
    BeanMap.create(bean).putAll(map);
    return bean;
  }
  
  public static <T> T toBean(Map map, Class<T> beanClass) {
    return fillBean(map, (T)ReflectUtil.newInstanceIfPossible(beanClass));
  }
}
