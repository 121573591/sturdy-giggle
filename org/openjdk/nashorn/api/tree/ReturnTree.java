package org.openjdk.nashorn.api.tree;

public interface ReturnTree extends StatementTree {
  ExpressionTree getExpression();
}
