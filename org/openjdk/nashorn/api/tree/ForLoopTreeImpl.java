package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.ForNode;
import org.openjdk.nashorn.internal.ir.Statement;

final class ForLoopTreeImpl extends StatementTreeImpl implements ForLoopTree {
  private final ExpressionTree init;
  
  private final ExpressionTree cond;
  
  private final ExpressionTree update;
  
  private final StatementTree stat;
  
  ForLoopTreeImpl(ForNode node, ExpressionTree init, ExpressionTree cond, ExpressionTree update, StatementTree stat) {
    super((Statement)node);
    assert !node.isForIn() : "for statement expected";
    this.init = init;
    this.cond = cond;
    this.update = update;
    this.stat = stat;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.FOR_LOOP;
  }
  
  public ExpressionTree getInitializer() {
    return this.init;
  }
  
  public ExpressionTree getCondition() {
    return this.cond;
  }
  
  public ExpressionTree getUpdate() {
    return this.update;
  }
  
  public StatementTree getStatement() {
    return this.stat;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitForLoop(this, data);
  }
}
