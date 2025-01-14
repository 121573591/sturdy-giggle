package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.Statement;
import org.openjdk.nashorn.internal.ir.WithNode;

final class WithTreeImpl extends StatementTreeImpl implements WithTree {
  private final ExpressionTree scope;
  
  private final StatementTree stat;
  
  WithTreeImpl(WithNode node, ExpressionTree scope, StatementTree stat) {
    super((Statement)node);
    this.scope = scope;
    this.stat = stat;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.WITH;
  }
  
  public ExpressionTree getScope() {
    return this.scope;
  }
  
  public StatementTree getStatement() {
    return this.stat;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitWith(this, data);
  }
}
