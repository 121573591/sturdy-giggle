package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.IfNode;
import org.openjdk.nashorn.internal.ir.Statement;

final class IfTreeImpl extends StatementTreeImpl implements IfTree {
  private final ExpressionTree cond;
  
  private final StatementTree thenStat;
  
  private final StatementTree elseStat;
  
  IfTreeImpl(IfNode node, ExpressionTree cond, StatementTree thenStat, StatementTree elseStat) {
    super((Statement)node);
    this.cond = cond;
    this.thenStat = thenStat;
    this.elseStat = elseStat;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.IF;
  }
  
  public ExpressionTree getCondition() {
    return this.cond;
  }
  
  public StatementTree getThenStatement() {
    return this.thenStat;
  }
  
  public StatementTree getElseStatement() {
    return this.elseStat;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitIf(this, data);
  }
}
