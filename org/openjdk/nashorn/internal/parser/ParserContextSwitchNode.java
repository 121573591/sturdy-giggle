package org.openjdk.nashorn.internal.parser;

class ParserContextSwitchNode extends ParserContextBaseNode implements ParserContextBreakableNode {
  public boolean isBreakableWithoutLabel() {
    return true;
  }
}
