package org.openjdk.nashorn.internal.ir;

public interface Flags<T extends LexicalContextNode> {
  int getFlags();
  
  boolean getFlag(int paramInt);
  
  T clearFlag(LexicalContext paramLexicalContext, int paramInt);
  
  T setFlag(LexicalContext paramLexicalContext, int paramInt);
  
  T setFlags(LexicalContext paramLexicalContext, int paramInt);
}
