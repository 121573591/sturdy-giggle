package org.openjdk.nashorn.api.tree;

public interface AssignmentTree extends ExpressionTree {
  ExpressionTree getVariable();
  
  ExpressionTree getExpression();
}
