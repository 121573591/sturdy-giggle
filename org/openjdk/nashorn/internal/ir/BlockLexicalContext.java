package org.openjdk.nashorn.internal.ir;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BlockLexicalContext extends LexicalContext {
  private final Deque<List<Statement>> sstack = new ArrayDeque<>();
  
  protected Statement lastStatement;
  
  public <T extends LexicalContextNode> T push(T node) {
    T pushed = super.push(node);
    if (node instanceof Block)
      this.sstack.push(new ArrayList<>()); 
    return pushed;
  }
  
  protected List<Statement> popStatements() {
    return this.sstack.pop();
  }
  
  protected Block afterSetStatements(Block block) {
    return block;
  }
  
  public <T extends Node> T pop(T node) {
    Block block;
    T expected = node;
    if (node instanceof Block) {
      List<Statement> newStatements = popStatements();
      block = ((Block)node).setStatements(this, newStatements);
      block = afterSetStatements(block);
      if (!this.sstack.isEmpty())
        this.lastStatement = lastStatement(this.sstack.peek()); 
    } 
    return super.pop((T)block);
  }
  
  public void appendStatement(Statement statement) {
    assert statement != null;
    ((List<Statement>)this.sstack.peek()).add(statement);
    this.lastStatement = statement;
  }
  
  public Node prependStatement(Statement statement) {
    assert statement != null;
    ((List<Statement>)this.sstack.peek()).add(0, statement);
    return statement;
  }
  
  public void prependStatements(List<Statement> statements) {
    assert statements != null;
    ((List<Statement>)this.sstack.peek()).addAll(0, statements);
  }
  
  public Statement getLastStatement() {
    return this.lastStatement;
  }
  
  private static Statement lastStatement(List<Statement> statements) {
    int s = statements.size();
    return (s == 0) ? null : statements.get(s - 1);
  }
}
