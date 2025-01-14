package org.openjdk.nashorn.internal.codegen;

import java.util.HashSet;
import java.util.Set;
import org.openjdk.nashorn.internal.IntDeque;
import org.openjdk.nashorn.internal.ir.AccessNode;
import org.openjdk.nashorn.internal.ir.BinaryNode;
import org.openjdk.nashorn.internal.ir.CallNode;
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.IndexNode;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.Optimistic;
import org.openjdk.nashorn.internal.ir.UnaryNode;
import org.openjdk.nashorn.internal.ir.VarNode;
import org.openjdk.nashorn.internal.ir.visitor.SimpleNodeVisitor;

class ProgramPoints extends SimpleNodeVisitor {
  private final IntDeque nextProgramPoint = new IntDeque();
  
  private final Set<Node> noProgramPoint = new HashSet<>();
  
  private int next() {
    int next = this.nextProgramPoint.getAndIncrement();
    if (next > 131071)
      throw new AssertionError("Function has more than 131071 program points"); 
    return next;
  }
  
  public boolean enterFunctionNode(FunctionNode functionNode) {
    this.nextProgramPoint.push(1);
    return true;
  }
  
  public Node leaveFunctionNode(FunctionNode functionNode) {
    this.nextProgramPoint.pop();
    return (Node)functionNode;
  }
  
  private Expression setProgramPoint(Optimistic optimistic) {
    if (this.noProgramPoint.contains(optimistic))
      return (Expression)optimistic; 
    return optimistic.canBeOptimistic() ? (Expression)optimistic.setProgramPoint(next()) : (Expression)optimistic;
  }
  
  public boolean enterVarNode(VarNode varNode) {
    this.noProgramPoint.add(varNode.getName());
    return true;
  }
  
  public boolean enterIdentNode(IdentNode identNode) {
    if (identNode.isInternal())
      this.noProgramPoint.add(identNode); 
    return true;
  }
  
  public Node leaveIdentNode(IdentNode identNode) {
    if (identNode.isPropertyName())
      return (Node)identNode; 
    return (Node)setProgramPoint((Optimistic)identNode);
  }
  
  public Node leaveCallNode(CallNode callNode) {
    return (Node)setProgramPoint((Optimistic)callNode);
  }
  
  public Node leaveAccessNode(AccessNode accessNode) {
    return (Node)setProgramPoint((Optimistic)accessNode);
  }
  
  public Node leaveIndexNode(IndexNode indexNode) {
    return (Node)setProgramPoint((Optimistic)indexNode);
  }
  
  public Node leaveBinaryNode(BinaryNode binaryNode) {
    return (Node)setProgramPoint((Optimistic)binaryNode);
  }
  
  public Node leaveUnaryNode(UnaryNode unaryNode) {
    return (Node)setProgramPoint((Optimistic)unaryNode);
  }
}
