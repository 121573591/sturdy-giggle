package cn.hutool.core.lang.func;

import cn.hutool.core.exceptions.ExceptionUtil;
import java.io.Serializable;

@FunctionalInterface
public interface VoidFunc0 extends Serializable {
  void call() throws Exception;
  
  default void callWithRuntimeException() {
    try {
      call();
    } catch (Exception e) {
      throw ExceptionUtil.wrapRuntime(e);
    } 
  }
}
