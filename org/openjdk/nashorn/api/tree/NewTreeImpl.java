package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.UnaryNode;
import org.openjdk.nashorn.internal.parser.TokenType;

final class NewTreeImpl extends ExpressionTreeImpl implements NewTree {
  private final ExpressionTree constrExpr;
  
  NewTreeImpl(UnaryNode node, ExpressionTree constrExpr) {
    super((Expression)node);
    assert node.isTokenType(TokenType.NEW) : "new expected";
    this.constrExpr = constrExpr;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.NEW;
  }
  
  public ExpressionTree getConstructorExpression() {
    return this.constrExpr;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitNew(this, data);
  }
}
