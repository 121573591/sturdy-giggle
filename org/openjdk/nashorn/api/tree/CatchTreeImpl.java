package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.CatchNode;
import org.openjdk.nashorn.internal.ir.Node;

final class CatchTreeImpl extends TreeImpl implements CatchTree {
  private final ExpressionTree param;
  
  private final BlockTree block;
  
  private final ExpressionTree condition;
  
  CatchTreeImpl(CatchNode node, ExpressionTree param, BlockTree block, ExpressionTree condition) {
    super((Node)node);
    this.param = param;
    this.block = block;
    this.condition = condition;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.CATCH;
  }
  
  public ExpressionTree getParameter() {
    return this.param;
  }
  
  public BlockTree getBlock() {
    return this.block;
  }
  
  public ExpressionTree getCondition() {
    return this.condition;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitCatch(this, data);
  }
}
