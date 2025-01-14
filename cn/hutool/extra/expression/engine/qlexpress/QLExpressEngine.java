package cn.hutool.extra.expression.engine.qlexpress;

import cn.hutool.extra.expression.ExpressionEngine;
import cn.hutool.extra.expression.ExpressionException;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import java.util.Collection;
import java.util.Map;

public class QLExpressEngine implements ExpressionEngine {
  private final ExpressRunner engine = new ExpressRunner();
  
  public Object eval(String expression, Map<String, Object> context, Collection<Class<?>> allowClassSet) {
    DefaultContext<String, Object> defaultContext = new DefaultContext();
    defaultContext.putAll(context);
    try {
      return this.engine.execute(expression, (IExpressContext)defaultContext, null, true, false);
    } catch (Exception e) {
      throw new ExpressionException(e);
    } 
  }
}
