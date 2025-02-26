package org.openjdk.nashorn.internal.ir;

import java.util.Collections;
import java.util.List;
import org.openjdk.nashorn.internal.codegen.Label;
import org.openjdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
abstract class BreakableStatement extends LexicalContextStatement implements BreakableNode {
  private static final long serialVersionUID = 1L;
  
  protected final Label breakLabel;
  
  final LocalVariableConversion conversion;
  
  protected BreakableStatement(int lineNumber, long token, int finish, Label breakLabel) {
    super(lineNumber, token, finish);
    this.breakLabel = breakLabel;
    this.conversion = null;
  }
  
  protected BreakableStatement(BreakableStatement breakableNode, LocalVariableConversion conversion) {
    super(breakableNode);
    this.breakLabel = new Label(breakableNode.getBreakLabel());
    this.conversion = conversion;
  }
  
  public boolean isBreakableWithoutLabel() {
    return true;
  }
  
  public Label getBreakLabel() {
    return this.breakLabel;
  }
  
  public List<Label> getLabels() {
    return Collections.unmodifiableList(Collections.singletonList(this.breakLabel));
  }
  
  public JoinPredecessor setLocalVariableConversion(LexicalContext lc, LocalVariableConversion conversion) {
    if (this.conversion == conversion)
      return this; 
    return setLocalVariableConversionChanged(lc, conversion);
  }
  
  public LocalVariableConversion getLocalVariableConversion() {
    return this.conversion;
  }
  
  abstract JoinPredecessor setLocalVariableConversionChanged(LexicalContext paramLexicalContext, LocalVariableConversion paramLocalVariableConversion);
}
