package org.openjdk.nashorn.api.tree;

import java.util.List;
import org.openjdk.nashorn.internal.ir.Expression;

final class TemplateLiteralTreeImpl extends ExpressionTreeImpl implements TemplateLiteralTree {
  private final List<? extends ExpressionTree> expressions;
  
  TemplateLiteralTreeImpl(Expression node, List<? extends ExpressionTree> expressions) {
    super(node);
    this.expressions = expressions;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.TEMPLATE_LITERAL;
  }
  
  public List<? extends ExpressionTree> getExpressions() {
    return this.expressions;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitTemplateLiteral(this, data);
  }
}
