package org.openjdk.nashorn.internal.ir;

import org.openjdk.nashorn.internal.codegen.Label;
import org.openjdk.nashorn.internal.ir.annotations.Immutable;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

@Immutable
public final class BreakNode extends JumpStatement {
  private static final long serialVersionUID = 1L;
  
  public BreakNode(int lineNumber, long token, int finish, String labelName) {
    super(lineNumber, token, finish, labelName);
  }
  
  private BreakNode(BreakNode breakNode, LocalVariableConversion conversion) {
    super(breakNode, conversion);
  }
  
  public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterBreakNode(this))
      return visitor.leaveBreakNode(this); 
    return this;
  }
  
  JumpStatement createNewJumpStatement(LocalVariableConversion conversion) {
    return new BreakNode(this, conversion);
  }
  
  String getStatementName() {
    return "break";
  }
  
  public BreakableNode getTarget(LexicalContext lc) {
    return lc.getBreakable(getLabelName());
  }
  
  Label getTargetLabel(BreakableNode target) {
    return target.getBreakLabel();
  }
}
