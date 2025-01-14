package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.Statement;
import org.openjdk.nashorn.internal.ir.WhileNode;

final class WhileLoopTreeImpl extends StatementTreeImpl implements WhileLoopTree {
  private final ExpressionTree cond;
  
  private final StatementTree stat;
  
  WhileLoopTreeImpl(WhileNode node, ExpressionTree cond, StatementTree stat) {
    super((Statement)node);
    assert !node.isDoWhile() : "while expected";
    this.cond = cond;
    this.stat = stat;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.WHILE_LOOP;
  }
  
  public ExpressionTree getCondition() {
    return this.cond;
  }
  
  public StatementTree getStatement() {
    return this.stat;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitWhileLoop(this, data);
  }
}
