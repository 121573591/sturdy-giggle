package cn.hutool.core.lang.func;

import cn.hutool.core.exceptions.ExceptionUtil;
import java.io.Serializable;

@FunctionalInterface
public interface Func0<R> extends Serializable {
  R call() throws Exception;
  
  default R callWithRuntimeException() {
    try {
      return call();
    } catch (Exception e) {
      throw ExceptionUtil.wrapRuntime(e);
    } 
  }
}
