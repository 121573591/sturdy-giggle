package cn.hutool.aop;

import cn.hutool.aop.aspects.Aspect;
import cn.hutool.aop.proxy.ProxyFactory;
import cn.hutool.core.util.ClassUtil;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public final class ProxyUtil {
  public static <T> T proxy(T target, Class<? extends Aspect> aspectClass) {
    return (T)ProxyFactory.createProxy(target, aspectClass);
  }
  
  public static <T> T proxy(T target, Aspect aspect) {
    return (T)ProxyFactory.createProxy(target, aspect);
  }
  
  public static <T> T newProxyInstance(ClassLoader classloader, InvocationHandler invocationHandler, Class<?>... interfaces) {
    return (T)Proxy.newProxyInstance(classloader, interfaces, invocationHandler);
  }
  
  public static <T> T newProxyInstance(InvocationHandler invocationHandler, Class<?>... interfaces) {
    return newProxyInstance(ClassUtil.getClassLoader(), invocationHandler, interfaces);
  }
}
