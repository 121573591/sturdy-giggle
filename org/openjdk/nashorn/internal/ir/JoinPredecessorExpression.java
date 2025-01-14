package org.openjdk.nashorn.internal.ir;

import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

public class JoinPredecessorExpression extends Expression implements JoinPredecessor {
  private static final long serialVersionUID = 1L;
  
  private final Expression expression;
  
  private final LocalVariableConversion conversion;
  
  public JoinPredecessorExpression() {
    this(null);
  }
  
  public JoinPredecessorExpression(Expression expression) {
    this(expression, null);
  }
  
  private JoinPredecessorExpression(Expression expression, LocalVariableConversion conversion) {
    super((expression == null) ? 0L : expression.getToken(), (expression == null) ? 0 : expression.getStart(), (expression == null) ? 0 : expression.getFinish());
    this.expression = expression;
    this.conversion = conversion;
  }
  
  public JoinPredecessor setLocalVariableConversion(LexicalContext lc, LocalVariableConversion conversion) {
    if (conversion == this.conversion)
      return this; 
    return new JoinPredecessorExpression(this.expression, conversion);
  }
  
  public Type getType() {
    return this.expression.getType();
  }
  
  public boolean isAlwaysFalse() {
    return (this.expression != null && this.expression.isAlwaysFalse());
  }
  
  public boolean isAlwaysTrue() {
    return (this.expression != null && this.expression.isAlwaysTrue());
  }
  
  public Expression getExpression() {
    return this.expression;
  }
  
  public JoinPredecessorExpression setExpression(Expression expression) {
    if (expression == this.expression)
      return this; 
    return new JoinPredecessorExpression(expression, this.conversion);
  }
  
  public LocalVariableConversion getLocalVariableConversion() {
    return this.conversion;
  }
  
  public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterJoinPredecessorExpression(this)) {
      Expression expr = getExpression();
      return visitor.leaveJoinPredecessorExpression((expr == null) ? this : setExpression((Expression)expr.accept(visitor)));
    } 
    return this;
  }
  
  public void toString(StringBuilder sb, boolean printType) {
    if (this.expression != null)
      this.expression.toString(sb, printType); 
    if (this.conversion != null)
      this.conversion.toString(sb); 
  }
}
