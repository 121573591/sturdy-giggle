package cn.hutool.aop.proxy;

import cn.hutool.aop.aspects.Aspect;
import cn.hutool.aop.interceptor.SpringCglibInterceptor;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import java.lang.reflect.Constructor;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;

public class SpringCglibProxyFactory extends ProxyFactory {
  private static final long serialVersionUID = 1L;
  
  public <T> T proxy(T target, Aspect aspect) {
    Class<?> targetClass = target.getClass();
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(targetClass);
    enhancer.setCallback((Callback)new SpringCglibInterceptor(target, aspect));
    return create(enhancer, targetClass);
  }
  
  private static <T> T create(Enhancer enhancer, Class<?> targetClass) {
    Constructor[] arrayOfConstructor = ReflectUtil.getConstructors(targetClass);
    IllegalArgumentException finalException = null;
    for (Constructor<?> constructor : arrayOfConstructor) {
      Class<?>[] parameterTypes = constructor.getParameterTypes();
      Object[] values = ClassUtil.getDefaultValues(parameterTypes);
      try {
        return (T)enhancer.create(parameterTypes, values);
      } catch (IllegalArgumentException e) {
        finalException = e;
      } 
    } 
    if (null != finalException)
      throw finalException; 
    throw new IllegalArgumentException("No constructor provided");
  }
}
