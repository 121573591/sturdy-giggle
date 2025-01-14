package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.AccessNode;
import org.openjdk.nashorn.internal.ir.Expression;

final class MemberSelectTreeImpl extends ExpressionTreeImpl implements MemberSelectTree {
  private final String ident;
  
  private final ExpressionTree expr;
  
  MemberSelectTreeImpl(AccessNode node, ExpressionTree expr) {
    super((Expression)node);
    this.ident = node.getProperty();
    this.expr = expr;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.MEMBER_SELECT;
  }
  
  public ExpressionTree getExpression() {
    return this.expr;
  }
  
  public String getIdentifier() {
    return this.ident;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitMemberSelect(this, data);
  }
}
