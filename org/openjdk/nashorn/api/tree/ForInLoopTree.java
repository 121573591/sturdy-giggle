package org.openjdk.nashorn.api.tree;

public interface ForInLoopTree extends LoopTree {
  ExpressionTree getVariable();
  
  ExpressionTree getExpression();
  
  StatementTree getStatement();
  
  boolean isForEach();
}
