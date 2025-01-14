package org.openjdk.nashorn.internal.ir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openjdk.nashorn.internal.ir.annotations.Immutable;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

@Immutable
public final class TryNode extends LexicalContextStatement implements JoinPredecessor {
  private static final long serialVersionUID = 1L;
  
  private final Block body;
  
  private final List<Block> catchBlocks;
  
  private final Block finallyBody;
  
  private final List<Block> inlinedFinallies;
  
  private final Symbol exception;
  
  private final LocalVariableConversion conversion;
  
  public TryNode(int lineNumber, long token, int finish, Block body, List<Block> catchBlocks, Block finallyBody) {
    super(lineNumber, token, finish);
    this.body = body;
    this.catchBlocks = catchBlocks;
    this.finallyBody = finallyBody;
    this.conversion = null;
    this.inlinedFinallies = Collections.emptyList();
    this.exception = null;
  }
  
  private TryNode(TryNode tryNode, Block body, List<Block> catchBlocks, Block finallyBody, LocalVariableConversion conversion, List<Block> inlinedFinallies, Symbol exception) {
    super(tryNode);
    this.body = body;
    this.catchBlocks = catchBlocks;
    this.finallyBody = finallyBody;
    this.conversion = conversion;
    this.inlinedFinallies = inlinedFinallies;
    this.exception = exception;
  }
  
  public Node ensureUniqueLabels(LexicalContext lc) {
    return new TryNode(this, this.body, this.catchBlocks, this.finallyBody, this.conversion, this.inlinedFinallies, this.exception);
  }
  
  public boolean isTerminal() {
    if (this.body.isTerminal()) {
      for (Block catchBlock : getCatchBlocks()) {
        if (!catchBlock.isTerminal())
          return false; 
      } 
      return true;
    } 
    return false;
  }
  
  public Node accept(LexicalContext lc, NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterTryNode(this)) {
      Block newFinallyBody = (this.finallyBody == null) ? null : (Block)this.finallyBody.accept(visitor);
      Block newBody = (Block)this.body.accept(visitor);
      return visitor.leaveTryNode(
          setBody(lc, newBody)
          .setFinallyBody(lc, newFinallyBody)
          .setCatchBlocks(lc, Node.accept(visitor, this.catchBlocks))
          .setInlinedFinallies(lc, Node.accept(visitor, this.inlinedFinallies)));
    } 
    return this;
  }
  
  public void toString(StringBuilder sb, boolean printType) {
    sb.append("try ");
  }
  
  public Block getBody() {
    return this.body;
  }
  
  public TryNode setBody(LexicalContext lc, Block body) {
    if (this.body == body)
      return this; 
    return Node.<TryNode>replaceInLexicalContext(lc, this, new TryNode(this, body, this.catchBlocks, this.finallyBody, this.conversion, this.inlinedFinallies, this.exception));
  }
  
  public List<CatchNode> getCatches() {
    List<CatchNode> catches = new ArrayList<>(this.catchBlocks.size());
    for (Block catchBlock : this.catchBlocks)
      catches.add(getCatchNodeFromBlock(catchBlock)); 
    return Collections.unmodifiableList(catches);
  }
  
  private static CatchNode getCatchNodeFromBlock(Block catchBlock) {
    return (CatchNode)catchBlock.getStatements().get(0);
  }
  
  public List<Block> getCatchBlocks() {
    return Collections.unmodifiableList(this.catchBlocks);
  }
  
  public TryNode setCatchBlocks(LexicalContext lc, List<Block> catchBlocks) {
    if (this.catchBlocks == catchBlocks)
      return this; 
    return Node.<TryNode>replaceInLexicalContext(lc, this, new TryNode(this, this.body, catchBlocks, this.finallyBody, this.conversion, this.inlinedFinallies, this.exception));
  }
  
  public Symbol getException() {
    return this.exception;
  }
  
  public TryNode setException(LexicalContext lc, Symbol exception) {
    if (this.exception == exception)
      return this; 
    return Node.<TryNode>replaceInLexicalContext(lc, this, new TryNode(this, this.body, this.catchBlocks, this.finallyBody, this.conversion, this.inlinedFinallies, exception));
  }
  
  public Block getFinallyBody() {
    return this.finallyBody;
  }
  
  public Block getInlinedFinally(String labelName) {
    for (Block inlinedFinally : this.inlinedFinallies) {
      LabelNode labelNode = getInlinedFinallyLabelNode(inlinedFinally);
      if (labelNode.getLabelName().equals(labelName))
        return labelNode.getBody(); 
    } 
    return null;
  }
  
  private static LabelNode getInlinedFinallyLabelNode(Block inlinedFinally) {
    return (LabelNode)inlinedFinally.getStatements().get(0);
  }
  
  public static Block getLabelledInlinedFinallyBlock(Block inlinedFinally) {
    return getInlinedFinallyLabelNode(inlinedFinally).getBody();
  }
  
  public List<Block> getInlinedFinallies() {
    return Collections.unmodifiableList(this.inlinedFinallies);
  }
  
  public TryNode setFinallyBody(LexicalContext lc, Block finallyBody) {
    if (this.finallyBody == finallyBody)
      return this; 
    return Node.<TryNode>replaceInLexicalContext(lc, this, new TryNode(this, this.body, this.catchBlocks, finallyBody, this.conversion, this.inlinedFinallies, this.exception));
  }
  
  public TryNode setInlinedFinallies(LexicalContext lc, List<Block> inlinedFinallies) {
    if (this.inlinedFinallies == inlinedFinallies)
      return this; 
    assert checkInlinedFinallies(inlinedFinallies);
    return Node.<TryNode>replaceInLexicalContext(lc, this, new TryNode(this, this.body, this.catchBlocks, this.finallyBody, this.conversion, inlinedFinallies, this.exception));
  }
  
  private static boolean checkInlinedFinallies(List<Block> inlinedFinallies) {
    if (!inlinedFinallies.isEmpty()) {
      Set<String> labels = new HashSet<>();
      for (Block inlinedFinally : inlinedFinallies) {
        List<Statement> stmts = inlinedFinally.getStatements();
        assert stmts.size() == 1;
        LabelNode ln = getInlinedFinallyLabelNode(inlinedFinally);
        assert labels.add(ln.getLabelName());
      } 
    } 
    return true;
  }
  
  public JoinPredecessor setLocalVariableConversion(LexicalContext lc, LocalVariableConversion conversion) {
    if (this.conversion == conversion)
      return this; 
    return new TryNode(this, this.body, this.catchBlocks, this.finallyBody, conversion, this.inlinedFinallies, this.exception);
  }
  
  public LocalVariableConversion getLocalVariableConversion() {
    return this.conversion;
  }
}
