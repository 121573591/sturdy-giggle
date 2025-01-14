package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.parser.Token;
import org.openjdk.nashorn.internal.runtime.ParserException;

final class DiagnosticImpl implements Diagnostic {
  private final ParserException exp;
  
  private final Diagnostic.Kind kind;
  
  DiagnosticImpl(ParserException exp, Diagnostic.Kind kind) {
    this.exp = exp;
    this.kind = kind;
  }
  
  public Diagnostic.Kind getKind() {
    return this.kind;
  }
  
  public long getPosition() {
    return this.exp.getPosition();
  }
  
  public String getFileName() {
    return this.exp.getFileName();
  }
  
  public long getLineNumber() {
    return this.exp.getLineNumber();
  }
  
  public long getColumnNumber() {
    return this.exp.getColumnNumber();
  }
  
  public String getCode() {
    long token = this.exp.getToken();
    return (token < 0L) ? null : Token.toString(null, token, true);
  }
  
  public String getMessage() {
    return this.exp.getMessage();
  }
  
  public String toString() {
    return getMessage();
  }
}
