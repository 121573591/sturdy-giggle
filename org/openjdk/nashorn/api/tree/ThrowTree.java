package org.openjdk.nashorn.api.tree;

public interface ThrowTree extends StatementTree {
  ExpressionTree getExpression();
}
