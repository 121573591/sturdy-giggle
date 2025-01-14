package org.openjdk.nashorn.api.tree;

import java.util.List;

public interface FunctionExpressionTree extends ExpressionTree {
  IdentifierTree getName();
  
  List<? extends ExpressionTree> getParameters();
  
  Tree getBody();
  
  boolean isStrict();
  
  boolean isArrow();
  
  boolean isGenerator();
}
