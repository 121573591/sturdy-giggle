package org.openjdk.nashorn.internal.parser;

class ParserContextBlockNode extends ParserContextBaseNode implements ParserContextBreakableNode {
  private final long token;
  
  public ParserContextBlockNode(long token) {
    this.token = token;
  }
  
  public boolean isBreakableWithoutLabel() {
    return false;
  }
  
  public long getToken() {
    return this.token;
  }
}
