package org.openjdk.nashorn.internal.codegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openjdk.nashorn.internal.ir.Block;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.LexicalContextNode;
import org.openjdk.nashorn.internal.ir.LiteralNode;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.ObjectNode;
import org.openjdk.nashorn.internal.ir.PropertyNode;
import org.openjdk.nashorn.internal.ir.SplitNode;
import org.openjdk.nashorn.internal.ir.Splittable;
import org.openjdk.nashorn.internal.ir.Statement;
import org.openjdk.nashorn.internal.ir.VarNode;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.ir.visitor.SimpleNodeVisitor;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;
import org.openjdk.nashorn.internal.runtime.logging.Loggable;
import org.openjdk.nashorn.internal.runtime.logging.Logger;
import org.openjdk.nashorn.internal.runtime.options.Options;

@Logger(name = "splitter")
final class Splitter extends SimpleNodeVisitor implements Loggable {
  private final Compiler compiler;
  
  private final FunctionNode outermost;
  
  private final CompileUnit outermostCompileUnit;
  
  private final Map<Node, Long> weightCache = new HashMap<>();
  
  public static final long SPLIT_THRESHOLD = Options.getIntProperty("nashorn.compiler.splitter.threshold", 32768);
  
  private final DebugLogger log;
  
  public Splitter(Compiler compiler, FunctionNode functionNode, CompileUnit outermostCompileUnit) {
    this.compiler = compiler;
    this.outermost = functionNode;
    this.outermostCompileUnit = outermostCompileUnit;
    this.log = initLogger(compiler.getContext());
  }
  
  public DebugLogger initLogger(Context context) {
    return context.getLogger(getClass());
  }
  
  public DebugLogger getLogger() {
    return this.log;
  }
  
  FunctionNode split(FunctionNode fn, boolean top) {
    FunctionNode functionNode = fn;
    this.log.fine(new Object[] { "Initiating split of '", functionNode.getName(), "'" });
    long weight = WeighNodes.weigh((Node)functionNode);
    assert this.lc.isEmpty() : "LexicalContext not empty";
    if (weight >= SPLIT_THRESHOLD) {
      this.log.info(new Object[] { "Splitting function '", functionNode.getName(), "' as its weight ", Long.valueOf(weight), " exceeds split threshold ", Long.valueOf(SPLIT_THRESHOLD) });
      functionNode = (FunctionNode)functionNode.accept((NodeVisitor)this);
      if (functionNode.isSplit()) {
        weight = WeighNodes.weigh((Node)functionNode, this.weightCache);
        functionNode = functionNode.setBody(null, functionNode.getBody().setNeedsScope(null));
      } 
      if (weight >= SPLIT_THRESHOLD) {
        functionNode = functionNode.setBody(null, splitBlock(functionNode.getBody(), functionNode));
        functionNode = functionNode.setFlag(null, 16);
        weight = WeighNodes.weigh((Node)functionNode.getBody(), this.weightCache);
      } 
    } 
    assert functionNode.getCompileUnit() == null : "compile unit already set for " + functionNode.getName();
    if (top) {
      assert this.outermostCompileUnit != null : "outermost compile unit is null";
      functionNode = functionNode.setCompileUnit(null, this.outermostCompileUnit);
      this.outermostCompileUnit.addWeight(weight + 40L);
    } else {
      functionNode = functionNode.setCompileUnit(null, findUnit(weight));
    } 
    Block body = functionNode.getBody();
    final List<FunctionNode> dc = directChildren(functionNode);
    Block newBody = (Block)body.accept((NodeVisitor)new SimpleNodeVisitor() {
          public boolean enterFunctionNode(FunctionNode nestedFunction) {
            return dc.contains(nestedFunction);
          }
          
          public Node leaveFunctionNode(FunctionNode nestedFunction) {
            FunctionNode split = (new Splitter(Splitter.this.compiler, nestedFunction, Splitter.this.outermostCompileUnit)).split(nestedFunction, false);
            this.lc.replace((LexicalContextNode)nestedFunction, (LexicalContextNode)split);
            return (Node)split;
          }
        });
    functionNode = functionNode.setBody(null, newBody);
    assert functionNode.getCompileUnit() != null;
    return functionNode;
  }
  
  private static List<FunctionNode> directChildren(final FunctionNode functionNode) {
    final List<FunctionNode> dc = new ArrayList<>();
    functionNode.accept((NodeVisitor)new SimpleNodeVisitor() {
          public boolean enterFunctionNode(FunctionNode child) {
            if (child == functionNode)
              return true; 
            if (this.lc.getParentFunction(child) == functionNode)
              dc.add(child); 
            return false;
          }
        });
    return dc;
  }
  
  protected CompileUnit findUnit(long weight) {
    return this.compiler.findUnit(weight);
  }
  
  private Block splitBlock(Block block, FunctionNode function) {
    List<Statement> splits = new ArrayList<>();
    List<Statement> statements = new ArrayList<>();
    long statementsWeight = 0L;
    for (Statement statement : block.getStatements()) {
      long weight = WeighNodes.weigh((Node)statement, this.weightCache);
      boolean isBlockScopedVarNode = isBlockScopedVarNode(statement);
      if ((statementsWeight + weight >= SPLIT_THRESHOLD || statement.isTerminal() || isBlockScopedVarNode) && 
        !statements.isEmpty()) {
        splits.add(createBlockSplitNode(block, function, statements, statementsWeight));
        statements = new ArrayList<>();
        statementsWeight = 0L;
      } 
      if (statement.isTerminal() || isBlockScopedVarNode) {
        splits.add(statement);
        continue;
      } 
      statements.add(statement);
      statementsWeight += weight;
    } 
    if (!statements.isEmpty())
      splits.add(createBlockSplitNode(block, function, statements, statementsWeight)); 
    return block.setStatements(this.lc, splits);
  }
  
  private SplitNode createBlockSplitNode(Block parent, FunctionNode function, List<Statement> statements, long weight) {
    long token = parent.getToken();
    int finish = parent.getFinish();
    String name = function.uniqueName(CompilerConstants.SPLIT_PREFIX.symbolName());
    Block newBlock = new Block(token, finish, statements);
    return new SplitNode(name, newBlock, this.compiler.findUnit(weight + 40L));
  }
  
  private boolean isBlockScopedVarNode(Statement statement) {
    return (statement instanceof VarNode && ((VarNode)statement).isBlockScoped());
  }
  
  public boolean enterBlock(Block block) {
    if (block.isCatchBlock())
      return false; 
    long weight = WeighNodes.weigh((Node)block, this.weightCache);
    if (weight < SPLIT_THRESHOLD) {
      this.weightCache.put(block, Long.valueOf(weight));
      return false;
    } 
    return true;
  }
  
  public Node leaveBlock(Block block) {
    assert !block.isCatchBlock();
    Block newBlock = block;
    long weight = WeighNodes.weigh((Node)block, this.weightCache);
    if (weight >= SPLIT_THRESHOLD) {
      FunctionNode currentFunction = this.lc.getCurrentFunction();
      newBlock = splitBlock(block, currentFunction);
      weight = WeighNodes.weigh((Node)newBlock, this.weightCache);
      this.lc.setFlag((LexicalContextNode)currentFunction, 16);
    } 
    this.weightCache.put(newBlock, Long.valueOf(weight));
    return (Node)newBlock;
  }
  
  public Node leaveLiteralNode(LiteralNode literal) {
    long weight = WeighNodes.weigh((Node)literal);
    if (weight < SPLIT_THRESHOLD)
      return (Node)literal; 
    FunctionNode functionNode = this.lc.getCurrentFunction();
    this.lc.setFlag((LexicalContextNode)functionNode, 16);
    if (literal instanceof LiteralNode.ArrayLiteralNode) {
      LiteralNode.ArrayLiteralNode arrayLiteralNode = (LiteralNode.ArrayLiteralNode)literal;
      Node[] value = (Node[])arrayLiteralNode.getValue();
      int[] postsets = arrayLiteralNode.getPostsets();
      List<Splittable.SplitRange> ranges = new ArrayList<>();
      long totalWeight = 0L;
      int lo = 0;
      for (int i = 0; i < postsets.length; i++) {
        int postset = postsets[i];
        Node element = value[postset];
        long elementWeight = WeighNodes.weigh(element);
        totalWeight += 2L + elementWeight;
        if (totalWeight >= SPLIT_THRESHOLD) {
          CompileUnit unit = this.compiler.findUnit(totalWeight - elementWeight);
          ranges.add(new Splittable.SplitRange(unit, lo, i));
          lo = i;
          totalWeight = elementWeight;
        } 
      } 
      if (lo != postsets.length) {
        CompileUnit unit = this.compiler.findUnit(totalWeight);
        ranges.add(new Splittable.SplitRange(unit, lo, postsets.length));
      } 
      this.log.info(new Object[] { "Splitting array literal in '", functionNode.getName(), "' as its weight ", Long.valueOf(weight), " exceeds split threshold ", Long.valueOf(SPLIT_THRESHOLD) });
      return (Node)arrayLiteralNode.setSplitRanges(this.lc, ranges);
    } 
    return (Node)literal;
  }
  
  public Node leaveObjectNode(ObjectNode objectNode) {
    long weight = WeighNodes.weigh((Node)objectNode);
    if (weight < SPLIT_THRESHOLD)
      return (Node)objectNode; 
    FunctionNode functionNode = this.lc.getCurrentFunction();
    this.lc.setFlag((LexicalContextNode)functionNode, 16);
    List<Splittable.SplitRange> ranges = new ArrayList<>();
    List<PropertyNode> properties = objectNode.getElements();
    boolean isSpillObject = (properties.size() > CodeGenerator.OBJECT_SPILL_THRESHOLD);
    long totalWeight = 0L;
    int lo = 0;
    for (int i = 0; i < properties.size(); i++) {
      PropertyNode property = properties.get(i);
      boolean isConstant = LiteralNode.isConstant(property.getValue());
      if (!isConstant || !isSpillObject) {
        long propertyWeight = isConstant ? 0L : WeighNodes.weigh((Node)property.getValue());
        totalWeight += 2L + propertyWeight;
        if (totalWeight >= SPLIT_THRESHOLD) {
          CompileUnit unit = this.compiler.findUnit(totalWeight - propertyWeight);
          ranges.add(new Splittable.SplitRange(unit, lo, i));
          lo = i;
          totalWeight = propertyWeight;
        } 
      } 
    } 
    if (lo != properties.size()) {
      CompileUnit unit = this.compiler.findUnit(totalWeight);
      ranges.add(new Splittable.SplitRange(unit, lo, properties.size()));
    } 
    this.log.info(new Object[] { "Splitting object node in '", functionNode.getName(), "' as its weight ", Long.valueOf(weight), " exceeds split threshold ", Long.valueOf(SPLIT_THRESHOLD) });
    return (Node)objectNode.setSplitRanges(this.lc, ranges);
  }
  
  public boolean enterFunctionNode(FunctionNode node) {
    return (node == this.outermost);
  }
}
