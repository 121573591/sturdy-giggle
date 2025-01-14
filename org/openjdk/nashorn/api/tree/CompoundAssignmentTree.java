package org.openjdk.nashorn.api.tree;

public interface CompoundAssignmentTree extends ExpressionTree {
  ExpressionTree getVariable();
  
  ExpressionTree getExpression();
}
