package org.openjdk.nashorn.internal.ir.visitor;

import org.openjdk.nashorn.internal.ir.LexicalContext;

public abstract class SimpleNodeVisitor extends NodeVisitor<LexicalContext> {
  public SimpleNodeVisitor() {
    super(new LexicalContext());
  }
}
