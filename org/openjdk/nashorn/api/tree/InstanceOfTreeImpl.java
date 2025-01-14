package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.BinaryNode;
import org.openjdk.nashorn.internal.parser.TokenType;

final class InstanceOfTreeImpl extends BinaryTreeImpl implements InstanceOfTree {
  InstanceOfTreeImpl(BinaryNode node, ExpressionTree expr, ExpressionTree type) {
    super(node, expr, type);
    assert node.isTokenType(TokenType.INSTANCEOF) : "instanceof expected";
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.INSTANCE_OF;
  }
  
  public ExpressionTree getExpression() {
    return getLeftOperand();
  }
  
  public Tree getType() {
    return getRightOperand();
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitInstanceOf(this, data);
  }
}
