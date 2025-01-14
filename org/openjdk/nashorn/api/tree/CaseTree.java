package org.openjdk.nashorn.api.tree;

import java.util.List;

public interface CaseTree extends Tree {
  ExpressionTree getExpression();
  
  List<? extends StatementTree> getStatements();
}
