package org.openjdk.nashorn.internal.ir;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.parser.Token;
import org.openjdk.nashorn.internal.parser.TokenType;

public abstract class Node implements Cloneable, Serializable {
  private static final long serialVersionUID = 1L;
  
  public static final int NO_LINE_NUMBER = -1;
  
  public static final long NO_TOKEN = 0L;
  
  public static final int NO_FINISH = 0;
  
  protected final int start;
  
  protected final int finish;
  
  private final long token;
  
  public Node(long token, int finish) {
    this.token = token;
    this.start = Token.descPosition(token);
    this.finish = finish;
  }
  
  protected Node(long token, int start, int finish) {
    this.start = start;
    this.finish = finish;
    this.token = token;
  }
  
  protected Node(Node node) {
    this.token = node.token;
    this.start = node.start;
    this.finish = node.finish;
  }
  
  protected Node(Node node, int finish) {
    this.token = node.token;
    this.start = node.start;
    this.finish = finish;
  }
  
  public boolean isLoop() {
    return false;
  }
  
  public boolean isAssignment() {
    return false;
  }
  
  public Node ensureUniqueLabels(LexicalContext lc) {
    return this;
  }
  
  public abstract Node accept(NodeVisitor<? extends LexicalContext> paramNodeVisitor);
  
  public final String toString() {
    return toString(true);
  }
  
  public final String toString(boolean includeTypeInfo) {
    StringBuilder sb = new StringBuilder();
    toString(sb, includeTypeInfo);
    return sb.toString();
  }
  
  public void toString(StringBuilder sb) {
    toString(sb, true);
  }
  
  public abstract void toString(StringBuilder paramStringBuilder, boolean paramBoolean);
  
  public int getFinish() {
    return this.finish;
  }
  
  public int getStart() {
    return this.start;
  }
  
  public int getSourceOrder() {
    return getStart();
  }
  
  protected Object clone() {
    try {
      return super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    } 
  }
  
  public final boolean equals(Object other) {
    return (this == other);
  }
  
  public final int hashCode() {
    return Long.hashCode(this.token);
  }
  
  public int position() {
    return Token.descPosition(this.token);
  }
  
  public int length() {
    return Token.descLength(this.token);
  }
  
  public TokenType tokenType() {
    return Token.descType(this.token);
  }
  
  public boolean isTokenType(TokenType type) {
    return (tokenType() == type);
  }
  
  public long getToken() {
    return this.token;
  }
  
  static <T extends Node> List<T> accept(NodeVisitor<? extends LexicalContext> visitor, List<T> list) {
    int size = list.size();
    if (size == 0)
      return list; 
    List<T> newList = null;
    for (int i = 0; i < size; i++) {
      Node node1 = (Node)list.get(i);
      Node node2 = (node1 == null) ? null : node1.accept(visitor);
      if (node2 != node1) {
        if (newList == null) {
          newList = new ArrayList<>(size);
          for (int j = 0; j < i; j++)
            newList.add(list.get(j)); 
        } 
        newList.add((T)node2);
      } else if (newList != null) {
        newList.add((T)node1);
      } 
    } 
    return (newList == null) ? list : newList;
  }
  
  static <T extends LexicalContextNode> T replaceInLexicalContext(LexicalContext lc, T oldNode, T newNode) {
    if (lc != null)
      lc.replace((LexicalContextNode)oldNode, (LexicalContextNode)newNode); 
    return newNode;
  }
}
