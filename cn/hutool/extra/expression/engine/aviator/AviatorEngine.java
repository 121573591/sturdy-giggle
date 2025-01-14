package cn.hutool.extra.expression.engine.aviator;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.expression.ExpressionEngine;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Options;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class AviatorEngine implements ExpressionEngine {
  private final AviatorEvaluatorInstance engine = AviatorEvaluator.getInstance();
  
  public Object eval(String expression, Map<String, Object> context, Collection<Class<?>> allowClassSet) {
    this.engine.setOption(Options.ALLOWED_CLASS_SET, 
        CollUtil.isEmpty(allowClassSet) ? Collections.emptySet() : CollUtil.newHashSet(allowClassSet));
    return this.engine.execute(expression, context);
  }
  
  public AviatorEvaluatorInstance getEngine() {
    return this.engine;
  }
}
