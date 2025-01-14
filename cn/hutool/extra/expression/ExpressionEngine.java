package cn.hutool.extra.expression;

import java.util.Collection;
import java.util.Map;

public interface ExpressionEngine {
  Object eval(String paramString, Map<String, Object> paramMap, Collection<Class<?>> paramCollection);
}
