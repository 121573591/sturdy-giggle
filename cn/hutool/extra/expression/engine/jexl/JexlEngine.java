package cn.hutool.extra.expression.engine.jexl;

import cn.hutool.extra.expression.ExpressionEngine;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.MapContext;

public class JexlEngine implements ExpressionEngine {
  private final org.apache.commons.jexl3.JexlEngine engine = (new JexlBuilder()).cache(512).strict(true).silent(false).create();
  
  public Object eval(String expression, Map<String, Object> context, Collection<Class<?>> allowClassSet) {
    MapContext mapContext = new MapContext(context);
    try {
      return this.engine.createExpression(expression).evaluate((JexlContext)mapContext);
    } catch (Exception ignore) {
      return this.engine.createScript(expression).execute((JexlContext)mapContext);
    } 
  }
  
  public org.apache.commons.jexl3.JexlEngine getEngine() {
    return this.engine;
  }
}
