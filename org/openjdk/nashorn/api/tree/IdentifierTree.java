package org.openjdk.nashorn.api.tree;

public interface IdentifierTree extends ExpressionTree {
  String getName();
  
  boolean isRestParameter();
  
  boolean isSuper();
  
  boolean isThis();
  
  boolean isStar();
  
  boolean isDefault();
  
  boolean isStarDefaultStar();
}
