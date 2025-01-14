package org.openjdk.nashorn.internal.ir;

import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

abstract class LexicalContextExpression extends Expression implements LexicalContextNode {
  private static final long serialVersionUID = 1L;
  
  LexicalContextExpression(LexicalContextExpression expr) {
    super(expr);
  }
  
  LexicalContextExpression(long token, int start, int finish) {
    super(token, start, finish);
  }
  
  LexicalContextExpression(long token, int finish) {
    super(token, finish);
  }
  
  public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
    return LexicalContextNode.Acceptor.accept(this, visitor);
  }
}
