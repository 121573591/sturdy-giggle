package org.openjdk.nashorn.internal.ir;

import java.util.List;
import org.openjdk.nashorn.internal.codegen.Label;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

public abstract class LoopNode extends BreakableStatement {
  private static final long serialVersionUID = 1L;
  
  protected final Label continueLabel;
  
  protected final JoinPredecessorExpression test;
  
  protected final Block body;
  
  protected final boolean controlFlowEscapes;
  
  protected LoopNode(int lineNumber, long token, int finish, Block body, JoinPredecessorExpression test, boolean controlFlowEscapes) {
    super(lineNumber, token, finish, new Label("while_break"));
    this.continueLabel = new Label("while_continue");
    this.body = body;
    this.controlFlowEscapes = controlFlowEscapes;
    this.test = test;
  }
  
  protected LoopNode(LoopNode loopNode, JoinPredecessorExpression test, Block body, boolean controlFlowEscapes, LocalVariableConversion conversion) {
    super(loopNode, conversion);
    this.continueLabel = new Label(loopNode.continueLabel);
    this.test = test;
    this.body = body;
    this.controlFlowEscapes = controlFlowEscapes;
  }
  
  public boolean controlFlowEscapes() {
    return this.controlFlowEscapes;
  }
  
  public boolean isTerminal() {
    if (!mustEnter())
      return false; 
    if (this.controlFlowEscapes)
      return false; 
    if (this.body.isTerminal())
      return true; 
    return (this.test == null);
  }
  
  public Label getContinueLabel() {
    return this.continueLabel;
  }
  
  public List<Label> getLabels() {
    return List.of(this.breakLabel, this.continueLabel);
  }
  
  public boolean isLoop() {
    return true;
  }
  
  public final JoinPredecessorExpression getTest() {
    return this.test;
  }
  
  public abstract Node ensureUniqueLabels(LexicalContext paramLexicalContext);
  
  public abstract boolean mustEnter();
  
  public abstract Block getBody();
  
  public abstract LoopNode setBody(LexicalContext paramLexicalContext, Block paramBlock);
  
  public abstract LoopNode setTest(LexicalContext paramLexicalContext, JoinPredecessorExpression paramJoinPredecessorExpression);
  
  public abstract LoopNode setControlFlowEscapes(LexicalContext paramLexicalContext, boolean paramBoolean);
  
  public abstract boolean hasPerIterationScope();
}
