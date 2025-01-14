package org.openjdk.nashorn.api.tree;

public interface YieldTree extends ExpressionTree {
  ExpressionTree getExpression();
  
  boolean isStar();
}
