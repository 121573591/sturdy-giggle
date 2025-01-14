package org.openjdk.nashorn.internal.ir;

import org.openjdk.nashorn.internal.codegen.Label;
import org.openjdk.nashorn.internal.ir.annotations.Immutable;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

@Immutable
public class ContinueNode extends JumpStatement {
  private static final long serialVersionUID = 1L;
  
  public ContinueNode(int lineNumber, long token, int finish, String labelName) {
    super(lineNumber, token, finish, labelName);
  }
  
  private ContinueNode(ContinueNode continueNode, LocalVariableConversion conversion) {
    super(continueNode, conversion);
  }
  
  public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterContinueNode(this))
      return visitor.leaveContinueNode(this); 
    return this;
  }
  
  JumpStatement createNewJumpStatement(LocalVariableConversion conversion) {
    return new ContinueNode(this, conversion);
  }
  
  String getStatementName() {
    return "continue";
  }
  
  public BreakableNode getTarget(LexicalContext lc) {
    return lc.getContinueTo(getLabelName());
  }
  
  Label getTargetLabel(BreakableNode target) {
    return ((LoopNode)target).getContinueLabel();
  }
}
