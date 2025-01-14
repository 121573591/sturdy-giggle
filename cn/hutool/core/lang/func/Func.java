package cn.hutool.core.lang.func;

import cn.hutool.core.exceptions.ExceptionUtil;
import java.io.Serializable;

@FunctionalInterface
public interface Func<P, R> extends Serializable {
  R call(P... paramVarArgs) throws Exception;
  
  R callWithRuntimeException(P... parameters) {
    try {
      return call(parameters);
    } catch (Exception e) {
      throw ExceptionUtil.wrapRuntime(e);
    } 
  }
}
