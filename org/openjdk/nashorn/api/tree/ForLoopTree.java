package org.openjdk.nashorn.api.tree;

public interface ForLoopTree extends ConditionalLoopTree {
  ExpressionTree getInitializer();
  
  ExpressionTree getCondition();
  
  ExpressionTree getUpdate();
  
  StatementTree getStatement();
}
