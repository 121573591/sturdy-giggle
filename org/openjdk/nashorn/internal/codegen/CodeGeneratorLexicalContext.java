package org.openjdk.nashorn.internal.codegen;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import org.openjdk.nashorn.internal.IntDeque;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.Block;
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.LexicalContext;
import org.openjdk.nashorn.internal.ir.LexicalContextNode;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.Symbol;

final class CodeGeneratorLexicalContext extends LexicalContext {
  private int dynamicScopeCount;
  
  private final Map<SharedScopeCall, SharedScopeCall> scopeCalls = new HashMap<>();
  
  private final Deque<CompileUnit> compileUnits = new ArrayDeque<>();
  
  private final Deque<MethodEmitter> methodEmitters = new ArrayDeque<>();
  
  private final Deque<Expression> discard = new ArrayDeque<>();
  
  private final Deque<Map<String, Collection<Label>>> unwarrantedOptimismHandlers = new ArrayDeque<>();
  
  private final Deque<StringBuilder> slotTypesDescriptors = new ArrayDeque<>();
  
  private final IntDeque splitLiterals = new IntDeque();
  
  private int[] nextFreeSlots = new int[16];
  
  private int nextFreeSlotsSize;
  
  private boolean isWithBoundary(Object node) {
    return (node instanceof Block && !isEmpty() && peek() instanceof org.openjdk.nashorn.internal.ir.WithNode);
  }
  
  public <T extends LexicalContextNode> T push(T node) {
    if (isWithBoundary(node)) {
      this.dynamicScopeCount++;
    } else if (node instanceof FunctionNode) {
      if (((FunctionNode)node).inDynamicContext())
        this.dynamicScopeCount++; 
      this.splitLiterals.push(0);
    } 
    return (T)super.push((LexicalContextNode)node);
  }
  
  void enterSplitLiteral() {
    this.splitLiterals.getAndIncrement();
    pushFreeSlots(((MethodEmitter)this.methodEmitters.peek()).getUsedSlotsWithLiveTemporaries());
  }
  
  void exitSplitLiteral() {
    int count = this.splitLiterals.decrementAndGet();
    assert count >= 0;
  }
  
  public <T extends Node> T pop(T node) {
    Node node1 = super.pop((Node)node);
    if (isWithBoundary(node)) {
      this.dynamicScopeCount--;
      assert this.dynamicScopeCount >= 0;
    } else if (node instanceof FunctionNode) {
      if (((FunctionNode)node).inDynamicContext()) {
        this.dynamicScopeCount--;
        assert this.dynamicScopeCount >= 0;
      } 
      assert this.splitLiterals.peek() == 0;
      this.splitLiterals.pop();
    } 
    return (T)node1;
  }
  
  boolean inDynamicScope() {
    return (this.dynamicScopeCount > 0);
  }
  
  boolean inSplitLiteral() {
    return (!this.splitLiterals.isEmpty() && this.splitLiterals.peek() > 0);
  }
  
  MethodEmitter pushMethodEmitter(MethodEmitter newMethod) {
    this.methodEmitters.push(newMethod);
    return newMethod;
  }
  
  MethodEmitter popMethodEmitter(MethodEmitter oldMethod) {
    assert this.methodEmitters.peek() == oldMethod;
    this.methodEmitters.pop();
    return this.methodEmitters.isEmpty() ? null : this.methodEmitters.peek();
  }
  
  void pushUnwarrantedOptimismHandlers() {
    this.unwarrantedOptimismHandlers.push(new HashMap<>());
    this.slotTypesDescriptors.push(new StringBuilder());
  }
  
  Map<String, Collection<Label>> getUnwarrantedOptimismHandlers() {
    return this.unwarrantedOptimismHandlers.peek();
  }
  
  Map<String, Collection<Label>> popUnwarrantedOptimismHandlers() {
    this.slotTypesDescriptors.pop();
    return this.unwarrantedOptimismHandlers.pop();
  }
  
  CompileUnit pushCompileUnit(CompileUnit newUnit) {
    this.compileUnits.push(newUnit);
    return newUnit;
  }
  
  CompileUnit popCompileUnit(CompileUnit oldUnit) {
    assert this.compileUnits.peek() == oldUnit;
    CompileUnit unit = this.compileUnits.pop();
    assert unit.hasCode() : "compile unit popped without code";
    unit.setUsed();
    return this.compileUnits.isEmpty() ? null : this.compileUnits.peek();
  }
  
  boolean hasCompileUnits() {
    return !this.compileUnits.isEmpty();
  }
  
  Collection<SharedScopeCall> getScopeCalls() {
    return Collections.unmodifiableCollection(this.scopeCalls.values());
  }
  
  SharedScopeCall getScopeCall(CompileUnit unit, Symbol symbol, Type valueType, Type returnType, Type[] paramTypes, int flags, boolean isOptimistic) {
    SharedScopeCall scopeCall = new SharedScopeCall(symbol, valueType, returnType, paramTypes, flags, isOptimistic);
    if (this.scopeCalls.containsKey(scopeCall))
      return this.scopeCalls.get(scopeCall); 
    scopeCall.setClassAndName(unit, getCurrentFunction().uniqueName(":scopeCall"));
    this.scopeCalls.put(scopeCall, scopeCall);
    return scopeCall;
  }
  
  SharedScopeCall getScopeGet(CompileUnit unit, Symbol symbol, Type valueType, int flags, boolean isOptimistic) {
    return getScopeCall(unit, symbol, valueType, valueType, (Type[])null, flags, isOptimistic);
  }
  
  void onEnterBlock(Block block) {
    pushFreeSlots(assignSlots(block, isFunctionBody() ? 0 : getUsedSlotCount()));
  }
  
  private void pushFreeSlots(int freeSlots) {
    if (this.nextFreeSlotsSize == this.nextFreeSlots.length) {
      int[] newNextFreeSlots = new int[this.nextFreeSlotsSize * 2];
      System.arraycopy(this.nextFreeSlots, 0, newNextFreeSlots, 0, this.nextFreeSlotsSize);
      this.nextFreeSlots = newNextFreeSlots;
    } 
    this.nextFreeSlots[this.nextFreeSlotsSize++] = freeSlots;
  }
  
  int getUsedSlotCount() {
    return this.nextFreeSlots[this.nextFreeSlotsSize - 1];
  }
  
  void releaseSlots() {
    this.nextFreeSlotsSize--;
    int undefinedFromSlot = (this.nextFreeSlotsSize == 0) ? 0 : this.nextFreeSlots[this.nextFreeSlotsSize - 1];
    if (!this.slotTypesDescriptors.isEmpty())
      ((StringBuilder)this.slotTypesDescriptors.peek()).setLength(undefinedFromSlot); 
    ((MethodEmitter)this.methodEmitters.peek()).undefineLocalVariables(undefinedFromSlot, false);
  }
  
  private int assignSlots(Block block, int firstSlot) {
    int fromSlot = firstSlot;
    MethodEmitter method = this.methodEmitters.peek();
    for (Symbol symbol : block.getSymbols()) {
      if (symbol.hasSlot()) {
        symbol.setFirstSlot(fromSlot);
        int toSlot = fromSlot + symbol.slotCount();
        method.defineBlockLocalVariable(fromSlot, toSlot);
        fromSlot = toSlot;
      } 
    } 
    return fromSlot;
  }
  
  static Type getTypeForSlotDescriptor(char typeDesc) {
    switch (typeDesc) {
      case 'I':
      case 'i':
        return (Type)Type.INT;
      case 'J':
      case 'j':
        return Type.LONG;
      case 'D':
      case 'd':
        return (Type)Type.NUMBER;
      case 'A':
      case 'a':
        return Type.OBJECT;
      case 'U':
      case 'u':
        return Type.UNKNOWN;
    } 
    throw new AssertionError();
  }
  
  void pushDiscard(Expression expr) {
    this.discard.push(expr);
  }
  
  boolean popDiscardIfCurrent(Expression expr) {
    if (isCurrentDiscard(expr)) {
      this.discard.pop();
      return true;
    } 
    return false;
  }
  
  boolean isCurrentDiscard(Expression expr) {
    return (this.discard.peek() == expr);
  }
  
  int quickSlot(Type type) {
    return ((MethodEmitter)this.methodEmitters.peek()).defineTemporaryLocalVariable(type.getSlots());
  }
}
