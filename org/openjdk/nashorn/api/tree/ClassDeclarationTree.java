package org.openjdk.nashorn.api.tree;

import java.util.List;

public interface ClassDeclarationTree extends StatementTree {
  IdentifierTree getName();
  
  ExpressionTree getClassHeritage();
  
  PropertyTree getConstructor();
  
  List<? extends PropertyTree> getClassElements();
}
