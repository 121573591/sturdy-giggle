package org.openjdk.nashorn.internal.parser;

class ParserContextLoopNode extends ParserContextBaseNode implements ParserContextBreakableNode {
  public boolean isBreakableWithoutLabel() {
    return true;
  }
}
