package org.openjdk.nashorn.internal.ir;

import org.openjdk.nashorn.internal.codegen.Label;

public interface BreakableNode extends LexicalContextNode, JoinPredecessor, Labels {
  Node ensureUniqueLabels(LexicalContext paramLexicalContext);
  
  boolean isBreakableWithoutLabel();
  
  Label getBreakLabel();
}
