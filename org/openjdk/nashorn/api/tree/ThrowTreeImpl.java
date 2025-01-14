package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.Statement;
import org.openjdk.nashorn.internal.ir.ThrowNode;

final class ThrowTreeImpl extends StatementTreeImpl implements ThrowTree {
  private final ExpressionTree expr;
  
  ThrowTreeImpl(ThrowNode node, ExpressionTree expr) {
    super((Statement)node);
    this.expr = expr;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.THROW;
  }
  
  public ExpressionTree getExpression() {
    return this.expr;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitThrow(this, data);
  }
}
