package org.openjdk.nashorn.internal.ir;

import java.util.Objects;
import org.openjdk.nashorn.internal.codegen.Label;
import org.openjdk.nashorn.internal.ir.annotations.Immutable;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

@Immutable
public final class JumpToInlinedFinally extends JumpStatement {
  private static final long serialVersionUID = 1L;
  
  public JumpToInlinedFinally(String labelName) {
    super(-1, 0L, 0, Objects.<String>requireNonNull(labelName));
  }
  
  private JumpToInlinedFinally(JumpToInlinedFinally breakNode, LocalVariableConversion conversion) {
    super(breakNode, conversion);
  }
  
  public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterJumpToInlinedFinally(this))
      return visitor.leaveJumpToInlinedFinally(this); 
    return this;
  }
  
  JumpStatement createNewJumpStatement(LocalVariableConversion conversion) {
    return new JumpToInlinedFinally(this, conversion);
  }
  
  String getStatementName() {
    return ":jumpToInlinedFinally";
  }
  
  public Block getTarget(LexicalContext lc) {
    return lc.getInlinedFinally(getLabelName());
  }
  
  public TryNode getPopScopeLimit(LexicalContext lc) {
    return lc.getTryNodeForInlinedFinally(getLabelName());
  }
  
  Label getTargetLabel(BreakableNode target) {
    assert target != null;
    return ((Block)target).getEntryLabel();
  }
}
