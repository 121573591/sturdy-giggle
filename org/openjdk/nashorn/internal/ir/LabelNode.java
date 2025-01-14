package org.openjdk.nashorn.internal.ir;

import org.openjdk.nashorn.internal.ir.annotations.Immutable;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

@Immutable
public final class LabelNode extends LexicalContextStatement implements JoinPredecessor {
  private static final long serialVersionUID = 1L;
  
  private final String labelName;
  
  private final Block body;
  
  private final LocalVariableConversion localVariableConversion;
  
  public LabelNode(int lineNumber, long token, int finish, String labelName, Block body) {
    super(lineNumber, token, finish);
    this.labelName = labelName;
    this.body = body;
    this.localVariableConversion = null;
  }
  
  private LabelNode(LabelNode labelNode, String labelName, Block body, LocalVariableConversion localVariableConversion) {
    super(labelNode);
    this.labelName = labelName;
    this.body = body;
    this.localVariableConversion = localVariableConversion;
  }
  
  public boolean isTerminal() {
    return this.body.isTerminal();
  }
  
  public Node accept(LexicalContext lc, NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterLabelNode(this))
      return visitor.leaveLabelNode(setBody(lc, (Block)this.body.accept(visitor))); 
    return this;
  }
  
  public void toString(StringBuilder sb, boolean printType) {
    sb.append(this.labelName).append(':');
  }
  
  public Block getBody() {
    return this.body;
  }
  
  public LabelNode setBody(LexicalContext lc, Block body) {
    if (this.body == body)
      return this; 
    return Node.<LabelNode>replaceInLexicalContext(lc, this, new LabelNode(this, this.labelName, body, this.localVariableConversion));
  }
  
  public String getLabelName() {
    return this.labelName;
  }
  
  public LocalVariableConversion getLocalVariableConversion() {
    return this.localVariableConversion;
  }
  
  public LabelNode setLocalVariableConversion(LexicalContext lc, LocalVariableConversion localVariableConversion) {
    if (this.localVariableConversion == localVariableConversion)
      return this; 
    return Node.<LabelNode>replaceInLexicalContext(lc, this, new LabelNode(this, this.labelName, this.body, localVariableConversion));
  }
}
