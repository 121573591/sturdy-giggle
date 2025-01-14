package org.openjdk.nashorn.api.tree;

public interface DoWhileLoopTree extends ConditionalLoopTree {
  ExpressionTree getCondition();
  
  StatementTree getStatement();
}
