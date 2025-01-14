package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.BinaryNode;
import org.openjdk.nashorn.internal.ir.Expression;

class BinaryTreeImpl extends ExpressionTreeImpl implements BinaryTree {
  private final Tree.Kind kind;
  
  private final ExpressionTree left;
  
  private final ExpressionTree right;
  
  BinaryTreeImpl(BinaryNode node, ExpressionTree left, ExpressionTree right) {
    super((Expression)node);
    assert !node.isAssignment() : "assignment node";
    this.left = left;
    this.right = right;
    this.kind = getOperator(node.tokenType());
  }
  
  public Tree.Kind getKind() {
    return this.kind;
  }
  
  public ExpressionTree getLeftOperand() {
    return this.left;
  }
  
  public ExpressionTree getRightOperand() {
    return this.right;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitBinary(this, data);
  }
}
