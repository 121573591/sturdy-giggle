package org.openjdk.nashorn.internal.ir;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import org.openjdk.nashorn.internal.codegen.types.Type;

public class OptimisticLexicalContext extends LexicalContext {
  private final boolean isEnabled;
  
  static class Assumption {
    Symbol symbol;
    
    Type type;
    
    Assumption(Symbol symbol, Type type) {
      this.symbol = symbol;
      this.type = type;
    }
    
    public String toString() {
      return this.symbol.getName() + "=" + this.symbol.getName();
    }
  }
  
  private final Deque<List<Assumption>> optimisticAssumptions = new ArrayDeque<>();
  
  public OptimisticLexicalContext(boolean isEnabled) {
    this.isEnabled = isEnabled;
  }
  
  public boolean isEnabled() {
    return this.isEnabled;
  }
  
  public void logOptimisticAssumption(Symbol symbol, Type type) {
    if (this.isEnabled) {
      List<Assumption> peek = this.optimisticAssumptions.peek();
      peek.add(new Assumption(symbol, type));
    } 
  }
  
  public List<Assumption> getOptimisticAssumptions() {
    return Collections.unmodifiableList(this.optimisticAssumptions.peek());
  }
  
  public boolean hasOptimisticAssumptions() {
    return (!this.optimisticAssumptions.isEmpty() && !getOptimisticAssumptions().isEmpty());
  }
  
  public <T extends LexicalContextNode> T push(T node) {
    if (this.isEnabled && 
      node instanceof FunctionNode)
      this.optimisticAssumptions.push(new ArrayList<>()); 
    return super.push(node);
  }
  
  public <T extends Node> T pop(T node) {
    T popped = super.pop(node);
    if (this.isEnabled && 
      node instanceof FunctionNode)
      this.optimisticAssumptions.pop(); 
    return popped;
  }
}
