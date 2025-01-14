package org.openjdk.nashorn.api.tree;

import java.util.List;
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.FunctionNode;

final class FunctionExpressionTreeImpl extends ExpressionTreeImpl implements FunctionExpressionTree {
  private final FunctionNode funcNode;
  
  private final IdentifierTree funcName;
  
  private final List<? extends ExpressionTree> params;
  
  private final Tree body;
  
  FunctionExpressionTreeImpl(FunctionNode node, List<? extends ExpressionTree> params, BlockTree body) {
    super((Expression)node);
    this.funcNode = node;
    assert !this.funcNode.isDeclared() || this.funcNode.isAnonymous() : "function expression expected";
    FunctionNode.Kind kind = node.getKind();
    if (node.isAnonymous() || kind == FunctionNode.Kind.GETTER || kind == FunctionNode.Kind.SETTER) {
      this.funcName = null;
    } else {
      this.funcName = new IdentifierTreeImpl(node.getIdent());
    } 
    this.params = params;
    if (node.getFlag(67108864)) {
      StatementTree first = body.getStatements().get(0);
      assert first instanceof ReturnTree : "consise func. expression should have a return statement";
      this.body = ((ReturnTree)first).getExpression();
    } else {
      this.body = body;
    } 
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.FUNCTION_EXPRESSION;
  }
  
  public IdentifierTree getName() {
    return this.funcName;
  }
  
  public List<? extends ExpressionTree> getParameters() {
    return this.params;
  }
  
  public Tree getBody() {
    return this.body;
  }
  
  public boolean isStrict() {
    return this.funcNode.isStrict();
  }
  
  public boolean isArrow() {
    return (this.funcNode.getKind() == FunctionNode.Kind.ARROW);
  }
  
  public boolean isGenerator() {
    return (this.funcNode.getKind() == FunctionNode.Kind.GENERATOR);
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitFunctionExpression(this, data);
  }
}
