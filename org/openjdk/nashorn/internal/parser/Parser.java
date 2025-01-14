package org.openjdk.nashorn.internal.parser;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.codegen.Namespace;
import org.openjdk.nashorn.internal.ir.AccessNode;
import org.openjdk.nashorn.internal.ir.BaseNode;
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
import org.openjdk.nashorn.internal.ir.ExpressionList;
import org.openjdk.nashorn.internal.ir.ExpressionStatement;
import org.openjdk.nashorn.internal.ir.ForNode;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.IfNode;
import org.openjdk.nashorn.internal.ir.IndexNode;
import org.openjdk.nashorn.internal.ir.JoinPredecessorExpression;
import org.openjdk.nashorn.internal.ir.LabelNode;
import org.openjdk.nashorn.internal.ir.LexicalContext;
import org.openjdk.nashorn.internal.ir.LiteralNode;
import org.openjdk.nashorn.internal.ir.Module;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.ObjectNode;
import org.openjdk.nashorn.internal.ir.PropertyKey;
import org.openjdk.nashorn.internal.ir.PropertyNode;
import org.openjdk.nashorn.internal.ir.ReturnNode;
import org.openjdk.nashorn.internal.ir.RuntimeNode;
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
import org.openjdk.nashorn.internal.ir.debug.ASTWriter;
import org.openjdk.nashorn.internal.ir.debug.PrintVisitor;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.ErrorManager;
import org.openjdk.nashorn.internal.runtime.JSErrorType;
import org.openjdk.nashorn.internal.runtime.ParserException;
import org.openjdk.nashorn.internal.runtime.RecompilableScriptFunctionData;
import org.openjdk.nashorn.internal.runtime.ScriptEnvironment;
import org.openjdk.nashorn.internal.runtime.Source;
import org.openjdk.nashorn.internal.runtime.Timing;
import org.openjdk.nashorn.internal.runtime.linker.NameCodec;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;
import org.openjdk.nashorn.internal.runtime.logging.Loggable;
import org.openjdk.nashorn.internal.runtime.logging.Logger;

@Logger(name = "parser")
public class Parser extends AbstractParser implements Loggable {
  private static final String ARGUMENTS_NAME = CompilerConstants.ARGUMENTS_VAR.symbolName();
  
  private static final String CONSTRUCTOR_NAME = "constructor";
  
  private static final String GET_NAME = "get";
  
  private static final String SET_NAME = "set";
  
  private final ScriptEnvironment env;
  
  private final boolean scripting;
  
  private List<Statement> functionDeclarations;
  
  private final ParserContext lc;
  
  private final Deque<Object> defaultNames;
  
  private final Namespace namespace;
  
  private final DebugLogger log;
  
  protected final Lexer.LineInfoReceiver lineInfoReceiver;
  
  private RecompilableScriptFunctionData reparsedFunction;
  
  public Parser(ScriptEnvironment env, Source source, ErrorManager errors) {
    this(env, source, errors, env._strict, (DebugLogger)null);
  }
  
  public Parser(ScriptEnvironment env, Source source, ErrorManager errors, boolean strict, DebugLogger log) {
    this(env, source, errors, strict, 0, log);
  }
  
  public Parser(ScriptEnvironment env, Source source, ErrorManager errors, boolean strict, int lineOffset, DebugLogger log) {
    super(source, errors, strict, lineOffset);
    this.lc = new ParserContext();
    this.defaultNames = new ArrayDeque();
    this.env = env;
    this.namespace = new Namespace(env.getNamespace());
    this.scripting = env._scripting;
    if (this.scripting) {
      this.lineInfoReceiver = ((receiverLine, receiverLinePosition) -> {
          this.line = receiverLine;
          this.linePosition = receiverLinePosition;
        });
    } else {
      this.lineInfoReceiver = null;
    } 
    this.log = (log == null) ? DebugLogger.DISABLED_LOGGER : log;
  }
  
  public DebugLogger getLogger() {
    return this.log;
  }
  
  public DebugLogger initLogger(Context context) {
    return context.getLogger(getClass());
  }
  
  public void setFunctionName(String name) {
    this.defaultNames.push(createIdentNode(0L, 0, name));
  }
  
  public void setReparsedFunction(RecompilableScriptFunctionData reparsedFunction) {
    this.reparsedFunction = reparsedFunction;
  }
  
  public FunctionNode parse() {
    return parse(CompilerConstants.PROGRAM.symbolName(), 0, this.source.getLength(), 0);
  }
  
  private void scanFirstToken() {
    this.k = -1;
    next();
  }
  
  public FunctionNode parse(String scriptName, int startPos, int len, int reparseFlags) {
    boolean isTimingEnabled = this.env.isTimingEnabled();
    long t0 = isTimingEnabled ? System.nanoTime() : 0L;
    this.log.info(new Object[] { this, " begin for '", scriptName, "'" });
    try {
      this.stream = new TokenStream();
      this.lexer = new Lexer(this.source, startPos, len, this.stream, (this.scripting && !this.env._no_syntax_extensions), isES6(), (this.reparsedFunction != null));
      this.lexer.line = this.lexer.pendingLine = this.lineOffset + 1;
      this.line = this.lineOffset;
      scanFirstToken();
      return program(scriptName, reparseFlags);
    } catch (Exception e) {
      handleParseException(e);
      return null;
    } finally {
      String end = "" + this + " end '" + this + "'";
      if (isTimingEnabled) {
        this.env._timing.accumulateTime(toString(), System.nanoTime() - t0);
        this.log.info(new Object[] { end, "' in ", Timing.toMillisPrint(System.nanoTime() - t0), " ms" });
      } else {
        this.log.info(end);
      } 
    } 
  }
  
  public FunctionNode parseModule(String moduleName, int startPos, int len) {
    try {
      this.stream = new TokenStream();
      this.lexer = new Lexer(this.source, startPos, len, this.stream, (this.scripting && !this.env._no_syntax_extensions), isES6(), (this.reparsedFunction != null));
      this.lexer.line = this.lexer.pendingLine = this.lineOffset + 1;
      this.line = this.lineOffset;
      scanFirstToken();
      return module(moduleName);
    } catch (Exception e) {
      handleParseException(e);
      return null;
    } 
  }
  
  public FunctionNode parseModule(String moduleName) {
    return parseModule(moduleName, 0, this.source.getLength());
  }
  
  public void parseFormalParameterList() {
    try {
      this.stream = new TokenStream();
      this.lexer = new Lexer(this.source, this.stream, (this.scripting && !this.env._no_syntax_extensions), isES6());
      scanFirstToken();
      formalParameterList(TokenType.EOF, false);
    } catch (Exception e) {
      handleParseException(e);
    } 
  }
  
  public void parseFunctionBody() {
    try {
      this.stream = new TokenStream();
      this.lexer = new Lexer(this.source, this.stream, (this.scripting && !this.env._no_syntax_extensions), isES6());
      int functionLine = this.line;
      scanFirstToken();
      long functionToken = Token.toDesc(TokenType.FUNCTION, 0, this.source.getLength());
      IdentNode ident = new IdentNode(functionToken, Token.descPosition(functionToken), CompilerConstants.PROGRAM.symbolName());
      ParserContextFunctionNode function = createParserContextFunctionNode(ident, functionToken, FunctionNode.Kind.NORMAL, functionLine, Collections.emptyList());
      this.lc.push(function);
      ParserContextBlockNode body = newBlock();
      this.functionDeclarations = new ArrayList<>();
      sourceElements(0);
      addFunctionDeclarations(function);
      this.functionDeclarations = null;
      restoreBlock(body);
      body.setFlag(1);
      Block functionBody = new Block(functionToken, this.source.getLength() - 1, body.getFlags() | 0x10, body.getStatements());
      this.lc.pop(function);
      expect(TokenType.EOF);
      FunctionNode functionNode = createFunctionNode(function, functionToken, ident, 
          
          Collections.emptyList(), FunctionNode.Kind.NORMAL, functionLine, functionBody);
      printAST(functionNode);
    } catch (Exception e) {
      handleParseException(e);
    } 
  }
  
  private void handleParseException(Exception e) {
    String message = e.getMessage();
    if (message == null)
      message = e.toString(); 
    if (e instanceof ParserException) {
      this.errors.error((ParserException)e);
    } else {
      this.errors.error(message);
    } 
    if (this.env._dump_on_error)
      e.printStackTrace(this.env.getErr()); 
  }
  
  private void recover(Exception e) {
    if (e != null) {
      String message = e.getMessage();
      if (message == null)
        message = e.toString(); 
      if (e instanceof ParserException) {
        this.errors.error((ParserException)e);
      } else {
        this.errors.error(message);
      } 
      if (this.env._dump_on_error)
        e.printStackTrace(this.env.getErr()); 
    } 
    while (true) {
      switch (this.type) {
        case EOF:
          break;
        case EOL:
        case SEMICOLON:
        case RBRACE:
          next();
          break;
      } 
      nextOrEOL();
    } 
  }
  
  private ParserContextBlockNode newBlock() {
    return this.lc.<ParserContextBlockNode>push(new ParserContextBlockNode(this.token));
  }
  
  private ParserContextFunctionNode createParserContextFunctionNode(IdentNode ident, long functionToken, FunctionNode.Kind kind, int functionLine, List<IdentNode> parameters) {
    StringBuilder sb = new StringBuilder();
    ParserContextFunctionNode parentFunction = this.lc.getCurrentFunction();
    if (parentFunction != null && !parentFunction.isProgram())
      sb.append(parentFunction.getName()).append(CompilerConstants.NESTED_FUNCTION_SEPARATOR.symbolName()); 
    assert ident.getName() != null;
    sb.append(ident.getName());
    String name = this.namespace.uniqueName(sb.toString());
    assert parentFunction != null || kind == FunctionNode.Kind.MODULE || name.equals(CompilerConstants.PROGRAM.symbolName()) : "name = " + name;
    int flags = 0;
    if (this.isStrictMode)
      flags |= 0x4; 
    if (parentFunction == null)
      flags |= 0x2000; 
    ParserContextFunctionNode functionNode = new ParserContextFunctionNode(functionToken, ident, name, this.namespace, functionLine, kind, parameters);
    functionNode.setFlag(flags);
    return functionNode;
  }
  
  private FunctionNode createFunctionNode(ParserContextFunctionNode function, long startToken, IdentNode ident, List<IdentNode> parameters, FunctionNode.Kind kind, int functionLine, Block body) {
    FunctionNode functionNode = new FunctionNode(this.source, functionLine, body.getToken(), Token.descPosition(body.getToken()), startToken, function.getLastToken(), this.namespace, ident, function.getName(), parameters, function.getParameterExpressions(), kind, function.getFlags(), body, function.getEndParserState(), function.getModule(), function.getDebugFlags());
    printAST(functionNode);
    return functionNode;
  }
  
  private void restoreBlock(ParserContextBlockNode block) {
    this.lc.pop(block);
  }
  
  private Block getBlock(boolean needsBraces) {
    long blockToken = this.token;
    ParserContextBlockNode newBlock = newBlock();
    try {
      if (needsBraces)
        expect(TokenType.LBRACE); 
      statementList();
    } finally {
      restoreBlock(newBlock);
    } 
    if (needsBraces)
      expect(TokenType.RBRACE); 
    int flags = newBlock.getFlags() | (needsBraces ? 0 : 16);
    return new Block(blockToken, this.finish, flags, newBlock.getStatements());
  }
  
  private Block getStatement() {
    return getStatement(false);
  }
  
  private Block getStatement(boolean labelledStatement) {
    if (this.type == TokenType.LBRACE)
      return getBlock(true); 
    ParserContextBlockNode newBlock = newBlock();
    try {
      statement(false, 0, true, labelledStatement);
    } finally {
      restoreBlock(newBlock);
    } 
    return new Block(newBlock.getToken(), this.finish, newBlock.getFlags() | 0x10, newBlock.getStatements());
  }
  
  private void detectSpecialFunction(IdentNode ident) {
    String name = ident.getName();
    if (CompilerConstants.EVAL.symbolName().equals(name)) {
      markEval(this.lc);
    } else if (TokenType.SUPER.getName().equals(name)) {
      assert ident.isDirectSuper();
      markSuperCall(this.lc);
    } 
  }
  
  private void detectSpecialProperty(IdentNode ident) {
    if (isArguments(ident))
      getCurrentNonArrowFunction().setFlag(8); 
  }
  
  private boolean useBlockScope() {
    return isES6();
  }
  
  private boolean isES6() {
    return this.env._es6;
  }
  
  private static boolean isArguments(String name) {
    return ARGUMENTS_NAME.equals(name);
  }
  
  static boolean isArguments(IdentNode ident) {
    return isArguments(ident.getName());
  }
  
  private static boolean checkIdentLValue(IdentNode ident) {
    return (ident.tokenType().getKind() != TokenKind.KEYWORD);
  }
  
  private Expression verifyAssignment(long op, Expression lhs, Expression rhs) {
    TokenType opType = Token.descType(op);
    switch (opType) {
      case ASSIGN:
      case ASSIGN_ADD:
      case ASSIGN_BIT_AND:
      case ASSIGN_BIT_OR:
      case ASSIGN_BIT_XOR:
      case ASSIGN_DIV:
      case ASSIGN_MOD:
      case ASSIGN_MUL:
      case ASSIGN_SAR:
      case ASSIGN_SHL:
      case ASSIGN_SHR:
      case ASSIGN_SUB:
        if (lhs instanceof IdentNode) {
          if (!checkIdentLValue((IdentNode)lhs))
            return (Expression)referenceError(lhs, rhs, false); 
          verifyIdent((IdentNode)lhs, "assignment");
          break;
        } 
        if (lhs instanceof AccessNode || lhs instanceof IndexNode)
          break; 
        if (opType == TokenType.ASSIGN && isDestructuringLhs(lhs)) {
          verifyDestructuringAssignmentPattern(lhs, "assignment");
          break;
        } 
        return (Expression)referenceError(lhs, rhs, this.env._early_lvalue_error);
    } 
    if (BinaryNode.isLogical(opType))
      return (Expression)new BinaryNode(op, (Expression)new JoinPredecessorExpression(lhs), (Expression)new JoinPredecessorExpression(rhs)); 
    return (Expression)new BinaryNode(op, lhs, rhs);
  }
  
  private boolean isDestructuringLhs(Expression lhs) {
    if (lhs instanceof ObjectNode || lhs instanceof LiteralNode.ArrayLiteralNode)
      return isES6(); 
    return false;
  }
  
  private void verifyDestructuringAssignmentPattern(Expression pattern, final String contextString) {
    assert pattern instanceof ObjectNode || pattern instanceof LiteralNode.ArrayLiteralNode;
    pattern.accept(new VerifyDestructuringPatternNodeVisitor(new LexicalContext()) {
          protected void verifySpreadElement(Expression lvalue) {
            if (!Parser.this.checkValidLValue(lvalue, contextString))
              throw Parser.this.error(AbstractParser.message("invalid.lvalue", new String[0]), lvalue.getToken()); 
          }
          
          public boolean enterIdentNode(IdentNode identNode) {
            Parser.this.verifyIdent(identNode, contextString);
            if (!Parser.checkIdentLValue(identNode)) {
              Parser.this.referenceError((Expression)identNode, (Expression)null, true);
              return false;
            } 
            return false;
          }
          
          public boolean enterAccessNode(AccessNode accessNode) {
            return false;
          }
          
          public boolean enterIndexNode(IndexNode indexNode) {
            return false;
          }
          
          protected boolean enterDefault(Node node) {
            throw Parser.this.error(String.format("unexpected node in AssignmentPattern: %s", new Object[] { node }));
          }
        });
  }
  
  private static UnaryNode incDecExpression(long firstToken, TokenType tokenType, Expression expression, boolean isPostfix) {
    if (isPostfix)
      return new UnaryNode(Token.recast(firstToken, (tokenType == TokenType.DECPREFIX) ? TokenType.DECPOSTFIX : TokenType.INCPOSTFIX), expression.getStart(), Token.descPosition(firstToken) + Token.descLength(firstToken), expression); 
    return new UnaryNode(firstToken, expression);
  }
  
  private FunctionNode program(String scriptName, int reparseFlags) {
    long functionToken = Token.toDesc(TokenType.FUNCTION, Token.descPosition(Token.withDelimiter(this.token)), this.source.getLength());
    int functionLine = this.line;
    IdentNode ident = new IdentNode(functionToken, Token.descPosition(functionToken), scriptName);
    ParserContextFunctionNode script = createParserContextFunctionNode(ident, functionToken, FunctionNode.Kind.SCRIPT, functionLine, 
        
        Collections.emptyList());
    this.lc.push(script);
    ParserContextBlockNode body = newBlock();
    this.functionDeclarations = new ArrayList<>();
    sourceElements(reparseFlags);
    addFunctionDeclarations(script);
    this.functionDeclarations = null;
    restoreBlock(body);
    body.setFlag(1);
    Block programBody = new Block(functionToken, this.finish, body.getFlags() | 0x10 | 0x20, body.getStatements());
    this.lc.pop(script);
    script.setLastToken(this.token);
    expect(TokenType.EOF);
    return createFunctionNode(script, functionToken, ident, Collections.emptyList(), FunctionNode.Kind.SCRIPT, functionLine, programBody);
  }
  
  private String getDirective(Node stmt) {
    if (stmt instanceof ExpressionStatement) {
      Expression expression = ((ExpressionStatement)stmt).getExpression();
      if (expression instanceof LiteralNode) {
        LiteralNode<?> lit = (LiteralNode)expression;
        long litToken = lit.getToken();
        TokenType tt = Token.descType(litToken);
        if (tt == TokenType.STRING || tt == TokenType.ESCSTRING)
          return this.source.getString(lit.getStart(), Token.descLength(litToken)); 
      } 
    } 
    return null;
  }
  
  private void sourceElements(int reparseFlags) {
    List<Node> directiveStmts = null;
    boolean checkDirective = true;
    int functionFlags = reparseFlags;
    boolean oldStrictMode = this.isStrictMode;
    try {
      while (this.type != TokenType.EOF) {
        if (this.type == TokenType.RBRACE)
          break; 
        try {
          statement(true, functionFlags, false, false);
          functionFlags = 0;
          if (checkDirective) {
            Statement lastStatement = this.lc.getLastStatement();
            String directive = getDirective((Node)lastStatement);
            checkDirective = (directive != null);
            if (checkDirective) {
              if (!oldStrictMode) {
                if (directiveStmts == null)
                  directiveStmts = new ArrayList<>(); 
                directiveStmts.add(lastStatement);
              } 
              if ("use strict".equals(directive)) {
                this.isStrictMode = true;
                ParserContextFunctionNode function = this.lc.getCurrentFunction();
                function.setFlag(4);
                if (!oldStrictMode) {
                  for (Node statement : directiveStmts)
                    getValue(statement.getToken()); 
                  verifyIdent(function.getIdent(), "function name");
                  for (IdentNode param : function.getParameters())
                    verifyIdent(param, "function parameter"); 
                } 
              } else if (Context.DEBUG) {
                int debugFlag = FunctionNode.getDirectiveFlag(directive);
                if (debugFlag != 0) {
                  ParserContextFunctionNode function = this.lc.getCurrentFunction();
                  function.setDebugFlag(debugFlag);
                } 
              } 
            } 
          } 
        } catch (Exception e) {
          int errorLine = this.line;
          long errorToken = this.token;
          recover(e);
          ErrorNode errorExpr = new ErrorNode(errorToken, this.finish);
          ExpressionStatement expressionStatement = new ExpressionStatement(errorLine, errorToken, this.finish, (Expression)errorExpr);
          appendStatement((Statement)expressionStatement);
        } 
        this.stream.commit(this.k);
      } 
    } finally {
      this.isStrictMode = oldStrictMode;
    } 
  }
  
  private void statement() {
    statement(false, 0, false, false);
  }
  
  private void statement(boolean topLevel, int reparseFlags, boolean singleStatement, boolean labelledStatement) {
    switch (this.type) {
      case LBRACE:
        block();
        return;
      case VAR:
        variableStatement(this.type);
        return;
      case SEMICOLON:
        emptyStatement();
        return;
      case IF:
        ifStatement();
        return;
      case FOR:
        forStatement();
        return;
      case WHILE:
        whileStatement();
        return;
      case DO:
        doStatement();
        return;
      case CONTINUE:
        continueStatement();
        return;
      case BREAK:
        breakStatement();
        return;
      case RETURN:
        returnStatement();
        return;
      case WITH:
        withStatement();
        return;
      case SWITCH:
        switchStatement();
        return;
      case THROW:
        throwStatement();
        return;
      case TRY:
        tryStatement();
        return;
      case DEBUGGER:
        debuggerStatement();
        return;
      case EOF:
      case RPAREN:
      case RBRACKET:
        expect(TokenType.SEMICOLON);
        return;
      case FUNCTION:
        if (singleStatement)
          if (!labelledStatement || this.isStrictMode)
            throw error(AbstractParser.message("expected.stmt", new String[] { "function declaration" }), this.token);  
        functionExpression(true, (topLevel || labelledStatement));
        return;
    } 
    if (useBlockScope() && ((this.type == TokenType.LET && lookaheadIsLetDeclaration(false)) || this.type == TokenType.CONST)) {
      if (singleStatement)
        throw error(AbstractParser.message("expected.stmt", new String[] { this.type.getName() + " declaration" }), this.token); 
      variableStatement(this.type);
    } else if (this.type == TokenType.CLASS && isES6()) {
      if (singleStatement)
        throw error(AbstractParser.message("expected.stmt", new String[] { "class declaration" }), this.token); 
      classDeclaration(false);
    } else if (this.env._const_as_var && this.type == TokenType.CONST) {
      variableStatement(TokenType.VAR);
    } else {
      if (this.type == TokenType.IDENT || isNonStrictModeIdent()) {
        if (T(this.k + 1) == TokenType.COLON) {
          labelStatement();
          return;
        } 
        if ((reparseFlags & 0x40) != 0) {
          String ident = (String)getValue();
          long propertyToken = this.token;
          int propertyLine = this.line;
          if ("get".equals(ident)) {
            next();
            addPropertyFunctionStatement(propertyGetterFunction(propertyToken, propertyLine));
            return;
          } 
          if ("set".equals(ident)) {
            next();
            addPropertyFunctionStatement(propertySetterFunction(propertyToken, propertyLine));
            return;
          } 
        } 
      } 
      if ((reparseFlags & 0x80) != 0 && (this.type == TokenType.IDENT || this.type == TokenType.LBRACKET || 
        isNonStrictModeIdent())) {
        String ident = (String)getValue();
        long propertyToken = this.token;
        int propertyLine = this.line;
        Expression propertyKey = propertyName();
        int flags = "constructor".equals(ident) ? 4194304 : 2097152;
        addPropertyFunctionStatement(propertyMethodFunction(propertyKey, propertyToken, propertyLine, false, flags, false));
        return;
      } 
      expressionStatement();
    } 
  }
  
  private void addPropertyFunctionStatement(PropertyFunction propertyFunction) {
    FunctionNode fn = propertyFunction.functionNode;
    this.functionDeclarations.add(new ExpressionStatement(fn.getLineNumber(), fn.getToken(), this.finish, (Expression)fn));
  }
  
  private ClassNode classDeclaration(boolean isDefault) {
    int classLineNumber = this.line;
    ClassNode classExpression = classExpression(!isDefault);
    if (!isDefault) {
      VarNode classVar = new VarNode(classLineNumber, classExpression.getToken(), classExpression.getIdent().getFinish(), classExpression.getIdent(), (Expression)classExpression, 2);
      appendStatement((Statement)classVar);
    } 
    return classExpression;
  }
  
  private ClassNode classExpression(boolean isStatement) {
    assert this.type == TokenType.CLASS;
    int classLineNumber = this.line;
    long classToken = this.token;
    next();
    IdentNode className = null;
    if (isStatement || this.type == TokenType.IDENT)
      className = getIdent(); 
    return classTail(classLineNumber, classToken, className, isStatement);
  }
  
  private static final class ClassElementKey {
    private final boolean isStatic;
    
    private final String propertyName;
    
    private ClassElementKey(boolean isStatic, String propertyName) {
      this.isStatic = isStatic;
      this.propertyName = propertyName;
    }
    
    public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.isStatic ? 1231 : 1237);
      result = 31 * result + ((this.propertyName == null) ? 0 : this.propertyName.hashCode());
      return result;
    }
    
    public boolean equals(Object obj) {
      if (obj instanceof ClassElementKey) {
        ClassElementKey other = (ClassElementKey)obj;
        return (this.isStatic == other.isStatic && Objects.equals(this.propertyName, other.propertyName));
      } 
      return false;
    }
  }
  
  private ClassNode classTail(int classLineNumber, long classToken, IdentNode className, boolean isStatement) {
    boolean oldStrictMode = this.isStrictMode;
    this.isStrictMode = true;
    try {
      Expression classHeritage = null;
      if (this.type == TokenType.EXTENDS) {
        next();
        classHeritage = leftHandSideExpression();
      } 
      expect(TokenType.LBRACE);
      PropertyNode constructor = null;
      ArrayList<PropertyNode> classElements = new ArrayList<>();
      Map<ClassElementKey, Integer> keyToIndexMap = new HashMap<>();
      while (true) {
        while (this.type == TokenType.SEMICOLON)
          next(); 
        if (this.type == TokenType.RBRACE)
          break; 
        long classElementToken = this.token;
        boolean isStatic = false;
        if (this.type == TokenType.STATIC) {
          isStatic = true;
          next();
        } 
        boolean generator = false;
        if (isES6() && this.type == TokenType.MUL) {
          generator = true;
          next();
        } 
        PropertyNode classElement = methodDefinition(isStatic, (classHeritage != null), generator);
        if (classElement.isComputed()) {
          classElements.add(classElement);
          continue;
        } 
        if (!classElement.isStatic() && "constructor".equals(classElement.getKeyName())) {
          if (constructor == null) {
            constructor = classElement;
            continue;
          } 
          throw error(AbstractParser.message("multiple.constructors", new String[0]), classElementToken);
        } 
        ClassElementKey key = new ClassElementKey(classElement.isStatic(), classElement.getKeyName());
        Integer existing = keyToIndexMap.get(key);
        if (existing == null) {
          keyToIndexMap.put(key, Integer.valueOf(classElements.size()));
          classElements.add(classElement);
          continue;
        } 
        PropertyNode existingProperty = classElements.get(existing.intValue());
        Expression value = classElement.getValue();
        FunctionNode getter = classElement.getGetter();
        FunctionNode setter = classElement.getSetter();
        if (value != null || existingProperty.getValue() != null) {
          keyToIndexMap.put(key, Integer.valueOf(classElements.size()));
          classElements.add(classElement);
          continue;
        } 
        if (getter != null) {
          assert existingProperty.getGetter() != null || existingProperty.getSetter() != null;
          classElements.set(existing.intValue(), existingProperty.setGetter(getter));
          continue;
        } 
        if (setter != null) {
          assert existingProperty.getGetter() != null || existingProperty.getSetter() != null;
          classElements.set(existing.intValue(), existingProperty.setSetter(setter));
        } 
      } 
      long lastToken = this.token;
      expect(TokenType.RBRACE);
      if (constructor == null)
        constructor = createDefaultClassConstructor(classLineNumber, classToken, lastToken, className, (classHeritage != null)); 
      classElements.trimToSize();
      return new ClassNode(classLineNumber, classToken, this.finish, className, classHeritage, constructor, classElements, isStatement);
    } finally {
      this.isStrictMode = oldStrictMode;
    } 
  }
  
  private PropertyNode createDefaultClassConstructor(int classLineNumber, long classToken, long lastToken, IdentNode className, boolean subclass) {
    List<Statement> statements;
    List<IdentNode> parameters;
    int ctorFinish = this.finish;
    long identToken = Token.recast(classToken, TokenType.IDENT);
    if (subclass) {
      IdentNode superIdent = createIdentNode(identToken, ctorFinish, TokenType.SUPER.getName()).setIsDirectSuper();
      IdentNode argsIdent = createIdentNode(identToken, ctorFinish, "args").setIsRestParameter();
      UnaryNode unaryNode = new UnaryNode(Token.recast(classToken, TokenType.SPREAD_ARGUMENT), (Expression)argsIdent);
      CallNode superCall = new CallNode(classLineNumber, classToken, ctorFinish, (Expression)superIdent, Collections.singletonList(unaryNode), false);
      statements = Collections.singletonList(new ExpressionStatement(classLineNumber, classToken, ctorFinish, (Expression)superCall));
      parameters = Collections.singletonList(argsIdent);
    } else {
      statements = Collections.emptyList();
      parameters = Collections.emptyList();
    } 
    Block body = new Block(classToken, ctorFinish, 32, statements);
    IdentNode ctorName = (className != null) ? className : createIdentNode(identToken, ctorFinish, "constructor");
    ParserContextFunctionNode function = createParserContextFunctionNode(ctorName, classToken, FunctionNode.Kind.NORMAL, classLineNumber, parameters);
    function.setLastToken(lastToken);
    function.setFlag(2097152);
    function.setFlag(4194304);
    if (subclass) {
      function.setFlag(8388608);
      function.setFlag(524288);
    } 
    if (className == null)
      function.setFlag(1); 
    return new PropertyNode(classToken, ctorFinish, (Expression)ctorName, (Expression)createFunctionNode(function, classToken, ctorName, parameters, FunctionNode.Kind.NORMAL, classLineNumber, body), null, null, false, false);
  }
  
  private PropertyNode methodDefinition(boolean isStatic, boolean subclass, boolean generator) {
    long methodToken = this.token;
    int methodLine = this.line;
    boolean computed = (this.type == TokenType.LBRACKET);
    boolean isIdent = (this.type == TokenType.IDENT);
    Expression propertyName = propertyName();
    int flags = 2097152;
    if (!computed) {
      String name = ((PropertyKey)propertyName).getPropertyName();
      if (!generator && isIdent && this.type != TokenType.LPAREN && name.equals("get")) {
        PropertyFunction propertyFunction = propertyGetterFunction(methodToken, methodLine, flags);
        verifyAllowedMethodName(propertyFunction.key, isStatic, propertyFunction.computed, false, true);
        return new PropertyNode(methodToken, this.finish, propertyFunction.key, null, propertyFunction.functionNode, null, isStatic, propertyFunction.computed);
      } 
      if (!generator && isIdent && this.type != TokenType.LPAREN && name.equals("set")) {
        PropertyFunction propertyFunction = propertySetterFunction(methodToken, methodLine, flags);
        verifyAllowedMethodName(propertyFunction.key, isStatic, propertyFunction.computed, false, true);
        return new PropertyNode(methodToken, this.finish, propertyFunction.key, null, null, propertyFunction.functionNode, isStatic, propertyFunction.computed);
      } 
      if (!isStatic && !generator && name.equals("constructor")) {
        flags |= 0x400000;
        if (subclass)
          flags |= 0x800000; 
      } 
      verifyAllowedMethodName(propertyName, isStatic, false, generator, false);
    } 
    PropertyFunction methodDefinition = propertyMethodFunction(propertyName, methodToken, methodLine, generator, flags, computed);
    return new PropertyNode(methodToken, this.finish, methodDefinition.key, (Expression)methodDefinition.functionNode, null, null, isStatic, computed);
  }
  
  private void verifyAllowedMethodName(Expression key, boolean isStatic, boolean computed, boolean generator, boolean accessor) {
    if (!computed) {
      if (!isStatic && generator && ((PropertyKey)key).getPropertyName().equals("constructor"))
        throw error(AbstractParser.message("generator.constructor", new String[0]), key.getToken()); 
      if (!isStatic && accessor && ((PropertyKey)key).getPropertyName().equals("constructor"))
        throw error(AbstractParser.message("accessor.constructor", new String[0]), key.getToken()); 
      if (isStatic && ((PropertyKey)key).getPropertyName().equals("prototype"))
        throw error(AbstractParser.message("static.prototype.method", new String[0]), key.getToken()); 
    } 
  }
  
  private void block() {
    appendStatement((Statement)new BlockStatement(this.line, getBlock(true)));
  }
  
  private void statementList() {
    while (this.type != TokenType.EOF) {
      switch (this.type) {
        case RBRACE:
        case CASE:
        case DEFAULT:
          break;
      } 
      statement();
    } 
  }
  
  private void verifyIdent(IdentNode ident, String contextString) {
    verifyStrictIdent(ident, contextString);
    checkEscapedKeyword(ident);
  }
  
  private void verifyStrictIdent(IdentNode ident, String contextString) {
    if (this.isStrictMode) {
      switch (ident.getName()) {
        case "eval":
        case "arguments":
          throw error(AbstractParser.message("strict.name", new String[] { ident.getName(), contextString }), ident.getToken());
      } 
      if (ident.isFutureStrictName())
        throw error(AbstractParser.message("strict.name", new String[] { ident.getName(), contextString }), ident.getToken()); 
    } 
  }
  
  private void checkEscapedKeyword(IdentNode ident) {
    if (isES6() && ident.containsEscapes()) {
      TokenType tokenType = TokenLookup.lookupKeyword(ident.getName().toCharArray(), 0, ident.getName().length());
      if (tokenType != TokenType.IDENT && (tokenType.getKind() != TokenKind.FUTURESTRICT || this.isStrictMode))
        throw error(AbstractParser.message("keyword.escaped.character", new String[0]), ident.getToken()); 
    } 
  }
  
  private void variableStatement(TokenType varType) {
    variableDeclarationList(varType, true, -1);
  }
  
  private static final class ForVariableDeclarationListResult {
    Expression missingAssignment;
    
    long declarationWithInitializerToken;
    
    Expression init;
    
    Expression firstBinding;
    
    Expression secondBinding;
    
    void recordMissingAssignment(Expression binding) {
      if (this.missingAssignment == null)
        this.missingAssignment = binding; 
    }
    
    void recordDeclarationWithInitializer(long token) {
      if (this.declarationWithInitializerToken == 0L)
        this.declarationWithInitializerToken = token; 
    }
    
    void addBinding(Expression binding) {
      if (this.firstBinding == null) {
        this.firstBinding = binding;
      } else if (this.secondBinding == null) {
        this.secondBinding = binding;
      } 
    }
    
    void addAssignment(Expression assignment) {
      if (this.init == null) {
        this.init = assignment;
      } else {
        this.init = (Expression)new BinaryNode(Token.recast(this.init.getToken(), TokenType.COMMARIGHT), this.init, assignment);
      } 
    }
  }
  
  private ForVariableDeclarationListResult variableDeclarationList(TokenType varType, boolean isStatement, int sourceOrder) {
    assert varType == TokenType.VAR || varType == TokenType.LET || varType == TokenType.CONST;
    int varLine = this.line;
    long varToken = this.token;
    next();
    int varFlags = 0;
    if (varType == TokenType.LET) {
      varFlags |= 0x1;
    } else if (varType == TokenType.CONST) {
      varFlags |= 0x2;
    } 
    ForVariableDeclarationListResult forResult = isStatement ? null : new ForVariableDeclarationListResult();
    while (true) {
      if (this.type == TokenType.YIELD && inGeneratorFunction())
        expect(TokenType.IDENT); 
      String contextString = "variable name";
      Expression binding = bindingIdentifierOrPattern("variable name");
      boolean isDestructuring = !(binding instanceof IdentNode);
      if (isDestructuring) {
        int finalVarFlags = varFlags;
        verifyDestructuringBindingPattern(binding, identNode -> {
              verifyIdent(identNode, "variable name");
              if (!this.env._parse_only) {
                VarNode var = new VarNode(varLine, varToken, sourceOrder, identNode.getFinish(), identNode.setIsDeclaredHere(), null, finalVarFlags);
                appendStatement((Statement)var);
              } 
            });
      } 
      Expression init = null;
      if (this.type == TokenType.ASSIGN) {
        if (!isStatement)
          forResult.recordDeclarationWithInitializer(varToken); 
        next();
        if (!isDestructuring)
          this.defaultNames.push(binding); 
        try {
          init = assignmentExpression(!isStatement);
        } finally {
          if (!isDestructuring)
            this.defaultNames.pop(); 
        } 
      } else if (isStatement) {
        if (isDestructuring)
          throw error(AbstractParser.message("missing.destructuring.assignment", new String[0]), this.token); 
        if (varType == TokenType.CONST)
          throw error(AbstractParser.message("missing.const.assignment", new String[] { ((IdentNode)binding).getName() })); 
      } 
      if (!isDestructuring) {
        assert init != null || varType != TokenType.CONST || !isStatement;
        IdentNode ident = (IdentNode)binding;
        if (!isStatement && ident.getName().equals("let"))
          throw error(AbstractParser.message("let.binding.for", new String[0])); 
        IdentNode name = (varType == TokenType.LET || varType == TokenType.CONST) ? ident.setIsDeclaredHere() : ident;
        if (!isStatement) {
          if (init == null && varType == TokenType.CONST)
            forResult.recordMissingAssignment((Expression)name); 
          forResult.addBinding((Expression)new IdentNode(name));
        } 
        VarNode var = new VarNode(varLine, varToken, sourceOrder, this.finish, name, init, varFlags);
        appendStatement((Statement)var);
      } else {
        assert init != null || !isStatement;
        if (init != null) {
          Expression assignment = verifyAssignment(Token.recast(varToken, TokenType.ASSIGN), binding, init);
          if (isStatement) {
            appendStatement((Statement)new ExpressionStatement(varLine, assignment.getToken(), this.finish, assignment, varType));
          } else {
            forResult.addAssignment(assignment);
            forResult.addBinding(assignment);
          } 
        } else if (!isStatement) {
          forResult.recordMissingAssignment(binding);
          forResult.addBinding(binding);
        } 
      } 
      if (this.type != TokenType.COMMARIGHT)
        break; 
      next();
    } 
    if (isStatement)
      endOfLine(); 
    return forResult;
  }
  
  private boolean isBindingIdentifier() {
    return (this.type == TokenType.IDENT || isNonStrictModeIdent());
  }
  
  private IdentNode bindingIdentifier(String contextString) {
    IdentNode name = getIdent();
    verifyIdent(name, contextString);
    return name;
  }
  
  private Expression bindingPattern() {
    if (this.type == TokenType.LBRACKET)
      return (Expression)arrayLiteral(); 
    if (this.type == TokenType.LBRACE)
      return (Expression)objectLiteral(); 
    throw error(AbstractParser.message("expected.binding", new String[0]));
  }
  
  private Expression bindingIdentifierOrPattern(String contextString) {
    if (isBindingIdentifier() || !isES6())
      return (Expression)bindingIdentifier(contextString); 
    return bindingPattern();
  }
  
  private abstract class VerifyDestructuringPatternNodeVisitor extends NodeVisitor<LexicalContext> {
    VerifyDestructuringPatternNodeVisitor(LexicalContext lc) {
      super(lc);
    }
    
    public boolean enterLiteralNode(LiteralNode<?> literalNode) {
      if (literalNode.isArray()) {
        if (((LiteralNode.ArrayLiteralNode)literalNode).hasSpread() && ((LiteralNode.ArrayLiteralNode)literalNode).hasTrailingComma())
          throw Parser.this.error("Rest element must be last", ((Expression)literalNode.getElementExpressions().get(literalNode.getElementExpressions().size() - 1)).getToken()); 
        boolean restElement = false;
        for (Expression element : literalNode.getElementExpressions()) {
          if (element != null) {
            if (restElement)
              throw Parser.this.error("Unexpected element after rest element", element.getToken()); 
            if (element.isTokenType(TokenType.SPREAD_ARRAY)) {
              restElement = true;
              Expression lvalue = ((UnaryNode)element).getExpression();
              verifySpreadElement(lvalue);
            } 
            element.accept(this);
          } 
        } 
        return false;
      } 
      return enterDefault((Node)literalNode);
    }
    
    protected abstract void verifySpreadElement(Expression param1Expression);
    
    public boolean enterObjectNode(ObjectNode objectNode) {
      return true;
    }
    
    public boolean enterPropertyNode(PropertyNode propertyNode) {
      if (propertyNode.getValue() != null) {
        propertyNode.getValue().accept(this);
        return false;
      } 
      return enterDefault((Node)propertyNode);
    }
    
    public boolean enterBinaryNode(BinaryNode binaryNode) {
      if (binaryNode.isTokenType(TokenType.ASSIGN)) {
        binaryNode.lhs().accept(this);
        return false;
      } 
      return enterDefault((Node)binaryNode);
    }
    
    public boolean enterUnaryNode(UnaryNode unaryNode) {
      if (unaryNode.isTokenType(TokenType.SPREAD_ARRAY))
        return true; 
      return enterDefault((Node)unaryNode);
    }
  }
  
  private void verifyDestructuringBindingPattern(Expression pattern, final Consumer<IdentNode> identifierCallback) {
    assert pattern instanceof ObjectNode || pattern instanceof LiteralNode.ArrayLiteralNode;
    pattern.accept(new VerifyDestructuringPatternNodeVisitor(new LexicalContext()) {
          protected void verifySpreadElement(Expression lvalue) {
            if (!(lvalue instanceof IdentNode))
              if (Parser.this.isDestructuringLhs(lvalue)) {
                Parser.this.verifyDestructuringBindingPattern(lvalue, identifierCallback);
              } else {
                throw Parser.this.error("Expected a valid binding identifier", lvalue.getToken());
              }  
          }
          
          public boolean enterIdentNode(IdentNode identNode) {
            identifierCallback.accept(identNode);
            return false;
          }
          
          protected boolean enterDefault(Node node) {
            throw Parser.this.error(String.format("unexpected node in BindingPattern: %s", new Object[] { node }));
          }
        });
  }
  
  private void emptyStatement() {
    if (this.env._empty_statements)
      appendStatement((Statement)new EmptyNode(this.line, this.token, Token.descPosition(this.token) + Token.descLength(this.token))); 
    next();
  }
  
  private void expressionStatement() {
    int expressionLine = this.line;
    long expressionToken = this.token;
    Expression expression = expression();
    if (expression != null) {
      ExpressionStatement expressionStatement = new ExpressionStatement(expressionLine, expressionToken, this.finish, expression);
      appendStatement((Statement)expressionStatement);
    } else {
      expect(null);
    } 
    endOfLine();
  }
  
  private void ifStatement() {
    int ifLine = this.line;
    long ifToken = this.token;
    next();
    expect(TokenType.LPAREN);
    Expression test = expression();
    expect(TokenType.RPAREN);
    Block pass = getStatement();
    Block fail = null;
    if (this.type == TokenType.ELSE) {
      next();
      fail = getStatement();
    } 
    appendStatement((Statement)new IfNode(ifLine, ifToken, (fail != null) ? fail.getFinish() : pass.getFinish(), test, pass, fail));
  }
  
  private void forStatement() {
    long forToken = this.token;
    int forLine = this.line;
    int forStart = Token.descPosition(forToken);
    ParserContextBlockNode outer = useBlockScope() ? newBlock() : null;
    ParserContextLoopNode forNode = new ParserContextLoopNode();
    this.lc.push(forNode);
    Block body = null;
    Expression init = null;
    JoinPredecessorExpression test = null;
    JoinPredecessorExpression modify = null;
    ForVariableDeclarationListResult varDeclList = null;
    int flags = 0;
    boolean isForOf = false;
    try {
      next();
      if (!this.env._no_syntax_extensions && this.type == TokenType.IDENT && "each".equals(getValue())) {
        flags |= 0x2;
        next();
      } 
      expect(TokenType.LPAREN);
      TokenType varType = null;
      switch (this.type) {
        case VAR:
          varDeclList = variableDeclarationList(varType = this.type, false, forStart);
          break;
        case SEMICOLON:
          break;
        default:
          if (useBlockScope() && ((this.type == TokenType.LET && lookaheadIsLetDeclaration(true)) || this.type == TokenType.CONST)) {
            flags |= 0x8;
            varDeclList = variableDeclarationList(varType = this.type, false, forStart);
            break;
          } 
          if (this.env._const_as_var && this.type == TokenType.CONST) {
            varDeclList = variableDeclarationList(varType = TokenType.VAR, false, forStart);
            break;
          } 
          init = expression(unaryExpression(), TokenType.COMMARIGHT.getPrecedence(), true);
          break;
      } 
      switch (this.type) {
        case SEMICOLON:
          if (varDeclList != null) {
            assert init == null;
            init = varDeclList.init;
            if (varDeclList.missingAssignment != null) {
              if (varDeclList.missingAssignment instanceof IdentNode)
                throw error(AbstractParser.message("missing.const.assignment", new String[] { ((IdentNode)varDeclList.missingAssignment).getName() })); 
              throw error(AbstractParser.message("missing.destructuring.assignment", new String[0]), varDeclList.missingAssignment.getToken());
            } 
          } 
          if ((flags & 0x2) != 0)
            throw error(AbstractParser.message("for.each.without.in", new String[0]), this.token); 
          expect(TokenType.SEMICOLON);
          if (this.type != TokenType.SEMICOLON)
            test = joinPredecessorExpression(); 
          expect(TokenType.SEMICOLON);
          if (this.type != TokenType.RPAREN)
            modify = joinPredecessorExpression(); 
          break;
        case IDENT:
          if (isES6() && "of".equals(getValue())) {
            isForOf = true;
          } else {
            expect(TokenType.SEMICOLON);
            break;
          } 
        case IN:
          flags |= isForOf ? 4 : 1;
          test = new JoinPredecessorExpression();
          if (varDeclList != null) {
            if (varDeclList.secondBinding != null)
              throw error(AbstractParser.message("many.vars.in.for.in.loop", new String[] { isForOf ? "of" : "in" }), varDeclList.secondBinding.getToken()); 
            if (varDeclList.declarationWithInitializerToken != 0L && (this.isStrictMode || this.type != TokenType.IN || varType != TokenType.VAR || varDeclList.init != null))
              throw error(AbstractParser.message("for.in.loop.initializer", new String[] { isForOf ? "of" : "in" }), varDeclList.declarationWithInitializerToken); 
            init = varDeclList.firstBinding;
            assert init instanceof IdentNode || isDestructuringLhs(init);
          } else {
            assert init != null : "for..in/of init expression can not be null here";
            if (!checkValidLValue(init, isForOf ? "for-of iterator" : "for-in iterator"))
              throw error(AbstractParser.message("not.lvalue.for.in.loop", new String[] { isForOf ? "of" : "in" }), init.getToken()); 
          } 
          next();
          modify = isForOf ? new JoinPredecessorExpression(assignmentExpression(false)) : joinPredecessorExpression();
          break;
        default:
          expect(TokenType.SEMICOLON);
          break;
      } 
      expect(TokenType.RPAREN);
      body = getStatement();
    } finally {
      this.lc.pop(forNode);
      for (Statement var : forNode.getStatements()) {
        assert var instanceof VarNode;
        appendStatement(var);
      } 
      if (body != null)
        appendStatement((Statement)new ForNode(forLine, forToken, body.getFinish(), body, forNode.getFlags() | flags, init, test, modify)); 
      if (outer != null) {
        restoreBlock(outer);
        if (body != null) {
          List<Statement> statements = new ArrayList<>();
          for (Statement var : outer.getStatements()) {
            if (var instanceof VarNode && !((VarNode)var).isBlockScoped()) {
              appendStatement(var);
              continue;
            } 
            statements.add(var);
          } 
          appendStatement((Statement)new BlockStatement(forLine, new Block(outer
                  .getToken(), body
                  .getFinish(), statements)));
        } 
      } 
    } 
  }
  
  private boolean checkValidLValue(Expression init, String contextString) {
    if (init instanceof IdentNode) {
      if (!checkIdentLValue((IdentNode)init))
        return false; 
      verifyIdent((IdentNode)init, contextString);
      return true;
    } 
    if (init instanceof AccessNode || init instanceof IndexNode)
      return true; 
    if (isDestructuringLhs(init)) {
      verifyDestructuringAssignmentPattern(init, contextString);
      return true;
    } 
    return false;
  }
  
  private boolean lookaheadIsLetDeclaration(boolean ofContextualKeyword) {
    assert this.type == TokenType.LET;
    for (int i = 1;; i++) {
      TokenType t = T(this.k + i);
      switch (t) {
        case EOL:
        case COMMENT:
          break;
        case IDENT:
          if (ofContextualKeyword && isES6() && "of".equals(getValue(getToken(this.k + i))))
            return false; 
        case LBRACE:
        case LBRACKET:
          return true;
        default:
          return (!this.isStrictMode && t.getKind() == TokenKind.FUTURESTRICT);
      } 
    } 
  }
  
  private void whileStatement() {
    JoinPredecessorExpression test;
    Block body;
    long whileToken = this.token;
    int whileLine = this.line;
    next();
    ParserContextLoopNode whileNode = new ParserContextLoopNode();
    this.lc.push(whileNode);
    try {
      expect(TokenType.LPAREN);
      test = joinPredecessorExpression();
      expect(TokenType.RPAREN);
      body = getStatement();
    } finally {
      this.lc.pop(whileNode);
    } 
    appendStatement((Statement)new WhileNode(whileLine, whileToken, body.getFinish(), false, test, body));
  }
  
  private void doStatement() {
    int doLine;
    Block body;
    JoinPredecessorExpression test;
    long doToken = this.token;
    next();
    ParserContextLoopNode doWhileNode = new ParserContextLoopNode();
    this.lc.push(doWhileNode);
    try {
      body = getStatement();
      expect(TokenType.WHILE);
      expect(TokenType.LPAREN);
      doLine = this.line;
      test = joinPredecessorExpression();
      expect(TokenType.RPAREN);
      if (this.type == TokenType.SEMICOLON)
        endOfLine(); 
    } finally {
      this.lc.pop(doWhileNode);
    } 
    appendStatement((Statement)new WhileNode(doLine, doToken, this.finish, true, test, body));
  }
  
  private void continueStatement() {
    IdentNode ident;
    int continueLine = this.line;
    long continueToken = this.token;
    nextOrEOL();
    ParserContextLabelNode labelNode = null;
    switch (this.type) {
      case EOF:
      case EOL:
      case SEMICOLON:
      case RBRACE:
        break;
      default:
        ident = getIdent();
        labelNode = this.lc.findLabel(ident.getName());
        if (labelNode == null)
          throw error(AbstractParser.message("undefined.label", new String[] { ident.getName() }), ident.getToken()); 
        break;
    } 
    String labelName = (labelNode == null) ? null : labelNode.getLabelName();
    ParserContextLoopNode targetNode = this.lc.getContinueTo(labelName);
    if (targetNode == null)
      throw error(AbstractParser.message("illegal.continue.stmt", new String[0]), continueToken); 
    endOfLine();
    appendStatement((Statement)new ContinueNode(continueLine, continueToken, this.finish, labelName));
  }
  
  private void breakStatement() {
    IdentNode ident;
    int breakLine = this.line;
    long breakToken = this.token;
    nextOrEOL();
    ParserContextLabelNode labelNode = null;
    switch (this.type) {
      case EOF:
      case EOL:
      case SEMICOLON:
      case RBRACE:
        break;
      default:
        ident = getIdent();
        labelNode = this.lc.findLabel(ident.getName());
        if (labelNode == null)
          throw error(AbstractParser.message("undefined.label", new String[] { ident.getName() }), ident.getToken()); 
        break;
    } 
    String labelName = (labelNode == null) ? null : labelNode.getLabelName();
    ParserContextBreakableNode targetNode = this.lc.getBreakable(labelName);
    if (targetNode instanceof ParserContextBlockNode)
      targetNode.setFlag(256); 
    if (targetNode == null)
      throw error(AbstractParser.message("illegal.break.stmt", new String[0]), breakToken); 
    endOfLine();
    appendStatement((Statement)new BreakNode(breakLine, breakToken, this.finish, labelName));
  }
  
  private void returnStatement() {
    if (this.lc.getCurrentFunction().getKind() == FunctionNode.Kind.SCRIPT || this.lc.getCurrentFunction().getKind() == FunctionNode.Kind.MODULE)
      throw error(AbstractParser.message("invalid.return", new String[0])); 
    int returnLine = this.line;
    long returnToken = this.token;
    nextOrEOL();
    Expression expression = null;
    switch (this.type) {
      case EOF:
      case EOL:
      case SEMICOLON:
      case RBRACE:
        break;
      default:
        expression = expression();
        break;
    } 
    endOfLine();
    appendStatement((Statement)new ReturnNode(returnLine, returnToken, this.finish, expression));
  }
  
  private Expression yieldExpression(boolean noIn) {
    assert inGeneratorFunction();
    long yieldToken = this.token;
    assert this.type == TokenType.YIELD;
    nextOrEOL();
    boolean yieldAsterisk = false;
    if (this.type == TokenType.MUL) {
      yieldAsterisk = true;
      yieldToken = Token.recast(yieldToken, TokenType.YIELD_STAR);
      next();
    } 
    switch (this.type) {
      case EOF:
      case EOL:
      case SEMICOLON:
      case RBRACE:
      case RPAREN:
      case RBRACKET:
      case COMMARIGHT:
      case COLON:
        if (!yieldAsterisk) {
          UnaryNode unaryNode = newUndefinedLiteral(yieldToken, this.finish);
          if (this.type == TokenType.EOL)
            next(); 
          return (Expression)new UnaryNode(yieldToken, (Expression)unaryNode);
        } 
        break;
    } 
    Expression expression = assignmentExpression(noIn);
    return (Expression)new UnaryNode(yieldToken, expression);
  }
  
  private static UnaryNode newUndefinedLiteral(long token, int finish) {
    return new UnaryNode(Token.recast(token, TokenType.VOID), (Expression)LiteralNode.newInstance(token, finish, Integer.valueOf(0)));
  }
  
  private void withStatement() {
    int withLine = this.line;
    long withToken = this.token;
    next();
    if (this.isStrictMode)
      throw error(AbstractParser.message("strict.no.with", new String[0]), withToken); 
    expect(TokenType.LPAREN);
    Expression expression = expression();
    expect(TokenType.RPAREN);
    Block body = getStatement();
    appendStatement((Statement)new WithNode(withLine, withToken, this.finish, expression, body));
  }
  
  private void switchStatement() {
    Expression expression;
    int switchLine = this.line;
    long switchToken = this.token;
    ParserContextBlockNode switchBlock = newBlock();
    next();
    ParserContextSwitchNode switchNode = new ParserContextSwitchNode();
    this.lc.push(switchNode);
    CaseNode defaultCase = null;
    List<CaseNode> cases = new ArrayList<>();
    try {
      expect(TokenType.LPAREN);
      expression = expression();
      expect(TokenType.RPAREN);
      expect(TokenType.LBRACE);
      while (this.type != TokenType.RBRACE) {
        Expression caseExpression = null;
        long caseToken = this.token;
        switch (this.type) {
          case CASE:
            next();
            caseExpression = expression();
            break;
          case DEFAULT:
            if (defaultCase != null)
              throw error(AbstractParser.message("duplicate.default.in.switch", new String[0])); 
            next();
            break;
          default:
            expect(TokenType.CASE);
            break;
        } 
        expect(TokenType.COLON);
        Block statements = getBlock(false);
        CaseNode caseNode = new CaseNode(caseToken, this.finish, caseExpression, statements);
        if (caseExpression == null)
          defaultCase = caseNode; 
        cases.add(caseNode);
      } 
      next();
    } finally {
      this.lc.pop(switchNode);
      restoreBlock(switchBlock);
    } 
    SwitchNode switchStatement = new SwitchNode(switchLine, switchToken, this.finish, expression, cases, defaultCase);
    appendStatement((Statement)new BlockStatement(switchLine, new Block(switchToken, this.finish, switchBlock.getFlags() | 0x10 | 0x80, new Statement[] { (Statement)switchStatement })));
  }
  
  private void labelStatement() {
    Block body;
    long labelToken = this.token;
    IdentNode ident = getIdent();
    expect(TokenType.COLON);
    if (this.lc.findLabel(ident.getName()) != null)
      throw error(AbstractParser.message("duplicate.label", new String[] { ident.getName() }), labelToken); 
    ParserContextLabelNode labelNode = new ParserContextLabelNode(ident.getName());
    try {
      this.lc.push(labelNode);
      body = getStatement(true);
    } finally {
      assert this.lc.peek() instanceof ParserContextLabelNode;
      this.lc.pop(labelNode);
    } 
    appendStatement((Statement)new LabelNode(this.line, labelToken, this.finish, ident.getName(), body));
  }
  
  private void throwStatement() {
    int throwLine = this.line;
    long throwToken = this.token;
    nextOrEOL();
    Expression expression = null;
    switch (this.type) {
      case EOL:
      case SEMICOLON:
      case RBRACE:
        break;
      default:
        expression = expression();
        break;
    } 
    if (expression == null)
      throw error(AbstractParser.message("expected.operand", new String[] { this.type.getNameOrType() })); 
    endOfLine();
    appendStatement((Statement)new ThrowNode(throwLine, throwToken, this.finish, expression, false));
  }
  
  private void tryStatement() {
    int tryLine = this.line;
    long tryToken = this.token;
    next();
    int startLine = this.line;
    ParserContextBlockNode outer = newBlock();
    try {
      Block tryBody = getBlock(true);
      List<Block> catchBlocks = new ArrayList<>();
      while (this.type == TokenType.CATCH) {
        Expression ifExpression;
        int catchLine = this.line;
        long catchToken = this.token;
        next();
        expect(TokenType.LPAREN);
        String contextString = "catch argument";
        Expression exception = bindingIdentifierOrPattern("catch argument");
        boolean isDestructuring = !(exception instanceof IdentNode);
        if (isDestructuring) {
          verifyDestructuringBindingPattern(exception, identNode -> verifyIdent(identNode, "catch argument"));
        } else {
          verifyIdent((IdentNode)exception, "catch argument");
        } 
        if (!this.env._no_syntax_extensions && this.type == TokenType.IF) {
          next();
          ifExpression = expression();
        } else {
          ifExpression = null;
        } 
        expect(TokenType.RPAREN);
        ParserContextBlockNode catchBlock = newBlock();
        try {
          Block catchBody = getBlock(true);
          CatchNode catchNode = new CatchNode(catchLine, catchToken, this.finish, exception, ifExpression, catchBody, false);
          appendStatement((Statement)catchNode);
        } finally {
          restoreBlock(catchBlock);
          catchBlocks.add(new Block(catchBlock.getToken(), this.finish, catchBlock.getFlags() | 0x10, catchBlock.getStatements()));
        } 
        if (ifExpression == null)
          break; 
      } 
      Block finallyStatements = null;
      if (this.type == TokenType.FINALLY) {
        next();
        finallyStatements = getBlock(true);
      } 
      if (catchBlocks.isEmpty() && finallyStatements == null)
        throw error(AbstractParser.message("missing.catch.or.finally", new String[0]), tryToken); 
      TryNode tryNode = new TryNode(tryLine, tryToken, this.finish, tryBody, catchBlocks, finallyStatements);
      assert this.lc.peek() == outer;
      appendStatement((Statement)tryNode);
    } finally {
      restoreBlock(outer);
    } 
    appendStatement((Statement)new BlockStatement(startLine, new Block(tryToken, this.finish, outer.getFlags() | 0x10, outer.getStatements())));
  }
  
  private void debuggerStatement() {
    int debuggerLine = this.line;
    long debuggerToken = this.token;
    next();
    endOfLine();
    appendStatement((Statement)new DebuggerNode(debuggerLine, debuggerToken, this.finish));
  }
  
  private Expression primaryExpression() {
    String name;
    IdentNode ident;
    Expression expression;
    int primaryLine = this.line;
    long primaryToken = this.token;
    switch (this.type) {
      case THIS:
        name = this.type.getName();
        next();
        markThis(this.lc);
        return (Expression)new IdentNode(primaryToken, this.finish, name);
      case IDENT:
        ident = getIdent();
        if (ident != null) {
          detectSpecialProperty(ident);
          checkEscapedKeyword(ident);
          return (Expression)ident;
        } 
        return null;
      case OCTAL_LEGACY:
        if (this.isStrictMode)
          throw error(AbstractParser.message("strict.no.octal", new String[0]), this.token); 
      case STRING:
      case ESCSTRING:
      case DECIMAL:
      case HEXADECIMAL:
      case OCTAL:
      case BINARY_NUMBER:
      case FLOATING:
      case REGEX:
      case XML:
        return (Expression)getLiteral();
      case EXECSTRING:
        return (Expression)execString(primaryLine, primaryToken);
      case FALSE:
        next();
        return (Expression)LiteralNode.newInstance(primaryToken, this.finish, false);
      case TRUE:
        next();
        return (Expression)LiteralNode.newInstance(primaryToken, this.finish, true);
      case NULL:
        next();
        return (Expression)LiteralNode.newInstance(primaryToken, this.finish);
      case LBRACKET:
        return (Expression)arrayLiteral();
      case LBRACE:
        return (Expression)objectLiteral();
      case LPAREN:
        next();
        if (isES6()) {
          if (this.type == TokenType.RPAREN) {
            nextOrEOL();
            expectDontAdvance(TokenType.ARROW);
            return (Expression)new ExpressionList(primaryToken, this.finish, Collections.emptyList());
          } 
          if (this.type == TokenType.ELLIPSIS) {
            IdentNode restParam = formalParameterList(false).get(0);
            expectDontAdvance(TokenType.RPAREN);
            nextOrEOL();
            expectDontAdvance(TokenType.ARROW);
            return (Expression)new ExpressionList(primaryToken, this.finish, Collections.singletonList(restParam));
          } 
        } 
        expression = expression();
        expect(TokenType.RPAREN);
        return expression;
      case TEMPLATE:
      case TEMPLATE_HEAD:
        return templateLiteral();
    } 
    if (this.lexer.scanLiteral(primaryToken, this.type, this.lineInfoReceiver)) {
      next();
      return (Expression)getLiteral();
    } 
    if (isNonStrictModeIdent())
      return (Expression)getIdent(); 
    return null;
  }
  
  CallNode execString(int primaryLine, long primaryToken) {
    IdentNode execIdent = new IdentNode(primaryToken, this.finish, "$EXEC");
    next();
    expect(TokenType.LBRACE);
    List<Expression> arguments = Collections.singletonList(expression());
    expect(TokenType.RBRACE);
    return new CallNode(primaryLine, primaryToken, this.finish, (Expression)execIdent, arguments, false);
  }
  
  private LiteralNode<Expression[]> arrayLiteral() {
    long arrayToken = this.token;
    next();
    List<Expression> elements = new ArrayList<>();
    boolean elision = true;
    boolean hasSpread = false;
    while (true) {
      long spreadToken = 0L;
      switch (this.type) {
        case RBRACKET:
          next();
          break;
        case COMMARIGHT:
          next();
          if (elision)
            elements.add(null); 
          elision = true;
          continue;
        case ELLIPSIS:
          if (isES6()) {
            hasSpread = true;
            spreadToken = this.token;
            next();
          } 
          break;
      } 
      if (!elision)
        throw error(AbstractParser.message("expected.comma", new String[] { this.type.getNameOrType() })); 
      Expression expression = assignmentExpression(false);
      if (expression != null) {
        UnaryNode unaryNode;
        if (spreadToken != 0L)
          unaryNode = new UnaryNode(Token.recast(spreadToken, TokenType.SPREAD_ARRAY), expression); 
        elements.add(unaryNode);
      } else {
        expect(TokenType.RBRACKET);
      } 
      elision = false;
    } 
    return LiteralNode.newInstance(arrayToken, this.finish, elements, hasSpread, elision);
  }
  
  private ObjectNode objectLiteral() {
    long objectToken = this.token;
    next();
    List<PropertyNode> elements = new ArrayList<>();
    Map<String, Integer> map = new HashMap<>();
    boolean commaSeen = true;
    while (true) {
      switch (this.type) {
        case RBRACE:
          next();
          break;
        case COMMARIGHT:
          if (commaSeen)
            throw error(AbstractParser.message("expected.property.id", new String[] { this.type.getNameOrType() })); 
          next();
          commaSeen = true;
          continue;
      } 
      if (!commaSeen)
        throw error(AbstractParser.message("expected.comma", new String[] { this.type.getNameOrType() })); 
      commaSeen = false;
      PropertyNode property = propertyAssignment();
      if (property.isComputed()) {
        elements.add(property);
        continue;
      } 
      String key = property.getKeyName();
      Integer existing = map.get(key);
      if (existing == null) {
        map.put(key, Integer.valueOf(elements.size()));
        elements.add(property);
        continue;
      } 
      PropertyNode existingProperty = elements.get(existing.intValue());
      Expression value = property.getValue();
      FunctionNode getter = property.getGetter();
      FunctionNode setter = property.getSetter();
      Expression prevValue = existingProperty.getValue();
      FunctionNode prevGetter = existingProperty.getGetter();
      FunctionNode prevSetter = existingProperty.getSetter();
      if (!isES6()) {
        checkPropertyRedefinition(property, value, getter, setter, prevValue, prevGetter, prevSetter);
      } else if (property.getKey() instanceof IdentNode && ((IdentNode)property.getKey()).isProtoPropertyName() && existingProperty
        .getKey() instanceof IdentNode && ((IdentNode)existingProperty.getKey()).isProtoPropertyName()) {
        throw error(AbstractParser.message("multiple.proto.key", new String[0]), property.getToken());
      } 
      if (value != null || prevValue != null) {
        map.put(key, Integer.valueOf(elements.size()));
        elements.add(property);
        continue;
      } 
      if (getter != null) {
        assert prevGetter != null || prevSetter != null;
        elements.set(existing.intValue(), existingProperty.setGetter(getter));
        continue;
      } 
      if (setter != null) {
        assert prevGetter != null || prevSetter != null;
        elements.set(existing.intValue(), existingProperty.setSetter(setter));
      } 
    } 
    return new ObjectNode(objectToken, this.finish, elements);
  }
  
  private void checkPropertyRedefinition(PropertyNode property, Expression value, FunctionNode getter, FunctionNode setter, Expression prevValue, FunctionNode prevGetter, FunctionNode prevSetter) {
    if (this.isStrictMode && value != null && prevValue != null)
      throw error(AbstractParser.message("property.redefinition", new String[] { property.getKeyName() }), property.getToken()); 
    boolean isPrevAccessor = (prevGetter != null || prevSetter != null);
    boolean isAccessor = (getter != null || setter != null);
    if (prevValue != null && isAccessor)
      throw error(AbstractParser.message("property.redefinition", new String[] { property.getKeyName() }), property.getToken()); 
    if (isPrevAccessor && value != null)
      throw error(AbstractParser.message("property.redefinition", new String[] { property.getKeyName() }), property.getToken()); 
    if (isAccessor && isPrevAccessor && ((
      getter != null && prevGetter != null) || (setter != null && prevSetter != null)))
      throw error(AbstractParser.message("property.redefinition", new String[] { property.getKeyName() }), property.getToken()); 
  }
  
  private PropertyKey literalPropertyName() {
    switch (this.type) {
      case IDENT:
        return (PropertyKey)getIdent().setIsPropertyName();
      case OCTAL_LEGACY:
        if (this.isStrictMode)
          throw error(AbstractParser.message("strict.no.octal", new String[0]), this.token); 
      case STRING:
      case ESCSTRING:
      case DECIMAL:
      case HEXADECIMAL:
      case OCTAL:
      case BINARY_NUMBER:
      case FLOATING:
        return (PropertyKey)getLiteral();
    } 
    return (PropertyKey)getIdentifierName().setIsPropertyName();
  }
  
  private Expression computedPropertyName() {
    expect(TokenType.LBRACKET);
    Expression expression = assignmentExpression(false);
    expect(TokenType.RBRACKET);
    return expression;
  }
  
  private Expression propertyName() {
    if (this.type == TokenType.LBRACKET && isES6())
      return computedPropertyName(); 
    return (Expression)literalPropertyName();
  }
  
  private PropertyNode propertyAssignment() {
    Expression propertyName;
    boolean isIdentifier;
    Expression propertyValue;
    long propertyToken = this.token;
    int functionLine = this.line;
    boolean generator = false;
    if (this.type == TokenType.MUL && isES6()) {
      generator = true;
      next();
    } 
    boolean computed = (this.type == TokenType.LBRACKET);
    if (this.type == TokenType.IDENT) {
      String ident = (String)expectValue(TokenType.IDENT);
      if (this.type != TokenType.COLON && (this.type != TokenType.LPAREN || !isES6())) {
        PropertyFunction getter;
        PropertyFunction setter;
        switch (ident) {
          case "get":
            getter = propertyGetterFunction(propertyToken, functionLine);
            return new PropertyNode(propertyToken, this.finish, getter.key, null, getter.functionNode, null, false, getter.computed);
          case "set":
            setter = propertySetterFunction(propertyToken, functionLine);
            return new PropertyNode(propertyToken, this.finish, setter.key, null, null, setter.functionNode, false, setter.computed);
        } 
      } 
      isIdentifier = true;
      IdentNode identNode = createIdentNode(propertyToken, this.finish, ident).setIsPropertyName();
      if (this.type == TokenType.COLON && ident.equals("__proto__"))
        identNode = identNode.setIsProtoPropertyName(); 
      IdentNode identNode1 = identNode;
    } else {
      isIdentifier = isNonStrictModeIdent();
      propertyName = propertyName();
    } 
    if (generator)
      expectDontAdvance(TokenType.LPAREN); 
    if (this.type == TokenType.LPAREN && isES6()) {
      FunctionNode functionNode = (propertyMethodFunction(propertyName, propertyToken, functionLine, generator, 2097152, computed)).functionNode;
    } else if (isIdentifier && (this.type == TokenType.COMMARIGHT || this.type == TokenType.RBRACE || this.type == TokenType.ASSIGN) && isES6()) {
      IdentNode identNode = createIdentNode(propertyToken, this.finish, ((IdentNode)propertyName).getPropertyName());
      if (this.type == TokenType.ASSIGN) {
        long assignToken = this.token;
        next();
        Expression rhs = assignmentExpression(false);
        propertyValue = verifyAssignment(assignToken, (Expression)identNode, rhs);
      } 
    } else {
      expect(TokenType.COLON);
      this.defaultNames.push(propertyName);
      try {
        propertyValue = assignmentExpression(false);
      } finally {
        this.defaultNames.pop();
      } 
    } 
    return new PropertyNode(propertyToken, this.finish, propertyName, propertyValue, null, null, false, computed);
  }
  
  private PropertyFunction propertyGetterFunction(long getSetToken, int functionLine) {
    return propertyGetterFunction(getSetToken, functionLine, 2097152);
  }
  
  private PropertyFunction propertyGetterFunction(long getSetToken, int functionLine, int flags) {
    Block functionBody;
    boolean computed = (this.type == TokenType.LBRACKET);
    Expression propertyName = propertyName();
    String getterName = (propertyName instanceof PropertyKey) ? ((PropertyKey)propertyName).getPropertyName() : getDefaultValidFunctionName(functionLine, false);
    IdentNode getNameNode = createIdentNode(propertyName.getToken(), this.finish, NameCodec.encode("get " + getterName));
    expect(TokenType.LPAREN);
    expect(TokenType.RPAREN);
    ParserContextFunctionNode functionNode = createParserContextFunctionNode(getNameNode, getSetToken, FunctionNode.Kind.GETTER, functionLine, Collections.emptyList());
    functionNode.setFlag(flags);
    if (computed)
      functionNode.setFlag(1); 
    this.lc.push(functionNode);
    try {
      functionBody = functionBody(functionNode);
    } finally {
      this.lc.pop(functionNode);
    } 
    FunctionNode function = createFunctionNode(functionNode, getSetToken, getNameNode, 
        
        Collections.emptyList(), FunctionNode.Kind.GETTER, functionLine, functionBody);
    return new PropertyFunction(propertyName, function, computed);
  }
  
  private PropertyFunction propertySetterFunction(long getSetToken, int functionLine) {
    return propertySetterFunction(getSetToken, functionLine, 2097152);
  }
  
  private PropertyFunction propertySetterFunction(long getSetToken, int functionLine, int flags) {
    IdentNode argIdent;
    Block functionBody;
    boolean computed = (this.type == TokenType.LBRACKET);
    Expression propertyName = propertyName();
    String setterName = (propertyName instanceof PropertyKey) ? ((PropertyKey)propertyName).getPropertyName() : getDefaultValidFunctionName(functionLine, false);
    IdentNode setNameNode = createIdentNode(propertyName.getToken(), this.finish, NameCodec.encode("set " + setterName));
    expect(TokenType.LPAREN);
    if (isBindingIdentifier()) {
      argIdent = getIdent();
      verifyIdent(argIdent, "setter argument");
    } else {
      argIdent = null;
    } 
    expect(TokenType.RPAREN);
    List<IdentNode> parameters = new ArrayList<>();
    if (argIdent != null)
      parameters.add(argIdent); 
    ParserContextFunctionNode functionNode = createParserContextFunctionNode(setNameNode, getSetToken, FunctionNode.Kind.SETTER, functionLine, parameters);
    functionNode.setFlag(flags);
    if (computed)
      functionNode.setFlag(1); 
    this.lc.push(functionNode);
    try {
      functionBody = functionBody(functionNode);
    } finally {
      this.lc.pop(functionNode);
    } 
    FunctionNode function = createFunctionNode(functionNode, getSetToken, setNameNode, parameters, FunctionNode.Kind.SETTER, functionLine, functionBody);
    return new PropertyFunction(propertyName, function, computed);
  }
  
  private PropertyFunction propertyMethodFunction(Expression key, long methodToken, int methodLine, boolean generator, int flags, boolean computed) {
    String methodName = (key instanceof PropertyKey) ? ((PropertyKey)key).getPropertyName() : getDefaultValidFunctionName(methodLine, false);
    IdentNode methodNameNode = createIdentNode(key.getToken(), this.finish, methodName);
    FunctionNode.Kind functionKind = generator ? FunctionNode.Kind.GENERATOR : FunctionNode.Kind.NORMAL;
    ParserContextFunctionNode functionNode = createParserContextFunctionNode(methodNameNode, methodToken, functionKind, methodLine, (List<IdentNode>)null);
    functionNode.setFlag(flags);
    if (computed)
      functionNode.setFlag(1); 
    this.lc.push(functionNode);
    try {
      List<IdentNode> parameters;
      ParserContextBlockNode parameterBlock = newBlock();
      try {
        expect(TokenType.LPAREN);
        parameters = formalParameterList(generator);
        functionNode.setParameters(parameters);
        expect(TokenType.RPAREN);
      } finally {
        restoreBlock(parameterBlock);
      } 
      Block functionBody = functionBody(functionNode);
      functionBody = maybeWrapBodyInParameterBlock(functionBody, parameterBlock);
      FunctionNode function = createFunctionNode(functionNode, methodToken, methodNameNode, parameters, functionKind, methodLine, functionBody);
      return new PropertyFunction(key, function, computed);
    } finally {
      this.lc.pop(functionNode);
    } 
  }
  
  private static class PropertyFunction {
    final Expression key;
    
    final FunctionNode functionNode;
    
    final boolean computed;
    
    PropertyFunction(Expression key, FunctionNode function, boolean computed) {
      this.key = key;
      this.functionNode = function;
      this.computed = computed;
    }
  }
  
  private Expression leftHandSideExpression() {
    CallNode callNode;
    int callLine = this.line;
    long callToken = this.token;
    Expression lhs = memberExpression();
    if (this.type == TokenType.LPAREN) {
      List<Expression> arguments = optimizeList(argumentList());
      if (lhs instanceof IdentNode) {
        detectSpecialFunction((IdentNode)lhs);
        checkEscapedKeyword((IdentNode)lhs);
      } 
      callNode = new CallNode(callLine, callToken, this.finish, lhs, arguments, false);
    } 
    while (true) {
      IndexNode indexNode;
      AccessNode accessNode;
      List<Expression> list1;
      Expression rhs;
      IdentNode property;
      List<Expression> arguments;
      callLine = this.line;
      callToken = this.token;
      switch (this.type) {
        case LPAREN:
          list1 = optimizeList(argumentList());
          callNode = new CallNode(callLine, callToken, this.finish, (Expression)callNode, list1, false);
          continue;
        case LBRACKET:
          next();
          rhs = expression();
          expect(TokenType.RBRACKET);
          indexNode = new IndexNode(callToken, this.finish, (Expression)callNode, rhs);
          continue;
        case PERIOD:
          next();
          property = getIdentifierName();
          accessNode = new AccessNode(callToken, this.finish, (Expression)indexNode, property.getName());
          continue;
        case TEMPLATE:
        case TEMPLATE_HEAD:
          arguments = templateLiteralArgumentList();
          callNode = new CallNode(callLine, callToken, this.finish, (Expression)accessNode, arguments, false);
          continue;
      } 
      break;
    } 
    return (Expression)callNode;
  }
  
  private Expression newExpression() {
    ArrayList<Expression> arguments;
    long newToken = this.token;
    next();
    if (this.type == TokenType.PERIOD && isES6()) {
      next();
      if (this.type == TokenType.IDENT && "target".equals(getValue())) {
        if (this.lc.getCurrentFunction().isProgram())
          throw error(AbstractParser.message("new.target.in.function", new String[0]), this.token); 
        next();
        markNewTarget(this.lc);
        return (Expression)new IdentNode(newToken, this.finish, "new.target");
      } 
      throw error(AbstractParser.message("expected.target", new String[0]), this.token);
    } 
    int callLine = this.line;
    Expression constructor = memberExpression();
    if (constructor == null)
      return null; 
    if (this.type == TokenType.LPAREN) {
      arguments = argumentList();
    } else {
      arguments = new ArrayList<>();
    } 
    if (!this.env._no_syntax_extensions && this.type == TokenType.LBRACE)
      arguments.add(objectLiteral()); 
    CallNode callNode = new CallNode(callLine, constructor.getToken(), this.finish, constructor, optimizeList(arguments), true);
    return (Expression)new UnaryNode(newToken, (Expression)callNode);
  }
  
  private Expression memberExpression() {
    Expression lhs;
    CallNode callNode;
    boolean isSuper = false;
    switch (this.type) {
      case NEW:
        lhs = newExpression();
        break;
      case FUNCTION:
        lhs = functionExpression(false, false);
        break;
      case CLASS:
        if (isES6()) {
          ClassNode classNode = classExpression(false);
          break;
        } 
      case SUPER:
        if (isES6()) {
          ParserContextFunctionNode currentFunction = getCurrentNonArrowFunction();
          if (currentFunction.isMethod()) {
            long identToken = Token.recast(this.token, TokenType.IDENT);
            next();
            IdentNode identNode = createIdentNode(identToken, this.finish, TokenType.SUPER.getName());
            switch (this.type) {
              case LBRACKET:
              case PERIOD:
                getCurrentNonArrowFunction().setFlag(1048576);
                isSuper = true;
                break;
              case LPAREN:
                if (currentFunction.isSubclassConstructor())
                  identNode = identNode.setIsDirectSuper(); 
                break;
            } 
            throw error(AbstractParser.message("invalid.super", new String[0]), identToken);
          } 
        } 
      default:
        lhs = primaryExpression();
        break;
    } 
    while (true) {
      IndexNode indexNode;
      BaseNode baseNode2;
      AccessNode accessNode;
      BaseNode baseNode1;
      Expression index;
      IdentNode property;
      int callLine;
      List<Expression> arguments;
      long callToken = this.token;
      switch (this.type) {
        case LBRACKET:
          next();
          index = expression();
          expect(TokenType.RBRACKET);
          indexNode = new IndexNode(callToken, this.finish, lhs, index);
          if (isSuper) {
            isSuper = false;
            baseNode2 = ((BaseNode)indexNode).setIsSuper();
          } 
          continue;
        case PERIOD:
          if (baseNode2 == null)
            throw error(AbstractParser.message("expected.operand", new String[] { this.type.getNameOrType() })); 
          next();
          property = getIdentifierName();
          accessNode = new AccessNode(callToken, this.finish, (Expression)baseNode2, property.getName());
          if (isSuper) {
            isSuper = false;
            baseNode1 = ((BaseNode)accessNode).setIsSuper();
          } 
          continue;
        case TEMPLATE:
        case TEMPLATE_HEAD:
          callLine = this.line;
          arguments = templateLiteralArgumentList();
          callNode = new CallNode(callLine, callToken, this.finish, (Expression)baseNode1, arguments, false);
          continue;
      } 
      break;
    } 
    return (Expression)callNode;
  }
  
  private ArrayList<Expression> argumentList() {
    ArrayList<Expression> nodeList = new ArrayList<>();
    next();
    boolean first = true;
    while (this.type != TokenType.RPAREN) {
      UnaryNode unaryNode;
      if (!first) {
        expect(TokenType.COMMARIGHT);
      } else {
        first = false;
      } 
      long spreadToken = 0L;
      if (this.type == TokenType.ELLIPSIS && isES6()) {
        spreadToken = this.token;
        next();
      } 
      Expression expression = assignmentExpression(false);
      if (spreadToken != 0L)
        unaryNode = new UnaryNode(Token.recast(spreadToken, TokenType.SPREAD_ARGUMENT), expression); 
      nodeList.add(unaryNode);
    } 
    expect(TokenType.RPAREN);
    return nodeList;
  }
  
  private static <T> List<T> optimizeList(ArrayList<T> list) {
    return List.copyOf(list);
  }
  
  private Expression functionExpression(boolean isStatement, boolean topLevel) {
    Block functionBody;
    long functionToken = this.token;
    int functionLine = this.line;
    assert this.type == TokenType.FUNCTION;
    next();
    boolean generator = false;
    if (this.type == TokenType.MUL && isES6()) {
      generator = true;
      next();
    } 
    IdentNode name = null;
    if (isBindingIdentifier()) {
      if (this.type == TokenType.YIELD && ((!isStatement && generator) || (isStatement && inGeneratorFunction())))
        expect(TokenType.IDENT); 
      name = getIdent();
      verifyIdent(name, "function name");
    } else if (isStatement) {
      if (this.env._no_syntax_extensions && this.reparsedFunction == null)
        expect(TokenType.IDENT); 
    } 
    boolean isAnonymous = false;
    if (name == null) {
      String tmpName = getDefaultValidFunctionName(functionLine, isStatement);
      name = new IdentNode(functionToken, Token.descPosition(functionToken), tmpName);
      isAnonymous = true;
    } 
    FunctionNode.Kind functionKind = generator ? FunctionNode.Kind.GENERATOR : FunctionNode.Kind.NORMAL;
    List<IdentNode> parameters = Collections.emptyList();
    ParserContextFunctionNode functionNode = createParserContextFunctionNode(name, functionToken, functionKind, functionLine, parameters);
    this.lc.push(functionNode);
    hideDefaultName();
    try {
      ParserContextBlockNode parameterBlock = newBlock();
      try {
        expect(TokenType.LPAREN);
        parameters = formalParameterList(generator);
        functionNode.setParameters(parameters);
        expect(TokenType.RPAREN);
      } finally {
        restoreBlock(parameterBlock);
      } 
      functionBody = maybeWrapBodyInParameterBlock(functionBody(functionNode), parameterBlock);
    } finally {
      this.defaultNames.pop();
      this.lc.pop(functionNode);
    } 
    if (isStatement) {
      if (topLevel || useBlockScope() || (!this.isStrictMode && this.env._function_statement == ScriptEnvironment.FunctionStatementBehavior.ACCEPT)) {
        functionNode.setFlag(2);
      } else {
        if (this.isStrictMode)
          throw error(JSErrorType.SYNTAX_ERROR, AbstractParser.message("strict.no.func.decl.here", new String[0]), functionToken); 
        if (this.env._function_statement == ScriptEnvironment.FunctionStatementBehavior.ERROR)
          throw error(JSErrorType.SYNTAX_ERROR, AbstractParser.message("no.func.decl.here", new String[0]), functionToken); 
        if (this.env._function_statement == ScriptEnvironment.FunctionStatementBehavior.WARNING)
          warning(JSErrorType.SYNTAX_ERROR, AbstractParser.message("no.func.decl.here.warn", new String[0]), functionToken); 
      } 
      if (isArguments(name))
        this.lc.getCurrentFunction().setFlag(256); 
    } 
    if (isAnonymous)
      functionNode.setFlag(1); 
    verifyParameterList(parameters, functionNode);
    FunctionNode function = createFunctionNode(functionNode, functionToken, name, parameters, functionKind, functionLine, functionBody);
    if (isStatement) {
      if (isAnonymous) {
        appendStatement((Statement)new ExpressionStatement(functionLine, functionToken, this.finish, (Expression)function));
        return (Expression)function;
      } 
      int varFlags = (topLevel || !useBlockScope()) ? 0 : 1;
      VarNode varNode = new VarNode(functionLine, functionToken, this.finish, name, (Expression)function, varFlags);
      if (topLevel) {
        this.functionDeclarations.add(varNode);
      } else if (useBlockScope()) {
        prependStatement((Statement)varNode);
      } else {
        appendStatement((Statement)varNode);
      } 
    } 
    return (Expression)function;
  }
  
  private void verifyParameterList(List<IdentNode> parameters, ParserContextFunctionNode functionNode) {
    IdentNode duplicateParameter = functionNode.getDuplicateParameterBinding();
    if (duplicateParameter != null) {
      if (functionNode.isStrict() || functionNode.getKind() == FunctionNode.Kind.ARROW || !functionNode.isSimpleParameterList())
        throw error(AbstractParser.message("strict.param.redefinition", new String[] { duplicateParameter.getName() }), duplicateParameter.getToken()); 
      int arity = parameters.size();
      HashSet<String> parametersSet = new HashSet<>(arity);
      for (int i = arity - 1; i >= 0; i--) {
        IdentNode parameter = parameters.get(i);
        String parameterName = parameter.getName();
        if (parametersSet.contains(parameterName)) {
          parameterName = functionNode.uniqueName(parameterName);
          long parameterToken = parameter.getToken();
          parameters.set(i, new IdentNode(parameterToken, Token.descPosition(parameterToken), functionNode.uniqueName(parameterName)));
        } 
        parametersSet.add(parameterName);
      } 
    } 
  }
  
  private static Block maybeWrapBodyInParameterBlock(Block functionBody, ParserContextBlockNode parameterBlock) {
    assert functionBody.isFunctionBody();
    if (!parameterBlock.getStatements().isEmpty()) {
      parameterBlock.appendStatement((Statement)new BlockStatement(functionBody));
      return new Block(parameterBlock.getToken(), functionBody.getFinish(), (functionBody.getFlags() | 0x40) & 0xFFFFFFDF, parameterBlock.getStatements());
    } 
    return functionBody;
  }
  
  private String getDefaultValidFunctionName(int functionLine, boolean isStatement) {
    String defaultFunctionName = getDefaultFunctionName();
    if (isValidIdentifier(defaultFunctionName)) {
      if (isStatement)
        return CompilerConstants.ANON_FUNCTION_PREFIX.symbolName() + CompilerConstants.ANON_FUNCTION_PREFIX.symbolName(); 
      return defaultFunctionName;
    } 
    return CompilerConstants.ANON_FUNCTION_PREFIX.symbolName() + CompilerConstants.ANON_FUNCTION_PREFIX.symbolName();
  }
  
  private static boolean isValidIdentifier(String name) {
    if (name == null || name.isEmpty())
      return false; 
    if (!Character.isJavaIdentifierStart(name.charAt(0)))
      return false; 
    for (int i = 1; i < name.length(); i++) {
      if (!Character.isJavaIdentifierPart(name.charAt(i)))
        return false; 
    } 
    return true;
  }
  
  private String getDefaultFunctionName() {
    if (!this.defaultNames.isEmpty()) {
      Object nameExpr = this.defaultNames.peek();
      if (nameExpr instanceof PropertyKey) {
        markDefaultNameUsed();
        return ((PropertyKey)nameExpr).getPropertyName();
      } 
      if (nameExpr instanceof AccessNode) {
        markDefaultNameUsed();
        return ((AccessNode)nameExpr).getProperty();
      } 
    } 
    return null;
  }
  
  private void markDefaultNameUsed() {
    this.defaultNames.pop();
    hideDefaultName();
  }
  
  private void hideDefaultName() {
    this.defaultNames.push("");
  }
  
  private List<IdentNode> formalParameterList(boolean yield) {
    return formalParameterList(TokenType.RPAREN, yield);
  }
  
  private List<IdentNode> formalParameterList(TokenType endType, boolean yield) {
    ArrayList<IdentNode> parameters = new ArrayList<>();
    boolean first = true;
    while (this.type != endType) {
      IdentNode ident;
      if (!first) {
        expect(TokenType.COMMARIGHT);
      } else {
        first = false;
      } 
      boolean restParameter = false;
      if (this.type == TokenType.ELLIPSIS && isES6()) {
        next();
        restParameter = true;
      } 
      if (this.type == TokenType.YIELD && yield)
        expect(TokenType.IDENT); 
      long paramToken = this.token;
      int paramLine = this.line;
      String contextString = "function parameter";
      if (isBindingIdentifier() || restParameter || !isES6()) {
        ident = bindingIdentifier("function parameter");
        if (restParameter) {
          ident = ident.setIsRestParameter();
          expectDontAdvance(endType);
          parameters.add(ident);
          break;
        } 
        if (this.type == TokenType.ASSIGN && isES6()) {
          next();
          ident = ident.setIsDefaultParameter();
          if (this.type == TokenType.YIELD && yield)
            expect(TokenType.IDENT); 
          Expression initializer = assignmentExpression(false);
          ParserContextFunctionNode parserContextFunctionNode = this.lc.getCurrentFunction();
          if (parserContextFunctionNode != null)
            if (this.env._parse_only) {
              BinaryNode assignment = new BinaryNode(Token.recast(paramToken, TokenType.ASSIGN), (Expression)ident, initializer);
              parserContextFunctionNode.addParameterExpression(ident, (Expression)assignment);
            } else {
              BinaryNode test = new BinaryNode(Token.recast(paramToken, TokenType.EQ_STRICT), (Expression)ident, (Expression)newUndefinedLiteral(paramToken, this.finish));
              TernaryNode value = new TernaryNode(Token.recast(paramToken, TokenType.TERNARY), (Expression)test, new JoinPredecessorExpression(initializer), new JoinPredecessorExpression((Expression)ident));
              BinaryNode assignment = new BinaryNode(Token.recast(paramToken, TokenType.ASSIGN), (Expression)ident, (Expression)value);
              this.lc.getFunctionBody(parserContextFunctionNode).appendStatement((Statement)new ExpressionStatement(paramLine, assignment.getToken(), assignment.getFinish(), (Expression)assignment));
            }  
        } 
        ParserContextFunctionNode currentFunction = this.lc.getCurrentFunction();
        if (currentFunction != null) {
          currentFunction.addParameterBinding(ident);
          if (ident.isRestParameter() || ident.isDefaultParameter())
            currentFunction.setSimpleParameterList(false); 
        } 
      } else {
        TernaryNode ternaryNode;
        Expression pattern = bindingPattern();
        ident = createIdentNode(paramToken, pattern.getFinish(), String.format("arguments[%d]", new Object[] { Integer.valueOf(parameters.size()) })).setIsDestructuredParameter();
        verifyDestructuringParameterBindingPattern(pattern, paramToken, paramLine, "function parameter");
        IdentNode identNode = ident;
        if (this.type == TokenType.ASSIGN) {
          next();
          ident = ident.setIsDefaultParameter();
          Expression initializer = assignmentExpression(false);
          if (this.env._parse_only) {
            Expression expression = initializer;
          } else {
            BinaryNode test = new BinaryNode(Token.recast(paramToken, TokenType.EQ_STRICT), (Expression)ident, (Expression)newUndefinedLiteral(paramToken, this.finish));
            ternaryNode = new TernaryNode(Token.recast(paramToken, TokenType.TERNARY), (Expression)test, new JoinPredecessorExpression(initializer), new JoinPredecessorExpression((Expression)ident));
          } 
        } 
        ParserContextFunctionNode currentFunction = this.lc.getCurrentFunction();
        if (currentFunction != null) {
          BinaryNode assignment = new BinaryNode(Token.recast(paramToken, TokenType.ASSIGN), pattern, (Expression)ternaryNode);
          if (this.env._parse_only) {
            if (ident.isDefaultParameter()) {
              currentFunction.addParameterExpression(ident, (Expression)assignment);
            } else {
              currentFunction.addParameterExpression(ident, pattern);
            } 
          } else {
            this.lc.getFunctionBody(currentFunction).appendStatement((Statement)new ExpressionStatement(paramLine, assignment.getToken(), assignment.getFinish(), (Expression)assignment));
          } 
        } 
      } 
      parameters.add(ident);
    } 
    parameters.trimToSize();
    return parameters;
  }
  
  private void verifyDestructuringParameterBindingPattern(Expression pattern, long paramToken, int paramLine, String contextString) {
    verifyDestructuringBindingPattern(pattern, identNode -> {
          verifyIdent(identNode, contextString);
          ParserContextFunctionNode currentFunction = this.lc.getCurrentFunction();
          if (currentFunction != null) {
            if (!this.env._parse_only)
              this.lc.getFunctionBody(currentFunction).appendStatement((Statement)new VarNode(paramLine, Token.recast(paramToken, TokenType.VAR), pattern.getFinish(), identNode, null)); 
            currentFunction.addParameterBinding(identNode);
            currentFunction.setSimpleParameterList(false);
          } 
        });
  }
  
  private Block functionBody(ParserContextFunctionNode functionNode) {
    boolean parseBody;
    ParserContextBlockNode body = null;
    long bodyToken = this.token;
    int bodyFinish = 0;
    Object endParserState = null;
    try {
      body = newBlock();
      if (this.env._debug_scopes)
        markEval(this.lc); 
      assert functionNode != null;
      int functionId = functionNode.getId();
      parseBody = (this.reparsedFunction == null || functionId <= this.reparsedFunction.getFunctionNodeId());
      if ((!this.env._no_syntax_extensions || functionNode.getKind() == FunctionNode.Kind.ARROW) && this.type != TokenType.LBRACE) {
        Expression expr = assignmentExpression(false);
        long lastToken = this.previousToken;
        functionNode.setLastToken(this.previousToken);
        assert this.lc.getCurrentBlock() == this.lc.getFunctionBody(functionNode);
        int lastFinish = Token.descPosition(lastToken) + ((Token.descType(lastToken) == TokenType.EOL) ? 0 : Token.descLength(lastToken));
        if (parseBody) {
          functionNode.setFlag(67108864);
          ReturnNode returnNode = new ReturnNode(functionNode.getLineNumber(), expr.getToken(), lastFinish, expr);
          appendStatement((Statement)returnNode);
        } 
      } else {
        expectDontAdvance(TokenType.LBRACE);
        if (parseBody || !skipFunctionBody(functionNode)) {
          next();
          List<Statement> prevFunctionDecls = this.functionDeclarations;
          this.functionDeclarations = new ArrayList<>();
          try {
            sourceElements(0);
            addFunctionDeclarations(functionNode);
          } finally {
            this.functionDeclarations = prevFunctionDecls;
          } 
          if (parseBody)
            endParserState = new ParserState(Token.descPosition(this.token), this.line, this.linePosition); 
        } 
        bodyFinish = this.finish;
        functionNode.setLastToken(this.token);
        expect(TokenType.RBRACE);
      } 
    } finally {
      restoreBlock(body);
    } 
    if (parseBody) {
      functionNode.setEndParserState(endParserState);
    } else if (!body.getStatements().isEmpty()) {
      body.setStatements(Collections.emptyList());
    } 
    if (this.reparsedFunction != null) {
      RecompilableScriptFunctionData data = this.reparsedFunction.getScriptFunctionData(functionNode.getId());
      if (data != null) {
        functionNode.setFlag(data.getFunctionFlags());
        if (functionNode.hasNestedEval()) {
          assert functionNode.hasScopeBlock();
          body.setFlag(1);
        } 
      } 
    } 
    Block functionBody = new Block(bodyToken, bodyFinish, body.getFlags() | 0x20, body.getStatements());
    return functionBody;
  }
  
  private boolean skipFunctionBody(ParserContextFunctionNode functionNode) {
    if (this.reparsedFunction == null)
      return false; 
    RecompilableScriptFunctionData data = this.reparsedFunction.getScriptFunctionData(functionNode.getId());
    if (data == null)
      return false; 
    ParserState parserState = (ParserState)data.getEndParserState();
    assert parserState != null;
    if (this.k < this.stream.last() && this.start < parserState.position && parserState.position <= Token.descPosition(this.stream.get(this.stream.last())))
      for (; this.k < this.stream.last(); this.k++) {
        long nextToken = this.stream.get(this.k + 1);
        if (Token.descPosition(nextToken) == parserState.position && Token.descType(nextToken) == TokenType.RBRACE) {
          this.token = this.stream.get(this.k);
          this.type = Token.descType(this.token);
          next();
          assert this.type == TokenType.RBRACE && this.start == parserState.position;
          return true;
        } 
      }  
    this.stream.reset();
    this.lexer = parserState.createLexer(this.source, this.lexer, this.stream, (this.scripting && !this.env._no_syntax_extensions), isES6());
    this.line = parserState.line;
    this.linePosition = parserState.linePosition;
    this.type = TokenType.SEMICOLON;
    scanFirstToken();
    return true;
  }
  
  private static class ParserState implements Serializable {
    private final int position;
    
    private final int line;
    
    private final int linePosition;
    
    private static final long serialVersionUID = -2382565130754093694L;
    
    ParserState(int position, int line, int linePosition) {
      this.position = position;
      this.line = line;
      this.linePosition = linePosition;
    }
    
    Lexer createLexer(Source source, Lexer lexer, TokenStream stream, boolean scripting, boolean es6) {
      Lexer newLexer = new Lexer(source, this.position, lexer.limit - this.position, stream, scripting, es6, true);
      newLexer.restoreState(new Lexer.State(this.position, 2147483647, this.line, -1, this.linePosition, TokenType.SEMICOLON));
      return newLexer;
    }
  }
  
  private void printAST(FunctionNode functionNode) {
    if (functionNode.getDebugFlag(4))
      this.env.getErr().println(new ASTWriter((Node)functionNode)); 
    if (functionNode.getDebugFlag(1))
      this.env.getErr().println(new PrintVisitor((Node)functionNode, true, false)); 
  }
  
  private void addFunctionDeclarations(ParserContextFunctionNode functionNode) {
    VarNode lastDecl = null;
    for (int i = this.functionDeclarations.size() - 1; i >= 0; i--) {
      VarNode varNode;
      Statement decl = this.functionDeclarations.get(i);
      if (lastDecl == null && decl instanceof VarNode) {
        varNode = lastDecl = ((VarNode)decl).setFlag(4);
        functionNode.setFlag(1024);
      } 
      prependStatement((Statement)varNode);
    } 
  }
  
  private RuntimeNode referenceError(Expression lhs, Expression rhs, boolean earlyError) {
    if (this.env._parse_only || earlyError)
      throw error(JSErrorType.REFERENCE_ERROR, AbstractParser.message("invalid.lvalue", new String[0]), lhs.getToken()); 
    ArrayList<Expression> args = new ArrayList<>();
    args.add(lhs);
    if (rhs == null) {
      args.add(LiteralNode.newInstance(lhs.getToken(), lhs.getFinish()));
    } else {
      args.add(rhs);
    } 
    args.add(LiteralNode.newInstance(lhs.getToken(), lhs.getFinish(), lhs.toString()));
    return new RuntimeNode(lhs.getToken(), lhs.getFinish(), RuntimeNode.Request.REFERENCE_ERROR, args);
  }
  
  private Expression unaryExpression() {
    TokenType opType;
    Expression expr, expression1;
    TokenType tokenType1;
    Expression lhs;
    long unaryToken = this.token;
    switch (this.type) {
      case ADD:
      case SUB:
        opType = this.type;
        next();
        expression1 = unaryExpression();
        return (Expression)new UnaryNode(Token.recast(unaryToken, (opType == TokenType.ADD) ? TokenType.POS : TokenType.NEG), expression1);
      case DELETE:
      case VOID:
      case TYPEOF:
      case BIT_NOT:
      case NOT:
        next();
        expr = unaryExpression();
        return (Expression)new UnaryNode(unaryToken, expr);
      case INCPREFIX:
      case DECPREFIX:
        tokenType1 = this.type;
        next();
        lhs = leftHandSideExpression();
        if (lhs == null)
          throw error(AbstractParser.message("expected.lvalue", new String[] { this.type.getNameOrType() })); 
        return verifyIncDecExpression(unaryToken, tokenType1, lhs, false);
    } 
    Expression expression = leftHandSideExpression();
    if (this.last != TokenType.EOL) {
      long opToken;
      TokenType tokenType;
      switch (this.type) {
        case INCPREFIX:
        case DECPREFIX:
          opToken = this.token;
          tokenType = this.type;
          if (expression == null)
            throw error(AbstractParser.message("expected.lvalue", new String[] { this.type.getNameOrType() })); 
          next();
          return verifyIncDecExpression(opToken, tokenType, expression, true);
      } 
    } 
    if (expression == null)
      throw error(AbstractParser.message("expected.operand", new String[] { this.type.getNameOrType() })); 
    return expression;
  }
  
  private Expression verifyIncDecExpression(long unaryToken, TokenType opType, Expression lhs, boolean isPostfix) {
    assert lhs != null;
    if (!(lhs instanceof AccessNode) && !(lhs instanceof IndexNode) && !(lhs instanceof IdentNode))
      return (Expression)referenceError(lhs, (Expression)null, this.env._early_lvalue_error); 
    if (lhs instanceof IdentNode) {
      if (!checkIdentLValue((IdentNode)lhs))
        return (Expression)referenceError(lhs, (Expression)null, false); 
      verifyIdent((IdentNode)lhs, "operand for " + opType.getName() + " operator");
    } 
    return (Expression)incDecExpression(unaryToken, opType, lhs, isPostfix);
  }
  
  protected Expression expression() {
    BinaryNode binaryNode;
    Expression assignmentExpression = assignmentExpression(false);
    while (this.type == TokenType.COMMARIGHT) {
      IdentNode identNode;
      long commaToken = this.token;
      next();
      boolean rhsRestParameter = false;
      if (this.type == TokenType.ELLIPSIS && isES6())
        if (isRestParameterEndOfArrowFunctionParameterList()) {
          next();
          rhsRestParameter = true;
        }  
      Expression rhs = assignmentExpression(false);
      if (rhsRestParameter) {
        identNode = ((IdentNode)rhs).setIsRestParameter();
        assert this.type == TokenType.RPAREN;
      } 
      binaryNode = new BinaryNode(commaToken, assignmentExpression, (Expression)identNode);
    } 
    return (Expression)binaryNode;
  }
  
  private Expression expression(int minPrecedence, boolean noIn) {
    return expression(unaryExpression(), minPrecedence, noIn);
  }
  
  private JoinPredecessorExpression joinPredecessorExpression() {
    return new JoinPredecessorExpression(expression());
  }
  
  private Expression expression(Expression exprLhs, int minPrecedence, boolean noIn) {
    int precedence = this.type.getPrecedence();
    Expression expression = exprLhs;
    while (this.type.isOperator(noIn) && precedence >= minPrecedence) {
      TernaryNode ternaryNode;
      long op = this.token;
      if (this.type == TokenType.TERNARY) {
        next();
        Expression trueExpr = expression(unaryExpression(), TokenType.ASSIGN.getPrecedence(), false);
        expect(TokenType.COLON);
        Expression falseExpr = expression(unaryExpression(), TokenType.ASSIGN.getPrecedence(), noIn);
        ternaryNode = new TernaryNode(op, expression, new JoinPredecessorExpression(trueExpr), new JoinPredecessorExpression(falseExpr));
      } else {
        Expression rhs;
        next();
        boolean isAssign = (Token.descType(op) == TokenType.ASSIGN);
        if (isAssign)
          this.defaultNames.push(ternaryNode); 
        try {
          rhs = unaryExpression();
          int nextPrecedence = this.type.getPrecedence();
          while (this.type.isOperator(noIn) && (nextPrecedence > precedence || (nextPrecedence == precedence && 
            
            !this.type.isLeftAssociative()))) {
            rhs = expression(rhs, nextPrecedence, noIn);
            nextPrecedence = this.type.getPrecedence();
          } 
        } finally {
          if (isAssign)
            this.defaultNames.pop(); 
        } 
        expression = verifyAssignment(op, (Expression)ternaryNode, rhs);
      } 
      precedence = this.type.getPrecedence();
    } 
    return expression;
  }
  
  protected Expression assignmentExpression(boolean noIn) {
    if (this.type == TokenType.YIELD && inGeneratorFunction() && isES6())
      return yieldExpression(noIn); 
    long startToken = this.token;
    int startLine = this.line;
    Expression exprLhs = conditionalExpression(noIn);
    if (this.type == TokenType.ARROW && isES6() && 
      checkNoLineTerminator()) {
      Expression paramListExpr;
      if (exprLhs instanceof ExpressionList) {
        paramListExpr = ((ExpressionList)exprLhs).getExpressions().isEmpty() ? null : ((ExpressionList)exprLhs).getExpressions().get(0);
      } else {
        paramListExpr = exprLhs;
      } 
      return arrowFunction(startToken, startLine, paramListExpr);
    } 
    assert !(exprLhs instanceof ExpressionList);
    if (isAssignmentOperator(this.type)) {
      boolean isAssign = (this.type == TokenType.ASSIGN);
      if (isAssign)
        this.defaultNames.push(exprLhs); 
      try {
        long assignToken = this.token;
        next();
        Expression exprRhs = assignmentExpression(noIn);
        return verifyAssignment(assignToken, exprLhs, exprRhs);
      } finally {
        if (isAssign)
          this.defaultNames.pop(); 
      } 
    } 
    return exprLhs;
  }
  
  private static boolean isAssignmentOperator(TokenType type) {
    switch (type) {
      case ASSIGN:
      case ASSIGN_ADD:
      case ASSIGN_BIT_AND:
      case ASSIGN_BIT_OR:
      case ASSIGN_BIT_XOR:
      case ASSIGN_DIV:
      case ASSIGN_MOD:
      case ASSIGN_MUL:
      case ASSIGN_SAR:
      case ASSIGN_SHL:
      case ASSIGN_SHR:
      case ASSIGN_SUB:
        return true;
    } 
    return false;
  }
  
  private Expression conditionalExpression(boolean noIn) {
    return expression(TokenType.TERNARY.getPrecedence(), noIn);
  }
  
  private Expression arrowFunction(long startToken, int functionLine, Expression paramListExpr) {
    assert this.type != TokenType.ARROW || checkNoLineTerminator();
    expect(TokenType.ARROW);
    long functionToken = Token.recast(startToken, TokenType.ARROW);
    IdentNode name = new IdentNode(functionToken, Token.descPosition(functionToken), NameCodec.encode("=>:") + NameCodec.encode("=>:"));
    ParserContextFunctionNode functionNode = createParserContextFunctionNode(name, functionToken, FunctionNode.Kind.ARROW, functionLine, (List<IdentNode>)null);
    functionNode.setFlag(1);
    this.lc.push(functionNode);
    try {
      List<IdentNode> parameters;
      ParserContextBlockNode parameterBlock = newBlock();
      try {
        parameters = convertArrowFunctionParameterList(paramListExpr, functionLine);
        functionNode.setParameters(parameters);
        if (!functionNode.isSimpleParameterList())
          markEvalInArrowParameterList(parameterBlock); 
      } finally {
        restoreBlock(parameterBlock);
      } 
      Block functionBody = functionBody(functionNode);
      functionBody = maybeWrapBodyInParameterBlock(functionBody, parameterBlock);
      verifyParameterList(parameters, functionNode);
      return (Expression)createFunctionNode(functionNode, functionToken, name, parameters, FunctionNode.Kind.ARROW, functionLine, functionBody);
    } finally {
      this.lc.pop(functionNode);
    } 
  }
  
  private void markEvalInArrowParameterList(ParserContextBlockNode parameterBlock) {
    Iterator<ParserContextFunctionNode> iter = this.lc.getFunctions();
    final ParserContextFunctionNode current = iter.next();
    ParserContextFunctionNode parent = iter.next();
    if (parent.getFlag(32) != 0)
      for (Statement st : parameterBlock.getStatements()) {
        st.accept(new NodeVisitor<LexicalContext>(new LexicalContext()) {
              public boolean enterCallNode(CallNode callNode) {
                if (callNode.getFunction() instanceof IdentNode && ((IdentNode)callNode.getFunction()).getName().equals("eval"))
                  current.setFlag(32); 
                return true;
              }
            });
      }  
  }
  
  private List<IdentNode> convertArrowFunctionParameterList(Expression paramListExpr, int functionLine) {
    List<IdentNode> parameters;
    if (paramListExpr == null) {
      parameters = Collections.emptyList();
    } else if (paramListExpr instanceof IdentNode || paramListExpr.isTokenType(TokenType.ASSIGN) || isDestructuringLhs(paramListExpr)) {
      parameters = Collections.singletonList(verifyArrowParameter(paramListExpr, 0, functionLine));
    } else if (paramListExpr instanceof BinaryNode && Token.descType(paramListExpr.getToken()) == TokenType.COMMARIGHT) {
      parameters = new ArrayList<>();
      Expression car = paramListExpr;
      do {
        Expression cdr = ((BinaryNode)car).rhs();
        parameters.add(0, verifyArrowParameter(cdr, parameters.size(), functionLine));
        car = ((BinaryNode)car).lhs();
      } while (car instanceof BinaryNode && Token.descType(car.getToken()) == TokenType.COMMARIGHT);
      parameters.add(0, verifyArrowParameter(car, parameters.size(), functionLine));
    } else {
      throw error(AbstractParser.message("expected.arrow.parameter", new String[0]), paramListExpr.getToken());
    } 
    return parameters;
  }
  
  private IdentNode verifyArrowParameter(Expression param, int index, int paramLine) {
    String contextString = "function parameter";
    if (param instanceof IdentNode) {
      IdentNode ident = (IdentNode)param;
      verifyIdent(ident, "function parameter");
      ParserContextFunctionNode currentFunction = this.lc.getCurrentFunction();
      if (currentFunction != null)
        currentFunction.addParameterBinding(ident); 
      return ident;
    } 
    if (param.isTokenType(TokenType.ASSIGN)) {
      Expression lhs = ((BinaryNode)param).lhs();
      long paramToken = lhs.getToken();
      Expression initializer = ((BinaryNode)param).rhs();
      if (lhs instanceof IdentNode) {
        IdentNode ident = (IdentNode)lhs;
        ParserContextFunctionNode currentFunction = this.lc.getCurrentFunction();
        if (currentFunction != null) {
          if (this.env._parse_only) {
            currentFunction.addParameterExpression(ident, param);
          } else {
            BinaryNode test = new BinaryNode(Token.recast(paramToken, TokenType.EQ_STRICT), (Expression)ident, (Expression)newUndefinedLiteral(paramToken, this.finish));
            TernaryNode value = new TernaryNode(Token.recast(paramToken, TokenType.TERNARY), (Expression)test, new JoinPredecessorExpression(initializer), new JoinPredecessorExpression((Expression)ident));
            BinaryNode assignment = new BinaryNode(Token.recast(paramToken, TokenType.ASSIGN), (Expression)ident, (Expression)value);
            this.lc.getFunctionBody(currentFunction).appendStatement((Statement)new ExpressionStatement(paramLine, assignment.getToken(), assignment.getFinish(), (Expression)assignment));
          } 
          currentFunction.addParameterBinding(ident);
          currentFunction.setSimpleParameterList(false);
        } 
        return ident;
      } 
      if (isDestructuringLhs(lhs)) {
        IdentNode ident = createIdentNode(paramToken, param.getFinish(), String.format("arguments[%d]", new Object[] { Integer.valueOf(index) })).setIsDestructuredParameter().setIsDefaultParameter();
        verifyDestructuringParameterBindingPattern(param, paramToken, paramLine, "function parameter");
        ParserContextFunctionNode currentFunction = this.lc.getCurrentFunction();
        if (currentFunction != null)
          if (this.env._parse_only) {
            currentFunction.addParameterExpression(ident, param);
          } else {
            BinaryNode test = new BinaryNode(Token.recast(paramToken, TokenType.EQ_STRICT), (Expression)ident, (Expression)newUndefinedLiteral(paramToken, this.finish));
            TernaryNode value = new TernaryNode(Token.recast(paramToken, TokenType.TERNARY), (Expression)test, new JoinPredecessorExpression(initializer), new JoinPredecessorExpression((Expression)ident));
            BinaryNode assignment = new BinaryNode(Token.recast(paramToken, TokenType.ASSIGN), param, (Expression)value);
            this.lc.getFunctionBody(currentFunction).appendStatement((Statement)new ExpressionStatement(paramLine, assignment.getToken(), assignment.getFinish(), (Expression)assignment));
          }  
        return ident;
      } 
    } else if (isDestructuringLhs(param)) {
      long paramToken = param.getToken();
      IdentNode ident = createIdentNode(paramToken, param.getFinish(), String.format("arguments[%d]", new Object[] { Integer.valueOf(index) })).setIsDestructuredParameter();
      verifyDestructuringParameterBindingPattern(param, paramToken, paramLine, "function parameter");
      ParserContextFunctionNode currentFunction = this.lc.getCurrentFunction();
      if (currentFunction != null)
        if (this.env._parse_only) {
          currentFunction.addParameterExpression(ident, param);
        } else {
          BinaryNode assignment = new BinaryNode(Token.recast(paramToken, TokenType.ASSIGN), param, (Expression)ident);
          this.lc.getFunctionBody(currentFunction).appendStatement((Statement)new ExpressionStatement(paramLine, assignment.getToken(), assignment.getFinish(), (Expression)assignment));
        }  
      return ident;
    } 
    throw error(AbstractParser.message("invalid.arrow.parameter", new String[0]), param.getToken());
  }
  
  private boolean checkNoLineTerminator() {
    assert this.type == TokenType.ARROW;
    if (this.last == TokenType.RPAREN)
      return true; 
    if (this.last == TokenType.IDENT)
      return true; 
    for (int i = this.k - 1; i >= 0; i--) {
      TokenType t = T(i);
      switch (t) {
        case RPAREN:
        case IDENT:
          return true;
        case EOL:
          return false;
        case COMMENT:
          break;
        default:
          return (t.getKind() == TokenKind.FUTURESTRICT);
      } 
    } 
    return false;
  }
  
  private boolean isRestParameterEndOfArrowFunctionParameterList() {
    assert this.type == TokenType.ELLIPSIS;
    int i = 1;
    while (true) {
      TokenType t = T(this.k + i++);
      if (t == TokenType.IDENT)
        break; 
      if (t != TokenType.EOL && t != TokenType.COMMENT)
        return false; 
    } 
    while (true) {
      TokenType t = T(this.k + i++);
      if (t == TokenType.RPAREN)
        break; 
      if (t != TokenType.EOL && t != TokenType.COMMENT)
        return false; 
    } 
    while (true) {
      TokenType t = T(this.k + i++);
      if (t == TokenType.ARROW)
        break; 
      if (t != TokenType.COMMENT)
        return false; 
    } 
    return true;
  }
  
  private void endOfLine() {
    switch (this.type) {
      case EOL:
      case SEMICOLON:
        next();
      case EOF:
      case RBRACE:
      case RPAREN:
      case RBRACKET:
        return;
    } 
    if (this.last != TokenType.EOL)
      expect(TokenType.SEMICOLON); 
  }
  
  private Expression templateLiteral() {
    assert this.type == TokenType.TEMPLATE || this.type == TokenType.TEMPLATE_HEAD;
    boolean noSubstitutionTemplate = (this.type == TokenType.TEMPLATE);
    long lastLiteralToken = this.token;
    LiteralNode<?> literal = getLiteral();
    if (noSubstitutionTemplate)
      return (Expression)literal; 
    if (this.env._parse_only) {
      List<Expression> exprs = new ArrayList<>();
      exprs.add(literal);
      while (true) {
        Expression expression = expression();
        if (this.type != TokenType.TEMPLATE_MIDDLE && this.type != TokenType.TEMPLATE_TAIL)
          throw error(AbstractParser.message("unterminated.template.expression", new String[0]), this.token); 
        exprs.add(expression);
        TokenType lastLiteralType = this.type;
        literal = getLiteral();
        exprs.add(literal);
        if (lastLiteralType != TokenType.TEMPLATE_MIDDLE)
          return (Expression)new TemplateLiteral(exprs); 
      } 
    } 
    LiteralNode<?> literalNode1 = literal;
    while (true) {
      Expression expression = expression();
      if (this.type != TokenType.TEMPLATE_MIDDLE && this.type != TokenType.TEMPLATE_TAIL)
        throw error(AbstractParser.message("unterminated.template.expression", new String[0]), this.token); 
      BinaryNode binaryNode = new BinaryNode(Token.recast(lastLiteralToken, TokenType.ADD), (Expression)literalNode1, expression);
      TokenType lastLiteralType = this.type;
      lastLiteralToken = this.token;
      literal = getLiteral();
      binaryNode = new BinaryNode(Token.recast(lastLiteralToken, TokenType.ADD), (Expression)binaryNode, (Expression)literal);
      if (lastLiteralType != TokenType.TEMPLATE_MIDDLE)
        return (Expression)binaryNode; 
    } 
  }
  
  private List<Expression> templateLiteralArgumentList() {
    assert this.type == TokenType.TEMPLATE || this.type == TokenType.TEMPLATE_HEAD;
    ArrayList<Expression> argumentList = new ArrayList<>();
    ArrayList<Expression> rawStrings = new ArrayList<>();
    ArrayList<Expression> cookedStrings = new ArrayList<>();
    argumentList.add(null);
    long templateToken = this.token;
    boolean hasSubstitutions = (this.type == TokenType.TEMPLATE_HEAD);
    addTemplateLiteralString(rawStrings, cookedStrings);
    if (hasSubstitutions) {
      TokenType lastLiteralType;
      do {
        Expression expression = expression();
        if (this.type != TokenType.TEMPLATE_MIDDLE && this.type != TokenType.TEMPLATE_TAIL)
          throw error(AbstractParser.message("unterminated.template.expression", new String[0]), this.token); 
        argumentList.add(expression);
        lastLiteralType = this.type;
        addTemplateLiteralString(rawStrings, cookedStrings);
      } while (lastLiteralType == TokenType.TEMPLATE_MIDDLE);
    } 
    LiteralNode<Expression[]> rawStringArray = LiteralNode.newInstance(templateToken, this.finish, rawStrings);
    LiteralNode<Expression[]> cookedStringArray = LiteralNode.newInstance(templateToken, this.finish, cookedStrings);
    if (!this.env._parse_only) {
      RuntimeNode templateObject = new RuntimeNode(templateToken, this.finish, RuntimeNode.Request.GET_TEMPLATE_OBJECT, new Expression[] { (Expression)rawStringArray, (Expression)cookedStringArray });
      argumentList.set(0, templateObject);
    } else {
      argumentList.set(0, rawStringArray);
    } 
    return optimizeList(argumentList);
  }
  
  private void addTemplateLiteralString(ArrayList<Expression> rawStrings, ArrayList<Expression> cookedStrings) {
    long stringToken = this.token;
    String rawString = this.lexer.valueOfRawString(stringToken);
    String cookedString = (String)getValue();
    next();
    rawStrings.add(LiteralNode.newInstance(stringToken, this.finish, rawString));
    cookedStrings.add(LiteralNode.newInstance(stringToken, this.finish, cookedString));
  }
  
  private FunctionNode module(String moduleName) {
    boolean oldStrictMode = this.isStrictMode;
    try {
      this.isStrictMode = true;
      int functionStart = Math.min(Token.descPosition(Token.withDelimiter(this.token)), this.finish);
      long functionToken = Token.toDesc(TokenType.FUNCTION, functionStart, this.source.getLength() - functionStart);
      int functionLine = this.line;
      IdentNode ident = new IdentNode(functionToken, Token.descPosition(functionToken), moduleName);
      ParserContextFunctionNode script = createParserContextFunctionNode(ident, functionToken, FunctionNode.Kind.MODULE, functionLine, 
          
          Collections.emptyList());
      this.lc.push(script);
      ParserContextModuleNode module = new ParserContextModuleNode(moduleName);
      this.lc.push(module);
      ParserContextBlockNode body = newBlock();
      this.functionDeclarations = new ArrayList<>();
      moduleBody();
      addFunctionDeclarations(script);
      this.functionDeclarations = null;
      restoreBlock(body);
      body.setFlag(1);
      Block programBody = new Block(functionToken, this.finish, body.getFlags() | 0x10 | 0x20, body.getStatements());
      this.lc.pop(module);
      this.lc.pop(script);
      script.setLastToken(this.token);
      expect(TokenType.EOF);
      script.setModule(module.createModule());
      return createFunctionNode(script, functionToken, ident, Collections.emptyList(), FunctionNode.Kind.MODULE, functionLine, programBody);
    } finally {
      this.isStrictMode = oldStrictMode;
    } 
  }
  
  private void moduleBody() {
    while (this.type != TokenType.EOF) {
      switch (this.type) {
        case IMPORT:
          importDeclaration();
          continue;
        case EXPORT:
          exportDeclaration();
          continue;
      } 
      statement(true, 0, false, false);
    } 
  }
  
  private void importDeclaration() {
    int startPosition = this.start;
    expect(TokenType.IMPORT);
    ParserContextModuleNode module = this.lc.getCurrentModule();
    if (this.type == TokenType.STRING || this.type == TokenType.ESCSTRING) {
      IdentNode moduleSpecifier = createIdentNode(this.token, this.finish, (String)getValue());
      next();
      module.addModuleRequest(moduleSpecifier);
    } else {
      List<Module.ImportEntry> importEntries;
      if (this.type == TokenType.MUL) {
        importEntries = Collections.singletonList(nameSpaceImport(startPosition));
      } else if (this.type == TokenType.LBRACE) {
        importEntries = namedImports(startPosition);
      } else if (isBindingIdentifier()) {
        IdentNode importedDefaultBinding = bindingIdentifier("ImportedBinding");
        Module.ImportEntry defaultImport = Module.ImportEntry.importSpecifier(importedDefaultBinding, startPosition, this.finish);
        if (this.type == TokenType.COMMARIGHT) {
          next();
          importEntries = new ArrayList<>();
          if (this.type == TokenType.MUL) {
            importEntries.add(nameSpaceImport(startPosition));
          } else if (this.type == TokenType.LBRACE) {
            importEntries.addAll(namedImports(startPosition));
          } else {
            throw error(AbstractParser.message("expected.named.import", new String[0]));
          } 
        } else {
          importEntries = Collections.singletonList(defaultImport);
        } 
      } else {
        throw error(AbstractParser.message("expected.import", new String[0]));
      } 
      IdentNode moduleSpecifier = fromClause();
      module.addModuleRequest(moduleSpecifier);
      for (Module.ImportEntry importEntry : importEntries)
        module.addImportEntry(importEntry.withFrom(moduleSpecifier, this.finish)); 
    } 
    expect(TokenType.SEMICOLON);
  }
  
  private Module.ImportEntry nameSpaceImport(int startPosition) {
    assert this.type == TokenType.MUL;
    IdentNode starName = createIdentNode(Token.recast(this.token, TokenType.IDENT), this.finish, "*");
    next();
    long asToken = this.token;
    String as = (String)expectValue(TokenType.IDENT);
    if (!"as".equals(as))
      throw error(AbstractParser.message("expected.as", new String[0]), asToken); 
    IdentNode localNameSpace = bindingIdentifier("ImportedBinding");
    return Module.ImportEntry.importSpecifier(starName, localNameSpace, startPosition, this.finish);
  }
  
  private List<Module.ImportEntry> namedImports(int startPosition) {
    assert this.type == TokenType.LBRACE;
    next();
    List<Module.ImportEntry> importEntries = new ArrayList<>();
    while (this.type != TokenType.RBRACE) {
      boolean bindingIdentifier = isBindingIdentifier();
      long nameToken = this.token;
      IdentNode importName = getIdentifierName();
      if (this.type == TokenType.IDENT && "as".equals(getValue())) {
        next();
        IdentNode localName = bindingIdentifier("ImportedBinding");
        importEntries.add(Module.ImportEntry.importSpecifier(importName, localName, startPosition, this.finish));
      } else {
        if (!bindingIdentifier)
          throw error(AbstractParser.message("expected.binding.identifier", new String[0]), nameToken); 
        importEntries.add(Module.ImportEntry.importSpecifier(importName, startPosition, this.finish));
      } 
      if (this.type == TokenType.COMMARIGHT)
        next(); 
    } 
    expect(TokenType.RBRACE);
    return importEntries;
  }
  
  private IdentNode fromClause() {
    long fromToken = this.token;
    String name = (String)expectValue(TokenType.IDENT);
    if (!"from".equals(name))
      throw error(AbstractParser.message("expected.from", new String[0]), fromToken); 
    if (this.type == TokenType.STRING || this.type == TokenType.ESCSTRING) {
      IdentNode moduleSpecifier = createIdentNode(Token.recast(this.token, TokenType.IDENT), this.finish, (String)getValue());
      next();
      return moduleSpecifier;
    } 
    throw error(expectMessage(TokenType.STRING));
  }
  
  private void exportDeclaration() {
    IdentNode starName;
    List<Module.ExportEntry> exportEntries;
    IdentNode defaultName, moduleRequest;
    Expression expression1;
    ClassNode classNode1;
    Expression assignmentExpression;
    IdentNode ident;
    int lineNumber;
    long rhsToken;
    boolean declaration;
    List<Statement> statements;
    int previousEnd;
    ClassNode classDeclaration;
    FunctionNode functionDeclaration;
    expect(TokenType.EXPORT);
    int startPosition = this.start;
    ParserContextModuleNode module = this.lc.getCurrentModule();
    switch (this.type) {
      case MUL:
        starName = createIdentNode(Token.recast(this.token, TokenType.IDENT), this.finish, "*");
        next();
        moduleRequest = fromClause();
        expect(TokenType.SEMICOLON);
        module.addModuleRequest(moduleRequest);
        module.addStarExportEntry(Module.ExportEntry.exportStarFrom(starName, moduleRequest, startPosition, this.finish));
        return;
      case LBRACE:
        exportEntries = exportClause(startPosition);
        if (this.type == TokenType.IDENT && "from".equals(getValue())) {
          moduleRequest = fromClause();
          module.addModuleRequest(moduleRequest);
          for (Module.ExportEntry exportEntry : exportEntries)
            module.addIndirectExportEntry(exportEntry.withFrom(moduleRequest, this.finish)); 
        } else {
          for (Module.ExportEntry exportEntry : exportEntries)
            module.addLocalExportEntry(exportEntry); 
        } 
        expect(TokenType.SEMICOLON);
        return;
      case DEFAULT:
        defaultName = createIdentNode(Token.recast(this.token, TokenType.IDENT), this.finish, "default");
        next();
        lineNumber = this.line;
        rhsToken = this.token;
        switch (this.type) {
          case FUNCTION:
            expression1 = functionExpression(false, true);
            ident = ((FunctionNode)expression1).getIdent();
            declaration = true;
            break;
          case CLASS:
            classNode1 = classDeclaration(true);
            ident = classNode1.getIdent();
            declaration = true;
            break;
          default:
            assignmentExpression = assignmentExpression(false);
            ident = null;
            declaration = false;
            break;
        } 
        if (ident != null) {
          module.addLocalExportEntry(Module.ExportEntry.exportDefault(defaultName, ident, startPosition, this.finish));
        } else {
          ident = createIdentNode(Token.recast(rhsToken, TokenType.IDENT), this.finish, "*default*");
          this.lc.appendStatementToCurrentNode((Statement)new VarNode(lineNumber, Token.recast(rhsToken, TokenType.LET), this.finish, ident, assignmentExpression));
          if (!declaration)
            expect(TokenType.SEMICOLON); 
          module.addLocalExportEntry(Module.ExportEntry.exportDefault(defaultName, ident, startPosition, this.finish));
        } 
        return;
      case VAR:
      case LET:
      case CONST:
        statements = this.lc.getCurrentBlock().getStatements();
        previousEnd = statements.size();
        variableStatement(this.type);
        for (Statement statement : statements.subList(previousEnd, statements.size())) {
          if (statement instanceof VarNode)
            module.addLocalExportEntry(Module.ExportEntry.exportSpecifier(((VarNode)statement).getName(), startPosition, this.finish)); 
        } 
        return;
      case CLASS:
        classDeclaration = classDeclaration(false);
        module.addLocalExportEntry(Module.ExportEntry.exportSpecifier(classDeclaration.getIdent(), startPosition, this.finish));
        return;
      case FUNCTION:
        functionDeclaration = (FunctionNode)functionExpression(true, true);
        module.addLocalExportEntry(Module.ExportEntry.exportSpecifier(functionDeclaration.getIdent(), startPosition, this.finish));
        return;
    } 
    throw error(AbstractParser.message("invalid.export", new String[0]), this.token);
  }
  
  private List<Module.ExportEntry> exportClause(int startPosition) {
    assert this.type == TokenType.LBRACE;
    next();
    List<Module.ExportEntry> exports = new ArrayList<>();
    while (this.type != TokenType.RBRACE) {
      IdentNode localName = getIdentifierName();
      if (this.type == TokenType.IDENT && "as".equals(getValue())) {
        next();
        IdentNode exportName = getIdentifierName();
        exports.add(Module.ExportEntry.exportSpecifier(exportName, localName, startPosition, this.finish));
      } else {
        exports.add(Module.ExportEntry.exportSpecifier(localName, startPosition, this.finish));
      } 
      if (this.type == TokenType.COMMARIGHT)
        next(); 
    } 
    expect(TokenType.RBRACE);
    return exports;
  }
  
  public String toString() {
    return "'JavaScript Parsing'";
  }
  
  private static void markEval(ParserContext lc) {
    Iterator<ParserContextFunctionNode> iter = lc.getFunctions();
    boolean flaggedCurrentFn = false;
    while (iter.hasNext()) {
      ParserContextFunctionNode fn = iter.next();
      if (!flaggedCurrentFn) {
        fn.setFlag(32);
        flaggedCurrentFn = true;
        if (fn.getKind() == FunctionNode.Kind.ARROW) {
          markThis(lc);
          markNewTarget(lc);
        } 
      } else {
        fn.setFlag(64);
      } 
      ParserContextBlockNode body = lc.getFunctionBody(fn);
      body.setFlag(1);
      fn.setFlag(128);
    } 
  }
  
  private void prependStatement(Statement statement) {
    this.lc.prependStatementToCurrentNode(statement);
  }
  
  private void appendStatement(Statement statement) {
    this.lc.appendStatementToCurrentNode(statement);
  }
  
  private static void markSuperCall(ParserContext lc) {
    Iterator<ParserContextFunctionNode> iter = lc.getFunctions();
    while (iter.hasNext()) {
      ParserContextFunctionNode fn = iter.next();
      if (fn.getKind() != FunctionNode.Kind.ARROW) {
        assert fn.isSubclassConstructor();
        fn.setFlag(524288);
        break;
      } 
    } 
  }
  
  private ParserContextFunctionNode getCurrentNonArrowFunction() {
    Iterator<ParserContextFunctionNode> iter = this.lc.getFunctions();
    while (iter.hasNext()) {
      ParserContextFunctionNode fn = iter.next();
      if (fn.getKind() != FunctionNode.Kind.ARROW)
        return fn; 
    } 
    return null;
  }
  
  private static void markThis(ParserContext lc) {
    Iterator<ParserContextFunctionNode> iter = lc.getFunctions();
    while (iter.hasNext()) {
      ParserContextFunctionNode fn = iter.next();
      fn.setFlag(32768);
      if (fn.getKind() != FunctionNode.Kind.ARROW)
        break; 
    } 
  }
  
  private static void markNewTarget(ParserContext lc) {
    Iterator<ParserContextFunctionNode> iter = lc.getFunctions();
    while (iter.hasNext()) {
      ParserContextFunctionNode fn = iter.next();
      if (fn.getKind() != FunctionNode.Kind.ARROW) {
        if (!fn.isProgram())
          fn.setFlag(33554432); 
        break;
      } 
    } 
  }
  
  private boolean inGeneratorFunction() {
    return (this.lc.getCurrentFunction().getKind() == FunctionNode.Kind.GENERATOR);
  }
}
