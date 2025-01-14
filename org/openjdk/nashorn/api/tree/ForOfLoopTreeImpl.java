package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.ForNode;
import org.openjdk.nashorn.internal.ir.Statement;

final class ForOfLoopTreeImpl extends StatementTreeImpl implements ForOfLoopTree {
  private final ExpressionTree lhsExpr;
  
  private final ExpressionTree expr;
  
  private final StatementTree stat;
  
  ForOfLoopTreeImpl(ForNode node, ExpressionTree lhsExpr, ExpressionTree expr, StatementTree stat) {
    super((Statement)node);
    assert node.isForIn() : "for ..in expected";
    this.lhsExpr = lhsExpr;
    this.expr = expr;
    this.stat = stat;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.FOR_IN_LOOP;
  }
  
  public ExpressionTree getVariable() {
    return this.lhsExpr;
  }
  
  public ExpressionTree getExpression() {
    return this.expr;
  }
  
  public StatementTree getStatement() {
    return this.stat;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitForOfLoop(this, data);
  }
}
