package org.apache.commons.io.file;

public class NoopPathVisitor extends SimplePathVisitor {
  public static final NoopPathVisitor INSTANCE = new NoopPathVisitor();
}
