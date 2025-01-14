package org.openjdk.nashorn.internal.ir;

import org.openjdk.nashorn.internal.ir.annotations.Immutable;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

@Immutable
public final class ThrowNode extends Statement implements JoinPredecessor {
  private static final long serialVersionUID = 1L;
  
  private final Expression expression;
  
  private final LocalVariableConversion conversion;
  
  private final boolean isSyntheticRethrow;
  
  public ThrowNode(int lineNumber, long token, int finish, Expression expression, boolean isSyntheticRethrow) {
    super(lineNumber, token, finish);
    this.expression = expression;
    this.isSyntheticRethrow = isSyntheticRethrow;
    this.conversion = null;
  }
  
  private ThrowNode(ThrowNode node, Expression expression, boolean isSyntheticRethrow, LocalVariableConversion conversion) {
    super(node);
    this.expression = expression;
    this.isSyntheticRethrow = isSyntheticRethrow;
    this.conversion = conversion;
  }
  
  public boolean isTerminal() {
    return true;
  }
  
  public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterThrowNode(this))
      return visitor.leaveThrowNode(setExpression((Expression)this.expression.accept(visitor))); 
    return this;
  }
  
  public void toString(StringBuilder sb, boolean printType) {
    sb.append("throw ");
    if (this.expression != null)
      this.expression.toString(sb, printType); 
    if (this.conversion != null)
      this.conversion.toString(sb); 
  }
  
  public Expression getExpression() {
    return this.expression;
  }
  
  public ThrowNode setExpression(Expression expression) {
    if (this.expression == expression)
      return this; 
    return new ThrowNode(this, expression, this.isSyntheticRethrow, this.conversion);
  }
  
  public boolean isSyntheticRethrow() {
    return this.isSyntheticRethrow;
  }
  
  public JoinPredecessor setLocalVariableConversion(LexicalContext lc, LocalVariableConversion conversion) {
    if (this.conversion == conversion)
      return this; 
    return new ThrowNode(this, this.expression, this.isSyntheticRethrow, conversion);
  }
  
  public LocalVariableConversion getLocalVariableConversion() {
    return this.conversion;
  }
}
