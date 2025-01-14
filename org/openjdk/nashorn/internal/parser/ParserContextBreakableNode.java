package org.openjdk.nashorn.internal.parser;

interface ParserContextBreakableNode extends ParserContextNode {
  boolean isBreakableWithoutLabel();
}
