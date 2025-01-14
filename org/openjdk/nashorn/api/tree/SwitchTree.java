package org.openjdk.nashorn.api.tree;

import java.util.List;

public interface SwitchTree extends StatementTree {
  ExpressionTree getExpression();
  
  List<? extends CaseTree> getCases();
}
