package cn.hutool.extra.expression.engine.jfireel;

import cn.hutool.extra.expression.ExpressionEngine;
import com.jfirer.jfireel.expression.Expression;
import java.util.Collection;
import java.util.Map;

public class JfireELEngine implements ExpressionEngine {
  static {
    checkEngineExist(Expression.class);
  }
  
  public Object eval(String expression, Map<String, Object> context, Collection<Class<?>> allowClassSet) {
    return Expression.parse(expression).calculate(context);
  }
  
  private static void checkEngineExist(Class<?> clazz) {}
}
