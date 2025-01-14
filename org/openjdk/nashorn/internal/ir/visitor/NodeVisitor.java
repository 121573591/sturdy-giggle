package org.openjdk.nashorn.internal.ir.visitor;

import org.openjdk.nashorn.internal.ir.AccessNode;
import org.openjdk.nashorn.internal.ir.BinaryNode;
import org.openjdk.nashorn.internal.ir.Block;
import org.openjdk.nashorn.internal.ir.BlockStatement;
import org.openjdk.nashorn.internal.ir.BreakNode;
import org.openjdk.nashorn.internal.ir.CallNode;
import org.openjdk.nashorn.internal.ir.CaseNode;
import org.openjdk.nashorn.internal.ir.CatchNode;
import org.openjdk.nashorn.internal.ir.ClassNode;
import org.openjdk.nashorn.internal.ir.ContinueNode;
import org.openjdk.nashorn.internal.ir.DebuggerNode;
import org.openjdk.nashorn.internal.ir.EmptyNode;
import org.openjdk.nashorn.internal.ir.ErrorNode;
import org.openjdk.nashorn.internal.ir.ExpressionStatement;
import org.openjdk.nashorn.internal.ir.ForNode;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.GetSplitState;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.IfNode;
import org.openjdk.nashorn.internal.ir.IndexNode;
import org.openjdk.nashorn.internal.ir.JoinPredecessorExpression;
import org.openjdk.nashorn.internal.ir.JumpToInlinedFinally;
import org.openjdk.nashorn.internal.ir.LabelNode;
import org.openjdk.nashorn.internal.ir.LexicalContext;
import org.openjdk.nashorn.internal.ir.LiteralNode;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.ObjectNode;
import org.openjdk.nashorn.internal.ir.PropertyNode;
import org.openjdk.nashorn.internal.ir.ReturnNode;
import org.openjdk.nashorn.internal.ir.RuntimeNode;
import org.openjdk.nashorn.internal.ir.SetSplitState;
import org.openjdk.nashorn.internal.ir.SplitNode;
import org.openjdk.nashorn.internal.ir.SplitReturn;
import org.openjdk.nashorn.internal.ir.SwitchNode;
import org.openjdk.nashorn.internal.ir.TemplateLiteral;
import org.openjdk.nashorn.internal.ir.TernaryNode;
import org.openjdk.nashorn.internal.ir.ThrowNode;
import org.openjdk.nashorn.internal.ir.TryNode;
import org.openjdk.nashorn.internal.ir.UnaryNode;
import org.openjdk.nashorn.internal.ir.VarNode;
import org.openjdk.nashorn.internal.ir.WhileNode;
import org.openjdk.nashorn.internal.ir.WithNode;

public abstract class NodeVisitor<T extends LexicalContext> {
  protected final T lc;
  
  public NodeVisitor(T lc) {
    this.lc = lc;
  }
  
  public T getLexicalContext() {
    return this.lc;
  }
  
  protected boolean enterDefault(Node node) {
    return true;
  }
  
  protected Node leaveDefault(Node node) {
    return node;
  }
  
  public boolean enterAccessNode(AccessNode accessNode) {
    return enterDefault((Node)accessNode);
  }
  
  public Node leaveAccessNode(AccessNode accessNode) {
    return leaveDefault((Node)accessNode);
  }
  
  public boolean enterBlock(Block block) {
    return enterDefault((Node)block);
  }
  
  public Node leaveBlock(Block block) {
    return leaveDefault((Node)block);
  }
  
  public boolean enterBinaryNode(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveBinaryNode(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterBreakNode(BreakNode breakNode) {
    return enterDefault((Node)breakNode);
  }
  
  public Node leaveBreakNode(BreakNode breakNode) {
    return leaveDefault((Node)breakNode);
  }
  
  public boolean enterCallNode(CallNode callNode) {
    return enterDefault((Node)callNode);
  }
  
  public Node leaveCallNode(CallNode callNode) {
    return leaveDefault((Node)callNode);
  }
  
  public boolean enterCaseNode(CaseNode caseNode) {
    return enterDefault((Node)caseNode);
  }
  
  public Node leaveCaseNode(CaseNode caseNode) {
    return leaveDefault((Node)caseNode);
  }
  
  public boolean enterCatchNode(CatchNode catchNode) {
    return enterDefault((Node)catchNode);
  }
  
  public Node leaveCatchNode(CatchNode catchNode) {
    return leaveDefault((Node)catchNode);
  }
  
  public boolean enterContinueNode(ContinueNode continueNode) {
    return enterDefault((Node)continueNode);
  }
  
  public Node leaveContinueNode(ContinueNode continueNode) {
    return leaveDefault((Node)continueNode);
  }
  
  public boolean enterDebuggerNode(DebuggerNode debuggerNode) {
    return enterDefault((Node)debuggerNode);
  }
  
  public Node leaveDebuggerNode(DebuggerNode debuggerNode) {
    return leaveDefault((Node)debuggerNode);
  }
  
  public boolean enterEmptyNode(EmptyNode emptyNode) {
    return enterDefault((Node)emptyNode);
  }
  
  public Node leaveEmptyNode(EmptyNode emptyNode) {
    return leaveDefault((Node)emptyNode);
  }
  
  public boolean enterErrorNode(ErrorNode errorNode) {
    return enterDefault((Node)errorNode);
  }
  
  public Node leaveErrorNode(ErrorNode errorNode) {
    return leaveDefault((Node)errorNode);
  }
  
  public boolean enterExpressionStatement(ExpressionStatement expressionStatement) {
    return enterDefault((Node)expressionStatement);
  }
  
  public Node leaveExpressionStatement(ExpressionStatement expressionStatement) {
    return leaveDefault((Node)expressionStatement);
  }
  
  public boolean enterBlockStatement(BlockStatement blockStatement) {
    return enterDefault((Node)blockStatement);
  }
  
  public Node leaveBlockStatement(BlockStatement blockStatement) {
    return leaveDefault((Node)blockStatement);
  }
  
  public boolean enterForNode(ForNode forNode) {
    return enterDefault((Node)forNode);
  }
  
  public Node leaveForNode(ForNode forNode) {
    return leaveDefault((Node)forNode);
  }
  
  public boolean enterFunctionNode(FunctionNode functionNode) {
    return enterDefault((Node)functionNode);
  }
  
  public Node leaveFunctionNode(FunctionNode functionNode) {
    return leaveDefault((Node)functionNode);
  }
  
  public boolean enterGetSplitState(GetSplitState getSplitState) {
    return enterDefault((Node)getSplitState);
  }
  
  public Node leaveGetSplitState(GetSplitState getSplitState) {
    return leaveDefault((Node)getSplitState);
  }
  
  public boolean enterIdentNode(IdentNode identNode) {
    return enterDefault((Node)identNode);
  }
  
  public Node leaveIdentNode(IdentNode identNode) {
    return leaveDefault((Node)identNode);
  }
  
  public boolean enterIfNode(IfNode ifNode) {
    return enterDefault((Node)ifNode);
  }
  
  public Node leaveIfNode(IfNode ifNode) {
    return leaveDefault((Node)ifNode);
  }
  
  public boolean enterIndexNode(IndexNode indexNode) {
    return enterDefault((Node)indexNode);
  }
  
  public Node leaveIndexNode(IndexNode indexNode) {
    return leaveDefault((Node)indexNode);
  }
  
  public boolean enterJumpToInlinedFinally(JumpToInlinedFinally jumpToInlinedFinally) {
    return enterDefault((Node)jumpToInlinedFinally);
  }
  
  public Node leaveJumpToInlinedFinally(JumpToInlinedFinally jumpToInlinedFinally) {
    return leaveDefault((Node)jumpToInlinedFinally);
  }
  
  public boolean enterLabelNode(LabelNode labelNode) {
    return enterDefault((Node)labelNode);
  }
  
  public Node leaveLabelNode(LabelNode labelNode) {
    return leaveDefault((Node)labelNode);
  }
  
  public boolean enterLiteralNode(LiteralNode<?> literalNode) {
    return enterDefault((Node)literalNode);
  }
  
  public Node leaveLiteralNode(LiteralNode<?> literalNode) {
    return leaveDefault((Node)literalNode);
  }
  
  public boolean enterObjectNode(ObjectNode objectNode) {
    return enterDefault((Node)objectNode);
  }
  
  public Node leaveObjectNode(ObjectNode objectNode) {
    return leaveDefault((Node)objectNode);
  }
  
  public boolean enterPropertyNode(PropertyNode propertyNode) {
    return enterDefault((Node)propertyNode);
  }
  
  public Node leavePropertyNode(PropertyNode propertyNode) {
    return leaveDefault((Node)propertyNode);
  }
  
  public boolean enterReturnNode(ReturnNode returnNode) {
    return enterDefault((Node)returnNode);
  }
  
  public Node leaveReturnNode(ReturnNode returnNode) {
    return leaveDefault((Node)returnNode);
  }
  
  public boolean enterRuntimeNode(RuntimeNode runtimeNode) {
    return enterDefault((Node)runtimeNode);
  }
  
  public Node leaveRuntimeNode(RuntimeNode runtimeNode) {
    return leaveDefault((Node)runtimeNode);
  }
  
  public boolean enterSetSplitState(SetSplitState setSplitState) {
    return enterDefault((Node)setSplitState);
  }
  
  public Node leaveSetSplitState(SetSplitState setSplitState) {
    return leaveDefault((Node)setSplitState);
  }
  
  public boolean enterSplitNode(SplitNode splitNode) {
    return enterDefault((Node)splitNode);
  }
  
  public Node leaveSplitNode(SplitNode splitNode) {
    return leaveDefault((Node)splitNode);
  }
  
  public boolean enterSplitReturn(SplitReturn splitReturn) {
    return enterDefault((Node)splitReturn);
  }
  
  public Node leaveSplitReturn(SplitReturn splitReturn) {
    return leaveDefault((Node)splitReturn);
  }
  
  public boolean enterSwitchNode(SwitchNode switchNode) {
    return enterDefault((Node)switchNode);
  }
  
  public Node leaveSwitchNode(SwitchNode switchNode) {
    return leaveDefault((Node)switchNode);
  }
  
  public boolean enterTemplateLiteral(TemplateLiteral templateLiteral) {
    return enterDefault((Node)templateLiteral);
  }
  
  public Node leaveTemplateLiteral(TemplateLiteral templateLiteral) {
    return leaveDefault((Node)templateLiteral);
  }
  
  public boolean enterTernaryNode(TernaryNode ternaryNode) {
    return enterDefault((Node)ternaryNode);
  }
  
  public Node leaveTernaryNode(TernaryNode ternaryNode) {
    return leaveDefault((Node)ternaryNode);
  }
  
  public boolean enterThrowNode(ThrowNode throwNode) {
    return enterDefault((Node)throwNode);
  }
  
  public Node leaveThrowNode(ThrowNode throwNode) {
    return leaveDefault((Node)throwNode);
  }
  
  public boolean enterTryNode(TryNode tryNode) {
    return enterDefault((Node)tryNode);
  }
  
  public Node leaveTryNode(TryNode tryNode) {
    return leaveDefault((Node)tryNode);
  }
  
  public boolean enterUnaryNode(UnaryNode unaryNode) {
    return enterDefault((Node)unaryNode);
  }
  
  public Node leaveUnaryNode(UnaryNode unaryNode) {
    return leaveDefault((Node)unaryNode);
  }
  
  public boolean enterJoinPredecessorExpression(JoinPredecessorExpression expr) {
    return enterDefault((Node)expr);
  }
  
  public Node leaveJoinPredecessorExpression(JoinPredecessorExpression expr) {
    return leaveDefault((Node)expr);
  }
  
  public boolean enterVarNode(VarNode varNode) {
    return enterDefault((Node)varNode);
  }
  
  public Node leaveVarNode(VarNode varNode) {
    return leaveDefault((Node)varNode);
  }
  
  public boolean enterWhileNode(WhileNode whileNode) {
    return enterDefault((Node)whileNode);
  }
  
  public Node leaveWhileNode(WhileNode whileNode) {
    return leaveDefault((Node)whileNode);
  }
  
  public boolean enterWithNode(WithNode withNode) {
    return enterDefault((Node)withNode);
  }
  
  public Node leaveWithNode(WithNode withNode) {
    return leaveDefault((Node)withNode);
  }
  
  public boolean enterClassNode(ClassNode classNode) {
    return enterDefault((Node)classNode);
  }
  
  public Node leaveClassNode(ClassNode classNode) {
    return leaveDefault((Node)classNode);
  }
}
