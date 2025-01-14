package org.openjdk.nashorn.api.tree;

public interface BinaryTree extends ExpressionTree {
  ExpressionTree getLeftOperand();
  
  ExpressionTree getRightOperand();
}
