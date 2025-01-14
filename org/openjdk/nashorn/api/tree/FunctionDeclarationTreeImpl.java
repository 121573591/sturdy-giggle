package org.openjdk.nashorn.api.tree;

import java.util.List;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.Statement;
import org.openjdk.nashorn.internal.ir.VarNode;

final class FunctionDeclarationTreeImpl extends StatementTreeImpl implements FunctionDeclarationTree {
  private final FunctionNode funcNode;
  
  private final IdentifierTree funcName;
  
  private final List<? extends ExpressionTree> params;
  
  private final BlockTree body;
  
  FunctionDeclarationTreeImpl(VarNode node, List<? extends ExpressionTree> params, BlockTree body) {
    super((Statement)node);
    assert node.getInit() instanceof FunctionNode : "function expected";
    this.funcNode = (FunctionNode)node.getInit();
    assert this.funcNode.isDeclared() : "function declaration expected";
    this.funcName = this.funcNode.isAnonymous() ? null : new IdentifierTreeImpl(node.getName());
    this.params = params;
    this.body = body;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.FUNCTION;
  }
  
  public IdentifierTree getName() {
    return this.funcName;
  }
  
  public List<? extends ExpressionTree> getParameters() {
    return this.params;
  }
  
  public BlockTree getBody() {
    return this.body;
  }
  
  public boolean isStrict() {
    return this.funcNode.isStrict();
  }
  
  public boolean isGenerator() {
    return (this.funcNode.getKind() == FunctionNode.Kind.GENERATOR);
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitFunctionDeclaration(this, data);
  }
}
