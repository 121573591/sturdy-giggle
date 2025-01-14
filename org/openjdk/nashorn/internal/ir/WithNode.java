package org.openjdk.nashorn.internal.ir;

import org.openjdk.nashorn.internal.ir.annotations.Immutable;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

@Immutable
public final class WithNode extends LexicalContextStatement {
  private static final long serialVersionUID = 1L;
  
  private final Expression expression;
  
  private final Block body;
  
  public WithNode(int lineNumber, long token, int finish, Expression expression, Block body) {
    super(lineNumber, token, finish);
    this.expression = expression;
    this.body = body;
  }
  
  private WithNode(WithNode node, Expression expression, Block body) {
    super(node);
    this.expression = expression;
    this.body = body;
  }
  
  public Node accept(LexicalContext lc, NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterWithNode(this))
      return visitor.leaveWithNode(
          setExpression(lc, (Expression)this.expression.accept(visitor))
          .setBody(lc, (Block)this.body.accept(visitor))); 
    return this;
  }
  
  public boolean isTerminal() {
    return this.body.isTerminal();
  }
  
  public void toString(StringBuilder sb, boolean printType) {
    sb.append("with (");
    this.expression.toString(sb, printType);
    sb.append(')');
  }
  
  public Block getBody() {
    return this.body;
  }
  
  public WithNode setBody(LexicalContext lc, Block body) {
    if (this.body == body)
      return this; 
    return Node.<WithNode>replaceInLexicalContext(lc, this, new WithNode(this, this.expression, body));
  }
  
  public Expression getExpression() {
    return this.expression;
  }
  
  public WithNode setExpression(LexicalContext lc, Expression expression) {
    if (this.expression == expression)
      return this; 
    return Node.<WithNode>replaceInLexicalContext(lc, this, new WithNode(this, expression, this.body));
  }
}
