package cn.hutool.core.lang.func;

import cn.hutool.core.exceptions.ExceptionUtil;
import java.io.Serializable;

@FunctionalInterface
public interface VoidFunc1<P> extends Serializable {
  void call(P paramP) throws Exception;
  
  default void callWithRuntimeException(P parameter) {
    try {
      call(parameter);
    } catch (Exception e) {
      throw ExceptionUtil.wrapRuntime(e);
    } 
  }
}
