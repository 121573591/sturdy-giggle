package org.openjdk.nashorn.api.tree;

import java.util.List;
import org.openjdk.nashorn.internal.ir.Statement;
import org.openjdk.nashorn.internal.ir.TryNode;

final class TryTreeImpl extends StatementTreeImpl implements TryTree {
  private final BlockTree block;
  
  private final List<? extends CatchTree> catches;
  
  private final BlockTree finallyBlock;
  
  TryTreeImpl(TryNode node, BlockTree block, List<? extends CatchTree> catches, BlockTree finallyBlock) {
    super((Statement)node);
    this.block = block;
    this.catches = catches;
    this.finallyBlock = finallyBlock;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.TRY;
  }
  
  public BlockTree getBlock() {
    return this.block;
  }
  
  public List<? extends CatchTree> getCatches() {
    return this.catches;
  }
  
  public BlockTree getFinallyBlock() {
    return this.finallyBlock;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitTry(this, data);
  }
}
