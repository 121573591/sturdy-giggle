package org.openjdk.nashorn.api.tree;

public interface VariableTree extends StatementTree {
  ExpressionTree getBinding();
  
  ExpressionTree getInitializer();
  
  boolean isConst();
  
  boolean isLet();
}
