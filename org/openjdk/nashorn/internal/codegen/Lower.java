package org.openjdk.nashorn.internal.codegen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;
import org.openjdk.nashorn.internal.ir.AccessNode;
import org.openjdk.nashorn.internal.ir.BaseNode;
import org.openjdk.nashorn.internal.ir.BinaryNode;
import org.openjdk.nashorn.internal.ir.Block;
import org.openjdk.nashorn.internal.ir.BlockLexicalContext;
import org.openjdk.nashorn.internal.ir.BlockStatement;
import org.openjdk.nashorn.internal.ir.BreakNode;
import org.openjdk.nashorn.internal.ir.CallNode;
import org.openjdk.nashorn.internal.ir.CaseNode;
import org.openjdk.nashorn.internal.ir.CatchNode;
import org.openjdk.nashorn.internal.ir.ClassNode;
import org.openjdk.nashorn.internal.ir.ContinueNode;
import org.openjdk.nashorn.internal.ir.DebuggerNode;
import org.openjdk.nashorn.internal.ir.EmptyNode;
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.ExpressionStatement;
import org.openjdk.nashorn.internal.ir.ForNode;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.IfNode;
import org.openjdk.nashorn.internal.ir.IndexNode;
import org.openjdk.nashorn.internal.ir.JoinPredecessorExpression;
import org.openjdk.nashorn.internal.ir.JumpStatement;
import org.openjdk.nashorn.internal.ir.JumpToInlinedFinally;
import org.openjdk.nashorn.internal.ir.LabelNode;
import org.openjdk.nashorn.internal.ir.LexicalContext;
import org.openjdk.nashorn.internal.ir.LexicalContextNode;
import org.openjdk.nashorn.internal.ir.LiteralNode;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.ReturnNode;
import org.openjdk.nashorn.internal.ir.RuntimeNode;
import org.openjdk.nashorn.internal.ir.Statement;
import org.openjdk.nashorn.internal.ir.SwitchNode;
import org.openjdk.nashorn.internal.ir.Symbol;
import org.openjdk.nashorn.internal.ir.ThrowNode;
import org.openjdk.nashorn.internal.ir.TryNode;
import org.openjdk.nashorn.internal.ir.UnaryNode;
import org.openjdk.nashorn.internal.ir.VarNode;
import org.openjdk.nashorn.internal.ir.WhileNode;
import org.openjdk.nashorn.internal.ir.WithNode;
import org.openjdk.nashorn.internal.ir.visitor.NodeOperatorVisitor;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.ir.visitor.SimpleNodeVisitor;
import org.openjdk.nashorn.internal.parser.Token;
import org.openjdk.nashorn.internal.parser.TokenType;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.ErrorManager;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.Source;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;
import org.openjdk.nashorn.internal.runtime.logging.Loggable;
import org.openjdk.nashorn.internal.runtime.logging.Logger;

@Logger(name = "lower")
final class Lower extends NodeOperatorVisitor<BlockLexicalContext> implements Loggable {
  private final DebugLogger log;
  
  private final boolean es6;
  
  private final Source source;
  
  private static final Pattern SAFE_PROPERTY_NAME = Pattern.compile("[a-zA-Z_$][\\w$]*");
  
  Lower(Compiler compiler) {
    super((LexicalContext)new BlockLexicalContext() {
          public List<Statement> popStatements() {
            List<Statement> newStatements = new ArrayList<>();
            boolean terminated = false;
            List<Statement> statements = super.popStatements();
            for (Statement statement : statements) {
              if (!terminated) {
                newStatements.add(statement);
                if (statement.isTerminal() || statement instanceof JumpStatement)
                  terminated = true; 
                continue;
              } 
              FoldConstants.extractVarNodesFromDeadCode((Node)statement, newStatements);
            } 
            return newStatements;
          }
          
          protected Block afterSetStatements(Block block) {
            List<Statement> stmts = block.getStatements();
            for (ListIterator<Statement> li = stmts.listIterator(stmts.size()); li.hasPrevious(); ) {
              Statement stmt = li.previous();
              if (!(stmt instanceof VarNode) || ((VarNode)stmt).getInit() != null)
                return block.setIsTerminal((LexicalContext)this, stmt.isTerminal()); 
            } 
            return block.setIsTerminal((LexicalContext)this, false);
          }
        });
    this.log = initLogger(compiler.getContext());
    this.es6 = (compiler.getScriptEnvironment())._es6;
    this.source = compiler.getSource();
  }
  
  public DebugLogger getLogger() {
    return this.log;
  }
  
  public DebugLogger initLogger(Context context) {
    return context.getLogger(getClass());
  }
  
  public boolean enterBreakNode(BreakNode breakNode) {
    addStatement((Statement)breakNode);
    return false;
  }
  
  public Node leaveCallNode(CallNode callNode) {
    return (Node)checkEval(callNode.setFunction(markerFunction(callNode.getFunction())));
  }
  
  public boolean enterCatchNode(CatchNode catchNode) {
    Expression exception = catchNode.getException();
    if (exception != null && !(exception instanceof IdentNode))
      throwNotImplementedYet("es6.destructuring", (Node)exception); 
    return true;
  }
  
  public Node leaveCatchNode(CatchNode catchNode) {
    return addStatement((Statement)catchNode);
  }
  
  public boolean enterContinueNode(ContinueNode continueNode) {
    addStatement((Statement)continueNode);
    return false;
  }
  
  public boolean enterDebuggerNode(DebuggerNode debuggerNode) {
    int line = debuggerNode.getLineNumber();
    long token = debuggerNode.getToken();
    int finish = debuggerNode.getFinish();
    addStatement((Statement)new ExpressionStatement(line, token, finish, (Expression)new RuntimeNode(token, finish, RuntimeNode.Request.DEBUGGER, new ArrayList())));
    return false;
  }
  
  public boolean enterJumpToInlinedFinally(JumpToInlinedFinally jumpToInlinedFinally) {
    addStatement((Statement)jumpToInlinedFinally);
    return false;
  }
  
  public boolean enterEmptyNode(EmptyNode emptyNode) {
    return false;
  }
  
  public Node leaveIndexNode(IndexNode indexNode) {
    String name = getConstantPropertyName(indexNode.getIndex());
    if (name != null) {
      assert indexNode.isIndex();
      return (Node)new AccessNode(indexNode.getToken(), indexNode.getFinish(), indexNode.getBase(), name);
    } 
    return super.leaveIndexNode(indexNode);
  }
  
  public Node leaveDELETE(UnaryNode delete) {
    Expression expression = delete.getExpression();
    if (expression instanceof IdentNode || expression instanceof BaseNode)
      return (Node)delete; 
    return (Node)new BinaryNode(Token.recast(delete.getToken(), TokenType.COMMARIGHT), expression, 
        (Expression)LiteralNode.newInstance(delete.getToken(), delete.getFinish(), true));
  }
  
  private static String getConstantPropertyName(Expression expression) {
    if (expression instanceof LiteralNode.PrimitiveLiteralNode) {
      Object value = ((LiteralNode)expression).getValue();
      if (value instanceof String && SAFE_PROPERTY_NAME.matcher((String)value).matches())
        return (String)value; 
    } 
    return null;
  }
  
  public Node leaveExpressionStatement(ExpressionStatement expressionStatement) {
    Expression expr = expressionStatement.getExpression();
    ExpressionStatement node = expressionStatement;
    FunctionNode currentFunction = ((BlockLexicalContext)this.lc).getCurrentFunction();
    if (currentFunction.isProgram() && 
      !isInternalExpression(expr) && !isEvalResultAssignment((Node)expr))
      node = expressionStatement.setExpression((Expression)new BinaryNode(
            
            Token.recast(expressionStatement
              .getToken(), TokenType.ASSIGN), (Expression)
            
            compilerConstant(CompilerConstants.RETURN), expr)); 
    if (this.es6 && expressionStatement.destructuringDeclarationType() != null)
      throwNotImplementedYet("es6.destructuring", (Node)expressionStatement); 
    return addStatement((Statement)node);
  }
  
  public Node leaveBlockStatement(BlockStatement blockStatement) {
    return addStatement((Statement)blockStatement);
  }
  
  public boolean enterForNode(ForNode forNode) {
    if (this.es6 && (forNode.getInit() instanceof org.openjdk.nashorn.internal.ir.ObjectNode || forNode.getInit() instanceof LiteralNode.ArrayLiteralNode))
      throwNotImplementedYet("es6.destructuring", (Node)forNode); 
    return super.enterForNode(forNode);
  }
  
  public Node leaveForNode(ForNode forNode) {
    ForNode newForNode = forNode;
    JoinPredecessorExpression joinPredecessorExpression = forNode.getTest();
    if (!forNode.isForInOrOf() && Expression.isAlwaysTrue((Expression)joinPredecessorExpression))
      newForNode = forNode.setTest(this.lc, null); 
    newForNode = checkEscape(newForNode);
    if (!this.es6 && newForNode.isForInOrOf()) {
      addStatementEnclosedInBlock((Statement)newForNode);
    } else {
      addStatement((Statement)newForNode);
    } 
    return (Node)newForNode;
  }
  
  public boolean enterFunctionNode(FunctionNode functionNode) {
    if (this.es6) {
      if (functionNode.getKind() == FunctionNode.Kind.MODULE)
        throwNotImplementedYet("es6.module", (Node)functionNode); 
      if (functionNode.getKind() == FunctionNode.Kind.GENERATOR)
        throwNotImplementedYet("es6.generator", (Node)functionNode); 
      if (functionNode.usesSuper())
        throwNotImplementedYet("es6.super", (Node)functionNode); 
      int numParams = functionNode.getNumOfParams();
      if (numParams > 0) {
        IdentNode lastParam = functionNode.getParameter(numParams - 1);
        if (lastParam.isRestParameter())
          throwNotImplementedYet("es6.rest.param", (Node)lastParam); 
      } 
      for (IdentNode param : functionNode.getParameters()) {
        if (param.isDestructuredParameter())
          throwNotImplementedYet("es6.destructuring", (Node)functionNode); 
      } 
    } 
    return super.enterFunctionNode(functionNode);
  }
  
  public Node leaveFunctionNode(FunctionNode functionNode) {
    this.log.info(new Object[] { "END FunctionNode: ", functionNode.getName() });
    return (Node)functionNode;
  }
  
  public Node leaveIfNode(IfNode ifNode) {
    return addStatement((Statement)ifNode);
  }
  
  public Node leaveIN(BinaryNode binaryNode) {
    return (Node)new RuntimeNode(binaryNode);
  }
  
  public Node leaveINSTANCEOF(BinaryNode binaryNode) {
    return (Node)new RuntimeNode(binaryNode);
  }
  
  public Node leaveLabelNode(LabelNode labelNode) {
    return addStatement((Statement)labelNode);
  }
  
  public Node leaveReturnNode(ReturnNode returnNode) {
    addStatement((Statement)returnNode);
    return (Node)returnNode;
  }
  
  public Node leaveCaseNode(CaseNode caseNode) {
    Expression expression = caseNode.getTest();
    if (expression instanceof LiteralNode) {
      LiteralNode<?> lit = (LiteralNode)expression;
      if (lit.isNumeric() && !(lit.getValue() instanceof Integer) && 
        JSType.isRepresentableAsInt(lit.getNumber()))
        return (Node)caseNode.setTest((Expression)LiteralNode.newInstance((Node)lit, Integer.valueOf(lit.getInt32())).accept((NodeVisitor)this)); 
    } 
    return (Node)caseNode;
  }
  
  public Node leaveSwitchNode(SwitchNode switchNode) {
    if (!switchNode.isUniqueInteger()) {
      addStatementEnclosedInBlock((Statement)switchNode);
    } else {
      addStatement((Statement)switchNode);
    } 
    return (Node)switchNode;
  }
  
  public Node leaveThrowNode(ThrowNode throwNode) {
    return addStatement((Statement)throwNode);
  }
  
  private static <T extends Node> T ensureUniqueNamesIn(T node) {
    return (T)node.accept((NodeVisitor)new SimpleNodeVisitor() {
          public Node leaveFunctionNode(FunctionNode functionNode) {
            String name = functionNode.getName();
            return (Node)functionNode.setName(this.lc, this.lc.getCurrentFunction().uniqueName(name));
          }
          
          public Node leaveDefault(Node labelledNode) {
            return labelledNode.ensureUniqueLabels(this.lc);
          }
        });
  }
  
  private static Block createFinallyBlock(Block finallyBody) {
    List<Statement> newStatements = new ArrayList<>();
    for (Statement statement : finallyBody.getStatements()) {
      newStatements.add(statement);
      if (statement.hasTerminalFlags())
        break; 
    } 
    return finallyBody.setStatements(null, newStatements);
  }
  
  private Block catchAllBlock(TryNode tryNode) {
    int lineNumber = tryNode.getLineNumber();
    long token = tryNode.getToken();
    int finish = tryNode.getFinish();
    IdentNode exception = new IdentNode(token, finish, ((BlockLexicalContext)this.lc).getCurrentFunction().uniqueName(CompilerConstants.EXCEPTION_PREFIX.symbolName()));
    Block catchBody = new Block(token, finish, new Statement[] { (Statement)new ThrowNode(lineNumber, token, finish, (Expression)new IdentNode(exception), true) });
    assert catchBody.isTerminal();
    CatchNode catchAllNode = new CatchNode(lineNumber, token, finish, (Expression)new IdentNode(exception), null, catchBody, true);
    Block catchAllBlock = new Block(token, finish, new Statement[] { (Statement)catchAllNode });
    return (Block)catchAllBlock.accept((NodeVisitor)this);
  }
  
  private IdentNode compilerConstant(CompilerConstants cc) {
    FunctionNode functionNode = ((BlockLexicalContext)this.lc).getCurrentFunction();
    return new IdentNode(functionNode.getToken(), functionNode.getFinish(), cc.symbolName());
  }
  
  private static boolean isTerminalFinally(Block finallyBlock) {
    return finallyBlock.getLastStatement().hasTerminalFlags();
  }
  
  private TryNode spliceFinally(TryNode tryNode, final ThrowNode rethrow, Block finallyBody) {
    assert tryNode.getFinallyBody() == null;
    final Block finallyBlock = createFinallyBlock(finallyBody);
    final ArrayList<Block> inlinedFinallies = new ArrayList<>();
    final FunctionNode fn = ((BlockLexicalContext)this.lc).getCurrentFunction();
    TryNode newTryNode = (TryNode)tryNode.accept((NodeVisitor)new SimpleNodeVisitor() {
          public boolean enterFunctionNode(FunctionNode functionNode) {
            return false;
          }
          
          public Node leaveThrowNode(ThrowNode throwNode) {
            if (rethrow == throwNode)
              return (Node)new BlockStatement(Lower.prependFinally(finallyBlock, (Statement)throwNode)); 
            return (Node)throwNode;
          }
          
          public Node leaveBreakNode(BreakNode breakNode) {
            return leaveJumpStatement((JumpStatement)breakNode);
          }
          
          public Node leaveContinueNode(ContinueNode continueNode) {
            return leaveJumpStatement((JumpStatement)continueNode);
          }
          
          private Node leaveJumpStatement(JumpStatement jump) {
            if (jump.getTarget(this.lc) == null)
              return (Node)Lower.createJumpToInlinedFinally(fn, inlinedFinallies, Lower.prependFinally(finallyBlock, (Statement)jump)); 
            return (Node)jump;
          }
          
          public Node leaveReturnNode(ReturnNode returnNode) {
            Expression expr = returnNode.getExpression();
            if (Lower.isTerminalFinally(finallyBlock)) {
              if (expr == null)
                return (Node)Lower.createJumpToInlinedFinally(fn, inlinedFinallies, Lower.<Block>ensureUniqueNamesIn(finallyBlock)); 
              List<Statement> list = new ArrayList<>(2);
              int i = returnNode.getLineNumber();
              long l = returnNode.getToken();
              list.add(new ExpressionStatement(i, l, returnNode.getFinish(), expr));
              list.add(Lower.createJumpToInlinedFinally(fn, inlinedFinallies, Lower.<Block>ensureUniqueNamesIn(finallyBlock)));
              return (Node)new BlockStatement(i, new Block(l, finallyBlock.getFinish(), list));
            } 
            if (expr == null || expr instanceof LiteralNode.PrimitiveLiteralNode || (expr instanceof IdentNode && CompilerConstants.RETURN.symbolName().equals(((IdentNode)expr).getName())))
              return (Node)Lower.createJumpToInlinedFinally(fn, inlinedFinallies, Lower.prependFinally(finallyBlock, (Statement)returnNode)); 
            List<Statement> newStatements = new ArrayList<>();
            int retLineNumber = returnNode.getLineNumber();
            long retToken = returnNode.getToken();
            int retFinish = returnNode.getFinish();
            IdentNode identNode = new IdentNode(expr.getToken(), expr.getFinish(), CompilerConstants.RETURN.symbolName());
            newStatements.add(new ExpressionStatement(retLineNumber, retToken, retFinish, (Expression)new BinaryNode(Token.recast(returnNode.getToken(), TokenType.ASSIGN), (Expression)identNode, expr)));
            newStatements.add(Lower.createJumpToInlinedFinally(fn, inlinedFinallies, Lower.prependFinally(finallyBlock, (Statement)returnNode.setExpression((Expression)identNode))));
            return (Node)new BlockStatement(retLineNumber, new Block(retToken, retFinish, newStatements));
          }
        });
    addStatement(inlinedFinallies.isEmpty() ? (Statement)newTryNode : (Statement)newTryNode.setInlinedFinallies(this.lc, inlinedFinallies));
    addStatement((Statement)new BlockStatement(finallyBlock));
    return newTryNode;
  }
  
  private static JumpToInlinedFinally createJumpToInlinedFinally(FunctionNode fn, List<Block> inlinedFinallies, Block finallyBlock) {
    String labelName = fn.uniqueName(":finally");
    long token = finallyBlock.getToken();
    int finish = finallyBlock.getFinish();
    inlinedFinallies.add(new Block(token, finish, new Statement[] { (Statement)new LabelNode(finallyBlock.getFirstStatementLineNumber(), token, finish, labelName, finallyBlock) }));
    return new JumpToInlinedFinally(labelName);
  }
  
  private static Block prependFinally(Block finallyBlock, Statement statement) {
    Block inlinedFinally = ensureUniqueNamesIn(finallyBlock);
    if (isTerminalFinally(finallyBlock))
      return inlinedFinally; 
    List<Statement> stmts = inlinedFinally.getStatements();
    List<Statement> newStmts = new ArrayList<>(stmts.size() + 1);
    newStmts.addAll(stmts);
    newStmts.add(statement);
    return new Block(inlinedFinally.getToken(), statement.getFinish(), newStmts);
  }
  
  public Node leaveTryNode(TryNode tryNode) {
    Block finallyBody = tryNode.getFinallyBody();
    TryNode newTryNode = tryNode.setFinallyBody(this.lc, null);
    if (finallyBody == null || finallyBody.getStatementCount() == 0) {
      List<CatchNode> catches = newTryNode.getCatches();
      if (catches == null || catches.isEmpty())
        return addStatement((Statement)new BlockStatement(tryNode.getBody())); 
      return addStatement((Statement)ensureUnconditionalCatch(newTryNode));
    } 
    Block catchAll = catchAllBlock(tryNode);
    final List<ThrowNode> rethrows = new ArrayList<>(1);
    catchAll.accept((NodeVisitor)new SimpleNodeVisitor() {
          public boolean enterThrowNode(ThrowNode throwNode) {
            rethrows.add(throwNode);
            return true;
          }
        });
    assert rethrows.size() == 1;
    if (!tryNode.getCatchBlocks().isEmpty()) {
      Block outerBody = new Block(newTryNode.getToken(), newTryNode.getFinish(), new Statement[] { (Statement)ensureUnconditionalCatch(newTryNode) });
      newTryNode = newTryNode.setBody(this.lc, outerBody).setCatchBlocks(this.lc, null);
    } 
    newTryNode = newTryNode.setCatchBlocks(this.lc, Arrays.asList(new Block[] { catchAll }));
    return (Node)((BlockLexicalContext)this.lc).replace((LexicalContextNode)tryNode, (LexicalContextNode)spliceFinally(newTryNode, rethrows.get(0), finallyBody));
  }
  
  private TryNode ensureUnconditionalCatch(TryNode tryNode) {
    List<CatchNode> catches = tryNode.getCatches();
    if (catches == null || catches.isEmpty() || ((CatchNode)catches.get(catches.size() - 1)).getExceptionCondition() == null)
      return tryNode; 
    List<Block> newCatchBlocks = new ArrayList<>(tryNode.getCatchBlocks());
    newCatchBlocks.add(catchAllBlock(tryNode));
    return tryNode.setCatchBlocks(this.lc, newCatchBlocks);
  }
  
  public boolean enterUnaryNode(UnaryNode unaryNode) {
    if (this.es6)
      if (unaryNode.isTokenType(TokenType.YIELD) || unaryNode
        .isTokenType(TokenType.YIELD_STAR)) {
        throwNotImplementedYet("es6.yield", (Node)unaryNode);
      } else if (unaryNode.isTokenType(TokenType.SPREAD_ARGUMENT) || unaryNode
        .isTokenType(TokenType.SPREAD_ARRAY)) {
        throwNotImplementedYet("es6.spread", (Node)unaryNode);
      }  
    return super.enterUnaryNode(unaryNode);
  }
  
  public boolean enterASSIGN(BinaryNode binaryNode) {
    if (this.es6 && (binaryNode.lhs() instanceof org.openjdk.nashorn.internal.ir.ObjectNode || binaryNode.lhs() instanceof LiteralNode.ArrayLiteralNode))
      throwNotImplementedYet("es6.destructuring", (Node)binaryNode); 
    return super.enterASSIGN(binaryNode);
  }
  
  public Node leaveVarNode(VarNode varNode) {
    addStatement((Statement)varNode);
    if (varNode.getFlag(4) && ((BlockLexicalContext)this.lc)
      .getCurrentFunction().isProgram() && ((FunctionNode)varNode
      .getInit()).isAnonymous())
      (new ExpressionStatement(varNode.getLineNumber(), varNode.getToken(), varNode.getFinish(), (Expression)new IdentNode(varNode.getName()))).accept((NodeVisitor)this); 
    return (Node)varNode;
  }
  
  public Node leaveWhileNode(WhileNode whileNode) {
    JoinPredecessorExpression joinPredecessorExpression = whileNode.getTest();
    Block body = whileNode.getBody();
    if (Expression.isAlwaysTrue((Expression)joinPredecessorExpression)) {
      ForNode forNode = (ForNode)(new ForNode(whileNode.getLineNumber(), whileNode.getToken(), whileNode.getFinish(), body, 0)).accept((NodeVisitor)this);
      ((BlockLexicalContext)this.lc).replace((LexicalContextNode)whileNode, (LexicalContextNode)forNode);
      return (Node)forNode;
    } 
    return addStatement((Statement)checkEscape(whileNode));
  }
  
  public Node leaveWithNode(WithNode withNode) {
    return addStatement((Statement)withNode);
  }
  
  public boolean enterClassNode(ClassNode classNode) {
    throwNotImplementedYet("es6.class", (Node)classNode);
    return super.enterClassNode(classNode);
  }
  
  private static Expression markerFunction(Expression function) {
    if (function instanceof IdentNode)
      return (Expression)((IdentNode)function).setIsFunction(); 
    if (function instanceof BaseNode)
      return (Expression)((BaseNode)function).setIsFunction(); 
    return function;
  }
  
  private String evalLocation(IdentNode node) {
    Source source = ((BlockLexicalContext)this.lc).getCurrentFunction().getSource();
    int pos = node.position();
    return source
      .getName() + '#' + 
      source
      .getLine(pos) + ':' + 
      source
      .getColumn(pos) + "<eval>";
  }
  
  private CallNode checkEval(CallNode callNode) {
    if (callNode.getFunction() instanceof IdentNode) {
      List<Expression> args = callNode.getArgs();
      IdentNode callee = (IdentNode)callNode.getFunction();
      if (args.size() >= 1 && CompilerConstants.EVAL.symbolName().equals(callee.getName())) {
        List<Expression> evalArgs = new ArrayList<>(args.size());
        for (Expression arg : args)
          evalArgs.add((Expression)((Expression)ensureUniqueNamesIn(arg)).accept((NodeVisitor)this)); 
        return callNode.setEvalArgs(new CallNode.EvalArgs(evalArgs, evalLocation(callee)));
      } 
    } 
    return callNode;
  }
  
  private static boolean controlFlowEscapes(final LexicalContext lex, Block loopBody) {
    final List<Node> escapes = new ArrayList<>();
    loopBody.accept((NodeVisitor)new SimpleNodeVisitor() {
          public Node leaveBreakNode(BreakNode node) {
            escapes.add(node);
            return (Node)node;
          }
          
          public Node leaveContinueNode(ContinueNode node) {
            if (lex.contains((LexicalContextNode)node.getTarget(lex)))
              escapes.add(node); 
            return (Node)node;
          }
        });
    return !escapes.isEmpty();
  }
  
  private <T extends org.openjdk.nashorn.internal.ir.LoopNode> T checkEscape(T loopNode) {
    boolean escapes = controlFlowEscapes(this.lc, loopNode.getBody());
    if (escapes)
      return (T)loopNode
        .setBody(this.lc, loopNode.getBody().setIsTerminal(this.lc, false))
        .setControlFlowEscapes(this.lc, escapes); 
    return loopNode;
  }
  
  private Node addStatement(Statement statement) {
    ((BlockLexicalContext)this.lc).appendStatement(statement);
    return (Node)statement;
  }
  
  private void addStatementEnclosedInBlock(Statement stmt) {
    BlockStatement b = BlockStatement.createReplacement(stmt, Collections.singletonList(stmt));
    if (stmt.isTerminal())
      b = b.setBlock(b.getBlock().setIsTerminal(null, true)); 
    addStatement((Statement)b);
  }
  
  private static boolean isInternalExpression(Expression expression) {
    if (!(expression instanceof IdentNode))
      return false; 
    Symbol symbol = ((IdentNode)expression).getSymbol();
    return (symbol != null && symbol.isInternal());
  }
  
  private static boolean isEvalResultAssignment(Node expression) {
    Node e = expression;
    if (e instanceof BinaryNode) {
      Expression expression1 = ((BinaryNode)e).lhs();
      if (expression1 instanceof IdentNode)
        return ((IdentNode)expression1).getName().equals(CompilerConstants.RETURN.symbolName()); 
    } 
    return false;
  }
  
  private void throwNotImplementedYet(String msgId, Node node) {
    long token = node.getToken();
    int line = this.source.getLine(node.getStart());
    int column = this.source.getColumn(node.getStart());
    String message = ECMAErrors.getMessage("unimplemented." + msgId, new String[0]);
    String formatted = ErrorManager.format(message, this.source, line, column, token);
    throw new RuntimeException(formatted);
  }
}
