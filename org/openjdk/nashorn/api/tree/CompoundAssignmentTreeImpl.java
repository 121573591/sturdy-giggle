package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.BinaryNode;
import org.openjdk.nashorn.internal.ir.Expression;

final class CompoundAssignmentTreeImpl extends ExpressionTreeImpl implements CompoundAssignmentTree {
  private final ExpressionTree var;
  
  private final ExpressionTree expr;
  
  private final Tree.Kind kind;
  
  CompoundAssignmentTreeImpl(BinaryNode node, ExpressionTree left, ExpressionTree right) {
    super((Expression)node);
    assert node.isAssignment() : "not an assignment node";
    this.var = left;
    this.expr = right;
    this.kind = getOperator(node.tokenType());
    assert this.kind != Tree.Kind.ASSIGNMENT : "compound assignment expected";
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
    return visitor.visitCompoundAssignment(this, data);
  }
}
