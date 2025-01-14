package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.EmptyNode;
import org.openjdk.nashorn.internal.ir.Statement;

final class EmptyStatementTreeImpl extends StatementTreeImpl implements EmptyStatementTree {
  EmptyStatementTreeImpl(EmptyNode node) {
    super((Statement)node);
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.EMPTY_STATEMENT;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitEmptyStatement(this, data);
  }
}
