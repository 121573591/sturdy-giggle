package cn.hutool.aop.proxy;

import cn.hutool.aop.aspects.Aspect;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.ServiceLoaderUtil;
import java.io.Serializable;

public abstract class ProxyFactory implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public <T> T proxy(T target, Class<? extends Aspect> aspectClass) {
    return proxy(target, (Aspect)ReflectUtil.newInstanceIfPossible(aspectClass));
  }
  
  public abstract <T> T proxy(T paramT, Aspect paramAspect);
  
  public static <T> T createProxy(T target, Class<? extends Aspect> aspectClass) {
    return createProxy(target, (Aspect)ReflectUtil.newInstance(aspectClass, new Object[0]));
  }
  
  public static <T> T createProxy(T target, Aspect aspect) {
    return create().proxy(target, aspect);
  }
  
  public static ProxyFactory create() {
    return (ProxyFactory)ServiceLoaderUtil.loadFirstAvailable(ProxyFactory.class);
  }
}
