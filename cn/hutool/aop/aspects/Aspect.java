package cn.hutool.aop.aspects;

import java.lang.reflect.Method;

public interface Aspect {
  boolean before(Object paramObject, Method paramMethod, Object[] paramArrayOfObject);
  
  boolean after(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2);
  
  boolean afterException(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Throwable paramThrowable);
}
