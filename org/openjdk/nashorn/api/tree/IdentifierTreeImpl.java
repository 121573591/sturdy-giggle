package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.IdentNode;

final class IdentifierTreeImpl extends ExpressionTreeImpl implements IdentifierTree {
  private final String name;
  
  IdentifierTreeImpl(IdentNode node) {
    super((Expression)node);
    this.name = node.getName();
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.IDENTIFIER;
  }
  
  public String getName() {
    return this.name;
  }
  
  public boolean isRestParameter() {
    return ((IdentNode)this.node).isRestParameter();
  }
  
  public boolean isSuper() {
    IdentNode ident = (IdentNode)this.node;
    return (ident.isDirectSuper() || "super".equals(ident.getName()));
  }
  
  public boolean isThis() {
    return "this".equals(((IdentNode)this.node).getName());
  }
  
  public boolean isStar() {
    return "*".equals(((IdentNode)this.node).getName());
  }
  
  public boolean isDefault() {
    return "default".equals(((IdentNode)this.node).getName());
  }
  
  public boolean isStarDefaultStar() {
    return "*default*".equals(((IdentNode)this.node).getName());
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitIdentifier(this, data);
  }
}
