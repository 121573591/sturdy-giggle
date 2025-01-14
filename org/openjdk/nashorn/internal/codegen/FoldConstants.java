package org.openjdk.nashorn.internal.codegen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.BinaryNode;
import org.openjdk.nashorn.internal.ir.Block;
import org.openjdk.nashorn.internal.ir.BlockStatement;
import org.openjdk.nashorn.internal.ir.CaseNode;
import org.openjdk.nashorn.internal.ir.EmptyNode;
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.IfNode;
import org.openjdk.nashorn.internal.ir.LiteralNode;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.Statement;
import org.openjdk.nashorn.internal.ir.SwitchNode;
import org.openjdk.nashorn.internal.ir.TernaryNode;
import org.openjdk.nashorn.internal.ir.UnaryNode;
import org.openjdk.nashorn.internal.ir.VarNode;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.ir.visitor.SimpleNodeVisitor;
import org.openjdk.nashorn.internal.parser.TokenType;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;
import org.openjdk.nashorn.internal.runtime.logging.Loggable;
import org.openjdk.nashorn.internal.runtime.logging.Logger;

@Logger(name = "fold")
final class FoldConstants extends SimpleNodeVisitor implements Loggable {
  private final DebugLogger log;
  
  FoldConstants(Compiler compiler) {
    this.log = initLogger(compiler.getContext());
  }
  
  public DebugLogger getLogger() {
    return this.log;
  }
  
  public DebugLogger initLogger(Context context) {
    return context.getLogger(getClass());
  }
  
  public Node leaveUnaryNode(UnaryNode unaryNode) {
    LiteralNode<?> literalNode = (new UnaryNodeConstantEvaluator(unaryNode)).eval();
    if (literalNode != null) {
      this.log.info(new Object[] { "Unary constant folded ", unaryNode, " to ", literalNode });
      return (Node)literalNode;
    } 
    return (Node)unaryNode;
  }
  
  public Node leaveBinaryNode(BinaryNode binaryNode) {
    LiteralNode<?> literalNode = (new BinaryNodeConstantEvaluator(binaryNode)).eval();
    if (literalNode != null) {
      this.log.info(new Object[] { "Binary constant folded ", binaryNode, " to ", literalNode });
      return (Node)literalNode;
    } 
    return (Node)binaryNode;
  }
  
  public Node leaveFunctionNode(FunctionNode functionNode) {
    return (Node)functionNode;
  }
  
  public Node leaveIfNode(IfNode ifNode) {
    Expression expression = ifNode.getTest();
    if (expression instanceof LiteralNode.PrimitiveLiteralNode) {
      boolean isTrue = ((LiteralNode.PrimitiveLiteralNode)expression).isTrue();
      Block executed = isTrue ? ifNode.getPass() : ifNode.getFail();
      Block dropped = isTrue ? ifNode.getFail() : ifNode.getPass();
      List<Statement> statements = new ArrayList<>();
      if (executed != null)
        statements.addAll(executed.getStatements()); 
      if (dropped != null)
        extractVarNodesFromDeadCode((Node)dropped, statements); 
      if (statements.isEmpty())
        return (Node)new EmptyNode((Statement)ifNode); 
      return (Node)BlockStatement.createReplacement((Statement)ifNode, ifNode.getFinish(), statements);
    } 
    return (Node)ifNode;
  }
  
  public Node leaveTernaryNode(TernaryNode ternaryNode) {
    Expression expression = ternaryNode.getTest();
    if (expression instanceof LiteralNode.PrimitiveLiteralNode)
      return (Node)(((LiteralNode.PrimitiveLiteralNode)expression).isTrue() ? ternaryNode.getTrueExpression() : ternaryNode.getFalseExpression()).getExpression(); 
    return (Node)ternaryNode;
  }
  
  public Node leaveSwitchNode(SwitchNode switchNode) {
    return (Node)switchNode.setUniqueInteger(this.lc, isUniqueIntegerSwitchNode(switchNode));
  }
  
  private static boolean isUniqueIntegerSwitchNode(SwitchNode switchNode) {
    Set<Integer> alreadySeen = new HashSet<>();
    for (CaseNode caseNode : switchNode.getCases()) {
      Expression test = caseNode.getTest();
      if (test != null && !isUniqueIntegerLiteral(test, alreadySeen))
        return false; 
    } 
    return true;
  }
  
  private static boolean isUniqueIntegerLiteral(Expression expr, Set<Integer> alreadySeen) {
    if (expr instanceof LiteralNode) {
      Object value = ((LiteralNode)expr).getValue();
      if (value instanceof Integer)
        return alreadySeen.add((Integer)value); 
    } 
    return false;
  }
  
  static abstract class ConstantEvaluator<T extends Node> {
    protected T parent;
    
    protected final long token;
    
    protected final int finish;
    
    protected ConstantEvaluator(T parent) {
      this.parent = parent;
      this.token = parent.getToken();
      this.finish = parent.getFinish();
    }
    
    protected abstract LiteralNode<?> eval();
  }
  
  static void extractVarNodesFromDeadCode(Node deadCodeRoot, final List<Statement> statements) {
    deadCodeRoot.accept((NodeVisitor)new SimpleNodeVisitor() {
          public boolean enterVarNode(VarNode varNode) {
            if (!varNode.isBlockScoped())
              statements.add(varNode.setInit(null)); 
            return false;
          }
          
          public boolean enterFunctionNode(FunctionNode functionNode) {
            return false;
          }
        });
  }
  
  private static class UnaryNodeConstantEvaluator extends ConstantEvaluator<UnaryNode> {
    UnaryNodeConstantEvaluator(UnaryNode parent) {
      super(parent);
    }
    
    protected LiteralNode<?> eval() {
      LiteralNode<?> literalNode;
      Expression expression = this.parent.getExpression();
      if (!(expression instanceof LiteralNode))
        return null; 
      if (expression instanceof LiteralNode.ArrayLiteralNode)
        return null; 
      LiteralNode<?> rhs = (LiteralNode)expression;
      Type rhsType = rhs.getType();
      boolean rhsInteger = (rhsType.isInteger() || rhsType.isBoolean());
      switch (this.parent.tokenType()) {
        case POS:
          if (rhsInteger) {
            literalNode = LiteralNode.newInstance(this.token, this.finish, Integer.valueOf(rhs.getInt32()));
          } else if (rhsType.isLong()) {
            literalNode = LiteralNode.newInstance(this.token, this.finish, Long.valueOf(rhs.getLong()));
          } else {
            literalNode = LiteralNode.newInstance(this.token, this.finish, Double.valueOf(rhs.getNumber()));
          } 
          return literalNode;
        case NEG:
          if (rhsInteger && rhs.getInt32() != 0) {
            literalNode = LiteralNode.newInstance(this.token, this.finish, Integer.valueOf(-rhs.getInt32()));
          } else if (rhsType.isLong() && rhs.getLong() != 0L) {
            literalNode = LiteralNode.newInstance(this.token, this.finish, Long.valueOf(-rhs.getLong()));
          } else {
            literalNode = LiteralNode.newInstance(this.token, this.finish, Double.valueOf(-rhs.getNumber()));
          } 
          return literalNode;
        case NOT:
          literalNode = LiteralNode.newInstance(this.token, this.finish, !rhs.getBoolean());
          return literalNode;
        case BIT_NOT:
          literalNode = LiteralNode.newInstance(this.token, this.finish, Integer.valueOf(rhs.getInt32() ^ 0xFFFFFFFF));
          return literalNode;
      } 
      return null;
    }
  }
  
  private static class BinaryNodeConstantEvaluator extends ConstantEvaluator<BinaryNode> {
    BinaryNodeConstantEvaluator(BinaryNode parent) {
      super(parent);
    }
    
    protected LiteralNode<?> eval() {
      LiteralNode<?> result = reduceTwoLiterals();
      if (result != null)
        return result; 
      return reduceOneLiteral();
    }
    
    private LiteralNode<?> reduceOneLiteral() {
      return null;
    }
    
    private LiteralNode<?> reduceTwoLiterals() {
      double value;
      long result;
      if (!(this.parent.lhs() instanceof LiteralNode) || !(this.parent.rhs() instanceof LiteralNode))
        return null; 
      LiteralNode<?> lhs = (LiteralNode)this.parent.lhs();
      LiteralNode<?> rhs = (LiteralNode)this.parent.rhs();
      if (lhs instanceof LiteralNode.ArrayLiteralNode || rhs instanceof LiteralNode.ArrayLiteralNode)
        return null; 
      Type widest = Type.widest(lhs.getType(), rhs.getType());
      boolean isInteger = widest.isInteger();
      switch (this.parent.tokenType()) {
        case DIV:
          value = lhs.getNumber() / rhs.getNumber();
          break;
        case ADD:
          if ((lhs.isString() || rhs.isNumeric()) && (rhs.isString() || rhs.isNumeric())) {
            Object res = ScriptRuntime.ADD(lhs.getObject(), rhs.getObject());
            if (res instanceof Number) {
              value = ((Number)res).doubleValue();
              break;
            } 
            assert res instanceof CharSequence : "" + res + " was not a CharSequence, it was a " + res;
            return LiteralNode.newInstance(this.token, this.finish, res.toString());
          } 
          return null;
        case MUL:
          value = lhs.getNumber() * rhs.getNumber();
          break;
        case MOD:
          value = lhs.getNumber() % rhs.getNumber();
          break;
        case SUB:
          value = lhs.getNumber() - rhs.getNumber();
          break;
        case SHR:
          result = JSType.toUint32(lhs.getInt32() >>> rhs.getInt32());
          return LiteralNode.newInstance(this.token, this.finish, JSType.toNarrowestNumber(result));
        case SAR:
          return LiteralNode.newInstance(this.token, this.finish, Integer.valueOf(lhs.getInt32() >> rhs.getInt32()));
        case SHL:
          return LiteralNode.newInstance(this.token, this.finish, Integer.valueOf(lhs.getInt32() << rhs.getInt32()));
        case BIT_XOR:
          return LiteralNode.newInstance(this.token, this.finish, Integer.valueOf(lhs.getInt32() ^ rhs.getInt32()));
        case BIT_AND:
          return LiteralNode.newInstance(this.token, this.finish, Integer.valueOf(lhs.getInt32() & rhs.getInt32()));
        case BIT_OR:
          return LiteralNode.newInstance(this.token, this.finish, Integer.valueOf(lhs.getInt32() | rhs.getInt32()));
        case GE:
          return LiteralNode.newInstance(this.token, this.finish, ScriptRuntime.GE(lhs.getObject(), rhs.getObject()));
        case LE:
          return LiteralNode.newInstance(this.token, this.finish, ScriptRuntime.LE(lhs.getObject(), rhs.getObject()));
        case GT:
          return LiteralNode.newInstance(this.token, this.finish, ScriptRuntime.GT(lhs.getObject(), rhs.getObject()));
        case LT:
          return LiteralNode.newInstance(this.token, this.finish, ScriptRuntime.LT(lhs.getObject(), rhs.getObject()));
        case NE:
          return LiteralNode.newInstance(this.token, this.finish, ScriptRuntime.NE(lhs.getObject(), rhs.getObject()));
        case NE_STRICT:
          return LiteralNode.newInstance(this.token, this.finish, ScriptRuntime.NE_STRICT(lhs.getObject(), rhs.getObject()));
        case EQ:
          return LiteralNode.newInstance(this.token, this.finish, ScriptRuntime.EQ(lhs.getObject(), rhs.getObject()));
        case EQ_STRICT:
          return LiteralNode.newInstance(this.token, this.finish, ScriptRuntime.EQ_STRICT(lhs.getObject(), rhs.getObject()));
        default:
          return null;
      } 
      isInteger &= JSType.isStrictlyRepresentableAsInt(value);
      if (isInteger)
        return LiteralNode.newInstance(this.token, this.finish, Integer.valueOf((int)value)); 
      return LiteralNode.newInstance(this.token, this.finish, Double.valueOf(value));
    }
  }
}
