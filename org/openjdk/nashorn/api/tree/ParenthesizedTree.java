package org.openjdk.nashorn.api.tree;

public interface ParenthesizedTree extends ExpressionTree {
  ExpressionTree getExpression();
}
