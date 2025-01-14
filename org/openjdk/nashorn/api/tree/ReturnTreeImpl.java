package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.ReturnNode;
import org.openjdk.nashorn.internal.ir.Statement;

final class ReturnTreeImpl extends StatementTreeImpl implements ReturnTree {
  private final ExpressionTree expr;
  
  ReturnTreeImpl(ReturnNode returnNode, ExpressionTree expr) {
    super((Statement)returnNode);
    this.expr = expr;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.RETURN;
  }
  
  public ExpressionTree getExpression() {
    return this.expr;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitReturn(this, data);
  }
}
