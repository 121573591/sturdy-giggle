package org.openjdk.nashorn.api.tree;

public interface Diagnostic {
  public static final long NOPOS = -1L;
  
  Kind getKind();
  
  long getPosition();
  
  String getFileName();
  
  long getLineNumber();
  
  long getColumnNumber();
  
  String getCode();
  
  String getMessage();
  
  public enum Kind {
    ERROR, WARNING, MANDATORY_WARNING, NOTE, OTHER;
  }
}
