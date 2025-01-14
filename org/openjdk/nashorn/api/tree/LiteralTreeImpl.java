package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.LiteralNode;

final class LiteralTreeImpl extends ExpressionTreeImpl implements LiteralTree {
  private final Object value;
  
  private final Tree.Kind kind;
  
  LiteralTreeImpl(LiteralNode<?> node) {
    super((Expression)node);
    this.kind = literalKind(node);
    this.value = node.getValue();
  }
  
  public Tree.Kind getKind() {
    return this.kind;
  }
  
  public Object getValue() {
    return this.value;
  }
  
  private static Tree.Kind literalKind(LiteralNode<?> node) {
    if (node.isBoolean())
      return Tree.Kind.BOOLEAN_LITERAL; 
    if (node.isNumeric())
      return Tree.Kind.NUMBER_LITERAL; 
    if (node.isString())
      return Tree.Kind.STRING_LITERAL; 
    if (node.isNull())
      return Tree.Kind.NULL_LITERAL; 
    throw new AssertionError("should not reach here: " + node.getValue());
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitLiteral(this, data);
  }
}
