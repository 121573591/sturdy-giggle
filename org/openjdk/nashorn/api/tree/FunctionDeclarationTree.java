package org.openjdk.nashorn.api.tree;

import java.util.List;

public interface FunctionDeclarationTree extends StatementTree {
  IdentifierTree getName();
  
  List<? extends ExpressionTree> getParameters();
  
  BlockTree getBody();
  
  boolean isStrict();
  
  boolean isGenerator();
}
