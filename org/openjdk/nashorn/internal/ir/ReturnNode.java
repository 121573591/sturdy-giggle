package org.openjdk.nashorn.internal.ir;

import org.openjdk.nashorn.internal.ir.annotations.Immutable;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.parser.TokenType;

@Immutable
public class ReturnNode extends Statement {
  private static final long serialVersionUID = 1L;
  
  private final Expression expression;
  
  public ReturnNode(int lineNumber, long token, int finish, Expression expression) {
    super(lineNumber, token, finish);
    this.expression = expression;
  }
  
  private ReturnNode(ReturnNode returnNode, Expression expression) {
    super(returnNode);
    this.expression = expression;
  }
  
  public boolean isTerminal() {
    return true;
  }
  
  public boolean isReturn() {
    return isTokenType(TokenType.RETURN);
  }
  
  public boolean isYield() {
    return isTokenType(TokenType.YIELD);
  }
  
  public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterReturnNode(this)) {
      if (this.expression != null)
        return visitor.leaveReturnNode(setExpression((Expression)this.expression.accept(visitor))); 
      return visitor.leaveReturnNode(this);
    } 
    return this;
  }
  
  public void toString(StringBuilder sb, boolean printType) {
    sb.append(isYield() ? "yield" : "return");
    if (this.expression != null) {
      sb.append(' ');
      this.expression.toString(sb, printType);
    } 
  }
  
  public Expression getExpression() {
    return this.expression;
  }
  
  public ReturnNode setExpression(Expression expression) {
    if (this.expression == expression)
      return this; 
    return new ReturnNode(this, expression);
  }
}
