package org.openjdk.nashorn.internal.codegen;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.openjdk.nashorn.internal.ir.Block;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.LexicalContext;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.Symbol;
import org.openjdk.nashorn.internal.ir.WithNode;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.ir.visitor.SimpleNodeVisitor;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.RecompilableScriptFunctionData;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;
import org.openjdk.nashorn.internal.runtime.logging.Loggable;
import org.openjdk.nashorn.internal.runtime.logging.Logger;

@Logger(name = "scopedepths")
final class FindScopeDepths extends SimpleNodeVisitor implements Loggable {
  private final Compiler compiler;
  
  private final Map<Integer, Map<Integer, RecompilableScriptFunctionData>> fnIdToNestedFunctions = new HashMap<>();
  
  private final Map<Integer, Map<String, Integer>> externalSymbolDepths = new HashMap<>();
  
  private final Map<Integer, Set<String>> internalSymbols = new HashMap<>();
  
  private final Set<Block> withBodies = new HashSet<>();
  
  private final DebugLogger log;
  
  private int dynamicScopeCount;
  
  FindScopeDepths(Compiler compiler) {
    this.compiler = compiler;
    this.log = initLogger(compiler.getContext());
  }
  
  public DebugLogger getLogger() {
    return this.log;
  }
  
  public DebugLogger initLogger(Context context) {
    return context.getLogger(getClass());
  }
  
  static int findScopesToStart(LexicalContext lc, FunctionNode fn, Block block) {
    Block bodyBlock = findBodyBlock(lc, fn, block);
    Iterator<Block> iter = lc.getBlocks(block);
    Block b = iter.next();
    int scopesToStart = 0;
    while (true) {
      if (b.needsScope())
        scopesToStart++; 
      if (b == bodyBlock)
        break; 
      b = iter.next();
    } 
    return scopesToStart;
  }
  
  static int findInternalDepth(LexicalContext lc, FunctionNode fn, Block block, Symbol symbol) {
    Block bodyBlock = findBodyBlock(lc, fn, block);
    Iterator<Block> iter = lc.getBlocks(block);
    Block b = iter.next();
    int scopesToStart = 0;
    while (true) {
      if (definedInBlock(b, symbol))
        return scopesToStart; 
      if (b.needsScope())
        scopesToStart++; 
      if (b == bodyBlock)
        break; 
      b = iter.next();
    } 
    return -1;
  }
  
  private static boolean definedInBlock(Block block, Symbol symbol) {
    if (symbol.isGlobal())
      return block.isGlobalScope(); 
    return (block.getExistingSymbol(symbol.getName()) == symbol);
  }
  
  static Block findBodyBlock(LexicalContext lc, FunctionNode fn, Block block) {
    Iterator<Block> iter = lc.getBlocks(block);
    while (iter.hasNext()) {
      Block next = iter.next();
      if (fn.getBody() == next)
        return next; 
    } 
    return null;
  }
  
  private static Block findGlobalBlock(LexicalContext lc, Block block) {
    Iterator<Block> iter = lc.getBlocks(block);
    Block globalBlock = null;
    while (iter.hasNext())
      globalBlock = iter.next(); 
    return globalBlock;
  }
  
  private static boolean isDynamicScopeBoundary(FunctionNode fn) {
    return fn.needsDynamicScope();
  }
  
  private boolean isDynamicScopeBoundary(Block block) {
    return this.withBodies.contains(block);
  }
  
  public boolean enterFunctionNode(FunctionNode functionNode) {
    if (this.compiler.isOnDemandCompilation())
      return true; 
    if (isDynamicScopeBoundary(functionNode))
      increaseDynamicScopeCount((Node)functionNode); 
    this.fnIdToNestedFunctions.computeIfAbsent(Integer.valueOf(functionNode.getId()), k -> new HashMap<>());
    return true;
  }
  
  public Node leaveFunctionNode(FunctionNode functionNode) {
    String name = functionNode.getName();
    FunctionNode newFunctionNode = functionNode;
    if (this.compiler.isOnDemandCompilation()) {
      RecompilableScriptFunctionData recompilableScriptFunctionData = this.compiler.getScriptFunctionData(newFunctionNode.getId());
      if (recompilableScriptFunctionData.inDynamicContext()) {
        this.log.fine(new Object[] { "Reviving scriptfunction ", DebugLogger.quote(name), " as defined in previous (now lost) dynamic scope." });
        newFunctionNode = newFunctionNode.setInDynamicContext(this.lc);
      } 
      if (newFunctionNode == this.lc.getOutermostFunction() && !newFunctionNode.hasApplyToCallSpecialization())
        recompilableScriptFunctionData.setCachedAst(newFunctionNode); 
      return (Node)newFunctionNode;
    } 
    if (inDynamicScope()) {
      this.log.fine(new Object[] { "Tagging ", DebugLogger.quote(name), " as defined in dynamic scope" });
      newFunctionNode = newFunctionNode.setInDynamicContext(this.lc);
    } 
    int fnId = newFunctionNode.getId();
    Map<Integer, RecompilableScriptFunctionData> nestedFunctions = this.fnIdToNestedFunctions.remove(Integer.valueOf(fnId));
    assert nestedFunctions != null;
    RecompilableScriptFunctionData data = new RecompilableScriptFunctionData(newFunctionNode, this.compiler.getCodeInstaller(), ObjectClassGenerator.createAllocationStrategy(newFunctionNode.getThisProperties(), this.compiler.getContext().useDualFields()), nestedFunctions, this.externalSymbolDepths.get(Integer.valueOf(fnId)), this.internalSymbols.get(Integer.valueOf(fnId)));
    if (this.lc.getOutermostFunction() != newFunctionNode) {
      FunctionNode parentFn = this.lc.getParentFunction(newFunctionNode);
      if (parentFn != null)
        ((Map<Integer, RecompilableScriptFunctionData>)this.fnIdToNestedFunctions.get(Integer.valueOf(parentFn.getId()))).put(Integer.valueOf(fnId), data); 
    } else {
      this.compiler.setData(data);
    } 
    if (isDynamicScopeBoundary(functionNode))
      decreaseDynamicScopeCount((Node)functionNode); 
    return (Node)newFunctionNode;
  }
  
  private boolean inDynamicScope() {
    return (this.dynamicScopeCount > 0);
  }
  
  private void increaseDynamicScopeCount(Node node) {
    assert this.dynamicScopeCount >= 0;
    this.dynamicScopeCount++;
    if (this.log.isEnabled())
      this.log.finest(new Object[] { DebugLogger.quote(this.lc.getCurrentFunction().getName()), " ++dynamicScopeCount = ", Integer.valueOf(this.dynamicScopeCount), " at: ", node, node.getClass() }); 
  }
  
  private void decreaseDynamicScopeCount(Node node) {
    this.dynamicScopeCount--;
    assert this.dynamicScopeCount >= 0;
    if (this.log.isEnabled())
      this.log.finest(new Object[] { DebugLogger.quote(this.lc.getCurrentFunction().getName()), " --dynamicScopeCount = ", Integer.valueOf(this.dynamicScopeCount), " at: ", node, node.getClass() }); 
  }
  
  public boolean enterWithNode(WithNode node) {
    this.withBodies.add(node.getBody());
    return true;
  }
  
  public boolean enterBlock(Block block) {
    if (this.compiler.isOnDemandCompilation())
      return true; 
    if (isDynamicScopeBoundary(block))
      increaseDynamicScopeCount((Node)block); 
    if (!this.lc.isFunctionBody())
      return true; 
    FunctionNode fn = this.lc.getCurrentFunction();
    final Set<Symbol> symbols = new HashSet<>();
    block.accept((NodeVisitor)new SimpleNodeVisitor() {
          public boolean enterIdentNode(IdentNode identNode) {
            Symbol symbol = identNode.getSymbol();
            if (symbol != null && symbol.isScope())
              symbols.add(symbol); 
            return true;
          }
        });
    Map<String, Integer> internals = new HashMap<>();
    Block globalBlock = findGlobalBlock(this.lc, block);
    Block bodyBlock = findBodyBlock(this.lc, fn, block);
    assert globalBlock != null;
    assert bodyBlock != null;
    for (Symbol symbol : symbols) {
      int internalDepth = findInternalDepth(this.lc, fn, block, symbol);
      boolean internal = (internalDepth >= 0);
      if (internal)
        internals.put(symbol.getName(), Integer.valueOf(internalDepth)); 
      if (!internal) {
        int depthAtStart = 0;
        Iterator<Block> iter = this.lc.getAncestorBlocks(bodyBlock);
        while (iter.hasNext()) {
          Block b2 = iter.next();
          if (definedInBlock(b2, symbol)) {
            addExternalSymbol(fn, symbol, depthAtStart);
            break;
          } 
          if (b2.needsScope())
            depthAtStart++; 
        } 
      } 
    } 
    addInternalSymbols(fn, internals.keySet());
    if (this.log.isEnabled())
      this.log.info(fn.getName() + " internals=" + fn.getName() + " externals=" + internals); 
    return true;
  }
  
  public Node leaveBlock(Block block) {
    if (this.compiler.isOnDemandCompilation())
      return (Node)block; 
    if (isDynamicScopeBoundary(block))
      decreaseDynamicScopeCount((Node)block); 
    return (Node)block;
  }
  
  private void addInternalSymbols(FunctionNode functionNode, Set<String> symbols) {
    int fnId = functionNode.getId();
    assert this.internalSymbols.get(Integer.valueOf(fnId)) == null || ((Set)this.internalSymbols.get(Integer.valueOf(fnId))).equals(symbols);
    this.internalSymbols.put(Integer.valueOf(fnId), symbols);
  }
  
  private void addExternalSymbol(FunctionNode functionNode, Symbol symbol, int depthAtStart) {
    ((Map<String, Integer>)this.externalSymbolDepths
      .computeIfAbsent(Integer.valueOf(functionNode.getId()), k -> new HashMap<>()))
      .put(symbol.getName(), Integer.valueOf(depthAtStart));
  }
}
