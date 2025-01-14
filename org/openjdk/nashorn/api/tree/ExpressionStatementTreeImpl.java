package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.ExpressionStatement;
import org.openjdk.nashorn.internal.ir.Statement;

final class ExpressionStatementTreeImpl extends StatementTreeImpl implements ExpressionStatementTree {
  private final ExpressionTree expr;
  
  ExpressionStatementTreeImpl(ExpressionStatement es, ExpressionTree expr) {
    super((Statement)es);
    this.expr = expr;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.EXPRESSION_STATEMENT;
  }
  
  public ExpressionTree getExpression() {
    return this.expr;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitExpressionStatement(this, data);
  }
}
