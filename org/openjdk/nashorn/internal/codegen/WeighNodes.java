package org.openjdk.nashorn.internal.codegen;

import java.util.List;
import java.util.Map;
import org.openjdk.nashorn.internal.ir.AccessNode;
import org.openjdk.nashorn.internal.ir.BinaryNode;
import org.openjdk.nashorn.internal.ir.Block;
import org.openjdk.nashorn.internal.ir.BreakNode;
import org.openjdk.nashorn.internal.ir.CallNode;
import org.openjdk.nashorn.internal.ir.CatchNode;
import org.openjdk.nashorn.internal.ir.ContinueNode;
import org.openjdk.nashorn.internal.ir.ExpressionStatement;
import org.openjdk.nashorn.internal.ir.ForNode;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.IfNode;
import org.openjdk.nashorn.internal.ir.IndexNode;
import org.openjdk.nashorn.internal.ir.JumpToInlinedFinally;
import org.openjdk.nashorn.internal.ir.LexicalContext;
import org.openjdk.nashorn.internal.ir.LiteralNode;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.ObjectNode;
import org.openjdk.nashorn.internal.ir.PropertyNode;
import org.openjdk.nashorn.internal.ir.ReturnNode;
import org.openjdk.nashorn.internal.ir.RuntimeNode;
import org.openjdk.nashorn.internal.ir.SplitNode;
import org.openjdk.nashorn.internal.ir.Splittable;
import org.openjdk.nashorn.internal.ir.SwitchNode;
import org.openjdk.nashorn.internal.ir.ThrowNode;
import org.openjdk.nashorn.internal.ir.TryNode;
import org.openjdk.nashorn.internal.ir.UnaryNode;
import org.openjdk.nashorn.internal.ir.VarNode;
import org.openjdk.nashorn.internal.ir.WhileNode;
import org.openjdk.nashorn.internal.ir.WithNode;
import org.openjdk.nashorn.internal.ir.visitor.NodeOperatorVisitor;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

final class WeighNodes extends NodeOperatorVisitor<LexicalContext> {
  static final long FUNCTION_WEIGHT = 40L;
  
  static final long AASTORE_WEIGHT = 2L;
  
  static final long ACCESS_WEIGHT = 4L;
  
  static final long ADD_WEIGHT = 10L;
  
  static final long BREAK_WEIGHT = 1L;
  
  static final long CALL_WEIGHT = 10L;
  
  static final long CATCH_WEIGHT = 10L;
  
  static final long COMPARE_WEIGHT = 6L;
  
  static final long CONTINUE_WEIGHT = 1L;
  
  static final long IF_WEIGHT = 2L;
  
  static final long LITERAL_WEIGHT = 10L;
  
  static final long LOOP_WEIGHT = 4L;
  
  static final long NEW_WEIGHT = 6L;
  
  static final long FUNC_EXPR_WEIGHT = 20L;
  
  static final long RETURN_WEIGHT = 2L;
  
  static final long SPLIT_WEIGHT = 40L;
  
  static final long SWITCH_WEIGHT = 8L;
  
  static final long THROW_WEIGHT = 2L;
  
  static final long VAR_WEIGHT = 40L;
  
  static final long WITH_WEIGHT = 8L;
  
  static final long OBJECT_WEIGHT = 16L;
  
  static final long SETPROP_WEIGHT = 5L;
  
  private long weight;
  
  private final Map<Node, Long> weightCache;
  
  private final FunctionNode topFunction;
  
  private WeighNodes(FunctionNode topFunction, Map<Node, Long> weightCache) {
    super(new LexicalContext());
    this.topFunction = topFunction;
    this.weightCache = weightCache;
  }
  
  static long weigh(Node node) {
    return weigh(node, null);
  }
  
  static long weigh(Node node, Map<Node, Long> weightCache) {
    WeighNodes weighNodes = new WeighNodes((node instanceof FunctionNode) ? (FunctionNode)node : null, weightCache);
    node.accept((NodeVisitor)weighNodes);
    return weighNodes.weight;
  }
  
  public Node leaveAccessNode(AccessNode accessNode) {
    this.weight += 4L;
    return (Node)accessNode;
  }
  
  public boolean enterBlock(Block block) {
    if (this.weightCache != null && this.weightCache.containsKey(block)) {
      this.weight += ((Long)this.weightCache.get(block)).longValue();
      return false;
    } 
    return true;
  }
  
  public Node leaveBreakNode(BreakNode breakNode) {
    this.weight++;
    return (Node)breakNode;
  }
  
  public Node leaveCallNode(CallNode callNode) {
    this.weight += 10L;
    return (Node)callNode;
  }
  
  public Node leaveCatchNode(CatchNode catchNode) {
    this.weight += 10L;
    return (Node)catchNode;
  }
  
  public Node leaveContinueNode(ContinueNode continueNode) {
    this.weight++;
    return (Node)continueNode;
  }
  
  public Node leaveExpressionStatement(ExpressionStatement expressionStatement) {
    return (Node)expressionStatement;
  }
  
  public Node leaveForNode(ForNode forNode) {
    this.weight += 4L;
    return (Node)forNode;
  }
  
  public boolean enterFunctionNode(FunctionNode functionNode) {
    if (functionNode == this.topFunction)
      return true; 
    this.weight += 20L;
    return false;
  }
  
  public Node leaveIdentNode(IdentNode identNode) {
    this.weight += 4L;
    return (Node)identNode;
  }
  
  public Node leaveIfNode(IfNode ifNode) {
    this.weight += 2L;
    return (Node)ifNode;
  }
  
  public Node leaveIndexNode(IndexNode indexNode) {
    this.weight += 4L;
    return (Node)indexNode;
  }
  
  public Node leaveJumpToInlinedFinally(JumpToInlinedFinally jumpToInlinedFinally) {
    this.weight++;
    return (Node)jumpToInlinedFinally;
  }
  
  public boolean enterLiteralNode(LiteralNode literalNode) {
    this.weight += 10L;
    if (literalNode instanceof LiteralNode.ArrayLiteralNode) {
      LiteralNode.ArrayLiteralNode arrayLiteralNode = (LiteralNode.ArrayLiteralNode)literalNode;
      Node[] value = (Node[])arrayLiteralNode.getValue();
      int[] postsets = arrayLiteralNode.getPostsets();
      List<Splittable.SplitRange> units = arrayLiteralNode.getSplitRanges();
      if (units == null)
        for (int postset : postsets) {
          this.weight += 2L;
          Node element = value[postset];
          if (element != null)
            element.accept((NodeVisitor)this); 
        }  
      return false;
    } 
    return true;
  }
  
  public boolean enterObjectNode(ObjectNode objectNode) {
    this.weight += 16L;
    List<PropertyNode> properties = objectNode.getElements();
    boolean isSpillObject = (properties.size() > CodeGenerator.OBJECT_SPILL_THRESHOLD);
    for (PropertyNode property : properties) {
      if (!LiteralNode.isConstant(property.getValue())) {
        this.weight += 5L;
        property.getValue().accept((NodeVisitor)this);
        continue;
      } 
      if (!isSpillObject)
        this.weight += 5L; 
    } 
    return false;
  }
  
  public Node leavePropertyNode(PropertyNode propertyNode) {
    this.weight += 10L;
    return (Node)propertyNode;
  }
  
  public Node leaveReturnNode(ReturnNode returnNode) {
    this.weight += 2L;
    return (Node)returnNode;
  }
  
  public Node leaveRuntimeNode(RuntimeNode runtimeNode) {
    this.weight += 10L;
    return (Node)runtimeNode;
  }
  
  public boolean enterSplitNode(SplitNode splitNode) {
    this.weight += 40L;
    return false;
  }
  
  public Node leaveSwitchNode(SwitchNode switchNode) {
    this.weight += 8L;
    return (Node)switchNode;
  }
  
  public Node leaveThrowNode(ThrowNode throwNode) {
    this.weight += 2L;
    return (Node)throwNode;
  }
  
  public Node leaveTryNode(TryNode tryNode) {
    this.weight += 2L;
    return (Node)tryNode;
  }
  
  public Node leaveVarNode(VarNode varNode) {
    this.weight += 40L;
    return (Node)varNode;
  }
  
  public Node leaveWhileNode(WhileNode whileNode) {
    this.weight += 4L;
    return (Node)whileNode;
  }
  
  public Node leaveWithNode(WithNode withNode) {
    this.weight += 8L;
    return (Node)withNode;
  }
  
  public Node leavePOS(UnaryNode unaryNode) {
    return unaryNodeWeight(unaryNode);
  }
  
  public Node leaveBIT_NOT(UnaryNode unaryNode) {
    return unaryNodeWeight(unaryNode);
  }
  
  public Node leaveDECINC(UnaryNode unaryNode) {
    return unaryNodeWeight(unaryNode);
  }
  
  public Node leaveDELETE(UnaryNode unaryNode) {
    return runtimeNodeWeight(unaryNode);
  }
  
  public Node leaveNEW(UnaryNode unaryNode) {
    this.weight += 6L;
    return (Node)unaryNode;
  }
  
  public Node leaveNOT(UnaryNode unaryNode) {
    return unaryNodeWeight(unaryNode);
  }
  
  public Node leaveNEG(UnaryNode unaryNode) {
    return unaryNodeWeight(unaryNode);
  }
  
  public Node leaveTYPEOF(UnaryNode unaryNode) {
    return runtimeNodeWeight(unaryNode);
  }
  
  public Node leaveVOID(UnaryNode unaryNode) {
    return unaryNodeWeight(unaryNode);
  }
  
  public Node leaveADD(BinaryNode binaryNode) {
    this.weight += 10L;
    return (Node)binaryNode;
  }
  
  public Node leaveAND(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveASSIGN(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveASSIGN_ADD(BinaryNode binaryNode) {
    this.weight += 10L;
    return (Node)binaryNode;
  }
  
  public Node leaveASSIGN_BIT_AND(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveASSIGN_BIT_OR(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveASSIGN_BIT_XOR(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveASSIGN_DIV(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveASSIGN_MOD(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveASSIGN_MUL(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveASSIGN_SAR(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveASSIGN_SHL(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveASSIGN_SHR(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveASSIGN_SUB(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveARROW(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveBIT_AND(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveBIT_OR(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveBIT_XOR(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveCOMMARIGHT(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveDIV(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveEQ(BinaryNode binaryNode) {
    return compareWeight(binaryNode);
  }
  
  public Node leaveEQ_STRICT(BinaryNode binaryNode) {
    return compareWeight(binaryNode);
  }
  
  public Node leaveGE(BinaryNode binaryNode) {
    return compareWeight(binaryNode);
  }
  
  public Node leaveGT(BinaryNode binaryNode) {
    return compareWeight(binaryNode);
  }
  
  public Node leaveIN(BinaryNode binaryNode) {
    this.weight += 10L;
    return (Node)binaryNode;
  }
  
  public Node leaveINSTANCEOF(BinaryNode binaryNode) {
    this.weight += 10L;
    return (Node)binaryNode;
  }
  
  public Node leaveLE(BinaryNode binaryNode) {
    return compareWeight(binaryNode);
  }
  
  public Node leaveLT(BinaryNode binaryNode) {
    return compareWeight(binaryNode);
  }
  
  public Node leaveMOD(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveMUL(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveNE(BinaryNode binaryNode) {
    return compareWeight(binaryNode);
  }
  
  public Node leaveNE_STRICT(BinaryNode binaryNode) {
    return compareWeight(binaryNode);
  }
  
  public Node leaveOR(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveSAR(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveSHL(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveSHR(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  public Node leaveSUB(BinaryNode binaryNode) {
    return binaryNodeWeight(binaryNode);
  }
  
  private Node unaryNodeWeight(UnaryNode unaryNode) {
    this.weight++;
    return (Node)unaryNode;
  }
  
  private Node binaryNodeWeight(BinaryNode binaryNode) {
    this.weight++;
    return (Node)binaryNode;
  }
  
  private Node runtimeNodeWeight(UnaryNode unaryNode) {
    this.weight += 10L;
    return (Node)unaryNode;
  }
  
  private Node compareWeight(BinaryNode binaryNode) {
    this.weight += 6L;
    return (Node)binaryNode;
  }
}
