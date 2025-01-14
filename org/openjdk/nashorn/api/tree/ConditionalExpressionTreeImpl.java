package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.TernaryNode;

final class ConditionalExpressionTreeImpl extends ExpressionTreeImpl implements ConditionalExpressionTree {
  private final ExpressionTree condExpr;
  
  private final ExpressionTree trueExpr;
  
  private final ExpressionTree falseExpr;
  
  ConditionalExpressionTreeImpl(TernaryNode node, ExpressionTree condExpr, ExpressionTree trueExpr, ExpressionTree falseExpr) {
    super((Expression)node);
    this.condExpr = condExpr;
    this.trueExpr = trueExpr;
    this.falseExpr = falseExpr;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.CONDITIONAL_EXPRESSION;
  }
  
  public ExpressionTree getCondition() {
    return this.condExpr;
  }
  
  public ExpressionTree getTrueExpression() {
    return this.trueExpr;
  }
  
  public ExpressionTree getFalseExpression() {
    return this.falseExpr;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitConditionalExpression(this, data);
  }
}
