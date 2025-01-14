package org.openjdk.nashorn.api.tree;

public interface RegExpLiteralTree extends ExpressionTree {
  String getPattern();
  
  String getOptions();
}
