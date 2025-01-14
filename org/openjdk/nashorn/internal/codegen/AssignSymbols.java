package org.openjdk.nashorn.internal.codegen;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import org.openjdk.nashorn.internal.ir.AccessNode;
import org.openjdk.nashorn.internal.ir.BinaryNode;
import org.openjdk.nashorn.internal.ir.Block;
import org.openjdk.nashorn.internal.ir.CatchNode;
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.ForNode;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.LexicalContextNode;
import org.openjdk.nashorn.internal.ir.LiteralNode;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.RuntimeNode;
import org.openjdk.nashorn.internal.ir.Splittable;
import org.openjdk.nashorn.internal.ir.Statement;
import org.openjdk.nashorn.internal.ir.SwitchNode;
import org.openjdk.nashorn.internal.ir.Symbol;
import org.openjdk.nashorn.internal.ir.TryNode;
import org.openjdk.nashorn.internal.ir.UnaryNode;
import org.openjdk.nashorn.internal.ir.VarNode;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.ir.visitor.SimpleNodeVisitor;
import org.openjdk.nashorn.internal.parser.TokenType;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.ErrorManager;
import org.openjdk.nashorn.internal.runtime.JSErrorType;
import org.openjdk.nashorn.internal.runtime.ParserException;
import org.openjdk.nashorn.internal.runtime.Source;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;
import org.openjdk.nashorn.internal.runtime.logging.Loggable;
import org.openjdk.nashorn.internal.runtime.logging.Logger;

@Logger(name = "symbols")
final class AssignSymbols extends SimpleNodeVisitor implements Loggable {
  private final DebugLogger log;
  
  private final boolean debug;
  
  private static boolean isParamOrVar(IdentNode identNode) {
    Symbol symbol = identNode.getSymbol();
    return (symbol.isParam() || symbol.isVar());
  }
  
  private static String name(Node node) {
    String cn = node.getClass().getName();
    int lastDot = cn.lastIndexOf('.');
    if (lastDot == -1)
      return cn; 
    return cn.substring(lastDot + 1);
  }
  
  private static FunctionNode removeUnusedSlots(FunctionNode functionNode) {
    if (!functionNode.needsCallee())
      functionNode.compilerConstant(CompilerConstants.CALLEE).setNeedsSlot(false); 
    if (!functionNode.hasScopeBlock() && !functionNode.needsParentScope())
      functionNode.compilerConstant(CompilerConstants.SCOPE).setNeedsSlot(false); 
    if (functionNode.isNamedFunctionExpression() && !functionNode.usesSelfSymbol()) {
      Symbol selfSymbol = functionNode.getBody().getExistingSymbol(functionNode.getIdent().getName());
      if (selfSymbol != null && selfSymbol.isFunctionSelf()) {
        selfSymbol.setNeedsSlot(false);
        selfSymbol.clearFlag(2);
      } 
    } 
    return functionNode;
  }
  
  private final Deque<Set<String>> thisProperties = new ArrayDeque<>();
  
  private final Map<String, Symbol> globalSymbols = new HashMap<>();
  
  private final Compiler compiler;
  
  private final boolean isOnDemand;
  
  public AssignSymbols(Compiler compiler) {
    this.compiler = compiler;
    this.log = initLogger(compiler.getContext());
    this.debug = this.log.isEnabled();
    this.isOnDemand = compiler.isOnDemandCompilation();
  }
  
  public DebugLogger getLogger() {
    return this.log;
  }
  
  public DebugLogger initLogger(Context context) {
    return context.getLogger(getClass());
  }
  
  private void acceptDeclarations(final Block body) {
    body.accept((NodeVisitor)new SimpleNodeVisitor() {
          protected boolean enterDefault(Node node) {
            return !(node instanceof Expression);
          }
          
          public Node leaveVarNode(VarNode varNode) {
            IdentNode ident = varNode.getName();
            boolean blockScoped = varNode.isBlockScoped();
            if (blockScoped && this.lc.inUnprotectedSwitchContext())
              AssignSymbols.this.throwUnprotectedSwitchError(varNode); 
            Block block = blockScoped ? this.lc.getCurrentBlock() : body;
            Symbol symbol = AssignSymbols.this.defineSymbol(block, ident.getName(), (Node)ident, varNode.getSymbolFlags());
            if (varNode.isFunctionDeclaration())
              symbol.setIsFunctionDeclaration(); 
            return (Node)varNode.setName(ident.setSymbol(symbol));
          }
        });
  }
  
  private IdentNode compilerConstantIdentifier(CompilerConstants cc) {
    return createImplicitIdentifier(cc.symbolName()).setSymbol(this.lc.getCurrentFunction().compilerConstant(cc));
  }
  
  private IdentNode createImplicitIdentifier(String name) {
    FunctionNode fn = this.lc.getCurrentFunction();
    return new IdentNode(fn.getToken(), fn.getFinish(), name);
  }
  
  private Symbol createSymbol(String name, int flags) {
    if ((flags & 0x3) == 1) {
      Symbol global = this.globalSymbols.get(name);
      if (global == null) {
        global = new Symbol(name, flags);
        this.globalSymbols.put(name, global);
      } 
      return global;
    } 
    return new Symbol(name, flags);
  }
  
  private VarNode createSyntheticInitializer(IdentNode name, CompilerConstants initConstant, FunctionNode fn) {
    IdentNode init = compilerConstantIdentifier(initConstant);
    assert init.getSymbol() != null && init.getSymbol().isBytecodeLocal();
    VarNode synthVar = new VarNode(fn.getLineNumber(), fn.getToken(), fn.getFinish(), name, (Expression)init);
    Symbol nameSymbol = fn.getBody().getExistingSymbol(name.getName());
    assert nameSymbol != null;
    return (VarNode)synthVar.setName(name.setSymbol(nameSymbol)).accept((NodeVisitor)this);
  }
  
  private FunctionNode createSyntheticInitializers(FunctionNode functionNode) {
    List<VarNode> syntheticInitializers = new ArrayList<>(2);
    Block body = functionNode.getBody();
    this.lc.push((LexicalContextNode)body);
    try {
      if (functionNode.usesSelfSymbol())
        syntheticInitializers.add(createSyntheticInitializer(functionNode.getIdent(), CompilerConstants.CALLEE, functionNode)); 
      if (functionNode.needsArguments())
        syntheticInitializers.add(createSyntheticInitializer(createImplicitIdentifier(CompilerConstants.ARGUMENTS_VAR.symbolName()), CompilerConstants.ARGUMENTS, functionNode)); 
      if (syntheticInitializers.isEmpty())
        return functionNode; 
      for (ListIterator<VarNode> it = syntheticInitializers.listIterator(); it.hasNext();)
        it.set((VarNode)((VarNode)it.next()).accept((NodeVisitor)this)); 
    } finally {
      this.lc.pop((Node)body);
    } 
    List<Statement> stmts = body.getStatements();
    List<Statement> newStatements = new ArrayList<>(stmts.size() + syntheticInitializers.size());
    newStatements.addAll(syntheticInitializers);
    newStatements.addAll(stmts);
    return functionNode.setBody(this.lc, body.setStatements(this.lc, newStatements));
  }
  
  private Symbol defineSymbol(Block block, String name, Node origin, int symbolFlags) {
    Symbol symbol;
    FunctionNode function;
    int flags = symbolFlags;
    boolean isBlockScope = ((flags & 0x10) != 0 || (flags & 0x20) != 0);
    boolean isGlobal = ((flags & 0x3) == 1);
    if (isBlockScope) {
      symbol = block.getExistingSymbol(name);
      function = this.lc.getCurrentFunction();
    } else {
      symbol = findSymbol(block, name);
      function = this.lc.getFunction(block);
    } 
    if (isGlobal)
      flags |= 0x4; 
    if (this.lc.getCurrentFunction().isProgram())
      flags |= 0x200; 
    boolean isParam = ((flags & 0x3) == 3);
    boolean isVar = ((flags & 0x3) == 2);
    if (symbol != null)
      if (isParam) {
        if (!isLocal(function, symbol)) {
          symbol = null;
        } else if (symbol.isParam()) {
          throwParserException(ECMAErrors.getMessage("syntax.error.duplicate.parameter", new String[] { name }), origin);
        } 
      } else if (isVar) {
        if (isBlockScope) {
          if (symbol.hasBeenDeclared()) {
            throwParserException(ECMAErrors.getMessage("syntax.error.redeclare.variable", new String[] { name }), origin);
          } else {
            symbol.setHasBeenDeclared();
            if (function.isProgram() && function.getBody() == block)
              symbol.setIsScope(); 
          } 
        } else if ((flags & 0x40) != 0) {
          symbol = null;
        } else {
          if (symbol.isBlockScoped() && isLocal(this.lc.getCurrentFunction(), symbol))
            throwParserException(ECMAErrors.getMessage("syntax.error.redeclare.variable", new String[] { name }), origin); 
          if (!isLocal(function, symbol) || symbol.less(2))
            symbol = null; 
        } 
      }  
    if (symbol == null) {
      Block symbolBlock;
      if (isVar && ((flags & 0x40) != 0 || isBlockScope)) {
        symbolBlock = block;
      } else if (isGlobal) {
        symbolBlock = this.lc.getOutermostFunction().getBody();
      } else {
        symbolBlock = this.lc.getFunctionBody(function);
      } 
      symbol = createSymbol(name, flags);
      symbolBlock.putSymbol(symbol);
      if ((flags & 0x4) == 0)
        symbol.setNeedsSlot(true); 
    } else if (symbol.less(flags)) {
      symbol.setFlags(flags);
    } 
    return symbol;
  }
  
  private <T extends Node> T end(T node) {
    if (this.debug) {
      StringBuilder sb = new StringBuilder();
      sb.append("[LEAVE ")
        .append(name((Node)node))
        .append("] ")
        .append(node.toString())
        .append(" in '")
        .append(this.lc.getCurrentFunction().getName())
        .append('\'');
      if (node instanceof IdentNode) {
        Symbol symbol = ((IdentNode)node).getSymbol();
        if (symbol == null) {
          sb.append(" <NO SYMBOL>");
        } else {
          sb.append(" <symbol=").append(symbol).append('>');
        } 
      } 
      this.log.unindent();
      this.log.info(new Object[] { sb });
    } 
    return node;
  }
  
  public boolean enterBlock(Block block) {
    start((Node)block);
    if (this.lc.isFunctionBody()) {
      assert !block.hasSymbols();
      FunctionNode fn = this.lc.getCurrentFunction();
      if (isUnparsedFunction(fn)) {
        for (String name : this.compiler.getScriptFunctionData(fn.getId()).getExternalSymbolNames())
          nameIsUsed(name, null); 
        assert block.getStatements().isEmpty();
        return false;
      } 
      enterFunctionBody();
    } 
    return true;
  }
  
  private boolean isUnparsedFunction(FunctionNode fn) {
    return (this.isOnDemand && fn != this.lc.getOutermostFunction());
  }
  
  public boolean enterCatchNode(CatchNode catchNode) {
    IdentNode exception = catchNode.getExceptionIdentifier();
    Block block = this.lc.getCurrentBlock();
    start((Node)catchNode);
    String exname = exception.getName();
    boolean isInternal = exname.startsWith(CompilerConstants.EXCEPTION_PREFIX.symbolName());
    Symbol symbol = defineSymbol(block, exname, (Node)catchNode, 0x12 | (isInternal ? 64 : 0) | 0x2000);
    symbol.clearFlag(16);
    return true;
  }
  
  private void enterFunctionBody() {
    FunctionNode functionNode = this.lc.getCurrentFunction();
    Block body = this.lc.getCurrentBlock();
    initFunctionWideVariables(functionNode, body);
    acceptDeclarations(body);
    defineFunctionSelfSymbol(functionNode, body);
  }
  
  private void defineFunctionSelfSymbol(FunctionNode functionNode, Block body) {
    if (!functionNode.isNamedFunctionExpression())
      return; 
    String name = functionNode.getIdent().getName();
    assert name != null;
    if (body.getExistingSymbol(name) != null)
      return; 
    defineSymbol(body, name, (Node)functionNode, 8322);
    if (functionNode.allVarsInScope())
      this.lc.setFlag((LexicalContextNode)functionNode, 16384); 
  }
  
  public boolean enterFunctionNode(FunctionNode functionNode) {
    start((Node)functionNode, false);
    this.thisProperties.push(new HashSet<>());
    assert functionNode.getBody() != null;
    return true;
  }
  
  public boolean enterVarNode(VarNode varNode) {
    start((Node)varNode);
    if (varNode.isFunctionDeclaration())
      defineVarIdent(varNode); 
    return true;
  }
  
  public Node leaveVarNode(VarNode varNode) {
    if (!varNode.isFunctionDeclaration())
      defineVarIdent(varNode); 
    return super.leaveVarNode(varNode);
  }
  
  private void defineVarIdent(VarNode varNode) {
    int flags;
    IdentNode ident = varNode.getName();
    if (!varNode.isBlockScoped() && this.lc.getCurrentFunction().isProgram()) {
      flags = 4;
    } else {
      flags = 0;
    } 
    defineSymbol(this.lc.getCurrentBlock(), ident.getName(), (Node)ident, varNode.getSymbolFlags() | flags);
  }
  
  private Symbol exceptionSymbol() {
    return newInternal(CompilerConstants.EXCEPTION_PREFIX);
  }
  
  private FunctionNode finalizeParameters(FunctionNode functionNode) {
    List<IdentNode> newParams = new ArrayList<>();
    boolean isVarArg = functionNode.isVarArg();
    Block body = functionNode.getBody();
    for (IdentNode param : functionNode.getParameters()) {
      Symbol paramSymbol = body.getExistingSymbol(param.getName());
      assert paramSymbol != null;
      assert paramSymbol.isParam() : "" + paramSymbol + " " + paramSymbol;
      newParams.add(param.setSymbol(paramSymbol));
      if (isVarArg)
        paramSymbol.setNeedsSlot(false); 
    } 
    return functionNode.setParameters(this.lc, newParams);
  }
  
  private Symbol findSymbol(Block block, String name) {
    for (Iterator<Block> blocks = this.lc.getBlocks(block); blocks.hasNext(); ) {
      Symbol symbol = ((Block)blocks.next()).getExistingSymbol(name);
      if (symbol != null)
        return symbol; 
    } 
    return null;
  }
  
  private void functionUsesGlobalSymbol() {
    for (Iterator<FunctionNode> fns = this.lc.getFunctions(); fns.hasNext();)
      this.lc.setFlag((LexicalContextNode)fns.next(), 512); 
  }
  
  private void functionUsesScopeSymbol(Symbol symbol) {
    String name = symbol.getName();
    for (Iterator<LexicalContextNode> contextNodeIter = this.lc.getAllNodes(); contextNodeIter.hasNext(); ) {
      LexicalContextNode node = contextNodeIter.next();
      if (node instanceof Block) {
        Block block = (Block)node;
        if (block.getExistingSymbol(name) != null) {
          assert this.lc.contains((LexicalContextNode)block);
          this.lc.setBlockNeedsScope(block);
          break;
        } 
        continue;
      } 
      if (node instanceof FunctionNode)
        this.lc.setFlag(node, 512); 
    } 
  }
  
  private void functionUsesSymbol(Symbol symbol) {
    assert symbol != null;
    if (symbol.isScope()) {
      if (symbol.isGlobal()) {
        functionUsesGlobalSymbol();
      } else {
        functionUsesScopeSymbol(symbol);
      } 
    } else {
      assert !symbol.isGlobal();
    } 
  }
  
  private void initCompileConstant(CompilerConstants cc, Block block, int flags) {
    defineSymbol(block, cc.symbolName(), null, flags).setNeedsSlot(true);
  }
  
  private void initFunctionWideVariables(FunctionNode functionNode, Block body) {
    initCompileConstant(CompilerConstants.CALLEE, body, 8259);
    initCompileConstant(CompilerConstants.THIS, body, 8203);
    if (functionNode.isVarArg()) {
      initCompileConstant(CompilerConstants.VARARGS, body, 8259);
      if (functionNode.needsArguments()) {
        initCompileConstant(CompilerConstants.ARGUMENTS, body, 8258);
        defineSymbol(body, CompilerConstants.ARGUMENTS_VAR.symbolName(), null, 8194);
      } 
    } 
    initParameters(functionNode, body);
    initCompileConstant(CompilerConstants.SCOPE, body, 8258);
    initCompileConstant(CompilerConstants.RETURN, body, 66);
  }
  
  private void initParameters(FunctionNode functionNode, Block body) {
    boolean isVarArg = functionNode.isVarArg();
    boolean scopeParams = (functionNode.allVarsInScope() || isVarArg);
    for (IdentNode param : functionNode.getParameters()) {
      Symbol symbol = defineSymbol(body, param.getName(), (Node)param, 3);
      if (scopeParams) {
        symbol.setIsScope();
        assert symbol.hasSlot();
        if (isVarArg)
          symbol.setNeedsSlot(false); 
      } 
    } 
  }
  
  private boolean isLocal(FunctionNode function, Symbol symbol) {
    FunctionNode definingFn = this.lc.getDefiningFunction(symbol);
    assert definingFn != null;
    return (definingFn == function);
  }
  
  public Node leaveBinaryNode(BinaryNode binaryNode) {
    if (binaryNode.isTokenType(TokenType.ASSIGN))
      return leaveASSIGN(binaryNode); 
    return super.leaveBinaryNode(binaryNode);
  }
  
  private Node leaveASSIGN(BinaryNode binaryNode) {
    Expression lhs = binaryNode.lhs();
    if (lhs instanceof AccessNode) {
      AccessNode accessNode = (AccessNode)lhs;
      Expression base = accessNode.getBase();
      if (base instanceof IdentNode) {
        Symbol symbol = ((IdentNode)base).getSymbol();
        if (symbol.isThis())
          ((Set<String>)this.thisProperties.peek()).add(accessNode.getProperty()); 
      } 
    } 
    return (Node)binaryNode;
  }
  
  public Node leaveUnaryNode(UnaryNode unaryNode) {
    if (unaryNode.tokenType() == TokenType.TYPEOF)
      return leaveTYPEOF(unaryNode); 
    return super.leaveUnaryNode(unaryNode);
  }
  
  public Node leaveForNode(ForNode forNode) {
    if (forNode.isForInOrOf())
      return (Node)forNode.setIterator(this.lc, newInternal(CompilerConstants.ITERATOR_PREFIX)); 
    return (Node)end(forNode);
  }
  
  public Node leaveFunctionNode(FunctionNode functionNode) {
    FunctionNode finalizedFunction;
    if (isUnparsedFunction(functionNode)) {
      finalizedFunction = functionNode;
    } else {
      finalizedFunction = markProgramBlock(
          removeUnusedSlots(
            createSyntheticInitializers(
              finalizeParameters((FunctionNode)this.lc
                .applyTopFlags((LexicalContextNode)functionNode))))
          .setThisProperties(this.lc, ((Set)this.thisProperties.pop()).size()));
    } 
    return (Node)finalizedFunction;
  }
  
  public Node leaveIdentNode(IdentNode identNode) {
    if (identNode.isPropertyName())
      return (Node)identNode; 
    Symbol symbol = nameIsUsed(identNode.getName(), identNode);
    if (!identNode.isInitializedHere())
      symbol.increaseUseCount(); 
    IdentNode newIdentNode = identNode.setSymbol(symbol);
    if (symbol.isBlockScoped() && !symbol.hasBeenDeclared() && !identNode.isDeclaredHere() && isLocal(this.lc.getCurrentFunction(), symbol))
      newIdentNode = newIdentNode.markDead(); 
    return (Node)end(newIdentNode);
  }
  
  private Symbol nameIsUsed(String name, IdentNode origin) {
    Block block = this.lc.getCurrentBlock();
    Symbol symbol = findSymbol(block, name);
    if (symbol != null) {
      this.log.info(new Object[] { "Existing symbol = ", symbol });
      if (symbol.isFunctionSelf()) {
        FunctionNode functionNode = this.lc.getDefiningFunction(symbol);
        assert functionNode != null;
        assert this.lc.getFunctionBody(functionNode).getExistingSymbol(CompilerConstants.CALLEE.symbolName()) != null;
        this.lc.setFlag((LexicalContextNode)functionNode, 16384);
      } 
      maybeForceScope(symbol);
    } else {
      this.log.info(new Object[] { "No symbol exists. Declare as global: ", name });
      symbol = defineSymbol(block, name, (Node)origin, 5);
    } 
    functionUsesSymbol(symbol);
    return symbol;
  }
  
  public Node leaveSwitchNode(SwitchNode switchNode) {
    if (!switchNode.isUniqueInteger())
      return (Node)switchNode.setTag(this.lc, newInternal(CompilerConstants.SWITCH_TAG_PREFIX)); 
    return (Node)switchNode;
  }
  
  public Node leaveTryNode(TryNode tryNode) {
    assert tryNode.getFinallyBody() == null;
    end(tryNode);
    return (Node)tryNode.setException(this.lc, exceptionSymbol());
  }
  
  private Node leaveTYPEOF(UnaryNode unaryNode) {
    Expression rhs = unaryNode.getExpression();
    List<Expression> args = new ArrayList<>();
    if (rhs instanceof IdentNode && !isParamOrVar((IdentNode)rhs)) {
      args.add(compilerConstantIdentifier(CompilerConstants.SCOPE));
      args.add(LiteralNode.newInstance((Node)rhs, ((IdentNode)rhs).getName()));
    } else {
      args.add(rhs);
      args.add(LiteralNode.newInstance((Node)unaryNode));
    } 
    RuntimeNode runtimeNode = new RuntimeNode((Expression)unaryNode, RuntimeNode.Request.TYPEOF, args);
    end(unaryNode);
    return (Node)runtimeNode;
  }
  
  private FunctionNode markProgramBlock(FunctionNode functionNode) {
    if (this.isOnDemand || !functionNode.isProgram())
      return functionNode; 
    return functionNode.setBody(this.lc, functionNode.getBody().setFlag(this.lc, 8));
  }
  
  private void maybeForceScope(Symbol symbol) {
    if (!symbol.isScope() && symbolNeedsToBeScope(symbol))
      Symbol.setSymbolIsScope(this.lc, symbol); 
  }
  
  private Symbol newInternal(CompilerConstants cc) {
    return defineSymbol(this.lc.getCurrentBlock(), this.lc.getCurrentFunction().uniqueName(cc.symbolName()), null, 8258);
  }
  
  private void start(Node node) {
    start(node, true);
  }
  
  private void start(Node node, boolean printNode) {
    if (this.debug) {
      StringBuilder sb = new StringBuilder();
      sb.append("[ENTER ")
        .append(name(node))
        .append("] ")
        .append(printNode ? node.toString() : "")
        .append(" in '")
        .append(this.lc.getCurrentFunction().getName())
        .append("'");
      this.log.info(new Object[] { sb });
      this.log.indent();
    } 
  }
  
  private boolean symbolNeedsToBeScope(Symbol symbol) {
    if (symbol.isThis() || symbol.isInternal())
      return false; 
    FunctionNode func = this.lc.getCurrentFunction();
    if (func.allVarsInScope() || (!symbol.isBlockScoped() && func.isProgram()))
      return true; 
    boolean previousWasBlock = false;
    for (Iterator<LexicalContextNode> it = this.lc.getAllNodes(); it.hasNext(); ) {
      LexicalContextNode node = it.next();
      if (node instanceof FunctionNode || isSplitLiteral(node))
        return true; 
      if (node instanceof org.openjdk.nashorn.internal.ir.WithNode) {
        if (previousWasBlock)
          return true; 
        previousWasBlock = false;
        continue;
      } 
      if (node instanceof Block) {
        if (((Block)node).getExistingSymbol(symbol.getName()) == symbol)
          return false; 
        previousWasBlock = true;
        continue;
      } 
      previousWasBlock = false;
    } 
    throw new AssertionError();
  }
  
  private static boolean isSplitLiteral(LexicalContextNode expr) {
    return (expr instanceof Splittable && ((Splittable)expr).getSplitRanges() != null);
  }
  
  private void throwUnprotectedSwitchError(VarNode varNode) {
    String msg = ECMAErrors.getMessage("syntax.error.unprotected.switch.declaration", new String[] { varNode.isLet() ? "let" : "const" });
    throwParserException(msg, (Node)varNode);
  }
  
  private void throwParserException(String message, Node origin) {
    if (origin == null)
      throw new ParserException(message); 
    Source source = this.compiler.getSource();
    long token = origin.getToken();
    int line = source.getLine(origin.getStart());
    int column = source.getColumn(origin.getStart());
    String formatted = ErrorManager.format(message, source, line, column, token);
    throw new ParserException(JSErrorType.SYNTAX_ERROR, formatted, source, line, column, token);
  }
}
