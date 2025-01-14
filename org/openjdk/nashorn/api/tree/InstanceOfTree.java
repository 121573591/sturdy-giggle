package org.openjdk.nashorn.api.tree;

public interface InstanceOfTree extends ExpressionTree {
  ExpressionTree getExpression();
  
  Tree getType();
}
