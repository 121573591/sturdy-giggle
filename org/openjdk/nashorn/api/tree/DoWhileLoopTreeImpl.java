package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.Statement;
import org.openjdk.nashorn.internal.ir.WhileNode;

final class DoWhileLoopTreeImpl extends StatementTreeImpl implements DoWhileLoopTree {
  private final ExpressionTree cond;
  
  private final StatementTree stat;
  
  DoWhileLoopTreeImpl(WhileNode node, ExpressionTree cond, StatementTree stat) {
    super((Statement)node);
    assert node.isDoWhile() : "do while expected";
    this.cond = cond;
    this.stat = stat;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.DO_WHILE_LOOP;
  }
  
  public ExpressionTree getCondition() {
    return this.cond;
  }
  
  public StatementTree getStatement() {
    return this.stat;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitDoWhileLoop(this, data);
  }
}
