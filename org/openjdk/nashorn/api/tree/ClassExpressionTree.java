package org.openjdk.nashorn.api.tree;

import java.util.List;

public interface ClassExpressionTree extends ExpressionTree {
  IdentifierTree getName();
  
  ExpressionTree getClassHeritage();
  
  PropertyTree getConstructor();
  
  List<? extends PropertyTree> getClassElements();
}
