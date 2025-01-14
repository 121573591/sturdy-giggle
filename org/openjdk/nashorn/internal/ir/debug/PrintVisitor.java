package org.openjdk.nashorn.internal.ir.debug;

import java.util.List;
import org.openjdk.nashorn.internal.ir.BinaryNode;
import org.openjdk.nashorn.internal.ir.Block;
import org.openjdk.nashorn.internal.ir.BlockStatement;
import org.openjdk.nashorn.internal.ir.BreakNode;
import org.openjdk.nashorn.internal.ir.CaseNode;
import org.openjdk.nashorn.internal.ir.CatchNode;
import org.openjdk.nashorn.internal.ir.ContinueNode;
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.ExpressionStatement;
import org.openjdk.nashorn.internal.ir.ForNode;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.IfNode;
import org.openjdk.nashorn.internal.ir.JoinPredecessor;
import org.openjdk.nashorn.internal.ir.JoinPredecessorExpression;
import org.openjdk.nashorn.internal.ir.LabelNode;
import org.openjdk.nashorn.internal.ir.LocalVariableConversion;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.SplitNode;
import org.openjdk.nashorn.internal.ir.Statement;
import org.openjdk.nashorn.internal.ir.SwitchNode;
import org.openjdk.nashorn.internal.ir.ThrowNode;
import org.openjdk.nashorn.internal.ir.TryNode;
import org.openjdk.nashorn.internal.ir.UnaryNode;
import org.openjdk.nashorn.internal.ir.VarNode;
import org.openjdk.nashorn.internal.ir.WhileNode;
import org.openjdk.nashorn.internal.ir.WithNode;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.ir.visitor.SimpleNodeVisitor;

public final class PrintVisitor extends SimpleNodeVisitor {
  private static final int TABWIDTH = 4;
  
  private final StringBuilder sb;
  
  private int indent;
  
  private final String EOLN;
  
  private final boolean printLineNumbers;
  
  private final boolean printTypes;
  
  private int lastLineNumber = -1;
  
  public PrintVisitor() {
    this(true, true);
  }
  
  public PrintVisitor(boolean printLineNumbers, boolean printTypes) {
    this.EOLN = System.lineSeparator();
    this.sb = new StringBuilder();
    this.printLineNumbers = printLineNumbers;
    this.printTypes = printTypes;
  }
  
  public PrintVisitor(Node root) {
    this(root, true, true);
  }
  
  public PrintVisitor(Node root, boolean printLineNumbers, boolean printTypes) {
    this(printLineNumbers, printTypes);
    visit(root);
  }
  
  private void visit(Node root) {
    root.accept((NodeVisitor)this);
  }
  
  public String toString() {
    return this.sb.append(this.EOLN).toString();
  }
  
  private void indent() {
    for (int i = this.indent; i > 0; i--)
      this.sb.append(' '); 
  }
  
  public boolean enterDefault(Node node) {
    node.toString(this.sb, this.printTypes);
    return false;
  }
  
  public boolean enterContinueNode(ContinueNode node) {
    node.toString(this.sb, this.printTypes);
    printLocalVariableConversion((JoinPredecessor)node);
    return false;
  }
  
  public boolean enterBreakNode(BreakNode node) {
    node.toString(this.sb, this.printTypes);
    printLocalVariableConversion((JoinPredecessor)node);
    return false;
  }
  
  public boolean enterThrowNode(ThrowNode node) {
    node.toString(this.sb, this.printTypes);
    printLocalVariableConversion((JoinPredecessor)node);
    return false;
  }
  
  public boolean enterBlock(Block block) {
    this.sb.append(' ');
    this.sb.append('{');
    this.indent += 4;
    List<Statement> statements = block.getStatements();
    for (Statement statement : statements) {
      if (this.printLineNumbers) {
        int lineNumber = statement.getLineNumber();
        this.sb.append('\n');
        if (lineNumber != this.lastLineNumber) {
          indent();
          this.sb.append("[|").append(lineNumber).append("|];").append('\n');
        } 
        this.lastLineNumber = lineNumber;
      } 
      indent();
      statement.accept((NodeVisitor)this);
      int lastIndex = this.sb.length() - 1;
      char lastChar = this.sb.charAt(lastIndex);
      while (Character.isWhitespace(lastChar) && lastIndex >= 0)
        lastChar = this.sb.charAt(--lastIndex); 
      if (lastChar != '}' && lastChar != ';')
        this.sb.append(';'); 
      if (statement.hasGoto())
        this.sb.append(" [GOTO]"); 
      if (statement.isTerminal())
        this.sb.append(" [TERMINAL]"); 
    } 
    this.indent -= 4;
    this.sb.append(this.EOLN);
    indent();
    this.sb.append('}');
    printLocalVariableConversion((JoinPredecessor)block);
    return false;
  }
  
  public boolean enterBlockStatement(BlockStatement statement) {
    statement.getBlock().accept((NodeVisitor)this);
    return false;
  }
  
  public boolean enterBinaryNode(BinaryNode binaryNode) {
    binaryNode.lhs().accept((NodeVisitor)this);
    this.sb.append(' ');
    this.sb.append(binaryNode.tokenType());
    this.sb.append(' ');
    binaryNode.rhs().accept((NodeVisitor)this);
    return false;
  }
  
  public boolean enterJoinPredecessorExpression(JoinPredecessorExpression expr) {
    expr.getExpression().accept((NodeVisitor)this);
    printLocalVariableConversion((JoinPredecessor)expr);
    return false;
  }
  
  public boolean enterIdentNode(IdentNode identNode) {
    identNode.toString(this.sb, this.printTypes);
    printLocalVariableConversion((JoinPredecessor)identNode);
    return true;
  }
  
  private void printLocalVariableConversion(JoinPredecessor joinPredecessor) {
    LocalVariableConversion.toString(joinPredecessor.getLocalVariableConversion(), this.sb);
  }
  
  public boolean enterUnaryNode(UnaryNode unaryNode) {
    unaryNode.toString(this.sb, () -> unaryNode.getExpression().accept((NodeVisitor)this));
    return false;
  }
  
  public boolean enterExpressionStatement(ExpressionStatement expressionStatement) {
    expressionStatement.getExpression().accept((NodeVisitor)this);
    return false;
  }
  
  public boolean enterForNode(ForNode forNode) {
    forNode.toString(this.sb, this.printTypes);
    forNode.getBody().accept((NodeVisitor)this);
    return false;
  }
  
  public boolean enterFunctionNode(FunctionNode functionNode) {
    functionNode.toString(this.sb, this.printTypes);
    enterBlock(functionNode.getBody());
    return false;
  }
  
  public boolean enterIfNode(IfNode ifNode) {
    ifNode.toString(this.sb, this.printTypes);
    ifNode.getPass().accept((NodeVisitor)this);
    Block fail = ifNode.getFail();
    if (fail != null) {
      this.sb.append(" else ");
      fail.accept((NodeVisitor)this);
    } 
    if (ifNode.getLocalVariableConversion() != null) {
      assert fail == null;
      this.sb.append(" else ");
      printLocalVariableConversion((JoinPredecessor)ifNode);
      this.sb.append(";");
    } 
    return false;
  }
  
  public boolean enterLabelNode(LabelNode labeledNode) {
    this.indent -= 4;
    indent();
    this.indent += 4;
    labeledNode.toString(this.sb, this.printTypes);
    labeledNode.getBody().accept((NodeVisitor)this);
    printLocalVariableConversion((JoinPredecessor)labeledNode);
    return false;
  }
  
  public boolean enterSplitNode(SplitNode splitNode) {
    splitNode.toString(this.sb, this.printTypes);
    this.sb.append(this.EOLN);
    this.indent += 4;
    indent();
    return true;
  }
  
  public Node leaveSplitNode(SplitNode splitNode) {
    this.sb.append("</split>");
    this.sb.append(this.EOLN);
    this.indent -= 4;
    indent();
    return (Node)splitNode;
  }
  
  public boolean enterSwitchNode(SwitchNode switchNode) {
    switchNode.toString(this.sb, this.printTypes);
    this.sb.append(" {");
    List<CaseNode> cases = switchNode.getCases();
    for (CaseNode caseNode : cases) {
      this.sb.append(this.EOLN);
      indent();
      caseNode.toString(this.sb, this.printTypes);
      printLocalVariableConversion((JoinPredecessor)caseNode);
      this.indent += 4;
      caseNode.getBody().accept((NodeVisitor)this);
      this.indent -= 4;
      this.sb.append(this.EOLN);
    } 
    if (switchNode.getLocalVariableConversion() != null) {
      this.sb.append(this.EOLN);
      indent();
      this.sb.append("default: ");
      printLocalVariableConversion((JoinPredecessor)switchNode);
      this.sb.append("{}");
    } 
    this.sb.append(this.EOLN);
    indent();
    this.sb.append("}");
    return false;
  }
  
  public boolean enterTryNode(TryNode tryNode) {
    tryNode.toString(this.sb, this.printTypes);
    printLocalVariableConversion((JoinPredecessor)tryNode);
    tryNode.getBody().accept((NodeVisitor)this);
    List<Block> catchBlocks = tryNode.getCatchBlocks();
    for (Block catchBlock : catchBlocks) {
      CatchNode catchNode = catchBlock.getStatements().get(0);
      catchNode.toString(this.sb, this.printTypes);
      catchNode.getBody().accept((NodeVisitor)this);
    } 
    Block finallyBody = tryNode.getFinallyBody();
    if (finallyBody != null) {
      this.sb.append(" finally ");
      finallyBody.accept((NodeVisitor)this);
    } 
    for (Block inlinedFinally : tryNode.getInlinedFinallies())
      inlinedFinally.accept((NodeVisitor)this); 
    return false;
  }
  
  public boolean enterVarNode(VarNode varNode) {
    this.sb.append(varNode.isConst() ? "const " : (varNode.isLet() ? "let " : "var "));
    varNode.getName().toString(this.sb, this.printTypes);
    printLocalVariableConversion((JoinPredecessor)varNode.getName());
    Expression expression = varNode.getInit();
    if (expression != null) {
      this.sb.append(" = ");
      expression.accept((NodeVisitor)this);
    } 
    return false;
  }
  
  public boolean enterWhileNode(WhileNode whileNode) {
    printLocalVariableConversion((JoinPredecessor)whileNode);
    if (whileNode.isDoWhile()) {
      this.sb.append("do");
      whileNode.getBody().accept((NodeVisitor)this);
      this.sb.append(' ');
      whileNode.toString(this.sb, this.printTypes);
    } else {
      whileNode.toString(this.sb, this.printTypes);
      whileNode.getBody().accept((NodeVisitor)this);
    } 
    return false;
  }
  
  public boolean enterWithNode(WithNode withNode) {
    withNode.toString(this.sb, this.printTypes);
    withNode.getBody().accept((NodeVisitor)this);
    return false;
  }
}
