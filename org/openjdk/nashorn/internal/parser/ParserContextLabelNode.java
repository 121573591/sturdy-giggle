package org.openjdk.nashorn.internal.parser;

class ParserContextLabelNode extends ParserContextBaseNode {
  private final String name;
  
  public ParserContextLabelNode(String name) {
    this.name = name;
  }
  
  public String getLabelName() {
    return this.name;
  }
}
