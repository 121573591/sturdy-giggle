package org.openjdk.nashorn.api.tree;

public interface ExpressionStatementTree extends StatementTree {
  ExpressionTree getExpression();
}
