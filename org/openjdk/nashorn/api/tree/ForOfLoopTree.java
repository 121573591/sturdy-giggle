package org.openjdk.nashorn.api.tree;

public interface ForOfLoopTree extends LoopTree {
  ExpressionTree getVariable();
  
  ExpressionTree getExpression();
  
  StatementTree getStatement();
}
