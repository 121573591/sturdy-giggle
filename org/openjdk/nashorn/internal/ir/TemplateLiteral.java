package org.openjdk.nashorn.internal.ir;

import java.util.Collections;
import java.util.List;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

public final class TemplateLiteral extends Expression {
  private static final long serialVersionUID = 1L;
  
  private final List<Expression> exprs;
  
  public TemplateLiteral(List<Expression> exprs) {
    super(((Expression)exprs.get(0)).getToken(), ((Expression)exprs.get(exprs.size() - 1)).finish);
    this.exprs = exprs;
  }
  
  public Type getType() {
    return Type.STRING;
  }
  
  public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterTemplateLiteral(this))
      return visitor.leaveTemplateLiteral(this); 
    return this;
  }
  
  public void toString(StringBuilder sb, boolean printType) {
    for (Expression expr : this.exprs)
      sb.append(expr); 
  }
  
  public List<Expression> getExpressions() {
    return Collections.unmodifiableList(this.exprs);
  }
}
