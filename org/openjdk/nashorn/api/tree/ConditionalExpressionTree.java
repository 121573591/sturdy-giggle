package org.openjdk.nashorn.api.tree;

public interface ConditionalExpressionTree extends ExpressionTree {
  ExpressionTree getCondition();
  
  ExpressionTree getTrueExpression();
  
  ExpressionTree getFalseExpression();
}
