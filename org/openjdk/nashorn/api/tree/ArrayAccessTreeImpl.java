package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.Expression;

final class ArrayAccessTreeImpl extends ExpressionTreeImpl implements ArrayAccessTree {
  private final ExpressionTree base;
  
  private final ExpressionTree index;
  
  ArrayAccessTreeImpl(Expression node, ExpressionTree base, ExpressionTree index) {
    super(node);
    this.base = base;
    this.index = index;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.ARRAY_ACCESS;
  }
  
  public ExpressionTree getExpression() {
    return this.base;
  }
  
  public ExpressionTree getIndex() {
    return this.index;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitArrayAccess(this, data);
  }
}
