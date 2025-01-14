package org.openjdk.nashorn.api.tree;

public interface CatchTree extends Tree {
  ExpressionTree getParameter();
  
  BlockTree getBlock();
  
  ExpressionTree getCondition();
}
