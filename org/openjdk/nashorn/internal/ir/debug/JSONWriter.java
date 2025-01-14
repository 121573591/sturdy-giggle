package org.openjdk.nashorn.internal.ir.debug;

import java.util.ArrayList;
import java.util.List;
import org.openjdk.nashorn.internal.ir.AccessNode;
import org.openjdk.nashorn.internal.ir.BinaryNode;
import org.openjdk.nashorn.internal.ir.Block;
import org.openjdk.nashorn.internal.ir.BlockStatement;
import org.openjdk.nashorn.internal.ir.BreakNode;
import org.openjdk.nashorn.internal.ir.CallNode;
import org.openjdk.nashorn.internal.ir.CaseNode;
import org.openjdk.nashorn.internal.ir.CatchNode;
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
import org.openjdk.nashorn.internal.ir.TernaryNode;
import org.openjdk.nashorn.internal.ir.ThrowNode;
import org.openjdk.nashorn.internal.ir.TryNode;
import org.openjdk.nashorn.internal.ir.UnaryNode;
import org.openjdk.nashorn.internal.ir.VarNode;
import org.openjdk.nashorn.internal.ir.WhileNode;
import org.openjdk.nashorn.internal.ir.WithNode;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.ir.visitor.SimpleNodeVisitor;
import org.openjdk.nashorn.internal.parser.JSONParser;
import org.openjdk.nashorn.internal.parser.Lexer;
import org.openjdk.nashorn.internal.parser.Parser;
import org.openjdk.nashorn.internal.parser.TokenType;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.ErrorManager;
import org.openjdk.nashorn.internal.runtime.ParserException;
import org.openjdk.nashorn.internal.runtime.Source;

public final class JSONWriter extends SimpleNodeVisitor {
  private final StringBuilder buf;
  
  private final boolean includeLocation;
  
  public static String parse(Context context, String code, String name, boolean includeLoc) {
    Parser parser = new Parser(context.getEnv(), Source.sourceFor(name, code), (ErrorManager)new Context.ThrowErrorManager(), (context.getEnv())._strict, context.getLogger(Parser.class));
    JSONWriter jsonWriter = new JSONWriter(includeLoc);
    try {
      FunctionNode functionNode = parser.parse();
      functionNode.accept((NodeVisitor)jsonWriter);
      return jsonWriter.getString();
    } catch (ParserException e) {
      e.throwAsEcmaException();
      return null;
    } 
  }
  
  public boolean enterJoinPredecessorExpression(JoinPredecessorExpression joinPredecessorExpression) {
    Expression expr = joinPredecessorExpression.getExpression();
    if (expr != null) {
      expr.accept((NodeVisitor)this);
    } else {
      nullValue();
    } 
    return false;
  }
  
  protected boolean enterDefault(Node node) {
    objectStart();
    location(node);
    return true;
  }
  
  private boolean leave() {
    objectEnd();
    return false;
  }
  
  protected Node leaveDefault(Node node) {
    objectEnd();
    return null;
  }
  
  public boolean enterAccessNode(AccessNode accessNode) {
    enterDefault((Node)accessNode);
    type("MemberExpression");
    comma();
    property("object");
    accessNode.getBase().accept((NodeVisitor)this);
    comma();
    property("property", accessNode.getProperty());
    comma();
    property("computed", false);
    return leave();
  }
  
  public boolean enterBlock(Block block) {
    enterDefault((Node)block);
    type("BlockStatement");
    comma();
    array("body", block.getStatements());
    return leave();
  }
  
  public boolean enterBinaryNode(BinaryNode binaryNode) {
    String name;
    enterDefault((Node)binaryNode);
    if (binaryNode.isAssignment()) {
      name = "AssignmentExpression";
    } else if (binaryNode.isLogical()) {
      name = "LogicalExpression";
    } else {
      name = "BinaryExpression";
    } 
    type(name);
    comma();
    property("operator", binaryNode.tokenType().getName());
    comma();
    property("left");
    binaryNode.lhs().accept((NodeVisitor)this);
    comma();
    property("right");
    binaryNode.rhs().accept((NodeVisitor)this);
    return leave();
  }
  
  public boolean enterBreakNode(BreakNode breakNode) {
    enterDefault((Node)breakNode);
    type("BreakStatement");
    comma();
    String label = breakNode.getLabelName();
    if (label != null) {
      property("label", label);
    } else {
      property("label");
      nullValue();
    } 
    return leave();
  }
  
  public boolean enterCallNode(CallNode callNode) {
    enterDefault((Node)callNode);
    type("CallExpression");
    comma();
    property("callee");
    callNode.getFunction().accept((NodeVisitor)this);
    comma();
    array("arguments", callNode.getArgs());
    return leave();
  }
  
  public boolean enterCaseNode(CaseNode caseNode) {
    enterDefault((Node)caseNode);
    type("SwitchCase");
    comma();
    Expression expression = caseNode.getTest();
    property("test");
    if (expression != null) {
      expression.accept((NodeVisitor)this);
    } else {
      nullValue();
    } 
    comma();
    array("consequent", caseNode.getBody().getStatements());
    return leave();
  }
  
  public boolean enterCatchNode(CatchNode catchNode) {
    enterDefault((Node)catchNode);
    type("CatchClause");
    comma();
    property("param");
    catchNode.getException().accept((NodeVisitor)this);
    comma();
    Expression expression = catchNode.getExceptionCondition();
    if (expression != null) {
      property("guard");
      expression.accept((NodeVisitor)this);
      comma();
    } 
    property("body");
    catchNode.getBody().accept((NodeVisitor)this);
    return leave();
  }
  
  public boolean enterContinueNode(ContinueNode continueNode) {
    enterDefault((Node)continueNode);
    type("ContinueStatement");
    comma();
    String label = continueNode.getLabelName();
    if (label != null) {
      property("label", label);
    } else {
      property("label");
      nullValue();
    } 
    return leave();
  }
  
  public boolean enterDebuggerNode(DebuggerNode debuggerNode) {
    enterDefault((Node)debuggerNode);
    type("DebuggerStatement");
    return leave();
  }
  
  public boolean enterEmptyNode(EmptyNode emptyNode) {
    enterDefault((Node)emptyNode);
    type("EmptyStatement");
    return leave();
  }
  
  public boolean enterExpressionStatement(ExpressionStatement expressionStatement) {
    Expression expression = expressionStatement.getExpression();
    if (expression instanceof RuntimeNode) {
      assert false : "should not reach here: RuntimeNode";
      return false;
    } 
    enterDefault((Node)expressionStatement);
    type("ExpressionStatement");
    comma();
    property("expression");
    expression.accept((NodeVisitor)this);
    return leave();
  }
  
  public boolean enterBlockStatement(BlockStatement blockStatement) {
    if (blockStatement.isSynthetic()) {
      Block blk = blockStatement.getBlock();
      ((Statement)blk.getStatements().get(0)).accept((NodeVisitor)this);
      return false;
    } 
    enterDefault((Node)blockStatement);
    type("BlockStatement");
    comma();
    array("body", blockStatement.getBlock().getStatements());
    return leave();
  }
  
  public boolean enterForNode(ForNode forNode) {
    enterDefault((Node)forNode);
    if (forNode.isForIn() || (forNode.isForEach() && forNode.getInit() != null)) {
      type("ForInStatement");
      comma();
      Expression expression = forNode.getInit();
      assert expression != null;
      property("left");
      expression.accept((NodeVisitor)this);
      comma();
      JoinPredecessorExpression joinPredecessorExpression = forNode.getModify();
      assert joinPredecessorExpression != null;
      property("right");
      joinPredecessorExpression.accept((NodeVisitor)this);
      comma();
      property("body");
      forNode.getBody().accept((NodeVisitor)this);
      comma();
      property("each", forNode.isForEach());
    } else {
      type("ForStatement");
      comma();
      Expression expression = forNode.getInit();
      property("init");
      if (expression != null) {
        expression.accept((NodeVisitor)this);
      } else {
        nullValue();
      } 
      comma();
      JoinPredecessorExpression joinPredecessorExpression1 = forNode.getTest();
      property("test");
      if (joinPredecessorExpression1 != null) {
        joinPredecessorExpression1.accept((NodeVisitor)this);
      } else {
        nullValue();
      } 
      comma();
      JoinPredecessorExpression joinPredecessorExpression2 = forNode.getModify();
      property("update");
      if (joinPredecessorExpression2 != null) {
        joinPredecessorExpression2.accept((NodeVisitor)this);
      } else {
        nullValue();
      } 
      comma();
      property("body");
      forNode.getBody().accept((NodeVisitor)this);
    } 
    return leave();
  }
  
  public boolean enterFunctionNode(FunctionNode functionNode) {
    String name;
    boolean program = functionNode.isProgram();
    if (program)
      return emitProgram(functionNode); 
    enterDefault((Node)functionNode);
    if (functionNode.isDeclared()) {
      name = "FunctionDeclaration";
    } else {
      name = "FunctionExpression";
    } 
    type(name);
    comma();
    property("id");
    FunctionNode.Kind kind = functionNode.getKind();
    if (functionNode.isAnonymous() || kind == FunctionNode.Kind.GETTER || kind == FunctionNode.Kind.SETTER) {
      nullValue();
    } else {
      functionNode.getIdent().accept((NodeVisitor)this);
    } 
    comma();
    array("params", functionNode.getParameters());
    comma();
    arrayStart("defaults");
    arrayEnd();
    comma();
    property("rest");
    nullValue();
    comma();
    property("body");
    functionNode.getBody().accept((NodeVisitor)this);
    comma();
    property("generator", false);
    comma();
    property("expression", false);
    return leave();
  }
  
  private boolean emitProgram(FunctionNode functionNode) {
    enterDefault((Node)functionNode);
    type("Program");
    comma();
    List<Statement> stats = functionNode.getBody().getStatements();
    int size = stats.size();
    int idx = 0;
    arrayStart("body");
    for (Node stat : stats) {
      stat.accept((NodeVisitor)this);
      if (idx != size - 1)
        comma(); 
      idx++;
    } 
    arrayEnd();
    return leave();
  }
  
  public boolean enterIdentNode(IdentNode identNode) {
    enterDefault((Node)identNode);
    String name = identNode.getName();
    if ("this".equals(name)) {
      type("ThisExpression");
    } else {
      type("Identifier");
      comma();
      property("name", identNode.getName());
    } 
    return leave();
  }
  
  public boolean enterIfNode(IfNode ifNode) {
    enterDefault((Node)ifNode);
    type("IfStatement");
    comma();
    property("test");
    ifNode.getTest().accept((NodeVisitor)this);
    comma();
    property("consequent");
    ifNode.getPass().accept((NodeVisitor)this);
    Block block = ifNode.getFail();
    comma();
    property("alternate");
    if (block != null) {
      block.accept((NodeVisitor)this);
    } else {
      nullValue();
    } 
    return leave();
  }
  
  public boolean enterIndexNode(IndexNode indexNode) {
    enterDefault((Node)indexNode);
    type("MemberExpression");
    comma();
    property("object");
    indexNode.getBase().accept((NodeVisitor)this);
    comma();
    property("property");
    indexNode.getIndex().accept((NodeVisitor)this);
    comma();
    property("computed", true);
    return leave();
  }
  
  public boolean enterLabelNode(LabelNode labelNode) {
    enterDefault((Node)labelNode);
    type("LabeledStatement");
    comma();
    property("label", labelNode.getLabelName());
    comma();
    property("body");
    labelNode.getBody().accept((NodeVisitor)this);
    return leave();
  }
  
  public boolean enterLiteralNode(LiteralNode literalNode) {
    enterDefault((Node)literalNode);
    if (literalNode instanceof LiteralNode.ArrayLiteralNode) {
      type("ArrayExpression");
      comma();
      array("elements", ((LiteralNode.ArrayLiteralNode)literalNode).getElementExpressions());
    } else {
      type("Literal");
      comma();
      property("value");
      Object value = literalNode.getValue();
      if (value instanceof Lexer.RegexToken) {
        Lexer.RegexToken regex = (Lexer.RegexToken)value;
        StringBuilder regexBuf = new StringBuilder();
        regexBuf.append('/');
        regexBuf.append(regex.getExpression());
        regexBuf.append('/');
        regexBuf.append(regex.getOptions());
        this.buf.append(quote(regexBuf.toString()));
      } else {
        String str = literalNode.getString();
        this.buf.append(literalNode.isString() ? quote("$" + str) : str);
      } 
    } 
    return leave();
  }
  
  public boolean enterObjectNode(ObjectNode objectNode) {
    enterDefault((Node)objectNode);
    type("ObjectExpression");
    comma();
    array("properties", objectNode.getElements());
    return leave();
  }
  
  public boolean enterPropertyNode(PropertyNode propertyNode) {
    Expression expression1 = propertyNode.getKey();
    Expression expression2 = propertyNode.getValue();
    if (expression2 != null) {
      objectStart();
      location((Node)propertyNode);
      property("key");
      expression1.accept((NodeVisitor)this);
      comma();
      property("value");
      expression2.accept((NodeVisitor)this);
      comma();
      property("kind", "init");
      objectEnd();
    } else {
      FunctionNode functionNode1 = propertyNode.getGetter();
      if (functionNode1 != null) {
        objectStart();
        location((Node)propertyNode);
        property("key");
        expression1.accept((NodeVisitor)this);
        comma();
        property("value");
        functionNode1.accept((NodeVisitor)this);
        comma();
        property("kind", "get");
        objectEnd();
      } 
      FunctionNode functionNode2 = propertyNode.getSetter();
      if (functionNode2 != null) {
        if (functionNode1 != null)
          comma(); 
        objectStart();
        location((Node)propertyNode);
        property("key");
        expression1.accept((NodeVisitor)this);
        comma();
        property("value");
        functionNode2.accept((NodeVisitor)this);
        comma();
        property("kind", "set");
        objectEnd();
      } 
    } 
    return false;
  }
  
  public boolean enterReturnNode(ReturnNode returnNode) {
    enterDefault((Node)returnNode);
    type("ReturnStatement");
    comma();
    Expression expression = returnNode.getExpression();
    property("argument");
    if (expression != null) {
      expression.accept((NodeVisitor)this);
    } else {
      nullValue();
    } 
    return leave();
  }
  
  public boolean enterRuntimeNode(RuntimeNode runtimeNode) {
    assert false : "should not reach here: RuntimeNode";
    return false;
  }
  
  public boolean enterSplitNode(SplitNode splitNode) {
    assert false : "should not reach here: SplitNode";
    return false;
  }
  
  public boolean enterSwitchNode(SwitchNode switchNode) {
    enterDefault((Node)switchNode);
    type("SwitchStatement");
    comma();
    property("discriminant");
    switchNode.getExpression().accept((NodeVisitor)this);
    comma();
    array("cases", switchNode.getCases());
    return leave();
  }
  
  public boolean enterTernaryNode(TernaryNode ternaryNode) {
    enterDefault((Node)ternaryNode);
    type("ConditionalExpression");
    comma();
    property("test");
    ternaryNode.getTest().accept((NodeVisitor)this);
    comma();
    property("consequent");
    ternaryNode.getTrueExpression().accept((NodeVisitor)this);
    comma();
    property("alternate");
    ternaryNode.getFalseExpression().accept((NodeVisitor)this);
    return leave();
  }
  
  public boolean enterThrowNode(ThrowNode throwNode) {
    enterDefault((Node)throwNode);
    type("ThrowStatement");
    comma();
    property("argument");
    throwNode.getExpression().accept((NodeVisitor)this);
    return leave();
  }
  
  public boolean enterTryNode(TryNode tryNode) {
    enterDefault((Node)tryNode);
    type("TryStatement");
    comma();
    property("block");
    tryNode.getBody().accept((NodeVisitor)this);
    comma();
    List<? extends Node> catches = tryNode.getCatches();
    List<CatchNode> guarded = new ArrayList<>();
    CatchNode unguarded = null;
    if (catches != null)
      for (Node n : catches) {
        CatchNode cn = (CatchNode)n;
        if (cn.getExceptionCondition() != null) {
          guarded.add(cn);
          continue;
        } 
        assert unguarded == null : "too many unguarded?";
        unguarded = cn;
      }  
    array("guardedHandlers", (List)guarded);
    comma();
    property("handler");
    if (unguarded != null) {
      unguarded.accept((NodeVisitor)this);
    } else {
      nullValue();
    } 
    comma();
    property("finalizer");
    Block block = tryNode.getFinallyBody();
    if (block != null) {
      block.accept((NodeVisitor)this);
    } else {
      nullValue();
    } 
    return leave();
  }
  
  public boolean enterUnaryNode(UnaryNode unaryNode) {
    enterDefault((Node)unaryNode);
    TokenType tokenType = unaryNode.tokenType();
    if (tokenType == TokenType.NEW) {
      type("NewExpression");
      comma();
      CallNode callNode = (CallNode)unaryNode.getExpression();
      property("callee");
      callNode.getFunction().accept((NodeVisitor)this);
      comma();
      array("arguments", callNode.getArgs());
    } else {
      String operator;
      boolean prefix;
      switch (tokenType) {
        case INCPOSTFIX:
          prefix = false;
          operator = "++";
          break;
        case DECPOSTFIX:
          prefix = false;
          operator = "--";
          break;
        case INCPREFIX:
          operator = "++";
          prefix = true;
          break;
        case DECPREFIX:
          operator = "--";
          prefix = true;
          break;
        default:
          prefix = true;
          operator = tokenType.getName();
          break;
      } 
      type(unaryNode.isAssignment() ? "UpdateExpression" : "UnaryExpression");
      comma();
      property("operator", operator);
      comma();
      property("prefix", prefix);
      comma();
      property("argument");
      unaryNode.getExpression().accept((NodeVisitor)this);
    } 
    return leave();
  }
  
  public boolean enterVarNode(VarNode varNode) {
    Expression expression = varNode.getInit();
    if (expression instanceof FunctionNode && ((FunctionNode)expression).isDeclared()) {
      expression.accept((NodeVisitor)this);
      return false;
    } 
    enterDefault((Node)varNode);
    type("VariableDeclaration");
    comma();
    arrayStart("declarations");
    objectStart();
    location((Node)varNode.getName());
    type("VariableDeclarator");
    comma();
    property("id");
    varNode.getName().accept((NodeVisitor)this);
    comma();
    property("init");
    if (expression != null) {
      expression.accept((NodeVisitor)this);
    } else {
      nullValue();
    } 
    objectEnd();
    arrayEnd();
    return leave();
  }
  
  public boolean enterWhileNode(WhileNode whileNode) {
    enterDefault((Node)whileNode);
    type(whileNode.isDoWhile() ? "DoWhileStatement" : "WhileStatement");
    comma();
    if (whileNode.isDoWhile()) {
      property("body");
      whileNode.getBody().accept((NodeVisitor)this);
      comma();
      property("test");
      whileNode.getTest().accept((NodeVisitor)this);
    } else {
      property("test");
      whileNode.getTest().accept((NodeVisitor)this);
      comma();
      property("body");
      whileNode.getBody().accept((NodeVisitor)this);
    } 
    return leave();
  }
  
  public boolean enterWithNode(WithNode withNode) {
    enterDefault((Node)withNode);
    type("WithStatement");
    comma();
    property("object");
    withNode.getExpression().accept((NodeVisitor)this);
    comma();
    property("body");
    withNode.getBody().accept((NodeVisitor)this);
    return leave();
  }
  
  private JSONWriter(boolean includeLocation) {
    this.buf = new StringBuilder();
    this.includeLocation = includeLocation;
  }
  
  private String getString() {
    return this.buf.toString();
  }
  
  private void property(String key, String value, boolean escape) {
    this.buf.append('"');
    this.buf.append(key);
    this.buf.append("\":");
    if (value != null) {
      if (escape)
        this.buf.append('"'); 
      this.buf.append(value);
      if (escape)
        this.buf.append('"'); 
    } 
  }
  
  private void property(String key, String value) {
    property(key, value, true);
  }
  
  private void property(String key, boolean value) {
    property(key, Boolean.toString(value), false);
  }
  
  private void property(String key, int value) {
    property(key, Integer.toString(value), false);
  }
  
  private void property(String key) {
    property(key, (String)null);
  }
  
  private void type(String value) {
    property("type", value);
  }
  
  private void objectStart(String name) {
    this.buf.append('"');
    this.buf.append(name);
    this.buf.append("\":{");
  }
  
  private void objectStart() {
    this.buf.append('{');
  }
  
  private void objectEnd() {
    this.buf.append('}');
  }
  
  private void array(String name, List<? extends Node> nodes) {
    int size = nodes.size();
    int idx = 0;
    arrayStart(name);
    for (Node node : nodes) {
      if (node != null) {
        node.accept((NodeVisitor)this);
      } else {
        nullValue();
      } 
      if (idx != size - 1)
        comma(); 
      idx++;
    } 
    arrayEnd();
  }
  
  private void arrayStart(String name) {
    this.buf.append('"');
    this.buf.append(name);
    this.buf.append('"');
    this.buf.append(':');
    this.buf.append('[');
  }
  
  private void arrayEnd() {
    this.buf.append(']');
  }
  
  private void comma() {
    this.buf.append(',');
  }
  
  private void nullValue() {
    this.buf.append("null");
  }
  
  private void location(Node node) {
    if (this.includeLocation) {
      objectStart("loc");
      Source src = this.lc.getCurrentFunction().getSource();
      property("source", src.getName());
      comma();
      objectStart("start");
      int start = node.getStart();
      property("line", src.getLine(start));
      comma();
      property("column", src.getColumn(start));
      objectEnd();
      comma();
      objectStart("end");
      int end = node.getFinish();
      property("line", src.getLine(end));
      comma();
      property("column", src.getColumn(end));
      objectEnd();
      objectEnd();
      comma();
    } 
  }
  
  private static String quote(String str) {
    return JSONParser.quote(str);
  }
}
