package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.ContinueNode;
import org.openjdk.nashorn.internal.ir.Statement;

final class ContinueTreeImpl extends StatementTreeImpl implements ContinueTree {
  private final String label;
  
  ContinueTreeImpl(ContinueNode node) {
    super((Statement)node);
    this.label = node.getLabelName();
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.CONTINUE;
  }
  
  public String getLabel() {
    return this.label;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitContinue(this, data);
  }
}
