package org.openjdk.nashorn.api.tree;

public interface ArrayAccessTree extends ExpressionTree {
  ExpressionTree getExpression();
  
  ExpressionTree getIndex();
}
