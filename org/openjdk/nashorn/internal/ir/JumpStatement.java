package org.openjdk.nashorn.internal.ir;

import org.openjdk.nashorn.internal.codegen.Label;

public abstract class JumpStatement extends Statement implements JoinPredecessor {
  private static final long serialVersionUID = 1L;
  
  private final String labelName;
  
  private final LocalVariableConversion conversion;
  
  protected JumpStatement(int lineNumber, long token, int finish, String labelName) {
    super(lineNumber, token, finish);
    this.labelName = labelName;
    this.conversion = null;
  }
  
  protected JumpStatement(JumpStatement jumpStatement, LocalVariableConversion conversion) {
    super(jumpStatement);
    this.labelName = jumpStatement.labelName;
    this.conversion = conversion;
  }
  
  public boolean hasGoto() {
    return true;
  }
  
  public String getLabelName() {
    return this.labelName;
  }
  
  public void toString(StringBuilder sb, boolean printType) {
    sb.append(getStatementName());
    if (this.labelName != null)
      sb.append(' ').append(this.labelName); 
  }
  
  public Label getTargetLabel(LexicalContext lc) {
    return getTargetLabel(getTarget(lc));
  }
  
  public LexicalContextNode getPopScopeLimit(LexicalContext lc) {
    return getTarget(lc);
  }
  
  public JumpStatement setLocalVariableConversion(LexicalContext lc, LocalVariableConversion conversion) {
    if (this.conversion == conversion)
      return this; 
    return createNewJumpStatement(conversion);
  }
  
  public LocalVariableConversion getLocalVariableConversion() {
    return this.conversion;
  }
  
  abstract String getStatementName();
  
  public abstract BreakableNode getTarget(LexicalContext paramLexicalContext);
  
  abstract Label getTargetLabel(BreakableNode paramBreakableNode);
  
  abstract JumpStatement createNewJumpStatement(LocalVariableConversion paramLocalVariableConversion);
}
