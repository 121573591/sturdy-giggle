package cn.hutool.aop.interceptor;

import cn.hutool.aop.aspects.Aspect;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibInterceptor implements MethodInterceptor, Serializable {
  private static final long serialVersionUID = 1L;
  
  private final Object target;
  
  private final Aspect aspect;
  
  public CglibInterceptor(Object target, Aspect aspect) {
    this.target = target;
    this.aspect = aspect;
  }
  
  public Object getTarget() {
    return this.target;
  }
  
  public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
    Object target = this.target;
    Object result = null;
    if (this.aspect.before(target, method, args))
      try {
        result = proxy.invoke(target, args);
      } catch (Throwable e) {
        Throwable throwable = e;
        if (throwable instanceof InvocationTargetException)
          throwable = ((InvocationTargetException)throwable).getTargetException(); 
        if (this.aspect.afterException(target, method, args, throwable))
          throw throwable; 
      }  
    if (this.aspect.after(target, method, args, result))
      return result; 
    return null;
  }
}
