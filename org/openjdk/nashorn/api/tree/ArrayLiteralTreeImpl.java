package org.openjdk.nashorn.api.tree;

import java.util.List;
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.LiteralNode;

final class ArrayLiteralTreeImpl extends ExpressionTreeImpl implements ArrayLiteralTree {
  private final List<? extends ExpressionTree> elements;
  
  ArrayLiteralTreeImpl(LiteralNode<?> node, List<? extends ExpressionTree> elements) {
    super((Expression)node);
    this.elements = elements;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.ARRAY_LITERAL;
  }
  
  public List<? extends ExpressionTree> getElements() {
    return this.elements;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitArrayLiteral(this, data);
  }
}
