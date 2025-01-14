package cn.hutool.extra.expression;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.extra.expression.engine.ExpressionFactory;
import java.util.Collection;
import java.util.Map;

public class ExpressionUtil {
  public static ExpressionEngine getEngine() {
    return ExpressionFactory.get();
  }
  
  public static Object eval(String expression, Map<String, Object> context) {
    return eval(expression, context, ListUtil.empty());
  }
  
  public static Object eval(String expression, Map<String, Object> context, Collection<Class<?>> allowClassSet) {
    return getEngine().eval(expression, context, allowClassSet);
  }
}
