package org.openjdk.nashorn.api.tree;

public interface WithTree extends StatementTree {
  ExpressionTree getScope();
  
  StatementTree getStatement();
}
