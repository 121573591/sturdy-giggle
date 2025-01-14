package org.openjdk.nashorn.api.tree;

public interface NewTree extends ExpressionTree {
  ExpressionTree getConstructorExpression();
}
