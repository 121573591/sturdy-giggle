package cn.hutool.core.lang.caller;

import cn.hutool.core.util.ArrayUtil;
import java.io.Serializable;

public class SecurityManagerCaller extends SecurityManager implements Caller, Serializable {
  private static final long serialVersionUID = 1L;
  
  private static final int OFFSET = 1;
  
  public Class<?> getCaller() {
    Class<?>[] context = getClassContext();
    if (null != context && 2 < context.length)
      return context[2]; 
    return null;
  }
  
  public Class<?> getCallerCaller() {
    Class<?>[] context = getClassContext();
    if (null != context && 3 < context.length)
      return context[3]; 
    return null;
  }
  
  public Class<?> getCaller(int depth) {
    Class<?>[] context = getClassContext();
    if (null != context && 1 + depth < context.length)
      return context[1 + depth]; 
    return null;
  }
  
  public boolean isCalledBy(Class<?> clazz) {
    Class<?>[] classes = getClassContext();
    if (ArrayUtil.isNotEmpty((Object[])classes))
      for (Class<?> contextClass : classes) {
        if (contextClass.equals(clazz))
          return true; 
      }  
    return false;
  }
}
