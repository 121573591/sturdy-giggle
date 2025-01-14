package org.openjdk.nashorn.internal.ir;

import java.io.File;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.openjdk.nashorn.internal.runtime.Debug;
import org.openjdk.nashorn.internal.runtime.Source;

public class LexicalContext {
  private LexicalContextNode[] stack = new LexicalContextNode[16];
  
  private int[] flags = new int[16];
  
  private int sp;
  
  public void setFlag(LexicalContextNode node, int flag) {
    if (flag != 0) {
      assert flag != 1 || !(node instanceof Block);
      for (int i = this.sp - 1; i >= 0; i--) {
        if (this.stack[i] == node) {
          this.flags[i] = this.flags[i] | flag;
          return;
        } 
      } 
    } 
    assert false;
  }
  
  public void setBlockNeedsScope(Block block) {
    for (int i = this.sp - 1; i >= 0; i--) {
      if (this.stack[i] == block) {
        this.flags[i] = this.flags[i] | 0x1;
        for (int j = i - 1; j >= 0; j--) {
          if (this.stack[j] instanceof FunctionNode) {
            this.flags[j] = this.flags[j] | 0x80;
            return;
          } 
        } 
      } 
    } 
    assert false;
  }
  
  public int getFlags(LexicalContextNode node) {
    for (int i = this.sp - 1; i >= 0; i--) {
      if (this.stack[i] == node)
        return this.flags[i]; 
    } 
    throw new AssertionError("flag node not on context stack");
  }
  
  public Block getFunctionBody(FunctionNode functionNode) {
    for (int i = this.sp - 1; i >= 0; i--) {
      if (this.stack[i] == functionNode)
        return (Block)this.stack[i + 1]; 
    } 
    throw new AssertionError(functionNode.getName() + " not on context stack");
  }
  
  public Iterator<LexicalContextNode> getAllNodes() {
    return new NodeIterator<>(LexicalContextNode.class);
  }
  
  public FunctionNode getOutermostFunction() {
    return (FunctionNode)this.stack[0];
  }
  
  public <T extends LexicalContextNode> T push(T node) {
    assert !contains((LexicalContextNode)node);
    if (this.sp == this.stack.length) {
      LexicalContextNode[] newStack = new LexicalContextNode[this.sp * 2];
      System.arraycopy(this.stack, 0, newStack, 0, this.sp);
      this.stack = newStack;
      int[] newFlags = new int[this.sp * 2];
      System.arraycopy(this.flags, 0, newFlags, 0, this.sp);
      this.flags = newFlags;
    } 
    this.stack[this.sp] = (LexicalContextNode)node;
    this.flags[this.sp] = 0;
    this.sp++;
    return node;
  }
  
  public boolean isEmpty() {
    return (this.sp == 0);
  }
  
  public int size() {
    return this.sp;
  }
  
  public <T extends Node> T pop(T node) {
    this.sp--;
    LexicalContextNode popped = this.stack[this.sp];
    this.stack[this.sp] = null;
    if (popped instanceof Flags)
      return (T)((Flags<Node>)popped).setFlag(this, this.flags[this.sp]); 
    return (T)popped;
  }
  
  public <T extends LexicalContextNode & Flags<T>> T applyTopFlags(T node) {
    assert node == peek();
    return (T)((Flags)node).setFlag(this, this.flags[this.sp - 1]);
  }
  
  public LexicalContextNode peek() {
    return this.stack[this.sp - 1];
  }
  
  public boolean contains(LexicalContextNode node) {
    for (int i = 0; i < this.sp; i++) {
      if (this.stack[i] == node)
        return true; 
    } 
    return false;
  }
  
  public LexicalContextNode replace(LexicalContextNode oldNode, LexicalContextNode newNode) {
    for (int i = this.sp - 1; i >= 0; i--) {
      if (this.stack[i] == oldNode) {
        assert i == this.sp - 1 : "violation of contract - we always expect to find the replacement node on top of the lexical context stack: " + newNode + " has " + this.stack[i + true].getClass() + " above it";
        this.stack[i] = newNode;
        break;
      } 
    } 
    return newNode;
  }
  
  public Iterator<Block> getBlocks() {
    return new NodeIterator<>(Block.class);
  }
  
  public Iterator<FunctionNode> getFunctions() {
    return new NodeIterator<>(FunctionNode.class);
  }
  
  public Block getParentBlock() {
    Iterator<Block> iter = new NodeIterator<>(Block.class, getCurrentFunction());
    iter.next();
    return iter.hasNext() ? iter.next() : null;
  }
  
  public LabelNode getCurrentBlockLabelNode() {
    assert this.stack[this.sp - 1] instanceof Block;
    if (this.sp < 2)
      return null; 
    LexicalContextNode parent = this.stack[this.sp - 2];
    return (parent instanceof LabelNode) ? (LabelNode)parent : null;
  }
  
  public Iterator<Block> getAncestorBlocks(Block block) {
    Iterator<Block> iter = getBlocks();
    while (iter.hasNext()) {
      Block b = iter.next();
      if (block == b)
        return iter; 
    } 
    throw new AssertionError("Block is not on the current lexical context stack");
  }
  
  public Iterator<Block> getBlocks(final Block block) {
    final Iterator<Block> iter = getAncestorBlocks(block);
    return new Iterator<Block>() {
        boolean blockReturned = false;
        
        public boolean hasNext() {
          return (iter.hasNext() || !this.blockReturned);
        }
        
        public Block next() {
          if (this.blockReturned)
            return iter.next(); 
          this.blockReturned = true;
          return block;
        }
        
        public void remove() {
          throw new UnsupportedOperationException();
        }
      };
  }
  
  public FunctionNode getFunction(Block block) {
    Iterator<LexicalContextNode> iter = new NodeIterator<>(LexicalContextNode.class);
    while (iter.hasNext()) {
      LexicalContextNode next = iter.next();
      if (next == block)
        while (iter.hasNext()) {
          LexicalContextNode next2 = iter.next();
          if (next2 instanceof FunctionNode)
            return (FunctionNode)next2; 
        }  
    } 
    assert false;
    return null;
  }
  
  public Block getCurrentBlock() {
    return getBlocks().next();
  }
  
  public FunctionNode getCurrentFunction() {
    for (int i = this.sp - 1; i >= 0; i--) {
      if (this.stack[i] instanceof FunctionNode)
        return (FunctionNode)this.stack[i]; 
    } 
    return null;
  }
  
  public Block getDefiningBlock(Symbol symbol) {
    String name = symbol.getName();
    for (Iterator<Block> it = getBlocks(); it.hasNext(); ) {
      Block next = it.next();
      if (next.getExistingSymbol(name) == symbol)
        return next; 
    } 
    throw new AssertionError("Couldn't find symbol " + name + " in the context");
  }
  
  public FunctionNode getDefiningFunction(Symbol symbol) {
    String name = symbol.getName();
    for (Iterator<LexicalContextNode> iter = new NodeIterator<>(LexicalContextNode.class); iter.hasNext(); ) {
      LexicalContextNode next = iter.next();
      if (next instanceof Block && ((Block)next).getExistingSymbol(name) == symbol) {
        while (iter.hasNext()) {
          LexicalContextNode next2 = iter.next();
          if (next2 instanceof FunctionNode)
            return (FunctionNode)next2; 
        } 
        throw new AssertionError("Defining block for symbol " + name + " has no function in the context");
      } 
    } 
    throw new AssertionError("Couldn't find symbol " + name + " in the context");
  }
  
  public boolean isFunctionBody() {
    return (getParentBlock() == null);
  }
  
  public boolean isSplitBody() {
    return (this.sp >= 2 && this.stack[this.sp - 1] instanceof Block && this.stack[this.sp - 2] instanceof SplitNode);
  }
  
  public FunctionNode getParentFunction(FunctionNode functionNode) {
    Iterator<FunctionNode> iter = new NodeIterator<>(FunctionNode.class);
    while (iter.hasNext()) {
      FunctionNode next = iter.next();
      if (next == functionNode)
        return iter.hasNext() ? iter.next() : null; 
    } 
    assert false;
    return null;
  }
  
  public int getScopeNestingLevelTo(LexicalContextNode until) {
    assert until != null;
    int n = 0;
    for (Iterator<LexicalContextNode> iter = getAllNodes(); iter.hasNext(); ) {
      LexicalContextNode node = iter.next();
      if (node == until)
        break; 
      assert !(node instanceof FunctionNode);
      if (node instanceof WithNode || (node instanceof Block && ((Block)node).needsScope()))
        n++; 
    } 
    return n;
  }
  
  private BreakableNode getBreakable() {
    for (NodeIterator<BreakableNode> iter = new NodeIterator<>(BreakableNode.class, getCurrentFunction()); iter.hasNext(); ) {
      BreakableNode next = iter.next();
      if (next.isBreakableWithoutLabel())
        return next; 
    } 
    return null;
  }
  
  public boolean inLoop() {
    return (getCurrentLoop() != null);
  }
  
  public LoopNode getCurrentLoop() {
    Iterator<LoopNode> iter = new NodeIterator<>(LoopNode.class, getCurrentFunction());
    return iter.hasNext() ? iter.next() : null;
  }
  
  public BreakableNode getBreakable(String labelName) {
    if (labelName != null) {
      LabelNode foundLabel = findLabel(labelName);
      if (foundLabel != null) {
        BreakableNode breakable = null;
        for (NodeIterator<BreakableNode> iter = new NodeIterator<>(BreakableNode.class, foundLabel); iter.hasNext();)
          breakable = iter.next(); 
        return breakable;
      } 
      return null;
    } 
    return getBreakable();
  }
  
  private LoopNode getContinueTo() {
    return getCurrentLoop();
  }
  
  public LoopNode getContinueTo(String labelName) {
    if (labelName != null) {
      LabelNode foundLabel = findLabel(labelName);
      if (foundLabel != null) {
        LoopNode loop = null;
        for (NodeIterator<LoopNode> iter = new NodeIterator<>(LoopNode.class, foundLabel); iter.hasNext();)
          loop = iter.next(); 
        return loop;
      } 
      return null;
    } 
    return getContinueTo();
  }
  
  public Block getInlinedFinally(String labelName) {
    for (NodeIterator<TryNode> iter = new NodeIterator<>(TryNode.class); iter.hasNext(); ) {
      Block inlinedFinally = ((TryNode)iter.next()).getInlinedFinally(labelName);
      if (inlinedFinally != null)
        return inlinedFinally; 
    } 
    return null;
  }
  
  public TryNode getTryNodeForInlinedFinally(String labelName) {
    for (NodeIterator<TryNode> iter = new NodeIterator<>(TryNode.class); iter.hasNext(); ) {
      TryNode tryNode = iter.next();
      if (tryNode.getInlinedFinally(labelName) != null)
        return tryNode; 
    } 
    return null;
  }
  
  private LabelNode findLabel(String name) {
    for (Iterator<LabelNode> iter = new NodeIterator<>(LabelNode.class, getCurrentFunction()); iter.hasNext(); ) {
      LabelNode next = iter.next();
      if (next.getLabelName().equals(name))
        return next; 
    } 
    return null;
  }
  
  public boolean isExternalTarget(SplitNode splitNode, BreakableNode target) {
    for (int i = this.sp; i-- > 0; ) {
      LexicalContextNode next = this.stack[i];
      if (next == splitNode)
        return true; 
      if (next == target)
        return false; 
      if (next instanceof TryNode)
        for (Block inlinedFinally : ((TryNode)next).getInlinedFinallies()) {
          if (TryNode.getLabelledInlinedFinallyBlock(inlinedFinally) == target)
            return false; 
        }  
    } 
    throw new AssertionError("" + target + " was expected in lexical context " + target + " but wasn't");
  }
  
  public boolean inUnprotectedSwitchContext() {
    for (int i = this.sp - 1; i > 0; i--) {
      LexicalContextNode next = this.stack[i];
      if (next instanceof Block)
        return this.stack[i - 1] instanceof SwitchNode; 
    } 
    return false;
  }
  
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("[ ");
    for (int i = 0; i < this.sp; i++) {
      Object node = this.stack[i];
      sb.append(node.getClass().getSimpleName());
      sb.append('@');
      sb.append(Debug.id(node));
      sb.append(':');
      if (node instanceof FunctionNode) {
        FunctionNode fn = (FunctionNode)node;
        Source source = fn.getSource();
        String src = source.toString();
        if (src.contains(File.pathSeparator))
          src = src.substring(src.lastIndexOf(File.pathSeparator)); 
        src = src + " ";
        src = src + src;
        sb.append(src);
      } 
      sb.append(' ');
    } 
    sb.append(" ==> ]");
    return sb.toString();
  }
  
  private class NodeIterator<T extends LexicalContextNode> implements Iterator<T> {
    private int index;
    
    private T next;
    
    private final Class<T> clazz;
    
    private LexicalContextNode until;
    
    NodeIterator(Class<T> clazz) {
      this(clazz, null);
    }
    
    NodeIterator(Class<T> clazz, LexicalContextNode until) {
      this.index = LexicalContext.this.sp - 1;
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
        Object node = LexicalContext.this.stack[i];
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
