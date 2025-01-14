package org.openjdk.nashorn.api.tree;

import java.util.List;
import org.openjdk.nashorn.internal.ir.Block;
import org.openjdk.nashorn.internal.ir.BlockStatement;
import org.openjdk.nashorn.internal.ir.Statement;

final class BlockTreeImpl extends StatementTreeImpl implements BlockTree {
  private final List<? extends StatementTree> statements;
  
  BlockTreeImpl(BlockStatement node, List<? extends StatementTree> statements) {
    super((Statement)node);
    this.statements = statements;
  }
  
  BlockTreeImpl(Block node, List<? extends StatementTree> statements) {
    super(node);
    this.statements = statements;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.BLOCK;
  }
  
  public List<? extends StatementTree> getStatements() {
    return this.statements;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitBlock(this, data);
  }
}
