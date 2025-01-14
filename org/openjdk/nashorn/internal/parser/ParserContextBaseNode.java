package org.openjdk.nashorn.internal.parser;

import java.util.ArrayList;
import java.util.List;
import org.openjdk.nashorn.internal.ir.Statement;

abstract class ParserContextBaseNode implements ParserContextNode {
  protected int flags;
  
  private List<Statement> statements = new ArrayList<>();
  
  public int getFlags() {
    return this.flags;
  }
  
  protected int getFlag(int flag) {
    return this.flags & flag;
  }
  
  public int setFlag(int flag) {
    this.flags |= flag;
    return this.flags;
  }
  
  public List<Statement> getStatements() {
    return this.statements;
  }
  
  public void setStatements(List<Statement> statements) {
    this.statements = statements;
  }
  
  public void appendStatement(Statement statement) {
    this.statements.add(statement);
  }
  
  public void prependStatement(Statement statement) {
    this.statements.add(0, statement);
  }
}
