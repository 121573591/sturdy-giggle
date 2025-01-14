package org.openjdk.nashorn.api.tree;

public interface LabeledStatementTree extends StatementTree {
  String getLabel();
  
  StatementTree getStatement();
}
