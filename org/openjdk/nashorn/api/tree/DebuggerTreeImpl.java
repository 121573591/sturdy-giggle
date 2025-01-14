package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.DebuggerNode;
import org.openjdk.nashorn.internal.ir.Statement;

final class DebuggerTreeImpl extends StatementTreeImpl implements DebuggerTree {
  DebuggerTreeImpl(DebuggerNode node) {
    super((Statement)node);
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.DEBUGGER;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitDebugger(this, data);
  }
}
