package cn.hutool.aop.proxy;

import cn.hutool.aop.aspects.Aspect;
import cn.hutool.aop.interceptor.CglibInterceptor;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import java.lang.reflect.Constructor;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

public class CglibProxyFactory extends ProxyFactory {
  private static final long serialVersionUID = 1L;
  
  public <T> T proxy(T target, Aspect aspect) {
    Class<?> targetClass = target.getClass();
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(target.getClass());
    enhancer.setCallback((Callback)new CglibInterceptor(target, aspect));
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
