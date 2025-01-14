package org.openjdk.nashorn.internal.ir;

public interface JoinPredecessor {
  JoinPredecessor setLocalVariableConversion(LexicalContext paramLexicalContext, LocalVariableConversion paramLocalVariableConversion);
  
  LocalVariableConversion getLocalVariableConversion();
}
