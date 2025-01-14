package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.LabelNode;
import org.openjdk.nashorn.internal.ir.Statement;

final class LabeledStatementTreeImpl extends StatementTreeImpl implements LabeledStatementTree {
  private final String name;
  
  private final StatementTree stat;
  
  LabeledStatementTreeImpl(LabelNode node, StatementTree stat) {
    super((Statement)node);
    this.name = node.getLabelName();
    this.stat = stat;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.LABELED_STATEMENT;
  }
  
  public String getLabel() {
    return this.name;
  }
  
  public StatementTree getStatement() {
    return this.stat;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitLabeledStatement(this, data);
  }
}
