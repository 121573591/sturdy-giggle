package org.openjdk.nashorn.internal.parser;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.openjdk.nashorn.internal.ir.Statement;

class ParserContext {
  private static final int INITIAL_DEPTH = 16;
  
  private int sp = 0;
  
  private ParserContextNode[] stack = new ParserContextNode[16];
  
  public <T extends ParserContextNode> T push(T node) {
    assert !contains((ParserContextNode)node);
    if (this.sp == this.stack.length) {
      ParserContextNode[] newStack = new ParserContextNode[this.sp * 2];
      System.arraycopy(this.stack, 0, newStack, 0, this.sp);
      this.stack = newStack;
    } 
    this.stack[this.sp] = (ParserContextNode)node;
    this.sp++;
    return node;
  }
  
  public ParserContextNode peek() {
    return this.stack[this.sp - 1];
  }
  
  public <T extends ParserContextNode> T pop(T node) {
    this.sp--;
    ParserContextNode parserContextNode = this.stack[this.sp];
    this.stack[this.sp] = null;
    assert node == parserContextNode;
    return (T)parserContextNode;
  }
  
  public boolean contains(ParserContextNode node) {
    for (int i = 0; i < this.sp; i++) {
      if (this.stack[i] == node)
        return true; 
    } 
    return false;
  }
  
  private ParserContextBreakableNode getBreakable() {
    for (NodeIterator<ParserContextBreakableNode> iter = new NodeIterator<>(ParserContextBreakableNode.class, getCurrentFunction()); iter.hasNext(); ) {
      ParserContextBreakableNode next = iter.next();
      if (next.isBreakableWithoutLabel())
        return next; 
    } 
    return null;
  }
  
  public ParserContextBreakableNode getBreakable(String labelName) {
    if (labelName != null) {
      ParserContextLabelNode foundLabel = findLabel(labelName);
      if (foundLabel != null) {
        ParserContextBreakableNode breakable = null;
        for (NodeIterator<ParserContextBreakableNode> iter = new NodeIterator<>(ParserContextBreakableNode.class, foundLabel); iter.hasNext();)
          breakable = iter.next(); 
        return breakable;
      } 
      return null;
    } 
    return getBreakable();
  }
  
  public ParserContextLoopNode getCurrentLoop() {
    Iterator<ParserContextLoopNode> iter = new NodeIterator<>(ParserContextLoopNode.class, getCurrentFunction());
    return iter.hasNext() ? iter.next() : null;
  }
  
  private ParserContextLoopNode getContinueTo() {
    return getCurrentLoop();
  }
  
  public ParserContextLoopNode getContinueTo(String labelName) {
    if (labelName != null) {
      ParserContextLabelNode foundLabel = findLabel(labelName);
      if (foundLabel != null) {
        ParserContextLoopNode loop = null;
        for (NodeIterator<ParserContextLoopNode> iter = new NodeIterator<>(ParserContextLoopNode.class, foundLabel); iter.hasNext();)
          loop = iter.next(); 
        return loop;
      } 
      return null;
    } 
    return getContinueTo();
  }
  
  public ParserContextBlockNode getFunctionBody(ParserContextFunctionNode functionNode) {
    for (int i = this.sp - 1; i >= 0; i--) {
      if (this.stack[i] == functionNode)
        return (ParserContextBlockNode)this.stack[i + 1]; 
    } 
    throw new AssertionError(functionNode.getName() + " not on context stack");
  }
  
  public ParserContextLabelNode findLabel(String name) {
    for (Iterator<ParserContextLabelNode> iter = new NodeIterator<>(ParserContextLabelNode.class, getCurrentFunction()); iter.hasNext(); ) {
      ParserContextLabelNode next = iter.next();
      if (next.getLabelName().equals(name))
        return next; 
    } 
    return null;
  }
  
  public void prependStatementToCurrentNode(Statement statement) {
    assert statement != null;
    this.stack[this.sp - 1].prependStatement(statement);
  }
  
  public void appendStatementToCurrentNode(Statement statement) {
    assert statement != null;
    this.stack[this.sp - 1].appendStatement(statement);
  }
  
  public ParserContextFunctionNode getCurrentFunction() {
    for (int i = this.sp - 1; i >= 0; i--) {
      if (this.stack[i] instanceof ParserContextFunctionNode)
        return (ParserContextFunctionNode)this.stack[i]; 
    } 
    return null;
  }
  
  public Iterator<ParserContextBlockNode> getBlocks() {
    return new NodeIterator<>(ParserContextBlockNode.class);
  }
  
  public ParserContextBlockNode getCurrentBlock() {
    return getBlocks().next();
  }
  
  public Statement getLastStatement() {
    if (this.sp == 0)
      return null; 
    ParserContextNode top = this.stack[this.sp - 1];
    int s = top.getStatements().size();
    return (s == 0) ? null : top.getStatements().get(s - 1);
  }
  
  public Iterator<ParserContextFunctionNode> getFunctions() {
    return new NodeIterator<>(ParserContextFunctionNode.class);
  }
  
  public ParserContextModuleNode getCurrentModule() {
    Iterator<ParserContextModuleNode> iter = new NodeIterator<>(ParserContextModuleNode.class, getCurrentFunction());
    return iter.hasNext() ? iter.next() : null;
  }
  
  private class NodeIterator<T extends ParserContextNode> implements Iterator<T> {
    private int index;
    
    private T next;
    
    private final Class<T> clazz;
    
    private final ParserContextNode until;
    
    NodeIterator(Class<T> clazz) {
      this(clazz, null);
    }
    
    NodeIterator(Class<T> clazz, ParserContextNode until) {
      this.index = ParserContext.this.sp - 1;
      this.clazz = clazz;
      this.until = until;
      this.next = findNext();
    }
    
    public boolean hasNext() {
      return (this.next != null);
    }
    
    public T next() {
      if (this.next == null)
        throw new NoSuchElementException(); 
      T lnext = this.next;
      this.next = findNext();
      return lnext;
    }
    
    private T findNext() {
      for (int i = this.index; i >= 0; i--) {
        Object node = ParserContext.this.stack[i];
        if (node == this.until)
          return null; 
        if (this.clazz.isAssignableFrom(node.getClass())) {
          this.index = i - 1;
          return (T)node;
        } 
      } 
      return null;
    }
    
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
