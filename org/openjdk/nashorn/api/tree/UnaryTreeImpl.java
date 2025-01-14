package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.UnaryNode;

class UnaryTreeImpl extends ExpressionTreeImpl implements UnaryTree {
  private final ExpressionTree expr;
  
  private final Tree.Kind kind;
  
  UnaryTreeImpl(UnaryNode node, ExpressionTree expr) {
    super((Expression)node);
    this.expr = expr;
    this.kind = getOperator(node.tokenType());
  }
  
  public Tree.Kind getKind() {
    return this.kind;
  }
  
  public ExpressionTree getExpression() {
    return this.expr;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitUnary(this, data);
  }
}
