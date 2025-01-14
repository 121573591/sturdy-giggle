package org.openjdk.nashorn.internal.codegen;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import org.openjdk.nashorn.internal.ir.AccessNode;
import org.openjdk.nashorn.internal.ir.BinaryNode;
import org.openjdk.nashorn.internal.ir.Block;
import org.openjdk.nashorn.internal.ir.BlockLexicalContext;
import org.openjdk.nashorn.internal.ir.BreakNode;
import org.openjdk.nashorn.internal.ir.CallNode;
import org.openjdk.nashorn.internal.ir.CaseNode;
import org.openjdk.nashorn.internal.ir.ContinueNode;
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.ExpressionStatement;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.GetSplitState;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.IfNode;
import org.openjdk.nashorn.internal.ir.JumpStatement;
import org.openjdk.nashorn.internal.ir.JumpToInlinedFinally;
import org.openjdk.nashorn.internal.ir.LexicalContext;
import org.openjdk.nashorn.internal.ir.LiteralNode;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.ReturnNode;
import org.openjdk.nashorn.internal.ir.SetSplitState;
import org.openjdk.nashorn.internal.ir.SplitNode;
import org.openjdk.nashorn.internal.ir.SplitReturn;
import org.openjdk.nashorn.internal.ir.Statement;
import org.openjdk.nashorn.internal.ir.SwitchNode;
import org.openjdk.nashorn.internal.ir.VarNode;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.parser.Token;
import org.openjdk.nashorn.internal.parser.TokenType;

final class SplitIntoFunctions extends NodeVisitor<BlockLexicalContext> {
  private static final int FALLTHROUGH_STATE = -1;
  
  private static final int RETURN_STATE = 0;
  
  private static final int BREAK_STATE = 1;
  
  private static final int FIRST_JUMP_STATE = 2;
  
  private static final String THIS_NAME = CompilerConstants.THIS.symbolName();
  
  private static final String RETURN_NAME = CompilerConstants.RETURN.symbolName();
  
  private static final String RETURN_PARAM_NAME = RETURN_NAME + "-in";
  
  private final Deque<FunctionState> functionStates = new ArrayDeque<>();
  
  private final Deque<SplitState> splitStates = new ArrayDeque<>();
  
  private final Namespace namespace;
  
  private boolean artificialBlock = false;
  
  private int nextFunctionId = -2;
  
  public SplitIntoFunctions(Compiler compiler) {
    super((LexicalContext)new BlockLexicalContext() {
          protected Block afterSetStatements(Block block) {
            for (Statement stmt : block.getStatements())
              assert !(stmt instanceof SplitNode); 
            return block;
          }
        });
    this.namespace = new Namespace(compiler.getScriptEnvironment().getNamespace());
  }
  
  public boolean enterFunctionNode(FunctionNode functionNode) {
    this.functionStates.push(new FunctionState(functionNode));
    return true;
  }
  
  public Node leaveFunctionNode(FunctionNode functionNode) {
    this.functionStates.pop();
    return (Node)functionNode;
  }
  
  protected Node leaveDefault(Node node) {
    if (node instanceof Statement)
      appendStatement((Statement)node); 
    return node;
  }
  
  public boolean enterSplitNode(SplitNode splitNode) {
    (getCurrentFunctionState()).splitDepth++;
    this.splitStates.push(new SplitState(splitNode));
    return true;
  }
  
  public Node leaveSplitNode(SplitNode splitNode) {
    CallNode callNode1;
    Statement splitStateHandler;
    IfNode ifNode;
    FunctionState fnState = getCurrentFunctionState();
    String name = splitNode.getName();
    Block body = splitNode.getBody();
    int firstLineNumber = body.getFirstStatementLineNumber();
    long token = body.getToken();
    int finish = body.getFinish();
    FunctionNode originalFn = fnState.fn;
    assert originalFn == ((BlockLexicalContext)this.lc).getCurrentFunction();
    boolean isProgram = originalFn.isProgram();
    long newFnToken = Token.toDesc(TokenType.FUNCTION, this.nextFunctionId--, 0);
    FunctionNode fn = (new FunctionNode(originalFn.getSource(), body.getFirstStatementLineNumber(), newFnToken, finish, newFnToken, 0L, this.namespace, createIdent(name), originalFn.getName() + "$" + originalFn.getName(), isProgram ? Collections.<IdentNode>singletonList(createReturnParamIdent()) : Collections.emptyList(), null, FunctionNode.Kind.NORMAL, 529, body, null, originalFn.getModule(), originalFn.getDebugFlags())).setCompileUnit(this.lc, splitNode.getCompileUnit());
    IdentNode thisIdent = createIdent(THIS_NAME);
    CallNode callNode = new CallNode(firstLineNumber, token, finish, (Expression)new AccessNode(0L, 0, (Expression)fn, "call"), isProgram ? Arrays.<Expression>asList(new Expression[] { (Expression)thisIdent, (Expression)createReturnIdent() }, ) : Collections.<IdentNode>singletonList(thisIdent), false);
    SplitState splitState = this.splitStates.pop();
    fnState.splitDepth--;
    boolean hasReturn = splitState.hasReturn;
    if (hasReturn && fnState.splitDepth > 0) {
      SplitState parentSplit = this.splitStates.peek();
      if (parentSplit != null)
        parentSplit.hasReturn = true; 
    } 
    if (hasReturn || isProgram) {
      BinaryNode binaryNode = new BinaryNode(Token.recast(token, TokenType.ASSIGN), (Expression)createReturnIdent(), (Expression)callNode);
    } else {
      callNode1 = callNode;
    } 
    appendStatement((Statement)new ExpressionStatement(firstLineNumber, token, finish, (Expression)callNode1));
    List<JumpStatement> jumpStatements = splitState.jumpStatements;
    int jumpCount = jumpStatements.size();
    if (jumpCount > 0) {
      List<CaseNode> cases = new ArrayList<>(jumpCount + (hasReturn ? 1 : 0));
      if (hasReturn)
        addCase(cases, 0, createReturnFromSplit()); 
      int i = 2;
      for (JumpStatement jump : jumpStatements)
        addCase(cases, i++, enblockAndVisit(jump)); 
      SwitchNode switchNode = new SwitchNode(-1, token, finish, (Expression)GetSplitState.INSTANCE, cases, null);
    } else {
      splitStateHandler = null;
    } 
    if (splitState.hasBreak)
      ifNode = makeIfStateEquals(firstLineNumber, token, finish, 1, 
          enblockAndVisit((JumpStatement)new BreakNode(-1, token, finish, null)), splitStateHandler); 
    if (hasReturn && jumpCount == 0)
      ifNode = makeIfStateEquals(-1, token, finish, 0, 
          createReturnFromSplit(), (Statement)ifNode); 
    if (ifNode != null)
      appendStatement((Statement)ifNode); 
    return (Node)splitNode;
  }
  
  private static void addCase(List<CaseNode> cases, int i, Block body) {
    cases.add(new CaseNode(0L, 0, (Expression)intLiteral(i), body));
  }
  
  private static LiteralNode<Number> intLiteral(int i) {
    return LiteralNode.newInstance(0L, 0, Integer.valueOf(i));
  }
  
  private static Block createReturnFromSplit() {
    return new Block(0L, 0, new Statement[] { (Statement)createReturnReturn() });
  }
  
  private static ReturnNode createReturnReturn() {
    return new ReturnNode(-1, 0L, 0, (Expression)createReturnIdent());
  }
  
  private static IdentNode createReturnIdent() {
    return createIdent(RETURN_NAME);
  }
  
  private static IdentNode createReturnParamIdent() {
    return createIdent(RETURN_PARAM_NAME);
  }
  
  private static IdentNode createIdent(String name) {
    return new IdentNode(0L, 0, name);
  }
  
  private Block enblockAndVisit(JumpStatement jump) {
    this.artificialBlock = true;
    Block block = (Block)(new Block(0L, 0, new Statement[] { (Statement)jump })).accept(this);
    this.artificialBlock = false;
    return block;
  }
  
  private static IfNode makeIfStateEquals(int lineNumber, long token, int finish, int value, Block pass, Statement fail) {
    return new IfNode(lineNumber, token, finish, (Expression)new BinaryNode(
          Token.recast(token, TokenType.EQ_STRICT), (Expression)GetSplitState.INSTANCE, 
          (Expression)intLiteral(value)), pass, 
        
        (fail == null) ? null : new Block(0L, 0, new Statement[] { fail }));
  }
  
  public boolean enterVarNode(VarNode varNode) {
    if (!inSplitNode() || varNode.isBlockScoped())
      return super.enterVarNode(varNode); 
    Expression init = varNode.getInit();
    (getCurrentFunctionState()).varStatements.add(varNode.setInit(null));
    if (init != null) {
      long token = Token.recast(varNode.getToken(), TokenType.ASSIGN);
      (new ExpressionStatement(varNode.getLineNumber(), token, varNode.getFinish(), (Expression)new BinaryNode(token, (Expression)varNode
            .getName(), varNode.getInit()))).accept(this);
    } 
    return false;
  }
  
  public Node leaveBlock(Block block) {
    if (!this.artificialBlock)
      if (((BlockLexicalContext)this.lc).isFunctionBody()) {
        ((BlockLexicalContext)this.lc).prependStatements((getCurrentFunctionState()).varStatements);
      } else if (((BlockLexicalContext)this.lc).isSplitBody()) {
        appendSplitReturn(-1, -1);
        if ((getCurrentFunctionState()).fn.isProgram())
          ((BlockLexicalContext)this.lc).prependStatement((Statement)new ExpressionStatement(-1, 0L, 0, (Expression)new BinaryNode(
                  Token.toDesc(TokenType.ASSIGN, 0, 0), (Expression)createReturnIdent(), (Expression)createReturnParamIdent()))); 
      }  
    return (Node)block;
  }
  
  public Node leaveBreakNode(BreakNode breakNode) {
    return (Node)leaveJumpNode((JumpStatement)breakNode);
  }
  
  public Node leaveContinueNode(ContinueNode continueNode) {
    return (Node)leaveJumpNode((JumpStatement)continueNode);
  }
  
  public Node leaveJumpToInlinedFinally(JumpToInlinedFinally jumpToInlinedFinally) {
    return (Node)leaveJumpNode((JumpStatement)jumpToInlinedFinally);
  }
  
  private JumpStatement leaveJumpNode(JumpStatement jump) {
    if (inSplitNode()) {
      SplitState splitState = getCurrentSplitState();
      SplitNode splitNode = splitState.splitNode;
      if (((BlockLexicalContext)this.lc).isExternalTarget(splitNode, jump.getTarget(this.lc))) {
        appendSplitReturn(splitState.getSplitStateIndex(jump), jump.getLineNumber());
        return jump;
      } 
    } 
    appendStatement((Statement)jump);
    return jump;
  }
  
  private void appendSplitReturn(int splitState, int lineNumber) {
    appendStatement((Statement)new SetSplitState(splitState, lineNumber));
    if ((getCurrentFunctionState()).fn.isProgram()) {
      appendStatement((Statement)createReturnReturn());
    } else {
      appendStatement((Statement)SplitReturn.INSTANCE);
    } 
  }
  
  public Node leaveReturnNode(ReturnNode returnNode) {
    if (inSplitNode()) {
      appendStatement((Statement)new SetSplitState(0, returnNode.getLineNumber()));
      (getCurrentSplitState()).hasReturn = true;
    } 
    appendStatement((Statement)returnNode);
    return (Node)returnNode;
  }
  
  private void appendStatement(Statement statement) {
    ((BlockLexicalContext)this.lc).appendStatement(statement);
  }
  
  private boolean inSplitNode() {
    return ((getCurrentFunctionState()).splitDepth > 0);
  }
  
  private FunctionState getCurrentFunctionState() {
    return this.functionStates.peek();
  }
  
  private SplitState getCurrentSplitState() {
    return this.splitStates.peek();
  }
  
  private static class FunctionState {
    final FunctionNode fn;
    
    final List<Statement> varStatements = new ArrayList<>();
    
    int splitDepth;
    
    FunctionState(FunctionNode fn) {
      this.fn = fn;
    }
  }
  
  private static class SplitState {
    final SplitNode splitNode;
    
    boolean hasReturn;
    
    boolean hasBreak;
    
    final List<JumpStatement> jumpStatements = new ArrayList<>();
    
    int getSplitStateIndex(JumpStatement jump) {
      if (jump instanceof BreakNode && jump.getLabelName() == null) {
        this.hasBreak = true;
        return 1;
      } 
      int i = 0;
      for (JumpStatement exJump : this.jumpStatements) {
        if (jump.getClass() == exJump.getClass() && Objects.equals(jump.getLabelName(), exJump.getLabelName()))
          return i + 2; 
        i++;
      } 
      this.jumpStatements.add(jump);
      return i + 2;
    }
    
    SplitState(SplitNode splitNode) {
      this.splitNode = splitNode;
    }
  }
}
