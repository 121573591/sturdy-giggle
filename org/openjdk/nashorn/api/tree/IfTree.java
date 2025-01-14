package org.openjdk.nashorn.api.tree;

public interface IfTree extends StatementTree {
  ExpressionTree getCondition();
  
  StatementTree getThenStatement();
  
  StatementTree getElseStatement();
}
