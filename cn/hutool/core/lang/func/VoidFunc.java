package cn.hutool.core.lang.func;

import cn.hutool.core.exceptions.ExceptionUtil;
import java.io.Serializable;

@FunctionalInterface
public interface VoidFunc<P> extends Serializable {
  void call(P... paramVarArgs) throws Exception;
  
  void callWithRuntimeException(P... parameters) {
    try {
      call(parameters);
    } catch (Exception e) {
      throw ExceptionUtil.wrapRuntime(e);
    } 
  }
}
