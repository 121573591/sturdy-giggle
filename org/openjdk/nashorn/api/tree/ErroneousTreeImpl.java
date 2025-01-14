package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.ErrorNode;
import org.openjdk.nashorn.internal.ir.Expression;

final class ErroneousTreeImpl extends ExpressionTreeImpl implements ErroneousTree {
  ErroneousTreeImpl(ErrorNode errorNode) {
    super((Expression)errorNode);
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.ERROR;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitErroneous(this, data);
  }
}
