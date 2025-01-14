package org.openjdk.nashorn.api.tree;

import java.util.List;
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.ObjectNode;

final class ObjectLiteralTreeImpl extends ExpressionTreeImpl implements ObjectLiteralTree {
  private final List<? extends PropertyTree> props;
  
  ObjectLiteralTreeImpl(ObjectNode node, List<? extends PropertyTree> props) {
    super((Expression)node);
    this.props = props;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.OBJECT_LITERAL;
  }
  
  public List<? extends PropertyTree> getProperties() {
    return this.props;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitObjectLiteral(this, data);
  }
}
