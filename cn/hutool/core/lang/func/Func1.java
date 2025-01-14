package cn.hutool.core.lang.func;

import cn.hutool.core.exceptions.ExceptionUtil;
import java.io.Serializable;

@FunctionalInterface
public interface Func1<P, R> extends Serializable {
  R call(P paramP) throws Exception;
  
  default R callWithRuntimeException(P parameter) {
    try {
      return call(parameter);
    } catch (Exception e) {
      throw ExceptionUtil.wrapRuntime(e);
    } 
  }
}
