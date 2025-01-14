package org.openjdk.nashorn.internal.ir;

import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

public interface LexicalContextNode {
  Node accept(LexicalContext paramLexicalContext, NodeVisitor<? extends LexicalContext> paramNodeVisitor);
  
  public static class Acceptor {
    static Node accept(LexicalContextNode node, NodeVisitor<? extends LexicalContext> visitor) {
      LexicalContext lc = visitor.getLexicalContext();
      lc.push(node);
      Node newNode = node.accept(lc, visitor);
      return lc.pop(newNode);
    }
  }
}
