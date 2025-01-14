package org.openjdk.nashorn.api.tree;

public interface WhileLoopTree extends ConditionalLoopTree {
  ExpressionTree getCondition();
  
  StatementTree getStatement();
}
