package org.openjdk.nashorn.internal.ir;

import java.util.List;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

public class BlockStatement extends Statement {
  private static final long serialVersionUID = 1L;
  
  private final Block block;
  
  public BlockStatement(Block block) {
    this(block.getFirstStatementLineNumber(), block);
  }
  
  public BlockStatement(int lineNumber, Block block) {
    super(lineNumber, block.getToken(), block.getFinish());
    this.block = block;
  }
  
  private BlockStatement(BlockStatement blockStatement, Block block) {
    super(blockStatement);
    this.block = block;
  }
  
  public static BlockStatement createReplacement(Statement stmt, List<Statement> newStmts) {
    return createReplacement(stmt, stmt.getFinish(), newStmts);
  }
  
  public static BlockStatement createReplacement(Statement stmt, int finish, List<Statement> newStmts) {
    return new BlockStatement(stmt.getLineNumber(), new Block(stmt.getToken(), finish, newStmts));
  }
  
  public boolean isTerminal() {
    return this.block.isTerminal();
  }
  
  public boolean isSynthetic() {
    return this.block.isSynthetic();
  }
  
  public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterBlockStatement(this))
      return visitor.leaveBlockStatement(setBlock((Block)this.block.accept(visitor))); 
    return this;
  }
  
  public void toString(StringBuilder sb, boolean printType) {
    this.block.toString(sb, printType);
  }
  
  public Block getBlock() {
    return this.block;
  }
  
  public BlockStatement setBlock(Block block) {
    if (this.block == block)
      return this; 
    return new BlockStatement(this, block);
  }
}
