package org.openjdk.nashorn.api.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.ExpressionStatement;
import org.openjdk.nashorn.internal.ir.ForNode;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.IfNode;
import org.openjdk.nashorn.internal.ir.IndexNode;
import org.openjdk.nashorn.internal.ir.LabelNode;
import org.openjdk.nashorn.internal.ir.LiteralNode;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.ObjectNode;
import org.openjdk.nashorn.internal.ir.PropertyNode;
import org.openjdk.nashorn.internal.ir.ReturnNode;
import org.openjdk.nashorn.internal.ir.RuntimeNode;
import org.openjdk.nashorn.internal.ir.SplitNode;
import org.openjdk.nashorn.internal.ir.Statement;
import org.openjdk.nashorn.internal.ir.SwitchNode;
import org.openjdk.nashorn.internal.ir.TemplateLiteral;
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

final class IRTranslator extends SimpleNodeVisitor {
  private StatementTreeImpl curStat;
  
  private ExpressionTreeImpl curExpr;
  
  CompilationUnitTree translate(FunctionNode node) {
    if (node == null)
      return null; 
    assert node.getKind() == FunctionNode.Kind.SCRIPT || node
      .getKind() == FunctionNode.Kind.MODULE : "script or module function expected";
    Block body = node.getBody();
    return new CompilationUnitTreeImpl(node, (List)
        translateStats((body != null) ? getOrderedStatements(body.getStatements()) : null), 
        translateModule(node));
  }
  
  public boolean enterAccessNode(AccessNode accessNode) {
    this.curExpr = new MemberSelectTreeImpl(accessNode, translateExpr(accessNode.getBase()));
    return false;
  }
  
  public boolean enterBlock(Block block) {
    return handleBlock(block, false);
  }
  
  public boolean enterBinaryNode(BinaryNode binaryNode) {
    if (binaryNode.isAssignment()) {
      ExpressionTree srcTree = translateExpr(binaryNode.getAssignmentSource());
      ExpressionTree destTree = translateExpr(binaryNode.getAssignmentDest());
      if (binaryNode.isTokenType(TokenType.ASSIGN)) {
        this.curExpr = new AssignmentTreeImpl(binaryNode, destTree, srcTree);
      } else {
        this.curExpr = new CompoundAssignmentTreeImpl(binaryNode, destTree, srcTree);
      } 
    } else {
      ExpressionTree leftTree = translateExpr(binaryNode.lhs());
      ExpressionTree rightTree = translateExpr(binaryNode.rhs());
      if (binaryNode.isTokenType(TokenType.INSTANCEOF)) {
        this.curExpr = new InstanceOfTreeImpl(binaryNode, leftTree, rightTree);
      } else {
        this.curExpr = new BinaryTreeImpl(binaryNode, leftTree, rightTree);
      } 
    } 
    return false;
  }
  
  public boolean enterBreakNode(BreakNode breakNode) {
    this.curStat = new BreakTreeImpl(breakNode);
    return false;
  }
  
  public boolean enterCallNode(CallNode callNode) {
    this.curExpr = null;
    callNode.getFunction().accept((NodeVisitor)this);
    ExpressionTree funcTree = this.curExpr;
    List<? extends ExpressionTree> argTrees = translateExprs(callNode.getArgs());
    this.curExpr = new FunctionCallTreeImpl(callNode, funcTree, argTrees);
    return false;
  }
  
  public boolean enterCaseNode(CaseNode caseNode) {
    assert false : "should not reach here!";
    return false;
  }
  
  public boolean enterCatchNode(CatchNode catchNode) {
    assert false : "should not reach here";
    return false;
  }
  
  public boolean enterContinueNode(ContinueNode continueNode) {
    this.curStat = new ContinueTreeImpl(continueNode);
    return false;
  }
  
  public boolean enterDebuggerNode(DebuggerNode debuggerNode) {
    this.curStat = new DebuggerTreeImpl(debuggerNode);
    return false;
  }
  
  public boolean enterEmptyNode(EmptyNode emptyNode) {
    this.curStat = new EmptyStatementTreeImpl(emptyNode);
    return false;
  }
  
  public boolean enterErrorNode(ErrorNode errorNode) {
    this.curExpr = new ErroneousTreeImpl(errorNode);
    return false;
  }
  
  public boolean enterExpressionStatement(ExpressionStatement expressionStatement) {
    if (expressionStatement.destructuringDeclarationType() != null) {
      ExpressionTree expr = translateExpr(expressionStatement.getExpression());
      assert expr instanceof AssignmentTree : "destructuring decl. statement does not have assignment";
      AssignmentTree assign = (AssignmentTree)expr;
      this.curStat = new DestructuringDeclTreeImpl(expressionStatement, assign.getVariable(), assign.getExpression());
    } else {
      this
        .curStat = new ExpressionStatementTreeImpl(expressionStatement, translateExpr(expressionStatement.getExpression()));
    } 
    return false;
  }
  
  public boolean enterBlockStatement(BlockStatement blockStatement) {
    Block block = blockStatement.getBlock();
    if (blockStatement.isSynthetic()) {
      assert block != null && block.getStatements() != null && block.getStatements().size() == 1;
      this.curStat = translateStat(block.getStatements().get(0));
    } else {
      this
        .curStat = new BlockTreeImpl(blockStatement, translateStats((block != null) ? block.getStatements() : null));
    } 
    return false;
  }
  
  public boolean enterForNode(ForNode forNode) {
    if (forNode.isForIn()) {
      this
        
        .curStat = new ForInLoopTreeImpl(forNode, translateExpr(forNode.getInit()), translateExpr((Expression)forNode.getModify()), translateBlock(forNode.getBody()));
    } else if (forNode.isForOf()) {
      this
        
        .curStat = new ForOfLoopTreeImpl(forNode, translateExpr(forNode.getInit()), translateExpr((Expression)forNode.getModify()), translateBlock(forNode.getBody()));
    } else {
      this
        
        .curStat = new ForLoopTreeImpl(forNode, translateExpr(forNode.getInit()), translateExpr((Expression)forNode.getTest()), translateExpr((Expression)forNode.getModify()), translateBlock(forNode.getBody()));
    } 
    return false;
  }
  
  public boolean enterFunctionNode(FunctionNode functionNode) {
    assert !functionNode.isDeclared() || functionNode.isAnonymous() : "should not reach here for function declaration";
    List<? extends ExpressionTree> paramTrees = translateParameters(functionNode);
    BlockTree blockTree = (BlockTree)translateBlock(functionNode.getBody(), true);
    this.curExpr = new FunctionExpressionTreeImpl(functionNode, paramTrees, blockTree);
    return false;
  }
  
  public boolean enterIdentNode(IdentNode identNode) {
    this.curExpr = new IdentifierTreeImpl(identNode);
    return false;
  }
  
  public boolean enterIfNode(IfNode ifNode) {
    this
      
      .curStat = new IfTreeImpl(ifNode, translateExpr(ifNode.getTest()), translateBlock(ifNode.getPass()), translateBlock(ifNode.getFail()));
    return false;
  }
  
  public boolean enterIndexNode(IndexNode indexNode) {
    this
      
      .curExpr = new ArrayAccessTreeImpl((Expression)indexNode, translateExpr(indexNode.getBase()), translateExpr(indexNode.getIndex()));
    return false;
  }
  
  public boolean enterLabelNode(LabelNode labelNode) {
    this
      .curStat = new LabeledStatementTreeImpl(labelNode, translateBlock(labelNode.getBody()));
    return false;
  }
  
  public boolean enterLiteralNode(LiteralNode<?> literalNode) {
    Object value = literalNode.getValue();
    if (value instanceof org.openjdk.nashorn.internal.parser.Lexer.RegexToken) {
      this.curExpr = new RegExpLiteralTreeImpl(literalNode);
    } else if (literalNode.isArray()) {
      List<Expression> exprNodes = literalNode.getElementExpressions();
      List<ExpressionTreeImpl> exprTrees = new ArrayList<>(exprNodes.size());
      for (Node node : exprNodes) {
        if (node == null) {
          exprTrees.add(null);
          continue;
        } 
        this.curExpr = null;
        node.accept((NodeVisitor)this);
        assert this.curExpr != null : "null for " + node;
        exprTrees.add(this.curExpr);
      } 
      this.curExpr = new ArrayLiteralTreeImpl(literalNode, (List)exprTrees);
    } else {
      this.curExpr = new LiteralTreeImpl(literalNode);
    } 
    return false;
  }
  
  public boolean enterObjectNode(ObjectNode objectNode) {
    List<PropertyNode> propNodes = objectNode.getElements();
    List<? extends PropertyTree> propTrees = translateProperties(propNodes);
    this.curExpr = new ObjectLiteralTreeImpl(objectNode, propTrees);
    return false;
  }
  
  public boolean enterPropertyNode(PropertyNode propertyNode) {
    assert false : "should not reach here!";
    return false;
  }
  
  public boolean enterReturnNode(ReturnNode returnNode) {
    this
      .curStat = new ReturnTreeImpl(returnNode, translateExpr(returnNode.getExpression()));
    return false;
  }
  
  public boolean enterRuntimeNode(RuntimeNode runtimeNode) {
    assert false : "should not reach here: RuntimeNode";
    return false;
  }
  
  public boolean enterSplitNode(SplitNode splitNode) {
    assert false : "should not reach here!";
    return false;
  }
  
  public boolean enterSwitchNode(SwitchNode switchNode) {
    List<CaseNode> caseNodes = switchNode.getCases();
    List<CaseTreeImpl> caseTrees = new ArrayList<>(caseNodes.size());
    for (CaseNode caseNode : caseNodes) {
      Block body = caseNode.getBody();
      caseTrees.add(new CaseTreeImpl(caseNode, 
            
            translateExpr(caseNode.getTest()), 
            translateStats((body != null) ? body.getStatements() : null)));
    } 
    this
      .curStat = new SwitchTreeImpl(switchNode, translateExpr(switchNode.getExpression()), (List)caseTrees);
    return false;
  }
  
  public boolean enterTemplateLiteral(TemplateLiteral templateLiteral) {
    this.curExpr = new TemplateLiteralTreeImpl((Expression)templateLiteral, translateExprs(templateLiteral.getExpressions()));
    return false;
  }
  
  public boolean enterTernaryNode(TernaryNode ternaryNode) {
    this
      
      .curExpr = new ConditionalExpressionTreeImpl(ternaryNode, translateExpr(ternaryNode.getTest()), translateExpr((Expression)ternaryNode.getTrueExpression()), translateExpr((Expression)ternaryNode.getFalseExpression()));
    return false;
  }
  
  public boolean enterThrowNode(ThrowNode throwNode) {
    this
      .curStat = new ThrowTreeImpl(throwNode, translateExpr(throwNode.getExpression()));
    return false;
  }
  
  public boolean enterTryNode(TryNode tryNode) {
    List<? extends CatchNode> catchNodes = tryNode.getCatches();
    List<CatchTreeImpl> catchTrees = new ArrayList<>(catchNodes.size());
    for (CatchNode catchNode : catchNodes)
      catchTrees.add(new CatchTreeImpl(catchNode, 
            translateExpr(catchNode.getException()), (BlockTree)
            translateBlock(catchNode.getBody()), 
            translateExpr(catchNode.getExceptionCondition()))); 
    this
      
      .curStat = new TryTreeImpl(tryNode, (BlockTree)translateBlock(tryNode.getBody()), (List)catchTrees, (BlockTree)translateBlock(tryNode.getFinallyBody()));
    return false;
  }
  
  public boolean enterUnaryNode(UnaryNode unaryNode) {
    if (unaryNode.isTokenType(TokenType.NEW)) {
      this
        .curExpr = new NewTreeImpl(unaryNode, translateExpr(unaryNode.getExpression()));
    } else if (unaryNode.isTokenType(TokenType.YIELD) || unaryNode
      .isTokenType(TokenType.YIELD_STAR)) {
      this
        .curExpr = new YieldTreeImpl((Expression)unaryNode, translateExpr(unaryNode.getExpression()));
    } else if (unaryNode.isTokenType(TokenType.SPREAD_ARGUMENT) || unaryNode
      .isTokenType(TokenType.SPREAD_ARRAY)) {
      this
        .curExpr = new SpreadTreeImpl((Expression)unaryNode, translateExpr(unaryNode.getExpression()));
    } else {
      this
        .curExpr = new UnaryTreeImpl(unaryNode, translateExpr(unaryNode.getExpression()));
    } 
    return false;
  }
  
  public boolean enterVarNode(VarNode varNode) {
    Expression initNode = varNode.getInit();
    if (initNode instanceof FunctionNode && ((FunctionNode)initNode).isDeclared()) {
      FunctionNode funcNode = (FunctionNode)initNode;
      List<? extends ExpressionTree> paramTrees = translateParameters(funcNode);
      BlockTree blockTree = (BlockTree)translateBlock(funcNode.getBody(), true);
      this.curStat = new FunctionDeclarationTreeImpl(varNode, paramTrees, blockTree);
    } else if (initNode instanceof ClassNode && ((ClassNode)initNode).isStatement()) {
      ClassNode classNode = (ClassNode)initNode;
      this
        
        .curStat = new ClassDeclarationTreeImpl(varNode, translateIdent(classNode.getIdent()), translateExpr(classNode.getClassHeritage()), translateProperty(classNode.getConstructor()), translateProperties(classNode.getClassElements()));
    } else {
      this.curStat = new VariableTreeImpl(varNode, translateIdent(varNode.getName()), translateExpr(initNode));
    } 
    return false;
  }
  
  public boolean enterWhileNode(WhileNode whileNode) {
    ExpressionTree condTree = translateExpr((Expression)whileNode.getTest());
    StatementTree statTree = translateBlock(whileNode.getBody());
    if (whileNode.isDoWhile()) {
      this.curStat = new DoWhileLoopTreeImpl(whileNode, condTree, statTree);
    } else {
      this.curStat = new WhileLoopTreeImpl(whileNode, condTree, statTree);
    } 
    return false;
  }
  
  public boolean enterWithNode(WithNode withNode) {
    this
      
      .curStat = new WithTreeImpl(withNode, translateExpr(withNode.getExpression()), translateBlock(withNode.getBody()));
    return false;
  }
  
  public boolean enterClassNode(ClassNode classNode) {
    assert !classNode.isStatement() : "should not reach here for class declaration";
    IdentNode className = classNode.getIdent();
    this
      
      .curExpr = new ClassExpressionTreeImpl(classNode, (className != null) ? translateIdent(className) : null, translateExpr(classNode.getClassHeritage()), translateProperty(classNode.getConstructor()), translateProperties(classNode.getClassElements()));
    return false;
  }
  
  private StatementTree translateBlock(Block blockNode) {
    return translateBlock(blockNode, false);
  }
  
  private StatementTree translateBlock(Block blockNode, boolean sortStats) {
    if (blockNode == null)
      return null; 
    this.curStat = null;
    handleBlock(blockNode, sortStats);
    return this.curStat;
  }
  
  private boolean handleBlock(Block block, boolean sortStats) {
    if (block.isSynthetic()) {
      EmptyNode emptyNode;
      int statCount = block.getStatementCount();
      switch (statCount) {
        case 0:
          emptyNode = new EmptyNode(-1, block.getToken(), block.getFinish());
          this.curStat = new EmptyStatementTreeImpl(emptyNode);
          return false;
        case 1:
          this.curStat = translateStat(block.getStatements().get(0));
          return false;
      } 
    } 
    List<? extends Statement> stats = block.getStatements();
    this
      .curStat = new BlockTreeImpl(block, translateStats(sortStats ? getOrderedStatements(stats) : stats));
    return false;
  }
  
  private List<? extends Statement> getOrderedStatements(List<? extends Statement> stats) {
    List<? extends Statement> statList = new ArrayList<>(stats);
    statList.sort(Comparator.comparingInt(Node::getSourceOrder));
    return statList;
  }
  
  private List<? extends StatementTree> translateStats(List<? extends Statement> stats) {
    if (stats == null)
      return null; 
    List<StatementTreeImpl> statTrees = new ArrayList<>(stats.size());
    for (Statement stat : stats) {
      this.curStat = null;
      stat.accept((NodeVisitor)this);
      assert this.curStat != null;
      statTrees.add(this.curStat);
    } 
    return (List)statTrees;
  }
  
  private List<? extends ExpressionTree> translateParameters(FunctionNode func) {
    Map<IdentNode, Expression> paramExprs = func.getParameterExpressions();
    if (paramExprs != null) {
      List<IdentNode> params = func.getParameters();
      List<ExpressionTreeImpl> exprTrees = new ArrayList<>(params.size());
      for (IdentNode ident : params) {
        Expression expr = paramExprs.containsKey(ident) ? paramExprs.get(ident) : (Expression)ident;
        this.curExpr = null;
        expr.accept((NodeVisitor)this);
        assert this.curExpr != null;
        exprTrees.add(this.curExpr);
      } 
      return (List)exprTrees;
    } 
    return translateExprs(func.getParameters());
  }
  
  private List<? extends ExpressionTree> translateExprs(List<? extends Expression> exprs) {
    if (exprs == null)
      return null; 
    List<ExpressionTreeImpl> exprTrees = new ArrayList<>(exprs.size());
    for (Expression expr : exprs) {
      this.curExpr = null;
      expr.accept((NodeVisitor)this);
      assert this.curExpr != null;
      exprTrees.add(this.curExpr);
    } 
    return (List)exprTrees;
  }
  
  private ExpressionTreeImpl translateExpr(Expression expr) {
    if (expr == null)
      return null; 
    this.curExpr = null;
    expr.accept((NodeVisitor)this);
    assert this.curExpr != null : "null for " + expr;
    return this.curExpr;
  }
  
  private StatementTreeImpl translateStat(Statement stat) {
    if (stat == null)
      return null; 
    this.curStat = null;
    stat.accept((NodeVisitor)this);
    assert this.curStat != null : "null for " + stat;
    return this.curStat;
  }
  
  private static IdentifierTree translateIdent(IdentNode ident) {
    return new IdentifierTreeImpl(ident);
  }
  
  private List<? extends PropertyTree> translateProperties(List<PropertyNode> propNodes) {
    List<PropertyTree> propTrees = new ArrayList<>(propNodes.size());
    for (PropertyNode propNode : propNodes)
      propTrees.add(translateProperty(propNode)); 
    return propTrees;
  }
  
  private PropertyTree translateProperty(PropertyNode propNode) {
    return new PropertyTreeImpl(propNode, 
        translateExpr(propNode.getKey()), 
        translateExpr(propNode.getValue()), (FunctionExpressionTree)
        translateExpr((Expression)propNode.getGetter()), (FunctionExpressionTree)
        translateExpr((Expression)propNode.getSetter()));
  }
  
  private ModuleTree translateModule(FunctionNode func) {
    return (func.getKind() == FunctionNode.Kind.MODULE) ? 
      ModuleTreeImpl.create(func) : null;
  }
}
