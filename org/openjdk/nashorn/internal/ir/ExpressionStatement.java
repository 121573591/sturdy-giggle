package org.openjdk.nashorn.internal.ir;

import org.openjdk.nashorn.internal.ir.annotations.Immutable;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.parser.TokenType;

@Immutable
public final class ExpressionStatement extends Statement {
  private static final long serialVersionUID = 1L;
  
  private final Expression expression;
  
  private final TokenType destructuringDecl;
  
  public ExpressionStatement(int lineNumber, long token, int finish, Expression expression, TokenType destructuringDecl) {
    super(lineNumber, token, finish);
    this.expression = expression;
    this.destructuringDecl = destructuringDecl;
  }
  
  public ExpressionStatement(int lineNumber, long token, int finish, Expression expression) {
    this(lineNumber, token, finish, expression, null);
  }
  
  private ExpressionStatement(ExpressionStatement expressionStatement, Expression expression) {
    super(expressionStatement);
    this.expression = expression;
    this.destructuringDecl = null;
  }
  
  public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterExpressionStatement(this))
      return visitor.leaveExpressionStatement(setExpression((Expression)this.expression.accept(visitor))); 
    return this;
  }
  
  public void toString(StringBuilder sb, boolean printTypes) {
    this.expression.toString(sb, printTypes);
  }
  
  public Expression getExpression() {
    return this.expression;
  }
  
  public TokenType destructuringDeclarationType() {
    return this.destructuringDecl;
  }
  
  public ExpressionStatement setExpression(Expression expression) {
    if (this.expression == expression)
      return this; 
    return new ExpressionStatement(this, expression);
  }
}
