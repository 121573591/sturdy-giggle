package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.Statement;
import org.openjdk.nashorn.internal.ir.VarNode;

final class VariableTreeImpl extends StatementTreeImpl implements VariableTree {
  private final IdentifierTree ident;
  
  private final ExpressionTree init;
  
  VariableTreeImpl(VarNode node, IdentifierTree ident, ExpressionTree init) {
    super((Statement)node);
    this.ident = ident;
    this.init = init;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.VARIABLE;
  }
  
  public ExpressionTree getBinding() {
    return this.ident;
  }
  
  public ExpressionTree getInitializer() {
    return this.init;
  }
  
  public boolean isConst() {
    return ((VarNode)this.node).isConst();
  }
  
  public boolean isLet() {
    return ((VarNode)this.node).isLet();
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitVariable(this, data);
  }
}
