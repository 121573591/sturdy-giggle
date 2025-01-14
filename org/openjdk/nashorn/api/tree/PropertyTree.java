package org.openjdk.nashorn.api.tree;

public interface PropertyTree extends Tree {
  ExpressionTree getKey();
  
  ExpressionTree getValue();
  
  FunctionExpressionTree getGetter();
  
  FunctionExpressionTree getSetter();
  
  boolean isStatic();
  
  boolean isComputed();
}
