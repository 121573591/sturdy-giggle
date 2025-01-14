package org.openjdk.nashorn.api.tree;

import java.util.List;

public interface TemplateLiteralTree extends ExpressionTree {
  List<? extends ExpressionTree> getExpressions();
}
