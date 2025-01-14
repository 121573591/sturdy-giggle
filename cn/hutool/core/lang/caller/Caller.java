package cn.hutool.core.lang.caller;

public interface Caller {
  Class<?> getCaller();
  
  Class<?> getCallerCaller();
  
  Class<?> getCaller(int paramInt);
  
  boolean isCalledBy(Class<?> paramClass);
}
