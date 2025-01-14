package cn.hutool.extra.expression.engine.rhino;

import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.expression.ExpressionEngine;
import java.util.Collection;
import java.util.Map;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class RhinoEngine implements ExpressionEngine {
  static {
    checkEngineExist(Context.class);
  }
  
  public Object eval(String expression, Map<String, Object> context, Collection<Class<?>> allowClassSet) {
    Context ctx = Context.enter();
    ScriptableObject scriptableObject = ctx.initStandardObjects();
    if (MapUtil.isNotEmpty(context))
      context.forEach((key, value) -> ScriptableObject.putProperty(scope, key, Context.javaToJS(value, scope))); 
    Object result = ctx.evaluateString((Scriptable)scriptableObject, expression, "rhino.js", 1, null);
    Context.exit();
    return result;
  }
  
  private static void checkEngineExist(Class<?> clazz) {}
}
