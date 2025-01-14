package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.LiteralNode;
import org.openjdk.nashorn.internal.parser.Lexer;

final class RegExpLiteralTreeImpl extends ExpressionTreeImpl implements RegExpLiteralTree {
  private final String pattern;
  
  private final String options;
  
  RegExpLiteralTreeImpl(LiteralNode<?> node) {
    super((Expression)node);
    assert node.getValue() instanceof Lexer.RegexToken : "regexp expected";
    Lexer.RegexToken regex = (Lexer.RegexToken)node.getValue();
    this.pattern = regex.getExpression();
    this.options = regex.getOptions();
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.REGEXP_LITERAL;
  }
  
  public String getPattern() {
    return this.pattern;
  }
  
  public String getOptions() {
    return this.options;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitRegExpLiteral(this, data);
  }
}
