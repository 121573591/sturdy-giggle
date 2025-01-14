package org.openjdk.nashorn.api.tree;

public interface MemberSelectTree extends ExpressionTree {
  ExpressionTree getExpression();
  
  String getIdentifier();
}
