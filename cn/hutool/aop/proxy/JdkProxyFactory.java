package cn.hutool.aop.proxy;

import cn.hutool.aop.ProxyUtil;
import cn.hutool.aop.aspects.Aspect;
import cn.hutool.aop.interceptor.JdkInterceptor;
import java.lang.reflect.InvocationHandler;

public class JdkProxyFactory extends ProxyFactory {
  private static final long serialVersionUID = 1L;
  
  public <T> T proxy(T target, Aspect aspect) {
    return (T)ProxyUtil.newProxyInstance(target
        .getClass().getClassLoader(), (InvocationHandler)new JdkInterceptor(target, aspect), target
        
        .getClass().getInterfaces());
  }
}
