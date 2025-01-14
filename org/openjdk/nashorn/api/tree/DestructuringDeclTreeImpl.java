package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.ExpressionStatement;
import org.openjdk.nashorn.internal.ir.Statement;
import org.openjdk.nashorn.internal.parser.TokenType;

final class DestructuringDeclTreeImpl extends StatementTreeImpl implements VariableTree {
  private final TokenType declType;
  
  private final ExpressionTree lhs;
  
  private final ExpressionTree init;
  
  DestructuringDeclTreeImpl(ExpressionStatement exprStat, ExpressionTree lhs, ExpressionTree init) {
    super((Statement)exprStat);
    assert exprStat.destructuringDeclarationType() != null : "expecting a destructuring decl. statement";
    this.declType = exprStat.destructuringDeclarationType();
    this.lhs = lhs;
    this.init = init;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.VARIABLE;
  }
  
  public ExpressionTree getBinding() {
    return this.lhs;
  }
  
  public ExpressionTree getInitializer() {
    return this.init;
  }
  
  public boolean isConst() {
    return (this.declType == TokenType.CONST);
  }
  
  public boolean isLet() {
    return (this.declType == TokenType.LET);
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitVariable(this, data);
  }
}
