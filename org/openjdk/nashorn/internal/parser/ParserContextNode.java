package org.openjdk.nashorn.internal.parser;

import java.util.List;
import org.openjdk.nashorn.internal.ir.Statement;

interface ParserContextNode {
  int getFlags();
  
  int setFlag(int paramInt);
  
  List<Statement> getStatements();
  
  void setStatements(List<Statement> paramList);
  
  void appendStatement(Statement paramStatement);
  
  void prependStatement(Statement paramStatement);
}
