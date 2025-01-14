package org.openjdk.nashorn.internal.codegen;

import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.Deque;
import org.openjdk.nashorn.internal.ir.AccessNode;
import org.openjdk.nashorn.internal.ir.BinaryNode;
import org.openjdk.nashorn.internal.ir.CallNode;
import org.openjdk.nashorn.internal.ir.CatchNode;
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.ExpressionStatement;
import org.openjdk.nashorn.internal.ir.ForNode;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.IfNode;
import org.openjdk.nashorn.internal.ir.IndexNode;
import org.openjdk.nashorn.internal.ir.JoinPredecessorExpression;
import org.openjdk.nashorn.internal.ir.LiteralNode;
import org.openjdk.nashorn.internal.ir.LoopNode;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.ObjectNode;
import org.openjdk.nashorn.internal.ir.Optimistic;
import org.openjdk.nashorn.internal.ir.PropertyNode;
import org.openjdk.nashorn.internal.ir.Symbol;
import org.openjdk.nashorn.internal.ir.TernaryNode;
import org.openjdk.nashorn.internal.ir.UnaryNode;
import org.openjdk.nashorn.internal.ir.VarNode;
import org.openjdk.nashorn.internal.ir.WhileNode;
import org.openjdk.nashorn.internal.ir.visitor.SimpleNodeVisitor;
import org.openjdk.nashorn.internal.parser.TokenType;
import org.openjdk.nashorn.internal.runtime.UnwarrantedOptimismException;

final class OptimisticTypesCalculator extends SimpleNodeVisitor {
  final Compiler compiler;
  
  final Deque<BitSet> neverOptimistic = new ArrayDeque<>();
  
  OptimisticTypesCalculator(Compiler compiler) {
    this.compiler = compiler;
  }
  
  public boolean enterAccessNode(AccessNode accessNode) {
    tagNeverOptimistic(accessNode.getBase());
    return true;
  }
  
  public boolean enterPropertyNode(PropertyNode propertyNode) {
    if ("__proto__".equals(propertyNode.getKeyName()))
      tagNeverOptimistic(propertyNode.getValue()); 
    return super.enterPropertyNode(propertyNode);
  }
  
  public boolean enterBinaryNode(BinaryNode binaryNode) {
    if (binaryNode.isAssignment()) {
      Expression lhs = binaryNode.lhs();
      if (!binaryNode.isSelfModifying())
        tagNeverOptimistic(lhs); 
      if (lhs instanceof IdentNode) {
        Symbol symbol = ((IdentNode)lhs).getSymbol();
        if (symbol.isInternal() && !binaryNode.rhs().isSelfModifying())
          tagNeverOptimistic(binaryNode.rhs()); 
      } 
    } else if (binaryNode.isTokenType(TokenType.INSTANCEOF) || binaryNode
      .isTokenType(TokenType.EQ_STRICT) || binaryNode
      .isTokenType(TokenType.NE_STRICT)) {
      tagNeverOptimistic(binaryNode.lhs());
      tagNeverOptimistic(binaryNode.rhs());
    } 
    return true;
  }
  
  public boolean enterCallNode(CallNode callNode) {
    tagNeverOptimistic(callNode.getFunction());
    return true;
  }
  
  public boolean enterCatchNode(CatchNode catchNode) {
    tagNeverOptimistic(catchNode.getExceptionCondition());
    return true;
  }
  
  public boolean enterExpressionStatement(ExpressionStatement expressionStatement) {
    Expression expr = expressionStatement.getExpression();
    if (!expr.isSelfModifying())
      tagNeverOptimistic(expr); 
    return true;
  }
  
  public boolean enterForNode(ForNode forNode) {
    if (forNode.isForInOrOf()) {
      tagNeverOptimistic((Expression)forNode.getModify());
    } else {
      tagNeverOptimisticLoopTest((LoopNode)forNode);
    } 
    return true;
  }
  
  public boolean enterFunctionNode(FunctionNode functionNode) {
    if (!this.neverOptimistic.isEmpty() && this.compiler.isOnDemandCompilation())
      return false; 
    this.neverOptimistic.push(new BitSet());
    return true;
  }
  
  public boolean enterIfNode(IfNode ifNode) {
    tagNeverOptimistic(ifNode.getTest());
    return true;
  }
  
  public boolean enterIndexNode(IndexNode indexNode) {
    tagNeverOptimistic(indexNode.getBase());
    return true;
  }
  
  public boolean enterTernaryNode(TernaryNode ternaryNode) {
    tagNeverOptimistic(ternaryNode.getTest());
    return true;
  }
  
  public boolean enterUnaryNode(UnaryNode unaryNode) {
    if (unaryNode.isTokenType(TokenType.NOT) || unaryNode.isTokenType(TokenType.NEW))
      tagNeverOptimistic(unaryNode.getExpression()); 
    return true;
  }
  
  public boolean enterVarNode(VarNode varNode) {
    tagNeverOptimistic((Expression)varNode.getName());
    return true;
  }
  
  public boolean enterObjectNode(ObjectNode objectNode) {
    if (objectNode.getSplitRanges() != null)
      return false; 
    return super.enterObjectNode(objectNode);
  }
  
  public boolean enterLiteralNode(LiteralNode<?> literalNode) {
    if (literalNode.isArray() && ((LiteralNode.ArrayLiteralNode)literalNode).getSplitRanges() != null)
      return false; 
    return super.enterLiteralNode(literalNode);
  }
  
  public boolean enterWhileNode(WhileNode whileNode) {
    tagNeverOptimisticLoopTest((LoopNode)whileNode);
    return true;
  }
  
  protected Node leaveDefault(Node node) {
    if (node instanceof Optimistic)
      return (Node)leaveOptimistic((Optimistic)node); 
    return node;
  }
  
  public Node leaveFunctionNode(FunctionNode functionNode) {
    this.neverOptimistic.pop();
    return (Node)functionNode;
  }
  
  public Node leaveIdentNode(IdentNode identNode) {
    Symbol symbol = identNode.getSymbol();
    if (symbol == null) {
      assert identNode.isPropertyName();
      return (Node)identNode;
    } 
    if (symbol.isBytecodeLocal())
      return (Node)identNode; 
    if (symbol.isParam() && this.lc.getCurrentFunction().isVarArg())
      return (Node)identNode.setType(identNode.getMostPessimisticType()); 
    assert symbol.isScope();
    return (Node)leaveOptimistic((Optimistic)identNode);
  }
  
  private Expression leaveOptimistic(Optimistic opt) {
    int pp = opt.getProgramPoint();
    if (UnwarrantedOptimismException.isValid(pp) && !((BitSet)this.neverOptimistic.peek()).get(pp))
      return (Expression)opt.setType(this.compiler.getOptimisticType(opt)); 
    return (Expression)opt;
  }
  
  private void tagNeverOptimistic(Expression expr) {
    if (expr instanceof Optimistic) {
      int pp = ((Optimistic)expr).getProgramPoint();
      if (UnwarrantedOptimismException.isValid(pp))
        ((BitSet)this.neverOptimistic.peek()).set(pp); 
    } 
  }
  
  private void tagNeverOptimisticLoopTest(LoopNode loopNode) {
    JoinPredecessorExpression test = loopNode.getTest();
    if (test != null)
      tagNeverOptimistic(test.getExpression()); 
  }
}
