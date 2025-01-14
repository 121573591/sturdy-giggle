package cn.hutool.extra.expression.engine;

import cn.hutool.core.lang.Singleton;
import cn.hutool.core.util.ServiceLoaderUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.expression.ExpressionEngine;
import cn.hutool.extra.expression.ExpressionException;
import cn.hutool.log.StaticLog;
import java.lang.invoke.SerializedLambda;

public class ExpressionFactory {
  public static ExpressionEngine get() {
    return (ExpressionEngine)Singleton.get(ExpressionEngine.class.getName(), ExpressionFactory::create);
  }
  
  public static ExpressionEngine create() {
    ExpressionEngine engine = doCreate();
    StaticLog.debug("Use [{}] Engine As Default.", new Object[] { StrUtil.removeSuffix(engine.getClass().getSimpleName(), "Engine") });
    return engine;
  }
  
  private static ExpressionEngine doCreate() {
    ExpressionEngine engine = (ExpressionEngine)ServiceLoaderUtil.loadFirstAvailable(ExpressionEngine.class);
    if (null != engine)
      return engine; 
    throw new ExpressionException("No expression jar found ! Please add one of it to your project !");
  }
}
