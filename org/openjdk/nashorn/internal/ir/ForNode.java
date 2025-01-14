package org.openjdk.nashorn.internal.ir;

import org.openjdk.nashorn.internal.ir.annotations.Immutable;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

@Immutable
public final class ForNode extends LoopNode {
  private static final long serialVersionUID = 1L;
  
  private final Expression init;
  
  private final JoinPredecessorExpression modify;
  
  private final Symbol iterator;
  
  public static final int IS_FOR_IN = 1;
  
  public static final int IS_FOR_EACH = 2;
  
  public static final int IS_FOR_OF = 4;
  
  public static final int PER_ITERATION_SCOPE = 8;
  
  private final int flags;
  
  public ForNode(int lineNumber, long token, int finish, Block body, int flags) {
    this(lineNumber, token, finish, body, flags, (Expression)null, (JoinPredecessorExpression)null, (JoinPredecessorExpression)null);
  }
  
  public ForNode(int lineNumber, long token, int finish, Block body, int flags, Expression init, JoinPredecessorExpression test, JoinPredecessorExpression modify) {
    super(lineNumber, token, finish, body, test, false);
    this.flags = flags;
    this.init = init;
    this.modify = modify;
    this.iterator = null;
  }
  
  private ForNode(ForNode forNode, Expression init, JoinPredecessorExpression test, Block body, JoinPredecessorExpression modify, int flags, boolean controlFlowEscapes, LocalVariableConversion conversion, Symbol iterator) {
    super(forNode, test, body, controlFlowEscapes, conversion);
    this.init = init;
    this.modify = modify;
    this.flags = flags;
    this.iterator = iterator;
  }
  
  public Node ensureUniqueLabels(LexicalContext lc) {
    return Node.<Node>replaceInLexicalContext(lc, this, new ForNode(this, this.init, this.test, this.body, this.modify, this.flags, this.controlFlowEscapes, this.conversion, this.iterator));
  }
  
  public Node accept(LexicalContext lc, NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterForNode(this))
      return visitor.leaveForNode(
          setInit(lc, (this.init == null) ? null : (Expression)this.init.accept(visitor))
          .setTest(lc, (this.test == null) ? null : (JoinPredecessorExpression)this.test.accept(visitor))
          .setModify(lc, (this.modify == null) ? null : (JoinPredecessorExpression)this.modify.accept(visitor))
          .setBody(lc, (Block)this.body.accept(visitor))); 
    return this;
  }
  
  public void toString(StringBuilder sb, boolean printTypes) {
    sb.append("for");
    LocalVariableConversion.toString(this.conversion, sb).append(' ');
    if (isForIn()) {
      this.init.toString(sb, printTypes);
      sb.append(" in ");
      this.modify.toString(sb, printTypes);
    } else if (isForOf()) {
      this.init.toString(sb, printTypes);
      sb.append(" of ");
      this.modify.toString(sb, printTypes);
    } else {
      if (this.init != null)
        this.init.toString(sb, printTypes); 
      sb.append("; ");
      if (this.test != null)
        this.test.toString(sb, printTypes); 
      sb.append("; ");
      if (this.modify != null)
        this.modify.toString(sb, printTypes); 
    } 
    sb.append(')');
  }
  
  public boolean hasGoto() {
    return (!isForInOrOf() && this.test == null);
  }
  
  public boolean mustEnter() {
    if (isForInOrOf())
      return false; 
    return (this.test == null);
  }
  
  public Expression getInit() {
    return this.init;
  }
  
  public ForNode setInit(LexicalContext lc, Expression init) {
    if (this.init == init)
      return this; 
    return Node.<ForNode>replaceInLexicalContext(lc, this, new ForNode(this, init, this.test, this.body, this.modify, this.flags, this.controlFlowEscapes, this.conversion, this.iterator));
  }
  
  public boolean isForIn() {
    return ((this.flags & 0x1) != 0);
  }
  
  public boolean isForOf() {
    return ((this.flags & 0x4) != 0);
  }
  
  public boolean isForInOrOf() {
    return (isForIn() || isForOf());
  }
  
  public boolean isForEach() {
    return ((this.flags & 0x2) != 0);
  }
  
  public Symbol getIterator() {
    return this.iterator;
  }
  
  public ForNode setIterator(LexicalContext lc, Symbol iterator) {
    if (this.iterator == iterator)
      return this; 
    return Node.<ForNode>replaceInLexicalContext(lc, this, new ForNode(this, this.init, this.test, this.body, this.modify, this.flags, this.controlFlowEscapes, this.conversion, iterator));
  }
  
  public JoinPredecessorExpression getModify() {
    return this.modify;
  }
  
  public ForNode setModify(LexicalContext lc, JoinPredecessorExpression modify) {
    if (this.modify == modify)
      return this; 
    return Node.<ForNode>replaceInLexicalContext(lc, this, new ForNode(this, this.init, this.test, this.body, modify, this.flags, this.controlFlowEscapes, this.conversion, this.iterator));
  }
  
  public ForNode setTest(LexicalContext lc, JoinPredecessorExpression test) {
    if (this.test == test)
      return this; 
    return Node.<ForNode>replaceInLexicalContext(lc, this, new ForNode(this, this.init, test, this.body, this.modify, this.flags, this.controlFlowEscapes, this.conversion, this.iterator));
  }
  
  public Block getBody() {
    return this.body;
  }
  
  public ForNode setBody(LexicalContext lc, Block body) {
    if (this.body == body)
      return this; 
    return Node.<ForNode>replaceInLexicalContext(lc, this, new ForNode(this, this.init, this.test, body, this.modify, this.flags, this.controlFlowEscapes, this.conversion, this.iterator));
  }
  
  public ForNode setControlFlowEscapes(LexicalContext lc, boolean controlFlowEscapes) {
    if (this.controlFlowEscapes == controlFlowEscapes)
      return this; 
    return Node.<ForNode>replaceInLexicalContext(lc, this, new ForNode(this, this.init, this.test, this.body, this.modify, this.flags, controlFlowEscapes, this.conversion, this.iterator));
  }
  
  JoinPredecessor setLocalVariableConversionChanged(LexicalContext lc, LocalVariableConversion conversion) {
    return Node.<JoinPredecessor>replaceInLexicalContext(lc, this, new ForNode(this, this.init, this.test, this.body, this.modify, this.flags, this.controlFlowEscapes, conversion, this.iterator));
  }
  
  public boolean hasPerIterationScope() {
    return ((this.flags & 0x8) != 0);
  }
  
  public boolean needsScopeCreator() {
    return (isForInOrOf() && hasPerIterationScope());
  }
}
