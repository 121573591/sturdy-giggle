package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.BreakNode;
import org.openjdk.nashorn.internal.ir.Statement;

final class BreakTreeImpl extends StatementTreeImpl implements BreakTree {
  private final String label;
  
  BreakTreeImpl(BreakNode node) {
    super((Statement)node);
    this.label = node.getLabelName();
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.BREAK;
  }
  
  public String getLabel() {
    return this.label;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitBreak(this, data);
  }
}
