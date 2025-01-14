package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.BinaryNode;
import org.openjdk.nashorn.internal.ir.Expression;

final class AssignmentTreeImpl extends ExpressionTreeImpl implements AssignmentTree {
  private final Tree.Kind kind;
  
  private final ExpressionTree var;
  
  private final ExpressionTree expr;
  
  AssignmentTreeImpl(BinaryNode node, ExpressionTree left, ExpressionTree right) {
    super((Expression)node);
    assert node.isAssignment() : "assignment node expected";
    this.var = left;
    this.expr = right;
    this.kind = getOperator(node.tokenType());
  }
  
  public Tree.Kind getKind() {
    return this.kind;
  }
  
  public ExpressionTree getVariable() {
    return this.var;
  }
  
  public ExpressionTree getExpression() {
    return this.expr;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitAssignment(this, data);
  }
}
