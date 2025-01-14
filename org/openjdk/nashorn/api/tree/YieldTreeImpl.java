package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.UnaryNode;
import org.openjdk.nashorn.internal.parser.TokenType;

final class YieldTreeImpl extends ExpressionTreeImpl implements YieldTree {
  private final ExpressionTree expr;
  
  YieldTreeImpl(Expression exprNode, ExpressionTree expr) {
    super(exprNode);
    this.expr = expr;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.YIELD;
  }
  
  public ExpressionTree getExpression() {
    return this.expr;
  }
  
  public boolean isStar() {
    return ((UnaryNode)this.node).isTokenType(TokenType.YIELD_STAR);
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitYield(this, data);
  }
}
