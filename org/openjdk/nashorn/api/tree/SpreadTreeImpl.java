package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.Expression;

final class SpreadTreeImpl extends ExpressionTreeImpl implements SpreadTree {
  private final ExpressionTree expr;
  
  SpreadTreeImpl(Expression exprNode, ExpressionTree expr) {
    super(exprNode);
    this.expr = expr;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.SPREAD;
  }
  
  public ExpressionTree getExpression() {
    return this.expr;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitSpread(this, data);
  }
}
