package org.openjdk.nashorn.internal.codegen;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.AccessNode;
import org.openjdk.nashorn.internal.ir.BinaryNode;
import org.openjdk.nashorn.internal.ir.Block;
import org.openjdk.nashorn.internal.ir.BreakNode;
import org.openjdk.nashorn.internal.ir.BreakableNode;
import org.openjdk.nashorn.internal.ir.CallNode;
import org.openjdk.nashorn.internal.ir.CaseNode;
import org.openjdk.nashorn.internal.ir.CatchNode;
import org.openjdk.nashorn.internal.ir.ContinueNode;
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.ExpressionStatement;
import org.openjdk.nashorn.internal.ir.ForNode;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.GetSplitState;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.IfNode;
import org.openjdk.nashorn.internal.ir.IndexNode;
import org.openjdk.nashorn.internal.ir.JoinPredecessor;
import org.openjdk.nashorn.internal.ir.JoinPredecessorExpression;
import org.openjdk.nashorn.internal.ir.JumpStatement;
import org.openjdk.nashorn.internal.ir.JumpToInlinedFinally;
import org.openjdk.nashorn.internal.ir.LabelNode;
import org.openjdk.nashorn.internal.ir.LexicalContext;
import org.openjdk.nashorn.internal.ir.LexicalContextNode;
import org.openjdk.nashorn.internal.ir.LiteralNode;
import org.openjdk.nashorn.internal.ir.LocalVariableConversion;
import org.openjdk.nashorn.internal.ir.LoopNode;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.ObjectNode;
import org.openjdk.nashorn.internal.ir.Optimistic;
import org.openjdk.nashorn.internal.ir.PropertyNode;
import org.openjdk.nashorn.internal.ir.ReturnNode;
import org.openjdk.nashorn.internal.ir.RuntimeNode;
import org.openjdk.nashorn.internal.ir.SplitReturn;
import org.openjdk.nashorn.internal.ir.Statement;
import org.openjdk.nashorn.internal.ir.SwitchNode;
import org.openjdk.nashorn.internal.ir.Symbol;
import org.openjdk.nashorn.internal.ir.TernaryNode;
import org.openjdk.nashorn.internal.ir.ThrowNode;
import org.openjdk.nashorn.internal.ir.TryNode;
import org.openjdk.nashorn.internal.ir.UnaryNode;
import org.openjdk.nashorn.internal.ir.VarNode;
import org.openjdk.nashorn.internal.ir.WhileNode;
import org.openjdk.nashorn.internal.ir.WithNode;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.ir.visitor.SimpleNodeVisitor;
import org.openjdk.nashorn.internal.parser.TokenType;

final class LocalVariableTypesCalculator extends SimpleNodeVisitor {
  private static class JumpOrigin {
    final JoinPredecessor node;
    
    final Map<Symbol, LocalVariableTypesCalculator.LvarType> types;
    
    JumpOrigin(JoinPredecessor node, Map<Symbol, LocalVariableTypesCalculator.LvarType> types) {
      this.node = node;
      this.types = types;
    }
  }
  
  private static class JumpTarget {
    private final List<LocalVariableTypesCalculator.JumpOrigin> origins = new LinkedList<>();
    
    private Map<Symbol, LocalVariableTypesCalculator.LvarType> types = Collections.emptyMap();
    
    void addOrigin(JoinPredecessor originNode, Map<Symbol, LocalVariableTypesCalculator.LvarType> originTypes, LocalVariableTypesCalculator calc) {
      this.origins.add(new LocalVariableTypesCalculator.JumpOrigin(originNode, originTypes));
      this.types = calc.getUnionTypes(this.types, originTypes);
    }
  }
  
  private enum LvarType {
    UNDEFINED((String)Type.UNDEFINED),
    BOOLEAN((String)Type.BOOLEAN),
    INT((String)Type.INT),
    DOUBLE((String)Type.NUMBER),
    OBJECT((String)Type.OBJECT);
    
    private final Type type;
    
    private final LocalVariableTypesCalculator.TypeHolderExpression typeExpression;
    
    LvarType(Type type) {
      this.type = type;
      this.typeExpression = new LocalVariableTypesCalculator.TypeHolderExpression(type);
    }
  }
  
  private static class TypeHolderExpression extends Expression {
    private static final long serialVersionUID = 1L;
    
    private final Type type;
    
    TypeHolderExpression(Type type) {
      super(0L, 0, 0);
      this.type = type;
    }
    
    public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
      throw new AssertionError();
    }
    
    public Type getType() {
      return this.type;
    }
    
    public void toString(StringBuilder sb, boolean printType) {
      throw new AssertionError();
    }
  }
  
  private static final Map<Type, LvarType> TO_LVAR_TYPE = new IdentityHashMap<>();
  
  private final Compiler compiler;
  
  static {
    for (LvarType lvarType : LvarType.values())
      TO_LVAR_TYPE.put(lvarType.type, lvarType); 
  }
  
  private static HashMap<Symbol, LvarType> cloneMap(Map<Symbol, LvarType> map) {
    return (HashMap<Symbol, LvarType>)((HashMap)map).clone();
  }
  
  private LocalVariableConversion createConversion(Symbol symbol, LvarType branchLvarType, Map<Symbol, LvarType> joinLvarTypes, LocalVariableConversion next) {
    if (this.invalidatedSymbols.contains(symbol))
      return next; 
    LvarType targetType = joinLvarTypes.get(symbol);
    assert targetType != null;
    if (targetType == branchLvarType)
      return next; 
    symbolIsConverted(symbol, branchLvarType, targetType);
    return new LocalVariableConversion(symbol, branchLvarType.type, targetType.type, next);
  }
  
  private Map<Symbol, LvarType> getUnionTypes(Map<Symbol, LvarType> types1, Map<Symbol, LvarType> types2) {
    Map<Symbol, LvarType> union;
    if (types1 == types2 || types1.isEmpty())
      return types2; 
    if (types2.isEmpty())
      return types1; 
    Set<Symbol> commonSymbols = new HashSet<>(types1.keySet());
    commonSymbols.retainAll(types2.keySet());
    int commonSize = commonSymbols.size();
    int types1Size = types1.size();
    int types2Size = types2.size();
    if (commonSize == types1Size && commonSize == types2Size) {
      boolean matches1 = true, matches2 = true;
      Map<Symbol, LvarType> map = null;
      for (Symbol symbol : commonSymbols) {
        LvarType type1 = types1.get(symbol);
        LvarType type2 = types2.get(symbol);
        LvarType widest = widestLvarType(type1, type2);
        if (widest != type1 && matches1) {
          matches1 = false;
          if (!matches2)
            map = cloneMap(types1); 
        } 
        if (widest != type2 && matches2) {
          matches2 = false;
          if (!matches1)
            map = cloneMap(types2); 
        } 
        if (!matches1 && !matches2) {
          assert map != null;
          map.put(symbol, widest);
        } 
      } 
      return matches1 ? types1 : (matches2 ? types2 : map);
    } 
    if (types1Size > types2Size) {
      union = cloneMap(types1);
      union.putAll(types2);
    } else {
      union = cloneMap(types2);
      union.putAll(types1);
    } 
    for (Symbol symbol : commonSymbols) {
      LvarType type1 = types1.get(symbol);
      LvarType type2 = types2.get(symbol);
      union.put(symbol, widestLvarType(type1, type2));
    } 
    union.keySet().removeAll(this.invalidatedSymbols);
    return union;
  }
  
  private static void symbolIsUsed(Symbol symbol, LvarType type) {
    if (type != LvarType.UNDEFINED)
      symbol.setHasSlotFor(type.type); 
  }
  
  private static class SymbolConversions {
    private static final byte I2D = 1;
    
    private static final byte I2O = 2;
    
    private static final byte D2O = 4;
    
    private byte conversions;
    
    void recordConversion(LocalVariableTypesCalculator.LvarType from, LocalVariableTypesCalculator.LvarType to) {
      switch (from) {
        case null:
          return;
        case null:
        case null:
          switch (to) {
            case EQ_STRICT:
              recordConversion((byte)1);
              return;
            case NE_STRICT:
              recordConversion((byte)2);
              return;
          } 
          illegalConversion(from, to);
          return;
        case EQ_STRICT:
          if (to == LocalVariableTypesCalculator.LvarType.OBJECT)
            recordConversion((byte)4); 
          return;
      } 
      illegalConversion(from, to);
    }
    
    private static void illegalConversion(LocalVariableTypesCalculator.LvarType from, LocalVariableTypesCalculator.LvarType to) {
      throw new AssertionError("Invalid conversion from " + from + " to " + to);
    }
    
    void recordConversion(byte convFlag) {
      this.conversions = (byte)(this.conversions | convFlag);
    }
    
    boolean hasConversion(byte convFlag) {
      return ((this.conversions & convFlag) != 0);
    }
    
    void calculateTypeLiveness(Symbol symbol) {
      if (symbol.hasSlotFor(Type.OBJECT)) {
        if (hasConversion((byte)4))
          symbol.setHasSlotFor((Type)Type.NUMBER); 
        if (hasConversion((byte)2))
          symbol.setHasSlotFor((Type)Type.INT); 
      } 
      if (symbol.hasSlotFor((Type)Type.NUMBER) && 
        hasConversion((byte)1))
        symbol.setHasSlotFor((Type)Type.INT); 
    }
  }
  
  private void symbolIsConverted(Symbol symbol, LvarType from, LvarType to) {
    SymbolConversions conversions = this.symbolConversions.get(symbol);
    if (conversions == null) {
      conversions = new SymbolConversions();
      this.symbolConversions.put(symbol, conversions);
    } 
    conversions.recordConversion(from, to);
  }
  
  private static LvarType toLvarType(Type type) {
    assert type != null;
    LvarType lvarType = TO_LVAR_TYPE.get(type);
    if (lvarType != null)
      return lvarType; 
    assert type.isObject() : "Unsupported primitive type: " + type;
    return LvarType.OBJECT;
  }
  
  private static LvarType widestLvarType(LvarType t1, LvarType t2) {
    if (t1 == t2)
      return t1; 
    if (t1.ordinal() < LvarType.INT.ordinal() || t2.ordinal() < LvarType.INT.ordinal())
      return LvarType.OBJECT; 
    return LvarType.values()[Math.max(t1.ordinal(), t2.ordinal())];
  }
  
  private final Map<Label, JumpTarget> jumpTargets = new IdentityHashMap<>();
  
  private Map<Symbol, LvarType> localVariableTypes = Collections.emptyMap();
  
  private final Set<Symbol> invalidatedSymbols = new HashSet<>();
  
  private final Deque<LvarType> typeStack = new ArrayDeque<>();
  
  private boolean reachable = true;
  
  private Type returnType = Type.UNKNOWN;
  
  private ReturnNode syntheticReturn;
  
  private boolean alreadyEnteredTopLevelFunction;
  
  private final Map<JoinPredecessor, LocalVariableConversion> localVariableConversions = new IdentityHashMap<>();
  
  private final Map<IdentNode, LvarType> identifierLvarTypes = new IdentityHashMap<>();
  
  private final Map<Symbol, SymbolConversions> symbolConversions = new IdentityHashMap<>();
  
  private final Deque<Label> catchLabels = new ArrayDeque<>();
  
  private LocalVariableTypesCalculator(Compiler compiler) {
    this.compiler = compiler;
  }
  
  LocalVariableTypesCalculator(Compiler compiler, Type returnType) {
    this(compiler);
    this.returnType = returnType;
  }
  
  private JumpTarget createJumpTarget(Label label) {
    assert !this.jumpTargets.containsKey(label);
    JumpTarget jumpTarget = new JumpTarget();
    this.jumpTargets.put(label, jumpTarget);
    return jumpTarget;
  }
  
  private void doesNotContinueSequentially() {
    this.reachable = false;
    this.localVariableTypes = Collections.emptyMap();
    assertTypeStackIsEmpty();
  }
  
  private boolean pushExpressionType(Expression expr) {
    this.typeStack.push(toLvarType(expr.getType()));
    return false;
  }
  
  public boolean enterAccessNode(AccessNode accessNode) {
    visitExpression(accessNode.getBase());
    return pushExpressionType((Expression)accessNode);
  }
  
  public boolean enterBinaryNode(BinaryNode binaryNode) {
    LvarType lhsType;
    Expression lhs = binaryNode.lhs();
    if (!(lhs instanceof IdentNode) || !binaryNode.isTokenType(TokenType.ASSIGN)) {
      lhsType = visitExpression(lhs);
    } else {
      lhsType = LvarType.UNDEFINED;
    } 
    boolean isLogical = binaryNode.isLogical();
    Label joinLabel = isLogical ? new Label("") : null;
    if (isLogical)
      jumpToLabel((JoinPredecessor)lhs, joinLabel); 
    Expression rhs = binaryNode.rhs();
    LvarType rhsType = visitExpression(rhs);
    if (isLogical)
      jumpToLabel((JoinPredecessor)rhs, joinLabel); 
    joinOnLabel(joinLabel);
    LvarType type = toLvarType(binaryNode.setOperands(lhsType.typeExpression, rhsType.typeExpression).getType());
    if (binaryNode.isAssignment() && lhs instanceof IdentNode)
      if (binaryNode.isSelfModifying()) {
        onSelfAssignment((IdentNode)lhs, type);
      } else {
        onAssignment((IdentNode)lhs, type);
      }  
    this.typeStack.push(type);
    return false;
  }
  
  public boolean enterBlock(Block block) {
    boolean cloned = false;
    for (Symbol symbol : block.getSymbols()) {
      if (symbol.isBytecodeLocal()) {
        if (getLocalVariableTypeOrNull(symbol) == null) {
          if (!cloned) {
            cloneOrNewLocalVariableTypes();
            cloned = true;
          } 
          this.localVariableTypes.put(symbol, LvarType.UNDEFINED);
        } 
        this.invalidatedSymbols.remove(symbol);
      } 
    } 
    return true;
  }
  
  public boolean enterBreakNode(BreakNode breakNode) {
    return enterJumpStatement((JumpStatement)breakNode);
  }
  
  public boolean enterCallNode(CallNode callNode) {
    visitExpression(callNode.getFunction());
    visitExpressions(callNode.getArgs());
    CallNode.EvalArgs evalArgs = callNode.getEvalArgs();
    if (evalArgs != null)
      visitExpressions(evalArgs.getArgs()); 
    return pushExpressionType((Expression)callNode);
  }
  
  public boolean enterContinueNode(ContinueNode continueNode) {
    return enterJumpStatement((JumpStatement)continueNode);
  }
  
  private boolean enterJumpStatement(JumpStatement jump) {
    if (!this.reachable)
      return false; 
    assertTypeStackIsEmpty();
    jumpToLabel((JoinPredecessor)jump, jump.getTargetLabel(this.lc), getBreakTargetTypes(jump.getPopScopeLimit(this.lc)));
    doesNotContinueSequentially();
    return false;
  }
  
  protected boolean enterDefault(Node node) {
    return this.reachable;
  }
  
  private void enterDoWhileLoop(WhileNode loopNode) {
    assertTypeStackIsEmpty();
    JoinPredecessorExpression test = loopNode.getTest();
    Block body = loopNode.getBody();
    Label continueLabel = loopNode.getContinueLabel();
    Label breakLabel = loopNode.getBreakLabel();
    Map<Symbol, LvarType> beforeLoopTypes = this.localVariableTypes;
    Label repeatLabel = new Label("");
    while (true) {
      jumpToLabel((JoinPredecessor)loopNode, repeatLabel, beforeLoopTypes);
      Map<Symbol, LvarType> beforeRepeatTypes = this.localVariableTypes;
      body.accept((NodeVisitor)this);
      if (this.reachable)
        jumpToLabel((JoinPredecessor)body, continueLabel); 
      joinOnLabel(continueLabel);
      if (!this.reachable)
        break; 
      visitExpressionOnEmptyStack((Expression)test);
      jumpToLabel((JoinPredecessor)test, breakLabel);
      if (Expression.isAlwaysFalse((Expression)test))
        break; 
      jumpToLabel((JoinPredecessor)test, repeatLabel);
      joinOnLabel(repeatLabel);
      if (this.localVariableTypes.equals(beforeRepeatTypes))
        break; 
      resetJoinPoint(continueLabel);
      resetJoinPoint(breakLabel);
      resetJoinPoint(repeatLabel);
    } 
    if (Expression.isAlwaysTrue((Expression)test))
      doesNotContinueSequentially(); 
    leaveBreakable((BreakableNode)loopNode);
  }
  
  public boolean enterExpressionStatement(ExpressionStatement expressionStatement) {
    if (this.reachable)
      visitExpressionOnEmptyStack(expressionStatement.getExpression()); 
    return false;
  }
  
  private void assertTypeStackIsEmpty() {
    assert this.typeStack.isEmpty();
  }
  
  protected Node leaveDefault(Node node) {
    assert !(node instanceof Expression);
    assert !(node instanceof Statement) || this.typeStack.isEmpty();
    return node;
  }
  
  private LvarType visitExpressionOnEmptyStack(Expression expr) {
    assertTypeStackIsEmpty();
    return visitExpression(expr);
  }
  
  private LvarType visitExpression(Expression expr) {
    int stackSize = this.typeStack.size();
    expr.accept((NodeVisitor)this);
    assert this.typeStack.size() == stackSize + 1;
    return this.typeStack.pop();
  }
  
  private void visitExpressions(List<Expression> exprs) {
    for (Expression expr : exprs) {
      if (expr != null)
        visitExpression(expr); 
    } 
  }
  
  public boolean enterForNode(ForNode forNode) {
    if (!this.reachable)
      return false; 
    Expression init = forNode.getInit();
    if (forNode.isForInOrOf()) {
      JoinPredecessorExpression iterable = forNode.getModify();
      visitExpression((Expression)iterable);
      enterTestFirstLoop((LoopNode)forNode, null, init, (
          
          !this.compiler.useOptimisticTypes() || (!forNode.isForEach() && this.compiler.hasStringPropertyIterator(iterable.getExpression()))));
    } else {
      if (init != null)
        visitExpressionOnEmptyStack(init); 
      enterTestFirstLoop((LoopNode)forNode, forNode.getModify(), null, false);
    } 
    assertTypeStackIsEmpty();
    return false;
  }
  
  public boolean enterFunctionNode(FunctionNode functionNode) {
    if (this.alreadyEnteredTopLevelFunction) {
      this.typeStack.push(LvarType.OBJECT);
      return false;
    } 
    int pos = 0;
    if (!functionNode.isVarArg())
      for (IdentNode param : functionNode.getParameters()) {
        Symbol symbol = param.getSymbol();
        assert symbol.hasSlot();
        Type callSiteParamType = this.compiler.getParamType(functionNode, pos);
        LvarType paramType = (callSiteParamType == null) ? LvarType.OBJECT : toLvarType(callSiteParamType);
        setType(symbol, paramType);
        symbolIsUsed(symbol);
        setIdentifierLvarType(param, paramType);
        pos++;
      }  
    setCompilerConstantAsObject(functionNode, CompilerConstants.THIS);
    if (functionNode.hasScopeBlock() || functionNode.needsParentScope())
      setCompilerConstantAsObject(functionNode, CompilerConstants.SCOPE); 
    if (functionNode.needsCallee())
      setCompilerConstantAsObject(functionNode, CompilerConstants.CALLEE); 
    if (functionNode.needsArguments())
      setCompilerConstantAsObject(functionNode, CompilerConstants.ARGUMENTS); 
    this.alreadyEnteredTopLevelFunction = true;
    return true;
  }
  
  public boolean enterGetSplitState(GetSplitState getSplitState) {
    return pushExpressionType((Expression)getSplitState);
  }
  
  public boolean enterIdentNode(IdentNode identNode) {
    Symbol symbol = identNode.getSymbol();
    if (symbol.isBytecodeLocal()) {
      symbolIsUsed(symbol);
      LvarType type = getLocalVariableType(symbol);
      setIdentifierLvarType(identNode, type);
      this.typeStack.push(type);
    } else {
      pushExpressionType((Expression)identNode);
    } 
    return false;
  }
  
  public boolean enterIfNode(IfNode ifNode) {
    processIfNode(ifNode);
    return false;
  }
  
  private void processIfNode(IfNode ifNode) {
    Map<Symbol, LvarType> passLvarTypes;
    boolean reachableFromPass;
    if (!this.reachable)
      return; 
    Expression test = ifNode.getTest();
    Block pass = ifNode.getPass();
    Block fail = ifNode.getFail();
    visitExpressionOnEmptyStack(test);
    boolean isTestAlwaysTrue = Expression.isAlwaysTrue(test);
    if (Expression.isAlwaysFalse(test)) {
      passLvarTypes = null;
      reachableFromPass = false;
    } else {
      Map<Symbol, LvarType> afterTestLvarTypes = this.localVariableTypes;
      pass.accept((NodeVisitor)this);
      assertTypeStackIsEmpty();
      if (isTestAlwaysTrue)
        return; 
      passLvarTypes = this.localVariableTypes;
      reachableFromPass = this.reachable;
      this.localVariableTypes = afterTestLvarTypes;
      this.reachable = true;
    } 
    assert !isTestAlwaysTrue;
    if (fail != null) {
      fail.accept((NodeVisitor)this);
      assertTypeStackIsEmpty();
    } 
    if (this.reachable) {
      if (reachableFromPass) {
        Map<Symbol, LvarType> failLvarTypes = this.localVariableTypes;
        this.localVariableTypes = getUnionTypes(passLvarTypes, failLvarTypes);
        setConversion((JoinPredecessor)pass, passLvarTypes, this.localVariableTypes);
        setConversion((fail != null) ? (JoinPredecessor)fail : (JoinPredecessor)ifNode, failLvarTypes, this.localVariableTypes);
      } 
    } else if (reachableFromPass) {
      assert passLvarTypes != null;
      this.localVariableTypes = passLvarTypes;
      this.reachable = true;
    } 
  }
  
  public boolean enterIndexNode(IndexNode indexNode) {
    visitExpression(indexNode.getBase());
    visitExpression(indexNode.getIndex());
    return pushExpressionType((Expression)indexNode);
  }
  
  public boolean enterJoinPredecessorExpression(JoinPredecessorExpression joinExpr) {
    Expression expr = joinExpr.getExpression();
    if (expr != null) {
      expr.accept((NodeVisitor)this);
    } else {
      this.typeStack.push(LvarType.UNDEFINED);
    } 
    return false;
  }
  
  public boolean enterJumpToInlinedFinally(JumpToInlinedFinally jumpToInlinedFinally) {
    return enterJumpStatement((JumpStatement)jumpToInlinedFinally);
  }
  
  public boolean enterLiteralNode(LiteralNode<?> literalNode) {
    if (literalNode instanceof LiteralNode.ArrayLiteralNode) {
      List<Expression> expressions = ((LiteralNode.ArrayLiteralNode)literalNode).getElementExpressions();
      if (expressions != null)
        visitExpressions(expressions); 
    } 
    pushExpressionType((Expression)literalNode);
    return false;
  }
  
  public boolean enterObjectNode(ObjectNode objectNode) {
    for (PropertyNode propertyNode : objectNode.getElements()) {
      Expression value = propertyNode.getValue();
      if (value != null)
        visitExpression(value); 
    } 
    return pushExpressionType((Expression)objectNode);
  }
  
  public boolean enterPropertyNode(PropertyNode propertyNode) {
    throw new AssertionError();
  }
  
  public boolean enterReturnNode(ReturnNode returnNode) {
    Type returnExprType;
    if (!this.reachable)
      return false; 
    Expression returnExpr = returnNode.getExpression();
    if (returnExpr != null) {
      returnExprType = (visitExpressionOnEmptyStack(returnExpr)).type;
    } else {
      assertTypeStackIsEmpty();
      returnExprType = Type.UNDEFINED;
    } 
    this.returnType = Type.widestReturnType(this.returnType, returnExprType);
    doesNotContinueSequentially();
    return false;
  }
  
  public boolean enterRuntimeNode(RuntimeNode runtimeNode) {
    visitExpressions(runtimeNode.getArgs());
    return pushExpressionType((Expression)runtimeNode);
  }
  
  public boolean enterSplitReturn(SplitReturn splitReturn) {
    doesNotContinueSequentially();
    return false;
  }
  
  public boolean enterSwitchNode(SwitchNode switchNode) {
    if (!this.reachable)
      return false; 
    visitExpressionOnEmptyStack(switchNode.getExpression());
    List<CaseNode> cases = switchNode.getCases();
    if (cases.isEmpty())
      return false; 
    boolean isInteger = switchNode.isUniqueInteger();
    Label breakLabel = switchNode.getBreakLabel();
    boolean hasDefault = (switchNode.getDefaultCase() != null);
    boolean tagUsed = false;
    for (CaseNode caseNode : cases) {
      Expression test = caseNode.getTest();
      if (!isInteger && test != null) {
        visitExpressionOnEmptyStack(test);
        if (!tagUsed) {
          symbolIsUsed(switchNode.getTag(), LvarType.OBJECT);
          tagUsed = true;
        } 
      } 
      jumpToLabel((JoinPredecessor)caseNode, caseNode.getBody().getEntryLabel());
    } 
    if (!hasDefault)
      jumpToLabel((JoinPredecessor)switchNode, breakLabel); 
    doesNotContinueSequentially();
    Block previousBlock = null;
    for (CaseNode caseNode : cases) {
      Block body = caseNode.getBody();
      Label entryLabel = body.getEntryLabel();
      if (previousBlock != null && this.reachable)
        jumpToLabel((JoinPredecessor)previousBlock, entryLabel); 
      joinOnLabel(entryLabel);
      assert this.reachable == true;
      body.accept((NodeVisitor)this);
      previousBlock = body;
    } 
    if (previousBlock != null && this.reachable)
      jumpToLabel((JoinPredecessor)previousBlock, breakLabel); 
    leaveBreakable((BreakableNode)switchNode);
    return false;
  }
  
  public boolean enterTernaryNode(TernaryNode ternaryNode) {
    LvarType trueType, falseType;
    Expression test = ternaryNode.getTest();
    JoinPredecessorExpression joinPredecessorExpression1 = ternaryNode.getTrueExpression();
    JoinPredecessorExpression joinPredecessorExpression2 = ternaryNode.getFalseExpression();
    visitExpression(test);
    Map<Symbol, LvarType> testExitLvarTypes = this.localVariableTypes;
    if (!Expression.isAlwaysFalse(test)) {
      trueType = visitExpression((Expression)joinPredecessorExpression1);
    } else {
      trueType = null;
    } 
    Map<Symbol, LvarType> trueExitLvarTypes = this.localVariableTypes;
    this.localVariableTypes = testExitLvarTypes;
    if (!Expression.isAlwaysTrue(test)) {
      falseType = visitExpression((Expression)joinPredecessorExpression2);
    } else {
      falseType = null;
    } 
    Map<Symbol, LvarType> falseExitLvarTypes = this.localVariableTypes;
    this.localVariableTypes = getUnionTypes(trueExitLvarTypes, falseExitLvarTypes);
    setConversion((JoinPredecessor)joinPredecessorExpression1, trueExitLvarTypes, this.localVariableTypes);
    setConversion((JoinPredecessor)joinPredecessorExpression2, falseExitLvarTypes, this.localVariableTypes);
    this.typeStack.push((trueType != null) ? ((falseType != null) ? widestLvarType(trueType, falseType) : trueType) : assertNotNull(falseType));
    return false;
  }
  
  private static <T> T assertNotNull(T t) {
    assert t != null;
    return t;
  }
  
  private void enterTestFirstLoop(LoopNode loopNode, JoinPredecessorExpression modify, Expression iteratorValues, boolean iteratorValuesAreObject) {
    JoinPredecessorExpression test = loopNode.getTest();
    if (Expression.isAlwaysFalse((Expression)test)) {
      visitExpressionOnEmptyStack((Expression)test);
      return;
    } 
    Label continueLabel = loopNode.getContinueLabel();
    Label breakLabel = loopNode.getBreakLabel();
    Label repeatLabel = (modify == null) ? continueLabel : new Label("");
    Map<Symbol, LvarType> beforeLoopTypes = this.localVariableTypes;
    while (true) {
      jumpToLabel((JoinPredecessor)loopNode, repeatLabel, beforeLoopTypes);
      Map<Symbol, LvarType> beforeRepeatTypes = this.localVariableTypes;
      if (test != null)
        visitExpressionOnEmptyStack((Expression)test); 
      if (!Expression.isAlwaysTrue((Expression)test))
        jumpToLabel((JoinPredecessor)test, breakLabel); 
      if (iteratorValues instanceof IdentNode) {
        IdentNode ident = (IdentNode)iteratorValues;
        onAssignment(ident, iteratorValuesAreObject ? LvarType.OBJECT : 
            toLvarType(this.compiler.getOptimisticType((Optimistic)ident)));
      } 
      Block body = loopNode.getBody();
      body.accept((NodeVisitor)this);
      if (this.reachable)
        jumpToLabel((JoinPredecessor)body, continueLabel); 
      joinOnLabel(continueLabel);
      if (!this.reachable)
        break; 
      if (modify != null) {
        visitExpressionOnEmptyStack((Expression)modify);
        jumpToLabel((JoinPredecessor)modify, repeatLabel);
        joinOnLabel(repeatLabel);
      } 
      if (this.localVariableTypes.equals(beforeRepeatTypes))
        break; 
      resetJoinPoint(continueLabel);
      resetJoinPoint(breakLabel);
      resetJoinPoint(repeatLabel);
    } 
    if (Expression.isAlwaysTrue((Expression)test) && iteratorValues == null)
      doesNotContinueSequentially(); 
    leaveBreakable((BreakableNode)loopNode);
  }
  
  public boolean enterThrowNode(ThrowNode throwNode) {
    if (!this.reachable)
      return false; 
    visitExpressionOnEmptyStack(throwNode.getExpression());
    jumpToCatchBlock((JoinPredecessor)throwNode);
    doesNotContinueSequentially();
    return false;
  }
  
  public boolean enterTryNode(TryNode tryNode) {
    if (!this.reachable)
      return false; 
    Label catchLabel = new Label("");
    this.catchLabels.push(catchLabel);
    jumpToLabel((JoinPredecessor)tryNode, catchLabel);
    Block body = tryNode.getBody();
    body.accept((NodeVisitor)this);
    this.catchLabels.pop();
    Label endLabel = new Label("");
    boolean canExit = false;
    if (this.reachable) {
      jumpToLabel((JoinPredecessor)body, endLabel);
      canExit = true;
    } 
    doesNotContinueSequentially();
    for (Block inlinedFinally : tryNode.getInlinedFinallies()) {
      Block finallyBody = TryNode.getLabelledInlinedFinallyBlock(inlinedFinally);
      joinOnLabel(finallyBody.getEntryLabel());
      if (this.reachable) {
        finallyBody.accept((NodeVisitor)this);
        assert !this.reachable;
      } 
    } 
    joinOnLabel(catchLabel);
    for (CatchNode catchNode : tryNode.getCatches()) {
      IdentNode exception = catchNode.getExceptionIdentifier();
      onAssignment(exception, LvarType.OBJECT);
      Expression condition = catchNode.getExceptionCondition();
      if (condition != null)
        visitExpression(condition); 
      Map<Symbol, LvarType> afterConditionTypes = this.localVariableTypes;
      Block catchBody = catchNode.getBody();
      this.reachable = true;
      catchBody.accept((NodeVisitor)this);
      if (this.reachable) {
        jumpToLabel((JoinPredecessor)catchBody, endLabel);
        canExit = true;
      } 
      this.localVariableTypes = afterConditionTypes;
    } 
    doesNotContinueSequentially();
    if (canExit)
      joinOnLabel(endLabel); 
    return false;
  }
  
  public boolean enterUnaryNode(UnaryNode unaryNode) {
    LvarType unaryType;
    Expression expr = unaryNode.getExpression();
    if (unaryNode.tokenType() == TokenType.DELETE && expr instanceof IdentNode) {
      unaryType = toLvarType(unaryNode.getType());
    } else {
      unaryType = toLvarType(unaryNode.setExpression((visitExpression(expr)).typeExpression).getType());
      if (unaryNode.isSelfModifying() && expr instanceof IdentNode)
        onSelfAssignment((IdentNode)expr, unaryType); 
    } 
    this.typeStack.push(unaryType);
    return false;
  }
  
  public boolean enterVarNode(VarNode varNode) {
    if (!this.reachable)
      return false; 
    Expression init = varNode.getInit();
    if (init != null)
      onAssignment(varNode.getName(), visitExpression(init)); 
    return false;
  }
  
  public boolean enterWhileNode(WhileNode whileNode) {
    if (!this.reachable)
      return false; 
    if (whileNode.isDoWhile()) {
      enterDoWhileLoop(whileNode);
    } else {
      enterTestFirstLoop((LoopNode)whileNode, null, null, false);
    } 
    return false;
  }
  
  public boolean enterWithNode(WithNode withNode) {
    if (this.reachable) {
      visitExpression(withNode.getExpression());
      withNode.getBody().accept((NodeVisitor)this);
    } 
    return false;
  }
  
  private Map<Symbol, LvarType> getBreakTargetTypes(LexicalContextNode target) {
    Map<Symbol, LvarType> types = this.localVariableTypes;
    for (Iterator<LexicalContextNode> it = this.lc.getAllNodes(); it.hasNext(); ) {
      LexicalContextNode node = it.next();
      if (node instanceof Block)
        for (Symbol symbol : ((Block)node).getSymbols()) {
          if (this.localVariableTypes.containsKey(symbol)) {
            if (types == this.localVariableTypes)
              types = cloneMap(this.localVariableTypes); 
            types.remove(symbol);
          } 
        }  
      if (node == target)
        break; 
    } 
    return types;
  }
  
  private LvarType getLocalVariableType(Symbol symbol) {
    LvarType type = getLocalVariableTypeOrNull(symbol);
    assert type != null;
    return type;
  }
  
  private LvarType getLocalVariableTypeOrNull(Symbol symbol) {
    return this.localVariableTypes.get(symbol);
  }
  
  private JumpTarget getOrCreateJumpTarget(Label label) {
    JumpTarget jumpTarget = this.jumpTargets.get(label);
    if (jumpTarget == null)
      jumpTarget = createJumpTarget(label); 
    return jumpTarget;
  }
  
  private void joinOnLabel(Label label) {
    JumpTarget jumpTarget = this.jumpTargets.remove(label);
    if (jumpTarget == null)
      return; 
    assert !jumpTarget.origins.isEmpty();
    this.reachable = true;
    this.localVariableTypes = getUnionTypes(jumpTarget.types, this.localVariableTypes);
    for (JumpOrigin jumpOrigin : jumpTarget.origins)
      setConversion(jumpOrigin.node, jumpOrigin.types, this.localVariableTypes); 
  }
  
  private void jumpToCatchBlock(JoinPredecessor jumpOrigin) {
    Label currentCatchLabel = this.catchLabels.peek();
    if (currentCatchLabel != null)
      jumpToLabel(jumpOrigin, currentCatchLabel); 
  }
  
  private void jumpToLabel(JoinPredecessor jumpOrigin, Label label) {
    jumpToLabel(jumpOrigin, label, this.localVariableTypes);
  }
  
  private void jumpToLabel(JoinPredecessor jumpOrigin, Label label, Map<Symbol, LvarType> types) {
    getOrCreateJumpTarget(label).addOrigin(jumpOrigin, types, this);
  }
  
  public Node leaveBlock(Block block) {
    if (this.lc.isFunctionBody()) {
      if (this.reachable) {
        createSyntheticReturn(block);
        assert !this.reachable;
      } 
      calculateReturnType();
    } 
    boolean cloned = false;
    for (Symbol symbol : block.getSymbols()) {
      if (symbol.hasSlot()) {
        if (symbol.isBytecodeLocal()) {
          if (this.localVariableTypes.containsKey(symbol) && 
            !cloned) {
            this.localVariableTypes = cloneMap(this.localVariableTypes);
            cloned = true;
          } 
          invalidateSymbol(symbol);
        } 
        SymbolConversions conversions = this.symbolConversions.get(symbol);
        if (conversions != null)
          conversions.calculateTypeLiveness(symbol); 
        if (symbol.slotCount() == 0)
          symbol.setNeedsSlot(false); 
      } 
    } 
    if (this.reachable) {
      LabelNode labelNode = this.lc.getCurrentBlockLabelNode();
      if (labelNode != null)
        jumpToLabel((JoinPredecessor)labelNode, block.getBreakLabel()); 
    } 
    leaveBreakable((BreakableNode)block);
    return (Node)block;
  }
  
  private void calculateReturnType() {
    if (this.returnType.isUnknown())
      this.returnType = Type.OBJECT; 
  }
  
  private void createSyntheticReturn(Block body) {
    IdentNode returnExpr;
    FunctionNode functionNode = this.lc.getCurrentFunction();
    long token = functionNode.getToken();
    int finish = functionNode.getFinish();
    List<Statement> statements = body.getStatements();
    int lineNumber = statements.isEmpty() ? functionNode.getLineNumber() : ((Statement)statements.get(statements.size() - 1)).getLineNumber();
    if (functionNode.isProgram()) {
      returnExpr = (new IdentNode(token, finish, CompilerConstants.RETURN.symbolName())).setSymbol(getCompilerConstantSymbol(functionNode, CompilerConstants.RETURN));
    } else {
      returnExpr = null;
    } 
    this.syntheticReturn = new ReturnNode(lineNumber, token, finish, (Expression)returnExpr);
    this.syntheticReturn.accept((NodeVisitor)this);
  }
  
  private void leaveBreakable(BreakableNode breakable) {
    joinOnLabel(breakable.getBreakLabel());
    assertTypeStackIsEmpty();
  }
  
  public Node leaveFunctionNode(FunctionNode functionNode) {
    FunctionNode newFunction = functionNode;
    SimpleNodeVisitor applyChangesVisitor = new SimpleNodeVisitor() {
        private boolean inOuterFunction = true;
        
        private final Deque<JoinPredecessor> joinPredecessors = new ArrayDeque<>();
        
        protected boolean enterDefault(Node node) {
          if (!this.inOuterFunction)
            return false; 
          if (node instanceof JoinPredecessor)
            this.joinPredecessors.push((JoinPredecessor)node); 
          return this.inOuterFunction;
        }
        
        public boolean enterFunctionNode(FunctionNode fn) {
          if (LocalVariableTypesCalculator.this.compiler.isOnDemandCompilation())
            return false; 
          this.inOuterFunction = false;
          return true;
        }
        
        public boolean enterUnaryNode(UnaryNode unaryNode) {
          return (unaryNode.tokenType() != TokenType.DELETE || !(unaryNode.getExpression() instanceof IdentNode));
        }
        
        public Node leaveBinaryNode(BinaryNode binaryNode) {
          if (binaryNode.isComparison()) {
            Expression undefinedNode, lhs = binaryNode.lhs();
            Expression rhs = binaryNode.rhs();
            TokenType tt = binaryNode.tokenType();
            switch (tt) {
              case EQ_STRICT:
              case NE_STRICT:
                undefinedNode = LocalVariableTypesCalculator.createIsUndefined((Expression)binaryNode, lhs, rhs, 
                    (tt == TokenType.EQ_STRICT) ? RuntimeNode.Request.IS_UNDEFINED : RuntimeNode.Request.IS_NOT_UNDEFINED);
                if (undefinedNode != binaryNode)
                  return (Node)undefinedNode; 
                if (lhs.getType().isBoolean() != rhs.getType().isBoolean())
                  return (Node)new RuntimeNode(binaryNode); 
                break;
            } 
            if (lhs.getType().isObject() && rhs.getType().isObject())
              return (Node)new RuntimeNode(binaryNode); 
          } else if (binaryNode.isOptimisticUndecidedType()) {
            return (Node)binaryNode.decideType();
          } 
          return (Node)binaryNode;
        }
        
        protected Node leaveDefault(Node node) {
          if (node instanceof JoinPredecessor) {
            JoinPredecessor original = this.joinPredecessors.pop();
            assert original.getClass() == node.getClass() : original.getClass().getName() + "!=" + original.getClass().getName();
            JoinPredecessor newNode = setLocalVariableConversion(original, (JoinPredecessor)node);
            if (newNode instanceof LexicalContextNode)
              this.lc.replace((LexicalContextNode)node, (LexicalContextNode)newNode); 
            return (Node)newNode;
          } 
          return node;
        }
        
        public Node leaveBlock(Block block) {
          if (this.inOuterFunction && LocalVariableTypesCalculator.this.syntheticReturn != null && this.lc.isFunctionBody()) {
            ArrayList<Statement> stmts = new ArrayList<>(block.getStatements());
            stmts.add(LocalVariableTypesCalculator.this.syntheticReturn.accept((NodeVisitor)this));
            return (Node)block.setStatements(this.lc, stmts);
          } 
          return super.leaveBlock(block);
        }
        
        public Node leaveFunctionNode(FunctionNode nestedFunctionNode) {
          this.inOuterFunction = true;
          FunctionNode newNestedFunction = (FunctionNode)nestedFunctionNode.accept((NodeVisitor)new LocalVariableTypesCalculator(LocalVariableTypesCalculator.this.compiler));
          this.lc.replace((LexicalContextNode)nestedFunctionNode, (LexicalContextNode)newNestedFunction);
          return (Node)newNestedFunction;
        }
        
        public Node leaveIdentNode(IdentNode identNode) {
          IdentNode original = (IdentNode)this.joinPredecessors.pop();
          Symbol symbol = identNode.getSymbol();
          if (symbol == null) {
            assert identNode.isPropertyName();
            return (Node)identNode;
          } 
          if (symbol.hasSlot()) {
            assert !symbol.isScope() || symbol.isParam();
            assert original.getName().equals(identNode.getName());
            LocalVariableTypesCalculator.LvarType lvarType = LocalVariableTypesCalculator.this.identifierLvarTypes.remove(original);
            if (lvarType != null)
              return (Node)setLocalVariableConversion((JoinPredecessor)original, identNode.setType(lvarType.type)); 
            assert LocalVariableTypesCalculator.this.localVariableConversions.get(original) == null;
          } else {
            assert LocalVariableTypesCalculator.this.identIsDeadAndHasNoLiveConversions(original);
          } 
          return (Node)identNode;
        }
        
        public Node leaveLiteralNode(LiteralNode<?> literalNode) {
          return (Node)literalNode.initialize(this.lc);
        }
        
        public Node leaveRuntimeNode(RuntimeNode runtimeNode) {
          RuntimeNode.Request request = runtimeNode.getRequest();
          boolean isEqStrict = (request == RuntimeNode.Request.EQ_STRICT);
          if (isEqStrict || request == RuntimeNode.Request.NE_STRICT)
            return (Node)LocalVariableTypesCalculator.createIsUndefined((Expression)runtimeNode, runtimeNode.getArgs().get(0), runtimeNode.getArgs().get(1), 
                isEqStrict ? RuntimeNode.Request.IS_UNDEFINED : RuntimeNode.Request.IS_NOT_UNDEFINED); 
          return (Node)runtimeNode;
        }
        
        private <T extends JoinPredecessor> T setLocalVariableConversion(JoinPredecessor original, T jp) {
          return (T)jp.setLocalVariableConversion(this.lc, LocalVariableTypesCalculator.this.localVariableConversions.get(original));
        }
      };
    newFunction = newFunction.setBody(this.lc, (Block)newFunction.getBody().accept((NodeVisitor)applyChangesVisitor));
    newFunction = newFunction.setReturnType(this.lc, this.returnType);
    newFunction = newFunction.setParameters(this.lc, newFunction.visitParameters((NodeVisitor)applyChangesVisitor));
    return (Node)newFunction;
  }
  
  private static Expression createIsUndefined(Expression parent, Expression lhs, Expression rhs, RuntimeNode.Request request) {
    if (isUndefinedIdent(lhs) || isUndefinedIdent(rhs))
      return (Expression)new RuntimeNode(parent, request, new Expression[] { lhs, rhs }); 
    return parent;
  }
  
  private static boolean isUndefinedIdent(Expression expr) {
    return (expr instanceof IdentNode && "undefined".equals(((IdentNode)expr).getName()));
  }
  
  private boolean identIsDeadAndHasNoLiveConversions(IdentNode identNode) {
    LocalVariableConversion conv = this.localVariableConversions.get(identNode);
    return (conv == null || !conv.isLive());
  }
  
  private void onAssignment(IdentNode identNode, LvarType type) {
    LvarType finalType;
    Symbol symbol = identNode.getSymbol();
    assert symbol != null : identNode.getName();
    if (!symbol.isBytecodeLocal())
      return; 
    assert type != null;
    if (type == LvarType.UNDEFINED && getLocalVariableType(symbol) != LvarType.UNDEFINED) {
      finalType = LvarType.OBJECT;
      symbol.setFlag(8192);
    } else {
      finalType = type;
    } 
    setType(symbol, finalType);
    setIdentifierLvarType(identNode, finalType);
    jumpToCatchBlock((JoinPredecessor)identNode);
  }
  
  private void onSelfAssignment(IdentNode identNode, LvarType type) {
    Symbol symbol = identNode.getSymbol();
    assert symbol != null : identNode.getName();
    if (!symbol.isBytecodeLocal())
      return; 
    assert type != null && type != LvarType.UNDEFINED && type != LvarType.BOOLEAN;
    setType(symbol, type);
    jumpToCatchBlock((JoinPredecessor)identNode);
  }
  
  private void resetJoinPoint(Label label) {
    this.jumpTargets.remove(label);
  }
  
  private void setCompilerConstantAsObject(FunctionNode functionNode, CompilerConstants cc) {
    Symbol symbol = getCompilerConstantSymbol(functionNode, cc);
    setType(symbol, LvarType.OBJECT);
    symbolIsUsed(symbol);
  }
  
  private static Symbol getCompilerConstantSymbol(FunctionNode functionNode, CompilerConstants cc) {
    return functionNode.getBody().getExistingSymbol(cc.symbolName());
  }
  
  private void setConversion(JoinPredecessor node, Map<Symbol, LvarType> branchLvarTypes, Map<Symbol, LvarType> joinLvarTypes) {
    if (node == null)
      return; 
    if (branchLvarTypes.isEmpty() || joinLvarTypes.isEmpty())
      this.localVariableConversions.remove(node); 
    LocalVariableConversion conversion = null;
    if (node instanceof IdentNode) {
      Symbol symbol = ((IdentNode)node).getSymbol();
      conversion = createConversion(symbol, branchLvarTypes.get(symbol), joinLvarTypes, null);
    } else {
      for (Map.Entry<Symbol, LvarType> entry : branchLvarTypes.entrySet()) {
        Symbol symbol = entry.getKey();
        LvarType branchLvarType = entry.getValue();
        conversion = createConversion(symbol, branchLvarType, joinLvarTypes, conversion);
      } 
    } 
    if (conversion != null) {
      this.localVariableConversions.put(node, conversion);
    } else {
      this.localVariableConversions.remove(node);
    } 
  }
  
  private void setIdentifierLvarType(IdentNode identNode, LvarType type) {
    assert type != null;
    this.identifierLvarTypes.put(identNode, type);
  }
  
  private void setType(Symbol symbol, LvarType type) {
    if (getLocalVariableTypeOrNull(symbol) == type)
      return; 
    assert symbol.hasSlot();
    assert !symbol.isGlobal();
    cloneOrNewLocalVariableTypes();
    this.localVariableTypes.put(symbol, type);
  }
  
  private void cloneOrNewLocalVariableTypes() {
    this.localVariableTypes = this.localVariableTypes.isEmpty() ? new HashMap<>() : cloneMap(this.localVariableTypes);
  }
  
  private void invalidateSymbol(Symbol symbol) {
    this.localVariableTypes.remove(symbol);
    this.invalidatedSymbols.add(symbol);
  }
  
  private void symbolIsUsed(Symbol symbol) {
    symbolIsUsed(symbol, getLocalVariableType(symbol));
  }
}
