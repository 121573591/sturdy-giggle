package cn.hutool.extra.expression.engine.mvel;

import cn.hutool.extra.expression.ExpressionEngine;
import java.util.Collection;
import java.util.Map;
import org.mvel2.MVEL;

public class MvelEngine implements ExpressionEngine {
  static {
    checkEngineExist(MVEL.class);
  }
  
  public Object eval(String expression, Map<String, Object> context, Collection<Class<?>> allowClassSet) {
    return MVEL.eval(expression, context);
  }
  
  private static void checkEngineExist(Class<?> clazz) {}
}
